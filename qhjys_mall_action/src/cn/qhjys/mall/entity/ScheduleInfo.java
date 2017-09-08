package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class ScheduleInfo implements Serializable {
    private Long id;

    private Long userId;

    private Long prodId;

    private Long detailId;

    private Integer reserNum;

    private Date reserTime;

    private String reserPhone;

    private String reserMan;

    private Long sellerId;

    private String content;

    private Date createTime;

    private Integer status;

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

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Integer getReserNum() {
        return reserNum;
    }

    public void setReserNum(Integer reserNum) {
        this.reserNum = reserNum;
    }

    public Date getReserTime() {
        return reserTime;
    }

    public void setReserTime(Date reserTime) {
        this.reserTime = reserTime;
    }

    public String getReserPhone() {
        return reserPhone;
    }

    public void setReserPhone(String reserPhone) {
        this.reserPhone = reserPhone == null ? null : reserPhone.trim();
    }

    public String getReserMan() {
        return reserMan;
    }

    public void setReserMan(String reserMan) {
        this.reserMan = reserMan == null ? null : reserMan.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}