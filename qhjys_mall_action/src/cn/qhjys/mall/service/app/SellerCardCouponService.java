package cn.qhjys.mall.service.app;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.util.AppResult;

public interface SellerCardCouponService {
	
	   int insertSelective(CardCouponTemplate template);
	  
	   public CardCouponTemplate queryCardCouponTemplateById(Long cardCouponTemplateId);
	   
	   Page<CardCouponTemplate> querySellerCardCouponBySellerId(Long sellerId,boolean equals,Integer statusCfg,Integer pageNum, Integer pageSize)
				throws Exception ;
	   
	   List<Map<String, String>> queryCouponTemplateBySellerIdAndStatusCfg(Long sellerId,Integer statusCfg);
	  
	   List<CardCouponDelivery> insertOrqueryCardCouponDeliveryById(Long sellerId);
	   
	   boolean deleteByPrimaryKey(Long sellerId,boolean isDel,Long cardCouponTemplateId);
	  
	   Page<CardCoupon> querySellerValidateCardCoupon(Long sellerId,Integer pageNum, Integer pageSize) 	throws Exception;

	   AppResult updateByCouponCode(Long sellerId, String code);
	   
	   String[] updateCouponRelation(Long sellerId,Integer cfg, Long cardCouponTemplateId,Integer sort);
	    
	   boolean updateCouponDeliveryStatus(Long sellerId,Integer cfg,Long deliveryId);
	   
	   List<String> queryConsumerBySellerId(Long sellerId);

	   int updateReducePushNum(Long sellerId);
	   
	   int updateAllSellerDeliveryPushNum();
	   
	   public void updateSellerStatementByQuartz();
	   
	   public CardDeliveryProperty getCardDeliveryPropertyByCfg(Integer cfg);
	   public int insertCardCouponDelivery(CardCouponDelivery cardCouponDelivery);
}
