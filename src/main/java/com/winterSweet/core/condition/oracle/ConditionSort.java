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

package com.winterSweet.core.condition.oracle;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/7 15:52
 * <p>
 * Description: 排序
 */
public class ConditionSort implements Serializable {

    private static final long serialVersionUID = 1L;

    private String property;
    private boolean desc = false;
    private boolean ignoreCase = false;

    public ConditionSort() {
    }

    public ConditionSort(String property) {
        this.property = property;
    }

    public ConditionSort(String property, boolean desc) {
        this.property = property;
        this.desc = desc;
    }

    public ConditionSort(String property, boolean desc, boolean ignoreCase) {
        this.property = property;
        this.desc = desc;
        this.ignoreCase = ignoreCase;
    }

    public static ConditionSort asc(String property) {
        return new ConditionSort(property);
    }

    public static ConditionSort asc(String property, boolean ignoreCase) {
        return new ConditionSort(property, false, ignoreCase);
    }

    public static ConditionSort desc(String property) {
        return new ConditionSort(property, true);
    }

    public static ConditionSort desc(String property, boolean ignoreCase) {
        return new ConditionSort(property, true, ignoreCase);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public String toString() {
        if (ignoreCase) {
            return null;
        }
        String value;
        if (null == property) {
            value = "NULL";
        } else {
            value = property;
        }
        return value + (desc ? " DESC" : " ASC");
    }
}
