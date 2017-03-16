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

package com.winterSweet.condition.core;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA <p>
 * User: Butterfly Killer <p>
 * Date: 2017/3/7 14:53 <p>
 * Version: 1.0 <p>
 * <p>
 * Description: 操作函数
 * <p>
 * DEFAULT \ COUNT \ COUNT_DISTINCT \ MAX \ MIN \ SUM \ AVG
 *
 * @see Condition
 */
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    // 默认为不使用函数
    private static final int OP_PROPERTY = 0;
    // 返回某列的行数（不包括 NULL 值）
    private static final int OP_COUNT = 1;
    // 返回指定列的不同值的数目
    private static final int OP_COUNT_DISTINCT = 2;
    // 返回某列的最高值
    private static final int OP_MAX = 3;
    // 返回某列的最低值
    private static final int OP_MIN = 4;
    // 返回某列的总和
    private static final int OP_SUM = 5;
    // 返回某列的平均值, NULL 值不包括在计算中
    private static final int OP_AVG = 6;

    private String property; // 函数操作对象
    private String key; // 别名
    private int operator = 0;

    public Field() {
    }

    public Field(String property) {
        this.property = property;
    }

    public Field(String property, String key) {
        this.property = property;
        this.key = key;
    }

    public Field(String property, int operator) {
        this.property = property;
        this.operator = operator;
    }

    public Field(String property, int operator, String key) {
        this.property = property;
        this.key = key;
        this.operator = operator;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        if (null == property || "".equals(property.trim())) return "";
        String nickName;
        if (null != key && !"".equals(key)) {
            nickName = " AS " + key;
        } else {
            nickName = "";
        }
        switch (operator) {
            case OP_PROPERTY:
                return property;
            case OP_COUNT:
                return "COUNT ( " + property + " )" + nickName;
            case OP_COUNT_DISTINCT:
                return "COUNT ( DISTINCT " + property + " )" + nickName;
            case OP_MAX:
                return "MAX ( " + property + " )" + nickName;
            case OP_MIN:
                return "MIN ( " + property + " )" + nickName;
            case OP_SUM:
                return "SUM ( " + property + " )" + nickName;
            case OP_AVG:
                return "avg ( " + property + " )" + nickName;
            default:
                return property;
        }
    }
}
