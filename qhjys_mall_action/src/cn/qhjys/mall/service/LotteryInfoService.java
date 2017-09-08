package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.LotteryInfo;

public interface LotteryInfoService {
	public int insertLotteryInfo(LotteryInfo lotteryInfo); 
	
	public LotteryInfo getLotteryInfo(String openId,Long userLotteryId);
	
	public List<LotteryInfo> listLotteryInfo(Long userLotteryId);
}
