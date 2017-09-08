package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysTaskExample() {
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

        public Criteria andTaskTotalIsNull() {
            addCriterion("task_total is null");
            return (Criteria) this;
        }

        public Criteria andTaskTotalIsNotNull() {
            addCriterion("task_total is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTotalEqualTo(Integer value) {
            addCriterion("task_total =", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalNotEqualTo(Integer value) {
            addCriterion("task_total <>", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalGreaterThan(Integer value) {
            addCriterion("task_total >", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_total >=", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalLessThan(Integer value) {
            addCriterion("task_total <", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalLessThanOrEqualTo(Integer value) {
            addCriterion("task_total <=", value, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalIn(List<Integer> values) {
            addCriterion("task_total in", values, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalNotIn(List<Integer> values) {
            addCriterion("task_total not in", values, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalBetween(Integer value1, Integer value2) {
            addCriterion("task_total between", value1, value2, "taskTotal");
            return (Criteria) this;
        }

        public Criteria andTaskTotalNotBetween(Integer value1, Integer value2) {
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

        public Criteria andTaskFulfilEqualTo(Integer value) {
            addCriterion("task_fulfil =", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilNotEqualTo(Integer value) {
            addCriterion("task_fulfil <>", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilGreaterThan(Integer value) {
            addCriterion("task_fulfil >", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_fulfil >=", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilLessThan(Integer value) {
            addCriterion("task_fulfil <", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilLessThanOrEqualTo(Integer value) {
            addCriterion("task_fulfil <=", value, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilIn(List<Integer> values) {
            addCriterion("task_fulfil in", values, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilNotIn(List<Integer> values) {
            addCriterion("task_fulfil not in", values, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilBetween(Integer value1, Integer value2) {
            addCriterion("task_fulfil between", value1, value2, "taskFulfil");
            return (Criteria) this;
        }

        public Criteria andTaskFulfilNotBetween(Integer value1, Integer value2) {
            addCriterion("task_fulfil not between", value1, value2, "taskFulfil");
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

        public Criteria andTaskFromIsNull() {
            addCriterion("task_from is null");
            return (Criteria) this;
        }

        public Criteria andTaskFromIsNotNull() {
            addCriterion("task_from is not null");
            return (Criteria) this;
        }

        public Criteria andTaskFromEqualTo(Long value) {
            addCriterion("task_from =", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotEqualTo(Long value) {
            addCriterion("task_from <>", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromGreaterThan(Long value) {
            addCriterion("task_from >", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromGreaterThanOrEqualTo(Long value) {
            addCriterion("task_from >=", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromLessThan(Long value) {
            addCriterion("task_from <", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromLessThanOrEqualTo(Long value) {
            addCriterion("task_from <=", value, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromIn(List<Long> values) {
            addCriterion("task_from in", values, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotIn(List<Long> values) {
            addCriterion("task_from not in", values, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromBetween(Long value1, Long value2) {
            addCriterion("task_from between", value1, value2, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andTaskFromNotBetween(Long value1, Long value2) {
            addCriterion("task_from not between", value1, value2, "taskFrom");
            return (Criteria) this;
        }

        public Criteria andStaffIsNull() {
            addCriterion("staff is null");
            return (Criteria) this;
        }

        public Criteria andStaffIsNotNull() {
            addCriterion("staff is not null");
            return (Criteria) this;
        }

        public Criteria andStaffEqualTo(String value) {
            addCriterion("staff =", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffNotEqualTo(String value) {
            addCriterion("staff <>", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffGreaterThan(String value) {
            addCriterion("staff >", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffGreaterThanOrEqualTo(String value) {
            addCriterion("staff >=", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffLessThan(String value) {
            addCriterion("staff <", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffLessThanOrEqualTo(String value) {
            addCriterion("staff <=", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffLike(String value) {
            addCriterion("staff like", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffNotLike(String value) {
            addCriterion("staff not like", value, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffIn(List<String> values) {
            addCriterion("staff in", values, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffNotIn(List<String> values) {
            addCriterion("staff not in", values, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffBetween(String value1, String value2) {
            addCriterion("staff between", value1, value2, "staff");
            return (Criteria) this;
        }

        public Criteria andStaffNotBetween(String value1, String value2) {
            addCriterion("staff not between", value1, value2, "staff");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNull() {
            addCriterion("verify_time is null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNotNull() {
            addCriterion("verify_time is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeEqualTo(Integer value) {
            addCriterion("verify_time =", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotEqualTo(Integer value) {
            addCriterion("verify_time <>", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThan(Integer value) {
            addCriterion("verify_time >", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("verify_time >=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThan(Integer value) {
            addCriterion("verify_time <", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThanOrEqualTo(Integer value) {
            addCriterion("verify_time <=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIn(List<Integer> values) {
            addCriterion("verify_time in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotIn(List<Integer> values) {
            addCriterion("verify_time not in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeBetween(Integer value1, Integer value2) {
            addCriterion("verify_time between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("verify_time not between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankIsNull() {
            addCriterion("appraise_rank is null");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankIsNotNull() {
            addCriterion("appraise_rank is not null");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankEqualTo(Integer value) {
            addCriterion("appraise_rank =", value, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankNotEqualTo(Integer value) {
            addCriterion("appraise_rank <>", value, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankGreaterThan(Integer value) {
            addCriterion("appraise_rank >", value, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankGreaterThanOrEqualTo(Integer value) {
            addCriterion("appraise_rank >=", value, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankLessThan(Integer value) {
            addCriterion("appraise_rank <", value, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankLessThanOrEqualTo(Integer value) {
            addCriterion("appraise_rank <=", value, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankIn(List<Integer> values) {
            addCriterion("appraise_rank in", values, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankNotIn(List<Integer> values) {
            addCriterion("appraise_rank not in", values, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankBetween(Integer value1, Integer value2) {
            addCriterion("appraise_rank between", value1, value2, "appraiseRank");
            return (Criteria) this;
        }

        public Criteria andAppraiseRankNotBetween(Integer value1, Integer value2) {
            addCriterion("appraise_rank not between", value1, value2, "appraiseRank");
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

        public Criteria andAppidIsNull() {
            addCriterion("appid is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appid is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appid =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appid <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appid >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appid >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appid <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appid <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appid like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appid not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appid in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appid not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appid between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appid not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andSecretIsNull() {
            addCriterion("secret is null");
            return (Criteria) this;
        }

        public Criteria andSecretIsNotNull() {
            addCriterion("secret is not null");
            return (Criteria) this;
        }

        public Criteria andSecretEqualTo(String value) {
            addCriterion("secret =", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotEqualTo(String value) {
            addCriterion("secret <>", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThan(String value) {
            addCriterion("secret >", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretGreaterThanOrEqualTo(String value) {
            addCriterion("secret >=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThan(String value) {
            addCriterion("secret <", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLessThanOrEqualTo(String value) {
            addCriterion("secret <=", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretLike(String value) {
            addCriterion("secret like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotLike(String value) {
            addCriterion("secret not like", value, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretIn(List<String> values) {
            addCriterion("secret in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotIn(List<String> values) {
            addCriterion("secret not in", values, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretBetween(String value1, String value2) {
            addCriterion("secret between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andSecretNotBetween(String value1, String value2) {
            addCriterion("secret not between", value1, value2, "secret");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("\" access_token\" is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("\" access_token\" is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("\" access_token\" =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("\" access_token\" <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("\" access_token\" >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("\" access_token\" >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("\" access_token\" <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("\" access_token\" <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("\" access_token\" like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("\" access_token\" not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("\" access_token\" in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("\" access_token\" not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("\" access_token\" between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("\" access_token\" not between", value1, value2, "accessToken");
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