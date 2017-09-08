package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RebateOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RebateOrderExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
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

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Long value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Long value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Long value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Long value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Long> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Long> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Long value1, Long value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(BigDecimal value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(BigDecimal value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(BigDecimal value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(BigDecimal value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(BigDecimal value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<BigDecimal> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<BigDecimal> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andNeedPayIsNull() {
            addCriterion("need_pay is null");
            return (Criteria) this;
        }

        public Criteria andNeedPayIsNotNull() {
            addCriterion("need_pay is not null");
            return (Criteria) this;
        }

        public Criteria andNeedPayEqualTo(BigDecimal value) {
            addCriterion("need_pay =", value, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayNotEqualTo(BigDecimal value) {
            addCriterion("need_pay <>", value, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayGreaterThan(BigDecimal value) {
            addCriterion("need_pay >", value, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("need_pay >=", value, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayLessThan(BigDecimal value) {
            addCriterion("need_pay <", value, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("need_pay <=", value, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayIn(List<BigDecimal> values) {
            addCriterion("need_pay in", values, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayNotIn(List<BigDecimal> values) {
            addCriterion("need_pay not in", values, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_pay between", value1, value2, "needPay");
            return (Criteria) this;
        }

        public Criteria andNeedPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("need_pay not between", value1, value2, "needPay");
            return (Criteria) this;
        }

        public Criteria andRealPayIsNull() {
            addCriterion("real_pay is null");
            return (Criteria) this;
        }

        public Criteria andRealPayIsNotNull() {
            addCriterion("real_pay is not null");
            return (Criteria) this;
        }

        public Criteria andRealPayEqualTo(BigDecimal value) {
            addCriterion("real_pay =", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayNotEqualTo(BigDecimal value) {
            addCriterion("real_pay <>", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayGreaterThan(BigDecimal value) {
            addCriterion("real_pay >", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("real_pay >=", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayLessThan(BigDecimal value) {
            addCriterion("real_pay <", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("real_pay <=", value, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayIn(List<BigDecimal> values) {
            addCriterion("real_pay in", values, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayNotIn(List<BigDecimal> values) {
            addCriterion("real_pay not in", values, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_pay between", value1, value2, "realPay");
            return (Criteria) this;
        }

        public Criteria andRealPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("real_pay not between", value1, value2, "realPay");
            return (Criteria) this;
        }

        public Criteria andTotamtIsNull() {
            addCriterion("totamt is null");
            return (Criteria) this;
        }

        public Criteria andTotamtIsNotNull() {
            addCriterion("totamt is not null");
            return (Criteria) this;
        }

        public Criteria andTotamtEqualTo(BigDecimal value) {
            addCriterion("totamt =", value, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtNotEqualTo(BigDecimal value) {
            addCriterion("totamt <>", value, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtGreaterThan(BigDecimal value) {
            addCriterion("totamt >", value, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("totamt >=", value, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtLessThan(BigDecimal value) {
            addCriterion("totamt <", value, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("totamt <=", value, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtIn(List<BigDecimal> values) {
            addCriterion("totamt in", values, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtNotIn(List<BigDecimal> values) {
            addCriterion("totamt not in", values, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totamt between", value1, value2, "totamt");
            return (Criteria) this;
        }

        public Criteria andTotamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("totamt not between", value1, value2, "totamt");
            return (Criteria) this;
        }

        public Criteria andNoDiscountIsNull() {
            addCriterion("no_discount is null");
            return (Criteria) this;
        }

        public Criteria andNoDiscountIsNotNull() {
            addCriterion("no_discount is not null");
            return (Criteria) this;
        }

        public Criteria andNoDiscountEqualTo(BigDecimal value) {
            addCriterion("no_discount =", value, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountNotEqualTo(BigDecimal value) {
            addCriterion("no_discount <>", value, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountGreaterThan(BigDecimal value) {
            addCriterion("no_discount >", value, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("no_discount >=", value, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountLessThan(BigDecimal value) {
            addCriterion("no_discount <", value, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("no_discount <=", value, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountIn(List<BigDecimal> values) {
            addCriterion("no_discount in", values, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountNotIn(List<BigDecimal> values) {
            addCriterion("no_discount not in", values, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("no_discount between", value1, value2, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andNoDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("no_discount not between", value1, value2, "noDiscount");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIsNull() {
            addCriterion("headimgurl is null");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIsNotNull() {
            addCriterion("headimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlEqualTo(String value) {
            addCriterion("headimgurl =", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotEqualTo(String value) {
            addCriterion("headimgurl <>", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlGreaterThan(String value) {
            addCriterion("headimgurl >", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("headimgurl >=", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLessThan(String value) {
            addCriterion("headimgurl <", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLessThanOrEqualTo(String value) {
            addCriterion("headimgurl <=", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLike(String value) {
            addCriterion("headimgurl like", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotLike(String value) {
            addCriterion("headimgurl not like", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIn(List<String> values) {
            addCriterion("headimgurl in", values, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotIn(List<String> values) {
            addCriterion("headimgurl not in", values, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlBetween(String value1, String value2) {
            addCriterion("headimgurl between", value1, value2, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotBetween(String value1, String value2) {
            addCriterion("headimgurl not between", value1, value2, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoIsNull() {
            addCriterion("weixin_order_no is null");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoIsNotNull() {
            addCriterion("weixin_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoEqualTo(String value) {
            addCriterion("weixin_order_no =", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoNotEqualTo(String value) {
            addCriterion("weixin_order_no <>", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoGreaterThan(String value) {
            addCriterion("weixin_order_no >", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_order_no >=", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoLessThan(String value) {
            addCriterion("weixin_order_no <", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoLessThanOrEqualTo(String value) {
            addCriterion("weixin_order_no <=", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoLike(String value) {
            addCriterion("weixin_order_no like", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoNotLike(String value) {
            addCriterion("weixin_order_no not like", value, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoIn(List<String> values) {
            addCriterion("weixin_order_no in", values, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoNotIn(List<String> values) {
            addCriterion("weixin_order_no not in", values, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoBetween(String value1, String value2) {
            addCriterion("weixin_order_no between", value1, value2, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andWeixinOrderNoNotBetween(String value1, String value2) {
            addCriterion("weixin_order_no not between", value1, value2, "weixinOrderNo");
            return (Criteria) this;
        }

        public Criteria andOrderRateIsNull() {
            addCriterion("order_rate is null");
            return (Criteria) this;
        }

        public Criteria andOrderRateIsNotNull() {
            addCriterion("order_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOrderRateEqualTo(BigDecimal value) {
            addCriterion("order_rate =", value, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateNotEqualTo(BigDecimal value) {
            addCriterion("order_rate <>", value, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateGreaterThan(BigDecimal value) {
            addCriterion("order_rate >", value, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_rate >=", value, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateLessThan(BigDecimal value) {
            addCriterion("order_rate <", value, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_rate <=", value, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateIn(List<BigDecimal> values) {
            addCriterion("order_rate in", values, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateNotIn(List<BigDecimal> values) {
            addCriterion("order_rate not in", values, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_rate between", value1, value2, "orderRate");
            return (Criteria) this;
        }

        public Criteria andOrderRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_rate not between", value1, value2, "orderRate");
            return (Criteria) this;
        }

        public Criteria andRateFeeIsNull() {
            addCriterion("rate_fee is null");
            return (Criteria) this;
        }

        public Criteria andRateFeeIsNotNull() {
            addCriterion("rate_fee is not null");
            return (Criteria) this;
        }

        public Criteria andRateFeeEqualTo(BigDecimal value) {
            addCriterion("rate_fee =", value, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeNotEqualTo(BigDecimal value) {
            addCriterion("rate_fee <>", value, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeGreaterThan(BigDecimal value) {
            addCriterion("rate_fee >", value, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rate_fee >=", value, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeLessThan(BigDecimal value) {
            addCriterion("rate_fee <", value, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rate_fee <=", value, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeIn(List<BigDecimal> values) {
            addCriterion("rate_fee in", values, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeNotIn(List<BigDecimal> values) {
            addCriterion("rate_fee not in", values, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate_fee between", value1, value2, "rateFee");
            return (Criteria) this;
        }

        public Criteria andRateFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate_fee not between", value1, value2, "rateFee");
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