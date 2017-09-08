package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CompanyInfo implements Serializable {
    private Long id;

    private String name;

    private String corpnName;

    private String corpnId;

    private String corpnCard;

    private String licenseNumber;

    private String businessLicenseType;

    private Long licenseProvince;

    private Long licenseCity;

    private Long licenseArea;

    private String licenseAddress;

    private String licenseCard;

    private Date setUpDate;

    private Long capital;

    private String scope;

    private String organizationCode;

    private String organizationImage;

    private Date createTime;

    private Integer enabled;

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

    public String getBusinessLicenseType() {
        return businessLicenseType;
    }

    public void setBusinessLicenseType(String businessLicenseType) {
        this.businessLicenseType = businessLicenseType == null ? null : businessLicenseType.trim();
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

    public Date getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(Date setUpDate) {
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
}