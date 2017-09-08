package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.custom.ProductMapper;
import cn.qhjys.mall.service.system.ProductManageService;
import cn.qhjys.mall.vo.system.ProductManageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ProductManageServiceImpl implements ProductManageService {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public Page<ProductManageVo> queryManageProductList(String productName, String storeName, BigDecimal startPrice,
			BigDecimal endPrice, String startTime, String endTime, Long enabled, Integer pageNum, Integer pageSize)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		 //SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (!StringUtils.isEmpty(productName))
			map.put("productName", productName);
		if (!StringUtils.isEmpty(storeName))
			map.put("storeName", storeName);
		if (null != startPrice)
			map.put("startPrice", startPrice);
		if (null != endPrice)
			map.put("endPrice", endPrice);
		if (!StringUtils.isEmpty(startTime))
			map.put("startTime", startTime);
		if (!StringUtils.isEmpty(endTime))
			map.put("endTime", endTime);
		Long[] en = new Long[5];
		if (enabled == 0L) {
			en[0] = 0L;
		} else if (enabled == 1L) {
			en[0] = 1L;
		} else if (enabled == -1L) {
			en[0] = 0L;
			en[1] = 1L;
		}
		map.put("enabled", en);
		PageHelper.startPage(pageNum, pageSize);
		Page<ProductManageVo> page = (Page<ProductManageVo>) productMapper.queryManageProductList(map);
		return page;
	}

	@Override
	public boolean updateProduct(int enabled, String[] ids) throws Exception {
		int row = 0;
		ProductInfo product = new ProductInfo();
		product.setEnabled(enabled);
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				product.setId(Long.parseLong(ids[i]));
				product.setOnShelf(new Date());
				row = productInfoMapper.updateByPrimaryKeySelective(product);
				if (row == 0)
					break;
			}
		}
		return row > 0 ? true : false;
	}

	@Override
	public ProductInfo getProductById(Long prodId) throws Exception {
		return productInfoMapper.selectByPrimaryKey(prodId);
	}

	@Override
	public boolean updateProductTitleById(Long prodId,Integer level, String keyword, String desc) throws Exception {
		ProductInfo info = productInfoMapper.selectByPrimaryKey(prodId);
		if (info == null)
			return false;
		info.setLevel(level);
		info.setKeywords(keyword);
		info.setDescription(desc);
		productInfoMapper.updateByPrimaryKey(info);
		return true;
	}
}