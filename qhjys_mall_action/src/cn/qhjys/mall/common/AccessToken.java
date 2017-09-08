package cn.qhjys.mall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import net.sf.json.JSONObject;
import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.impl.RedisServiceImpl;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.WeiXinUtil;
/**
 * 微信请求access_token 
 * 过期自动请求微信服务器刷新access_token
 * @author Administrator
 *
 */
public class AccessToken {
	protected static final Logger logger = LoggerFactory.getLogger(AccessToken.class);
	//微信access_token
	private static String accessToken;
	//旺pos机的access_token
	private static String wPosAccessToken;
	
	//过期时间戳
	private static long expiresTime = 0l;
	//旺pos机 过期时间戳
	private static long wPosExpiresTime = 0l;
	private static  WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
	static RedisService redisService = (RedisService) wac.getBean(RedisServiceImpl.class);
	/**
	 * 判断access_token是否过期
	 * @return
	 * @throws Exception 
	 */
	public static boolean isAccessTokenExpired(){
		Object a = redisService.getValueByKey("expiresTime");
		expiresTime = StringUtils.isEmpty(a)?0l:(Long)a;
	    return System.currentTimeMillis() > expiresTime;
	  }
	
	
	/**
	 * 判断旺pos机 access_token是否过期
	 * @return
	 * @throws Exception 
	 */
	public static boolean isWPosAccessTokenExpired(){
		Object a = redisService.getValueByKey("wPosExpiresTime");
		wPosExpiresTime = StringUtils.isEmpty(a)?0l:(Long)a;
	    return System.currentTimeMillis() > wPosExpiresTime;
	}
	
	
	/**
	 * 获取access_token  若access_token已过期 则先更新access_token
	 * @return
	 * @throws Exception 
	 */
	public static String getAccessToken(){
		if (isAccessTokenExpired()) {
			updateAccessToken();
			logger.info("updatetoken----");
		}
		return (String) redisService.getValueByKey("accessToken");
		//		return "KFe5Jru9LRWHqGKhA9jZKlWzrNBJIfGYlMU_oNz7hWKNCZeBf9godaOADXnki9o8w8DDBBXplQD-w4ah-4nuRwkxiLrV-k5KbI4WUbt-VseARagKY3hX3mQfzbSMa4cBRZQfCHAUCT";
	}
	
	/**
	 * 获取旺Pos机的access_token
	 * @return
	 * @throws Exception 
	 */
	public static String getWPosAccessToken(){
		if (isWPosAccessTokenExpired()) {
			updateWPosAccessToken();
			logger.info("updatetoken----");
		}
		return (String) redisService.getValueByKey("wPosAccessToken");
	}
	
	
	/**
	 * 更新access_token 请求微信服务器刷新token和过期时间
	 * @throws Exception 
	 */
	public static void updateAccessToken(){
		 String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
//	              + "&appid=" + "wx605bd41598bdbedb"
//	              + "&secret=" + "b129eef7f4acaab3a5a41e533a896b7c";
		 + "&appid=" + ConstantsConfigurer.getProperty("weixin_appid")
         + "&secret=" + ConstantsConfigurer.getProperty("weixin_appsecret");
		 JSONObject httpRequest = WeiXinUtil.httpRequest(url, "GET", null);
		 //logger.info("updateAccessToken----"+httpRequest);
		 String access_token = (String) httpRequest.get("access_token");
		 int expires_in = (int) httpRequest.get("expires_in");
		 accessToken = access_token;
		 expiresTime = System.currentTimeMillis() + (expires_in - 1200) * 1000l;
		 redisService.setValueByKey("accessToken", accessToken);
		 redisService.setValueByKey("expiresTime", expiresTime);
	}
	
	
	/**
	 *  更新旺POS机的access_token
	 * @throws Exception 
	 */
	public static void updateWPosAccessToken(){
		 String url = "http://open.wangpos.com/wopengateway/api/accesstoken/get"
		 		+ "?appid=" + ConstantsConfigurer.getProperty("wang_pos_appid")
		 		+ "&secret=" + ConstantsConfigurer.getProperty("wang_pos_secret");
		 String rs = HttpClientUtil.sendHttpGetMsg(url);
		 com.alibaba.fastjson.JSONObject obj = com.alibaba.fastjson.JSONObject.parseObject(rs);
		 obj = obj.getJSONObject("data");
		 String access_token = obj.get("access_token").toString();
		 wPosExpiresTime = (long) obj.get("expire_time");
		 wPosAccessToken = access_token;
		 redisService.setValueByKey("wPosAccessToken", wPosAccessToken);
		 redisService.setValueByKey("wPosExpiresTime", wPosExpiresTime);
	}
	
}
