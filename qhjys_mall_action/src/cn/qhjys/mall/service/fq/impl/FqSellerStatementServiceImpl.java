package cn.qhjys.mall.service.fq.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashLogExample;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
import cn.qhjys.mall.entity.FqSellerStatementExample.Criteria;
import cn.qhjys.mall.entity.FqWithdrawChangeinfo;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.service.BankInfoService;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashSaveWithdrawService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.FqWithdrawChangeInfoService;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.vo.SellerUserInfoVo;

@Service("fqSellerStatementService")
public class FqSellerStatementServiceImpl extends Base implements FqSellerStatementService{
	
	@Autowired
	FqSellerStatementMapper fqSellerStatementMapper;
	@Autowired
	CashAccountService cashAccountService;
	@Autowired
	BankInfoService bankInfoService; 
	@Autowired
	CashSaveWithdrawService cashSaveWithdrawService;
	@Autowired
	CashLogMapper cashLogMapper;
	@Autowired
	FqWithdrawChangeInfoService fqWithdrawChangeInfoService;
	@Autowired
	SellerService sellerService;
	@Autowired
	SellerUserCountService sellerUserCountService;
	
	@Override
	public Page<FqSellerStatement> querySellerStatementBySellerId(Long sellerId,
			Integer pageNum,Integer pageSize) throws Exception {
		if (sellerId == null) {
			return null;
		}
		FqSellerStatementExample example = new FqSellerStatementExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		PageHelper.startPage(pageNum, pageSize);
		example.setOrderByClause("create_time desc");
		Page<FqSellerStatement> statements = (Page<FqSellerStatement>) fqSellerStatementMapper.selectByExample(example);
		return statements;
	}

	@Override
	public synchronized int insertWithdraw(Long sellerId, String money) throws Exception {
		if (sellerId == null || StringUtils.isEmpty(money)) {
			return -1;
		}
		BigDecimal withdrawMoney = new BigDecimal(money);
		logger.info(sellerId+"-----提现金额------->"+withdrawMoney);
		int valid = withdrawMoney.compareTo(new BigDecimal(0));
		if (valid < 1) {
			return -1;
		}
		// 查询余额
		CashAccount cashAccount = cashAccountService.queryCashAccount(sellerId, null);
		if (cashAccount == null) {
			return -1;
		}
		BigDecimal balance = cashAccount.getBalance();
		logger.info(sellerId+"-----当前余额------->"+balance);
		int result = balance.compareTo(withdrawMoney);
		if (result == -1) {
			// 余额不足
			return 0;
		}
		// 查询银行卡信息
		BankInfo bankInfo = bankInfoService.queryBankInfoBySellerId(sellerId);
		if (bankInfo == null) {
			// 银行卡异常
			return 1;
		}
//		// 计算当月提现总额
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH) + 1;
//		MonthWithdrawMoney monthWithdrawMoney = withdrawCustomMapper.queryMonthWithdrawMoney(sellerId, 
//				DateUtils.getFirstDayOfMonth(year, month), DateUtils.getLastDayOfMonth(year, month));
//		BigDecimal monthMoney = null;
//		if (monthWithdrawMoney == null || monthWithdrawMoney.getSumMoney() == null) {
//			monthMoney = new BigDecimal(0.00);
//		}else {
//			monthMoney = monthWithdrawMoney.getSumMoney();
//		}
//		// 计算需要收取手续费的金额
//		BigDecimal lineMoney = new BigDecimal(200000);// 20万额度
//		int isMore = monthMoney.compareTo(lineMoney);
		CashSaveWithdraw cashSaveWithdraw = new CashSaveWithdraw();
		cashSaveWithdraw.setOperType(1);
		cashSaveWithdraw.setCashId(cashAccount.getId());
		cashSaveWithdraw.setBankId(bankInfo.getId());
		cashSaveWithdraw.setMoney(withdrawMoney);
		cashSaveWithdraw.setCreateDate(new Date());
		cashSaveWithdraw.setStatus(2);
		cashSaveWithdraw.setAccountId(sellerId);
//		if (isMore == -1) {
//			// 小于20万
//			BigDecimal isFull = withdrawMoney.add(monthMoney);
//			int isFullResult = isFull.compareTo(lineMoney);
//			if (isFullResult == -1) {
//				// 加上这笔提现，还不够额度
//				cashSaveWithdraw.setFeeMoney(new BigDecimal(0.00));
//			}else {
//				BigDecimal moneyToFee = isFull.subtract(lineMoney);
//				BigDecimal feeMoney = moneyToFee.multiply(new BigDecimal(0.006));
//				cashSaveWithdraw.setFeeMoney(feeMoney);
//			}
//		}else {
//			// 手续费
//			BigDecimal feeMoney = withdrawMoney.multiply(new BigDecimal(0.006));
//			cashSaveWithdraw.setFeeMoney(feeMoney);
//		}
		BigDecimal freezeMoney = cashAccount.getFreezeMoney();
		cashAccount.setFreezeMoney(freezeMoney.add(withdrawMoney));
		cashAccount.setBalance(balance.subtract(withdrawMoney));
		boolean insertResult = cashSaveWithdrawService.insertCashSaveWithdraw(cashSaveWithdraw);
		CashLog cashLog = new CashLog();
		cashLog.setRecordNo(cashSaveWithdraw.getOrderNum());
		cashLog.setAmount(withdrawMoney);
		cashLog.setPayWay(4);
		cashLog.setBankno(bankInfo.getCarkNum());
		cashLog.setCreateTime(new Date());
		cashLog.setSendId(cashAccount.getAccountId());
		cashLog.setPayType(4);
		cashLog.setBusinessCode(PAYCODE.B008);
		cashLog.setBusinessName(PAYCODE.B008N);
		cashLog.setSendBefor(balance);
		cashLog.setSendAfter(cashAccount.getBalance());
		cashLogMapper.insertSelective(cashLog);
		if (!insertResult) {
			throw new Exception("insert withdraw record error");
		}
		boolean updateResult = cashAccountService.updateCashAccount(cashAccount);
		if (!updateResult) {
			throw new Exception("update cash account error");
		}
		return 2;
	}

//	/**
//	 * 定时任务更新每日结算状态
//	 * @param date
//	 * @return
//	 * @throws Exception
//	 */
//	@Override
//	public boolean updateSellerStatementByDate(Date date) throws Exception {
//		if (date == null) {
//			return false;
//		}
//		FqSellerStatementExample example = new FqSellerStatementExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andStateEqualTo(0);
//		criteria.andStatementDateGreaterThanOrEqualTo(DateUtils.getFirstSecondOfDay(date));
//		criteria.andStatementDateLessThanOrEqualTo(DateUtils.getLastSecondOfDay(date));
//		FqSellerStatement record = new FqSellerStatement();
//		record.setState(1);
//		int result = fqSellerStatementMapper.updateByExampleSelective(record, example);
//		if (result > 0 ) {
//			 return addTotalMoneyToCashAccount(date);
//		}
//		return false;
//	}
//
//	/**
//	 * 定时任务将每日结算钱加到商家账户总额
//	 * @param date
//	 * @return
//	 * @throws Exception
//	 */
//	@Override
//	public boolean addTotalMoneyToCashAccount(Date date) throws Exception {
//		if (date == null) {
//			return false;
//		}
//		List<FqSellerStatement> statements = querySellerStatementByDate(date);
//		if (statements != null && statements.size() > 0) {
//			for (FqSellerStatement fqSellerStatement : statements) {
//				// 查询余额
//				CashAccount cashAccount = cashAccountService.queryCashAccount(fqSellerStatement.getSellerId(), null);
//				if (cashAccount != null) {
//					BigDecimal balance = cashAccount.getBalance();
//					BigDecimal totalMoney = fqSellerStatement.getTotalMoney();
//					cashAccount.setBalance(balance.add(totalMoney));
//					// 更新
//					cashAccountService.updateCashAccount(cashAccount);
//					CashLog log = new CashLog(); // 添加商家销售入账记录
//					log.setRecordNo("");
//					log.setSendId(fqSellerStatement.getSellerId());
//					log.setReviewId(fqSellerStatement.getSellerId());
//					log.setAmount(totalMoney);
//					log.setPayType(5);
//					log.setPayWay(4);
//					log.setBusinessCode(PAYCODE.B005);
//					log.setBusinessName(PAYCODE.B005N);
//					log.setReviewBefor(balance);
//					log.setReviewAfter(balance.add(totalMoney));
//					log.setCreateTime(new Date());
//					cashLogMapper.insertSelective(log);
//				}
//			}
//			return true;
//		}
//		return false;
//	}

	@Override
	public List<FqSellerStatement> querySellerStatementByDate(Date date) throws Exception {
		if (date == null) {
			return null;
		}
		// 开启自动提现才会有数据
		FqSellerStatementExample example = new FqSellerStatementExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatementDateGreaterThanOrEqualTo(DateUtils.getFirstSecondOfDay(date));
		criteria.andStatementDateLessThanOrEqualTo(DateUtils.getLastSecondOfDay(date));
//		criteria.andWithdrawStatusEqualTo(1);
		criteria.andStateEqualTo(0); // 未结算的
		return fqSellerStatementMapper.selectByExample(example);
	}
	@Override
	public List<FqSellerStatement> querySellerStatementByPeriodDate(Date date) throws Exception {
		if (date == null) {
			return null;
		}
		// 开启自动提现才会有数据
		FqSellerStatementExample example = new FqSellerStatementExample();
		Criteria criteria = example.createCriteria();
		criteria.andPeriodDateGreaterThanOrEqualTo(DateUtils.getFirstSecondOfDay(date));
		criteria.andPeriodDateLessThanOrEqualTo(DateUtils.getLastSecondOfDay(date));
//		criteria.andWithdrawStatusEqualTo(1);
		criteria.andStateEqualTo(0); // 未结算的
		return fqSellerStatementMapper.selectByExample(example);
	}
	
	/**
	 * 定时器调用，用于更新结算状态，追加账户余额，修改是否自动提现。
	 * @return
	 * @throws Exceptionk
	 */
	@Override
	public boolean updateSellerStatementAndAutoWithdrawByQuartz() throws Exception {
		logger.info("*****************定时器结算商家余额开始*****************");
		// 一、追加账户总额,更改结算状态
		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -1); // 昨天
		Date today = cal.getTime();
		logger.info("*****************定时器结算昨天时间为="+today+"*****************");
		List<FqSellerStatement> statements = querySellerStatementByPeriodDate(today);
		logger.info("*****************定时器结算商家总数"+statements.size()+"*****************");
		if (statements != null && statements.size() > 0) {
			for (FqSellerStatement fqSellerStatement : statements) {
				// 查询余额
				CashAccount cashAccount = cashAccountService.queryCashAccount(fqSellerStatement.getSellerId(), null);
				logger.info("*****************定时器结算商家账号："+fqSellerStatement.getSellerId()+"*****************");
				if (cashAccount != null) {
					SellerUserInfoVo sellerUserInfoVo = sellerUserCountService.queryJsDayliyVo(fqSellerStatement.getSellerId(), fqSellerStatement.getStatementDate());
					if (sellerUserInfoVo.getConNum() != fqSellerStatement.getTotalNum()) {
						fqSellerStatement.setTotalMoney(sellerUserInfoVo.getConMoney());
						fqSellerStatement.setTotalNum(sellerUserInfoVo.getConNum());
					}
					BigDecimal balance = cashAccount.getBalance();
					BigDecimal totalMoney = fqSellerStatement.getTotalMoney();
					if (fqSellerStatement.getWpMoney() != null) {
						totalMoney = totalMoney.subtract(fqSellerStatement.getWpMoney());
					}
					// 结算到账户总额
					cashAccount.setBalance(balance.add(totalMoney));
					logger.info("*****************定时器结算商家账号："+fqSellerStatement.getSellerId()+",balance="+balance+",totalMoney="+totalMoney+"****************");
					// 更新
					cashAccountService.updateCashAccount(cashAccount);
					// 更改结算状态
					fqSellerStatement.setState(1);
					fqSellerStatementMapper.updateByPrimaryKeySelective(fqSellerStatement);
					CashLog log = new CashLog(); // 添加商家销售入账记录
					log.setRecordNo("");
					log.setSendId(fqSellerStatement.getSellerId());
					log.setReviewId(fqSellerStatement.getSellerId());
					log.setAmount(totalMoney);
					log.setPayType(5);
					log.setPayWay(4);
					log.setBusinessCode(PAYCODE.B005);
					log.setBusinessName(PAYCODE.B005N);
					log.setReviewBefor(balance);
					log.setReviewAfter(balance.add(totalMoney));
					log.setCreateTime(new Date());
					logger.info("*****************定时器结算商家账号："+fqSellerStatement.getSellerId()+" 金额日志创建时间:"+log.getCreateTime()+"*****************");
					cashLogMapper.insertSelective(log);
				}
			}
		}
		// 二、修改自动提现
		// 查找昨天的修改信息
		cal.add(Calendar.DATE, -1); // 昨天
		Date yesterday = cal.getTime();
		List<FqWithdrawChangeinfo> changeinfos = fqWithdrawChangeInfoService.queryFqWithdrawChangeInfoByDate(yesterday);
		logger.info("*****************定时器修改自动提现：changeinfos的大小"+changeinfos.size()+"*****************");
		
		if (changeinfos != null && changeinfos.size() > 0) {
			for (FqWithdrawChangeinfo fqWithdrawChangeinfo : changeinfos) {
				Integer statusAfter = fqWithdrawChangeinfo.getStatusAfter();
				Integer statusBefore = fqWithdrawChangeinfo.getStatusBefore();
				Long sellerId = fqWithdrawChangeinfo.getSellerId();
				logger.info("*****************定时器修改自动提现updateSellerWithdrawStatus：sellerId="+sellerId+",statusAfter="+statusAfter+",statusBefore="+statusBefore+"*****************");
				
				sellerService.updateSellerWithdrawStatus(sellerId, statusAfter);
				/*if (statusBefore != null && statusAfter != null) {
					// 如果是关闭自动提现
					if (statusBefore == 1 && statusAfter == 0) {
						// 自动帮商家提现一次
						int result = -1;
						try {
							CashAccount cashAccount = cashAccountService.queryCashAccount(sellerId, null);
							BigDecimal balance = cashAccount.getBalance();
							result = insertWithdraw(sellerId, balance.toPlainString());
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (result != 2) {
							logger.info("----自动提现失败----",sellerId);
						}
					}
				}*/
			}
		}
		logger.info("*****************定时器结算商家余额结束*****************");
		return true;
	}

	@Override
	public List<FqSellerStatement> querySellerStatementBySellerId(Long sellerId) throws Exception {
		if (sellerId == null) {
			return null;
		}
		FqSellerStatementExample example = new FqSellerStatementExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		example.setOrderByClause("create_time desc");
		return fqSellerStatementMapper.selectByExample(example);
	}

	@Override
	public FqSellerStatement getFqSellerStatement(Long sellerId, Date date) {
		FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
		fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date);
		List<FqSellerStatement> list = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public boolean updateSellerStatementByQuartz() throws Exception {
		
		logger.info("*****************定时器结算 start");
		
		Date date = new Date();
		List<FqSellerStatement> statements = querySellerStatementByDate(date);
		if (statements != null && statements.size() > 0) {
			for (FqSellerStatement fqSellerStatement : statements) {
				SellerUserInfoVo sellerUserInfoVo = sellerUserCountService.queryJsDayliyVo(fqSellerStatement.getSellerId(), date);
				if (sellerUserInfoVo.getConNum() != fqSellerStatement.getTotalNum()) {
					FqSellerStatement fss = new FqSellerStatement();
					fss.setTotalMoney(sellerUserInfoVo.getConMoney());
					fss.setTotalNum(sellerUserInfoVo.getConNum());
					fss.setId(fqSellerStatement.getId());
					logger.info("*****************定时器结算验证id="+fss.getId()+",TotalMoney="+fss.getTotalMoney()+", TotalNum="+fss.getTotalNum()+"*****************");
					fqSellerStatementMapper.updateByPrimaryKeySelective(fss);
				}
			}
		}
		logger.info("*****************定时器结算 end");
		return true;
	}

	@Override
	public boolean updateSellerStatementHasPeriod() {
		Calendar c = Calendar.getInstance();
		FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
		fqSellerStatementExample.createCriteria().andPeriodDateLessThanOrEqualTo(c.getTime()).andStateEqualTo(0);
		List<FqSellerStatement> list = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
		int a = 0;
		for (int i = 0; i < list.size(); i++) {
			FqSellerStatement fss = list.get(i);
			CashLogExample cashLogExample = new CashLogExample();
			cashLogExample.createCriteria().andCreateTimeBetween(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", fss.getPeriodDate()+" 00:00:00"), DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", fss.getPeriodDate()+" 00:59:00")).andBusinessCodeEqualTo("005")
			.andAmountEqualTo(fss.getTotalMoney().subtract(fss.getWpMoney() != null?fss.getWpMoney():BigDecimal.ZERO));
			List<CashLog> casslist = cashLogMapper.selectByExample(cashLogExample);
			if (casslist.size()>0) {
				SellerUserInfoVo sellerUserInfoVo = sellerUserCountService.queryJsDayliyVo(fss.getSellerId(), fss.getStatementDate());
				if (sellerUserInfoVo.getConNum() != fss.getTotalNum()) {
					FqSellerStatement fss1 = new FqSellerStatement();
					fss1.setTotalMoney(sellerUserInfoVo.getConMoney());
					fss1.setTotalNum(sellerUserInfoVo.getConNum());
					fss1.setId(fss.getId());
					fss1.setState(1);
					logger.info("********updateSellerStatementHasPeriod*********id="+fss.getId()+",TotalMoney="+fss.getTotalMoney()+", TotalNum="+fss.getTotalNum()+"*****************");
					a += fqSellerStatementMapper.updateByPrimaryKeySelective(fss);
				}
			}
		}
		return a>0?true:false;
	}

	@Override
	public boolean updateMsSellerWithdraw(Long sellerId, Date startDate,
			Date endDate) throws Exception {
		FqSellerStatementExample example = new FqSellerStatementExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(4);
		criteria.andSellerIdEqualTo(sellerId);
		criteria.andCreateTimeGreaterThan(startDate);
		criteria.andCreateTimeLessThanOrEqualTo(endDate);
		FqSellerStatement record = new FqSellerStatement();
		record.setState(3);
		int result = fqSellerStatementMapper.updateByExampleSelective(record, example);
		return result <= 0 ? false : true;
	}
	
	@Override
	public boolean updateMsSellerStatementQuartz() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("--当前日期为："+sdf.format(new Date()));
		//得到昨天23：00
		Date startDate = DateUtils.getDateThree((DateUtils.getDateBefore(new Date(),1)));
		//得到今天23：00
		Date endDate = DateUtils.getDateThree((DateUtils.getDateBefore(new Date(),0)));
		logger.info("--执行"+sdf.format(startDate)+"至"+sdf.format(endDate)+"时间段的数据---");
		List<FqSellerStatement> statements = querySellerStatementByCreateDate(startDate,endDate);
		logger.info("-需要结算商家总数"+statements.size()+"");
		if (statements != null && statements.size() > 0) {
			for (FqSellerStatement fqSellerStatement : statements) {
				logger.info("-结算商家账号："+fqSellerStatement.getSellerId()+"");
				BigDecimal totalMoney = fqSellerStatement.getTotalMoney();
				if (fqSellerStatement.getWpMoney() != null) {
					totalMoney = totalMoney.subtract(fqSellerStatement.getWpMoney());
				}
				// 更改结算状态
				fqSellerStatement.setState(4);
				fqSellerStatementMapper.updateByPrimaryKeySelective(fqSellerStatement);
			}
		}
		return true;
	}

	@Override
	public List<FqSellerStatement> querySellerStatementByCreateDate(
			Date startDate, Date endDate) throws Exception {
		if (startDate == null || endDate == null) {
			return null;
		}
		FqSellerStatementExample example = new FqSellerStatementExample();
		Criteria criteria = example.createCriteria();
		criteria.andCreateTimeGreaterThan(startDate);
		criteria.andCreateTimeLessThanOrEqualTo(endDate);
		criteria.andStateEqualTo(2); // 民生未结算的
		return fqSellerStatementMapper.selectByExample(example);
	}

	@Override
	public boolean updateVeriMsSellerStatementQuartz() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = DateUtils.getDateThree((DateUtils.getDateBefore(new Date(),1)));
		Date endDate = DateUtils.getDateThree((DateUtils.getDateBefore(new Date(),0)));
		logger.info("--结算验证，执行"+sdf.format(startDate)+"至"+sdf.format(endDate)+"时间段的数据---");
		List<FqSellerStatement> statements = querySellerStatementByCreateDate(startDate,endDate);
		if (statements != null && statements.size() > 0) {
			logger.info("-结算验证总数:"+statements.size()+"");
			for (FqSellerStatement fqSellerStatement : statements) {
				logger.info("-验证卖家ID:"+fqSellerStatement.getSellerId()+"");
				Map<String,Object> map = 
						queryThirdByCreateDate(startDate,endDate,fqSellerStatement.getSellerId());
				logger.info("-总计付款:"+(BigDecimal)map.get("totalMoney")+"");
				logger.info("-总计笔数:"+(Integer)map.get("totalNum")+"");
				fqSellerStatement.setTotalMoney((BigDecimal)map.get("totalMoney"));
				fqSellerStatement.setTotalNum(((Integer)map.get("totalNum")).intValue());
				fqSellerStatementMapper.updateByPrimaryKeySelective(fqSellerStatement);
			}
		}
		return true;
		
	}

	public Map<String,Object> queryThirdByCreateDate(
			Date startDate, Date endDate,Long sellerId) throws Exception {
		if (startDate == null || endDate == null) {
			return null;
		}
		Map<String,Object> whereMap = new HashMap<String,Object>();
		whereMap.put("startDate", startDate);
		whereMap.put("endDate", endDate);
		whereMap.put("sellerId", sellerId);
		return fqSellerStatementMapper.selectThirdCount(whereMap);
	}

}
