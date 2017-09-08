package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.BankInfo;

public interface BankInfoService {

	BankInfo queryBankInfoBySellerId(Long sellerId) throws Exception;
	
	int updateByPrimaryKeySelective(BankInfo bankInfo);
	
}
