package cn.qhjys.mall.vo;

import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

@SuppressWarnings("serial")
public class ProdSchedule extends BaseVo {
	// 记录编号
	private Long id;
	// 用户编号
	private Long userId;
	// 预定人
	private String userName;
	// 商品编号
	private Long prodId;
	// 商品名称
	private String prodName;
	// 商品图片
	private String prodImg;
	// 联系人
	private String reserMan;
	// 联系电话
	private String reserPhone;
	// 预定时间
	private Date reserTime;
	// 预定人数
	private Integer reserNum;
	// 预定状态
	private Integer status;
	// 创建时间
	private Date createTime;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdImg() {
		return prodImg;
	}

	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}

	public String getReserMan() {
		return reserMan;
	}

	public void setReserMan(String reserMan) {
		this.reserMan = reserMan;
	}

	public String getReserPhone() {
		return reserPhone;
	}

	public void setReserPhone(String reserPhone) {
		this.reserPhone = reserPhone;
	}

	public Date getReserTime() {
		return reserTime;
	}

	public void setReserTime(Date reserTime) {
		this.reserTime = reserTime;
	}

	public Integer getReserNum() {
		return reserNum;
	}

	public void setReserNum(Integer reserNum) {
		this.reserNum = reserNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}