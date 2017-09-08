package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;







import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Autowired
	private FqSellerStatementMapper fqSellerStatementMapper;

	@Override
	public int updateSellerStatement() {
		int a = 0;
		FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
		fqSellerStatementExample.createCriteria().andSellerIdEqualTo(1933L)
				.andStatementDateEqualTo(new Date());
		List<FqSellerStatement> fsslist = fqSellerStatementMapper
				.selectByExample(fqSellerStatementExample);
		FqSellerStatement fsstate = null;
		if (fsslist.size() > 0) {
			fsstate = fsslist.get(0);
			fsstate.setTotalMoney(fsstate.getTotalMoney().add(new BigDecimal(1)));
			fsstate.setTotalNum(fsstate.getTotalNum() + 1);
			a += fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
		}
		return a;
	}

}
