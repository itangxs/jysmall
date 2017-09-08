package cn.qhjys.mall.vo;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

public class RebateOrderVo extends BaseVo{
	
	private Long id;

    private String orderNo;
    private Long storeId;
    private String storeName;
    private Long userId;
    private String openId;
    private Long sellerId;
    private String rebateName;
    
    private Integer type;
    
    private Integer sort;
    
    private Long couponId;
    private BigDecimal integral;
    private BigDecimal needPay;
    private String nickname;

    private BigDecimal rebate;
    private BigDecimal realPay;
    private Long rebateId;
    private BigDecimal noDiscount;
    private BigDecimal orderRate;

    private BigDecimal rateFee;
    private BigDecimal totamt;

    private Integer payType;



    private Date createTime;

    private Integer status;

    private Date payTime;
    
    


    private BigDecimal ortherRebate;

    private String storeLogo;
  

    private String headimgurl;

    private String weixinOrderNo;
    
    private Integer bankType;
    
    private Integer isCash;
    private Integer payNum;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getRebateName() {
		return rebateName;
	}

	public void setRebateName(String rebateName) {
		this.rebateName = rebateName;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

	public BigDecimal getOrtherRebate() {
		return ortherRebate;
	}

	public void setOrtherRebate(BigDecimal ortherRebate) {
		this.ortherRebate = ortherRebate;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreLogo() {
		return storeLogo;
	}

	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) throws UnsupportedEncodingException {
		this.nickname = URLDecoder.decode(nickname,"UTF-8");
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getWeixinOrderNo() {
		return weixinOrderNo;
	}

	public void setWeixinOrderNo(String weixinOrderNo) {
		this.weixinOrderNo = weixinOrderNo;
	}
	

	public Integer getBankType() {
		return bankType;
	}

	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Integer getIsCash() {
		return isCash;
	}

	public void setIsCash(Integer isCash) {
		this.isCash = isCash;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPayNum() {
		return payNum;
	}

	public void setPayNum(Integer payNum) {
		this.payNum = payNum;
	}
	
	
}
