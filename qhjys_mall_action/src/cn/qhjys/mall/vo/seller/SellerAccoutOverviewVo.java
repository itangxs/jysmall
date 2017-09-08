package cn.qhjys.mall.vo.seller;

import java.math.BigDecimal;
import java.util.Date;

/**
 *卖家(商家)  账号总览VO
 * @author JiangXiaoPing
 *
 */
public class SellerAccoutOverviewVo {
	
	//商家ID
	private Long id; 
	//总金额
	private BigDecimal totalMoney;
	//日期
	private Date date;
	//提现前预存款（元）	
	private BigDecimal sendBefor;
	//提现后预存款（元）	
	private BigDecimal sendAfter;
	//变更金额（元）
	private BigDecimal changeMoney;
	//充值前预存款（元）
	private BigDecimal reviewBefor;
	//充值后预存款（元）
	private BigDecimal reviewAfter;
	//备注
	private String remarks;
	//支付类型
	private int payType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getSendBefor() {
		return sendBefor;
	}
	public void setSendBefor(BigDecimal sendBefor) {
		this.sendBefor = sendBefor;
	}
	public BigDecimal getSendAfter() {
		return sendAfter;
	}
	public void setSendAfter(BigDecimal sendAfter) {
		this.sendAfter = sendAfter;
	}
	public BigDecimal getChangeMoney() {
		return changeMoney;
	}
	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}
	public BigDecimal getReviewBefor() {
		return reviewBefor;
	}
	public void setReviewBefor(BigDecimal reviewBefor) {
		this.reviewBefor = reviewBefor;
	}
	public BigDecimal getReviewAfter() {
		return reviewAfter;
	}
	public void setReviewAfter(BigDecimal reviewAfter) {
		this.reviewAfter = reviewAfter;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
}
