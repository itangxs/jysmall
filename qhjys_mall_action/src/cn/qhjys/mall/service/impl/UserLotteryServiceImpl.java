package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.UserLottery;
import cn.qhjys.mall.entity.UserLotteryExample;
import cn.qhjys.mall.mapper.UserLotteryMapper;
import cn.qhjys.mall.service.UserLotteryService;

@Service("userLotteryService")
public class UserLotteryServiceImpl implements UserLotteryService {
	
	@Autowired
	private UserLotteryMapper userLotteryMapper;
	
	@Override
	public int insertUserLottery(UserLottery userLottery) {
		return userLotteryMapper.insertSelective(userLottery);
	}

	@Override
	public UserLottery selectUserLottery(String openid, Long lotteryId) {
		UserLotteryExample example = new UserLotteryExample();
		example.createCriteria().andOpenIdEqualTo(openid).andLotteryIdEqualTo(lotteryId).andStatusEqualTo(0);
		List<UserLottery> uts = userLotteryMapper.selectByExample(example);
		return uts.size()>0?uts.get(0):null;
	}

	@Override
	public UserLottery getUserLottery(Long id) {
		return userLotteryMapper.selectByPrimaryKey(id);
	}

}
