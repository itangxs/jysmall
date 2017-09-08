package cn.qhjys.mall.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.vo.AppProdVo;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.ProdSchedule;

import com.github.pagehelper.Page;

/**
 * 商品接口
 * 
 * @author zengrong
 *
 */
public interface ProductService {

	/**
	 * 根据类别查询商品
	 * 
	 * @param list
	 *            商品类别
	 * @param cityId
	 *            城市编号
	 * @param clause
	 *            排序字段
	 * @param pageSize
	 *            查询数量
	 * @return
	 */
	Map<String, List<ProductInfo>> searchProductByCategory(List<CategoryInfo> list, Long cityId, String clause,
			int pageSize) throws Exception;

	/**
	 * 根据类别搜索商品
	 * 
	 * @param category
	 *            类别
	 * @param cityId
	 *            所在城市
	 * @param area
	 *            所在区域
	 * @param maxUse
	 *            使用人数
	 * @param priceRange
	 *            价格区间
	 * @param clause
	 *            排序字段
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<ProductInfo> searchProductByCategory(Long category, Long cityId, Long area, Integer maxUse,
			Integer priceRange, String clause, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 根据关键字搜索商品
	 * 
	 * @param keywork
	 *            检索名称
	 * @param category
	 *            商品类别
	 * @param cityId
	 *            所在城市
	 * @param area
	 *            所在区域
	 * @param priceRange
	 *            价格区间
	 * @param store
	 *            店铺编号
	 * @param clause
	 *            排序字段
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<ProductInfo> searchProductByKeyWord(String keywork, Long category, Long cityId, Long area, Integer priceRange,
			Long store, String clause, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 根据编号获取商品信息
	 * 
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	ProductInfo getProductInfoById(Long prodId) throws Exception;

	/**
	 * 根据店铺编号获取商品详情
	 * 
	 * @param storId
	 *            店铺编号
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	ProductInfo getProductInfoById(Long storId, Long prodId) throws Exception;

	/**
	 * 获取商店的其他商品
	 * 
	 * @param storeId
	 * @return
	 */
	List<ProductInfo> selectProductByStoreId(Long storeId) throws Exception;

	/**
	 * 随机取得5条商品
	 * 
	 * @return
	 */
	public List<ProductInfo> searchProductListByRand(Long cityId) throws Exception;

	/***
	 * 查询评论列表(后台)
	 *
	 * @param sellerId
	 *            商家编号
	 * @param productName
	 *            商品名
	 * @param pageNum
	 *            分页条件
	 * @param pageSize
	 *            分页条件
	 * @return
	 */
	Page<EvaluateVo> queryEvaluateList(Long sellerId, String productName, int pageNum, int pageSize) throws Exception;

	/**
	 * 获取预定商品
	 * 
	 * @param userId
	 *            用户编号
	 * @param status
	 *            订单状态
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<ProdSchedule> queryScheduleProduct(Long userId, Integer status, int pageNum, int pageSize) throws Exception;

	/**
	 * 取消预定
	 * 
	 * @param schedule
	 *            预定编号
	 * @param userId
	 *            用户编号
	 * @return
	 * @throws Exception
	 */
	boolean deleteScheduleProduct(Long schedule, Long userId) throws Exception;

	/***
	 * 查询商品列表(后台)
	 * 
	 * @param long1
	 *            商家ID
	 * @param productName
	 *            商品名称
	 * @param status
	 *            (审核状态：0未审核，1不通过，2通过，3已下架) 商品审核状态
	 * @param addStartTime
	 *            添加时间(开始)
	 * @param addEndTime
	 *            添加时间(结束)
	 * @param stopStartTime
	 *            截止时间(开始)
	 * @param stopEndTime
	 *            截止时间(结束)
	 * @param pageNum
	 *            分页条件
	 * @param pageSize
	 *            分页条件
	 * @return
	 */
	Page<ProductInfo> searchProductList(Long long1, String productName, String status, String addStartTime,
			String addEndTime, String stopStartTime, String stopEndTime, int pageNum, int pageSize)
			throws ParseException;

	/***
	 * 添加商品(后台)
	 * 
	 * @param product
	 * @throws Exception
	 */
	boolean saveProduct(ProductInfo product) throws Exception;

	/***
	 * 根据商品编号查询对应的商品信息(后台)
	 * 
	 * @param prodId
	 * @return
	 */
	ProductInfo findProduct(Long prodId) throws Exception;

	/***
	 * 修改商品(后台)
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean updateProduct(Long sellerId, ProductInfo product) throws Exception;

	/***
	 * 删除商品(后台)
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param productId
	 *            商品编号数组
	 * @return
	 * @throws Exception
	 */
	boolean deleteProduct(Long sellerId, Long[] productId) throws Exception;

	/**
	 * 推荐商品
	 * 
	 * @param city
	 *            所在城市
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<AppProdVo> queryRecommendProd(Long city, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 查询商品
	 * 
	 * @param sellerid
	 *            商家id
	 * @param status
	 *            状态
	 * @param enabled
	 *            是否启用
	 * @param category_id
	 *            类别
	 * @param prod_type
	 *            实物还是虚拟
	 * @return
	 * @throws Exception
	 */
	public List<ProductInfo> findProductList(Long sellerid, Integer status, Integer enabled, Long category_id,
			Integer prod_type) throws Exception;

	/**
	 * 通过商家ID 找到店铺
	 * 
	 * @param sid
	 *            商家ID
	 * @return
	 * @throws Exception
	 * @return StoreInfo
	 */
	public StoreInfo queryStoreBySid(Long sid) throws Exception;

	/***
	 * 根据评论编号查询评论详情
	 * 
	 * @param reviewId
	 *            评论编号
	 * @return
	 * @throws Exception
	 */
	public EvaluateVo queryEvaluateDetail(Long reviewId) throws Exception;

	/**
	 * 修改商品状态
	 * 
	 * @param sellId
	 *            商家ID
	 * @param prodIds
	 *            产品数组
	 * @param status
	 *            状态
	 * @return
	 * @throws Exception
	 */
	boolean updateProduct(Long sellId, Long[] prodIds, Integer status) throws Exception;

	/**
	 * 添加用户浏览记录
	 * 
	 * @param userId
	 * @param prodId
	 * @return
	 * @throws Exception
	 */
	boolean addUserBrowsed(Long userId, Long prodId) throws Exception;

	/***
	 * 商家回复评论
	 * 
	 * @param review
	 *            回复内容对象
	 * @return
	 * @throws Exception
	 */
	boolean insertSellerEvaluate(ReviewsLog review) throws Exception;

	/**
	 * 商品自动下架
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean updateAutoShelfProduct() throws Exception;
	
	public List<ProductInfo> listProductInfoBycity(int pageNum,int pageSize,Long cityId);
}