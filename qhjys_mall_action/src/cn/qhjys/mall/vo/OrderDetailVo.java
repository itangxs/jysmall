package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import cn.qhjys.mall.common.BaseVo;
import cn.qhjys.mall.entity.VolumeInfo;

/**
 * 前台订单详细VO
 * 
 * @author JiangXiaoPing
 * 
 */
@SuppressWarnings("serial")
public class OrderDetailVo extends BaseVo {
	// 订单编号
	private Long orderId;
	// 订单流水号
	private String orderNo;
	// 详单编号
	private Long detailId;
	// 详单流水号
	private String detailNo;
	// 用户编号
	private Long userId;
	// 用户帐号
	private String userName;
	// 商品编号
	private Long prodId;
	// 商家编号
	private Long sellerId;
	// 店铺编号
	private Long storeId;
	// 店铺名称
	private String storeName;
	// 商品名称
	private String prodName;
	// 商品图片
	private String prodImage;
	// 商品类型
	private Integer prodType;
	// 过期时间
	private Date prodEndDate;
	// 商品折扣价
	private BigDecimal unitPrice;
	// 商品支付价
	private BigDecimal payPric;
	// 商品数量
	private Integer quantity;
	// 商品总积分
	private BigDecimal integral;
	// 商品总价
	private BigDecimal moneny;
	// 物流单号
	private Long expressId;
	// 快递供应商（中通，EMS等）
	private String express;
	// 退款申请时间
	private Date process;
	// 退款时间
	private Date retime;
	// 订单状态
	private Integer status;
	// 预约状态
	private Integer reStatus;
	// 下单时间
	private Date orderDate;
	// 消费卷
	private List<VolumeInfo> volumes;
	
	private String stoName;
	
	private Long stoId;
	
	private String stoImg;
	
	private Long category;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public String getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdImage() {
		return prodImage;
	}

	public void setProdImage(String prodImage) {
		this.prodImage = prodImage;
	}

	public Integer getProdType() {
		return prodType;
	}

	public void setProdType(Integer prodType) {
		this.prodType = prodType;
	}

	public Date getProdEndDate() {
		return prodEndDate;
	}

	public void setProdEndDate(Date prodEndDate) {
		this.prodEndDate = prodEndDate;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getPayPric() {
		return payPric;
	}

	public void setPayPric(BigDecimal payPric) {
		this.payPric = payPric;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getMoneny() {
		return moneny;
	}

	public void setMoneny(BigDecimal moneny) {
		this.moneny = moneny;
	}

	public Long getExpressId() {
		return expressId;
	}

	public void setExpressId(Long expressId) {
		this.expressId = expressId;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public Date getProcess() {
		return process;
	}

	public void setProcess(Date process) {
		this.process = process;
	}

	public Date getRetime() {
		return retime;
	}

	public void setRetime(Date retime) {
		this.retime = retime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReStatus() {
		return reStatus;
	}

	public void setReStatus(Integer reStatus) {
		this.reStatus = reStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public List<VolumeInfo> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<VolumeInfo> volumes) {
		this.volumes = volumes;
	}

	public String getStoName() {
		return stoName;
	}

	public void setStoName(String stoName) {
		this.stoName = stoName;
	}

	public Long getStoId() {
		return stoId;
	}

	public void setStoId(Long stoId) {
		this.stoId = stoId;
	}

	public String getStoImg() {
		return stoImg;
	}

	public void setStoImg(String stoImg) {
		this.stoImg = stoImg;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}
	
}