/*
 * Copyright [2017] [Butterfly Killer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.winterSweet.utils.excel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/2/23 下午 8:48
 * <p>
 * Version: 1.0
 * Description: 读取Excel文件工具
 * 支持读取的Excel文件为多sheet表结构，sheet名称为Map中的key，具体的内容放置在`List&lt;List&lt;Object&gt;&gt;`
 * : 第一层List为行row，第二层List为格cell
 * </p>
 */
public class ReadExcelUtil {

    private static Logger LOGGER = LogManager.getLogger(ReadExcelUtil.class.getName());

    public static void main(String[] args) {
        String path = "E://test.xls";
        System.out.println(readExcel(path));
    }

    /**
     * 通过Excel文件的存储路径读取文件内容
     * @param path 文件路径
     * @return 文件提取出来的集合
     */
    public static Map<String, List<List<Object>>> readExcel(String path) {
        File file = new File(path);
        if (!file.exists()) {
            LOGGER.debug("file does not existed error.");
            return null;
        }
        Workbook workbook = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (IOException | InvalidFormatException e) {
            LOGGER.error(e.getMessage());
        }
        return readSheet(workbook);
    }

    /**
     * 读取workbook的值
     * @param workbook Excel文件对象
     * @return 文件内容
     */
    private static Map<String, List<List<Object>>> readSheet(Workbook workbook) {
        Map<String, List<List<Object>>> sheets = Maps.newHashMap();
        // 获取文件中的sheet总数
        int total = workbook.getNumberOfSheets();
        for (int i = 0; i < total; i++) {
            // 根据sheet的序号获取sheet对象
            Sheet sheet = workbook.getSheetAt(i);
            String name = sheet.getSheetName();
            int lines = sheet.getPhysicalNumberOfRows();
            if (1 > lines) continue;
            List<List<Object>> content = readSheet(lines, sheet);
            sheets.put(name, content);
        }
        try {
            workbook.close();
        } catch (IOException e) {
            LOGGER.error("workbook closed error : {}", e.getMessage());
        }
        return sheets;
    }

    /**
     * 获取所有行的值，存储到List/<Object>中，既是整个sheet的值。
     * @param index 一个sheet中的总行数
     * @param sheet sheet对象
     * @return 所有行的集合
     */
    private static List<List<Object>> readSheet(int index, Sheet sheet) {
        List<List<Object>> rows = Lists.newArrayList();
        for (int i = 0; i < index; i++) {
            Row row = sheet.getRow(i);
            int total = row.getPhysicalNumberOfCells();
            List<Object> cells = readRow(total, row);
            rows.add(cells);
        }
        return rows;
    }

    /**
     * 遍历一行的所有Cell并存储到List/<String>中
     * @param index 一行中的cell的总数
     * @param row 行对象
     * @return 一行中的cell的值的集合
     */
    private static List<Object> readRow(int index, Row row) {
        List<Object> cells = Lists.newArrayList();
        for (int i = 0; i < index; i++) {
            Cell cell = row.getCell(i);
            Object object = readCell(cell);
            cells.add(object);
        }
        return cells;
    }

    /**
     * 对cell的值进行处理
     * @param cell cell对象
     * @return String字符串
     */
    @SuppressWarnings ("deprecation")
    private static Object readCell(Cell cell) {
        if (null == cell) return null;
        switch (cell.getCellTypeEnum()) {
            case _NONE: // 错误或未知类型等无法处理的东西
                return null;
            case NUMERIC: // 数值: 整数、分数、日期
                return cell.getNumericCellValue();
            case STRING: // 字符串
                return cell.getStringCellValue();
            case FORMULA: // 公式
                return cell.getCellFormula();
            case BLANK: // 空白
                return "";
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR: // Error cell type
                LOGGER.debug("GET AN ERROR CELL TYPE AT ::: " + cell.getAddress());
                return cell.getErrorCellValue();
            default:
                return null;
        }
    }
}
