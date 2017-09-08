package cn.qhjys.mall.controller.account;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.AddressService;
import cn.qhjys.mall.service.CartInfoService;
import cn.qhjys.mall.service.CouponInfoService;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.CartProdVo;
import cn.qhjys.mall.vo.DeliveryAddrVo;
import cn.qhjys.mall.vo.StoreVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/account")
public class UserCartController extends Base {
	@Autowired
	private CartInfoService cartInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserInfoService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private StoreService storeService;

	/**
	 * 查询购物车
	 * 
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getCartProd")
	public void getCartProd(HttpSession session, HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null || user.getId() == null) {
			HtmlUtil.writerJson(response, "");
			return;
		}
		List<CartProdVo> list = cartInfoService.queryCartByUser(user.getId());
		HtmlUtil.writerJson(response, list);
	}

	/**
	 * 添加商品到购物车
	 * 
	 * @param session
	 * @param prodId
	 * @param prodNum
	 * @return
	 */
	@RequestMapping("/addCartProd")
	public void addCartProd(HttpSession session, HttpServletResponse response, Long prodId, Long storeId,
			Integer prodNum) {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			HtmlUtil.writerJson(response, "userNull");
			return;
		}
		if ((prodId == null && storeId == null) || prodNum == null) {
			HtmlUtil.writerJson(response, "prodNull");
			return;
		}
		try {
			boolean result = cartInfoService.hasSellProduct(prodId);
			if (result || storeId != null) {
				result = cartInfoService.addCartProd(user.getId(), prodId,storeId, new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale")), prodNum);
				HtmlUtil.writerJson(response, result ? "success" : "error");
			} else
				HtmlUtil.writerJson(response, "prodError");
			return;
		} catch (Exception e) {
			this.logger.error("添加商品到购物车异常：", e);
			HtmlUtil.writerJson(response, "error");
		}
	}

	@ResponseBody
	@RequestMapping("/addCartProdAjax")
	public void addCartProdAjax(HttpSession session, HttpServletResponse response, Long prodId,Long storeId,
			Integer prodNum) {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (prodId == null || prodNum == null || null == user) {
			HtmlUtil.writerJson(response, "userNull");
			return;
		}
		try {
			boolean result = cartInfoService.hasSellProduct(prodId);
			if (result) {
				result = cartInfoService.addCartProd(user.getId(), prodId,storeId,new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale")), prodNum);
				HtmlUtil.writerJson(response, result ? "success" : "error");
			} else
				HtmlUtil.writerJson(response, "prodError");
			return;
		} catch (Exception e) {
			this.logger.error("添加商品到购物车异常：", e);
			HtmlUtil.writerJson(response, "error");
		}
	}

	/**
	 * 修改购物车商品的数量
	 * 
	 * @param prodId
	 * @param prodNum
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/changCartNum")
	public void changCartNum(String createTime,Long prodId,Long storeId, Integer prodNum, HttpSession session, HttpServletResponse response)
			throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		boolean result = cartInfoService.updateCartProdNum(prodId,storeId, prodNum, user.getId(),sdf1.parse(createTime));
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 删除购物车商品
	 * 
	 * @param prodId
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/delCartProd")
	public void deletCartProd(Long prodId,String createTime,Long storeId, HttpSession session, HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		if (createTime.length() == 13) {
			date = new  Date(Long.parseLong(createTime));
		}else{
			date =sdf1.parse(createTime);
		}
		boolean delCart = cartInfoService.deleteCartProduct(user.getId(), prodId,date,storeId);
		HtmlUtil.writerJson(response, delCart);
	}

	/**
	 * 跳转购物车结算
	 * 
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toCartConfirm")
	public ModelAndView toCartConfirm(HttpSession session, HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/cartConfirm");
		user = userInfoService.selecUserByStr(user.getUsername());
		session.setAttribute(ConstantsConfigurer.getUser(), user);
		List<CartProdVo> list = cartInfoService.queryCartByUser(user.getId());
		Long [] sellerIds = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			CartProdVo cpv =list.get(i);
			if (cpv.getProdId() != null) {
				StoreVo sv = storeService.getStoreById(cpv.getStoId());
				sellerIds[i] = sv.getSellerId();
			}else if (cpv.getStoreId() != null) {
				StoreVo sv = storeService.getStoreById(cpv.getStoreId());
				sellerIds[i] = sv.getSellerId();
			}
		}
		List<CouponInfo> coupons = couponInfoService.selectCouponByUserAndSeller(user.getId(), sellerIds);
		Page<DeliveryAddr> addr = userService.queryAddressList(user.getId());
		List<DeliveryAddrVo> addrList = new ArrayList<DeliveryAddrVo>();
		if (addr.size() > 0) {
			for (int i = 0; i < addr.size(); i++) {
				DeliveryAddrVo address = new DeliveryAddrVo();
				ProvinceInfo province = addressService.getProvinceInfoById(addr.get(i).getProvince());
				String provinceName = province.getName();
				CityInfo city = addressService.getCityInfoById(addr.get(i).getCity());
				String cityName = city.getName();
				AreaInfo area = addressService.getAreaInfoById(addr.get(i).getArea());
				String areaName = area.getName();
				address.setId(addr.get(i).getId());
				address.setRecipient(addr.get(i).getRecipient());
				address.setPhone(addr.get(i).getPhone());
				address.setAddress(addr.get(i).getAddress());
				address.setProvinceName(provinceName);
				address.setCityName(cityName);
				address.setAreaName(areaName);
				address.setIsdefault(addr.get(i).getIsdefault());
				addrList.add(address);
			}
		}
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "productOrder", token);
		view.addObject("token", token);
		view.addObject("coupons", coupons);
		view.addObject("cartProd", list);
		view.addObject("addr", addrList);
		return view;
	}

	/*
	 * @RequestMapping("/verifyCoupon") public void verifyCoupon(HttpSession
	 * session, HttpServletResponse response, String couponId, String sellerIds)
	 * throws Exception { UserInfo user = (UserInfo)
	 * session.getAttribute(ConstantsConfigurer.getUser()); List<Long> list =
	 * new ArrayList<Long>(); if (sellerIds != null) { String[] array =
	 * sellerIds.split(","); for (int i = 0; i < array.length; i++)
	 * list.add(Long.valueOf(array[i])); } boolean result =
	 * couponInfoService.verifyCouponInfo(user.getId(), couponId, list);
	 * HtmlUtil.writerJson(response, result); }
	 */
}