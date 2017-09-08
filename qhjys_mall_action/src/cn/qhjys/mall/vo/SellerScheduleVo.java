package cn.qhjys.mall.vo;

import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

public class SellerScheduleVo extends BaseVo{
	//预约ID
	private Long id;
	//产品ID
	private Long productId;
	//订单详细ID
	private Long orderId;
	//商品名称
	private String title;
	//联系人
	private String name;
	//电话号码
	private String phone;
	//预约时间
	private Date createDate;
	//到店时间
	private Date reserTime;
	//人数
	private Integer reserNum;
	//内容
	private String content;
	//状态
	private Integer status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
