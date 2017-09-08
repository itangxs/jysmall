package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardDeliveryPropertyExample;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqCommissionRoleExample;
import cn.qhjys.mall.entity.FqCommissionRoleExample.Criteria;
import cn.qhjys.mall.entity.FqStoreLoan;
import cn.qhjys.mall.entity.FqStoreLoanExample;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.FqStoreRateExample;
import cn.qhjys.mall.entity.FqStoreRepayment;
import cn.qhjys.mall.entity.FqStoreRepaymentExample;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.CashSaveWithdrawMapper;
import cn.qhjys.mall.mapper.FqCommissionRoleMapper;
import cn.qhjys.mall.mapper.FqCommissionRoleVoMapper;
import cn.qhjys.mall.mapper.FqStoreLoanMapper;
import cn.qhjys.mall.mapper.FqStoreRateMapper;
import cn.qhjys.mall.mapper.FqStoreRepaymentMapper;
import cn.qhjys.mall.mapper.custom.ChongzhiManageMapper;
import cn.qhjys.mall.mapper.custom.FqStoreRateVoMapper;
import cn.qhjys.mall.mapper.custom.StoreLoanVoMapper;
import cn.qhjys.mall.service.system.FinanceService;
import cn.qhjys.mall.vo.system.CashLogVo;
import cn.qhjys.mall.vo.system.FqCommissionRoleVo;
import cn.qhjys.mall.vo.system.FqStoreRateVo;
import cn.qhjys.mall.vo.system.StoreLoanVo;
import cn.qhjys.mall.vo.system.WithdrawsVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class FinanceServiceImpl extends Base implements FinanceService {
	@Autowired
	private ChongzhiManageMapper chongzhiManageMapper;
	@Autowired
	private CashSaveWithdrawMapper cashSaveWithdrawMapper;
	@Autowired
	private CashLogMapper cashLogMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private FqStoreLoanMapper fqStoreLoanMapper;
	@Autowired
	private StoreLoanVoMapper storeLoanMapper;
	@Autowired
	private FqStoreRepaymentMapper fqStoreRepaymentMapper;
	@Autowired
	private FqStoreRateMapper fqStoreRateMapper;
	@Autowired
	private FqStoreRateVoMapper fqStoreRateVoMapper; 
	@Autowired
	private FqCommissionRoleMapper fqCommissionRoleMapper;
	@Autowired
	private FqCommissionRoleVoMapper fqCommissionRoleVoMapper;

	@Override
	public Page<CashLogVo> queryUserAccountRecordPage(Long userId,
			Integer payType, String userName, String beginTime, String endTime,
			Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("payType", payType);
		map.put("userName", userName);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return chongzhiManageMapper.queryUserAccountRecordPage(map);
	}

	@Override
	public Page<CashLogVo> querySellerAccountRecordPage(Integer payType,
			String sellerName, String beginTime, String endTime,
			Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payType", payType);
		map.put("sellerName", sellerName);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum, pageSize);
		return chongzhiManageMapper.querySellerAccountRecordPage(map);
	}

	@Override
	public boolean insertRecharge(int operType, int payType, int payWay,
			Long admin, Long userId, BigDecimal money, String orderNum)
			throws Exception {
		boolean result = false;
		CashSaveWithdraw cash = new CashSaveWithdraw();
		cash.setOperType(0);
		cash.setMoney(money);
		cash.setCashId(userId);
		cash.setOrderNum(orderNum);
		cash.setAdminId(admin);
		cash.setOperDate(new Date());
		cash.setCreateDate(new Date());
		cash.setStatus(1);
		// 添加交易
		int row = cashSaveWithdrawMapper.insertSelective(cash);
		if (row == 0) {
			throw new Exception("添加操作交易表异常");
		}
		CashAccount cashAccount = new CashAccount();
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(userId);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list.size() > 0)
			cashAccount = list.get(0);
		CashLog cashLog = new CashLog();
		cashLog.setRecordNo(orderNum);
		if (null != admin)
			// 平台账号支付
			cashLog.setSendId(admin);
		cashLog.setReviewId(userId);
		cashLog.setAmount(money);
		cashLog.setPayType(payType);
		cashLog.setPayWay(payWay);
		cashLog.setBusinessCode("001");
		cashLog.setBusinessName("充值");
		BigDecimal reviewBefor = cashAccount.getBalance();
		BigDecimal reviewAfter = cashAccount.getBalance().add(money);
		cashLog.setReviewBefor(reviewBefor);
		cashLog.setReviewAfter(reviewAfter);
		cashLog.setCreateTime(new Date());
		// 添加交易记录表
		int count = cashLogMapper.insertSelective(cashLog);
		if (count == 0)
			throw new Exception("添加现金交易记录表异常");
		// 修改账号余额
		cashAccount.setBalance(reviewAfter);
		int num = cashAccountMapper.updateByPrimaryKeySelective(cashAccount);
		if (num == 0)
			throw new Exception("修改账号余额异常");
		result = true;
		return result;
	}

	@Override
	public Page<WithdrawsVo> queryUserWithdrawsRecord(String userName,
			Integer status, String beginTime, String endTime, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("status", status);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum, pageSize);
		return chongzhiManageMapper.userWithdrawsRecord(map);
	}

	@Override
	public Page<WithdrawsVo> querySellerWithdrawsRecord(String sellerName,
			Integer status, String beginTime, String endTime, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeName", sellerName);
		map.put("status", status);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotEmpty(beginTime)) {
			Date start = format.parse(beginTime + " 00:00:00");
			map.put("beginTime", start);
		}
		if (StringUtils.isNotEmpty(endTime)) {
			Date end = format.parse(endTime + " 23:59:59");
			map.put("endTime", end);
		}
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		return chongzhiManageMapper.sellerWithdrawsRecord(map);
	}

	@Override
	public Page<FqStoreLoan> queryStoreLoan(Integer pageNum, Integer pageSize)
			throws Exception {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		
		FqStoreLoanExample example = new FqStoreLoanExample();
		example.setOrderByClause("create_time desc");
		Page<FqStoreLoan> page = (Page<FqStoreLoan>) fqStoreLoanMapper.selectByExample(example);
		
		return page;
	}

	@Override
	public FqStoreLoan queryStoreLoanList(Long id) throws Exception {
		if (id != null) {
			FqStoreLoan key = fqStoreLoanMapper.selectByPrimaryKey(id);
			return key;
		} else {
			return null;
		}
	}
	@Override
	public int updateStoreLoan(FqStoreLoan loan) throws Exception {
		
		return fqStoreLoanMapper.updateByPrimaryKeySelective(loan);
	}
	/**
	 * 查询当前项目数据
	 */
	@Override
	public StoreLoanVo queryStoreLoanVoCurrent() throws Exception {
		
		List<StoreLoanVo> list = storeLoanMapper.queryStoreLoanVoCurrent();
		return list.size()>0?list.get(0):null;
	}
	/**
	 * 查询历史项目数据
	 */
	@Override
	public StoreLoanVo queryStoreLoanVoHistory() throws Exception {
		List<StoreLoanVo> list = storeLoanMapper.queryStoreLoanVoHistory();
		return list.size()>0?list.get(0):null;
	}
	/**
	 * 删除借款项目记录
	 */
	@Override
	public int deleteStoreLoan(Long id,Integer status) throws Exception {
		FqStoreRepaymentExample repaymentExample = new FqStoreRepaymentExample();
		repaymentExample.createCriteria().andLoanIdEqualTo(id);
		int count = fqStoreRepaymentMapper.countByExample(repaymentExample);
		if(count == 0 && status != 2){
			FqStoreLoanExample example = new FqStoreLoanExample();
			example.createCriteria().andIdEqualTo(id).andStatusEqualTo(status);
			int deletes = fqStoreLoanMapper.deleteByPrimaryKey(id);
			if(deletes > 0){
				return 1;
			}
		}
		return 0;
	}
	/**
	 * 查询还款记录
	 */
	@Override
	public Page<FqStoreRepayment> queryStoreRepayment(Long id,Integer pageNum,
			Integer pageSize) throws Exception {
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		FqStoreRepaymentExample repaymentExample = new FqStoreRepaymentExample();
			repaymentExample.createCriteria().andLoanIdEqualTo(id);
			repaymentExample.setOrderByClause("create_time desc");
			Page<FqStoreRepayment> page = (Page<FqStoreRepayment>) fqStoreRepaymentMapper.selectByExample(repaymentExample);
		return page;
	}
	/**
	 * 新增还款
	 */
	@Override
	public int insertRepayment(FqStoreRepayment fqStoreRepayment,Long id)
			throws Exception {
		//获取借款表对象
		
		FqStoreLoan storeLoan = fqStoreLoanMapper.selectByPrimaryKey(id);
		
		BigDecimal restTotal = storeLoan.getInterestTotal();//利息
		BigDecimal norepayment = storeLoan.getNorepayment();//未还款金额
		
		BigDecimal principal = fqStoreRepayment.getPrincipal();//新增本金
		BigDecimal interest = fqStoreRepayment.getInterest();//新增利息
		//比较大小
		BigDecimal add = principal.add(interest);//新增还款总额
		int compareTo1 = norepayment.compareTo(principal);
		Date now = new Date();
		if(compareTo1 > -1){
			
			if(fqStoreRepayment.getRepaymentType()==1){
				CashAccountExample cashAccountExample = new CashAccountExample();
				cashAccountExample.createCriteria().andAccountIdEqualTo(storeLoan.getSellerId())
				.andAccountTypeEqualTo(1);
				List<CashAccount>  accounts = cashAccountMapper.selectByExample(cashAccountExample);
				CashAccount account = accounts.size()>0?accounts.get(0):null;
				if (account == null) {
					return -1;
				}
				if (account.getBalance().compareTo(add)>-1) {
					BigDecimal balance = account.getBalance();
					account.setBalance(account.getBalance().subtract(add));
					cashAccountMapper.updateByPrimaryKeySelective(account);
					CashLog cashLog = new CashLog();
					cashLog.setRecordNo("");
					cashLog.setAmount(principal);
					cashLog.setPayWay(4);
					cashLog.setCreateTime(now);
					cashLog.setSendId(account.getAccountId());
					cashLog.setPayType(4);
					cashLog.setBusinessCode(PAYCODE.B021);
					cashLog.setBusinessName(PAYCODE.B021N);
					cashLog.setSendBefor(balance);
					BigDecimal sendAfter = balance.subtract(principal);
					cashLog.setSendAfter(sendAfter);
					cashLogMapper.insertSelective(cashLog);
					if (interest.compareTo(BigDecimal.ZERO)>0) {
						cashLog.setAmount(interest);
						cashLog.setBusinessCode(PAYCODE.B022);
						cashLog.setBusinessName(PAYCODE.B022N);
						cashLog.setSendBefor(sendAfter);
						cashLog.setSendAfter(account.getBalance());
						cashLogMapper.insertSelective(cashLog);
					}
					
					fqStoreRepayment.setStatus(1);
					fqStoreRepayment.setRepaymentTime(now);
					fqStoreRepayment.setReservationTime(now);
					fqStoreRepaymentMapper.insertSelective(fqStoreRepayment);
					storeLoan.setNorepayment(norepayment.subtract(principal));
					storeLoan.setInterestTotal(restTotal.add(interest));
					if (storeLoan.getNorepayment().compareTo(BigDecimal.ZERO) == 0) {
						storeLoan.setStatus(2);
					}
					fqStoreLoanMapper.updateByPrimaryKeySelective(storeLoan);
					return 1;
				}else{
					fqStoreRepayment.setStatus(2);
					fqStoreRepayment.setRepaymentTime(now);
					fqStoreRepayment.setReservationTime(now);
					fqStoreRepaymentMapper.insertSelective(fqStoreRepayment);
					return -2;
				}
			}else if(fqStoreRepayment.getRepaymentType() == 2){
				fqStoreRepayment.setStatus(0);
				fqStoreRepaymentMapper.insertSelective(fqStoreRepayment);
				return 1;
			}
		}else{
			fqStoreRepayment.setStatus(2);
			fqStoreRepayment.setRepaymentTime(now);
			fqStoreRepayment.setReservationTime(now);
			fqStoreRepaymentMapper.insertSelective(fqStoreRepayment);
			return -3;
		}
		return -4;
	}
	/**
	 * 点击取消更改状态
	 */
	@Override
	public int updateStatus(FqStoreRepayment repayment, Long id) throws Exception {
		if(id != null){
				int key = fqStoreRepaymentMapper.updateByPrimaryKey(repayment);
				if(key > 0){
					return 1;
			}
		}
		return 0;
	}
	/**
	 * 查询还款记录
	 */
	public FqStoreRepayment queryStoreRepaymentById(Long id)throws Exception{
		return fqStoreRepaymentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateSellerRepayment() {
		Date now = new Date();
		FqStoreRepaymentExample example = new FqStoreRepaymentExample();
		example.createCriteria().andReservationTimeLessThanOrEqualTo(now).andRepaymentTypeEqualTo(2).andStatusEqualTo(0);
		List<FqStoreRepayment> list = fqStoreRepaymentMapper.selectByExample(example);
		int a = 0;
		for (int i = 0; i < list.size(); i++) {
			FqStoreRepayment repayment = list.get(i);
			System.out.println(repayment.getId());
			System.out.println(repayment.getLoanId());
			FqStoreLoan storeLoan = fqStoreLoanMapper.selectByPrimaryKey(repayment.getLoanId());
			
			System.out.println(storeLoan.getId());
			System.out.println(storeLoan.getInterestTotal());
			
			BigDecimal restTotal = storeLoan.getInterestTotal();//利息
			BigDecimal norepayment = storeLoan.getNorepayment();//未还款金额
			
			BigDecimal principal = repayment.getPrincipal();//新增本金
			BigDecimal interest = repayment.getInterest();//新增利息
			
			
			BigDecimal add = principal.add(interest);//新增还款总额
			int compareTo1 = norepayment.compareTo(principal);
			if(compareTo1 > -1){
				
					CashAccountExample cashAccountExample = new CashAccountExample();
					cashAccountExample.createCriteria().andAccountIdEqualTo(storeLoan.getSellerId())
					.andAccountTypeEqualTo(1);
					List<CashAccount>  accounts = cashAccountMapper.selectByExample(cashAccountExample);
					CashAccount account = accounts.size()>0?accounts.get(0):null;
					if (account == null) {
						repayment.setStatus(2);
						repayment.setRepaymentTime(now);
					}else{
						if (account.getBalance().compareTo(add)>-1) {
							BigDecimal balance = account.getBalance();
							account.setBalance(account.getBalance().subtract(add));
							cashAccountMapper.updateByPrimaryKeySelective(account);
							CashLog cashLog = new CashLog();
							cashLog.setRecordNo("");
							cashLog.setAmount(principal);
							cashLog.setPayWay(4);
							cashLog.setCreateTime(now);
							cashLog.setSendId(account.getAccountId());
							cashLog.setPayType(4);
							cashLog.setBusinessCode(PAYCODE.B021);
							cashLog.setBusinessName(PAYCODE.B021N);
							cashLog.setSendBefor(balance);
							BigDecimal sendAfter = balance.subtract(principal);
							cashLog.setSendAfter(sendAfter);
							cashLogMapper.insertSelective(cashLog);
							if (interest.compareTo(BigDecimal.ZERO)>0) {
								cashLog.setAmount(interest);
								cashLog.setBusinessCode(PAYCODE.B022);
								cashLog.setBusinessName(PAYCODE.B022N);
								cashLog.setSendBefor(sendAfter);
								cashLog.setSendAfter(account.getBalance());
								cashLogMapper.insertSelective(cashLog);
							}
							
							repayment.setStatus(1);
							repayment.setRepaymentTime(now);
							storeLoan.setNorepayment(norepayment.subtract(principal));
							storeLoan.setInterestTotal(restTotal.add(interest));
							if (storeLoan.getNorepayment().compareTo(BigDecimal.ZERO) == 0) {
								storeLoan.setStatus(2);
							}
							fqStoreLoanMapper.updateByPrimaryKeySelective(storeLoan);
							
						}else{
							repayment.setStatus(2);
							repayment.setRepaymentTime(now);
						}
					}
			}else{
				repayment.setStatus(2);
				repayment.setRepaymentTime(now);
			}
			a+=fqStoreRepaymentMapper.updateByPrimaryKeySelective(repayment);
		}
		return a;
	}
	/**
	 * 新增汇率
	 */
	@Override
	public int insertFqStoreRate(String rateName, Long adminId,
			String adminUsername, BigDecimal wechatBaseRate,
			BigDecimal wechatAppendRate, BigDecimal wechatAppendMoney,
			BigDecimal alipayBaseRate, BigDecimal alipayAppendRate,
			BigDecimal alipayAppendMoney, BigDecimal qqpayBaseRate,
			BigDecimal qqpayAppendRate, BigDecimal qqpayAppendMoney
			) throws Exception {
		FqStoreRate rate = new FqStoreRate();
		rate.setAdminId(adminId);
		rate.setAdminUsername(adminUsername);
		rate.setAlipayAppendMoney(alipayAppendMoney);
		rate.setAlipayAppendRate(alipayAppendRate);
		rate.setAlipayBaseRate(alipayBaseRate);
		rate.setCreateTime(new Date());
		rate.setQqpayAppendMoney(qqpayAppendMoney);
		rate.setQqpayAppendRate(qqpayAppendRate);
		rate.setQqpayBaseRate(qqpayBaseRate);
		rate.setRateName(rateName);
		rate.setWechatAppendMoney(wechatAppendMoney);
		rate.setWechatAppendRate(wechatAppendRate);
		rate.setWechatBaseRate(wechatBaseRate);
		int result = fqStoreRateMapper.insertSelective(rate);
		if(result > 0){
			return 1;
		}
		return -1;
	}
	/**
	 * 费率套餐列表
	 */
	@Override
	public Page<FqStoreRateVo> queryFqStoreRateVo(String startTime, String endTime,
			String rateName, Integer pageSize, Integer pageNum)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rateName",rateName );
		if (!StringUtils.isEmpty(startTime)) {
			map.put("startTime", startTime+" 00:00:00");
		}
		if (!StringUtils.isEmpty(endTime)) {
			map.put("endTime", endTime+" 23:59:59");
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<FqStoreRateVo> page = fqStoreRateVoMapper.queryFqStoreRateVo(map);
		return page;
	}
	/**
	 * 删除
	 */
	@Override
	public int deleteFqStoreRate(Long id) throws Exception {
		if(id != null){
			int key = fqStoreRateMapper.deleteByPrimaryKey(id);
			if(key > 0){
				return 1;
			}
		}
		return -1;
	}
	/**
	 * 修改
	 */
	@Override
	public int updateFqStoreRate(FqStoreRate rate) throws Exception {
		
		return fqStoreRateMapper.updateByPrimaryKeySelective(rate);
		
	}
	/**
	 * 查询该项目详情
	 */
	@Override
	public FqStoreRate queryFqStoreRate(Long ids) throws Exception {
		return fqStoreRateMapper.selectByPrimaryKey(ids);
	}

	@Override
	public Page<FqCommissionRoleVo> royaltyIncentiveStrategy(String roleName,
			String createName, Date beginTime, Date endTime, Integer pageNum, Integer pageSize) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleName",roleName);
		map.put("createName", createName);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqCommissionRoleVo>) fqCommissionRoleVoMapper.queryFqCommissionRole(map);
	}

	@Override
	public int addFqCommissionRole(String roleName, Long adminId, Double teamProportion,
			Double clerkProportion) throws Exception {
		FqCommissionRole fqCommissionRole = new FqCommissionRole();
		fqCommissionRole.setRoleName(roleName);
		fqCommissionRole.setAdminId(adminId);
		fqCommissionRole.setTeamProportion(new BigDecimal(teamProportion));
		fqCommissionRole.setClerkProportion(new BigDecimal(clerkProportion));
		fqCommissionRole.setCreateTime(new Date());
		return fqCommissionRoleMapper.insertSelective(fqCommissionRole);
	}

	@Override
	public int updateFqCommissionRole(Long id, Double teamProportion, Double clerkProportion) throws Exception {
		FqCommissionRole fqCommissionRole = fqCommissionRoleMapper.selectByPrimaryKey(id);
		fqCommissionRole.setTeamProportion(new BigDecimal(teamProportion));
		fqCommissionRole.setClerkProportion(new BigDecimal(clerkProportion));
		return fqCommissionRoleMapper.updateByPrimaryKeySelective(fqCommissionRole);
	}

	@Override
	public FqCommissionRole fqCommissionRoleDetail(Long id) throws Exception {
		if(null != id || !id.equals("")){
			return fqCommissionRoleMapper.selectByPrimaryKey(id);
		}
		return null;
	}

	@Override
	public int deleteFqCommissionRole(Long id) throws Exception {
		if(null != id || !id.equals("")){
			return fqCommissionRoleMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}
}
