package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class CommonInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommonInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andJkeyIsNull() {
            addCriterion("jkey is null");
            return (Criteria) this;
        }

        public Criteria andJkeyIsNotNull() {
            addCriterion("jkey is not null");
            return (Criteria) this;
        }

        public Criteria andJkeyEqualTo(String value) {
            addCriterion("jkey =", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyNotEqualTo(String value) {
            addCriterion("jkey <>", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyGreaterThan(String value) {
            addCriterion("jkey >", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyGreaterThanOrEqualTo(String value) {
            addCriterion("jkey >=", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyLessThan(String value) {
            addCriterion("jkey <", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyLessThanOrEqualTo(String value) {
            addCriterion("jkey <=", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyLike(String value) {
            addCriterion("jkey like", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyNotLike(String value) {
            addCriterion("jkey not like", value, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyIn(List<String> values) {
            addCriterion("jkey in", values, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyNotIn(List<String> values) {
            addCriterion("jkey not in", values, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyBetween(String value1, String value2) {
            addCriterion("jkey between", value1, value2, "jkey");
            return (Criteria) this;
        }

        public Criteria andJkeyNotBetween(String value1, String value2) {
            addCriterion("jkey not between", value1, value2, "jkey");
            return (Criteria) this;
        }

        public Criteria andJvalueIsNull() {
            addCriterion("jvalue is null");
            return (Criteria) this;
        }

        public Criteria andJvalueIsNotNull() {
            addCriterion("jvalue is not null");
            return (Criteria) this;
        }

        public Criteria andJvalueEqualTo(String value) {
            addCriterion("jvalue =", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueNotEqualTo(String value) {
            addCriterion("jvalue <>", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueGreaterThan(String value) {
            addCriterion("jvalue >", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueGreaterThanOrEqualTo(String value) {
            addCriterion("jvalue >=", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueLessThan(String value) {
            addCriterion("jvalue <", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueLessThanOrEqualTo(String value) {
            addCriterion("jvalue <=", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueLike(String value) {
            addCriterion("jvalue like", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueNotLike(String value) {
            addCriterion("jvalue not like", value, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueIn(List<String> values) {
            addCriterion("jvalue in", values, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueNotIn(List<String> values) {
            addCriterion("jvalue not in", values, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueBetween(String value1, String value2) {
            addCriterion("jvalue between", value1, value2, "jvalue");
            return (Criteria) this;
        }

        public Criteria andJvalueNotBetween(String value1, String value2) {
            addCriterion("jvalue not between", value1, value2, "jvalue");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}