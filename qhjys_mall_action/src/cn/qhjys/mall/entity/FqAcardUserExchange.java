package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class FqAcardUserExchange implements Serializable {
    private Long id;

    private Long acardRecordId;

    private Long userId;

    private Long acardPrizeId;

    private Long storeId;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date createTime;

    private Long acardId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcardRecordId() {
        return acardRecordId;
    }

    public void setAcardRecordId(Long acardRecordId) {
        this.acardRecordId = acardRecordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAcardPrizeId() {
        return acardPrizeId;
    }

    public void setAcardPrizeId(Long acardPrizeId) {
        this.acardPrizeId = acardPrizeId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Long getAcardId() {
        return acardId;
    }

    public void setAcardId(Long acardId) {
        this.acardId = acardId;
    }
}