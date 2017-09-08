package cn.qhjys.mall.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSendUtil {

	public static final String HOST = "smtp.163.com";

	public static final String PORT = "25";

	public static final String USERNAME = "jysmall@163.com";

	public static final String PASSWORD = "voimeotihabmsqpv";

	
	//激活（邮箱注册）url:激活链接 
	public static String ACTIVATIONREGISTEREMAIL = "hi 用户名,感谢您注册飞券网，请点击下面的链接验证您的Email： 此处为链接 url 飞券网";
	
	//注册成功 username:账号
	public static String REGISTRATIONSUCCESS = "您已成功注册 飞券网会员，会员名为username，祝您生活愉快。 飞券网";
	
	//密码找回（邮箱找回）username:用户名     url:链接地址
	public static String BACKEAMILPASSWORD = "尊敬的：username,您正在申请找回密码，请点击下面的链接：此处为链接url。 飞券网";
	
	//预约提示（商品预约）username:用户名    order:订单号	commodity:商品
	public final static String BOOKINGTIPS ="尊敬的：username，您的订单：order已提交预约。商品名称：commodity 等待商户反馈。飞券网";
	
	//邮箱验证码  
	public final static String EMAILCODE ="尊敬的用户，您本次的邮箱验证码：(code),请妥善保管.";
		
	
	/**
	 * 
	 * @Title: send
	 * @param jszdz
	 *            接收的地址
	 * @param hashMap
	 *            必须有: title 标题 content 内容 name 客户名字
	 * @param mb
	 *            模板
	 * @return
	 * @return String
	 */
	public static Boolean send(String jszdz, HashMap<String, String> hashMap, String mb) {
		Iterator<Entry<String, String>> it = hashMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			mb = mb.replaceAll(entry.getKey().toString(), entry.getValue().toString());
		}

		/*
		 * mb=mb.replaceAll("name", hashMap.get("name"));
		 * mb=mb.replaceAll("content", hashMap.get("content"));
		 */

		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(HOST);
		mailInfo.setMailServerPort(PORT);
		mailInfo.setValidate(true);
		mailInfo.setUserName(USERNAME); // 实际发送者
		mailInfo.setPassword(PASSWORD);// 您的邮箱密码
		mailInfo.setFromAddress(USERNAME); // 设置发送人邮箱地址
		mailInfo.setToAddress(jszdz); // 设置接受者邮箱地址
		mailInfo.setSubject(hashMap.get("title"));
		mailInfo.setContent(mb);
		// 这个类主要来发送邮件
		Boolean bl = sendTextMail(mailInfo); // 发送文体格式
		return bl;
	}

	public static Boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/*
		*//**
	 * 以HTML格式发送邮件
	 * 
	 * @param mailInfo
	 *            待发送的邮件信息
	 */
	/*
	 * public static boolean sendHtmlMail(MailSenderInfo mailInfo){ //
	 * 判断是否需要身份认证 MyAuthenticator authenticator = null; Properties pro =
	 * mailInfo.getProperties(); //如果需要身份认证，则创建一个密码验证器 if
	 * (mailInfo.isValidate()) { authenticator = new
	 * MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword()); } //
	 * 根据邮件会话属性和密码验证器构造一个发送邮件的session Session sendMailSession =
	 * Session.getDefaultInstance(pro,authenticator); try { // 根据session创建一个邮件消息
	 * Message mailMessage = new MimeMessage(sendMailSession); Address from =
	 * new InternetAddress(mailInfo.getFromAddress()); // 设置邮件消息的发送者
	 * mailMessage.setFrom(from); // 创建邮件的接收者地址，并设置到邮件消息中 Address to = new
	 * InternetAddress(mailInfo.getToAddress()); //
	 * Message.RecipientType.TO属性表示接收者的类型为TO
	 * mailMessage.setRecipient(Message.RecipientType.TO,to); // 设置邮件消息的主题
	 * mailMessage.setSubject(mailInfo.getSubject()); // 设置邮件消息发送的时间
	 * mailMessage.setSentDate(new Date()); //
	 * MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象 Multipart mainPart = new
	 * MimeMultipart(); // 创建一个包含HTML内容的MimeBodyPart BodyPart html = new
	 * MimeBodyPart(); // 设置HTML内容 html.setContent(mailInfo.getContent(),
	 * "text/html; charset=utf-8"); mainPart.addBodyPart(html); //
	 * 将MiniMultipart对象设置为邮件内容 mailMessage.setContent(mainPart); // 发送邮件
	 * Transport.send(mailMessage); return true; } catch (MessagingException ex)
	 * { ex.printStackTrace(); } return false; }
	 */
}
