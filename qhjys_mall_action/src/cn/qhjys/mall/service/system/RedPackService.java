package cn.qhjys.mall.service.system;

import java.math.BigDecimal;
import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackDetail;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqRedpackTime;
import cn.qhjys.mall.vo.system.RedpackRecordVo;

public interface RedPackService {
	
	int insertRedPackAndDetailAndTime(String activityName,Long storeId,String storeName, String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,
			Long[] endMinute,String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,Integer[] fenduanGaiLv,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception;

	FqRedpack insertRedPack(String activityName,Long storeId,String storeName, String startDate,String endDate
			,String packMaxMoney,String packMinMoney,Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception;
	
	boolean insertRedPackDetail(Long redpackId,String[] subMoneyMin,String[] subMoneyMax,Integer[] fenduanGaiLv,
			Integer randType, String[] zhifujineMin,String[] zhifujineMax) throws Exception;
	
	boolean insertRedPackTime(Long redpackId,Long[] startHour,Long[] startMinute,Long[] endHour,
			Long[] endMinute) throws Exception;
	
	boolean updateRedpackAndDetailAndTime(Long redpackId,
			Long[] detailIds,Integer[] detailNewIds,Long[] timeIds,Integer[] timeNewIds,
			String activityName,Long storeId,String storeName, String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,Long[] endMinute,
			Long[] startHourNew,Long[] startMinuteNew,Long[] endHourNew,Long[] endMinuteNew,
			String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,String[] subMoneyNewMin,String[] subMoneyNewMax,
			Integer[] fenduanGaiLv,Integer[] fenduanGaiLvNew,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,String[] zhifujineNewMin,String[] zhifujineNewMax,
			Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception;
	
	boolean updateRedpack(Long redpackId,String activityName,Long storeId,
			String storeName, String startDate,String endDate,String packMaxMoney,String packMinMoney,
			Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception;
	
	boolean updateRedPackDetail(Long redpackId,Long[] detailIds,Integer[] detailNewIds,
			String[] subMoneyMin,String[] subMoneyMax,String[] subMoneyNewMin,String[] subMoneyNewMax,
			Integer[] fenduanGaiLv,Integer[] fenduanGaiLvNew,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,String[] zhifujineNewMin,String[] zhifujineNewMax) throws Exception;
	
	boolean updateRedPackTime(Long redpackId,Long[] timeIds,Integer[] timeNewIds,
			Long[] startHour,Long[] startMinute,Long[] endHour,Long[] endMinute,
			Long[] startHourNew,Long[] startMinuteNew,Long[] endHourNew,Long[] endMinuteNew) throws Exception;
	
	Page<FqRedpack> queryRedpackList(Integer page,Integer pageSize) throws Exception;
	Page<FqRedpack> queryRedpackList(Long storeId,Integer page,Integer pageSize) throws Exception;
	
	int updateRedpackStatus(Long id,Integer status) throws Exception;
	
	boolean deleteRedpack(Long id) throws Exception;
	
	FqRedpack queryRedpackById(Long id) throws Exception;
	
	List<FqRedpackDetail> queryFqRedpackDetailByRedpackId(Long id) throws Exception;
	
	List<FqRedpackTime> queryRedpackTimeByRedpackId(Long id) throws Exception;
	
	List<FqRedpackRecord> queryRedpackRecordByRedpackId(Long id) throws Exception;
	
	RedpackRecordVo queryRedpackRecordVoByRedpackId(Long id) throws Exception;
	public boolean queryStoreIntegral(Long storeId,BigDecimal money);
	public boolean updateStoreIntegral(Long storeId,BigDecimal money);
}
