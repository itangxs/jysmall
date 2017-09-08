package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.IntegralExpired;
import cn.qhjys.mall.entity.IntegralExpiredExample;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.IntegralExpiredMapper;
import cn.qhjys.mall.mapper.UserInfoMapper;
import cn.qhjys.mall.service.IntegralExpiredService;
import cn.qhjys.mall.util.ConstantsConfigurer;

@Service
public class IntegralExpiredServiceImpl extends Base implements IntegralExpiredService {

	@Autowired
	private IntegralExpiredMapper integralExpiredMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashLogMapper cashLogMapper;

	@Override
	public boolean insertIntegralExpired(IntegralExpired integralExpired) throws Exception {
		int num = integralExpiredMapper.insertSelective(integralExpired);
		return num > 0 ? true : false;
	}

	@Override
	public boolean saveIntegralExpired() throws Exception {
		List<UserInfo> list = userInfoMapper.selectByExample(null);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		for (int i = 0; i < list.size(); i++) {
			IntegralExpired ie = this.getIntegralExpired(list.get(i).getId(), year, month);
			if (ie != null) {
				CashAccountExample example = new CashAccountExample();
				example.createCriteria().andAccountIdEqualTo(list.get(i).getId()).andAccountTypeEqualTo(0);
				List<CashAccount> cashlist = cashAccountMapper.selectByExample(example);
				if (cashlist.size() > 0) {
					CashAccount ca = cashlist.get(0);
					BigDecimal beforintegral = ca.getBalance();
					ca.setBalance(ca.getBalance().subtract(new BigDecimal(ie.getSurplus())));
					ie.setSurplus(0);
					this.updateIntegralExpired(ie);
					if (ie.getSurplus() != 0) {
						Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
						cashAccountMapper.updateByPrimaryKeySelective(ca);
						CashLog log = new CashLog();
						log.setSendId(ie.getAccountId());
						log.setReviewId(jysmall);
						log.setAmount(new BigDecimal(ie.getSurplus()));
						log.setPayType(10);
						log.setPayWay(4);
						log.setBusinessCode(PAYCODE.B007);
						log.setBusinessName(PAYCODE.B007N);
						log.setReviewBefor(beforintegral);
						log.setReviewAfter(ca.getBalance());
						log.setCreateTime(new Date());
						cashLogMapper.insertSelective(log);
					}
				}
			}
			if (!saveUserIntegralExpired(list.get(i).getId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean saveUserIntegralExpired(Long accountId) throws Exception {
		Calendar c = Calendar.getInstance();
		for (int j = 0; j < 6; j++) {
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			IntegralExpired ie = this.getIntegralExpired(accountId, year, month);
			if (ie == null) {
				ie = new IntegralExpired();
				ie.setAccountId(accountId);
				ie.setDestroy(0);
				ie.setIncome(0);
				ie.setMonth(month);
				ie.setSurplus(0);
				ie.setYear(year);
				this.insertIntegralExpired(ie);
				
			}
			c.add(Calendar.MONTH, 1);
		}
		return true;
	}

	@Override
	public IntegralExpired getIntegralExpired(Long accountId, int year, int month) throws Exception {
		IntegralExpiredExample example = new IntegralExpiredExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andYearEqualTo(year).andMonthEqualTo(month);
		List<IntegralExpired> list = integralExpiredMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	public IntegralExpiredMapper getIntegralExpiredMapper() throws Exception {
		return integralExpiredMapper;
	}

	public void setIntegralExpiredMapper(IntegralExpiredMapper integralExpiredMapper) throws Exception {
		this.integralExpiredMapper = integralExpiredMapper;
	}

	@Override
	public boolean updateIntegralExpired(IntegralExpired integralExpired) throws Exception {
		int num = integralExpiredMapper.updateByPrimaryKeySelective(integralExpired);
		return num > 0 ? true : false;
	}

	@Override
	public boolean updateIntegralExpiredByShop(Long accountId, Integer integral) throws Exception {
		IntegralExpiredExample example = new IntegralExpiredExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andSurplusGreaterThan(0);
		example.setOrderByClause(" year,month ");
		List<IntegralExpired> list = integralExpiredMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			if (integral > 0) {
				IntegralExpired ie = list.get(i);
				if (integral > ie.getSurplus()) {
					integral -= ie.getSurplus();
					ie.setSurplus(0);
				} else {
					ie.setSurplus(ie.getSurplus() - integral);
				}
				if (!this.updateIntegralExpired(ie)) {
					return false;
				}
			} else {
				break;
			}
		}
		return true;
	}

	@Override
	public int updateIntegralExpiredByRefund(Long accountId, Integer integral) throws Exception {
		Calendar c = Calendar.getInstance();
		IntegralExpiredExample example = new IntegralExpiredExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andDestroyGreaterThan(0);
		example.setOrderByClause(" year desc,month desc ");
		List<IntegralExpired> list = integralExpiredMapper.selectByExample(example);
		int rintegral = integral;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		for (int i = 0; i < list.size(); i++) {
			IntegralExpired ie = list.get(i);
			if (rintegral > 0 && (ie.getYear() * 100 + ie.getMonth()) > (year * 100 + month)) {
				int destroy = ie.getDestroy();
				int surplus = ie.getSurplus();
				if (rintegral > (destroy - surplus)) {
					ie.setSurplus(destroy);
					rintegral -= (destroy - surplus);
				} else {
					ie.setSurplus(surplus + rintegral);
					rintegral = 0;
				}
				this.updateIntegralExpired(ie);
			} else {
				break;
			}
		}
		return rintegral;
	}

	@Override
	public boolean updateIntegralExpiredByEvaluate(Long accountId, Integer integral) throws Exception {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		IntegralExpired ie = this.getIntegralExpired(accountId, year, month);
		c.add(Calendar.MONTH, 5);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		IntegralExpired ie1 = this.getIntegralExpired(accountId, year, month);
		if (ie == null || ie1 == null) {
			this.saveUserIntegralExpired(accountId);
			ie1 = this.getIntegralExpired(accountId, year, month);
			c.add(Calendar.MONTH, -5);
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH) + 1;
			ie = this.getIntegralExpired(accountId, year, month);
		}
		ie.setIncome(ie.getIncome() + integral);
		ie1.setDestroy(ie1.getDestroy() + integral);
		ie1.setSurplus(ie1.getSurplus() + integral);
		if (this.updateIntegralExpired(ie) && this.updateIntegralExpired(ie1)) {
			return true;
		}
		return false;
	}
}