package cn.qhjys.mall.util;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * httpClient 工具类
 * @author liuliwei
 * 2016-03-07
 */
public class HttpClientUtil
{
 
	public static void main(String[] args) {
		
		String rs= sendHttpGetMsg("http://api.duoshuo.com/log/list.json?short_name=98vip&secret=04ecc619e992ad03909e83caba91f059");
		
		JSONObject json=JSONObject.fromObject(rs);
		
		
		JSONArray array= json.getJSONArray("response");
		if(array.size()>0)
		{
			for (int i = 0; i < array.size(); i++) {
				json = array.getJSONObject(i).getJSONObject("meta");
			}
		}
		
		
	}
	
    
   /**
    * 新版http发送方式
    */
    @SuppressWarnings("finally")
	public static String sendHttpMsg(String url, String msg, Map<String, String> headerMap)
    {
        String cotent = "";
        HttpClient base = new DefaultHttpClient();
        try
        {
            HttpContext context = new BasicHttpContext();
            context.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());
            HttpPost post = new HttpPost(url);
            if (null != headerMap && !headerMap.isEmpty())
            {
                Set<String> keySet = headerMap.keySet();
                for (String key : keySet)
                {
                    post.addHeader(key, headerMap.get(key));
                }
            }
            //请求超时
            base.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
            
            post.setHeader("Content-Type", "text/xml; charset=utf-8");
            if(msg!=null && msg!="")
            {
            	HttpEntity stringEntity = new StringEntity(msg, "UTF-8");
            	post.setEntity(stringEntity);
            }

            HttpResponse response = base.execute(post, context);
            HttpEntity entity = response.getEntity();
            try
            {
                if (null != entity)
                {
                    cotent = EntityUtils.toString(entity);
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            base.getConnectionManager().shutdown();
            return cotent;
        }
    }
    
    /**
     * 新版http发送方式 get
     */
     @SuppressWarnings("finally")
	public static String sendHttpGetMsg(String url)
     {
         String cotent = "";
         HttpClient base = new DefaultHttpClient();
         try
         {
             HttpContext context = new BasicHttpContext();
             context.setAttribute(ClientContext.COOKIE_STORE, new BasicCookieStore());
             HttpGet get = new HttpGet(url);
           
             //请求超时
             base.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000); 
             
             get.setHeader("Content-Type", "text/xml; charset=utf-8");
    

             HttpResponse response = base.execute(get, context);
             HttpEntity entity = response.getEntity();
             try
             {
                 if (null != entity)
                 {
                     cotent = EntityUtils.toString(entity);
                 }
             }
             catch (IOException ex)
             {
                 ex.printStackTrace();
             }
         }
         catch (Exception ex)
         {
             ex.printStackTrace();
         }
         finally
         {
             base.getConnectionManager().shutdown();
             return cotent;
         }
     }

    
    /**
     * 老版http发送方式
     */
    @SuppressWarnings("finally")
    public static String sendHttpMsg2(String url  , Map<String, String> params)
    {
        String cotent = "";
        org.apache.commons.httpclient.HttpClient httpclient = new org.apache.commons.httpclient.HttpClient();  
        try
        {
            httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);//设置连接超时时间为60000秒 
            
            httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");//指定传送字符集为utf-8格式
            
            PostMethod post = new PostMethod(url);
            
            if(params!=null)
            {
            	org.apache.commons.httpclient.NameValuePair[] nameValuePairs = new org.apache.commons.httpclient.NameValuePair[params.size()];
            	int index = 0;
            	
            	for (Entry<String, String> param : params.entrySet()) 
            	{
            		nameValuePairs[index] = new org.apache.commons.httpclient.NameValuePair(param.getKey(), param.getValue());
            		index++;
            	}
            	post.setRequestBody(nameValuePairs);
            }
            
            int i= httpclient.executeMethod(post);  
            
            byte[] responseBody = post.getResponseBody();
            String ssoVerifyString = new String(responseBody,"UTF-8");
            cotent= ssoVerifyString;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return cotent;
    }
    
    
    
    /** 
     * 发送http请求取得返回的输入流 
     *  
     * @param requestUrl 请求地址 
     * @return InputStream 
     */  
    public static InputStream getHttpInputStream(String requestUrl) throws Exception {  
        InputStream inputStream = null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setRequestMethod("GET");  
            httpUrlConn.connect();  
            // 获得返回的输入流  
            inputStream = httpUrlConn.getInputStream();  
        } catch (Exception e) {  
            e.printStackTrace();
            throw e;
        }  
        return inputStream;  
    }  

   
}