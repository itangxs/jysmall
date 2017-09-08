package cn.qhjys.mall.controller.weixin;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.JsapiTicket;
import cn.qhjys.mall.entity.Bargain;
import cn.qhjys.mall.entity.BargainInfo;
import cn.qhjys.mall.entity.UserBargain;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.service.BargainService;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.Sha1;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

@Controller
@RequestMapping("/bargain")
public class BargainController extends Base{
	
	@Autowired
	private BargainService bargainService;
	@Autowired
	private WeixinUserinfoService weixinUserinfoService;
	
	
	/**
	 * 发起微信授权
	 * @return 回调到砍价页面
	 */
	@RequestMapping("/getBargain")
	public String getBargain(HttpSession session){
		String openId = (String) session.getAttribute("openId");
		String request ;
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = ConstantsConfigurer.getProperty("get_bargain_url");
			 request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
		}else{
			 request = ConstantsConfigurer.getProperty("get_bargain_url");
		}
		return "redirect:"+request;
	}
	/**
	 * 获取微信授权信息 ,获取session信息,查询砍价活动信息及详情
	 * @return 回调到砍价页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/toBargain")
	public ModelAndView toBargain(HttpSession session,HttpServletRequest request,String code) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		logger.info("--url-"+url);
		String openId = (String) session.getAttribute("openId");
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			 openId = (String) httpRequest.get("openid");
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			String nickname = httpRequest1.getString("nickname");
			String headimgurl = httpRequest1.getString("headimgurl");
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			session.setAttribute("openId", openId);
		}
		Bargain bargain = bargainService.getLastBargain();
		UserBargain ub = bargainService.getUserBargainByOpenid( openId, bargain.getId());
		if (ub == null) {
			ub = new UserBargain();
			ub.setBargainId(bargain.getId());
			ub.setCreateTime(new Date());
			ub.setNowPrice(bargain.getPrice());
			ub.setOpenId(openId);
			ub.setStatus(0);
			ub.setCodes(BaseUtil.couponRandom());
			bargainService.insertUserBargain(ub);
		}
		List<BargainInfo> bis1 = bargainService.listBargainInfos(ub.getId());
		List<BargainInfo> bis = new ArrayList<>();
		for ( BargainInfo bi :bis1){
			//bi.setNickName(new String(bi.getNickName().getBytes("UTF-8"),"UTF-8"));
			bi.setNickName(URLDecoder.decode(bi.getNickName()));
			bis.add(bi);
		}
		ModelAndView view = new ModelAndView("/weixin/bargain");
		view.addObject("bargain", bargain);
		view.addObject("ub", ub);
		view.addObject("bis", bis);
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
	
	@RequestMapping("/userBargain")
	public String userBargain(HttpSession session,String openid,Long bargainId){
		String openId = (String) session.getAttribute("openId");
		String request ;
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase())) {
			String url = ConstantsConfigurer.getProperty("get_userbargain_url")+"?openid="+openid+","+bargainId;
			request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
		}else{
			request = ConstantsConfigurer.getProperty("get_userbargain_url")+"?openid="+openid+","+bargainId;
		}
		return "redirect:"+request;
	}
	
	@RequestMapping("/toUserBargain")
	public ModelAndView toUserBargain(HttpSession session,HttpServletRequest request,String code,String openid) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		String [] a = openid.split(",");
		Long bargainId = Long.valueOf(a[1]);
		openid= a[0];
		String openId = (String) session.getAttribute("openId");
		logger.info("--toUserBargain--openId-"+openId);
		if (StringUtils.isEmpty(openId) || "NULL".equals(openId.toUpperCase()))  {
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			 openId = (String) httpRequest.get("openid");
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			String nickname = httpRequest1.getString("nickname");
			String headimgurl = httpRequest1.getString("headimgurl");
			logger.info("--toUserBargain--nickname-"+nickname);
			logger.info("--toUserBargain--headimgurl-"+headimgurl);
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			session.setAttribute("openId", openId);
		}
		ModelAndView view = new ModelAndView("/weixin/userbargain");
		if (openId.equals(openid)) {
			view.setViewName("/weixin/bargain");
		}
		Bargain bargain = bargainService.getBargain(bargainId);
		UserBargain ub = bargainService.getUserBargainByOpenid( openid, bargain.getId());
		List<BargainInfo> bis1 = bargainService.listBargainInfos(ub.getId());
		List<BargainInfo> bis = new ArrayList<>();
		for ( BargainInfo bi :bis1){
			//bi.setNickName(new String(bi.getNickName().getBytes("UTF-8"),"UTF-8"));
			bi.setNickName(URLDecoder.decode(bi.getNickName()));
			
			bis.add(bi);
		}
		
		view.addObject("bargain", bargain);
		view.addObject("ub", ub);
		view.addObject("bis", bis);
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
	
	@RequestMapping("/woyaocanyu")
	public void woyaocanyu(HttpServletResponse response,String openid){
		WeixinUserinfo userinfo = weixinUserinfoService.getWeixinUserinfo(openid);
		if (userinfo == null) {
			HtmlUtil.writerJson(response, "noguanzhu");
		}else{
			HtmlUtil.writerJson(response, "guanzhu");
		}
	}
	@RequestMapping("/bangtakanjia")
	public void bangtakanjia(HttpSession session,HttpServletResponse response,Long userBargainId) throws Exception {
		//logger.info("---userBargainId--"+userBargainId);
		String openId = String.valueOf(session.getAttribute("openId"));
		WeixinUserinfo userinfo = weixinUserinfoService.getWeixinUserinfo(openId);
		//logger.info("--userinfo--"+ (null == userinfo));
		//logger.info("--openId--"+ openId);
		if (null == userinfo) {
			HtmlUtil.writerJson(response, "noguanzhu");
		}else{
			UserBargain ub = bargainService.getUserBargain(userBargainId);
			BargainInfo info = bargainService.getBargainInfoByOpenId(openId, userBargainId);
			//logger.info("--openId1--");
			if (null == info) {
				//logger.info("--openId2--");
				String nickname = String.valueOf(session.getAttribute("nickname"));
				String headimgurl = String.valueOf(session.getAttribute("headimgurl"));
				logger.info("--bangtakanjia--nickname-"+nickname);
				logger.info("--bangtakanjia--headimgurl-"+headimgurl);
				//logger.info("--nickname--"+nickname);
				//logger.info("--headimgurl--"+headimgurl);
				info = new BargainInfo();
				info.setCreateTime(new Date());
				info.setMoney(new BigDecimal(BaseUtil.get1to3()));
				//info.setNickName(new String(nickname.getBytes("UTF-8"),"UTF-8"));
				info.setNickName(URLEncoder.encode(nickname));
				info.setOpenId(openId);
				info.setPortrait(headimgurl);
				info.setUserBargainId(userBargainId);
				//logger.info("--openId3--");
				bargainService.insertBargainInfo(info);
				//logger.info("--openId4--");
				BigDecimal nowPrice= ub.getNowPrice().subtract(info.getMoney());
				//logger.info("--openId5--");
				if (nowPrice.doubleValue() <0) {
					nowPrice = BigDecimal.ZERO;
				}
				ub.setNowPrice(nowPrice);
				//logger.info("--openId6--");
				bargainService.updateUserBargain(ub);
				//logger.info("--userinfo1--");
				if (ub.getOpenId().equals(openId)) {
					HtmlUtil.writerJson(response, "恭喜你砍掉了"+info.getMoney()+"元");
				}else{
					HtmlUtil.writerJson(response, "您已经帮您的好友砍了"+info.getMoney()+"元");
				}
			}else{
				//logger.info("--userinfo2--");
				if (ub.getOpenId().equals(openId)) {
					HtmlUtil.writerJson(response, "already1");
				}else{
					HtmlUtil.writerJson(response, "already");
				}
			}
		}
	}
}
