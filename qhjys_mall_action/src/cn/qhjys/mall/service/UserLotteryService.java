package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.UserLottery;

public interface UserLotteryService {
	public int insertUserLottery(UserLottery userLottery);
	public UserLottery selectUserLottery(String openid ,Long lotteryId);
	
	public UserLottery getUserLottery(Long id);
}
