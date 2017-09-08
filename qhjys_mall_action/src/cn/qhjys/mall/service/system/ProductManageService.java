package cn.qhjys.mall.service.system;

import java.math.BigDecimal;

import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.vo.system.ProductManageVo;

import com.github.pagehelper.Page;

/***
 * 总后台商品管理接口
 * 
 * @author zengrong
 *
 */
public interface ProductManageService {

	/***
	 * 总后台商品列表
	 * 
	 * @param productName
	 *            商品名
	 * @param storeName
	 *            商户名
	 * @param startPrice
	 *            开始价格(搜索)
	 * @param endPrice
	 *            结束价格(搜索)
	 * @param startTime
	 *            开始时间(搜索)
	 * @param endTime
	 *            结束时间(搜索)
	 * @param enabled
	 *            是否启售(0禁售,1启售,2全部)
	 * @param pageNum
	 *            分页条件
	 * @param pageSize
	 *            分页条件
	 * @return
	 */
	public Page<ProductManageVo> queryManageProductList(String productName, String storeName, BigDecimal startPrice,
			BigDecimal endPrice, String startTime, String endTime, Long enabled, Integer pageNum, Integer pageSize)
			throws Exception;

	/***
	 * 根据商品编号修改总后台商品状态
	 * 
	 * @param enabled
	 *            是否启售
	 * @param ids
	 *            商品id数组
	 * @return
	 */
	public boolean updateProduct(int enabled, String[] ids) throws Exception;

	/**
	 * 获取商品详情
	 * 
	 * @param prodId
	 * @return
	 * @throws Exception
	 */
	public ProductInfo getProductById(Long prodId) throws Exception;

	/**
	 * 跟新商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateProductTitleById(Long prodId,Integer level, String keyword, String desc) throws Exception;
}
