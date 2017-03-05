package winterSweetTest;
import com.winterSweet.util.ReadExcelUtil;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/6 上午 12:08
 * <p>
 * Version: 1.0
 * Description: 测试Excel文件读取和书写工具
 */
public class ExcelTest {

    public static void main(String[] args) {
        String path = "E://test.xls";
        Map<String, List<List<Object>>> excel = ReadExcelUtil.readExcel(path);
        System.out.println(excel);
    }
}
