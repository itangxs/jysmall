package cn.qhjys.mall.service.fq;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.vo.FqTypeAndProductVo;


/**
 * 菜品管理
 *  
 * @author apple
 */
public interface FqProductService {

	Page<FqProduct> searchProductListByStoreId(Long storeId, String name, Integer status,
			String tjsjs, String tjsje, Integer pageNum, Integer pageSize)
			throws Exception;
	
	Page<FqProduct> searchProductListByProductType(Long productType, Long storeId, String name, Integer status,  
			String createStartTime, String createEndTime, Integer pageNum, Integer pageSize)
			throws Exception;
	
	/***
	 * 添加菜品(后台)
	 * 
	 * @param product
	 * @throws Exception
	 */
	boolean saveProduct(FqProduct product) throws Exception;
	
	
	/**
	 * 根据店铺编号获取菜品详情
	 * 
	 * @param storId
	 *            店铺编号
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	FqProduct getProductInfoById(Long storeId, Long prodId) throws Exception;
	
	/**
	 * 更新菜品信息
	 * @param product
	 * @return
	 * @throws Exception
	 */
	boolean updateProduct(FqProduct product) throws Exception;
	
	boolean updateProductStatus(Long [] ids,Integer status);
	
	boolean deleteProductByIds(Long [] ids);
	
	/**
	 * 删除菜系
	 * @param id
	 * @return
	 */
	boolean deleteProductTypeById(Long[] ids);

	/**
	 * 
	 * queryproductTypeListByStore 通过 微信店铺ID和可以用状态查找菜品系列 
	 * @param storeid 店铺ID
	 * @param enable 是否可用(ps 可null)
	 * @return
	 * @throws Exception
	 */
	List<FqProductType> queryproductTypeListByStore(Long storeid, Integer enable)throws Exception;
	
	/**
	 * 
	 * queryproductTypePageByStore 分页查询
	 * @param storeid  店铺id
	 * @param name   菜系名称
	 * @param enable 是否可用
	 * @param tjsje  添加开始
	 * @param tjsjs  添加结束
	 * @param page  页数
	 * @param pagesize 每页多少
	 * @return
	 * @throws Exception
	 */
	Page<FqProductType> queryproductTypePageByStore(Long storeid, String name, Integer enable, String tjsjs, String tjsje, Integer page, int pagesize)throws Exception;
	
	List<FqProductType> queryproductTypePageByStore(Long storeid, String name, Integer enable, String createStartTime, String createEndTime)throws Exception;

	/**
	 * 
	 * queryFqProductTypeBystorIdAndId
	 * @param storId 商家ID
	 * @param id  菜系ID
	 * @return
	 * @throws Exception
	 */
	FqProductType queryFqProductTypeBystorIdAndId(Long storId, Long id)throws Exception;

	/**
	 * 
	 * saveOrUpdateFqProductType
	 * @param storeid  店铺ID
	 * @param storeName 店铺名称
	 * @param id2     菜系id 可空
	 * @param typeName   菜系名称
	 * @param level   排序等级
	 * @param enable  是否开启
	 * @return   true 成功  false失败
	 * @throws Exception 
	 */
	Boolean saveOrUpdateFqProductType(Long storeid, String storeName, Long id2, String typeName, Integer level,
			Integer enable)throws Exception;

	/**
	 * 
	 * updateProductTypeStatusByStoreId 通过店铺ID修改 该店铺的菜品 
	 * @param storeid 
	 * @param ids
	 * @param status
	 * @return
	 * @throws Exception
	 */
	boolean updateProductTypeStatusByStoreId(Long storeid, Long[] ids, Integer status)throws Exception;
	
	
	List<FqTypeAndProductVo> queryProductByTypesAndStoreId(List<FqProductType> productTypes,Long storeId)throws Exception;
	
	List<FqProduct> queryProductsWithOrderedByIdsAndStoreId(Long[] ids,Long storeId) throws Exception;
	
	FqProduct queryProductByIdAndStoreId(Long id,Long storeId) throws Exception;
	
	/**
	 * 菜系排序
	 * @param ids
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	List<FqProductType> updateProductTypeSortByLevel(Long[] ids, Long storeId) throws Exception;
	
	/**
	 * 菜品排序
	 * @param ids
	 * @param storeId
	 * @param prodId
	 * @return
	 * @throws Exception
	 */
	List<FqProduct> updateProductSortByLevel(Long[] ids, Long storeId, Long prodType) throws Exception;
}
