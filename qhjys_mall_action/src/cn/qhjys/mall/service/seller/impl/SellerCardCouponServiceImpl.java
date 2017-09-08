package cn.qhjys.mall.service.seller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryExample;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelation;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelationExample;
import cn.qhjys.mall.entity.CardCouponExample;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardCouponTemplateExample;
import cn.qhjys.mall.entity.CardDeliveryCount;
import cn.qhjys.mall.entity.CardDeliveryCountExample;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqThirdPayExample;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserInfoExample;
import cn.qhjys.mall.mapper.CardCouponDeliveryCountVoMapper;
import cn.qhjys.mall.mapper.CardCouponDeliveryMapper;
import cn.qhjys.mall.mapper.CardCouponDeliveryTemplateRelationMapper;
import cn.qhjys.mall.mapper.CardCouponMapper;
import cn.qhjys.mall.mapper.CardCouponTemplateMapper;
import cn.qhjys.mall.mapper.CardDeliveryCountMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.UserInfoMapper;
import cn.qhjys.mall.service.seller.SellerCardCouponService;
import cn.qhjys.mall.vo.seller.SellerCardCouponTemplateVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("sellerCardCouponService")
public class SellerCardCouponServiceImpl implements SellerCardCouponService{
	@Autowired
	private CardCouponTemplateMapper cardCouponTemplateMapper;
	@Autowired
	private CardCouponMapper cardCouponMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private CardCouponDeliveryMapper cardCouponDeliveryMapper;
	@Autowired
	private CardDeliveryCountMapper cardDeliveryCountMapper;
	@Autowired
	private CardCouponDeliveryTemplateRelationMapper cardCouponDeliveryTemplateRelationMapper;
	@Autowired
	private CardCouponDeliveryCountVoMapper cardCouponDeliveryCountVoMapper;
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	
	@Override
	public Page<CardCouponTemplate> querySellerCardCoupon(Long sellerId, Integer statusCfg, Integer couponCfg, Integer pageNum, Integer pageSize) {
		CardCouponTemplateExample example = new CardCouponTemplateExample();
		if (sellerId != null && null != statusCfg && statusCfg != 3 && null != couponCfg) {
			example.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgEqualTo(statusCfg).andCouponCfgEqualTo(couponCfg);
		}else if (sellerId != null && null != statusCfg && statusCfg != 3) {
			example.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgEqualTo(statusCfg);
		}else{
			example.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgNotEqualTo(statusCfg);
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<CardCouponTemplate> page = (Page<CardCouponTemplate>) cardCouponTemplateMapper.selectByExample(example);
		return page;
	}
	
	@Override
	public CardCouponTemplate getSellerCardCoupon(Long id) {
		CardCouponTemplateExample example = new CardCouponTemplateExample();
		if (null != id) {
			example.createCriteria().andIdEqualTo(id);
		}
		List<CardCouponTemplate> list = cardCouponTemplateMapper.selectByExample(example);
		return list.size()>0 ? list.get(0):null;
	}
	
	@Override
	public CardCouponTemplate getSellerCardCouponTemplate(Long sellerId, Long id) {
		CardCouponTemplateExample example = new CardCouponTemplateExample();
		if (null != sellerId && null != id) {
			example.createCriteria().andSellerIdEqualTo(sellerId).andIdEqualTo(id);
		}
		List<CardCouponTemplate> list = cardCouponTemplateMapper.selectByExample(example);
		return list.size()>0 ? list.get(0):null;
	}
	
	@Override
	public int insertSellerCardCoupon(CardCouponTemplate cardCouponTemplate) {
		return cardCouponTemplateMapper.insertSelective(cardCouponTemplate);
	}

	@Override
	public int deleteCardCoupon(Long id,CardCouponTemplate cardCouponTemplate) {
		if (null != id && null != cardCouponTemplate) {
			CardCouponDeliveryTemplateRelationExample rExample = new CardCouponDeliveryTemplateRelationExample();
			rExample.createCriteria().andSellerIdEqualTo(cardCouponTemplate.getSellerId()).andCardCouponTemplateIdEqualTo(cardCouponTemplate.getId());
			List<CardCouponDeliveryTemplateRelation> list = cardCouponDeliveryTemplateRelationMapper.selectByExample(rExample);
			if(list.size()>0){
				CardCouponDeliveryTemplateRelation ccdtr = list.get(0);
				int rm = cardCouponDeliveryTemplateRelationMapper.deleteByPrimaryKey(ccdtr.getId());
				if(rm > 0){
					CardCouponDeliveryExample dExample = new CardCouponDeliveryExample();
					dExample.createCriteria().andIdEqualTo(ccdtr.getCardCouponDeliveryId());
					List<CardCouponDelivery> deliveryList = cardCouponDeliveryMapper.selectByExample(dExample);
					if(deliveryList.size() > 0){
						CardCouponDelivery cardCouponDelivery = deliveryList.get(0);
						if(cardCouponTemplate.getStatusCfg() == 1){
							cardCouponDelivery.setProprietaryStatus(0);
						}else if(cardCouponTemplate.getStatusCfg() == 2){
							cardCouponDelivery.setPeripheralStatus(0);
						}
						int dm = cardCouponDeliveryMapper.updateByPrimaryKey(cardCouponDelivery);
						if(dm > 0){
							CardCouponTemplateExample example = new CardCouponTemplateExample();
							example.createCriteria().andIdEqualTo(id);
							cardCouponTemplate.setStatusCfg(3);
							int res = cardCouponTemplateMapper.updateByExample(cardCouponTemplate,example);
							return res;
						}
					}else{
						CardCouponTemplateExample example = new CardCouponTemplateExample();
						example.createCriteria().andIdEqualTo(id);
						cardCouponTemplate.setStatusCfg(3);
						int res = cardCouponTemplateMapper.updateByExample(cardCouponTemplate,example);
						return res;
					}
				}
			}else{
				CardCouponTemplateExample example = new CardCouponTemplateExample();
				example.createCriteria().andIdEqualTo(id);
				cardCouponTemplate.setStatusCfg(3);
				int res = cardCouponTemplateMapper.updateByExample(cardCouponTemplate,example);
				return res;
			}
		}
		return -1;
	}
	
	@Override
	public List<CardCoupon> querySellerCardCoupon(Long sellerId, Integer statusCfg, Integer getWay) {
		CardCouponExample example = new CardCouponExample();
		if (sellerId != null && null != getWay && null != statusCfg) {
			example.createCriteria().andSellerIdEqualTo(sellerId).andGetWayEqualTo(getWay).andStatusCfgEqualTo(statusCfg);
			List<CardCoupon> cardCoupon = cardCouponMapper.selectByExample(example);
			return cardCoupon;
		}
		return null;
	}

	@Override
	public CardCouponDelivery queryCardCouponDelivery(Long sellerId) {
		CardCouponDeliveryExample example = new CardCouponDeliveryExample();
		if(null != sellerId){
			example.createCriteria().andSellerIdEqualTo(sellerId);
		}
		List<CardCouponDelivery> list = cardCouponDeliveryMapper.selectByExample(example);
		return list.size()>0 ? list.get(0):null;
	}
	
	@Override
	public CardDeliveryCount queryCardDeliveryCount(Long sellerId, Integer deliverType) {
		CardDeliveryCountExample example = new CardDeliveryCountExample();
		if(null != sellerId){
			example.createCriteria().andSellerIdEqualTo(sellerId).andDeliverTypeEqualTo(deliverType);
		}
		List<CardDeliveryCount> list = cardDeliveryCountMapper.selectByExample(example);
		return list.size()>0 ? list.get(0):null;
	} 
	
	@Override
	public CardDeliveryCount queryCardDeliveryCount(Long cardTemplateId) {
		CardDeliveryCountExample example = new CardDeliveryCountExample();
		if(null != cardTemplateId){
			example.createCriteria().andCardTemplateIdEqualTo(cardTemplateId);
		}
		List<CardDeliveryCount> list = cardDeliveryCountMapper.selectByExample(example);
		return list.size()>0 ? list.get(0):null;
	}
	
	@Override
	public List<CardCouponTemplate> queryCardCouponTemplate(Long sellerId, Integer deliverType){
		CardCouponTemplateExample example = new CardCouponTemplateExample();
		if (sellerId != null) {
			example.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgEqualTo(deliverType);
		}
		return cardCouponTemplateMapper.selectByExample(example);
	}
	
	@Override
	public CardCoupon identifyCardCoupon(Long sellerId, String code) {
		List<CardCoupon> cardCoupon = new ArrayList<CardCoupon>();
		if (sellerId != null && null != code) {
			CardCouponExample example = new CardCouponExample();
			example.createCriteria().andSellerIdEqualTo(sellerId).andCodeEqualTo(code);
			cardCoupon = cardCouponMapper.selectByExample(example);
		}
		return cardCoupon.size()>0 ? cardCoupon.get(0):null;
	}
	
	@Override
	public int updateCardCoupon(CardCoupon cardCoupon){
		return cardCouponMapper.updateByPrimaryKey(cardCoupon);
	}

	@Override
	public int insertCardCouponDeliveryTemplateRelation(
			CardCouponDeliveryTemplateRelation cardCouponDeliveryTemplateRelation) {
		if(null != cardCouponDeliveryTemplateRelation){
			return cardCouponDeliveryTemplateRelationMapper.insert(cardCouponDeliveryTemplateRelation);
		}
		return -1;
	}

	
	@Override
	public List<Map<String, String>> queryCouponTemplateBySellerIdAndStatusCfg(
			Long sellerId, Integer statusCfg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("statusCfg", statusCfg);
		List<Map<String, String>> list = cardCouponTemplateMapper.queryCouponTemplateBySellerIdAndStatusCfg(map);
		return list;
	}

	@Override
	public int updateStatusCfg(Long cardId, CardCouponTemplate cardCouponTemplate) {
		CardCouponTemplateExample example = new CardCouponTemplateExample();
		example.createCriteria().andIdEqualTo(cardId);
		return cardCouponTemplateMapper.updateByExample(cardCouponTemplate, example);
	}

	@Override
	public int updateCardCouponDelivery(CardCouponDelivery cardCouponDelivery) {
		return cardCouponDeliveryMapper.updateByPrimaryKey(cardCouponDelivery);
	}
	
	@Override
	public Page<SellerCardCouponTemplateVo> queryCardDeliveryCount(
			Long sellerId, Integer couponCfg, String startTime, String endTime,
			Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == startTime || startTime == ""){
			startTime = null;
		}
		if(null == endTime || endTime == ""){
			endTime = null;
		}
		map.put("sellerId", sellerId);
		map.put("couponCfg", couponCfg);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<SellerCardCouponTemplateVo>) cardCouponDeliveryCountVoMapper.queryCardDeliveryCount(map);
	}

	@Override
	public Page<SellerCardCouponTemplateVo> queryDeliveryData(Long sellerId,
			Integer getWay, String startTime, String endTime,
			Integer pageNum, Integer pageSize) {
		if(null == getWay || getWay.equals("")){
			getWay = null;
		}
		if(null == startTime || startTime == ""){
			startTime = null;
		}
		if(null == endTime || endTime == ""){
			endTime = null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("sellerId", sellerId);
		map.put("getWay", getWay);
		
		PageHelper.startPage(pageNum, pageSize);
		return (Page<SellerCardCouponTemplateVo>) cardCouponDeliveryCountVoMapper.queryDeliveryData(map);
	}

	@Override
	public Page<SellerCardCouponTemplateVo> queryValidateData(Long sellerId,
			Integer couponCfg, String startTime, String endTime,
			Integer pageNum, Integer pageSize) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == startTime || startTime == ""){
			startTime = null;
		}
		if(null == endTime || endTime == ""){
			endTime = null;
		}
		map.put("sellerId", sellerId);
		map.put("couponCfg", couponCfg);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<SellerCardCouponTemplateVo>) cardCouponDeliveryCountVoMapper.queryValidateData(map);
	}

	@Override
	public SellerCardCouponTemplateVo queryCountBytype(Long sellerId,
			Integer deliverType, String countDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sellerId", sellerId);
		map.put("deliverType", deliverType);
		if (!StringUtils.isEmpty(countDate)) {
			map.put("countDate", countDate);
		}
		return cardCouponDeliveryCountVoMapper.queryCountBytype(map);
	}

	@Override
	public Integer queryCountByCoupon(Long sellerId, Integer getWay,
			Integer statusCfg, String receiveDate, String validateDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sellerId", sellerId);
		if (!StringUtils.isEmpty(getWay)) {
			map.put("getWay", getWay);
		}
		if (!StringUtils.isEmpty(statusCfg)) {
			map.put("statusCfg", statusCfg);
		}
		if (!StringUtils.isEmpty(receiveDate)) {
			map.put("receiveDate", receiveDate);
		}
		if (!StringUtils.isEmpty(validateDate)) {
			map.put("validateDate", validateDate);
		}
		return cardCouponDeliveryCountVoMapper.queryCountByCoupon(map);
	}

	

	

}
