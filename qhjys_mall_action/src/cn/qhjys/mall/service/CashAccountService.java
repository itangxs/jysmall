package cn.qhjys.mall.service;

import java.math.BigDecimal;

import cn.qhjys.mall.entity.CashAccount;

public interface CashAccountService {

	/**
	 * 生成用户或者商家的资金账号
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param userId
	 *            用户ID
	 * @param password
	 *            支付密码
	 * @return
	 * @throws Exception
	 */
	public int saveCashAccount(Long sellerId, Long userId, String password) throws Exception;

	/***
	 * 查询账号余额
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param userId
	 *            会员编号
	 * @return
	 */
	public CashAccount queryCashAccount(Long sellerId, Long userId) throws Exception;

	/**
	 * 更新账户资金
	 * 
	 * @param account
	 * @return
	 */
	public boolean updateCashAccount(CashAccount account) throws Exception;
	
	public int updateBalanceToIntegral(Long sellerId,BigDecimal money) throws Exception;
	
	public int updateIntegralByRecharge(Long storeId,BigDecimal money,String remark) throws Exception;
}
