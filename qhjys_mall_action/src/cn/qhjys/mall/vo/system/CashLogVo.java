package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 
 * @author zengrong
 *
 */
public class CashLogVo {

	private Long id;
	//用户名
	private String userName;
	//商家名
	private String sellerName;
	//操作时间
	private Date createDate;
	//操作金额
	private BigDecimal amount;
	//支付类型：1会员充值、2会员提现、3卖家充值、4卖家提现、5购物、6退款
	private Integer payType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
}
