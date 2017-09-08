package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RebateCashExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RebateCashExample() {
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

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andRebateIdIsNull() {
            addCriterion("rebate_id is null");
            return (Criteria) this;
        }

        public Criteria andRebateIdIsNotNull() {
            addCriterion("rebate_id is not null");
            return (Criteria) this;
        }

        public Criteria andRebateIdEqualTo(Long value) {
            addCriterion("rebate_id =", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdNotEqualTo(Long value) {
            addCriterion("rebate_id <>", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdGreaterThan(Long value) {
            addCriterion("rebate_id >", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rebate_id >=", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdLessThan(Long value) {
            addCriterion("rebate_id <", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdLessThanOrEqualTo(Long value) {
            addCriterion("rebate_id <=", value, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdIn(List<Long> values) {
            addCriterion("rebate_id in", values, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdNotIn(List<Long> values) {
            addCriterion("rebate_id not in", values, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdBetween(Long value1, Long value2) {
            addCriterion("rebate_id between", value1, value2, "rebateId");
            return (Criteria) this;
        }

        public Criteria andRebateIdNotBetween(Long value1, Long value2) {
            addCriterion("rebate_id not between", value1, value2, "rebateId");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalIsNull() {
            addCriterion("integral_total is null");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalIsNotNull() {
            addCriterion("integral_total is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalEqualTo(BigDecimal value) {
            addCriterion("integral_total =", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalNotEqualTo(BigDecimal value) {
            addCriterion("integral_total <>", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalGreaterThan(BigDecimal value) {
            addCriterion("integral_total >", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_total >=", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalLessThan(BigDecimal value) {
            addCriterion("integral_total <", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral_total <=", value, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalIn(List<BigDecimal> values) {
            addCriterion("integral_total in", values, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalNotIn(List<BigDecimal> values) {
            addCriterion("integral_total not in", values, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_total between", value1, value2, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andIntegralTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral_total not between", value1, value2, "integralTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalIsNull() {
            addCriterion("need_total is null");
            return (Criteria) this;
        }

        public Criteria andNeedTotalIsNotNull() {
            addCriterion("need_total is not null");
            return (Criteria) this;
        }

        public Criteria andNeedTotalEqualTo(BigDecimal value) {
            addCriterion("need_total =", value, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalNotEqualTo(BigDecimal value) {
            addCriterion("need_total <>", value, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalGreaterThan(BigDecimal value) {
            addCriterion("need_total >", value, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("need_total >=", value, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalLessThan(BigDecimal value) {
            addCriterion("need_total <", value, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("need_total <=", value, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalIn(List<BigDecimal> values) {
            addCriterion("need_total in", values, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalNotIn(List<BigDecimal> values) {
            addCriterion("need_total not in", values, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_total between", value1, value2, "needTotal");
            return (Criteria) this;
        }

        public Criteria andNeedTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_total not between", value1, value2, "needTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalIsNull() {
            addCriterion("real_total is null");
            return (Criteria) this;
        }

        public Criteria andRealTotalIsNotNull() {
            addCriterion("real_total is not null");
            return (Criteria) this;
        }

        public Criteria andRealTotalEqualTo(BigDecimal value) {
            addCriterion("real_total =", value, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalNotEqualTo(BigDecimal value) {
            addCriterion("real_total <>", value, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalGreaterThan(BigDecimal value) {
            addCriterion("real_total >", value, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("real_total >=", value, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalLessThan(BigDecimal value) {
            addCriterion("real_total <", value, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("real_total <=", value, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalIn(List<BigDecimal> values) {
            addCriterion("real_total in", values, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalNotIn(List<BigDecimal> values) {
            addCriterion("real_total not in", values, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_total between", value1, value2, "realTotal");
            return (Criteria) this;
        }

        public Criteria andRealTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_total not between", value1, value2, "realTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalIsNull() {
            addCriterion("totamt_total is null");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalIsNotNull() {
            addCriterion("totamt_total is not null");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalEqualTo(BigDecimal value) {
            addCriterion("totamt_total =", value, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalNotEqualTo(BigDecimal value) {
            addCriterion("totamt_total <>", value, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalGreaterThan(BigDecimal value) {
            addCriterion("totamt_total >", value, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totamt_total >=", value, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalLessThan(BigDecimal value) {
            addCriterion("totamt_total <", value, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totamt_total <=", value, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalIn(List<BigDecimal> values) {
            addCriterion("totamt_total in", values, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalNotIn(List<BigDecimal> values) {
            addCriterion("totamt_total not in", values, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totamt_total between", value1, value2, "totamtTotal");
            return (Criteria) this;
        }

        public Criteria andTotamtTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totamt_total not between", value1, value2, "totamtTotal");
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