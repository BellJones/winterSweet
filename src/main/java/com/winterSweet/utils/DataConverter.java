/*
 * Copyright 2017 [Butterfly Killer]
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
 *
 */

package com.winterSweet.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/11 21:26
 * <p>
 * Description: 通用的数据转换操作函数
 */
public class DataConverter {

    private static Logger LOGGER = LogManager.getLogger(DataConverter.class.getName());

    private DataConverter() {
    }

    /**
     * 用来将DB中获取的字符串转换成时间戳 "2013-10-24" -> 1382544000000L
     */
    public static long stringToTime(String dateString) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date currentDate = dateformat.parse(dateString);
            return currentDate.getTime();
        } catch (ParseException e) {
            LOGGER.error("Invalid date in db, message: {}", e.getMessage());
            return 0;
        }
    }

    /**
     * 按输入时间段生成缺省的 map<timestamp, 0>, 每天一条记录
     */
    public static Map<Long, Long> makeDefault(Date startDate, Date endDate) {
        long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
        long endTime = DateTimeUtil.getStartOfDay(endDate).getTime();
        long curTime = DateTimeUtil.getStartOfDay(startDate).getTime();
        Map<Long, Long> defaultData = Maps.newTreeMap();
        while (curTime <= endTime) {
            defaultData.put(curTime, 0L);
            curTime += interval;
        }
        return defaultData;
    }

    /**
     * {k1:v1,k2:v2} => [[k1,v1],[k2,v2]]
     */
    public static <T> List<List<T>> mapToList(Map<T, T> input) {
        List<List<T>> list = Lists.newArrayList();
        for (Map.Entry<T, T> e : input.entrySet()) {
            List<T> item = Lists.newArrayList();
            item.add(e.getKey());
            item.add(e.getValue());
            list.add(item);
        }
        return list;
    }

    /**
     * string.join 操作
     */
    public static <T> String join(String join, Collection<T> collections) {
        String result = "";
        for (T item : collections) {
            if (result.length() > 0) {
                result += join;
            }
            result += item;
        }
        return result;
    }
}
