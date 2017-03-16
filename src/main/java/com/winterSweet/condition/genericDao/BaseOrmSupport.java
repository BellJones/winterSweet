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
 *
 */

package com.winterSweet.condition.genericDao;

import com.winterSweet.condition.mapping.Table;
import com.winterSweet.condition.mapping.TableField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/14 20:59
 * <p>
 * Description: TODO
 */
public abstract class BaseOrmSupport<T> {

    public void save(T o) {
        Class<? extends Object> entityClass = o.getClass();
        String sql = "INSERT INTO ";
        Table table = (Table)entityClass.getAnnotation(Table.class);
        if (null != table) {
            sql += table.tableName();
        } else {
            sql += entityClass.getName().substring(entityClass.getName().lastIndexOf(".")+1);
        }
        sql += " (";

        Field[] fields = entityClass.getDeclaredFields();
        String valueSql = "";
        boolean flag = false;
        for (Field field : fields) {
            if (flag) {
                sql += ",";
            }
            TableField tableField = field.getAnnotation(TableField.class);
            // 获得字段第一个字母大写
            String firstLetter = field.getName().substring(0, 1).toUpperCase();
            // 转换成字段的get方法
            String getMethodName = "get" + firstLetter + field.getName().substring(1);

            try {
                Method getMethod = entityClass.getMethod(getMethodName, new Class[] {});
                //这个对象字段get方法的值
                Object value = getMethod.invoke(o, new Object[] {});
                if (field.getType().getName().equals(java.lang.String.class.getName())) {
                    valueSql += " '" + value + "'";
                } else {
                    valueSql += " " + value;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (null != tableField) {
                sql += " " + tableField.name();
            } else {
                sql += " " + field.getName();
            }
            flag = true;
        }
        sql += ") VALUES (" + valueSql + ")";
        System.out.println(sql);
    }
}
