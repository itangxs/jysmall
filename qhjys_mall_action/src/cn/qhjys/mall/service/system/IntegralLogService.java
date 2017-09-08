package cn.qhjys.mall.service.system;

import cn.qhjys.mall.entity.IntegralLog;
import com.github.pagehelper.Page;

public interface IntegralLogService {

	/**
	 * 分页查询积分操作记录
	 * 
	 * @param userId
	 *            用户编号
	 * @param type
	 *            操作类型
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	Page<IntegralLog> queryPage(Long userId, String type, String start, String end, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 分页查询积分操作记录
	 * 
	 * @param userId
	 *            用户编号
	 * @param type
	 *            操作类型
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	Page<IntegralLog> queryPageBySellerExchange(Long sellerId, String type, String start, String end, Integer pageNum,
			Integer pageSize) throws Exception;
}