package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.StoreLottery;

public interface StoreLotteryService {
	
	public StoreLottery getStoreLottery(Long id);
	
	public List<LotteryDish> listLotteryDishByLottery(Long LotteryId);
	
	public LotteryDish getLotteryDish(Long lotteryId,Integer rank);
}
