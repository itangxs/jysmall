package cn.qhjys.mall.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryExample;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelation;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelationExample;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardCouponTemplateExample;
import cn.qhjys.mall.entity.CardCouponTemplateExample.Criteria;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardDeliveryPropertyExample;
import cn.qhjys.mall.mapper.CardCouponDeliveryMapper;
import cn.qhjys.mall.mapper.CardCouponDeliveryTemplateRelationMapper;
import cn.qhjys.mall.mapper.CardCouponTemplateMapper;
import cn.qhjys.mall.mapper.CardDeliveryCountMapper;
import cn.qhjys.mall.mapper.CardDeliveryPropertyMapper;
import cn.qhjys.mall.mapper.custom.CardDeliveryCountVoMapper;
import cn.qhjys.mall.service.system.CardCouponService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.vo.system.CardDeliveryCountVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class CardCouponServiceImpl implements CardCouponService {
	@Autowired
	private CardCouponTemplateMapper cardCouponTemplateMapper;
	@Autowired
	private CardCouponDeliveryTemplateRelationMapper cardCouponDeliveryTemplateRelationMapper;
	@Autowired
	private CardDeliveryPropertyMapper cardDeliveryPropertyMapper;
	@Autowired
	private CardCouponDeliveryMapper cardCouponDeliveryMapper;
	@Autowired
	private CardDeliveryCountMapper cardDeliveryCountMapper;
	@Autowired
	private CardDeliveryCountVoMapper cardDeliveryCountVoMapper;
	
	/**
	 * 卡券列表
	 */
	@Override
	public Page<CardCouponTemplate> searchCardListByPage(Long storeId,
			String storeName, Integer couponCfg, Integer statusCfg,Integer deleveryType,
			Integer pageNum, Integer pageSize) throws Exception {
		CardCouponTemplateExample example = new CardCouponTemplateExample();
		Criteria criteria = example.createCriteria();
		if (null != storeId)
			criteria.andStoreIdEqualTo(storeId);

		if (null != storeName)
			criteria.andStoreNameEqualTo(storeName);

		if (null != couponCfg)
			criteria.andCouponCfgEqualTo(couponCfg);
		if (null != deleveryType){
			if (deleveryType == 1 || deleveryType == 2) {
				criteria.andStatusCfgBetween(1, 2);
			}else{
				criteria.andStatusCfgEqualTo(deleveryType);
			}
		}
		if (null != statusCfg)
			criteria.andStatusCfgEqualTo(statusCfg);
		criteria.andStatusCfgNotEqualTo(3);
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNum, pageSize);
		Page<CardCouponTemplate> page = (Page<CardCouponTemplate>) cardCouponTemplateMapper
				.selectByExample(example);
		return page;
	}
	/**
	 * 查询详情
	 */
	@Override
	public CardCouponTemplate queryCardCouponTempById(Long id) {
		// TODO Auto-generated method stub
		if (null == id) {
			return null;
		}
		return cardCouponTemplateMapper.selectByPrimaryKey(id);
	}
	/**
	 * 删除卡券
	 */
	@Override 
	public boolean deleteCardStatusCfg(Long id,boolean isDel, Long sellerId) throws Exception {
		if(id != null && sellerId != null){
			CardCouponTemplate cardTemplate = cardCouponTemplateMapper.selectByPrimaryKey(id);
			Integer statusCfg = cardTemplate.getStatusCfg();//投放状态
			CardCouponTemplate template = new CardCouponTemplate();
			template.setId(id);
			if(isDel){
				template.setStatusCfg(3);//把状态改成删除
			}else{
				template.setStatusCfg(0);
			}
			int selective = cardCouponTemplateMapper.updateByPrimaryKeySelective(template);

			if(selective>0){
				CardCouponDeliveryTemplateRelationExample example = new CardCouponDeliveryTemplateRelationExample();
				example.createCriteria().andSellerIdEqualTo(sellerId).andCardCouponTemplateIdEqualTo(id);
				List<CardCouponDeliveryTemplateRelation> list = cardCouponDeliveryTemplateRelationMapper.selectByExample(example);
				if(list != null){ 
					for(CardCouponDeliveryTemplateRelation relation:list){
						Long relationId = relation.getId();
						Long deliveryId = relation.getCardCouponDeliveryId();//投放ID
						if(statusCfg == 1){
							CardCouponDelivery cardDelivery = new CardCouponDelivery();
							cardDelivery.setId(deliveryId);
							cardDelivery.setProprietaryStatus(0);//自营投放状态设置未启用  
							cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardDelivery);
						}else if(statusCfg == 2){
							CardCouponDelivery cardDelivery = new CardCouponDelivery();
							cardDelivery.setId(deliveryId);
							cardDelivery.setPeripheralStatus(0);//周边投放状态设置未启用  
							cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardDelivery);
						}
						//删除关联表记录
						cardCouponDeliveryTemplateRelationMapper.deleteByPrimaryKey(relationId);
					}
				}
				return true;
			}
		}
		return true;
	}
	/**
	 * 更改投放状态为未投放
	 */
	@Override
	public boolean updateStatus(Long sellerId,Long id) throws Exception {
		if (null != sellerId && null != id) {
			CardCouponTemplate CardTemplate = cardCouponTemplateMapper.selectByPrimaryKey(id);
			if (CardTemplate.getStatusCfg() == 1 ) {
				CardTemplate.setStatusCfg(0);
			}else if( CardTemplate.getStatusCfg() == 2){
				CardTemplate.setStatusCfg(0);
			}
			int selective = cardCouponTemplateMapper.updateByPrimaryKeySelective(CardTemplate);
			if(selective > 0){
				//更改成功删除关联表数据
				CardCouponDeliveryTemplateRelationExample example = new CardCouponDeliveryTemplateRelationExample();
				example.createCriteria().andSellerIdEqualTo(sellerId).andCardCouponTemplateIdEqualTo(id);
				List<CardCouponDeliveryTemplateRelation> cardList = cardCouponDeliveryTemplateRelationMapper.selectByExample(example);
				for (CardCouponDeliveryTemplateRelation cardRelation : cardList) {
					cardCouponDeliveryTemplateRelationMapper.deleteByPrimaryKey(cardRelation.getId());
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * 查询卡券数据
	 */
	@Override
	public Page<CardDeliveryCountVo> queryCardDeliveryCount(Long storeId,
			String storeName,Integer couponCfg,String startDate,String endDate,Integer pageNum, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId",storeId );
		map.put("storeName",storeName);
		map.put("couponCfg", couponCfg);
		if (!StringUtils.isEmpty(startDate)) {
			map.put("startDate", startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			map.put("endDate", endDate);
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<CardDeliveryCountVo> page = cardDeliveryCountVoMapper.queryCardDeliveryCount(map);
		return page;
	}
	/**
	 * 查询投放数据
	 */
	@Override
	public Page<CardDeliveryCountVo> queryDeliverData(Long storeId,
			String storeName, Integer deliverType,String startDate,String endDate, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId",storeId );
		map.put("storeName",storeName );
		map.put("deliverType", deliverType);
		if (!StringUtils.isEmpty(startDate)) {
			map.put("startDate", startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			map.put("endDate", endDate);
		}
		
		PageHelper.startPage(pageNum, pageSize);
		Page<CardDeliveryCountVo> page = cardDeliveryCountVoMapper.queryDeliverData(map);
		return page;
	}
	/**
	 * 查询核销数据
	 */
	@Override
	public Page<CardDeliveryCountVo> queryValiData(Long storeId, String storeName,
			 Integer couponCfg,String startDate,String endDate,Integer pageNum,Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId",storeId );
		map.put("storeName",storeName );
		map.put("couponCfg", couponCfg);
		if (!StringUtils.isEmpty(startDate)) {
			map.put("startDate", startDate);
		}
		if (!StringUtils.isEmpty(endDate)) {
			map.put("endDate", endDate);
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<CardDeliveryCountVo> page = cardDeliveryCountVoMapper.queryValiData(map);
		return page;  
	}
	/**
	 * 根据type查找对象所有属性
	 */
	@Override
	public CardDeliveryProperty queryCardDeliveryByType(Integer type)
			throws Exception {
		CardDeliveryPropertyExample example = new CardDeliveryPropertyExample();
		example.createCriteria().andDeliverTypeEqualTo(type);
		List<CardDeliveryProperty> list = cardDeliveryPropertyMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	/**
	 * 根据sellerId查找对象所有属性
	 */
	@Override
	public CardCouponDelivery queryCardCouponDelivery(Long sellerId)
			throws Exception {
		CardCouponDeliveryExample example = new CardCouponDeliveryExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<CardCouponDelivery> list = cardCouponDeliveryMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	/**
	 * 设定自营投放
	 */
	@Override
	public int updateCardCouponZyDelivery(CardDeliveryProperty property)
			throws Exception {
		return cardDeliveryPropertyMapper.updateByPrimaryKeySelective(property);
	}
	/**
	 * 设定周边投放
	 */
	@Override
	public int updateCardCouponZbDelivery(CardDeliveryProperty property)
			throws Exception {
		return cardDeliveryPropertyMapper.updateByPrimaryKeySelective(property);
		
	}
	/**
	 * 设定公众号投放
	 */

	@Override
	public int updateCardCouponGzDelivery(CardDeliveryProperty property,
			Long sellerId) throws Exception {
		if(sellerId != null){
			CardCouponDeliveryExample example = new CardCouponDeliveryExample();
			example.createCriteria().andSellerIdEqualTo(sellerId);;
			List<CardCouponDelivery> list = cardCouponDeliveryMapper.selectByExample(example);
			if(list ==null || list.size()<1){
				return 2;
			}
			CardCouponDelivery cardCouponDelivery = list.get(0);
			cardCouponDelivery.setPushNum(property.getDeliveryNum());
			
			int update = cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardCouponDelivery);
//			int update = cardDeliveryPropertyMapper.updateByPrimaryKeySelective(property);
			if(update > 0){
				return 1;
			}
		}else{
			cardDeliveryPropertyMapper.updateByPrimaryKeySelective(property);
			return 1;
		}
		return -1;
	}
}
