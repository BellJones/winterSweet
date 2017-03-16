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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA <p>
 * User: Butterfly Killer <p>
 * Date: 2017/3/7 13:21 <p>
 * Version: 1.0 <p>
 * <p>
 * Description: SQL中的过滤条件
 */
public class Filter implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int OP_NOT = 42;
    private static final int OP_EQUAL = 1;
    private static final int OP_NOT_EQUAL = -1;
    private static final int OP_LIKE = 10;
    private static final int OP_LEFT_LIKE = 11;
    private static final int OP_RIGHT_LIKE = 12;
    private static final int OP_ALL_LIKE = 13;
    private static final int OP_GREATER_THAN = 20;
    private static final int OP_LESS_THAN = 21;
    private static final int OP_GREATER_OR_EQUAL = 22;
    private static final int OP_LESS_OR_EQUAL = 23;
    private static final int OP_IS_NULL = 30;
    private static final int OP_IS_NOT_NULL = 31;
    private static final int OP_AND = 40;
    private static final int OP_OR = 41;
    // 范围值,使用 [BETWEEN...AND] 语句,左右全包含
    private static final int OP_RANGE_INT = 100;
    private static final int OP_RANGE_DATE = 102;
    // 用户自定义模式
    private static final int OP_CUSTOMER = 200;
    /**
     * 过滤条件参数
     */
    private String property;
    /**
     * 过滤条件参数对应的值
     */
    private Object value;
    /**
     * 过滤方式
     */
    private int operator;

    public Filter() {
    }

    public Filter(String property, Object value, int operator) {
        this.property = property;
        this.value = value;
        this.operator = operator;
    }

    // =
    public static Filter equal(String property, Object value) {
        return new Filter(property, value, OP_EQUAL);
    }

    // !=
    public static Filter notEqual(String property, Object value) {
        return new Filter(property, value, OP_NOT_EQUAL);
    }

    // LIKE
    public static Filter like(String property, Object value) {
        return new Filter(property, value, OP_LIKE);
    }

    // LIKE '%XXX'
    public static Filter leftLike(String property, Object value) {
        return new Filter(property, value, OP_LEFT_LIKE);
    }

    // LIKE 'XXX%'
    public static Filter rightLike(String property, Object value) {
        return new Filter(property, value, OP_RIGHT_LIKE);
    }

    // LIKE '%XXX%'
    public static Filter allLike(String property, Object value) {
        return new Filter(property, value, OP_ALL_LIKE);
    }

    // >
    public static Filter greatThan(String property, Object value) {
        return new Filter(property, value, OP_GREATER_THAN);
    }

    // <
    public static Filter lessThan(String property, Object value) {
        return new Filter(property, value, OP_LESS_THAN);
    }

    // >=
    public static Filter greatOrEqual(String property, Object value) {
        return new Filter(property, value, OP_GREATER_OR_EQUAL);
    }

    // <=
    public static Filter lessOrEqual(String property, Object value) {
        return new Filter(property, value, OP_LESS_OR_EQUAL);
    }

    // IS NULL
    public static Filter isNull(String property) {
        return new Filter(property, true, OP_IS_NULL);
    }

    // IS NOT NULL
    public static Filter isNotNull(String property) {
        return new Filter(property, true, OP_IS_NOT_NULL);
    }

    // AND ...
    public static Filter and(Filter... filters) {
        Filter filter = new Filter("AND", null, OP_AND);
        for (Filter f : filters) {
            filter.add(f);
        }
        return filter;
    }

    // OR ...
    public static Filter or(Filter... filters) {
        Filter filter = and(filters);
        filter.property = "OR";
        filter.operator = OP_OR;
        return filter;
    }

    // NOT ...
    public static Filter not(Filter filter) {
        return new Filter("NOT", filter, OP_NOT);
    }

    // BETWEEN 123 AND 456
    public static Filter rangeInt(String property, Object value) {
        return new Filter(property, value, OP_RANGE_INT);
    }

    // BETWEEN TO_DATE(TIME_A, 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE(TIME_B, 'yyyy-mm-dd hh24:mi:ss')
    public static Filter rangeDate(String property, Object value) {
        return new Filter(property, value, OP_RANGE_DATE);
    }

    // ba la ba la
    public static Filter customer(String property, Object value) {
        return new Filter(property, value, OP_CUSTOMER);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    @SuppressWarnings("unchecked")
    public void add(Filter filter) {
        if (null != value && value instanceof List) {
            ((List) value).add(filter);
        } else {
            value = new ArrayList();
        }
    }

    @Override
    public String toString() {
        switch (operator) {
            case Filter.OP_EQUAL:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " = '" + value + "'";
            case Filter.OP_NOT_EQUAL:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " != '" + value + "'";
            case Filter.OP_LIKE:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " LIKE '" + value + "'";
            case Filter.OP_LEFT_LIKE:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " LIKE '%" + value + "'";
            case Filter.OP_RIGHT_LIKE:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " LIKE '" + value + "%'";
            case Filter.OP_ALL_LIKE:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " LIKE '%" + value + "%'";
            case Filter.OP_GREATER_THAN:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " > '" + value + "'";
            case Filter.OP_LESS_THAN:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " < '" + value + "'";
            case Filter.OP_GREATER_OR_EQUAL:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " >= '" + value + "'";
            case Filter.OP_LESS_OR_EQUAL:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " <= '" + value + "'";
            case Filter.OP_IS_NULL:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " IS NULL";
            case Filter.OP_IS_NOT_NULL:
                if (null == value || "".equals(value.toString().trim())) return "";
                return property + " IS NOT NULL";
            case Filter.OP_AND:
            case Filter.OP_OR:
                if (null == value || !(value instanceof List)) return "";
                String op = operator == Filter.OP_AND ? " AND " : " OR ";
                StringBuilder builder = new StringBuilder();
                boolean first = true;
                for (Object object : ((List) value)) {
                    String param = object.toString().trim();
                    if ("".equals(param)) continue;
                    if (first) first = false;
                    else builder.append(op);
                    if (object instanceof Filter) builder.append(object.toString());
                    else builder.append("**INVALID VALUE - NOT A FILTER: (" + object + ") **");
                }
                if (first) return (operator == Filter.OP_AND ? "AND: " : "OR: ") + "**EMPTY LIST**";
                return builder.toString();
            case Filter.OP_NOT:
                if (value instanceof Filter) return "NOT " + value.toString();
            case Filter.OP_RANGE_INT:
                if (null == value || "".equals(value.toString().trim())) return "";
                if (!value.toString().contains("_")) return new Filter(property, value, OP_EQUAL).toString();
                String[] param = value.toString().split("_");
                String min = param[0].trim();
                String max = param[1].trim();
                if ("".equals(min)) return new Filter(property, max, OP_LESS_OR_EQUAL).toString();
                if ("".equals(max)) return new Filter(property, min, OP_GREATER_OR_EQUAL).toString();
                return property + " BETWEEN '" + min + "' AND '" + max + "'";
            case Filter.OP_RANGE_DATE:
                if (null == value || "".equals(value.toString().trim())) return "";
                if (!value.toString().contains("_"))
                    return property + " = TO_DATE('" + value + "', 'yyyy-mm-dd hh24:mi:ss')";
                String[] dates = value.toString().split("_");
                String minDate = dates[0].trim();
                String maxDate = dates[1].trim();
                if ("".equals(minDate)) minDate = "TO_DATE('0001-01-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS')";
                if ("".equals(maxDate)) maxDate = "sysdate";
                return property + " BETWEEN TO_DATE('" + minDate + "', 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE('" + maxDate + "', 'yyyy-mm-dd hh24:mi:ss')";
            case Filter.OP_CUSTOMER:
                return property + " " + value;
            default:
                return "";
        }
    }
}
