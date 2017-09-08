package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScheduleInfoExample() {
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

        public Criteria andProdIdIsNull() {
            addCriterion("prod_id is null");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNotNull() {
            addCriterion("prod_id is not null");
            return (Criteria) this;
        }

        public Criteria andProdIdEqualTo(Long value) {
            addCriterion("prod_id =", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotEqualTo(Long value) {
            addCriterion("prod_id <>", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThan(Long value) {
            addCriterion("prod_id >", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThanOrEqualTo(Long value) {
            addCriterion("prod_id >=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThan(Long value) {
            addCriterion("prod_id <", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThanOrEqualTo(Long value) {
            addCriterion("prod_id <=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdIn(List<Long> values) {
            addCriterion("prod_id in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotIn(List<Long> values) {
            addCriterion("prod_id not in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdBetween(Long value1, Long value2) {
            addCriterion("prod_id between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotBetween(Long value1, Long value2) {
            addCriterion("prod_id not between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNull() {
            addCriterion("detail_id is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(Long value) {
            addCriterion("detail_id =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(Long value) {
            addCriterion("detail_id <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(Long value) {
            addCriterion("detail_id >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("detail_id >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(Long value) {
            addCriterion("detail_id <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("detail_id <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<Long> values) {
            addCriterion("detail_id in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<Long> values) {
            addCriterion("detail_id not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(Long value1, Long value2) {
            addCriterion("detail_id between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("detail_id not between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andReserNumIsNull() {
            addCriterion("reser_num is null");
            return (Criteria) this;
        }

        public Criteria andReserNumIsNotNull() {
            addCriterion("reser_num is not null");
            return (Criteria) this;
        }

        public Criteria andReserNumEqualTo(Integer value) {
            addCriterion("reser_num =", value, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumNotEqualTo(Integer value) {
            addCriterion("reser_num <>", value, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumGreaterThan(Integer value) {
            addCriterion("reser_num >", value, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("reser_num >=", value, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumLessThan(Integer value) {
            addCriterion("reser_num <", value, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumLessThanOrEqualTo(Integer value) {
            addCriterion("reser_num <=", value, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumIn(List<Integer> values) {
            addCriterion("reser_num in", values, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumNotIn(List<Integer> values) {
            addCriterion("reser_num not in", values, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumBetween(Integer value1, Integer value2) {
            addCriterion("reser_num between", value1, value2, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserNumNotBetween(Integer value1, Integer value2) {
            addCriterion("reser_num not between", value1, value2, "reserNum");
            return (Criteria) this;
        }

        public Criteria andReserTimeIsNull() {
            addCriterion("reser_time is null");
            return (Criteria) this;
        }

        public Criteria andReserTimeIsNotNull() {
            addCriterion("reser_time is not null");
            return (Criteria) this;
        }

        public Criteria andReserTimeEqualTo(Date value) {
            addCriterion("reser_time =", value, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeNotEqualTo(Date value) {
            addCriterion("reser_time <>", value, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeGreaterThan(Date value) {
            addCriterion("reser_time >", value, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reser_time >=", value, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeLessThan(Date value) {
            addCriterion("reser_time <", value, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeLessThanOrEqualTo(Date value) {
            addCriterion("reser_time <=", value, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeIn(List<Date> values) {
            addCriterion("reser_time in", values, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeNotIn(List<Date> values) {
            addCriterion("reser_time not in", values, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeBetween(Date value1, Date value2) {
            addCriterion("reser_time between", value1, value2, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserTimeNotBetween(Date value1, Date value2) {
            addCriterion("reser_time not between", value1, value2, "reserTime");
            return (Criteria) this;
        }

        public Criteria andReserPhoneIsNull() {
            addCriterion("reser_phone is null");
            return (Criteria) this;
        }

        public Criteria andReserPhoneIsNotNull() {
            addCriterion("reser_phone is not null");
            return (Criteria) this;
        }

        public Criteria andReserPhoneEqualTo(String value) {
            addCriterion("reser_phone =", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneNotEqualTo(String value) {
            addCriterion("reser_phone <>", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneGreaterThan(String value) {
            addCriterion("reser_phone >", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("reser_phone >=", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneLessThan(String value) {
            addCriterion("reser_phone <", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneLessThanOrEqualTo(String value) {
            addCriterion("reser_phone <=", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneLike(String value) {
            addCriterion("reser_phone like", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneNotLike(String value) {
            addCriterion("reser_phone not like", value, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneIn(List<String> values) {
            addCriterion("reser_phone in", values, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneNotIn(List<String> values) {
            addCriterion("reser_phone not in", values, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneBetween(String value1, String value2) {
            addCriterion("reser_phone between", value1, value2, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserPhoneNotBetween(String value1, String value2) {
            addCriterion("reser_phone not between", value1, value2, "reserPhone");
            return (Criteria) this;
        }

        public Criteria andReserManIsNull() {
            addCriterion("reser_man is null");
            return (Criteria) this;
        }

        public Criteria andReserManIsNotNull() {
            addCriterion("reser_man is not null");
            return (Criteria) this;
        }

        public Criteria andReserManEqualTo(String value) {
            addCriterion("reser_man =", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManNotEqualTo(String value) {
            addCriterion("reser_man <>", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManGreaterThan(String value) {
            addCriterion("reser_man >", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManGreaterThanOrEqualTo(String value) {
            addCriterion("reser_man >=", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManLessThan(String value) {
            addCriterion("reser_man <", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManLessThanOrEqualTo(String value) {
            addCriterion("reser_man <=", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManLike(String value) {
            addCriterion("reser_man like", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManNotLike(String value) {
            addCriterion("reser_man not like", value, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManIn(List<String> values) {
            addCriterion("reser_man in", values, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManNotIn(List<String> values) {
            addCriterion("reser_man not in", values, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManBetween(String value1, String value2) {
            addCriterion("reser_man between", value1, value2, "reserMan");
            return (Criteria) this;
        }

        public Criteria andReserManNotBetween(String value1, String value2) {
            addCriterion("reser_man not between", value1, value2, "reserMan");
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
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