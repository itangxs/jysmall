package cn.qhjys.mall.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.CashIntegralLog;

public interface CashIntegralLogService {
	public List<CashIntegralLog> queryCashIntegralLog(Long sellerId);
	
	public Page<CashIntegralLog> queryCashIntegralLog(Long storeId,String storeName,Integer type,Integer pageNum,Integer pageSize);
}
