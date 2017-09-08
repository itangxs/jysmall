package cn.qhjys.mall.service.system;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqAcard;
import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardTime;

public interface FqACardService {

	boolean insertAcardAndPrizeAndTime(String acardName,Long storeId,String storeName,String beginDate,
			String endDate,String[] startTime,String[] endTime,Integer pushNum,String[] prizeName,
			Integer[] prizeLine,String[] prizeDesc,String[] imgs,Integer[] probability) throws Exception;
	
	Page<FqAcard> queryAcard(String activityName,String storeName,Integer pageNum,Integer pageSize) throws Exception;
	
	boolean deleteAcard(Long id) throws Exception;
	
	boolean updateAcardStatus(Long id,Integer status) throws Exception;
	
	FqAcard queryAcardById(Long id) throws Exception;
	
	List<FqAcardTime> queryAcardTimeByAcardId(Long acardId) throws Exception;
	
	List<FqAcardPrize> queryAcardPrizeByAcardId(Long acardId) throws Exception;
	
	boolean updateAcardAndPrizeAndTime(Long acardId,
			String acardName,Long storeId,String storeName,String beginDate,String endDate,
			Long[] timeIds,String[] startTime,String[] endTime,String[] startTimeNew,
			String[] endTimeNew,Integer pushNum,
			Long[] prizeIds,String[] prizeName,String[] prizeLine,String[] prizeDesc,
			String[] imgs,Integer[] probability) throws Exception;
	
	
}
