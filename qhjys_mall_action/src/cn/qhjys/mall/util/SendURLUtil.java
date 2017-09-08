package cn.qhjys.mall.util;

import java.io.BufferedReader;  
import java.io.ByteArrayOutputStream;  
import java.io.DataOutputStream;  
import java.io.InputStreamReader;  
import java.net.HttpURLConnection;  
import java.net.InetSocketAddress;  
import java.net.Proxy;  
import java.net.SocketAddress;  
import java.net.URL;  
  
import org.apache.commons.io.IOUtils;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  

public class SendURLUtil {
	private static URL sUrl = null;  
    private static HttpURLConnection httpURlConnection = null;  
    private static final Logger logger = LoggerFactory.getLogger(SendURLUtil.class);  
  
    /*  
     * 发送url地址并获取响应消息  
     */  
    public static String sendURL(String url, String encode) {  
        BufferedReader reader = null;  
        String result = null;  
        // ByteArrayOutputStream baos = null;  
        try {  
            // 发送请求  
            sUrl = new URL(url);  
  
            // SocketAddress addr = new InetSocketAddress("proxy2.xxx.com.cn",  
            // 80);  
            // Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);  
            // httpURlConnection = (HttpURLConnection)  
            // sUrl.openConnection(typeProxy);  
  
            httpURlConnection = (HttpURLConnection) sUrl.openConnection();  
            httpURlConnection.setRequestMethod("POST");  
            httpURlConnection.setDoOutput(true);  
            httpURlConnection.setDoInput(true);  
            // 设置连接主机超时  
            httpURlConnection.setConnectTimeout(15000);  
            // 设置从主机读取数据超时  
            httpURlConnection.setReadTimeout(120000);  
            httpURlConnection.setUseCaches(false);  
  
            // 设定传送的内容类型是可序列化的java对象  
            httpURlConnection.setRequestProperty("Content-type", "text/xml");  
  
            httpURlConnection.connect();  
  
            // 读取返回内容  
            StringBuffer buffer = new StringBuffer();  
            reader = new BufferedReader(new InputStreamReader(httpURlConnection.getInputStream(), encode));  
            String temp;  
            while ((temp = reader.readLine()) != null) {  
                buffer.append(temp);  
            }  
            result = buffer.toString();  
            // int i = -1;  
            // baos = new ByteArrayOutputStream();  
            // while ((i = reader.read()) != -1) {  
            // baos.write(i);  
            // }  
            // result = baos.toString();  
        } catch (Exception e) {  
            logger.error("请求异常:", e);  
            return null;  
        } finally {  
            try {  
                if (httpURlConnection != null) {  
                    httpURlConnection.disconnect();  
                }  
            } catch (Exception e) {  
                logger.error("錯誤:", e);  
            }  
            // 关闭输入输出流  
            IOUtils.closeQuietly(reader);  
            // IOUtils.closeQuietly(baos);  
  
        }  
        return result;  
    }  
  
    /*  
     * 发送post请求获取响应消息  
     */  
    public static String sendPostReq(String url, String content) throws Exception {  
        String result = null;  
        DataOutputStream outStrm = null;  
        BufferedReader reader = null;  
        ByteArrayOutputStream baos = null;  
        try {  
            // 发送请求  
            sUrl = new URL(url);  
  
            // 本地环境需要设置代理  
            SocketAddress addr = new InetSocketAddress("www.jysmall.com", 80);  
            Proxy typeProxy = new Proxy(Proxy.Type.HTTP, addr);  
            httpURlConnection = (HttpURLConnection) sUrl.openConnection(typeProxy);  
  
             httpURlConnection = (HttpURLConnection) sUrl.openConnection();  
  
            // 设置请求方式，缺省为get方式  
            httpURlConnection.setRequestMethod("POST");  
  
            // 设置是否向httpUrlConnection输出，post请求需要设为true,默认情况下是false;  
            httpURlConnection.setDoOutput(true);  
  
            // 设置是否从httpUrlConnection读入，默认情况下是true;  
            httpURlConnection.setDoInput(true);  
  
            // 设置连接主机超时  
            httpURlConnection.setConnectTimeout(15000);  
  
            // 设置从主机读取数据超时  
            httpURlConnection.setReadTimeout(120000);  
            // Post 请求不能使用缓存  
            httpURlConnection.setUseCaches(false);  
  
            // 设定传送的内容类型是可序列化的java对象  
            httpURlConnection.setRequestProperty("Content-type", "text/xml");  
  
            // 连接，配置必须要在connect之前完成，  
            httpURlConnection.connect();  
  
            // 此处getOutputStream会隐含的进行connect,所以在开发中不调用上述的connect()也可以。  
            outStrm = new DataOutputStream(httpURlConnection.getOutputStream());  
  
            // 向对象输出流写出数据，这些数据将存到内存缓冲区中  
            outStrm.write(content.toString().getBytes("UTF-8"));  
  
            // 刷新对象输出流，将任何字节都写入潜在的流中（此处为ObjectOutputStream）  
            outStrm.flush();  
  
            // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,  
            // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器  
            outStrm.close();  
            // 读取返回内容  
  
            // inStrm = httpURlConnection.getInputStream();  
            reader = new BufferedReader(new InputStreamReader(httpURlConnection.getInputStream(), "utf-8"));  
            // <===注意，实际发送请求的代码段就在这里  
            // reader = new BufferedReader(new  
            // InputStreamReader(httpURlConnection.getInputStream()));  
            int i = -1;  
            baos = new ByteArrayOutputStream();  
            while ((i = reader.read()) != -1) {  
                baos.write(i);  
            }  
            result = baos.toString();  
  
        } catch (Exception e) {  
            logger.error("请求异常:" + e.getMessage(), e);  
            throw e;  
        } finally {  
            try {  
                if (httpURlConnection != null) {  
                    httpURlConnection.disconnect();  
                }  
            } catch (Exception e) {  
                logger.error("錯誤:", e);  
            }  
            // 关闭输入输出流  
            IOUtils.closeQuietly(outStrm);  
            IOUtils.closeQuietly(reader);  
            IOUtils.closeQuietly(baos);  
        }  
        return result;  
    }  
  
}  
