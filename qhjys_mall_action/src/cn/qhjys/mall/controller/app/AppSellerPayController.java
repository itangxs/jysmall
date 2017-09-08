package cn.qhjys.mall.controller.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.XingeService;
import cn.qhjys.mall.controller.seller.SellerCashierDeskController;
import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import cn.qhjys.mall.mapper.WeixinUserinfoMapper;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.FqThirdPayService;
import cn.qhjys.mall.service.MsPayService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.MessageUtil;


@Controller
@RequestMapping("/wxMall")
public class AppSellerPayController extends Base{
	
	@Autowired
	private FqThirdPayService fqThirdPayService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private FqPushInfoService fqPushInfoService;
	@Autowired
	private MsPayService msPayService;
	
	
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
		if (seller.getEnabled() != 1) {
			result.setFlag(1);
			result.setReason("商户已停用！");
			this.logger.warn("商户已停用！");
			HtmlUtil.writerJson(response, result);
		}
		return seller;
	}
	
	@RequestMapping("/toPayOrder")
	@ResponseBody
	public AppResult toPayOrder(HttpServletResponse response, HttpServletRequest request,Long sellerId,BigDecimal money,String authCode,Integer type){
		
		logger.info("进入toPayOrder   ==============start :sellerId"+sellerId + ":money"+money + ":type"+type);
		AppResult result = new AppResult();
		try {
			
		
		SellerInfo seller  = verifyUser(response, result, sellerId);
		
		if (seller.getEnabled() != 1) {
			result.setFlag(1);
			result.setReason("商户已停用！");
			this.logger.warn("商户已停用！");
			HtmlUtil.writerJson(response, result);
		}else{
			
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
		
		StoreInfo sinfo = storeService.queryStoreInfoBySeller(seller.getId());
		if(sinfo==null){
			result.setFlag(1);
			result.setReason("门店信息异常");
			return result;
		}
		FqThirdPay thirdPay = null;
		Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));


		
		if ((authCodeBegin.intValue() > 9) && (authCodeBegin.intValue() < 16)) {
	          if (sinfo.getChannelValidation().intValue() == 1)
	            thirdPay = this.msPayService.insertPayOrderByMSPayAPP(seller, money, authCode, request, response);
	          else if (sinfo.getChannelValidation().intValue() == 2)
	            thirdPay = this.fqThirdPayService.insertPayOrderByXYPayAPP(sellerId, money, authCode, request, response);
	          else if (sinfo.getChannelValidation().intValue() == 3){
	        	 
	          }else {
	            thirdPay = this.fqThirdPayService.insertPayOrderByWxPay(sellerId, money, authCode, request, response);
//	            thirdPay = this.fqThirdPayService.insertPayOrderByXYEQPayAPP(sellerId, money, authCode, request, response);
	          }
	        }
	        else if (authCodeBegin.intValue() == 28)
	        {
	          if (sinfo.getChannelValidation().intValue() == 1) {
	            thirdPay = this.msPayService.insertPayOrderByMSPayAPP(seller, money, authCode, request, response);
	          } else if (sinfo.getChannelValidation().intValue() == 2) {
	            thirdPay = this.fqThirdPayService.insertPayOrderByXYPayAPP(sellerId, money, authCode, request, response);
	          }  else if (sinfo.getChannelValidation().intValue() == 3){
	        	  thirdPay = this.fqThirdPayService.insertPayOrderByXYEQPayAPP(sellerId, money, authCode, request, response);
	          }else {
//	            FqMicroOrder order = this.fqThirdPayService.insertFqMicroOrderToAli(seller.getId(), money);
//	            thirdPay = this.fqThirdPayService.insertPayOrderByAliPay(sellerId, money, authCode, order, request, response);
	        	thirdPay = this.fqThirdPayService.insertPayOrderByXYEQPayAPP(sellerId, money, authCode, request, response);
	          }
	        } else if (authCodeBegin.intValue() == 91) {
	          thirdPay = this.fqThirdPayService.insertPayOrderByQqPay(sellerId, money, authCode, request, response);
	        }
	        else
	        {
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
			}else{
				
				result.setFlag(0);
				result.setReason("支付成功");
				result.setData((JSONObject) JSON.toJSON(thirdPay));
				logger.info("进入toPayOrder==============支付成功");
			}
		}
		}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("进入toPayOrder   ==============end");

		return result;
	}
	
	@RequestMapping("/reserseOrder")
	@ResponseBody
	public String reserseOrder(String orderNo) throws JDOMException, IOException{
		return ""+fqThirdPayService.reverseMicroOrder(orderNo);
	}
	
	
	@RequestMapping("/toSavePosOrder")
	@ResponseBody
	public AppResult toSavePOSOrder(HttpServletResponse response, HttpServletRequest request,Long sellerId,BigDecimal money) throws Exception{
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		
		if (money == null || money.compareTo(BigDecimal.ZERO)<1) {
			result.setFlag(1);
			result.setReason("支付金额错误");
			return result;
		}
		String orderNo = fqThirdPayService.insertPosOrder(sellerId,money);
		if (orderNo==null) {
			result.setFlag(1);
			result.setReason("新增订单失败，请重试");
		}else{
			result.setFlag(0);
			result.setReason("保存成功");
			JSONObject obj = new JSONObject();
			obj.put("orderNo", orderNo);
			result.setData(obj);
		}
		
		return result;
	}
	
	
	/**
	 * 旺pos支付 回调通知接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyWangPos")
	public void checkNotifyWangPos(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String,String> notifyParamsMap = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		logger.info(requestParams.toString());
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			notifyParamsMap.put(name, valueStr);
		}
		
		if(notifyParamsMap != null){
			Integer a = fqThirdPayService.updateCheckNotifyWangPos(notifyParamsMap);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
	
	
	/**
	 * 旺pos支付 APP通知接口 （保证能通知到）
	 * 异步接口暂时只能通知到一次
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/checkNotifyWangPosByApp")
	@ResponseBody
	public AppResult checkNotifyWangPosByApp(HttpServletRequest request,HttpServletResponse response,String data,Long sellerId,String sign) throws Exception {
		AppResult result = new AppResult();
		verifyUser(response, result, sellerId);
		
		if (data == null || sign==null) {
			result.setFlag(1);
			result.setReason("数据不能为空");
			return result;
		}
		
		//验证data数据是否被篡改
		String str = data+"&company=jysmall&sellerId="+sellerId;
		String dataSign =  MD5Util.MD5Encode(str, "UTF-8");
		if(!dataSign.endsWith(sign)){
			result.setFlag(1);
			result.setReason("签名错误");
			return result;
		}
		Integer a = fqThirdPayService.updateCheckNotifyWangPosByApp(data,sellerId);
		if (a == 1) {
			result.setFlag(0);
			result.setReason("处理成功");
		}else{
			result.setFlag(1);
			result.setReason("处理失败");
		}
		return result;
	}
	
}
