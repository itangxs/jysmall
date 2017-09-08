package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo implements Serializable {
    private Long id;

    private String orderNo;

    private Long userId;

    private Long payId;

    private Long couponId;

    private BigDecimal integral;

    private BigDecimal totamt;

    private Long delivId;

    private Date delivDate;

    private BigDecimal expfare;

    private Date createTime;

    private Integer enabled;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
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

    public BigDecimal getTotamt() {
        return totamt;
    }

    public void setTotamt(BigDecimal totamt) {
        this.totamt = totamt;
    }

    public Long getDelivId() {
        return delivId;
    }

    public void setDelivId(Long delivId) {
        this.delivId = delivId;
    }

    public Date getDelivDate() {
        return delivDate;
    }

    public void setDelivDate(Date delivDate) {
        this.delivDate = delivDate;
    }

    public BigDecimal getExpfare() {
        return expfare;
    }

    public void setExpfare(BigDecimal expfare) {
        this.expfare = expfare;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}