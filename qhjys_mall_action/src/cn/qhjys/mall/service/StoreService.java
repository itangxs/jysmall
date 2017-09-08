package cn.qhjys.mall.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.vo.StorExporteVo;
import cn.qhjys.mall.vo.StoreVo;
import cn.qhjys.mall.vo.system.FqClerkVo;

import com.github.pagehelper.Page;

/**
 * 店铺管理
 * 
 * @author LiXiang
 *
 */
public interface StoreService {

	/**
	 * 查询店铺详情信息
	 * 
	 * @param id
	 * @return
	 */
	public StoreInfo getStoreDetaile(Long id) throws Exception;

	/**
	 * 查询店铺详情信息
	 * 
	 * @param id
	 *            店铺编号
	 * @return
	 */
	public StoreVo getStoreById(Long id) throws Exception;

	/**
	 * 获取店铺信息
	 * 
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	public StoreInfo getStoreByProdId(Long prodId) throws Exception;

	/**
	 * 
	 * @param prodId
	 * @return
	 */
	public StoreVo getStoreVoByProdId(Long prodId) throws Exception;

	/**
	 * 搜索商铺接口
	 * 
	 * @param storeName
	 *            商铺名称
	 * @param categoryId
	 *            分类编号
	 * @param cityId
	 *            所在城市
	 * @param areaId
	 *            区域编号
	 * @param clause
	 *            排序字段
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	public Page<StoreVo> searchStores(String storeName, Long categoryId, Long cityId, Long areaId, String clause,
			Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 系統查询商铺
	 * 
	 * @param id
	 *            商铺id
	 * @param storeName
	 *            店铺名称
	 * @param provinceId
	 *            店铺所在地 省
	 * @param cityId
	 *            市
	 * @param areaId
	 *            区
	 * @param date
	 *            创建时间 开始
	 * @param date2
	 *            创建时间 结束
	 * @param status
	 *            状态
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws Exception
	 */
	public Page<StoreInfo> querySystemManageStoreByPage(Long id, String storeName, Long provinceId, Long cityId,
			Long areaId, Date date, Date date2, Integer status, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 系统修改商铺的状态
	 * 
	 * @param l
	 *            系统用户ID
	 * @param strlist
	 *            商铺ID数组
	 * @param staus
	 *            状态 1:审核失败，2:审核通过
	 * @return
	 */
	public Boolean updateStoreStatusBySystem(long l, List<Long> strlist, Integer staus) throws Exception;
	
	/**
	 * 修改微信授权状态
	 * @param strList
	 * @param scope
	 * @return
	 * @throws Exception
	 */
	public Boolean updateScope(List<Long> strList ,Integer scope) throws Exception;
	
	/**
	 * 修改店铺收银台状态
	 * @param strList
	 * @param cashierStatus
	 * @return
	 * @throws Exception
	 */
	public Boolean updateCashierStatus(List<Long> strList ,Integer cashierStatus) throws Exception;
	
	/**
	 * 修改店铺点餐状态
	 * @param strList
	 * @param orderStatus
	 * @return
	 * @throws Exception
	 */
	public Boolean updateOrderStatus(List<Long> strList ,Integer orderStatus, Integer judge) throws Exception;
	

	/**
	 * 删除商铺
	 * 
	 * @param l
	 *            系统ID
	 * @param id
	 *            商铺ID
	 * @return
	 * @throws Exception
	 */
	public Boolean updatedeleteStoreBySystem(long l, Long id) throws Exception;

	/**
	 * 系統查询商铺修改列表
	 * 
	 * @param storeName
	 *            店铺名称
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws Exception
	 */
	public Page<StoreCheck> querySystemManageStoreCheckByPage(String storeName, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 系统修改商铺的状态
	 * 
	 * @param l
	 *            系统用户ID
	 * @param strlist
	 *            商铺ID数组
	 * @param staus
	 *            状态 1:审核失败，2:审核通过
	 * @return
	 */
	public Boolean updateStoreCheckStatusBySystem(long l, List<Long> strlist, Integer staus) throws Exception;

	public Page<StoreVo> queryStoreVoByPage(String username, String storeName, Long storeId, Long provinceId, Long cityId, Long areaId,
			Date date, Date date2, Integer status, Integer cashierStatus, Integer orderStatus, Integer rateStatus, Integer wxAuthState ,Integer zfbAuthState,Integer hangyeType2 ,Integer hangyeType ,String yewuyuan ,Integer pageNum, Integer pageSize) throws Exception;
	
	public List<StorExporteVo> queryStoreVoByExcel(String username, String storeName, Long storeId, Long provinceId, Long cityId, Long areaId,
			Date date, Date date2, Integer status, Integer cashierStatus, Integer orderStatus, Integer rateStatus, Integer wxAuthState ,Integer zfbAuthState,Integer hangyeType ) throws Exception;
	
	public StoreInfo queryStoreInfoBySeller(Long sellerId);
	
	public StoreInfo getStoreInfoById(Long storeId);
	
	public List<FqStoreRate> getFqStoreRate();
	
	public FqStoreRate getFqStoreRateByRateName(String rateName);
	
	public int updateStoreInfoRateId(Long storeId, Long rateId,Integer period);
	public int updateStoreInfoCategory(Long storeId, Long categoryId,String categoryDetail);
	
	public Page<FqStoreApply> queryFqStoreApply(Long sellerId, Integer pageNum, Integer pageSize);
	
	public int updateStoreInfoBinding(Long storeId, Long provinceId, Long cityId, Long clerkId) throws Exception;
	
	public FqClerkVo getClerkAndStore(Long storeId) throws Exception;

	public FqStoreRate getFqStoreRateById(Long id);
	
	public int updateStoreEffective();
	
	public int updateByPrimaryKeySelective(StoreInfo storeInfo);
	
	public StoreInfo selectByPrimaryKey(Long id);

}