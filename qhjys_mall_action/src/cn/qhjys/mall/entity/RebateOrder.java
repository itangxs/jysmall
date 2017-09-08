package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RebateOrder implements Serializable {
    private Long id;

    private String orderNo;

    private String openId;

    private Long rebateId;

    private Long couponId;

    private BigDecimal integral;

    private Integer payType;

    private BigDecimal needPay;

    private BigDecimal realPay;

    private BigDecimal totamt;

    private BigDecimal noDiscount;

    private Date createTime;

    private Integer status;

    private Date payTime;

    private String nickname;

    private String headimgurl;

    private String weixinOrderNo;

    private BigDecimal orderRate;

    private BigDecimal rateFee;

    private static final long serialVersionUID = 1L;

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
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Long getRebateId() {
        return rebateId;
    }

    public void setRebateId(Long rebateId) {
        this.rebateId = rebateId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getNeedPay() {
        return needPay;
    }

    public void setNeedPay(BigDecimal needPay) {
        this.needPay = needPay;
    }

    public BigDecimal getRealPay() {
        return realPay;
    }

    public void setRealPay(BigDecimal realPay) {
        this.realPay = realPay;
    }

    public BigDecimal getTotamt() {
        return totamt;
    }

    public void setTotamt(BigDecimal totamt) {
        this.totamt = totamt;
    }

    public BigDecimal getNoDiscount() {
        return noDiscount;
    }

    public void setNoDiscount(BigDecimal noDiscount) {
        this.noDiscount = noDiscount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getWeixinOrderNo() {
        return weixinOrderNo;
    }

    public void setWeixinOrderNo(String weixinOrderNo) {
        this.weixinOrderNo = weixinOrderNo == null ? null : weixinOrderNo.trim();
    }

    public BigDecimal getOrderRate() {
        return orderRate;
    }

    public void setOrderRate(BigDecimal orderRate) {
        this.orderRate = orderRate;
    }

    public BigDecimal getRateFee() {
        return rateFee;
    }

    public void setRateFee(BigDecimal rateFee) {
        this.rateFee = rateFee;
    }

	public RebateOrder(Long id, String orderNo, String openId, Long rebateId,
			Long couponId, BigDecimal integral, Integer payType,
			BigDecimal needPay, BigDecimal realPay, BigDecimal totamt,
			BigDecimal noDiscount, Date createTime, Integer status,
			Date payTime, String nickname, String headimgurl,
			String weixinOrderNo, BigDecimal orderRate, BigDecimal rateFee) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.openId = openId;
		this.rebateId = rebateId;
		this.couponId = couponId;
		this.integral = integral;
		this.payType = payType;
		this.needPay = needPay;
		this.realPay = realPay;
		this.totamt = totamt;
		this.noDiscount = noDiscount;
		this.createTime = createTime;
		this.status = status;
		this.payTime = payTime;
		this.nickname = nickname;
		this.headimgurl = headimgurl;
		this.weixinOrderNo = weixinOrderNo;
		this.orderRate = orderRate;
		this.rateFee = rateFee;
	}

	public RebateOrder() {
		super();
	}

	
	public RebateOrder(String orderNo,String openId,long rebateId,long couponId,BigDecimal integral,Integer payType
    		,BigDecimal needPay,BigDecimal realPay,BigDecimal totamt,BigDecimal noDiscount,Date createTime,int status, String nickname, String headimgurl){
    	    this.orderNo = orderNo;
    	    this.openId  = openId;
    	    this.rebateId  = rebateId;
    	    this.couponId  = couponId;
    	    this.integral  = integral;
    	    this.payType  = payType;
    	    this.needPay  = needPay;
    	    this.realPay  = realPay;
    	    this.totamt  = totamt;
    	    this.noDiscount  = noDiscount;
    	    this.createTime  = createTime;
    	    this.status  = status;
    		this.nickname = nickname;
    		this.headimgurl = headimgurl;
    }
    
}