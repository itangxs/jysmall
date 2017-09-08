package cn.qhjys.mall.service.fq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqStoreCheck;
import cn.qhjys.mall.entity.FqStoreCheckExample;
import cn.qhjys.mall.entity.FqStoreCheckExample.Criteria;
import cn.qhjys.mall.mapper.FqStoreCheckMapper;
import cn.qhjys.mall.service.fq.FqStoreCheckService;

@Service("fqStoreCheckService")
public class FqStoreCheckServiceImpl implements FqStoreCheckService{
	
	@Autowired
	private FqStoreCheckMapper fqStoreCheckMapper;

	@Override
	public FqStoreCheck queryFqStoreCheck(Long storeId) {
		FqStoreCheckExample example = new FqStoreCheckExample();
		Criteria criteria = example.createCriteria();
		if(null != storeId){
			criteria.andStoreIdEqualTo(storeId);
		}
		example.setOrderByClause("create_time desc");
		List<FqStoreCheck> fsc = fqStoreCheckMapper.selectByExample(example);
		return fsc.size()>0 ? fsc.get(0) : null;
	}
 
}
