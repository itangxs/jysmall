package cn.qhjys.mall.controller.account;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.AddressService;
import cn.qhjys.mall.service.CartInfoService;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashSaveWithdrawService;
import cn.qhjys.mall.service.CollectService;
import cn.qhjys.mall.service.CouponInfoService;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.ReviewService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.HttpUtil;
import cn.qhjys.mall.util.LLPayUtil;
import cn.qhjys.mall.util.OrderResult;
import cn.qhjys.mall.util.OrderResult.OrderEnum;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.CartProdVo;
import cn.qhjys.mall.vo.CollectVo;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.OrderVo;
import cn.qhjys.mall.vo.PaymentInfo;
import cn.qhjys.mall.vo.StoreVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 用户订单管理
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/account/order")
public class OrderController extends Base {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private CashSaveWithdrawService cashSaveWithdrawService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private CartInfoService cartInfoService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private ProductService productService;

	/**
	 * 我的订单
	 * 
	 * @param session
	 * @param pag
	 *            页数
	 * @param pageSize
	 *            每页
	 * @param status
	 *            状态
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/myOrderForm")
	public ModelAndView myOrderForm(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "status", required = false) Long status) throws Exception {
		ModelAndView view = new ModelAndView("/account/order/my_orderForm");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (null == page || page < 1)
			page = 1;
		if (page > 0)
			view.addObject("page", page);
		pageSize = 10;
		Long[] _status = new Long[9];
		if (null == status || status == 0L) {
			_status[0] = 1L;
			_status[1] = 2L;
			_status[2] = 3L;
			_status[3] = 4L;
			_status[4] = 5L;
			_status[5] = 6L;
			_status[6] = 7L;
			_status[7] = 8L;
			_status[8] = 9L;
		} else if(status == 3L){
			_status[0] = 3L;
			_status[1] = 4L;
		}else
			_status[0] = status;
		view.addObject("status", status);
		try {
			Page<OrderVo> pageInfo = orderService.queryOrderByUser(user.getId(), _status, page, pageSize);
			view.addObject("pageInfo", pageInfo);
		} catch (Exception e) {
			this.logger.error("查询我的订单异常:", e);
		}
		return view;
	}

	/**
	 * 我的订单详情
	 * 
	 * @param session
	 * @param id
	 *            订单ID
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/myOrderFormDatail")
	public ModelAndView myOrderFormDatail(HttpSession session, @RequestParam(value = "id", required = true) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/account/order/my_orderForm_datail");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		OrderVo orderVo = orderService.getOrderInfo(user.getId(), id);
		if (null != orderVo.getAddr()) {
			ProvinceInfo province = addressService.getProvinceInfoById(orderVo.getAddr().getProvince());
			String userProvinceName = province.getName();
			CityInfo city = addressService.getCityInfoById(orderVo.getAddr().getCity());
			String userCityName = city.getName();
			AreaInfo area = addressService.getAreaInfoById(orderVo.getAddr().getArea());
			String userAreaName = area.getName();
			view.addObject("areaName", userAreaName);
			view.addObject("provinceName", userProvinceName);
			view.addObject("cityName", userCityName);
		}
		view.addObject("orderVo", orderVo);
		return view;
	}

	/***
	 * 我的订单物流信息
	 * 
	 * @param session
	 * @param orderId
	 *            订单编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/myLogistics")
	public ModelAndView myLogistics(HttpSession session, @RequestParam(value = "orderId", required = true) Long orderId)
			throws Exception {
		ModelAndView view = new ModelAndView("/account/order/my_logistics");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		OrderDetailVo orderDetailVo = orderService.getOrderDetail(user.getId(), orderId);
		OrderVo orderVo = orderService.getOrderInfo(user.getId(), orderDetailVo.getOrderId());
		ProvinceInfo province = addressService.getProvinceInfoById(orderVo.getAddr().getProvince());
		String userProvinceName = province.getName();
		CityInfo city = addressService.getCityInfoById(orderVo.getAddr().getCity());
		String userCityName = city.getName();
		AreaInfo area = addressService.getAreaInfoById(orderVo.getAddr().getArea());
		String userAreaName = area.getName();
		StoreVo store = storeService.getStoreById(orderDetailVo.getStoreId());
		String sellerProvinceName = store.getProvince();
		String sellerCityName = store.getCity();
		String sellerAreaName = store.getAreaName();
		String zip = orderVo.getAddr().getZip();
		String address = orderVo.getAddr().getAddress();
		String recipient = orderVo.getAddr().getRecipient();
		String userAddr = userProvinceName.concat(userCityName).concat(userAreaName).concat(address).concat("," + zip)
				.concat("," + recipient);
		String sellerAddr = sellerProvinceName.concat(sellerCityName).concat(sellerAreaName).concat(store.getAddress())
				.concat("," + store.getContactName()).concat("," + store.getContactTel());
		view.addObject("orderDetail", orderDetailVo);
		view.addObject("userAddr", userAddr);
		view.addObject("sellerAddr", sellerAddr);
		view.setViewName("/account/order/my_logistics");
		return view;
	}

	/***
	 * 调用快递api方法查询快递详情
	 * 
	 * @param session
	 * @param order
	 *            快递单号
	 * @param id
	 *            快递供应商
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/kuaidiapi")
	public void kuaidiapi(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "order", required = true) String order,
			@RequestParam(value = "id", required = true) String id) throws Exception {
		String url = "http://www.kuaidiapi.cn/rest/";
		Map<String, String> params = new HashMap<String, String>();
		String uid = "33390";
		String key = "4888dbd0beb940afb30159a23f03416d";
		params.put("uid", uid);
		params.put("key", key);
		id = getCodeByExpress(id);
		params.put("id", id);
		params.put("order", order);
		byte[] kuaidi = HttpUtil.doSend(url, "GET", params);
		String kuaidis = new String(kuaidi, "UTF-8");
		logger.info(kuaidis);
		HtmlUtil.writerJson(response, kuaidis);
	}

	/***
	 * 根据快递运营商获取对应的快到运营商id
	 * 
	 * @param express
	 *            快递运营商
	 * @return
	 */
	private String getCodeByExpress(String express) {
		if (express.equals("顺丰快递")) {
			return "shunfeng";
		}
		if (express.equals("申通快递")) {
			return "shentong";
		}
		if (express.equals("中通快递")) {
			return "zhongtong";
		}
		if (express.equals("圆通快递")) {
			return "yuantong";
		}
		if (express.equals("韵达快递")) {
			return "yunda";
		}
		if (express.equals("天天快递")) {
			return "tiantian";
		}
		if (express.equals("汇通快递")) {
			return "huitong";
		}
		if (express.equals("广东ems快递")) {
			return "gdems";
		}
		return "";
	}

	/**
	 * 删除订单
	 * 
	 * @param session
	 * @param id
	 *            订单ID
	 * @return
	 * @throws Exception
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOrderForm")
	public void deleteOrderForm(HttpSession session, HttpServletResponse response, Long id) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		logger.info("---------------订单ID-------------------" + id);
		logger.info("---------------用户ID-------------------" + user.getId());
		boolean result = orderService.deleteOrderById(user.getId(), id);
		HtmlUtil.writerJson(response, result ? "scuess" : "error");
	}

	/**
	 * 
	 * addMyProdCollet 添加收藏
	 * 
	 * @param proId
	 *            产品ID
	 * @param tag
	 *            标签
	 * @param remark
	 *            标签说明
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping("/addMyProdCollet")
	public void addMyProdCollet(@RequestParam(value = "proId", required = true) Long proId,
			@RequestParam(value = "tag", required = false) String tag,
			@RequestParam(value = "remark", required = false) String remark, HttpServletResponse response,
			HttpSession session) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (null == user) {
			HtmlUtil.writerJson(response, "userNull");
			return;
		}
		logger.info("---------------商品ID-------------------" + proId);
		logger.info("---------------用户ID-------------------" + user.getId());
		Boolean bl = collectService.judgeProductIsCollect(user.getId(), proId);
		if (bl) {
			HtmlUtil.writerJson(response, "addError"); // 存在
			return;
		}
		Boolean bb = collectService.insertUserCollect(user.getId(), proId, null, null, null);
		if (bb)
			HtmlUtil.writerJson(response, "sccuess"); // 成功
		else
			HtmlUtil.writerJson(response, "error"); // 失败
	}

	/**
	 * 编辑店铺收藏
	 * 
	 * @param session
	 * @param response
	 * @param storeId
	 * @throws Exception
	 */
	@RequestMapping("/addMyStoreCollet")
	public void addMyStoreCollet(HttpSession session, HttpServletResponse response, Long storeId) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (storeId == null) {
			HtmlUtil.writerJson(response, "storeNull");
			return;
		}
		logger.info("---------------店铺ID-------------------" + storeId);
		logger.info("---------------用户ID-------------------" + user.getId());
		boolean hasCollect = false;
		try {
			hasCollect = collectService.judgeStroeIsCollect(user.getId(), storeId);
		} catch (Exception e) {
			this.logger.error("编辑店铺收藏异常：", e);
		}
		if (hasCollect) {
			collectService.deleteUserCollect(null, storeId, user.getId());
			HtmlUtil.writerJson(response, "delete");
		} else {
			collectService.insertUserCollect(user.getId(), null, storeId, null, null);
			HtmlUtil.writerJson(response, "insert"); // 成功
		}
	}

	/**
	 * 我的收藏
	 * 
	 * @param session
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @param status
	 *            收藏类型(0店铺 1商品)
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/myCollet")
	public ModelAndView myCollet(HttpSession session, Integer status, Integer pageNum, Integer pageSize)
			throws Exception {
		ModelAndView view = new ModelAndView("/account/order/my_collet");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (pageNum == null || pageNum < 1)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		if (null == status)
			status = 1;
		try {
			List<CollectVo> pageInfo = collectService.queryUserCollect(user.getId(), status, pageNum, pageSize);
			view.addObject("pageInfo", pageInfo);
			view.addObject("status", status);
		} catch (Exception e) {
			this.logger.error("查询我的收藏异常：", e);
		}
		return view;
	}

	/**
	 * 删除收藏
	 * 
	 * @param session
	 * @param prodId
	 *            商品编号
	 * @param storeId
	 *            店铺编号
	 * @return
	 * @throws Exception
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCollet")
	public void deleteCollet(HttpSession session, HttpServletResponse response, Long prodId, Long storeId)
			throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		logger.info("---------------店铺ID-------------------" + storeId);
		logger.info("---------------商品ID-------------------" + prodId);
		logger.info("---------------用户ID-------------------" + user.getId());
		boolean result = false;
		try {
			result = collectService.deleteUserCollect(prodId, storeId, user.getId());
		} catch (Exception e) {
			this.logger.error("删除收藏异常：", e);
		}
		HtmlUtil.writerJson(response, result ? "scuess" : "error");
	}

	/**
	 * 我的评论
	 * 
	 * @param id
	 *            商品ID
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @param status
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/myEvaluate")
	public ModelAndView myEvaluate(HttpSession session, Long id, Integer page, Integer pageSize, Integer status)
			throws Exception {
		ModelAndView view = new ModelAndView("/user/my_evaluate");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (null == page || page < 1)
			page = 1;
		if (page > 0)
			view.addObject("page", page);
		pageSize = 10;
		if (null == status || status < 1)
			status = 0;
		view.addObject("status", status);
		Page<EvaluateVo> pageInfo = reviewService.selectReviewByUserId(id, user.getId(), status, page, pageSize);
		view.addObject("pageInfo", pageInfo);
		return view;
	}

	/**
	 * 去评价页面
	 * 
	 * @param session
	 * @param id
	 *            订单ID
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/ratesdatail")
	public ModelAndView ratesdatail(HttpSession session, @RequestParam(value = "id", required = true) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/user/my_evaluate_page");
		return view;
	}

	/**
	 * 添加评价
	 * 
	 * @param session
	 * @param id
	 *            商品ID
	 * @param star
	 *            星级
	 * @param evalute_lv
	 *            评价等级
	 * @param evalute_explain
	 *            评价说明
	 * @param imgurl
	 *            图片地址
	 * @return
	 * @throws Exception
	 * @return Object
	 */
	@RequestMapping(value = "/saveEvaluate")
	public String saveEvaluate(HttpSession session, Long id, Integer star, Integer evalute_lv, String evalute_explain,
			String imgurl) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		logger.info("---------------id-------------------" + id);
		logger.info("---------------star-------------------" + star);
		logger.info("---------------evalute_lv-------------------" + evalute_lv);
		logger.info("---------------evalute_explain-------------------" + evalute_explain);
		logger.info("---------------用户ID-------------------" + user.getId());
		try {
			collectService.saveEvaluate(id, user.getId(), star, evalute_lv, evalute_explain, imgurl);
		} catch (Exception e) {
			this.logger.error("添加商品评论异常：", e);
		}
		return "redirect:/user/myEvaluate.do";
	}

	/***
	 * 添加我的订单
	 * 
	 * @param session
	 * @param productIds
	 *            商品编号(数组)
	 * @param productNum
	 *            商品数量(数组)
	 * @param money
	 *            单个商品总价格(数组)
	 * @param totalMoney
	 *            所有商品价格
	 * @param addressId
	 *            收货地址编号
	 * @param phone
	 *            手机号码
	 * @param captcha
	 *            手机动态码
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public void addOrder(HttpSession session, HttpServletResponse response, Long addrId, String token, String phone,
			String captcha, Long couponId) throws Exception {
		String _captcha = (String) session.getAttribute("captcha");
		if (StringUtils.isNotEmpty(captcha)) {
			if (_captcha == null || !_captcha.equals(captcha)) {
				HtmlUtil.writerJson(response, "codeError");
				return;
			}
		}
		session.removeAttribute("captcha");
		Long orderId = null;
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		logger.info("---------------addrId-------------------" + addrId);
		logger.info("---------------phone-------------------" + phone);
		logger.info("---------------captcha-------------------" + captcha);
		logger.info("---------------用户ID-------------------" + user.getId());
		Object tokenSession = SessionUtil.getSession(session, "productOrder");
		SessionUtil.removeSession(session, "productOrder");
		if (null != tokenSession && tokenSession.equals(token)) {
			try {
				List<CartProdVo> list = cartInfoService.queryCartByUser(user.getId());
				
				Long[] prodIds = new Long[list.size()];
				Long[] storeIds = new Long[list.size()];
				Integer[] prodNums = new Integer[list.size()];
				BigDecimal[] prodPrics = new BigDecimal[list.size()];
				Date [] createTimes = new Date[list.size()];
				for (int i = 0; i < list.size(); i++) {
					CartProdVo cartProd = list.get(i);
					prodIds[i] = cartProd.getProdId();
					storeIds[i] = cartProd.getStoreId()==null?productService.getProductInfoById(cartProd.getProdId()).getStoreId():cartProd.getStoreId();
					prodNums[i] = 1;
					prodPrics[i] = cartProd.getProdPrice().multiply(new BigDecimal(cartProd.getProdNum()));
					createTimes[i] = cartProd.getCreateTime();
				}
				couponId = couponId == null || couponId == -1L ? null : couponId;
				// 调用提交订单方法
				OrderResult result = orderService.insertUserOrder(user.getId(), prodIds,storeIds, prodPrics, prodNums, addrId,
						phone, couponId,createTimes);
				orderId = result.getOrderId();
				if (null == orderId && StringUtils.isNotBlank(result.getReason())){
					HtmlUtil.writerJson(response, result.getReason());
					return;
				}
			} catch (Exception e) {
				this.logger.error("创建用户订单异常：", e);
				HtmlUtil.writerJson(response, "创建订单异常");
				return;
			}
		} else {
			// 提示用户不能重复提交表单
			HtmlUtil.writerJson(response, "tokenError");
			return;
		}
		HtmlUtil.writerJson(response, orderId);
		return;
	}

	/**
	 * 跳转到去付款页面
	 * 
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toPay")
	public ModelAndView toCartConfirm(HttpSession session, HttpServletResponse response, Long orderId) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/toPay");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "payToken", token);
		OrderVo order = orderService.getOrderInfo(user.getId(), orderId);
		CashAccount cashAccount = orderService.queryCashAccount(user.getId());
		view.addObject("payToken", token);
		view.addObject("order", order);
		view.addObject("cashAccount", cashAccount);
		if (order.getCouponId() != null) {
			CouponInfo coupon = couponInfoService.selectCouponById(order.getCouponId());
			view.addObject("coupon", coupon);
		}
		return view;
	}

	/***
	 * 去付款
	 * 
	 * @param session
	 * @param response
	 * @param orderId
	 *            大订单编号
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/payOrder", method = RequestMethod.POST)
	public ModelAndView addOrder(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			String token, Long orderId) throws Exception {
		ModelAndView view = new ModelAndView("account/payMessage");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		Object tokenSession = SessionUtil.getSession(session, "payToken");
		SessionUtil.removeSession(session, "payToken");
		if (null != tokenSession && tokenSession.equals(token)) {
			OrderResult result = new OrderResult();
			if (orderId == null) {
				view.addObject("payMessage", OrderEnum.PARMT_ERROR.msg);
				return view;
			}
			try {
				boolean isSuccess = orderService.orderPayment(user.getId(), orderId, result);
				if (isSuccess) {
					CashAccount cashAccount = cashAccountService.queryCashAccount(null, user.getId());
					session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"), cashAccount);
					view.addObject("payMessage", "支付成功");
//					boolean a =taskInfoService.insertNewUserTask(user.getId(), NewTaskCode.N006);
//					if (a) {
//			    		request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, user.getId()));
//					}
				} else {
					view.addObject("payMessage", result.getReason());
					return view;
				}
			} catch (Exception e) {
				this.logger.error("创建用户订单异常：", e);
				view.addObject("payMessage", BaseUtil.judgeNull(result.getReason()) ? "支付异常" : result.getReason());
			}
		} else {
			// 提示用户不能重复提交表单
			view.addObject("payMessage", "表单重复提交");
		}
		return view;
	}

	/***
	 * 用户连连支付付款
	 * 
	 * @param req
	 * @param response
	 * @param session
	 * @param orderId
	 *            订单编号
	 * @param orderNum
	 *            充值订单唯一编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/recharge")
	public ModelAndView recharge(HttpServletRequest req, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "orderId", required = true) Long orderId,
			@RequestParam(value = "orderNum", required = true) String orderNum,
			@RequestParam(value = "cardNo", required = true) String cardNo) throws Exception {
		ModelAndView view = new ModelAndView("account/payMessage");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		CashSaveWithdraw cash = sellerVoService.queryCashSaveWithdrawByOrderNum(orderNum);
		if (cash.getStatus().equals(2)) {
			cash.setStatus(1);
			BigDecimal amount = cash.getMoney().divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale")));
			CashAccount cashAccount = sellerVoService.queryAccountBySellerId(0, user.getId());
			BigDecimal balance = cashAccount.getBalance();
			BigDecimal reviewAfter = balance.add(amount);
			CashAccount account = new CashAccount();
			account.setId(cashAccount.getId());
			account.setBalance(reviewAfter);
			CashLog cashLog = new CashLog();
			cashLog.setReviewId(user.getId());
			cashLog.setBankno(cardNo);
			cashLog.setAmount(amount);
			cashLog.setRecordNo(orderNum);
			cashLog.setPayType(1);
			cashLog.setPayWay(1);
			cashLog.setBusinessCode("001");
			cashLog.setBusinessName("充值");
			cashLog.setReviewBefor(balance);
			cashLog.setReviewAfter(reviewAfter);
			cashLog.setCreateTime(new Date());
			sellerVoService.insertRechargeIsSucess(cash, account, cashLog);
			CashSaveWithdraw cashSave = cashSaveWithdrawService.getCashSaveWithdraw(cashAccount.getId(), 0, orderNum);
			if (cashSave.getStatus() != 1) { // 如果充值失败,直接跳出
				view.addObject("payMessage", "余额充值失败，充值记录已更新！");
				return view;
			}
		}
		OrderResult result = new OrderResult();
		try {
			boolean isSuccess = orderService.orderPayment(user.getId(), orderId, result);
			if (isSuccess) {
				CashAccount userCashAccount = cashAccountService.queryCashAccount(null, user.getId());
				session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"), userCashAccount);
				view.addObject("payMessage", "支付成功");
			}
		} catch (Exception e) {
			this.logger.error("创建用户订单异常：", e);
			view.addObject("payMessage", BaseUtil.judgeNull(result.getReason()) ? "支付异常" : result.getReason());
		}
		return view;
	}

	public HttpServletRequest OrderPayLL(HttpServletRequest req, HttpServletResponse response, HttpSession session,
			String cardNo, UserInfo user, Long orderId) throws Exception {
		OrderInfo orderInfo = orderService.getOrderInfoByExample(orderId, user.getId());
		CashSaveWithdraw cash = new CashSaveWithdraw();
		CashAccount cashAccount = sellerVoService.queryAccountBySellerId(0, user.getId());
		BigDecimal money = orderInfo.getTotamt();
		// 需要支付的金额等于商品总额减去积分相抵金额
		cash.setMoney(money.divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"))));
		cash.setOperType(0);
		cash.setCashId(cashAccount.getId());
		Date date = new Date();
		cash.setCreateDate(date);
		cash.setStatus(2);
		String orderNum = String.valueOf(UUID.randomUUID());
		orderNum = orderNum.replaceAll("-", "");
		cash.setOrderNum(orderNum);
		boolean isSuccess = sellerVoService.insertSaveWithdraw(cash);
		if (isSuccess == true) {
			// 构造支付请求对象
			PaymentInfo paymentInfo = new PaymentInfo();
			paymentInfo.setVersion("1.0");
			paymentInfo.setOid_partner(ConstantsConfigurer.getProperty("OID_PARTNER"));// 商户编号
			paymentInfo.setUser_id("" + cashAccount.getId());
			paymentInfo.setSign_type("MD5");
			// 业务类型，连连支付根据商户业务为商户开设的业务类型；
			// （101001：虚拟商品销售、109001：实物商品销售、108001：外部账户充值）
			paymentInfo.setBusi_partner("101001");
			paymentInfo.setNo_order(orderNum);// 商户唯一订单号(只在充值时有)
			SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
			paymentInfo.setDt_order(sim.format(date));// 商户订单时间
			// 商品名称
			paymentInfo.setName_goods("充值");// 这里是充值，所以随便写个充值
			// paymentInfo.setInfo_order(order.getInfo_order());//订单备注信息(非必填)
			// 交易金额
			paymentInfo.setMoney_order(""
					+ money.divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"))));
			// 服务器异步通知地址(异步请求.do,做成功或失败后的操作)
			paymentInfo.setNotify_url(ConstantsConfigurer.getProperty("USER_NOTIFY_URL") + "?cardNo=" + cardNo);
			// 支付结束回显url(这里先跳转到充值页面)
			paymentInfo.setUrl_return(ConstantsConfigurer.getProperty("USER_PAY_RETURN_URL") + "?orderId=" + orderId
					+ "&orderNum=" + orderNum + "&cardNo=" + cardNo);
			// 用户端申请IP(暂时不填)
			// paymentInfo.setUserreq_ip(LLPayUtil.getIpAddr(req).replaceAll("\\.","_"));
			paymentInfo.setUrl_order("");
			// 订单有效时间(单位分钟，可以为空，默认7天)
			// paymentInfo.setValid_order("10080");
			// 风险控制参数
			paymentInfo.setRisk_item(createRiskItem());
			// 时间戳
			paymentInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
			// 商戶从自己系统中获取用户身份信息（认证支付必须将用户身份信息传输给连连，且修改标记flag_modify设置成1：不可修改）
			// 证件类型（0身份证）
			paymentInfo.setId_type("0");
			// 根据用户编号查询用户姓名和银行卡号
			// 证件号码
			paymentInfo.setId_no(user.getCardId());
			// 银行账号姓名
			paymentInfo.setAcct_name(user.getRealname());
			paymentInfo.setFlag_modify("1");
			if (!LLPayUtil.isnull(req.getParameter("no_agree"))) {// 协议号和卡号同时存在时，优先将协议号送给连连，不要将协议号和卡号都送给连连
				paymentInfo.setNo_agree(req.getParameter("no_agree"));
			} else {
				// 银行卡号
				paymentInfo.setCard_no(cardNo);
			}
			// 返回修改信息地址(银行卡号前置，需要修改卡号时，用户点击返回的 url链接地址)
			paymentInfo.setBack_url(ConstantsConfigurer.getProperty("USER_BACK_URL"));
			// 加签名
			String sign = LLPayUtil.addSign(JSON.parseObject(JSON.toJSONString(paymentInfo)),
					ConstantsConfigurer.getProperty("trader_pri_key"), ConstantsConfigurer.getProperty("md5_key"));
			paymentInfo.setSign(sign);
			req.setAttribute("version", paymentInfo.getVersion());
			req.setAttribute("oid_partner", paymentInfo.getOid_partner());
			req.setAttribute("user_id", paymentInfo.getUser_id());
			req.setAttribute("sign_type", paymentInfo.getSign_type());
			req.setAttribute("busi_partner", paymentInfo.getBusi_partner());
			req.setAttribute("no_order", paymentInfo.getNo_order());
			req.setAttribute("dt_order", paymentInfo.getDt_order());
			req.setAttribute("name_goods", paymentInfo.getName_goods());
			req.setAttribute("info_order", paymentInfo.getInfo_order());
			req.setAttribute("money_order", paymentInfo.getMoney_order());
			req.setAttribute("notify_url", paymentInfo.getNotify_url());
			req.setAttribute("url_return", paymentInfo.getUrl_return());
			req.setAttribute("userreq_ip", paymentInfo.getUserreq_ip());
			req.setAttribute("url_order", paymentInfo.getUrl_order());
			req.setAttribute("valid_order", paymentInfo.getValid_order());
			req.setAttribute("timestamp", paymentInfo.getTimestamp());
			req.setAttribute("sign", paymentInfo.getSign());
			req.setAttribute("risk_item", paymentInfo.getRisk_item());
			req.setAttribute("no_agree", paymentInfo.getNo_agree());
			req.setAttribute("id_type", paymentInfo.getId_type());
			req.setAttribute("id_no", paymentInfo.getId_no());
			req.setAttribute("acct_name", paymentInfo.getAcct_name());
			req.setAttribute("flag_modify", paymentInfo.getFlag_modify());
			req.setAttribute("card_no", paymentInfo.getCard_no());
			req.setAttribute("back_url", paymentInfo.getBack_url());
			req.setAttribute("req_url", ConstantsConfigurer.getProperty("PAY_URL"));
		}
		return req;
	}

	/**
	 * 根据连连支付风控部门要求的参数进行构造风控参数
	 * 
	 * @return
	 */
	private String createRiskItem() {
		JSONObject riskItemObj = new JSONObject();
		riskItemObj.put("user_info_full_name", "你好");
		riskItemObj.put("frms_ware_category", "1999");
		return riskItemObj.toString();
	}

}