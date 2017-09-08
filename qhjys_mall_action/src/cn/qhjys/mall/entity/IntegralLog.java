package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IntegralLog implements Serializable {
    private Long id;

    private Long sendId;

    private Long reviewId;

    private Integer type;

    private String businessCode;

    private String businessName;

    private BigDecimal integral;

    private BigDecimal sendBefor;

    private BigDecimal sendAfter;

    private BigDecimal reviewBefor;

    private BigDecimal reviewAfter;

    private String remark;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}