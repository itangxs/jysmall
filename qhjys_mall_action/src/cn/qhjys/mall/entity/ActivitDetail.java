package cn.qhjys.mall.entity;

import java.io.Serializable;

public class ActivitDetail implements Serializable {
    private Long id;

    private Long activitId;

    private Long sellerId;

    private Long prodId;

    private Long adminId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivitId() {
        return activitId;
    }

    public void setActivitId(Long activitId) {
        this.activitId = activitId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
}