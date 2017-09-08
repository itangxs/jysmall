package cn.qhjys.mall.util;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import cn.qhjys.mall.common.AccessToken;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class WeixinUtil {
	// 素材上传(POST)
	private static final String UPLOAD_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
	private static final String UPLOAD_IMAGE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload";
	public static final String UPLOAD_FODDER_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadnews";  
	
	public static final String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/mass/send";  
	public static final String PREVIEW_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/mass/preview";
	
	public static final String SEND_TEMP_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send";
	
	public static final String SEND_CYSTOM_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	
	private static HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	private static  CloseableHttpClient httpClient = httpClientBuilder.build();
	
	public static JSONObject sendTempMessage(String data) throws Exception {  
		String posturl = String.format("%s?access_token=%s", SEND_TEMP_MESSAGE, AccessToken.getAccessToken()); 
		String returnStr = SendURLUtil.sendPostReq(posturl, data);  
		if (returnStr != null) {  
			JSONObject json = JSON.parseObject(returnStr);  
			return json;
		}  
		
		return null;  
	}  
	public static JSONObject sendCustomMessage(String data) throws Exception {  
		String posturl = String.format("%s?access_token=%s", SEND_CYSTOM_MESSAGE, AccessToken.getAccessToken()); 
		String returnStr = SendURLUtil.sendPostReq(posturl, data);  
		if (returnStr != null) {  
			JSONObject json = JSON.parseObject(returnStr);  
			return json;
		}  
		
		return null;  
	}  
	
	public static JSONObject uploadFodder(String access_token, String data) throws Exception {  
        String posturl = String.format("%s?access_token=%s", UPLOAD_FODDER_URL, access_token);  
            String returnStr = SendURLUtil.sendPostReq(posturl, data);  
            if (returnStr != null) {  
                JSONObject json = JSON.parseObject(returnStr);  
               return json;
            }  
       
        return null;  
    }  
	public static JSONObject sendMessage(String access_token, String data) throws Exception { 
		String posturl = String.format("%s?access_token=%s", SEND_MESSAGE, access_token); 
		String returnStr = SendURLUtil.sendPostReq(posturl, data);  
		if (returnStr != null) {  
			JSONObject json = JSON.parseObject(returnStr);  
			return json;
		}  
		
		return null;  
	}  
	
	public static JSONObject previewMessage(String access_token, String data) throws Exception { 
		String posturl = String.format("%s?access_token=%s", PREVIEW_MESSAGE, access_token); 
		String returnStr = SendURLUtil.sendPostReq(posturl, data);  
		if (returnStr != null) {  
			JSONObject json = JSON.parseObject(returnStr);  
			return json;
		}  
		
		return null;  
	}  
	
	 /**  
     * 上传多媒体文件  
     *   
     * @param url  
     *            访问url  
     * @param access_token  
     *            access_token  
     * @param type  
     *            文件类型  
     * @param file  
     *            文件对象  
     * @return  
     * @throws Exception  
     */  
    public static JSONObject uploadImage(String access_token, String type, File file) throws Exception {  
  
        String uploadurl = String.format("%s?access_token=%s&type=%s", UPLOAD_IMAGE_URL, access_token, type);  
        HttpPost post = new HttpPost(uploadurl);  
        post.setHeader("User-Agent",  
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");  
        post.setHeader("Host", "file.api.weixin.qq.com");  
        post.setHeader("Connection", "Keep-Alive");  
        post.setHeader("Cache-Control", "no-cache"); 
            if (file != null && file.exists()) {  
                // 对请求的表单域进行填充  
                MultipartEntity reqEntity = new MultipartEntity();  
                reqEntity.addPart("file", new FileBody(file));  
                post.setEntity(reqEntity);  
  
                // ---本地环境设置代理地址，代理端口号，协议类型----  
                // HttpHost proxy = new HttpHost("xxx.com.cn", 80,  
                // "http");  
                // RequestConfig config =  
                // RequestConfig.custom().setProxy(proxy).build();  
                // post.setConfig(config);  
                // -----------------------------------
                HttpResponse res = httpClient.execute(post); 
                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                    HttpEntity entity = res.getEntity();  
                    String responseContent = EntityUtils.toString(entity, "UTF-8");  
                    JSONObject json = JSON.parseObject(responseContent);
                   return json;
                }  
            }  
       
        return null;  
  
    }  
	
	/**
     * 微信服务器素材上传
     * @param file  表单名称media
     * @param token access_token
     * @param type  type只支持四种类型素材(video/image/voice/thumb)
     */
	    public static String uploadImg(String access_token, File file) throws Exception {  
	        String uploadurl = UPLOAD_MEDIA+"?access_token="+access_token;  
	        HttpPost post = new HttpPost(uploadurl);  
	        post.setHeader("User-Agent",  
	                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");  
	        post.setHeader("Host", "file.api.weixin.qq.com");  
	        post.setHeader("Connection", "Keep-Alive");  
	        post.setHeader("Cache-Control", "no-cache");  
	        try {  
	            if (file != null && file.exists()) {  
	                // 对请求的表单域进行填充  
	                MultipartEntity reqEntity = new MultipartEntity();  
	                reqEntity.addPart("file", new FileBody(file));  
	                post.setEntity(reqEntity);  
	  
	                // ---本地环境设置代理地址，代理端口号，协议类型----  
	                // HttpHost proxy = new HttpHost("xxx.com.cn", 80,  
	                // "http");  
	                // RequestConfig config =  
	                // RequestConfig.custom().setProxy(proxy).build();  
	                // post.setConfig(config);  
	                // ------------------------------------  
	  
	                HttpResponse res = httpClient.execute(post);  
	                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
	                    HttpEntity entity = res.getEntity();  
	                    String responseContent = EntityUtils.toString(entity, "UTF-8");  
	                    JSONObject json = JSON.parseObject(responseContent);
	                    if (json.get("url") != null) {  
	                        return (String) json.get("url");
	                    }  
	                }  
	            }  
	        } catch (Exception e) {  
	        }  
	        return null;  
	  
	    }  
	    
	    
}
