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

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

import static com.winterSweet.condition.core.ICondition.*;

/**
 * Created with IntelliJ IDEA
 * User: Butterfly Killer
 * Date: 2017/3/16 20:18
 * <p>
 * Description: TODO
 */
class ConditionUtil {

    static void clearPaging(ICondition condition) {
        condition.setFirstResult(-1);
        condition.setPage(-1);
        condition.setMaxResults(-1);
    }

    static void addFilter(ICondition condition, Filter filter) {
        List<Filter> filters = condition.getFilters();
        if (null == filters) {
            filters = Lists.newArrayList();
            condition.setFilters(filters);
        }
        filters.add(filter);
    }

    static void addFilters(ICondition condition, Filter... filters) {
        if (null != filters) {
            for (Filter filter : filters) {
                addFilter(condition, filter);
            }
        }
    }

    static void addFilterEqual(ICondition condition, String property, String value) {
        addFilter(condition, Filter.equal(property, value));
    }

    static void addFilterNotEqual(ICondition condition, String property, String value) {
        addFilter(condition, Filter.notEqual(property, value));
    }

    static void addFilterLike(ICondition condition, String property, String value) {
        addFilter(condition, Filter.like(property, value));
    }

    static void addFilterLeftLike(ICondition condition, String property, String value) {
        addFilter(condition, Filter.leftLike(property, value));
    }

    static void addFilterRightLike(ICondition condition, String property, String value) {
        addFilter(condition, Filter.rightLike(property, value));
    }

    static void addFilterAllLike(ICondition condition, String property, String value) {
        addFilter(condition, Filter.allLike(property, value));
    }

    static void addFilterGreatThan(ICondition condition, String property, String value) {
        addFilter(condition, Filter.greatThan(property, value));
    }

    static void addFilterLessThan(ICondition condition, String property, String value) {
        addFilter(condition, Filter.lessThan(property, value));
    }

    static void addFilterGreatOrEqual(ICondition condition, String property, String value) {
        addFilter(condition, Filter.greatOrEqual(property, value));
    }

    static void addFilterLessOrEqual(ICondition condition, String property, String value) {
        addFilter(condition, Filter.lessOrEqual(property, value));
    }

    static void addFilterIsNull(ICondition condition, String property) {
        addFilter(condition, Filter.isNull(property));
    }

    static void addFilterIsNotNull(ICondition condition, String property) {
        addFilter(condition, Filter.isNotNull(property));
    }

    static void addFilterAnd(ICondition condition, Filter... filters) {
        addFilter(condition, Filter.and(filters));
    }

    static void addFilterOr(ICondition condition, Filter... filters) {
        addFilter(condition, Filter.or(filters));
    }

    static void removeFilter(ICondition condition, Filter filter) {
        List<Filter> filters = condition.getFilters();
        if (null != filters) {
            filters.remove(filter);
        }
    }

    static void clearFilters(ICondition condition) {
        if (null != condition.getFilters()) {
            condition.getFilters().clear();
        }
    }

    static void addSort(ICondition condition, Sort sort) {
        if (null == sort) {
            return;
        }
        List<Sort> sorts = condition.getSorts();
        if (null == sorts) {
            sorts = Lists.newArrayList();
            condition.setSorts(sorts);
        }
        sorts.add(sort);
    }

    static void addSort(ICondition condition, String property, boolean desc, boolean ignoreCase) {
        if (null == property) return;
        addSort(condition, new Sort(property, desc, ignoreCase));
    }

    static void addSorts(Condition condition, Sort[] sorts) {
        if (null != sorts) {
            for (Sort sort : sorts) {
                addSort(condition, sort);
            }
        }
    }

    static void addSortAsc(Condition condition, String property) {
        addSort(condition, property, false, false);
    }

    static void addSortAsc(Condition condition, String property, boolean ignoreCase) {
        addSort(condition, property, false, ignoreCase);
    }

    static void addSort(Condition condition, String property, boolean desc) {
        addSort(condition, property, desc, false);
    }

    static void addSortDesc(Condition condition, String property) {
        addSort(condition, property, true, false);
    }

    static void addSortDesc(Condition condition, String property, boolean ignoreCase) {
        addSort(condition, property, true, ignoreCase);
    }

    static void removeSort(Condition condition, Sort sort) {
        if (null != condition.getSorts()) {
            condition.getSorts().remove(sort);
        }
    }

    static void removeSort(Condition condition, String property) {
        if (property == null || condition.getSorts() == null) {
            return;
        }
        Iterator<Sort> iterator = condition.getSorts().iterator();
        while (iterator.hasNext()) {
            if (property.equals(iterator.next().getProperty())) {
                iterator.remove();
            }
        }
    }

    static void clearSort(Condition condition) {
        if (null != condition.getSorts()) {
            condition.getSorts().clear();
        }
    }

    static void addField(Condition condition, Field field) {
        List<Field> fields = condition.getFields();
        if (fields == null) {
            fields = Lists.newArrayList();
            condition.setFields(fields);
        }
        fields.add(field);
    }

    public static void addFields(Condition condition, Field... fields) {
        if (null != condition.getFilters()) {
            for (Field field : fields) {
                addField(condition, field);
            }
        }
    }

    static void addField(Condition condition, String property) {
        if (null == property) return;
        addField(condition, new Field(property));
    }

    static void addField(Condition condition, String property, String key) {
        if (null == property) return;
        addField(condition, new Field(property, key));
    }

    static void addField(Condition condition, String property, int operator) {
        if (null == property) return;
        addField(condition, new Field(property, operator));
    }

    static void addField(Condition condition, String property, int operator, String key) {
        if (null == property) return;
        addField(condition, new Field(property, operator, key));
    }

    public static void removeField(Condition condition, Field field) {
        if (null != condition.getFields()) {
            condition.getFields().remove(field);
        }
    }

    public static void removeField(Condition condition, String property) {
        if (null == condition.getFields()) return;
        Iterator<Field> iterator = condition.getFields().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getProperty().equals(property)) {
                iterator.remove();
            }
        }
    }

    public static void removeField(Condition condition, String property, String key) {
        if (null == condition.getFields()) return;
        Iterator<Field> iterator = condition.getFields().iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (field.getProperty().equals(property) && field.getKey().equals(key)) {
                iterator.remove();
            }
        }
    }

    public static void clearFields(Condition condition) {
        if (null != condition.getFields()) {
            condition.getFields().clear();
        }
    }

    public static void addFetch(Condition condition, String property) {
        if (null == property || "".equals(property)) return;
        List<String> fetches = condition.getFetches();
        if (null == fetches) {
            fetches = Lists.newArrayList();
            condition.setFetches(fetches);
        }
        fetches.add(property);
    }

    public static void addFetches(Condition condition, String... properties) {
        if (null != properties) {
            for (String property : properties) {
                addFetch(condition, property);
            }
        }
    }

    public static void removeFetch(Condition condition, String property) {
        if (null != condition.getFetches()) {
            condition.getFetches().remove(property);
        }
    }

    public static void clearFetches(Condition condition) {
        if (null != condition.getFetches()) {
            condition.getFetches().clear();
        }
    }

    public static void clear(Condition condition) {
        clearFilters(condition);
        clearSort(condition);
        clearFields(condition);
        clearPaging(condition);
        clearFetches(condition);
        condition.setResultMode(RESULT_AUTO);
        condition.setDisjunction(false);
    }

    public static ICondition shallowCopy(ICondition source, ICondition destination) {
        destination.setConditionClass(source.getConditionClass());
        destination.setDistinct(source.isDistinct());
        destination.setDisjunction(source.isDisjunction());
        destination.setResultMode(source.getResultMode());
        destination.setFirstResult(source.getFirstResult());
        destination.setPage(source.getPage());
        destination.setMaxResults(source.getMaxResults());
        destination.setFetches(source.getFetches());
        destination.setFields(source.getFields());
        destination.setFilters(source.getFilters());
        destination.setSorts(source.getSorts());
        return destination;
    }

    public static ICondition shallowCopy(ICondition source) {
        return shallowCopy(source, new Condition());
    }

    public static <T extends ICondition> T copy(ICondition source, T destination) {
        shallowCopy(source, destination);

        List<String> fetches = Lists.newArrayList();
        fetches.addAll(source.getFetches());
        destination.setFetches(fetches);

        List<Field> fields = Lists.newArrayList();
        fields.addAll(source.getFields());
        destination.setFields(fields);

        List<Filter> filters = Lists.newArrayList();
        filters.addAll(source.getFilters());
        destination.setFilters(filters);

        List<Sort> sorts = Lists.newArrayList();
        sorts.addAll(source.getSorts());
        destination.setSorts(sorts);

        return destination;
    }

    public static Condition copy(ICondition source) {
        return copy(source, new Condition());
    }

    static boolean equals(Condition condition, Object object) {
        if (condition == object) {
            return true;
        }
        if (!(object instanceof ICondition)) {
            return false;
        }
        ICondition s = (ICondition) object;
        if (null == condition.getConditionClass() ? null != s.getConditionClass() : !condition.getConditionClass().equals(s.getConditionClass())) {
            return false;
        } else if (condition.isDisjunction() != s.isDisjunction() || condition.getResultMode() != s.getResultMode()
                || condition.getFirstResult() != s.getFirstResult() || condition.getPage() != s.getPage()
                || condition.getMaxResults() != s.getMaxResults()) {
            return false;
        } else if (null == condition.getFetches() ? null != s.getFetches() : !condition.getFetches().equals(s.getFetches())) {
            return false;
        } else if (null == condition.getFields() ? null != s.getFields() : !condition.getFields().equals(s.getFields())) {
            return false;
        } else if (null == condition.getFilters() ? null != s.getFilters() : !condition.getFilters().equals(s.getFilters())) {
            return false;
        } else if (null == condition.getSorts() ? null != s.getSorts() : !condition.getSorts().equals(s.getSorts())) {
            return false;
        } else {
            return true;
        }
    }

    static int hashCode(Condition condition) {
        int hash = 1;
        hash = hash * 31 + (condition.getConditionClass() == null ? 0 : condition.getConditionClass().hashCode());
        hash = hash * 31 + (condition.getFields() == null ? 0 : condition.getFields().hashCode());
        hash = hash * 31 + (condition.getFilters() == null ? 0 : condition.getFilters().hashCode());
        hash = hash * 31 + (condition.getSorts() == null ? 0 : condition.getSorts().hashCode());
        hash = hash * 31 + (condition.isDisjunction() ? 1 : 0);
        hash = hash * 31 + (new Integer(condition.getResultMode()).hashCode());
        hash = hash * 31 + (new Integer(condition.getFirstResult()).hashCode());
        hash = hash * 31 + (new Integer(condition.getPage()).hashCode());
        hash = hash * 31 + (new Integer(condition.getMaxResults()).hashCode());
        return hash;
    }

    static String toString(Condition condition) {
        StringBuilder sb = new StringBuilder("Condition (");
        sb.append(condition.getConditionClass());
        sb.append(")[first: ").append(condition.getFirstResult());
        sb.append(", page: ").append(condition.getPage());
        sb.append(", max: ").append(condition.getMaxResults());
        sb.append("] {\n resultMode: ");
        switch (condition.getResultMode()) {
            case RESULT_AUTO:
                sb.append("AUTO");
                break;
            case RESULT_ARRAY:
                sb.append("ARRAY");
                break;
            case RESULT_LIST:
                sb.append("LIST");
                break;
            case RESULT_MAP:
                sb.append("MAP");
                break;
            case RESULT_SINGLE:
                sb.append("SINGLE");
                break;
            default:
                sb.append("**INVALID RESULT MODE: (" + condition.getResultMode() + ")**");
                break;
        }
        sb.append(",\n disjunction: ").append(condition.isDisjunction());
        sb.append(",\n fields: { ");
        appendList(sb, condition.getFields(), ", ");
        sb.append(" },\n filters: {\n  ");
        appendList(sb, condition.getFilters(), ",\n  ");
        sb.append("\n },\n sorts: { ");
        appendList(sb, condition.getSorts(), ", ");
        sb.append(" }\n}");
        return sb.toString();
    }

    private static void appendList(StringBuilder sb, List<?> list, String separator) {
        if (list == null) {
            sb.append("null");
            return;
        }
        boolean first = true;
        for (Object o : list) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            sb.append(o);
        }
    }
}
