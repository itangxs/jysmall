package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.mapper.custom.SystemOperateVoMapper;
import cn.qhjys.mall.service.system.SystemOperateService;
import cn.qhjys.mall.util.DateTimeUtil;
import cn.qhjys.mall.vo.system.SalesDetailVo;
import cn.qhjys.mall.vo.system.SalesofGoodsVo;
import cn.qhjys.mall.vo.system.ShopAreaVo;
import cn.qhjys.mall.vo.system.ShopSalesVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SystemOperateServiceImpl implements SystemOperateService {

	@Autowired
	private SystemOperateVoMapper systemOperateVoMapper;

	@Override
	public Page<SalesofGoodsVo> querySalesofGoodsVoPage(String proName, String storName, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(proName)) {
			map.put("proName", proName);
		}
		if (!StringUtils.isEmpty(storName)) {
			map.put("storName", storName);
		}
		PageHelper.startPage(pageNum, pageSize);
		return systemOperateVoMapper.querySalesofGoodsList(map);
	}

	@Override
	public Page<SalesDetailVo> querySalesofGoodsVoPage(String orderNo, String proName, Date date, Date date2,
			String stoName, Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(orderNo)) {
			map.put("orderNo", orderNo);
		}
		if (!StringUtils.isEmpty(proName)) {
			map.put("productName", proName);
		}
		if (!StringUtils.isEmpty(stoName)) {
			map.put("storeName", stoName);
		}

		if (null != date) {
			map.put("startDate", date);
		}
		if (null != date2) {
			map.put("endDate", date2);
		}
		PageHelper.startPage(pageNum, pageSize);
		return systemOperateVoMapper.querySalesDetailList(map);
	}

	@Override
	public Page<ShopAreaVo> queryShopAreaVoPage(String city, String area, Date date, Date date2, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(city)) {
			map.put("city", city);
		}
		if (!StringUtils.isEmpty(area)) {
			map.put("area", area);
		}

		if (null != date) {
			map.put("startDate", date);
		}
		if (null != date2) {
			map.put("endDate", date2);
		}
		PageHelper.startPage(pageNum, pageSize);
		return systemOperateVoMapper.queryShopAreaList(map);
	}

	@Override
	public Page<ShopSalesVo> queryShopSalesVoPage(String sellerId, String storeName, Integer pageNum, Integer pageSize)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(sellerId)) {
			map.put("sellerId", sellerId);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		PageHelper.startPage(pageNum, pageSize);
		return systemOperateVoMapper.queryShopSalesList(map);
	}

	@Override
	public Page<ShopSalesVo> queryShopDaySalesVoPage(String sellerId, String storeName, Date date, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(sellerId)) {
			map.put("sellerId", sellerId);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		if (null != date) {
			map.put("start", DateTimeUtil.getStartTime(date));
			map.put("end", DateTimeUtil.getEndTime(date));
		}
		PageHelper.startPage(pageNum, pageSize);
		return systemOperateVoMapper.queryShopDaySalesList(map);
	}

	@Override
	public Page<ShopSalesVo> queryShopMonthSalesVoPage(String sellerId, String storeName, Date date, Date date2,
			Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(sellerId)) {
			map.put("sellerId", sellerId);
		}
		if (!StringUtils.isEmpty(storeName)) {
			map.put("storeName", storeName);
		}
		if (date != null && date2 != null) {
			map.put("start", date);
			map.put("end", date2);
		} else if (date == null || date2 == null) {
			if (date == null && date2 != null) {
				map.put("date", date2);
			}
			if (date != null && date2 == null) {
				map.put("date", date);
			}
		}
		PageHelper.startPage(pageNum, pageSize);
		return systemOperateVoMapper.queryShopMonthSalesList(map);
	}

}
