package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.CollectInfo;
import cn.qhjys.mall.vo.CollectVo;

/**
 * 商品收藏
 * 
 * @author jxp
 */
public interface CollectService {

	/**
	 * 查询用户的收藏
	 * 
	 * @param userId
	 *            用户编号
	 * @param type
	 *            收藏类型(0 店铺，1 商品)
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	public List<CollectVo> queryUserCollect(Long userId, Integer type, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 判断商品是否收藏
	 * 
	 * @param userId
	 * @param prodId
	 * @return
	 */
	public boolean judgeProductIsCollect(Long userId, Long prodId) throws Exception;

	/**
	 * 判断店铺是否收藏
	 * 
	 * @param userId
	 * @param storeId
	 * @return
	 */
	public boolean judgeStroeIsCollect(Long userId, Long storeId) throws Exception;

	/**
	 * 添加评论
	 * 
	 * @param id
	 *            商品ID
	 * @param uid
	 *            用户ID
	 * @param star
	 *            星级
	 * @param evalute_lv
	 *            评价等级
	 * @param evalute_explain
	 *            评价说明
	 * @param imgurl
	 *            图片地址
	 * @throws Exception
	 * @return void
	 */
	public void saveEvaluate(Long id, Long uid, Integer star, Integer evalute_lv, String evalute_explain, String imgurl)
			throws Exception;

	/**
	 * 添加用户收藏
	 * 
	 * @param userId
	 *            用户编号
	 * @param prodId
	 *            商品编号
	 * @param tag
	 *            收藏标签
	 * @param remark
	 *            收藏说明
	 * @return
	 * @throws Exception
	 */
	public boolean insertUserCollect(Long userId, Long prodId, Long storeId, String tag, String remark)
			throws Exception;

	/**
	 * 删除用户收藏
	 * 
	 * @param prodId
	 *            产品编号
	 * @param storeId
	 *            店铺编号
	 * @param userId
	 *            用户编号
	 * @return void
	 */
	public boolean deleteUserCollect(Long prodId, Long storeId, Long userId) throws Exception;
	
	public CollectInfo queryCollectInfo(Long userId ,Long productId);

}
