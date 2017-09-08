package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import cn.qhjys.mall.common.BaseVo;

@SuppressWarnings("serial")
public class ExpiredOrderVo extends BaseVo {
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
	// 商品编号
	private Long prodId;
	// 商家编号
	private Long sellerId;
	// 商品单价
	private BigDecimal unitPric;
	// 未使用消费卷数量
	private Integer quantity;
	// 订单总价
	private BigDecimal totalPric;

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

	public BigDecimal getUnitPric() {
		return unitPric;
	}

	public void setUnitPric(BigDecimal unitPric) {
		this.unitPric = unitPric;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPric() {
		return totalPric;
	}

	public void setTotalPric(BigDecimal totalPric) {
		this.totalPric = totalPric;
	}
}