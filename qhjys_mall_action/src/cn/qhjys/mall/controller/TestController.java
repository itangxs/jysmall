package cn.qhjys.mall.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.szpf.SSLClient;
import cn.qhjys.mall.szpf.SignUtils;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

@Controller
@RequestMapping("/test")
public class TestController extends Base{
	
	
	public static void main(String[] args) throws Exception {
//		DefaultHttpClient httpClient = new SSLClient();
//        HttpPost postMethod = new HttpPost("http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq");
//        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
//        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
//        nvps.add(new BasicNameValuePair("version", "V1.1"));
//        nvps.add(new BasicNameValuePair("transId", "27"));
//        nvps.add(new BasicNameValuePair("merNo", "310440300004895"));
//        nvps.add(new BasicNameValuePair("subMchId", "38814689"));
////      nvps.add(new BasicNameValuePair("subMchId", "36791448"));
//        nvps.add(new BasicNameValuePair("payWay", "WX"));
//       
//        nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
//        try {
//            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//            HttpResponse resp = httpClient.execute(postMethod);
//            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
//            System.out.println("str------"+URLDecoder.decode(str));
//            
//            int statusCode = resp.getStatusLine().getStatusCode();
//            if (200 == statusCode) {
//                boolean signFlag = SignUtils.verferSignData(str);
//                if (!signFlag) {
//                    System.out.println("验签失败");
//                    return;
//                }
//                System.out.println("验签成功");
//                return;
//            }
//            System.out.println("返回错误码:" + statusCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        DefaultHttpClient httpClient = new SSLClient();
//        HttpPost postMethod = new HttpPost("http://www.jysmall.com/wechatPay/checkNotifyWXSZPF.do");
//        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
//        nvps.add(new BasicNameValuePair("bankType","CFT"));
//        nvps.add(new BasicNameValuePair("merNo","310440300004895"));
//        nvps.add(new BasicNameValuePair("orderDate","2017-08-28"));
//        nvps.add(new BasicNameValuePair("orderId","10028537716"));
//        nvps.add(new BasicNameValuePair("orderNo","SP2017082813151500496865"));
//        nvps.add(new BasicNameValuePair("productId","0112"));
//        nvps.add(new BasicNameValuePair("respCode","0000"));
//        nvps.add(new BasicNameValuePair("respDesc","交易成功"));
//        nvps.add(new BasicNameValuePair("signature","os3QzsBLiBH8Oy1kZUwl0C3CjEFBFl7Tq2RnQXziw/s3Rf8xy+jUZdbDYbnEzz1S8nVn7JnE7XpXYtNR6mP12GSveuHp8Wlf8nRhAaQ6tTbjNqGogaciJb7ffuAXHyMU7HI2JJ/SGQS3+NX/ZF8GewwGFjsbnXc9qS+55EPYFJZpGqypoZSLStLwyRhTPT0OY+oB92ZlcdXICHmHZDcIfZeS+I+5qoCldqe7ahrBJHzp/KIkgxoBHAj/nCDwvglO/z+uGc8H9ZN8mzx4nngCt/9Dj6Mpg/gjAb+Be2mk72VV/CvIKUojKUhBOKeQS5IsGmhoUyeDNSyHgHmakRK9Sg=="));
//        nvps.add(new BasicNameValuePair("timeEnd","20170828131523"));
//        nvps.add(new BasicNameValuePair("transAmt","7600"));
//        nvps.add(new BasicNameValuePair("transactionId","4006862001201708288714449408"));
//        
//        try {
//        	postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//        	HttpResponse resp = httpClient.execute(postMethod);
//        	String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
//        	System.out.println("str------"+URLDecoder.decode(str));
//        	
//        	int statusCode = resp.getStatusLine().getStatusCode();
//        	if (200 == statusCode) {
//        		boolean signFlag = SignUtils.verferSignData(str);
//        		if (!signFlag) {
//        			System.out.println("验签失败");
//        			return;
//        		}
//        		System.out.println("验签成功");
//        		return;
//        	}
//        	System.out.println("返回错误码:" + statusCode);
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }
//        
        
        
//        DefaultHttpClient httpClient = new SSLClient();
//        HttpPost postMethod = new HttpPost("http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq");
//        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
//        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
//        nvps.add(new BasicNameValuePair("version", "V1.1"));
//        nvps.add(new BasicNameValuePair("transId", "04"));
//        nvps.add(new BasicNameValuePair("merNo", "310440300004895"));
////      nvps.add(new BasicNameValuePair("subMchId", "38814689"));
//        nvps.add(new BasicNameValuePair("orderDate", "2017-08-29"));
//        nvps.add(new BasicNameValuePair("orderNo", "SP2017082912530900519908"));
//        nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
//        try {
//        	postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//        	HttpResponse resp = httpClient.execute(postMethod);
//        	String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
//        	System.out.println("str------"+URLDecoder.decode(str));
//        	
//        	int statusCode = resp.getStatusLine().getStatusCode();
//        	if (200 == statusCode) {
//        		boolean signFlag = SignUtils.verferSignData(str);
//        		if (!signFlag) {
//        			System.out.println("验签失败");
//        			return;
//        		}
//        		System.out.println("验签成功");
//        		return;
//        	}
//        	System.out.println("返回错误码:" + statusCode);
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }

		
//		Integer pushMin = 100;
//		Integer pushMax = 200;
//		//产生 pushMin 到	pushMax 之间的随机数			
//		Integer pushRandom = getNumBetweenRandom(pushMin,pushMax);
//		BigDecimal pushRandomDec = new BigDecimal(pushRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP); 
//		//卡券推送次数（展示数据）= 实际交易笔数 × 百分比
//		pushRandomDec = new BigDecimal(6).multiply(pushRandomDec);
//		pushRandom = pushRandomDec.intValue();
//		System.out.println(pushRandom);
		
//		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
//		String MCH_ID="105500000535";
//		String PAY_KEY="3affcafd756d52ad4860d600a88d056f";
//		String orderNo = "GA2017071918363200344034";
//		String bank="";
//		if(orderNo.startsWith("PX")||orderNo.startsWith("PI")){ //浦发微信 浦发支付宝
//			 MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
//			 PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
//			 bank="PF";
//		}else if(orderNo.startsWith("GI")||orderNo.startsWith("GW")){ //光大微信
//			 MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
//			 PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
//			 bank="GD";
//		}
		
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("service", "unified.trade.query"); 
//        packageParams.put("mch_id", MCH_ID); 		
//        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
//        packageParams.put("out_trade_no",orderNo); 	
//        String sign = createSignGd(packageParams, PAY_KEY);
//		packageParams.put("sign", sign);
//		String requestXML = XMLUtil.getRequestXml(packageParams);
//	    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
//	    System.out.println(result);
//	    Map<String, String> signMap = new HashMap<String, String>();
//		signMap = XMLUtil.doXMLParse(result);
//		Set es = signMap.entrySet();
//		Iterator it = es.iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String k = (String) entry.getKey();
//			String v = (String) entry.getValue();
//			if (null != v && !"".equals(v) && !"sign".equals(k)
//					&& !"key".equals(k)) {
//				System.out.println(k + "=" + v );
//			}
//		}
		
//		
//		String transUrl="http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq";
//		DefaultHttpClient httpClient = new SSLClient();
//        HttpPost postMethod = new HttpPost(transUrl);
//        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
//        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
//        nvps.add(new BasicNameValuePair("version", "V1.1"));
//        nvps.add(new BasicNameValuePair("transId", "04"));
//        nvps.add(new BasicNameValuePair("merNo", "310440300004895"));
//        nvps.add(new BasicNameValuePair("orderDate", "20170725"));
//        nvps.add(new BasicNameValuePair("orderNo", "SP2017072509385700499616"));
//        nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
//        try {
//            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
//            HttpResponse resp = httpClient.execute(postMethod);
//            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
//            
//            
//            System.out.println("结果："+str);
//            
//            int statusCode = resp.getStatusLine().getStatusCode();
//            if (200 == statusCode) {
//                boolean signFlag = SignUtils.verferSignData(str);
//                if (!signFlag) {
//                    System.out.println("验签失败");
//                    return;
//                }
//                System.out.println("验签成功");
//                return;
//            }
//            System.out.println("返回错误码:" + statusCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
		
//		Calendar c = Calendar.getInstance();
//		System.out.println(c.getTime());
		
//		String JSAPI_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
//		String appId = "wx6e0532e3ad8d3b82";
//		String partner = "1322321401";
//		String pkey = "5dbac43a762046f4d27dee3ce4fdf4e9";
//		
//		String orderNo ="WS2017072412095700414321";
//		SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
//		cxQueryParams.put("appid", appId);
//		cxQueryParams.put("mch_id", partner);
//		cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
////		cxQueryParams.put("out_trade_no", orderNo);
//		cxQueryParams.put("transaction_id", "4002042001201707242386718036");
//		String sign = createSign(cxQueryParams, pkey);
//		cxQueryParams.put("sign", sign);
//		
//		String requestXML = XMLUtil.getRequestXml(cxQueryParams);
//		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
//		Map<String, String> signMap = XMLUtil.doXMLParse(result);
//		System.out.println("定时器补单:"+orderNo+">>>>>>>>>>>>>>>>>>MICRO 查询参数:"+signMap.toString());
		
//		System.out.println(new Date(System.currentTimeMillis()-8*60*60*1000L));
		
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V2.0"));
        nvps.add(new BasicNameValuePair("transId", "04"));
        nvps.add(new BasicNameValuePair("merNo", "850440059441318"));
        nvps.add(new BasicNameValuePair("orderDate", "20170905"));
        nvps.add(new BasicNameValuePair("orderNo", "MI2017090512443500156667"));
		
        Map<String, String> notifyParamsMap = BaseRequest.getSignToSend(nvps);
		
	}
	private static int getNumBetweenRandom(int min,int max){
		double random = Math.random();
		int aaa = (int) (random*(max - min)+min);
		return aaa;
	}
	
	public static String createSign(SortedMap<String, String> packageParams,String key) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();   
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
				.toUpperCase();

		return sign;

	}
//	public static String createSignGd(SortedMap<String, String> packageParams,String key) {
//		StringBuffer sb = new StringBuffer();
//		Set es = packageParams.entrySet();
//		Iterator it = es.iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String k = (String) entry.getKey();
//			String v = (String) entry.getValue();
//			if (null != v && !"".equals(v) && !"sign".equals(k)
//					&& !"key".equals(k)) {
//				sb.append(k + "=" + v + "&");
//			}
//		}
//		sb.append("key=" + key);
//		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
//				.toUpperCase();
//
//		return sign;
//
//	}
}
