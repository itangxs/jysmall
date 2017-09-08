package cn.qhjys.mall.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.CouponsInfo;

public interface CouponsInfoService {
	public int insertCouponsInfo(CouponsInfo couponsInfo);
	public List<CouponsInfo> listCouponsInfo(Long userLotteryId);
	public CouponsInfo getCouponsInfo(Long userLotteryId,Integer rank);
	
	public CouponsInfo getCouponsInfo(Long userLotteryId);
	
	public CouponsInfo getCouponsInfoById(Long id);
	
	public int updateCouponsInfo(CouponsInfo couponsInfo);
	public Page<CouponsInfo> listCoupons(String openId, Long lotteryId,Integer pageNum,Integer pageSize);
}
