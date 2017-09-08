package cn.qhjys.mall.util.ms;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qhjys.mall.weixin.util.SystemConstant;

public class BaseRequest {
	
	public final static Logger logger = LoggerFactory.getLogger(BaseRequest.class);
	
	public static Map<String, String> getSignToSend(List<BasicNameValuePair> nvps) {
		String str = "";
		try {
			TreeMap<String, String> respMap = SignUtils.getTreeMap(nvps);
			logger.info(">>>>>>>>>>>>>>>>>>组装签名串=>" + respMap);
			String signature = SignUtils.getSignData(respMap);
			logger.info(">>>>>>>>>>>>>>>>>>签名参数=>" + signature);
			nvps.add(new BasicNameValuePair("signature", signature));
			
			HttpPost postMethod = new HttpPost(ConfigUtils.getProperty(SystemConstant.MS_PAY_TEST_URL));
			postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			
			DefaultHttpClient httpClient = new SSLClient();
			HttpResponse resp = httpClient.execute(postMethod);
			
			str = EntityUtils.toString(resp.getEntity(), "UTF-8");
			logger.info(">>>>>>>>>>>>>>>>>>响应数据=>" + str);
			int statusCode = resp.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				TreeMap<String, String> reqMap = SignUtils.strToTreeMap(str);
				if (!SignUtils.verferSignData(reqMap)) {
					System.out.println("验签失败");
					logger.info(">>>>>>>>>>>>>>>>>>验签失败>>>>>>>>>>>>>>");
					// 验签失败,交易未知流程处理
				} else {
					System.out.println(">>>>>>>>响应信息=>" + reqMap.get("respCode") + "-" + reqMap.get("respDesc"));
					logger.info(">>>>>>>>响应信息=>" + reqMap.get("respCode") + "-" + reqMap.get("respDesc"));
					// 0000-执行成功流程，接口文档中标注的中间状态处理码执行未知流程，其他错误码执行失败流程
				}
				return reqMap;
			} else {
				System.out.println("响应错误码=>" + statusCode);
				logger.info(">>>>>>>>响应错误码=>" + statusCode);
				// 非200错误码,交易未知流程处理
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 请求异常,交易未知流程处理
			return null;
		}
	}
	
}
