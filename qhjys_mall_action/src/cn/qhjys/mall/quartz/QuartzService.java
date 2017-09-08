package cn.qhjys.mall.quartz;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.FROMWHERE;
import cn.qhjys.mall.common.PushCard;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.entity.WxMessage;
import cn.qhjys.mall.mapper.CouponInfoMapper;
import cn.qhjys.mall.service.FqThirdPayService;
import cn.qhjys.mall.service.IntegralExpiredService;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.ThirdAccountService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.service.VolumeService;
import cn.qhjys.mall.service.WxMallService;
import cn.qhjys.mall.service.WxMessageService;
import cn.qhjys.mall.service.app.SellerCardCouponService;
import cn.qhjys.mall.service.fq.DaliyCreditService;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.service.fq.FqRedpackService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.FqStoreCreditService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.service.system.FinanceService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.service.system.SysUserTaskService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.InternetImage;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.vo.PushCardVo;
import cn.qhjys.mall.vo.TaskVo;
import cn.qhjys.mall.vo.WxMessageInfo;

public class QuartzService extends Base {
	@Autowired
	private VolumeService volumeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private IntegralExpiredService integralExpiredService;
	@Autowired
	private ProductService productService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private ThirdAccountService thirdAccountService;
	@Autowired
	private CouponInfoMapper couponInfoMapper;
	@Autowired
	private SysUserTaskService sysUserTaskService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private FqOrderService fqOrderService;
	@Autowired
	private FqStoreService fqStoreService;
	@Autowired
	private DaliyCreditService daliyCreditService;
	@Autowired
	private FqStoreCreditService fqStoreCreditService;
	@Autowired
	private FqSellerStatementService fqSellerStatementService;
	@Autowired
	private FqRedpackService fqRedpackService;
	@Autowired
	private StoreRebateService storeRebateService;
	@Autowired
	private FqThirdPayService fqThirdPayService;
	@Autowired
	private SellerCardCouponService sellerCardCouponService;
	@Autowired
	private FinanceService financeService;
	@Autowired
	private WxMallService wxMallService;
	@Autowired
	private WxMessageService wxMessageService;
	@Autowired
	private RebateOrderService  rebateOrderService;
	
	public void insertFqClerkCount(){
		logger.info("----------------插入业务员统计数据--begin----------------");
		try {
			rebateOrderService.insertFqClerkCount(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------插入业务员统计数据 -END---------");
	}
	public void updateStoreEffective(){
		logger.info("----------------更改有效店铺--begin----------------");
		try {
			storeService.updateStoreEffective();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------更改有效店铺 -END---------");
	}
	public void updateCashOrder(){
		logger.info("----------------更改套现属性--begin----------------");
		try {
			fqThirdPayService.updateCashOrder();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------更改套现属性 -END---------");
	}
	
	public void sendPreviewWxMessage() throws Exception{
		logger.info("-----------------发送预览消息--start----------------");
		while(FROMWHERE.ylmessages.size()>0){
			logger.info("-------------------start----------------");
			WxMessageInfo messageInfo = FROMWHERE.ylmessages.get(0);
			WxMessage message = messageInfo.getMessage();
			File file = new File(messageInfo.getPath()+message.getImage());
			logger.info("messageInfo.getPath()-------"+messageInfo.getPath());
			logger.info("file-------"+file);
			JSONObject json = WeixinUtil.uploadImage(AccessToken.getAccessToken(), "image", file);
			String media_id = json.getString("media_id");
			logger.info("media_id-------"+media_id);
			String content = message.getContent();
			List<String> iamges = getImgSrc(content);
			while (iamges.size()>0) {
				String image = iamges.get(0);
				File file1 = null;
				if (image.contains(ConstantsConfigurer.getProperty("web_url"))) {
					file1 = new File(messageInfo.getPath()+image.replace("http://"+ConstantsConfigurer.getProperty("web_url"), ""));
				}else{
					file1 = new File(messageInfo.getPath()+InternetImage.download(image, messageInfo.getPath()));
				};
				String jsonurl = WeixinUtil.uploadImg(AccessToken.getAccessToken(), file1);
				logger.info("jsonurl----------"+jsonurl);
				if (!StringUtils.isEmpty(jsonurl)) {
					content = content.replace(image, jsonurl);
				}
				iamges.remove(image);
			}
		
			logger.info(content);
			JSONObject json1  = new JSONObject();
			json1.put("thumb_media_id", media_id);
			json1.put("author", "飞券");
			json1.put("title", message.getTitle());
			json1.put("content_source_url", null);
			json1.put("content", content);
			json1.put("show_cover_pic", 1);
			JSONArray array = new JSONArray();
			array.add(json1);
			JSONObject jsonnews = new JSONObject();
			jsonnews.put("articles", array);
			JSONObject json2 = WeixinUtil.uploadFodder(AccessToken.getAccessToken(), jsonnews.toJSONString());
			String mediaId = json2.getString("media_id");
			
			JSONObject media_id1 = new JSONObject();
			media_id1.put("media_id", mediaId);
			JSONObject news = new JSONObject();
			news.put("towxname", messageInfo.getUsername());
			news.put("mpnews", media_id1);
			news.put("msgtype", "mpnews");
			logger.info(news.toJSONString());
			JSONObject json3 = WeixinUtil.previewMessage(AccessToken.getAccessToken(), news.toJSONString());
			logger.info("------sendmessagejson3------"+json3);
			FROMWHERE.ylmessages.remove(0);
//			if (!json3.getInteger("errcode").equals(0)) {
//				FROMWHERE.messages.add(messageInfo);
//			}	
		}
		logger.info("-----------------发送预览消息--end----------------");
	}
	
	public void updateWxSendMessage(){
		logger.info("----------------发送微信消息--begin----------------");
		try {
			wxMessageService.updateWxSendMessage();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------发送微信消息 -END---------");
	}
	
	public void updateWxContent(){
		logger.info("----------------上传微信--begin----------------");
		try {
			wxMessageService.updateWxContent();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------上传微信 -END---------");
	}
	public void pushCardQuartz(){
		logger.info("----------------公众号投放--begin----------------");
		try {
			while(PushCard.pushcards.size()>0){
				PushCardVo cardVo = PushCard.pushcards.get(0);
				logger.info("----------------公众号投放------------------"+cardVo.getCardCouponTemplate().getId());
				logger.info("----------------公众号投放------------------"+cardVo.getOpenIds().size());
				wxMallService.insertPushCard(cardVo);
				PushCard.pushcards.remove(0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------公众号投放 -END---------");
	}
	
	public void updateCardCouponDataQuartz(){
		long statr = System.currentTimeMillis();
		logger.info("----------------每日统计卡券数据--begin----------------");
		try {
			sellerCardCouponService.updateSellerStatementByQuartz();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("----------------每日统计卡券数据--END-"+(System.currentTimeMillis()-statr)+"ms---------------");
	}
	
	
	public void updateSellerStatementByQuartz(){
		logger.info("----------------结算验证--begin----------------");
		try {
			fqSellerStatementService.updateSellerStatementByQuartz();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("----------------结算验证--END----------------");
	}
	
	public void updateSellerStatementHasPeriod(){
		logger.info("----------------查验结算异常--begin----------------");
		try {
			fqSellerStatementService.updateSellerStatementHasPeriod();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("----------------查验结算异常--END----------------");
	}
	public void updateSellerRepayment(){
		logger.info("----------------预约还款--begin----------------");
		int a = financeService.updateSellerRepayment();
		if (a >0) {
			logger.info("----------------TIMING REPAYMENT --SUCCESS----------------");
		}else{
			logger.info("----------------NO TIMING REPAYMENT------------------");
		}
		logger.info("----------------预约还款--END----------------");
	}
	public void updateTimeOutRevate(){
		storeRebateService.updateTimeOutRebate();
	}
	public void updateMicroOrder() throws Exception{
		int a = fqThirdPayService.updateMicroOrder();
		if (a >0) {
			logger.info("----------------MICRO ORDER SELECT--SUCCESS----------------");
		}else{
			logger.info("----------------NO MICRO ORDER SELECT------------------");
		}
	}
	
	/**
	 * 旺pos机定时器 补单
	 */
	public void updateWPosOrder() throws Exception{
		int a = fqThirdPayService.updateWPosOrder();
		if (a >0) {
			logger.info("---------------更新旺pos机订单"+a+"条----------------");
		}else{
			logger.info("----------------无旺pos机 ORDER SELECT------------------");
		}
	}
	
	
	public void updateAllSellerDeliveryPushNum(){
		logger.info("----------------更新商户直接投放次数任务--begin----------------");
		try {
			int result = sellerCardCouponService.updateAllSellerDeliveryPushNum();
			if (result > 0) {
				logger.info("----------------更新商户直接投放次数--SUCCESS----------------");
			} else {
				logger.error("----------------更新商户直接投放次数--NO REDPACK----------------");
			}
		} catch (Exception e) {
			this.logger.error("更新商户直接投放次数任务异常", e);
			
		}
		logger.info("----------------更新商户直接投放次数任务--end----------------");
	}
	
	
	public void sendRedpack(){
		logger.info("----------------发送红包任务--begin----------------");
		try {
			int result = fqRedpackService.updateFqRedpackRecordBySend();
			if (result > 0) {
				logger.info("----------------发送红包任务--SUCCESS----------------");
			} else {
				logger.error("----------------发送红包任务--NO REDPACK----------------");
			}
		} catch (Exception e) {
			this.logger.error("发送红包任务任务异常", e);
			
		}
		logger.info("----------------发送红包任务任务--end----------------");
	}
	public void statementToCashCredit() throws InterruptedException{
		logger.info("----------------商家余额结算定时任务--start----------------时间="+System.currentTimeMillis());
		int a = 0;
		while (a<5) {
			try {
				boolean result = fqSellerStatementService.updateSellerStatementAndAutoWithdrawByQuartz();
				if (result) {
					logger.info("----------------商家余额结算定时任务--SUCCESS----------------");
					a=5;
				} else {
					logger.error("----------------商家余额结算定时任务--ERROR----------------");
					a+=1;
					Thread.sleep(30000);
				}
			} catch (Exception e) {
				this.logger.error("商家余额结算定时任务异常", e);
				a+=1;
				Thread.sleep(100000);
			}
		}
		
		logger.info("----------------商家余额结算定时任务--end----------------时间="+System.currentTimeMillis());
	}
	public void verifStoreCredit(){
		logger.info("----------------借贷信息定时任务--begin----------------");
		try {
			boolean result = fqStoreCreditService.updateStoreCreditStatus();
			if (result) {
				logger.info("----------------借贷信息定时任务--SUCCESS----------------");
			} else {
				logger.error("----------------借贷信息定时任务--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("借贷信息定时任务异常", e);
		}
		logger.info("----------------借贷信息定时任务--end----------------");
	}
	
	
	public void verifDaliyCredit() throws IOException{
		logger.info("----------------每日额度--begin----------------");
		Long datelong = System.currentTimeMillis();
		Date endTime = new Date();
		Date beginTime = new Date(datelong - datelong%86400000 - 86400000L/24*8);
		List<FqStore> list = fqStoreService.listFqStoreByDaliy();
		for (int i = 0; i < list.size(); i++) {
			FqStore store = list.get(i);
			BigDecimal sum = daliyCreditService.queryDaliyCredit(store.getSellerId(), store.getId(), beginTime, endTime);
			
			if (sum.compareTo(store.getDaliyCredit()) <0) {
				String content = "店铺["+store.getStoreName()+"]今日微信营业额为:"+sum+"元,未到达预设每日额度:"+store.getDaliyCredit()+"元.";
				MessageUtil.sendSmsContent(store.getClerkPhone(), content);
			}
		}
		logger.info("----------------每日额度--end----------------");
	}

	/**
	 * 1. 过期消费卷自动退款 2. 申请退款超时自动退款
	 */
	public void autoRefund() {
		logger.info("----------------自动退款--begin----------------");
		/*try {
			boolean result = volumeService.updateAutoRefundOrder();
			if (result) {
				logger.info("----------------1.过期消费卷自动退款--SUCCESS----------------");
			} else {
				logger.error("----------------1.过期消费卷自动退款--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("过期消费卷自动退款异常", e);
		}*/
		try {
			boolean result = volumeService.updateOvertimeOrder();
			if (result) {
				logger.info("----------------2.申请退款超时自动退款--SUCCESS----------------");
			} else {
				logger.error("----------------2.申请退款超时自动退款--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("过期订单自动退款异常", e);
		}
		logger.info("----------------自动退款--end----------------");
	}

	/**
	 * 自动删除超时的未支付订单
	 */
	public void overtimeOrder() {
		logger.info("----------------自动删除超时的未支付订单--begin----------------");
		try {
			boolean result = orderService.deleteOvertimeOrder();
			if (result) {
				logger.info("----------------自动删除超时的未支付订单--SUCCESS----------------");
			} else {
				logger.error("----------------自动删除超时的未支付订单--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("过期未支付订单删除异常", e);
		}
		logger.info("----------------自动删除超时的未支付订单--end----------------");
	}

	/**
	 * 自动下架商品
	 */
	public void autoShelfProduct() {
		logger.info("----------------商品自动下架--begin----------------");
		try {
			boolean result = productService.updateAutoShelfProduct();
			if (result) {
				logger.info("----------------商品自动下架--SUCCESS----------------");
			} else {
				logger.error("----------------商品自动下架--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("自动下架商品:", e);
		}
		logger.info("----------------商品自动下架--end----------------");
	}

	/**
	 * 自动更新会员等级
	 */
	public void userLeve() {
		logger.info("----------------自动更新会员等级--begin----------------");
		try {
			boolean result = userInfoService.updateUserLevel();
			if (result) {
				logger.info("----------------自动更新会员等级--SUCCESS----------------");
			} else {
				logger.error("----------------自动更新会员等级--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("更新用户等级异常:", e);
		}
		logger.info("----------------自动更新会员等级--end----------------");
	}

	public void monthPoints() {
		logger.info("--------------------创建会员积分过期记录----start-------------");
		try {
			boolean result = integralExpiredService.saveIntegralExpired();
			if (result) {
				logger.info("--------------SUCCESS------创建会员积分过期记录成功-----------------");
			} else {
				logger.info("--------------ERROR------创建会员积分过期记录异常-----------------");
			}
		} catch (Exception e) {
			this.logger.error("创建会员积分过期记录异常：", e);
		}
		logger.info("--------------------创建会员积分过期记录----end-------------");
	}

	public void thirdDayUpdate() {
		logger.info("--------------------每日更新绑定账号次数----start-------------");
		try {
			boolean result = userInfoService.updateUserThirdDay();
			if (result) {
				logger.info("----------------每日更新绑定账号次数--SUCCESS----------------");
			} else {
				logger.error("----------------每日更新绑定账号次数--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("每日更新绑定账号次数异常:", e);
		}
		logger.info("----------------每日更新绑定账号次数--end----------------");
	}
	public void verifiP2p() throws Exception{
//		logger.info("--------------------每日处理P2P账号关联任务奖励----start-------------");
//		TaskInfo task  = taskInfoService.getTaskInfo(NewTaskCode.N004);
//		List<UserTask> userTasks = userTaskService.listUserInfoByStatus(task.getId(), "c");
//		for (int i = 0; i < userTasks.size(); i++) {
//			UserTask ut = userTasks.get(i);
//			ThirdAccount ta = thirdAccountService.queryThirdAccount(ut.getUserId(), "001");
//			StringBuffer sb = new StringBuffer();
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("username", ta.getAccount());
//			sb.append("username=" + ta.getAccount() + "&");
//			map.put("phone", ta.getPhone());
//			sb.append("phone=" + ta.getPhone() + "&");
//			sb.append("md5key=" + ConstantsConfigurer.getProperty("jysmd5"));
//			map.put("sign", EncodeMD5.GetMD5Code(sb.toString()));
//			byte[] a = HttpUtil.doSend("http://www.jysp2p.com/mallrequest.do", "GET", map);
//			String b = new String(a);
//			JSONObject jo = JSONObject.parseObject(b);
//			String msgs1 = ((String) jo.get("msg1"));
//			String msgs2 = ((String) jo.get("msg2"));
//			if (msgs1.equals("001")&&msgs2.equals("001")) {
//				ut.setStatus("cp");
//				ut.setTotamt(task.getFulfilReward());
//				userTaskService.updateUserTaskByCp(ut);
//			}
//		}
//		logger.info("----------------每日处理P2P账号关联任务奖励--end----------------");
	}
	public void verifTaskByc() throws IOException{
		try {
			Calendar c= Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			Date beginTime = c.getTime();
			logger.info("----------------beginTime----------------"+beginTime);
			c.add(Calendar.DATE, 1);
			Date endTime =c.getTime();
			logger.info("----------------endTime----------------"+endTime);
			logger.info("----------------类型0----------------"+endTime);
			List<TaskVo> vos0 = taskInfoService.listTaskInfoByC("c", 0, beginTime, endTime);
			for (int i = 0; i < vos0.size(); i++) {
				TaskVo tv = vos0.get(i);
				logger.info("------tv.getUid()------"+tv.getUid());
				logger.info("-----tv.getUserId()-------"+tv.getUserId());
				logger.info("------tv.getId()------"+tv.getId());
				logger.info("-----tv.getTaskLevel()-------"+tv.getTaskLevel());
				if (tv.getUserId() < 1000000000) {
				if (tv.getFulfilReward().intValue() > 30) {
					UserInfo user = userInfoService.selectUserById(tv.getUserId());
					if (user.getInviteCode() != null && user.getInviteCode() >200000) {
						UserTask ut = new UserTask();
						ut.setId(tv.getUid());
						ut.setUserId(tv.getUserId());
						ut.setStatus("cp");
						ut.setTaskId(tv.getTaskId());
						ut.setTotamt(tv.getFulfilReward());
						//UserInfo ui = userInfoService.selectUserById(ut.getUserId());
						Date now = new Date();
						OrderInfo order = new OrderInfo();
						order.setCreateTime(now);
						order.setEnabled(1);
						order.setIntegral(ut.getTotamt());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
						String orderNo = "T" + sdf.format(now);
						order.setOrderNo(orderNo);
						order.setTotamt(ut.getTotamt().divide(new BigDecimal(20)));
						order.setUserId(user.getId());
						orderService.insertOrderInfo(order);
						order = orderService.getOrderInfoByOrderNo(orderNo);
						OrderDetail detail = new OrderDetail();
						detail.setOrderId(order.getId());
						detail.setDetailNo(orderNo+"-1");
						SellerInfo seller = sellerService.getSellerInfoByInvite(user.getInviteCode());
						StoreInfo store = storeService.queryStoreInfoBySeller(seller.getId());
						detail.setStoreId(store.getId());
						detail.setSellerId(seller.getId());
						detail.setEnabled(1);
						detail.setStatus(2);
						detail.setQuantity(1);
						detail.setTotalPrice(ut.getTotamt());
						detail.setPayPrice(ut.getTotamt());
						detail.setCreateTime(new Date());
						orderService.insertOrderDetail(detail);
						detail = orderService.getOrderDetailByOrderId(order.getId());
						orderService.insertVolume(detail.getId(), orderService.getVolumeCode(), new Date(System.currentTimeMillis()+1*24*60*60*1000L));
						userTaskService.updateUserTask(ut);
					}else{
						UserTask ut = new UserTask();
						ut.setId(tv.getUid());
						ut.setUserId(tv.getUserId());
						ut.setStatus("cp");
						ut.setTaskId(tv.getTaskId());
						ut.setTotamt(tv.getFulfilReward());
						userTaskService.updateUserTaskByCp(ut);
					}
				}else{
					UserTask ut = new UserTask();
					ut.setId(tv.getUid());
					ut.setUserId(tv.getUserId());
					ut.setStatus("cp");
					ut.setTaskId(tv.getTaskId());
					ut.setTotamt(tv.getFulfilReward());
					userTaskService.updateUserTaskByCp(ut);
				}
			}
			}
			logger.info("----------------类型4----------------"+endTime);
			List<TaskVo> vos4 = taskInfoService.listTaskInfoByC("c", 4, beginTime, endTime);
			for (int i = 0; i < vos4.size(); i++) {
				TaskVo tv = vos4.get(i);
				UserTask ut = new UserTask();
				logger.info("----------------"+tv.getUid());
				logger.info("----------------"+tv.getUserId());
				ut.setId(tv.getUid());
				ut.setUserId(tv.getUserId());
				ut.setStatus("cp");
				ut.setTaskId(tv.getTaskId());
				ut.setTotamt(tv.getFulfilReward());
				userTaskService.updateUserTask(ut);
				CouponInfo ci = new CouponInfo();
				ci.setAmount(tv.getFulfilReward());
				ci.setConsume(0);
				ci.setCouponId(BaseUtil.couponRandom());
				ci.setExpireTime(new Date(System.currentTimeMillis() + 30 * 24 * 60* 60 * 1000L));
				ci.setName("商家元宝抵用卷: " + tv.getFulfilReward());
				ci.setStoreId(Long.valueOf(tv.getProject()));
				ci.setUserId(tv.getUserId());
				couponInfoMapper.insertSelective(ci);
				logger.info("----------------verifTaskByc- end---------------"+endTime);
			}
		} catch (Exception e) {
			 MessageUtil.sendSmsContent("18300043285", "任务审核处理程序出错");
			 e.printStackTrace();
		}
		
		try {
			List<TaskInfo> taskInfos = taskInfoService.listTaskInfo(2);
			for (int i = 0; i < taskInfos.size(); i++) {
				TaskInfo ti = taskInfos.get(i);
				BigDecimal change = new BigDecimal((int)(300+Math.random()*200));
				ti.setTaskFulfil(ti.getTaskFulfil().add(change));
				taskInfoService.updateTaskInfo(ti);
			}
		} catch (Exception e) {
			MessageUtil.sendSmsContent("18300043285", "每日增加任务完成数程序出错");
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  关注任务自动审核
	 */
	public void sysUserTaskTiming()throws IOException{
		logger.info("--------------------每日关注任务自动审核----start-------------");
		try {
			boolean result = sysUserTaskService.updatetiming();
			if (result) {
				logger.info("-----------------每日关注任务自动审核--SUCCESS----------------");
			} else {
				logger.error("-----------------每日关注任务自动审核--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("-每日关注任务自动审核异常:", e);
		}
		logger.info("-----------------每日关注任务自动审核--end----------------");
	}
	
	public void wenjuanTaskTiming() throws Exception{
		logger.info("--------------------每日问卷任务自动审核----start-------------");
		try {
			boolean result = sysUserTaskService.updateWenjuanTiming();
			if (result) {
				logger.info("-----------------每日问卷任务自动审核--SUCCESS----------------");
			} else {
				logger.error("-----------------每日问卷任务自动审核--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("-每日问卷任务自动审核异常:", e);
		}
		logger.info("-----------------每日评论任务自动审核--end----------------");
		logger.info("--------------------每日问卷任务自动审核----start-------------");
		try {
			boolean result1 = sysUserTaskService.updatePinlunTiming();
			if (result1) {
				logger.info("-----------------每日评论任务自动审核--SUCCESS----------------");
			} else {
				logger.error("-----------------每日评论任务自动审核--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("-每日评论任务自动审核异常:", e);
		}
		logger.info("-----------------每日评论任务自动审核--end----------------");
		
	}
	
	public void deleteNoPayOrder(){
		logger.info("--------------------未支付订单自动删除----start-------------");
		try {
			int result = fqOrderService.deleteOrderNoPay();
			if (result>0) {
				logger.info("-----------------未支付订单自动删除--SUCCESS----------------");
			} else {
				logger.error("-----------------未支付订单自动删除--ERROR----------------");
			}
		} catch (Exception e) {
			this.logger.error("-未支付订单自动删除异常:", e);
		}
		logger.info("-----------------未支付订单自动删除--end----------------");
	}
	
	public void sendWxMessage() throws Exception{
		logger.info("-----------------商户图文信息--start----------------");
		while(FROMWHERE.messages.size()>0){
			logger.info("-------------------start----------------");
			WxMessageInfo messageInfo = FROMWHERE.messages.get(0);
			WxMessage message = messageInfo.getMessage();
			File file = new File(messageInfo.getPath()+message.getImage());
			logger.info("messageInfo.getPath()-------"+messageInfo.getPath());
			logger.info("file-------"+file);
			JSONObject json = WeixinUtil.uploadImage(AccessToken.getAccessToken(), "image", file);
			String media_id = json.getString("media_id");
			logger.info("media_id-------"+media_id);
			String content = message.getContent();
			List<String> iamges = getImgSrc(content);
			for (int i = 0; i < iamges.size(); i++) {
				logger.info("iamges----------"+iamges.get(i));
				if (iamges.get(i).contains(ConstantsConfigurer.getProperty("web_url"))) {
					logger.info("iamgesreal----------"+messageInfo.getPath()+iamges.get(i).replace("http://"+ConstantsConfigurer.getProperty("web_url"), ""));
					File file1 = new File(messageInfo.getPath()+iamges.get(i).replace("http://"+ConstantsConfigurer.getProperty("web_url"), ""));
					logger.info("file1----------"+file1);
					String jsonurl = WeixinUtil.uploadImg(AccessToken.getAccessToken(), file1);
					logger.info("jsonurl----------"+jsonurl);
					if (!StringUtils.isEmpty(jsonurl)) {
						content = content.replace(iamges.get(i), jsonurl);
					}
				}
			}
			logger.info(content);
			JSONObject json1  = new JSONObject();
			json1.put("thumb_media_id", media_id);
			json1.put("author", "飞券");
			json1.put("title", message.getTitle());
			json1.put("content_source_url", null);
			json1.put("content", content);
			json1.put("show_cover_pic", 1);
			JSONArray array = new JSONArray();
			array.add(json1);
			JSONObject jsonnews = new JSONObject();
			jsonnews.put("articles", array);
			JSONObject json2 = WeixinUtil.uploadFodder(AccessToken.getAccessToken(), jsonnews.toJSONString());
			String mediaId = json2.getString("media_id");
			
			String [] openids = messageInfo.getOpenidslist().toArray(new String [messageInfo.getOpenidslist().size()]);
			JSONObject mpnews = new JSONObject();
			JSONObject media_id1 = new JSONObject();
			media_id1.put("media_id", mediaId);
			mpnews.put("mpnews", media_id1);
			JSONObject news = new JSONObject();
			news.put("touser", openids);
			news.put("mpnews", media_id1);
			news.put("msgtype", "mpnews");
			logger.info(news.toJSONString());
			JSONObject json3 = WeixinUtil.sendMessage(AccessToken.getAccessToken(), news.toJSONString());
			FROMWHERE.messages.remove(0);
			if (!json3.getInteger("errcode").equals(0)) {
				FROMWHERE.messages.add(messageInfo);
			}
				
		}
	}
	public static  List<String> getImgSrc1(String htmlStr) {  
        String img = "";  
        Pattern p_image;  
        Matcher m_image;  
        List<String> pics = new ArrayList<String>();  
//       String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址  
        String regEx_img = "<img.src\\s*=\\s*(.*?)[^>]*?>";  
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);  
        m_image = p_image.matcher(htmlStr);  
        while (m_image.find()) {  
            img = img + "," + m_image.group();  
            // Matcher m =  
            // Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src  
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);  
            while (m.find()) {  
                pics.add(m.group(1));  
            }  
        }  
        return pics;  
    }  
	 public static List<String> getImgSrc(String content){
         
	        List<String> list = new ArrayList<String>();
	        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
	        Matcher m_img = p_img.matcher(content);
	        boolean result_img = m_img.find();
	        if (result_img) {
	            while (result_img) {
	                String str_img = m_img.group(2);
	                 
	                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
	                Matcher m_src = p_src.matcher(str_img);
	                if (m_src.find()) {
	                    String str_src = m_src.group(3);
	                    list.add(str_src);
	                }
	                 
	                result_img = m_img.find();
	            }
	        }
	        return list;
	    }
	
}