package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductInfoExample() {
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

        public Criteria andNoIsNull() {
            addCriterion("no is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("no is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("no =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("no <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("no >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("no >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("no <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("no <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("no like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("no not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("no in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("no not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("no between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("no not between", value1, value2, "no");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andProdTypeIsNull() {
            addCriterion("prod_type is null");
            return (Criteria) this;
        }

        public Criteria andProdTypeIsNotNull() {
            addCriterion("prod_type is not null");
            return (Criteria) this;
        }

        public Criteria andProdTypeEqualTo(Integer value) {
            addCriterion("prod_type =", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotEqualTo(Integer value) {
            addCriterion("prod_type <>", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeGreaterThan(Integer value) {
            addCriterion("prod_type >", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_type >=", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeLessThan(Integer value) {
            addCriterion("prod_type <", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeLessThanOrEqualTo(Integer value) {
            addCriterion("prod_type <=", value, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeIn(List<Integer> values) {
            addCriterion("prod_type in", values, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotIn(List<Integer> values) {
            addCriterion("prod_type not in", values, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeBetween(Integer value1, Integer value2) {
            addCriterion("prod_type between", value1, value2, "prodType");
            return (Criteria) this;
        }

        public Criteria andProdTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_type not between", value1, value2, "prodType");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andImagesIsNull() {
            addCriterion("images is null");
            return (Criteria) this;
        }

        public Criteria andImagesIsNotNull() {
            addCriterion("images is not null");
            return (Criteria) this;
        }

        public Criteria andImagesEqualTo(String value) {
            addCriterion("images =", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotEqualTo(String value) {
            addCriterion("images <>", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThan(String value) {
            addCriterion("images >", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThanOrEqualTo(String value) {
            addCriterion("images >=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThan(String value) {
            addCriterion("images <", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThanOrEqualTo(String value) {
            addCriterion("images <=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLike(String value) {
            addCriterion("images like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotLike(String value) {
            addCriterion("images not like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesIn(List<String> values) {
            addCriterion("images in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotIn(List<String> values) {
            addCriterion("images not in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesBetween(String value1, String value2) {
            addCriterion("images between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotBetween(String value1, String value2) {
            addCriterion("images not between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(BigDecimal value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(BigDecimal value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(BigDecimal value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(BigDecimal value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<BigDecimal> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<BigDecimal> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceIsNull() {
            addCriterion("orig_price is null");
            return (Criteria) this;
        }

        public Criteria andOrigPriceIsNotNull() {
            addCriterion("orig_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrigPriceEqualTo(BigDecimal value) {
            addCriterion("orig_price =", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceNotEqualTo(BigDecimal value) {
            addCriterion("orig_price <>", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceGreaterThan(BigDecimal value) {
            addCriterion("orig_price >", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("orig_price >=", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceLessThan(BigDecimal value) {
            addCriterion("orig_price <", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("orig_price <=", value, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceIn(List<BigDecimal> values) {
            addCriterion("orig_price in", values, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceNotIn(List<BigDecimal> values) {
            addCriterion("orig_price not in", values, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orig_price between", value1, value2, "origPrice");
            return (Criteria) this;
        }

        public Criteria andOrigPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("orig_price not between", value1, value2, "origPrice");
            return (Criteria) this;
        }

        public Criteria andProdStockIsNull() {
            addCriterion("prod_stock is null");
            return (Criteria) this;
        }

        public Criteria andProdStockIsNotNull() {
            addCriterion("prod_stock is not null");
            return (Criteria) this;
        }

        public Criteria andProdStockEqualTo(Integer value) {
            addCriterion("prod_stock =", value, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockNotEqualTo(Integer value) {
            addCriterion("prod_stock <>", value, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockGreaterThan(Integer value) {
            addCriterion("prod_stock >", value, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_stock >=", value, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockLessThan(Integer value) {
            addCriterion("prod_stock <", value, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockLessThanOrEqualTo(Integer value) {
            addCriterion("prod_stock <=", value, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockIn(List<Integer> values) {
            addCriterion("prod_stock in", values, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockNotIn(List<Integer> values) {
            addCriterion("prod_stock not in", values, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockBetween(Integer value1, Integer value2) {
            addCriterion("prod_stock between", value1, value2, "prodStock");
            return (Criteria) this;
        }

        public Criteria andProdStockNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_stock not between", value1, value2, "prodStock");
            return (Criteria) this;
        }

        public Criteria andMaxPayIsNull() {
            addCriterion("max_pay is null");
            return (Criteria) this;
        }

        public Criteria andMaxPayIsNotNull() {
            addCriterion("max_pay is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPayEqualTo(Integer value) {
            addCriterion("max_pay =", value, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayNotEqualTo(Integer value) {
            addCriterion("max_pay <>", value, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayGreaterThan(Integer value) {
            addCriterion("max_pay >", value, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_pay >=", value, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayLessThan(Integer value) {
            addCriterion("max_pay <", value, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayLessThanOrEqualTo(Integer value) {
            addCriterion("max_pay <=", value, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayIn(List<Integer> values) {
            addCriterion("max_pay in", values, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayNotIn(List<Integer> values) {
            addCriterion("max_pay not in", values, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayBetween(Integer value1, Integer value2) {
            addCriterion("max_pay between", value1, value2, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxPayNotBetween(Integer value1, Integer value2) {
            addCriterion("max_pay not between", value1, value2, "maxPay");
            return (Criteria) this;
        }

        public Criteria andMaxUseIsNull() {
            addCriterion("max_use is null");
            return (Criteria) this;
        }

        public Criteria andMaxUseIsNotNull() {
            addCriterion("max_use is not null");
            return (Criteria) this;
        }

        public Criteria andMaxUseEqualTo(Integer value) {
            addCriterion("max_use =", value, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseNotEqualTo(Integer value) {
            addCriterion("max_use <>", value, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseGreaterThan(Integer value) {
            addCriterion("max_use >", value, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_use >=", value, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseLessThan(Integer value) {
            addCriterion("max_use <", value, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseLessThanOrEqualTo(Integer value) {
            addCriterion("max_use <=", value, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseIn(List<Integer> values) {
            addCriterion("max_use in", values, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseNotIn(List<Integer> values) {
            addCriterion("max_use not in", values, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseBetween(Integer value1, Integer value2) {
            addCriterion("max_use between", value1, value2, "maxUse");
            return (Criteria) this;
        }

        public Criteria andMaxUseNotBetween(Integer value1, Integer value2) {
            addCriterion("max_use not between", value1, value2, "maxUse");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Long value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Long value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Long value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Long value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Long> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Long> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Long value1, Long value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andSalesNumIsNull() {
            addCriterion("sales_num is null");
            return (Criteria) this;
        }

        public Criteria andSalesNumIsNotNull() {
            addCriterion("sales_num is not null");
            return (Criteria) this;
        }

        public Criteria andSalesNumEqualTo(Integer value) {
            addCriterion("sales_num =", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotEqualTo(Integer value) {
            addCriterion("sales_num <>", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumGreaterThan(Integer value) {
            addCriterion("sales_num >", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_num >=", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumLessThan(Integer value) {
            addCriterion("sales_num <", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumLessThanOrEqualTo(Integer value) {
            addCriterion("sales_num <=", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumIn(List<Integer> values) {
            addCriterion("sales_num in", values, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotIn(List<Integer> values) {
            addCriterion("sales_num not in", values, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumBetween(Integer value1, Integer value2) {
            addCriterion("sales_num between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_num not between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        public Criteria andScoreAvgIsNull() {
            addCriterion("score_avg is null");
            return (Criteria) this;
        }

        public Criteria andScoreAvgIsNotNull() {
            addCriterion("score_avg is not null");
            return (Criteria) this;
        }

        public Criteria andScoreAvgEqualTo(Float value) {
            addCriterion("score_avg =", value, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgNotEqualTo(Float value) {
            addCriterion("score_avg <>", value, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgGreaterThan(Float value) {
            addCriterion("score_avg >", value, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgGreaterThanOrEqualTo(Float value) {
            addCriterion("score_avg >=", value, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgLessThan(Float value) {
            addCriterion("score_avg <", value, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgLessThanOrEqualTo(Float value) {
            addCriterion("score_avg <=", value, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgIn(List<Float> values) {
            addCriterion("score_avg in", values, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgNotIn(List<Float> values) {
            addCriterion("score_avg not in", values, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgBetween(Float value1, Float value2) {
            addCriterion("score_avg between", value1, value2, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andScoreAvgNotBetween(Float value1, Float value2) {
            addCriterion("score_avg not between", value1, value2, "scoreAvg");
            return (Criteria) this;
        }

        public Criteria andMarkNumIsNull() {
            addCriterion("mark_num is null");
            return (Criteria) this;
        }

        public Criteria andMarkNumIsNotNull() {
            addCriterion("mark_num is not null");
            return (Criteria) this;
        }

        public Criteria andMarkNumEqualTo(Integer value) {
            addCriterion("mark_num =", value, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumNotEqualTo(Integer value) {
            addCriterion("mark_num <>", value, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumGreaterThan(Integer value) {
            addCriterion("mark_num >", value, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("mark_num >=", value, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumLessThan(Integer value) {
            addCriterion("mark_num <", value, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumLessThanOrEqualTo(Integer value) {
            addCriterion("mark_num <=", value, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumIn(List<Integer> values) {
            addCriterion("mark_num in", values, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumNotIn(List<Integer> values) {
            addCriterion("mark_num not in", values, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumBetween(Integer value1, Integer value2) {
            addCriterion("mark_num between", value1, value2, "markNum");
            return (Criteria) this;
        }

        public Criteria andMarkNumNotBetween(Integer value1, Integer value2) {
            addCriterion("mark_num not between", value1, value2, "markNum");
            return (Criteria) this;
        }

        public Criteria andStarDateIsNull() {
            addCriterion("star_date is null");
            return (Criteria) this;
        }

        public Criteria andStarDateIsNotNull() {
            addCriterion("star_date is not null");
            return (Criteria) this;
        }

        public Criteria andStarDateEqualTo(Date value) {
            addCriterion("star_date =", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateNotEqualTo(Date value) {
            addCriterion("star_date <>", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateGreaterThan(Date value) {
            addCriterion("star_date >", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateGreaterThanOrEqualTo(Date value) {
            addCriterion("star_date >=", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateLessThan(Date value) {
            addCriterion("star_date <", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateLessThanOrEqualTo(Date value) {
            addCriterion("star_date <=", value, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateIn(List<Date> values) {
            addCriterion("star_date in", values, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateNotIn(List<Date> values) {
            addCriterion("star_date not in", values, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateBetween(Date value1, Date value2) {
            addCriterion("star_date between", value1, value2, "starDate");
            return (Criteria) this;
        }

        public Criteria andStarDateNotBetween(Date value1, Date value2) {
            addCriterion("star_date not between", value1, value2, "starDate");
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
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(Date value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(Date value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(Date value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(Date value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<Date> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<Date> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(Date value1, Date value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(Date value1, Date value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andPromiseIsNull() {
            addCriterion("promise is null");
            return (Criteria) this;
        }

        public Criteria andPromiseIsNotNull() {
            addCriterion("promise is not null");
            return (Criteria) this;
        }

        public Criteria andPromiseEqualTo(String value) {
            addCriterion("promise =", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseNotEqualTo(String value) {
            addCriterion("promise <>", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseGreaterThan(String value) {
            addCriterion("promise >", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseGreaterThanOrEqualTo(String value) {
            addCriterion("promise >=", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseLessThan(String value) {
            addCriterion("promise <", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseLessThanOrEqualTo(String value) {
            addCriterion("promise <=", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseLike(String value) {
            addCriterion("promise like", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseNotLike(String value) {
            addCriterion("promise not like", value, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseIn(List<String> values) {
            addCriterion("promise in", values, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseNotIn(List<String> values) {
            addCriterion("promise not in", values, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseBetween(String value1, String value2) {
            addCriterion("promise between", value1, value2, "promise");
            return (Criteria) this;
        }

        public Criteria andPromiseNotBetween(String value1, String value2) {
            addCriterion("promise not between", value1, value2, "promise");
            return (Criteria) this;
        }

        public Criteria andScheduleerIsNull() {
            addCriterion("scheduleer is null");
            return (Criteria) this;
        }

        public Criteria andScheduleerIsNotNull() {
            addCriterion("scheduleer is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleerEqualTo(Integer value) {
            addCriterion("scheduleer =", value, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerNotEqualTo(Integer value) {
            addCriterion("scheduleer <>", value, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerGreaterThan(Integer value) {
            addCriterion("scheduleer >", value, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerGreaterThanOrEqualTo(Integer value) {
            addCriterion("scheduleer >=", value, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerLessThan(Integer value) {
            addCriterion("scheduleer <", value, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerLessThanOrEqualTo(Integer value) {
            addCriterion("scheduleer <=", value, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerIn(List<Integer> values) {
            addCriterion("scheduleer in", values, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerNotIn(List<Integer> values) {
            addCriterion("scheduleer not in", values, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerBetween(Integer value1, Integer value2) {
            addCriterion("scheduleer between", value1, value2, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleerNotBetween(Integer value1, Integer value2) {
            addCriterion("scheduleer not between", value1, value2, "scheduleer");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIsNull() {
            addCriterion("schedule_type is null");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIsNotNull() {
            addCriterion("schedule_type is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeEqualTo(Integer value) {
            addCriterion("schedule_type =", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotEqualTo(Integer value) {
            addCriterion("schedule_type <>", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeGreaterThan(Integer value) {
            addCriterion("schedule_type >", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("schedule_type >=", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLessThan(Integer value) {
            addCriterion("schedule_type <", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("schedule_type <=", value, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeIn(List<Integer> values) {
            addCriterion("schedule_type in", values, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotIn(List<Integer> values) {
            addCriterion("schedule_type not in", values, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeBetween(Integer value1, Integer value2) {
            addCriterion("schedule_type between", value1, value2, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andScheduleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("schedule_type not between", value1, value2, "scheduleType");
            return (Criteria) this;
        }

        public Criteria andHaopingIsNull() {
            addCriterion("haoping is null");
            return (Criteria) this;
        }

        public Criteria andHaopingIsNotNull() {
            addCriterion("haoping is not null");
            return (Criteria) this;
        }

        public Criteria andHaopingEqualTo(Integer value) {
            addCriterion("haoping =", value, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingNotEqualTo(Integer value) {
            addCriterion("haoping <>", value, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingGreaterThan(Integer value) {
            addCriterion("haoping >", value, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingGreaterThanOrEqualTo(Integer value) {
            addCriterion("haoping >=", value, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingLessThan(Integer value) {
            addCriterion("haoping <", value, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingLessThanOrEqualTo(Integer value) {
            addCriterion("haoping <=", value, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingIn(List<Integer> values) {
            addCriterion("haoping in", values, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingNotIn(List<Integer> values) {
            addCriterion("haoping not in", values, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingBetween(Integer value1, Integer value2) {
            addCriterion("haoping between", value1, value2, "haoping");
            return (Criteria) this;
        }

        public Criteria andHaopingNotBetween(Integer value1, Integer value2) {
            addCriterion("haoping not between", value1, value2, "haoping");
            return (Criteria) this;
        }

        public Criteria andZhongpingIsNull() {
            addCriterion("zhongping is null");
            return (Criteria) this;
        }

        public Criteria andZhongpingIsNotNull() {
            addCriterion("zhongping is not null");
            return (Criteria) this;
        }

        public Criteria andZhongpingEqualTo(Integer value) {
            addCriterion("zhongping =", value, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingNotEqualTo(Integer value) {
            addCriterion("zhongping <>", value, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingGreaterThan(Integer value) {
            addCriterion("zhongping >", value, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingGreaterThanOrEqualTo(Integer value) {
            addCriterion("zhongping >=", value, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingLessThan(Integer value) {
            addCriterion("zhongping <", value, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingLessThanOrEqualTo(Integer value) {
            addCriterion("zhongping <=", value, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingIn(List<Integer> values) {
            addCriterion("zhongping in", values, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingNotIn(List<Integer> values) {
            addCriterion("zhongping not in", values, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingBetween(Integer value1, Integer value2) {
            addCriterion("zhongping between", value1, value2, "zhongping");
            return (Criteria) this;
        }

        public Criteria andZhongpingNotBetween(Integer value1, Integer value2) {
            addCriterion("zhongping not between", value1, value2, "zhongping");
            return (Criteria) this;
        }

        public Criteria andChapingIsNull() {
            addCriterion("chaping is null");
            return (Criteria) this;
        }

        public Criteria andChapingIsNotNull() {
            addCriterion("chaping is not null");
            return (Criteria) this;
        }

        public Criteria andChapingEqualTo(Integer value) {
            addCriterion("chaping =", value, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingNotEqualTo(Integer value) {
            addCriterion("chaping <>", value, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingGreaterThan(Integer value) {
            addCriterion("chaping >", value, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingGreaterThanOrEqualTo(Integer value) {
            addCriterion("chaping >=", value, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingLessThan(Integer value) {
            addCriterion("chaping <", value, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingLessThanOrEqualTo(Integer value) {
            addCriterion("chaping <=", value, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingIn(List<Integer> values) {
            addCriterion("chaping in", values, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingNotIn(List<Integer> values) {
            addCriterion("chaping not in", values, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingBetween(Integer value1, Integer value2) {
            addCriterion("chaping between", value1, value2, "chaping");
            return (Criteria) this;
        }

        public Criteria andChapingNotBetween(Integer value1, Integer value2) {
            addCriterion("chaping not between", value1, value2, "chaping");
            return (Criteria) this;
        }

        public Criteria andOnShelfIsNull() {
            addCriterion("on_shelf is null");
            return (Criteria) this;
        }

        public Criteria andOnShelfIsNotNull() {
            addCriterion("on_shelf is not null");
            return (Criteria) this;
        }

        public Criteria andOnShelfEqualTo(Date value) {
            addCriterion("on_shelf =", value, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfNotEqualTo(Date value) {
            addCriterion("on_shelf <>", value, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfGreaterThan(Date value) {
            addCriterion("on_shelf >", value, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfGreaterThanOrEqualTo(Date value) {
            addCriterion("on_shelf >=", value, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfLessThan(Date value) {
            addCriterion("on_shelf <", value, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfLessThanOrEqualTo(Date value) {
            addCriterion("on_shelf <=", value, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfIn(List<Date> values) {
            addCriterion("on_shelf in", values, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfNotIn(List<Date> values) {
            addCriterion("on_shelf not in", values, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfBetween(Date value1, Date value2) {
            addCriterion("on_shelf between", value1, value2, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOnShelfNotBetween(Date value1, Date value2) {
            addCriterion("on_shelf not between", value1, value2, "onShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfIsNull() {
            addCriterion("off_shelf is null");
            return (Criteria) this;
        }

        public Criteria andOffShelfIsNotNull() {
            addCriterion("off_shelf is not null");
            return (Criteria) this;
        }

        public Criteria andOffShelfEqualTo(Date value) {
            addCriterion("off_shelf =", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotEqualTo(Date value) {
            addCriterion("off_shelf <>", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfGreaterThan(Date value) {
            addCriterion("off_shelf >", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfGreaterThanOrEqualTo(Date value) {
            addCriterion("off_shelf >=", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfLessThan(Date value) {
            addCriterion("off_shelf <", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfLessThanOrEqualTo(Date value) {
            addCriterion("off_shelf <=", value, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfIn(List<Date> values) {
            addCriterion("off_shelf in", values, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotIn(List<Date> values) {
            addCriterion("off_shelf not in", values, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfBetween(Date value1, Date value2) {
            addCriterion("off_shelf between", value1, value2, "offShelf");
            return (Criteria) this;
        }

        public Criteria andOffShelfNotBetween(Date value1, Date value2) {
            addCriterion("off_shelf not between", value1, value2, "offShelf");
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

        public Criteria andKeywordsIsNull() {
            addCriterion("keywords is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("keywords is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("keywords =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("keywords <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("keywords >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("keywords >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("keywords <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("keywords <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("keywords like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("keywords not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("keywords in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("keywords not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("keywords between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("keywords not between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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