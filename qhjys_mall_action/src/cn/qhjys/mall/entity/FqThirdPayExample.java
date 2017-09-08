package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FqThirdPayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqThirdPayExample() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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

        public Criteria andThirdOrderNoIsNull() {
            addCriterion("third_order_no is null");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoIsNotNull() {
            addCriterion("third_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoEqualTo(String value) {
            addCriterion("third_order_no =", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotEqualTo(String value) {
            addCriterion("third_order_no <>", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoGreaterThan(String value) {
            addCriterion("third_order_no >", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("third_order_no >=", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoLessThan(String value) {
            addCriterion("third_order_no <", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoLessThanOrEqualTo(String value) {
            addCriterion("third_order_no <=", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoLike(String value) {
            addCriterion("third_order_no like", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotLike(String value) {
            addCriterion("third_order_no not like", value, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoIn(List<String> values) {
            addCriterion("third_order_no in", values, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotIn(List<String> values) {
            addCriterion("third_order_no not in", values, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoBetween(String value1, String value2) {
            addCriterion("third_order_no between", value1, value2, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andThirdOrderNoNotBetween(String value1, String value2) {
            addCriterion("third_order_no not between", value1, value2, "thirdOrderNo");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andBankTypeIsNull() {
            addCriterion("bank_type is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeIsNotNull() {
            addCriterion("bank_type is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeEqualTo(Integer value) {
            addCriterion("bank_type =", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotEqualTo(Integer value) {
            addCriterion("bank_type <>", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThan(Integer value) {
            addCriterion("bank_type >", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("bank_type >=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThan(Integer value) {
            addCriterion("bank_type <", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeLessThanOrEqualTo(Integer value) {
            addCriterion("bank_type <=", value, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeIn(List<Integer> values) {
            addCriterion("bank_type in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotIn(List<Integer> values) {
            addCriterion("bank_type not in", values, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeBetween(Integer value1, Integer value2) {
            addCriterion("bank_type between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("bank_type not between", value1, value2, "bankType");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeIsNull() {
            addCriterion("bank_type_code is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeIsNotNull() {
            addCriterion("bank_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeEqualTo(String value) {
            addCriterion("bank_type_code =", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeNotEqualTo(String value) {
            addCriterion("bank_type_code <>", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeGreaterThan(String value) {
            addCriterion("bank_type_code >", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_type_code >=", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeLessThan(String value) {
            addCriterion("bank_type_code <", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_type_code <=", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeLike(String value) {
            addCriterion("bank_type_code like", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeNotLike(String value) {
            addCriterion("bank_type_code not like", value, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeIn(List<String> values) {
            addCriterion("bank_type_code in", values, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeNotIn(List<String> values) {
            addCriterion("bank_type_code not in", values, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeBetween(String value1, String value2) {
            addCriterion("bank_type_code between", value1, value2, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeCodeNotBetween(String value1, String value2) {
            addCriterion("bank_type_code not between", value1, value2, "bankTypeCode");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoIsNull() {
            addCriterion("bank_type_info is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoIsNotNull() {
            addCriterion("bank_type_info is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoEqualTo(String value) {
            addCriterion("bank_type_info =", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoNotEqualTo(String value) {
            addCriterion("bank_type_info <>", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoGreaterThan(String value) {
            addCriterion("bank_type_info >", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_type_info >=", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoLessThan(String value) {
            addCriterion("bank_type_info <", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoLessThanOrEqualTo(String value) {
            addCriterion("bank_type_info <=", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoLike(String value) {
            addCriterion("bank_type_info like", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoNotLike(String value) {
            addCriterion("bank_type_info not like", value, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoIn(List<String> values) {
            addCriterion("bank_type_info in", values, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoNotIn(List<String> values) {
            addCriterion("bank_type_info not in", values, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoBetween(String value1, String value2) {
            addCriterion("bank_type_info between", value1, value2, "bankTypeInfo");
            return (Criteria) this;
        }

        public Criteria andBankTypeInfoNotBetween(String value1, String value2) {
            addCriterion("bank_type_info not between", value1, value2, "bankTypeInfo");
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

        public Criteria andIsCashIsNull() {
            addCriterion("is_cash is null");
            return (Criteria) this;
        }

        public Criteria andIsCashIsNotNull() {
            addCriterion("is_cash is not null");
            return (Criteria) this;
        }

        public Criteria andIsCashEqualTo(Integer value) {
            addCriterion("is_cash =", value, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashNotEqualTo(Integer value) {
            addCriterion("is_cash <>", value, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashGreaterThan(Integer value) {
            addCriterion("is_cash >", value, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_cash >=", value, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashLessThan(Integer value) {
            addCriterion("is_cash <", value, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashLessThanOrEqualTo(Integer value) {
            addCriterion("is_cash <=", value, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashIn(List<Integer> values) {
            addCriterion("is_cash in", values, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashNotIn(List<Integer> values) {
            addCriterion("is_cash not in", values, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashBetween(Integer value1, Integer value2) {
            addCriterion("is_cash between", value1, value2, "isCash");
            return (Criteria) this;
        }

        public Criteria andIsCashNotBetween(Integer value1, Integer value2) {
            addCriterion("is_cash not between", value1, value2, "isCash");
            return (Criteria) this;
        }

        public Criteria andPayNumIsNull() {
            addCriterion("pay_num is null");
            return (Criteria) this;
        }

        public Criteria andPayNumIsNotNull() {
            addCriterion("pay_num is not null");
            return (Criteria) this;
        }

        public Criteria andPayNumEqualTo(Integer value) {
            addCriterion("pay_num =", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumNotEqualTo(Integer value) {
            addCriterion("pay_num <>", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumGreaterThan(Integer value) {
            addCriterion("pay_num >", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_num >=", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumLessThan(Integer value) {
            addCriterion("pay_num <", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumLessThanOrEqualTo(Integer value) {
            addCriterion("pay_num <=", value, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumIn(List<Integer> values) {
            addCriterion("pay_num in", values, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumNotIn(List<Integer> values) {
            addCriterion("pay_num not in", values, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumBetween(Integer value1, Integer value2) {
            addCriterion("pay_num between", value1, value2, "payNum");
            return (Criteria) this;
        }

        public Criteria andPayNumNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_num not between", value1, value2, "payNum");
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