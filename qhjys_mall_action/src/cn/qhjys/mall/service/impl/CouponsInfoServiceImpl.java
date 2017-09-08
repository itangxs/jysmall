package cn.qhjys.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.CouponsInfo;
import cn.qhjys.mall.entity.CouponsInfoExample;
import cn.qhjys.mall.entity.UserLottery;
import cn.qhjys.mall.entity.UserLotteryExample;
import cn.qhjys.mall.mapper.CouponsInfoMapper;
import cn.qhjys.mall.mapper.UserLotteryMapper;
import cn.qhjys.mall.service.CouponsInfoService;

@Service("couponsInfoService")
public class CouponsInfoServiceImpl implements CouponsInfoService {
	@Autowired
	private CouponsInfoMapper couponsInfoMapper;
	@Autowired
	private UserLotteryMapper userLotteryMapper;
	@Override
	public int insertCouponsInfo(CouponsInfo couponsInfo) {
		return couponsInfoMapper.insertSelective(couponsInfo);
	}

	@Override
	public List<CouponsInfo> listCouponsInfo( Long userLotteryId) {
		CouponsInfoExample example = new CouponsInfoExample();
		example.createCriteria().andUserLotteryIdEqualTo(userLotteryId);
		return couponsInfoMapper.selectByExample(example);
	}

	@Override
	public CouponsInfo getCouponsInfo(Long userLotteryId, Integer rank) {
		CouponsInfoExample example = new CouponsInfoExample();
		example.createCriteria().andUserLotteryIdEqualTo(userLotteryId).andRankEqualTo(rank);
		List<CouponsInfo> list = couponsInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int updateCouponsInfo(CouponsInfo couponsInfo) {
		return couponsInfoMapper.updateByPrimaryKeySelective(couponsInfo);
	}

	@Override
	public CouponsInfo getCouponsInfo(Long userLotteryId) {
		CouponsInfoExample example = new CouponsInfoExample();
		example.createCriteria().andUserLotteryIdEqualTo(userLotteryId);
		example.setOrderByClause("rank asc");
		List<CouponsInfo> list = couponsInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public Page<CouponsInfo> listCoupons(String openId, Long lotteryId,Integer pageNum,Integer pageSize) {
		UserLotteryExample example = new UserLotteryExample();
		example.createCriteria().andLotteryIdEqualTo(lotteryId).andOpenIdEqualTo(openId);
		List<UserLottery> list = userLotteryMapper.selectByExample(example);
		List<Long> ulids = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			ulids.add(list.get(i).getId());
		}
		CouponsInfoExample example2 = new CouponsInfoExample();
		example2.createCriteria().andUserLotteryIdIn(ulids).andStatusEqualTo(0);
		example2.setOrderByClause("rank asc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<CouponsInfo>) couponsInfoMapper.selectByExample(example2);
	}

	@Override
	public CouponsInfo getCouponsInfoById(Long id) {
		return couponsInfoMapper.selectByPrimaryKey(id);
	}

}
