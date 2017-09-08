package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.vo.AppProdVo;
import cn.qhjys.mall.vo.system.ProductManageVo;
import com.github.pagehelper.Page;

public interface ProductMapper {

	/**
	 * 根据类别搜索商品
	 * 
	 * @param map
	 * @return
	 */
	Page<ProductInfo> searchProductByCategory(Map<String, Object> map);

	/**
	 * 根据关键字搜索商品
	 * 
	 * @param map
	 *            查询条件
	 * @return
	 */
	Page<ProductInfo> searchProductByKeyWord(Map<String, Object> map);

	/***
	 * 根据商品编号和商家编号删除商品
	 * 
	 * @param map
	 * @return
	 */
	int deleteProductById(Map<String, Long> map);

	/***
	 * 根据商品编号和商家编号修改商品信息
	 * 
	 * @param map
	 * @return
	 */
	int updateProduct(Map<String, Object> map);

	/**
	 * 查找最近浏览商品
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	Page<AppProdVo> selectBrowsedProduct(@Param("userId") Long userId);

	/**
	 * 查询商店
	 * 
	 * @param area
	 * @return
	 */
	Page<Map<String, Object>> selectStore(@Param("city") Long city, @Param("category") Long category);

	/**
	 * 查找附件的店铺
	 * 
	 * @param lngtd
	 *            经度
	 * @param lattd
	 *            维度
	 * @param range
	 *            范围
	 * @return
	 */
	Page<Map<String, Object>> selectNearbyStore(@Param("lngtd") Double lngtd, @Param("lattd") Double lattd,
			@Param("range") Integer range, @Param("category") Long category);

	/**
	 * 查询店铺商品
	 * 
	 * @param storeId
	 *            店铺编号
	 * @param category
	 *            商品类别
	 * @param city
	 *            所在城市
	 * @param column
	 *            排序字段
	 * @return
	 */
	Page<AppProdVo> selectStoreProduct(@Param("storeId") Long storeId, @Param("category") Long category,
			@Param("city") Long city, @Param("column") String column);

	/**
	 * 关键字查找商品
	 * 
	 * @param keyword
	 *            关键字
	 * @param cityId
	 *            所在城市
	 * @param areaId
	 *            所在区域
	 * @param category
	 *            商品类别
	 * @param column
	 *            排序字段
	 * @return
	 */
	Page<AppProdVo> searchAppProduct(@Param("keyword") String keyword, @Param("cityId") Long cityId,
			@Param("areaId") Long areaId, @Param("category") Long category, @Param("column") String column);

	/**
	 * 查找附件的商品
	 * 
	 * @param lngtd
	 *            经度
	 * @param lattd
	 *            维度
	 * @param range
	 *            范围
	 * @param keyword
	 *            关键字
	 * @param cityId
	 *            所在城市
	 * @param areaId
	 *            所在区域
	 * @param category
	 *            商品类别
	 * @param column
	 *            排序字段
	 * @return
	 */
	Page<AppProdVo> selectNearbyProduct(@Param("lngtd") Double lngtd, @Param("lattd") Double lattd,
			@Param("range") Integer range, @Param("keyword") String keyword, @Param("cityId") Long cityId,
			@Param("areaId") Long areaId, @Param("category") Long category, @Param("column") String column);

	/**
	 * 获取商品基本信息
	 * 
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	AppProdVo getProductInfoById(@Param("prodId") Long prodId, @Param("prodNum") Integer prodNum);

	/**
	 * 查找随机的五个商品
	 * 
	 * @return
	 */
	List<ProductInfo> selectProductInfoByRand(Long cityId);

	/**
	 * 获取购买过的商品
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	Page<AppProdVo> queryProdByBought(@Param("userId") Long userId);

	/**
	 * 
	 * @param map
	 * @return
	 */
	List<ProductManageVo> queryManageProductList(Map<String, Object> map);

	/**
	 * 自动下架的商品
	 * 
	 * @return
	 */
	Integer updateAutoShelfProduct();

}