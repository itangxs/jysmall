package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;

public class CompanyInfoVo  extends BaseVo{
	//公司编号
	private Long companyId;
	//公司名称
	private String name;
	//法人姓名
	private String corpnName;
	//法人身份证号
	private String corpnId;
	//法人身份证图片
	private String corpnCard;
	//营业执照编号
	private String licenseNumber;
	//执照所在省份
	private Long licenseProvince;
	//执照所在城市
	private Long licenseCity;
	//执照所在区域
	private Long licenseArea;
	//执照详细地址
	private String licenseAddress;
	//营业执照图片
	private String licenseCard;
	//公司成立时间
	private String setUpDate;
	//公司注册资本
	private Long capital;
	//经营业务范围
	private String scope;
	//组织机构代码证
	private String organizationCode;
	//组织机构代码证图片
	private String organizationImage;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCorpnName() {
		return corpnName;
	}

	public void setCorpnName(String corpnName) {
		this.corpnName = corpnName == null ? null : corpnName.trim();
	}

	public String getCorpnId() {
		return corpnId;
	}

	public void setCorpnId(String corpnId) {
		this.corpnId = corpnId == null ? null : corpnId.trim();
	}

	public String getCorpnCard() {
		return corpnCard;
	}

	public void setCorpnCard(String corpnCard) {
		this.corpnCard = corpnCard == null ? null : corpnCard.trim();
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber == null ? null : licenseNumber.trim();
	}

	public Long getLicenseProvince() {
		return licenseProvince;
	}

	public void setLicenseProvince(Long licenseProvince) {
		this.licenseProvince = licenseProvince;
	}

	public Long getLicenseCity() {
		return licenseCity;
	}

	public void setLicenseCity(Long licenseCity) {
		this.licenseCity = licenseCity;
	}

	public Long getLicenseArea() {
		return licenseArea;
	}

	public void setLicenseArea(Long licenseArea) {
		this.licenseArea = licenseArea;
	}

	public String getLicenseAddress() {
		return licenseAddress;
	}

	public void setLicenseAddress(String licenseAddress) {
		this.licenseAddress = licenseAddress == null ? null : licenseAddress.trim();
	}

	public String getLicenseCard() {
		return licenseCard;
	}

	public void setLicenseCard(String licenseCard) {
		this.licenseCard = licenseCard == null ? null : licenseCard.trim();
	}

	public String getSetUpDate() {
		return setUpDate;
	}

	public void setSetUpDate(String setUpDate) {
		this.setUpDate = setUpDate;
	}

	public Long getCapital() {
		return capital;
	}

	public void setCapital(Long capital) {
		this.capital = capital;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope == null ? null : scope.trim();
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode == null ? null : organizationCode.trim();
	}

	public String getOrganizationImage() {
		return organizationImage;
	}

	public void setOrganizationImage(String organizationImage) {
		this.organizationImage = organizationImage == null ? null : organizationImage.trim();
	}

}