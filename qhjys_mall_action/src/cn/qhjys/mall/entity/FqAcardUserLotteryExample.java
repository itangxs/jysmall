package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FqAcardUserLotteryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqAcardUserLotteryExample() {
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

        public Criteria andAcardRecordIdIsNull() {
            addCriterion("acard_record_id is null");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdIsNotNull() {
            addCriterion("acard_record_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdEqualTo(Long value) {
            addCriterion("acard_record_id =", value, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdNotEqualTo(Long value) {
            addCriterion("acard_record_id <>", value, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdGreaterThan(Long value) {
            addCriterion("acard_record_id >", value, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdGreaterThanOrEqualTo(Long value) {
            addCriterion("acard_record_id >=", value, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdLessThan(Long value) {
            addCriterion("acard_record_id <", value, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdLessThanOrEqualTo(Long value) {
            addCriterion("acard_record_id <=", value, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdIn(List<Long> values) {
            addCriterion("acard_record_id in", values, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdNotIn(List<Long> values) {
            addCriterion("acard_record_id not in", values, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdBetween(Long value1, Long value2) {
            addCriterion("acard_record_id between", value1, value2, "acardRecordId");
            return (Criteria) this;
        }

        public Criteria andAcardRecordIdNotBetween(Long value1, Long value2) {
            addCriterion("acard_record_id not between", value1, value2, "acardRecordId");
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

        public Criteria andAcardPrizeIdIsNull() {
            addCriterion("acard_prize_id is null");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdIsNotNull() {
            addCriterion("acard_prize_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdEqualTo(Long value) {
            addCriterion("acard_prize_id =", value, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdNotEqualTo(Long value) {
            addCriterion("acard_prize_id <>", value, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdGreaterThan(Long value) {
            addCriterion("acard_prize_id >", value, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("acard_prize_id >=", value, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdLessThan(Long value) {
            addCriterion("acard_prize_id <", value, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdLessThanOrEqualTo(Long value) {
            addCriterion("acard_prize_id <=", value, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdIn(List<Long> values) {
            addCriterion("acard_prize_id in", values, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdNotIn(List<Long> values) {
            addCriterion("acard_prize_id not in", values, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdBetween(Long value1, Long value2) {
            addCriterion("acard_prize_id between", value1, value2, "acardPrizeId");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeIdNotBetween(Long value1, Long value2) {
            addCriterion("acard_prize_id not between", value1, value2, "acardPrizeId");
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

        public Criteria andAcardPrizeNameIsNull() {
            addCriterion("acard_prize_name is null");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameIsNotNull() {
            addCriterion("acard_prize_name is not null");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameEqualTo(String value) {
            addCriterion("acard_prize_name =", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameNotEqualTo(String value) {
            addCriterion("acard_prize_name <>", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameGreaterThan(String value) {
            addCriterion("acard_prize_name >", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameGreaterThanOrEqualTo(String value) {
            addCriterion("acard_prize_name >=", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameLessThan(String value) {
            addCriterion("acard_prize_name <", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameLessThanOrEqualTo(String value) {
            addCriterion("acard_prize_name <=", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameLike(String value) {
            addCriterion("acard_prize_name like", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameNotLike(String value) {
            addCriterion("acard_prize_name not like", value, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameIn(List<String> values) {
            addCriterion("acard_prize_name in", values, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameNotIn(List<String> values) {
            addCriterion("acard_prize_name not in", values, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameBetween(String value1, String value2) {
            addCriterion("acard_prize_name between", value1, value2, "acardPrizeName");
            return (Criteria) this;
        }

        public Criteria andAcardPrizeNameNotBetween(String value1, String value2) {
            addCriterion("acard_prize_name not between", value1, value2, "acardPrizeName");
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