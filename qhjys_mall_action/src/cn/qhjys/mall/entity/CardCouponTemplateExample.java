package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardCouponTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardCouponTemplateExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCouponCfgIsNull() {
            addCriterion("coupon_cfg is null");
            return (Criteria) this;
        }

        public Criteria andCouponCfgIsNotNull() {
            addCriterion("coupon_cfg is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCfgEqualTo(Integer value) {
            addCriterion("coupon_cfg =", value, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgNotEqualTo(Integer value) {
            addCriterion("coupon_cfg <>", value, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgGreaterThan(Integer value) {
            addCriterion("coupon_cfg >", value, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_cfg >=", value, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgLessThan(Integer value) {
            addCriterion("coupon_cfg <", value, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_cfg <=", value, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgIn(List<Integer> values) {
            addCriterion("coupon_cfg in", values, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgNotIn(List<Integer> values) {
            addCriterion("coupon_cfg not in", values, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgBetween(Integer value1, Integer value2) {
            addCriterion("coupon_cfg between", value1, value2, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andCouponCfgNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_cfg not between", value1, value2, "couponCfg");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNull() {
            addCriterion("total_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("total_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Integer value) {
            addCriterion("total_count =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Integer value) {
            addCriterion("total_count <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Integer value) {
            addCriterion("total_count >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_count >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Integer value) {
            addCriterion("total_count <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Integer value) {
            addCriterion("total_count <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Integer> values) {
            addCriterion("total_count in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Integer> values) {
            addCriterion("total_count not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Integer value1, Integer value2) {
            addCriterion("total_count between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("total_count not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountIsNull() {
            addCriterion("surplus_count is null");
            return (Criteria) this;
        }

        public Criteria andSurplusCountIsNotNull() {
            addCriterion("surplus_count is not null");
            return (Criteria) this;
        }

        public Criteria andSurplusCountEqualTo(Integer value) {
            addCriterion("surplus_count =", value, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountNotEqualTo(Integer value) {
            addCriterion("surplus_count <>", value, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountGreaterThan(Integer value) {
            addCriterion("surplus_count >", value, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("surplus_count >=", value, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountLessThan(Integer value) {
            addCriterion("surplus_count <", value, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountLessThanOrEqualTo(Integer value) {
            addCriterion("surplus_count <=", value, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountIn(List<Integer> values) {
            addCriterion("surplus_count in", values, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountNotIn(List<Integer> values) {
            addCriterion("surplus_count not in", values, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountBetween(Integer value1, Integer value2) {
            addCriterion("surplus_count between", value1, value2, "surplusCount");
            return (Criteria) this;
        }

        public Criteria andSurplusCountNotBetween(Integer value1, Integer value2) {
            addCriterion("surplus_count not between", value1, value2, "surplusCount");
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

        public Criteria andCountLimitIsNull() {
            addCriterion("count_limit is null");
            return (Criteria) this;
        }

        public Criteria andCountLimitIsNotNull() {
            addCriterion("count_limit is not null");
            return (Criteria) this;
        }

        public Criteria andCountLimitEqualTo(Integer value) {
            addCriterion("count_limit =", value, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitNotEqualTo(Integer value) {
            addCriterion("count_limit <>", value, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitGreaterThan(Integer value) {
            addCriterion("count_limit >", value, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_limit >=", value, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitLessThan(Integer value) {
            addCriterion("count_limit <", value, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitLessThanOrEqualTo(Integer value) {
            addCriterion("count_limit <=", value, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitIn(List<Integer> values) {
            addCriterion("count_limit in", values, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitNotIn(List<Integer> values) {
            addCriterion("count_limit not in", values, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitBetween(Integer value1, Integer value2) {
            addCriterion("count_limit between", value1, value2, "countLimit");
            return (Criteria) this;
        }

        public Criteria andCountLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("count_limit not between", value1, value2, "countLimit");
            return (Criteria) this;
        }

        public Criteria andUseExplainIsNull() {
            addCriterion("use_explain is null");
            return (Criteria) this;
        }

        public Criteria andUseExplainIsNotNull() {
            addCriterion("use_explain is not null");
            return (Criteria) this;
        }

        public Criteria andUseExplainEqualTo(String value) {
            addCriterion("use_explain =", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainNotEqualTo(String value) {
            addCriterion("use_explain <>", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainGreaterThan(String value) {
            addCriterion("use_explain >", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainGreaterThanOrEqualTo(String value) {
            addCriterion("use_explain >=", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainLessThan(String value) {
            addCriterion("use_explain <", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainLessThanOrEqualTo(String value) {
            addCriterion("use_explain <=", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainLike(String value) {
            addCriterion("use_explain like", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainNotLike(String value) {
            addCriterion("use_explain not like", value, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainIn(List<String> values) {
            addCriterion("use_explain in", values, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainNotIn(List<String> values) {
            addCriterion("use_explain not in", values, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainBetween(String value1, String value2) {
            addCriterion("use_explain between", value1, value2, "useExplain");
            return (Criteria) this;
        }

        public Criteria andUseExplainNotBetween(String value1, String value2) {
            addCriterion("use_explain not between", value1, value2, "useExplain");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Integer value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Integer value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Integer value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Integer value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Integer value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Integer> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Integer> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Integer value1, Integer value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Integer value1, Integer value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andValidityCfgIsNull() {
            addCriterion("validity_cfg is null");
            return (Criteria) this;
        }

        public Criteria andValidityCfgIsNotNull() {
            addCriterion("validity_cfg is not null");
            return (Criteria) this;
        }

        public Criteria andValidityCfgEqualTo(Integer value) {
            addCriterion("validity_cfg =", value, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgNotEqualTo(Integer value) {
            addCriterion("validity_cfg <>", value, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgGreaterThan(Integer value) {
            addCriterion("validity_cfg >", value, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_cfg >=", value, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgLessThan(Integer value) {
            addCriterion("validity_cfg <", value, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgLessThanOrEqualTo(Integer value) {
            addCriterion("validity_cfg <=", value, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgIn(List<Integer> values) {
            addCriterion("validity_cfg in", values, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgNotIn(List<Integer> values) {
            addCriterion("validity_cfg not in", values, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgBetween(Integer value1, Integer value2) {
            addCriterion("validity_cfg between", value1, value2, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityCfgNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_cfg not between", value1, value2, "validityCfg");
            return (Criteria) this;
        }

        public Criteria andValidityDayIsNull() {
            addCriterion("validity_day is null");
            return (Criteria) this;
        }

        public Criteria andValidityDayIsNotNull() {
            addCriterion("validity_day is not null");
            return (Criteria) this;
        }

        public Criteria andValidityDayEqualTo(Integer value) {
            addCriterion("validity_day =", value, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayNotEqualTo(Integer value) {
            addCriterion("validity_day <>", value, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayGreaterThan(Integer value) {
            addCriterion("validity_day >", value, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_day >=", value, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayLessThan(Integer value) {
            addCriterion("validity_day <", value, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayLessThanOrEqualTo(Integer value) {
            addCriterion("validity_day <=", value, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayIn(List<Integer> values) {
            addCriterion("validity_day in", values, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayNotIn(List<Integer> values) {
            addCriterion("validity_day not in", values, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayBetween(Integer value1, Integer value2) {
            addCriterion("validity_day between", value1, value2, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityDayNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_day not between", value1, value2, "validityDay");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeIsNull() {
            addCriterion("validity_starttime is null");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeIsNotNull() {
            addCriterion("validity_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeEqualTo(Date value) {
            addCriterion("validity_starttime =", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeNotEqualTo(Date value) {
            addCriterion("validity_starttime <>", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeGreaterThan(Date value) {
            addCriterion("validity_starttime >", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validity_starttime >=", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeLessThan(Date value) {
            addCriterion("validity_starttime <", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeLessThanOrEqualTo(Date value) {
            addCriterion("validity_starttime <=", value, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeIn(List<Date> values) {
            addCriterion("validity_starttime in", values, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeNotIn(List<Date> values) {
            addCriterion("validity_starttime not in", values, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeBetween(Date value1, Date value2) {
            addCriterion("validity_starttime between", value1, value2, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityStarttimeNotBetween(Date value1, Date value2) {
            addCriterion("validity_starttime not between", value1, value2, "validityStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeIsNull() {
            addCriterion("validity_endtime is null");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeIsNotNull() {
            addCriterion("validity_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeEqualTo(Date value) {
            addCriterion("validity_endtime =", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeNotEqualTo(Date value) {
            addCriterion("validity_endtime <>", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeGreaterThan(Date value) {
            addCriterion("validity_endtime >", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("validity_endtime >=", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeLessThan(Date value) {
            addCriterion("validity_endtime <", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("validity_endtime <=", value, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeIn(List<Date> values) {
            addCriterion("validity_endtime in", values, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeNotIn(List<Date> values) {
            addCriterion("validity_endtime not in", values, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeBetween(Date value1, Date value2) {
            addCriterion("validity_endtime between", value1, value2, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("validity_endtime not between", value1, value2, "validityEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeIsNull() {
            addCriterion("validity_use_starttime is null");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeIsNotNull() {
            addCriterion("validity_use_starttime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeEqualTo(Integer value) {
            addCriterion("validity_use_starttime =", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeNotEqualTo(Integer value) {
            addCriterion("validity_use_starttime <>", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeGreaterThan(Integer value) {
            addCriterion("validity_use_starttime >", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_use_starttime >=", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeLessThan(Integer value) {
            addCriterion("validity_use_starttime <", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeLessThanOrEqualTo(Integer value) {
            addCriterion("validity_use_starttime <=", value, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeIn(List<Integer> values) {
            addCriterion("validity_use_starttime in", values, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeNotIn(List<Integer> values) {
            addCriterion("validity_use_starttime not in", values, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeBetween(Integer value1, Integer value2) {
            addCriterion("validity_use_starttime between", value1, value2, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUseStarttimeNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_use_starttime not between", value1, value2, "validityUseStarttime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeIsNull() {
            addCriterion("validity_user_endtime is null");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeIsNotNull() {
            addCriterion("validity_user_endtime is not null");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeEqualTo(Integer value) {
            addCriterion("validity_user_endtime =", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeNotEqualTo(Integer value) {
            addCriterion("validity_user_endtime <>", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeGreaterThan(Integer value) {
            addCriterion("validity_user_endtime >", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_user_endtime >=", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeLessThan(Integer value) {
            addCriterion("validity_user_endtime <", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeLessThanOrEqualTo(Integer value) {
            addCriterion("validity_user_endtime <=", value, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeIn(List<Integer> values) {
            addCriterion("validity_user_endtime in", values, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeNotIn(List<Integer> values) {
            addCriterion("validity_user_endtime not in", values, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeBetween(Integer value1, Integer value2) {
            addCriterion("validity_user_endtime between", value1, value2, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andValidityUserEndtimeNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_user_endtime not between", value1, value2, "validityUserEndtime");
            return (Criteria) this;
        }

        public Criteria andUseCfgIsNull() {
            addCriterion("use_cfg is null");
            return (Criteria) this;
        }

        public Criteria andUseCfgIsNotNull() {
            addCriterion("use_cfg is not null");
            return (Criteria) this;
        }

        public Criteria andUseCfgEqualTo(Integer value) {
            addCriterion("use_cfg =", value, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgNotEqualTo(Integer value) {
            addCriterion("use_cfg <>", value, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgGreaterThan(Integer value) {
            addCriterion("use_cfg >", value, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgGreaterThanOrEqualTo(Integer value) {
            addCriterion("use_cfg >=", value, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgLessThan(Integer value) {
            addCriterion("use_cfg <", value, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgLessThanOrEqualTo(Integer value) {
            addCriterion("use_cfg <=", value, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgIn(List<Integer> values) {
            addCriterion("use_cfg in", values, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgNotIn(List<Integer> values) {
            addCriterion("use_cfg not in", values, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgBetween(Integer value1, Integer value2) {
            addCriterion("use_cfg between", value1, value2, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseCfgNotBetween(Integer value1, Integer value2) {
            addCriterion("use_cfg not between", value1, value2, "useCfg");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountIsNull() {
            addCriterion("use_over_amount is null");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountIsNotNull() {
            addCriterion("use_over_amount is not null");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountEqualTo(BigDecimal value) {
            addCriterion("use_over_amount =", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountNotEqualTo(BigDecimal value) {
            addCriterion("use_over_amount <>", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountGreaterThan(BigDecimal value) {
            addCriterion("use_over_amount >", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("use_over_amount >=", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountLessThan(BigDecimal value) {
            addCriterion("use_over_amount <", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("use_over_amount <=", value, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountIn(List<BigDecimal> values) {
            addCriterion("use_over_amount in", values, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountNotIn(List<BigDecimal> values) {
            addCriterion("use_over_amount not in", values, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_over_amount between", value1, value2, "useOverAmount");
            return (Criteria) this;
        }

        public Criteria andUseOverAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("use_over_amount not between", value1, value2, "useOverAmount");
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

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
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