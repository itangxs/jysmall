package cn.qhjys.mall.service.fq;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqSellerStatement;

public interface FqSellerStatementService {

	Page<FqSellerStatement> querySellerStatementBySellerId(Long sellerId,Integer pageNum,Integer pageSize) throws Exception;
	
	int insertWithdraw(Long sellerId,String money) throws Exception;
	
//	boolean updateSellerStatementByDate(Date date) throws Exception;
//	
//	boolean addTotalMoneyToCashAccount(Date date) throws Exception;
	
	/**
	 * 定时器调用，用于更新结算状态，追加账户余额，修改是否自动提现。
	 * @return
	 * @throws Exception
	 */
	boolean updateSellerStatementAndAutoWithdrawByQuartz() throws Exception;
	
	boolean updateSellerStatementByQuartz() throws Exception;
	
	List<FqSellerStatement> querySellerStatementByDate(Date date) throws Exception;
	
	List<FqSellerStatement> querySellerStatementByCreateDate(Date startDate,Date endDate) throws Exception;
	
	List<FqSellerStatement> querySellerStatementByPeriodDate(Date date) throws Exception;
	
	List<FqSellerStatement>querySellerStatementBySellerId(Long sellerId) throws Exception;
	
	FqSellerStatement getFqSellerStatement(Long sellerId,Date date);
	
	boolean updateSellerStatementHasPeriod();
	
	boolean updateMsSellerWithdraw(Long sellerId,Date startDate,Date endDate) throws Exception;
	
	/**
	 * 定时器调用，用于更新民生结算状态
	 * @return
	 * @throws Exception
	 */
	boolean updateMsSellerStatementQuartz() throws Exception;
	
	/**
	 * 定时器调用，用于验证民生结算数据
	 * @return
	 * @throws Exception
	 */
	boolean updateVeriMsSellerStatementQuartz() throws Exception;
	
	
}
