package cn.qhjys.mall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

public class JsapiTicket {
	protected static final Logger logger = LoggerFactory.getLogger(JsapiTicket.class);
	private static String ticket;
	//过期时间戳
	private static long expiresTime = 0l;
	
	public static boolean isJsapiTicketExpired() {
	    return System.currentTimeMillis() > expiresTime;
	  }
	/**
	 * 获取access_token  若access_token已过期 则先更新access_token
	 * @return
	 */
	public static String getTicket(){
		if (isJsapiTicketExpired()) {
			updateJsapiTicket();
		}
		return ticket;
	}
	/**
	 * 更新access_token 请求微信服务器刷新token和过期时间
	 */
	public static void updateJsapiTicket(){
		 String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
	              + "access_token="+AccessToken.getAccessToken()
	              + "&type=jsapi";
		 JSONObject httpRequest = WeiXinUtil.httpRequest(url, "GET", null);
		 //logger.info("updateJsapiTicket----"+httpRequest);
		 String ticket1 = (String) httpRequest.get("ticket");
		 int expires_in = (int) httpRequest.get("expires_in");
		 ticket = ticket1;
		 expiresTime = System.currentTimeMillis() + (expires_in - 1200) * 1000l;
	}
}
