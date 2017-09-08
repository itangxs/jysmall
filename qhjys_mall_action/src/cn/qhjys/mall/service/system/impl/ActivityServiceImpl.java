package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CouponsInfo;
import cn.qhjys.mall.entity.CouponsInfoExample;
import cn.qhjys.mall.entity.CouponsVirify;
import cn.qhjys.mall.entity.CouponsVirifyExample;
import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.LotteryDishExample;
import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.entity.StoreLotteryExample;
import cn.qhjys.mall.entity.UserLottery;
import cn.qhjys.mall.mapper.CouponsInfoMapper;
import cn.qhjys.mall.mapper.CouponsVirifyMapper;
import cn.qhjys.mall.mapper.LotteryDishMapper;
import cn.qhjys.mall.mapper.StoreLotteryMapper;
import cn.qhjys.mall.mapper.UserLotteryMapper;
import cn.qhjys.mall.mapper.custom.CouponsVirifyVoMapper;
import cn.qhjys.mall.mapper.custom.StoreLotteryVoMapper;
import cn.qhjys.mall.service.system.ActivityService;
import cn.qhjys.mall.vo.system.CouponsVirifyVo;
import cn.qhjys.mall.vo.system.StoreLotteryVo;
@Service("activityService")
public class ActivityServiceImpl extends Base implements ActivityService {

	@Autowired
	private StoreLotteryMapper storeLotteryMapper;
	@Autowired
	private LotteryDishMapper lotteryDishMapper;
	@Autowired
	private CouponsVirifyMapper couponsVirifyMapper;
	@Autowired
	private StoreLotteryVoMapper storeLotteryVoMapper;
	@Autowired
	private CouponsInfoMapper couponsInfoMapper;
	@Autowired
	private CouponsVirifyVoMapper couponsVirifyVoMapper;
	@Autowired
	private UserLotteryMapper userLotteryMapper;
	
	
	@Override
	public Page<StoreLottery> queryStoreLotteryByList(Integer page, Integer pageSize) throws Exception {
		
		PageHelper.startPage(page, pageSize);
		StoreLotteryExample example = new StoreLotteryExample();
		 Page<StoreLottery> pageList =  (Page<StoreLottery>) storeLotteryMapper.selectByExample(example );
		return pageList;
	}

	@Override
	public int insertStoreLotteryBy(Long storeId, String lotteryName, String lotteryContent, Date startDate,
			Date endDate, Integer status) {
		
		StoreLottery storeLottery = new StoreLottery();
		storeLottery.setStoreId(storeId);
		storeLottery.setLotteryName(lotteryName);
		storeLottery.setLotteryContent(lotteryContent);
		storeLottery.setStartDate(startDate);
		storeLottery.setEndDate(endDate);
		storeLottery.setStatus(status);
		storeLottery.setCreateDate(new Date());
		return storeLotteryMapper.insert(storeLottery);
	}

	@Override
	public StoreLottery getStoreLotteryById(Long id) throws Exception {
		
		return storeLotteryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateStoreLotteryBy(Long id, Long storeId, String lotteryName, String lotteryContent, Date startDate,
			Date endDate, Integer status) {
		if(status==1){
			StoreLotteryExample example= new StoreLotteryExample();
			example.createCriteria().andStoreIdEqualTo(storeId);
			List<StoreLottery> selectByExample = storeLotteryMapper.selectByExample(example);
			if(selectByExample.size()>0){
				for (int i = 0; i < selectByExample.size(); i++) {
					StoreLottery storeLottery = selectByExample.get(i);
					if(storeLottery.getStatus()!=0){
						storeLottery.setStatus(0);
						storeLotteryMapper.updateByPrimaryKey(storeLottery);
					}
				}
			}
		}
		StoreLottery record = storeLotteryMapper.selectByPrimaryKey(id);
		record.setStoreId(storeId);
		record.setLotteryName(lotteryName);
		record.setLotteryContent(lotteryContent);
		record.setStartDate(startDate);
		record.setEndDate(endDate);
		record.setStatus(status);
		return storeLotteryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<StoreLottery> getStoreLotteryByStatus(Integer status) throws Exception {
		StoreLotteryExample example = new StoreLotteryExample();
		if(null!=status){
			example.createCriteria().andStatusEqualTo(status);
		}
		return storeLotteryMapper.selectByExample(example);
	}
	

	@Override
	public List<StoreLotteryVo> getStoreLotteryVoByStatus(Integer status) throws Exception {
		return storeLotteryVoMapper.selectByStoreLotteryVoByStatus(status);
	}

	@Override
	public Page<LotteryDish> queryLotteryDishByList(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		LotteryDishExample example = new LotteryDishExample();
		Page<LotteryDish> dishs = (Page<LotteryDish>) lotteryDishMapper.selectByExample(example);
		return dishs;
	}

	@Override
	public int insertLotteryDishBy(Long lotteryId, Integer rankLevel, String dishName, String dishImage, String dishInfo,Integer limitNum,Date parse) {
		
		LotteryDish record =  new LotteryDish();
		record.setLotteryId(lotteryId);
		record.setRankLevel(rankLevel);
		record.setDishName(dishName);
		record.setDishImage(dishImage);
		record.setDishInfo(dishInfo);
		record.setLimitNum(limitNum);
		record.setBeginTime(parse);
		return lotteryDishMapper.insert(record);
	}

	@Override
	public LotteryDish getLotteryDishById(Long id) {
		return lotteryDishMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateLotteryDishBy(Long id, Long lotteryId, Integer rankLevel, String dishName, String dishImage,
			String dishInfo,Integer limitNum,Date parse) {
		LotteryDish record =  lotteryDishMapper.selectByPrimaryKey(id);
		record.setLotteryId(lotteryId);
		record.setRankLevel(rankLevel);
		record.setDishName(dishName);
		record.setDishImage(dishImage);
		record.setDishInfo(dishInfo);
		record.setLimitNum(limitNum);
		record.setBeginTime(parse);
		return lotteryDishMapper.updateByPrimaryKey(record);
	}

	@Override
	public Page<CouponsVirify> queryVirifyByList(Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		CouponsVirifyExample example = new CouponsVirifyExample();
		Page<CouponsVirify> page=	(Page<CouponsVirify>) couponsVirifyMapper.selectByExample(example);
		return page;
	}

	@Override
	public List<LotteryDish> getLotteryDishByLotteryId(Long lotteryId) throws Exception {
		LotteryDishExample example =  new LotteryDishExample();
		example.createCriteria().andLotteryIdEqualTo(lotteryId);
		return lotteryDishMapper.selectByExample(example);
	}

	@Override
	public List<CouponsInfo> getCouponsInfoByOpenId(String openid) throws Exception {
		CouponsInfoExample example = new CouponsInfoExample();
		example.createCriteria().andOpenIdEqualTo(openid).andStatusEqualTo(0);
		return couponsInfoMapper.selectByExample(example );
	}

	@Override
	public Boolean insertCouponsByStoreOpenIdAndCouponsId(Long storeId, Long couponsId) throws Exception {
	
		CouponsInfo couponsInfo = couponsInfoMapper.selectByPrimaryKey(couponsId);
		Integer status = couponsInfo.getStatus();
		if(status==0){
			couponsInfo.setStatus(1);
			couponsInfoMapper.updateByPrimaryKey(couponsInfo);
			
			CouponsVirify record = new CouponsVirify();
			record.setCouponsId(couponsId);
			record.setStoreId(storeId);
			record.setVerifyTime(new Date());
			int insert = couponsVirifyMapper.insert(record);
			if(insert==0){
				return false;
			}
			logger.info("-------couponsVirifyMapper.insert(record)--------"+insert);
			if(insert>0){//成功让别人可以继续玩
				Long userLotteryId = couponsInfo.getUserLotteryId();
				UserLottery ul = userLotteryMapper.selectByPrimaryKey(userLotteryId);
				if(null!=ul){
					ul.setStatus(1);
					userLotteryMapper.updateByPrimaryKey(ul);
				}
			}
			
			return true;
		}else{
			return false;
		}
	}

	@Override
	public CouponsInfo getCouponsInfoById(Long couponsId) throws Exception {
		return couponsInfoMapper.selectByPrimaryKey(couponsId);
	}

	@Override
	public List<CouponsVirifyVo> getCouponsVirifyVoByOpenId(String openid) throws Exception {
		return couponsVirifyVoMapper.getCouponsVirifyVoByOpenId(openid);
	}

	@Override
	public Boolean updateIsTime(Long lotteryId, Integer rank) {
		LotteryDishExample example = new LotteryDishExample();
		example.createCriteria().andLotteryIdEqualTo(lotteryId).andRankLevelEqualTo(rank);
		List<LotteryDish> list = lotteryDishMapper.selectByExample(example );
		if(list.size()<1){
			return false;
		}
		LotteryDish lotteryDish = list.get(0);
		if(null==lotteryDish.getBeginTime()||lotteryDish.getBeginTime().getTime()< new Date().getTime()){
			return true;
		}
		
		return false;
	}


}
