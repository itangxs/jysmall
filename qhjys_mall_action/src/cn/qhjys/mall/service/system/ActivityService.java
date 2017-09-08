package cn.qhjys.mall.service.system;

import java.util.Date;
import java.util.List;
import cn.qhjys.mall.entity.CouponsInfo;
import cn.qhjys.mall.entity.CouponsVirify;
import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.vo.system.CouponsVirifyVo;
import cn.qhjys.mall.vo.system.StoreLotteryVo;
import com.github.pagehelper.Page;

public interface ActivityService {
	
	/**
	 * 
	 * queryStoreLotteryByList 获取到活动列表
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<StoreLottery> queryStoreLotteryByList(Integer page,Integer pageSize)throws Exception;

	/**
	 * 
	 * insertStoreLotteryBy 添加活动
	 * @param storeId  商家ID
	 * @param lotteryName 活动名称
	 * @param lotteryContent 活动说明
	 * @param startDate   开始时间
	 * @param endDate 结束时间
	 * @param status  状态
	 * @return
	 */
	public int insertStoreLotteryBy(Long storeId, String lotteryName, String lotteryContent, Date startDate, Date endDate,
			Integer status);

	/**
	 * 
	 * getStoreLotteryById 获取到某个活动实体
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StoreLottery getStoreLotteryById(Long id)throws Exception;
	
	/**
	 * 
	 * updateStoreLotteryBy 修改活动
	 * @param Id  id
	 * @param storeId  商家ID
	 * @param lotteryName 活动名称
	 * @param lotteryContent 活动说明
	 * @param startDate   开始时间
	 * @param endDate 结束时间
	 * @param status  状态
	 * @return
	 */
	public int updateStoreLotteryBy(Long id, Long storeId, String lotteryName, String lotteryContent, Date parse,
			Date parse2, Integer status);
	/**
	 * 
	 * getStoreLotteryByStatus 按照状态获取到集合
	 * @param status
	 * @throws Exception
	 */
   public 	List<StoreLottery>	getStoreLotteryByStatus(Integer status)throws Exception;
   
   /**
    * 
    * getStoreLotteryByStatus 按照状态获取到集合
    * @param status
    * @throws Exception
    */
   public 	List<StoreLotteryVo> getStoreLotteryVoByStatus(Integer status)throws Exception;

   /**
    * 
    * queryLotteryDishByList 获取到菜品的分页
    * @param pageNum 页数
    * @param pageSize 每页显示
    * @return
    */
	public Page<LotteryDish> queryLotteryDishByList(Integer pageNum, Integer pageSize)throws Exception;

	/**
	 * 
	 * insertLotteryDishBy 添加菜品
	 * @param lotteryId      菜品活动ID
	 * @param rankLevel     奖励等级
	 * @param dishName 		菜品名称
	 * @param dishImage		菜品图片
	 * @param dishInfo		菜品说明
	 * @return
	 */
    public int insertLotteryDishBy(Long lotteryId, Integer rankLevel, String dishName, String dishImage, String dishInfo,Integer limitNum,Date parse)throws Exception;

    
    /**
     * 
     * getLotteryDishById 通过ID获取到菜品
     * @param id
     * @return
     */
	public LotteryDish getLotteryDishById(Long id)throws Exception;
	
	 
    /**
     * 
     * getLotteryDishByLotteryId 通过活动ID获取到菜品
     * @param id
     * @return
     */
	public List<LotteryDish> getLotteryDishByLotteryId(Long lotteryId)throws Exception;


	/**
	 * 
	 * updateLotteryDishBy 通过ID修改菜品
	 * @param id	
	 * @param lotteryId    菜品活动ID    
	 * @param rankLevel    奖励等级       
	 * @param dishName     菜品名称       
	 * @param dishImage    菜品图片       
	 * @param dishInfo     菜品说明     
	 * @return
	 */
	public int updateLotteryDishBy(Long id, Long lotteryId, Integer rankLevel, String dishName, String dishImage,
			String dishInfo,Integer limitNum,Date parse)throws Exception;

	/**
	 * 
	 * queryVirifyByList 列表消费卷记录
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public Page<CouponsVirify> queryVirifyByList(Integer pageNum, Integer pageSize)throws Exception;

	/**
	 * 
	 * getCouponsInfoByOpenId 通过用户的openID 获取到用的所有消费卷
	 * @param openid	用户的openID
	 * @return
	 * @throws Exception
	 */
	public List<CouponsInfo> getCouponsInfoByOpenId(String openid)throws Exception;

	/**
	 * 
	 * getCouponsByStoreOpenIdAndCouponsId 消费卷是可用
	 * @param storeId  店铺的ID
	 * @param couponsId 消费卷的ID
	 * @return
	 */
	public Boolean insertCouponsByStoreOpenIdAndCouponsId(Long storeId, Long couponsId)throws Exception;

	public CouponsInfo getCouponsInfoById(Long couponsId)throws Exception;

	/**
	 * 
	 * getCouponsVirifyVoByOpenId 通过商家的openid获取CouponsVirifyVo 商家的消费记录
	 * @param openId 商家openid 
	 * @return
	 */
	public List<CouponsVirifyVo> getCouponsVirifyVoByOpenId(String openId)throws Exception;

	/**
	 * 
	 * updateIsTime 是否在有效期
	 * @param lotteryId
	 * @param rank
	 * @return
	 */
	public Boolean updateIsTime(Long lotteryId, Integer rank);
}
