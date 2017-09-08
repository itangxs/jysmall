package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.UserRefund;

/**
 * 
 * @author jxp 退款
 */
public interface RefundService {

	/**
	 * 用户退款详情
	 * 
	 * @param userId
	 *            用户编号
	 * @param detailId
	 *            详单编号
	 * @throws Exception
	 * @return RefundVo
	 */
	public UserRefund getRefundByuidAid(Long userId, Long detailId) throws Exception;

}
