package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class CardDeliveryPropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardDeliveryPropertyExample() {
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

        public Criteria andDeliverTypeIsNull() {
            addCriterion("deliver_type is null");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeIsNotNull() {
            addCriterion("deliver_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeEqualTo(Integer value) {
            addCriterion("deliver_type =", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeNotEqualTo(Integer value) {
            addCriterion("deliver_type <>", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeGreaterThan(Integer value) {
            addCriterion("deliver_type >", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_type >=", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeLessThan(Integer value) {
            addCriterion("deliver_type <", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_type <=", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeIn(List<Integer> values) {
            addCriterion("deliver_type in", values, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeNotIn(List<Integer> values) {
            addCriterion("deliver_type not in", values, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeBetween(Integer value1, Integer value2) {
            addCriterion("deliver_type between", value1, value2, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_type not between", value1, value2, "deliverType");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinIsNull() {
            addCriterion("card_receive_min is null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinIsNotNull() {
            addCriterion("card_receive_min is not null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinEqualTo(Integer value) {
            addCriterion("card_receive_min =", value, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinNotEqualTo(Integer value) {
            addCriterion("card_receive_min <>", value, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinGreaterThan(Integer value) {
            addCriterion("card_receive_min >", value, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_receive_min >=", value, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinLessThan(Integer value) {
            addCriterion("card_receive_min <", value, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinLessThanOrEqualTo(Integer value) {
            addCriterion("card_receive_min <=", value, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinIn(List<Integer> values) {
            addCriterion("card_receive_min in", values, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinNotIn(List<Integer> values) {
            addCriterion("card_receive_min not in", values, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_min between", value1, value2, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMinNotBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_min not between", value1, value2, "cardReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxIsNull() {
            addCriterion("card_receive_max is null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxIsNotNull() {
            addCriterion("card_receive_max is not null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxEqualTo(Integer value) {
            addCriterion("card_receive_max =", value, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxNotEqualTo(Integer value) {
            addCriterion("card_receive_max <>", value, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxGreaterThan(Integer value) {
            addCriterion("card_receive_max >", value, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_receive_max >=", value, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxLessThan(Integer value) {
            addCriterion("card_receive_max <", value, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxLessThanOrEqualTo(Integer value) {
            addCriterion("card_receive_max <=", value, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxIn(List<Integer> values) {
            addCriterion("card_receive_max in", values, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxNotIn(List<Integer> values) {
            addCriterion("card_receive_max not in", values, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_max between", value1, value2, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardReceiveMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_max not between", value1, value2, "cardReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMinIsNull() {
            addCriterion("card_push_min is null");
            return (Criteria) this;
        }

        public Criteria andCardPushMinIsNotNull() {
            addCriterion("card_push_min is not null");
            return (Criteria) this;
        }

        public Criteria andCardPushMinEqualTo(Integer value) {
            addCriterion("card_push_min =", value, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinNotEqualTo(Integer value) {
            addCriterion("card_push_min <>", value, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinGreaterThan(Integer value) {
            addCriterion("card_push_min >", value, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_push_min >=", value, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinLessThan(Integer value) {
            addCriterion("card_push_min <", value, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinLessThanOrEqualTo(Integer value) {
            addCriterion("card_push_min <=", value, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinIn(List<Integer> values) {
            addCriterion("card_push_min in", values, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinNotIn(List<Integer> values) {
            addCriterion("card_push_min not in", values, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinBetween(Integer value1, Integer value2) {
            addCriterion("card_push_min between", value1, value2, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMinNotBetween(Integer value1, Integer value2) {
            addCriterion("card_push_min not between", value1, value2, "cardPushMin");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxIsNull() {
            addCriterion("card_push_max is null");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxIsNotNull() {
            addCriterion("card_push_max is not null");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxEqualTo(Integer value) {
            addCriterion("card_push_max =", value, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxNotEqualTo(Integer value) {
            addCriterion("card_push_max <>", value, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxGreaterThan(Integer value) {
            addCriterion("card_push_max >", value, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_push_max >=", value, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxLessThan(Integer value) {
            addCriterion("card_push_max <", value, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxLessThanOrEqualTo(Integer value) {
            addCriterion("card_push_max <=", value, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxIn(List<Integer> values) {
            addCriterion("card_push_max in", values, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxNotIn(List<Integer> values) {
            addCriterion("card_push_max not in", values, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxBetween(Integer value1, Integer value2) {
            addCriterion("card_push_max between", value1, value2, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardPushMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("card_push_max not between", value1, value2, "cardPushMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMinIsNull() {
            addCriterion("card_share_min is null");
            return (Criteria) this;
        }

        public Criteria andCardShareMinIsNotNull() {
            addCriterion("card_share_min is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareMinEqualTo(Integer value) {
            addCriterion("card_share_min =", value, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinNotEqualTo(Integer value) {
            addCriterion("card_share_min <>", value, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinGreaterThan(Integer value) {
            addCriterion("card_share_min >", value, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_min >=", value, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinLessThan(Integer value) {
            addCriterion("card_share_min <", value, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_min <=", value, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinIn(List<Integer> values) {
            addCriterion("card_share_min in", values, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinNotIn(List<Integer> values) {
            addCriterion("card_share_min not in", values, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinBetween(Integer value1, Integer value2) {
            addCriterion("card_share_min between", value1, value2, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMinNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_min not between", value1, value2, "cardShareMin");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxIsNull() {
            addCriterion("card_share_max is null");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxIsNotNull() {
            addCriterion("card_share_max is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxEqualTo(Integer value) {
            addCriterion("card_share_max =", value, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxNotEqualTo(Integer value) {
            addCriterion("card_share_max <>", value, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxGreaterThan(Integer value) {
            addCriterion("card_share_max >", value, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_max >=", value, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxLessThan(Integer value) {
            addCriterion("card_share_max <", value, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_max <=", value, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxIn(List<Integer> values) {
            addCriterion("card_share_max in", values, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxNotIn(List<Integer> values) {
            addCriterion("card_share_max not in", values, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxBetween(Integer value1, Integer value2) {
            addCriterion("card_share_max between", value1, value2, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_max not between", value1, value2, "cardShareMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinIsNull() {
            addCriterion("card_share_receive_min is null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinIsNotNull() {
            addCriterion("card_share_receive_min is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinEqualTo(Integer value) {
            addCriterion("card_share_receive_min =", value, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinNotEqualTo(Integer value) {
            addCriterion("card_share_receive_min <>", value, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinGreaterThan(Integer value) {
            addCriterion("card_share_receive_min >", value, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_min >=", value, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinLessThan(Integer value) {
            addCriterion("card_share_receive_min <", value, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_min <=", value, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinIn(List<Integer> values) {
            addCriterion("card_share_receive_min in", values, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinNotIn(List<Integer> values) {
            addCriterion("card_share_receive_min not in", values, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_min between", value1, value2, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMinNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_min not between", value1, value2, "cardShareReceiveMin");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxIsNull() {
            addCriterion("card_share_receive_max is null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxIsNotNull() {
            addCriterion("card_share_receive_max is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxEqualTo(Integer value) {
            addCriterion("card_share_receive_max =", value, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxNotEqualTo(Integer value) {
            addCriterion("card_share_receive_max <>", value, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxGreaterThan(Integer value) {
            addCriterion("card_share_receive_max >", value, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_max >=", value, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxLessThan(Integer value) {
            addCriterion("card_share_receive_max <", value, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_max <=", value, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxIn(List<Integer> values) {
            addCriterion("card_share_receive_max in", values, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxNotIn(List<Integer> values) {
            addCriterion("card_share_receive_max not in", values, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_max between", value1, value2, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_max not between", value1, value2, "cardShareReceiveMax");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityIsNull() {
            addCriterion("first_rank_probability is null");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityIsNotNull() {
            addCriterion("first_rank_probability is not null");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityEqualTo(Integer value) {
            addCriterion("first_rank_probability =", value, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityNotEqualTo(Integer value) {
            addCriterion("first_rank_probability <>", value, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityGreaterThan(Integer value) {
            addCriterion("first_rank_probability >", value, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("first_rank_probability >=", value, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityLessThan(Integer value) {
            addCriterion("first_rank_probability <", value, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("first_rank_probability <=", value, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityIn(List<Integer> values) {
            addCriterion("first_rank_probability in", values, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityNotIn(List<Integer> values) {
            addCriterion("first_rank_probability not in", values, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("first_rank_probability between", value1, value2, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andFirstRankProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("first_rank_probability not between", value1, value2, "firstRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityIsNull() {
            addCriterion("second_rank_probability is null");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityIsNotNull() {
            addCriterion("second_rank_probability is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityEqualTo(Integer value) {
            addCriterion("second_rank_probability =", value, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityNotEqualTo(Integer value) {
            addCriterion("second_rank_probability <>", value, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityGreaterThan(Integer value) {
            addCriterion("second_rank_probability >", value, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("second_rank_probability >=", value, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityLessThan(Integer value) {
            addCriterion("second_rank_probability <", value, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("second_rank_probability <=", value, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityIn(List<Integer> values) {
            addCriterion("second_rank_probability in", values, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityNotIn(List<Integer> values) {
            addCriterion("second_rank_probability not in", values, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("second_rank_probability between", value1, value2, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andSecondRankProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("second_rank_probability not between", value1, value2, "secondRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityIsNull() {
            addCriterion("third_rank_probability is null");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityIsNotNull() {
            addCriterion("third_rank_probability is not null");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityEqualTo(Integer value) {
            addCriterion("third_rank_probability =", value, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityNotEqualTo(Integer value) {
            addCriterion("third_rank_probability <>", value, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityGreaterThan(Integer value) {
            addCriterion("third_rank_probability >", value, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_rank_probability >=", value, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityLessThan(Integer value) {
            addCriterion("third_rank_probability <", value, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("third_rank_probability <=", value, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityIn(List<Integer> values) {
            addCriterion("third_rank_probability in", values, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityNotIn(List<Integer> values) {
            addCriterion("third_rank_probability not in", values, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("third_rank_probability between", value1, value2, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andThirdRankProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("third_rank_probability not between", value1, value2, "thirdRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityIsNull() {
            addCriterion("fourth_rank_probability is null");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityIsNotNull() {
            addCriterion("fourth_rank_probability is not null");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityEqualTo(Integer value) {
            addCriterion("fourth_rank_probability =", value, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityNotEqualTo(Integer value) {
            addCriterion("fourth_rank_probability <>", value, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityGreaterThan(Integer value) {
            addCriterion("fourth_rank_probability >", value, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityGreaterThanOrEqualTo(Integer value) {
            addCriterion("fourth_rank_probability >=", value, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityLessThan(Integer value) {
            addCriterion("fourth_rank_probability <", value, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityLessThanOrEqualTo(Integer value) {
            addCriterion("fourth_rank_probability <=", value, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityIn(List<Integer> values) {
            addCriterion("fourth_rank_probability in", values, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityNotIn(List<Integer> values) {
            addCriterion("fourth_rank_probability not in", values, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityBetween(Integer value1, Integer value2) {
            addCriterion("fourth_rank_probability between", value1, value2, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andFourthRankProbabilityNotBetween(Integer value1, Integer value2) {
            addCriterion("fourth_rank_probability not between", value1, value2, "fourthRankProbability");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumIsNull() {
            addCriterion("delivery_num is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumIsNotNull() {
            addCriterion("delivery_num is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumEqualTo(Integer value) {
            addCriterion("delivery_num =", value, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumNotEqualTo(Integer value) {
            addCriterion("delivery_num <>", value, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumGreaterThan(Integer value) {
            addCriterion("delivery_num >", value, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_num >=", value, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumLessThan(Integer value) {
            addCriterion("delivery_num <", value, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_num <=", value, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumIn(List<Integer> values) {
            addCriterion("delivery_num in", values, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumNotIn(List<Integer> values) {
            addCriterion("delivery_num not in", values, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumBetween(Integer value1, Integer value2) {
            addCriterion("delivery_num between", value1, value2, "deliveryNum");
            return (Criteria) this;
        }

        public Criteria andDeliveryNumNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_num not between", value1, value2, "deliveryNum");
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