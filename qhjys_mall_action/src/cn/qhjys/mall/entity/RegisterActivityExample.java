package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RegisterActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RegisterActivityExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterionForJDBCDate("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterionForJDBCDate("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andCommonCouponIsNull() {
            addCriterion("common_coupon is null");
            return (Criteria) this;
        }

        public Criteria andCommonCouponIsNotNull() {
            addCriterion("common_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andCommonCouponEqualTo(Integer value) {
            addCriterion("common_coupon =", value, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponNotEqualTo(Integer value) {
            addCriterion("common_coupon <>", value, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponGreaterThan(Integer value) {
            addCriterion("common_coupon >", value, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponGreaterThanOrEqualTo(Integer value) {
            addCriterion("common_coupon >=", value, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponLessThan(Integer value) {
            addCriterion("common_coupon <", value, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponLessThanOrEqualTo(Integer value) {
            addCriterion("common_coupon <=", value, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponIn(List<Integer> values) {
            addCriterion("common_coupon in", values, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponNotIn(List<Integer> values) {
            addCriterion("common_coupon not in", values, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponBetween(Integer value1, Integer value2) {
            addCriterion("common_coupon between", value1, value2, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andCommonCouponNotBetween(Integer value1, Integer value2) {
            addCriterion("common_coupon not between", value1, value2, "commonCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponIsNull() {
            addCriterion("store_coupon is null");
            return (Criteria) this;
        }

        public Criteria andStoreCouponIsNotNull() {
            addCriterion("store_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andStoreCouponEqualTo(Integer value) {
            addCriterion("store_coupon =", value, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponNotEqualTo(Integer value) {
            addCriterion("store_coupon <>", value, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponGreaterThan(Integer value) {
            addCriterion("store_coupon >", value, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_coupon >=", value, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponLessThan(Integer value) {
            addCriterion("store_coupon <", value, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponLessThanOrEqualTo(Integer value) {
            addCriterion("store_coupon <=", value, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponIn(List<Integer> values) {
            addCriterion("store_coupon in", values, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponNotIn(List<Integer> values) {
            addCriterion("store_coupon not in", values, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponBetween(Integer value1, Integer value2) {
            addCriterion("store_coupon between", value1, value2, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andStoreCouponNotBetween(Integer value1, Integer value2) {
            addCriterion("store_coupon not between", value1, value2, "storeCoupon");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Integer value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Integer value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Integer value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Integer value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Integer value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Integer> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Integer> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Integer value1, Integer value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Integer value1, Integer value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andCouponValidityIsNull() {
            addCriterion("coupon_validity is null");
            return (Criteria) this;
        }

        public Criteria andCouponValidityIsNotNull() {
            addCriterion("coupon_validity is not null");
            return (Criteria) this;
        }

        public Criteria andCouponValidityEqualTo(Integer value) {
            addCriterion("coupon_validity =", value, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityNotEqualTo(Integer value) {
            addCriterion("coupon_validity <>", value, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityGreaterThan(Integer value) {
            addCriterion("coupon_validity >", value, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_validity >=", value, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityLessThan(Integer value) {
            addCriterion("coupon_validity <", value, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_validity <=", value, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityIn(List<Integer> values) {
            addCriterion("coupon_validity in", values, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityNotIn(List<Integer> values) {
            addCriterion("coupon_validity not in", values, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityBetween(Integer value1, Integer value2) {
            addCriterion("coupon_validity between", value1, value2, "couponValidity");
            return (Criteria) this;
        }

        public Criteria andCouponValidityNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_validity not between", value1, value2, "couponValidity");
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