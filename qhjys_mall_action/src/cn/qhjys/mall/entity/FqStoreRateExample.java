package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FqStoreRateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqStoreRateExample() {
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

        public Criteria andRateNameIsNull() {
            addCriterion("rate_name is null");
            return (Criteria) this;
        }

        public Criteria andRateNameIsNotNull() {
            addCriterion("rate_name is not null");
            return (Criteria) this;
        }

        public Criteria andRateNameEqualTo(String value) {
            addCriterion("rate_name =", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotEqualTo(String value) {
            addCriterion("rate_name <>", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameGreaterThan(String value) {
            addCriterion("rate_name >", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameGreaterThanOrEqualTo(String value) {
            addCriterion("rate_name >=", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameLessThan(String value) {
            addCriterion("rate_name <", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameLessThanOrEqualTo(String value) {
            addCriterion("rate_name <=", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameLike(String value) {
            addCriterion("rate_name like", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotLike(String value) {
            addCriterion("rate_name not like", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameIn(List<String> values) {
            addCriterion("rate_name in", values, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotIn(List<String> values) {
            addCriterion("rate_name not in", values, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameBetween(String value1, String value2) {
            addCriterion("rate_name between", value1, value2, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotBetween(String value1, String value2) {
            addCriterion("rate_name not between", value1, value2, "rateName");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Long value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Long value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Long value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Long value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Long> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Long> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Long value1, Long value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIsNull() {
            addCriterion("admin_username is null");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIsNotNull() {
            addCriterion("admin_username is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameEqualTo(String value) {
            addCriterion("admin_username =", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotEqualTo(String value) {
            addCriterion("admin_username <>", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameGreaterThan(String value) {
            addCriterion("admin_username >", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("admin_username >=", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLessThan(String value) {
            addCriterion("admin_username <", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLessThanOrEqualTo(String value) {
            addCriterion("admin_username <=", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameLike(String value) {
            addCriterion("admin_username like", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotLike(String value) {
            addCriterion("admin_username not like", value, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameIn(List<String> values) {
            addCriterion("admin_username in", values, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotIn(List<String> values) {
            addCriterion("admin_username not in", values, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameBetween(String value1, String value2) {
            addCriterion("admin_username between", value1, value2, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andAdminUsernameNotBetween(String value1, String value2) {
            addCriterion("admin_username not between", value1, value2, "adminUsername");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateIsNull() {
            addCriterion("wechat_base_rate is null");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateIsNotNull() {
            addCriterion("wechat_base_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateEqualTo(BigDecimal value) {
            addCriterion("wechat_base_rate =", value, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateNotEqualTo(BigDecimal value) {
            addCriterion("wechat_base_rate <>", value, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateGreaterThan(BigDecimal value) {
            addCriterion("wechat_base_rate >", value, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wechat_base_rate >=", value, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateLessThan(BigDecimal value) {
            addCriterion("wechat_base_rate <", value, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wechat_base_rate <=", value, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateIn(List<BigDecimal> values) {
            addCriterion("wechat_base_rate in", values, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateNotIn(List<BigDecimal> values) {
            addCriterion("wechat_base_rate not in", values, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wechat_base_rate between", value1, value2, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatBaseRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wechat_base_rate not between", value1, value2, "wechatBaseRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateIsNull() {
            addCriterion("wechat_append_rate is null");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateIsNotNull() {
            addCriterion("wechat_append_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateEqualTo(BigDecimal value) {
            addCriterion("wechat_append_rate =", value, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateNotEqualTo(BigDecimal value) {
            addCriterion("wechat_append_rate <>", value, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateGreaterThan(BigDecimal value) {
            addCriterion("wechat_append_rate >", value, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wechat_append_rate >=", value, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateLessThan(BigDecimal value) {
            addCriterion("wechat_append_rate <", value, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wechat_append_rate <=", value, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateIn(List<BigDecimal> values) {
            addCriterion("wechat_append_rate in", values, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateNotIn(List<BigDecimal> values) {
            addCriterion("wechat_append_rate not in", values, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wechat_append_rate between", value1, value2, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wechat_append_rate not between", value1, value2, "wechatAppendRate");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyIsNull() {
            addCriterion("wechat_append_money is null");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyIsNotNull() {
            addCriterion("wechat_append_money is not null");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyEqualTo(BigDecimal value) {
            addCriterion("wechat_append_money =", value, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyNotEqualTo(BigDecimal value) {
            addCriterion("wechat_append_money <>", value, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyGreaterThan(BigDecimal value) {
            addCriterion("wechat_append_money >", value, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wechat_append_money >=", value, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyLessThan(BigDecimal value) {
            addCriterion("wechat_append_money <", value, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wechat_append_money <=", value, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyIn(List<BigDecimal> values) {
            addCriterion("wechat_append_money in", values, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyNotIn(List<BigDecimal> values) {
            addCriterion("wechat_append_money not in", values, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wechat_append_money between", value1, value2, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andWechatAppendMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wechat_append_money not between", value1, value2, "wechatAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateIsNull() {
            addCriterion("alipay_base_rate is null");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateIsNotNull() {
            addCriterion("alipay_base_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateEqualTo(BigDecimal value) {
            addCriterion("alipay_base_rate =", value, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateNotEqualTo(BigDecimal value) {
            addCriterion("alipay_base_rate <>", value, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateGreaterThan(BigDecimal value) {
            addCriterion("alipay_base_rate >", value, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_base_rate >=", value, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateLessThan(BigDecimal value) {
            addCriterion("alipay_base_rate <", value, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_base_rate <=", value, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateIn(List<BigDecimal> values) {
            addCriterion("alipay_base_rate in", values, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateNotIn(List<BigDecimal> values) {
            addCriterion("alipay_base_rate not in", values, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_base_rate between", value1, value2, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayBaseRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_base_rate not between", value1, value2, "alipayBaseRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateIsNull() {
            addCriterion("alipay_append_rate is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateIsNotNull() {
            addCriterion("alipay_append_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateEqualTo(BigDecimal value) {
            addCriterion("alipay_append_rate =", value, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateNotEqualTo(BigDecimal value) {
            addCriterion("alipay_append_rate <>", value, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateGreaterThan(BigDecimal value) {
            addCriterion("alipay_append_rate >", value, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_append_rate >=", value, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateLessThan(BigDecimal value) {
            addCriterion("alipay_append_rate <", value, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_append_rate <=", value, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateIn(List<BigDecimal> values) {
            addCriterion("alipay_append_rate in", values, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateNotIn(List<BigDecimal> values) {
            addCriterion("alipay_append_rate not in", values, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_append_rate between", value1, value2, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_append_rate not between", value1, value2, "alipayAppendRate");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyIsNull() {
            addCriterion("alipay_append_money is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyIsNotNull() {
            addCriterion("alipay_append_money is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyEqualTo(BigDecimal value) {
            addCriterion("alipay_append_money =", value, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyNotEqualTo(BigDecimal value) {
            addCriterion("alipay_append_money <>", value, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyGreaterThan(BigDecimal value) {
            addCriterion("alipay_append_money >", value, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_append_money >=", value, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyLessThan(BigDecimal value) {
            addCriterion("alipay_append_money <", value, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alipay_append_money <=", value, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyIn(List<BigDecimal> values) {
            addCriterion("alipay_append_money in", values, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyNotIn(List<BigDecimal> values) {
            addCriterion("alipay_append_money not in", values, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_append_money between", value1, value2, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andAlipayAppendMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alipay_append_money not between", value1, value2, "alipayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateIsNull() {
            addCriterion("qqpay_base_rate is null");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateIsNotNull() {
            addCriterion("qqpay_base_rate is not null");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateEqualTo(BigDecimal value) {
            addCriterion("qqpay_base_rate =", value, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateNotEqualTo(BigDecimal value) {
            addCriterion("qqpay_base_rate <>", value, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateGreaterThan(BigDecimal value) {
            addCriterion("qqpay_base_rate >", value, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("qqpay_base_rate >=", value, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateLessThan(BigDecimal value) {
            addCriterion("qqpay_base_rate <", value, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("qqpay_base_rate <=", value, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateIn(List<BigDecimal> values) {
            addCriterion("qqpay_base_rate in", values, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateNotIn(List<BigDecimal> values) {
            addCriterion("qqpay_base_rate not in", values, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qqpay_base_rate between", value1, value2, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayBaseRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qqpay_base_rate not between", value1, value2, "qqpayBaseRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateIsNull() {
            addCriterion("qqpay_append_rate is null");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateIsNotNull() {
            addCriterion("qqpay_append_rate is not null");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_rate =", value, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateNotEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_rate <>", value, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateGreaterThan(BigDecimal value) {
            addCriterion("qqpay_append_rate >", value, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_rate >=", value, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateLessThan(BigDecimal value) {
            addCriterion("qqpay_append_rate <", value, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_rate <=", value, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateIn(List<BigDecimal> values) {
            addCriterion("qqpay_append_rate in", values, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateNotIn(List<BigDecimal> values) {
            addCriterion("qqpay_append_rate not in", values, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qqpay_append_rate between", value1, value2, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qqpay_append_rate not between", value1, value2, "qqpayAppendRate");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyIsNull() {
            addCriterion("qqpay_append_money is null");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyIsNotNull() {
            addCriterion("qqpay_append_money is not null");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_money =", value, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyNotEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_money <>", value, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyGreaterThan(BigDecimal value) {
            addCriterion("qqpay_append_money >", value, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_money >=", value, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyLessThan(BigDecimal value) {
            addCriterion("qqpay_append_money <", value, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("qqpay_append_money <=", value, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyIn(List<BigDecimal> values) {
            addCriterion("qqpay_append_money in", values, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyNotIn(List<BigDecimal> values) {
            addCriterion("qqpay_append_money not in", values, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qqpay_append_money between", value1, value2, "qqpayAppendMoney");
            return (Criteria) this;
        }

        public Criteria andQqpayAppendMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("qqpay_append_money not between", value1, value2, "qqpayAppendMoney");
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