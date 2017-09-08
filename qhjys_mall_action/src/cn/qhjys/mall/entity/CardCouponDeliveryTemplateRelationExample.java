package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class CardCouponDeliveryTemplateRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardCouponDeliveryTemplateRelationExample() {
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

        public Criteria andCardCouponDeliveryIdIsNull() {
            addCriterion("card_coupon_delivery_id is null");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdIsNotNull() {
            addCriterion("card_coupon_delivery_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdEqualTo(Long value) {
            addCriterion("card_coupon_delivery_id =", value, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdNotEqualTo(Long value) {
            addCriterion("card_coupon_delivery_id <>", value, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdGreaterThan(Long value) {
            addCriterion("card_coupon_delivery_id >", value, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("card_coupon_delivery_id >=", value, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdLessThan(Long value) {
            addCriterion("card_coupon_delivery_id <", value, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdLessThanOrEqualTo(Long value) {
            addCriterion("card_coupon_delivery_id <=", value, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdIn(List<Long> values) {
            addCriterion("card_coupon_delivery_id in", values, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdNotIn(List<Long> values) {
            addCriterion("card_coupon_delivery_id not in", values, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdBetween(Long value1, Long value2) {
            addCriterion("card_coupon_delivery_id between", value1, value2, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponDeliveryIdNotBetween(Long value1, Long value2) {
            addCriterion("card_coupon_delivery_id not between", value1, value2, "cardCouponDeliveryId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdIsNull() {
            addCriterion("card_coupon_template_id is null");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdIsNotNull() {
            addCriterion("card_coupon_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdEqualTo(Long value) {
            addCriterion("card_coupon_template_id =", value, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdNotEqualTo(Long value) {
            addCriterion("card_coupon_template_id <>", value, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdGreaterThan(Long value) {
            addCriterion("card_coupon_template_id >", value, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("card_coupon_template_id >=", value, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdLessThan(Long value) {
            addCriterion("card_coupon_template_id <", value, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("card_coupon_template_id <=", value, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdIn(List<Long> values) {
            addCriterion("card_coupon_template_id in", values, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdNotIn(List<Long> values) {
            addCriterion("card_coupon_template_id not in", values, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdBetween(Long value1, Long value2) {
            addCriterion("card_coupon_template_id between", value1, value2, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardCouponTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("card_coupon_template_id not between", value1, value2, "cardCouponTemplateId");
            return (Criteria) this;
        }

        public Criteria andStatusCfgIsNull() {
            addCriterion("status_cfg is null");
            return (Criteria) this;
        }

        public Criteria andStatusCfgIsNotNull() {
            addCriterion("status_cfg is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCfgEqualTo(Integer value) {
            addCriterion("status_cfg =", value, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgNotEqualTo(Integer value) {
            addCriterion("status_cfg <>", value, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgGreaterThan(Integer value) {
            addCriterion("status_cfg >", value, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_cfg >=", value, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgLessThan(Integer value) {
            addCriterion("status_cfg <", value, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgLessThanOrEqualTo(Integer value) {
            addCriterion("status_cfg <=", value, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgIn(List<Integer> values) {
            addCriterion("status_cfg in", values, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgNotIn(List<Integer> values) {
            addCriterion("status_cfg not in", values, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgBetween(Integer value1, Integer value2) {
            addCriterion("status_cfg between", value1, value2, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andStatusCfgNotBetween(Integer value1, Integer value2) {
            addCriterion("status_cfg not between", value1, value2, "statusCfg");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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