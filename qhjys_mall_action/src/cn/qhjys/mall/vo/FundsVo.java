package cn.qhjys.mall.vo;

import java.math.BigDecimal;

import cn.qhjys.mall.common.BaseVo;

/**
 * 
 * @author Administrator
 *  用户资金 Vo
 */
public class FundsVo  extends BaseVo{

	private Long id;
	
	//用户ID
	private Long userId;
	
	//余额
	private BigDecimal balance;

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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	
}
