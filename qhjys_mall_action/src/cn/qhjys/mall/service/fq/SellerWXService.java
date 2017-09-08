package cn.qhjys.mall.service.fq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqCuisine;
import cn.qhjys.mall.entity.FqLocation;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqRebate;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreCheck;
import cn.qhjys.mall.vo.FqStoreVo;

/**
 * 
 * 类名称:SellerWXService
 * 类描述:商家微信
 * 创建人:JiangXiaoPing
 * 创建时间:2016年5月9日下午2:35:05
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public interface SellerWXService {
	
	/**
	 * 
	 * queryFqCuisineListByEnabled 找出菜系
	 * @param enabled 
	 * @return 
	 * @throws Exception
	 */
	List<FqCuisine> queryFqCuisineListByEnabled(Integer enabled)throws Exception;
	
	/**
	 * 
	 * querFqLocationsListByEnabled 找出商圈
	 * @param enabled
	 * @return
	 * @throws Exception
	 */
	List<FqLocation> querFqLocationsListByEnabled(Integer enabled)throws Exception;
	
	
	/**
	 * 
	 * queryFqStoreBySellerId
	 * @param sellerid 商家ID
	 * @return
	 * @throws Exception
	 */
	FqStore queryFqStoreBySellerId(Long sellerid)throws Exception;
	
	/**
	 * 
	 * saveFqStore 添加微信店铺
	 * @param sellerid 商家ID
	 * @param storename 微信店铺名称
	 * @param storelogo 微信店铺Logo
	 * @param activityinfo  微信店铺 活动介绍
	 * @param storeinfo 微信店铺 店铺介绍
	 * @param address 微信店铺 地址
	 * @param traffictime 微信店铺 营业时间
	 * @param phonenum 微信店铺 电话
	 * @param proportion 微信店铺 定金比例
	 * @param locationid 微信店铺 商圈
	 * @param cuisineid 微信店铺 菜系
	 * @param storeimages  店铺或者菜品相关图片
	 * @return
	 * @throws Exception
	 */
	int saveFqStore(Long sellerid,String storename,String storelogo,String activityinfo,String storeinfo,String address, String begin,String end,String phonenum,Integer proportion,Long locationid,Long cuisineid, String storeimages,BigDecimal storeRebat)throws Exception;
	
	/**
	 * 
	 * updateFqStore 修改微信店铺
	 * @param id 微信店铺ID
	 * @param storename 微信店铺名称
	 * @param storelogo 微信店铺Logo
	 * @param activityinfo  微信店铺 活动介绍
	 * @param storeinfo 微信店铺 店铺介绍
	 * @param address 微信店铺 地址
	 * @param traffictime 微信店铺 营业时间
	 * @param phonenum 微信店铺 电话
	 * @param proportion 微信店铺 定金比例
	 * @param locationid 微信店铺 商圈
	 * @param cuisineid 微信店铺 菜系
	 * @param storeimages 
	 * @return
	 * @throws Exception
	 */
	int updateFqStore(Long id,String storename,String storelogo,String activityinfo,String storeinfo,String address, String begin,String end,String phonenum,Integer proportion,Long locationid,Long cuisineid, String storeimages,BigDecimal storeRebat)throws Exception;
	
	int updateFqStoreApp(Long id, Long sellerId, String userName, String storename,String storelogo,String activityinfo,String storeinfo,String address, String begin,String end,String phonenum,Integer proportion,Long locationid, String storeimages)throws Exception;
	
	int updateFqStoreApp(Long id,String storename,String storelogo,String activityinfo,String storeinfo,String address, String begin,String end,String phonenum,Integer proportion,Long locationid, String storeimages)throws Exception;
	/**
	 * 
	 * queryFqStoreByPage 获取分页
	 * @param sellerid  商家ID
	 * @param wxstoreid 微信店铺ID
	 * @param wxstorename 微信店铺名称
	 * @param date  创建时间开始
	 * @param date2  创建时间结束
	 * @param status 状态
	 * @param pagesize 
	 * @param page 
	 * @return
	 */
	Page<FqStore> queryFqStoreByPage(Long sellerid, Long wxstoreid, String wxstorename, Date date, Date date2,
			Integer status, Integer page, Integer pagesize)throws Exception;
	/**
	 * 
	 * queryFqStoreById 通过ID获取到微信店铺
	 * @param id
	 * @return
	 * @throws Exception
	 */
	FqStore queryFqStoreById(Long id)throws Exception;
	
	FqStore queryFqStoreById(Long id, int enable);
	
	List<FqStore> queryFqStoreByStatus(int enable);

	/**
	 * 
	 * updateFqStore 系统后台修改商家微信店铺的状态
	 * @param id   微信店铺的ID
	 * @param status 状态
	 * @param bigDecimal 店铺的折扣 必填
	 * @param type 是否是金融商家
	 * @param level 排序等级
	 * @return
	 */
	int updateFqStore(Long id, Integer status, BigDecimal bigDecimal, Integer type, Integer level,String clerkPhone,
			BigDecimal daliyCredit,Integer withdrawStatus)throws Exception;

	/**
	 * 
	 * queryFqRebateByStoreId 通过微信店铺ID获取到折扣
	 * @param id 微信店铺ID
	 * @return
	 * @throws Exception
	 */
	FqRebate queryFqRebateByStoreId(Long id)throws Exception;
	/**
	 * 
	 * queryFqRebateByStoreIdList  通过店铺ID获取到所有的折扣
	 * @param storeid 店铺ID
	 * @return
	 * @throws Exception
	 */
	List<FqRebate> queryFqRebateByStoreIdList(Long storeid)throws Exception;
	
	List<FqRebate> queryFqRebateByStoreIdList(Long id, int enable)throws Exception;

	/**
	 * 
	 * saveOrUpdateFqRebate 修改或者添加微信店铺折扣
	 * @param id 商家ID
	 * @param id2 折扣ID
	 * @param bigDecimal 折扣
	 * @param rebateInfo 折扣说明
	 * @param beginTime 开始时间
	 * @param endTime  结束时间
	 * @param enable 
	 * @return
	 * @throws Exception
	 */
	Boolean saveOrUpdateFqRebate(Long id, Long id2, BigDecimal bigDecimal, String rebateInfo, String beginTime,
			String endTime, Integer enable)throws Exception;
	/**
	 * 
	 * queryFqProductTypeListByfqStoreId 通过微信店铺ID获取到它的所有的折扣  ()
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<FqRebate> queryFqRebateListByfqStoreId(Long id)throws Exception;

	/**
	 * 
	 * queryFqStoreByPageLocation 通过商圈找出商家 微信客户前台所用
	 * @param lid  商圈ID
	 * @param i    状态1 为已经审核通过的
	 * @param page  页数
	 * @param pagesize 每页显示
	 * @return
	 * @throws Exception
	 */
	Page<FqStoreVo> queryFqStoreByPageLocation(Long lid, int i, Integer page, int pagesize)throws Exception;
	
	/**
	 * 
	 * queryFqLocationListByStatsu 通过状态找出符合的商圈
	 * @param i状态  0 不可用 1可用
	 * @return
	 * @throws Exception
	 */
	List<FqLocation> queryFqLocationListByStatsu(int i)throws Exception;

	/**
	 * 
	 * queryFqOrderListByOpenId 用户的openId获取到用户的订单
	 * @param openId
	 * @param page
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	List<FqOrder> queryFqOrderListByOpenId(String openId, Integer page, Integer pagesize)throws Exception;

	/**
	 * 
	 * queryFqorderByIdAndOpenId 通过订单ID和用户opengid找出用户的订单详情
	 * @param id
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	FqOrder queryFqorderByIdAndOpenId(Long id, String openId)throws Exception;

	/**
	 * 
	 * queryFqOrderDetailByOrderId 通过订单找到订单详情菜品  
	 * @param id 订单ID
	 * @return
	 * @throws Exception
	 */
	List<FqOrderDetail>  queryFqOrderDetailByOrderId(Long id)throws Exception;

	/**
	 * 
	 * queryFqRebateByStoreIdList  通过店铺ID找出折扣分页
	 * @param sid
	 * @param page
	 * @param pageSize
	 * @return
	 */
	Page<FqRebate> queryFqRebateByStoreIdList(Long id, Integer page, Integer pageSize)throws Exception;

	/**
	 * 
	 * queryFqRebateByStoreId 通过折扣ID 以及店铺ID找出折扣
	 * @param id 折扣ID
	 * @param long1 店铺ID
	 * @return
	 */
	FqRebate queryFqRebateByStoreId(Long id, Long long1)throws Exception;

	FqRebate queryFqRebateById(Long id)throws Exception;

	/**
	 *  
	 * deleteFqrebateByIdAndStroeId 通过ID 和店铺ID 删除 折扣
	 * @param id
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	Boolean deleteFqrebateByIdAndStroeId(Long id, Long sid)throws Exception;



	/**
	 * 查询开通借贷的店铺
	 * @param sellerid
	 * @param wxstoreid
	 * @param wxstorename
	 * @param page
	 * @param pagesize
	 * @return
	 * @throws Exception
	 */
	Page<FqStore> queryBorrowFqStoreByPage(Long sellerid, Long wxstoreid, String wxstorename, 
			Integer page, Integer pagesize)throws Exception;
	
	Page<FqStoreCheck> queryFqStoreCheckByPage( Long wxstoreid, String wxstorename, Date date, Date date2,
			Integer status, Integer page, Integer pagesize)throws Exception;
	
	FqStoreCheck getFqStoreCheckById(Long id);
	
	int updateWxStoreCheck(Long id,Integer status);
	
}
