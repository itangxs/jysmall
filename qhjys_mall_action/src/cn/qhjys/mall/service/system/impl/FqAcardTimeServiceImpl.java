package cn.qhjys.mall.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.mapper.FqAcardTimeMapper;
import cn.qhjys.mall.service.system.FqAcardTimeService;

@Service("fqAcardTimeService")
public class FqAcardTimeServiceImpl implements FqAcardTimeService {
	
	@Autowired
	FqAcardTimeMapper fqAcardTimeMapper;

	@Override
	public boolean deleteAcardTimeById(Long timeId) throws Exception {
		if (timeId == null) {
			return false;
		}
		int result = fqAcardTimeMapper.deleteByPrimaryKey(timeId);
		return result > 0 ? true : false;
	}

}
