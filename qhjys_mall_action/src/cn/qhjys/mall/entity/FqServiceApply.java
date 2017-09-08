package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class FqServiceApply implements Serializable {
    private Long id;

    private Integer applyType;

    private Long sellerId;

    private Long storeId;

    private String storeName;

    private Integer applyStyleId;

    private String applyStyle;

    private Date createTime;

    private Integer status;

    private String applyInfo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Integer getApplyStyleId() {
        return applyStyleId;
    }

    public void setApplyStyleId(Integer applyStyleId) {
        this.applyStyleId = applyStyleId;
    }

    public String getApplyStyle() {
        return applyStyle;
    }

    public void setApplyStyle(String applyStyle) {
        this.applyStyle = applyStyle == null ? null : applyStyle.trim();
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

    public String getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(String applyInfo) {
        this.applyInfo = applyInfo == null ? null : applyInfo.trim();
    }
}