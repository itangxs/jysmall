package cn.qhjys.mall.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.CashSaveWithdraw;

public interface CashSaveWithdrawService {

	/**
	 * 获取充值(提现)记录
	 * 
	 * @param id
	 *            资金帐号编号
	 * @param operType
	 *            交易类型(0充值 1提现)
	 * @param orderNum
	 *            支付流水号
	 * @return
	 */
	CashSaveWithdraw getCashSaveWithdraw(Long id, Integer operType, String orderNum) throws Exception;

	/**
	 * 修改充值(提现)记录
	 * 
	 * @param cash
	 *            充值(提现)记录
	 * @param bankNo
	 *            银行卡号
	 * @param userType
	 *            账户类型(0 会员 1 商家)
	 * @param payType
	 *            支付方式(1在线支付；2网银支付；3支付宝支付；4平台支付)
	 * @return
	 */
	boolean updateCashSaveWithdraw(CashSaveWithdraw cash, String bankNo, Integer userType, Integer payType)
			throws Exception;

	/**
	 * 添加充值(提现)记录
	 * 
	 * @param cash
	 *            充值(提现)记录
	 * @param userType
	 *            账户类型(0 会员 1 商家)
	 * @return
	 */
	boolean insertCashSaveWithdraw(CashSaveWithdraw cash) throws Exception;

	/***
	 * 根据主键id查询充值(提现)记录
	 * 
	 * @param id
	 *            主键id
	 * @return
	 */
	CashSaveWithdraw queryCashSaveWithdrawById(Long id) throws Exception;

	
	List<CashSaveWithdraw> queryCashSaveWithdrawBySellerId(Long sellerId) throws Exception;
	Page<CashSaveWithdraw> queryCashSaveWithdrawPageBySellerId(Long sellerId,Integer pageNum,Integer pageSize);
}