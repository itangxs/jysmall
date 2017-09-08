package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

public class ChongzhiVo extends BaseVo{
	//充值记录的ID
	private Long id;
	//充值单号
	private String orderId;
	//会员ID
	private Long userId;
	//会员名
	private String name;
	//会员资金账户ID
	private Long accountId;
	//操作日期
	private Date createDate;
	//当前预存款
	private BigDecimal money;
	//金额
	private BigDecimal amount;
	//充值方式
	private int chongzhifangshi;
	//状态
	private int status;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public int getChongzhifangshi() {
		return chongzhifangshi;
	}
	public void setChongzhifangshi(int chongzhifangshi) {
		this.chongzhifangshi = chongzhifangshi;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	

}
