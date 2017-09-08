package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CashSaveWithdrawExample;
import cn.qhjys.mall.entity.CashSaveWithdrawExample.Criteria;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.CashSaveWithdrawMapper;
import cn.qhjys.mall.mapper.custom.WithdrawCustomMapper;
import cn.qhjys.mall.service.CashSaveWithdrawService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.vo.MonthWithdrawMoney;

@Service("cashSaveWithdrawService")
public class CashSaveWithdrawServiceImpl implements CashSaveWithdrawService {
	@Autowired
	private CashSaveWithdrawMapper cashSaveWithdrawMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashLogMapper cashLogMapper;
	@Autowired
	WithdrawCustomMapper withdrawCustomMapper; 

	@Override
	public CashSaveWithdraw getCashSaveWithdraw(Long id, Integer operType, String orderNum) throws Exception {
		CashSaveWithdrawExample example = new CashSaveWithdrawExample();
		Criteria criteria = example.createCriteria().andCashIdEqualTo(id).andOperTypeEqualTo(operType);
		if (!BaseUtil.judgeNull(orderNum))
			criteria.andOrderNumEqualTo(orderNum);
		List<CashSaveWithdraw> list = cashSaveWithdrawMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			return null;
		return list.get(0);
	}

	@Override
	public boolean updateCashSaveWithdraw(CashSaveWithdraw cash, String bankNo, Integer userType, Integer payType)
			throws Exception {
		if (cash == null || cash.getId() == null || cash.getStatus() == null)
			return false;
		if (cash.getStatus() == 1) { // 充值或提现审核
			CashAccount account = cashAccountMapper.selectByPrimaryKey(cash.getCashId());
			if (null == account)
				return false; 
			if (cash.getOperType() == 0) { // 充值
				BigDecimal money = cash.getMoney();
				BigDecimal balance = account.getBalance();
//				BigDecimal freeze = account.getFreezeMoney();
//				BigDecimal withdrawMoney = account.getWithdrawMoney();
				CashLog cashLog = new CashLog();
				cashLog.setRecordNo(cash.getOrderNum());
				cashLog.setAmount(money);
				cashLog.setPayWay(payType);
				cashLog.setBankno(bankNo);
				cashLog.setCreateTime(new Date());
				account.setBalance(balance.add(money));
				cashLog.setReviewId(account.getAccountId());
				cashLog.setPayType(userType.equals(0) ? 1 : 3);
				cashLog.setBusinessCode(PAYCODE.B001);
				cashLog.setBusinessName(PAYCODE.B001N);
				cashLog.setReviewBefor(balance);
				cashLog.setReviewAfter(balance.add(money));
				cashAccountMapper.updateByPrimaryKeySelective(account);
				cashLogMapper.insertSelective(cashLog);
			} else if (cash.getOperType() == 1) {
				// 提现审核，计算手续费
				// 计算当月提现总额
				BigDecimal withdrawMoney = cash.getMoney();
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				MonthWithdrawMoney monthWithdrawMoney = withdrawCustomMapper.queryMonthWithdrawMoney(cash.getAccountId(), 
						DateUtils.getFirstDayOfMonth(year, month), DateUtils.getLastDayOfMonth(year, month));
				BigDecimal monthMoney = null;
				if (monthWithdrawMoney == null || monthWithdrawMoney.getSumMoney() == null) {
					monthMoney = new BigDecimal(0.00);
				}else {
					monthMoney = monthWithdrawMoney.getSumMoney();
				}
				// 计算需要收取手续费的金额
				BigDecimal lineMoney = new BigDecimal(200000000);// 20万额度
				int isMore = monthMoney.compareTo(lineMoney);
				if (isMore == -1) {
					// 小于20万
					BigDecimal isFull = withdrawMoney.add(monthMoney);
					int isFullResult = isFull.compareTo(lineMoney);
					if (isFullResult == -1) {
						// 加上这笔提现，还不够额度
						cash.setFeeMoney(new BigDecimal(0.00));
					}else {
						BigDecimal moneyToFee = isFull.subtract(lineMoney);
						BigDecimal feeMoney = moneyToFee.multiply(new BigDecimal(0.006));
						cash.setFeeMoney(feeMoney);
					}
				}else {
					// 手续费
					BigDecimal feeMoney = withdrawMoney.multiply(new BigDecimal(0.006));
					cash.setFeeMoney(feeMoney);
				}
				
				// 释放冻结金额
				BigDecimal balance = account.getBalance();
				BigDecimal freeze = account.getFreezeMoney();
				BigDecimal accountWithdrawMoney = account.getWithdrawMoney();
				CashLog cashLog = new CashLog();
				cashLog.setRecordNo(cash.getOrderNum());
				cashLog.setAmount(withdrawMoney);
				cashLog.setPayWay(4);
				cashLog.setBankno(bankNo);
				cashLog.setCreateTime(new Date());
				cashLog.setSendId(account.getAccountId());
				cashLog.setPayType(4);
				cashLog.setBusinessCode(PAYCODE.B031);
				cashLog.setBusinessName(PAYCODE.B031N);
				cashLog.setSendBefor(balance);
				cashLog.setSendAfter(balance);
				cashLogMapper.insertSelective(cashLog);
				account.setFreezeMoney(freeze.subtract(withdrawMoney));
				account.setWithdrawMoney(accountWithdrawMoney.add(withdrawMoney));
				cashAccountMapper.updateByPrimaryKeySelective(account);
				
			}
		}else if(cash.getStatus() == 0){
			CashAccount account = cashAccountMapper.selectByPrimaryKey(cash.getCashId());
			if (null == account)
				return false;
			BigDecimal money = cash.getMoney();
			BigDecimal balance = account.getBalance();
			BigDecimal freeze = account.getFreezeMoney();
//			BigDecimal withdrawMoney = account.getWithdrawMoney();
			CashLog cashLog = new CashLog();
			cashLog.setRecordNo(cash.getOrderNum());
			cashLog.setAmount(money);
			cashLog.setPayWay(payType);
			cashLog.setBankno(bankNo);
			cashLog.setCreateTime(new Date());
			if (cash.getOperType() == 1) { // 提现失败
				account.setFreezeMoney(freeze.subtract(money));
//				account.setWithdrawMoney(withdrawMoney.subtract(money));
				account.setBalance(balance.add(money));
				cashLog.setReviewId(account.getAccountId()); 
				cashLog.setPayType(8);
				cashLog.setBusinessCode(PAYCODE.B012);
				cashLog.setBusinessName(PAYCODE.B012N);
				cashLog.setReviewBefor(balance);
				cashLog.setReviewAfter(balance.add(money));
			}
			cashAccountMapper.updateByPrimaryKeySelective(account);
			cashLogMapper.insertSelective(cashLog);
		}else if (cash.getStatus() == 3) {
			if (cash.getOperType() == 1) {
				// 提现出账
			}
		}
		int row = cashSaveWithdrawMapper.updateByPrimaryKeySelective(cash);
		return row > 0;
	}

	@Override
	public boolean insertCashSaveWithdraw(CashSaveWithdraw cash) throws Exception {
		if (cash == null || cash.getCashId() == null || cash.getStatus() != 2 || cash.getMoney() == null)
			return false;
		int row = cashSaveWithdrawMapper.insertSelective(cash);
		return row > 0;
	}

	@Override
	public CashSaveWithdraw queryCashSaveWithdrawById(Long id) throws Exception {
		return cashSaveWithdrawMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CashSaveWithdraw> queryCashSaveWithdrawBySellerId(Long sellerId) throws Exception {
		if (sellerId == null) {
			return null;
		}
		CashSaveWithdrawExample example = new CashSaveWithdrawExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountIdEqualTo(sellerId);
		example.setOrderByClause("create_date desc");
		return cashSaveWithdrawMapper.selectByExample(example);
	}

	@Override
	public Page<CashSaveWithdraw> queryCashSaveWithdrawPageBySellerId(
			Long sellerId, Integer pageNum, Integer pageSize) {
		if (sellerId == null) {
			return null;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		CashSaveWithdrawExample example = new CashSaveWithdrawExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountIdEqualTo(sellerId);
		example.setOrderByClause("create_date desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<CashSaveWithdraw>) cashSaveWithdrawMapper.selectByExample(example);
	}
}