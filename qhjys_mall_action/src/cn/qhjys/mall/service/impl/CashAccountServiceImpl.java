package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashIntegralLog;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashIntegralLogMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.service.CashAccountService;

@Service("cashAccountService")
public class CashAccountServiceImpl implements CashAccountService {
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashIntegralLogMapper cashIntegralLogMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;

	@Override
	public int saveCashAccount(Long sellerId, Long userId, String password) throws Exception {
		CashAccount ca = new CashAccount();
		BigDecimal zero = new BigDecimal(0);
		if (null != sellerId) {
			ca.setAccountId(sellerId);
			ca.setAccountType(1);
		}
		if (null != userId) {
			ca.setAccountId(userId);
			ca.setAccountType(0);
		}
		ca.setBalance(zero);
		ca.setCreateDate(new Date());
		ca.setFreezeIntegral(zero);
		ca.setFreezeMoney(zero);
		ca.setIntegral(zero);
		ca.setPayCode(password);
		ca.setWithdrawMoney(zero);
		return cashAccountMapper.insertSelective(ca);
	}

	@Override
	public CashAccount queryCashAccount(Long sellerId, Long userId) throws Exception {
		CashAccountExample example = new CashAccountExample();
		if (null != sellerId){
			example.createCriteria().andAccountTypeEqualTo(1).andAccountIdEqualTo(sellerId);
		}else{
			example.createCriteria().andAccountTypeEqualTo(0).andAccountIdEqualTo(userId);
		}
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean updateCashAccount(CashAccount account) throws Exception {
		if (account == null || account.getId() == null) {
			return false;
		}
		int row = cashAccountMapper.updateByPrimaryKeySelective(account);
		return row > 0;
	}

	@Override
	public int updateBalanceToIntegral(Long sellerId, BigDecimal money) throws Exception {
		CashAccount cash = queryCashAccount(sellerId, null);
		if (cash.getBalance().compareTo(money) == -1) {
			return -1;
		}
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<StoreInfo> list = storeInfoMapper.selectByExample(example);
		StoreInfo store = list.get(0);
		cash.setBalance(cash.getBalance().subtract(money));
		cash.setIntegral(cash.getIntegral().add(money));
		if (updateCashAccount(cash)) {
			CashIntegralLog log = new CashIntegralLog();
			log.setCreateTime(new Date());
			log.setMoney(money);
			log.setSellerId(sellerId);
			log.setType(1);
			log.setStoreId(store.getId());
			log.setStoreName(store.getName());
			cashIntegralLogMapper.insertSelective(log);
			return 1;
		}
		return 0;
	}

	@Override
	public int updateIntegralByRecharge(Long storeId, BigDecimal money,String remark)
			throws Exception {
		StoreInfo store  = storeInfoMapper.selectByPrimaryKey(storeId);
		if (store == null) {
			return -1;
		}
		CashAccount cash = queryCashAccount(store.getSellerId(), null);
		cash.setIntegral(cash.getIntegral().add(money));
		if (updateCashAccount(cash)) {
			CashIntegralLog log = new CashIntegralLog();
			log.setCreateTime(new Date());
			log.setMoney(money);
			log.setSellerId(store.getSellerId());
			log.setType(2);
			log.setStoreId(store.getId());
			log.setStoreName(store.getName());
			log.setRemark(remark);
			cashIntegralLogMapper.insertSelective(log);
			return 1;
		}
		return 0;
	}
}