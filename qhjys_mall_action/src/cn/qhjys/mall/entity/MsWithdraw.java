package cn.qhjys.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 标题：民生-商户提现
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 上午11:09:33
 * 修改时间：
 *
 */
public class MsWithdraw {
    private Long id;
    //商家ID
    private Long sellerId;
    //店铺ID
    private Long storeId;
    //店铺名称
    private String storeName;
    //交易金额
    private BigDecimal payMoney;
    //费扣费金额
    private BigDecimal notFeeMoney;
    //已扣费金额
    private BigDecimal allFeeMoney;
    //手续费
    private BigDecimal rateFee;
    //创建时间
    private Date createDate;
    //提现金额
    private BigDecimal withdrawMoeny;
    //状态(0:未审核1:已审核2:提现中3:提现失败4:提现成功)
    private Integer state;
    //审核时间
    private Date examineDate;
    //提现时间
    private Date settDate;
    //提现的银行卡号
    private String bankCard;
    //请求流水号
    private String requestNo;
    //提现订单号
    private String orderNo;
    //应答码
    private String respCode;
    //应答码描述
    private String respDesc;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
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

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getNotFeeMoney() {
        return notFeeMoney;
    }

    public void setNotFeeMoney(BigDecimal notFeeMoney) {
        this.notFeeMoney = notFeeMoney;
    }

    public BigDecimal getAllFeeMoney() {
        return allFeeMoney;
    }

    public void setAllFeeMoney(BigDecimal allFeeMoney) {
        this.allFeeMoney = allFeeMoney;
    }

    public BigDecimal getRateFee() {
        return rateFee;
    }

    public void setRateFee(BigDecimal rateFee) {
        this.rateFee = rateFee;
    }

    public BigDecimal getWithdrawMoeny() {
        return withdrawMoeny;
    }

    public void setWithdrawMoeny(BigDecimal withdrawMoeny) {
        this.withdrawMoeny = withdrawMoeny;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExamineDate() {
        return examineDate;
    }

    public void setExamineDate(Date examineDate) {
        this.examineDate = examineDate;
    }

    public Date getSettDate() {
        return settDate;
    }

    public void setSettDate(Date settDate) {
        this.settDate = settDate;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
}