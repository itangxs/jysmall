package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CartInfoExample;
import cn.qhjys.mall.entity.CartInfoExample.Criteria;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.OrderDetailExample;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.entity.OrderInfoExample;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserRefund;
import cn.qhjys.mall.entity.UserRefundExample;
import cn.qhjys.mall.entity.VolumeInfo;
import cn.qhjys.mall.entity.VolumeInfoExample;
import cn.qhjys.mall.mapper.CartInfoMapper;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.CategoryInfoMapper;
import cn.qhjys.mall.mapper.CouponInfoMapper;
import cn.qhjys.mall.mapper.DeliveryAddrMapper;
import cn.qhjys.mall.mapper.OrderDetailMapper;
import cn.qhjys.mall.mapper.OrderInfoMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.UserRefundMapper;
import cn.qhjys.mall.mapper.VolumeInfoMapper;
import cn.qhjys.mall.mapper.custom.OrderManageMapper;
import cn.qhjys.mall.mapper.custom.ProductMapper;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.PaymentService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.OrderResult;
import cn.qhjys.mall.util.OrderResult.OrderEnum;
import cn.qhjys.mall.vo.AppProdVo;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.OrderVo;
import cn.qhjys.mall.vo.RefundVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class OrderServiceImpl implements OrderService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private DeliveryAddrMapper deliveryAddrMapper;
	@Autowired
	private CashLogMapper cashlogMapper;
	@Autowired
	private OrderManageMapper orderManageMapper;
	@Autowired
	private VolumeInfoMapper volumeInfoMapper;
	@Autowired
	private UserRefundMapper userRefundMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private CartInfoMapper cartInfoMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CategoryInfoMapper categoryInfoMapper;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserInfoService userService;
	@Autowired
	private CouponInfoMapper couponInfoMapper;
	@Autowired
	private MessageInfoService messageInfoService;
	// 兑换比例
	private BigDecimal scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));

	@Override
	public Page<OrderVo> queryOrderByUser(Long userId, Long[] status, int pageNum, int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<OrderVo> page = (Page<OrderVo>) orderManageMapper.queryOrderList(userId, status);
		List<OrderDetailVo> list;
		for (OrderVo order : page) {
			list = orderManageMapper.queryOrderDetail(order.getOrderId(), null, status);
			order.setList(list);
			List<VolumeInfo> volumes;
			for (OrderDetailVo orderDetail : list) {
				VolumeInfoExample example = new VolumeInfoExample();
				example.createCriteria().andDetailIdEqualTo(orderDetail.getDetailId());
				volumes = volumeInfoMapper.selectByExample(example);
				orderDetail.setVolumes(volumes);
				if (orderDetail.getStoImg() != null && orderDetail.getStoImg().length()>10) {
					String [] imgs = orderDetail.getStoImg().split(",");
					if (imgs.length>0) {
						orderDetail.setStoImg(imgs[0]);
					}
				}
			}
		}
		return page;
	}

	@Override
	public OrderInfo getOrderInfoByExample(Long orderId, Long userId) throws Exception {
		OrderInfo order = orderInfoMapper.selectByPrimaryKey(orderId);
		if (order == null || !order.getUserId().equals(userId))
			order = null;
		return order;
	}

	@Override
	public OrderDetail getOrderDetail(Long detailId) throws Exception {
		return orderDetailMapper.selectByPrimaryKey(detailId);
	}

	@Override
	public OrderVo getOrderInfo(Long userId, Long orderId) throws Exception {
		OrderVo order = orderManageMapper.getOrderVoByOrder(userId, orderId);
		if (order == null)
			return order;
		List<OrderDetailVo> list = orderManageMapper.queryOrderDetail(order.getOrderId(), userId, null);
		order.setList(list);
		List<VolumeInfo> volumes;
		for (OrderDetailVo orderDetail : list) {
			VolumeInfoExample example = new VolumeInfoExample();
			example.createCriteria().andDetailIdEqualTo(orderDetail.getDetailId());
			volumes = volumeInfoMapper.selectByExample(example);
			orderDetail.setVolumes(volumes);
		}
		DeliveryAddr addr = deliveryAddrMapper.selectByPrimaryKey(order.getDelivId());
		order.setAddr(addr);
		CashLog cashLog = cashlogMapper.selectByPrimaryKey(order.getPayId());
		order.setCashLog(cashLog);
		return order;
	}

	@Override
	public boolean deleteOrderById(Long userId, Long orderId) throws Exception {
		OrderDetailExample dexample = new OrderDetailExample();
		dexample.createCriteria().andOrderIdEqualTo(orderId);
		orderDetailMapper.deleteByExample(dexample);
		OrderInfoExample example = new OrderInfoExample();
		example.createCriteria().andIdEqualTo(orderId).andUserIdEqualTo(userId);
		OrderInfo order = orderInfoMapper.selectByPrimaryKey(orderId);
		Long couponId = null;
		if (order != null)
			couponId = order.getCouponId();
		int row = orderInfoMapper.deleteByExample(example);
		if (couponId != null) {
			CouponInfo info = couponInfoMapper.selectByPrimaryKey(couponId);
			info.setId(couponId);
			info.setOrderId(null);
			info.setConsume(0);
			info.setConsumeTime(null);
			couponInfoMapper.updateByPrimaryKey(info);
		}
		return row > 0 ? true : false;
	}

	@Override
	public Page<OrderDetailVo> queryOrderDetailList(Long userId, Long[] status, int pageNum, int pageSize)
			throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<OrderDetailVo> page = (Page<OrderDetailVo>) orderManageMapper.queryOrderDetailList(userId, status);
		return page;
	}

	@Override
	public OrderDetailVo getOrderDetail(Long userId, Long detailId) throws Exception {
		OrderDetailVo detail = orderManageMapper.getOrderDetail(userId, detailId);
		VolumeInfoExample example = new VolumeInfoExample();
		example.createCriteria().andDetailIdEqualTo(detailId);
		List<VolumeInfo> list = volumeInfoMapper.selectByExample(example);
		detail.setVolumes(list);
		return detail;
	}

	@Override
	public boolean insertRefund(UserRefund refund) throws Exception {
		if (refund == null || refund.getUserId() == null || refund.getDetailId() == null)
			return false;
		OrderDetail detail = new OrderDetail();
		detail.setId(refund.getDetailId());
		detail.setStatus(5);
		detail.setApplyTime(new Date());
		orderDetailMapper.updateByPrimaryKeySelective(detail);
		UserRefundExample example = new UserRefundExample();
		example.createCriteria().andUserIdEqualTo(refund.getUserId()).andDetailIdEqualTo(refund.getDetailId());
		int count = userRefundMapper.countByExample(example);
		if (count > 0)
			return false;
		refund.setStatus(1);
		refund.setCreateTime(new Date());
		int row = userRefundMapper.insertSelective(refund);
		return row > 0;
	}

	@Override
	public Page<OrderDetailVo> searchOrderListBySellerId(Long sellerId, String detailNo, String prodName,
			Long[] orstat, String begin, String ending, String userName, Long[] restat, Integer pageNum,
			Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("detailNo", detailNo);
		map.put("productName", prodName);
		map.put("startTime", begin);
		map.put("endTime", ending);
		map.put("userName", userName);
		if (orstat != null && orstat.length > 0)
			map.put("orStatus", orstat);
		if (restat != null && restat.length > 0)
			map.put("reStatus", restat);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<OrderDetailVo>) orderManageMapper.searchOrderListBySellerId(map);
	}

	@Override
	public int getOrderCountBySeller(Long sellerId, String detailNo, String prodName, Long[] orstat, String begin,
			String ending, String userName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("detailNo", detailNo);
		map.put("productName", prodName);
		map.put("startTime", begin);
		map.put("endTime", ending);
		map.put("userName", userName);
		if (orstat != null && orstat.length > 0)
			map.put("orStatus", orstat);
		return orderManageMapper.getNotReceiptOrder(map);
	}

	@Override
	public Page<RefundVo> queryRefundListBySellerId(Long sellerId, String detailNo, Long orderStatus, int pageNum,
			int pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		return (Page<RefundVo>) orderManageMapper.queryRefundListBySellerId(sellerId, detailNo, orderStatus);
	}

	@Override
	public UserRefund getRefundById(Long id) throws Exception {
		return userRefundMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<OrderDetailVo> queryOrderListByAdmin(String orderNo, String detailNo, Integer status, String storeName,
			String userName, String begin, String ending, Integer pageNum, Integer pageSize) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 10)
			pageSize = 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderNo", orderNo);
		map.put("detailNo", detailNo);
		map.put("status", status);
		map.put("storeName", storeName);
		map.put("userName", userName);
		map.put("begin", StringUtils.isEmpty(begin) ? null : dateFormat.parse(begin));
		map.put("ending", StringUtils.isEmpty(ending) ? null : dateFormat.parse(ending));
		PageHelper.startPage(pageNum, pageSize);
		Page<OrderDetailVo> page = (Page<OrderDetailVo>) orderManageMapper.queryOrderListByAdmin(map);
		return page;
	}

	@Override
	public CashAccount queryCashAccount(Long accountId) throws Exception {
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(accountId).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ProductInfo queryProductById(Long productId) throws Exception {
		return productInfoMapper.selectByPrimaryKey(productId);
	}

	@Override
	public boolean updateCahsAccount(CashAccount cashAccount) throws Exception {
		if (cashAccount == null || cashAccount.getId() == null)
			return false;
		int row = cashAccountMapper.updateByPrimaryKeySelective(cashAccount);
		return row > 0;
	}

	@Override
	public boolean updateProductById(ProductInfo info) throws Exception {
		if (info == null || info.getId() == null)
			return false;
		int row = productInfoMapper.updateByPrimaryKeySelective(info);
		return row > 0;
	}

	@Override
	public boolean insertOrderDetail(OrderDetail orderDetail) throws Exception {
		int row = orderDetailMapper.insertSelective(orderDetail);
		return row > 0 ? true : false;
	}

	@Override
	public boolean insertOrderInfo(OrderInfo orderInfo) throws Exception {
		int row = orderInfoMapper.insertSelective(orderInfo);
		return row > 0 ? true : false;
	}

	@Override
	public OrderResult insertUserOrder(Long userId, Long[] prodIds,Long[] storeIds, BigDecimal[] payPrics, Integer[] payNums,
			Long addrId, String phone, Long couponId,Date [] createTimes) throws Exception {
		OrderResult result = new OrderResult();
		if (prodIds.length != payPrics.length || prodIds.length != payNums.length) {
			result.setResult(false);
			result.setFlag(OrderEnum.PROD_ERROR.tag);
			result.setReason(OrderEnum.PROD_ERROR.msg);
			return result;
		}
		// 判断手机号是否为空，不为空就把手机号添加到用户表
		if (StringUtils.isNotEmpty(phone)) {
			UserInfo user = new UserInfo();
			user.setId(userId);
			user.setPhoneNum(phone);
			logger.info("进入userService类的updateUserById方法");
			boolean row = userService.updateUserById(user);
			if (!row)
				throw new Exception("添加用户手机号错误");
		}
		boolean error = false;
		// 判断商品编号和数量的有效性
		for (int i = 0; i < prodIds.length; i++) {
			if(prodIds[i] != null){
			AppProdVo prod = productMapper.getProductInfoById(prodIds[i], payNums[i]);
			if (prod == null) {
				error = true;
				break;
			}
			}
		}
		if (error) {
			result.setResult(false);
			result.setFlag(OrderEnum.PROD_ERROR.tag);
			result.setReason(OrderEnum.PROD_ERROR.msg);
			return result;
		}
		// 计算总订单的价格
		BigDecimal totamt = new BigDecimal(0);// 订单总价
		BigDecimal integral = new BigDecimal(0);// 订单总积分
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		logger.info("提交订单计算订单总价格...");
		for (int i = 0; i < payNums.length; i++) {
			if (payPrics[i] != null)
				integral = integral.add(payPrics[i].multiply(new BigDecimal(payNums[i])));
		}
		totamt = integral.divide(scale);
		OrderInfo order = new OrderInfo();
		String orderNo = "T" + sdf.format(now);
		order.setOrderNo(orderNo);
		order.setUserId(userId);
		order.setTotamt(totamt);
		order.setIntegral(integral);
		order.setDelivId(addrId);
		order.setCreateTime(now);
		order.setEnabled(1);
		CouponInfo info = couponInfoMapper.selectByPrimaryKey(couponId);
		if (info != null && info.getAmount() != null) {
			// 减去优惠卷金额
			integral = integral.subtract(info.getAmount());
			order.setCouponId(couponId);
		}
		// 插入总订单记录
		logger.info("提交订单插入总订单记录...");
		int orderCount = orderInfoMapper.insertSelective(order);
		// 是否插入成功
		if (orderCount < 1 || order.getId() == null) {
			result.setResult(false);
			result.setFlag(OrderEnum.ORDER_ERROR.tag);
			result.setReason(OrderEnum.ORDER_ERROR.msg);
			return result;
		}
		result.setOrderId(order.getId());
		// 循环插入小订单(详单)记录
		OrderDetail detail = new OrderDetail();
		for (int i = 0; i < payNums.length; i++) {
			logger.info("提交订单循环插入小订单(详单)记录...");
			detail.setDetailNo("S" + sdf.format(now) + "-" + (i + 1));
			detail.setOrderId(order.getId());
			detail.setProdId(prodIds[i]);
			detail.setStoreId(storeIds[i]);
			detail.setPayPrice(payPrics[i]);
			if (payPrics[i] != null)
				detail.setTotalPrice(payPrics[i].multiply(new BigDecimal(payNums[i])));
			detail.setQuantity(payNums[i]);
			if (prodIds[i] != null) {
				AppProdVo vo = productMapper.getProductInfoById(prodIds[i], payNums[i]);
				detail.setSellerId(vo.getSellerId());
			}
			if (storeIds[i] != null) {
				StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeIds[i]);
				detail.setSellerId(store.getSellerId());
			}
			if (info!=null && detail.getSellerId() == info.getStoreId()) {
				detail.setConponId(info.getId());
			}
			detail.setCreateTime(now);
			detail.setStatus(1);
			detail.setEnabled(1);
			orderDetailMapper.insertSelective(detail);
		}
		for (int i = 0; i < prodIds.length; i++)
			this.deleteCartProduct(userId, prodIds[i],storeIds[i],createTimes[i]);
		// 更新已使用的优惠卷
		if (info != null && info.getAmount() != null) {
			info.setId(couponId);
			info.setOrderId(order.getId());
			info.setConsume(1);
			info.setConsumeTime(new Date());
			couponInfoMapper.updateByPrimaryKeySelective(info);
		}
		result.setResult(true);
		result.setFlag(OrderEnum.SUCCESS.tag);
		result.setReason(OrderEnum.SUCCESS.msg);
		return result;
	}

	@Override
	public StoreInfo queryStoreById(Long storeId) throws Exception {
		return storeInfoMapper.selectByPrimaryKey(storeId);
	}

	@Override
	public boolean deleteCartProduct(Long userId, Long prodId,Long storeId,Date createTime) throws Exception {
		CartInfoExample example = new CartInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		if (prodId == null) {
			criteria.andStoreIdEqualTo(storeId);
		}else{
			criteria.andProdIdEqualTo(prodId);
		}
		if (createTime != null) {
			criteria.andCreateTimeEqualTo(createTime);
		}
		int result = cartInfoMapper.deleteByExample(example);
		return result > 0 ? true : false;
	}

	@Override
	public boolean orderPayment(Long userId, Long orderId, OrderResult result) throws Exception {
		UserInfo user = userService.selectUserById(userId);
		String volumeCodeMessage = MessageUtil.VOLUMECODE;
		OrderInfo order = getOrderInfoByExample(orderId, userId); // 获取大订单
		OrderDetailExample example = new OrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId).andStatusEqualTo(1);
		example.setOrderByClause(" total_price DESC");
		List<OrderDetail> orderDetailList = orderDetailMapper.selectByExample(example); // 所有小订单
		String volueCode = "";
		List<MessageInfo> messageInfos = new ArrayList<MessageInfo>();
		for (OrderDetail detail : orderDetailList) {
			ProductInfo pro = null;
			Integer quantity = detail.getQuantity();
			if (detail.getProdId() != null) {
			 pro = this.queryProductById(detail.getProdId());
			if (pro.getStatus() != 2) { // 已下架
				result.setResult(false);
				result.setFlag(OrderEnum.PROD_OFF_SHELF.tag);
				result.setReason("您购买的" + pro.getName() + OrderEnum.PROD_OFF_SHELF.msg);
				throw new Exception("您购买的" + pro.getName() + OrderEnum.PROD_OFF_SHELF.msg);
			}
			if (pro.getEnabled() != 1) { // 禁售
				result.setResult(false);
				result.setFlag(OrderEnum.PROD_LOCK.tag);
				result.setReason("您购买的" + pro.getName() + OrderEnum.PROD_LOCK.msg);
				throw new Exception("您购买的" + pro.getName() + OrderEnum.PROD_LOCK.msg);
			}
			int maxPay = pro.getMaxPay(); // 用户已购买的商品数量
			int prodSumNum = orderManageMapper.queryProdSumNumByProdId(pro.getId(), userId); // 用户历史购买数
			if (prodSumNum > maxPay) { // 限购
				result.setResult(false);
				result.setFlag(OrderEnum.PROD_MAX_PAY.tag);
				result.setReason("您购买的" + pro.getName() + OrderEnum.PROD_MAX_PAY.msg);
				throw new Exception("您购买的" + pro.getName() + OrderEnum.PROD_MAX_PAY.msg);
			}
			
			Integer proStock = pro.getProdStock();
			pro.getProdStock();
			if (quantity > proStock) { // 库存
				result.setResult(false);
				result.setFlag(OrderEnum.PROD_STOCK_ERROR.tag);
				result.setReason(OrderEnum.PROD_STOCK_ERROR.msg);
				throw new Exception(OrderEnum.PROD_STOCK_ERROR.msg);
			}
			pro.setProdStock(proStock - quantity);
			Integer sales = pro.getSalesNum();
			pro.setSalesNum(sales == null ? quantity : (sales + quantity));
			logger.info("支付订单————更新商品库存...");
			boolean upProd = this.updateProductById(pro); // 更新商品库存
			if (!upProd) { // 商品错误
				result.setResult(false);
				result.setFlag(OrderEnum.PROD_ERROR.tag);
				result.setReason(OrderEnum.PROD_ERROR.msg);
				throw new Exception(OrderEnum.PROD_ERROR.msg);
			}
			
			}
			logger.info("支付订单————更新订单状态...");
			boolean upDetail = this.updateOrderDetail(detail.getId(), 2); // 更新订单状态
			if (!upDetail) {
				result.setResult(false);
				result.setFlag(OrderEnum.ORDER_ERROR.tag);
				result.setReason(OrderEnum.ORDER_ERROR.msg);
				throw new Exception(OrderEnum.ORDER_ERROR.msg);
			}
			CategoryInfo category =null;
			StoreInfo store = storeInfoMapper.selectByPrimaryKey(detail.getStoreId());
			if (pro != null) {
				category = categoryInfoMapper.selectByPrimaryKey(pro.getCategoryId());
			}else{
				
			}
			if (category == null || category.getParentId() != 3) { // 判断是否虚拟商品
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < quantity; i++) {
					volueCode = this.getVolumeCode();
					logger.info("支付订单————生成消费卷信息...");
					boolean addVolume = this.insertVolume(detail.getId(), volueCode, pro == null ?new Date(System.currentTimeMillis()+90*24*60*60*1000L):pro.getEndDate()); // 生成消费卷
					if (!addVolume) {
						result.setResult(false);
						result.setFlag(OrderEnum.VOLUME_ERROR.tag);
						result.setReason(OrderEnum.VOLUME_ERROR.msg);
						throw new Exception(OrderEnum.VOLUME_ERROR.msg);
					}
					volumeCodeMessage = volumeCodeMessage.replace("username",
							BaseUtil.judgeNull(user.getRealname()) ? user.getNickname() : user.getRealname());
					volumeCodeMessage = volumeCodeMessage.replace("product", pro==null?store.getName():pro.getName());
					if (i == quantity - 1) {
						volueCode = stringBuffer.append(volueCode).toString();
					} else {
						volueCode = stringBuffer.append(volueCode + "     ").toString();
					}
				}
			}
			
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setContent("新订单"+detail.getDetailNo()+"用户已成功支付!");
			messageInfo.setCreateDate(new Date());
			messageInfo.setMsgType(0);
			messageInfo.setSendee(detail.getSellerId());
			messageInfo.setTitle("新订单");
			messageInfo.setType(2);
			messageInfos.add(messageInfo);
		}
		logger.info("支付订单————更新资金账户...");
		boolean relust = paymentService.orderPaymentByUser(userId, order, result);
		if (!relust) {
			result.setResult(false);
			result.setFlag(OrderEnum.ACCOUNT_ERROR.tag);
			result.setReason(OrderEnum.ACCOUNT_ERROR.msg);
			throw new Exception(OrderEnum.ACCOUNT_ERROR.msg);
		}
		String message = MessageUtil.PAYMENTSUCCESS;
		message = message.replace("username",
				BaseUtil.judgeNull(user.getRealname()) ? user.getNickname() : user.getRealname());
		message = message.replace("order", order.getOrderNo());
		message = message.replace("money", order.getIntegral().intValue()+"");
		logger.info("支付订单后发送手机短信...");
		// 支付订单发送短信
		try {
			MessageUtil.sendSmsContent(user.getPhoneNum(), message);
		} catch (Exception e) {
			throw new Exception("支付订单后发送手机短信异常：", e);
		}
		if (!volumeCodeMessage.equals(MessageUtil.VOLUMECODE)) {
			// 生成消费卷密码后发送短信
			volumeCodeMessage = volumeCodeMessage.replace("volumeCode", volueCode);
			try {
				MessageUtil.sendSmsContent(user.getPhoneNum(), volumeCodeMessage);
			} catch (Exception e) {
				throw new Exception("生成消费卷密码后发送短信异常：", e);
			}
		}
		result.setResult(true);
		result.setFlag(OrderEnum.PAY_SUCCESS.tag);
		result.setReason(OrderEnum.PAY_SUCCESS.msg);
		messageInfoService.insertMessageInfo(messageInfos);
		return true;
	}

	@Override
	public List<OrderInfo> queryOrderInfoList(Long userId) throws Exception {
		OrderInfoExample orderExample = new OrderInfoExample();
		orderExample.createCriteria().andUserIdEqualTo(userId);
		return orderInfoMapper.selectByExample(orderExample);
	}

	@Override
	public List<OrderDetail> queryOrderDetailList(Long orderId) throws Exception {
		OrderDetailExample example = new OrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId).andStatusEqualTo(1);
		return orderDetailMapper.selectByExample(example);
	}

	@Override
	public boolean insertVolume(Long detailId, String volumeCode, Date productEndDate) throws Exception {
		VolumeInfo volumeInfo = new VolumeInfo();
		volumeInfo.setDetailId(detailId);
		volumeInfo.setVolumeCode(volumeCode);
		volumeInfo.setExpiration(productEndDate);
		int row = volumeInfoMapper.insertSelective(volumeInfo);
		return row > 0 ? true : false;
	}
	@Override
	public String getVolumeCode() throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		// 消费卷密码
		String volumeCode = String.valueOf(c.get(Calendar.YEAR)).substring(3);
		volumeCode = volumeCode + "8";
		VolumeInfoExample example = new VolumeInfoExample();
		example.setOrderByClause("ID DESC");
		Long id = 0L;
		List<VolumeInfo> list = volumeInfoMapper.selectByExample(example);
		if (null != list && list.size()>0) {
			id = list.get(0).getId();
		}
		String str = String.valueOf(id);
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 6 - str.length(); i++) {
			stringBuffer.append("0");
		}
		stringBuffer.append(str);
		str = stringBuffer.toString();
		volumeCode = volumeCode + str;
		return volumeCode;
	}

	@Override
	public boolean updateOrderDetail(Long id, int status) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(id);
		orderDetail.setStatus(status);
		if (status == 6)
			orderDetail.setRetime(new Date());
		int row = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
		return row > 0 ? true : false;
	}

	@Override
	public boolean updateRefund(int isRefund, UserRefund refund) throws Exception {
		boolean isSuccess = false;
		int row = userRefundMapper.updateByPrimaryKeySelective(refund);
		if (row == 0) {
			throw new RuntimeException("修改退款记录错误");
		}
		refund = userRefundMapper.selectByPrimaryKey(refund.getId());
		// 审核通过把退款返还到用户账户里面
		if (isRefund == 1) {
			isSuccess = this.updateOrderDetail(refund.getDetailId(), 6);
			if (!isSuccess) {
				throw new RuntimeException("修改订单详情错误");
			}
			Long userId = refund.getUserId();
			Long detaId = refund.getDetailId();
			OrderDetail detail = orderDetailMapper.selectByPrimaryKey(detaId);
			String detaNo = detail.getDetailNo();
			BigDecimal totamt = detail.getTotalPrice();
			logger.info("提交退款订单更新账户资金信息...");
			boolean accountSuccess = paymentService.orderRefundByUser(userId, detaNo, totamt);
			if (!accountSuccess) {
				throw new RuntimeException("修改账户余额错误");
			}
			ProductInfo pro = this.queryProductById(detail.getProdId());
			Integer quantity = detail.getQuantity();
			Integer proStock = pro.getProdStock();
			pro.getProdStock();
			pro.setProdStock(proStock + quantity);
			Integer sales = pro.getSalesNum();
			pro.setSalesNum(sales == null ? quantity : (sales - quantity));
			logger.info("提交退款订单更新商品库存...");
			boolean upProd = this.updateProductById(pro); // 更新商品库存
			if (!upProd)
				throw new Exception("更新商品库存和销量错误");
		} else {
			logger.info("提交退款订单更新详细订单信息...");
			isSuccess = this.updateOrderDetail(refund.getDetailId(), 3);
		}
		return isSuccess;
	}

	@Override
	public List<OrderDetailVo> queryOrderDetaiVoByProdIdAndUserId(Long prodId, Long uid, int i) throws Exception {

		return orderManageMapper.queryOrderDetaiVoByProdIdAndUserId(prodId, uid, i);
	}

	@Override
	public boolean updateDelivery(Long userId, Long id, int status) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(id);
		orderDetail.setStatus(status);
		if (status == 6)
			orderDetail.setRetime(new Date());
		int row = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
		if (row != 1)
			throw new Exception("确认收货错误");
		OrderDetail detail = orderDetailMapper.selectByPrimaryKey(id);
		BigDecimal totamt = detail.getTotalPrice();
		Long sellerId = detail.getSellerId();
		String detailNo = detail.getDetailNo();
		paymentService.orderSuccessBySeller(userId, sellerId, detailNo, totamt);
		return row > 0 ? true : false;
	}

	@Override
	public boolean updateCancelRefund(Long userId, Long detailId) throws Exception {
		OrderDetail detail = new OrderDetail();
		detail.setId(detailId);
		detail.setStatus(2);
		int row = orderDetailMapper.updateByPrimaryKeySelective(detail);
		if (row == 0)
			throw new Exception("修改用户订单信息错误");
		UserRefundExample example = new UserRefundExample();
		example.createCriteria().andUserIdEqualTo(userId).andDetailIdEqualTo(detailId);
		UserRefund refund = new UserRefund();
		refund.setStatus(0);
		int count = userRefundMapper.updateByExampleSelective(refund, example);
		if (count == 0)
			throw new Exception("修改用户退款表错误");
		return true;
	}

	@Override
	public boolean updateOrderDetailById(OrderDetail orderDetail) throws Exception {
		int row = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
		return row > 0 ? true : false;
	}

	@Override
	public boolean deleteOvertimeOrder() throws Exception {
		List<Long> list = orderManageMapper.queryOvertimeOrder();
		OrderDetailExample example = new OrderDetailExample();
		for (Long orderId : list) {
			try {
				example.clear();
				example.createCriteria().andOrderIdEqualTo(orderId);
				orderDetailMapper.deleteByExample(example);
				orderInfoMapper.deleteByPrimaryKey(orderId);
			} catch (Exception e) {
				this.logger.error("删除订单异常，订单编号——" + orderId, e);
				throw new Exception("删除订单异常，订单编号——" + orderId, e);
			}
		}
		return true;
	}

	@Override
	public boolean updateOrderInfo(Long id, Integer payPoint) throws Exception {
		OrderInfo oi = new OrderInfo();
		oi.setId(id);
		oi.setIntegral(new BigDecimal(payPoint));
		int a = orderInfoMapper.updateByPrimaryKeySelective(oi);
		return a > 0 ? true : false;
	}

	@Override
	public boolean updateOrderDetailPoints(Long id, BigDecimal points) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(id);
		/* orderDetail.setIntegralPrice(points); */
		int row = orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
		return row > 0 ? true : false;
	}
	
	@Override
	public OrderInfo getOrderInfoByOrderNo(String orderNo){
		OrderInfoExample example = new OrderInfoExample();
		example.createCriteria().andOrderNoEqualTo(orderNo);
		List<OrderInfo> list = orderInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public OrderDetail getOrderDetailByOrderId(Long orderId) {
		OrderDetailExample example = new OrderDetailExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<OrderDetail> list = orderDetailMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
}