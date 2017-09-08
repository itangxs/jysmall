package cn.qhjys.mall.vo;

import java.math.BigDecimal;

public class OrderCountVo {
	private Integer storeNum;
	private Integer userNum;
	private Integer orderNum;
	private BigDecimal payTotal;
	private BigDecimal rateTotal;
	private BigDecimal totamtTotal;
	public Integer getStoreNum() {
		return storeNum;
	}
	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public BigDecimal getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(BigDecimal payTotal) {
		this.payTotal = payTotal;
	}
	public BigDecimal getRateTotal() {
		return rateTotal;
	}
	public void setRateTotal(BigDecimal rateTotal) {
		this.rateTotal = rateTotal;
	}
	public BigDecimal getTotamtTotal() {
		return totamtTotal;
	}
	public void setTotamtTotal(BigDecimal totamtTotal) {
		this.totamtTotal = totamtTotal;
	}
	public OrderCountVo(Integer storeNum, Integer userNum, Integer orderNum,
			BigDecimal payTotal, BigDecimal rateTotal, BigDecimal totamtTotal) {
		super();
		this.storeNum = storeNum;
		this.userNum = userNum;
		this.orderNum = orderNum;
		this.payTotal = payTotal;
		this.rateTotal = rateTotal;
		this.totamtTotal = totamtTotal;
	}
	public OrderCountVo() {
		super();
	}
	
}
