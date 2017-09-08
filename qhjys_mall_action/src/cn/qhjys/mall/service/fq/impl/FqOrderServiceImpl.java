package cn.qhjys.mall.service.fq.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqOrderDetailExample;
import cn.qhjys.mall.entity.FqOrderExample;
import cn.qhjys.mall.entity.FqRandom;
import cn.qhjys.mall.entity.FqRandomExample;
import cn.qhjys.mall.entity.FqOrderExample.Criteria;
import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreExample;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.entity.StoreRebateExample;
import cn.qhjys.mall.mapper.FqOrderDetailMapper;
import cn.qhjys.mall.mapper.FqOrderMapper;
import cn.qhjys.mall.mapper.FqProductMapper;
import cn.qhjys.mall.mapper.FqRandomMapper;
import cn.qhjys.mall.mapper.FqRebateMapper;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.weixin.util.OrderUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("fqOrderService")
public class FqOrderServiceImpl implements FqOrderService{
	
	
	@Autowired
	private FqOrderMapper fqOrderMapper;
	@Autowired
	private FqOrderDetailMapper fqOrderDetailMapper;
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	@Autowired
	private FqStoreMapper fqStoreMapper;
	@Autowired
	private FqRebateMapper fqRebateMapper;
	@Autowired
	private FqProductMapper fqProductMapper;
	@Autowired
	private FqRandomMapper fqRandomMapper;
	@Autowired
	private StoreRebateMapper storeRebateMapper;

	@Override
	public Page<FqOrder> searchOrderListByStoreId(Long storeId, String orderNo, Integer status,
			String createTimeBegin, String createTimeEnding, String bookTimeBegin, String bookTimeEnding,
			Integer pageNum, Integer pageSize) throws Exception {
		FqOrderExample example = new FqOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		if(!StringUtils.isEmpty(orderNo)){
			criteria.andOrderNoLike(orderNo);
		}
		criteria.andStatusGreaterThan(1);
		
		if(!StringUtils.isEmpty(createTimeBegin)){
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(createTimeBegin+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(createTimeEnding)) {
			criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(createTimeEnding+" 23:59:59"));
		}
		if (!StringUtils.isEmpty(bookTimeBegin)) {
			criteria.andPreordainDateGreaterThanOrEqualTo(BaseUtil.toDate(bookTimeBegin+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(bookTimeEnding)) {
			criteria.andPreordainDateLessThanOrEqualTo(BaseUtil.toDate(bookTimeEnding+" 23:59:59"));
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqOrder>) fqOrderMapper.selectByExample(example);
	}

	@Override
	public Page<FqOrder> searchOrderListByParams(String storeName,
			String orderNo, Integer status, Integer payType,
			String createTimeBegin, String createTimeEnding,
			String bookTimeBegin, String bookTimeEnding, Integer pageNum,
			Integer pageSize) throws Exception {
		FqOrderExample example = new FqOrderExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(storeName)){
			criteria.andStoreNameLike(storeName);
		}
		if(!StringUtils.isEmpty(orderNo)){
			criteria.andOrderNoLike("%"+orderNo+"%");
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		if (payType != null) {
			criteria.andPayTypeEqualTo(payType);
		}
		if(!StringUtils.isEmpty(createTimeBegin)){
			criteria.andCreateTimeGreaterThanOrEqualTo(BaseUtil.toDate(createTimeBegin+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(createTimeEnding)) {
			criteria.andCreateTimeLessThanOrEqualTo(BaseUtil.toDate(createTimeEnding+" 23:59:59"));
		}
		if (!StringUtils.isEmpty(bookTimeBegin)) {
			criteria.andPreordainDateGreaterThanOrEqualTo(BaseUtil.toDate(bookTimeBegin+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(bookTimeEnding)) {
			criteria.andPreordainDateLessThanOrEqualTo(BaseUtil.toDate(bookTimeEnding+" 23:59:59"));
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqOrder>) fqOrderMapper.selectByExample(example);
	}

	@Override
	public FqOrder getFqOrder(Long id) {
		return fqOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FqOrderDetail> queryOrderDetailByOrderId(Long orderId) {
		FqOrderDetailExample example = new FqOrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		return fqOrderDetailMapper.selectByExample(example);
	}

	@Override
	public Long insertFqOrderAndDetail(String ids, String nums,
			 Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception {
		BigDecimal totalprice = BigDecimal.ZERO;
		List<FqOrderDetail> details = new ArrayList<FqOrderDetail>();
		String[] idarr = ids.split(",");
		String[] numarr = nums.split(",");
		for (int i = 0; i < idarr.length; i++) {
			Long id = Long.valueOf(idarr[i]);
			Integer num = Integer.valueOf(numarr[i]);
			FqProduct product = fqProductMapper.selectByPrimaryKey(id);
			totalprice = totalprice.add(product.getPrice().multiply(new BigDecimal(num)));
			FqOrderDetail detail = new FqOrderDetail();
			detail.setPrice(product.getPrice());
			detail.setProductId(product.getId());
			detail.setProductName(product.getProductName());
			detail.setQuantity(num);
			details.add(detail);
			if ((product.getStock() - num)<0) {
				return -3L;
			}else{
				product.setStock(product.getStock() - num);
				fqProductMapper.updateByPrimaryKeySelective(product);
			}
		}
		FqOrder order = new FqOrder();
		order.setCreateTime(new Date());
		order.setOrderNo(OrderUtil.getOrderNo("YD"));
		order.setDeskNo(deskNo);
		order.setContacts(username);
		order.setPhoneNum(phoneNum);
		order.setPeopleNum(peopleNum);
		FqStore store = fqStoreMapper.selectByPrimaryKey(storeId);
		StoreRebateExample example = new StoreRebateExample();
		example.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andBeginDateLessThanOrEqualTo(new Date()).andEndDateGreaterThanOrEqualTo(new Date());
		example.setOrderByClause("rebate asc");
		List<StoreRebate> list = storeRebateMapper.selectByExample(example);
		StoreRebate rebate= list.size()>0?list.get(0):null;
		if (rebate != null) {
			order.setRebate(rebate.getRebate());
			order.setRebateId(rebate.getId());
			order.setRebateAmount(totalprice.multiply(rebate.getRebate().divide(new BigDecimal(10))));
		}else{
			order.setRebate(new BigDecimal(10));
			order.setRebateAmount(totalprice);
		}
		order.setStatus(0);
		order.setStoreId(storeId);
		order.setStoreName(store.getStoreName());
		order.setStoreRebate(store.getStoreRebate());
		order.setTotalAmount(totalprice);
		order.setUserId(userId);
		FqRandomExample example3 = new FqRandomExample();
		example3.createCriteria().andExpiredTimeLessThan(new Date(System.currentTimeMillis()-86400000L*3));
		PageHelper.startPage(1, 1);
		List<FqRandom> list2 = fqRandomMapper.selectByExample(example3);
		FqRandom random = list2.get(0);
		order.setCoupon(String.valueOf(random.getRandomNum()));
		random.setExpiredTime(new Date(System.currentTimeMillis()+3*186400000L));
		fqRandomMapper.updateByPrimaryKeySelective(random);
		int a = fqOrderMapper.insertSelective(order);
		if (a<1) {
			return -1L;
		}
		FqOrderExample example2 = new FqOrderExample();
		example2.createCriteria().andOrderNoEqualTo(order.getOrderNo());
		example2.setOrderByClause("create_time desc");
		List<FqOrder> orders = fqOrderMapper.selectByExample(example2);
		FqOrder order1= orders.get(0);
		for (int i = 0; i < details.size(); i++) {
			FqOrderDetail detail = details.get(i);
			detail.setOrderId(order1.getId());
			a = fqOrderDetailMapper.insertSelective(detail);
			if (a<0) {
				return -2L;
			}
		}
		return order1.getId();
	}
	
	@Override
	public Long insertFqOrderAndDetailGd(String ids, String nums,
			Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception {
		BigDecimal totalprice = BigDecimal.ZERO;
		List<FqOrderDetail> details = new ArrayList<FqOrderDetail>();
		String[] idarr = ids.split(",");
		String[] numarr = nums.split(",");
		for (int i = 0; i < idarr.length; i++) {
			Long id = Long.valueOf(idarr[i]);
			Integer num = Integer.valueOf(numarr[i]);
			FqProduct product = fqProductMapper.selectByPrimaryKey(id);
			totalprice = totalprice.add(product.getPrice().multiply(new BigDecimal(num)));
			FqOrderDetail detail = new FqOrderDetail();
			detail.setPrice(product.getPrice());
			detail.setProductId(product.getId());
			detail.setProductName(product.getProductName());
			detail.setQuantity(num);
			details.add(detail);
			if ((product.getStock() - num)<0) {
				return -3L;
			}else{
				product.setStock(product.getStock() - num);
				fqProductMapper.updateByPrimaryKeySelective(product);
			}
		}
		FqOrder order = new FqOrder();
		order.setCreateTime(new Date());
		order.setOrderNo(OrderUtil.getOrderNo("DG"));
		order.setDeskNo(deskNo);
		order.setContacts(username);
		order.setPhoneNum(phoneNum);
		order.setPeopleNum(peopleNum);
		FqStore store = fqStoreMapper.selectByPrimaryKey(storeId);
		StoreRebateExample example = new StoreRebateExample();
		example.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andBeginDateLessThanOrEqualTo(new Date()).andEndDateGreaterThanOrEqualTo(new Date());
		example.setOrderByClause("rebate asc");
		List<StoreRebate> list = storeRebateMapper.selectByExample(example);
		StoreRebate rebate= list.size()>0?list.get(0):null;
		if (rebate != null) {
			order.setRebate(rebate.getRebate());
			order.setRebateId(rebate.getId());
			order.setRebateAmount(totalprice.multiply(rebate.getRebate().divide(new BigDecimal(10))));
		}else{
			order.setRebate(new BigDecimal(10));
			order.setRebateAmount(totalprice);
		}
		order.setStatus(0);
		order.setStoreId(storeId);
		order.setStoreName(store.getStoreName());
		order.setStoreRebate(store.getStoreRebate());
		order.setTotalAmount(totalprice);
		order.setUserId(userId);
		FqRandomExample example3 = new FqRandomExample();
		example3.createCriteria().andExpiredTimeLessThan(new Date(System.currentTimeMillis()-86400000L*3));
		PageHelper.startPage(1, 1);
		List<FqRandom> list2 = fqRandomMapper.selectByExample(example3);
		FqRandom random = list2.get(0);
		order.setCoupon(String.valueOf(random.getRandomNum()));
		random.setExpiredTime(new Date(System.currentTimeMillis()+3*186400000L));
		fqRandomMapper.updateByPrimaryKeySelective(random);
		int a = fqOrderMapper.insertSelective(order);
		if (a<1) {
			return -1L;
		}
		FqOrderExample example2 = new FqOrderExample();
		example2.createCriteria().andOrderNoEqualTo(order.getOrderNo());
		example2.setOrderByClause("create_time desc");
		List<FqOrder> orders = fqOrderMapper.selectByExample(example2);
		FqOrder order1= orders.get(0);
		for (int i = 0; i < details.size(); i++) {
			FqOrderDetail detail = details.get(i);
			detail.setOrderId(order1.getId());
			a = fqOrderDetailMapper.insertSelective(detail);
			if (a<0) {
				return -2L;
			}
		}
		return order1.getId();
	}
	@Override
	public Long insertFqOrderAndDetailPf(String ids, String nums,
			Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception {
		BigDecimal totalprice = BigDecimal.ZERO;
		List<FqOrderDetail> details = new ArrayList<FqOrderDetail>();
		String[] idarr = ids.split(",");
		String[] numarr = nums.split(",");
		for (int i = 0; i < idarr.length; i++) {
			Long id = Long.valueOf(idarr[i]);
			Integer num = Integer.valueOf(numarr[i]);
			FqProduct product = fqProductMapper.selectByPrimaryKey(id);
			totalprice = totalprice.add(product.getPrice().multiply(new BigDecimal(num)));
			FqOrderDetail detail = new FqOrderDetail();
			detail.setPrice(product.getPrice());
			detail.setProductId(product.getId());
			detail.setProductName(product.getProductName());
			detail.setQuantity(num);
			details.add(detail);
			if ((product.getStock() - num)<0) {
				return -3L;
			}else{
				product.setStock(product.getStock() - num);
				fqProductMapper.updateByPrimaryKeySelective(product);
			}
		}
		FqOrder order = new FqOrder();
		order.setCreateTime(new Date());
		order.setOrderNo(OrderUtil.getOrderNo("PG"));
		order.setDeskNo(deskNo);
		order.setContacts(username);
		order.setPhoneNum(phoneNum);
		order.setPeopleNum(peopleNum);
		FqStore store = fqStoreMapper.selectByPrimaryKey(storeId);
		StoreRebateExample example = new StoreRebateExample();
		example.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andBeginDateLessThanOrEqualTo(new Date()).andEndDateGreaterThanOrEqualTo(new Date());
		example.setOrderByClause("rebate asc");
		List<StoreRebate> list = storeRebateMapper.selectByExample(example);
		StoreRebate rebate= list.size()>0?list.get(0):null;
		if (rebate != null) {
			order.setRebate(rebate.getRebate());
			order.setRebateId(rebate.getId());
			order.setRebateAmount(totalprice.multiply(rebate.getRebate().divide(new BigDecimal(10))));
		}else{
			order.setRebate(new BigDecimal(10));
			order.setRebateAmount(totalprice);
		}
		order.setStatus(0);
		order.setStoreId(storeId);
		order.setStoreName(store.getStoreName());
		order.setStoreRebate(store.getStoreRebate());
		order.setTotalAmount(totalprice);
		order.setUserId(userId);
		FqRandomExample example3 = new FqRandomExample();
		example3.createCriteria().andExpiredTimeLessThan(new Date(System.currentTimeMillis()-86400000L*3));
		PageHelper.startPage(1, 1);
		List<FqRandom> list2 = fqRandomMapper.selectByExample(example3);
		FqRandom random = list2.get(0);
		order.setCoupon(String.valueOf(random.getRandomNum()));
		random.setExpiredTime(new Date(System.currentTimeMillis()+3*186400000L));
		fqRandomMapper.updateByPrimaryKeySelective(random);
		int a = fqOrderMapper.insertSelective(order);
		if (a<1) {
			return -1L;
		}
		FqOrderExample example2 = new FqOrderExample();
		example2.createCriteria().andOrderNoEqualTo(order.getOrderNo());
		example2.setOrderByClause("create_time desc");
		List<FqOrder> orders = fqOrderMapper.selectByExample(example2);
		FqOrder order1= orders.get(0);
		for (int i = 0; i < details.size(); i++) {
			FqOrderDetail detail = details.get(i);
			detail.setOrderId(order1.getId());
			a = fqOrderDetailMapper.insertSelective(detail);
			if (a<0) {
				return -2L;
			}
		}
		return order1.getId();
	}

	@Override
	public int deleteOrderNoPay() {
		Long timeMillis = System.currentTimeMillis();
		//Date beginTime = new Date(timeMillis - timeMillis%86400000 - 86400000/3*4);
		Date endTime = new Date(timeMillis - timeMillis%86400000 - 86400000/3);
		FqOrderExample example = new FqOrderExample();
		example.createCriteria().andStatusEqualTo(0).andCreateTimeLessThanOrEqualTo(endTime);
		return  fqOrderMapper.deleteByExample(example);
	}

	public static void main(String[] args) {
		Long timeMillis = System.currentTimeMillis();
		Date endTime = new Date(timeMillis - timeMillis%86400000);
	}

	@Override
	public Page<FqOrder> listFqOrderByStoreIdAndOpenId(Long sellerId,
			String openId, Integer pageSize, Integer pageNum) {
		FqUserInfoExample example1 = new FqUserInfoExample();
		example1.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example1);
		if (list.size() <=0) {
			return null;
		}
		FqStoreExample example2 = new FqStoreExample();
		example2.createCriteria().andSellerIdEqualTo(sellerId);
		List<FqStore> list2 = fqStoreMapper.selectByExample(example2);
		if (list2.size()>0) {
			FqOrderExample example = new FqOrderExample();
			Criteria criteria = example.createCriteria();
			criteria.andStoreIdEqualTo(list2.get(0).getId()).andUserIdEqualTo(list.get(0).getId()).andStatusGreaterThan(0);
			PageHelper.startPage(pageNum, pageSize);
			return (Page<FqOrder>) fqOrderMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public FqOrder getFqOrderByOrderNo(String orderNo) {
		FqOrderExample example = new FqOrderExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
		return recordlist.size()>0?recordlist.get(0):null;
	}
	
	@Override
	public Long insertFqOrderAndDetailMs(String orderHead,String ids, String nums,
			Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception {
		BigDecimal totalprice = BigDecimal.ZERO;
		List<FqOrderDetail> details = new ArrayList<FqOrderDetail>();
		String[] idarr = ids.split(",");
		String[] numarr = nums.split(",");
		for (int i = 0; i < idarr.length; i++) {
			Long id = Long.valueOf(idarr[i]);
			Integer num = Integer.valueOf(numarr[i]);
			FqProduct product = fqProductMapper.selectByPrimaryKey(id);
			totalprice = totalprice.add(product.getPrice().multiply(new BigDecimal(num)));
			FqOrderDetail detail = new FqOrderDetail();
			detail.setPrice(product.getPrice());
			detail.setProductId(product.getId());
			detail.setProductName(product.getProductName());
			detail.setQuantity(num);
			details.add(detail);
			if ((product.getStock() - num)<0) {
				return -3L;
			}else{
				product.setStock(product.getStock() - num);
				fqProductMapper.updateByPrimaryKeySelective(product);
			}
		}
		FqOrder order = new FqOrder();
		order.setCreateTime(new Date());
		order.setOrderNo(OrderUtil.getOrderNo(orderHead));
		order.setDeskNo(deskNo);
		order.setContacts(username);
		order.setPhoneNum(phoneNum);
		order.setPeopleNum(peopleNum);
		FqStore store = fqStoreMapper.selectByPrimaryKey(storeId);
		StoreRebateExample example = new StoreRebateExample();
		example.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andBeginDateLessThanOrEqualTo(new Date()).andEndDateGreaterThanOrEqualTo(new Date());
		example.setOrderByClause("rebate asc");
		List<StoreRebate> list = storeRebateMapper.selectByExample(example);
		StoreRebate rebate= list.size()>0?list.get(0):null;
		if (rebate != null) {
			order.setRebate(rebate.getRebate());
			order.setRebateId(rebate.getId());
			order.setRebateAmount(totalprice.multiply(rebate.getRebate().divide(new BigDecimal(10))));
		}else{
			order.setRebate(new BigDecimal(10));
			order.setRebateAmount(totalprice);
		}
		order.setStatus(0);
		order.setStoreId(storeId);
		order.setStoreName(store.getStoreName());
		order.setStoreRebate(store.getStoreRebate());
		order.setTotalAmount(totalprice);
		order.setUserId(userId);
		FqRandomExample example3 = new FqRandomExample();
		example3.createCriteria().andExpiredTimeLessThan(new Date(System.currentTimeMillis()-86400000L*3));
		PageHelper.startPage(1, 1);
		List<FqRandom> list2 = fqRandomMapper.selectByExample(example3);
		FqRandom random = list2.get(0);
		order.setCoupon(String.valueOf(random.getRandomNum()));
		random.setExpiredTime(new Date(System.currentTimeMillis()+3*186400000L));
		fqRandomMapper.updateByPrimaryKeySelective(random);
		int a = fqOrderMapper.insertSelective(order);
		if (a<1) {
			return -1L;
		}
		FqOrderExample example2 = new FqOrderExample();
		example2.createCriteria().andOrderNoEqualTo(order.getOrderNo());
		example2.setOrderByClause("create_time desc");
		List<FqOrder> orders = fqOrderMapper.selectByExample(example2);
		FqOrder order1= orders.get(0);
		for (int i = 0; i < details.size(); i++) {
			FqOrderDetail detail = details.get(i);
			detail.setOrderId(order1.getId());
			a = fqOrderDetailMapper.insertSelective(detail);
			if (a<0) {
				return -2L;
			}
		}
		return order1.getId();
	}
	
	
}
