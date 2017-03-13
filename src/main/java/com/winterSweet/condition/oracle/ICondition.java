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

package com.winterSweet.condition.oracle;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/11 21:52
 * <p>
 * Description: Condition接口，定义condition的方法
 */
public interface ICondition {

    // 数据返回格式定义
    int RESULT_AUTO = 0; // 默认为JSON格式
    int RESULT_ARRAY = 1; // 字符串格式
    int RESULT_LIST = 2; // list格式
    int RESULT_MAP = 3; // map键值对格式
    int RESULT_SINGLE = 4; // 单列数据

    Class<?> getBaseClass(); // 获取操作类

    List<ConditionFilter> getConditionFilters(); // 搜索条件

    List<ConditionField> getConditionFields(); // 操作函数

    int getResultMode(); // 返回数据格式模型
}
