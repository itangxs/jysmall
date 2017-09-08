package cn.qhjys.mall.service;

import java.util.Date;
import java.util.List;
import cn.qhjys.mall.vo.SellerScheduleVo;
import cn.qhjys.mall.vo.seller.SellerScheduleListVo;
import cn.qhjys.mall.vo.seller.SellerScheduleProductInfoVo;

/***
 * 商预约接口
 * 
 * @author c zhao
 *
 */
public interface SellerScheduleService {

	/**
	 * 根据商家的ID得到 某一个时间开始的七天的预约
	 * 
	 * @param id
	 *            商家ID
	 * @param date
	 *            开始时间
	 * @return
	 */
	public SellerScheduleListVo getSellerScheduleListVoBySellerId(Long id, Date date) throws Exception;

	/**
	 * 根据商家的ID得到上架产品的列表
	 * 
	 * @param id
	 *            商家ID
	 * @return
	 * @throws Exception
	 */
	public List<SellerScheduleProductInfoVo> getSellerScheduleProductInfoVoList(Long id) throws Exception;

	/**
	 * 得到某一个商家下面的某一个产品的信息
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param productId
	 *            产品ID
	 * @return
	 * @throws Exception
	 */
	public SellerScheduleProductInfoVo getSellerScheduleProductInfoVo(Long sellerId, Long productId) throws Exception;

	/**
	 * 更新某一个产品的预约信息
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param productId
	 *            产品ID
	 * @param scheduleType
	 *            是否预约
	 * @param scheduleer
	 *            最大预约数量
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductSchedule(Long sellerId, Long productId, Integer scheduleType, Integer scheduleer)
			throws Exception;

	/**
	 * 得到商家某一状态的预约数据
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<SellerScheduleVo> getSellerScheduleVoList(Long sellerId, int status, String phone) throws Exception;

	/**
	 * 得到商家订单预约数据大于某一状态的集合
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<SellerScheduleVo> getSellerScheduleVoListThan(Long sellerId, int status, String phone) throws Exception;

	/**
	 * 商家对某一订单的预约处理处理
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param id
	 *            预约ID
	 * @param status
	 *            需要修改的状态
	 * @return
	 * @throws Exception
	 */
	public boolean updateSellerSchedule(Long sellerId, Long id, Integer status) throws Exception;

	/**
	 * 得到商家商品某一状态的预约数据
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<SellerScheduleVo> getSellerScheduleVoBySellerList(Long id, int i, String phone) throws Exception;

	/**
	 * 得到商家商品预约数据大于某一状态的集合
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<SellerScheduleVo> getSellerScheduleVoBySellerListThan(Long id, int i, String phone) throws Exception;

}