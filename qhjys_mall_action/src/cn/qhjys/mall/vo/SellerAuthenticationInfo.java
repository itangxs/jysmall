package cn.qhjys.mall.vo;

public class SellerAuthenticationInfo {
	
	private Long storeId; //店铺ID
	private Integer status; //店铺审核状态
	private String contactName; //负责人
	private String companyName; //公司名称
	private String address; //详细街道
	private String storeName; //店铺名称
	private String contactTel; //联系电话
	private String corpnName; //法人
	private String licenseNumber; //营业执照编号
	private String licenseCard; //营业执照
	private String corpnCard; //法人身份证正面
	private String logo; //店铺照片
	private String bankName; //开户银行
	private String carkNum; //银行卡号
	private String corpnId; //身份证号码
	private String branch; //开户支行
	private String cardholder; //开户人
	private String phone; //银行预留手机号码
	private Long province; //省份
	private Long city; //城市
	private Long area; //区域
	private String contactType; //联系人类型
	private String businessLicenseType; //营业执照类型
	private Long msAuthenticationId;  //店铺进件信息ID
	
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getCorpnName() {
		return corpnName;
	}
	public void setCorpnName(String corpnName) {
		this.corpnName = corpnName;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCarkNum() {
		return carkNum;
	}
	public void setCarkNum(String carkNum) {
		this.carkNum = carkNum;
	}
	public String getCorpnId() {
		return corpnId;
	}
	public void setCorpnId(String corpnId) {
		this.corpnId = corpnId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLicenseCard() {
		return licenseCard;
	}
	public void setLicenseCard(String licenseCard) {
		this.licenseCard = licenseCard;
	}
	public String getCorpnCard() {
		return corpnCard;
	}
	public void setCorpnCard(String corpnCard) {
		this.corpnCard = corpnCard;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getBusinessLicenseType() {
		return businessLicenseType;
	}
	public void setBusinessLicenseType(String businessLicenseType) {
		this.businessLicenseType = businessLicenseType;
	}
	public Long getMsAuthenticationId() {
		return msAuthenticationId;
	}
	public void setMsAuthenticationId(Long msAuthenticationId) {
		this.msAuthenticationId = msAuthenticationId;
	}
}
