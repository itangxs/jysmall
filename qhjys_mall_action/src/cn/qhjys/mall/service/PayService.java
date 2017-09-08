package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.CashSaveWithdraw;

public interface PayService {

	/**
	 * 连连支付商家充值成功回调
	 * 
	 * @param reqStr
	 *            连连支付返回的字符串
	 * @param orderNum
	 *            订单编号
	 * @param cashSaveWithdraw
	 * @return
	 */
	public String saveSellerPayLL(String reqStr, String cardNo, String orderNum, CashSaveWithdraw cashSaveWithdraw)
			throws Exception;

	/**
	 * 连连支付用户充值成功回调
	 * 
	 * @param reqStr
	 *            连连支付返回的字符串
	 * @param orderNum
	 *            订单编号
	 * @param cardNo
	 *            银行卡号
	 * @param cashSaveWithdraw
	 * @return
	 */
	public String saveUserPayLL(String reqStr, String orderNum, String cardNo, CashSaveWithdraw cashSaveWithdraw)
			throws Exception;

}
