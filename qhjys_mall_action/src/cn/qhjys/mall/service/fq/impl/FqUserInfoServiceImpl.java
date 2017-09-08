package cn.qhjys.mall.service.fq.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.entity.FqThirdPayExample;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.custom.ThirdPayMapper;
import cn.qhjys.mall.service.fq.FqUserInfoService;

@Service("fqUserInfoService")
public class FqUserInfoServiceImpl implements FqUserInfoService {
	
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	@Autowired
	private ThirdPayMapper thirdPayMapper;
	@Autowired
	private FqThirdPayMapper fqthirdPayMapper;
	@Override
	public FqUserInfo getFqUserInfoById(Long id) {
		return fqUserInfoMapper.selectByPrimaryKey(id);
	}
	@Override
	public FqUserInfo updateFqUserInfo(String openId, String nickname, String portrait) {
		FqUserInfoExample example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example);
		FqUserInfo user = null;
		if (list.size()>0) {
			user= list.get(0);
			if (StringUtils.isEmpty(user.getNickName())||StringUtils.isEmpty(user.getPortrait())|| !user.getNickName().equals(nickname)|| !user.getPortrait().equals(portrait)) {
				if (!StringUtils.isEmpty(nickname)) {
					user.setNickName(nickname);
				}
				if (!StringUtils.isEmpty(portrait)) {
					user.setPortrait(portrait);
				}
				fqUserInfoMapper.updateByPrimaryKeySelective(user);
			}
		}else{
			user = new FqUserInfo();
			user.setCreateTime(new Date());
			user.setNickName(nickname);
			user.setOpenId(openId);
			user.setPortrait(portrait);
			fqUserInfoMapper.insertSelective(user);
		}
		return user;
	}
	@Override
	public FqUserInfo getFqUserInfoByOpenId(String openId) throws Exception {
		if (StringUtils.isEmpty(openId)) {
			return null;
		}
		FqUserInfoExample example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public int updateThirdPayByOpenid() {
		int tnum = fqthirdPayMapper.countByExample(new FqThirdPayExample());
		int a = 0;
		for (int i = 0; i < tnum; i+=100) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("beginNum", i);
			map.put("endNum", i+100);
			a+=thirdPayMapper.updateThirdPayByOpenid(map);
		}
		return a;
	}
	@Override
	public BigDecimal querySellerThirdPaySum(Long sellerId) {
		return thirdPayMapper.querySellerThirdPaySum(sellerId);
	}
	@Override
	public int querySellerByRegis(Long sellerId) {
		return thirdPayMapper.querySellerByRegis(sellerId);
	}

}
