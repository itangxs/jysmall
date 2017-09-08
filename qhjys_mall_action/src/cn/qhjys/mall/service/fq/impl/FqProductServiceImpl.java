package cn.qhjys.mall.service.fq.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqProductExample;
import cn.qhjys.mall.entity.FqProductExample.Criteria;
import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.entity.FqProductTypeExample;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.mapper.FqProductMapper;
import cn.qhjys.mall.mapper.FqProductTypeMapper;
import cn.qhjys.mall.service.fq.FqProductService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.vo.FqTypeAndProductVo;

@Service("fqProductService")
public class FqProductServiceImpl implements FqProductService{
	
	@Autowired
	private FqProductMapper fqProductMapper;
	@Autowired
	private FqProductTypeMapper fqProductTypeMapper;

	@Override
	public Page<FqProduct> searchProductListByStoreId(Long storeId, String name, Integer status, String tjsjs,
			String tjsje, Integer pageNum, Integer pageSize) throws Exception {
		FqProductExample example = new FqProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		if (!StringUtils.isEmpty(name)) {
			criteria.andProductNameEqualTo(name);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		if (!StringUtils.isEmpty(tjsjs)) {
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(tjsjs+" 00:00:00"));
		}
	    if (!StringUtils.isEmpty(tjsje)) {
	    	criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(tjsje+" 23:59:59"));
		}
	    example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqProduct>) fqProductMapper.selectByExample(example);
	}
	
	@Override
	public Page<FqProduct> searchProductListByProductType(Long productType, Long storeId, String name, Integer status, 
			String createStartTime,	String createEndTime, Integer pageNum, Integer pageSize) throws Exception {
		FqProductExample example = new FqProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId).andProductTypeEqualTo(productType);
		if (!StringUtils.isEmpty(name)) {
			criteria.andProductNameEqualTo(name);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		if (!StringUtils.isEmpty(createStartTime)) {
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(createStartTime+" 00:00:00"));
		}
	    if (!StringUtils.isEmpty(createEndTime)) {
	    	criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(createEndTime+" 23:59:59"));
		}
	    example.setOrderByClause("level desc,create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqProduct>) fqProductMapper.selectByExample(example);
	}

	@Override
	public boolean saveProduct(FqProduct product) throws Exception {
		int row = fqProductMapper.insertSelective(product);
		return row > 0 ? true : false;
	}

	@Override
	public FqProduct getProductInfoById(Long storeId, Long prodId) throws Exception {
		FqProductExample example = new FqProductExample();
		example.createCriteria().andIdEqualTo(prodId).andStoreIdEqualTo(storeId);
		List<FqProduct> products = fqProductMapper.selectByExample(example);
		return products.size() > 0 ? products.get(0) : null;
	}

	@Override
	public boolean updateProduct(FqProduct product) throws Exception {
		int row = fqProductMapper.updateByPrimaryKeySelective(product);
		return row > 0 ? true : false;
	}


	@Override
	public boolean updateProductStatus(Long[] ids, Integer status){
		FqProductExample example = new FqProductExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		FqProduct product = new FqProduct();
		product.setStatus(status);
		return fqProductMapper.updateByExampleSelective(product, example)>0?true:false;
	}


	@Override
	public boolean deleteProductByIds(Long[] ids) {
		FqProductExample example = new FqProductExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return fqProductMapper.deleteByExample(example) >0?true:false;
	}
	
	@Override
	public boolean deleteProductTypeById(Long[] ids){ 
		FqProductTypeExample example = new FqProductTypeExample();
		example.createCriteria().andIdIn(Arrays.asList(ids));
		return fqProductTypeMapper.deleteByExample(example) > 0?true:false;
	}

	@Override
	public List<FqProductType> queryproductTypeListByStore(Long storeid, Integer enable) throws Exception {
		FqProductTypeExample example = new FqProductTypeExample();
		cn.qhjys.mall.entity.FqProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeid);
		if(null!=enable){
			criteria.andEnableEqualTo(enable);
		}
		return fqProductTypeMapper.selectByExample(example);
	}

	@Override
	public Page<FqProductType> queryproductTypePageByStore(Long storeid, String name, Integer enable, String tjsjs, String tjsje, Integer pageNum, int pagesize)
			throws Exception {
		FqProductTypeExample example = new FqProductTypeExample();
		cn.qhjys.mall.entity.FqProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeid);
		if(null!=enable){
			criteria.andEnableEqualTo(enable);
		}
		if (!StringUtils.isEmpty(name)) {
			criteria.andTypeNameEqualTo(name);
		}
		if (!StringUtils.isEmpty(tjsjs)) {
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(tjsjs+" 00:00:00"));
		}
	    if (!StringUtils.isEmpty(tjsje)) {
	    	criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(tjsje+" 23:59:59"));
		}
	    example.setOrderByClause(" level desc ");
		PageHelper.startPage(pageNum,pagesize);
		Page<FqProductType> selectByExample = (Page<FqProductType>) fqProductTypeMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	public List<FqProductType> queryproductTypePageByStore(Long storeid, String name, Integer enable, String createStartTime, String createEndTime)
			throws Exception {
		FqProductTypeExample example = new FqProductTypeExample();
		cn.qhjys.mall.entity.FqProductTypeExample.Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeid);
		if(null!=enable){
			criteria.andEnableEqualTo(enable);
		}
		if (!StringUtils.isEmpty(name)) {
			criteria.andTypeNameEqualTo(name);
		}
		if (!StringUtils.isEmpty(createStartTime)) {
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(createStartTime+" 00:00:00"));
		}
	    if (!StringUtils.isEmpty(createEndTime)) {
	    	criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(createEndTime+" 23:59:59"));
		}
	    example.setOrderByClause(" level desc ");
		List<FqProductType> selectByExample = fqProductTypeMapper.selectByExample(example);
		return selectByExample;
	}

	@Override
	public FqProductType queryFqProductTypeBystorIdAndId(Long storId, Long id) throws Exception {
		FqProductType productType = fqProductTypeMapper.selectByPrimaryKey(id);
		if(productType.getStoreId()==storId){
			return productType;
		}
		return null;
	}


	@Override
	public Boolean saveOrUpdateFqProductType(Long storeid, String storeName, Long id, String typeName, Integer level,
			Integer enable) throws Exception {
		if(null!=id){
			FqProductType productType = fqProductTypeMapper.selectByPrimaryKey(id);
			if(null==productType|productType.getStoreId()!=storeid){
				return false;
			}
			productType.setTypeName(typeName);
			productType.setLevel(level);
			productType.setEnable(enable);
			int key = fqProductTypeMapper.updateByPrimaryKey(productType);
			return key>0?true:false;
		}else{
			FqProductType productType = new FqProductType();
			productType.setStoreId(storeid);
			productType.setStoreName(storeName);
			productType.setTypeName(typeName);
			productType.setLevel(0);
			productType.setEnable(enable);
			productType.setCreateTime(new Date());
			int key = fqProductTypeMapper.insert(productType);
			return key>0?true:false;
			
		}
	}

	@Override
	public boolean updateProductTypeStatusByStoreId(Long storeid, Long[] ids, Integer status) throws Exception {
		FqProductTypeExample example = new FqProductTypeExample();
		example.createCriteria().andIdIn(Arrays.asList(ids)).andStoreIdEqualTo(storeid);
		FqProductType product = new FqProductType();
		product.setEnable(status);
		return fqProductTypeMapper.updateByExampleSelective(product, example)>0?true:false;
	}

	@Override
	public List<FqTypeAndProductVo> queryProductByTypesAndStoreId(List<FqProductType> productTypes, Long storeId)
			throws Exception {
		List<FqTypeAndProductVo> typeAndProducts = new ArrayList<>();
		for (FqProductType fqProductType:productTypes) {
			FqTypeAndProductVo fqTypeAndProductVo = new FqTypeAndProductVo();
			FqProductExample example = new FqProductExample();
			Criteria criteria = example.createCriteria();
			criteria.andStoreIdEqualTo(storeId);
			criteria.andProductTypeEqualTo(fqProductType.getId());
			criteria.andStatusEqualTo(1);
			example.setOrderByClause(" level desc ");
			List<FqProduct> products = fqProductMapper.selectByExample(example);
			fqTypeAndProductVo.setTypeName(fqProductType.getTypeName());
			fqTypeAndProductVo.setProducts(products);
			typeAndProducts.add(fqTypeAndProductVo);
		}
		return typeAndProducts;
	}

	@Override
	public List<FqProduct> queryProductsWithOrderedByIdsAndStoreId(Long[] ids, Long storeId) throws Exception {
//		FqProductExample example = new FqProductExample();
//		Criteria criteria =  example.createCriteria();
//		criteria.andStoreIdEqualTo(storeId);
//		criteria.andIdIn(Arrays.asList(ids));
//		return fqProductMapper.selectByExample(example);
		// 根据特定顺序查
		List<FqProduct> products = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			FqProduct fqProduct = fqProductMapper.selectByPrimaryKey(ids[i]);
			if (fqProduct.getStoreId().equals(storeId)) {
				products.add(i, fqProduct);
			}
		}
		return products;
	}

	@Override
	public FqProduct queryProductByIdAndStoreId(Long id, Long storeId) throws Exception {
		FqProductExample example = new FqProductExample();
		example.createCriteria().andIdEqualTo(id).andStoreIdEqualTo(storeId);
		List<FqProduct> products = fqProductMapper.selectByExample(example);
		return products.size() > 0 ? products.get(0) : null;
	}

	@Override
	public List<FqProductType> updateProductTypeSortByLevel(Long[] ids, Long storeId)
			throws Exception {
		List<FqProductType> productTypes = new ArrayList<>();
		if(null != ids){
			Collections.reverse(Arrays.asList(ids));
			for(int i = ids.length-1; i >= 0 ; i--){
				FqProductType fqProductType = fqProductTypeMapper.selectByPrimaryKey(ids[i]);
				if(fqProductType.getStoreId().equals(storeId)){
					fqProductType.setLevel(i+1);
					int res = fqProductTypeMapper.updateByPrimaryKey(fqProductType);
					if(res > 0){
						productTypes.add(fqProductType);
					}
				}
			}
			return productTypes.size() == ids.length ? productTypes : null;
		}
		return null;
	}
	
	@Override
	public List<FqProduct> updateProductSortByLevel(Long[] ids, Long storeId, Long prodType)
			throws Exception {
		List<FqProduct> products = new ArrayList<>();
		if(null != ids){
			Collections.reverse(Arrays.asList(ids));
			for(int i = ids.length-1; i >= 0 ; i--){
				FqProduct fqProduct = fqProductMapper.selectByPrimaryKey(ids[i]);
				if(fqProduct.getStoreId().equals(storeId) && fqProduct.getProductType().equals(prodType)){
					fqProduct.setLevel(i+1);
					int res = fqProductMapper.updateByPrimaryKey(fqProduct);
					if(res > 0){
						products.add(fqProduct);
					}
				}
			}
			return products.size() == ids.length ? products : null;
		}
		return null;
	}
}
