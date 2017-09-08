package cn.qhjys.mall.controller.fq;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qhjys.mall.alipay.util.AlipayNotify;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.service.fq.FqWxPayService;
import cn.qhjys.mall.weixin.util.MessageUtil;

@Controller
@RequestMapping("/wxpay/callback")
public class FqWxPayCallBackController extends Base{
	
	@Autowired
	private FqWxPayService fqWxPayService;
	
	
	/**
	 * QQ刷卡付款码支付回调接口
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/qqPay")
	public void checkNotifyQq(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = fqWxPayService.updateQPay(notifyParamsMap, request, response);
			if (a == 1) {
				response.getWriter().print("<xml><return_code>SUCCESS</return_code></xml>");
			}else{
				response.getWriter().print("<xml><return_code>FAIL</return_code></xml>");
			}
		}
	}
	@RequestMapping("/yuding")
	public void checkNotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = fqWxPayService.updateYudingPay(notifyParamsMap, request, response);
			if (a == 1) {
				response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			}else{
				response.getWriter().print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>");
			}
		}
	}
	
	@RequestMapping("/wftpay")
	public void checkNotifywftpay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		logger.info("wftpay----url----"+url);
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		if(notifyParamsMap != null){
			Integer a = fqWxPayService.updateWftPay(notifyParamsMap, request, response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("error");
			}
		}
		
	}
	@RequestMapping("/gdpay")
	public void checkNotifygdpay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		logger.info("wftpay----url----"+url);
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		if(notifyParamsMap != null){
			Integer a = fqWxPayService.updateGdPay(notifyParamsMap, request, response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("error");
			}
		}
		
	}
	@RequestMapping("/pfpay")
	public void checkNotifypfpay(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		logger.info("wftpay----url----"+url);
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		if(notifyParamsMap != null){
			Integer a = fqWxPayService.updatePfPay(notifyParamsMap, request, response);
			if (a == 1) {
				response.getWriter().print("success");
			}else{
				response.getWriter().print("error");
			}
		}
		
	}
	
	@RequestMapping("/weikuan")
	public void checkNotifyWeikuan(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//解析微信回调信息
		Map<String, String> notifyParamsMap =null;  
		notifyParamsMap =MessageUtil.parseXml(request);
		
		if(notifyParamsMap != null){
			Integer a = fqWxPayService.updateWeiKuanPay(notifyParamsMap, request, response);
			if (a == 1) {
				response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
			}else{
				response.getWriter().print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[ERROR]]></return_msg></xml>");
			}
		}
	}
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
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
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
				fqWxPayService.updateAlipay(out_trade_no, trade_no);
				
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			response.getWriter().print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			response.getWriter().print("fail");	
		}
	}
	
}
