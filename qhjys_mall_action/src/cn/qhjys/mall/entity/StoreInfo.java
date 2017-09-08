package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class StoreInfo implements Serializable {
    private Long id;

    private String name;

    private String logo;

    private String keywork;

    private Long sellerId;

    private String contactName;

    private String contactType;

    private String contactTel;

    private String contactPhone;

    private String promise;

    private String level;

    private Integer points;

    private Long province;

    private Long city;

    private Long area;

    private Long districtId;

    private String address;

    private Double longitude;

    private Double latitude;

    private String images;

    private String labels;

    private Long categoryId;

    private String categoryDetails;

    private Date createTime;

    private Integer status;

    private Integer enabled;

    private Integer scope;

    private Integer openCashier;

    private Integer openOrder;

    private Long rateId;

    private Integer statementPeriod;

    private Integer messageNum;

    private Long clerkId;

    private Integer isEffective;

    private Date effectiveDate;

    private Integer channelValidation;

    private String storeDetail;
    
    private Integer wxAuthState;
    
    private Integer zfbAuthState;

    private Integer txShow;
    
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getKeywork() {
        return keywork;
    }

    public void setKeywork(String keywork) {
        this.keywork = keywork == null ? null : keywork.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType == null ? null : contactType.trim();
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel == null ? null : contactTel.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getPromise() {
        return promise;
    }

    public void setPromise(String promise) {
        this.promise = promise == null ? null : promise.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels == null ? null : labels.trim();
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails == null ? null : categoryDetails.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Integer getOpenCashier() {
        return openCashier;
    }

    public void setOpenCashier(Integer openCashier) {
        this.openCashier = openCashier;
    }

    public Integer getOpenOrder() {
        return openOrder;
    }

    public void setOpenOrder(Integer openOrder) {
        this.openOrder = openOrder;
    }

    public Long getRateId() {
        return rateId;
    }

    public void setRateId(Long rateId) {
        this.rateId = rateId;
    }

    public Integer getStatementPeriod() {
        return statementPeriod;
    }

    public void setStatementPeriod(Integer statementPeriod) {
        this.statementPeriod = statementPeriod;
    }

    public Integer getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public Integer getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(Integer isEffective) {
        this.isEffective = isEffective;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Integer getChannelValidation() {
        return channelValidation;
    }

    public void setChannelValidation(Integer channelValidation) {
        this.channelValidation = channelValidation;
    }

    public String getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(String storeDetail) {
        this.storeDetail = storeDetail == null ? null : storeDetail.trim();
    }

	public Integer getWxAuthState() {
		return wxAuthState;
	}

	public void setWxAuthState(Integer wxAuthState) {
		this.wxAuthState = wxAuthState;
	}

	public Integer getZfbAuthState() {
		return zfbAuthState;
	}

	public void setZfbAuthState(Integer zfbAuthState) {
		this.zfbAuthState = zfbAuthState;
	}

	public Integer getTxShow() {
		return txShow;
	}

	public void setTxShow(Integer txShow) {
		this.txShow = txShow;
	}
    
}