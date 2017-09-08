package cn.qhjys.mall.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.XingeService;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.FqThirdPayService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.Sha1Util;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

@Controller
@RequestMapping("/testpay")
public class TestPayController extends Base{
	@Autowired 
	public FqUserInfoService fqUserInfoService;
	@Autowired 
	public FqSellerStatementService fqSellerStatementService;
	@Autowired 
	public FqThirdPayService fqThirdPayService;
	
	@Autowired 
	public SellerService sellerService;
	@Autowired 
	public FqPushInfoService fqPushInfoService;
	String APPID = "2016071401617934";
	String APP_PRIVATE_KEY="MIIEwAIBADANBgkqhkiG9w0BAQEFAASCBKowggSmAgEAAoIBAQCfXxxwNf6pcUeyDxrTO1aicUqCKDs5hv7/1Ufk2Qo+eC+rymrUuYW4g0en3Xpk1+0BuuMDfPgODkTAB6YIGeMpITlJ8Yw+SPHkl0u/yoVfOFoXkD8C5DgbFLuOzefWuPU7NvFyPHwJjYooukcqfTzZX3XfCqgVT/Es707jyaVpeCnC13kR/NktkrtkPnzEgbViCjYJD6dYxYBg2d92T1PRyzkwrE/icvYkroHs6ZygUS8u3p1DRsRg01v/FxbwU0b/yFIeEUNOzJi4E6DpiI8YupyApidaCvzdZowyuNsxFFNAoRsYP8U40G+zO1aIPNQQFnjM/KIh28DJT9CYdQRNAgMBAAECggEBAJquPXPLdDgQ8n0nm+mYliVjv+VEFvdmxuiXoQfEf+TR0bLy6208A0VqZYnPtJNb33TmZp9kE33Q63lV8xe5yHiK/od+IZfyRJSRI7wqW/hnQUnNftrXlnzySnf38SS+r4WhdgZiHiFHdujAo4Hhu8VemZC5TxogVPw/6Fm9xQXvSnE3U0tALBkHxD+sufiGAH84ccWFqrQgDF18l2QZ5IRy3lzgEy2+FYxEHdesU912tcK8oayTepACv8Ek0tzZIrWqRXJ06CULBbbIgipGrVaCyeKQj8edqtpwB6kVjsp/Za8Swh+FOXLTD5lzJ/J2alo5iv4rmhZZ1bAZSmXljQECgYEA0j0ufXF0nAwfyKGgwZ4QeJ/LZNWeoI57iv9ZIczfKtoQQPbPOxUMar0aCEMOyBmMjJ3CZ6AKpM5H67fB669FAqR7mBGhCHHbNuSUYmrp4zshdJFKzkqP0RAS8jC/s3Utsddv98eF5tuaHb6HbvkJFrrooSeFghvmWpCkcjDEFPUCgYEAwg+Fe8L4IHAn19h5X7BgEnZ7R5Dpii4GV6gbhoFY8X+jIePnQV8/s5PHsUj29NAimw8e1PoMzAGzRHmKup8esHrM/Oc1fePJxLqNJkVm6Edl1QFxGpCiaNj4aSC7mDsWUFgwpnIFRUQlBT50mWTmzRMYP84zURV+eo/tjmHU2vkCgYEAtNkKP8Az1fQb9yMJvOOyOYEDnlaf4G8QWO30hz3LjvoL10Y31xPxHXRTi13bvkcz/tXKIAesgD4t4detSYb4byB8bMBlbHU8ZAjWl55uWRhJXBOLbM74zZbtJUbH8KBQ0VWILHWGeqLW/NUjfVNd/R6E8/OiRdyoF6mqu8u1MG0CgYEAkG7O8Xr+A96VuO9z/oUeK/+k53Hyb5arv8ftnKFemndd6hZegugqWDRwFwHwU+spJnENiIQGzDujuh4f46AUTlNHiB9pT6YfAN27ujVDTzP8lZl6HcRyLEj4Mf5bwQSRKgFJTEhmpdP/HbULQTO5F/7TOIH27a1qsju3Gha0PrkCgYEA0FxJfFIgIcsl1ylb3bgbuFZV4w1GS/FD/htrbSMGuhKwPV2JE1kJlpo2H16EkY48poryhbtN832TV3eBvCyumUQ0TXzGOgW12Oi0mbssQcjyW3aJozyqbsLBVwkifQwDG+jQG4tqRf5iFpvWXtq8ieD4ZrzEBAMYWuD3K1mSyYo=";
	String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwAEFLczikziyq1w1Kb+66CIdbdzrnXXhqc9GGEKDZEIK/0zVqYETIl6/ncg+fXTHHFEQehxJVkTgtElO+gNEGdR4gNmARcM340SXbqxkMiBNeGWSW6EAgamOAspgvcbvA3h88uqQEtU3OktvcXuuvpFddmi/KGPBcx5zJFqcka/UttTQ/BvuKHFbOIuxmbfVEvVxiIn4i3dhh+rpv5UNV4kxvRIgdflbg8K74hDqGDU7UoAgsi5WZHqXgh/5o4GEgGjRDt+TeANlrkxAbEdAke75vsn/NMMIqwVlqatHRRXJ6JmMPZ7wXiQEZf2/ZbrMpBKzTrzcxKT468Nwpek9iwIDAQAB";
	String CHARSET="UTF-8";
	@RequestMapping("/testaliy")
	public String testaliy(){
		String ENCODED_URL="http://liberty.tunnel.qydev.com/testpay/testaliyback.do";
		return "redirect:https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id="+APPID+"&scope=auth_base&redirect_uri="+ENCODED_URL;
	}
	@RequestMapping("/testaliyback")
	public ModelAndView testaliyback(String auth_code){
		ModelAndView view = new ModelAndView("/test1");
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APPID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); 
		AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
		request.setCode(auth_code);
		request.setGrantType("authorization_code");
		try {
		    AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
		    System.out.println(oauthTokenResponse.getAccessToken());
		    System.out.println(oauthTokenResponse.getUserId());
		} catch (AlipayApiException e) {
		    //处理异常
		    e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping("/sellerstatement")
	@ResponseBody
	public String sellerstatement() throws Exception{
		return ""+fqSellerStatementService.updateSellerStatementAndAutoWithdrawByQuartz();
	}
	@RequestMapping("/utpo")
	@ResponseBody
	public String updatethirdpayopenid(){
		return ""+fqUserInfoService.updateThirdPayByOpenid();
	}
	
	private SellerInfo verifyUser(HttpServletResponse response, AppResult result, Long sellerId) throws Exception{
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("商户编号错误！");
			this.logger.warn("商户编号错误！");
			HtmlUtil.writerJson(response, result);
			return null;
		}
		SellerInfo seller = sellerService.getSellerById(sellerId);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			this.logger.warn("商户不存在！");
			HtmlUtil.writerJson(response, result);
			return null;
		}
		return seller;
	}
	
	@RequestMapping("/toPayOrder")
	@ResponseBody
	public AppResult toPayOrder(HttpServletResponse response, HttpServletRequest request,Long sellerId,BigDecimal money,String authCode,Integer type) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		if (type == null || (type != 1 && type != 2)) {
			result.setFlag(1);
			result.setReason("支付方式错误");
			return result;
		}
		if (StringUtils.isEmpty(authCode)||authCode.length() != 18) {
			result.setFlag(1);
			result.setReason("付款码错误");
			return result;
		}
		if (money == null || money.compareTo(BigDecimal.ZERO)<1) {
			result.setFlag(1);
			result.setReason("支付金额错误");
			return result;
		}
		FqThirdPay thirdPay = null;
		if (type == 1) {
			thirdPay = fqThirdPayService.insertPayOrderByWxPay(sellerId,money, authCode, request, response);
		}else if(type == 2){
//			thirdPay = fqThirdPayService.insertPayOrderByAliPay(sellerId,money, authCode, request, response);
		}else{
			result.setFlag(1);
			result.setReason("付款码错误");
			return result;
		}
		if (thirdPay==null) {
			result.setFlag(1);
			result.setReason("支付失败");
		}else{
			result.setFlag(0);
			result.setReason("支付成功");
			FqPushInfo fqPushInfo = new FqPushInfo();
			fqPushInfo.setContent("");
			fqPushInfo.setPushTime(new Date());
			fqPushInfo.setSellerId(thirdPay.getSellerId());
			fqPushInfo.setTitle("你已收到付款"+thirdPay.getMoney().toString()+"元");
			fqPushInfo.setType(1);
		    int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
			Map<String,Object> custom = new HashMap<String,Object>(); 
			custom.put("payTime",thirdPay.getPayTime());
			custom.put("orderNo",thirdPay.getOrderNo());
			custom.put("thirdOrderNo",thirdPay.getThirdOrderNo());
			custom.put("money",thirdPay.getMoney());
			 MessageIOS messageIos = new MessageIOS();
			 messageIos.setExpireTime(60);
			 messageIos.setAlert("你已收到付款"+thirdPay.getMoney().toString()+"元");
			 messageIos.setBadge(1);
			 messageIos.setSound("beep.wav");
			 messageIos.setCustom(custom);
			 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD);
			 Message message = new Message();
			 message.setExpireTime(60);
			 message.setTitle("支付成功");
			 message.setContent("你已收到付款"+thirdPay.getMoney().toString()+"元");
			 message.setType(Message.TYPE_NOTIFICATION);
			 message.setCustom(custom);
			result.setData((JSONObject) JSON.toJSON(thirdPay));
			XingeService.xinge.pushSingleAccount(0, thirdPay.getSellerId()+"", message);
		}
		return result;
	}

	@RequestMapping("/pay")
	public ModelAndView testpay() throws JDOMException, IOException{
		ModelAndView view = new ModelAndView("/test");
		String MCH_ID="101510001886";
		String PAY_KEY="a5282789f91f4ef6c772f69bca1a44c6";
		String PAY_URL="https://pay.swiftpass.cn/pay/gateway";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		packageParams.put("service", "pay.weixin.native");
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("out_trade_no", UUID.randomUUID().toString().replace("-", ""));
		packageParams.put("body", "测试支付");
		packageParams.put("total_fee","100" );
		packageParams.put("mch_create_ip", "192.168.1.1");
		packageParams.put("notify_url", ConstantsConfigurer.getProperty("web_url")+"/testpay/paysuccess.do");
		packageParams.put("nonce_str", formatter.format(new Date())+BaseUtil.numRandom(10));
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		String result = HttpClientUtil.httpsRequest(PAY_URL, "POST", requestXML);
		Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
		view.addObject("img", notifyParamsMap.get("code_img_url"));
		return view;
	}
	@RequestMapping("/topay")
	public ModelAndView topay(){
		ModelAndView view = new ModelAndView("/test1");
		return view;
	}
	@RequestMapping("/gzhpay")
	@ResponseBody
	public JSONObject gzhpay() throws JDOMException, IOException{
		Map<String,String> resultMap = new HashMap<String,String>();
		JSONObject json = new JSONObject();
//		String resultCode = SystemConstant.SUCCESS;
		String resultCode = "0000";
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
//		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_NOTIFYURLYF);
		String notifyUrl = "http://liberty.tunnel.qydev.com/wxpay/callback/wftpay.do";
		
//		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.WFT_MCH_ID);
//		String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_KEY);
		String MCH_ID="101510001886";
		String PAY_KEY="a5282789f91f4ef6c772f69bca1a44c6";
		 String noncestr = Sha1Util.getNonceStr();
		 int fee = 100;
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service","pay.weixin.jspay" );
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("is_raw", "1");
		packageParams.put("out_trade_no", UUID.randomUUID().toString().replace("-", ""));
		packageParams.put("body", "测试-公众号支付");
		packageParams.put("sub_openid", "ob8WbwWgH77yhLfZIeNMrY_SJC4A");
		
		packageParams.put("total_fee", String.valueOf(fee));
		packageParams.put("mch_create_ip","192.168.1.1");
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("nonce_str", noncestr);
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		logger.info("requestXML------"+requestXML);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		logger.info("result------"+result);
		Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
		
		if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
			String jsonString = notifyParamsMap.get("pay_info");
			
			resultMap.put("errcode", "0000");
			resultMap.put("jsonstr", jsonString);
		}else{
			resultMap.put("errcode", "80000");
		}
		
		resultCode = resultMap.get("errcode");
		
		if(SystemConstant.SUCCESS.equals(resultCode)){
			json = JSON.parseObject(resultMap.get("jsonstr"));
			json.put("code","0");
		}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
			json.put("code","40007");
		}else if(SystemConstant.ERROR.equals(resultCode)){
			json.put("code","99999");
		}else if("80000".equals(resultCode)){
			json.put("code","80000");
		}
		return json;
	}
	public String createSign(SortedMap<String, String> packageParams,String key) {
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
}
