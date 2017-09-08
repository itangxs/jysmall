package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.IntegralExpired;

public interface IntegralExpiredService {
	public boolean insertIntegralExpired(IntegralExpired integralExpired) throws Exception;

	public boolean saveUserIntegralExpired(Long accountId) throws Exception;

	public boolean saveIntegralExpired() throws Exception;

	public boolean updateIntegralExpired(IntegralExpired integralExpired) throws Exception;

	public boolean updateIntegralExpiredByShop(Long accountId, Integer integral) throws Exception;

	public int updateIntegralExpiredByRefund(Long accountId, Integer integral) throws Exception;

	public IntegralExpired getIntegralExpired(Long accountId, int year, int month) throws Exception;

	public boolean updateIntegralExpiredByEvaluate(Long accountId, Integer integral) throws Exception;
}