package cn.qhjys.mall.weixin.common;


  
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.qhjys.mall.weixin.reqsq.Article;
import cn.qhjys.mall.weixin.reqsq.Menu;
import cn.qhjys.mall.weixin.reqsq.MusicMessage;
import cn.qhjys.mall.weixin.reqsq.NewsMessage;
import cn.qhjys.mall.weixin.reqsq.TextMessage;
import cn.qhjys.mall.weixin.util.MyX509TrustManager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class WeiXinUtil {
	private static Logger log = LoggerFactory.getLogger(WeiXinUtil.class);  
	
// 获取access_token的接口地址（GET） 限200（次/天）  
	 public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	 
	// 菜单创建（POST） 限100（次/天）  
	 public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN"; 
	// 菜单创建（POST） 限100（次/天）  
	public static String ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 获取用户列表  
	public static String get_users_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	// 获取用户详情 
	public static String get_user_d = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	
	/** 
     * 返回消息类型：文本 
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 返回消息类型：音乐 
     */  
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  
  
    /** 
     * 返回消息类型：图文 
     */  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
  
    /** 
     * 请求消息类型：文本 
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 请求消息类型：图片 
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
  
    /** 
     * 请求消息类型：链接 
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
    /** 
     * 请求消息类型：地理位置 
     */  
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";  
  
    /** 
     * 请求消息类型：音频 
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  
  
    /** 
     * 请求消息类型：推送 
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    /** 
     * 事件类型：subscribe(订阅) 
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
  
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
    public static final String EVENT_TYPE_CLICK = "CLICK";  
  
    /** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
  
        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();  
  
        // 遍历所有子节点  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  
  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
  
        return map;  
    } 
    
    
    /** 
     * 扩展xstream，使其支持CDATA块 
     *  
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        @Override
		public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                @Override
				@SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                @Override
				protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });
  
    /** 
     * 文本消息对象转换成xml 
     *  
     * @param textMessage 文本消息对象 
     * @return xml 
     */  
    public static String textMessageToXml(TextMessage textMessage) {  
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }  
  
    /** 
     * 音乐消息对象转换成xml 
     *  
     * @param musicMessage 音乐消息对象 
     * @return xml 
     */  
    public static String musicMessageToXml(MusicMessage musicMessage) {  
        xstream.alias("xml", musicMessage.getClass());  
        return xstream.toXML(musicMessage);  
    }  
  
    /** 
     * 图文消息对象转换成xml 
     *  
     * @param newsMessage 图文消息对象 
     * @return xml 
     */  
    public static String newsMessageToXml(NewsMessage newsMessage) {  
        xstream.alias("xml", newsMessage.getClass());  
        xstream.alias("item", new Article().getClass());  
        return xstream.toXML(newsMessage);  
    } 
    
    /** 
	  * 获取ticket 
	  *  
	  * @param appid 凭证 
	  * @param appsecret 密钥 
	  * @return 
	  */  
	 public static String getAccessToken(String ACCESS_TOKEN) {  
	     String ticket = "";  
	   
	     String requestUrl = ticket_url.replace("ACCESS_TOKEN", ACCESS_TOKEN);  
	     JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	     // 如果请求成功  
	     if (null != jsonObject) {  
	         try {  
	        	 ticket = jsonObject.getString("ticket");  
	         } catch (JSONException e) {  
	        	 ticket = null;  
	             // 获取token失败  
	             log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	         }  
	     }  
	     return ticket;  
	 } 
    
    /** 
	  * 获取access_token 
	  *  
	  * @param appid 凭证 
	  * @param appsecret 密钥 
	  * @return 
	  */  
	 public static AccessToken getAccessToken(String appid, String appsecret) {  
	     AccessToken accessToken = null;  
	   
	     String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
	     JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	     // 如果请求成功  
	     if (null != jsonObject) {  
	         try {  
	             accessToken = new AccessToken();  
	             accessToken.setToken(jsonObject.getString("access_token"));  
	             accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
	         } catch (JSONException e) {  
	             accessToken = null;  
	             // 获取token失败  
	             log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	         }  
	     }  
	     return accessToken;  
	 } 
	 
	 /** 
	  * 获取用户 列表
	  *  
	  * @return 
	  */  
	 public static WeiXinUserList getUser(String ACCESS_TOKEN,String NEXT_OPENID) {  
	     String requestUrl = get_users_url.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("NEXT_OPENID", NEXT_OPENID);  
	     JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
	     // 如果请求成功  
	     WeiXinUserList weiXinUser = new WeiXinUserList(); 
	     if (null != jsonObject) {  
	    	  log.error("获取用户列表 返回值:"+jsonObject);  
	         try {  
	        	 weiXinUser = new WeiXinUserList(); 
	        	/* if(!StringUtils.isEmpty((String) jsonObject.get("total"))){*/
		        	 weiXinUser.setTotal((int)jsonObject.get("total"));
		        	 weiXinUser.setCount((int)jsonObject.get("count"));
		        	 weiXinUser.setNext_openid(jsonObject.get("next_openid")+"");
		        	 JSONObject lit = 	(JSONObject) jsonObject.get("data");
		        	 JSONArray users =  lit.getJSONArray("openid");
		        	 List<WeiXinUser> list= new ArrayList();
		        	 for (int i = 0; i < users.size(); i++) {
		        		 WeiXinUser user = new WeiXinUser();
		        		 user.setOpenid(users.get(i)+"");
		        		 list.add(user);
					}
		        	 weiXinUser.setData(list);
	        	/* }else{
	        		 weiXinUser=null;
	        	 } */
	         } catch (JSONException e) {  
	        	 weiXinUser = null;  
	             // 获取token失败  
	             log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	         }  
	     }
	     return weiXinUser;   
	 } 
	 
	 /** 
	  * 获取用户详情
	  *  
	  * @return 
	  */  
	 public static WeiXinUser getUserd(String ACCESS_TOKEN,String OPENID) {  
	     String requestUrl = get_user_d.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("OPENID", OPENID);  
	     JSONObject jsonObject = httpRequest(requestUrl, "GET", null);   
	     // 如果请求成功  
	     WeiXinUser WeiXinUser = new WeiXinUser(); 
	     if (null != jsonObject) {  
	   	  log.error("获取用户详情返回值:"+jsonObject);  
	         try {  
	        	 WeiXinUser.setSubscribe((int)jsonObject.get("subscribe"));
	        	 WeiXinUser.setOpenid((String)jsonObject.get("openid"));
	        	 WeiXinUser.setNickname((String)jsonObject.get("nickname"));
	        	 WeiXinUser.setSex((int)jsonObject.get("sex"));
	        	 WeiXinUser.setCity((String)jsonObject.get("city"));
	        	 WeiXinUser.setCountry((String)jsonObject.get("country"));
	        	 WeiXinUser.setProvince((String)jsonObject.get("province"));
	        	 WeiXinUser.setLanguage((String)jsonObject.get("language"));
	        	 WeiXinUser.setHeadimgurl((String)jsonObject.get("headimgurl"));
	        	 WeiXinUser.setSubscribe_time(Long.valueOf(jsonObject.get("subscribe_time")+""));
	        	 WeiXinUser.setUnionid((String)jsonObject.get("unionid"));
	        	 WeiXinUser.setRemark((String)jsonObject.get("remark"));
	        	 WeiXinUser.setGroupid((int)jsonObject.get("groupid"));
	         } catch (JSONException e) {  
	        	 WeiXinUser = null;  
	             // 获取token失败  
	             log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	         }  
	     }
	     return WeiXinUser;   
	 } 
	 
	 /** 
	     * 发起https请求并获取结果 
	     *  
	     * @param requestUrl 请求地址 
	     * @param requestMethod 请求方式（GET、POST） 
	     * @param outputStr 提交的数据 
	     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
	     */  
	    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
	        JSONObject jsonObject = null;  
	        StringBuffer buffer = new StringBuffer();  
	        try {  
	            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
	            TrustManager[] tm = { new MyX509TrustManager() };  
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	            sslContext.init(null, tm, new java.security.SecureRandom());  
	            // 从上述SSLContext对象中得到SSLSocketFactory对象  
	            SSLSocketFactory ssf = sslContext.getSocketFactory();  
	  
	            URL url = new URL(requestUrl);  
	            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
	            httpUrlConn.setSSLSocketFactory(ssf);  
	  
	            httpUrlConn.setDoOutput(true);  
	            httpUrlConn.setDoInput(true);  
	            httpUrlConn.setUseCaches(false);  
	            // 设置请求方式（GET/POST）  
	            httpUrlConn.setRequestMethod(requestMethod);  
	  
	            if ("GET".equalsIgnoreCase(requestMethod))  
	                httpUrlConn.connect();  
	  
	            // 当有数据需要提交时  
	            if (null != outputStr) {  
	                OutputStream outputStream = httpUrlConn.getOutputStream();  
	                // 注意编码格式，防止中文乱码  
	                outputStream.write(outputStr.getBytes("UTF-8"));  
	                outputStream.close();  
	            }  
	  
	            // 将返回的输入流转换成字符串  
	            InputStream inputStream = httpUrlConn.getInputStream();  
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	  
	            String str = null;  
	            while ((str = bufferedReader.readLine()) != null) {  
	                buffer.append(str);  
	            }  
	            bufferedReader.close();  
	            inputStreamReader.close();  
	            // 释放资源  
	            inputStream.close();  
	            inputStream = null;  
	            httpUrlConn.disconnect();  
	            jsonObject = JSONObject.fromObject(buffer.toString());  
	        } catch (ConnectException ce) {  
	            log.error("Weixin server connection timed out.");  
	        } catch (Exception e) {  
	            log.error("https request error:{}", e);  
	        }  
	        return jsonObject;  
	    } 
	    
	    
	    /**
	     * 创建菜单
	     * 
	     * @param menu 菜单实例
	     * @param accessToken 有效的access_token
	     * @return 0表示成功，其他值表示失败
	     */
	    public static int createMenu(Menu menu, String accessToken) {
	    	int result = 0;

	    	// 拼装创建菜单的url
	    	String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
	    	// 将菜单对象转换成json字符串
	    	String jsonMenu = JSONObject.fromObject(menu).toString();
	    	// 调用接口创建菜单
	    	JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);

	    	if (null != jsonObject) {
	    		if (0 != jsonObject.getInt("errcode")) {
	    			result = jsonObject.getInt("errcode");
	    			log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
	    		}
	    	}
	    	return result;
	    }

}
