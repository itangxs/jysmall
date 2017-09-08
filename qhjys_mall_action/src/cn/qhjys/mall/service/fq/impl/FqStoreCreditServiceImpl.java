package cn.qhjys.mall.service.fq.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqStoreCreditExample;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqThirdPayExample;
import cn.qhjys.mall.entity.FqStoreCreditExample.Criteria;
import cn.qhjys.mall.mapper.FqStoreCreditMapper;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.custom.FqStoreCreditVoMapper;
import cn.qhjys.mall.mapper.custom.ThirdPayMapper;
import cn.qhjys.mall.service.fq.FqStoreCreditService;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.vo.system.FqStoreCreditVo;

@Service("fqStoreCreditService")
public class FqStoreCreditServiceImpl implements FqStoreCreditService{
	
	@Autowired
	FqStoreCreditMapper fqStoreCreditMapper;
	@Autowired
	FqStoreCreditVoMapper fqStoreCreditVoMapper;
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private FqStoreMapper fqStoreMapper;
	@Autowired
	private ThirdPayMapper thirdPayMapper;
	
	@Override
	public Page<FqStoreCredit> queryFqStoreCredit(Long wxstoreid, Integer period,Integer page,Integer pagesize) throws Exception {
		FqStoreCreditExample example = new FqStoreCreditExample();
		Criteria criteria = example.createCriteria();
		if (wxstoreid != null) {
			criteria.andStoreIdEqualTo(wxstoreid);
		}
		if (period != null) {
			criteria.andPeriodEqualTo(period);
		}
		example.setOrderByClause("period desc");
		PageHelper.startPage(page, pagesize);
		Page<FqStoreCredit> list = (Page<FqStoreCredit>) fqStoreCreditMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean updateCreditStatus(Long[] ids, Integer status) throws Exception {
		if (status == 0) {
			return false;
		}
		
		for (int i = 0; i < ids.length; i++) {
			FqStoreCredit credit = fqStoreCreditMapper.selectByPrimaryKey(ids[i]);
			if (credit != null) {
				credit.setStatus(status);
				int k = fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
				if (k == 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Page<FqStoreCreditVo> queryFqStoreFromCredit(Long wxstoreid, String wxstorename, Integer pageNum,
			Integer pagesize) throws Exception {
		PageHelper.startPage(pageNum, pagesize);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("id", wxstoreid);
		map.put("storeName", wxstorename);
		Page<FqStoreCreditVo> fqStoreCreditVos = (Page<FqStoreCreditVo>) fqStoreCreditVoMapper.queryFqStoreFromCredit(map);	
		return fqStoreCreditVos;
	}

	@Override
	public FqStoreCredit queryLastCreditByStore(Long storeId) throws Exception {
		FqStoreCreditExample example = new FqStoreCreditExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		example.setOrderByClause("period desc");
		List<FqStoreCredit> list = fqStoreCreditMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean insertFqStoreCredit(FqStoreCredit fqStoreCredit) throws Exception {
		int result =  fqStoreCreditMapper.insertSelective(fqStoreCredit);
		return result > 0 ? true : false;
	}

	@Override
	public Page<FqStoreCredit> queryFqStoreCredit(String wxstorename, Integer page, Integer pagesize) throws Exception {
		FqStoreCreditExample example = new FqStoreCreditExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(wxstorename)) {
			criteria.andStoreNameLike("%"+wxstorename+"%");
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, pagesize);
		Page<FqStoreCredit> data = (Page<FqStoreCredit>) fqStoreCreditMapper.selectByExample(example);
		return data;
	}
	@Override
	public FqStoreCredit getLastCredit(Long sellerId) {
		FqStoreCreditExample example = new FqStoreCreditExample();
		example.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1);
		example.setOrderByClause("period desc ");
		List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example);
		return credits.size()>0?credits.get(0):null;
	}

	@Override
	public Page<FqThirdPay> queryFqThirdPayBySeller(Long sellerId,Integer pageSize,Integer pageNum) {
		if (pageSize == null) {
			pageSize = 10;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		FqThirdPayExample example = new FqThirdPayExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		example.setOrderByClause("pay_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqThirdPay>) fqThirdPayMapper.selectByExample(example);
	}

	@Override
	public boolean updateStoreCreditStatus() throws IOException {
		Date now = new Date();
		FqStoreCreditExample example = new FqStoreCreditExample();
		example.createCriteria().andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now);
		List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example);
		for (int i = 0; i < credits.size(); i++) {
			FqStoreCredit credit = credits.get(0);
			if (credit.getEndTime().getTime() <= now.getTime()) {
				credit.setStatus(2);
				fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
			}
			if (((now.getTime()/86400000)-(credit.getStartTime().getTime()/86400000))%7 == 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("sellerId", credit.getSellerId());
				map.put("beginTime", new Date(now.getTime() - 7*86400000L));
				map.put("endTime", now);
				BigDecimal num = thirdPayMapper.queryThirdPaySum(map);
				if (num.compareTo(credit.getWeekMoney())<0) {
					FqStore store = fqStoreMapper.selectByPrimaryKey(credit.getStoreId());
					String content = "店铺["+store.getStoreName()+"]七天营业额未到达预设每周还款额度:"+credit.getWeekMoney()+"元.";
					MessageUtil.sendSmsContent(store.getClerkPhone(), content);
				}
			}
		}
		return false;
	}

}
