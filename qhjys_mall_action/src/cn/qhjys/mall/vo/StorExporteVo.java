package cn.qhjys.mall.vo;


/**
 * 商家(店铺) VO
 * 
 * @author JiangXiaoPing
 */
public class StorExporteVo {
	
	private String userName;				//注册号
	private String name;					//店铺名称
	private String contactName;				//联系人
	private String contactTel;				//联系电话
	private String area;					//区域
	private String address;					//详细街道
	private String companyName;				//商户名称
	private String licenseNumber;			//营业执照号
	private String bankName;				//银行名称
	private String cardholder;				//开户人
	private String carkNum;					//银行卡号
	private String phone;					//预留手机号
	private String corpnId;					//身份证号
	private String categoryName;			//行业
	private String rateName;				//费率套餐+结算周期
	private String clerkName;				//绑定渠道
	private Integer wxAuthState;			//微信进件状态
	private Integer zfbAuthState;			//支付宝进件状态
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getCarkNum() {
		return carkNum;
	}
	public void setCarkNum(String carkNum) {
		this.carkNum = carkNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCorpnId() {
		return corpnId;
	}
	public void setCorpnId(String corpnId) {
		this.corpnId = corpnId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public String getClerkName() {
		return clerkName;
	}
	public void setClerkName(String clerkName) {
		this.clerkName = clerkName;
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
}