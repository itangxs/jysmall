package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.common.BaseVo;
import cn.qhjys.mall.util.ConstantsConfigurer;

/**
 * 前台用户收藏 Vo
 * 
 * @author jxp
 */
@SuppressWarnings("serial")
public class CollectVo extends BaseVo {
	private Long id;
	// 前台用户ID
	private Long userId;
	// 店铺编号
	private Long storeId;
	// 商家名称
	private String storeName;
	// 商家图片
	private String storeImg;
	// 商品编号
	private Long prodId;
	// 商品流水号
	private String prodNo;
	// 商品名称
	private String prodName;
	// 商品短说明
	private String title;
	// 商品类别
	private Long category;
	// 商品图片
	private String prodImg;
	// 商品价格
	private BigDecimal price;
	// 商品原价
	private BigDecimal dePrice;
	// 商品销量
	private Integer saleNum;
	// 区域编号
	private Long areaId;
	// 区域名称
	private String areaName;
	// 收藏的标签
	private String markTag;
	// 收藏日期
	private Date markDate;
	// 收藏说明
	private String markRemark;
	// 兑换比例
	private BigDecimal scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getStoreImg() {
		return storeImg;
	}

	public void setStoreImg(String storeImg) {
		this.storeImg = storeImg;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
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

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public String getMarkTag() {
		return markTag;
	}

	public void setMarkTag(String markTag) {
		this.markTag = markTag;
	}

	public Date getMarkDate() {
		return markDate;
	}

	public void setMarkDate(Date markDate) {
		this.markDate = markDate;
	}

	public String getMarkRemark() {
		return markRemark;
	}

	public void setMarkRemark(String markRemark) {
		this.markRemark = markRemark;
	}

	public BigDecimal getScale() {
		this.scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));
		return this.scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));
	}
}