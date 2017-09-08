package cn.qhjys.mall.service;

import java.math.BigDecimal;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.util.OrderResult;

public interface PaymentService {

	/**
	 * 订单支付后，资金更新
	 * 
	 * @param userId
	 *            购买用户
	 * @param order
	 *            支付订单
	 * @param result
	 *            操作结果
	 * @return
	 * @throws Exception
	 */
	boolean orderPaymentByUser(Long userId, OrderInfo order, OrderResult result) throws Exception;

	/**
	 * 订单消费(收货)后，资金更新
	 * 
	 * @param userId
	 *            用户编号
	 * @param seller
	 *            商家编号
	 * @param detaNo
	 *            订单流水
	 * @param totamt
	 *            消费金额
	 * @return
	 * @throws Exception
	 */
	boolean orderSuccessBySeller(Long userId, Long seller, String detaNo, BigDecimal totamt) throws Exception;

	/**
	 * 订单退款后，资金更新
	 * 
	 * @param userId
	 *            用户编号
	 * @param detaNo
	 *            订单流水
	 * @param totamt
	 *            退款金额
	 * @return
	 * @throws Exception
	 */
	boolean orderRefundByUser(Long userId, String detaNo, BigDecimal totamt) throws Exception;

	/**
	 * 兑换积分后，资金更新
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param exchangeintegral
	 *            兑换积分
	 * @return
	 * @throws Exception
	 */
	boolean updateExchangeBySeller(Long sellerId, BigDecimal exchangeIntegral) throws Exception;

	public boolean addUserBalanceTrade(Long userId, Integer type, BigDecimal integral) throws Exception;
	
	boolean updateExchangeByRecomId(Long recomId, BigDecimal exchangeIntegral) throws Exception;
}