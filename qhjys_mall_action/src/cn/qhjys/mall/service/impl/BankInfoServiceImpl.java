package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.BankInfoExample;
import cn.qhjys.mall.mapper.BankInfoMapper;
import cn.qhjys.mall.service.BankInfoService;

@Service("bankInfoService")
public class BankInfoServiceImpl implements BankInfoService{

	@Autowired
	BankInfoMapper bankInfoMapper;
	
	@Override
	public BankInfo queryBankInfoBySellerId(Long sellerId) throws Exception {
		if (sellerId == null) {
			return null;
		}
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId)
		.andEnabledEqualTo(1).andIsdefaultEqualTo(1);
		List<BankInfo> bankInfos = bankInfoMapper.selectByExample(example);
		if (bankInfos != null && bankInfos.size() > 0) {
			return bankInfos.get(0);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(BankInfo bankInfo) {
		return bankInfoMapper.updateByPrimaryKeySelective(bankInfo);
	}
	
}
