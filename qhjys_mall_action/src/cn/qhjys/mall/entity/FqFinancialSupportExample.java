package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class FqFinancialSupportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FqFinancialSupportExample() {
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

        public Criteria andApplyIdIsNull() {
            addCriterion("apply_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyIdIsNotNull() {
            addCriterion("apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyIdEqualTo(Long value) {
            addCriterion("apply_id =", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotEqualTo(Long value) {
            addCriterion("apply_id <>", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThan(Long value) {
            addCriterion("apply_id >", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_id >=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThan(Long value) {
            addCriterion("apply_id <", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdLessThanOrEqualTo(Long value) {
            addCriterion("apply_id <=", value, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdIn(List<Long> values) {
            addCriterion("apply_id in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotIn(List<Long> values) {
            addCriterion("apply_id not in", values, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdBetween(Long value1, Long value2) {
            addCriterion("apply_id between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andApplyIdNotBetween(Long value1, Long value2) {
            addCriterion("apply_id not between", value1, value2, "applyId");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesIsNull() {
            addCriterion("business_images is null");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesIsNotNull() {
            addCriterion("business_images is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesEqualTo(String value) {
            addCriterion("business_images =", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesNotEqualTo(String value) {
            addCriterion("business_images <>", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesGreaterThan(String value) {
            addCriterion("business_images >", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesGreaterThanOrEqualTo(String value) {
            addCriterion("business_images >=", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesLessThan(String value) {
            addCriterion("business_images <", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesLessThanOrEqualTo(String value) {
            addCriterion("business_images <=", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesLike(String value) {
            addCriterion("business_images like", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesNotLike(String value) {
            addCriterion("business_images not like", value, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesIn(List<String> values) {
            addCriterion("business_images in", values, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesNotIn(List<String> values) {
            addCriterion("business_images not in", values, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesBetween(String value1, String value2) {
            addCriterion("business_images between", value1, value2, "businessImages");
            return (Criteria) this;
        }

        public Criteria andBusinessImagesNotBetween(String value1, String value2) {
            addCriterion("business_images not between", value1, value2, "businessImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesIsNull() {
            addCriterion("idcard_images is null");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesIsNotNull() {
            addCriterion("idcard_images is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesEqualTo(String value) {
            addCriterion("idcard_images =", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesNotEqualTo(String value) {
            addCriterion("idcard_images <>", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesGreaterThan(String value) {
            addCriterion("idcard_images >", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_images >=", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesLessThan(String value) {
            addCriterion("idcard_images <", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesLessThanOrEqualTo(String value) {
            addCriterion("idcard_images <=", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesLike(String value) {
            addCriterion("idcard_images like", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesNotLike(String value) {
            addCriterion("idcard_images not like", value, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesIn(List<String> values) {
            addCriterion("idcard_images in", values, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesNotIn(List<String> values) {
            addCriterion("idcard_images not in", values, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesBetween(String value1, String value2) {
            addCriterion("idcard_images between", value1, value2, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andIdcardImagesNotBetween(String value1, String value2) {
            addCriterion("idcard_images not between", value1, value2, "idcardImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesIsNull() {
            addCriterion("store_images is null");
            return (Criteria) this;
        }

        public Criteria andStoreImagesIsNotNull() {
            addCriterion("store_images is not null");
            return (Criteria) this;
        }

        public Criteria andStoreImagesEqualTo(String value) {
            addCriterion("store_images =", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesNotEqualTo(String value) {
            addCriterion("store_images <>", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesGreaterThan(String value) {
            addCriterion("store_images >", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesGreaterThanOrEqualTo(String value) {
            addCriterion("store_images >=", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesLessThan(String value) {
            addCriterion("store_images <", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesLessThanOrEqualTo(String value) {
            addCriterion("store_images <=", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesLike(String value) {
            addCriterion("store_images like", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesNotLike(String value) {
            addCriterion("store_images not like", value, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesIn(List<String> values) {
            addCriterion("store_images in", values, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesNotIn(List<String> values) {
            addCriterion("store_images not in", values, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesBetween(String value1, String value2) {
            addCriterion("store_images between", value1, value2, "storeImages");
            return (Criteria) this;
        }

        public Criteria andStoreImagesNotBetween(String value1, String value2) {
            addCriterion("store_images not between", value1, value2, "storeImages");
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

        public Criteria andUseInfoIsNull() {
            addCriterion("use_info is null");
            return (Criteria) this;
        }

        public Criteria andUseInfoIsNotNull() {
            addCriterion("use_info is not null");
            return (Criteria) this;
        }

        public Criteria andUseInfoEqualTo(String value) {
            addCriterion("use_info =", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoNotEqualTo(String value) {
            addCriterion("use_info <>", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoGreaterThan(String value) {
            addCriterion("use_info >", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoGreaterThanOrEqualTo(String value) {
            addCriterion("use_info >=", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoLessThan(String value) {
            addCriterion("use_info <", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoLessThanOrEqualTo(String value) {
            addCriterion("use_info <=", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoLike(String value) {
            addCriterion("use_info like", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoNotLike(String value) {
            addCriterion("use_info not like", value, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoIn(List<String> values) {
            addCriterion("use_info in", values, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoNotIn(List<String> values) {
            addCriterion("use_info not in", values, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoBetween(String value1, String value2) {
            addCriterion("use_info between", value1, value2, "useInfo");
            return (Criteria) this;
        }

        public Criteria andUseInfoNotBetween(String value1, String value2) {
            addCriterion("use_info not between", value1, value2, "useInfo");
            return (Criteria) this;
        }

        public Criteria andBankcardNumIsNull() {
            addCriterion("bankcard_num is null");
            return (Criteria) this;
        }

        public Criteria andBankcardNumIsNotNull() {
            addCriterion("bankcard_num is not null");
            return (Criteria) this;
        }

        public Criteria andBankcardNumEqualTo(String value) {
            addCriterion("bankcard_num =", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumNotEqualTo(String value) {
            addCriterion("bankcard_num <>", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumGreaterThan(String value) {
            addCriterion("bankcard_num >", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumGreaterThanOrEqualTo(String value) {
            addCriterion("bankcard_num >=", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumLessThan(String value) {
            addCriterion("bankcard_num <", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumLessThanOrEqualTo(String value) {
            addCriterion("bankcard_num <=", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumLike(String value) {
            addCriterion("bankcard_num like", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumNotLike(String value) {
            addCriterion("bankcard_num not like", value, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumIn(List<String> values) {
            addCriterion("bankcard_num in", values, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumNotIn(List<String> values) {
            addCriterion("bankcard_num not in", values, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumBetween(String value1, String value2) {
            addCriterion("bankcard_num between", value1, value2, "bankcardNum");
            return (Criteria) this;
        }

        public Criteria andBankcardNumNotBetween(String value1, String value2) {
            addCriterion("bankcard_num not between", value1, value2, "bankcardNum");
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