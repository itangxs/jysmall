package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashLog implements Serializable {
    private Long id;

    private String recordNo;

    private Long sendId;

    private Long reviewId;

    private BigDecimal amount;

    private Integer payType;

    private Integer payWay;

    private String bankno;

    private String businessCode;

    private String businessName;

    private BigDecimal sendBefor;

    private BigDecimal sendAfter;

    private BigDecimal reviewBefor;

    private BigDecimal reviewAfter;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo == null ? null : recordNo.trim();
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public BigDecimal getSendBefor() {
        return sendBefor;
    }

    public void setSendBefor(BigDecimal sendBefor) {
        this.sendBefor = sendBefor;
    }

    public BigDecimal getSendAfter() {
        return sendAfter;
    }

    public void setSendAfter(BigDecimal sendAfter) {
        this.sendAfter = sendAfter;
    }

    public BigDecimal getReviewBefor() {
        return reviewBefor;
    }

    public void setReviewBefor(BigDecimal reviewBefor) {
        this.reviewBefor = reviewBefor;
    }

    public BigDecimal getReviewAfter() {
        return reviewAfter;
    }

    public void setReviewAfter(BigDecimal reviewAfter) {
        this.reviewAfter = reviewAfter;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}