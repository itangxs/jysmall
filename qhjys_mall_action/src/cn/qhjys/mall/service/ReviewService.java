package cn.qhjys.mall.service;

import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.ReviewVo;

import com.github.pagehelper.Page;

/***
 * 商品评论
 * 
 * @author zengrong
 *
 */
public interface ReviewService {

	/**
	 * 根据商品编号获取总体评论
	 * 
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	ReviewVo getReviewByPordId(Long prodId) throws Exception;

	/**
	 * 根据店铺编号获取总体评论
	 * 
	 * @param storeId
	 *            店铺编号
	 * @return
	 */
	ReviewVo getReviewByStoreId(Long storeId) throws Exception;

	/**
	 * 获取商品的评论(商品编号)
	 * 
	 * @param prodId
	 * @param level
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<EvaluateVo> selectReviewLevelByProdId(Long prodId, Integer level, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 获取商品的评论(店铺编号)
	 * 
	 * @param storeId
	 * @param level
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<EvaluateVo> selectReviewLevelByStoreId(Long storeId, Integer level, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 获取商品评论(商品编号、用户编号)
	 * 
	 * @param prodId
	 * @param userId
	 * @param status
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	Page<EvaluateVo> selectReviewByUserId(Long prodId, Long userId, Integer status, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 评论商品
	 * 
	 * @param reviews
	 * @return
	 */
	boolean addProductEvaluate(ReviewsLog reviews) throws Exception;

	/**
	 * 
	 * findEvaluateBySaUoSId 通过用户 订单详情 查询商品订单评论
	 * 
	 * @param prodId
	 *            产品ID
	 * @param id
	 *            用户ID
	 * @param long1
	 *            订单ID
	 * @return
	 * @throws Exception
	 */
	List<EvaluateVo> findEvaluateBySaUoSId(Long prodId, Long id, Long long1) throws Exception;
	
	boolean countReviewsLogByUserAndTime(Long userId,Date date,Long sellerId);
}
