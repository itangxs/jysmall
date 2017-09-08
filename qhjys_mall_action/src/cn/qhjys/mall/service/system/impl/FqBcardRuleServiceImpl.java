package cn.qhjys.mall.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.mapper.FqBcardRuleMapper;
import cn.qhjys.mall.service.system.FqBcardRuleService;

@Service("fqBcardRuleService")
public class FqBcardRuleServiceImpl implements FqBcardRuleService{

	@Autowired
	FqBcardRuleMapper fqBcardRuleMapper;
	
	@Override
	public boolean deleteRuleById(Long ruleId) throws Exception {
		if (ruleId == null) {
			return false;
		}
		int result = fqBcardRuleMapper.deleteByPrimaryKey(ruleId);
		return result > 0 ? true : false;
	}
}
