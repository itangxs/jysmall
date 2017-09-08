package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.entity.StoreRebate;

public class FqStoreVo {
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
    
    private BigDecimal rebateNum;
    
    private BigDecimal rebateId;
    
    private StoreRebate storeRebateTemp;

	public StoreRebate getStoreRebateTemp() {
		return storeRebateTemp;
	}

	public void setStoreRebateTemp(StoreRebate storeRebateTemp) {
		this.storeRebateTemp = storeRebateTemp;
	}

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
		this.storeName = storeName;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(String storeInfo) {
		this.storeInfo = storeInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTrafficBeginTime() {
		return trafficBeginTime;
	}

	public void setTrafficBeginTime(String trafficBeginTime) {
		this.trafficBeginTime = trafficBeginTime;
	}

	public String getTrafficEndTime() {
		return trafficEndTime;
	}

	public void setTrafficEndTime(String trafficEndTime) {
		this.trafficEndTime = trafficEndTime;
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
		this.phoneNum = phoneNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		this.storeImage = storeImage;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getRebateNum() {
		return rebateNum;
	}

	public void setRebateNum(BigDecimal rebateNum) {
		this.rebateNum = rebateNum;
	}

	public BigDecimal getRebateId() {
		return rebateId;
	}

	public void setRebateId(BigDecimal rebateId) {
		this.rebateId = rebateId;
	}
    
    
}
