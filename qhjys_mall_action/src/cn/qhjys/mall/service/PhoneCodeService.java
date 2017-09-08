package cn.qhjys.mall.service;

/***
 * 短信验证
 * 
 * @author LiXiang
 *
 */
public interface PhoneCodeService {

	/**
	 * 判断是否连续发送
	 * 
	 * @param phone
	 *            手机号
	 * @return
	 */
	boolean canDoPhoneCode(String phone) throws Exception;

	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 *            手机号
	 * @param captcha
	 *            IP地址
	 * @param ip
	 *            IP地址
	 * @return
	 */
	boolean insertPhondCode(String phone, String captcha, String ip) throws Exception;
}