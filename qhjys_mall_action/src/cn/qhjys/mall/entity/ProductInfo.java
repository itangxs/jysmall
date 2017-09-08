package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo implements Serializable {
    private Long id;

    private String no;

    private String name;

    private Long storeId;

    private String storeName;

    private Long categoryId;

    private Integer prodType;

    private String title;

    private String images;

    private BigDecimal unitPrice;

    private BigDecimal origPrice;

    private Integer prodStock;

    private Integer maxPay;

    private Integer maxUse;

    private Long activityId;

    private Integer salesNum;

    private Float scoreAvg;

    private Integer markNum;

    private Date starDate;

    private Date endDate;

    private String promise;

    private Integer scheduleer;

    private Integer scheduleType;

    private Integer haoping;

    private Integer zhongping;

    private Integer chaping;

    private Date onShelf;

    private Date offShelf;

    private Integer status;

    private Date createTime;

    private Integer enabled;

    private String keywords;

    private String description;

    private Integer level;

    private String prodDetail;

    private String buyingTips;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getProdType() {
        return prodType;
    }

    public void setProdType(Integer prodType) {
        this.prodType = prodType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(BigDecimal origPrice) {
        this.origPrice = origPrice;
    }

    public Integer getProdStock() {
        return prodStock;
    }

    public void setProdStock(Integer prodStock) {
        this.prodStock = prodStock;
    }

    public Integer getMaxPay() {
        return maxPay;
    }

    public void setMaxPay(Integer maxPay) {
        this.maxPay = maxPay;
    }

    public Integer getMaxUse() {
        return maxUse;
    }

    public void setMaxUse(Integer maxUse) {
        this.maxUse = maxUse;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Float getScoreAvg() {
        return scoreAvg;
    }

    public void setScoreAvg(Float scoreAvg) {
        this.scoreAvg = scoreAvg;
    }

    public Integer getMarkNum() {
        return markNum;
    }

    public void setMarkNum(Integer markNum) {
        this.markNum = markNum;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise == null ? null : promise.trim();
    }

    public Integer getScheduleer() {
        return scheduleer;
    }

    public void setScheduleer(Integer scheduleer) {
        this.scheduleer = scheduleer;
    }

    public Integer getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Integer getHaoping() {
        return haoping;
    }

    public void setHaoping(Integer haoping) {
        this.haoping = haoping;
    }

    public Integer getZhongping() {
        return zhongping;
    }

    public void setZhongping(Integer zhongping) {
        this.zhongping = zhongping;
    }

    public Integer getChaping() {
        return chaping;
    }

    public void setChaping(Integer chaping) {
        this.chaping = chaping;
    }

    public Date getOnShelf() {
        return onShelf;
    }

    public void setOnShelf(Date onShelf) {
        this.onShelf = onShelf;
    }

    public Date getOffShelf() {
        return offShelf;
    }

    public void setOffShelf(Date offShelf) {
        this.offShelf = offShelf;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail == null ? null : prodDetail.trim();
    }

    public String getBuyingTips() {
        return buyingTips;
    }

    public void setBuyingTips(String buyingTips) {
        this.buyingTips = buyingTips == null ? null : buyingTips.trim();
    }
}