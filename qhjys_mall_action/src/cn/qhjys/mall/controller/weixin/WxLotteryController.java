package cn.qhjys.mall.controller.weixin;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.FROMWHERE;
import cn.qhjys.mall.common.JsapiTicket;
import cn.qhjys.mall.entity.CouponsInfo;
import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.LotteryInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.entity.UserLottery;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.service.CouponsInfoService;
import cn.qhjys.mall.service.LotteryInfoService;
import cn.qhjys.mall.service.StoreLotteryService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.UserLotteryService;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ZxingUtil;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.Sha1;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

import com.github.pagehelper.Page;
import com.google.zxing.WriterException;

@RequestMapping("/lottery")
@Controller
public class WxLotteryController extends Base{
	
	@Autowired
	private StoreLotteryService storeLotteryService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private UserLotteryService userLotteryService;
	@Autowired
	private WeixinUserinfoService weixinUserinfoService;
	@Autowired
	private LotteryInfoService lotteryInfoService;
	@Autowired
	private CouponsInfoService couponsInfoService;
	
	
	@RequestMapping("/fromWhere")
	public String getFrom(Integer fromwhere){
		return "redirect:/wxActivity/wxProductlist.do?fromwhere="+fromwhere;
	}
	/**
	 * 发起微信授权
	 * @return 回调到砍价页面
	 */
	@RequestMapping("/userLottery")
	public String userLottery(HttpSession session,Long userLotteryId,String openid){
		logger.info(" ---userLotteryId-"+userLotteryId);
		logger.info(" ---openid-"+openid);
		String openId = (String) session.getAttribute("openId");
		String request ;
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = ConstantsConfigurer.getProperty("get_userlottery_url")+"?openid="+userLotteryId+","+openid;
			 request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
		}else{
			 request = ConstantsConfigurer.getProperty("get_userlottery_url")+"?openid="+userLotteryId+","+openid;
		}
		return "redirect:"+request;
	}
	
	@RequestMapping("/toUserLottery")
	public ModelAndView toUserLottery(HttpSession session,HttpServletRequest request,String code,String openid){
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		logger.info(" ---url-"+url);
		String [] a = openid.split(",");
		logger.info(" ---openid-"+openid);
		Long userLotteryId = Long.valueOf(a[0]);
		openid= a[1];
		logger.info(" ---openid-"+openid);
		logger.info(" ---userLotteryId-"+userLotteryId);
		String openId = String.valueOf(session.getAttribute("openId"));
		logger.info("---openId-------"+openId);
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			 openId = String.valueOf(httpRequest.get("openid"));
			 logger.info("---openId-1------"+openId);
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			logger.info("---httpRequest1-------"+httpRequest1);
			String nickname = httpRequest1.optString("nickname");
			String headimgurl = httpRequest1.optString("headimgurl");
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			session.setAttribute("openId", openId);
		}
		ModelAndView view = new ModelAndView("/weixin/lottery");
		logger.info(" ---openId-"+openId);
		if (openId.equals(openid)) {
			view.setViewName("/weixin/userlottery");
		}

		UserLottery ul = userLotteryService.getUserLottery(userLotteryId);
		StoreLottery lottery = storeLotteryService.getStoreLottery(ul.getLotteryId());
		StoreInfo store = storeService.getStoreInfoById(lottery.getStoreId());
		List<LotteryDish> lds = storeLotteryService.listLotteryDishByLottery(lottery.getId());
		for (int i = 0; i < lds.size(); i++) {
			if (i == 0) {
				LotteryDish ld1 = lds.get(i);
				view.addObject("ld1", ld1);
			}
			if (i == 1) {
				LotteryDish ld2 = lds.get(i);
				view.addObject("ld2", ld2);
			}
			if (i == 2) {
				LotteryDish ld3 = lds.get(i);
				view.addObject("ld3", ld3);
			}
			if (i == 3) {
				LotteryDish ld4 = lds.get(i);
				view.addObject("ld4", ld4);
			}
		}
		ul.setNickName(URLDecoder.decode(ul.getNickName()));
		view.addObject("lottery", lottery);
		view.addObject("store", store);
		view.addObject("ul", ul);
		logger.info(" ---openId-----3---"+openId);
		WeixinUserinfo userinfo = weixinUserinfoService.getWeixinUserinfo(openId);
		if (userinfo != null) {
			view.addObject("isguanzhu", 1);
		}else{
			view.addObject("isguanzhu", 0);
		}
		logger.info("---openId--"+openId);
		logger.info("---ul.getId()--"+ul.getId());
		LotteryInfo li = lotteryInfoService.getLotteryInfo(openId, ul.getId());
		if (li != null) {
			view.addObject("already", 1);
		}else{
			view.addObject("already", 0);
		}
		Long datetime = System.currentTimeMillis()/1000L;
		String nonceStr = UUID.randomUUID()+"";
		view.addObject("datetime", datetime);
		view.addObject("nonceStr", nonceStr);
		String ticket = JsapiTicket.getTicket();
		String sha1 = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+datetime+"&url="+url;
		String sha2 = new Sha1().getDigestOfString(sha1.getBytes()).toLowerCase();
		view.addObject("sha", sha2);
		return view;
	}
	
	@RequestMapping("/getLottery")
	public String getLottery(HttpSession session,Long lotteryId,Integer fromwhere){
		if (fromwhere == null) {
			fromwhere = -1;
		}
		String openId = (String) session.getAttribute("openId");
		String request ;
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = ConstantsConfigurer.getProperty("get_lottery_url")+"?lotteryId="+lotteryId+","+fromwhere;
			 request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
		}else{
			 request = ConstantsConfigurer.getProperty("get_lottery_url")+"?lotteryId="+lotteryId+","+fromwhere;
		}
		return "redirect:"+request;
	}
	
	
	
	@RequestMapping("/toLottery")
	public ModelAndView toLottery(HttpSession session,HttpServletRequest request,String code,String lotteryId){
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		String [] a = lotteryId.split(",");
		Long lotteryId1 = Long.valueOf(a[0]);
		Integer fromwhere= Integer.valueOf(a[1]);
		String openId = String.valueOf(session.getAttribute("openId"));
		logger.info("---openId-------"+openId);
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			 openId = String.valueOf(httpRequest.get("openid"));
			 logger.info("---openId--1-----"+openId);
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			logger.info("---httpRequest1-------"+httpRequest1);
			String nickname = httpRequest1.optString("nickname");
			String headimgurl = httpRequest1.optString("headimgurl");
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			session.setAttribute("openId", openId);
		}
		 if (fromwhere>0) {
				FROMWHERE.fromwhere.put(openId, fromwhere);
			}
		StoreLottery lottery = storeLotteryService.getStoreLottery(lotteryId1);
		logger.info("---lottery-------"+lottery.getId());
		StoreInfo store = storeService.getStoreInfoById(lottery.getStoreId());
		UserLottery ul = userLotteryService.selectUserLottery(openId, lottery.getId());
		if (ul == null) {
			ul = new UserLottery();
			ul.setCreateDate(new Date());
			ul.setLotteryId(lottery.getId());
			ul.setNickName(URLEncoder.encode(String.valueOf(session.getAttribute("nickname"))));
			ul.setOpenId(openId);
			ul.setPortrait((String)session.getAttribute("headimgurl"));
			ul.setStatus(0);
			ul.setStoreId(lottery.getStoreId());
			userLotteryService.insertUserLottery(ul);
		}
		ul.setNickName(URLDecoder.decode(ul.getNickName()));
		ModelAndView view = new ModelAndView("/weixin/userlottery");
		List<LotteryDish> lds = storeLotteryService.listLotteryDishByLottery(lottery.getId());
		logger.info("---lds-------"+lds.size());
		for (int i = 0; i < lds.size(); i++) {
			if (i == 0) {
				LotteryDish ld1 = lds.get(i);
				view.addObject("ld1", ld1);
			}
			if (i == 1) {
				LotteryDish ld2 = lds.get(i);
				view.addObject("ld2", ld2);
			}
			if (i == 2) {
				LotteryDish ld3 = lds.get(i);
				view.addObject("ld3", ld3);
			}
			if (i == 3) {
				LotteryDish ld4 = lds.get(i);
				view.addObject("ld4", ld4);
			}
		}
		
		view.addObject("lottery", lottery);
		view.addObject("store", store);
		view.addObject("ul", ul);
		WeixinUserinfo userinfo = weixinUserinfoService.getWeixinUserinfo(openId);
		if (userinfo != null) {
			view.addObject("isguanzhu", 1);
		}else{
			view.addObject("isguanzhu", 0);
		}
		LotteryInfo li = lotteryInfoService.getLotteryInfo(openId, ul.getId());
		if (li != null) {
			view.addObject("already", 1);
		}else{
			view.addObject("already", 0);
		}
		
		Long datetime = System.currentTimeMillis()/1000L;
		String nonceStr = UUID.randomUUID()+"";
		view.addObject("datetime", datetime);
		view.addObject("nonceStr", nonceStr);
		String ticket = JsapiTicket.getTicket();
		String sha1 = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+datetime+"&url="+url;
		String sha2 = new Sha1().getDigestOfString(sha1.getBytes()).toLowerCase();
		view.addObject("sha", sha2);
		return view;
	}
	
	@RequestMapping("choujiang")
	@ResponseBody
	public String choujiang(HttpSession session,Long userLotteryId,String openid){
		String result="";
		String openId = (String) session.getAttribute("openId");
		int jiangxiang = (int) (Math.random()*100);
		int ranklevel = 4;
		if (openid.equals(openId)) {
			if (jiangxiang>59) {
				ranklevel = 2;
			}else if(jiangxiang>29){
				ranklevel = 3;
			}
		}else{
			if (jiangxiang>95) {
				ranklevel = 1;
			}else if(jiangxiang>69){
				ranklevel = 2;
			}else if(jiangxiang>29){
				ranklevel = 3;
			}
		}
		LotteryInfo li = new LotteryInfo();
		String nickname = URLEncoder.encode(String.valueOf(session.getAttribute("nickname")));
		String headimgurl = (String) session.getAttribute("headimgurl");
		li.setCreateDate(new Date());
		li.setNickName(nickname);
		li.setOpenId(openId);
		li.setPortrait(headimgurl);
		li.setRank(ranklevel);
		li.setUserLottertId(userLotteryId);
		lotteryInfoService.insertLotteryInfo(li);
		result = ""+ranklevel;
		return result;
	}
	
	@RequestMapping("listLotteryInfo")
	@ResponseBody
	public JSONObject listLotteryInfo(Long userLotteryId){
		List<LotteryInfo> lis = lotteryInfoService.listLotteryInfo(userLotteryId);
		logger.info("lis----------0--"+lis.size());
		CouponsInfo ci = couponsInfoService.getCouponsInfo(userLotteryId);
		JSONObject json = new JSONObject();
		json.put("lis", lis);
		List<CouponsInfo> cis = null;
		if (ci != null) {
			cis = new ArrayList<>();
			cis.add(ci);
		}
		json.put("cis", cis);
		//logger.info(json.toString());
		return json;
	}
	
	@RequestMapping("listLotteryInfo1")
	@ResponseBody
	public JSONObject listLotteryInfo1(Long userLotteryId){
		List<LotteryInfo> lis = lotteryInfoService.listLotteryInfo(userLotteryId);
		logger.info("lis----------1--"+lis.size());
		JSONObject json = new JSONObject();
		json.put("lis", lis);
		//logger.info(json.toString());
		return json;
	}
	
	@RequestMapping("/exchange")
	public ModelAndView exchange(HttpServletRequest request,Long userLotteryId,Integer rank) throws IOException, WriterException{
		String a = request.getSession().getServletContext().getRealPath("/");
		String urlbase  = request.getScheme()+"://"+ request.getServerName();
		logger.info(urlbase);
		logger.info(a);
		UserLottery ul = userLotteryService.getUserLottery(userLotteryId);
		CouponsInfo ci = couponsInfoService.getCouponsInfo(userLotteryId, rank);
		LotteryDish dish = storeLotteryService.getLotteryDish(ul.getLotteryId(), rank);
		if (ci == null) {
			ci = new CouponsInfo();
			ci.setCreateTime(new Date());
			Date endTime = new Date(System.currentTimeMillis()+7*24*60*60*1000L - 1L);
			ci.setEndTime(endTime);
			ci.setLotteryId(ul.getLotteryId());
			ci.setOpenId(ul.getOpenId());
			ci.setRank(rank);
			ci.setStatus(0);
			ci.setUserLotteryId(userLotteryId);
			couponsInfoService.insertCouponsInfo(ci);
		}
		String url = urlbase+"/wxActivity/businessesScan.do?couponsId="+ci.getId();
		String imageurl  = ZxingUtil.made(url, a);
		ci.setCouponsImage(imageurl);
		couponsInfoService.updateCouponsInfo(ci);
		
		ModelAndView view = new ModelAndView("/weixin/exchange");
		StoreLottery lottery = storeLotteryService.getStoreLottery(ul.getLotteryId());
		StoreInfo store = storeService.getStoreInfoById(lottery.getStoreId());
		view.addObject("dish", dish);
		view.addObject("ci", ci);
		view.addObject("store", store);
		return view;
	}
	
	@RequestMapping("/getCoupons")
	public ModelAndView getCoupons(String openId,Long lotteryId,Integer pageNum){
		Integer pageSize = 1;
		pageNum = 1;
		CouponsInfo ci = null;
		Page<CouponsInfo> cipage = couponsInfoService.listCoupons(openId, lotteryId, pageNum, pageSize);
		if (cipage.getTotal() != 0) {
			ci = cipage.getResult().get(0);
		}
		LotteryDish dish = storeLotteryService.getLotteryDish(lotteryId, ci.getRank());
		ModelAndView view = new ModelAndView("/weixin/exchange");
		StoreLottery lottery = storeLotteryService.getStoreLottery(dish.getLotteryId());
		StoreInfo store = storeService.getStoreInfoById(lottery.getStoreId());
		view.addObject("dish", dish);
		 view.addObject("ci", ci);
		 view.addObject("store", store);
		 view.addObject("pageNum", cipage.getPageNum());
		 view.addObject("lotteryId", lotteryId);
		 view.addObject("openId", openId);
		 view.addObject("total", cipage.getTotal());
		 return view;
	}
	@RequestMapping("/showCoupons")
	public ModelAndView showCoupons(Long id){
		CouponsInfo ci = couponsInfoService.getCouponsInfoById(id);
		LotteryDish dish = storeLotteryService.getLotteryDish(ci.getLotteryId(), ci.getRank());
		ModelAndView view = new ModelAndView("/weixin/exchange");
		StoreLottery lottery = storeLotteryService.getStoreLottery(dish.getLotteryId());
		StoreInfo store = storeService.getStoreInfoById(lottery.getStoreId());
		view.addObject("dish", dish);
		view.addObject("ci", ci);
		view.addObject("store", store);
		return view;
	}
	
}
