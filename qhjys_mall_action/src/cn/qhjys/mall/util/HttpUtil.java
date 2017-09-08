package cn.qhjys.mall.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpUtil {
	/**
	 * 通过HttpURLConnection模拟post表单提交
	 * 
	 * @param doUrl
	 *            请求连接
	 * @param method
	 *            请求方式
	 * @param params
	 *            请求参数
	 * @return
	 * @throws IOException
	 */
	public static byte[] doSend(String doUrl, String method, Map<String, String> params) throws IOException {
		HttpURLConnection urlConn = null;
		if (method.equalsIgnoreCase("GET") && params != null) {
			StringBuffer param = new StringBuffer();
			int i = 0;
			for (String key : params.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			doUrl += param;
		}
		URL url = new URL(doUrl);
		urlConn = (HttpURLConnection) url.openConnection();
		// 设定请求的方法为,默认是GET
		urlConn.setRequestMethod(method);
		// 设置是否向httpUrlConnection输出
		urlConn.setDoOutput(true);
		// 设置是否从httpUrlConnection读入
		urlConn.setDoInput(true);
		// 请求是否使用缓存
		urlConn.setUseCaches(false);
		// 设定传送的内容类型是可序列化的java对象
		urlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		if (method.equalsIgnoreCase("POST") && params != null) {
			StringBuffer param = new StringBuffer();
			for (String key : params.keySet()) {
				param.append("&");
				param.append(key).append("=").append(params.get(key)); 
			}
			urlConn.getOutputStream().write(param.toString().getBytes());
			urlConn.getOutputStream().flush();
			urlConn.getOutputStream().close();
		}
		// 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端
		InputStream is = urlConn.getInputStream();
		byte[] data = readInputStream(is);
		if (urlConn != null)
			urlConn.disconnect();
		return data;
	}

	/**
	 * 从输入流中读取数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream is) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = null;
		int len = 0;
		try {
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			data = outStream.toByteArray();// 网页的二进制数据
			outStream.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 获取网络和运营商信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public static String[] getIPAdress() throws IOException {
		String url = "http://api.liqwei.com/location/";
		byte[] data = doSend(url, "GET", null);
		String[] ip = new String(data, "GBK").split(",");
		return ip;
	}
}