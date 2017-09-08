package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;

/***
 * 商家信息vo
 * 
 * @author zengrong
 *
 */
public class SellerVo extends BaseVo {
	private Long sellerId;// 商家编号
	private String realname;// 商家名称
	private String userName;// 用户名
	private String phone;// 商家手机号码
	private String email;// 商家邮箱
	private String password;// 账户密码
	private String question;// 密保问题
	private String answer;// 密保答案
	private String tradetime;// 交易时间
	private double reviewBefor;// 当前存款
	private double reviewAlfter;// 剩余金额
	private double amount;// 变更金额
	private Integer payType;// 支付类型
	private String businessCode;// 交易备注

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public double getReviewBefor() {
		return reviewBefor;
	}

	public void setReviewBefor(double reviewBefor) {
		this.reviewBefor = reviewBefor;
	}

	public double getReviewAlfter() {
		return reviewAlfter;
	}

	public void setReviewAlfter(double reviewAlfter) {
		this.reviewAlfter = reviewAlfter;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

}
