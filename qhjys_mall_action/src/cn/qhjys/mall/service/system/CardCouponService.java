package cn.qhjys.mall.service.system;

import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.vo.system.CardDeliveryCountVo;

import com.github.pagehelper.Page;

public interface CardCouponService {
	/**
	 * 查询卡券列表
	 * @param storeId
	 * 			商家ID
	 * @param storeName
	 * 			商家名称
	 * @param couponCfg
	 * 			投放类型
	 * @param statusCfg
	 * 			投放状态
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page<CardCouponTemplate> searchCardListByPage(Long storeId,String storeName,
			Integer couponCfg, Integer statusCfg,Integer deleveryType, Integer pageNum, Integer pageSize) throws Exception;
	
	/**
	 * 查询详情
	 */
	CardCouponTemplate queryCardCouponTempById(Long id) throws Exception;
	/**
	 * 删除投放状态
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	boolean deleteCardStatusCfg(Long id,boolean isDel ,Long sellerId) throws Exception;
	
	/**
	 * 更改投放状态
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	boolean updateStatus(Long sellerId,Long id) throws Exception;
	/**
	 * 设定自营投放
	 */
	int updateCardCouponZyDelivery(CardDeliveryProperty property) throws Exception;
	/**
	 * 设定周边投放
	 * @param property
	 * @return
	 * @throws Exception
	 */
	int updateCardCouponZbDelivery(CardDeliveryProperty property) throws Exception;
	/**
	 * 公众号推送次数设定
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateCardCouponGzDelivery(CardDeliveryProperty property,Long sellerId)throws Exception;
	/**
	 *  根据type查找对象所有属性
	 * @param type
	 * @return
	 * @throws Exception
	 */
	CardDeliveryProperty queryCardDeliveryByType(Integer type) throws Exception;
	/**
	 * 根据sellerId查找CardCouponDelivery对象信息
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	CardCouponDelivery queryCardCouponDelivery(Long sellerId) throws Exception;
	/**
	 * 查询卡券数据
	 * @param sellerId
	 * @param storeName
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Page<CardDeliveryCountVo> queryCardDeliveryCount(Long storeId,String storeName,Integer couponCfg,String startDate,String endDate,Integer pageNum, Integer pageSize) throws Exception;
	/**
	 * 查询投放数据
	 * @param sellerId
	 * @param storeName
	 * @param deliverType
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page<CardDeliveryCountVo> queryDeliverData(Long storeId,String storeName,Integer deliverType,String startDate,String endDate,Integer pageNum,Integer pageSize)throws Exception;
	/**
	 * 查询核销数据
	 * @param sellerId
	 * @param storeName
	 * @param couponCfg
	 * @return
	 * @throws Exception
	 */
	Page<CardDeliveryCountVo> queryValiData(Long storeId, String storeName,Integer couponCfg,String startDate,String endDate,Integer pageNum,Integer pageSize)throws Exception;
	
}
