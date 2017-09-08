package cn.qhjys.mall.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardLotteryDetail;
import cn.qhjys.mall.entity.CardUserLottery;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.vo.CardCouponVo;
import cn.qhjys.mall.vo.PushCardVo;
import cn.qhjys.mall.vo.RebateStoreVo;

import com.github.pagehelper.Page;


/**
 * 微信商城服务类
 * @author llw
 * @date 20160309
 *
 */
public interface WxMallService {
	
     /**
      * 分页查询打折店铺列表
      * @param map
      * @return
      */
	Page<RebateStoreVo> selectStoreRebateList(Integer pageNum, Integer pageSize);
	 /**
     * 根据rebateId查询具体打折信息
     */
    Map<String, Object> selectRebateDetailById(Long rebateId);
		
	 /**
     * 根据订单id查询订单详情信息
     */
    Map<String, Object> selectOrderDetailById(Long orderId);
	
    /**
     * 根据商家ID查询微信授权状态
     */
	public Integer selectScope(Long rebateId);

	/**
	 * 查询周边投放开启的商家
	 * @throws Exception 
	 */
	public List<Map> insertAndSelectSellerPeripheralList(Long orderId) throws Exception;
	
	public CardCouponDelivery selectSellerDeliveryStatus(Long orderId);
	
	public CardUserLottery insertCardUserLotteryInfo(Integer statusCfg,String openId,Long sellerId,Long deliveryId,Long orderId);
	
	public List<Map> selectSellerDeliveryCardTemplateList(Integer statusCfg,Long sellerId);
	
	public List<CardLotteryDetail> selectIsUserLottery(Long userLotteryId,String openId,String forOpenId,Long orderId);
	
	public int insertCardLotteryDetail(Long userLotteryId,String openId,Long cardTemplateId, Long orderId, String forOpenId);
	
	public Map<String, List<Map>> selectUserFriendLottery(Long orderId,String openId,Long userLotteryId);
	
	public CardCoupon selectUserAvailableCardCoupon(BigDecimal totalPay,Long storeId,String openId);
	
	public List<CardCoupon> selectUserAllCardCoupon(Long storeId,String openId);
	/**
	 * 我的卡包  卡券列表
	 * @param openId  用户id
	 * @param couponCfg 卡券类型
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<CardCouponVo> getMyCardCoupon(String openId,Long couponCfg,Integer pageNum, Integer pageSize);
	
	
	public CardCoupon getMyCardCouponDetail(Long cardCouponId);
	
	public AppResult insertUserCardCoupon(String openId,Long cardCoupontemplateId,Integer source, Long userLotteryId, Integer isFriend, String forOpenId);
	
	public boolean updateCardCouponShareCount(Long userLotteryId);
	
	CardDeliveryProperty selectDeliveryRankProbability(Integer statusCfg);
	public boolean insertPushCard(PushCardVo cardVo);
	
	public CardCouponTemplate getCardCouponTemplateById(Long id);
	
}
