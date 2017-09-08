package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.SellerLoginLog;

public interface SellerLoginLogService {

	public int insertSellerLoginLog(SellerLoginLog sellerLoginLog);
	
	public int countSellerLoginLog(Long sellerId,Integer type);
	public int countSellerLoginLogToday(Long sellerId,Integer type);
}
