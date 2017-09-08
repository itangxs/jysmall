package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;

/***
 * 商品vo
 * 
 * @author zengrong
 *
 */
public class ProductVo  extends BaseVo{
	
	private Long productId;// 商品编号
	private String productName;// 商品名称
	private Long storeId;// 店铺编号
	private Long sellerId;// 商家编号
	private Long categoryId;// 商品分类编号
	private String categoryName;// 商品分类
	private Integer prodType;// 商品类型：1 虚拟，2 实物
	private String shortDetail;// 商品简短说明
	private String images;// 商品图片
	private String prodDetail;// 商品详情
	private String buyingTips;// 购买提示
	private String sellerDetail;// 商家介绍
	private String keepDate;// 商品有效期
	private double unitPrice;// 商品单价
	private double origPrice;// 商品原价
	private Long prodStock;// 商品库存数量
	private Integer maxPay;// 限购量
	private Long activityId;// 活动编号
	private Integer salesNum;// 商品销量
	private Integer scoreAvg;// 平均评分
	private Integer markNum;// 收藏数量
	private String createTime;// 商品添加时间
	private Integer status;// 商品状态

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	public String getShortDetail() {
		return shortDetail;
	}

	public void setShortDetail(String shortDetail) {
		this.shortDetail = shortDetail;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getProdDetail() {
		return prodDetail;
	}

	public void setProdDetail(String prodDetail) {
		this.prodDetail = prodDetail;
	}

	public String getBuyingTips() {
		return buyingTips;
	}

	public void setBuyingTips(String buyingTips) {
		this.buyingTips = buyingTips;
	}

	public String getSellerDetail() {
		return sellerDetail;
	}

	public void setSellerDetail(String sellerDetail) {
		this.sellerDetail = sellerDetail;
	}

	public String getKeepDate() {
		return keepDate;
	}

	public void setKeepDate(String keepDate) {
		this.keepDate = keepDate;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(double origPrice) {
		this.origPrice = origPrice;
	}

	public Long getProdStock() {
		return prodStock;
	}

	public void setProdStock(Long prodStock) {
		this.prodStock = prodStock;
	}

	public Integer getMaxPay() {
		return maxPay;
	}

	public void setMaxPay(Integer maxPay) {
		this.maxPay = maxPay;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getScoreAvg() {
		return scoreAvg;
	}

	public void setScoreAvg(Integer scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	public Integer getMarkNum() {
		return markNum;
	}

	public void setMarkNum(Integer markNum) {
		this.markNum = markNum;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
