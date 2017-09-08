package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardLotteryDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardLotteryDetailExample() {
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

        public Criteria andUserLotteryIdIsNull() {
            addCriterion("user_lottery_id is null");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdIsNotNull() {
            addCriterion("user_lottery_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdEqualTo(Long value) {
            addCriterion("user_lottery_id =", value, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdNotEqualTo(Long value) {
            addCriterion("user_lottery_id <>", value, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdGreaterThan(Long value) {
            addCriterion("user_lottery_id >", value, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_lottery_id >=", value, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdLessThan(Long value) {
            addCriterion("user_lottery_id <", value, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdLessThanOrEqualTo(Long value) {
            addCriterion("user_lottery_id <=", value, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdIn(List<Long> values) {
            addCriterion("user_lottery_id in", values, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdNotIn(List<Long> values) {
            addCriterion("user_lottery_id not in", values, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdBetween(Long value1, Long value2) {
            addCriterion("user_lottery_id between", value1, value2, "userLotteryId");
            return (Criteria) this;
        }

        public Criteria andUserLotteryIdNotBetween(Long value1, Long value2) {
            addCriterion("user_lottery_id not between", value1, value2, "userLotteryId");
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

        public Criteria andForOpenIdIsNull() {
            addCriterion("for_open_id is null");
            return (Criteria) this;
        }

        public Criteria andForOpenIdIsNotNull() {
            addCriterion("for_open_id is not null");
            return (Criteria) this;
        }

        public Criteria andForOpenIdEqualTo(String value) {
            addCriterion("for_open_id =", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdNotEqualTo(String value) {
            addCriterion("for_open_id <>", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdGreaterThan(String value) {
            addCriterion("for_open_id >", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("for_open_id >=", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdLessThan(String value) {
            addCriterion("for_open_id <", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdLessThanOrEqualTo(String value) {
            addCriterion("for_open_id <=", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdLike(String value) {
            addCriterion("for_open_id like", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdNotLike(String value) {
            addCriterion("for_open_id not like", value, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdIn(List<String> values) {
            addCriterion("for_open_id in", values, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdNotIn(List<String> values) {
            addCriterion("for_open_id not in", values, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdBetween(String value1, String value2) {
            addCriterion("for_open_id between", value1, value2, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForOpenIdNotBetween(String value1, String value2) {
            addCriterion("for_open_id not between", value1, value2, "forOpenId");
            return (Criteria) this;
        }

        public Criteria andForUserIdIsNull() {
            addCriterion("for_user_id is null");
            return (Criteria) this;
        }

        public Criteria andForUserIdIsNotNull() {
            addCriterion("for_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andForUserIdEqualTo(Long value) {
            addCriterion("for_user_id =", value, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdNotEqualTo(Long value) {
            addCriterion("for_user_id <>", value, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdGreaterThan(Long value) {
            addCriterion("for_user_id >", value, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("for_user_id >=", value, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdLessThan(Long value) {
            addCriterion("for_user_id <", value, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdLessThanOrEqualTo(Long value) {
            addCriterion("for_user_id <=", value, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdIn(List<Long> values) {
            addCriterion("for_user_id in", values, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdNotIn(List<Long> values) {
            addCriterion("for_user_id not in", values, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdBetween(Long value1, Long value2) {
            addCriterion("for_user_id between", value1, value2, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForUserIdNotBetween(Long value1, Long value2) {
            addCriterion("for_user_id not between", value1, value2, "forUserId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdIsNull() {
            addCriterion("for_order_id is null");
            return (Criteria) this;
        }

        public Criteria andForOrderIdIsNotNull() {
            addCriterion("for_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andForOrderIdEqualTo(Long value) {
            addCriterion("for_order_id =", value, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdNotEqualTo(Long value) {
            addCriterion("for_order_id <>", value, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdGreaterThan(Long value) {
            addCriterion("for_order_id >", value, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("for_order_id >=", value, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdLessThan(Long value) {
            addCriterion("for_order_id <", value, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("for_order_id <=", value, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdIn(List<Long> values) {
            addCriterion("for_order_id in", values, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdNotIn(List<Long> values) {
            addCriterion("for_order_id not in", values, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdBetween(Long value1, Long value2) {
            addCriterion("for_order_id between", value1, value2, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andForOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("for_order_id not between", value1, value2, "forOrderId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdIsNull() {
            addCriterion("card_template_id is null");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdIsNotNull() {
            addCriterion("card_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdEqualTo(Long value) {
            addCriterion("card_template_id =", value, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdNotEqualTo(Long value) {
            addCriterion("card_template_id <>", value, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdGreaterThan(Long value) {
            addCriterion("card_template_id >", value, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("card_template_id >=", value, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdLessThan(Long value) {
            addCriterion("card_template_id <", value, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdLessThanOrEqualTo(Long value) {
            addCriterion("card_template_id <=", value, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdIn(List<Long> values) {
            addCriterion("card_template_id in", values, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdNotIn(List<Long> values) {
            addCriterion("card_template_id not in", values, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdBetween(Long value1, Long value2) {
            addCriterion("card_template_id between", value1, value2, "cardTemplateId");
            return (Criteria) this;
        }

        public Criteria andCardTemplateIdNotBetween(Long value1, Long value2) {
            addCriterion("card_template_id not between", value1, value2, "cardTemplateId");
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