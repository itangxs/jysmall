package cn.qhjys.mall.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardPrizeExample;
import cn.qhjys.mall.mapper.FqAcardPrizeMapper;
import cn.qhjys.mall.service.system.FqAcardPrizeService;

@Service("fqAcardPrizeService")
public class FqAcardPrizeServiceImpl implements FqAcardPrizeService{

	@Autowired
	FqAcardPrizeMapper fqAcardPrizeMapper;
	
	@Override
	public List<FqAcardPrize> queryAcardPrizeByAcardId(Long acardId) throws Exception {
		if (acardId == null) {
			return null;
		}
		
		FqAcardPrizeExample example = new FqAcardPrizeExample();
		example.createCriteria().andAcardIdEqualTo(acardId);
		return fqAcardPrizeMapper.selectByExample(example);
	}
	
}
