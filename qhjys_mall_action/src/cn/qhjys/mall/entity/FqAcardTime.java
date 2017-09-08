package cn.qhjys.mall.entity;

import java.io.Serializable;

public class FqAcardTime implements Serializable {
    private Long id;

    private Long acardId;

    private Integer startTime;

    private Integer stopTime;

    private Long storeId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcardId() {
        return acardId;
    }

    public void setAcardId(Long acardId) {
        this.acardId = acardId;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public void setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}