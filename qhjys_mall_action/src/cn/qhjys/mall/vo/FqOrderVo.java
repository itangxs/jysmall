package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;

public class FqOrderVo{
	
	private static final long serialVersionUID = 1L;
	private Long id;

    private String orderNo;

    private Long userId;

    private BigDecimal totalAmount;

    private Date preordainDate;

    private Long rebateId;

    private Integer payType;

    private Integer status;

    private String contacts;

    private Integer contactsSex;

    private String phoneNum;

    private Long storeId;

    private String storeName;

    private Date createTime;

    private BigDecimal rebate;

    private BigDecimal storeRebate;

    private BigDecimal payAmount;

    private BigDecimal rebateAmount;

    private String peopleNum;

    private String coupon;

    private String wxOrderNo;

    private Date payTime;

    private String deskNo;
    
	private List<FqOrderDetail> orderDetails;
	
	

	public FqOrderVo() {
		super();
	}

	public FqOrderVo(Long id, String orderNo, Long userId,
			BigDecimal totalAmount, Date preordainDate, Long rebateId,
			Integer payType, Integer status, String contacts,
			Integer contactsSex, String phoneNum, Long storeId,
			String storeName, Date createTime, BigDecimal rebate,
			BigDecimal storeRebate, BigDecimal payAmount,
			BigDecimal rebateAmount, String peopleNum, String coupon,
			String wxOrderNo, Date payTime, String deskNo) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.preordainDate = preordainDate;
		this.rebateId = rebateId;
		this.payType = payType;
		this.status = status;
		this.contacts = contacts;
		this.contactsSex = contactsSex;
		this.phoneNum = phoneNum;
		this.storeId = storeId;
		this.storeName = storeName;
		this.createTime = createTime;
		this.rebate = rebate;
		this.storeRebate = storeRebate;
		this.payAmount = payAmount;
		this.rebateAmount = rebateAmount;
		this.peopleNum = peopleNum;
		this.coupon = coupon;
		this.wxOrderNo = wxOrderNo;
		this.payTime = payTime;
		this.deskNo = deskNo;
	}

	public FqOrderVo(FqOrder fqOrder) {
		super();
		this.id = fqOrder.getId();
		this.orderNo = fqOrder.getOrderNo();
		this.userId = fqOrder.getUserId();
		this.totalAmount = fqOrder.getTotalAmount();
		this.preordainDate = fqOrder.getPreordainDate();
		this.rebateId = fqOrder.getRebateId();
		this.payType = fqOrder.getPayType();
		this.status = fqOrder.getStatus();
		this.contacts = fqOrder.getContacts();
		this.contactsSex = fqOrder.getContactsSex();
		this.phoneNum = fqOrder.getPhoneNum();
		this.storeId = fqOrder.getStoreId();
		this.storeName = fqOrder.getStoreName();
		this.createTime = fqOrder.getCreateTime();
		this.rebate = fqOrder.getRebate();
		this.storeRebate = fqOrder.getStoreRebate();
		this.payAmount = fqOrder.getPayAmount();
		this.rebateAmount = fqOrder.getRebateAmount();
		this.peopleNum = fqOrder.getPeopleNum();
		this.coupon = fqOrder.getCoupon();
		this.wxOrderNo = fqOrder.getWxOrderNo();
		this.payTime = fqOrder.getPayTime();
		this.deskNo = fqOrder.getDeskNo();
	}

	public List<FqOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<FqOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPreordainDate() {
		return preordainDate;
	}

	public void setPreordainDate(Date preordainDate) {
		this.preordainDate = preordainDate;
	}

	public Long getRebateId() {
		return rebateId;
	}

	public void setRebateId(Long rebateId) {
		this.rebateId = rebateId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Integer getContactsSex() {
		return contactsSex;
	}

	public void setContactsSex(Integer contactsSex) {
		this.contactsSex = contactsSex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public BigDecimal getStoreRebate() {
		return storeRebate;
	}

	public void setStoreRebate(BigDecimal storeRebate) {
		this.storeRebate = storeRebate;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public String getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getWxOrderNo() {
		return wxOrderNo;
	}

	public void setWxOrderNo(String wxOrderNo) {
		this.wxOrderNo = wxOrderNo;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getDeskNo() {
		return deskNo;
	}

	public void setDeskNo(String deskNo) {
		this.deskNo = deskNo;
	}
	
}
