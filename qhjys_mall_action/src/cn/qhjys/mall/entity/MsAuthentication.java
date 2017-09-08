package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class MsAuthentication implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long sellerId;
    private Long storeId;
    private String bankSqName;
    private String aliasName;
    private String bankAppId;
    private String authPaydir;
    private String wxCategoryId;
    private String zfbCategoryId;
    private String servicePhone;
    private String contactName;
    private String contactPhone;
    private String contactMobile;
    private String contactEmail;
    private String contactType;
    private String contactIdCardNo;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
    private String addressType;
    private String address;
    private String businessLicense;
    private String businessLicenseType;
    private Long bankInfoId;
    private String logonId;
    private String payCodeInfo;
    private String chnlType;
    private String weiXinChannelId;
    private String subscribeAppid;
    private String contactWechatid;
    private String acceptFlag;
    private String bankAcceptAppid;
    private String requestNo;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getBankSqName() {
        return bankSqName;
    }

    public void setBankSqName(String bankSqName) {
        this.bankSqName = bankSqName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getBankAppId() {
        return bankAppId;
    }

    public void setBankAppId(String bankAppId) {
        this.bankAppId = bankAppId;
    }

    public String getAuthPaydir() {
        return authPaydir;
    }

    public void setAuthPaydir(String authPaydir) {
        this.authPaydir = authPaydir;
    }
    
    public String getWxCategoryId() {
		return wxCategoryId;
	}

	public void setWxCategoryId(String wxCategoryId) {
		this.wxCategoryId = wxCategoryId;
	}

	public String getZfbCategoryId() {
		return zfbCategoryId;
	}

	public void setZfbCategoryId(String zfbCategoryId) {
		this.zfbCategoryId = zfbCategoryId;
	}

	public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactIdCardNo() {
        return contactIdCardNo;
    }

    public void setContactIdCardNo(String contactIdCardNo) {
        this.contactIdCardNo = contactIdCardNo;
    }
    
    public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseType() {
        return businessLicenseType;
    }

    public void setBusinessLicenseType(String businessLicenseType) {
        this.businessLicenseType = businessLicenseType;
    }

    public Long getBankInfoId() {
        return bankInfoId;
    }

    public void setBankInfoId(Long bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public String getLogonId() {
        return logonId;
    }

    public void setLogonId(String logonId) {
        this.logonId = logonId;
    }

    public String getPayCodeInfo() {
        return payCodeInfo;
    }

    public void setPayCodeInfo(String payCodeInfo) {
        this.payCodeInfo = payCodeInfo;
    }

    public String getChnlType() {
        return chnlType;
    }

    public void setChnlType(String chnlType) {
        this.chnlType = chnlType;
    }

    public String getWeiXinChannelId() {
        return weiXinChannelId;
    }

    public void setWeiXinChannelId(String weiXinChannelId) {
        this.weiXinChannelId = weiXinChannelId;
    }

    public String getSubscribeAppid() {
        return subscribeAppid;
    }

    public void setSubscribeAppid(String subscribeAppid) {
        this.subscribeAppid = subscribeAppid;
    }

    public String getContactWechatid() {
        return contactWechatid;
    }

    public void setContactWechatid(String contactWechatid) {
        this.contactWechatid = contactWechatid;
    }

    public String getAcceptFlag() {
        return acceptFlag;
    }

    public void setAcceptFlag(String acceptFlag) {
        this.acceptFlag = acceptFlag;
    }

    public String getBankAcceptAppid() {
        return bankAcceptAppid;
    }

    public void setBankAcceptAppid(String bankAcceptAppid) {
        this.bankAcceptAppid = bankAcceptAppid;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}