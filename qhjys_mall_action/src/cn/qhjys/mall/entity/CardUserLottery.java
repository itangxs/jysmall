package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CardUserLottery implements Serializable {
    private Long id;

    private String openId;

    private Long userId;

    private Long cardDeliveryId;

    private Integer statusCfg;

    private Integer status;

    private Date createTime;

    private Integer fromWhere;

    private Long sellerId;

    private Long orderId;

    private Integer shareNum;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardDeliveryId() {
        return cardDeliveryId;
    }

    public void setCardDeliveryId(Long cardDeliveryId) {
        this.cardDeliveryId = cardDeliveryId;
    }

    public Integer getStatusCfg() {
        return statusCfg;
    }

    public void setStatusCfg(Integer statusCfg) {
        this.statusCfg = statusCfg;
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

    public Integer getFromWhere() {
        return fromWhere;
    }

    public void setFromWhere(Integer fromWhere) {
        this.fromWhere = fromWhere;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }
}