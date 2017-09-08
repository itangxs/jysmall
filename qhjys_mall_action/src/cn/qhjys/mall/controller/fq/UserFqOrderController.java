package cn.qhjys.mall.controller.fq;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.qhjys.mall.alipay.util.AlipayNotify;
import cn.qhjys.mall.controller.weixin.MsPayController;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.service.fq.FqRedpackService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.service.fq.FqWxPayService;
import cn.qhjys.mall.weixin.util.SystemConstant;

@Controller
@RequestMapping("/user/fqorder")
public class UserFqOrderController {
	
	@Autowired
	private FqOrderService fqOrderService;
	@Autowired
	private FqStoreService fqStoreService;
	@Autowired
	private FqWxPayService fqWxPayService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	@Autowired
	private FqRedpackService fqRedpackService;
	@Autowired
	private StoreService storeService;
	private final Log logger = LogFactory.getLog(UserFqOrderController.class);
	@RequestMapping("/confrimOrder")
	@ResponseBody
	public JSONObject confrimOrder(HttpSession session,String ids,String nums,Long storeId,Long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception{
		//FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		JSONObject json = new JSONObject();
		Long orderId= fqOrderService.insertFqOrderAndDetail(ids, nums,storeId, userId,deskNo,username,phoneNum,peopleNum);
		if (orderId >=1L) {
			json.put("code", "success");
			json.put("orderId", orderId);
		}else{
			json.put("code", "error");
			json.put("errorcode", orderId);
		}
		return json;
	}
	
	
	@RequestMapping("/confrimOrderGd")
	@ResponseBody
	public JSONObject confrimOrderGd(HttpSession session,String ids,String nums,Long storeId,Long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception{
		//FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>生成点餐订单start ! storeId :"+storeId);
		JSONObject json = new JSONObject();
		FqStore store = fqStoreService.getFqStoreById(storeId);
		StoreInfo store1 = storeService.queryStoreInfoBySeller(store.getSellerId());
		Long orderId = 0l;
		if(store1 == null || store1.getChannelValidation() != 1){
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入【非民生】通道: "+orderId);
			orderId= fqOrderService.insertFqOrderAndDetailGd(ids, nums,storeId, userId,deskNo,username,phoneNum,peopleNum);
		}else{
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>进入【民生】通道: "+orderId);
			//如果开通了民生通道  则生成民生账单
			orderId= fqOrderService.insertFqOrderAndDetailMs(SystemConstant.MS_WX_STROE_PAY_TYPE,ids, nums,storeId, userId,deskNo,username,phoneNum,peopleNum);
		}
		if (orderId >=1L) {
			json.put("code", "success");
			json.put("orderId", orderId);
		}else{
			json.put("code", "error");
			json.put("errorcode", orderId);
		}
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>orderId: "+orderId);
		return json;
	}
	@RequestMapping("/confrimOrderPF")
	@ResponseBody
	public JSONObject confrimOrderPF(HttpSession session,String ids,String nums,Long storeId,Long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception{
		//FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		JSONObject json = new JSONObject();
		Long orderId= fqOrderService.insertFqOrderAndDetailPf(ids, nums,storeId, userId,deskNo,username,phoneNum,peopleNum);
		if (orderId >=1L) {
			json.put("code", "success");
			json.put("orderId", orderId);
		}else{
			json.put("code", "error");
			json.put("errorcode", orderId);
		}
		return json;
	}
	@RequestMapping("/confrimOrderPf")
	@ResponseBody
	public JSONObject confrimOrderPf(HttpSession session,String ids,String nums,Long storeId,Long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception{
		//FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
		JSONObject json = new JSONObject();
		Long orderId= fqOrderService.insertFqOrderAndDetailPf(ids, nums,storeId, userId,deskNo,username,phoneNum,peopleNum);
		if (orderId >=1L) {
			json.put("code", "success");
			json.put("orderId", orderId);
		}else{
			json.put("code", "error");
			json.put("errorcode", orderId);
		}
		return json;
	}
	
	@RequestMapping("/toOrderDetail")
	public ModelAndView toOrderDetail(Long orderId){
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>【支付】点餐订单start ! orderId :"+orderId);
		FqOrder order = fqOrderService.getFqOrder(orderId);
		List<FqOrderDetail> details = fqOrderService.queryOrderDetailByOrderId(order.getId());
		if (order.getStoreId().equals(3156L)) {
			ModelAndView view = new ModelAndView("/wxstore/payorder_fq");
			Integer totleNum = 0;
			for (int i = 0; i < details.size(); i++) {
				FqOrderDetail detail = details.get(0);
				totleNum += detail.getQuantity();
			}
			FqStore store = fqStoreService.getFqStoreById(order.getStoreId());
			StoreInfo store1 = storeService.queryStoreInfoBySeller(store.getSellerId());
			view.addObject("order", order);
			view.addObject("details", details);
			view.addObject("totleNum", totleNum);
			view.addObject("store", store);
			view.addObject("channelVali", store1.getChannelValidation());
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>【支付】点餐订单  store1 :"+store1.getId());
			return view;
		}
		ModelAndView view = new ModelAndView("/wxstore/payorder");
		Integer totleNum = 0;
		for (int i = 0; i < details.size(); i++) {
			FqOrderDetail detail = details.get(0);
			totleNum += detail.getQuantity();
		}
		FqStore store = fqStoreService.getFqStoreById(order.getStoreId());
		StoreInfo store1 = storeService.queryStoreInfoBySeller(store.getSellerId());
		view.addObject("order", order);
		view.addObject("details", details);
		view.addObject("totleNum", totleNum);
		view.addObject("store", store);
		view.addObject("channelVali", store1.getChannelValidation());
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>【支付】点餐订单  store1 :"+store1.getId());
		return view;
	}
	@RequestMapping("/toOrderDetailWk")
	public ModelAndView toOrderDetailWk(Long orderId){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		List<FqOrderDetail> details = fqOrderService.queryOrderDetailByOrderId(order.getId());
		ModelAndView view = new ModelAndView("/wxstore/payorderwx");
		Integer totleNum = 0;
		for (int i = 0; i < details.size(); i++) {
			FqOrderDetail detail = details.get(0);
			totleNum += detail.getQuantity();
		}
		view.addObject("order", order);
		view.addObject("details", details);
		view.addObject("totleNum", totleNum); 
		view.addObject("weikuan", order.getRebateAmount().subtract(order.getPayAmount()));
		return view;
	}
	
	@RequestMapping("/toPayOrder")
	@ResponseBody
	public JSONObject toPayOrder(Long orderId,HttpServletRequest request,HttpServletResponse response){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		order.setPayAmount(order.getRebateAmount());
		order.setPayType(1);
		JSONObject json = new JSONObject(); 
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap =fqWxPayService.updateAndPayOrder(order, request, response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				 json = JSON.parseObject(JSON.toJSONString(resultMap));
				 json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}else if("80000".equals(resultCode)){
				json.put("code","80000");
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	@RequestMapping("/toPayOrderByWft")
	@ResponseBody
	public JSONObject toPayOrderByWft(Long orderId,HttpServletRequest request,HttpServletResponse response){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		order.setPayAmount(order.getRebateAmount());
		order.setPayType(1);
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,String> resultMap=null;
			resultMap =fqWxPayService.updateAndPayOrderByWft(order, request, response);
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
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		System.out.println("json----"+json);
		return json;
	}
	@RequestMapping("/toPayOrderByGd")
	@ResponseBody
	public JSONObject toPayOrderByGd(Long orderId,HttpServletRequest request,HttpServletResponse response){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		order.setPayAmount(order.getRebateAmount());
		order.setPayType(1);
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,String> resultMap=null;
			resultMap =fqWxPayService.updateAndPayOrderByGd(order, request, response);
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
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		System.out.println("json----"+json);
		return json;
	}
	@RequestMapping("/toPayOrderByPf")
	@ResponseBody
	public JSONObject toPayOrderByPf(Long orderId,HttpServletRequest request,HttpServletResponse response){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		order.setPayAmount(order.getRebateAmount());
		order.setPayType(1);
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,String> resultMap=null;
			resultMap =fqWxPayService.updateAndPayOrderByPf(order, request, response);
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
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		System.out.println("json----"+json);
		return json;
	}
	@RequestMapping("/toAlipay")
	public ModelAndView toAlipay(Long orderId){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		FqUserInfo user = fqUserInfoService.getFqUserInfoById(order.getUserId());
		ModelAndView view = new ModelAndView("/wxstore/payorderalipay");
		view.addObject("order",order);
		view.addObject("user",user);
		return view;
	}
	
	@RequestMapping("/toPayOrderAlipay")
	@ResponseBody
	public JSONObject toPayOrderAlipay(Long orderId,HttpServletRequest request,HttpServletResponse response){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		order.setPayAmount(order.getRebateAmount());
		order.setPayType(0);
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap =fqWxPayService.updateAndPayOrderByAlipay(order);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				 json = JSON.parseObject(JSON.toJSONString(resultMap));
				 json.put("code","0");
			}else if("80000".equals(resultCode)){
				json.put("code","80000");
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	@RequestMapping("/toPayOrderWk")
	@ResponseBody
	public JSONObject toPayOrderWk(Long orderId,HttpServletRequest request,HttpServletResponse response){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		JSONObject json = new JSONObject();
		String resultCode = SystemConstant.SUCCESS;
		try{
			Map<String,Object> resultMap=null;
			resultMap =fqWxPayService.updateAndPayOrderWk(order, request, response);
			resultCode = resultMap.get("errcode").toString();
			
			if(SystemConstant.SUCCESS.equals(resultCode)){
				json = JSON.parseObject(JSON.toJSONString(resultMap));
				json.put("code","0");
			}else if(SystemConstant.DATA_DIFF.equals(resultCode)){
				json.put("code","40007");
			}else if(SystemConstant.ERROR.equals(resultCode)){
				json.put("code","99999");
			}else if("80000".equals(resultCode)){
				json.put("code","80000");
			}
		}catch(Exception e){
			resultCode =SystemConstant.ERROR;
			json.put("code","99999");
			return json;
		}
		return json;
	}
	
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
//			valueStr = URLDecoder.decode(valueStr);
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

//		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		if(verify_result){//验证成功
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				FqOrder order = fqOrderService.getFqOrderByOrderNo(out_trade_no);
				FqUserInfo user = fqUserInfoService.getFqUserInfoById(order.getUserId());
				view.setViewName("redirect:/wxMall/fqorderDetail.do?id="+order.getId()+"&openId="+user.getOpenId());
				return view;
			}else{
				view.addObject("msg", "支付失败:支付未完成");
			}
		}else{
			view.addObject("msg", "支付失败:签名验证失败");
		}
		return view;
	}
	
	@RequestMapping("/getOrderStatus")
	@ResponseBody
	public String getOrderStatus(Long orderId){
		FqOrder order = fqOrderService.getFqOrder(orderId);
		return order.getStatus()+"";
	}
	
	@RequestMapping("/fqorderRedpack")
	public ModelAndView orderRedpack(HttpServletRequest request,Long id,String openid)
			throws Exception {
		ModelAndView view = new ModelAndView("/wxstore/redpack");
		FqRedpackRecord fpr = fqRedpackService.insertFqRedpackRecordByfqorder(id);
		if (fpr == null) {
			view.setViewName("redirect:/wxMall/fqorderDetail.do?id="+id+"openId="+openid);
		}else{
			view.addObject("orderId", id);
			view.addObject("fpr", fpr);
			
		}
		return view;
	}
	@ResponseBody
	@RequestMapping("/doOrderRedpack")
	public String doOrderRepack(Long rprid){
		int a = fqRedpackService.updateRedpackRecordStatusByDo(rprid);
		return a+"";
	}
}
