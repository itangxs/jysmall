package cn.qhjys.mall.service.app.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.AreaInfoExample;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CategoryInfoExample;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.DeliveryAddrExample;
import cn.qhjys.mall.entity.DistrictInfo;
import cn.qhjys.mall.entity.DistrictInfoExample;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.IntegralLogExample;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ScheduleInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.SysImgExample;
import cn.qhjys.mall.mapper.AreaInfoMapper;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CategoryInfoMapper;
import cn.qhjys.mall.mapper.DeliveryAddrMapper;
import cn.qhjys.mall.mapper.DistrictInfoMapper;
import cn.qhjys.mall.mapper.IntegralLogMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.ScheduleInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.SysImgMapper;
import cn.qhjys.mall.mapper.UserBrowsedMapper;
import cn.qhjys.mall.mapper.UserInfoMapper;
import cn.qhjys.mall.mapper.custom.OrderManageMapper;
import cn.qhjys.mall.mapper.custom.ProductMapper;
import cn.qhjys.mall.mapper.custom.ProductScheduleMapper;
import cn.qhjys.mall.mapper.custom.ReceivAddressMapper;
import cn.qhjys.mall.service.app.AppService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.vo.AppProdVo;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.ProdSchedule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AppServiceImpl implements AppService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	@Autowired
	private CategoryInfoMapper categoryInfoMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private IntegralLogMapper integralLogMapper;
	@Autowired
	private ProductScheduleMapper productScheduleMapper;
	@Autowired
	private OrderManageMapper orderManageMapper;
	@Autowired
	private ScheduleInfoMapper scheduleInfoMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserBrowsedMapper userBrowsedMapper;
	@Autowired
	private DistrictInfoMapper districtInfoMapper;
	@Autowired
	private ReceivAddressMapper receivAddressMapper;
	@Autowired
	private DeliveryAddrMapper deliveryAddrMapper;
	@Autowired
	private SysImgMapper sysImgMapper;

	@Override
	public Page<AppProdVo> selectBrowsedProduct(Long userId, Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		Page<AppProdVo> page = productMapper.selectBrowsedProduct(userId);
		return page;
	}

	@Override
	public JSONArray selectNearbyProduct(Double lngtd, Double lattd, Integer range, Long category, Long city,
			Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Page<Map<String, Object>> stors = null;
		PageHelper.startPage(pageNum, pageSize);
		if (lngtd == null || lattd == null || range == null)
			stors = productMapper.selectStore(city, category);
		else
			stors = productMapper.selectNearbyStore(lngtd, lattd, range, category);
		if (stors == null || stors.size() < 1)
			return new JSONArray();
		Long storeId = null;
		JSONObject json = null;
		JSONArray array = new JSONArray();
		for (Map<String, Object> map : stors) {
			json = new JSONObject();
			storeId = (Long) map.get("id");
			json.put("storeId", storeId);
			json.put("storeName", map.get("name"));
			json.put("distance", map.get("distance"));
			PageHelper.startPage(0, 3);
			Page<AppProdVo> page = productMapper.selectStoreProduct(storeId, category, city, "p.sales_num DESC");
			json.put("products", JSON.toJSON(page));
			if (page != null && page.size()>0) {
				array.add(json);
			}
		}
		return array;
	}

	@Override
	public Page<AppProdVo> queryHomeProduct(Long category, Long city, Integer pageNum, Integer pageSize)
			throws Exception {
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		Page<AppProdVo> page = productMapper.selectStoreProduct(null, category, city, " p.level desc,p.sales_num DESC");
		return page;
	}

	@Override
	public JSONObject getProdDetailById(Long userId, Long prodId) throws Exception {
		ProductInfo prod = productInfoMapper.selectByPrimaryKey(prodId);
		JSONObject json = new JSONObject();
		if (prod == null)
			return new JSONObject();
		StoreInfo stor = storeInfoMapper.selectByPrimaryKey(prod.getStoreId());
		if (stor == null)
			return new JSONObject();
		AreaInfo area = areaInfoMapper.selectByPrimaryKey(stor.getArea());
		if (area == null)
			return new JSONObject();
		json.put("prodId", prod.getId());
		json.put("prodName", prod.getName());
		json.put("prodType", prod.getProdType());
		json.put("area", area.getName());
		json.put("scoreAvg", prod.getScoreAvg());
		json.put("prodDetail", prod.getProdDetail());
		json.put("buyingTips", prod.getBuyingTips());
		json.put("imageUrl", (prod.getImages() + "").split("\\|")[0]);
		json.put("title", prod.getTitle());
		json.put("unitPrice", prod.getUnitPrice());
		json.put("dePrice", prod.getOrigPrice());
		json.put("salesNum", prod.getSalesNum());
		json.put("storeId", prod.getStoreId());
		json.put("storeName", stor.getName());
		json.put("lngtd", stor.getLongitude());
		json.put("lattd", stor.getLatitude());
		json.put("telephone", stor.getContactPhone());
		json.put("address", area.getName() + " " + stor.getAddress());
		json.put("category", prod.getCategoryId());
		return json;
	}

	@Override
	public Page<AppProdVo> queryStoreComboById(Long storeId, Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		Page<AppProdVo> page = productMapper.selectStoreProduct(storeId, null, null, "p.sales_num");
		return page;
	}

	@Override
	public JSONObject queryFilterContent(Long cityId) {
		JSONObject result = new JSONObject();
		CategoryInfoExample example = new CategoryInfoExample();
		example.setOrderByClause("id ASC");
		List<CategoryInfo> list = categoryInfoMapper.selectByExample(example);
		JSONArray array = new JSONArray();
		JSONObject obj = null;
		for (CategoryInfo info : list) {
			if (info.getParentId() == 0) {
				obj = new JSONObject();
				obj.put("id", info.getId());
				obj.put("text", info.getName());
				obj.put("childs", getCategoryTree(list, info.getId()));
				array.add(obj);
			}
		}
		result.put("category", array);
		if (cityId == null)
			cityId = 200L;
		AreaInfoExample example2 = new AreaInfoExample();
		example2.createCriteria().andCityIdEqualTo(cityId).andEnabledEqualTo(1);
		List<AreaInfo> areas = areaInfoMapper.selectByExample(example2);
		array = new JSONArray();
		for (AreaInfo info : areas) {
			obj = new JSONObject();
			obj.put("id", info.getId());
			obj.put("text", info.getName());
			obj.put("childs", getDistrict(info.getId()));
			array.add(obj);
		}
		result.put("area", array);
		array = new JSONArray();
		obj = new JSONObject();
		obj.put("id", "recent");
		obj.put("text", "离我最近");
		array.add(obj);
		obj = new JSONObject();
		obj.put("id", "reviews");
		obj.put("text", "评价最好");
		array.add(obj);
		obj = new JSONObject();
		obj.put("id", "popularity");
		obj.put("text", "人气最高");
		array.add(obj);
		obj = new JSONObject();
		obj.put("id", "discount");
		obj.put("text", "折扣最大");
		array.add(obj);
		result.put("sort", array);
		return result;
	}

	private JSONArray getCategoryTree(List<CategoryInfo> list, Long parent) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		for (CategoryInfo info : list) {
			if (parent == info.getParentId()) {
				obj = new JSONObject();
				obj.put("id", info.getId());
				obj.put("text", info.getName());
				obj.put("childs", getCategoryTree(list, info.getId()));
				array.add(obj);
			}
		}
		return array;
	}

	private JSONArray getDistrict(Long areaId) {
		if (areaId == null)
			return null;
		DistrictInfoExample example = new DistrictInfoExample();
		example.createCriteria().andAreaIdEqualTo(areaId).andEnabledEqualTo(1);
		List<DistrictInfo> list = districtInfoMapper.selectByExample(example);
		JSONArray array = new JSONArray();
		JSONObject json = null;
		for (int i = 0; i < list.size(); i++) {
			json = new JSONObject();
			json.put("id", list.get(i).getId());
			json.put("text", list.get(i).getName());
			array.add(json);
		}
		return array;
	}

	@Override
	public Page<AppProdVo> searchProduct(String keyword, Double lngtd, Double lattd, Integer range, Long cityId,
			Long areaId, Long category, String column, Integer pageNum, Integer pageSize) throws Exception {
	
		if (BaseUtil.judgeNull(column))
			column = "t.level DESC,t.saleNum DESC";
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Page<AppProdVo> page = null;
		if ("reviews".equals(column))
			column = "t.avgScore DESC";
		else if ("discount".equals(column))
			column = "t.dePrice DESC";
		else
			column = "t.saleNum DESC";
		PageHelper.startPage(pageNum, pageSize);
		if (lngtd == null || lattd == null)
			page = productMapper.searchAppProduct(keyword, cityId, areaId, category, column);
		else {
			if ("recent".equals(column))
				column = "t.distance ASC";
			page = productMapper.selectNearbyProduct(lngtd, lattd, range, keyword, cityId, areaId, category, column);
		}
		return page;
	}

	@Override
	public JSONObject getUserPurseByUserId(Long userId) throws Exception {
		JSONObject json = new JSONObject();
		if (userId == null)
			return json;
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountTypeEqualTo(0).andAccountIdEqualTo(userId);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		json.put("vipTickets", "初级会员");
		if (list == null || list.size() < 1) {
			json.put("integral", 0);
			json.put("balance", 0);
			return json;
		}
		CashAccount account = list.get(0);
		json.put("integral", account.getIntegral());
		json.put("balance", account.getBalance());
		return json;
	}

	@Override
	public JSONArray queryUserIntegralUserId(Long userId, Integer pageNum, Integer pageSize) throws Exception {
		if (userId == null)
			return new JSONArray();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		IntegralLogExample example = new IntegralLogExample();
		example.createCriteria().andTypeEqualTo(1).andReviewIdEqualTo(userId);
		example.or().andTypeEqualTo(2).andSendIdEqualTo(userId);
		List<IntegralLog> list = integralLogMapper.selectByExample(example);
		return (JSONArray) JSON.toJSON(list);
	}

	@Override
	public JSONObject queryUserBalanceUserId(Long userId) throws Exception {
		if (userId == null)
			return new JSONObject();
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(userId).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			return new JSONObject();
		return (JSONObject) JSON.toJSON(list.get(0));
	}

	@Override
	public JSONArray queryDeliveryAddr(Long userId) throws Exception {
		if (userId == null)
			return null;
		PageHelper.startPage(1, 6);
		Page<Map<String, Object>> page = receivAddressMapper.queryDeliveryAddr(userId);
		return (JSONArray) JSON.toJSON(page);
	}

	@Override
	public boolean addDeliveryAddr(DeliveryAddr addr) throws Exception {
		if (addr == null || addr.getUserId() == null)
			return false;
		if (addr.getIsdefault() == 1) {
			DeliveryAddrExample example = new DeliveryAddrExample();
			example.createCriteria().andUserIdEqualTo(addr.getUserId());
			DeliveryAddr uAdd = new DeliveryAddr();
			uAdd.setIsdefault(0);
			deliveryAddrMapper.updateByExampleSelective(uAdd, example);
		}
		int row = deliveryAddrMapper.insertSelective(addr);
		return row > 0;
	}

	@Override
	public boolean updateDeliveryAddr(DeliveryAddr addr) throws Exception {
		if (addr == null || addr.getId() == null)
			return false;
		if (addr.getIsdefault() == 1) {
			DeliveryAddrExample example = new DeliveryAddrExample();
			example.createCriteria().andUserIdEqualTo(addr.getUserId());
			DeliveryAddr uAdd = new DeliveryAddr();
			uAdd.setIsdefault(0);
			deliveryAddrMapper.updateByExampleSelective(uAdd, example);
		}
		int row = deliveryAddrMapper.updateByPrimaryKeySelective(addr);
		return row > 0;
	}

	@Override
	public JSONArray queryUserScheduleById(Long userId, Integer status, Integer pageNum, Integer pageSize)
			throws Exception {
		if (userId == null)
			return null;
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		Page<ProdSchedule> page = productScheduleMapper.queryProductSchedule(userId, status);
		return (JSONArray) JSON.toJSON(page);
	}

	@Override
	public boolean addScheduleOrderById(Long userId, Long detailId, Date reserTime, String name, String phone,
			String content) throws Exception {
		if (userId == null || detailId == null)
			return false;
		OrderDetailVo detail = orderManageMapper.getOrderDetail(userId, detailId);
		if (detail == null || detail.getStatus() != 2)
			return false;
		ProductInfo product = productInfoMapper.selectByPrimaryKey(detail.getProdId());
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(product.getStoreId());
		ScheduleInfo info = new ScheduleInfo();
		info.setUserId(userId);
		info.setProdId(detail.getProdId());
		info.setDetailId(detailId);
		info.setReserNum(detail.getQuantity());
		info.setReserTime(reserTime);
		info.setReserPhone(phone);
		info.setReserMan(name);
		info.setSellerId(store.getSellerId());
		info.setContent(content);
		info.setStatus(1);
		info.setCreateTime(new Date());
		int result = scheduleInfoMapper.insertSelective(info);
		return result > 0 ? true : false;
	}

	@Override
	public Page<AppProdVo> queryProdByBought(Long userId, Integer pageNum, Integer pageSize) {
		if (userId == null)
			return null;
		if (pageNum == null || pageNum < 0)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		return productMapper.queryProdByBought(userId);
	}

	@Override
	public JSONObject getAppHomeRecommend(byte type,long cityId) {
		SysImgExample example = new SysImgExample();
		example.createCriteria().andTypeEqualTo(type).andCityIdEqualTo(cityId);
		List<SysImg> list = sysImgMapper.selectByExample(example);
		JSONObject json = new JSONObject();
		for (int i = 0; i < 6; i++) {
			String img = null, store = null;
			if (list.size() > i) {
				SysImg sysImg = list.get(i);
				img = sysImg.getImg();
				store = sysImg.getField();
			}
			json.put("appImg" + (i + 1), img);
			json.put("storeId" + (i + 1), store);
		}
		return json;
	}
}