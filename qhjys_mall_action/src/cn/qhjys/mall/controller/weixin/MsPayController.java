package cn.qhjys.mall.controller.weixin;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qhjys.mall.common.BaseController;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.MsPayService;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.MsConstant;
import cn.qhjys.mall.weixin.util.MessageUtil;
import cn.qhjys.mall.weixin.util.SystemConstant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 民生统一支付 （支持以下六种）
 * 
 * 	0105-微信公众号支付
 *  0106-微信刷卡（反扫）
 *  
 * 	0110-支付宝刷卡支付
 *	0115-支付宝支付窗支付（支付宝门店固定二维码支付）
 *
 *  0119-QQ公众号支付 
 *	0120-QQ刷卡（反扫）
 *  
 * 				
 * @author tangxs
 *
 * 2017年7月12日
 */
@Controller
@RequestMapping(value = "/wxMall")
public class MsPayController extends BaseController {

	@Autowired
	private MsPayService msPayService;
	@Autowired
	private RebateOrderService rebateOrderService;
	@Autowired
	private StoreRebateService storeRebateService;
	@Autowired
	private FqOrderService fqOrderService;
	
	private final Log logger = LogFactory.getLog(MsPayController.class);
	/**
	 * 
	 * 民生银行的支付宝支付窗支付
	 */
	@RequestMapping("/toPayOrderAlipayMS")
	@ResponseBody
	public JSONObject toPayOrderAlipayMS(String openId, long sellerId,
							String storeName, String rebate,long rebateId, String needPay,long storeId,
							String realPay, String totamt, String noDiscount,String nickname,String headimgurl,HttpServletRequest request,
							HttpServletResponse response){
		logger.info(">>>>>>>>>>>>>>>>>>进入toPayOrderAlipayMS start");
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = msPayService.insertOrderAndGetAlipayMS(openId,sellerId,storeName,new BigDecimal(rebate),rebateId,new BigDecimal(needPay),storeId,new BigDecimal(realPay),new BigDecimal(totamt),new BigDecimal(noDiscount),nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json.put("tradeNo", resultMap.get("tradeNo"));
				json.put("orderNo", resultMap.get("orderNo"));
				json.put("orderId", resultMap.get("orderId"));
				json.put("code","0");
				logger.info(">>>>>>>>>>>>>>>>>>进入toPayOrderAlipayMS end");
				return json;
			}else{
				json.put("code",resultCode);
			}
		}catch(Exception e){
			log.error("构造民生阿里订单支付数据 error", e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		logger.info(">>>>>>>>>>>>>>>>>>进入toPayOrderAlipayMS end");
		return json;
	}
	
	/**
	 * 
	 * 民生银行的微信支付
	 * 
	 * couponId 为卡券id
	 */
	@RequestMapping("/toPayOrderWXMS")
	@ResponseBody
	public JSONObject toPayOrderWeiXinMS(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, String openId, long sellerId,
			String storeName, String rebate,long rebateId, String needPay,long storeId,
			String realPay, String totamt, String noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		logger.info(">>>>>>>>>>>>>>>>>>进入toPayOrderWXMS start couponId:"+couponId);
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = msPayService.insertOrderAndGetWXMS(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId,new BigDecimal(needPay), storeId, new BigDecimal(realPay), new BigDecimal(totamt), new BigDecimal(noDiscount), new BigDecimal(rebate), request, response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
				json.put("orderNo", resultMap.get("orderNo"));
				json.put("code","0");
				session.removeAttribute(storeId+"cards");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}else if("00009527".equals(resultCode)){
				//直接返回支付成功
				json.put("code","00009527");
				json.put("orderId",resultMap.get("orderId"));
				session.removeAttribute(storeId+"cards");
			}
		}catch(Exception e){
			logger.error(">>>>>>>>>>>>>>>>>>toPayOrderWXMS error"+e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		logger.info(">>>>>>>>>>>>>>>>>>进入toPayOrderWXMS end");
		return json;
		
	}
	
	@RequestMapping("/toPayOrderFqStoreByWXMS")
	@ResponseBody
	public JSONObject toPayOrderByGd(Long orderId,HttpServletRequest request,HttpServletResponse response){
		logger.info(">>>>>>>>>>>>>>>>>>进入toPayOrderFqStoreByWXMS start! orderId"+orderId);
		FqOrder order = fqOrderService.getFqOrder(orderId);
		order.setPayAmount(order.getRebateAmount());
		order.setPayType(1);
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,String> resultMap=null;
			resultMap =msPayService.insertOrderFqStoreByWXMS(order, request, response);
			resultCode = resultMap.get("errcode");
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
				json.put("orderNo", resultMap.get("orderNo"));
				json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}else if("80000".equals(resultCode)){
				json.put("code","80000");
			}
		}catch(Exception e){
			logger.error(">>>>>>>>>>>>>>>>>>进入toPayOrderFqStoreByWXMS error! e "+e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		logger.info(">>>>>>>>>>>>>>>>>>toPayOrderFqStoreByWXMS json! "+orderId);
		return json;
	}
	
	/**
	 * 民生银行QQ公众号支付 
	 * @throws Exception
	 */
	@RequestMapping("/toPayOrderQQMS")
	@ResponseBody
	public JSONObject toPayOrderQQMS(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, String rebate,long rebateId, String needPay,long storeId,
			String realPay, String totamt, String noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		logger.info(">>>>>>>>>>>>>>>>>>toPayOrderQQMS json! ");
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = msPayService.insertOrderAndGetQQMS(openId,sellerId,storeName,new BigDecimal(rebate),rebateId,new BigDecimal(needPay),storeId,new BigDecimal(realPay),new BigDecimal(totamt),new BigDecimal(noDiscount),nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
				json.put("orderNo", resultMap.get("orderNo"));
				json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}else if("00009527".equals(resultCode)){
				//直接返回支付成功
				json.put("code","00009527");
				json.put("orderId",resultMap.get("orderId"));
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
	
	/**
	 * 民生银行统一支付  回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyMSPay")
	public void checkNotifyMSPay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>checkNotifyMSPay start! ");
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			String a = msPayService.updateCheckNotifyMSPay(notifyParamsMap,request,response);
			if ("SUCCESS".equals(a)) {
				logger.info(">>>>>>>>>>>>>>>>>>checkNotifyMSPay success! ");
				response.getWriter().print("success");
			}else{
				logger.info(">>>>>>>>>>>>>>>>>>checkNotifyMSPay fail! ");
				response.getWriter().print("fail");
			}
		}
	}
	
	/**
	 * 民生银行统一支付  	状态查询接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/queryPayStatus")
	@ResponseBody
	public JSONObject queryPayStatus(HttpServletRequest request,HttpServletResponse response,String orderNo) 
			throws Exception {
		
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付----状态查询接口-----：start，orderNo："+orderNo);
		
		JSONObject json = new JSONObject();
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V2.0"));
        nvps.add(new BasicNameValuePair("transId", "04"));
        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
        nvps.add(new BasicNameValuePair("orderNo", orderNo));
		
        Map<String, String> notifyParamsMap = BaseRequest.getSignToSend(nvps);
        
        if(notifyParamsMap == null ){
        	json.put("code", "80000");
        	logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：执行异常 map is null，orderNo："+orderNo);
			return json;
        }
		if(MsConstant.DOING_SUCCEE.equals(notifyParamsMap.get("respCode")) 
        		|| SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode")))
		{
			logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付------状态查询接口-----："+notifyParamsMap.get("origRespDesc")+"，orderNo："+orderNo);
			if(SystemConstant.SUCCESS.equals(notifyParamsMap.get("origRespCode")))
			{
				if(SystemConstant.MS_WX_PAY_TYPE.equals(orderNo.substring(0, 2)))	//MW
				{
					notifyParamsMap.put("productId", "0105");	//  	..0105-微信公众号支付 
				}else if(SystemConstant.MS_WX_STROE_PAY_TYPE.equals(orderNo.substring(0, 2)))	//WM
				{
					notifyParamsMap.put("wxStoreOrder", "yes");
					notifyParamsMap.put("productId", "0105");	//  	..0105-微信公众号支付 
				}
				else if(SystemConstant.MS_ALI_PAY_TYPE.equals(orderNo.substring(0, 2)))	//MA
				{
					notifyParamsMap.put("productId", "0115");	//  	..0115-支付宝支付窗支付
				}
				else if(SystemConstant.MS_QQ_PAY_TYPE.equals(orderNo.substring(0, 2)))	//MQ
				{
					notifyParamsMap.put("productId", "0119");	//  	..0119-QQ公众号支付 
				}
				else
				{
						//	扫码枪反扫回调  	..0120-QQ刷卡（反扫）	0110-支付宝刷卡支付		0106-微信刷卡（反扫）
					notifyParamsMap.put("productId", "0106");
				}
				
				String a = msPayService.updateCheckNotifyMSPay(notifyParamsMap,request,response);
				if ("SUCCESS".equals(a)) 
				{
					json.put("code","0");
					logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：end:"+notifyParamsMap.get("respDesc")+"，orderNo："+orderNo);
					return json;
				}
				else
				{
					json.put("code","80000");
					logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：end:"+notifyParamsMap.get("respDesc")+"，orderNo："+orderNo);
					return json;
				}
			}
			else
			{
				json.put("code", "80000");
				logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：end:"+notifyParamsMap.get("respDesc")+"，orderNo："+orderNo);
				return json;
			}
		}
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：end:"+notifyParamsMap.get("respDesc")+"，orderNo："+orderNo);
		json.put("code","99999");
		return json;
	}
	
	public static void main(String[] args) {
		System.out.println("MW2017072818015300479107".substring(0,2));
		
	}
	/**
	 * 民生收银台收款
	 * @param money
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/msMicroPay")
	@ResponseBody
	public AppResult msMicroPay(HttpServletResponse response, HttpServletRequest request,HttpSession session,BigDecimal money,String authCode) throws Exception{
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行扫码支付");
		AppResult result = new AppResult();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(seller==null){
			result.setFlag(1);
			result.setReason("商户登录信息异常");
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
		Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		
		if ((authCodeBegin>9 && authCodeBegin < 16) || authCodeBegin == 28 || authCodeBegin == 91 ) { 
			//广州民生银行的微信、支付宝、QQ合为一个
			thirdPay = msPayService.insertPayOrderByMSPay(seller,money, authCode, request, response);
		}else{
			result.setFlag(1);
			result.setReason("付款码错误");
			return result;
		}
		
		if (thirdPay==null) {
			result.setFlag(1);
			result.setReason("支付失败");
		}else{
			if (thirdPay.getType() == -1) {
				result.setFlag(1);
				result.setReason("等待用户输入密码,支付以用户支付结果及订单通知为准");
			} else {
				result.setFlag(0);
				result.setReason("支付成功");
				result.setData((JSONObject) JSON.toJSON(thirdPay));
			}
		}
		return result;
	}
	
}

