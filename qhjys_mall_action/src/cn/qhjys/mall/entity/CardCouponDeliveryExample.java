package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class CardCouponDeliveryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardCouponDeliveryExample() {
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

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Long value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Long value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Long value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Long value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Long value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Long> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Long> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Long value1, Long value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Long value1, Long value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andPushNumIsNull() {
            addCriterion("push_num is null");
            return (Criteria) this;
        }

        public Criteria andPushNumIsNotNull() {
            addCriterion("push_num is not null");
            return (Criteria) this;
        }

        public Criteria andPushNumEqualTo(Integer value) {
            addCriterion("push_num =", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumNotEqualTo(Integer value) {
            addCriterion("push_num <>", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumGreaterThan(Integer value) {
            addCriterion("push_num >", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_num >=", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumLessThan(Integer value) {
            addCriterion("push_num <", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumLessThanOrEqualTo(Integer value) {
            addCriterion("push_num <=", value, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumIn(List<Integer> values) {
            addCriterion("push_num in", values, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumNotIn(List<Integer> values) {
            addCriterion("push_num not in", values, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumBetween(Integer value1, Integer value2) {
            addCriterion("push_num between", value1, value2, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPushNumNotBetween(Integer value1, Integer value2) {
            addCriterion("push_num not between", value1, value2, "pushNum");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusIsNull() {
            addCriterion("peripheral_status is null");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusIsNotNull() {
            addCriterion("peripheral_status is not null");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusEqualTo(Integer value) {
            addCriterion("peripheral_status =", value, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusNotEqualTo(Integer value) {
            addCriterion("peripheral_status <>", value, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusGreaterThan(Integer value) {
            addCriterion("peripheral_status >", value, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("peripheral_status >=", value, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusLessThan(Integer value) {
            addCriterion("peripheral_status <", value, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusLessThanOrEqualTo(Integer value) {
            addCriterion("peripheral_status <=", value, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusIn(List<Integer> values) {
            addCriterion("peripheral_status in", values, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusNotIn(List<Integer> values) {
            addCriterion("peripheral_status not in", values, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusBetween(Integer value1, Integer value2) {
            addCriterion("peripheral_status between", value1, value2, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andPeripheralStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("peripheral_status not between", value1, value2, "peripheralStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusIsNull() {
            addCriterion("proprietary_status is null");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusIsNotNull() {
            addCriterion("proprietary_status is not null");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusEqualTo(Integer value) {
            addCriterion("proprietary_status =", value, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusNotEqualTo(Integer value) {
            addCriterion("proprietary_status <>", value, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusGreaterThan(Integer value) {
            addCriterion("proprietary_status >", value, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("proprietary_status >=", value, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusLessThan(Integer value) {
            addCriterion("proprietary_status <", value, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusLessThanOrEqualTo(Integer value) {
            addCriterion("proprietary_status <=", value, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusIn(List<Integer> values) {
            addCriterion("proprietary_status in", values, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusNotIn(List<Integer> values) {
            addCriterion("proprietary_status not in", values, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusBetween(Integer value1, Integer value2) {
            addCriterion("proprietary_status between", value1, value2, "proprietaryStatus");
            return (Criteria) this;
        }

        public Criteria andProprietaryStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("proprietary_status not between", value1, value2, "proprietaryStatus");
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