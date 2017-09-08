package cn.qhjys.mall.service.system;

import java.math.BigDecimal;
import java.util.Date;

import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqStoreLoan;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.FqStoreRepayment;
import cn.qhjys.mall.vo.system.CashLogVo;
import cn.qhjys.mall.vo.system.FqCommissionRoleVo;
import cn.qhjys.mall.vo.system.FqStoreRateVo;
import cn.qhjys.mall.vo.system.StoreLoanVo;
import cn.qhjys.mall.vo.system.WithdrawsVo;

import com.github.pagehelper.Page;

public interface FinanceService {

	/***
	 * 用户资金日志列表
	 * 
	 * @param userId
	 *            用户编号
	 * @param payType
	 *            支付类型
	 * @param userName
	 *            用户名
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 */
	public Page<CashLogVo> queryUserAccountRecordPage(Long userId, Integer payType, String userName, String beginTime,
			String endTime, Integer pageNum, Integer pageSize) throws Exception;

	/***
	 * 商家资金日志列表
	 * 
	 * @param payType
	 *            支付类型
	 * @param sellerName
	 *            商家名
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 */
	public Page<CashLogVo> querySellerAccountRecordPage(Integer payType, String sellerName, String beginTime,
			String endTime, Integer pageNum, Integer pageSize) throws Exception;

	/***
	 * 平台充值
	 * 
	 * @param operType
	 *            操作类型：0充值，1提现
	 * @param payType
	 *            支付类型：1会员充值、2会员提现、3卖家充值、4卖家提现、5购物、6退款
	 * @param payWay
	 *            支付方式：1连连支付；2网银支付；3支付宝支付；4平台支付
	 * @param admin
	 *            操作用户
	 * @param userId
	 *            用户编号
	 * @param money
	 *            充值金额
	 * @return
	 * @throws Exception
	 */
	boolean insertRecharge(int operType, int payType, int payWay, Long admin, Long userId, BigDecimal money,
			String orderNum) throws Exception;

	/***
	 * 用户提现审核列表
	 * 
	 * @param userName
	 *            用户名
	 * @param status
	 *            状态 0不成功，1成功,2未处理
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 */
	Page<WithdrawsVo> queryUserWithdrawsRecord(String userName, Integer status, String beginTime, String endTime,
			Integer pageNum, Integer pageSize) throws Exception;

	/***
	 * 商家提现审核列表
	 * 
	 * @param sellerName
	 *            商家名
	 * @param status
	 *            状态 0不成功，1成功,2未处理
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 */
	Page<WithdrawsVo> querySellerWithdrawsRecord(String sellerName, Integer status, String beginTime, String endTime,
			Integer pageNum, Integer pageSize) throws Exception;
	/**
	 * 查询当前数据
	 * @return
	 * @throws Exception
	 */
	StoreLoanVo queryStoreLoanVoCurrent() throws Exception;
	/**
	 * 查询历史数据
	 * @return
	 * @throws Exception
	 */
	StoreLoanVo queryStoreLoanVoHistory() throws Exception;
	
	/**
	 * 查询项目列表
	 * @return
	 * @throws Exception
	 */
	Page<FqStoreLoan> queryStoreLoan(Integer pageNum, Integer pageSize)throws Exception;
	
	/**
	 * 查询项目详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	FqStoreLoan queryStoreLoanList(Long id)throws Exception;
	/**
	 * 更改借贷信息
	 * @param fqStoreLoan
	 * @return
	 * @throws Exception
	 */
	int updateStoreLoan(FqStoreLoan loan)throws Exception;
	/**
	 * 删除项目
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteStoreLoan(Long id,Integer status)throws Exception;
	/**
	 * 查询还款列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page<FqStoreRepayment> queryStoreRepayment(Long id,Integer pageNum,Integer pageSize)throws Exception;
	
	
	FqStoreRepayment queryStoreRepaymentById(Long id)throws Exception;
	/**
	 * 新增还款
	 * @param fqStoreRepayment
	 * @return
	 * @throws Exception
	 */
	int insertRepayment(FqStoreRepayment fqStoreRepayment,Long id)throws Exception;
	/**
	 * 点击取消更改状态
	 * @param repayment
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateStatus(FqStoreRepayment repayment,Long id)throws Exception;
	
	int updateSellerRepayment();
	/**
	 * 新增汇率
	 * @param rateName
	 * 			项目名称
	 * @param adminId
	 * 			管理员id
	 * @param adminUsername
	 * 			管理员账号
	 * @param wechatBaseRate
	 * 			微信支付基础汇率
	 * @param wechatAppendRate
	 * 			微信支付附加汇率
	 * @param wechatAppendMoney
	 * 			微信支付附加汇率起始值
	 * @param alipayBaseRate
	 * 			支付宝支付基础汇率
	 * @param alipayAppendRate
	 * 			支付宝支付附加汇率
	 * @param alipayAppendMoney
	 * 			支付宝支付附加汇率起始值
	 * @param qqpayBaseRate
	 * 			qq支付基础汇率
	 * @param qqpayAppendRate
	 * 			qq支付附加汇率
	 * @param qqpayAppendMoney
	 * 			qq支付附加汇率起始值
	 * @param createTime
	 * 			创建时间
	 * @return
	 * @throws Exception
	 */
	int insertFqStoreRate(String rateName,Long adminId,String adminUsername,BigDecimal wechatBaseRate,
			BigDecimal wechatAppendRate,BigDecimal wechatAppendMoney,BigDecimal alipayBaseRate,BigDecimal alipayAppendRate,
			BigDecimal alipayAppendMoney,BigDecimal qqpayBaseRate,BigDecimal qqpayAppendRate,BigDecimal qqpayAppendMoney)throws Exception;
	/**
	 * 查询汇率项目列表
	 * @param startTime
	 * 			开始时间
	 * @param endTime
	 * 			结束时间
	 * @param rateName
	 * 			项目名称
	 * @param pageSize
	 * 			
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	Page<FqStoreRateVo> queryFqStoreRateVo(String startTime,String endTime,String rateName,Integer pageSize,Integer pageNum)throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteFqStoreRate(Long id)throws Exception;
	/**
	 * 修改
	 * @param rate
	 * @return
	 * @throws Exception
	 */
	int updateFqStoreRate(FqStoreRate rate)throws Exception;
	
	FqStoreRate queryFqStoreRate(Long ids)throws Exception;
	
	Page<FqCommissionRoleVo> royaltyIncentiveStrategy(String roleName,String admin,Date beginTime,Date endTime, Integer pageNum, Integer pageSize)throws Exception;
	
	int addFqCommissionRole(String roleName, Long adminId, Double teamProportion, Double clerkProportion)throws Exception;
	
	FqCommissionRole fqCommissionRoleDetail(Long id)throws Exception;
	
	int updateFqCommissionRole(Long id, Double teamProportion, Double clerkProportion)throws Exception;
	
	int deleteFqCommissionRole(Long id)throws Exception;
}
