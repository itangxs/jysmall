package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FqStoreLoanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqStoreLoanExample() {
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

        public Criteria andLoanTotalIsNull() {
            addCriterion("loan_total is null");
            return (Criteria) this;
        }

        public Criteria andLoanTotalIsNotNull() {
            addCriterion("loan_total is not null");
            return (Criteria) this;
        }

        public Criteria andLoanTotalEqualTo(BigDecimal value) {
            addCriterion("loan_total =", value, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalNotEqualTo(BigDecimal value) {
            addCriterion("loan_total <>", value, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalGreaterThan(BigDecimal value) {
            addCriterion("loan_total >", value, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("loan_total >=", value, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalLessThan(BigDecimal value) {
            addCriterion("loan_total <", value, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("loan_total <=", value, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalIn(List<BigDecimal> values) {
            addCriterion("loan_total in", values, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalNotIn(List<BigDecimal> values) {
            addCriterion("loan_total not in", values, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loan_total between", value1, value2, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andLoanTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("loan_total not between", value1, value2, "loanTotal");
            return (Criteria) this;
        }

        public Criteria andNorepaymentIsNull() {
            addCriterion("norepayment is null");
            return (Criteria) this;
        }

        public Criteria andNorepaymentIsNotNull() {
            addCriterion("norepayment is not null");
            return (Criteria) this;
        }

        public Criteria andNorepaymentEqualTo(BigDecimal value) {
            addCriterion("norepayment =", value, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentNotEqualTo(BigDecimal value) {
            addCriterion("norepayment <>", value, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentGreaterThan(BigDecimal value) {
            addCriterion("norepayment >", value, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("norepayment >=", value, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentLessThan(BigDecimal value) {
            addCriterion("norepayment <", value, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("norepayment <=", value, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentIn(List<BigDecimal> values) {
            addCriterion("norepayment in", values, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentNotIn(List<BigDecimal> values) {
            addCriterion("norepayment not in", values, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("norepayment between", value1, value2, "norepayment");
            return (Criteria) this;
        }

        public Criteria andNorepaymentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("norepayment not between", value1, value2, "norepayment");
            return (Criteria) this;
        }

        public Criteria andInterestTotalIsNull() {
            addCriterion("interest_total is null");
            return (Criteria) this;
        }

        public Criteria andInterestTotalIsNotNull() {
            addCriterion("interest_total is not null");
            return (Criteria) this;
        }

        public Criteria andInterestTotalEqualTo(BigDecimal value) {
            addCriterion("interest_total =", value, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalNotEqualTo(BigDecimal value) {
            addCriterion("interest_total <>", value, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalGreaterThan(BigDecimal value) {
            addCriterion("interest_total >", value, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_total >=", value, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalLessThan(BigDecimal value) {
            addCriterion("interest_total <", value, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("interest_total <=", value, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalIn(List<BigDecimal> values) {
            addCriterion("interest_total in", values, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalNotIn(List<BigDecimal> values) {
            addCriterion("interest_total not in", values, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_total between", value1, value2, "interestTotal");
            return (Criteria) this;
        }

        public Criteria andInterestTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("interest_total not between", value1, value2, "interestTotal");
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

        public Criteria andLoanCycleIsNull() {
            addCriterion("loan_cycle is null");
            return (Criteria) this;
        }

        public Criteria andLoanCycleIsNotNull() {
            addCriterion("loan_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andLoanCycleEqualTo(Integer value) {
            addCriterion("loan_cycle =", value, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleNotEqualTo(Integer value) {
            addCriterion("loan_cycle <>", value, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleGreaterThan(Integer value) {
            addCriterion("loan_cycle >", value, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleGreaterThanOrEqualTo(Integer value) {
            addCriterion("loan_cycle >=", value, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleLessThan(Integer value) {
            addCriterion("loan_cycle <", value, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleLessThanOrEqualTo(Integer value) {
            addCriterion("loan_cycle <=", value, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleIn(List<Integer> values) {
            addCriterion("loan_cycle in", values, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleNotIn(List<Integer> values) {
            addCriterion("loan_cycle not in", values, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleBetween(Integer value1, Integer value2) {
            addCriterion("loan_cycle between", value1, value2, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanCycleNotBetween(Integer value1, Integer value2) {
            addCriterion("loan_cycle not between", value1, value2, "loanCycle");
            return (Criteria) this;
        }

        public Criteria andLoanDateIsNull() {
            addCriterion("loan_date is null");
            return (Criteria) this;
        }

        public Criteria andLoanDateIsNotNull() {
            addCriterion("loan_date is not null");
            return (Criteria) this;
        }

        public Criteria andLoanDateEqualTo(Date value) {
            addCriterionForJDBCDate("loan_date =", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("loan_date <>", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateGreaterThan(Date value) {
            addCriterionForJDBCDate("loan_date >", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("loan_date >=", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLessThan(Date value) {
            addCriterionForJDBCDate("loan_date <", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("loan_date <=", value, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateIn(List<Date> values) {
            addCriterionForJDBCDate("loan_date in", values, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("loan_date not in", values, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("loan_date between", value1, value2, "loanDate");
            return (Criteria) this;
        }

        public Criteria andLoanDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("loan_date not between", value1, value2, "loanDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andCardAccountIsNull() {
            addCriterion("card_account is null");
            return (Criteria) this;
        }

        public Criteria andCardAccountIsNotNull() {
            addCriterion("card_account is not null");
            return (Criteria) this;
        }

        public Criteria andCardAccountEqualTo(String value) {
            addCriterion("card_account =", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountNotEqualTo(String value) {
            addCriterion("card_account <>", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountGreaterThan(String value) {
            addCriterion("card_account >", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountGreaterThanOrEqualTo(String value) {
            addCriterion("card_account >=", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountLessThan(String value) {
            addCriterion("card_account <", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountLessThanOrEqualTo(String value) {
            addCriterion("card_account <=", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountLike(String value) {
            addCriterion("card_account like", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountNotLike(String value) {
            addCriterion("card_account not like", value, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountIn(List<String> values) {
            addCriterion("card_account in", values, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountNotIn(List<String> values) {
            addCriterion("card_account not in", values, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountBetween(String value1, String value2) {
            addCriterion("card_account between", value1, value2, "cardAccount");
            return (Criteria) this;
        }

        public Criteria andCardAccountNotBetween(String value1, String value2) {
            addCriterion("card_account not between", value1, value2, "cardAccount");
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