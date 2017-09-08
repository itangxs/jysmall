package cn.qhjys.mall.controller.weixin;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.alipay.util.AlipayNotify;
import cn.qhjys.mall.common.BaseController;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.service.MsPayService;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.WxPayService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.weixin.util.MessageUtil;
import cn.qhjys.mall.weixin.util.SystemConstant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/wechatPay")
public class WxPayController extends BaseController {
	
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private RebateOrderService rebateOrderService;
	@Autowired
	private StoreRebateService storeRebateService;

	
	/**
	 * 微信统一支付接口封装
	 * @param request 
	 * @param response
	 * @param openId 用户openId 
	 * @param sellerId 卖家id
	 * @param storeName 卖家店名
	 * @param rebateId 打折id
	 * @param storeId  店铺id
	 * @param needPay 需要支付的金额
	 * @param realPay 实际支付金额
	 * @param totamt 总的输入金额
	 * @param noDiscount 不参与优惠的金额
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wxUnionPay")
	@ResponseBody
	public JSONObject wxUnionPay(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
			throws Exception {
		JSONObject json = new JSONObject();
//		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		
		
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
		Map<String,Object> resultMap=null;
		
		resultMap = wxPayService.insertOrderAndGetWxPars(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, realPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
		resultCode = resultMap.get("errcode").toString();
		
		if(SystemConstant.SUCCESS.equals(resultCode)){
			 json = JSON.parseObject(JSON.toJSONString(resultMap));
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
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
	
	@RequestMapping("/wftUnionPay")
	@ResponseBody
	public JSONObject wftUnionPay(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl)
					throws Exception {
		JSONObject json = new JSONObject();
//		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetWft(URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, realPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
				json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
	@RequestMapping("/gdUnionPay")
	@ResponseBody
	public JSONObject gdUnionPay(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl)
					throws Exception {
		JSONObject json = new JSONObject();
//		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetGd(URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
				json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
	
	/**
	 * 
	 * 原生支付宝支付 
	 *
	 */
	@RequestMapping("/toPayOrderAlipay")
	@ResponseBody
	public JSONObject toPayOrderAlipay(String openId, long sellerId,
							String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
							BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl){
		
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap =wxPayService.insertOrderAndGetAlipay(openId,sellerId,storeName,rebate,rebateId,needPay,storeId,realPay,totamt,noDiscount,nickname,headimgurl);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				 json = JSON.parseObject(JSON.toJSONString(resultMap));
				 json.put("code","0");
			}else{
				json.put("code",resultCode);
			}
		}catch(Exception e){
			log.error("构造阿里订单支付数据 error", e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	
	/**
	 * 
	 * 浦发银行的支付宝支付
	 */
	@RequestMapping("/toPayOrderAlipayPF")
	@ResponseBody
	public JSONObject toPayOrderAlipayPF(String openId, long sellerId,
							String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
							BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
							HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetAlipayPF(openId,sellerId,storeName,rebate,rebateId,needPay,storeId,realPay,totamt,noDiscount,nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json.put("tradeNO", resultMap.get("tradeNO"));
				json.put("orderId", resultMap.get("orderId"));
				 json.put("code","0");
			}else{
				json.put("code",resultCode);
			}
		}catch(Exception e){
			log.error("构造浦发阿里订单支付数据 error", e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	/**
	 * 
	 * 兴业银行的支付宝支付
	 */
	@RequestMapping("/toPayOrderAlipayXY")
	@ResponseBody
	public JSONObject toPayOrderAlipayXY(String openId, long sellerId,
							String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
							BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
							HttpServletResponse response){
		log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付宝支付 start");
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetAlipayXY(openId,sellerId,storeName,rebate,rebateId,needPay,storeId,realPay,totamt,noDiscount,nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json.put("tradeNo", resultMap.get("tradeNO"));
				json.put("orderId", resultMap.get("orderId"));
				json.put("orderNo", resultMap.get("orderNo"));
				 json.put("code","0");
			}else{
				json.put("code",resultCode);
			}
		}catch(Exception e){
			log.error("构造兴业阿里订单支付数据 error", e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	/**
	 * 
	 * 兴业银行的支付宝支付
	 */
	@RequestMapping("/toPayOrderXYEQAlipay")
	@ResponseBody
	public JSONObject toPayOrderXYEQAlipay(String openId, long sellerId,
							String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
							BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
							HttpServletResponse response){
		log.info(">>>>>>>>>>>>>>>>>>>兴业支付宝二清【金钥匙科技】 start");
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetXYEQAlipay(openId,sellerId,storeName,rebate,rebateId,needPay,storeId,realPay,totamt,noDiscount,nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json.put("tradeNo", resultMap.get("tradeNO"));
				json.put("orderId", resultMap.get("orderId"));
				json.put("orderNo", resultMap.get("orderNo"));
				 json.put("code","0");
			}else{
				json.put("code",resultCode);
			}
		}catch(Exception e){
			log.error("构造兴业阿里订单支付数据 error", e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	
	@RequestMapping("/checkNotifyXYEQAlipay")
	public void checkNotifyXYEQAlipay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付回调 start");
		//解析回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request); 
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyXYEQAlipay(notifyParamsMap);
			if (a == 1) {
				log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付回调 success");
				response.getWriter().print("success");
			}else{
				log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付回调 fail");
				response.getWriter().print("fail");
			}
		}
	}
	/**
	 * 
	 * 光大银行的支付宝支付
	 */
	@RequestMapping("/toPayOrderAlipayGD")
	@ResponseBody
	public JSONObject toPayOrderAlipayGD(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response){
		
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetAlipayGD(openId,sellerId,storeName,rebate,rebateId,needPay,storeId,realPay,totamt,noDiscount,nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json.put("tradeNO", resultMap.get("tradeNO"));
				json.put("orderId", resultMap.get("orderId"));
				json.put("code","0");
			}else{
				json.put("code",resultCode);
			}
		}catch(Exception e){
			log.error("构造光大阿里订单支付数据 error", e);
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	/**
	 * 浦发银行支付宝支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyAlipayPF")
	public void checkNotifyAlipayPF(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//解析回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyAlipayPF(notifyParamsMap);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	/**
	 * 兴业银行支付宝支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyAlipayXY")
	public void checkNotifyAlipayXY(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付回调 start");
		//解析回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyAlipayXY(notifyParamsMap);
			if (a == 1) {
				log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付回调 success");
				response.getWriter().print("success");
			}else{
				log.info(">>>>>>>>>>>>>>>>>>>进入兴业支付回调 fail");
				response.getWriter().print("fail");
			}
		}
	}
	
	/**
	 * 光大银行支付宝支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyAlipayGD")
	public void checkNotifyAlipayGD(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//解析回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyAlipayGD(notifyParamsMap);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	
	/**
	 * 
	 * 浦发银行的微信支付
	 * 
	 * couponId 为卡券id
	 */
	@RequestMapping("/toPayOrderWXPF")
	@ResponseBody
	public JSONObject toPayOrderWeiXinPF(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetWXPF(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
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
	 * 
	 * 兴业银行的微信支付
	 * 
	 * couponId 为卡券id
	 */
	@RequestMapping("/toPayOrderWXXY")
	@ResponseBody
	public JSONObject toPayOrderWXXY(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		log.info(">>>>>>>>>>>>>>>>>>>进入兴业微信支付 start");
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetWXXY(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
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
	
	@RequestMapping("/toPayOrderSZPFWX")
	@ResponseBody
	public JSONObject toPayOrderSZPFWX(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetSZPFWX(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
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
			e.printStackTrace();
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
	
	@RequestMapping("/toPayOrderXYEQWX")
	@ResponseBody
	public JSONObject toPayOrderXYEQWX(HttpServletRequest request,
			HttpServletResponse response,HttpSession session, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		log.info(">>>>>>>>>>>>>>>>>>>>>>>兴业银行微信支付【金钥匙科技二清】 START!");
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap = wxPayService.insertOrderAndGetXYEQWX(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultCode = resultMap.get("errcode").toString();
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
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
			e.printStackTrace();
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
		
	}
	
	@RequestMapping("/toPayOrderQQPF")
	@ResponseBody
	public JSONObject toPayOrderQQPF(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,Long couponId)
					throws Exception {
		JSONObject json = new JSONObject();
		
		//TODO 后面添加一个校验价格是否正确的方法 暂时不做 处理逻辑 先生成订单，然后调取微信支付统一接口，返回数据给js调用
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
//			resultMap = wxPayService.insertOrderAndGetQQPF(couponId,URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, needPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			resultMap = wxPayService.insertOrderAndGetQQPF(openId,sellerId,storeName,rebate,rebateId,needPay,storeId,realPay,totamt,noDiscount,nickname,headimgurl,request,response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(resultMap.get("jsonstr").toString());
				json.put("orderId", resultMap.get("orderId"));
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
	 * 浦发银行微信支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyWXPF")
	public void checkNotifyWXPF(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyWXPF(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	/**
	 * 兴业银行微信支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyWXXY")
	public void checkNotifyWXXY(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyWXXY(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	/**
	 * 浦发银行微信支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyWXSZPF")
	public void checkNotifyWXSZPF(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String param = request.getQueryString();
		String url = request.getRequestURI()+(StringUtils.isEmpty(param)?"":"?"+param);
		//解析微信回调信息
		Map<String, String> notifyParamsMap = new TreeMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                notifyParamsMap.put(en, value);
            }
        }
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyWXSZPF(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("SUCCESS");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	
	/**
	 * 兴业银行金钥匙科技二清回调
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyXYEQWX")
	public void checkNotifyXYEQWX(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>兴业银行微信支付 回调【金钥匙科技二清】 START!");
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyXYEQWX(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	
	
	
	
	/**
	 * QQ 钱包支付
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toPayOrderQpay")
	@ResponseBody
	public JSONObject toPayOrderCQPay(HttpServletRequest request,
			HttpServletResponse response, String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl){

		JSONObject json = new JSONObject();
		try{
			Map<String,String> resultMap =  wxPayService.insertOrderAndGetCQPay(URLEncoder.encode(nickname),headimgurl,openId, sellerId, storeName, rebateId, realPay, storeId, realPay, totamt, noDiscount, rebate, request, response);
			
			String return_code = resultMap.get("return_code");
			String result_code	= resultMap.get("result_code");   
			if(return_code!=null&&return_code.equals("SUCCESS")&&result_code!=null&&result_code.equals("SUCCESS")){
				 json.put("prepay_id", resultMap.get("prepay_id"));
				 json.put("orderId", resultMap.get("orderId"));
				 json.put("code","0");
			}else{
				json.put("code","80000");
		    }
		}catch(Exception e){
			log.error("error", e);
			json.put("code","99999");
			return json;
		}
		return json;
	}
	/**
	 * QQ 钱包支付结果回调通知
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyCQQ")
	public void checkNotifyCQQ(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//解析QQ钱包支付回调信息
		Map<String, String> notifyParamsMap = MessageUtil.parseXml(request);  

		if(notifyParamsMap != null){
			boolean flag = wxPayService.updateCheckNotifyCQQ(notifyParamsMap);
			if (flag) {
				response.getWriter().print("<xml><return_code>SUCCESS</return_code></xml>");
			}else{
				response.getWriter().print("<xml><return_code>FAIL</return_code></xml>");
			}
		}
		
	}
	
	
	/**
	 * 微信支付回调接口
	 * @return
	 */
	@RequestMapping("/checkNotify")
	public void checkNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			String a = wxPayService.updateCheckNotify(notifyParamsMap,request,response);
			if (a == "1") {
				response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			}else{
				response.getWriter().print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>");
			}
		}
	}
	@RequestMapping("/checkNotifyWft")
	public void checkNotifyWft(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyWft(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	@RequestMapping("/checkNotifyGd")
	public void checkNotifyGd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		
		if(notifyParamsMap != null){
			Integer a = wxPayService.updateCheckNotifyGd(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	
	/**
	 * 支付宝同步通知页面跳转接口
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/alipayRetrun")
	public ModelAndView alipayRetrun(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		ModelAndView view = new ModelAndView("/weixin/fqstore/payerror");
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = params.get("out_trade_no");

		//支付宝交易号
		//String trade_no = params.get("trade_no");
		//交易状态
		String trade_status = params.get("trade_status");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		
		if(verify_result){//验证成功
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				RebateOrder order = rebateOrderService.getRebateOrderByOrderNo(out_trade_no);
				view.setViewName("redirect:/wxMall/orderDetail.do?orderId="+order.getId());
				return view;
			}else{
				view.addObject("msg", "支付失败:支付未完成");
			}
		}else{
			view.addObject("msg", "支付失败:签名验证失败");
		}
		return view;
	}
	
	@Autowired
	private MsPayService msPayService;
	/**
	 * 民生银行统一支付  回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyMSPay")
	public void checkNotifyMSPay(HttpServletRequest request,HttpServletResponse response) throws Exception {
		log.info(">>>>>>>>>>>>>>>>>>>>>>>进入民生银行支付自动回调 START!");
		try {
			String param = request.getQueryString();
			String url = request.getRequestURI()+(StringUtils.isEmpty(param)?"":"?"+param);
			//解析微信回调信息
			Map<String, String> notifyParamsMap = new TreeMap<String, String>();
	        Enumeration<?> temp = request.getParameterNames();
	        if (null != temp) {
	            while (temp.hasMoreElements()) {
	                String en = (String) temp.nextElement();
	                String value = request.getParameter(en);
	                notifyParamsMap.put(en, value);
	            }
	        }
			if(notifyParamsMap != null){
				String a = msPayService.updateCheckNotifyMSPay(notifyParamsMap,request,response);
				if ("SUCCESS".equals(a)) {
					log.info(">>>>>>>>>>>>>>>>>>>>>>>民生银行支付自动回调 success!");
					response.getWriter().print("success");
				}else{
					log.info(">>>>>>>>>>>>>>>>>>>>>>>民生银行支付自动回调 fail!");
					response.getWriter().print("fail");
				}
			}
		} catch (Exception e) {
			log.error(">>>>>>>>>>>>>>>>>>>>>>>民生银行支付自动回调 error!"+ e);
		}
		
	}
	
	/**
	 * 支付宝异步支付通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/alipaynotify")
	public void alipaynotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//交易状态
		String trade_status = params.get("trade_status");

		if(AlipayNotify.verify(params)){//验证成功
			//请在这里加上商户的业务逻辑程序代码
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")){
				wxPayService.updateCheckNotifyAlipay(params);
			}
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			response.getWriter().print("success");	//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			response.getWriter().print("fail");	
		}
	}
	@RequestMapping("/getOrderStatus")
	@ResponseBody
	public String getOrderStatus(Long orderId){
		RebateOrder order = rebateOrderService.getRebateOrder(orderId);
		return order.getStatus()+"";
	}
}
