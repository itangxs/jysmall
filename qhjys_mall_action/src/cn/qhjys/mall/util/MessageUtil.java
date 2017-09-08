package cn.qhjys.mall.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 短信发送 帮助类
 * 
 * @author JiangXiaoPing
 *
 */
public class MessageUtil {
	public final static String URL = "http://m.5c.com.cn/api/send/index.php";
	public final static String USER_NAME = "jinyaoshi";
	public final static String PASS_WORD = "qwer1234";
	public final static String APIKEY = "c6facdd103f4e006ef5bd82fdf725359";

	public final static String VURL = "http://m.5c.com.cn/api/send/index.php";// 验证
	public final static String VUSER_NAME = "jinyaoshitz";// 验证
	public final static String VPASS_WORD = "qwer1234";// 验证
	public final static String VAPIKEY = "dc9bae1aa243366e7d0947c5d842b7a8";// 验证

	// 5次或以上登陆失败模板 name :用戶名
	public final static String LONGINERROR = "尊敬的 name,您连续5次密码错误，账户已经冻结。如遗忘密码，请通过忘记密码找回。";
	// 通用验证码
	public final static String COMMCAPTCHA = "尊敬的用户，您本次的手机验证码为（captcha）。";

	// 注册验证码 code :验证码
	public final static String REGISTRATIONVERIFICATIONCODE = "欢迎注册我们的会员, 您本次注册的验证码为（code）。";

	// 登陆验证码 code :验证码
	public final static String LONGINVERIFICATIONCODE = "你正在登陆网站，本次登陆验证码为（code）。";

	// 密码修改 username :用户名
	public final static String UPDATEPASSWORD = "尊敬的：username，您的密码已经修改成功，请妥善保管。";

	// 密码找回（手机找回） username:账号 code:验证码
	public final static String BACKPASSWORDPHONE = "尊敬的： username，您正在申请找回密码，本次操作验证码为（code）。";
	
	
	// 商家密码重置（系统重置） username:账号 code:密码
	public final static String BACKSELLERPASSWORDPHONE = "尊敬的： username，您的密码已重置，重置密码为（code）。请妥善保管。";
	
	// 前台密码重置（系统重置） username:账号 code:密码
	public final static String BACKUSERPASSWORDPHONE = "尊敬的会员： username，您的密码已重置，重置密码为（code）。请妥善保管。";

	// 支付成功 username:用户名 order:订单号
	public final static String PAYMENTSUCCESS = "尊敬的：username,您的订单order已经支付成功，共money金元宝。";

	// 发货成功（实体产品） username:用户名 order:订单号 ems:快递公司 numbers:快递单号
	public final static String SUCCESSFULDELIVERY = "尊敬的：username，您的订单：order已发货。物流公司：ems快递，物流单号 numbers请注意查收。";

	// 预约提示（商品预约）username:用户名 order:订单号 commodity:商品
	public final static String BOOKINGTIPS = "尊敬的：username，您的订单：order已提交预约。商品名称：commodity 等待商户反馈。";

	// 预约反馈（商品预约）成功 username:用户名 order:订单号
	public final static String RESERVATIONFEEDBACKSUCCESS = "尊敬的：username，您的订单：order，预约【成功】。“您的预约已受理，请按时到店体验服务，如有变更或任何疑问请提前与我们联系。";
	// 预约反馈（商品预约）失败 username:用户名 order:订单号
	public final static String RESERVATIONFEEDBACKERROR = "尊敬的：username，您的订单：order，预约【不成功】。“感谢您的选择，由于营业安排临时变更，商户暂时不接受您的预约，为您带来的不便请见谅。”）。";

	// 账户充值 username:用户名 money:充值金额 balance:可用金额
	public final static String ACCOUNTRECHARGE = "尊敬的：username，你已成功充值money金元宝，账户目前可用余额balance金元宝。请注意查询。";

	// 账户提取 username:用户名 money:提现金额 balance:可用金额
	public final static String ACCOUNTEXTRACTION = "尊敬的：username，你申请提现money元，账户目前可用余额balance元。请注意查询。";

	// 账户消费 username:用户名 money:消费金额 order:订单号
	public final static String CONSUMERACCOUNT = "尊敬的：username，你已消费money金元宝。订单号：order。";

	// 提取异常（大于账户余额） username:用户名
	public final static String ANOMALYEXTRACTIONBALANCE = "尊敬的：username，您本次申请的提取金额大于账户余额，请查看账户余额再进行提现操作。";

	// 提取异常（大于单笔限额） username:用户名
	public final static String ANOMALYEXTRACTIOSINGLE = "尊敬的：username，您本次申请的提取金额大于单笔限额（10万每次），请分多次提取。";

	// 密码修改 username :用户名
		public final static String VOLUMECODE = "尊敬的：username，您购买的product商品的消费卷密码为(volumeCode)。";
	
	/**
	 * 
	 * @Title: SendMessage
	 * @param content
	 *            发送的消息
	 * @param phone
	 *            手机号码
	 * @param type
	 *            类型 "0001":营销 "0002":验证码
	 * @return Boolean true:成功 false:失败
	 * @throws IOException
	 */
	public static Boolean SendMessage(String phone, Map<String, String> map, String model) throws IOException {
		if (!BaseUtil.isMobile(phone))
			return false;
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			model = model.replaceAll(entry.getKey().toString(), entry.getValue().toString());
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", VUSER_NAME);
		params.put("password", VPASS_WORD);
		params.put("apikey", VAPIKEY);
		params.put("mobile", phone);
		params.put("content", URLEncoder.encode(model,   "utf-8"));
		params.put("encode", "1");
		byte[] data = HttpUtil.doSend(VURL, "GET", params);
		if (null != data)
			return true;
		else
			return false;
	}

	/**
	 * 发送手机短息
	 * 
	 * @param phone
	 *            手机号
	 * @param map
	 *            参数
	 * @param model
	 *            模板
	 * @return
	 * @throws Exception
	 */
	public static boolean SendVerification(String phone, Map<String, String> map, String model) throws Exception {
		if (!BaseUtil.isMobile(phone))
			return false;
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			model = model.replaceAll(entry.getKey().toString(), entry.getValue().toString());
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", VUSER_NAME);
		params.put("password", VPASS_WORD);
		params.put("apikey", VAPIKEY);
		params.put("mobile", phone);
		params.put("content", URLEncoder.encode(model,   "utf-8"));
		params.put("encode", "1");
		byte[] data = HttpUtil.doSend(VURL, "GET", params);
		if (null != data)
			return true;
		else
			return false;// 失败
	}

	/**
	 * 发送短信内容
	 * 
	 * @param phone
	 *            手机号码
	 * @param content
	 *            短息内容
	 * @return
	 * @throws IOException
	 */
	public static boolean sendSmsContent(String phone, String content) throws IOException {
		if (!BaseUtil.isMobile(phone))
			return false;
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", VUSER_NAME);
		params.put("password", VPASS_WORD);
		params.put("apikey", VAPIKEY);
		params.put("mobile", phone);
		params.put("content", URLEncoder.encode(content,   "utf-8"));
		params.put("encode", "1");
		byte[] data = HttpUtil.doSend(VURL, "GET", params);
		if (null != data)
			return true;
		else
			return false;// 失败
	}
}