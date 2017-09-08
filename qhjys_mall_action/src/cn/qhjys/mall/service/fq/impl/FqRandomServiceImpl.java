package cn.qhjys.mall.service.fq.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqRandom;
import cn.qhjys.mall.entity.FqRandomExample;
import cn.qhjys.mall.mapper.FqRandomMapper;
import cn.qhjys.mall.service.fq.FqRandomService;

@Service("/fqRandomService")
public class FqRandomServiceImpl implements FqRandomService{

	@Autowired
	private FqRandomMapper fqRandomMapper;
	
	@Override
	public int insertFqRandom(List<FqRandom> fqRandoms) {
		for (int i = 0; i < fqRandoms.size(); i++) {
			fqRandomMapper.insertSelective(fqRandoms.get(i));
		}
		return 1;
	}

	@Override
	public int updateFqRandom(FqRandom fqRandom) {
		return fqRandomMapper.updateByPrimaryKeySelective(fqRandom);
	}

	@Override
	public FqRandom getFqRandom() {
		FqRandomExample example = new FqRandomExample();
		example.createCriteria().andExpiredTimeLessThan(new Date(System.currentTimeMillis()-86400000L*3));
		PageHelper.startPage(1, 1);
		List<FqRandom> list = fqRandomMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

}
