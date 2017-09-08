package cn.qhjys.mall.entity;

import java.io.Serializable;

public class FqRedpackTime implements Serializable {
    private Long id;

    private Long redpackId;

    private Long beginTime;

    private Long endTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRedpackId() {
        return redpackId;
    }

    public void setRedpackId(Long redpackId) {
        this.redpackId = redpackId;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}