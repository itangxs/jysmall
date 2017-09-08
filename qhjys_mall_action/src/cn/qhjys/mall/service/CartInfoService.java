package cn.qhjys.mall.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.qhjys.mall.vo.CartProdVo;

/**
 * 购物车
 * 
 * @author LiXiang
 *
 */
public interface CartInfoService {

	/**
	 * 获取用户购物车的商品
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	List<CartProdVo> queryCartByUser(Long userId) throws Exception;

	/**
	 * 判断商品是否正在出售
	 * 
	 * @param prodId
	 * @return
	 */
	boolean hasSellProduct(Long prodId) throws Exception;

	/**
	 * 添加商品到购物车
	 * 
	 * @param userId
	 *            用户编号
	 * @param prodId
	 *            商品编号
	 * @param prodNum
	 *            商品数量
	 * @return
	 */
	boolean addCartProd(Long userId, Long prodId,Long storeId, BigDecimal payPrice, int prodNum) throws Exception;

	/**
	 * 修改购物车商品数量
	 * 
	 * @param prodId
	 *            商品编号
	 * @param prodNum
	 *            商品数量
	 * @param userId
	 *            用户编号
	 * @return
	 */
	boolean updateCartProdNum(Long prodId,Long storeId ,Integer prodNum, Long userId,Date createTime) throws Exception;

	/**
	 * 删除购物车商品
	 * 
	 * @param userId
	 *            用户编号
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	boolean deleteCartProduct(Long userId, Long prodId,Date createTime,Long storeId) throws Exception;
}