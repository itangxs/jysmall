package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserRefund implements Serializable {
    private Long id;

    private Long userId;

    private Long detailId;

    private BigDecimal refundPrice;

    private BigDecimal refundIntegral;

    private String refundReason;

    private String rejectReason;

    private String images;

    private String platformAnswer;

    private Integer status;

    private Date createTime;

    private static final long serialVersionUID = 1L;

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

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public BigDecimal getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(BigDecimal refundPrice) {
        this.refundPrice = refundPrice;
    }

    public BigDecimal getRefundIntegral() {
        return refundIntegral;
    }

    public void setRefundIntegral(BigDecimal refundIntegral) {
        this.refundIntegral = refundIntegral;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason == null ? null : refundReason.trim();
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getPlatformAnswer() {
        return platformAnswer;
    }

    public void setPlatformAnswer(String platformAnswer) {
        this.platformAnswer = platformAnswer == null ? null : platformAnswer.trim();
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