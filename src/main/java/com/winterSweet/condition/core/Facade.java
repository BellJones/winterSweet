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
 * Date: 2017/3/16 22:37
 * <p>
 * Description: provides a clean interface to the Condition APIs.
 */
public interface Facade {

    List condition(ICondition condition);

    List condition(Class<?> conditionClass, ICondition condition);

    int count(ICondition condition);

    int count(Class<?> conditionClass, ICondition condition);

    Result conditionAndCount(ICondition condition);

    Result conditionAndCount(Class<?> conditionClass, ICondition condition);

    Object conditionUnique(ICondition condition);

    Object conditionUnique(Class<?> conditionClass, ICondition condition);
}
