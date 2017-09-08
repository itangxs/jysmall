package cn.qhjys.mall.quartz;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;
import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.XingeService;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.util.SendPushPost;
import cn.qhjys.mall.util.WeixinUtil;

/** 
* @author  刘洋 
* @version 时间：2017年3月27日 下午3:13:42 
* 类说明 
* 
* 2:app推送时 		必须有 sellerId    money
* 3:微信商家推送时 	必须有 sellerId    money  tradeType  transactionId
* 4:微信用户推送时 	必须有 sellerId    money  openId    payTime
* 1:必须全部有
* 否则推送失败
*/
public class PushPayInfoTask implements Runnable {

	private final Log logger = LogFactory.getLog(PushPayInfoTask.class);
	private  WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
	/**
	 * 
	 * 推送类型数组
	 *  1：全部推送   2:app推送   3:微信商家推送   4:微信用户推送   
	 * 
	 * 只能有1、2、3、4
	 */
	private HashSet<Integer> pushTypeArr; 
	 

	private Long sellerId;    //商家id
	
	private BigDecimal money; //支付金额
	
    private Date payTime;    //支付时间
	
    private String openId;    //微信用户的openId
    
	private String transactionId; //第三方支付订单号

    private String tradeType;    //支付类型   weixin   alipay  qq


	@Override
	public void run() {
		try {
			if(pushTypeArr.contains(1)){//全部推送
				pushToApp();
				pushToWXStore();
				pushToWXUser();
			}else{
				for(Integer num:pushTypeArr){
					if(num==2){
						pushToApp();
					}
					if(num==3){
						pushToWXStore();
					}
					if(num==4){
						pushToWXUser();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 
	 * 推送微信支付消息给商家
	 * @throws Exception 
	 * 
	 */
	private void pushToWXStore() throws Exception{
//		logger.info("=============================即将推送支付消息给商家=============================");
		WeixinUserinfoService weixinUserinfoService =  (WeixinUserinfoService) wac.getBean(cn.qhjys.mall.service.impl.WeixinUserinfoServiceImpl.class);
		StoreService storeService = (StoreService) wac.getBean(cn.qhjys.mall.service.impl.StoreServiceImpl.class);
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		WeixinUserinfoExample example2 = new WeixinUserinfoExample();
		example2.createCriteria().andStoreIdEqualTo(store.getId());
		List<WeixinUserinfo> wxus = weixinUserinfoService.selectByExample(example2);
		JSONObject data1 = new JSONObject();
		//data1.put("touser", "ob8WbwfgeZpxaAoEWw3HwSmtJ-QI");
		data1.put("touser", "ob8Wbwc0J-UcF6_QZiJfBjCWJ_LY");
		data1.put("template_id", "L5rS8B-IoLLFc7Y1q-4_5J3xWjLRri2ghNfTUKPGs2k");
		JSONObject data = new JSONObject();
		JSONObject params;
		params = new JSONObject();
		params.put("value", "尊敬的商户，您已收到付款，信息如下：");
		params.put("color", "#173177");
		data.put("first", params);
		params = new JSONObject();
		params.put("value", "￥"+money);
		params.put("color", "#173177");
		data.put("keyword1", params);
		params = new JSONObject();
		if(tradeType.contains("weixin")){
			params.put("value", "微信支付");
		}else if(tradeType.contains("alipay")){
			params.put("value", "支付宝支付");
		}else if(tradeType.contains("qq")){
			params.put("value", "QQ支付");
		}else if(tradeType.contains("pos")){
			params.put("value", "POS机支付");
		}else if(tradeType.contains("coupon")){
			if(tradeType.contains("_")){
				String[] arrs = tradeType.split("_");
				String str = "卡券优惠" + (arrs.length==2 ? arrs[1]:"");
				params.put("value", str);
			}else{
				params.put("value", "卡券优惠");
			}
		}
		params.put("color", "#173177");
		data.put("keyword2", params);
		
		params = new JSONObject();
		params.put("value", store.getName());
		params.put("color", "#173177");
		data.put("keyword3", params);
		
		params = new JSONObject();
		params.put("value", transactionId);
		params.put("color", "#173177");
		data.put("keyword4", params);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		params = new JSONObject();
		params.put("value", sdf.format(new Date()));
		params.put("color", "#173177");
		data.put("keyword5", params);
		
		params = new JSONObject();
		params.put("value", "如有疑问请联系 400-6333-088");
		params.put("color", "#173177");
		data.put("remark", params);
		
		data1.put("data", data);
		data1.put("url", "");
		JSONObject json =new JSONObject();
		for (int i = 0; i < wxus.size(); i++) {
			data1.remove("touser");
			data1.put("touser",wxus.get(i).getOpenId());
//			json = WeixinUtil.sendTempMessage(data1.toJSONString());
			String SEND_TEMP_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessToken.getAccessToken();
			String result = SendPushPost.sendPost(SEND_TEMP_MESSAGE, data1.toJSONString());
//			logger.info("回调发送微信通知-------userinfo-----------"+openId+"------wxorderno------"+transactionId+"------resultwechat----"+result);
		}
//		logger.info("=============================推送支付消息给商家 end=============================");
	}
	
	
	private void pushToWXUser() throws Exception{
		logger.info("=============================推送支付消息给买家 start=============================");
		WeixinUserinfoService weixinUserinfoService =  (WeixinUserinfoService) wac.getBean(cn.qhjys.mall.service.impl.WeixinUserinfoServiceImpl.class);
		StoreService storeService = (StoreService) wac.getBean(cn.qhjys.mall.service.impl.StoreServiceImpl.class);
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		WeixinUserinfoExample example2 = new WeixinUserinfoExample();
		example2.createCriteria().andOpenIdEqualTo(openId);
		List<WeixinUserinfo> wxus = weixinUserinfoService.selectByExample(example2);
		wxus = weixinUserinfoService.selectByExample(example2);
		if (wxus.size()>0) {
			JSONObject data2 = new JSONObject();
			data2.put("touser", openId);
			data2.put("template_id", "odLp24sxZVWiAfZZbizUeystBdfiZ_F6eJaDytxrqrg");
			JSONObject data3 = new JSONObject();
			JSONObject params1;
			params1 = new JSONObject();
			params1.put("value", "￥"+money);
			params1.put("color", "#fb784b");
			data3.put("pay", params1);
			params1 = new JSONObject();
			params1.put("value", store.getName());
			params1.put("color", "#000000");
			data3.put("address", params1);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			params1 = new JSONObject();
			params1.put("value",sdf.format(payTime));
			params1.put("color", "#000000");
			data3.put("time", params1);
			
			params1 = new JSONObject();
			params1.put("value", "\n看到消费记录心痛了？省钱不如…");
			params1.put("color", "#fb784b");
			data3.put("remark", params1);
			
			data2.put("data", data3);
			data2.put("url", "http://www.jysmall.com/app/p2p_introduce.html");
			String SEND_TEMP_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessToken.getAccessToken();
			String result = SendPushPost.sendPost(SEND_TEMP_MESSAGE, data2.toJSONString());
			
			logger.info("回调发送微信通知-------userinfo-----------"+openId+"------wxorderno------"+transactionId+"------resultwechat----"+result);
		}
		
	}
	
	
	private void pushToApp(){
		FqPushInfoService fqPushInfoService = (FqPushInfoService) wac.getBean("fqPushInfoService");
//		logger.info("=============================推送支付消息给App start=============================");
		FqPushInfo fqPushInfo = new FqPushInfo();
		fqPushInfo.setContent("");
		fqPushInfo.setPushTime(new Date());
		fqPushInfo.setSellerId(sellerId);
		fqPushInfo.setTitle("你已收到付款" + money.toString() + "元");
		fqPushInfo.setType(1);
		int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
		logger.info("---------save--pushinfo-------" + a);
		Map<String, Object> custom = new HashMap<String, Object>();
		custom.put("type", 1);
		MessageIOS messageIos = new MessageIOS();
		messageIos.setExpireTime(60);
		messageIos.setAlert("您已收到付款" + money.toString() + "元");
		messageIos.setBadge(1);
		messageIos.setSound("12 Bar Blues Bass.caf");
		JSONObject obj = new JSONObject();
		JSONObject aps = new JSONObject();
		aps.put("sound", "12 Bar Blues Bass.caf");
		aps.put("alert", "您已收到付款" + money.toString() + "元");
		aps.put("badge", 1);
		obj.put("aps", aps);
		obj.put("type", "1");
		messageIos.setRaw(obj.toString());
		XingeService.xingeIos.pushSingleAccount(0, sellerId + "", messageIos,
				XingeApp.IOSENV_PROD);
		XingeService.xingeIos.pushSingleAccount(0, sellerId + "", messageIos,
				XingeApp.IOSENV_DEV);
		Message message = new Message();
		message.setExpireTime(60);
		message.setTitle("支付成功");
		message.setContent("您已收到付款" + money.toString() + "元");
		message.setType(Message.TYPE_NOTIFICATION);
		message.setCustom(custom);
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_ACTIVITY);
		action.setActivity("com.freechange.seller.modules.MainActivity");
		message.setAction(action);
		XingeService.xinge.pushSingleAccount(0, sellerId + "", message);
//		logger.info("=============================推送支付消息给App end=============================");
	}


	public HashSet<Integer> getPushTypeArr() {
		return pushTypeArr;
	}


	public void setPushTypeArr(HashSet<Integer> pushTypeArr) {
		this.pushTypeArr = pushTypeArr;
	}


	public Long getSellerId() {
		return sellerId;
	}


	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}


	public BigDecimal getMoney() {
		return money;
	}


	public void setMoney(BigDecimal money) {
		this.money = money;
	}


	public Date getPayTime() {
		return payTime;
	}


	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}


	public String getOpenId() {
		return openId;
	}


	public void setOpenId(String openId) {
		this.openId = openId;
	}


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getTradeType() {
		return tradeType;
	}


	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	
	
}
