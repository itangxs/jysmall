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
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ScheduleInfo;
import cn.qhjys.mall.entity.ScheduleInfoExample;
import cn.qhjys.mall.mapper.ScheduleInfoMapper;
import cn.qhjys.mall.mapper.custom.SellerScheduleVoMapper;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.SellerScheduleService;
import cn.qhjys.mall.util.TIMECONST;
import cn.qhjys.mall.vo.SellerScheduleVo;
import cn.qhjys.mall.vo.seller.SellerScheduleInfoVo;
import cn.qhjys.mall.vo.seller.SellerScheduleListVo;
import cn.qhjys.mall.vo.seller.SellerScheduleProductInfoVo;

@Service
public class SellerScheduleServiceImpl implements SellerScheduleService {
	@Autowired
	private ProductService productService;
	@Autowired
	private ScheduleInfoMapper scheduleInfoMapper;
	@Autowired
	private SellerScheduleVoMapper sellerScheduleVoMapper;

	public SellerScheduleVoMapper getSellerScheduleVoMapper() throws Exception {
		return sellerScheduleVoMapper;
	}

	public void setSellerScheduleVoMapper(SellerScheduleVoMapper sellerScheduleVoMapper) throws Exception {
		this.sellerScheduleVoMapper = sellerScheduleVoMapper;
	}

	public ScheduleInfoMapper getScheduleInfoMapper() throws Exception {
		return scheduleInfoMapper;
	}

	public void setScheduleInfoMapper(ScheduleInfoMapper scheduleInfoMapper) throws Exception {
		this.scheduleInfoMapper = scheduleInfoMapper;
	}

	public ProductService getProductService() throws Exception {
		return productService;
	}

	public void setProductService(ProductService productService) throws Exception {
		this.productService = productService;
	}

	@Override
	public SellerScheduleListVo getSellerScheduleListVoBySellerId(Long id, Date date) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ProductInfo> list = productService.findProductList(id, 2, 1, null, null);
		SellerScheduleListVo vo = new SellerScheduleListVo();
		List<SellerScheduleInfoVo> list2 = new ArrayList<SellerScheduleInfoVo>();
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			throw new Exception(e);
		}
		Date day1 = date;
		Date day2 = new Date(day1.getTime() + TIMECONST.DAYLONG);
		Date day3 = new Date(day2.getTime() + TIMECONST.DAYLONG);
		Date day4 = new Date(day3.getTime() + TIMECONST.DAYLONG);
		Date day5 = new Date(day4.getTime() + TIMECONST.DAYLONG);
		Date day6 = new Date(day5.getTime() + TIMECONST.DAYLONG);
		Date day7 = new Date(day6.getTime() + TIMECONST.DAYLONG);
		Date day8 = new Date(day7.getTime() + TIMECONST.DAYLONG);
		for (ProductInfo pd : list) {
			SellerScheduleInfoVo ssv = new SellerScheduleInfoVo();
			ssv.setProductId(pd.getId());
			ssv.setProductTitle(pd.getTitle());
			ssv.setDay1(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day1, day2, null, null, null));
			ssv.setDay2(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day2, day3, null, null, null));
			ssv.setDay3(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day3, day4, null, null, null));
			ssv.setDay4(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day4, day5, null, null, null));
			ssv.setDay5(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day5, day6, null, null, null));
			ssv.setDay6(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day6, day7, null, null, null));
			ssv.setDay7(pd.getScheduleer()
					- getScheduleInfoCount(null, null, pd.getId(), null, null, null, null, null, null, null, null,
							day7, day8, null, null, null));
			list2.add(ssv);
		}
		vo.setDateList(list2);
		return vo;
	}

	private int getScheduleInfoCount(Long review_id, Long store_id, Long prod_id, Long reply_id, Integer reply_type,
			Integer anonymous, Integer enabled, Date create_time, Date create_time_than, Date create_time_less,
			Date reser_time, Date reser_time_than, Date reser_time_less, String phone, Integer status,
			Integer statusThan) throws Exception {
		ScheduleInfoExample example = getScheduleInfoExample(review_id, store_id, prod_id, reply_id, reply_type,
				anonymous, enabled, create_time, create_time_than, create_time_less, reser_time, reser_time_than,
				reser_time_less, phone, status, statusThan);
		return scheduleInfoMapper.countByExample(example);
	}

	private ScheduleInfoExample getScheduleInfoExample(Long review_id, Long store_id, Long prod_id, Long reply_id,
			Integer reply_type, Integer anonymous, Integer enabled, Date create_time, Date create_time_than,
			Date create_time_less, Date reser_time, Date reser_time_than, Date reser_time_less, String phone,
			Integer status, Integer statusThan) throws Exception {
		ScheduleInfoExample example = new ScheduleInfoExample();
		if (null != store_id)
			example.createCriteria().andSellerIdEqualTo(store_id);
		if (null != prod_id)
			example.createCriteria().andProdIdEqualTo(prod_id);
		if (null != reser_time)
			example.createCriteria().andReserTimeEqualTo(reser_time);
		if (null != reser_time_than)
			example.createCriteria().andReserTimeGreaterThan(reser_time_than);
		if (null != reser_time_less)
			example.createCriteria().andReserTimeLessThan(reser_time_less);
		if (null != create_time)
			example.createCriteria().andCreateTimeEqualTo(create_time);
		if (null != create_time_than)
			example.createCriteria().andCreateTimeGreaterThan(create_time_than);
		if (null != create_time_less)
			example.createCriteria().andCreateTimeLessThan(create_time_less);
		if (StringUtils.isNotBlank(phone))
			example.createCriteria().andReserPhoneEqualTo(phone);
		if (null != status)
			example.createCriteria().andStatusEqualTo(status);
		if (null != statusThan)
			example.createCriteria().andStatusGreaterThan(statusThan);
		return example;
	}

	@Override
	public List<SellerScheduleProductInfoVo> getSellerScheduleProductInfoVoList(Long id) throws Exception {
		List<ProductInfo> list2 = productService.findProductList(id, 2, 1, null, null);
		List<SellerScheduleProductInfoVo> list = new ArrayList<SellerScheduleProductInfoVo>();
		for (ProductInfo pd : list2) {
			list.add(getSellerScheduleProductInfoVoByProduct(pd));
		}
		return list;
	}

	@Override
	public SellerScheduleProductInfoVo getSellerScheduleProductInfoVo(Long sellerId, Long productId) throws Exception {
		ProductInfo pd = productService.findProduct(productId);
		if (pd.getStoreId().equals(sellerId)) {
			return getSellerScheduleProductInfoVoByProduct(pd);
		}
		return null;
	}

	private SellerScheduleProductInfoVo getSellerScheduleProductInfoVoByProduct(ProductInfo pd) throws Exception {
		if (null != pd) {
			SellerScheduleProductInfoVo ssv = new SellerScheduleProductInfoVo();
			ssv.setProductId(pd.getId());
			ssv.setScheduleer(pd.getScheduleer());
			ssv.setScheduleType(pd.getScheduleType());
			ssv.setTitle(pd.getTitle());
			return ssv;
		} else {
			return null;
		}

	}

	@Override
	public boolean updateProductSchedule(Long sellerId, Long productId, Integer scheduleType, Integer scheduleer)
			throws Exception {
		ProductInfo pd = productService.findProduct(productId);
		if (pd.getStoreId().equals(sellerId)) {
			pd.setScheduleType(scheduleType);
			pd.setScheduleer(scheduleer);
			return productService.updateProduct(sellerId, pd);
		}
		return false;
	}

	@Override
	public List<SellerScheduleVo> getSellerScheduleVoList(Long sellerId, int status, String phone) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("status", 1);
		if (StringUtils.isNotBlank(phone))
			map.put("phone", phone);
		return sellerScheduleVoMapper.querySellerScheduleVoBySellerId(map);
	}

	@Override
	public List<SellerScheduleVo> getSellerScheduleVoListThan(Long sellerId, int status, String phone) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("status", 1);
		if (StringUtils.isNotBlank(phone))
			map.put("phone", phone);
		return sellerScheduleVoMapper.querySellerScheduleVoBySellerIdThan(map);
	}

	@Override
	public boolean updateSellerSchedule(Long sellerId, Long id, Integer status) throws Exception {
		ScheduleInfo sif = scheduleInfoMapper.selectByPrimaryKey(id);
		if (sif.getSellerId().equals(sellerId)) {
			sif.setStatus(status);
			scheduleInfoMapper.updateByPrimaryKey(sif);
			return true;
		}
		return false;
	}

	@Override
	public List<SellerScheduleVo> getSellerScheduleVoBySellerList(Long sellerId, int i, String phone) throws Exception {
		ScheduleInfoExample example = getScheduleInfoExample(null, sellerId, null, null, 1, null, null, null, null,
				null, null, null, null, phone, 1, null);
		List<ScheduleInfo> list1 = scheduleInfoMapper.selectByExample(example);
		List<SellerScheduleVo> list = new ArrayList<SellerScheduleVo>();
		for (ScheduleInfo si : list1) {
			list.add(getSellerScheduleVoByScheduleInfo(si));
		}
		return list;
	}

	@Override
	public List<SellerScheduleVo> getSellerScheduleVoBySellerListThan(Long sellerId, int i, String phone)
			throws Exception {
		ScheduleInfoExample example = getScheduleInfoExample(null, sellerId, null, null, 1, null, null, null, null,
				null, null, null, null, phone, null, 1);
		List<ScheduleInfo> list1 = scheduleInfoMapper.selectByExample(example);
		List<SellerScheduleVo> list = new ArrayList<SellerScheduleVo>();
		for (ScheduleInfo si : list1) {
			list.add(getSellerScheduleVoByScheduleInfo(si));
		}
		return list;
	}

	private SellerScheduleVo getSellerScheduleVoByScheduleInfo(ScheduleInfo si) throws Exception {
		if (null != si) {
			SellerScheduleVo sv = new SellerScheduleVo();
			sv.setContent(si.getContent());
			sv.setCreateDate(si.getCreateTime());
			sv.setId(si.getId());
			sv.setName(si.getReserMan());
			sv.setPhone(si.getReserPhone());
			sv.setReserNum(si.getReserNum());
			sv.setReserTime(si.getReserTime());
			sv.setStatus(si.getStatus());
			return sv;
		}
		return null;
	}
}
