package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;

/***
 * 
 * @author zengrong
 *
 */
public class BankVo  extends BaseVo{

	private Long id;
	private String name;//银行名称
	private String branch;//支行名称
	private Long userId;//用户编号
	private String cardholder;//持卡人姓名
	private String cardId;//身份证号码
	private String carkNum;//银行卡号
	private int status;//认证状态
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCarkNum() {
		return carkNum;
	}
	public void setCarkNum(String carkNum) {
		this.carkNum = carkNum;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
     
}