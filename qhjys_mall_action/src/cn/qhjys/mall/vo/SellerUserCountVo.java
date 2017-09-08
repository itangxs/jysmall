package cn.qhjys.mall.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SellerUserCountVo implements Serializable{
	
	
	private Integer totalUser;
	private Integer onceUser;
	private Integer twiceUser;
	private Integer thirdUser;
	private Integer fourUser;
	private Integer newUser;
	public Integer getTotalUser() {
		return totalUser;
	}
	public void setTotalUser(Integer totalUser) {
		this.totalUser = totalUser;
	}
	public Integer getOnceUser() {
		return onceUser;
	}
	public void setOnceUser(Integer onceUser) {
		this.onceUser = onceUser;
	}
	public Integer getTwiceUser() {
		return twiceUser;
	}
	public void setTwiceUser(Integer twiceUser) {
		this.twiceUser = twiceUser;
	}
	public Integer getThirdUser() {
		return thirdUser;
	}
	public void setThirdUser(Integer thirdUser) {
		this.thirdUser = thirdUser;
	}
	public Integer getFourUser() {
		return fourUser;
	}
	public void setFourUser(Integer fourUser) {
		this.fourUser = fourUser;
	}
	public Integer getNewUser() {
		return newUser;
	}
	public void setNewUser(Integer newUser) {
		this.newUser = newUser;
	}
	public SellerUserCountVo(Integer totalUser, Integer onceUser,
			Integer twiceUser, Integer thirdUser, Integer fourUser,
			Integer newUser) {
		super();
		this.totalUser = totalUser;
		this.onceUser = onceUser;
		this.twiceUser = twiceUser;
		this.thirdUser = thirdUser;
		this.fourUser = fourUser;
		this.newUser = newUser;
	}
	public SellerUserCountVo() {
		super();
	}
	
	
}
