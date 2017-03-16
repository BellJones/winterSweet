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

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/7 14:58
 * <p>
 * Description: SQL拼接主函数
 */
public class Condition implements ICondition {

    private int firstResult = -1;
    private int maxResults = -1;
    private int page = -1;
    private Class<?> conditionClass;
    private List<Filter> filters = Lists.newArrayList();
    private boolean disjunction;
    private List<Sort> sorts = Lists.newArrayList();
    private List<Field> fields = Lists.newArrayList();
    private boolean distinct;
    private List<String> fetches = Lists.newArrayList();
    private int resultMode = RESULT_AUTO;

    public Condition() {
    }

    public Condition(Class<?> conditionClass) {
        this.conditionClass = conditionClass;
    }

    @Override
    public int getFirstResult() {
        return firstResult;
    }

    @Override
    public ICondition setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public ICondition setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public ICondition setPage(int page) {
        this.page = page;
        return this;
    }

    public void clearPaging() {
        ConditionUtil.clearPaging(this);
    }

    @Override
    public Class<?> getConditionClass() {
        return conditionClass;
    }

    @Override
    public ICondition setConditionClass(Class<?> conditionClass) {
        this.conditionClass = conditionClass;
        return this;
    }

    public Condition addFilter(Filter filter) {
        ConditionUtil.addFilter(this, filter);
        return this;
    }

    public Condition addFilters(Filter... filters) {
        ConditionUtil.addFilters(this, filters);
        return this;
    }

    public Condition addFilterEqual(String property, String value) {
        ConditionUtil.addFilterEqual(this, property, value);
        return this;
    }

    public Condition addFilterNotEqual(String property, String value) {
        ConditionUtil.addFilterNotEqual(this, property, value);
        return this;
    }

    public Condition addFilterLike(String property, String value) {
        ConditionUtil.addFilterLike(this, property, value);
        return this;
    }

    public Condition addFilterLeftLike(String property, String value) {
        ConditionUtil.addFilterLeftLike(this, property, value);
        return this;
    }

    public Condition addFilterRightLike(String property, String value) {
        ConditionUtil.addFilterRightLike(this, property, value);
        return this;
    }

    public Condition addFilterAllLike(String property, String value) {
        ConditionUtil.addFilterAllLike(this, property, value);
        return this;
    }

    public Condition addFilterGreatThan(String property, String value) {
        ConditionUtil.addFilterGreatThan(this, property, value);
        return this;
    }

    public Condition addFilterLessThan(String property, String value) {
        ConditionUtil.addFilterLessThan(this, property, value);
        return this;
    }

    public Condition addFilterGreatOrEqual(String property, String value) {
        ConditionUtil.addFilterGreatOrEqual(this, property, value);
        return this;
    }

    public Condition addFilterLessOrEqual(String property, String value) {
        ConditionUtil.addFilterLessOrEqual(this, property, value);
        return this;
    }

    public Condition addFilterIsNull(String property) {
        ConditionUtil.addFilterIsNull(this, property);
        return this;
    }

    public Condition addFilterIsNotNull(String property) {
        ConditionUtil.addFilterIsNotNull(this, property);
        return this;
    }

    public Condition addFilterAnd(Filter... filters) {
        ConditionUtil.addFilterAnd(this, filters);
        return this;
    }

    public Condition addFilterOr(Filter... filters) {
        ConditionUtil.addFilterOr(this, filters);
        return this;
    }

    public void removeFilter(Filter filter) {
        ConditionUtil.removeFilter(this, filter);
    }

    public void clearFilters() {
        ConditionUtil.clearFilters(this);
    }

    public List<Filter> getFilters() {
        return filters;
    }

    @Override
    public ICondition setFilters(List<Filter> filter) {
        this.filters = filter;
        return this;
    }

    @Override
    public boolean isDisjunction() {
        return disjunction;
    }

    // 默认为false,既使用AND拼接,为true时会使用OR进行拼接
    @Override
    public ICondition setDisjunction(boolean disjunction) {
        this.disjunction = disjunction;
        return this;
    }

    public Condition addSort(Sort sort) {
        ConditionUtil.addSort(this, sort);
        return this;
    }

    public Condition addSorts(Sort... sorts) {
        ConditionUtil.addSorts(this, sorts);
        return this;
    }

    public Condition addSortAsc(String property) {
        ConditionUtil.addSortAsc(this, property);
        return this;
    }

    public Condition addSortAsc(String property, boolean ignoreCase) {
        ConditionUtil.addSortAsc(this, property, ignoreCase);
        return this;
    }

    public Condition addSortDesc(String property) {
        ConditionUtil.addSortDesc(this, property);
        return this;
    }

    public Condition addSortDesc(String property, boolean ignoreCase) {
        ConditionUtil.addSortDesc(this, property, ignoreCase);
        return this;
    }

    public Condition addSort(String property, boolean desc) {
        ConditionUtil.addSort(this, property, desc);
        return this;
    }

    public Condition addSort(String property, boolean desc, boolean ignoreCase) {
        ConditionUtil.addSort(this, property, desc, ignoreCase);
        return this;
    }

    public void removeSort(Sort sort) {
        ConditionUtil.removeSort(this, sort);
    }

    public void removeSort(String property) {
        ConditionUtil.removeSort(this, property);
    }

    public void clearSort() {
        ConditionUtil.clearSort(this);
    }

    @Override
    public List<Sort> getSorts() {
        return sorts;
    }

    @Override
    public ICondition setSorts(List<Sort> sort) {
        this.sorts = sort;
        return this;
    }

    public Condition addField(Field field) {
        ConditionUtil.addField(this, field);
        return this;
    }

    public Condition addFields(Field... fields) {
        ConditionUtil.addFields(this, fields);
        return this;
    }

    public Condition addField(String property) {
        ConditionUtil.addField(this, property);
        return this;
    }

    public Condition addField(String property, String key) {
        ConditionUtil.addField(this, property, key);
        return this;
    }

    public Condition addField(String property, int operator) {
        ConditionUtil.addField(this, property, operator);
        return this;
    }

    public Condition addField(String property, int operator, String key) {
        ConditionUtil.addField(this, property, operator, key);
        return this;
    }

    public void removeField(Field field) {
        ConditionUtil.removeField(this, field);
    }

    public void removeField(String property) {
        ConditionUtil.removeField(this, property);
    }

    public void removeField(String property, String key) {
        ConditionUtil.removeField(this, property, key);
    }

    public void clearFields() {
        ConditionUtil.clearFields(this);
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }

    @Override
    public ICondition setFields(List<Field> field) {
        this.fields = field;
        return this;
    }

    @Override
    public boolean isDistinct() {
        return distinct;
    }

    @Override
    public ICondition setDistinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    public Condition addFetch(String property) {
        ConditionUtil.addFetch(this, property);
        return this;
    }

    public Condition addFetches(String... properties) {
        ConditionUtil.addFetches(this, properties);
        return this;
    }

    public void removeFetch(String property) {
        ConditionUtil.removeFetch(this, property);
    }

    public void clearFetchs() {
        ConditionUtil.clearFetches(this);
    }

    @Override
    public List<String> getFetches() {
        return fetches;
    }

    @Override
    public ICondition setFetches(List<String> fetches) {
        this.fetches = fetches;
        return this;
    }

    @Override
    public int getResultMode() {
        return resultMode;
    }

    @Override
    public ICondition setResultMode(int resultMode) {
        if (0 > resultMode || 4 < resultMode) {
            throw new IllegalArgumentException("Result Mode ( " + resultMode + " ) is not a valid option.");
        } else {
            this.resultMode = resultMode;
            return this;
        }
    }

    public void clear() {
        ConditionUtil.clear(this);
    }

    public Condition copy() {
        Condition dest = new Condition();
        ConditionUtil.copy(this, dest);
        return dest;
    }

    @Override
    public boolean equals(Object object) {
        return ConditionUtil.equals(this, object);
    }

    @Override
    public int hashCode() {
        return ConditionUtil.hashCode(this);
    }

    @Override
    public String toString() {
        return ConditionUtil.toString(this);
    }
}
