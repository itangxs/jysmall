package cn.qhjys.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import cn.qhjys.mall.entity.Bargain;
import cn.qhjys.mall.entity.BargainExample;
import cn.qhjys.mall.entity.BargainInfo;
import cn.qhjys.mall.entity.BargainInfoExample;
import cn.qhjys.mall.entity.UserBargain;
import cn.qhjys.mall.entity.UserBargainExample;
import cn.qhjys.mall.mapper.BargainInfoMapper;
import cn.qhjys.mall.mapper.BargainMapper;
import cn.qhjys.mall.mapper.UserBargainMapper;
import cn.qhjys.mall.service.BargainService;

@Service("bargainService")
public class BargainServiceImpl implements BargainService {
	
	@Autowired
	private BargainMapper bargainMapper;
	@Autowired
	private UserBargainMapper userBargainMapper;
	@Autowired
	private BargainInfoMapper bargainInfoMapper;
	
	@Override
	public int insertBargain(Bargain bargain) {
		return bargainMapper.insertSelective(bargain);
	}

	@Override
	public int updateBargain(Bargain bargain) {
		return bargainMapper.updateByPrimaryKeySelective(bargain);
	}

	@Override
	public Bargain getBargain(Long id) {
		return bargainMapper.selectByPrimaryKey(id);
	}

	@Override
	public Bargain getLastBargain() {
		BargainExample example = new BargainExample();
		example.createCriteria().andStatusEqualTo(1);
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(1, 1);
		List<Bargain> list = bargainMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public UserBargain getUserBargainByOpenid(String openId,Long bargainId) {
		UserBargainExample example = new UserBargainExample();
		example.createCriteria().andBargainIdEqualTo(bargainId).andOpenIdEqualTo(openId);
		List<UserBargain> list = userBargainMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int insertUserBargain(UserBargain userBargain) {
		return userBargainMapper.insertSelective(userBargain);
	}

	@Override
	public List<BargainInfo> listBargainInfos(Long userBargainId) {
		BargainInfoExample example = new BargainInfoExample();
		example.createCriteria().andUserBargainIdEqualTo(userBargainId);
		example.setOrderByClause("create_time desc");
		return bargainInfoMapper.selectByExample(example);
	}

	@Override
	public BargainInfo getBargainInfoByOpenId(String openId, Long userBargainId) {
		BargainInfoExample example = new BargainInfoExample();
		example.createCriteria().andUserBargainIdEqualTo(userBargainId).andOpenIdEqualTo(openId);
		List<BargainInfo> list = bargainInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int insertBargainInfo(BargainInfo bargainInfo)throws Exception {
		return bargainInfoMapper.insertSelective(bargainInfo);
	}

	@Override
	public UserBargain getUserBargain(Long id) {
		return userBargainMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateUserBargain(UserBargain userBargain) {
		return userBargainMapper.updateByPrimaryKeySelective(userBargain);
	}

	@Override
	public int insertBargainInfo1(BargainInfo info) throws Exception {
		return bargainInfoMapper.insertsimple(info);
		
	}

}
