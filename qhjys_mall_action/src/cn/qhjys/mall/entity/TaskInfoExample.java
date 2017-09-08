package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskInfoExample() {
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

        public Criteria andTaskNameIsNull() {
            addCriterion("task_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskNameIsNotNull() {
            addCriterion("task_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskNameEqualTo(String value) {
            addCriterion("task_name =", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotEqualTo(String value) {
            addCriterion("task_name <>", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThan(String value) {
            addCriterion("task_name >", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_name >=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThan(String value) {
            addCriterion("task_name <", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLessThanOrEqualTo(String value) {
            addCriterion("task_name <=", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameLike(String value) {
            addCriterion("task_name like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotLike(String value) {
            addCriterion("task_name not like", value, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameIn(List<String> values) {
            addCriterion("task_name in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotIn(List<String> values) {
            addCriterion("task_name not in", values, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameBetween(String value1, String value2) {
            addCriterion("task_name between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskNameNotBetween(String value1, String value2) {
            addCriterion("task_name not between", value1, value2, "taskName");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("task_type is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("task_type is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(Integer value) {
            addCriterion("task_type =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(Integer value) {
            addCriterion("task_type <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(Integer value) {
            addCriterion("task_type >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_type >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(Integer value) {
            addCriterion("task_type <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(Integer value) {
            addCriterion("task_type <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<Integer> values) {
            addCriterion("task_type in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<Integer> values) {
            addCriterion("task_type not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(Integer value1, Integer value2) {
            addCriterion("task_type between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("task_type not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskFromIsNull() {
            addCriterion("task_from is null");
            return (Criteria) this;
        }

        public Criteria andTaskFromIsNotNull() {
            addCriterion("task_from is not null");
            return (Criteria) this;
        }

        public Criteria andTaskFromEqualTo(String value) {
            addCriterion("task_from =", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotEqualTo(String value) {
            addCriterion("task_from <>", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromGreaterThan(String value) {
            addCriterion("task_from >", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromGreaterThanOrEqualTo(String value) {
            addCriterion("task_from >=", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromLessThan(String value) {
            addCriterion("task_from <", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromLessThanOrEqualTo(String value) {
            addCriterion("task_from <=", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromLike(String value) {
            addCriterion("task_from like", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotLike(String value) {
            addCriterion("task_from not like", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromIn(List<String> values) {
            addCriterion("task_from in", values, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotIn(List<String> values) {
            addCriterion("task_from not in", values, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromBetween(String value1, String value2) {
            addCriterion("task_from between", value1, value2, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotBetween(String value1, String value2) {
            addCriterion("task_from not between", value1, value2, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskLevelIsNull() {
            addCriterion("task_level is null");
            return (Criteria) this;
        }

        public Criteria andTaskLevelIsNotNull() {
            addCriterion("task_level is not null");
            return (Criteria) this;
        }

        public Criteria andTaskLevelEqualTo(Integer value) {
            addCriterion("task_level =", value, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelNotEqualTo(Integer value) {
            addCriterion("task_level <>", value, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelGreaterThan(Integer value) {
            addCriterion("task_level >", value, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_level >=", value, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelLessThan(Integer value) {
            addCriterion("task_level <", value, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelLessThanOrEqualTo(Integer value) {
            addCriterion("task_level <=", value, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelIn(List<Integer> values) {
            addCriterion("task_level in", values, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelNotIn(List<Integer> values) {
            addCriterion("task_level not in", values, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelBetween(Integer value1, Integer value2) {
            addCriterion("task_level between", value1, value2, "taskLevel");
            return (Criteria) this;
        }

        public Criteria andTaskLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("task_level not between", value1, value2, "taskLevel");
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

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andOffShelfIsNull() {
            addCriterion("off_shelf is null");
            return (Criteria) this;
        }

        public Criteria andOffShelfIsNotNull() {
            addCriterion("off_shelf is not null");
            return (Criteria) this;
        }

        public Criteria andOffShelfEqualTo(Date value) {
            addCriterion("off_shelf =", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotEqualTo(Date value) {
            addCriterion("off_shelf <>", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfGreaterThan(Date value) {
            addCriterion("off_shelf >", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfGreaterThanOrEqualTo(Date value) {
            addCriterion("off_shelf >=", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfLessThan(Date value) {
            addCriterion("off_shelf <", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfLessThanOrEqualTo(Date value) {
            addCriterion("off_shelf <=", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfIn(List<Date> values) {
            addCriterion("off_shelf in", values, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotIn(List<Date> values) {
            addCriterion("off_shelf not in", values, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfBetween(Date value1, Date value2) {
            addCriterion("off_shelf between", value1, value2, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotBetween(Date value1, Date value2) {
            addCriterion("off_shelf not between", value1, value2, "offShelf");
            return (Criteria) this;
        }

        public Criteria andTaskTotalIsNull() {
            addCriterion("task_total is null");
            return (Criteria) this;
        }

        public Criteria andTaskTotalIsNotNull() {
            addCriterion("task_total is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTotalEqualTo(BigDecimal value) {
            addCriterion("task_total =", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalNotEqualTo(BigDecimal value) {
            addCriterion("task_total <>", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalGreaterThan(BigDecimal value) {
            addCriterion("task_total >", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("task_total >=", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalLessThan(BigDecimal value) {
            addCriterion("task_total <", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("task_total <=", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalIn(List<BigDecimal> values) {
            addCriterion("task_total in", values, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalNotIn(List<BigDecimal> values) {
            addCriterion("task_total not in", values, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("task_total between", value1, value2, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("task_total not between", value1, value2, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilIsNull() {
            addCriterion("task_fulfil is null");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilIsNotNull() {
            addCriterion("task_fulfil is not null");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilEqualTo(BigDecimal value) {
            addCriterion("task_fulfil =", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilNotEqualTo(BigDecimal value) {
            addCriterion("task_fulfil <>", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilGreaterThan(BigDecimal value) {
            addCriterion("task_fulfil >", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("task_fulfil >=", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilLessThan(BigDecimal value) {
            addCriterion("task_fulfil <", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilLessThanOrEqualTo(BigDecimal value) {
            addCriterion("task_fulfil <=", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilIn(List<BigDecimal> values) {
            addCriterion("task_fulfil in", values, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilNotIn(List<BigDecimal> values) {
            addCriterion("task_fulfil not in", values, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("task_fulfil between", value1, value2, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("task_fulfil not between", value1, value2, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIsNull() {
            addCriterion("plan_time is null");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIsNotNull() {
            addCriterion("plan_time is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTimeEqualTo(String value) {
            addCriterion("plan_time =", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotEqualTo(String value) {
            addCriterion("plan_time <>", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeGreaterThan(String value) {
            addCriterion("plan_time >", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeGreaterThanOrEqualTo(String value) {
            addCriterion("plan_time >=", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLessThan(String value) {
            addCriterion("plan_time <", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLessThanOrEqualTo(String value) {
            addCriterion("plan_time <=", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLike(String value) {
            addCriterion("plan_time like", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotLike(String value) {
            addCriterion("plan_time not like", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIn(List<String> values) {
            addCriterion("plan_time in", values, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotIn(List<String> values) {
            addCriterion("plan_time not in", values, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeBetween(String value1, String value2) {
            addCriterion("plan_time between", value1, value2, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotBetween(String value1, String value2) {
            addCriterion("plan_time not between", value1, value2, "planTime");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardIsNull() {
            addCriterion("fulfil_reward is null");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardIsNotNull() {
            addCriterion("fulfil_reward is not null");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardEqualTo(BigDecimal value) {
            addCriterion("fulfil_reward =", value, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardNotEqualTo(BigDecimal value) {
            addCriterion("fulfil_reward <>", value, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardGreaterThan(BigDecimal value) {
            addCriterion("fulfil_reward >", value, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fulfil_reward >=", value, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardLessThan(BigDecimal value) {
            addCriterion("fulfil_reward <", value, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fulfil_reward <=", value, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardIn(List<BigDecimal> values) {
            addCriterion("fulfil_reward in", values, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardNotIn(List<BigDecimal> values) {
            addCriterion("fulfil_reward not in", values, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fulfil_reward between", value1, value2, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andFulfilRewardNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fulfil_reward not between", value1, value2, "fulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardIsNull() {
            addCriterion("unfulfil_reward is null");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardIsNotNull() {
            addCriterion("unfulfil_reward is not null");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardEqualTo(BigDecimal value) {
            addCriterion("unfulfil_reward =", value, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardNotEqualTo(BigDecimal value) {
            addCriterion("unfulfil_reward <>", value, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardGreaterThan(BigDecimal value) {
            addCriterion("unfulfil_reward >", value, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unfulfil_reward >=", value, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardLessThan(BigDecimal value) {
            addCriterion("unfulfil_reward <", value, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unfulfil_reward <=", value, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardIn(List<BigDecimal> values) {
            addCriterion("unfulfil_reward in", values, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardNotIn(List<BigDecimal> values) {
            addCriterion("unfulfil_reward not in", values, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unfulfil_reward between", value1, value2, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andUnfulfilRewardNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unfulfil_reward not between", value1, value2, "unfulfilReward");
            return (Criteria) this;
        }

        public Criteria andImagesIsNull() {
            addCriterion("images is null");
            return (Criteria) this;
        }

        public Criteria andImagesIsNotNull() {
            addCriterion("images is not null");
            return (Criteria) this;
        }

        public Criteria andImagesEqualTo(String value) {
            addCriterion("images =", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotEqualTo(String value) {
            addCriterion("images <>", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThan(String value) {
            addCriterion("images >", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThanOrEqualTo(String value) {
            addCriterion("images >=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThan(String value) {
            addCriterion("images <", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThanOrEqualTo(String value) {
            addCriterion("images <=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLike(String value) {
            addCriterion("images like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotLike(String value) {
            addCriterion("images not like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesIn(List<String> values) {
            addCriterion("images in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotIn(List<String> values) {
            addCriterion("images not in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesBetween(String value1, String value2) {
            addCriterion("images between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotBetween(String value1, String value2) {
            addCriterion("images not between", value1, value2, "images");
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

        public Criteria andProjectIsNull() {
            addCriterion("project is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsNotNull() {
            addCriterion("project is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEqualTo(String value) {
            addCriterion("project =", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotEqualTo(String value) {
            addCriterion("project <>", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThan(String value) {
            addCriterion("project >", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThanOrEqualTo(String value) {
            addCriterion("project >=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThan(String value) {
            addCriterion("project <", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThanOrEqualTo(String value) {
            addCriterion("project <=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLike(String value) {
            addCriterion("project like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotLike(String value) {
            addCriterion("project not like", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectIn(List<String> values) {
            addCriterion("project in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotIn(List<String> values) {
            addCriterion("project not in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectBetween(String value1, String value2) {
            addCriterion("project between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotBetween(String value1, String value2) {
            addCriterion("project not between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectUrlIsNull() {
            addCriterion("project_url is null");
            return (Criteria) this;
        }

        public Criteria andProjectUrlIsNotNull() {
            addCriterion("project_url is not null");
            return (Criteria) this;
        }

        public Criteria andProjectUrlEqualTo(String value) {
            addCriterion("project_url =", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlNotEqualTo(String value) {
            addCriterion("project_url <>", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlGreaterThan(String value) {
            addCriterion("project_url >", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlGreaterThanOrEqualTo(String value) {
            addCriterion("project_url >=", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlLessThan(String value) {
            addCriterion("project_url <", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlLessThanOrEqualTo(String value) {
            addCriterion("project_url <=", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlLike(String value) {
            addCriterion("project_url like", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlNotLike(String value) {
            addCriterion("project_url not like", value, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlIn(List<String> values) {
            addCriterion("project_url in", values, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlNotIn(List<String> values) {
            addCriterion("project_url not in", values, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlBetween(String value1, String value2) {
            addCriterion("project_url between", value1, value2, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectUrlNotBetween(String value1, String value2) {
            addCriterion("project_url not between", value1, value2, "projectUrl");
            return (Criteria) this;
        }

        public Criteria andProjectPassIsNull() {
            addCriterion("project_pass is null");
            return (Criteria) this;
        }

        public Criteria andProjectPassIsNotNull() {
            addCriterion("project_pass is not null");
            return (Criteria) this;
        }

        public Criteria andProjectPassEqualTo(String value) {
            addCriterion("project_pass =", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassNotEqualTo(String value) {
            addCriterion("project_pass <>", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassGreaterThan(String value) {
            addCriterion("project_pass >", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassGreaterThanOrEqualTo(String value) {
            addCriterion("project_pass >=", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassLessThan(String value) {
            addCriterion("project_pass <", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassLessThanOrEqualTo(String value) {
            addCriterion("project_pass <=", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassLike(String value) {
            addCriterion("project_pass like", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassNotLike(String value) {
            addCriterion("project_pass not like", value, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassIn(List<String> values) {
            addCriterion("project_pass in", values, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassNotIn(List<String> values) {
            addCriterion("project_pass not in", values, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassBetween(String value1, String value2) {
            addCriterion("project_pass between", value1, value2, "projectPass");
            return (Criteria) this;
        }

        public Criteria andProjectPassNotBetween(String value1, String value2) {
            addCriterion("project_pass not between", value1, value2, "projectPass");
            return (Criteria) this;
        }

        public Criteria andInfoUrlIsNull() {
            addCriterion("info_url is null");
            return (Criteria) this;
        }

        public Criteria andInfoUrlIsNotNull() {
            addCriterion("info_url is not null");
            return (Criteria) this;
        }

        public Criteria andInfoUrlEqualTo(String value) {
            addCriterion("info_url =", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlNotEqualTo(String value) {
            addCriterion("info_url <>", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlGreaterThan(String value) {
            addCriterion("info_url >", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("info_url >=", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlLessThan(String value) {
            addCriterion("info_url <", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlLessThanOrEqualTo(String value) {
            addCriterion("info_url <=", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlLike(String value) {
            addCriterion("info_url like", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlNotLike(String value) {
            addCriterion("info_url not like", value, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlIn(List<String> values) {
            addCriterion("info_url in", values, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlNotIn(List<String> values) {
            addCriterion("info_url not in", values, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlBetween(String value1, String value2) {
            addCriterion("info_url between", value1, value2, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoUrlNotBetween(String value1, String value2) {
            addCriterion("info_url not between", value1, value2, "infoUrl");
            return (Criteria) this;
        }

        public Criteria andInfoNameIsNull() {
            addCriterion("info_name is null");
            return (Criteria) this;
        }

        public Criteria andInfoNameIsNotNull() {
            addCriterion("info_name is not null");
            return (Criteria) this;
        }

        public Criteria andInfoNameEqualTo(String value) {
            addCriterion("info_name =", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotEqualTo(String value) {
            addCriterion("info_name <>", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameGreaterThan(String value) {
            addCriterion("info_name >", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameGreaterThanOrEqualTo(String value) {
            addCriterion("info_name >=", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameLessThan(String value) {
            addCriterion("info_name <", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameLessThanOrEqualTo(String value) {
            addCriterion("info_name <=", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameLike(String value) {
            addCriterion("info_name like", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotLike(String value) {
            addCriterion("info_name not like", value, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameIn(List<String> values) {
            addCriterion("info_name in", values, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotIn(List<String> values) {
            addCriterion("info_name not in", values, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameBetween(String value1, String value2) {
            addCriterion("info_name between", value1, value2, "infoName");
            return (Criteria) this;
        }

        public Criteria andInfoNameNotBetween(String value1, String value2) {
            addCriterion("info_name not between", value1, value2, "infoName");
            return (Criteria) this;
        }

        public Criteria andBgimgIsNull() {
            addCriterion("bgimg is null");
            return (Criteria) this;
        }

        public Criteria andBgimgIsNotNull() {
            addCriterion("bgimg is not null");
            return (Criteria) this;
        }

        public Criteria andBgimgEqualTo(String value) {
            addCriterion("bgimg =", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgNotEqualTo(String value) {
            addCriterion("bgimg <>", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgGreaterThan(String value) {
            addCriterion("bgimg >", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgGreaterThanOrEqualTo(String value) {
            addCriterion("bgimg >=", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgLessThan(String value) {
            addCriterion("bgimg <", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgLessThanOrEqualTo(String value) {
            addCriterion("bgimg <=", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgLike(String value) {
            addCriterion("bgimg like", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgNotLike(String value) {
            addCriterion("bgimg not like", value, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgIn(List<String> values) {
            addCriterion("bgimg in", values, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgNotIn(List<String> values) {
            addCriterion("bgimg not in", values, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgBetween(String value1, String value2) {
            addCriterion("bgimg between", value1, value2, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgimgNotBetween(String value1, String value2) {
            addCriterion("bgimg not between", value1, value2, "bgimg");
            return (Criteria) this;
        }

        public Criteria andBgcolorIsNull() {
            addCriterion("bgcolor is null");
            return (Criteria) this;
        }

        public Criteria andBgcolorIsNotNull() {
            addCriterion("bgcolor is not null");
            return (Criteria) this;
        }

        public Criteria andBgcolorEqualTo(String value) {
            addCriterion("bgcolor =", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorNotEqualTo(String value) {
            addCriterion("bgcolor <>", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorGreaterThan(String value) {
            addCriterion("bgcolor >", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorGreaterThanOrEqualTo(String value) {
            addCriterion("bgcolor >=", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorLessThan(String value) {
            addCriterion("bgcolor <", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorLessThanOrEqualTo(String value) {
            addCriterion("bgcolor <=", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorLike(String value) {
            addCriterion("bgcolor like", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorNotLike(String value) {
            addCriterion("bgcolor not like", value, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorIn(List<String> values) {
            addCriterion("bgcolor in", values, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorNotIn(List<String> values) {
            addCriterion("bgcolor not in", values, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorBetween(String value1, String value2) {
            addCriterion("bgcolor between", value1, value2, "bgcolor");
            return (Criteria) this;
        }

        public Criteria andBgcolorNotBetween(String value1, String value2) {
            addCriterion("bgcolor not between", value1, value2, "bgcolor");
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