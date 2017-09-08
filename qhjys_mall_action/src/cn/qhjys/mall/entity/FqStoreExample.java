package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FqStoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqStoreExample() {
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

        public Criteria andStoreLogoIsNull() {
            addCriterion("store_logo is null");
            return (Criteria) this;
        }

        public Criteria andStoreLogoIsNotNull() {
            addCriterion("store_logo is not null");
            return (Criteria) this;
        }

        public Criteria andStoreLogoEqualTo(String value) {
            addCriterion("store_logo =", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoNotEqualTo(String value) {
            addCriterion("store_logo <>", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoGreaterThan(String value) {
            addCriterion("store_logo >", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoGreaterThanOrEqualTo(String value) {
            addCriterion("store_logo >=", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoLessThan(String value) {
            addCriterion("store_logo <", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoLessThanOrEqualTo(String value) {
            addCriterion("store_logo <=", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoLike(String value) {
            addCriterion("store_logo like", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoNotLike(String value) {
            addCriterion("store_logo not like", value, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoIn(List<String> values) {
            addCriterion("store_logo in", values, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoNotIn(List<String> values) {
            addCriterion("store_logo not in", values, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoBetween(String value1, String value2) {
            addCriterion("store_logo between", value1, value2, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andStoreLogoNotBetween(String value1, String value2) {
            addCriterion("store_logo not between", value1, value2, "storeLogo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoIsNull() {
            addCriterion("activity_info is null");
            return (Criteria) this;
        }

        public Criteria andActivityInfoIsNotNull() {
            addCriterion("activity_info is not null");
            return (Criteria) this;
        }

        public Criteria andActivityInfoEqualTo(String value) {
            addCriterion("activity_info =", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoNotEqualTo(String value) {
            addCriterion("activity_info <>", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoGreaterThan(String value) {
            addCriterion("activity_info >", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoGreaterThanOrEqualTo(String value) {
            addCriterion("activity_info >=", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoLessThan(String value) {
            addCriterion("activity_info <", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoLessThanOrEqualTo(String value) {
            addCriterion("activity_info <=", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoLike(String value) {
            addCriterion("activity_info like", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoNotLike(String value) {
            addCriterion("activity_info not like", value, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoIn(List<String> values) {
            addCriterion("activity_info in", values, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoNotIn(List<String> values) {
            addCriterion("activity_info not in", values, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoBetween(String value1, String value2) {
            addCriterion("activity_info between", value1, value2, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andActivityInfoNotBetween(String value1, String value2) {
            addCriterion("activity_info not between", value1, value2, "activityInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoIsNull() {
            addCriterion("store_info is null");
            return (Criteria) this;
        }

        public Criteria andStoreInfoIsNotNull() {
            addCriterion("store_info is not null");
            return (Criteria) this;
        }

        public Criteria andStoreInfoEqualTo(String value) {
            addCriterion("store_info =", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoNotEqualTo(String value) {
            addCriterion("store_info <>", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoGreaterThan(String value) {
            addCriterion("store_info >", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoGreaterThanOrEqualTo(String value) {
            addCriterion("store_info >=", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoLessThan(String value) {
            addCriterion("store_info <", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoLessThanOrEqualTo(String value) {
            addCriterion("store_info <=", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoLike(String value) {
            addCriterion("store_info like", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoNotLike(String value) {
            addCriterion("store_info not like", value, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoIn(List<String> values) {
            addCriterion("store_info in", values, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoNotIn(List<String> values) {
            addCriterion("store_info not in", values, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoBetween(String value1, String value2) {
            addCriterion("store_info between", value1, value2, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andStoreInfoNotBetween(String value1, String value2) {
            addCriterion("store_info not between", value1, value2, "storeInfo");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeIsNull() {
            addCriterion("traffic_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeIsNotNull() {
            addCriterion("traffic_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeEqualTo(String value) {
            addCriterion("traffic_begin_time =", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeNotEqualTo(String value) {
            addCriterion("traffic_begin_time <>", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeGreaterThan(String value) {
            addCriterion("traffic_begin_time >", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("traffic_begin_time >=", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeLessThan(String value) {
            addCriterion("traffic_begin_time <", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeLessThanOrEqualTo(String value) {
            addCriterion("traffic_begin_time <=", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeLike(String value) {
            addCriterion("traffic_begin_time like", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeNotLike(String value) {
            addCriterion("traffic_begin_time not like", value, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeIn(List<String> values) {
            addCriterion("traffic_begin_time in", values, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeNotIn(List<String> values) {
            addCriterion("traffic_begin_time not in", values, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeBetween(String value1, String value2) {
            addCriterion("traffic_begin_time between", value1, value2, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficBeginTimeNotBetween(String value1, String value2) {
            addCriterion("traffic_begin_time not between", value1, value2, "trafficBeginTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeIsNull() {
            addCriterion("traffic_end_time is null");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeIsNotNull() {
            addCriterion("traffic_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeEqualTo(String value) {
            addCriterion("traffic_end_time =", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeNotEqualTo(String value) {
            addCriterion("traffic_end_time <>", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeGreaterThan(String value) {
            addCriterion("traffic_end_time >", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("traffic_end_time >=", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeLessThan(String value) {
            addCriterion("traffic_end_time <", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeLessThanOrEqualTo(String value) {
            addCriterion("traffic_end_time <=", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeLike(String value) {
            addCriterion("traffic_end_time like", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeNotLike(String value) {
            addCriterion("traffic_end_time not like", value, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeIn(List<String> values) {
            addCriterion("traffic_end_time in", values, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeNotIn(List<String> values) {
            addCriterion("traffic_end_time not in", values, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeBetween(String value1, String value2) {
            addCriterion("traffic_end_time between", value1, value2, "trafficEndTime");
            return (Criteria) this;
        }

        public Criteria andTrafficEndTimeNotBetween(String value1, String value2) {
            addCriterion("traffic_end_time not between", value1, value2, "trafficEndTime");
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

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andProportionIsNull() {
            addCriterion("proportion is null");
            return (Criteria) this;
        }

        public Criteria andProportionIsNotNull() {
            addCriterion("proportion is not null");
            return (Criteria) this;
        }

        public Criteria andProportionEqualTo(Integer value) {
            addCriterion("proportion =", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotEqualTo(Integer value) {
            addCriterion("proportion <>", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionGreaterThan(Integer value) {
            addCriterion("proportion >", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionGreaterThanOrEqualTo(Integer value) {
            addCriterion("proportion >=", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionLessThan(Integer value) {
            addCriterion("proportion <", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionLessThanOrEqualTo(Integer value) {
            addCriterion("proportion <=", value, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionIn(List<Integer> values) {
            addCriterion("proportion in", values, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotIn(List<Integer> values) {
            addCriterion("proportion not in", values, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionBetween(Integer value1, Integer value2) {
            addCriterion("proportion between", value1, value2, "proportion");
            return (Criteria) this;
        }

        public Criteria andProportionNotBetween(Integer value1, Integer value2) {
            addCriterion("proportion not between", value1, value2, "proportion");
            return (Criteria) this;
        }

        public Criteria andLocationIdIsNull() {
            addCriterion("location_id is null");
            return (Criteria) this;
        }

        public Criteria andLocationIdIsNotNull() {
            addCriterion("location_id is not null");
            return (Criteria) this;
        }

        public Criteria andLocationIdEqualTo(Long value) {
            addCriterion("location_id =", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotEqualTo(Long value) {
            addCriterion("location_id <>", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdGreaterThan(Long value) {
            addCriterion("location_id >", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("location_id >=", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLessThan(Long value) {
            addCriterion("location_id <", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdLessThanOrEqualTo(Long value) {
            addCriterion("location_id <=", value, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdIn(List<Long> values) {
            addCriterion("location_id in", values, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotIn(List<Long> values) {
            addCriterion("location_id not in", values, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdBetween(Long value1, Long value2) {
            addCriterion("location_id between", value1, value2, "locationId");
            return (Criteria) this;
        }

        public Criteria andLocationIdNotBetween(Long value1, Long value2) {
            addCriterion("location_id not between", value1, value2, "locationId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdIsNull() {
            addCriterion("cuisine_id is null");
            return (Criteria) this;
        }

        public Criteria andCuisineIdIsNotNull() {
            addCriterion("cuisine_id is not null");
            return (Criteria) this;
        }

        public Criteria andCuisineIdEqualTo(Long value) {
            addCriterion("cuisine_id =", value, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdNotEqualTo(Long value) {
            addCriterion("cuisine_id <>", value, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdGreaterThan(Long value) {
            addCriterion("cuisine_id >", value, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdGreaterThanOrEqualTo(Long value) {
            addCriterion("cuisine_id >=", value, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdLessThan(Long value) {
            addCriterion("cuisine_id <", value, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdLessThanOrEqualTo(Long value) {
            addCriterion("cuisine_id <=", value, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdIn(List<Long> values) {
            addCriterion("cuisine_id in", values, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdNotIn(List<Long> values) {
            addCriterion("cuisine_id not in", values, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdBetween(Long value1, Long value2) {
            addCriterion("cuisine_id between", value1, value2, "cuisineId");
            return (Criteria) this;
        }

        public Criteria andCuisineIdNotBetween(Long value1, Long value2) {
            addCriterion("cuisine_id not between", value1, value2, "cuisineId");
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

        public Criteria andStoreRebateIsNull() {
            addCriterion("store_rebate is null");
            return (Criteria) this;
        }

        public Criteria andStoreRebateIsNotNull() {
            addCriterion("store_rebate is not null");
            return (Criteria) this;
        }

        public Criteria andStoreRebateEqualTo(BigDecimal value) {
            addCriterion("store_rebate =", value, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateNotEqualTo(BigDecimal value) {
            addCriterion("store_rebate <>", value, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateGreaterThan(BigDecimal value) {
            addCriterion("store_rebate >", value, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("store_rebate >=", value, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateLessThan(BigDecimal value) {
            addCriterion("store_rebate <", value, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("store_rebate <=", value, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateIn(List<BigDecimal> values) {
            addCriterion("store_rebate in", values, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateNotIn(List<BigDecimal> values) {
            addCriterion("store_rebate not in", values, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("store_rebate between", value1, value2, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreRebateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("store_rebate not between", value1, value2, "storeRebate");
            return (Criteria) this;
        }

        public Criteria andStoreImageIsNull() {
            addCriterion("store_image is null");
            return (Criteria) this;
        }

        public Criteria andStoreImageIsNotNull() {
            addCriterion("store_image is not null");
            return (Criteria) this;
        }

        public Criteria andStoreImageEqualTo(String value) {
            addCriterion("store_image =", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageNotEqualTo(String value) {
            addCriterion("store_image <>", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageGreaterThan(String value) {
            addCriterion("store_image >", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageGreaterThanOrEqualTo(String value) {
            addCriterion("store_image >=", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageLessThan(String value) {
            addCriterion("store_image <", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageLessThanOrEqualTo(String value) {
            addCriterion("store_image <=", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageLike(String value) {
            addCriterion("store_image like", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageNotLike(String value) {
            addCriterion("store_image not like", value, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageIn(List<String> values) {
            addCriterion("store_image in", values, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageNotIn(List<String> values) {
            addCriterion("store_image not in", values, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageBetween(String value1, String value2) {
            addCriterion("store_image between", value1, value2, "storeImage");
            return (Criteria) this;
        }

        public Criteria andStoreImageNotBetween(String value1, String value2) {
            addCriterion("store_image not between", value1, value2, "storeImage");
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneIsNull() {
            addCriterion("clerk_phone is null");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneIsNotNull() {
            addCriterion("clerk_phone is not null");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneEqualTo(String value) {
            addCriterion("clerk_phone =", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneNotEqualTo(String value) {
            addCriterion("clerk_phone <>", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneGreaterThan(String value) {
            addCriterion("clerk_phone >", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("clerk_phone >=", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneLessThan(String value) {
            addCriterion("clerk_phone <", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneLessThanOrEqualTo(String value) {
            addCriterion("clerk_phone <=", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneLike(String value) {
            addCriterion("clerk_phone like", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneNotLike(String value) {
            addCriterion("clerk_phone not like", value, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneIn(List<String> values) {
            addCriterion("clerk_phone in", values, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneNotIn(List<String> values) {
            addCriterion("clerk_phone not in", values, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneBetween(String value1, String value2) {
            addCriterion("clerk_phone between", value1, value2, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andClerkPhoneNotBetween(String value1, String value2) {
            addCriterion("clerk_phone not between", value1, value2, "clerkPhone");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditIsNull() {
            addCriterion("daliy_credit is null");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditIsNotNull() {
            addCriterion("daliy_credit is not null");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditEqualTo(BigDecimal value) {
            addCriterion("daliy_credit =", value, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditNotEqualTo(BigDecimal value) {
            addCriterion("daliy_credit <>", value, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditGreaterThan(BigDecimal value) {
            addCriterion("daliy_credit >", value, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("daliy_credit >=", value, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditLessThan(BigDecimal value) {
            addCriterion("daliy_credit <", value, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditLessThanOrEqualTo(BigDecimal value) {
            addCriterion("daliy_credit <=", value, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditIn(List<BigDecimal> values) {
            addCriterion("daliy_credit in", values, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditNotIn(List<BigDecimal> values) {
            addCriterion("daliy_credit not in", values, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("daliy_credit between", value1, value2, "daliyCredit");
            return (Criteria) this;
        }

        public Criteria andDaliyCreditNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("daliy_credit not between", value1, value2, "daliyCredit");
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