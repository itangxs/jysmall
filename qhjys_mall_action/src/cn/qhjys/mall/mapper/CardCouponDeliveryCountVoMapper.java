package cn.qhjys.mall.mapper;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.vo.seller.SellerCardCouponTemplateVo;

public interface CardCouponDeliveryCountVoMapper {
	List<SellerCardCouponTemplateVo> queryCardDeliveryCount(Map<String,Object> map);

	List<SellerCardCouponTemplateVo> queryDeliveryData(Map<String,Object> map);

	List<SellerCardCouponTemplateVo> queryValidateData(Map<String,Object> map);
	SellerCardCouponTemplateVo queryCountBytype(Map<String,Object> map);
	Integer queryCountByCoupon(Map<String,Object> map);
	
	
}