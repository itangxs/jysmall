package cn.qhjys.mall.service.fq.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.FqWithdrawChangeinfo;
import cn.qhjys.mall.entity.FqWithdrawChangeinfoExample;
import cn.qhjys.mall.entity.FqWithdrawChangeinfoExample.Criteria;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.mapper.FqWithdrawChangeinfoMapper;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.fq.FqWithdrawChangeInfoService;
import cn.qhjys.mall.util.DateUtils;

@Service("fqWithdrawChangeInfoService")
public class FqWithdrawChangeInfoServiceImpl implements FqWithdrawChangeInfoService{

	@Autowired
	FqWithdrawChangeinfoMapper fqWithdrawChangeinfoMapper;
	@Autowired
	SellerService sellerService;
	
	@Override
	public boolean updateOrInsertWithdrawChangeInfo(Long sellerId,
			Integer statusAfter) throws Exception {
		if (sellerId == null || statusAfter == null) {
			return false;
		}
		SellerInfo sellerInfo = sellerService.getSellerById(sellerId);
		FqWithdrawChangeinfoExample example = new FqWithdrawChangeinfoExample();
		Criteria criteria = example.createCriteria();
		Date today = new Date();
		criteria.andCreateTimeGreaterThanOrEqualTo(DateUtils.getFirstSecondOfDay(today));
		criteria.andCreateTimeLessThanOrEqualTo(DateUtils.getLastSecondOfDay(today));
		criteria.andSellerIdEqualTo(sellerId);
		example.setOrderByClause("create_time desc");
		List<FqWithdrawChangeinfo> changeinfos = fqWithdrawChangeinfoMapper.selectByExample(example);
		if (changeinfos != null && changeinfos.size() > 0) {
			FqWithdrawChangeinfo changeinfo = changeinfos.get(0);
			changeinfo.setChangeDate(new Date());
			changeinfo.setStatusBefore(sellerInfo.getWithdrawStatus());
			changeinfo.setStatusAfter(statusAfter);
			int result = fqWithdrawChangeinfoMapper.updateByPrimaryKeySelective(changeinfo);
			return result > 0;
		}
		
		FqWithdrawChangeinfo changeinfo = new FqWithdrawChangeinfo();
		changeinfo.setSellerId(sellerId);
		changeinfo.setStatusBefore(sellerInfo.getWithdrawStatus());
		changeinfo.setStatusAfter(statusAfter);
		changeinfo.setChangeDate(new Date());
		changeinfo.setCreateTime(new Date());
		int result = fqWithdrawChangeinfoMapper.insertSelective(changeinfo);
		return result > 0;
	}

	@Override
	public List<FqWithdrawChangeinfo> queryFqWithdrawChangeInfoByDate(Date date) throws Exception {
		if (date == null) {
			return null;
		}
		// distinct
		FqWithdrawChangeinfoExample example = new FqWithdrawChangeinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andChangeDateGreaterThanOrEqualTo(DateUtils.getFirstSecondOfDay(date));
		criteria.andChangeDateLessThanOrEqualTo(DateUtils.getLastSecondOfDay(date));
		return fqWithdrawChangeinfoMapper.selectByExample(example);
	}

	@Override
	public FqWithdrawChangeinfo queryFqWithdrawChangeInfoBySellerIdAndDate(Long sellerId, Date date) throws Exception {
		if (sellerId == null || date == null) {
			return null;
		}
		FqWithdrawChangeinfoExample example = new FqWithdrawChangeinfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andChangeDateGreaterThanOrEqualTo(DateUtils.getFirstSecondOfDay(date));
		criteria.andChangeDateLessThanOrEqualTo(DateUtils.getLastSecondOfDay(date));
		criteria.andSellerIdEqualTo(sellerId);
		List<FqWithdrawChangeinfo> changeinfos = fqWithdrawChangeinfoMapper.selectByExample(example);
		if (changeinfos != null && changeinfos.size() > 0) {
			return changeinfos.get(0);
		}
		return null;
	}
}
