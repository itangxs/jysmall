package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CouponsInfo implements Serializable {
    private Long id;

    private Long lotteryId;

    private Long userLotteryId;

    private String openId;

    private Integer rank;

    private Integer status;

    private Date endTime;

    private Date createTime;

    private String couponsImage;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLotteryId() {
        return lotteryId;
    }

    public void setLotteryId(Long lotteryId) {
        this.lotteryId = lotteryId;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCouponsImage() {
        return couponsImage;
    }

    public void setCouponsImage(String couponsImage) {
        this.couponsImage = couponsImage == null ? null : couponsImage.trim();
    }
}