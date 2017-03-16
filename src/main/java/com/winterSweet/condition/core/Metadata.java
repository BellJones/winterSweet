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

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/16 22:47
 * <p>
 * Description: TODO
 */
public interface Metadata {

    boolean isEntity();

    boolean isCollection();

    boolean isString();

    boolean isNumeric();

    Class<?> getJavaClass();

    String getEntityName();

    String[] getProperties();

    Object getPropertyValue(Object object, String property);

    Metadata getPropertyType(String property);

    String getIdProperty();

    Metadata getIdType();

    Object getIdValue(Object object);

    Class<?> getCollectionClass();
}
