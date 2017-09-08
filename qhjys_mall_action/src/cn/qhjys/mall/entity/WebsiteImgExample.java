package cn.qhjys.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebsiteImgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebsiteImgExample() {
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

        public Criteria andPcBannerIsNull() {
            addCriterion("pc_banner is null");
            return (Criteria) this;
        }

        public Criteria andPcBannerIsNotNull() {
            addCriterion("pc_banner is not null");
            return (Criteria) this;
        }

        public Criteria andPcBannerEqualTo(String value) {
            addCriterion("pc_banner =", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerNotEqualTo(String value) {
            addCriterion("pc_banner <>", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerGreaterThan(String value) {
            addCriterion("pc_banner >", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerGreaterThanOrEqualTo(String value) {
            addCriterion("pc_banner >=", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerLessThan(String value) {
            addCriterion("pc_banner <", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerLessThanOrEqualTo(String value) {
            addCriterion("pc_banner <=", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerLike(String value) {
            addCriterion("pc_banner like", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerNotLike(String value) {
            addCriterion("pc_banner not like", value, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerIn(List<String> values) {
            addCriterion("pc_banner in", values, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerNotIn(List<String> values) {
            addCriterion("pc_banner not in", values, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerBetween(String value1, String value2) {
            addCriterion("pc_banner between", value1, value2, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcBannerNotBetween(String value1, String value2) {
            addCriterion("pc_banner not between", value1, value2, "pcBanner");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisIsNull() {
            addCriterion("pc_menu_meis is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisIsNotNull() {
            addCriterion("pc_menu_meis is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisEqualTo(String value) {
            addCriterion("pc_menu_meis =", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisNotEqualTo(String value) {
            addCriterion("pc_menu_meis <>", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisGreaterThan(String value) {
            addCriterion("pc_menu_meis >", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_meis >=", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisLessThan(String value) {
            addCriterion("pc_menu_meis <", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_meis <=", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisLike(String value) {
            addCriterion("pc_menu_meis like", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisNotLike(String value) {
            addCriterion("pc_menu_meis not like", value, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisIn(List<String> values) {
            addCriterion("pc_menu_meis in", values, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisNotIn(List<String> values) {
            addCriterion("pc_menu_meis not in", values, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisBetween(String value1, String value2) {
            addCriterion("pc_menu_meis between", value1, value2, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuMeisNotBetween(String value1, String value2) {
            addCriterion("pc_menu_meis not between", value1, value2, "pcMenuMeis");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyIsNull() {
            addCriterion("pc_menu_diany is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyIsNotNull() {
            addCriterion("pc_menu_diany is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyEqualTo(String value) {
            addCriterion("pc_menu_diany =", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyNotEqualTo(String value) {
            addCriterion("pc_menu_diany <>", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyGreaterThan(String value) {
            addCriterion("pc_menu_diany >", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_diany >=", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyLessThan(String value) {
            addCriterion("pc_menu_diany <", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_diany <=", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyLike(String value) {
            addCriterion("pc_menu_diany like", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyNotLike(String value) {
            addCriterion("pc_menu_diany not like", value, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyIn(List<String> values) {
            addCriterion("pc_menu_diany in", values, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyNotIn(List<String> values) {
            addCriterion("pc_menu_diany not in", values, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyBetween(String value1, String value2) {
            addCriterion("pc_menu_diany between", value1, value2, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuDianyNotBetween(String value1, String value2) {
            addCriterion("pc_menu_diany not between", value1, value2, "pcMenuDiany");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwIsNull() {
            addCriterion("pc_menu_gouw is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwIsNotNull() {
            addCriterion("pc_menu_gouw is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwEqualTo(String value) {
            addCriterion("pc_menu_gouw =", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwNotEqualTo(String value) {
            addCriterion("pc_menu_gouw <>", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwGreaterThan(String value) {
            addCriterion("pc_menu_gouw >", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_gouw >=", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwLessThan(String value) {
            addCriterion("pc_menu_gouw <", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_gouw <=", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwLike(String value) {
            addCriterion("pc_menu_gouw like", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwNotLike(String value) {
            addCriterion("pc_menu_gouw not like", value, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwIn(List<String> values) {
            addCriterion("pc_menu_gouw in", values, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwNotIn(List<String> values) {
            addCriterion("pc_menu_gouw not in", values, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwBetween(String value1, String value2) {
            addCriterion("pc_menu_gouw between", value1, value2, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuGouwNotBetween(String value1, String value2) {
            addCriterion("pc_menu_gouw not between", value1, value2, "pcMenuGouw");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouIsNull() {
            addCriterion("pc_menu_lvyou is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouIsNotNull() {
            addCriterion("pc_menu_lvyou is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouEqualTo(String value) {
            addCriterion("pc_menu_lvyou =", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouNotEqualTo(String value) {
            addCriterion("pc_menu_lvyou <>", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouGreaterThan(String value) {
            addCriterion("pc_menu_lvyou >", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_lvyou >=", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouLessThan(String value) {
            addCriterion("pc_menu_lvyou <", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_lvyou <=", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouLike(String value) {
            addCriterion("pc_menu_lvyou like", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouNotLike(String value) {
            addCriterion("pc_menu_lvyou not like", value, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouIn(List<String> values) {
            addCriterion("pc_menu_lvyou in", values, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouNotIn(List<String> values) {
            addCriterion("pc_menu_lvyou not in", values, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouBetween(String value1, String value2) {
            addCriterion("pc_menu_lvyou between", value1, value2, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuLvyouNotBetween(String value1, String value2) {
            addCriterion("pc_menu_lvyou not between", value1, value2, "pcMenuLvyou");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxIsNull() {
            addCriterion("pc_menu_xiux is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxIsNotNull() {
            addCriterion("pc_menu_xiux is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxEqualTo(String value) {
            addCriterion("pc_menu_xiux =", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxNotEqualTo(String value) {
            addCriterion("pc_menu_xiux <>", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxGreaterThan(String value) {
            addCriterion("pc_menu_xiux >", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_xiux >=", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxLessThan(String value) {
            addCriterion("pc_menu_xiux <", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_xiux <=", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxLike(String value) {
            addCriterion("pc_menu_xiux like", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxNotLike(String value) {
            addCriterion("pc_menu_xiux not like", value, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxIn(List<String> values) {
            addCriterion("pc_menu_xiux in", values, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxNotIn(List<String> values) {
            addCriterion("pc_menu_xiux not in", values, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxBetween(String value1, String value2) {
            addCriterion("pc_menu_xiux between", value1, value2, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuXiuxNotBetween(String value1, String value2) {
            addCriterion("pc_menu_xiux not between", value1, value2, "pcMenuXiux");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenIsNull() {
            addCriterion("pc_menu_liren is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenIsNotNull() {
            addCriterion("pc_menu_liren is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenEqualTo(String value) {
            addCriterion("pc_menu_liren =", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenNotEqualTo(String value) {
            addCriterion("pc_menu_liren <>", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenGreaterThan(String value) {
            addCriterion("pc_menu_liren >", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_liren >=", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenLessThan(String value) {
            addCriterion("pc_menu_liren <", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_liren <=", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenLike(String value) {
            addCriterion("pc_menu_liren like", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenNotLike(String value) {
            addCriterion("pc_menu_liren not like", value, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenIn(List<String> values) {
            addCriterion("pc_menu_liren in", values, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenNotIn(List<String> values) {
            addCriterion("pc_menu_liren not in", values, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenBetween(String value1, String value2) {
            addCriterion("pc_menu_liren between", value1, value2, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuLirenNotBetween(String value1, String value2) {
            addCriterion("pc_menu_liren not between", value1, value2, "pcMenuLiren");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghIsNull() {
            addCriterion("pc_menu_shengh is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghIsNotNull() {
            addCriterion("pc_menu_shengh is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghEqualTo(String value) {
            addCriterion("pc_menu_shengh =", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghNotEqualTo(String value) {
            addCriterion("pc_menu_shengh <>", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghGreaterThan(String value) {
            addCriterion("pc_menu_shengh >", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_shengh >=", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghLessThan(String value) {
            addCriterion("pc_menu_shengh <", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_shengh <=", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghLike(String value) {
            addCriterion("pc_menu_shengh like", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghNotLike(String value) {
            addCriterion("pc_menu_shengh not like", value, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghIn(List<String> values) {
            addCriterion("pc_menu_shengh in", values, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghNotIn(List<String> values) {
            addCriterion("pc_menu_shengh not in", values, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghBetween(String value1, String value2) {
            addCriterion("pc_menu_shengh between", value1, value2, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuShenghNotBetween(String value1, String value2) {
            addCriterion("pc_menu_shengh not between", value1, value2, "pcMenuShengh");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiIsNull() {
            addCriterion("pc_menu_licai is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiIsNotNull() {
            addCriterion("pc_menu_licai is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiEqualTo(String value) {
            addCriterion("pc_menu_licai =", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiNotEqualTo(String value) {
            addCriterion("pc_menu_licai <>", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiGreaterThan(String value) {
            addCriterion("pc_menu_licai >", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiGreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_licai >=", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiLessThan(String value) {
            addCriterion("pc_menu_licai <", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiLessThanOrEqualTo(String value) {
            addCriterion("pc_menu_licai <=", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiLike(String value) {
            addCriterion("pc_menu_licai like", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiNotLike(String value) {
            addCriterion("pc_menu_licai not like", value, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiIn(List<String> values) {
            addCriterion("pc_menu_licai in", values, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiNotIn(List<String> values) {
            addCriterion("pc_menu_licai not in", values, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiBetween(String value1, String value2) {
            addCriterion("pc_menu_licai between", value1, value2, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuLicaiNotBetween(String value1, String value2) {
            addCriterion("pc_menu_licai not between", value1, value2, "pcMenuLicai");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1IsNull() {
            addCriterion("pc_menu_other1 is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1IsNotNull() {
            addCriterion("pc_menu_other1 is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1EqualTo(String value) {
            addCriterion("pc_menu_other1 =", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1NotEqualTo(String value) {
            addCriterion("pc_menu_other1 <>", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1GreaterThan(String value) {
            addCriterion("pc_menu_other1 >", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1GreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_other1 >=", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1LessThan(String value) {
            addCriterion("pc_menu_other1 <", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1LessThanOrEqualTo(String value) {
            addCriterion("pc_menu_other1 <=", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1Like(String value) {
            addCriterion("pc_menu_other1 like", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1NotLike(String value) {
            addCriterion("pc_menu_other1 not like", value, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1In(List<String> values) {
            addCriterion("pc_menu_other1 in", values, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1NotIn(List<String> values) {
            addCriterion("pc_menu_other1 not in", values, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1Between(String value1, String value2) {
            addCriterion("pc_menu_other1 between", value1, value2, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther1NotBetween(String value1, String value2) {
            addCriterion("pc_menu_other1 not between", value1, value2, "pcMenuOther1");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2IsNull() {
            addCriterion("pc_menu_other2 is null");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2IsNotNull() {
            addCriterion("pc_menu_other2 is not null");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2EqualTo(String value) {
            addCriterion("pc_menu_other2 =", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2NotEqualTo(String value) {
            addCriterion("pc_menu_other2 <>", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2GreaterThan(String value) {
            addCriterion("pc_menu_other2 >", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2GreaterThanOrEqualTo(String value) {
            addCriterion("pc_menu_other2 >=", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2LessThan(String value) {
            addCriterion("pc_menu_other2 <", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2LessThanOrEqualTo(String value) {
            addCriterion("pc_menu_other2 <=", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2Like(String value) {
            addCriterion("pc_menu_other2 like", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2NotLike(String value) {
            addCriterion("pc_menu_other2 not like", value, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2In(List<String> values) {
            addCriterion("pc_menu_other2 in", values, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2NotIn(List<String> values) {
            addCriterion("pc_menu_other2 not in", values, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2Between(String value1, String value2) {
            addCriterion("pc_menu_other2 between", value1, value2, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andPcMenuOther2NotBetween(String value1, String value2) {
            addCriterion("pc_menu_other2 not between", value1, value2, "pcMenuOther2");
            return (Criteria) this;
        }

        public Criteria andAppBanner1IsNull() {
            addCriterion("app_banner1 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner1IsNotNull() {
            addCriterion("app_banner1 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner1EqualTo(String value) {
            addCriterion("app_banner1 =", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1NotEqualTo(String value) {
            addCriterion("app_banner1 <>", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1GreaterThan(String value) {
            addCriterion("app_banner1 >", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner1 >=", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1LessThan(String value) {
            addCriterion("app_banner1 <", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1LessThanOrEqualTo(String value) {
            addCriterion("app_banner1 <=", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1Like(String value) {
            addCriterion("app_banner1 like", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1NotLike(String value) {
            addCriterion("app_banner1 not like", value, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1In(List<String> values) {
            addCriterion("app_banner1 in", values, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1NotIn(List<String> values) {
            addCriterion("app_banner1 not in", values, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1Between(String value1, String value2) {
            addCriterion("app_banner1 between", value1, value2, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner1NotBetween(String value1, String value2) {
            addCriterion("app_banner1 not between", value1, value2, "appBanner1");
            return (Criteria) this;
        }

        public Criteria andAppBanner2IsNull() {
            addCriterion("app_banner2 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner2IsNotNull() {
            addCriterion("app_banner2 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner2EqualTo(String value) {
            addCriterion("app_banner2 =", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2NotEqualTo(String value) {
            addCriterion("app_banner2 <>", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2GreaterThan(String value) {
            addCriterion("app_banner2 >", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner2 >=", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2LessThan(String value) {
            addCriterion("app_banner2 <", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2LessThanOrEqualTo(String value) {
            addCriterion("app_banner2 <=", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2Like(String value) {
            addCriterion("app_banner2 like", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2NotLike(String value) {
            addCriterion("app_banner2 not like", value, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2In(List<String> values) {
            addCriterion("app_banner2 in", values, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2NotIn(List<String> values) {
            addCriterion("app_banner2 not in", values, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2Between(String value1, String value2) {
            addCriterion("app_banner2 between", value1, value2, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner2NotBetween(String value1, String value2) {
            addCriterion("app_banner2 not between", value1, value2, "appBanner2");
            return (Criteria) this;
        }

        public Criteria andAppBanner3IsNull() {
            addCriterion("app_banner3 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner3IsNotNull() {
            addCriterion("app_banner3 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner3EqualTo(String value) {
            addCriterion("app_banner3 =", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3NotEqualTo(String value) {
            addCriterion("app_banner3 <>", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3GreaterThan(String value) {
            addCriterion("app_banner3 >", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner3 >=", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3LessThan(String value) {
            addCriterion("app_banner3 <", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3LessThanOrEqualTo(String value) {
            addCriterion("app_banner3 <=", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3Like(String value) {
            addCriterion("app_banner3 like", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3NotLike(String value) {
            addCriterion("app_banner3 not like", value, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3In(List<String> values) {
            addCriterion("app_banner3 in", values, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3NotIn(List<String> values) {
            addCriterion("app_banner3 not in", values, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3Between(String value1, String value2) {
            addCriterion("app_banner3 between", value1, value2, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner3NotBetween(String value1, String value2) {
            addCriterion("app_banner3 not between", value1, value2, "appBanner3");
            return (Criteria) this;
        }

        public Criteria andAppBanner4IsNull() {
            addCriterion("app_banner4 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner4IsNotNull() {
            addCriterion("app_banner4 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner4EqualTo(String value) {
            addCriterion("app_banner4 =", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4NotEqualTo(String value) {
            addCriterion("app_banner4 <>", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4GreaterThan(String value) {
            addCriterion("app_banner4 >", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner4 >=", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4LessThan(String value) {
            addCriterion("app_banner4 <", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4LessThanOrEqualTo(String value) {
            addCriterion("app_banner4 <=", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4Like(String value) {
            addCriterion("app_banner4 like", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4NotLike(String value) {
            addCriterion("app_banner4 not like", value, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4In(List<String> values) {
            addCriterion("app_banner4 in", values, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4NotIn(List<String> values) {
            addCriterion("app_banner4 not in", values, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4Between(String value1, String value2) {
            addCriterion("app_banner4 between", value1, value2, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner4NotBetween(String value1, String value2) {
            addCriterion("app_banner4 not between", value1, value2, "appBanner4");
            return (Criteria) this;
        }

        public Criteria andAppBanner5IsNull() {
            addCriterion("app_banner5 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner5IsNotNull() {
            addCriterion("app_banner5 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner5EqualTo(String value) {
            addCriterion("app_banner5 =", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5NotEqualTo(String value) {
            addCriterion("app_banner5 <>", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5GreaterThan(String value) {
            addCriterion("app_banner5 >", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner5 >=", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5LessThan(String value) {
            addCriterion("app_banner5 <", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5LessThanOrEqualTo(String value) {
            addCriterion("app_banner5 <=", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5Like(String value) {
            addCriterion("app_banner5 like", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5NotLike(String value) {
            addCriterion("app_banner5 not like", value, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5In(List<String> values) {
            addCriterion("app_banner5 in", values, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5NotIn(List<String> values) {
            addCriterion("app_banner5 not in", values, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5Between(String value1, String value2) {
            addCriterion("app_banner5 between", value1, value2, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner5NotBetween(String value1, String value2) {
            addCriterion("app_banner5 not between", value1, value2, "appBanner5");
            return (Criteria) this;
        }

        public Criteria andAppBanner6IsNull() {
            addCriterion("app_banner6 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner6IsNotNull() {
            addCriterion("app_banner6 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner6EqualTo(String value) {
            addCriterion("app_banner6 =", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6NotEqualTo(String value) {
            addCriterion("app_banner6 <>", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6GreaterThan(String value) {
            addCriterion("app_banner6 >", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner6 >=", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6LessThan(String value) {
            addCriterion("app_banner6 <", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6LessThanOrEqualTo(String value) {
            addCriterion("app_banner6 <=", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6Like(String value) {
            addCriterion("app_banner6 like", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6NotLike(String value) {
            addCriterion("app_banner6 not like", value, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6In(List<String> values) {
            addCriterion("app_banner6 in", values, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6NotIn(List<String> values) {
            addCriterion("app_banner6 not in", values, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6Between(String value1, String value2) {
            addCriterion("app_banner6 between", value1, value2, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner6NotBetween(String value1, String value2) {
            addCriterion("app_banner6 not between", value1, value2, "appBanner6");
            return (Criteria) this;
        }

        public Criteria andAppBanner7IsNull() {
            addCriterion("app_banner7 is null");
            return (Criteria) this;
        }

        public Criteria andAppBanner7IsNotNull() {
            addCriterion("app_banner7 is not null");
            return (Criteria) this;
        }

        public Criteria andAppBanner7EqualTo(String value) {
            addCriterion("app_banner7 =", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7NotEqualTo(String value) {
            addCriterion("app_banner7 <>", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7GreaterThan(String value) {
            addCriterion("app_banner7 >", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7GreaterThanOrEqualTo(String value) {
            addCriterion("app_banner7 >=", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7LessThan(String value) {
            addCriterion("app_banner7 <", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7LessThanOrEqualTo(String value) {
            addCriterion("app_banner7 <=", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7Like(String value) {
            addCriterion("app_banner7 like", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7NotLike(String value) {
            addCriterion("app_banner7 not like", value, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7In(List<String> values) {
            addCriterion("app_banner7 in", values, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7NotIn(List<String> values) {
            addCriterion("app_banner7 not in", values, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7Between(String value1, String value2) {
            addCriterion("app_banner7 between", value1, value2, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andAppBanner7NotBetween(String value1, String value2) {
            addCriterion("app_banner7 not between", value1, value2, "appBanner7");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNull() {
            addCriterion("admin_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminIdIsNotNull() {
            addCriterion("admin_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminIdEqualTo(Long value) {
            addCriterion("admin_id =", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotEqualTo(Long value) {
            addCriterion("admin_id <>", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThan(Long value) {
            addCriterion("admin_id >", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdGreaterThanOrEqualTo(Long value) {
            addCriterion("admin_id >=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThan(Long value) {
            addCriterion("admin_id <", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdLessThanOrEqualTo(Long value) {
            addCriterion("admin_id <=", value, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdIn(List<Long> values) {
            addCriterion("admin_id in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotIn(List<Long> values) {
            addCriterion("admin_id not in", values, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdBetween(Long value1, Long value2) {
            addCriterion("admin_id between", value1, value2, "adminId");
            return (Criteria) this;
        }

        public Criteria andAdminIdNotBetween(Long value1, Long value2) {
            addCriterion("admin_id not between", value1, value2, "adminId");
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