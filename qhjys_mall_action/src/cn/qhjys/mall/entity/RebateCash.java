package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class RebateCash implements Serializable {
    private Long id;

    private Long sellerId;

    private Long storeId;

    private Long rebateId;

    private BigDecimal integralTotal;

    private BigDecimal needTotal;

    private BigDecimal realTotal;

    private BigDecimal totamtTotal;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getRebateId() {
        return rebateId;
    }

    public void setRebateId(Long rebateId) {
        this.rebateId = rebateId;
    }

    public BigDecimal getIntegralTotal() {
        return integralTotal;
    }

    public void setIntegralTotal(BigDecimal integralTotal) {
        this.integralTotal = integralTotal;
    }

    public BigDecimal getNeedTotal() {
        return needTotal;
    }

    public void setNeedTotal(BigDecimal needTotal) {
        this.needTotal = needTotal;
    }

    public BigDecimal getRealTotal() {
        return realTotal;
    }

    public void setRealTotal(BigDecimal realTotal) {
        this.realTotal = realTotal;
    }

    public BigDecimal getTotamtTotal() {
        return totamtTotal;
    }

    public void setTotamtTotal(BigDecimal totamtTotal) {
        this.totamtTotal = totamtTotal;
    }
}