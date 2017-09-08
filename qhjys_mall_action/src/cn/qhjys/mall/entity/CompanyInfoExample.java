package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CompanyInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CompanyInfoExample() {
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

        public Criteria andCorpnNameIsNull() {
            addCriterion("corpn_name is null");
            return (Criteria) this;
        }

        public Criteria andCorpnNameIsNotNull() {
            addCriterion("corpn_name is not null");
            return (Criteria) this;
        }

        public Criteria andCorpnNameEqualTo(String value) {
            addCriterion("corpn_name =", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameNotEqualTo(String value) {
            addCriterion("corpn_name <>", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameGreaterThan(String value) {
            addCriterion("corpn_name >", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameGreaterThanOrEqualTo(String value) {
            addCriterion("corpn_name >=", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameLessThan(String value) {
            addCriterion("corpn_name <", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameLessThanOrEqualTo(String value) {
            addCriterion("corpn_name <=", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameLike(String value) {
            addCriterion("corpn_name like", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameNotLike(String value) {
            addCriterion("corpn_name not like", value, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameIn(List<String> values) {
            addCriterion("corpn_name in", values, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameNotIn(List<String> values) {
            addCriterion("corpn_name not in", values, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameBetween(String value1, String value2) {
            addCriterion("corpn_name between", value1, value2, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnNameNotBetween(String value1, String value2) {
            addCriterion("corpn_name not between", value1, value2, "corpnName");
            return (Criteria) this;
        }

        public Criteria andCorpnIdIsNull() {
            addCriterion("corpn_id is null");
            return (Criteria) this;
        }

        public Criteria andCorpnIdIsNotNull() {
            addCriterion("corpn_id is not null");
            return (Criteria) this;
        }

        public Criteria andCorpnIdEqualTo(String value) {
            addCriterion("corpn_id =", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdNotEqualTo(String value) {
            addCriterion("corpn_id <>", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdGreaterThan(String value) {
            addCriterion("corpn_id >", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdGreaterThanOrEqualTo(String value) {
            addCriterion("corpn_id >=", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdLessThan(String value) {
            addCriterion("corpn_id <", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdLessThanOrEqualTo(String value) {
            addCriterion("corpn_id <=", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdLike(String value) {
            addCriterion("corpn_id like", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdNotLike(String value) {
            addCriterion("corpn_id not like", value, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdIn(List<String> values) {
            addCriterion("corpn_id in", values, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdNotIn(List<String> values) {
            addCriterion("corpn_id not in", values, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdBetween(String value1, String value2) {
            addCriterion("corpn_id between", value1, value2, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnIdNotBetween(String value1, String value2) {
            addCriterion("corpn_id not between", value1, value2, "corpnId");
            return (Criteria) this;
        }

        public Criteria andCorpnCardIsNull() {
            addCriterion("corpn_card is null");
            return (Criteria) this;
        }

        public Criteria andCorpnCardIsNotNull() {
            addCriterion("corpn_card is not null");
            return (Criteria) this;
        }

        public Criteria andCorpnCardEqualTo(String value) {
            addCriterion("corpn_card =", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardNotEqualTo(String value) {
            addCriterion("corpn_card <>", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardGreaterThan(String value) {
            addCriterion("corpn_card >", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardGreaterThanOrEqualTo(String value) {
            addCriterion("corpn_card >=", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardLessThan(String value) {
            addCriterion("corpn_card <", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardLessThanOrEqualTo(String value) {
            addCriterion("corpn_card <=", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardLike(String value) {
            addCriterion("corpn_card like", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardNotLike(String value) {
            addCriterion("corpn_card not like", value, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardIn(List<String> values) {
            addCriterion("corpn_card in", values, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardNotIn(List<String> values) {
            addCriterion("corpn_card not in", values, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardBetween(String value1, String value2) {
            addCriterion("corpn_card between", value1, value2, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andCorpnCardNotBetween(String value1, String value2) {
            addCriterion("corpn_card not between", value1, value2, "corpnCard");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIsNull() {
            addCriterion("license_number is null");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIsNotNull() {
            addCriterion("license_number is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberEqualTo(String value) {
            addCriterion("license_number =", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotEqualTo(String value) {
            addCriterion("license_number <>", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberGreaterThan(String value) {
            addCriterion("license_number >", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("license_number >=", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLessThan(String value) {
            addCriterion("license_number <", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLessThanOrEqualTo(String value) {
            addCriterion("license_number <=", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberLike(String value) {
            addCriterion("license_number like", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotLike(String value) {
            addCriterion("license_number not like", value, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberIn(List<String> values) {
            addCriterion("license_number in", values, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotIn(List<String> values) {
            addCriterion("license_number not in", values, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberBetween(String value1, String value2) {
            addCriterion("license_number between", value1, value2, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andLicenseNumberNotBetween(String value1, String value2) {
            addCriterion("license_number not between", value1, value2, "licenseNumber");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeIsNull() {
            addCriterion("business_license_type is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeIsNotNull() {
            addCriterion("business_license_type is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeEqualTo(String value) {
            addCriterion("business_license_type =", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeNotEqualTo(String value) {
            addCriterion("business_license_type <>", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeGreaterThan(String value) {
            addCriterion("business_license_type >", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("business_license_type >=", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeLessThan(String value) {
            addCriterion("business_license_type <", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeLessThanOrEqualTo(String value) {
            addCriterion("business_license_type <=", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeLike(String value) {
            addCriterion("business_license_type like", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeNotLike(String value) {
            addCriterion("business_license_type not like", value, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeIn(List<String> values) {
            addCriterion("business_license_type in", values, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeNotIn(List<String> values) {
            addCriterion("business_license_type not in", values, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeBetween(String value1, String value2) {
            addCriterion("business_license_type between", value1, value2, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseTypeNotBetween(String value1, String value2) {
            addCriterion("business_license_type not between", value1, value2, "businessLicenseType");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceIsNull() {
            addCriterion("license_province is null");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceIsNotNull() {
            addCriterion("license_province is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceEqualTo(Long value) {
            addCriterion("license_province =", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotEqualTo(Long value) {
            addCriterion("license_province <>", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceGreaterThan(Long value) {
            addCriterion("license_province >", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceGreaterThanOrEqualTo(Long value) {
            addCriterion("license_province >=", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceLessThan(Long value) {
            addCriterion("license_province <", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceLessThanOrEqualTo(Long value) {
            addCriterion("license_province <=", value, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceIn(List<Long> values) {
            addCriterion("license_province in", values, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotIn(List<Long> values) {
            addCriterion("license_province not in", values, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceBetween(Long value1, Long value2) {
            addCriterion("license_province between", value1, value2, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseProvinceNotBetween(Long value1, Long value2) {
            addCriterion("license_province not between", value1, value2, "licenseProvince");
            return (Criteria) this;
        }

        public Criteria andLicenseCityIsNull() {
            addCriterion("license_city is null");
            return (Criteria) this;
        }

        public Criteria andLicenseCityIsNotNull() {
            addCriterion("license_city is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseCityEqualTo(Long value) {
            addCriterion("license_city =", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotEqualTo(Long value) {
            addCriterion("license_city <>", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityGreaterThan(Long value) {
            addCriterion("license_city >", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityGreaterThanOrEqualTo(Long value) {
            addCriterion("license_city >=", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityLessThan(Long value) {
            addCriterion("license_city <", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityLessThanOrEqualTo(Long value) {
            addCriterion("license_city <=", value, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityIn(List<Long> values) {
            addCriterion("license_city in", values, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotIn(List<Long> values) {
            addCriterion("license_city not in", values, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityBetween(Long value1, Long value2) {
            addCriterion("license_city between", value1, value2, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseCityNotBetween(Long value1, Long value2) {
            addCriterion("license_city not between", value1, value2, "licenseCity");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaIsNull() {
            addCriterion("license_area is null");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaIsNotNull() {
            addCriterion("license_area is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaEqualTo(Long value) {
            addCriterion("license_area =", value, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaNotEqualTo(Long value) {
            addCriterion("license_area <>", value, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaGreaterThan(Long value) {
            addCriterion("license_area >", value, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaGreaterThanOrEqualTo(Long value) {
            addCriterion("license_area >=", value, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaLessThan(Long value) {
            addCriterion("license_area <", value, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaLessThanOrEqualTo(Long value) {
            addCriterion("license_area <=", value, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaIn(List<Long> values) {
            addCriterion("license_area in", values, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaNotIn(List<Long> values) {
            addCriterion("license_area not in", values, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaBetween(Long value1, Long value2) {
            addCriterion("license_area between", value1, value2, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAreaNotBetween(Long value1, Long value2) {
            addCriterion("license_area not between", value1, value2, "licenseArea");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressIsNull() {
            addCriterion("license_address is null");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressIsNotNull() {
            addCriterion("license_address is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressEqualTo(String value) {
            addCriterion("license_address =", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressNotEqualTo(String value) {
            addCriterion("license_address <>", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressGreaterThan(String value) {
            addCriterion("license_address >", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressGreaterThanOrEqualTo(String value) {
            addCriterion("license_address >=", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressLessThan(String value) {
            addCriterion("license_address <", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressLessThanOrEqualTo(String value) {
            addCriterion("license_address <=", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressLike(String value) {
            addCriterion("license_address like", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressNotLike(String value) {
            addCriterion("license_address not like", value, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressIn(List<String> values) {
            addCriterion("license_address in", values, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressNotIn(List<String> values) {
            addCriterion("license_address not in", values, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressBetween(String value1, String value2) {
            addCriterion("license_address between", value1, value2, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseAddressNotBetween(String value1, String value2) {
            addCriterion("license_address not between", value1, value2, "licenseAddress");
            return (Criteria) this;
        }

        public Criteria andLicenseCardIsNull() {
            addCriterion("license_card is null");
            return (Criteria) this;
        }

        public Criteria andLicenseCardIsNotNull() {
            addCriterion("license_card is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseCardEqualTo(String value) {
            addCriterion("license_card =", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotEqualTo(String value) {
            addCriterion("license_card <>", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardGreaterThan(String value) {
            addCriterion("license_card >", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardGreaterThanOrEqualTo(String value) {
            addCriterion("license_card >=", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardLessThan(String value) {
            addCriterion("license_card <", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardLessThanOrEqualTo(String value) {
            addCriterion("license_card <=", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardLike(String value) {
            addCriterion("license_card like", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotLike(String value) {
            addCriterion("license_card not like", value, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardIn(List<String> values) {
            addCriterion("license_card in", values, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotIn(List<String> values) {
            addCriterion("license_card not in", values, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardBetween(String value1, String value2) {
            addCriterion("license_card between", value1, value2, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andLicenseCardNotBetween(String value1, String value2) {
            addCriterion("license_card not between", value1, value2, "licenseCard");
            return (Criteria) this;
        }

        public Criteria andSetUpDateIsNull() {
            addCriterion("set_up_date is null");
            return (Criteria) this;
        }

        public Criteria andSetUpDateIsNotNull() {
            addCriterion("set_up_date is not null");
            return (Criteria) this;
        }

        public Criteria andSetUpDateEqualTo(Date value) {
            addCriterionForJDBCDate("set_up_date =", value, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("set_up_date <>", value, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateGreaterThan(Date value) {
            addCriterionForJDBCDate("set_up_date >", value, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("set_up_date >=", value, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateLessThan(Date value) {
            addCriterionForJDBCDate("set_up_date <", value, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("set_up_date <=", value, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateIn(List<Date> values) {
            addCriterionForJDBCDate("set_up_date in", values, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("set_up_date not in", values, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("set_up_date between", value1, value2, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andSetUpDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("set_up_date not between", value1, value2, "setUpDate");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNull() {
            addCriterion("capital is null");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNotNull() {
            addCriterion("capital is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalEqualTo(Long value) {
            addCriterion("capital =", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotEqualTo(Long value) {
            addCriterion("capital <>", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThan(Long value) {
            addCriterion("capital >", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThanOrEqualTo(Long value) {
            addCriterion("capital >=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThan(Long value) {
            addCriterion("capital <", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThanOrEqualTo(Long value) {
            addCriterion("capital <=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalIn(List<Long> values) {
            addCriterion("capital in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotIn(List<Long> values) {
            addCriterion("capital not in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalBetween(Long value1, Long value2) {
            addCriterion("capital between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotBetween(Long value1, Long value2) {
            addCriterion("capital not between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andScopeIsNull() {
            addCriterion("scope is null");
            return (Criteria) this;
        }

        public Criteria andScopeIsNotNull() {
            addCriterion("scope is not null");
            return (Criteria) this;
        }

        public Criteria andScopeEqualTo(String value) {
            addCriterion("scope =", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotEqualTo(String value) {
            addCriterion("scope <>", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThan(String value) {
            addCriterion("scope >", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeGreaterThanOrEqualTo(String value) {
            addCriterion("scope >=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThan(String value) {
            addCriterion("scope <", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLessThanOrEqualTo(String value) {
            addCriterion("scope <=", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeLike(String value) {
            addCriterion("scope like", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotLike(String value) {
            addCriterion("scope not like", value, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeIn(List<String> values) {
            addCriterion("scope in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotIn(List<String> values) {
            addCriterion("scope not in", values, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeBetween(String value1, String value2) {
            addCriterion("scope between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andScopeNotBetween(String value1, String value2) {
            addCriterion("scope not between", value1, value2, "scope");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeIsNull() {
            addCriterion("organization_code is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeIsNotNull() {
            addCriterion("organization_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeEqualTo(String value) {
            addCriterion("organization_code =", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotEqualTo(String value) {
            addCriterion("organization_code <>", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeGreaterThan(String value) {
            addCriterion("organization_code >", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("organization_code >=", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeLessThan(String value) {
            addCriterion("organization_code <", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeLessThanOrEqualTo(String value) {
            addCriterion("organization_code <=", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeLike(String value) {
            addCriterion("organization_code like", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotLike(String value) {
            addCriterion("organization_code not like", value, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeIn(List<String> values) {
            addCriterion("organization_code in", values, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotIn(List<String> values) {
            addCriterion("organization_code not in", values, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeBetween(String value1, String value2) {
            addCriterion("organization_code between", value1, value2, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationCodeNotBetween(String value1, String value2) {
            addCriterion("organization_code not between", value1, value2, "organizationCode");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageIsNull() {
            addCriterion("organization_image is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageIsNotNull() {
            addCriterion("organization_image is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageEqualTo(String value) {
            addCriterion("organization_image =", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageNotEqualTo(String value) {
            addCriterion("organization_image <>", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageGreaterThan(String value) {
            addCriterion("organization_image >", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageGreaterThanOrEqualTo(String value) {
            addCriterion("organization_image >=", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageLessThan(String value) {
            addCriterion("organization_image <", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageLessThanOrEqualTo(String value) {
            addCriterion("organization_image <=", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageLike(String value) {
            addCriterion("organization_image like", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageNotLike(String value) {
            addCriterion("organization_image not like", value, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageIn(List<String> values) {
            addCriterion("organization_image in", values, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageNotIn(List<String> values) {
            addCriterion("organization_image not in", values, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageBetween(String value1, String value2) {
            addCriterion("organization_image between", value1, value2, "organizationImage");
            return (Criteria) this;
        }

        public Criteria andOrganizationImageNotBetween(String value1, String value2) {
            addCriterion("organization_image not between", value1, value2, "organizationImage");
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

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Integer value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Integer value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Integer value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Integer value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Integer value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Integer> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Integer> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Integer value1, Integer value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Integer value1, Integer value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
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