package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FqClerkMonthExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqClerkMonthExample() {
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

        public Criteria andClerkTimeIsNull() {
            addCriterion("clerk_time is null");
            return (Criteria) this;
        }

        public Criteria andClerkTimeIsNotNull() {
            addCriterion("clerk_time is not null");
            return (Criteria) this;
        }

        public Criteria andClerkTimeEqualTo(Date value) {
            addCriterionForJDBCDate("clerk_time =", value, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("clerk_time <>", value, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("clerk_time >", value, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("clerk_time >=", value, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeLessThan(Date value) {
            addCriterionForJDBCDate("clerk_time <", value, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("clerk_time <=", value, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeIn(List<Date> values) {
            addCriterionForJDBCDate("clerk_time in", values, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("clerk_time not in", values, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("clerk_time between", value1, value2, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andClerkTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("clerk_time not between", value1, value2, "clerkTime");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("province_id is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("province_id is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Long value) {
            addCriterion("province_id =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Long value) {
            addCriterion("province_id <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Long value) {
            addCriterion("province_id >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("province_id >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Long value) {
            addCriterion("province_id <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Long value) {
            addCriterion("province_id <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Long> values) {
            addCriterion("province_id in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Long> values) {
            addCriterion("province_id not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Long value1, Long value2) {
            addCriterion("province_id between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Long value1, Long value2) {
            addCriterion("province_id not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNull() {
            addCriterion("province_name is null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIsNotNull() {
            addCriterion("province_name is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceNameEqualTo(String value) {
            addCriterion("province_name =", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotEqualTo(String value) {
            addCriterion("province_name <>", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThan(String value) {
            addCriterion("province_name >", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameGreaterThanOrEqualTo(String value) {
            addCriterion("province_name >=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThan(String value) {
            addCriterion("province_name <", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLessThanOrEqualTo(String value) {
            addCriterion("province_name <=", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameLike(String value) {
            addCriterion("province_name like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotLike(String value) {
            addCriterion("province_name not like", value, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameIn(List<String> values) {
            addCriterion("province_name in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotIn(List<String> values) {
            addCriterion("province_name not in", values, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameBetween(String value1, String value2) {
            addCriterion("province_name between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andProvinceNameNotBetween(String value1, String value2) {
            addCriterion("province_name not between", value1, value2, "provinceName");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Long value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Long value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Long value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Long value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Long value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Long> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Long> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Long value1, Long value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Long value1, Long value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNull() {
            addCriterion("branch_id is null");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNotNull() {
            addCriterion("branch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBranchIdEqualTo(Long value) {
            addCriterion("branch_id =", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotEqualTo(Long value) {
            addCriterion("branch_id <>", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThan(Long value) {
            addCriterion("branch_id >", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThanOrEqualTo(Long value) {
            addCriterion("branch_id >=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThan(Long value) {
            addCriterion("branch_id <", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThanOrEqualTo(Long value) {
            addCriterion("branch_id <=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIn(List<Long> values) {
            addCriterion("branch_id in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotIn(List<Long> values) {
            addCriterion("branch_id not in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdBetween(Long value1, Long value2) {
            addCriterion("branch_id between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotBetween(Long value1, Long value2) {
            addCriterion("branch_id not between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNull() {
            addCriterion("branch_name is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("branch_name =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("branch_name <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("branch_name >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("branch_name >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("branch_name <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("branch_name <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("branch_name like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("branch_name not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("branch_name in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("branch_name not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("branch_name between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("branch_name not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(Long value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Long value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Long value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Long value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Long value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Long> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Long> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Long value1, Long value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Long value1, Long value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNull() {
            addCriterion("team_name is null");
            return (Criteria) this;
        }

        public Criteria andTeamNameIsNotNull() {
            addCriterion("team_name is not null");
            return (Criteria) this;
        }

        public Criteria andTeamNameEqualTo(String value) {
            addCriterion("team_name =", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotEqualTo(String value) {
            addCriterion("team_name <>", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThan(String value) {
            addCriterion("team_name >", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameGreaterThanOrEqualTo(String value) {
            addCriterion("team_name >=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThan(String value) {
            addCriterion("team_name <", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLessThanOrEqualTo(String value) {
            addCriterion("team_name <=", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameLike(String value) {
            addCriterion("team_name like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotLike(String value) {
            addCriterion("team_name not like", value, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameIn(List<String> values) {
            addCriterion("team_name in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotIn(List<String> values) {
            addCriterion("team_name not in", values, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameBetween(String value1, String value2) {
            addCriterion("team_name between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andTeamNameNotBetween(String value1, String value2) {
            addCriterion("team_name not between", value1, value2, "teamName");
            return (Criteria) this;
        }

        public Criteria andClerkIdIsNull() {
            addCriterion("clerk_id is null");
            return (Criteria) this;
        }

        public Criteria andClerkIdIsNotNull() {
            addCriterion("clerk_id is not null");
            return (Criteria) this;
        }

        public Criteria andClerkIdEqualTo(Long value) {
            addCriterion("clerk_id =", value, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdNotEqualTo(Long value) {
            addCriterion("clerk_id <>", value, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdGreaterThan(Long value) {
            addCriterion("clerk_id >", value, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdGreaterThanOrEqualTo(Long value) {
            addCriterion("clerk_id >=", value, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdLessThan(Long value) {
            addCriterion("clerk_id <", value, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdLessThanOrEqualTo(Long value) {
            addCriterion("clerk_id <=", value, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdIn(List<Long> values) {
            addCriterion("clerk_id in", values, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdNotIn(List<Long> values) {
            addCriterion("clerk_id not in", values, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdBetween(Long value1, Long value2) {
            addCriterion("clerk_id between", value1, value2, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkIdNotBetween(Long value1, Long value2) {
            addCriterion("clerk_id not between", value1, value2, "clerkId");
            return (Criteria) this;
        }

        public Criteria andClerkNameIsNull() {
            addCriterion("clerk_name is null");
            return (Criteria) this;
        }

        public Criteria andClerkNameIsNotNull() {
            addCriterion("clerk_name is not null");
            return (Criteria) this;
        }

        public Criteria andClerkNameEqualTo(String value) {
            addCriterion("clerk_name =", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameNotEqualTo(String value) {
            addCriterion("clerk_name <>", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameGreaterThan(String value) {
            addCriterion("clerk_name >", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameGreaterThanOrEqualTo(String value) {
            addCriterion("clerk_name >=", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameLessThan(String value) {
            addCriterion("clerk_name <", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameLessThanOrEqualTo(String value) {
            addCriterion("clerk_name <=", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameLike(String value) {
            addCriterion("clerk_name like", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameNotLike(String value) {
            addCriterion("clerk_name not like", value, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameIn(List<String> values) {
            addCriterion("clerk_name in", values, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameNotIn(List<String> values) {
            addCriterion("clerk_name not in", values, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameBetween(String value1, String value2) {
            addCriterion("clerk_name between", value1, value2, "clerkName");
            return (Criteria) this;
        }

        public Criteria andClerkNameNotBetween(String value1, String value2) {
            addCriterion("clerk_name not between", value1, value2, "clerkName");
            return (Criteria) this;
        }

        public Criteria andCountMonthIsNull() {
            addCriterion("count_month is null");
            return (Criteria) this;
        }

        public Criteria andCountMonthIsNotNull() {
            addCriterion("count_month is not null");
            return (Criteria) this;
        }

        public Criteria andCountMonthEqualTo(String value) {
            addCriterion("count_month =", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthNotEqualTo(String value) {
            addCriterion("count_month <>", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthGreaterThan(String value) {
            addCriterion("count_month >", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthGreaterThanOrEqualTo(String value) {
            addCriterion("count_month >=", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthLessThan(String value) {
            addCriterion("count_month <", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthLessThanOrEqualTo(String value) {
            addCriterion("count_month <=", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthLike(String value) {
            addCriterion("count_month like", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthNotLike(String value) {
            addCriterion("count_month not like", value, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthIn(List<String> values) {
            addCriterion("count_month in", values, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthNotIn(List<String> values) {
            addCriterion("count_month not in", values, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthBetween(String value1, String value2) {
            addCriterion("count_month between", value1, value2, "countMonth");
            return (Criteria) this;
        }

        public Criteria andCountMonthNotBetween(String value1, String value2) {
            addCriterion("count_month not between", value1, value2, "countMonth");
            return (Criteria) this;
        }

        public Criteria andSignedNumIsNull() {
            addCriterion("signed_num is null");
            return (Criteria) this;
        }

        public Criteria andSignedNumIsNotNull() {
            addCriterion("signed_num is not null");
            return (Criteria) this;
        }

        public Criteria andSignedNumEqualTo(Integer value) {
            addCriterion("signed_num =", value, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumNotEqualTo(Integer value) {
            addCriterion("signed_num <>", value, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumGreaterThan(Integer value) {
            addCriterion("signed_num >", value, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("signed_num >=", value, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumLessThan(Integer value) {
            addCriterion("signed_num <", value, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumLessThanOrEqualTo(Integer value) {
            addCriterion("signed_num <=", value, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumIn(List<Integer> values) {
            addCriterion("signed_num in", values, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumNotIn(List<Integer> values) {
            addCriterion("signed_num not in", values, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumBetween(Integer value1, Integer value2) {
            addCriterion("signed_num between", value1, value2, "signedNum");
            return (Criteria) this;
        }

        public Criteria andSignedNumNotBetween(Integer value1, Integer value2) {
            addCriterion("signed_num not between", value1, value2, "signedNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumIsNull() {
            addCriterion("effective_num is null");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumIsNotNull() {
            addCriterion("effective_num is not null");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumEqualTo(Integer value) {
            addCriterion("effective_num =", value, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumNotEqualTo(Integer value) {
            addCriterion("effective_num <>", value, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumGreaterThan(Integer value) {
            addCriterion("effective_num >", value, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("effective_num >=", value, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumLessThan(Integer value) {
            addCriterion("effective_num <", value, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumLessThanOrEqualTo(Integer value) {
            addCriterion("effective_num <=", value, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumIn(List<Integer> values) {
            addCriterion("effective_num in", values, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumNotIn(List<Integer> values) {
            addCriterion("effective_num not in", values, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumBetween(Integer value1, Integer value2) {
            addCriterion("effective_num between", value1, value2, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andEffectiveNumNotBetween(Integer value1, Integer value2) {
            addCriterion("effective_num not between", value1, value2, "effectiveNum");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIsNull() {
            addCriterion("total_money is null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIsNotNull() {
            addCriterion("total_money is not null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyEqualTo(BigDecimal value) {
            addCriterion("total_money =", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotEqualTo(BigDecimal value) {
            addCriterion("total_money <>", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyGreaterThan(BigDecimal value) {
            addCriterion("total_money >", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money >=", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyLessThan(BigDecimal value) {
            addCriterion("total_money <", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money <=", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIn(List<BigDecimal> values) {
            addCriterion("total_money in", values, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotIn(List<BigDecimal> values) {
            addCriterion("total_money not in", values, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money between", value1, value2, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money not between", value1, value2, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyIsNull() {
            addCriterion("cash_money is null");
            return (Criteria) this;
        }

        public Criteria andCashMoneyIsNotNull() {
            addCriterion("cash_money is not null");
            return (Criteria) this;
        }

        public Criteria andCashMoneyEqualTo(BigDecimal value) {
            addCriterion("cash_money =", value, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyNotEqualTo(BigDecimal value) {
            addCriterion("cash_money <>", value, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyGreaterThan(BigDecimal value) {
            addCriterion("cash_money >", value, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_money >=", value, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyLessThan(BigDecimal value) {
            addCriterion("cash_money <", value, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("cash_money <=", value, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyIn(List<BigDecimal> values) {
            addCriterion("cash_money in", values, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyNotIn(List<BigDecimal> values) {
            addCriterion("cash_money not in", values, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_money between", value1, value2, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andCashMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("cash_money not between", value1, value2, "cashMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyIsNull() {
            addCriterion("clerk_money is null");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyIsNotNull() {
            addCriterion("clerk_money is not null");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyEqualTo(BigDecimal value) {
            addCriterion("clerk_money =", value, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyNotEqualTo(BigDecimal value) {
            addCriterion("clerk_money <>", value, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyGreaterThan(BigDecimal value) {
            addCriterion("clerk_money >", value, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("clerk_money >=", value, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyLessThan(BigDecimal value) {
            addCriterion("clerk_money <", value, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("clerk_money <=", value, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyIn(List<BigDecimal> values) {
            addCriterion("clerk_money in", values, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyNotIn(List<BigDecimal> values) {
            addCriterion("clerk_money not in", values, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("clerk_money between", value1, value2, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andClerkMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("clerk_money not between", value1, value2, "clerkMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyIsNull() {
            addCriterion("team_money is null");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyIsNotNull() {
            addCriterion("team_money is not null");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyEqualTo(BigDecimal value) {
            addCriterion("team_money =", value, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyNotEqualTo(BigDecimal value) {
            addCriterion("team_money <>", value, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyGreaterThan(BigDecimal value) {
            addCriterion("team_money >", value, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("team_money >=", value, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyLessThan(BigDecimal value) {
            addCriterion("team_money <", value, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("team_money <=", value, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyIn(List<BigDecimal> values) {
            addCriterion("team_money in", values, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyNotIn(List<BigDecimal> values) {
            addCriterion("team_money not in", values, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("team_money between", value1, value2, "teamMoney");
            return (Criteria) this;
        }

        public Criteria andTeamMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("team_money not between", value1, value2, "teamMoney");
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