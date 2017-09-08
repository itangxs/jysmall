package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LotteryDishExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LotteryDishExample() {
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

        public Criteria andLotteryIdIsNull() {
            addCriterion("lottery_id is null");
            return (Criteria) this;
        }

        public Criteria andLotteryIdIsNotNull() {
            addCriterion("lottery_id is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryIdEqualTo(Long value) {
            addCriterion("lottery_id =", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotEqualTo(Long value) {
            addCriterion("lottery_id <>", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdGreaterThan(Long value) {
            addCriterion("lottery_id >", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("lottery_id >=", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdLessThan(Long value) {
            addCriterion("lottery_id <", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdLessThanOrEqualTo(Long value) {
            addCriterion("lottery_id <=", value, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdIn(List<Long> values) {
            addCriterion("lottery_id in", values, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotIn(List<Long> values) {
            addCriterion("lottery_id not in", values, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdBetween(Long value1, Long value2) {
            addCriterion("lottery_id between", value1, value2, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andLotteryIdNotBetween(Long value1, Long value2) {
            addCriterion("lottery_id not between", value1, value2, "lotteryId");
            return (Criteria) this;
        }

        public Criteria andRankLevelIsNull() {
            addCriterion("rank_level is null");
            return (Criteria) this;
        }

        public Criteria andRankLevelIsNotNull() {
            addCriterion("rank_level is not null");
            return (Criteria) this;
        }

        public Criteria andRankLevelEqualTo(Integer value) {
            addCriterion("rank_level =", value, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelNotEqualTo(Integer value) {
            addCriterion("rank_level <>", value, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelGreaterThan(Integer value) {
            addCriterion("rank_level >", value, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("rank_level >=", value, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelLessThan(Integer value) {
            addCriterion("rank_level <", value, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelLessThanOrEqualTo(Integer value) {
            addCriterion("rank_level <=", value, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelIn(List<Integer> values) {
            addCriterion("rank_level in", values, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelNotIn(List<Integer> values) {
            addCriterion("rank_level not in", values, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelBetween(Integer value1, Integer value2) {
            addCriterion("rank_level between", value1, value2, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andRankLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("rank_level not between", value1, value2, "rankLevel");
            return (Criteria) this;
        }

        public Criteria andDishNameIsNull() {
            addCriterion("dish_name is null");
            return (Criteria) this;
        }

        public Criteria andDishNameIsNotNull() {
            addCriterion("dish_name is not null");
            return (Criteria) this;
        }

        public Criteria andDishNameEqualTo(String value) {
            addCriterion("dish_name =", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameNotEqualTo(String value) {
            addCriterion("dish_name <>", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameGreaterThan(String value) {
            addCriterion("dish_name >", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameGreaterThanOrEqualTo(String value) {
            addCriterion("dish_name >=", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameLessThan(String value) {
            addCriterion("dish_name <", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameLessThanOrEqualTo(String value) {
            addCriterion("dish_name <=", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameLike(String value) {
            addCriterion("dish_name like", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameNotLike(String value) {
            addCriterion("dish_name not like", value, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameIn(List<String> values) {
            addCriterion("dish_name in", values, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameNotIn(List<String> values) {
            addCriterion("dish_name not in", values, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameBetween(String value1, String value2) {
            addCriterion("dish_name between", value1, value2, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishNameNotBetween(String value1, String value2) {
            addCriterion("dish_name not between", value1, value2, "dishName");
            return (Criteria) this;
        }

        public Criteria andDishImageIsNull() {
            addCriterion("dish_image is null");
            return (Criteria) this;
        }

        public Criteria andDishImageIsNotNull() {
            addCriterion("dish_image is not null");
            return (Criteria) this;
        }

        public Criteria andDishImageEqualTo(String value) {
            addCriterion("dish_image =", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageNotEqualTo(String value) {
            addCriterion("dish_image <>", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageGreaterThan(String value) {
            addCriterion("dish_image >", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageGreaterThanOrEqualTo(String value) {
            addCriterion("dish_image >=", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageLessThan(String value) {
            addCriterion("dish_image <", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageLessThanOrEqualTo(String value) {
            addCriterion("dish_image <=", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageLike(String value) {
            addCriterion("dish_image like", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageNotLike(String value) {
            addCriterion("dish_image not like", value, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageIn(List<String> values) {
            addCriterion("dish_image in", values, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageNotIn(List<String> values) {
            addCriterion("dish_image not in", values, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageBetween(String value1, String value2) {
            addCriterion("dish_image between", value1, value2, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishImageNotBetween(String value1, String value2) {
            addCriterion("dish_image not between", value1, value2, "dishImage");
            return (Criteria) this;
        }

        public Criteria andDishInfoIsNull() {
            addCriterion("dish_info is null");
            return (Criteria) this;
        }

        public Criteria andDishInfoIsNotNull() {
            addCriterion("dish_info is not null");
            return (Criteria) this;
        }

        public Criteria andDishInfoEqualTo(String value) {
            addCriterion("dish_info =", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoNotEqualTo(String value) {
            addCriterion("dish_info <>", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoGreaterThan(String value) {
            addCriterion("dish_info >", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoGreaterThanOrEqualTo(String value) {
            addCriterion("dish_info >=", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoLessThan(String value) {
            addCriterion("dish_info <", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoLessThanOrEqualTo(String value) {
            addCriterion("dish_info <=", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoLike(String value) {
            addCriterion("dish_info like", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoNotLike(String value) {
            addCriterion("dish_info not like", value, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoIn(List<String> values) {
            addCriterion("dish_info in", values, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoNotIn(List<String> values) {
            addCriterion("dish_info not in", values, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoBetween(String value1, String value2) {
            addCriterion("dish_info between", value1, value2, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andDishInfoNotBetween(String value1, String value2) {
            addCriterion("dish_info not between", value1, value2, "dishInfo");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNull() {
            addCriterion("limit_num is null");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNotNull() {
            addCriterion("limit_num is not null");
            return (Criteria) this;
        }

        public Criteria andLimitNumEqualTo(Integer value) {
            addCriterion("limit_num =", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotEqualTo(Integer value) {
            addCriterion("limit_num <>", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThan(Integer value) {
            addCriterion("limit_num >", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_num >=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThan(Integer value) {
            addCriterion("limit_num <", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("limit_num <=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumIn(List<Integer> values) {
            addCriterion("limit_num in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotIn(List<Integer> values) {
            addCriterion("limit_num not in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("limit_num between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_num not between", value1, value2, "limitNum");
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
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
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