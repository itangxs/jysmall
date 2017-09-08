package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;


/***
 * 
 * @author zengrong
 *
 */
public class UserExpandVo  extends BaseVo{

	private Long id;
	private Long userId;// 用户编号
	private int sex;// 性别
	private String birthday;// 生日
	private int identity;// 用户身份：0 保密，1 学生，2 在职，3 自由职业，4 其它
	private int maritalStatus; // 婚姻状况：0 保密，1 单身，2 恋爱中，3 已婚，4 保密
	private String interest;// 爱好：0保密，1美食，2电影，3购物，4旅游，5休闲娱乐,6丽人,7生活服务

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public int getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}
}