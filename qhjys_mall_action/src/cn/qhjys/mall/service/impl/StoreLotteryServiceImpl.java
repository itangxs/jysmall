package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.LotteryDishExample;
import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.mapper.LotteryDishMapper;
import cn.qhjys.mall.mapper.StoreLotteryMapper;
import cn.qhjys.mall.service.StoreLotteryService;

@Service("storeLotteryService")
public class StoreLotteryServiceImpl implements StoreLotteryService {
	
	@Autowired
	private StoreLotteryMapper storeLotteryMapper;
	@Autowired
	private LotteryDishMapper lotteryDishMapper;

	@Override
	public StoreLottery getStoreLottery(Long id) {
		return storeLotteryMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<LotteryDish> listLotteryDishByLottery(Long LotteryId) {
		LotteryDishExample example = new LotteryDishExample();
		example.createCriteria().andLotteryIdEqualTo(LotteryId);
		example.setOrderByClause("rank_level asc");
		return lotteryDishMapper.selectByExample(example);
	}
	@Override
	public LotteryDish getLotteryDish(Long lotteryId, Integer rank) {
		LotteryDishExample example = new LotteryDishExample();
		example.createCriteria().andLotteryIdEqualTo(lotteryId).andRankLevelEqualTo(rank);
		List<LotteryDish> list = lotteryDishMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
}
