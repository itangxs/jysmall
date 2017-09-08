package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackDetail;
import cn.qhjys.mall.entity.FqRedpackDetailExample;
import cn.qhjys.mall.entity.FqRedpackExample;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqRedpackRecordExample;
import cn.qhjys.mall.entity.FqRedpackTime;
import cn.qhjys.mall.entity.FqRedpackTimeExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.FqRedpackDetailMapper;
import cn.qhjys.mall.mapper.FqRedpackMapper;
import cn.qhjys.mall.mapper.FqRedpackRecordMapper;
import cn.qhjys.mall.mapper.FqRedpackTimeMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.custom.RedpackRecordVoMapper;
import cn.qhjys.mall.service.system.RedPackService;
import cn.qhjys.mall.vo.system.RedpackRecordVo;

@Service("redPackService")
public class RedPackServiceImpl implements RedPackService{
	
	@Autowired
	FqRedpackMapper fqRedpackMapper;
	@Autowired
	FqRedpackDetailMapper fqRedpackDetailMapper;
	@Autowired
	FqRedpackTimeMapper fqRedpackTimeMapper;
	@Autowired
	FqRedpackRecordMapper fqRedpackRecordMapper;
	@Autowired
	RedpackRecordVoMapper redpackRecordVoMapper;
	@Autowired
	StoreInfoMapper storeInfoMapper;
	@Autowired
	CashAccountMapper cashAccountMapper;
	
	
	
	@Override
	public int insertRedPackAndDetailAndTime(String activityName,Long storeId,String storeName, 
			String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,
			Long[] endMinute,String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,Integer[] fenduanGaiLv,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception {
		
		if (!queryStoreIntegral(storeId, new BigDecimal(totalMoney))) {
			return -1;
		}
		
		FqRedpack redpack = insertRedPack(activityName, storeId, storeName, 
				startDate, endDate, packMaxMoney, packMinMoney, maxPackNum, totalMoney, quanxian);
		
		if (redpack == null) {
			return -2;
		}
		
		boolean detailResult =  insertRedPackDetail(redpack.getId(), subMoneyMin, subMoneyMax, 
				fenduanGaiLv, randType, zhifujineMin, zhifujineMax);
		
		boolean timeResult = insertRedPackTime(redpack.getId(), startHour, startMinute, endHour, endMinute);
		
		if (detailResult&&timeResult) {
			if (updateStoreIntegral(storeId, new BigDecimal(totalMoney))) {
				return 1;
			}else{
				throw new IllegalArgumentException("余额更新失败");
			}
			
		}else {
			return 0;
		}
	}

	@Override
	public FqRedpack insertRedPack(String activityName,Long storeId,String storeName, String startDate,String endDate
			,String packMaxMoney,String packMinMoney,Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception {
		
		if (StringUtils.isEmpty(activityName) || storeId == null
				|| StringUtils.isEmpty(storeName) || StringUtils.isEmpty(startDate)
				|| StringUtils.isEmpty(endDate) || StringUtils.isEmpty(packMaxMoney)
				|| StringUtils.isEmpty(packMinMoney)) {
			throw new IllegalArgumentException("红包数据不能为空");
		}
		if (maxPackNum == null || maxPackNum < 0) {
			throw new IllegalArgumentException("最大红包数错误");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FqRedpack fqRedpack = new FqRedpack();
		fqRedpack.setActivityName(activityName);
		fqRedpack.setStoreId(storeId);
		fqRedpack.setStoreName(storeName);
		fqRedpack.setBeginDate(sdf.parse(startDate));
		fqRedpack.setEndDate(sdf.parse(endDate));
		fqRedpack.setMaxAmount(new BigDecimal(packMaxMoney));
		fqRedpack.setMinAmount(new BigDecimal(packMinMoney));
		fqRedpack.setDaliyNum(maxPackNum);
		fqRedpack.setTotalMoney(new BigDecimal(totalMoney));
		fqRedpack.setLaveMoney(new BigDecimal(totalMoney));
		fqRedpack.setStatus(0);
		fqRedpack.setHasChange(quanxian);
		fqRedpack.setCreateTime(new Date());
		int result = fqRedpackMapper.insertSelective(fqRedpack);
		return result > 0 ? fqRedpack : null;
	}

	@Override
	public boolean insertRedPackDetail(Long redpackId, String[] subMoneyMin, String[] subMoneyMax, Integer[] fenduanGaiLv,
			Integer randType,String[] zhifujineMin, String[] zhifujineMax) throws Exception {
		if (redpackId == null) {
			throw new IllegalArgumentException("红包Id异常");
		}
		if (randType == null) {
			throw new IllegalArgumentException("概率类型异常");
		}
		if (subMoneyMin.length != subMoneyMax.length) {
			throw new IllegalArgumentException("分段金额异常");
		}
		
		if (randType == 1) {
			if (subMoneyMin.length != fenduanGaiLv.length) {
				throw new IllegalArgumentException("分段概率异常");
			}
			
			// 分段概率
			for (int i = 0; i < subMoneyMin.length; i++) {
				FqRedpackDetail fqRedpackDetail = new FqRedpackDetail();
				fqRedpackDetail.setRedpackId(redpackId);
				fqRedpackDetail.setMinAmount(new BigDecimal(subMoneyMin[i]));
				fqRedpackDetail.setMaxAmount(new BigDecimal(subMoneyMax[i]));
				fqRedpackDetail.setType(1);
				fqRedpackDetail.setProbability(fenduanGaiLv[i]);
				int result = fqRedpackDetailMapper.insertSelective(fqRedpackDetail);
				if (result <= 0) {
					return false;
				}
			}
			
			return true;
		} else if (randType == 2) {
			if (subMoneyMin.length != zhifujineMin.length) {
				throw new IllegalArgumentException("分段支付金额异常");
			}
			if (zhifujineMin.length != zhifujineMax.length) {
				throw new IllegalArgumentException("分段支付金额异常");
			}
			
			// 分段支付金额
			for (int i = 0; i < subMoneyMin.length; i++) {
				FqRedpackDetail fqRedpackDetail = new FqRedpackDetail();
				fqRedpackDetail.setRedpackId(redpackId);
				fqRedpackDetail.setMinAmount(new BigDecimal(subMoneyMin[i]));
				fqRedpackDetail.setMaxAmount(new BigDecimal(subMoneyMax[i]));
				fqRedpackDetail.setType(2);
				fqRedpackDetail.setMinMoney(new BigDecimal(zhifujineMin[i]));
				fqRedpackDetail.setMaxMoney(new BigDecimal(zhifujineMax[i]));
				int result = fqRedpackDetailMapper.insertSelective(fqRedpackDetail);
				if (result <= 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	

	@Override
	public boolean insertRedPackTime(Long redpackId, Long[] startHour, Long[] startMinute, Long[] endHour,
			Long[] endMinute) throws Exception {
		
		if (redpackId == null) {
			throw new IllegalArgumentException("红包id异常");
		}
		
		if (startHour.length != startMinute.length) {
			throw new IllegalArgumentException("时间段异常");
		}
		
		if (endHour.length != endMinute.length) {
			throw new IllegalArgumentException("时间段异常");
		}
		
		if (startHour.length != endHour.length) {
			throw new IllegalArgumentException("时间段异常");
		}
		
		for (int i = 0; i < startHour.length; i++) {
			FqRedpackTime fqRedpackTime = new FqRedpackTime();
			fqRedpackTime.setRedpackId(redpackId);
			Long beginTime = startHour[i] * 60 + startMinute[i];
			fqRedpackTime.setBeginTime(beginTime);
			Long endTime = endHour[i] * 60 + endMinute[i];
			fqRedpackTime.setEndTime(endTime);
			int result = fqRedpackTimeMapper.insertSelective(fqRedpackTime);
			if (result <= 0) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean updateRedpackAndDetailAndTime(Long redpackId,
			Long[] detailIds,Integer[] detailNewIds,Long[] timeIds,Integer[] timeNewIds,
			String activityName,Long storeId,String storeName, String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,Long[] endMinute,
			Long[] startHourNew,Long[] startMinuteNew,Long[] endHourNew,Long[] endMinuteNew,
			String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,String[] subMoneyNewMin,String[] subMoneyNewMax,
			Integer[] fenduanGaiLv,Integer[] fenduanGaiLvNew,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,String[] zhifujineNewMin,String[] zhifujineNewMax,
			Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception {
		
		boolean resultRedpack = updateRedpack(redpackId, activityName, storeId, storeName, 
					startDate, endDate, packMaxMoney, packMinMoney, maxPackNum,totalMoney,quanxian);
		
		boolean resultDetail = updateRedPackDetail(redpackId, detailIds, detailNewIds,
				subMoneyMin, subMoneyMax, subMoneyNewMin, subMoneyNewMax, fenduanGaiLv, 
				fenduanGaiLvNew, randType, zhifujineMin, zhifujineMax, zhifujineNewMin, zhifujineNewMax);
		
		boolean resultTime = updateRedPackTime(redpackId, timeIds, timeNewIds, startHour,
				startMinute, endHour, endMinute, startHourNew, startMinuteNew, endHourNew,
				endMinuteNew);
		
		if (resultRedpack && resultDetail && resultTime) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateRedpack(Long redpackId,String activityName,Long storeId,
			String storeName, String startDate,String endDate,String packMaxMoney,String packMinMoney,
			Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception {
		if (redpackId == null || StringUtils.isEmpty(activityName) || storeId == null
				|| StringUtils.isEmpty(storeName) || StringUtils.isEmpty(startDate)
				|| StringUtils.isEmpty(endDate) || StringUtils.isEmpty(packMaxMoney)
				|| StringUtils.isEmpty(packMinMoney) || maxPackNum == null) {
			throw new IllegalArgumentException("红包参数不能为空");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FqRedpack fqRedpack = new FqRedpack();
		fqRedpack.setId(redpackId);
		fqRedpack.setActivityName(activityName);
		fqRedpack.setStoreId(storeId);
		fqRedpack.setStoreName(storeName);
		fqRedpack.setBeginDate(sdf.parse(startDate));
		fqRedpack.setEndDate(sdf.parse(endDate));
		fqRedpack.setMaxAmount(new BigDecimal(packMaxMoney));
		fqRedpack.setMinAmount(new BigDecimal(packMinMoney));
		fqRedpack.setDaliyNum(maxPackNum);
		fqRedpack.setStatus(0);
		fqRedpack.setHasChange(quanxian);
		fqRedpack.setCreateTime(new Date());
		int result = fqRedpackMapper.updateByPrimaryKeySelective(fqRedpack);
		return result > 0 ? true : false;
	}
	
	@Override
	public boolean updateRedPackDetail(Long redpackId,Long[] detailIds,Integer[] detailNewIds,
			String[] subMoneyMin, String[] subMoneyMax,String[] subMoneyNewMin,String[] subMoneyNewMax,
			Integer[] fenduanGaiLv,Integer[] fenduanGaiLvNew,Integer randType,
			String[] zhifujineMin, String[] zhifujineMax,String[] zhifujineNewMin,String[] zhifujineNewMax) throws Exception {
		
		if (redpackId == null || detailIds.length == 0) {
			throw new IllegalArgumentException("红包详情id异常");
		}
		if (subMoneyMin.length != subMoneyMax.length) {
			throw new IllegalArgumentException("分段金额异常");
		}
		if (randType == 1) {
			if (detailIds.length != fenduanGaiLv.length || subMoneyMin.length != detailIds.length) {
				throw new IllegalArgumentException("分段概率异常");
			}
			// 分段概率
			List<FqRedpackDetail> details = queryFqRedpackDetailByRedpackId(redpackId);
			for (int i = 0; i < detailIds.length; i++) {
				FqRedpackDetail fqRedpackDetail = new FqRedpackDetail();
				fqRedpackDetail.setId(detailIds[i]);
				fqRedpackDetail.setRedpackId(redpackId);
				fqRedpackDetail.setMinAmount(new BigDecimal(subMoneyMin[i]));
				fqRedpackDetail.setMaxAmount(new BigDecimal(subMoneyMax[i]));
				fqRedpackDetail.setType(1);
				fqRedpackDetail.setProbability(fenduanGaiLv[i]);
				int result = fqRedpackDetailMapper.updateByPrimaryKeySelective(fqRedpackDetail);
				if (result <= 0) {
					return false;
				}
				for (int j = 0; j < details.size(); j++) {
					if (detailIds[i] == details.get(j).getId()) {
						details.remove(j);
					}
				}
			}
			for (int j = 0; j < details.size(); j++) {
				fqRedpackDetailMapper.deleteByPrimaryKey(details.get(j).getId());
			}
			if (detailNewIds != null && detailNewIds.length > 0) {
				if (detailNewIds.length != fenduanGaiLvNew.length) {
					throw new IllegalArgumentException("分段概率异常");
				}
				if (subMoneyNewMin.length != subMoneyNewMax.length || subMoneyNewMin.length != fenduanGaiLvNew.length) {
					throw new IllegalArgumentException("分段金额异常");
				}
				for (int i = 0; i < detailNewIds.length; i++) {
					FqRedpackDetail fqRedpackDetail = new FqRedpackDetail();
					fqRedpackDetail.setRedpackId(redpackId);
					fqRedpackDetail.setMinAmount(new BigDecimal(subMoneyNewMin[i]));
					fqRedpackDetail.setMaxAmount(new BigDecimal(subMoneyNewMax[i]));
					fqRedpackDetail.setType(1);
					fqRedpackDetail.setProbability(fenduanGaiLvNew[i]);
					int result = fqRedpackDetailMapper.insertSelective(fqRedpackDetail);
					if (result <= 0) {
						return false;
					}
				}
			}
			return true;
		}else if (randType == 2) {
			if (zhifujineMin.length != zhifujineMax.length) {
				throw new IllegalArgumentException("分段支付金额异常");
			}
			if (detailIds.length != subMoneyMin.length || subMoneyMin.length != zhifujineMin.length) {
				throw new IllegalArgumentException("分段金额异常");
			}
			List<FqRedpackDetail> details = queryFqRedpackDetailByRedpackId(redpackId);
			// 分段支付金额
			for (int i = 0; i < detailIds.length; i++) {
				FqRedpackDetail fqRedpackDetail = new FqRedpackDetail();
				fqRedpackDetail.setId(detailIds[i]);
				fqRedpackDetail.setRedpackId(redpackId);
				fqRedpackDetail.setMinAmount(new BigDecimal(subMoneyMin[i]));
				fqRedpackDetail.setMaxAmount(new BigDecimal(subMoneyMax[i]));
				fqRedpackDetail.setType(2);
				fqRedpackDetail.setMinMoney(new BigDecimal(zhifujineMin[i]));
				fqRedpackDetail.setMaxMoney(new BigDecimal(zhifujineMax[i]));
				int result = fqRedpackDetailMapper.updateByPrimaryKeySelective(fqRedpackDetail);
				if (result <= 0) {
					return false;
				}
				for (int j = 0; j < details.size(); j++) {
					if (detailIds[i].intValue() == details.get(j).getId().intValue()) {
						details.remove(j);
					}
				}
			}
			for (int j = 0; j < details.size(); j++) {
				fqRedpackDetailMapper.deleteByPrimaryKey(details.get(j).getId());
			}
			if (detailNewIds != null && detailNewIds.length > 0) {
				if (detailNewIds.length != subMoneyNewMin.length || detailNewIds.length != zhifujineNewMin.length) {
					throw new IllegalArgumentException("分段支付金额异常");
				}
				if (subMoneyNewMin.length != subMoneyNewMax.length || zhifujineNewMin.length != zhifujineNewMax.length) {
					throw new IllegalArgumentException("分段支付金额异常");
				}
				for (int i = 0; i < detailNewIds.length; i++) {
					FqRedpackDetail fqRedpackDetail = new FqRedpackDetail();
					fqRedpackDetail.setRedpackId(redpackId);
					fqRedpackDetail.setMinAmount(new BigDecimal(subMoneyNewMin[i]));
					fqRedpackDetail.setMaxAmount(new BigDecimal(subMoneyNewMax[i]));
					fqRedpackDetail.setType(2);
					fqRedpackDetail.setMinMoney(new BigDecimal(zhifujineNewMin[i]));
					fqRedpackDetail.setMaxMoney(new BigDecimal(zhifujineNewMax[i]));
					int result = fqRedpackDetailMapper.insertSelective(fqRedpackDetail);
					if (result <= 0) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean updateRedPackTime(Long redpackId, Long[] timeIds,Integer[] timeNewIds,
			Long[] startHour, Long[] startMinute, Long[] endHour,Long[] endMinute,
			Long[] startHourNew,Long[] startMinuteNew,Long[] endHourNew,Long[] endMinuteNew) throws Exception {
		if (redpackId == null) {
			throw new IllegalArgumentException("红包ID异常");
		}
		if (timeIds.length != startHour.length || timeIds.length != endHour.length) {
			throw new IllegalArgumentException("时间段参数异常");
		}
		if (startHour.length != startMinute.length || endHour.length != endMinute.length) {
			throw new IllegalArgumentException("时间段参数异常");
		}
		List<FqRedpackTime> redpackTimes = queryRedpackTimeByRedpackId(redpackId);
		for (int i = 0; i < timeIds.length; i++) {
			FqRedpackTime fqRedpackTime = new FqRedpackTime();
			fqRedpackTime.setId(timeIds[i]);
			fqRedpackTime.setRedpackId(redpackId);
			Long beginTime = startHour[i] * 60 + startMinute[i];
			fqRedpackTime.setBeginTime(beginTime);
			Long endTime = endHour[i] * 60 + endMinute[i];
			fqRedpackTime.setEndTime(endTime);
			int result = fqRedpackTimeMapper.updateByPrimaryKeySelective(fqRedpackTime);
			if (result <= 0) {
				return false;
			}
			for (int j = 0; j < redpackTimes.size(); j++) {
				if (timeIds[i].intValue() == redpackTimes.get(j).getId().intValue()) {
					redpackTimes.remove(j);
				}
			}
		}
		for (int j = 0; j < redpackTimes.size(); j++) {
			fqRedpackTimeMapper.deleteByPrimaryKey(redpackTimes.get(j).getId());
		}
		if (timeNewIds != null && timeNewIds.length > 0) {
			if (timeNewIds.length != startHourNew.length || timeNewIds.length != endHourNew.length) {
				throw new IllegalArgumentException("时间段参数异常");
			}
			if (startHourNew.length != startMinuteNew.length || endHourNew.length != endMinuteNew.length) {
				throw new IllegalArgumentException("时间段参数异常");
			}
			for (int i = 0; i < timeNewIds.length; i++) {
				FqRedpackTime fqRedpackTime = new FqRedpackTime();
				fqRedpackTime.setRedpackId(redpackId);
				Long beginTime = startHourNew[i] * 60 + startMinuteNew[i];
				fqRedpackTime.setBeginTime(beginTime);
				Long endTime = endHourNew[i] * 60 + endMinuteNew[i];
				fqRedpackTime.setEndTime(endTime);
				int result = fqRedpackTimeMapper.insertSelective(fqRedpackTime);
				if (result <= 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Page<FqRedpack> queryRedpackList(Integer page,Integer pageSize) throws Exception {
		FqRedpackExample example = new FqRedpackExample();
		example.createCriteria();
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, pageSize);
		Page<FqRedpack> datas = (Page<FqRedpack>) fqRedpackMapper.selectByExample(example);
		return datas;
	}

	@Override
	public int updateRedpackStatus(Long id, Integer status) throws Exception {
		if (id == null || status == null) {
			return -1;
		}
		FqRedpack redpack = queryRedpackById(id);
		if (redpack == null) {
			return -1;
		}
		if (status == 1) {
			FqRedpackExample example = new FqRedpackExample();
			example.createCriteria().andStoreIdEqualTo(redpack.getStoreId()).andStatusEqualTo(1);
			List<FqRedpack> list = fqRedpackMapper.selectByExample(example);
			if (list.size() >0) {
				return -2;
			}
		}
		FqRedpack record = new FqRedpack();
		record.setId(id);
		record.setStatus(status);
		int result = fqRedpackMapper.updateByPrimaryKeySelective(record);
		return result > 0 ? 1 : -1;
	}

	@Override
	public boolean deleteRedpack(Long id) throws Exception {
		if (id == null) {
			return false;
		}
		int result = fqRedpackMapper.deleteByPrimaryKey(id);
		return result > 0 ? true : false;
	}

	@Override
	public FqRedpack queryRedpackById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		FqRedpack fqRedpack = fqRedpackMapper.selectByPrimaryKey(id);
		return fqRedpack;
	}

	@Override
	public List<FqRedpackDetail> queryFqRedpackDetailByRedpackId(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		FqRedpackDetailExample example = new FqRedpackDetailExample();
		example.createCriteria().andRedpackIdEqualTo(id);
		List<FqRedpackDetail> details = fqRedpackDetailMapper.selectByExample(example);
		return details;
	}

	@Override
	public List<FqRedpackTime> queryRedpackTimeByRedpackId(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		FqRedpackTimeExample example = new FqRedpackTimeExample();
		example.createCriteria().andRedpackIdEqualTo(id);
		List<FqRedpackTime> redpackTimes = fqRedpackTimeMapper.selectByExample(example);
		return redpackTimes;
	}

	@Override
	public List<FqRedpackRecord> queryRedpackRecordByRedpackId(Long id)
			throws Exception {
		if (id == null) {
			return null;
		}
		FqRedpackRecordExample example = new FqRedpackRecordExample();
		example.createCriteria().andRedpackIdEqualTo(id).andStatusEqualTo(1);
		example.setOrderByClause("create_time desc");
		List<FqRedpackRecord> datas = fqRedpackRecordMapper.selectByExample(example);
		return datas;
	}

	@Override
	public RedpackRecordVo queryRedpackRecordVoByRedpackId(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		RedpackRecordVo vo = redpackRecordVoMapper.queryRedpackRecordVoByRedpackId(id,1);
		return vo;
	}

	@Override
	public boolean queryStoreIntegral(Long storeId, BigDecimal money) {
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeId);
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountTypeEqualTo(1).andAccountIdEqualTo(store.getSellerId());
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list.size() > 0) {
			BigDecimal integral = list.get(0).getIntegral();
			BigDecimal isBalance = integral.subtract(money);
			if (isBalance.compareTo(BigDecimal.ZERO) == -1) {
				return false;
				}else{
					return true;
				}
		}
		return false;
	}
	@Override
	public boolean updateStoreIntegral(Long storeId, BigDecimal money) {
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeId);
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountTypeEqualTo(1).andAccountIdEqualTo(store.getSellerId());
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list.size() > 0) {
			CashAccount cash= list.get(0);
			BigDecimal integral = cash.getIntegral();
			BigDecimal isBalance = integral.subtract(money);
			if (isBalance.compareTo(BigDecimal.ZERO) == -1) {
				return false;
			}else{
				cash.setIntegral(isBalance);
				cashAccountMapper.updateByPrimaryKeySelective(cash);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<FqRedpack> queryRedpackList(Long storeId, Integer page,
			Integer pageSize) throws Exception {
		FqRedpackExample example = new FqRedpackExample();
		example.createCriteria().andStoreIdEqualTo(storeId);
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(page, pageSize);
		Page<FqRedpack> datas = (Page<FqRedpack>) fqRedpackMapper.selectByExample(example);
		return datas;
	}
	
}
