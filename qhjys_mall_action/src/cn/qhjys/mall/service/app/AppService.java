package cn.qhjys.mall.service.app;

import java.util.Date;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.vo.AppProdVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

public interface AppService {

	/**
	 * 查询用户浏览的商品
	 * 
	 * @param userId
	 *            用户编号
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	Page<AppProdVo> selectBrowsedProduct(Long userId, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 查找附近店铺的商品
	 * 
	 * @param lngtd
	 *            店铺经度
	 * @param lattd
	 *            店铺维度
	 * @param range
	 *            查询范围
	 * @param category
	 *            分类类型
	 * @param city
	 *            所在城市
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	JSONArray selectNearbyProduct(Double lngtd, Double lattd, Integer range, Long category, Long city, Integer pageNum,
			Integer pageSize) throws Exception;

	/**
	 * 推荐商品
	 * 
	 * @param category
	 *            商品类别
	 * 
	 * @param city
	 *            所在城市
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	Page<AppProdVo> queryHomeProduct(Long category, Long city, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 已购买过的商品
	 * 
	 * @param userId
	 *            用户编号
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<AppProdVo> queryProdByBought(Long userId, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 获取商品详情
	 * 
	 * @param prodId
	 *            商品编号
	 * @return
	 * @throws Exception
	 */
	JSONObject getProdDetailById(Long userId, Long prodId) throws Exception;

	/**
	 * 本商家其它热卖套餐
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	Page<AppProdVo> queryStoreComboById(Long storeId, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 获取筛选条件:一级 商品分类； 二级 城市 地区名
	 * 
	 * @param cityId
	 *            所在城市
	 * @return JSONObject
	 */
	JSONObject queryFilterContent(Long cityId) throws Exception;

	/**
	 * 商品搜索
	 * 
	 * @param keyword
	 *            关键字
	 * @param lngtd
	 *            所在经度
	 * @param lattd
	 *            所在维度
	 * @param range
	 *            距离范围
	 * @param cityId
	 *            所在城市
	 * @param areaId
	 *            所在区域
	 * @param category
	 *            分类条件
	 * @param column
	 *            排序字段
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	Page<AppProdVo> searchProduct(String keyword, Double lngtd, Double lattd, Integer range, Long cityId, Long areaId,
			Long category, String column, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 我的钱包
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 * @throws Exception
	 */
	JSONObject getUserPurseByUserId(Long userId) throws Exception;

	/**
	 * 积分交易明细
	 * 
	 * @param userId
	 *            用户编号
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	JSONArray queryUserIntegralUserId(Long userId, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 我的余额
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 * @throws Exception
	 */
	JSONObject queryUserBalanceUserId(Long userId) throws Exception;

	/**
	 * 查找用户收货地址
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	JSONArray queryDeliveryAddr(Long userId) throws Exception;

	/**
	 * 添加收货地址
	 * 
	 * @param addr
	 * @return
	 * @throws Exception
	 */
	boolean addDeliveryAddr(DeliveryAddr addr) throws Exception;

	/**
	 * 修改收货地址
	 * 
	 * @param addr
	 * @return
	 * @throws Exception
	 */
	boolean updateDeliveryAddr(DeliveryAddr addr) throws Exception;

	/**
	 * 我的预约列表
	 * 
	 * @param userId
	 *            用户编号
	 * @param status
	 *            预约状态
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @throws Exception
	 */
	JSONArray queryUserScheduleById(Long userId, Integer status, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 预约商品消费
	 * 
	 * @param userId
	 *            用户编号
	 * @param detailId
	 *            详单编号
	 * @param reserTime
	 *            消费时间
	 * @param name
	 *            预约人名
	 * @param phone
	 *            预定电话
	 * @param content
	 *            内容说明
	 * @return
	 * @throws Exception
	 */
	boolean addScheduleOrderById(Long userId, Long detailId, Date reserTime, String name, String phone, String content)
			throws Exception;

	/**
	 * 获取推荐图片
	 * 
	 * @param type
	 *            图片类型
	 * @return
	 */
	JSONObject getAppHomeRecommend(byte type,long cityId) throws Exception;
}