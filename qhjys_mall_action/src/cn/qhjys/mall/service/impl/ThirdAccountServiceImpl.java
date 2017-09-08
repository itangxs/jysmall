package cn.qhjys.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.ThirdAccount;
import cn.qhjys.mall.entity.ThirdAccountExample;
import cn.qhjys.mall.mapper.ThirdAccountMapper;
import cn.qhjys.mall.service.ThirdAccountService;

@Service
public class ThirdAccountServiceImpl extends Base implements ThirdAccountService {
	@Autowired
	private ThirdAccountMapper thirdAccountMapper;

	@Override
	public ThirdAccount queryThirdAccount(Long accountId, String thirdCode) throws Exception {
		ThirdAccountExample example = new ThirdAccountExample();
		example.createCriteria().andUserIdEqualTo(accountId).andThirdCodeEqualTo(thirdCode);
		List<ThirdAccount> thirdAccounts = thirdAccountMapper.selectByExample(example);
		return thirdAccounts.size() > 0 ? thirdAccounts.get(0) : null;
	}

	@Override
	public boolean insertThirdAccount(ThirdAccount thirdAccount) throws Exception {
		int num = thirdAccountMapper.insertSelective(thirdAccount);
		return num > 0;
	}

}