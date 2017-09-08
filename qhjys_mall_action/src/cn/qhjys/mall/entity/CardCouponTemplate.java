package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CardCouponTemplate implements Serializable {
    private Long id;

    private String name;

    private Integer couponCfg;

    private Integer totalCount;

    private Integer surplusCount;

    private Long sellerId;

    private Integer countLimit;

    private String useExplain;

    private BigDecimal amount;

    private Integer discount;

    private Integer validityCfg;

    private Integer validityDay;

    private Date validityStarttime;

    private Date validityEndtime;

    private Integer validityUseStarttime;

    private Integer validityUserEndtime;

    private Integer useCfg;

    private BigDecimal useOverAmount;

    private Integer statusCfg;

    private Long storeId;

    private String storeName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCouponCfg() {
        return couponCfg;
    }

    public void setCouponCfg(Integer couponCfg) {
        this.couponCfg = couponCfg;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(Integer surplusCount) {
        this.surplusCount = surplusCount;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCountLimit() {
        return countLimit;
    }

    public void setCountLimit(Integer countLimit) {
        this.countLimit = countLimit;
    }

    public String getUseExplain() {
        return useExplain;
    }

    public void setUseExplain(String useExplain) {
        this.useExplain = useExplain == null ? null : useExplain.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getValidityCfg() {
        return validityCfg;
    }

    public void setValidityCfg(Integer validityCfg) {
        this.validityCfg = validityCfg;
    }

    public Integer getValidityDay() {
        return validityDay;
    }

    public void setValidityDay(Integer validityDay) {
        this.validityDay = validityDay;
    }

    public Date getValidityStarttime() {
        return validityStarttime;
    }

    public void setValidityStarttime(Date validityStarttime) {
        this.validityStarttime = validityStarttime;
    }

    public Date getValidityEndtime() {
        return validityEndtime;
    }

    public void setValidityEndtime(Date validityEndtime) {
        this.validityEndtime = validityEndtime;
    }

    public Integer getValidityUseStarttime() {
        return validityUseStarttime;
    }

    public void setValidityUseStarttime(Integer validityUseStarttime) {
        this.validityUseStarttime = validityUseStarttime;
    }

    public Integer getValidityUserEndtime() {
        return validityUserEndtime;
    }

    public void setValidityUserEndtime(Integer validityUserEndtime) {
        this.validityUserEndtime = validityUserEndtime;
    }

    public Integer getUseCfg() {
        return useCfg;
    }

    public void setUseCfg(Integer useCfg) {
        this.useCfg = useCfg;
    }

    public BigDecimal getUseOverAmount() {
        return useOverAmount;
    }

    public void setUseOverAmount(BigDecimal useOverAmount) {
        this.useOverAmount = useOverAmount;
    }

    public Integer getStatusCfg() {
        return statusCfg;
    }

    public void setStatusCfg(Integer statusCfg) {
        this.statusCfg = statusCfg;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }
}