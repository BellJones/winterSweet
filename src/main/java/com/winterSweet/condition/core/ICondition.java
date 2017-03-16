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

package com.winterSweet.condition.core;

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
    int RESULT_AUTO = 0; // 这是默认的ResultMode，会根据情况分别选择以下四种模式
    int RESULT_ARRAY = 1; // 字符数组格式
    int RESULT_LIST = 2; // 集合格式
    int RESULT_MAP = 3; // 键值对格式
    int RESULT_SINGLE = 4; // 单个数据或无返回数据

    int getFirstResult();

    ICondition setFirstResult(int firstResult);

    int getMaxResults();

    ICondition setMaxResults(int maxResults);

    int getPage();

    ICondition setPage(int page);

    Class<?> getConditionClass(); // 获取操作类

    ICondition setConditionClass(Class<?> conditionClass);

    List<Filter> getFilters(); // 搜索条件

    ICondition setFilters(List<Filter> filter);

    boolean isDisjunction();

    ICondition setDisjunction(boolean disjunction);

    List<Sort> getSorts();

    ICondition setSorts(List<Sort> sort);

    List<Field> getFields(); // 操作函数

    ICondition setFields(List<Field> field);

    boolean isDistinct();

    ICondition setDistinct(boolean distinct);

    List<String> getFetches();

    ICondition setFetches(List<String> fetches);

    /**
     * @see #RESULT_AUTO
     * @see #RESULT_ARRAY
     * @see #RESULT_LIST
     * @see #RESULT_MAP
     * @see #RESULT_SINGLE
     */
    int getResultMode(); // 返回数据格式模型

    ICondition setResultMode(int resultMode);
}
