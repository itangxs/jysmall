package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqChannelRole;
import cn.qhjys.mall.entity.FqChannelRoleExample;
import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.FqStoreApplyExample;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.FqStoreRateExample;
import cn.qhjys.mall.entity.FqStoreclerkChange;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.StoreAudit;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreCheckExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.entity.StoreInfoExample.Criteria;
import cn.qhjys.mall.mapper.FqChannelRoleMapper;
import cn.qhjys.mall.mapper.FqClerkVoMapper;
import cn.qhjys.mall.mapper.FqStoreApplyMapper;
import cn.qhjys.mall.mapper.FqStoreRateMapper;
import cn.qhjys.mall.mapper.FqStoreclerkChangeMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.StoreAuditMapper;
import cn.qhjys.mall.mapper.StoreCheckMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.custom.SellerUserMapper;
import cn.qhjys.mall.mapper.custom.StoreVoMapper;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.vo.SellerUserInfoVo;
import cn.qhjys.mall.vo.StorExporteVo;
import cn.qhjys.mall.vo.StoreVo;
import cn.qhjys.mall.vo.system.FqClerkVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private StoreVoMapper storeVoMapper;
	@Autowired
	private StoreCheckMapper storeCheckMapper;
	@Autowired
	private StoreAuditMapper storeAuditMapper;
	@Autowired
	private FqStoreRateMapper fqStoreRateMapper;
	@Autowired
	private FqStoreApplyMapper fqStoreApplyMapper;
	@Autowired
	private FqStoreclerkChangeMapper fqStoreclerkChangeMapper;
	@Autowired
	private FqClerkVoMapper fqClerkVoMapper;
	@Autowired
	private FqChannelRoleMapper fqChannelRoleMapper;
	@Autowired
	private SellerUserMapper sellerUserMapper;

	@Override
	public StoreInfo getStoreDetaile(Long id) throws Exception {
		return storeInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public StoreVo getStoreById(Long id) throws Exception {
		return storeVoMapper.getStoreById(id);
	}

	@Override
	public StoreInfo getStoreByProdId(Long prodId) throws Exception {
		ProductInfo product = productInfoMapper.selectByPrimaryKey(prodId);
		return storeInfoMapper.selectByPrimaryKey(product.getStoreId());
	}

	@Override
	public StoreVo getStoreVoByProdId(Long prodId) throws Exception {
		return storeVoMapper.getStoreByProdId(prodId);
	}

	@Override
	public Page<StoreVo> searchStores(String storeName, Long categoryId, Long cityId, Long areaId, String clause,
			Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null)
			pageSize = 20;
		if ("avgScore".equals(clause))
			clause = "avgScore DESC";
		else
			clause = "salesNum DESC";
		Map<String, Object> parmt = new HashMap<String, Object>();
		parmt.put("keywork", storeName);
		parmt.put("category", categoryId);
		parmt.put("cityId", cityId);
		parmt.put("areaId", areaId);
		parmt.put("column", clause);
		PageHelper.startPage(pageNum, pageSize);
		Page<StoreVo> page = storeVoMapper.searchStoreByKeyword(parmt);
		return page;
	}

	@Override
	public Page<StoreInfo> querySystemManageStoreByPage(Long id, String storeName, Long provinceId, Long cityId,
			Long areaId, Date date, Date date2, Integer status, Integer pageNum, Integer pageSize) throws Exception {
		StoreInfoExample example = new StoreInfoExample();
		Criteria criteria = example.createCriteria();
		if (null != id)
			criteria.andIdEqualTo(id);

		if (null != storeName)
			criteria.andNameEqualTo(storeName);

		if (null != provinceId)
			criteria.andProvinceEqualTo(provinceId);

		if (null != cityId)
			criteria.andCityEqualTo(cityId);

		if (null != areaId)
			criteria.andAreaEqualTo(areaId);

		if (null != date)
			criteria.andCreateTimeGreaterThan(date);

		if (null != date2)
			criteria.andCreateTimeLessThan(date2);

		if (null != status)
			criteria.andStatusEqualTo(status);

		PageHelper.startPage(pageNum, pageSize);
		List<StoreInfo> list = storeInfoMapper.selectByExampleWithBLOBs(example);
		return (Page<StoreInfo>) list;
	}

	@Override
	public Page<StoreVo> queryStoreVoByPage(String username, String storeName, Long storeId, Long provinceId, Long cityId, Long areaId,
			Date date, Date date2, Integer status, Integer cashierStatus, Integer orderStatus, Integer rateStatus,
			 Integer wxAuthState ,Integer zfbAuthState,Integer hangyeType2,Integer hangyeType,String yewuyuan ,Integer pageNum, Integer pageSize) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("storeName", storeName);
		map.put("storeId", storeId);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("areaId", areaId);
		map.put("begindate", date);
		map.put("enddate", date2);
		map.put("status", status);
		map.put("cashierStatus", cashierStatus);
		map.put("orderStatus", orderStatus);
		map.put("rateStatus", rateStatus);
		map.put("wxAuthState", wxAuthState);
		map.put("zfbAuthState", zfbAuthState);
		map.put("hangyeType", hangyeType);
		map.put("hangyeType2", hangyeType2);
		map.put("yewuyuan", yewuyuan);
		PageHelper.startPage(pageNum, pageSize);
		Page<StoreVo> page = storeVoMapper.searchStoreAndSeller(map);
		return page;
	}

	@Override
	public Boolean updateStoreStatusBySystem(long l, List<Long> strlist, Integer staus) throws Exception {
		for (int i = 0; i < strlist.size(); i++) {
			StoreInfo record = storeInfoMapper.selectByPrimaryKey(strlist.get(i));
			record.setStatus(staus);
			int j = storeInfoMapper.updateByPrimaryKeySelective(record);
			if (j == 0)
				return false;
		}
		return true;
	}
	@Override
	public Boolean updateScope(List<Long> strlist, Integer scope) throws Exception {
		for (int i = 0; i < strlist.size(); i++) {
			StoreInfo record = storeInfoMapper.selectByPrimaryKey(strlist.get(i));
			record.setScope(scope);
			int j = storeInfoMapper.updateByPrimaryKeySelective(record);
			if (j == 0)
				return false;
		}
		return true;
	}
	
	@Override
	public Boolean updateCashierStatus(List<Long> strlist, Integer cashierStatus) throws Exception {
		for (int i = 0; i < strlist.size(); i++) {
			StoreInfo record = new StoreInfo(); 
			record.setId(strlist.get(i));
			record.setOpenCashier(cashierStatus);
			int j =  storeInfoMapper.updateByPrimaryKeySelective(record);
			if (j == 0){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Boolean updateOrderStatus(List<Long> strlist, Integer orderStatus, Integer judge) throws Exception {
		if(judge == 0){
			for (int i = 0; i < strlist.size(); i++) {
				FqStoreApply fqStoreApply = fqStoreApplyMapper.selectByPrimaryKey(strlist.get(i));
				fqStoreApply.setStatus(orderStatus);
				int j = fqStoreApplyMapper.updateByPrimaryKeySelective(fqStoreApply);
				StoreInfo record = storeInfoMapper.selectByPrimaryKey(fqStoreApply.getStoreId()); 
				record.setOpenOrder(orderStatus);
				int k =  storeInfoMapper.updateByPrimaryKeySelective(record);
				if (j == 0 || k == 0){
					return false;
				}
			}
		}else{
			for (int i = 0; i < strlist.size(); i++) {
				StoreInfo record = storeInfoMapper.selectByPrimaryKey(strlist.get(i)); 
				record.setOpenOrder(orderStatus);
				FqStoreApply fsa = new FqStoreApply();
//				fsa.setStoreId(strlist.get(i));
				fsa.setStatus(orderStatus);
				FqStoreApplyExample example = new FqStoreApplyExample();
				example.createCriteria().andStoreIdEqualTo(strlist.get(i));
				int j =  storeInfoMapper.updateByPrimaryKeySelective(record);
				int k = fqStoreApplyMapper.updateByExampleSelective(fsa, example);
				if (j == 0 && k == 0){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public Boolean updatedeleteStoreBySystem(long l, Long id) throws Exception {
		int i = storeInfoMapper.deleteByPrimaryKey(id);
		return i < 1 ? false : true;
	}

	@Override
	public Page<StoreCheck> querySystemManageStoreCheckByPage(String storeName, Integer pageNum, Integer pageSize)
			throws Exception {
		StoreCheckExample example = new StoreCheckExample();
		if (null != storeName)
			example.createCriteria().andNameLike(storeName);
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNum, pageSize);
		List<StoreCheck> list = storeCheckMapper.selectByExampleWithBLOBs(example);
		return (Page<StoreCheck>) list;
	}

	@Override
	public Boolean updateStoreCheckStatusBySystem(long l, List<Long> strlist, Integer status) throws Exception {
		for (int i = 0; i < strlist.size(); i++) {
			StoreCheck storeCheck = storeCheckMapper.selectByPrimaryKey(strlist.get(i));
			StoreAudit storeAudit = new StoreAudit();
			storeAudit.setAdminId(l);
			storeAudit.setStoreId(storeCheck.getStoreId());
			storeAudit.setStatus(status);
			storeAudit.setTime(new Date());
			int count = storeAuditMapper.insertSelective(storeAudit);
			if (count == 0) {
				throw new Exception("添加店铺审核记录表失败");
			}
			storeCheck.setStatus(status);
			int row = storeCheckMapper.updateByPrimaryKeySelective(storeCheck);
			if (row == 0) {
				throw new Exception("修改审核修改表异常");
			}
			if (status == 2) {
				// 审核通过，修改店铺表
				StoreInfo record = storeInfoMapper.selectByPrimaryKey(storeCheck.getStoreId());
				record.setName(storeCheck.getName());
				record.setLogo(storeCheck.getLogo());
				record.setKeywork(storeCheck.getKeywork());
				record.setContactName(storeCheck.getContactName());
				record.setContactPhone(storeCheck.getContactPhone());
				record.setContactTel(storeCheck.getContactTel());
				record.setProvince(storeCheck.getProvince());
				record.setCity(storeCheck.getCity());
				record.setArea(storeCheck.getArea());
				record.setAddress(storeCheck.getAddress());
				record.setLongitude(storeCheck.getLongitude());
				record.setLatitude(storeCheck.getLatitude());
				record.setImages(storeCheck.getImages());
				record.setLabels(storeCheck.getLabels());
				record.setStoreDetail(storeCheck.getStoreDetail());
				record.setCategoryId(storeCheck.getCategoryId());
				record.setCategoryDetails(storeCheck.getCategoryDetails());
				record.setPromise(storeCheck.getPromise());
				int j = storeInfoMapper.updateByPrimaryKeySelective(record);
				if (j == 0) {
					throw new Exception("修改店铺表异常");
				}
			}
		}
		return true;
	}

	@Override
	public StoreInfo queryStoreInfoBySeller(Long sellerId) {
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		example.setOrderByClause("status desc,create_time desc");
		List<StoreInfo> stores = storeInfoMapper.selectByExample(example);
		return stores.size()>0?stores.get(0):null;
	}

	@Override
	public StoreInfo getStoreInfoById(Long storeId) {
		return storeInfoMapper.selectByPrimaryKey(storeId);
	}
	
	@Override
	public List<FqStoreRate> getFqStoreRate() {
		FqStoreRateExample example = new FqStoreRateExample();
		return fqStoreRateMapper.selectByExample(example);
	}
	
	@Override
	public FqStoreRate getFqStoreRateByRateName(String rateName) {
		FqStoreRateExample example = new FqStoreRateExample();
		example.createCriteria().andRateNameEqualTo(rateName);
		List<FqStoreRate> fsr = fqStoreRateMapper.selectByExample(example);
		if(fsr.size()>0){
			return fsr.get(0);
		}
		return null;
	}
	
	@Override
	public int updateStoreInfoRateId(Long storeId, Long rateId,Integer period){
		if (rateId != null) {
			FqStoreRate rate = fqStoreRateMapper.selectByPrimaryKey(rateId);
			if (rate == null) {
				rateId = null;
			}
		}
		StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
		storeInfo.setRateId(rateId);
		if (period != null && period>0) {
			storeInfo.setStatementPeriod(period);
		}
		return storeInfoMapper.updateByPrimaryKey(storeInfo);
	}

	@Override
	public Page<FqStoreApply> queryFqStoreApply(Long sellerId, Integer pageNum, Integer pageSize) {
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		pageSize = 10;
		FqStoreApplyExample example = new FqStoreApplyExample();
		if(null != sellerId){
			example.createCriteria().andSellerIdEqualTo(sellerId);
		}else{
			example.createCriteria();
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqStoreApply>) fqStoreApplyMapper.selectByExample(example);
	}

	@Override
	public int updateStoreInfoCategory(Long storeId, Long categoryId,
			String categoryDetail) {
		if (categoryId == null) {
			return 0;
		}
		StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
		storeInfo.setCategoryId(categoryId);
		storeInfo.setCategoryDetails(categoryDetail);
		return storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
	}

	@Override
	public int updateStoreInfoBinding(Long storeId, Long provinceId, Long cityId,
			Long clerkId) throws Exception {
		StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
		if(null != provinceId || !"".equals(provinceId)){
			storeInfo.setProvince(provinceId);
		}
		if(null != cityId || !"".equals(cityId)){
			storeInfo.setCity(cityId);
		}
		if(null != clerkId || !"".equals(clerkId)){
			storeInfo.setClerkId(clerkId);
		}
		int res = storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
		if(res > 0){
			FqStoreclerkChange fscc = new FqStoreclerkChange();
			fscc.setStoreId(storeId);
			fscc.setClerkId(clerkId);
			fscc.setCreateTime(new Date());
			return fqStoreclerkChangeMapper.insertSelective(fscc);
		}
		return 0;
	}

	@Override
	public FqClerkVo getClerkAndStore(Long storeId) throws Exception {
		if(null != storeId){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("storeId", storeId);
			return fqClerkVoMapper.getClerkAndStore(map);
		}
		return null;
	}

	@Override
	public FqStoreRate getFqStoreRateById(Long id) {
		return fqStoreRateMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateStoreEffective() {
		Date date = new Date();
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andClerkIdIsNotNull().andStatusEqualTo(2).andIsEffectiveEqualTo(0);
		List<StoreInfo> stores = storeInfoMapper.selectByExample(example);
		FqChannelRoleExample example2 = new FqChannelRoleExample();
		example2.createCriteria().andTypeEqualTo(1).andEffectiveTimeLessThanOrEqualTo(date);
		example2.setOrderByClause(" effective_time desc ");
		PageHelper.startPage(0, 1);
		List<FqChannelRole> roles = fqChannelRoleMapper.selectByExample(example2);
		if (roles.size()<1) {
			return 0;
		}
		int a = 0;
		FqChannelRole role = roles.get(0);
		for (int i = 0; i < stores.size(); i++) {
			StoreInfo store = stores.get(i);
			SellerUserInfoVo vo = sellerUserMapper.querySellerVo(store.getSellerId());
			if (role.getTotalNum()<=vo.getConNum()&&role.getTotalMoney().compareTo(vo.getConMoney())<0) {
				StoreInfo store1 = new StoreInfo();
				store1.setId(store.getId());
				store1.setIsEffective(1);
				store1.setEffectiveDate(date);
				a+=storeInfoMapper.updateByPrimaryKeySelective(store1);
			}
		}
		return a;
	}

	@Override
	public int updateByPrimaryKeySelective(StoreInfo storeInfo) {
		return storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
	}

	@Override
	public StoreInfo selectByPrimaryKey(Long id) {
		return storeInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StorExporteVo> queryStoreVoByExcel(String username, String storeName,
			Long storeId, Long provinceId, Long cityId, Long areaId, Date date,
			Date date2, Integer status, Integer cashierStatus,
			Integer orderStatus, Integer rateStatus, Integer wxAuthState,
			Integer zfbAuthState, Integer hangyeType) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("storeName", storeName);
		map.put("storeId", storeId);
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("areaId", areaId);
		map.put("begindate", date);
		map.put("enddate", date2);
		map.put("status", status);
		map.put("cashierStatus", cashierStatus);
		map.put("orderStatus", orderStatus);
		map.put("rateStatus", rateStatus);
		map.put("wxAuthState", wxAuthState);
		map.put("zfbAuthState", zfbAuthState);
		map.put("hangyeType", hangyeType);
		List<StorExporteVo> list = storeVoMapper.searchStoreExporte(map);
		return list;
	}

}