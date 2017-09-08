package cn.qhjys.mall.vo;

import java.math.BigInteger;
import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

/**
 * 商家(店铺) VO
 * 
 * @author JiangXiaoPing
 */
@SuppressWarnings("serial")
public class StoreVo extends BaseVo {
	private Long rowNum;
	
	private Long id;
	// 卖家名称
	private String sellerName;
	// 店铺名称
	private String name;
	
	private Integer invite;
	
	private Integer status;
	
	private Integer scope;
	
	private Integer openCashier;
	
	private Integer openOrder;
	
	// 创建时间
	private Date createDate;
	
	//业务员名称
	private String clerkName;
	
	// 店铺LOGO
	private String logo;
	// 卖家ID
	private Long sellerId;
	
	// 联系人名字
	private String contactName;
	// 联系人 联系方式 电话
	private String contactTel;
	// 联系人 联系方式 手机
	private String contactPhone;
	// 关联的银行卡ID
	private Long bankId;
	// 所在的省
	private String province;
	// 所在的城市
	private String city;
	// 所在的区域
	private String areaName;
	// 详情地址
	private String address;
	// 经度
	private Double lngtd;
	// 纬度
	private Double lattd;
	// 图片
	private String imageUrl;
	// 店铺标签
	private String labels;
	// 经营类别
	private Long categoryId;
	// 类别详情
	private String categoryDetails;
	// 店铺详情
	private String storeDetail;

	// 评价次数
	private BigInteger countNum;
	// 平均评分
	private Float avgScore;
	// 总销量
	private BigInteger salesNum;
	// 是否启用
	private Integer enabled;
	
	private String rateName;
	private Long rateId;

	private Integer statementPeriod;
	//业务员id
	private Long clerkId;
	
	private Integer chnnelState;
	
	private Integer payChannel;
	
	private Integer wxAuthState;
	
	private Integer zfbAuthState;
	
	private Integer channelValidation;
	
	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

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
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLngtd() {
		return lngtd;
	}

	public void setLngtd(Double lngtd) {
		this.lngtd = lngtd;
	}

	public Double getLattd() {
		return lattd;
	}

	public void setLattd(Double lattd) {
		this.lattd = lattd;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
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
		this.categoryDetails = categoryDetails;
	}

	public String getStoreDetail() {
		return storeDetail;
	}

	public void setStoreDetail(String storeDetail) {
		this.storeDetail = storeDetail;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public BigInteger getCountNum() {
		return countNum;
	}

	public void setCountNum(BigInteger countNum) {
		this.countNum = countNum;
	}

	public Float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	public BigInteger getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(BigInteger salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getInvite() {
		return invite;
	}

	public void setInvite(Integer invite) {
		this.invite = invite;
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

	public String getRateName() {
		return rateName;
	}

	public void setRateId(String rateName) {
		this.rateName = rateName;
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

	public void setRateName(String rateName) {
		this.rateName = rateName;
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
		this.clerkName = clerkName;
	}

	public Integer getChnnelState() {
		return chnnelState;
	}

	public void setChnnelState(Integer chnnelState) {
		this.chnnelState = chnnelState;
	}

	public Integer getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
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

	public Integer getChannelValidation() {
		return channelValidation;
	}

	public void setChannelValidation(Integer channelValidation) {
		this.channelValidation = channelValidation;
	}

	public Long getRowNum() {
		return rowNum;
	}

	public void setRowNum(Long rowNum) {
		this.rowNum = rowNum;
	}
	
}