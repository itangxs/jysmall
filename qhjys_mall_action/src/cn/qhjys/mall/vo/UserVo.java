package cn.qhjys.mall.vo;

import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

/**
 * 会员信息Vo
 * 
 * @author JiangXiaoPing
 */
public class UserVo  extends BaseVo{
	// 用户编号
	private Long id;
	// 用户账号
	private String userName;
	// 密码
	private String passWord;
	// 支付密码
	private String payWode;
	// 会员等级
	private String level;
	// 用户昵称
	private String nickName;
	// 真实姓名
	private String realName;
	// 出生日期
	private Date birthday;
	// 性别
	private int sex;
	// 头像
	private String avatar;
	// 电话
	private String phone;
	// 邮件
	private String email;
	// 省份
	private Long province;
	// 城市
	private Long city;
	// 区域
	private Long area;
	// 地址
	private String address;
	// 注册时间
	private Date regisDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPayWode() {
		return payWode;
	}

	public void setPayWode(String payWode) {
		this.payWode = payWode;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegisDate() {
		return regisDate;
	}

	public void setRegisDate(Date regisDate) {
		this.regisDate = regisDate;
	}
}