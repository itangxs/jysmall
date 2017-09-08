package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class FqWithdrawChangeinfo implements Serializable {
    private Long id;

    private Integer statusBefore;

    private Integer statusAfter;

    private Date changeDate;

    private Date createTime;

    private Long sellerId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatusBefore() {
        return statusBefore;
    }

    public void setStatusBefore(Integer statusBefore) {
        this.statusBefore = statusBefore;
    }

    public Integer getStatusAfter() {
        return statusAfter;
    }

    public void setStatusAfter(Integer statusAfter) {
        this.statusAfter = statusAfter;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}