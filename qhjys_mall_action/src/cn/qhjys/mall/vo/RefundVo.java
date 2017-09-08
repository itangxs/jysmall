package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

/**
 * 
 * @author jxp 前台退款Vo
 */
public class RefundVo extends BaseVo {
	private Long id;
	// 用户ID
	private Long userId;
	// 用户名字
	private String name;
	// 订单ID
	private Long orderId;
	// 订单编号
	private String orderNo;
	// 商品图片
	private String commodityImgUrl;
	// 数量
	private int count;
	// 总价
	private BigDecimal price;
	// 结束时间
	private Date endDate;
	// 退款状态
	private int status;
	// 退款原因
	private String refundReason;
	// 举例图片
	private String images;
	// 平台意见
	private String platformAnswer;
	// 提交时间
	private Date startDate;
	// 订单状态
	private int orderStatus;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getCommodityImgUrl() {
		return commodityImgUrl;
	}

	public void setCommodityImgUrl(String commodityImgUrl) {
		this.commodityImgUrl = commodityImgUrl;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getPlatformAnswer() {
		return platformAnswer;
	}

	public void setPlatformAnswer(String platformAnswer) {
		this.platformAnswer = platformAnswer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

}