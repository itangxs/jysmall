package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import cn.qhjys.mall.common.BaseVo;
import cn.qhjys.mall.util.ConstantsConfigurer;

/***
 * 
 * @author LiXiang
 *
 */
@SuppressWarnings("serial")
public class AppProdVo extends BaseVo {
	// 用户编号
	private Long userId;
	// 商品编号
	private Long prodId;
	// 商品名称
	private String prodName;
	// 商品图片
	private String imageUrl;
	// 商品说明
	private String title;
	// 商品类别
	private Long category;
	// 商品类型
	private Integer prodType;
	// 商品最大
	private BigDecimal unitPrice;
	// 商品原价
	private BigDecimal dePrice;
	// 已售数量
	private Integer saleNum;
	// 平均评分
	private Float avgScore;
	// 所在区编号
	private Long areaId;
	// 所在区名称
	private String areaName;
	// 店铺编号
	private Long storeId;
	// 店铺名称
	private String storeName;
	// 商家编号
	private Long sellerId;
	// 兑换比例
	private BigDecimal scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getDePrice() {
		return dePrice;
	}

	public void setDePrice(BigDecimal dePrice) {
		this.dePrice = dePrice;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public BigDecimal getScale() {
		this.scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));
		return this.scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));
	}
}