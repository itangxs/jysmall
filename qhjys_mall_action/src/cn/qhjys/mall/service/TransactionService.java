package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.CashLog;
import com.github.pagehelper.Page;

/**
 * 交易管理
 * 
 * @author LiXiang
 *
 */
public interface TransactionService {

	/**
	 * 查询交易记录
	 * 
	 * @param record
	 *            充值单号
	 * @param userid
	 *            充值用户
	 * @param status
	 *            充值状态
	 * @param begin
	 *            开始时间
	 * @param ending
	 *            结束时间
	 * @param type
	 *            交易类型
	 * @param page
	 *            分页条件
	 * @return
	 */
	public Page<CashLog> queryTransactionList(String record, long userid, int status, long begin, long ending,
			int type, Page<CashLog> page) throws Exception;
}