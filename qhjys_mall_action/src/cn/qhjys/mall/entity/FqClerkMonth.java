package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqClerkMonth implements Serializable {
    private Long id;

    private Date clerkTime;

    private Long provinceId;

    private String provinceName;

    private Long cityId;

    private String cityName;

    private Long branchId;

    private String branchName;

    private Long teamId;

    private String teamName;

    private Long clerkId;

    private String clerkName;

    private String countMonth;

    private Integer signedNum;

    private Integer effectiveNum;

    private BigDecimal totalMoney;

    private BigDecimal cashMoney;

    private BigDecimal clerkMoney;

    private BigDecimal teamMoney;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getClerkTime() {
        return clerkTime;
    }

    public void setClerkTime(Date clerkTime) {
        this.clerkTime = clerkTime;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName == null ? null : clerkName.trim();
    }

    public String getCountMonth() {
        return countMonth;
    }

    public void setCountMonth(String countMonth) {
        this.countMonth = countMonth == null ? null : countMonth.trim();
    }

    public Integer getSignedNum() {
        return signedNum;
    }

    public void setSignedNum(Integer signedNum) {
        this.signedNum = signedNum;
    }

    public Integer getEffectiveNum() {
        return effectiveNum;
    }

    public void setEffectiveNum(Integer effectiveNum) {
        this.effectiveNum = effectiveNum;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public BigDecimal getClerkMoney() {
        return clerkMoney;
    }

    public void setClerkMoney(BigDecimal clerkMoney) {
        this.clerkMoney = clerkMoney;
    }

    public BigDecimal getTeamMoney() {
        return teamMoney;
    }

    public void setTeamMoney(BigDecimal teamMoney) {
        this.teamMoney = teamMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}