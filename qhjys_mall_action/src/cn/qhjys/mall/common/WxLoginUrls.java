package cn.qhjys.mall.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.impl.RedisServiceImpl;

public class WxLoginUrls {
	public static Map<String, String> map = new HashMap<String, String>();
	
	private static  WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
	static RedisService redisService = (RedisService) wac.getBean(RedisServiceImpl.class);
	public static void addMap(String urlid,String url){
		redisService.setValueByKey(urlid, url);
//		map.put(urlid, url);
	}
	public static String getUrl(String urlid){
//		String url = map.get(urlid);
		String url = (String) redisService.getValueByKey(urlid);
		redisService.delKey(urlid);
//		map.remove(urlid);
		return url;
	}
	public static String getLoginUrl(String urlid){
		return "/wxuser/login/todologin.do?urlid="+urlid;
	}
	public static String getLoginUrlKQ(String urlid){
		return "/wxuser/login/todologinKQ.do?urlid="+urlid;
	}
	public static String getBaseLoginUrl(String urlid){
		return "/wxuser/login/toBaseLogin.do?urlid="+urlid;
	}
	
	public static String getAliBaseLoginUrl(String urlid){
		return "/wxuser/login/toAliBaseLogin.do?urlid="+urlid;
	}
}
