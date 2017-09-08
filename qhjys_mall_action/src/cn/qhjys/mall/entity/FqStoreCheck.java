package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class FqStoreCheck implements Serializable {
    private Long id;

    private Long storeId;

    private String storeName;

    private String storeLogo;

    private String storeInfo;

    private String address;

    private String trafficBeginTime;

    private String trafficEndTime;

    private Integer status;

    private String phoneNum;

    private Integer proportion;

    private Long locationId;

    private Date createTime;

    private String storeImage;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo == null ? null : storeLogo.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage == null ? null : storeImage.trim();
    }
}