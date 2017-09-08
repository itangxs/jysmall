package cn.qhjys.mall.controller.fq;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qhjys.mall.common.WxLoginUrls;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.SendPushPost;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

@Controller
@RequestMapping("/wxuser/login")
public class UserLoginController {
	
	@Autowired
	private FqUserInfoService fqUserInfoService;
	Logger log = Logger.getLogger(UserLoginController.class);
	
	@RequestMapping("/todologin")
	public String todologin(String urlid){
		String url = ConstantsConfigurer.getProperty("get_wxuserlogin_url")+"?urlid="+urlid;
		String request = GetWeiXinCode.getCodeRequestByUserInfo(ConstantsConfigurer.getProperty("weixin_appid"),url);
		return "redirect:"+request;
	}
	@RequestMapping("/wxuserlogin")
	public String wxuserlogin(HttpSession session,String urlid,String code){
			String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>weixin_appid:"+ConstantsConfigurer.getProperty("weixin_appid"));
			JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
			String openId = (String) httpRequest.get("openid");
			String access_token = (String) httpRequest.get("access_token");
			String currentUserInfourl = GetWeiXinCode.getCurrentUserInfo(access_token,openId);
			JSONObject httpRequest1 = WeiXinUtil.httpRequest(currentUserInfourl, "GET", null);
			String nickname = httpRequest1.optString("nickname");
			String headimgurl = httpRequest1.optString("headimgurl");
			FqUserInfo user = fqUserInfoService.updateFqUserInfo(openId, nickname, headimgurl);
			session.setAttribute(ConstantsConfigurer.getWxUser(), user);
			session.setAttribute("user_openid", openId);
			session.setAttribute("kaquan_openid", openId);
			session.setAttribute("nickname", nickname);
			session.setAttribute("headimgurl", headimgurl);
			return "redirect:"+WxLoginUrls.getUrl(urlid);
		}
	
	@RequestMapping("/toBaseLogin")
	public String toBaseLogin(String urlid){
		String url1 = ConstantsConfigurer.getProperty("get_wxbaselogin_url")+"?urlid="+urlid;
		String request = GetWeiXinCode.getCodeRequestByBase(ConstantsConfigurer.getProperty("weixin_appid"),url1);
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>weixin_appid:"+ConstantsConfigurer.getProperty("weixin_appid"));
		return "redirect:"+request;
	}
	@RequestMapping("/wxbaselogin")
	public String wxbaselogin(HttpSession session,String urlid,String code){
		String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,ConstantsConfigurer.getProperty("weixin_appid"),ConstantsConfigurer.getProperty("weixin_appsecret"));
//		JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
		String result = SendPushPost.sendGet(currentOpenIdurl);
		System.out.println(result);
		JSONObject httpRequest = JSONObject.fromObject(result);  
		 String openId = (String) httpRequest.get("openid");
		 FqUserInfo user = fqUserInfoService.updateFqUserInfo(openId, null, null);
		session.setAttribute("user_openid", openId);
		System.out.println("--shouquanopenId---"+openId);
		return "redirect:"+WxLoginUrls.getUrl(urlid);
	}
	
	
	/**
	 * 支付宝oauth授权
	 * @param urlid
	 * @return
	 */
	@RequestMapping("/toAliBaseLogin")
	public String toAliBaseLogin(String urlid){
		System.out.println("urlid1-----"+urlid);
		String appId = ConstantsConfigurer.getProperty("ali_appid");
		String url =ConstantsConfigurer.getProperty("get_alibaselogin_url");
		String request = "https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm?app_id="+appId+"&scope=auth_base&state="+urlid+"&redirect_uri="+url;
		System.out.println("request-----"+request);
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Alipay_appId:"+appId);
		return "redirect:"+request;
	}
	
	/**
	 * 支付宝静默授权
	 * @param session
	 * @param urlid
	 * @param code
	 * @return
	 * @throws AlipayApiException 
	 */
	@RequestMapping("/aliBaseUserLogin")
	public String aliBaseUserLogin(HttpSession session,String auth_code,String source,String state,String scope) throws AlipayApiException{
		System.out.println("state-----"+state);
		
		try {
			String appId = ConstantsConfigurer.getProperty("ali_appid");
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Alipay_appId:"+appId);
			String ali_app_private_key = ConstantsConfigurer.getProperty("ali_app_private_key");
			String ali_alipay_public_key = ConstantsConfigurer.getProperty("ali_alipay_public_key");
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", appId, ali_app_private_key, "json", "UTF-8", ali_alipay_public_key, "RSA2"); 
			AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
			request.setCode(auth_code);
			request.setGrantType("authorization_code");
		    AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
			session.setAttribute("user_openid", oauthTokenResponse.getUserId());
			
		} catch (AlipayApiException e) {
		    e.printStackTrace();
		}
		System.out.println("state-----"+state);
		 return "redirect:"+WxLoginUrls.getUrl(state);
	}
	
}
