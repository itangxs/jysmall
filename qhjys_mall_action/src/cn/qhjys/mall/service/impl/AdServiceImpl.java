package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.AdInfoExample;
import cn.qhjys.mall.entity.AdInfoExample.Criteria;
import cn.qhjys.mall.entity.AdPosition;
import cn.qhjys.mall.mapper.AdInfoMapper;
import cn.qhjys.mall.mapper.AdPositionMapper;
import cn.qhjys.mall.service.AdService;

@Service("adService")
public class AdServiceImpl implements AdService {
	@Autowired
	private AdPositionMapper adPositionMapper;
	@Autowired
	private AdInfoMapper adInfoMapper;

	@Override
	public List<AdPosition> listAdPositions() {
		return adPositionMapper.selectByExample(null);
	}

	@Override
	public int insertAdInfo(AdInfo adInfo) {
		return adInfoMapper.insertSelective(adInfo);
	}

	@Override
	public int deleteAdInfoById(Long id) {
		return adInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public AdInfo getAdInfoByAp(Long apId,Long cityId) {
		Date date = new Date();
		AdInfoExample example = new AdInfoExample();
		example.createCriteria().andPositionIdEqualTo(apId).andStartTimeLessThanOrEqualTo(date)
		.andEndTimeGreaterThanOrEqualTo(date).andCityIdEqualTo(cityId);
		example.setOrderByClause("start_time desc");
		List<AdInfo> list = adInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<AdInfo> listAdInfoByAp(Long apId,Long cid,int page,int pageSize) {
		Date date = new Date();
		AdInfoExample example = new AdInfoExample();
		example.createCriteria().andPositionIdEqualTo(apId).andStartTimeLessThanOrEqualTo(date)
		.andEndTimeGreaterThanOrEqualTo(date).andCityIdEqualTo(cid);
		example.setOrderByClause("start_time desc");
		PageHelper.startPage(page, pageSize);
		List<AdInfo> list = adInfoMapper.selectByExample(example);
		return list;
	}

	@Override
	public Page<AdInfo> listAdInfo(Long cityId,Long apId, int page, int pageSize) {
		AdInfoExample example = new AdInfoExample();
		Criteria criteria = example.createCriteria();
		if (apId != null) {
			criteria.andPositionIdEqualTo(apId);
		}
		if (cityId != null) {
			criteria.andCityIdEqualTo(cityId);
		}
		example.setOrderByClause("start_time desc");
		PageHelper.startPage(page, pageSize);
		Page<AdInfo> list = (Page<AdInfo>) adInfoMapper.selectByExample(example);
		return list;
	}

}
