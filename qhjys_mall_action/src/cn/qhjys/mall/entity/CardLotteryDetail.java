package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CardLotteryDetail implements Serializable {
    private Long id;

    private Long userLotteryId;

    private String openId;

    private Long userId;

    private String forOpenId;

    private Long forUserId;

    private Long forOrderId;

    private Long cardTemplateId;

    private Integer statusCfg;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserLotteryId() {
        return userLotteryId;
    }

    public void setUserLotteryId(Long userLotteryId) {
        this.userLotteryId = userLotteryId;
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

    public String getForOpenId() {
        return forOpenId;
    }

    public void setForOpenId(String forOpenId) {
        this.forOpenId = forOpenId == null ? null : forOpenId.trim();
    }

    public Long getForUserId() {
        return forUserId;
    }

    public void setForUserId(Long forUserId) {
        this.forUserId = forUserId;
    }

    public Long getForOrderId() {
        return forOrderId;
    }

    public void setForOrderId(Long forOrderId) {
        this.forOrderId = forOrderId;
    }

    public Long getCardTemplateId() {
        return cardTemplateId;
    }

    public void setCardTemplateId(Long cardTemplateId) {
        this.cardTemplateId = cardTemplateId;
    }

    public Integer getStatusCfg() {
        return statusCfg;
    }

    public void setStatusCfg(Integer statusCfg) {
        this.statusCfg = statusCfg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}