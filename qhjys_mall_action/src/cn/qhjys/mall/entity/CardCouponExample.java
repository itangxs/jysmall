package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardCouponExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andZxingImgIsNull() {
            addCriterion("zxing_img is null");
            return (Criteria) this;
        }

        public Criteria andZxingImgIsNotNull() {
            addCriterion("zxing_img is not null");
            return (Criteria) this;
        }

        public Criteria andZxingImgEqualTo(String value) {
            addCriterion("zxing_img =", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgNotEqualTo(String value) {
            addCriterion("zxing_img <>", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgGreaterThan(String value) {
            addCriterion("zxing_img >", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgGreaterThanOrEqualTo(String value) {
            addCriterion("zxing_img >=", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgLessThan(String value) {
            addCriterion("zxing_img <", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgLessThanOrEqualTo(String value) {
            addCriterion("zxing_img <=", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgLike(String value) {
            addCriterion("zxing_img like", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgNotLike(String value) {
            addCriterion("zxing_img not like", value, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgIn(List<String> values) {
            addCriterion("zxing_img in", values, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgNotIn(List<String> values) {
            addCriterion("zxing_img not in", values, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgBetween(String value1, String value2) {
            addCriterion("zxing_img between", value1, value2, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andZxingImgNotBetween(String value1, String value2) {
            addCriterion("zxing_img not between", value1, value2, "zxingImg");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeIsNull() {
            addCriterion("validity_starttime is null");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeIsNotNull() {
            addCriterion("validity_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeEqualTo(Date value) {
            addCriterion("validity_starttime =", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeNotEqualTo(Date value) {
            addCriterion("validity_starttime <>", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeGreaterThan(Date value) {
            addCriterion("validity_starttime >", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validity_starttime >=", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeLessThan(Date value) {
            addCriterion("validity_starttime <", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("validity_starttime <=", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeIn(List<Date> values) {
            addCriterion("validity_starttime in", values, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeNotIn(List<Date> values) {
            addCriterion("validity_starttime not in", values, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeBetween(Date value1, Date value2) {
            addCriterion("validity_starttime between", value1, value2, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("validity_starttime not between", value1, value2, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeIsNull() {
            addCriterion("validity_endtime is null");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeIsNotNull() {
            addCriterion("validity_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeEqualTo(Date value) {
            addCriterion("validity_endtime =", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeNotEqualTo(Date value) {
            addCriterion("validity_endtime <>", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeGreaterThan(Date value) {
            addCriterion("validity_endtime >", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validity_endtime >=", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeLessThan(Date value) {
            addCriterion("validity_endtime <", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("validity_endtime <=", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeIn(List<Date> values) {
            addCriterion("validity_endtime in", values, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeNotIn(List<Date> values) {
            addCriterion("validity_endtime not in", values, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeBetween(Date value1, Date value2) {
            addCriterion("validity_endtime between", value1, value2, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("validity_endtime not between", value1, value2, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeIsNull() {
            addCriterion("validity_use_starttime is null");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeIsNotNull() {
            addCriterion("validity_use_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeEqualTo(Integer value) {
            addCriterion("validity_use_starttime =", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeNotEqualTo(Integer value) {
            addCriterion("validity_use_starttime <>", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeGreaterThan(Integer value) {
            addCriterion("validity_use_starttime >", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_use_starttime >=", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeLessThan(Integer value) {
            addCriterion("validity_use_starttime <", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeLessThanOrEqualTo(Integer value) {
            addCriterion("validity_use_starttime <=", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeIn(List<Integer> values) {
            addCriterion("validity_use_starttime in", values, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeNotIn(List<Integer> values) {
            addCriterion("validity_use_starttime not in", values, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeBetween(Integer value1, Integer value2) {
            addCriterion("validity_use_starttime between", value1, value2, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_use_starttime not between", value1, value2, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeIsNull() {
            addCriterion("validity_user_endtime is null");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeIsNotNull() {
            addCriterion("validity_user_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeEqualTo(Integer value) {
            addCriterion("validity_user_endtime =", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeNotEqualTo(Integer value) {
            addCriterion("validity_user_endtime <>", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeGreaterThan(Integer value) {
            addCriterion("validity_user_endtime >", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_user_endtime >=", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeLessThan(Integer value) {
            addCriterion("validity_user_endtime <", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeLessThanOrEqualTo(Integer value) {
            addCriterion("validity_user_endtime <=", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeIn(List<Integer> values) {
            addCriterion("validity_user_endtime in", values, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeNotIn(List<Integer> values) {
            addCriterion("validity_user_endtime not in", values, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeBetween(Integer value1, Integer value2) {
            addCriterion("validity_user_endtime between", value1, value2, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_user_endtime not between", value1, value2, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountIsNull() {
            addCriterion("use_over_amount is null");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountIsNotNull() {
            addCriterion("use_over_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountEqualTo(BigDecimal value) {
            addCriterion("use_over_amount =", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountNotEqualTo(BigDecimal value) {
            addCriterion("use_over_amount <>", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountGreaterThan(BigDecimal value) {
            addCriterion("use_over_amount >", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("use_over_amount >=", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountLessThan(BigDecimal value) {
            addCriterion("use_over_amount <", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("use_over_amount <=", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountIn(List<BigDecimal> values) {
            addCriterion("use_over_amount in", values, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountNotIn(List<BigDecimal> values) {
            addCriterion("use_over_amount not in", values, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_over_amount between", value1, value2, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_over_amount not between", value1, value2, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andReceiveDateIsNull() {
            addCriterion("receive_date is null");
            return (Criteria) this;
        }

        public Criteria andReceiveDateIsNotNull() {
            addCriterion("receive_date is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveDateEqualTo(Date value) {
            addCriterion("receive_date =", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateNotEqualTo(Date value) {
            addCriterion("receive_date <>", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateGreaterThan(Date value) {
            addCriterion("receive_date >", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateGreaterThanOrEqualTo(Date value) {
            addCriterion("receive_date >=", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateLessThan(Date value) {
            addCriterion("receive_date <", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateLessThanOrEqualTo(Date value) {
            addCriterion("receive_date <=", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateIn(List<Date> values) {
            addCriterion("receive_date in", values, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateNotIn(List<Date> values) {
            addCriterion("receive_date not in", values, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateBetween(Date value1, Date value2) {
            addCriterion("receive_date between", value1, value2, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateNotBetween(Date value1, Date value2) {
            addCriterion("receive_date not between", value1, value2, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateIsNull() {
            addCriterion("validate_date is null");
            return (Criteria) this;
        }

        public Criteria andValidateDateIsNotNull() {
            addCriterion("validate_date is not null");
            return (Criteria) this;
        }

        public Criteria andValidateDateEqualTo(Date value) {
            addCriterion("validate_date =", value, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateNotEqualTo(Date value) {
            addCriterion("validate_date <>", value, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateGreaterThan(Date value) {
            addCriterion("validate_date >", value, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("validate_date >=", value, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateLessThan(Date value) {
            addCriterion("validate_date <", value, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateLessThanOrEqualTo(Date value) {
            addCriterion("validate_date <=", value, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateIn(List<Date> values) {
            addCriterion("validate_date in", values, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateNotIn(List<Date> values) {
            addCriterion("validate_date not in", values, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateBetween(Date value1, Date value2) {
            addCriterion("validate_date between", value1, value2, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateDateNotBetween(Date value1, Date value2) {
            addCriterion("validate_date not between", value1, value2, "validateDate");
            return (Criteria) this;
        }

        public Criteria andValidateCfgIsNull() {
            addCriterion("validate_cfg is null");
            return (Criteria) this;
        }

        public Criteria andValidateCfgIsNotNull() {
            addCriterion("validate_cfg is not null");
            return (Criteria) this;
        }

        public Criteria andValidateCfgEqualTo(Integer value) {
            addCriterion("validate_cfg =", value, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgNotEqualTo(Integer value) {
            addCriterion("validate_cfg <>", value, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgGreaterThan(Integer value) {
            addCriterion("validate_cfg >", value, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgGreaterThanOrEqualTo(Integer value) {
            addCriterion("validate_cfg >=", value, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgLessThan(Integer value) {
            addCriterion("validate_cfg <", value, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgLessThanOrEqualTo(Integer value) {
            addCriterion("validate_cfg <=", value, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgIn(List<Integer> values) {
            addCriterion("validate_cfg in", values, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgNotIn(List<Integer> values) {
            addCriterion("validate_cfg not in", values, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgBetween(Integer value1, Integer value2) {
            addCriterion("validate_cfg between", value1, value2, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andValidateCfgNotBetween(Integer value1, Integer value2) {
            addCriterion("validate_cfg not between", value1, value2, "validateCfg");
            return (Criteria) this;
        }

        public Criteria andGetWayIsNull() {
            addCriterion("get_way is null");
            return (Criteria) this;
        }

        public Criteria andGetWayIsNotNull() {
            addCriterion("get_way is not null");
            return (Criteria) this;
        }

        public Criteria andGetWayEqualTo(Integer value) {
            addCriterion("get_way =", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayNotEqualTo(Integer value) {
            addCriterion("get_way <>", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayGreaterThan(Integer value) {
            addCriterion("get_way >", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("get_way >=", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayLessThan(Integer value) {
            addCriterion("get_way <", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayLessThanOrEqualTo(Integer value) {
            addCriterion("get_way <=", value, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayIn(List<Integer> values) {
            addCriterion("get_way in", values, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayNotIn(List<Integer> values) {
            addCriterion("get_way not in", values, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayBetween(Integer value1, Integer value2) {
            addCriterion("get_way between", value1, value2, "getWay");
            return (Criteria) this;
        }

        public Criteria andGetWayNotBetween(Integer value1, Integer value2) {
            addCriterion("get_way not between", value1, value2, "getWay");
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
            addCriterion("open_id =", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "open_id");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "open_id");
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

        public Criteria andTemplateIdIsNull() {
            addCriterion("template_id is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIsNotNull() {
            addCriterion("template_id is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateIdEqualTo(Long value) {
            addCriterion("template_id =", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotEqualTo(Long value) {
            addCriterion("template_id <>", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThan(Long value) {
            addCriterion("template_id >", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("template_id >=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThan(Long value) {
            addCriterion("template_id <", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("template_id <=", value, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdIn(List<Long> values) {
            addCriterion("template_id in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotIn(List<Long> values) {
            addCriterion("template_id not in", values, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdBetween(Long value1, Long value2) {
            addCriterion("template_id between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("template_id not between", value1, value2, "templateId");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameIsNull() {
            addCriterion("template_coupon_name is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameIsNotNull() {
            addCriterion("template_coupon_name is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameEqualTo(String value) {
            addCriterion("template_coupon_name =", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameNotEqualTo(String value) {
            addCriterion("template_coupon_name <>", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameGreaterThan(String value) {
            addCriterion("template_coupon_name >", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameGreaterThanOrEqualTo(String value) {
            addCriterion("template_coupon_name >=", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameLessThan(String value) {
            addCriterion("template_coupon_name <", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameLessThanOrEqualTo(String value) {
            addCriterion("template_coupon_name <=", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameLike(String value) {
            addCriterion("template_coupon_name like", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameNotLike(String value) {
            addCriterion("template_coupon_name not like", value, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameIn(List<String> values) {
            addCriterion("template_coupon_name in", values, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameNotIn(List<String> values) {
            addCriterion("template_coupon_name not in", values, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameBetween(String value1, String value2) {
            addCriterion("template_coupon_name between", value1, value2, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponNameNotBetween(String value1, String value2) {
            addCriterion("template_coupon_name not between", value1, value2, "templateCouponName");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgIsNull() {
            addCriterion("template_coupon_cfg is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgIsNotNull() {
            addCriterion("template_coupon_cfg is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgEqualTo(String value) {
            addCriterion("template_coupon_cfg =", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgNotEqualTo(String value) {
            addCriterion("template_coupon_cfg <>", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgGreaterThan(String value) {
            addCriterion("template_coupon_cfg >", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgGreaterThanOrEqualTo(String value) {
            addCriterion("template_coupon_cfg >=", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgLessThan(String value) {
            addCriterion("template_coupon_cfg <", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgLessThanOrEqualTo(String value) {
            addCriterion("template_coupon_cfg <=", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgLike(String value) {
            addCriterion("template_coupon_cfg like", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgNotLike(String value) {
            addCriterion("template_coupon_cfg not like", value, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgIn(List<String> values) {
            addCriterion("template_coupon_cfg in", values, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgNotIn(List<String> values) {
            addCriterion("template_coupon_cfg not in", values, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgBetween(String value1, String value2) {
            addCriterion("template_coupon_cfg between", value1, value2, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponCfgNotBetween(String value1, String value2) {
            addCriterion("template_coupon_cfg not between", value1, value2, "templateCouponCfg");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountIsNull() {
            addCriterion("template_coupon_amount is null");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountIsNotNull() {
            addCriterion("template_coupon_amount is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountEqualTo(String value) {
            addCriterion("template_coupon_amount =", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountNotEqualTo(String value) {
            addCriterion("template_coupon_amount <>", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountGreaterThan(String value) {
            addCriterion("template_coupon_amount >", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountGreaterThanOrEqualTo(String value) {
            addCriterion("template_coupon_amount >=", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountLessThan(String value) {
            addCriterion("template_coupon_amount <", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountLessThanOrEqualTo(String value) {
            addCriterion("template_coupon_amount <=", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountLike(String value) {
            addCriterion("template_coupon_amount like", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountNotLike(String value) {
            addCriterion("template_coupon_amount not like", value, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountIn(List<String> values) {
            addCriterion("template_coupon_amount in", values, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountNotIn(List<String> values) {
            addCriterion("template_coupon_amount not in", values, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountBetween(String value1, String value2) {
            addCriterion("template_coupon_amount between", value1, value2, "templateCouponAmount");
            return (Criteria) this;
        }

        public Criteria andTemplateCouponAmountNotBetween(String value1, String value2) {
            addCriterion("template_coupon_amount not between", value1, value2, "templateCouponAmount");
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

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
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