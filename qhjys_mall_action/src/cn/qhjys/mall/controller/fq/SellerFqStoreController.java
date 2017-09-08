package cn.qhjys.mall.controller.fq;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqCuisine;
import cn.qhjys.mall.entity.FqLocation;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqRebate;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.FqStoreVo;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

@Controller
public class SellerFqStoreController extends Base {
	@Autowired
	private SellerWXService sellerWXService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	@Autowired
	private StoreRebateService storeRebateService;
	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/managermall/seller/queryWXStoreInfo")
	public Object queryWXStoreInfo(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		List<FqCuisine> fqCuisines = sellerWXService.queryFqCuisineListByEnabled(null);
		List<FqLocation> Location = sellerWXService.querFqLocationsListByEnabled(null);
		view.addObject("fqCuisines", fqCuisines);
		view.addObject("fqLocation", Location);
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		} else {
			Long sellerId = seller.getId();
			FqStore fqStore = sellerWXService.queryFqStoreBySellerId(sellerId);
			if (null == fqStore) {
				String token = UUID.randomUUID().toString();
				view.addObject("addWeixinStoreToken", token);
				SessionUtil.setSession(session, "addWeixinStoreToken", token);
				view.setViewName("/seller/weixin/add_weixin_store");
				return view;
			} else {
				view.addObject("fqStore", fqStore);
				if (fqStore.getStatus() == 0) {
					String token = UUID.randomUUID().toString();
					view.addObject("updateWeixinStoreToken", token);
					SessionUtil.setSession(session, "updateWeixinStoreToken", token);
					view.setViewName("/seller/weixin/update_weixin_store");
					return view;
				}
				if (fqStore.getStatus() == 1) {
					List<FqRebate> fqRebateList = sellerWXService.queryFqRebateByStoreIdList(fqStore.getId());
					String weixinRebateToken = UUID.randomUUID().toString();
					SessionUtil.setSession(session, "weixinRebateToken", weixinRebateToken);
					view.addObject("weixinRebateToken", weixinRebateToken);
					String updateWeixinStoreToken = UUID.randomUUID().toString();
					view.addObject("updateWeixinStoreToken", updateWeixinStoreToken);
					SessionUtil.setSession(session, "updateWeixinStoreToken", updateWeixinStoreToken);

					view.addObject("fqRebateList", fqRebateList);
				}
				view.setViewName("/seller/weixin/weixin_store");
				return view;
			}
		}
	}
	//微信店铺折扣列表
	@RequestMapping("/managermall/seller/fqRebate_list")
	public Object fqRebate_list(
			@RequestParam(value="pageNum",required = false)Integer pageNum,
			 HttpSession session
			) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			return "redirect:/seller/login.do";
		}
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(seller.getId());
		if(null==fqStore){
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请先申请一个店铺");
		}
		if(fqStore.getType()==1){
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "您暂时无法操作此项.请联系客服");
		}
		if(null==pageNum||pageNum<1)
			pageNum =1;
		Integer pageSize =30;
		Page<FqRebate> rebates =   sellerWXService.queryFqRebateByStoreIdList(fqStore.getId(),pageNum,pageSize);
		ModelAndView view = new ModelAndView("/seller/weixin/fqRebate_list");
		view.addObject("page",rebates);
		return view;
	}
	@RequestMapping("/managermall/seller/fqRebate_page")
	public Object fqRebate_page(
			@RequestParam(value="id",required = false)Long id,
			 HttpSession session
			) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			return "redirect:/seller/login.do";
		}
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(seller.getId());
		if(null==fqStore){
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请先申请一个店铺");
		}
		if(fqStore.getType()==1){
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "您暂时无法操作此项.请联系客服");
		}
		ModelAndView view = new ModelAndView("/seller/weixin/fqRebate_page");
		String weixinRebateToken = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "weixinRebateToken", weixinRebateToken);
		view.addObject("weixinRebateToken", weixinRebateToken);
		if(null!=id){
			FqRebate fqRebate=  sellerWXService.queryFqRebateByStoreId(id,fqStore.getId());
			view.addObject("fqRebate", fqRebate);
		}
		return view;
	}
	
	
	
	
	
	
	
	
	

	@RequestMapping("/managermall/seller/fqRebate")
	public Object fqRebate(String token, Long id, String rebate, String rebateInfo, String beginTime, String endTime,
			@RequestParam(value = "enable", required = true) Integer enable, HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			return "redirect:/seller/login.do";
		}
		String string = SessionUtil.getSession(session, "weixinRebateToken").toString();
		if (StringUtils.isEmpty(string) || !token.equals(string)) {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "请不要重复提交");
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date parse2 = null;
		Date parse = null;
		try {
			parse2 = format.parse(beginTime);
			parse = format.parse(endTime);
		} catch (ParseException e) {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "开始或者结束时间错误");
		}
		if (parse2.getTime() >= parse.getTime()) {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "结束时间必须大于开始时间");
		}
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(seller.getId());
		if (null == fqStore) {
			ModelAndView view = new ModelAndView();
			String tokent = UUID.randomUUID().toString();
			view.addObject("addWeixinStoreToken", tokent);
			SessionUtil.setSession(session, "addWeixinStoreToken", tokent);
			view.setViewName("/seller/weixin/add_weixin_store");
			return view;
		}
		if (fqStore.getStatus() != 1) {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "审核未通过!请联系客服");
		}
		if (fqStore.getType() != 0) {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "状态错误!请联系客服");
		}

		Boolean l = sellerWXService.saveOrUpdateFqRebate(seller.getId(), id, new BigDecimal(rebate), rebateInfo,
				beginTime, endTime, enable);
		if (l) {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "成功");
		} else {
			return super.goUrl("/managermall/seller/fqRebate_list.do", "失败");
		}

	}

	@RequestMapping(value = "/managermall/seller/addWXStoreInfo")
	public Object addWXStoreInfo(@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "storename", required = true) String storename,
			@RequestParam(value = "storelogo", required = true) String storelogo,
			@RequestParam(value = "activityinfo", required = false) String activityinfo,
			@RequestParam(value = "storeinfo", required = true) String storeinfo,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "phonenum", required = true) String phonenum,
			@RequestParam(value = "trafficBeginTime", required = true) String trafficBeginTime,
			@RequestParam(value = "trafficEndTime", required = true) String trafficEndTime,
			@RequestParam(value = "proportion", required = true) Integer proportion,
			@RequestParam(value = "locationid", required = true) Long locationid,
			/* @RequestParam(value="cuisineid",required = true)Long cuisineid, */
			String relevantImage1, String relevantImage2, String relevantImage3, HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			return "redirect:/seller/login.do";
		}
		String string = SessionUtil.getSession(session, "addWeixinStoreToken").toString();
		if (StringUtils.isEmpty(string) || !token.equals(string)) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请不要重复提交");
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date begin = null;
		Date end = null;
		try {
			begin = format.parse(trafficBeginTime);
			end = format.parse(trafficEndTime);
		} catch (ParseException e) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "开始或者结束时间错误");
		}
		if (begin.getTime() >= end.getTime()) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "结束时间必须大于开始时间");
		}
		StringBuffer storeImages = new StringBuffer();
		if (StringUtils.isNotEmpty(relevantImage1))
			storeImages.append(relevantImage1 + ",");
		if (StringUtils.isNotEmpty(relevantImage2))
			storeImages.append(relevantImage2 + ",");
		if (StringUtils.isNotEmpty(relevantImage3))
			storeImages.append(relevantImage3 + ",");
		int saveFqStore = sellerWXService.saveFqStore(seller.getId(), storename, storelogo, activityinfo, storeinfo,
				address, trafficBeginTime, trafficEndTime, phonenum, proportion, locationid, null,
				storeImages.toString(), null);

		if (saveFqStore == 1) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "添加成功");
		} else {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "添加失败");
		}

	}

	@RequestMapping(value = "/managermall/seller/updateWXStoreInfo")
	public Object updateWXStoreInfo(@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "storename", required = true) String storename,
			@RequestParam(value = "storelogo", required = true) String storelogo,
			@RequestParam(value = "activityinfo", required = false) String activityinfo,
			@RequestParam(value = "storeinfo", required = true) String storeinfo,
			@RequestParam(value = "address", required = true) String address,
			@RequestParam(value = "trafficBeginTime", required = true) String trafficBeginTime,
			@RequestParam(value = "trafficEndTime", required = true) String trafficEndTime,
			@RequestParam(value = "phonenum", required = true) String phonenum,
			@RequestParam(value = "proportion", required = true) Integer proportion,
			@RequestParam(value = "locationid", required = true) Long locationid,
			/* @RequestParam(value="cuisineid",required = true)Long cuisineid, */
			String relevantImage1, String relevantImage2, String relevantImage3, HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			return "redirect:/seller/login.do";
		}
		String string = SessionUtil.getSession(session, "updateWeixinStoreToken").toString();
		if (StringUtils.isEmpty(string) || !token.equals(string)) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "请不要重复提交");
		}

		Long sellerId = seller.getId();
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(sellerId);
		if (null == fqStore) {
			List<FqCuisine> fqCuisines = sellerWXService.queryFqCuisineListByEnabled(null);
			List<FqLocation> Location = sellerWXService.querFqLocationsListByEnabled(null);
			String rtoken = UUID.randomUUID().toString();
			view.addObject("addWeixinStoreToken", rtoken);
			SessionUtil.setSession(session, "addWeixinStoreToken", rtoken);
			view.addObject("fqCuisines", fqCuisines);
			view.addObject("fqLocation", Location);
			view.setViewName("/seller/weixin/add_weixin_store");
			return view;
		}
		/*
		 * if(fqStore.getStatus()!=0){
		 * view.setViewName("/seller/weixin/weixin_store"); return view; }
		 */
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date begin = null;
		Date end = null;
		try {
			begin = format.parse(trafficBeginTime);
			end = format.parse(trafficEndTime);
		} catch (ParseException e) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "开始或者结束时间错误");
		}
		if (begin.getTime() >= end.getTime()) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "结束时间必须大于开始时间");
		}
		StringBuffer storeImages = new StringBuffer();
		if (StringUtils.isNotEmpty(relevantImage1))
			storeImages.append(relevantImage1 + ",");
		if (StringUtils.isNotEmpty(relevantImage2))
			storeImages.append(relevantImage2 + ",");
		if (StringUtils.isNotEmpty(relevantImage3))
			storeImages.append(relevantImage3 + ",");
		int saveFqStore = sellerWXService.updateFqStore(fqStore.getId(), storename, storelogo, activityinfo, storeinfo,
				address, trafficBeginTime, trafficEndTime, phonenum, proportion, locationid, null,
				storeImages.toString(), null);

		if (saveFqStore == 1) {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "修改成功");
		} else {
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "修改失败");
		}

	}
	
	// 微信店铺列表
	@RequestMapping("/wxMall/fqStoreList")
	public Object fqStoreList(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "lid", required = false) Long lid,HttpSession session,HttpServletRequest request) throws Exception {
		FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		logger.info("---fqStoreList-");
		logger.info("---user == null-"+(user == null));

//		if (user == null) {
//			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
//			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
//			WxLoginUrls.addMap(urlid, url);
//			return "redirect:"+WxLoginUrls.getLoginUrl(urlid);
//		}

		ModelAndView view = new ModelAndView("/weixin/fqStore/list");
		if (null == page)
			page = 1;
		 Integer pageSize = 10;
		 logger.info("---lid-"+(null==lid?"null":lid));
		Page<FqStoreVo> stores = sellerWXService.queryFqStoreByPageLocation(lid, 1, page, pageSize);
		// 新添加:根据店铺拿折扣信息

		view.addObject("stores", stores); 
		view.addObject("page", page);
		List<FqLocation> fqLocations = sellerWXService.queryFqLocationListByStatsu(1);
		 logger.info(">>>>>>>>>>>>>>>fqLocations>>>>>>>>>>>"+fqLocations.size());
		view.addObject("fqLocations", fqLocations);
		view.addObject("lid", lid);
		return view;
	}
	//ajax加载
	@ResponseBody
	@RequestMapping("/wxMall/fqStoreListAjax")
	public Object fqStoreListAjax(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "lid", required = false) Long lid) throws Exception {
		if (null == page)
			page = 1;
		 Integer pageSize = 10;
		Page<FqStoreVo> stores = sellerWXService.queryFqStoreByPageLocation(lid, 1, page, pageSize);
		// 新添加:根据店铺拿折扣信息
//				for (int i = 0; i < stores.size(); i++) {
//					FqStoreVo fqStoreVo = stores.get(i);
//					StoreInfo store1 = storeService.queryStoreInfoBySeller(fqStoreVo.getSellerId());
//					if(null!=store1){
//						StoreRebate rebate = storeRebateService.getStoreRebateByStoreId(store1.getId());
//						if (rebate != null) {
//							fqStoreVo.setStoreRebateTemp(rebate);
//						}
//					}
//				}
		if(page>stores.getPages()){
			return null;
		}
		return stores.getResult();
	}

	// 微信店铺详情
	@RequestMapping("/wxMall/fqStoreDetail")
	public ModelAndView fqStoreDetail(@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("/weixin/fqStore/detail");
		FqStore store = sellerWXService.queryFqStoreById(id, 1);
		StoreInfo store1 = storeService.queryStoreInfoBySeller(store.getSellerId());
		if(null!=store1){
//			List<FqRebate> rebateList = sellerWXService.queryFqRebateByStoreIdList(store.getId(),1);
			StoreRebate rebate = storeRebateService.getStoreRebateByStoreId(store1.getId());
//			view.addObject("rebateList",rebateList);
			if (rebate != null) {
				view.addObject("storerebate",rebate);
			}
		}
		view.addObject("store", store);
		return view;
	}
	
	
		//用户订单管理
		/**
		 * 发起微信授权
		 * @return 用户订单页面
		 */
		@RequestMapping("/wxMall/myfqorder")
		public String getBargain(HttpSession session){
			FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
			String request ;
			if (user ==null) {
				String url = ConstantsConfigurer.getProperty("get_myfqorder_url");
				 request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
			}else{
				 request = ConstantsConfigurer.getProperty("get_myfqorder_url");
			}
			return "redirect:"+request;
		}
		
		
		
		@RequestMapping ("/wxMall/getmyfqorder")
		public ModelAndView  getmycouponsInfo(
				HttpSession session,HttpServletRequest request,String code
				) throws Exception{
			
			String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
			if (user ==null) {
				String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
				JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
				 String openId = (String) httpRequest.get("openid");
				String access_token = (String) httpRequest.get("access_token");
				String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
				JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
				logger.info("---httpRequest1-------"+httpRequest1);
				String nickname = httpRequest1.optString("nickname");
				String headimgurl = httpRequest1.optString("headimgurl");
				user = fqUserInfoService.updateFqUserInfo(openId, nickname, headimgurl);
				session.setAttribute(ConstantsConfigurer.getWxUser(), user);
			}
			
			ModelAndView view = new ModelAndView("/weixin/fqStore/order_detail_list");
			
		/*	
			String openId = "og3UrwF_78vOHhKQuCyojXy9umWs";
			session.setAttribute("openId", openId);*/
			
			Integer page = 1;
			Integer pagesize = 10;
			
			List<FqOrder> list =  sellerWXService.queryFqOrderListByOpenId(user.getOpenId(),page,pagesize);
			
			/*List<CouponsInfo> list=getUserCouponsInfo(openId);
			List<StoreLotteryVo> lotterys = activityService.getStoreLotteryVoByStatus(1);
			view.addObject("lotterys",lotterys);*/
			view.addObject("list",list);
			view.addObject("page",page);
			view.addObject("openId",user.getOpenId());
			return view;
		}
		@ResponseBody
		@RequestMapping("/wxMall/ajaxmyfqorder")
		public Object  ajaxmyfqorder(
				@RequestParam(value = "openId",required = true)String openId,
				@RequestParam(value = "page",required = true)Integer page
				) throws Exception{
			Integer pagesize = 10;
			List<FqOrder> list =  sellerWXService.queryFqOrderListByOpenId(openId,page,pagesize);
			return list;
			
		}
		
		@RequestMapping("/wxMall/fqorderDetail")
		public ModelAndView fqorderDetail(
				@RequestParam(value = "id",required = true)Long id,
				@RequestParam(value = "openId",required = true)String openId
				) throws Exception{
			ModelAndView  view = new ModelAndView("/weixin/fqStore/order_detail");
			FqOrder order = sellerWXService.queryFqorderByIdAndOpenId(id,openId);
			if(order==null){
				return null;
			}
			List<FqOrderDetail> detail = sellerWXService.queryFqOrderDetailByOrderId(id);
			view.addObject("order",order);
			view.addObject("detail",detail);
			return view;
		}
}
