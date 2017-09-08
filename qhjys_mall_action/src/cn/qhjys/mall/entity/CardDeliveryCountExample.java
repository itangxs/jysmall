package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CardDeliveryCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardDeliveryCountExample() {
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

        public Criteria andCardReceiveDisplayIsNull() {
            addCriterion("card_receive_display is null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayIsNotNull() {
            addCriterion("card_receive_display is not null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayEqualTo(Integer value) {
            addCriterion("card_receive_display =", value, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayNotEqualTo(Integer value) {
            addCriterion("card_receive_display <>", value, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayGreaterThan(Integer value) {
            addCriterion("card_receive_display >", value, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_receive_display >=", value, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayLessThan(Integer value) {
            addCriterion("card_receive_display <", value, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("card_receive_display <=", value, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayIn(List<Integer> values) {
            addCriterion("card_receive_display in", values, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayNotIn(List<Integer> values) {
            addCriterion("card_receive_display not in", values, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_display between", value1, value2, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_display not between", value1, value2, "cardReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueIsNull() {
            addCriterion("card_receive_true is null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueIsNotNull() {
            addCriterion("card_receive_true is not null");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueEqualTo(Integer value) {
            addCriterion("card_receive_true =", value, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueNotEqualTo(Integer value) {
            addCriterion("card_receive_true <>", value, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueGreaterThan(Integer value) {
            addCriterion("card_receive_true >", value, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_receive_true >=", value, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueLessThan(Integer value) {
            addCriterion("card_receive_true <", value, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueLessThanOrEqualTo(Integer value) {
            addCriterion("card_receive_true <=", value, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueIn(List<Integer> values) {
            addCriterion("card_receive_true in", values, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueNotIn(List<Integer> values) {
            addCriterion("card_receive_true not in", values, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_true between", value1, value2, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardReceiveTrueNotBetween(Integer value1, Integer value2) {
            addCriterion("card_receive_true not between", value1, value2, "cardReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayIsNull() {
            addCriterion("card_push_display is null");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayIsNotNull() {
            addCriterion("card_push_display is not null");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayEqualTo(Integer value) {
            addCriterion("card_push_display =", value, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayNotEqualTo(Integer value) {
            addCriterion("card_push_display <>", value, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayGreaterThan(Integer value) {
            addCriterion("card_push_display >", value, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_push_display >=", value, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayLessThan(Integer value) {
            addCriterion("card_push_display <", value, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("card_push_display <=", value, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayIn(List<Integer> values) {
            addCriterion("card_push_display in", values, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayNotIn(List<Integer> values) {
            addCriterion("card_push_display not in", values, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayBetween(Integer value1, Integer value2) {
            addCriterion("card_push_display between", value1, value2, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("card_push_display not between", value1, value2, "cardPushDisplay");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueIsNull() {
            addCriterion("card_push_true is null");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueIsNotNull() {
            addCriterion("card_push_true is not null");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueEqualTo(Integer value) {
            addCriterion("card_push_true =", value, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueNotEqualTo(Integer value) {
            addCriterion("card_push_true <>", value, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueGreaterThan(Integer value) {
            addCriterion("card_push_true >", value, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_push_true >=", value, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueLessThan(Integer value) {
            addCriterion("card_push_true <", value, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueLessThanOrEqualTo(Integer value) {
            addCriterion("card_push_true <=", value, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueIn(List<Integer> values) {
            addCriterion("card_push_true in", values, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueNotIn(List<Integer> values) {
            addCriterion("card_push_true not in", values, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueBetween(Integer value1, Integer value2) {
            addCriterion("card_push_true between", value1, value2, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardPushTrueNotBetween(Integer value1, Integer value2) {
            addCriterion("card_push_true not between", value1, value2, "cardPushTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayIsNull() {
            addCriterion("card_share_display is null");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayIsNotNull() {
            addCriterion("card_share_display is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayEqualTo(Integer value) {
            addCriterion("card_share_display =", value, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayNotEqualTo(Integer value) {
            addCriterion("card_share_display <>", value, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayGreaterThan(Integer value) {
            addCriterion("card_share_display >", value, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_display >=", value, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayLessThan(Integer value) {
            addCriterion("card_share_display <", value, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_display <=", value, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayIn(List<Integer> values) {
            addCriterion("card_share_display in", values, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayNotIn(List<Integer> values) {
            addCriterion("card_share_display not in", values, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayBetween(Integer value1, Integer value2) {
            addCriterion("card_share_display between", value1, value2, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_display not between", value1, value2, "cardShareDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueIsNull() {
            addCriterion("card_share_true is null");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueIsNotNull() {
            addCriterion("card_share_true is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueEqualTo(Integer value) {
            addCriterion("card_share_true =", value, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueNotEqualTo(Integer value) {
            addCriterion("card_share_true <>", value, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueGreaterThan(Integer value) {
            addCriterion("card_share_true >", value, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_true >=", value, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueLessThan(Integer value) {
            addCriterion("card_share_true <", value, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_true <=", value, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueIn(List<Integer> values) {
            addCriterion("card_share_true in", values, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueNotIn(List<Integer> values) {
            addCriterion("card_share_true not in", values, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueBetween(Integer value1, Integer value2) {
            addCriterion("card_share_true between", value1, value2, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareTrueNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_true not between", value1, value2, "cardShareTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayIsNull() {
            addCriterion("card_share_receive_display is null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayIsNotNull() {
            addCriterion("card_share_receive_display is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayEqualTo(Integer value) {
            addCriterion("card_share_receive_display =", value, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayNotEqualTo(Integer value) {
            addCriterion("card_share_receive_display <>", value, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayGreaterThan(Integer value) {
            addCriterion("card_share_receive_display >", value, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_display >=", value, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayLessThan(Integer value) {
            addCriterion("card_share_receive_display <", value, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_display <=", value, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayIn(List<Integer> values) {
            addCriterion("card_share_receive_display in", values, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayNotIn(List<Integer> values) {
            addCriterion("card_share_receive_display not in", values, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_display between", value1, value2, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveDisplayNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_display not between", value1, value2, "cardShareReceiveDisplay");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueIsNull() {
            addCriterion("card_share_receive_true is null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueIsNotNull() {
            addCriterion("card_share_receive_true is not null");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueEqualTo(Integer value) {
            addCriterion("card_share_receive_true =", value, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueNotEqualTo(Integer value) {
            addCriterion("card_share_receive_true <>", value, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueGreaterThan(Integer value) {
            addCriterion("card_share_receive_true >", value, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_true >=", value, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueLessThan(Integer value) {
            addCriterion("card_share_receive_true <", value, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueLessThanOrEqualTo(Integer value) {
            addCriterion("card_share_receive_true <=", value, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueIn(List<Integer> values) {
            addCriterion("card_share_receive_true in", values, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueNotIn(List<Integer> values) {
            addCriterion("card_share_receive_true not in", values, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_true between", value1, value2, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCardShareReceiveTrueNotBetween(Integer value1, Integer value2) {
            addCriterion("card_share_receive_true not between", value1, value2, "cardShareReceiveTrue");
            return (Criteria) this;
        }

        public Criteria andCountDateIsNull() {
            addCriterion("count_date is null");
            return (Criteria) this;
        }

        public Criteria andCountDateIsNotNull() {
            addCriterion("count_date is not null");
            return (Criteria) this;
        }

        public Criteria andCountDateEqualTo(Date value) {
            addCriterionForJDBCDate("count_date =", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("count_date <>", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateGreaterThan(Date value) {
            addCriterionForJDBCDate("count_date >", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("count_date >=", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateLessThan(Date value) {
            addCriterionForJDBCDate("count_date <", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("count_date <=", value, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateIn(List<Date> values) {
            addCriterionForJDBCDate("count_date in", values, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("count_date not in", values, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("count_date between", value1, value2, "countDate");
            return (Criteria) this;
        }

        public Criteria andCountDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("count_date not between", value1, value2, "countDate");
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

        public Criteria andWriteOffIsNull() {
            addCriterion("write_off is null");
            return (Criteria) this;
        }

        public Criteria andWriteOffIsNotNull() {
            addCriterion("write_off is not null");
            return (Criteria) this;
        }

        public Criteria andWriteOffEqualTo(Integer value) {
            addCriterion("write_off =", value, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffNotEqualTo(Integer value) {
            addCriterion("write_off <>", value, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffGreaterThan(Integer value) {
            addCriterion("write_off >", value, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffGreaterThanOrEqualTo(Integer value) {
            addCriterion("write_off >=", value, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffLessThan(Integer value) {
            addCriterion("write_off <", value, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffLessThanOrEqualTo(Integer value) {
            addCriterion("write_off <=", value, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffIn(List<Integer> values) {
            addCriterion("write_off in", values, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffNotIn(List<Integer> values) {
            addCriterion("write_off not in", values, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffBetween(Integer value1, Integer value2) {
            addCriterion("write_off between", value1, value2, "writeOff");
            return (Criteria) this;
        }

        public Criteria andWriteOffNotBetween(Integer value1, Integer value2) {
            addCriterion("write_off not between", value1, value2, "writeOff");
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