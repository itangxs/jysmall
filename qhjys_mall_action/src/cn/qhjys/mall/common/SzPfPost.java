package cn.qhjys.mall.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.qhjys.mall.szpf.SSLClient;
import cn.qhjys.mall.szpf.SignUtils;
import cn.qhjys.mall.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.XMLUtil;

public class SzPfPost {
	private static  String JSAPI_URL = "http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq";
//	private static  String JSAPI_URL = "http://121.201.32.197:9080/payment-gate-web/gateway/api/backTransReq";

	
	public static Map<String, String> postPay(SortedMap<String, String> packageParams) throws Exception{
		Map<String, String> map = new  HashMap<String, String>();
		DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost(JSAPI_URL);
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", UUID.randomUUID().toString().replace("-","")));
        nvps.add(new BasicNameValuePair("version", "V1.1"));
        Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				 nvps.add(new BasicNameValuePair(k, v));
			}
		}
        nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
        try {
            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse resp = httpClient.execute(postMethod);
            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
            map = strToMap(str);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                boolean signFlag = SignUtils.verferSignData(str);
                if (!signFlag) {
                	map.put("signcode", "-1");
                    return map;
                }
                map.put("signcode", "0");
                return map;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("signcode", "-2");
        return map;
	}
	
	public static Map<String, String> strToMap(String str){
		Map<String, String> map = new  HashMap<String, String>();
		String [] params = str.split("&");
		for (int i = 0; i < params.length; i++) {
			if (params[i].contains("formfield=")) {
				map.put("formfield",params[i].replace("formfield=", ""));
			}else{
				String [] b = params[i].split("=");
				if (b.length>1) {
					map.put(b[0], b[1]);
				}
			}
			
		}
		return map;
	}

	
}
