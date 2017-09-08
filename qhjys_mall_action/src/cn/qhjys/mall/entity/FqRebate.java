package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FqRebate implements Serializable {
    private Long id;

    private BigDecimal rebate;

    private String rebateInfo;

    private String beginTime;

    private String endTime;

    private Long storeId;

    private Integer enable;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRebate() {
        return rebate;
    }

    public void setRebate(BigDecimal rebate) {
        this.rebate = rebate;
    }

    public String getRebateInfo() {
        return rebateInfo;
    }

    public void setRebateInfo(String rebateInfo) {
        this.rebateInfo = rebateInfo == null ? null : rebateInfo.trim();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}