package cn.qhjys.mall.service.fq.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqCuisine;
import cn.qhjys.mall.entity.FqCuisineExample;
import cn.qhjys.mall.entity.FqLocation;
import cn.qhjys.mall.entity.FqLocationExample;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqOrderDetailExample;
import cn.qhjys.mall.entity.FqOrderExample;
import cn.qhjys.mall.entity.FqRebate;
import cn.qhjys.mall.entity.FqRebateExample;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.FqStoreCheck;
import cn.qhjys.mall.entity.FqStoreCheckExample;
import cn.qhjys.mall.entity.FqStoreExample;
import cn.qhjys.mall.entity.FqStoreExample.Criteria;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.mapper.FqCuisineMapper;
import cn.qhjys.mall.mapper.FqLocationMapper;
import cn.qhjys.mall.mapper.FqOrderDetailMapper;
import cn.qhjys.mall.mapper.FqOrderMapper;
import cn.qhjys.mall.mapper.FqRebateMapper;
import cn.qhjys.mall.mapper.FqStoreApplyMapper;
import cn.qhjys.mall.mapper.FqStoreCheckMapper;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.custom.FqStoreVoMapper;
import cn.qhjys.mall.service.fq.FqWithdrawChangeInfoService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.vo.FqStoreVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


@Service("sellerWXService")
public class SellerWXServiceImpl extends Base implements SellerWXService  {

	@Autowired
	private FqCuisineMapper fqCuisineMapper;
	@Autowired
	private FqLocationMapper fqLocationMapper;
	@Autowired
	private FqStoreMapper fqStoreMapper;
	@Autowired
	private FqRebateMapper fqRebateMapper;
	@Autowired
	private FqUserInfoMapper  fqUserInfoMapper;
//	@Autowired
//	private WeixinUserinfoMapper  weixinUserinfoMapper;
	@Autowired
	private FqOrderMapper fqOrderMapper;
	@Autowired
	private FqOrderDetailMapper fqOrderDetailMapper;
	@Autowired
	private FqStoreVoMapper fqStoreVoMapper;
	@Autowired
	private FqStoreCheckMapper fqStoreCheckMapper;

	@Autowired 
	FqWithdrawChangeInfoService fqWithdrawChangeInfoService;
	
	@Override
	public List<FqCuisine> queryFqCuisineListByEnabled(Integer enabled) throws Exception {
		FqCuisineExample example = new FqCuisineExample();
		if(null!=enabled){
			example.createCriteria().andEnabledEqualTo(enabled);
		}
		return fqCuisineMapper.selectByExample(example );
	}

	@Override
	public List<FqLocation> querFqLocationsListByEnabled(Integer enabled) throws Exception {
		FqLocationExample example = new FqLocationExample();
		if(null!=enabled){
			example.createCriteria().andEnabledEqualTo(enabled);
		}
		return fqLocationMapper.selectByExample(example);
	}

	@Override
	public FqStore queryFqStoreBySellerId(Long sellerid) throws Exception {
		FqStoreExample example = new FqStoreExample();
		example.createCriteria().andSellerIdEqualTo(sellerid);
		List<FqStore> list = fqStoreMapper.selectByExample(example );
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int saveFqStore(Long sellerid, String storename, String storelogo, String activityinfo, String storeinfo,
			String address, String begin,String end, String phonenum, Integer proportion, Long locationid, Long cuisineid,String storeimages,BigDecimal storeRebat)
			throws Exception {
		FqStore fqStore = new FqStore();
		fqStore.setStoreName(storename);
		fqStore.setStoreLogo(storelogo);
		fqStore.setActivityInfo(activityinfo);
		fqStore.setStoreInfo(storeinfo);
		fqStore.setAddress(address);
		fqStore.setTrafficBeginTime(begin);
		fqStore.setTrafficEndTime(end);
		fqStore.setPhoneNum(phonenum);
		fqStore.setProportion(proportion);
		fqStore.setLocationId(locationid);
	/*	fqStore.setCuisineId(cuisineid);*/
		fqStore.setCreateTime(new Date());
		fqStore.setSellerId(sellerid);
		fqStore.setStatus(0);
		fqStore.setType(0);
		fqStore.setLevel(100);
		if(!StringUtils.isEmpty(storeimages))
			fqStore.setStoreImage(storeimages);
		fqStore.setStoreRebate(storeRebat);
		return fqStoreMapper.insertSelective(fqStore);
	}

	@Override
	public int updateFqStore(Long id, String storename, String storelogo, String activityinfo, String storeinfo,
			String address,  String begin,String end, String phonenum, Integer proportion, Long locationid, Long cuisineid,String storeimages,BigDecimal storeRebat)
			throws Exception {
		FqStore fqStore = fqStoreMapper.selectByPrimaryKey(id);
		if (fqStore.getStatus()==0) {
			fqStore.setStoreName(storename);
			fqStore.setStoreLogo(storelogo);
			fqStore.setActivityInfo(activityinfo);
			fqStore.setStoreInfo(storeinfo);
			fqStore.setAddress(address);
			fqStore.setTrafficBeginTime(begin);
			fqStore.setTrafficEndTime(end);
			fqStore.setPhoneNum(phonenum);
			fqStore.setProportion(proportion);
			fqStore.setLocationId(locationid);
			if(!StringUtils.isEmpty(storeimages))
				fqStore.setStoreImage(storeimages);
			fqStore.setStoreRebate(storeRebat);
			fqStore.setStatus(0);
			return fqStoreMapper.updateByPrimaryKey(fqStore);
		}else{
			FqStoreCheck fqStoreCheck = new FqStoreCheck();
			fqStoreCheck.setStoreId(id);
			fqStoreCheck.setStoreName(storename);
			fqStoreCheck.setStoreLogo(storelogo);
			fqStoreCheck.setStoreInfo(storeinfo);
			fqStoreCheck.setAddress(address);
			fqStoreCheck.setTrafficBeginTime(begin);
			fqStoreCheck.setTrafficEndTime(end);
			fqStoreCheck.setPhoneNum(phonenum);
			fqStoreCheck.setProportion(proportion);
			fqStoreCheck.setLocationId(locationid);
			if(!StringUtils.isEmpty(storeimages))
				fqStoreCheck.setStoreImage(storeimages);
			fqStoreCheck.setStatus(0);
			fqStoreCheck.setCreateTime(new Date());
			return fqStoreCheckMapper.insertSelective(fqStoreCheck);
		}
	}
	
	@Override
	public int updateFqStoreApp(Long id, Long sellerId, String userName, String storename, String storelogo, String activityinfo, String storeinfo,
			String address,  String begin,String end, String phonenum, Integer proportion, Long locationid, String storeimages)
			throws Exception {
		FqStore fqStore = fqStoreMapper.selectByPrimaryKey(id);
		if (null != fqStore) {
			fqStore.setStoreName(storename);
			fqStore.setStoreLogo(storelogo);
			fqStore.setActivityInfo(activityinfo);
			fqStore.setStoreInfo(storeinfo);
			fqStore.setAddress(address);
			fqStore.setTrafficBeginTime(begin);
			fqStore.setTrafficEndTime(end);
			fqStore.setPhoneNum(phonenum);
			fqStore.setProportion(proportion);
			fqStore.setLocationId(locationid);
			if(!StringUtils.isEmpty(storeimages))
				fqStore.setStoreImage(storeimages);
			fqStore.setStatus(0);
			return fqStoreMapper.updateByPrimaryKey(fqStore);
		}
		return -1;
	}
	
	@Override
	public int updateFqStoreApp(Long id, String storename, String storelogo, String activityinfo, String storeinfo,
			String address,  String begin,String end, String phonenum, Integer proportion, Long locationid, String storeimages)
			throws Exception {
		FqStoreCheck fqStoreCheck = new FqStoreCheck();
		fqStoreCheck.setStoreId(id);
		fqStoreCheck.setStoreName(storename);
		fqStoreCheck.setStoreLogo(storelogo);
		fqStoreCheck.setStoreInfo(storeinfo);
		fqStoreCheck.setAddress(address);
		fqStoreCheck.setTrafficBeginTime(begin);
		fqStoreCheck.setTrafficEndTime(end);
		fqStoreCheck.setPhoneNum(phonenum);
		fqStoreCheck.setProportion(proportion);
		fqStoreCheck.setLocationId(locationid);
		if(!StringUtils.isEmpty(storeimages))
			fqStoreCheck.setStoreImage(storeimages);
		fqStoreCheck.setStatus(0);
		fqStoreCheck.setCreateTime(new Date());
		return fqStoreCheckMapper.insertSelective(fqStoreCheck);
	}

	@Override
	public Page<FqStore> queryFqStoreByPage(Long sellerid, Long wxstoreid, String wxstorename, Date date, Date date2,
			Integer status,Integer page,Integer pagesize) throws Exception {
		
		FqStoreExample example = new FqStoreExample();
		Criteria criteria = example.createCriteria();
		if(null!=sellerid){
			criteria.andSellerIdEqualTo(sellerid);
		}
		if(null!=wxstoreid){
			criteria.andIdEqualTo(wxstoreid);
		}
		if(!StringUtils.isEmpty(wxstorename)){
			criteria.andStoreNameLike("%"+wxstorename+"%");
		}
		if(null!=date){
			criteria.andCreateTimeGreaterThanOrEqualTo(date);
		}
		if(null!=date2){
			criteria.andCreateTimeLessThanOrEqualTo(date2);
		}
		if(null!=status){
			criteria.andStatusEqualTo(status);
		}
		example.setOrderByClause("id desc");
		PageHelper.startPage(page, pagesize);
		Page<FqStore> list  = (Page<FqStore>) fqStoreMapper.selectByExample(example);
		return list;
	}

	@Override
	public FqStore queryFqStoreById(Long id) throws Exception {
		return fqStoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateFqStore(Long id, Integer status, BigDecimal bigDecimal,Integer type, 
			Integer level,String clerkPhone,BigDecimal daliyCredit,Integer withdrawStatus) throws Exception{
		FqStore fqStore = fqStoreMapper.selectByPrimaryKey(id);
		fqStore.setStatus(status);
		fqStore.setStoreRebate(bigDecimal);
		fqStore.setType(type);
		fqStore.setLevel(level);
		fqStore.setClerkPhone(clerkPhone);
		fqStore.setDaliyCredit(daliyCredit);
		int i = fqStoreMapper.updateByPrimaryKey(fqStore);
		// 修改是否开启提现
		fqWithdrawChangeInfoService.updateOrInsertWithdrawChangeInfo(fqStore.getSellerId(), withdrawStatus);
		if(i==1&&status==1&&fqStore.getStatus()!=1){
			try {
				boolean sendSmsContent = MessageUtil.sendSmsContent(fqStore.getPhoneNum(), "亲爱的商家您好,您在飞卷网申请的微信店铺已经通过审核,请妥善保管.");
				logger.info(">>>>>>>>>>>>>>审核店铺ID"+fqStore.getId()+">>是否通过>>>"+sendSmsContent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	@Override
	public FqRebate queryFqRebateByStoreId(Long id) throws Exception {
		FqRebateExample example = new FqRebateExample();
		example.createCriteria().andStoreIdEqualTo(id);
		List<FqRebate> list = fqRebateMapper.selectByExample(example );
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<FqRebate> queryFqRebateByStoreIdList(Long storeid) throws Exception {
		FqRebateExample example = new FqRebateExample();
		example.createCriteria().andStoreIdEqualTo(storeid);
		List<FqRebate> list = fqRebateMapper.selectByExample(example );
		return list;
	}
	@Override
	public List<FqRebate> queryFqRebateByStoreIdList(Long id, int enable) throws Exception {
		FqRebateExample example = new FqRebateExample();
		example.createCriteria().andStoreIdEqualTo(id).andEnableEqualTo(enable);
		List<FqRebate> list = fqRebateMapper.selectByExample(example );
		return list;
	}

	@Override
	public Boolean saveOrUpdateFqRebate(Long sid, Long rid2, BigDecimal bigDecimal, String rebateInfo, String beginTime,
			String endTime,Integer enable) throws Exception {
		FqStore fqStore = queryFqStoreBySellerId(sid);
		if(fqStore==null|fqStore.getStatus()!=1){
			return false;
		}
		FqRebate fqRebate = fqRebateMapper.selectByPrimaryKey(rid2);
		if(null==rid2){
			FqRebate rebate = new FqRebate();
			rebate.setRebate(bigDecimal);
			rebate.setRebateInfo(rebateInfo);
			rebate.setEnable(enable);
			rebate.setBeginTime(beginTime);
			rebate.setEndTime(endTime);
			rebate.setStoreId(fqStore.getId());
		
			int insert = fqRebateMapper.insert(rebate);
			return insert==0?false:true;
		}else{
			FqStore store = queryFqStoreBySellerId(sid);
			if(null==store||store.getSellerId().longValue()!=sid.longValue()){
				return false;
			}
			fqRebate.setRebate(bigDecimal);
			fqRebate.setRebateInfo(rebateInfo);
			fqRebate.setEnable(enable);
			fqRebate.setBeginTime(beginTime);
			fqRebate.setEndTime(endTime);
			int key = fqRebateMapper.updateByPrimaryKey(fqRebate);
			return key==0?false:true;
		}
		
		
		
	}

	@Override
	public List<FqRebate> queryFqRebateListByfqStoreId(Long id) throws Exception {
		FqRebateExample example = new FqRebateExample();
		example.createCriteria().andStoreIdEqualTo(id);
		return fqRebateMapper.selectByExample(example );
	}

	@Override
	public Page<FqStoreVo> queryFqStoreByPageLocation(Long lid, int i, Integer page, int pagesize) throws Exception {
		PageHelper.startPage(page, pagesize);
		Page<FqStoreVo> sPage = (Page<FqStoreVo>) fqStoreVoMapper.selectByExample(lid);
		return sPage;
	}

	@Override
	public List<FqLocation> queryFqLocationListByStatsu(int i) throws Exception {
		FqLocationExample example = new FqLocationExample();
		example.createCriteria().andEnabledEqualTo(i);
		return fqLocationMapper.selectByExample(example);
	}

	@Override
	public FqStore queryFqStoreById(Long id, int enable) {
		FqStore store = fqStoreMapper.selectByPrimaryKey(id);
		if(store.getStatus()==enable){
			return store;
		}
		return null;
	}

	@Override
	public List<FqOrder> queryFqOrderListByOpenId(String openId, Integer page, Integer pagesize) throws Exception {
		FqUserInfoExample example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example);
		if(list.size()<1){
			return null;
		}
		FqUserInfo userinfo = list.get(0);
		FqOrderExample orderExample = new FqOrderExample();
		orderExample.setOrderByClause("create_time desc");
		orderExample.createCriteria().andUserIdEqualTo(userinfo.getId());
		PageHelper.startPage(page, pagesize);
		Page<FqOrder> orders = (Page<FqOrder>) fqOrderMapper.selectByExample(orderExample);
		if(page>orders.getPages()){
			return null;
		}
		return orders;
	}

	@Override
	public FqOrder queryFqorderByIdAndOpenId(Long id, String openId) throws Exception {
		FqOrder fqOrder = fqOrderMapper.selectByPrimaryKey(id);
		if(null==fqOrder){
			return null;
		}
		FqUserInfoExample example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example);
		if(list.size()>0&&fqOrder.getUserId()==list.get(0).getId()){
			return fqOrder;
		}
		return null;
	}

	@Override
	public List<FqOrderDetail> queryFqOrderDetailByOrderId(Long id) throws Exception {
		FqOrderDetailExample example = new FqOrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(id);
		List<FqOrderDetail> list = fqOrderDetailMapper.selectByExample(example );
		return list;
	}

	@Override
	public Page<FqRebate> queryFqRebateByStoreIdList(Long id, Integer page, Integer pageSize) {
		FqRebateExample example = new FqRebateExample();
		example.createCriteria().andStoreIdEqualTo(id);
		PageHelper.startPage(page, pageSize);
		Page<FqRebate> rebates = (Page<FqRebate>) fqRebateMapper.selectByExample(example);
		return rebates;
	}

	@Override
	public FqRebate queryFqRebateByStoreId(Long id, Long long1) {
		FqRebate rebate = fqRebateMapper.selectByPrimaryKey(id);
		if(null==rebate)
			return null;
		if(rebate.getStoreId().longValue()==long1.longValue()){
			return rebate;
		}
		return null;
	}

	@Override
	public FqRebate queryFqRebateById(Long id) throws Exception {
		FqRebate rebate = fqRebateMapper.selectByPrimaryKey(id);
		return rebate;
	}

	@Override
	public Boolean deleteFqrebateByIdAndStroeId(Long id, Long sid) throws Exception {
		
		FqRebateExample example = new FqRebateExample();
		example.createCriteria().andIdEqualTo(id).andStoreIdEqualTo(sid);
		int i = fqRebateMapper.deleteByExample(example );
		return i>0?true:false;
	}

	@Override
	public Page<FqStore> queryBorrowFqStoreByPage(Long sellerid, Long wxstoreid, String wxstorename, Integer page,
			Integer pagesize) throws Exception {
		FqStoreExample example = new FqStoreExample();
		Criteria criteria = example.createCriteria();
		if(null!=sellerid){
			criteria.andSellerIdEqualTo(sellerid);
		}
		if(null!=wxstoreid){
			criteria.andIdEqualTo(wxstoreid);
		}
		if(!StringUtils.isEmpty(wxstorename)){
			criteria.andStoreNameLike(wxstorename);
		}
		// 审核通过的商家
		criteria.andStatusEqualTo(1);
		example.setOrderByClause("id desc");
		PageHelper.startPage(page, pagesize);
		Page<FqStore> list  = (Page<FqStore>) fqStoreMapper.selectByExample(example);
		
		return list;
	}

	@Override
	public List<FqStore> queryFqStoreByStatus(int enable) {
		FqStoreExample example = new FqStoreExample();
		example.createCriteria().andStatusEqualTo(enable);
		return fqStoreMapper.selectByExample(example);
	}

	@Override
	public Page<FqStoreCheck> queryFqStoreCheckByPage(Long wxstoreid,
			String wxstorename, Date date, Date date2, Integer status,
			Integer page, Integer pagesize) throws Exception {
		FqStoreCheckExample example = new FqStoreCheckExample();
		cn.qhjys.mall.entity.FqStoreCheckExample.Criteria criteria = example.createCriteria();
		
		if(null!=wxstoreid){
			criteria.andStoreIdEqualTo(wxstoreid);
		}
		if(!StringUtils.isEmpty(wxstorename)){
			criteria.andStoreNameLike("%"+wxstorename+"%");
		}
		if(null!=date){
			criteria.andCreateTimeGreaterThanOrEqualTo(date);
		}
		if(null!=date2){
			criteria.andCreateTimeLessThanOrEqualTo(date2);
		}
		if(null!=status){
			criteria.andStatusEqualTo(status);
		}
		example.setOrderByClause("id desc");
		PageHelper.startPage(page, pagesize);
		Page<FqStoreCheck> list  = (Page<FqStoreCheck>) fqStoreCheckMapper.selectByExample(example);
		return list;
	}

	@Override
	public FqStoreCheck getFqStoreCheckById(Long id) {
		return fqStoreCheckMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateWxStoreCheck(Long id, Integer status) {
		FqStoreCheck fsc = fqStoreCheckMapper.selectByPrimaryKey(id);
		if (status==1) {
			FqStore store = fqStoreMapper.selectByPrimaryKey(fsc.getStoreId());
			store.setStoreName(fsc.getStoreName());
			store.setStoreLogo(fsc.getStoreLogo());
			store.setStoreInfo(fsc.getStoreInfo());
			store.setAddress(fsc.getAddress());
			store.setTrafficBeginTime(fsc.getTrafficBeginTime());
			store.setTrafficEndTime(fsc.getTrafficEndTime());
			store.setPhoneNum(fsc.getPhoneNum());
			store.setProportion(fsc.getProportion());
			store.setLocationId(fsc.getLocationId());
			store.setStoreImage(fsc.getStoreImage());
			fqStoreMapper.updateByPrimaryKeySelective(store);
		}
		fsc.setStatus(status);
		return fqStoreCheckMapper.updateByPrimaryKeySelective(fsc);
	}
	
}
