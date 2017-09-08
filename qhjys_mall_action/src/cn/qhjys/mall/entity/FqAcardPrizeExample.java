package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FqAcardPrizeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqAcardPrizeExample() {
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

        public Criteria andAcardIdIsNull() {
            addCriterion("acard_id is null");
            return (Criteria) this;
        }

        public Criteria andAcardIdIsNotNull() {
            addCriterion("acard_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcardIdEqualTo(Long value) {
            addCriterion("acard_id =", value, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdNotEqualTo(Long value) {
            addCriterion("acard_id <>", value, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdGreaterThan(Long value) {
            addCriterion("acard_id >", value, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdGreaterThanOrEqualTo(Long value) {
            addCriterion("acard_id >=", value, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdLessThan(Long value) {
            addCriterion("acard_id <", value, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdLessThanOrEqualTo(Long value) {
            addCriterion("acard_id <=", value, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdIn(List<Long> values) {
            addCriterion("acard_id in", values, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdNotIn(List<Long> values) {
            addCriterion("acard_id not in", values, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdBetween(Long value1, Long value2) {
            addCriterion("acard_id between", value1, value2, "acardId");
            return (Criteria) this;
        }

        public Criteria andAcardIdNotBetween(Long value1, Long value2) {
            addCriterion("acard_id not between", value1, value2, "acardId");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNull() {
            addCriterion("prize_name is null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIsNotNull() {
            addCriterion("prize_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeNameEqualTo(String value) {
            addCriterion("prize_name =", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotEqualTo(String value) {
            addCriterion("prize_name <>", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThan(String value) {
            addCriterion("prize_name >", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("prize_name >=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThan(String value) {
            addCriterion("prize_name <", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLessThanOrEqualTo(String value) {
            addCriterion("prize_name <=", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameLike(String value) {
            addCriterion("prize_name like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotLike(String value) {
            addCriterion("prize_name not like", value, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameIn(List<String> values) {
            addCriterion("prize_name in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotIn(List<String> values) {
            addCriterion("prize_name not in", values, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameBetween(String value1, String value2) {
            addCriterion("prize_name between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeNameNotBetween(String value1, String value2) {
            addCriterion("prize_name not between", value1, value2, "prizeName");
            return (Criteria) this;
        }

        public Criteria andPrizeLineIsNull() {
            addCriterion("prize_line is null");
            return (Criteria) this;
        }

        public Criteria andPrizeLineIsNotNull() {
            addCriterion("prize_line is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeLineEqualTo(BigDecimal value) {
            addCriterion("prize_line =", value, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineNotEqualTo(BigDecimal value) {
            addCriterion("prize_line <>", value, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineGreaterThan(BigDecimal value) {
            addCriterion("prize_line >", value, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("prize_line >=", value, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineLessThan(BigDecimal value) {
            addCriterion("prize_line <", value, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineLessThanOrEqualTo(BigDecimal value) {
            addCriterion("prize_line <=", value, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineIn(List<BigDecimal> values) {
            addCriterion("prize_line in", values, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineNotIn(List<BigDecimal> values) {
            addCriterion("prize_line not in", values, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prize_line between", value1, value2, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeLineNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("prize_line not between", value1, value2, "prizeLine");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoIsNull() {
            addCriterion("prize_info is null");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoIsNotNull() {
            addCriterion("prize_info is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoEqualTo(String value) {
            addCriterion("prize_info =", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoNotEqualTo(String value) {
            addCriterion("prize_info <>", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoGreaterThan(String value) {
            addCriterion("prize_info >", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("prize_info >=", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoLessThan(String value) {
            addCriterion("prize_info <", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoLessThanOrEqualTo(String value) {
            addCriterion("prize_info <=", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoLike(String value) {
            addCriterion("prize_info like", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoNotLike(String value) {
            addCriterion("prize_info not like", value, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoIn(List<String> values) {
            addCriterion("prize_info in", values, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoNotIn(List<String> values) {
            addCriterion("prize_info not in", values, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoBetween(String value1, String value2) {
            addCriterion("prize_info between", value1, value2, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeInfoNotBetween(String value1, String value2) {
            addCriterion("prize_info not between", value1, value2, "prizeInfo");
            return (Criteria) this;
        }

        public Criteria andPrizeImageIsNull() {
            addCriterion("prize_image is null");
            return (Criteria) this;
        }

        public Criteria andPrizeImageIsNotNull() {
            addCriterion("prize_image is not null");
            return (Criteria) this;
        }

        public Criteria andPrizeImageEqualTo(String value) {
            addCriterion("prize_image =", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotEqualTo(String value) {
            addCriterion("prize_image <>", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageGreaterThan(String value) {
            addCriterion("prize_image >", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageGreaterThanOrEqualTo(String value) {
            addCriterion("prize_image >=", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageLessThan(String value) {
            addCriterion("prize_image <", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageLessThanOrEqualTo(String value) {
            addCriterion("prize_image <=", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageLike(String value) {
            addCriterion("prize_image like", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotLike(String value) {
            addCriterion("prize_image not like", value, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageIn(List<String> values) {
            addCriterion("prize_image in", values, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotIn(List<String> values) {
            addCriterion("prize_image not in", values, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageBetween(String value1, String value2) {
            addCriterion("prize_image between", value1, value2, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andPrizeImageNotBetween(String value1, String value2) {
            addCriterion("prize_image not between", value1, value2, "prizeImage");
            return (Criteria) this;
        }

        public Criteria andProbabilityIsNull() {
            addCriterion("probability is null");
            return (Criteria) this;
        }

        public Criteria andProbabilityIsNotNull() {
            addCriterion("probability is not null");
            return (Criteria) this;
        }

        public Criteria andProbabilityEqualTo(Integer value) {
            addCriterion("probability =", value, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityNotEqualTo(Integer value) {
            addCriterion("probability <>", value, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityGreaterThan(Integer value) {
            addCriterion("probability >", value, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("probability >=", value, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityLessThan(Integer value) {
            addCriterion("probability <", value, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("probability <=", value, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityIn(List<Integer> values) {
            addCriterion("probability in", values, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityNotIn(List<Integer> values) {
            addCriterion("probability not in", values, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("probability between", value1, value2, "probability");
            return (Criteria) this;
        }

        public Criteria andProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("probability not between", value1, value2, "probability");
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