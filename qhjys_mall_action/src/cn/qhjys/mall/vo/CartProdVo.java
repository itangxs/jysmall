package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

@SuppressWarnings("serial")
public class CartProdVo extends BaseVo {
	// 会员编号
	private Long userId;
	// 商品编号
	private Long prodId;
	// 商品名称
	private String prodName;
	// 商品短说明
	private String prodShort;
	// 商品主图片
	private String prodImg;
	// 商品类型
	private Integer prodType;
	// 商品数量
	private Integer prodNum;
	// 最大购买量
	private Integer maxPay;
	// 库存量
	private Integer prodStock;
	// 金额
	private BigDecimal prodPrice;
	
	private BigDecimal unitPrice;
	// 商家编号
	private Long storeId;
	
	private Date createTime;
	
	private String storeName;
	
	private String storeImg;
	
	private Long stoId;

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

	public String getProdShort() {
		return prodShort;
	}

	public void setProdShort(String prodShort) {
		this.prodShort = prodShort;
	}

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	public Integer getProdNum() {
		return prodNum;
	}

	public void setProdNum(Integer prodNum) {
		this.prodNum = prodNum;
	}

	public Integer getMaxPay() {
		return maxPay;
	}

	public void setMaxPay(Integer maxPay) {
		this.maxPay = maxPay;
	}

	public Integer getProdStock() {
		return prodStock;
	}

	public void setProdStock(Integer prodStock) {
		this.prodStock = prodStock;
	}

	public BigDecimal getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreImg() {
		return storeImg;
	}

	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}

	public Long getStoId() {
		return stoId;
	}

	public void setStoId(Long stoId) {
		this.stoId = stoId;
	}

}