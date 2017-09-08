package cn.qhjys.mall.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.MyX509TrustManager;
import cn.qhjys.mall.weixin.util.SignUtil;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

/**
 * 
 * 类名称:CrazyServlet 微信核心请求处理类 
 * 类描述:
 * 创建人:JiangXiaoPing
 * 创建时间:2015年9月22日上午11:42:09
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class CrazyServlet extends HttpServlet {
	private static final long serialVersionUID = -5021188348833856475L;
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	        // 微信加密签名  
	        String signature = request.getParameter("signature");  
	        // 时间戳  
	        String timestamp = request.getParameter("timestamp");  
	        // 随机数  
	        String nonce = request.getParameter("nonce");  
	        // 随机字符串  
	        String echostr = request.getParameter("echostr");  
	        PrintWriter out = response.getWriter();  
	        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
	        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
	            out.print(echostr);  
	        }  
	        out.close();
	        out = null;
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	GetWeiXinCode.getCodeRequest();
	        // TODO 消息的接收、处理、响应  
	    	HttpSession session = request.getSession();
	    	request.setCharacterEncoding("utf-8");
	    	response.setCharacterEncoding("utf-8");
	    	PrintWriter out = response.getWriter();
	    	String messageXml = null;
	    	try {
				Map<String, String> map = WeiXinUtil.parseXml(request);
				String fromUserName = map.get("FromUserName");
				String toUserName = map.get("ToUserName");
				String createTime = map.get("CreateTime");
				String msgType = map.get("MsgType");
				String content = map.get("Content");
				String msgid = map.get("MsgId");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				out.close();
			}
	    	
	    }

	    
	    
	    

		 /** 
		     * 发起https请求并获取结果 
		     *  
		     * @param requestUrl 请求地址 
		     * @param requestMethod 请求方式（GET、POST） 
		     * @param outputStr 提交的数据 
		     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
		     */  
		    public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {  
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
		           // jsonObject = JSONObject.fromObject(buffer.toString());  
		        } catch (ConnectException ce) {  
		        } catch (Exception e) {  
		        }  
		       // return jsonObject;  
		        return buffer.toString();
		    } 
}
