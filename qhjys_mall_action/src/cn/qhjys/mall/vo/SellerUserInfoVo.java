package cn.qhjys.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class SellerUserInfoVo implements Serializable{
	
	private Integer conNum;
	private BigDecimal conMoney;
	private String nickName;
	private String portrait;
	private Date beTime;
	private String openId;
	public Integer getConNum() {
		return conNum;
	}
	public void setConNum(Integer conNum) {
		this.conNum = conNum;
	}
	public BigDecimal getConMoney() {
		return conMoney;
	}
	public void setConMoney(BigDecimal conMoney) {
		this.conMoney = conMoney;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPortrait() {
		return portrait;
	}
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public Date getBeTime() {
		return beTime;
	}
	public void setBeTime(Date beTime) {
		this.beTime = beTime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public SellerUserInfoVo(Integer conNum, BigDecimal conMoney,
			String nickName, String portrait, Date beTime, String openId) {
		super();
		this.conNum = conNum;
		this.conMoney = conMoney;
		this.nickName = nickName;
		this.portrait = portrait;
		this.beTime = beTime;
		this.openId = openId;
	}
	public SellerUserInfoVo() {
		super();
	} 

}
