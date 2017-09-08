package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class FqAcardUserLottery implements Serializable {
    private Long id;

    private Long acardRecordId;

    private Long userId;

    private Long acardPrizeId;

    private Date createTime;

    private String acardPrizeName;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAcardPrizeName() {
        return acardPrizeName;
    }

    public void setAcardPrizeName(String acardPrizeName) {
        this.acardPrizeName = acardPrizeName == null ? null : acardPrizeName.trim();
    }

    public Long getAcardId() {
        return acardId;
    }

    public void setAcardId(Long acardId) {
        this.acardId = acardId;
    }
}