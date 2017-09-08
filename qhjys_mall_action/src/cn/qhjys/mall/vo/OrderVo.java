package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import cn.qhjys.mall.common.BaseVo;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.DeliveryAddr;

/**
 * 前台 订单(我的订单)
 * 
 * @author Administrator
 */
@SuppressWarnings("serial")
public class OrderVo extends BaseVo {
	// 记录编号
	private Long orderId;
	// 订单编号
	private String orderNo;
	// 用户编号
	private Long userId;
	// 订单总积分
	private BigDecimal integral;
	// 订单总价
	private BigDecimal totamt;
	// 快递费用
	private BigDecimal expFare;
	// 快递编号
	private Long delivId;
	// 支付方式
	private Long payId;
	// 下单时间
	private Date orderDate;
	// 配送日期
	private Date delivDate;
	// 订单状态
	private int status;
	// 送货地址
	private DeliveryAddr addr;
	// 优惠卷编号
	private Long couponId;
	// 支付记录
	private CashLog cashLog;
	// 详细订单
	private List<OrderDetailVo> list;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getTotamt() {
		return totamt;
	}

	public void setTotamt(BigDecimal totamt) {
		this.totamt = totamt;
	}

	public BigDecimal getExpFare() {
		return expFare;
	}

	public void setExpFare(BigDecimal expFare) {
		this.expFare = expFare;
	}

	public Long getDelivId() {
		return delivId;
	}

	public void setDelivId(Long delivId) {
		this.delivId = delivId;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDelivDate() {
		return delivDate;
	}

	public void setDelivDate(Date delivDate) {
		this.delivDate = delivDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public DeliveryAddr getAddr() {
		return addr;
	}

	public void setAddr(DeliveryAddr addr) {
		this.addr = addr;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public CashLog getCashLog() {
		return cashLog;
	}

	public void setCashLog(CashLog cashLog) {
		this.cashLog = cashLog;
	}

	public List<OrderDetailVo> getList() {
		return list;
	}

	public void setList(List<OrderDetailVo> list) {
		this.list = list;
	}
}