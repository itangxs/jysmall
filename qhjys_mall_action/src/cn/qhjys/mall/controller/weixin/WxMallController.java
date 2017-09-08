package cn.qhjys.mall.controller.weixin;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.BaseController;
import cn.qhjys.mall.common.ErrorCode;
import cn.qhjys.mall.common.JSONResult;
import cn.qhjys.mall.common.JsapiTicket;
import cn.qhjys.mall.common.WxLoginUrls;
import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardLotteryDetail;
import cn.qhjys.mall.entity.CardUserLottery;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.service.CommonInfoService;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.service.WxMallService;
import cn.qhjys.mall.service.WxPayService;
import cn.qhjys.mall.service.app.SellerCardCouponService;
import cn.qhjys.mall.service.fq.FqRedpackService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.util.MathChanceRandom;
import cn.qhjys.mall.util.SendPushPost;
import cn.qhjys.mall.vo.CardCouponVo;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.RebateStoreVo;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.Sha1;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

/**
 * 微信公众号商城controller
 * 
 * @author llw
 * 
 */
@Controller
@RequestMapping("/wxMall")
public class WxMallController extends BaseController {

	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private WxMallService wxMallService;
	@Autowired
	private RebateOrderService RebateOrderService;
	@Autowired
	private FqRedpackService fqRedpackService;
	@Autowired 
	private StoreRebateService storeRebateService;
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private SellerCardCouponService sellerCardCouponService;
	
	@Autowired
	private WeixinUserinfoService weixinUserinfoService;
	@Autowired
	private RebateOrderService rebateOrderService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	@Autowired
	private CommonInfoService commonInfoService;
	
    /**
     * 跳转到打折店铺列表页面
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/turnStoreList")
	public ModelAndView storeDetail(HttpServletRequest request) throws Exception {
	        return goToModelAndView("/weixin/rebateStoreList", null, null);
	}

	
	/**
	 * 查询打折店铺列表
	 * @param request
	 * @param pageNum 当前页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rebateStoreList")
	@ResponseBody
	public JSONResult rebateStoreList(HttpServletRequest request, Integer pageNum)
			throws Exception {
		try {
			pageNum = pageNum == null ? 1: pageNum;
			Page<RebateStoreVo> page = wxMallService.selectStoreRebateList(pageNum, 10);
			if(page.getPages() >= pageNum){
				data.put("page", page);
			}else{
				data.put("page", null);
			}
		
		} catch (Exception e) {
			log.error("select store list error", e);
			this.returnError(ErrorCode.ERROR_99999);
		}
		return data;
	}
	
	@RequestMapping("/storeDetail1")
	public String storeDetail1(HttpSession session,Long rebateId){
//		String openId = (String) session.getAttribute("openId");
		String request ;
		String url = ConstantsConfigurer.getProperty("get_store_url")+"?rebateId="+rebateId;
//		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
//		}else{
//			 request = url;
//		}
		return "redirect:"+request;
	}
 /**
  * 鏍规嵁rebateId鏌ヨ鍏蜂綋鎵撴姌淇℃伅 璺宠浆鍒拌鍗曢〉闈�
  * @param request
  * @param rebateId 鎵撴姌id
  * @return
  * @throws Exception
  */
	@RequestMapping("/toRebateDetail1")
	public ModelAndView toRebateDetail1(HttpSession session,HttpServletRequest request,Long rebateId,String code)
			throws Exception {
		try{
		Map<String, Object> map= wxMallService.selectRebateDetailById(rebateId);
//		 openId = (String) session.getAttribute("openId");
//		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			String openId = (String) httpRequest.get("openid");
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			log.info("---httpRequest1-------"+httpRequest1);
			String nickname = httpRequest1.optString("nickname");
			String headimgurl = httpRequest1.optString("headimgurl");
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			session.setAttribute("openId", openId);
			//map.put("nickname", URLEncoder.encode(nickname));
			map.put("nickname", nickname);
			map.put("headimgurl", headimgurl);
//		 }
		map.put("openId", openId);
		return goToModelAndView("/weixin/rebateDetail", "map", map);
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
	        return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}

	@RequestMapping("/storeDetail")
	public String storeDetail(HttpSession session,Long rebateId){
//		String openId = (String) session.getAttribute("openId");
		String request ;
		String requests;
		String url = ConstantsConfigurer.getProperty("get_store_url")+"?rebateId="+rebateId;
		int scope_nubmer = 1;
		if(wxMallService.selectScope(rebateId) != null && wxMallService.selectScope(rebateId) !=1 ){
			scope_nubmer = 2;
		}
		if(2 == scope_nubmer){
			request = GetWeiXinCode.getCodeRequestByBase(ConstantsConfigurer.getProperty("weixin_appid"),url);
			return "redirect:"+request;
		}else{
			requests = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
			return "redirect:"+requests;
		}
		
	}
 /**
  * 根据rebateId查询具体打折信息 跳转到订单页面
  * @param request
  * @param rebateId 打折id
  * @return
  * @throws Exception
  */
	@RequestMapping("/toRebateDetail")
	public ModelAndView toRebateDetail(HttpSession session,HttpServletRequest request,Long rebateId,String code)
			throws Exception {
		try{
		Map<String, Object> map= wxMallService.selectRebateDetailById(rebateId);
//		 openId = (String) session.getAttribute("openId");
//		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			String openId = (String) httpRequest.get("openid");
			session.setAttribute("openId", openId);
			if("snsapi_userinfo".equalsIgnoreCase((String) httpRequest.get("scope"))){
				String access_token = (String) httpRequest.get("access_token");
				String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
				JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
				log.info("---httpRequest1-------"+httpRequest1);
				String nickname = httpRequest1.optString("nickname");
				String headimgurl = httpRequest1.optString("headimgurl");
				session.setAttribute("nickname", nickname);
				session.setAttribute("headimgurl", headimgurl);
				//map.put("nickname", URLEncoder.encode(nickname));
				map.put("nickname", nickname);
				map.put("headimgurl", headimgurl);
			}
			
//		 }
		map.put("openId", openId);
		return goToModelAndView("/weixin/rebateDetail", "map", map);
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
	        return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}
	
	@RequestMapping("/goRebateDetailbak")
	public ModelAndView goRebateDetail(HttpSession session,HttpServletRequest request,Long storeId)
			throws Exception {
		try{
			StoreRebate rebate = storeRebateService.getStoreRebateByStoreId(storeId);
			if (rebate == null) {
				ModelAndView view = new ModelAndView("/weixin/redpack/message");
				view.addObject("message", "商家暂无折扣买单");
				return view;
			}
			if (rebate.getSellerId() == 1933L) {
				ModelAndView view = new ModelAndView("redirect:/wxMall/goRebateDetailbak.do?storeId="+storeId);
				return view;
			}
			String openId = (String) session.getAttribute("user_openid");
//			String openId = "111111111";
			if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
				
				String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
				String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
				WxLoginUrls.addMap(urlid, url);
				
				ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
				return view;
				 
			}
			
			Map<String, Object> map= wxMallService.selectRebateDetailById(rebate.getId());
			map.put("openId", openId);
			map.put("nickname", "");
			map.put("headimgurl", "");
			
			return goToModelAndView("/weixin/rebateDetail", "map", map);
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
			return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}
	@RequestMapping("/goRebateDetail1")
	public ModelAndView goRebateDetail1(HttpSession session,HttpServletRequest request,Long storeId)
			throws Exception {
		try{
			StoreRebate rebate = storeRebateService.getStoreRebateByStoreId(storeId);
			if (rebate == null) {
				ModelAndView view = new ModelAndView("/weixin/redpack/message");
				view.addObject("message", "商家暂无折扣买单");
				return view;
			}
			String openId = (String) session.getAttribute("user_openid");
//			String openId = "111111111";
			if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
				
				String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
				String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
				WxLoginUrls.addMap(urlid, url);
				
				ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
				return view;
			}
			
			Map<String, Object> map= wxMallService.selectRebateDetailById(rebate.getId());
			map.put("openId", openId);
			map.put("nickname", "");
			map.put("headimgurl", "");
			return goToModelAndView("/weixin/rebateDetail1", "map", map);
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
			return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}

	
	@RequestMapping("/goRebateDetail")
	public ModelAndView goRebateDetailbak1(HttpSession session,HttpServletRequest request,Long storeId)
			throws Exception {
		try
	    {
	      String param = request.getQueryString();
	      String url = request.getRequestURI() + (StringUtils.isEmpty(param) ? "" : new StringBuilder("?").append(param).toString());
	      this.log.info("--begin pay storeId---" + url);
	      if ((StringUtils.isEmpty(storeId)) && (
	        (param.contains("%253D")) || (param.contains("%3D")))) {
	        int loc = param.indexOf("3D");
	        String storeIdStr = param.substring(loc + 2, param.length());
	        storeId = Long.valueOf(storeIdStr);
	      }

	      StoreRebate rebate = this.storeRebateService.getStoreRebateByStoreId(storeId);
	      this.log.info("--begin pay storeId-rebate--" + rebate);
	      if (rebate == null) {
	        ModelAndView view = new ModelAndView("/weixin/redpack/message");
	        view.addObject("message", "商家暂无折扣买单");
	        return view;
	      }
	      this.log.info("--begin pay storeId-rebate--" + rebate.getId());
	      String openId = "";
	      String payType = "";
	      String uAgent = request.getHeader("user-agent").toLowerCase();
	      if (uAgent.indexOf("micromessenger") > 0) {
	        openId = (String)session.getAttribute("user_openid");
	        this.log.info("--weixin session-openId-" + openId);
	        if ((StringUtils.isEmpty(openId)) || ("NULL".equals(openId.toUpperCase())))
	        {
	          String dorequest = GetWeiXinCode.getCodeRequestByBase(ConstantsConfigurer.getProperty("weixin_appid"), ConstantsConfigurer.getProperty("do_rebate_url") + "?rebateId=" + rebate.getId());
	          this.log.info("--weixin session-dorequest-" + dorequest);
	          return new ModelAndView("redirect:" + dorequest);
	        }

	        payType = "1";
	      } else if (uAgent.indexOf("qq/") > 0) {
	        payType = "2";
	      } else if ((uAgent.indexOf("aliapp") > 0) || (uAgent.indexOf("alipaydefined") > 0) || (uAgent.indexOf("alipayclient") > 0))
	      {
	        openId = (String)session.getAttribute("user_openid");
	        if ((StringUtils.isEmpty(openId)) || ("NULL".equals(openId.toUpperCase())))
	        {
	          String urlid = System.currentTimeMillis() + BaseUtil.numRandom(6);
	          WxLoginUrls.addMap(urlid, url);
	          return new ModelAndView("redirect:" + WxLoginUrls.getAliBaseLoginUrl(urlid));
	        }

	        payType = "0";
	      }
	      else {
	        payType = "2";
	      }
	      Map<String, Object> map = this.wxMallService.selectRebateDetailById(rebate.getId());
	      map.put("openId", openId);
	      map.put("nickname", "");
	      map.put("headimgurl", "");
	      map.put("payType", payType);
	      this.log.info("--begin pay storeId-jieshu--");
	      return goToModelAndView("/weixin/rebateDetail31", "map", map);
	    } catch (Exception e) {
	      this.log.error("select store list error", e);
	      returnError(ErrorCode.ERROR_99999);
	    }return goToModelAndView("/weixin/rebateStoreList", null, null);

	}
	
	@RequestMapping("/doRebateDetail")
	public ModelAndView doRebateDetail(HttpSession session,Long rebateId,String code){
		this.log.info("--weixin session-doRebateDetail-code--" + code);
		String openId = (String)session.getAttribute("user_openid");
	    if ((StringUtils.isEmpty(openId)) || ("NULL".equals(openId.toUpperCase()))){
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			this.log.info("--currentOpenIdurl--" + currentOpenIdurl);
			//		JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			String result = SendPushPost.sendGet(currentOpenIdurl);
			JSONObject httpRequest = JSONObject.fromObject(result);
			this.log.info("--httpRequest--" + httpRequest);
			 openId = (String) httpRequest.get("openid");
			 fqUserInfoService.updateFqUserInfo(openId, null, null);
			session.setAttribute("user_openid", openId);
	    }
		String payType = "1";
		Map<String, Object> map= wxMallService.selectRebateDetailById(rebateId);
		map.put("openId", openId);
		map.put("nickname", "");
		map.put("headimgurl", "");
		map.put("payType", payType);
		this.log.info("--weixin session- end-" +openId);
		return goToModelAndView("/weixin/rebateDetail31", "map", map);
	} 
	
	
	
	@RequestMapping("/goRebateDetailnew")
	public ModelAndView goRebateDetailnew(HttpSession session,HttpServletRequest request,Long storeId)
			throws Exception {
		try{
			String param = request.getQueryString();
			String url = request.getRequestURI()+(StringUtils.isEmpty(param)?"":"?"+param);
			log.info("--begin pay storeId---"+url);
			if (StringUtils.isEmpty(storeId)) {
				if (param.contains("%253D")||param.contains("%3D")) {
					int loc = param.indexOf("3D");
					String storeIdStr = param.substring(loc+2, param.length());
					storeId = Long.valueOf(storeIdStr);
				}
			}
			StoreRebate rebate = storeRebateService.getStoreRebateByStoreId(storeId);
			if (rebate == null) {
				ModelAndView view = new ModelAndView("/weixin/redpack/message");
				view.addObject("message", "商家暂无折扣买单");
				return view;
			}
			Map<String, Object> map= wxMallService.selectRebateDetailById(rebate.getId());
			String openId="";
			String payType="";
			String uAgent = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
			if (uAgent.indexOf("micromessenger") > 0) {// 是微信浏览器
				openId = (String) session.getAttribute("user_openid");
//				openId =  "ob8Wbwc0J-UcF6_QZiJfBjCWJ_LY";
				//session.setAttribute("user_openid",openId);
				if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
					
					String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
					WxLoginUrls.addMap(urlid, url);
					
					ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
					return view;
				}
				payType = "1";
			}else if (uAgent.indexOf("qq/")>0){  //"可能含有mqqbrowser参数 必须排除,所以加上qq/"
				payType = "2";
		    }else if (uAgent.indexOf("aliapp")>0||uAgent.indexOf("alipaydefined")> 0 ||uAgent.indexOf("alipayclient")>0){ //支付宝
		    	//切换到浦发银行支付宝后，须像微信一样必须授权获取到用户的id
		    	  openId = (String) session.getAttribute("user_openid");
		    	  //openId =  "2016080600182556";
		    	  //openId =  "2088702048390643";
		    	  //session.setAttribute("user_openid",openId);
				  if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
//						String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
						String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
						WxLoginUrls.addMap(urlid, url);
						ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getAliBaseLoginUrl(urlid));
						return view;
				 }
		    	payType = "0";
		    }else{
		    	//可能其他浏览器扫描的
		    	payType = "2";
		    }		
			
			
			map.put("openId", openId);
			map.put("nickname", "");
			map.put("headimgurl", "");
			map.put("payType", payType);
			return goToModelAndView("/weixin/rebateDetail5", "map", map);
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
			return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}
	
	
//	@RequestMapping("/qushouquan")
//	public String qushouquan(HttpServletRequest request){
//		return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx605bd41598bdbedb&redirect_uri=http://liberty.tunnel.qydev.com/wxMall/shouquan.do&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
//	}
////	
//	@RequestMapping("/shouquan")
//	@ResponseBody
//	public String shouquan(HttpSession session,String code){
//		String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
//		//		JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
//		String result = SendPushPost.sendGet(currentOpenIdurl);
//		JSONObject httpRequest = JSONObject.fromObject(result);
//		String openId = (String) httpRequest.get("openid");
//		log.info("shouquan wanhceng openid="+openId);
//		fqUserInfoService.updateFqUserInfo(openId, null, null);
//		session.setAttribute("user_openid", openId);
//		return "1111";
//	}
	
	
	/**
	 * 支付成功后跳转的页面
	 * 
	 */
	@RequestMapping("/paySuccessPage")
	public ModelAndView paySuccessPage(HttpSession session,HttpServletRequest request,Long orderId){
		try {
		log.info(">>>>>>>>>>>>>进入卡券paySuccessPage orderId" + orderId);
		ModelAndView view = new ModelAndView("/weixin/indexCardCoupon1");
		view.addObject("orderId", orderId);
		RebateOrder rebateOrder = rebateOrderService.getRebateOrder(orderId);
		StoreRebate  rebate = storeRebateService.getStoreRebate(rebateOrder.getRebateId());
		StoreInfo store = storeService.getStoreInfoById(rebate.getStoreId());
		Integer sum =0;
		String citys = commonInfoService.getValueById(1L);
		if (!StringUtils.isEmpty(citys)&&citys.contains(store.getCity()+",")) {
					//判断有无 自营，  周边抽奖，  红包的
					
					//查询本店商家是否 有自营的卡券投放中
				   CardCouponDelivery delivery = wxMallService.selectSellerDeliveryStatus(orderId);
				   
				   view.addObject("sellerId",delivery.getSellerId());
				   
				   Integer status =  delivery.getProprietaryStatus(); //本店自营状态
				   if(status != null && status==1){
					   view.addObject("proprietaryFlag","1");
					   sum+=1;
					   log.info(">>>>>>>>>>sum>>>>>>>>>>>>>proprietaryFlag:"+status);
				   }else{
					   view.addObject("proprietaryFlag","0");
				   }
					
				   //周边 
				   List<Map> list = wxMallService.insertAndSelectSellerPeripheralList(orderId);
				   if(list.size()>0){
					   sum+=1;
					   log.info(">>>>>>>>>>sum>>>>>>>>>>>>>peripheralFlag:"+list.size());
					   view.addObject("peripheralFlag","1");
					   session.setAttribute(orderId+"shangjialist", list);
				   }else{
					   view.addObject("peripheralFlag","0");
				   }
			
			}
		FqRedpackRecord fpr = fqRedpackService.insertFqRedpackRecordByorder(orderId);
		if (fpr == null) {
			view.addObject("redpackFlag","0");
		}else{
			sum+=1;
			log.info(">>>>>>>>>>sum>>>>>>>>>>>>>redpackFlag:"+fpr.getId());
			view.addObject("redpackFlag","1");
			view.addObject("orderId", orderId);
			view.addObject("id", fpr.getId());
			view.addObject("status", fpr.getStatus());
			view.addObject("redpackMoney", fpr.getRedpackMoney());
		}
		view.addObject("sum", sum);
		log.info(">>>>>>>>>>sum>>>>>>>>>>>>>"+sum);
		if(sum==0){
			view.setViewName("redirect:/wxMall/orderDetail.do?orderId="+orderId);
			return view;
		}else{
			
			view.addObject("order", rebateOrder);
			if (rebateOrder.getCouponId() != null && rebateOrder.getCouponId()>0) {
				view.addObject("coupon", wxMallService.getMyCardCouponDetail(rebateOrder.getCouponId()));
			}
			
		}
		 return view;
		} catch (Exception e) {
				e.printStackTrace();
			}
		return mav;
		
	}
	/**
	 * 增加一个页面跳往 /weixin/redpack.jsp
	 * @param request
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jump/orderRedpack")
	public ModelAndView jumporderRedpack(HttpServletRequest request,Long orderId,Long id,Integer status,BigDecimal redpackMoney)
			throws Exception {
		ModelAndView view = new ModelAndView("/weixin/redpack");
		FqRedpackRecord fpr = new FqRedpackRecord();
		fpr.setId(id);
		fpr.setStatus(status);
		fpr.setRedpackMoney(redpackMoney);
		
		view.addObject("orderId", orderId);
		view.addObject("fpr", fpr);
		return view;
	}
	
	
	
	/**
	 * 根据orderid返回支付详情页面
	 * @param request
	 * @param orderId 订单id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderDetail")
	public ModelAndView orderDetail(HttpServletRequest request,Long orderId)
			throws Exception {
		try{
		Map<String, Object> map= wxMallService.selectOrderDetailById(orderId);
		return goToModelAndView("/weixin/orderDetail", "map", map);
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
	        return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}
	
	@RequestMapping("/orderRedpack")
	public ModelAndView orderRedpack(HttpServletRequest request,Long orderId)
			throws Exception {
		ModelAndView view = new ModelAndView("/weixin/redpack");
		FqRedpackRecord fpr = fqRedpackService.insertFqRedpackRecordByorder(orderId);
		if (fpr == null) {
			view.setViewName("redirect:/wxMall/orderDetail.do?orderId="+orderId);
		}else{
			view.addObject("orderId", orderId);
			view.addObject("fpr", fpr);
			
		}
		return view;
	}
	@ResponseBody
	@RequestMapping("/doOrderRedpack")
	public String doOrderRepack(Long rprid){
		int a = fqRedpackService.updateRedpackRecordStatusByDo(rprid);
		return a+"";
	}
	
	@RequestMapping("/toOrder")
	public ModelAndView getOrder(HttpServletRequest request,String code)
			throws Exception {
		try{
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			Object openId = httpRequest.get("openid");
			if (openId == null) {
				return goToModelAndView("/weixin/message", "message", "暂无订单");
			}
			
			Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVoByThird(null, null, null, null, null, openId.toString(),null, 1, 50,null,null,null,null);
//			RebateOrder order = RebateOrderService.getLastOrder(openId+"");
			if (page!=null && page.getPages()>0) {
//				Map<String, Object> map= wxMallService.selectOrderDetailById(order.getId());
				
				return goToModelAndView("/weixin/orderList", "page", page);
			}else{
				return goToModelAndView("/weixin/message", "message", "暂无订单");
			}
		}catch(Exception e){
			log.error("select store list error",e);
			this.returnError(ErrorCode.ERROR_99999);
	        return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}
//	@RequestMapping("/toOrderList")
//	public ModelAndView toOrderList(HttpServletRequest request,String code)
//			throws Exception {
//		try{
//			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
//			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
//			Object openId = httpRequest.get("openid");
//			if (openId == null) {
//				return goToModelAndView("/weixin/message", "message", "暂无订单");
//			}
//			RebateOrder order = RebateOrderService.getLastOrder(openId+"");
//			if (order!=null) {
//				Map<String, Object> map= wxMallService.selectOrderDetailById(order.getId());
//				return goToModelAndView("/weixin/orderDetail", "map", map);
//			}else{
//				return goToModelAndView("/weixin/message", "message", "暂无订单");
//			}
//		}catch(Exception e){
//			log.error("select store list error",e);
//			this.returnError(ErrorCode.ERROR_99999);
//			return goToModelAndView("/weixin/rebateStoreList", null, null);
//		}
//	}
	
	@RequestMapping("/getOrder")
	public String getWeixinCode(){
		String url = ConstantsConfigurer.getProperty("get_order_url")+"?a="+new Date().getTime();
		String request = GetWeiXinCode.getCodeRequestByBase(ConstantsConfigurer.getProperty("weixin_appid"),url);
		return "redirect:"+request;
	}
	
	/**
	 * 查询用户当前优惠最大的卡券
	 * 
	 */
	@RequestMapping("/getUserAvailableCardCoupon")
	@ResponseBody
	public AppResult selectUserAvailableCardCoupon(HttpSession session,HttpServletRequest request,BigDecimal totalPay,Long storeId){
		AppResult app = new AppResult();
		List<CardCoupon> list = (List<CardCoupon>) session.getAttribute(storeId+"cards");
		
		Map<Double,Integer> map = new TreeMap<Double,Integer>();
		
		//计算出最优惠的卡券
		for(int i=0;i<list.size();i++){
			CardCoupon card = list.get(i);
			//满多少金额才可以使用    0不限制
			BigDecimal useOverAmount = card.getUseOverAmount();
			if(useOverAmount.doubleValue()>0){
				if(totalPay.doubleValue()<useOverAmount.doubleValue()){ //不满足 满足多少金额使用，跳过
					continue;
				}
			}
			int curHour = DateUtils.getHour(new Date());
			Integer uSHour = card.getValidityUseStarttime();
			Integer uEHour = card.getValidityUserEndtime();
			if(uSHour!=0&&uEHour!=0){ 	//限制使用时段 
				if(curHour<uSHour|| curHour>uEHour){
					continue;
				}
			}
			Integer couponCfg = card.getTemplateCouponCfg();
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				Double tempPay = totalPay.subtract(discountPay).doubleValue();   
				map.put(tempPay, i);
			}else if(couponCfg==2){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				Double tempPay = totalPay.multiply(discount).doubleValue();   
				map.put(tempPay, i);
			}
		}
		app.setFlag(0);
		app.setReason("获取成功");
		if(map.size()==0){
			return null;
		}else{
			Double lastKey = ((TreeMap<Double, Integer>) map).firstKey();
			Integer i = map.get(lastKey);
			CardCoupon card = list.get(i);
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("id", card.getId());
			obj.put("couponName",  card.getTemplateCouponName());
			obj.put("couponCfg", card.getTemplateCouponCfg());
			obj.put("couponAmount", card.getTemplateCouponAmount());
			app.setData(obj);
		}
		return app;
	}
	/**
	 * 查询用户当前店铺所有卡券
	 * 
	 */
	@RequestMapping("/getUserAllCardCoupon")
	@ResponseBody
	public void selectUserAllCardCoupon(HttpSession session,HttpServletRequest request,Long storeId){
		String openId = (String) session.getAttribute("user_openid");
		log.info(">>进入查询用户当前店铺卡券>>>>>>>>>>>>>>>>>>storeId:"+storeId+",>>>>>openId:"+openId);
		if (!StringUtils.isEmpty(openId) && !"NULL".equals(openId.toUpperCase()) && openId.length()==28) {
			List<CardCoupon> card = wxMallService.selectUserAllCardCoupon(storeId, openId);
			session.setAttribute(storeId+"cards", card);
		}
	}
	
	
	 /**
	  * 跳转到周边领取主页
	  * @param session
	  * @param request
	  * @return
	 * @throws Exception 
	  */
	@RequestMapping("/jump/localCardCoupon")
	public ModelAndView jumpLocalCardCoupon(HttpSession session,HttpServletRequest request,Long orderId) throws Exception{
		String openId = (String) session.getAttribute("user_openid");
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
			return view;
		}
		//查询周边投放开启的商家  限制5家
		List<Map> list =  (List<Map>) session.getAttribute(orderId+"shangjialist");
		if (list== null || list.size()==0) {
			 list = wxMallService.insertAndSelectSellerPeripheralList(orderId);
		}
		ModelAndView view = new ModelAndView("/weixin/localCardCoupon");
		view.addObject("list",list);
		view.addObject("orderId",orderId);
		return view;
	}
	
	/**
	  * 跳转到抽奖页面
	  * @param session
	  * @param request
	  * @param flag  1:自营抽奖   2:周边抽奖
	  * 
	  * forOpenId 可为空， 帮朋友抽时有效
	  * @return
	  */
	@RequestMapping("/jump/getCardCoupon")
	public ModelAndView jumpGetCardCoupon(HttpSession session,HttpServletRequest request,Integer flag,Long sellerId,Long deliveryId,Long orderId,String forOpenId){
		try {
		
		String signUrl = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
	
		String openId = (String) session.getAttribute("kaquan_openid");
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getLoginUrl(urlid));
			return view;
		}
		session.removeAttribute("kaquan_openid");
		Integer isShare = 0 ;// 0自己  1 分享
		//自营进来时没有deliveryId
		//分享的不记录用户信息
		//查询本店周边或自营抽奖的卡券模板
		Long userLotteryId = -1l;
		List<Map> list = wxMallService.selectSellerDeliveryCardTemplateList(flag,sellerId);
		//分享人不为空说明是分享
		if(forOpenId!=null&&forOpenId.length()==28&&!forOpenId.endsWith(openId)){
			isShare = 1;
			CardUserLottery	cardUserLottery = wxMallService.insertCardUserLotteryInfo(flag,forOpenId,sellerId,deliveryId,orderId);
			userLotteryId = cardUserLottery.getId();
		}else{
			CardUserLottery	cardUserLottery = wxMallService.insertCardUserLotteryInfo(flag,openId,sellerId,deliveryId,orderId);
			userLotteryId = cardUserLottery.getId();
		}
		ModelAndView view = new ModelAndView("/weixin/getCardCoupon1");
			if (deliveryId == null) {
				view.setViewName("/weixin/getCardCoupon1");
			}else{
				view.setViewName("/weixin/getCardCoupon32");
			}


		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		view.addObject("store", store);
		view.addObject("list", list);
		view.addObject("userLotteryId",userLotteryId);
		view.addObject("statusCfg", flag);
		view.addObject("sellerId", sellerId);
		
		view.addObject("isShare", isShare);
		
		view.addObject("orderId", orderId);
		view.addObject("selfOpenId", openId);//供分享使用
		view.addObject("forOpenId", forOpenId);
		
		WeixinUserinfo userinfo = weixinUserinfoService.getWeixinUserinfo(openId);
		if (userinfo != null) {
			view.addObject("isguanzhu", 1);
		}else{
			view.addObject("isguanzhu", 0);
		}
		
		//是自己的话查询 朋友帮自己抽奖情况
		if(isShare==0){ 
			Map<String, List<Map>>  friendMap = wxMallService.selectUserFriendLottery(orderId,openId,userLotteryId);
			view.addObject("friendMap", friendMap);
			FqUserInfo fquser = fqUserInfoService.getFqUserInfoByOpenId(openId);
			System.out.println(fquser.getNickName());
			view.addObject("fquser", fquser);
		}else{
			Map<String, List<Map>>  friendMap = wxMallService.selectUserFriendLottery(orderId,forOpenId,userLotteryId);
			view.addObject("friendMap", friendMap);
			FqUserInfo fquser = fqUserInfoService.getFqUserInfoByOpenId(forOpenId);
			System.out.println(fquser.getNickName());
			view.addObject("fquser", fquser);
		}
		
		List<CardLotteryDetail> detailList = wxMallService.selectIsUserLottery(userLotteryId,openId,forOpenId,orderId);
		if(detailList!=null&&detailList.size()>0){
			
			CardLotteryDetail detail = detailList.get(0);
			System.out.println("detail.getCardTemplateId()---------"+detail.getCardTemplateId());
			CardCouponTemplate cardCouponTemplate = wxMallService.getCardCouponTemplateById(detail.getCardTemplateId());
			view.addObject("cardCouponTemplate", cardCouponTemplate);
		}
	
		//微信JS-SDK的页面注入配置信息
		Long datetime = System.currentTimeMillis()/1000L;
		String nonceStr = UUID.randomUUID()+"";
		view.addObject("datetime", datetime);
		view.addObject("appId", ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID));
		view.addObject("nonceStr", nonceStr);
		String ticket = JsapiTicket.getTicket();
		String sha1 = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+datetime+"&url="+signUrl;
		String sha2 = new Sha1().getDigestOfString(sha1.getBytes()).toLowerCase();
		view.addObject("sha", sha2);
		return view;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @param statusCfg
	 * @param sellerId
	 * @param userLotteryId
	 * @param forOpenId 朋友分享出来时，分享人的openId
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/lottery/getCardCoupon")
	@ResponseBody
	public AppResult lotteryGetCardCoupon(HttpSession session,HttpServletRequest request,Integer statusCfg,Long sellerId,Long userLotteryId,String forOpenId,Long orderId){
		System.out.println("userLotteryId------"+userLotteryId);
		String openId = (String) session.getAttribute("user_openid");
		AppResult app = new AppResult();
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase()) || openId.length()!=28) {
			app.setFlag(1);
			app.setReason("用户信息错误，请返回重试");
			return app;
		}
	
		//查询用户是否已抽过
		List<CardLotteryDetail> detailList = wxMallService.selectIsUserLottery(userLotteryId,openId,forOpenId,orderId);
		if(detailList!=null&&detailList.size()>0){
			CardLotteryDetail detail = detailList.get(0);
			app.setFlag(2);
			app.setReason("已中奖");
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("cardTemplateId", detail.getCardTemplateId());
			obj.put("statusCfg", detail.getStatusCfg());//抽到的是否兑换过
			app.setData(obj);
			return app;
		}
		
		List<Map> list = wxMallService.selectSellerDeliveryCardTemplateList(statusCfg,sellerId);
		
		
		CardDeliveryProperty  property = wxMallService.selectDeliveryRankProbability(statusCfg);
		int index = 0;
		if(property==null){
			//随机中奖一个信息  
			 index = (int) (Math.random() * list.size());
		}else{
			double rate0 = new BigDecimal(property.getFirstRankProbability()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
			double rate1 = new BigDecimal(property.getSecondRankProbability()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
			double rate2 = new BigDecimal(property.getThirdRankProbability()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
			double rate3 = new BigDecimal(property.getFourthRankProbability()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
			index = MathChanceRandom.percentageRandom(rate0, rate1, rate2, rate3);
		}
		Map map = list.get(index);
		Long id = (Long) map.get("id");//中奖的卡券模板id
		String name = map.get("name").toString();//中奖的卡券模板id
		Integer couponCfg = Integer.parseInt(map.get("couponCfg").toString());//中奖的卡券模板id
		//记录用户中奖信息
		int num = wxMallService.insertCardLotteryDetail(userLotteryId,openId,id,orderId,forOpenId);
		if(num>0){
			app.setFlag(0);
			app.setReason("抽奖成功");
			com.alibaba.fastjson.JSONObject obj = new com.alibaba.fastjson.JSONObject();
			obj.put("index", index);
			obj.put("cardTemplateId", id);
			obj.put("name", name);
			obj.put("couponCfg", couponCfg);
			app.setData(obj);
		}else{
			app.setFlag(1);
			app.setReason("抽奖失败，请稍后再试");
		}
		return app;
	}
	
	 /**
	  * 跳转到领取卡券页面
	  * @param session
	  * @param request
	  * @param source  来自哪个渠道   0:公众号   只有公众号有这个领取页面//1:自营  2:周边  
	  * @param tId 	卡券模板id
	  * @return
	  */
	@RequestMapping("/jump/receiveCardCoupon")
	public ModelAndView jumpReceiveCardCoupon(HttpSession session,HttpServletRequest request,Integer source,Long tId){
		String openId = (String) session.getAttribute("user_openid");
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
			return view;
		}
		
		ModelAndView view = new ModelAndView("/weixin/paiQuan");
		CardCouponTemplate cardCouponTem  = sellerCardCouponService.queryCardCouponTemplateById(tId);
		if(cardCouponTem.getValidityCfg()==1){//领取多少日内有效
			Date date = DateUtils.getDateAfter(new Date(), cardCouponTem.getValidityDay());
			cardCouponTem.setValidityStarttime(new Date());
			cardCouponTem.setValidityEndtime(date);
		}
		
		view.addObject("cardCouponTem", cardCouponTem);
		view.addObject("source", source);
		return view;
	}
	
	/**
	 * 领取卡券
	 * @param session
	 * @param lotteryId
	 * @param fromwhere
	 * userLotteryId  自营，周边领取 需传的参数
	 * isFriend 0 或 null 自己
	 *          1 朋友的抽的     forOpenId 朋友的openId
	 *       
	 * @return
	 */
	@RequestMapping("/receiveCardCoupon")
	@ResponseBody
	public AppResult receiveCardCoupon(HttpSession session,HttpServletRequest request,Long id,Integer source,Long userLotteryId,Integer isFriend,String forOpenId){
		AppResult result = new AppResult();
		try{
			String openId = (String) session.getAttribute("user_openid");
			if(openId==null||openId.length()!=28){
				result.setFlag(1);
				result.setReason("用户数据异常,请重新打开页面重试!");
				return result;
			}
			if(source==1 || source==2){
				if(userLotteryId==null){
					result.setFlag(1);
					result.setReason("周边，自营领取缺少参数!");
					return result;
				}
			}
			result = wxMallService.insertUserCardCoupon(openId,id,source,userLotteryId,isFriend,forOpenId);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("领取失败");
			return result;
		}
	}
	
	/**
	 * 更改用户分享次数
	 *       
	 * @return
	 */
	@RequestMapping("/updateCardCouponShareCount")
	@ResponseBody
	public AppResult updateShareCount(HttpSession session,HttpServletRequest request,Long userLotteryId){
		AppResult result = new AppResult();
		try{
			String openId = (String) session.getAttribute("user_openid");
			if(openId==null||openId.length()!=28){
				result.setFlag(1);
				result.setReason("用户数据异常,请重新打开页面重试!");
				return result;
			}
			if(userLotteryId==null || userLotteryId<0){
				result.setFlag(1);
				result.setReason("周边，自营领取缺少参数!");
				return result;
			}
			
			boolean flag = wxMallService.updateCardCouponShareCount(userLotteryId);
			result.setFlag(0);
			result.setReason(flag+"");
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setFlag(1);
			return result;
		}
	}
	
	
	 /**
	  * 微信菜单跳转到我的卡包页面
	  * @param session
	  * @param request
	  * @return
	  */
	@RequestMapping("/jump/getMyCardCoupon")
	public ModelAndView getMyCardCoupon(HttpSession session,HttpServletRequest request){
		String openId = (String) session.getAttribute("user_openid");
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
			return view;
		}
		ModelAndView view = new ModelAndView("/weixin/myCardCoupon");
		return view;
	}
	
	
	/**
	 * 我的卡包 
	 * @param session
	 * @param lotteryId
	 * @param fromwhere
	 * @return
	 */
	@RequestMapping("/getMyCardCoupon")
	@ResponseBody
	public AppResult getMyCardCoupon(HttpSession session,HttpServletRequest request,Long couponCfg,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try{
			String openId = (String) session.getAttribute("user_openid");
			com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
			//查询我的卡包 ，如果couponCfg = null  查询所有
			Page<CardCouponVo> page = wxMallService.getMyCardCoupon(openId,couponCfg,pageNum,pageSize);
			
			json.put("list", page.getResult());
			json.put("ishasmore", page.getPages()>pageNum);
			result.setFlag(0);
			result.setReason("查询成功");
			result.setData(json);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
	}
	
	/**
	 * 我的卡包-卡券详情
	 * @param session
	 * @param lotteryId
	 * @param fromwhere
	 * @return
	 */
	@RequestMapping("/queryCardCouponDetail")
	public ModelAndView getMyCardCoupon(HttpSession session,HttpServletRequest request,Long cardCouponId){
		try{
			String openId = (String) session.getAttribute("user_openid");

			if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
				String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
				String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
				WxLoginUrls.addMap(urlid, url);
				ModelAndView view = new ModelAndView("redirect:"+WxLoginUrls.getBaseLoginUrl(urlid));
				return view;
			}
			CardCoupon cardCoupon = wxMallService.getMyCardCouponDetail(cardCouponId);
			return goToModelAndView("/weixin/myCardCouponDetail", "cardCoupon", cardCoupon);
		}catch(Exception e){
			log.error("select myCardCoupon error",e);
			this.returnError(ErrorCode.ERROR_99999);
			return goToModelAndView("/weixin/rebateStoreList", null, null);
		}
	}
	/**
	 * 
	 * 兴业银行的微信支付
	 * 
	 * couponId 为卡券id
	 */
	@RequestMapping("/toPayOrderWXXY")
	@ResponseBody
	public com.alibaba.fastjson.JSONObject toPayOrderWXXY(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		log.info(">>>>>>>>>>>>>>>>>>>进入兴业微信支付 start");
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetWXXY(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
				json.put("orderNo", resultMap.get("orderNo"));
				json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}else if("00009527".equals(resultCode)){
				//直接返回支付成功
				json.put("code","00009527");
				json.put("orderId",resultMap.get("orderId"));
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
}
