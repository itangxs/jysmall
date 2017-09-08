package cn.qhjys.mall.service.seller;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelation;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryCount;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.vo.seller.SellerCardCouponTemplateVo;

import com.github.pagehelper.Page;

public interface SellerCardCouponService {
	/**
	 * 卡券列表
	 * @param storeId
	 * @param statusCfg
	 * @param couponCfg
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<CardCouponTemplate> querySellerCardCoupon(Long sellerId, Integer statusCfg, Integer couponCfg, Integer pageNum, Integer pageSize);
	
	/**
	 * 卡券详情
	 * @param id
	 * @return
	 */
	public CardCouponTemplate getSellerCardCoupon(Long id);
	
	public CardCouponTemplate getSellerCardCouponTemplate(Long sellerId, Long id);
	
	/**
	 * 创建卡券
	 * @param cardCouponTemplate
	 * @return
	 */
	public int insertSellerCardCoupon(CardCouponTemplate cardCouponTemplate);
	
	/**
	 * 删除卡券
	 * @param id
	 * @return
	 */
	public int deleteCardCoupon(Long id,CardCouponTemplate cardCouponTemplate);
	
	public List<CardCoupon> querySellerCardCoupon(Long sellerId, Integer statusCfg, Integer getWay);
	
	/**
	 * 核销页面的卡券数据
	 * @param storeId
	 * @param couponCfg
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<SellerCardCouponTemplateVo> queryCardDeliveryCount(Long sellerId, Integer couponCfg, String startTime, String endTime, Integer pageNum, Integer pageSize);
	
	/**
	 * 核销页面的投放数据
	 * @param storeId
	 * @param couponCfg
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<SellerCardCouponTemplateVo> queryDeliveryData(Long sellerId, Integer couponCfg, String startTime, String endTime, Integer pageNum, Integer pageSize);
	/**
	 * 核销页面的核销数据
	 * @param storeId
	 * @param validateDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<SellerCardCouponTemplateVo> queryValidateData(Long sellerId, Integer couponCfg, String startTime, String endTime, Integer pageNum, Integer pageSize);
	/**
	 * 卡券认证
	 * @param storeId
	 * @param code
	 * @return
	 */
	public CardCoupon identifyCardCoupon(Long sellerId, String code);
	
	/**
	 * 确定认证后修改卡券状态以及核销时间
	 * @param cardCoupon
	 * @return
	 */
	public int updateCardCoupon(CardCoupon cardCoupon);
	
	/**
	 * 获取投放方式的数据
	 * @param sellerId
	 * @return
	 */
	public CardCouponDelivery queryCardCouponDelivery(Long sellerId);
	
	/**
	 * 获取投放方式统计表数据
	 * @param sellerId
	 * @param deliverType
	 * @return
	 */
	public CardDeliveryCount queryCardDeliveryCount(Long sellerId, Integer deliverType);
	
	/**
	 * 根据cardTemplateId获取投放方式统计表数据
	 * @param cardTemplateId
	 * @return
	 */
	public CardDeliveryCount queryCardDeliveryCount(Long cardTemplateId);
	
	public List<CardCouponTemplate> queryCardCouponTemplate(Long sellerId, Integer deliverType);
	
	/**
	 * 向卡券关系表插入数据
	 * @param cardCouponDeliveryTemplateRelation
	 * @return
	 */
	public int insertCardCouponDeliveryTemplateRelation(CardCouponDeliveryTemplateRelation cardCouponDeliveryTemplateRelation);
	
	public List<Map<String, String>> queryCouponTemplateBySellerIdAndStatusCfg(Long sellerId, Integer statusCfg);
	
	public int updateStatusCfg(Long cardId, CardCouponTemplate cardCouponTemplate);
	public int updateCardCouponDelivery(CardCouponDelivery cardCouponDelivery);
	
	public SellerCardCouponTemplateVo queryCountBytype(Long sellerId,Integer deliverType,String countDate);
	public Integer queryCountByCoupon(Long sellerId,Integer getWay,Integer statusCfg,String receiveDate,String validateDate);

}
