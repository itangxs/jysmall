package cn.qhjys.mall.controller.fq;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.JsapiTicket;
import cn.qhjys.mall.common.WxLoginUrls;
import cn.qhjys.mall.entity.FqAcard;
import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardUserLottery;
import cn.qhjys.mall.entity.FqAcardUserRecord;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.service.fq.FqAcardUserExchangeService;
import cn.qhjys.mall.service.fq.FqAcardUserLotteryService;
import cn.qhjys.mall.service.fq.FqAcardUserRecordService;
import cn.qhjys.mall.service.fq.FqAcardWebService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.vo.AcardLotteryInfoVo;
import cn.qhjys.mall.weixin.util.Sha1;

@Controller
@RequestMapping("/user/fqcardactivity")
public class FqAcardController extends Base {
	
	@Autowired
	FqAcardWebService fqAcardWebService;
	@Autowired
	FqAcardUserRecordService fqAcardUserRecordService;
	@Autowired
	FqAcardUserLotteryService fqAcardUserLotteryService;
	@Autowired 
	FqAcardUserExchangeService fqAcardUserExchangeService;
	@Autowired
	SellerWXService sellerWXService;
	@Autowired
	FqUserInfoService fqUserInfoService;
	
	
	/**
	 * 红包AB券活动页
	 * @param session
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/activity_page")
	public ModelAndView activityPage(HttpSession session,Long orderId,Long storeId) throws Exception {
		ModelAndView view = new ModelAndView("/weixin/abcard/activity_page");
		// 控制A券显示
		FqAcard fqAcard = fqAcardWebService.queryAcardByStoreIdAndStatus(storeId);
		view.addObject("fqAcard", fqAcard);
		view.addObject("orderId", orderId);
		view.addObject("storeId", storeId);
		return view;
	}
	
	/**
	 * 发起a券抽奖页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/a_raffle")
	public Object aRaffle(HttpSession session,HttpServletRequest request,
			Long orderId,Long storeId,Long acardId,Integer type) throws Exception {
		String signUrl = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		
		FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		if (user == null) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			return "redirect:"+WxLoginUrls.getLoginUrl(urlid);
		}
		
		// 生成用户参加活动记录
		Long recordId = fqAcardUserRecordService.insertUserRecordOnce(orderId, storeId, acardId, user.getId(),type);
		if (recordId == null) {
			return goUrl("/user/fqcardactivity/activity_page.do", "参数异常");
		}
		
		ModelAndView view = new ModelAndView("/weixin/abcard/a_raffle");
		
		// 查询店铺信息
		FqStore fqStore = sellerWXService.queryFqStoreById(storeId);
		// 查询用户抽奖情况信息
		List<AcardLotteryInfoVo> lotteryInfoVos = fqAcardUserLotteryService.queryAcardLotteryInfo(recordId);
		
		Long datetime = System.currentTimeMillis()/1000L;
		String nonceStr = UUID.randomUUID()+"";
		view.addObject("datetime", datetime);
		view.addObject("nonceStr", nonceStr);
		String ticket = JsapiTicket.getTicket();
		String sha1 = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+datetime+"&url="+signUrl;
		String sha2 = new Sha1().getDigestOfString(sha1.getBytes()).toLowerCase();
		view.addObject("sha", sha2);
		view.addObject("recordId", recordId);
		view.addObject("user", user);
		view.addObject("acardId", acardId);
		view.addObject("storeId", storeId);
		view.addObject("fqStore", fqStore);
		view.addObject("lotteryInfoVos", lotteryInfoVos);
		return view;
	}
	
	/**
	 * 抽奖（自己抽）
	 * @param session
	 * @param acardId
	 * @param userId
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/acard_raffle")
	@ResponseBody
	public Object acardRaffle(HttpSession session,Long acardId,
			Long userId,Long recordId) throws Exception {
		// 判断该用户是否已经兑换
		boolean isExchange = fqAcardUserRecordService.isExchangeAcard(recordId);
		if (isExchange) {
			// 已经兑换或者recordId不存在
			return "-1001";
		}
		// 判断是否已经抽过奖
		FqAcardUserLottery lottery = fqAcardUserLotteryService.queryAcardLotteryByUser(userId, recordId);
		if (lottery != null) {
			return "-1000";
		}
		// 抽奖
		FqAcardPrize prize = fqAcardUserLotteryService.acardRaffle(acardId, userId, recordId);
		if (prize == null) {
			return "fail";
		}
		return prize;
	}
	
	@RequestMapping("/acard_exchange")
	@ResponseBody
	public Integer acardExchange(HttpSession session,
			Long userId,Long recordId,Long prizeId,Long storeId) throws Exception {
		int result = -1;
		try {
			result = fqAcardUserExchangeService.insertAcardUserExchangeOnce(userId, recordId, prizeId, storeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == 0) {
			// 已兑换过
			return 0;
		}else if (result == 1) {
			// 未关注
			return 1;
		}else if (result == 2) {
			// 已关注
			return 2;
		}else {
			return -1;
		}
	}
	
	/**
	 * 分享跳转action
	 * @param session
	 * @param request
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/a_share")
	public Object aShare(HttpSession session,HttpServletRequest request,
			Long recordId) throws Exception {
		
		FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		if (user == null) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			return "redirect:"+WxLoginUrls.getLoginUrl(urlid);
		}
		FqAcardUserRecord acardUserRecord = fqAcardUserRecordService.queryAcardUserRecordById(recordId);
		if (acardUserRecord.getUserId() == user.getId()) {
			String signUrl = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			// 同一用户
			ModelAndView view = new ModelAndView("/weixin/abcard/a_raffle");
			// 查询店铺信息
			FqStore fqStore = sellerWXService.queryFqStoreById(acardUserRecord.getStoreId());
			// 查询用户抽奖情况信息
			List<AcardLotteryInfoVo> lotteryInfoVos = fqAcardUserLotteryService.queryAcardLotteryInfo(recordId);
			
			Long datetime = System.currentTimeMillis()/1000L;
			String nonceStr = UUID.randomUUID()+"";
			view.addObject("datetime", datetime);
			view.addObject("nonceStr", nonceStr);
			String ticket = JsapiTicket.getTicket();
			String sha1 = "jsapi_ticket="+ticket+"&noncestr="+nonceStr+"&timestamp="+datetime+"&url="+signUrl;
			String sha2 = new Sha1().getDigestOfString(sha1.getBytes()).toLowerCase();
			view.addObject("sha", sha2);
			view.addObject("recordId", recordId);
			view.addObject("user", user);
			view.addObject("acardId", acardUserRecord.getAcardId());
			view.addObject("storeId", acardUserRecord.getStoreId());
			view.addObject("fqStore", fqStore);
			view.addObject("lotteryInfoVos", lotteryInfoVos);
			return view;
		}else {
			ModelAndView view = new ModelAndView("/weixin/abcard/a_share");
			// 查询店铺信息
			FqStore fqStore = sellerWXService.queryFqStoreById(acardUserRecord.getStoreId());
			view.addObject("fqStore", fqStore);
			FqUserInfo fqUserInfo = fqUserInfoService.getFqUserInfoById(acardUserRecord.getUserId());
			view.addObject("friendName", fqUserInfo.getNickName());
			// 查询用户抽奖情况信息
			List<AcardLotteryInfoVo> lotteryInfoVos = fqAcardUserLotteryService.queryAcardLotteryInfo(recordId);
			view.addObject("lotteryInfoVos", lotteryInfoVos);
			view.addObject("recordId", recordId);
			view.addObject("user", user);
			view.addObject("acardId", acardUserRecord.getAcardId());
			view.addObject("storeId", acardUserRecord.getStoreId());
			view.addObject("orderId", acardUserRecord.getOrderId());
			return view;
		}
	}
	
	/**
	 * 抽奖（分享页）
	 * @param session
	 * @param acardId
	 * @param userId
	 * @param recordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/acard_share_raffle")
	@ResponseBody
	public Object acardShareRaffle(HttpSession session,Long acardId,
			Long userId,Long recordId) throws Exception {
		// 判断该用户是否已经兑换
		boolean isExchange = fqAcardUserRecordService.isExchangeAcard(recordId);
		if (isExchange) {
			// 已经兑换或者recordId不存在
			return "-1001";
		}
		
		// 判断是否已经抽过奖
		FqAcardUserLottery lottery = fqAcardUserLotteryService.queryAcardLotteryByUser(userId, recordId);
		if (lottery != null) {
			return "-1000";
		}
		// 抽奖
		FqAcardPrize prize = fqAcardUserLotteryService.acardRaffle(acardId, userId, recordId);
		if (prize == null) {
			return "fail";
		}
		return prize;
	}
}
