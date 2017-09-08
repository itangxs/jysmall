package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.ProdAudit;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.mapper.ProdAuditMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.custom.AuditProductMapper;
import cn.qhjys.mall.service.system.AuditProductService;
import cn.qhjys.mall.vo.system.AuditProductVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AuditProductServiceImpl implements AuditProductService {

	@Autowired
	private AuditProductMapper auditProductMapper;
	@Autowired
	private ProdAuditMapper prodAditMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;

	@Override
	public Page<AuditProductVo> queryAuditProductList(String productName, String storeName, BigDecimal startPrice,
			BigDecimal endPrice, String startTime, String endTime, Long status, Integer pageNum, Integer pageSize)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productName", productName);
		map.put("storeName", storeName);
		map.put("startPrice", startPrice);
		map.put("endPrice", endPrice);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		Long[] st = new Long[5];
		if (status == 0L) {
			st[0] = 0L;
		} else if (status == 1L) {
			st[0] = 1L;
		} else if (status == 2L) {
			st[0] = 2L;
		} else if (status == -1L) {
			st[0] = 0L;
			st[1] = 1L;
			st[2] = 2L;
		}
		map.put("status", st);
		PageHelper.startPage(pageNum, pageSize);
		Page<AuditProductVo> page = (Page<AuditProductVo>) auditProductMapper.queryAuditProductList(map);
		return page;
	}

	@Override
	public boolean updateAuditProduct(String[] ids, int status, Long adminId) throws Exception {
		int row = 0;
		int isSuccess = 0;
		ProductInfo product = new ProductInfo();
		product.setStatus(status);
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				product.setId(Long.parseLong(ids[i]));
				// ProductInfo productInfo =
				// productInfoMapper.selectByPrimaryKey(Long.parseLong(ids[i]));
				// if (productInfo.getStatus() != status) {
				row = productInfoMapper.updateByPrimaryKeySelective(product);
				if (row == 0)
					break;
				// }
			}
		}
		if (row > 0) {
			ProdAudit prodAudit = new ProdAudit();
			prodAudit.setAdminId(adminId);
			prodAudit.setStatus(status);
			for (int i = 0; i < ids.length; i++) {
				if (StringUtils.isNotEmpty(ids[i])) {
					prodAudit.setProdId(Long.parseLong(ids[i]));
					prodAudit.setTime(new Date());
					// ProductInfo productInfo =
					// productInfoMapper.selectByPrimaryKey(Long.parseLong(ids[i]));
					// if (productInfo.getStatus() != status) {
					isSuccess = prodAditMapper.insertSelective(prodAudit);
					if (isSuccess == 0)
						break;
					// }
				}
			}
		}
		return isSuccess > 0 ? true : false;
	}
}