package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqStore implements Serializable {
    private Long id;

    private String storeName;

    private String storeLogo;

    private String activityInfo;

    private String storeInfo;

    private String address;

    private String trafficBeginTime;

    private String trafficEndTime;

    private Integer status;

    private String phoneNum;

    private String userName;

    private String password;

    private Integer proportion;

    private Long locationId;

    private Long cuisineId;

    private Date createTime;

    private Long sellerId;

    private BigDecimal storeRebate;

    private String storeImage;

    private Integer type;

    private Integer level;

    private String clerkPhone;

    private BigDecimal daliyCredit;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo == null ? null : storeLogo.trim();
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo == null ? null : activityInfo.trim();
    }

    public String getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(String storeInfo) {
        this.storeInfo = storeInfo == null ? null : storeInfo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTrafficBeginTime() {
        return trafficBeginTime;
    }

    public void setTrafficBeginTime(String trafficBeginTime) {
        this.trafficBeginTime = trafficBeginTime == null ? null : trafficBeginTime.trim();
    }

    public String getTrafficEndTime() {
        return trafficEndTime;
    }

    public void setTrafficEndTime(String trafficEndTime) {
        this.trafficEndTime = trafficEndTime == null ? null : trafficEndTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getProportion() {
        return proportion;
    }

    public void setProportion(Integer proportion) {
        this.proportion = proportion;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getCuisineId() {
        return cuisineId;
    }

    public void setCuisineId(Long cuisineId) {
        this.cuisineId = cuisineId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getStoreRebate() {
        return storeRebate;
    }

    public void setStoreRebate(BigDecimal storeRebate) {
        this.storeRebate = storeRebate;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage == null ? null : storeImage.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getClerkPhone() {
        return clerkPhone;
    }

    public void setClerkPhone(String clerkPhone) {
        this.clerkPhone = clerkPhone == null ? null : clerkPhone.trim();
    }

    public BigDecimal getDaliyCredit() {
        return daliyCredit;
    }

    public void setDaliyCredit(BigDecimal daliyCredit) {
        this.daliyCredit = daliyCredit;
    }
}