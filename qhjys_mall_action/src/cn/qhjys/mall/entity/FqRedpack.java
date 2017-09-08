package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqRedpack implements Serializable {
    private Long id;

    private String activityName;

    private Long storeId;

    private String storeName;

    private Date beginDate;

    private Date endDate;

    private BigDecimal maxAmount;

    private BigDecimal minAmount;

    private Integer daliyNum;

    private Integer status;

    private Date createTime;

    private BigDecimal totalMoney;

    private BigDecimal laveMoney;

    private Integer hasChange;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getDaliyNum() {
        return daliyNum;
    }

    public void setDaliyNum(Integer daliyNum) {
        this.daliyNum = daliyNum;
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

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getLaveMoney() {
        return laveMoney;
    }

    public void setLaveMoney(BigDecimal laveMoney) {
        this.laveMoney = laveMoney;
    }

    public Integer getHasChange() {
        return hasChange;
    }

    public void setHasChange(Integer hasChange) {
        this.hasChange = hasChange;
    }
}