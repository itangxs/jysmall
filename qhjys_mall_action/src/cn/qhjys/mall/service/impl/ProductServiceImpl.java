package cn.qhjys.mall.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ProductInfoExample;
import cn.qhjys.mall.entity.ProductInfoExample.Criteria;
import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.entity.ScheduleInfoExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.entity.UserBrowsed;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.ReviewsLogMapper;
import cn.qhjys.mall.mapper.ScheduleInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.UserBrowsedMapper;
import cn.qhjys.mall.mapper.custom.ProductMapper;
import cn.qhjys.mall.mapper.custom.ProductScheduleMapper;
import cn.qhjys.mall.mapper.custom.ReviewMapper;
import cn.qhjys.mall.mapper.custom.UserProdBrowsedMapper;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.vo.AppProdVo;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.ProdSchedule;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/***
 * 
 * @author zengrong
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductScheduleMapper productScheduleMapper;
	@Autowired
	private ScheduleInfoMapper scheduleInfoMapper;
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private UserBrowsedMapper userBrowsedMapper;
	@Autowired
	private UserProdBrowsedMapper userProdBrowsedMapper;
	@Autowired
	private ReviewsLogMapper reviewsLogMapper;

	@Override
	public Map<String, List<ProductInfo>> searchProductByCategory(List<CategoryInfo> list, Long cityId, String clause,
			int pageSize) throws Exception {
		Map<String, List<ProductInfo>> map = new HashMap<String, List<ProductInfo>>();
		Map<String, Object> parmt;
		for (CategoryInfo info : list) {
			parmt = new HashMap<String, Object>();
			parmt.put("category", info.getId());
			parmt.put("cityId", cityId);
			parmt.put("clause", "p.level desc,p.sales_num desc ");
			PageHelper.startPage(0, 4);
			Page<ProductInfo> page = productMapper.searchProductByCategory(parmt);
			map.put(info.getName(), page);
		}
		return map;
	}
	
	@Override
	public Page<ProductInfo> searchProductByCategory(Long category, Long cityId, Long area, Integer maxUse,
			Integer priceRange, String clause, Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 10)
			pageSize = 30;
		if ("price".equals(clause))
			clause = "p.unit_price ASC";
		else if ("avgScore".equals(clause))
			clause = "p.score_avg DESC";
		else if ("publish".equals(clause))
			clause = "p.on_shelf ASC";
		else
			clause = "p.sales_num DESC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("cityId", cityId);
		map.put("area", area);
		map.put("maxUse", maxUse);
		map.put("priceRange", priceRange);
		map.put("clause", clause);
		PageHelper.startPage(pageNum, pageSize);
		Page<ProductInfo> page = productMapper.searchProductByCategory(map);
		return page;
	}

	@Override
	public Page<ProductInfo> searchProductByKeyWord(String keywork, Long category, Long cityId, Long area,
			Integer priceRange, Long store, String clause, Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 10)
			pageSize = 30;
		if ("price".equals(clause))
			clause = "p.unit_price ASC";
		else if ("avgScore".equals(clause))
			clause = "p.score_avg DESC";
		else if ("publish".equals(clause))
			clause = "p.on_shelf ASC";
		else
			clause = "p.sales_num DESC";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywork", keywork);
		map.put("category", category);
		map.put("cityId", cityId);
		map.put("area", area);
		map.put("priceRange", priceRange);
		map.put("store", store);
		map.put("clause", clause);
		PageHelper.startPage(pageNum, pageSize, true, true);
		Page<ProductInfo> page = productMapper.searchProductByKeyWord(map);
		return page;
	}

	@Override
	public ProductInfo getProductInfoById(Long storId, Long prodId) throws Exception {
		ProductInfoExample example = new ProductInfoExample();
		example.createCriteria().andIdEqualTo(prodId).andStoreIdEqualTo(storId);
		List<ProductInfo> list = productInfoMapper.selectByExampleWithBLOBs(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public ProductInfo getProductInfoById(Long prodId) throws Exception {
		return productInfoMapper.selectByPrimaryKey(prodId);
	}

	@Override
	public List<ProductInfo> selectProductByStoreId(Long storeId) throws Exception {
		PageHelper.startPage(0, 3);
		ProductInfoExample example = new ProductInfoExample();
		example.createCriteria().andStoreIdEqualTo(storeId).andStatusEqualTo(2).andEnabledEqualTo(1)
				.andOffShelfGreaterThan(new Date());
		return productInfoMapper.selectByExample(example);
	}

	@Override
	public List<ProductInfo> searchProductListByRand(Long cityId) throws Exception {
		PageHelper.startPage(0, 5);
		return productMapper.selectProductInfoByRand(cityId);
	}

	@Override
	public Page<EvaluateVo> queryEvaluateList(Long sellerId, String productName, int pageNum, int pageSize)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("productName", productName);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<EvaluateVo>) reviewMapper.queryEvaluateListByName(map);
	}

	@Override
	public Page<ProdSchedule> queryScheduleProduct(Long userId, Integer status, int pageNum, int pageSize)
			throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<ProdSchedule> page = productScheduleMapper.queryProductSchedule(userId, status);
		return page;
	}

	@Override
	public boolean deleteScheduleProduct(Long schedule, Long userId) throws Exception {
		ScheduleInfoExample example = new ScheduleInfoExample();
		example.createCriteria().andIdEqualTo(schedule).andUserIdEqualTo(userId);
		int row = scheduleInfoMapper.deleteByExample(example);
		return row > 0 ? true : false;
	}

	@Override
	public Page<ProductInfo> searchProductList(Long sid, String productName, String status, String addStartTime,
			String addEndTime, String stopStartTime, String stopEndTime, int pageNum, int pageSize)
			throws ParseException {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		ProductInfoExample example = new ProductInfoExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(addStartTime) && StringUtils.isNotEmpty(addEndTime)) {
			criteria.andCreateTimeBetween(sim.parse(addStartTime), sim.parse(addEndTime));
		} else if (StringUtils.isNotEmpty(stopStartTime) && StringUtils.isNotEmpty(stopEndTime)) {
			criteria.andEndDateBetween(sim.parse(stopStartTime), sim.parse(stopEndTime));
		} else if (StringUtils.isNotEmpty(productName)) {
			criteria.andNameEqualTo(productName);
		} else if (StringUtils.isNotEmpty(status) && !"01".equals(status)) {
			criteria.andStatusEqualTo(Integer.parseInt(status));
		} else if (StringUtils.isNotEmpty(addStartTime)) {
			criteria.andCreateTimeGreaterThanOrEqualTo(sim.parse(addStartTime));
		} else if (StringUtils.isNotEmpty(addEndTime)) {
			criteria.andCreateTimeLessThanOrEqualTo(sim.parse(addEndTime));
		} else if (StringUtils.isNotEmpty(stopStartTime)) {
			criteria.andEndDateGreaterThanOrEqualTo(sim.parse(stopStartTime));
		} else if (StringUtils.isNotEmpty(stopEndTime)) {
			criteria.andEndDateLessThanOrEqualTo(sim.parse(stopEndTime));
		}
		if (StringUtils.isNotEmpty(status) && "01".equals(status)) {
			List<Integer> values = new ArrayList<Integer>();
			values.add(0);
			values.add(1);
			criteria.andStatusIn(values);
		}
		criteria.andStoreIdEqualTo(sid);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<ProductInfo>) productInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public boolean saveProduct(ProductInfo product) throws Exception {
		int row = productInfoMapper.insertSelective(product);
		return row > 0 ? true : false;
	}

	@Override
	public ProductInfo findProduct(Long prodId) throws Exception {
		return productInfoMapper.selectByPrimaryKey(prodId);
	}

	@Override
	public boolean updateProduct(Long sellerId, ProductInfo product) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("record", product);
		map.put("sellerId", sellerId);
		int row = productMapper.updateProduct(map);
		return row > 0 ? true : false;
	}

	@Override
	public boolean deleteProduct(Long sellerId, Long[] productId) throws Exception {
		int row = 0;
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("sellerId", sellerId);
		for (int i = 0; i < productId.length; i++) {
			map.put("productId", productId[i]);
			row = productMapper.deleteProductById(map);
		}
		return row > 0 ? true : false;
	}

	@Override
	public Page<AppProdVo> queryRecommendProd(Long city, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<AppProdVo> page = productMapper.selectStoreProduct(null, null, city, "p.sales_num,p.score_avg");
		return page;
	}

	@Override
	public List<ProductInfo> findProductList(Long sellerid, Integer status, Integer enabled, Long category_id,
			Integer prod_type) throws Exception {
		ProductInfoExample example = new ProductInfoExample();
		example.clear();
		if (null != category_id)
			example.createCriteria().andCategoryIdEqualTo(category_id);
		if (null != sellerid)
			example.createCriteria().andStoreIdEqualTo(sellerid);
		if (null != status)
			example.createCriteria().andStatusEqualTo(status);
		if (null != enabled)
			example.createCriteria().andEnabledEqualTo(enabled);
		if (null != prod_type)
			example.createCriteria().andProdTypeEqualTo(prod_type);
		return productInfoMapper.selectByExample(example);
	}

	@Override
	public StoreInfo queryStoreBySid(Long sid) throws Exception {
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sid);
		List<StoreInfo> list = storeInfoMapper.selectByExample(example);
		if (list.size() > 0) {
			StoreInfo store = list.get(0);
			return store;
		} else {
			return null;
		}
	}

	@Override
	public EvaluateVo queryEvaluateDetail(Long reviewId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reviewId", reviewId);
		List<EvaluateVo> list = reviewMapper.queryEvaluateListByName(map);
		return list.get(0);
	}

	@Override
	public boolean updateProduct(Long sellId, Long[] prodIds, Integer status) throws Exception {
		int row = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellId);
		for (int i = 0; i < prodIds.length; i++) {
			ProductInfo info = productInfoMapper.selectByPrimaryKey(prodIds[i]);
			info.setStatus(status);
			info.setOffShelf(new Date());
			map.put("record", info);
			row = productMapper.updateProduct(map);
			if (row == 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean addUserBrowsed(Long userId, Long prodId) throws Exception {
		if (userId == null || prodId == null)
			return false;
		userProdBrowsedMapper.deleteExceedBrowsed(userId, 20);
		UserBrowsed record = new UserBrowsed();
		record.setProdId(prodId);
		record.setUserId(userId);
		record.setCreateTime(new Date());
		int row = userBrowsedMapper.insertSelective(record);
		return row > 0 ? true : false;
	}

	@Override
	public boolean insertSellerEvaluate(ReviewsLog review) throws Exception {
		int row = reviewsLogMapper.updateByPrimaryKeySelective(review);
		return row > 0 ? true : false;
	}

	@Override
	public boolean updateAutoShelfProduct() throws Exception {
		productMapper.updateAutoShelfProduct();
		return true;
	}

	@Override
	public List<ProductInfo> listProductInfoBycity(int pageNum, int pageSize,
			Long cityId) {
		Map<String, Object> parmt = new HashMap<String, Object>();
			parmt.put("cityId", cityId);
			parmt.put("clause", "p.level desc,p.sales_num desc");
			PageHelper.startPage(pageNum, pageSize);
		return productMapper.searchProductByCategory(parmt);
	}
}