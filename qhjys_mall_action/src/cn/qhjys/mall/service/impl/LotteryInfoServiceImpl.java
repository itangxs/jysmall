package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.LotteryInfo;
import cn.qhjys.mall.entity.LotteryInfoExample;
import cn.qhjys.mall.mapper.LotteryInfoMapper;
import cn.qhjys.mall.service.LotteryInfoService;

@Service("lotteryInfoService")
public class LotteryInfoServiceImpl implements LotteryInfoService {
	@Autowired
	private LotteryInfoMapper lotteryInfoMapper;

	@Override
	public int insertLotteryInfo(LotteryInfo lotteryInfo) {
		return lotteryInfoMapper.insertSelective(lotteryInfo);
	}

	@Override
	public LotteryInfo getLotteryInfo(String openId, Long userLotteryId) {
		LotteryInfoExample example = new LotteryInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId).andUserLottertIdEqualTo(userLotteryId);
		List<LotteryInfo> list = lotteryInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<LotteryInfo> listLotteryInfo(Long userLotteryId) {
		LotteryInfoExample example = new LotteryInfoExample();
		example.createCriteria().andUserLottertIdEqualTo(userLotteryId);
		return lotteryInfoMapper.selectByExample(example);
	}

}
