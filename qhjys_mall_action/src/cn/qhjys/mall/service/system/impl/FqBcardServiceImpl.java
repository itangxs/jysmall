package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinpay.ets.client.StringUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqArea;
import cn.qhjys.mall.entity.FqBcard;
import cn.qhjys.mall.entity.FqBcardExample;
import cn.qhjys.mall.entity.FqBcardExample.Criteria;
import cn.qhjys.mall.entity.FqBcardPrize;
import cn.qhjys.mall.entity.FqBcardPrizeExample;
import cn.qhjys.mall.entity.FqBcardRule;
import cn.qhjys.mall.entity.FqBcardRuleExample;
import cn.qhjys.mall.entity.FqCity;
import cn.qhjys.mall.entity.FqDistrict;
import cn.qhjys.mall.entity.FqIndustry;
import cn.qhjys.mall.mapper.FqBcardMapper;
import cn.qhjys.mall.mapper.FqBcardPrizeMapper;
import cn.qhjys.mall.mapper.FqBcardRuleMapper;
import cn.qhjys.mall.service.system.FqBcardService;
import cn.qhjys.mall.service.system.FqCityService;
import cn.qhjys.mall.service.system.FqIndustryService;
import cn.qhjys.mall.vo.system.FqBcardRuleNameVo;

@Service("bcardService")
public class FqBcardServiceImpl implements FqBcardService {
	
	@Autowired
	FqBcardMapper fqBcardMapper;
	@Autowired
	FqBcardPrizeMapper fqBcardPrizeMapper;
	@Autowired
	FqBcardRuleMapper fqBcardRuleMapper;
	@Autowired
	FqCityService fqCityService;
	@Autowired
	FqIndustryService fqIndustryService;

	@Override
	public boolean insertBcardAndPrizeAndRule(Long storeId,String storeName,String beginDate,String endDate,Integer validityDate,
			String cardDescript,Integer pushNum,String[] prizeName,String[] prizeLine,String[] prizeInfo, String[] imgs,
			Long[] city,Long[] district,Long[] area,Long[] industry,Long[] industryDetail) throws Exception {
		
		if (storeId == null || StringUtils.isEmpty(beginDate) || StringUtil.isEmpty(endDate) 
				|| validityDate == null || StringUtils.isEmpty(cardDescript) || pushNum == null) {
			return false;
		}
		if (prizeName.length != prizeInfo.length || prizeName.length != imgs.length) {
			return false;
		}
		if (city.length != district.length || city.length != area.length
				|| city.length != industry.length || city.length != industryDetail.length) {
			return false;
		}
		
		FqBcard fqBcard = new FqBcard();
		fqBcard.setStoreId(storeId);
		fqBcard.setStoreName(storeName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		fqBcard.setBeginDate(sdf.parse(beginDate));
		fqBcard.setEndDate(sdf.parse(endDate));
		fqBcard.setValidity(validityDate);
		fqBcard.setBcardInfo(cardDescript);
		fqBcard.setPushNum(pushNum);
		fqBcard.setStatus(0);
		fqBcard.setCreateTime(new Date());
		int bcardResult = fqBcardMapper.insertSelective(fqBcard);
		if (bcardResult <= 0) {
			throw new Exception("insert error");
		}
		
		for (int i = 0; i < prizeName.length; i++) {
			FqBcardPrize fqBcardPrize = new FqBcardPrize();
			fqBcardPrize.setBcardId(fqBcard.getId());
			fqBcardPrize.setPrizeName(prizeName[i]);
			fqBcardPrize.setPrizeLine(new BigDecimal(prizeLine[i]));
			fqBcardPrize.setPrizeInfo(prizeInfo[i]);
			fqBcardPrize.setPrizeImage(imgs[i]);
			int result = fqBcardPrizeMapper.insertSelective(fqBcardPrize);
			if (result <= 0) {
				throw new Exception("insert error");
			}
		}
		
		for (int i = 0; i < city.length; i++) {
			FqBcardRule bcardRule = new FqBcardRule();
			bcardRule.setBcardId(fqBcard.getId());
			bcardRule.setCityId(city[i]);
			bcardRule.setDistrictId(district[i]);
			bcardRule.setAreaId(area[i]);
			bcardRule.setIndustryId(industry[i]);
			bcardRule.setIndustryDetailId(industryDetail[i]);
			int result = fqBcardRuleMapper.insertSelective(bcardRule);
			if (result <= 0) {
				throw new Exception("insert error");
			}
		}
		return true;
	}

	@Override
	public Page<FqBcard> queryBcardByStoreName(String storeName, Integer pageNum, Integer pageSize) throws Exception {
		FqBcardExample example = new FqBcardExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(storeName)) {
			criteria.andStoreNameLike("%"+storeName+"%");
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		Page<FqBcard> datas = (Page<FqBcard>) fqBcardMapper.selectByExample(example);
		return datas;
	}

	@Override
	public boolean updateBcardStatusById(Long id, Integer status) throws Exception {
		if (id == null || status == null) {
			return false;
		}
		FqBcard bcard = new FqBcard();
		bcard.setId(id);
		bcard.setStatus(status);
		int result = fqBcardMapper.updateByPrimaryKeySelective(bcard);
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteBcardById(Long id) throws Exception {
		if (id == null) {
			return false;
		}
		int result = fqBcardMapper.deleteByPrimaryKey(id);
		return result > 0 ? true : false;
	}

	@Override
	public FqBcard queryBcard(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return fqBcardMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FqBcardPrize> queryBcardPrize(Long bcardId) throws Exception {
		if (bcardId == null) {
			return null;
		}
		FqBcardPrizeExample example = new FqBcardPrizeExample();
		example.createCriteria().andBcardIdEqualTo(bcardId);
		List<FqBcardPrize> fqBcardPrizes = fqBcardPrizeMapper.selectByExample(example);
		return fqBcardPrizes;
	}

	@Override
	public List<FqBcardRule> queryBcardRule(Long bcardId) throws Exception {
		if (bcardId == null) {
			return null;
		}
		FqBcardRuleExample example = new FqBcardRuleExample();
		example.createCriteria().andBcardIdEqualTo(bcardId);
		List<FqBcardRule> bcardRules = fqBcardRuleMapper.selectByExample(example);
		return bcardRules;
	}
	
	@Override
	public List<FqBcardRuleNameVo> queryBcardRuleName(Long bcardId) throws Exception {
		if (bcardId == null) {
			return null;
		}
		FqBcardRuleExample example = new FqBcardRuleExample();
		example.createCriteria().andBcardIdEqualTo(bcardId);
		List<FqBcardRule> bcardRules = fqBcardRuleMapper.selectByExample(example);
		if (bcardRules == null) {
			return null;
		}
		
		List<FqBcardRuleNameVo> nameVos = new ArrayList<>();
		for (int i = 0; i < bcardRules.size(); i++) {
			FqBcardRuleNameVo ruleNameVo = new FqBcardRuleNameVo();
			FqBcardRule fqBcardRule = bcardRules.get(i);
			FqCity fqCity = fqCityService.queryCityById(fqBcardRule.getCityId());
			ruleNameVo.setCityName(fqCity.getCity());
			FqDistrict fqDistrict = fqCityService.queryDistrictById(fqBcardRule.getDistrictId());
			ruleNameVo.setDistrictName(fqDistrict.getDistrict());
			FqArea fqArea = fqCityService.queryAreaById(fqBcardRule.getAreaId());
			ruleNameVo.setAreaName(fqArea.getArea());
			if (fqBcardRule.getIndustryId() == 1) {
				ruleNameVo.setIndustryName("餐饮");
			}else {
				ruleNameVo.setIndustryName("非餐饮");
			}
			FqIndustry fqIndustry = fqIndustryService.queryIndustryById(fqBcardRule.getIndustryDetailId());
			ruleNameVo.setIndustryDetailName(fqIndustry.getIndustry());
			nameVos.add(ruleNameVo);
		}
		return nameVos;
	}

	@Override
	public boolean updateFqBcardAndPrizeAndRule(Long bcardId,Long storeId, String beginDate, String endDate, Integer validityDate,
			String cardDescript, Integer pushNum,Long[] prizeIds,String[] prizeName, String[] prizeLine,
			String[] prizeInfo, String[] imgs, Long[] ruleIds,Long[] city, Long[] district,
			Long[] area, Long[] industry, Long[] industryDetail,
			Long[] cityNew,Long[] distNew,Long[] areaNew,Long[] induNew,Long[] indudeNew) throws Exception {
		
		if (storeId == null || StringUtils.isEmpty(beginDate) || StringUtil.isEmpty(endDate) 
				|| validityDate == null || StringUtils.isEmpty(cardDescript) || pushNum == null) {
			return false;
		}
		if (prizeIds.length != prizeName.length) {
			return false;
		}
		if (prizeName.length != prizeInfo.length || prizeName.length != imgs.length) {
			return false;
		}
		if (ruleIds.length != city.length) {
			return false;
		}
		if (city.length != district.length || city.length != area.length
				|| city.length != industry.length || city.length != industryDetail.length) {
			return false;
		}
		
		FqBcard fqBcard = new FqBcard();
		fqBcard.setId(bcardId);
		fqBcard.setStoreId(storeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		fqBcard.setBeginDate(sdf.parse(beginDate));
		fqBcard.setEndDate(sdf.parse(endDate));
		fqBcard.setValidity(validityDate);
		fqBcard.setBcardInfo(cardDescript);
		fqBcard.setPushNum(pushNum);
		fqBcard.setStatus(0);
		int bcardResult = fqBcardMapper.updateByPrimaryKeySelective(fqBcard);
		if (bcardResult <= 0) {
			throw new Exception("update error");
		}
		
		for (int i = 0; i < prizeName.length; i++) {
			FqBcardPrize fqBcardPrize = new FqBcardPrize();
			fqBcardPrize.setId(prizeIds[i]);
			fqBcardPrize.setBcardId(fqBcard.getId());
			fqBcardPrize.setPrizeName(prizeName[i]);
			fqBcardPrize.setPrizeLine(new BigDecimal(prizeLine[i]));
			fqBcardPrize.setPrizeInfo(prizeInfo[i]);
			fqBcardPrize.setPrizeImage(imgs[i]);
			int result = fqBcardPrizeMapper.updateByPrimaryKeySelective(fqBcardPrize);
			if (result <= 0) {
				throw new Exception("update error");
			}
		}
		
		for (int i = 0; i < city.length; i++) {
			FqBcardRule bcardRule = new FqBcardRule();
			bcardRule.setId(ruleIds[i]);
			bcardRule.setBcardId(fqBcard.getId());
			bcardRule.setCityId(city[i]);
			bcardRule.setDistrictId(district[i]);
			bcardRule.setAreaId(area[i]);
			bcardRule.setIndustryId(industry[i]);
			bcardRule.setIndustryDetailId(industryDetail[i]);
			int result = fqBcardRuleMapper.updateByPrimaryKeySelective(bcardRule);
			if (result <= 0) {
				throw new Exception("update error");
			}
		}
		
		//插入新的规则
		if (cityNew != null && cityNew.length > 0) {
			if (cityNew.length != distNew.length || cityNew.length != areaNew.length
					|| cityNew.length != induNew.length || cityNew.length != indudeNew.length) {
				throw new Exception("新增规则错误");
			}
			for (int i = 0; i < cityNew.length; i++) {
				FqBcardRule bcardRule = new FqBcardRule();
				bcardRule.setBcardId(fqBcard.getId());
				bcardRule.setCityId(cityNew[i]);
				bcardRule.setDistrictId(distNew[i]);
				bcardRule.setAreaId(areaNew[i]);
				bcardRule.setIndustryId(induNew[i]);
				bcardRule.setIndustryDetailId(indudeNew[i]);
				int result = fqBcardRuleMapper.insertSelective(bcardRule);
				if (result <= 0) {
					throw new Exception("insert error");
				}
			}
		}
		
		return true;
	}
	
}
