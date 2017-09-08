package cn.qhjys.mall.service.fq.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.entity.FqAcardUserExchange;
import cn.qhjys.mall.entity.FqAcardUserExchangeExample;
import cn.qhjys.mall.entity.FqAcardUserExchangeExample.Criteria;
import cn.qhjys.mall.entity.FqAcardUserRecord;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.mapper.FqAcardUserExchangeMapper;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.service.fq.FqAcardUserExchangeService;
import cn.qhjys.mall.service.fq.FqAcardUserRecordService;
import cn.qhjys.mall.service.fq.FqUserInfoService;

@Service("fqAcardUserExchangeService")
public class FqAcardUserExchangeServiceImpl implements FqAcardUserExchangeService{
	
	@Autowired
	FqAcardUserExchangeMapper fqAcardUserExchangeMapper;
	@Autowired
	FqAcardUserRecordService fqAcardUserRecordService;
	@Autowired
	FqUserInfoService fqUserInfoService;
	@Autowired
	WeixinUserinfoService weixinUserinfoService;
	
	@Override
	public int insertAcardUserExchangeOnce(Long userId, Long recordId, Long prizeId, Long storeId)
			throws Exception {
		
		if (userId == null || recordId == null || prizeId == null
				 || storeId == null) {
			return -1;
		}
		
		FqAcardUserExchangeExample example = new FqAcardUserExchangeExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andAcardRecordIdEqualTo(recordId);
		criteria.andAcardPrizeIdEqualTo(prizeId);
		criteria.andStoreIdEqualTo(storeId);
		List<FqAcardUserExchange> exchanges = fqAcardUserExchangeMapper.selectByExample(example);
		if (exchanges != null && exchanges.size() > 0) {
			// 已兑换过
			return 0;
		}
		
		FqAcardUserRecord acardUserRecord = fqAcardUserRecordService.queryAcardUserRecordById(recordId);
		FqAcardUserExchange exchange = new FqAcardUserExchange();
		exchange.setAcardRecordId(recordId);
		exchange.setAcardId(acardUserRecord.getAcardId());
		exchange.setUserId(userId);
		exchange.setAcardPrizeId(prizeId);
		exchange.setStoreId(storeId);
		Date today = new Date();
		exchange.setStartTime(today);
		long endtime = (today.getTime() + 15 * 24 * 60 * 60 * 1000L);
		exchange.setEndTime(new Date(endtime));
		exchange.setStatus(0);// 未成功兑换
		exchange.setCreateTime(new Date());
		int result = fqAcardUserExchangeMapper.insertSelective(exchange);
		if (result > 0) {
			boolean updateResult = fqAcardUserRecordService.updateAcardUserRecordById(recordId, 1);//0参与中,1已兑换
			if (updateResult) {
				// 判断是否关注
				FqUserInfo fqUserInfo = fqUserInfoService.getFqUserInfoById(userId);
				WeixinUserinfo weixinUserinfo = weixinUserinfoService.getWeixinUserinfo(fqUserInfo.getOpenId());
				if (weixinUserinfo == null) {
					// 未关注
					return 1;
				}else {
					// 已关注
					FqAcardUserExchange temp = new FqAcardUserExchange();
					temp.setId(exchange.getId());
					temp.setStatus(1);// 成功兑换
					boolean tempResult = updateAcardUserExchangeByKey(temp);
					if (tempResult) {
						return 2;
					}
				}
			}
		}
		
		return -1;
	}

	@Override
	public boolean updateAcardUserExchangeByKey(FqAcardUserExchange acardUserExchange) throws Exception {
		if (acardUserExchange == null) {
			return false;
		}
		int result = fqAcardUserExchangeMapper.updateByPrimaryKeySelective(acardUserExchange);
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateAcardUserExchangeByOpenId(String openId) throws Exception {
		if (StringUtils.isEmpty(openId)) {
			return false;
		}
		FqUserInfo fqUserInfo = fqUserInfoService.getFqUserInfoByOpenId(openId);
		if (fqUserInfo != null) {
			FqAcardUserExchangeExample example = new FqAcardUserExchangeExample();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(fqUserInfo.getId());
			criteria.andStatusEqualTo(0);
			long halfBefore = System.currentTimeMillis()-30 * 60 * 1000L;
			criteria.andCreateTimeGreaterThanOrEqualTo(new Date(halfBefore));
			List<FqAcardUserExchange> exchanges = fqAcardUserExchangeMapper.selectByExample(example);
			if (exchanges != null && exchanges.size() > 0) {
				for (int i = 0; i < exchanges.size(); i++) {
					FqAcardUserExchange exchange = exchanges.get(i);
					exchange.setStatus(1);
					fqAcardUserExchangeMapper.updateByPrimaryKeySelective(exchange);
				}
				return true;
			}
		}
		return false;
	}
	
}
