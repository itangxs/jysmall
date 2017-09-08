package cn.qhjys.mall.service.system;

import cn.qhjys.mall.vo.system.ReviewProductVo;
import com.github.pagehelper.Page;

/***
 * 总后台商品评论接口
 * 
 * @author zengrong
 *
 */
public interface ReviewProductService {

	/***
	 * 总后台商品评论列表
	 * 
	 * @param productName
	 *            商品名称
	 * @param userName
	 *            评论人
	 * @param startTime
	 *            评论开始时间
	 * @param endTime
	 *            评论结束时间
	 * @param storeName
	 *            店铺名
	 * @param pageNum
	 *            分页条件
	 * @param PageSize
	 *            分页条件
	 * @return
	 */
	public Page<ReviewProductVo> queryReviewProductList(String productName, String userName, String startTime,
			String endTime, String storeName, Integer pageNum, Integer pageSize) throws Exception;

	/***
	 * 总后台删除商品评论
	 * 
	 * @param ids
	 *            评论id数组
	 * @return
	 */
	public boolean deleteReviewProduct(String[] ids) throws Exception;

}
