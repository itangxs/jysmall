package cn.qhjys.mall.service.system;

import java.util.Date;
import cn.qhjys.mall.vo.system.SalesDetailVo;
import cn.qhjys.mall.vo.system.SalesofGoodsVo;
import cn.qhjys.mall.vo.system.ShopAreaVo;
import cn.qhjys.mall.vo.system.ShopSalesVo;
import com.github.pagehelper.Page;

public interface SystemOperateService {

	/**
	 * 
	 * querySalesofGoodsVoPage ·商品销售排行报表 分页
	 * 
	 * @param proName
	 * 
	 * @param storName
	 * 
	 * @return
	 */
	Page<SalesofGoodsVo> querySalesofGoodsVoPage(String proName, String storName, Integer page, Integer pageSize)
			throws Exception;

	/**
	 * 
	 * querySalesofGoodsVoPage ·商品销售明细报表 分页
	 * 
	 * @param orderNo
	 *            订单号
	 * @param proName
	 *            商品名称
	 * @param date
	 *            交易时间：开始
	 * @param date2
	 *            交易时间：结束
	 * @param stoName
	 *            店铺名称
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 * @return
	 */
	Page<SalesDetailVo> querySalesofGoodsVoPage(String orderNo, String proName, Date date, Date date2, String stoName,
			Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 
	 * queryShopAreaVoPage ·店铺区域分布 分页
	 * 
	 * @param city
	 *            市
	 * @param area
	 *            区域
	 * @param date
	 *            交易时间 开始
	 * @param date2
	 *            交易时间 结束
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 * @return
	 */
	Page<ShopAreaVo> queryShopAreaVoPage(String city, String area, Date date, Date date2, Integer pageNum,
			Integer pageSize) throws Exception;

	Page<ShopSalesVo> queryShopSalesVoPage(String sellerId, String storeName, Integer pageNum, Integer pageSize)
			throws Exception;

	Page<ShopSalesVo> queryShopDaySalesVoPage(String sellerId, String storeName, Date date, Integer pageNum,
			Integer pageSize) throws Exception;

	Page<ShopSalesVo> queryShopMonthSalesVoPage(String sellerId, String storeName, Date date, Date date2,
			Integer pageNum, Integer pageSize) throws Exception;
}
