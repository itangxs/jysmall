package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FqRedpackDetail implements Serializable {
    private Long id;

    private Long redpackId;

    private BigDecimal maxAmount;

    private BigDecimal minAmount;

    private Integer type;

    private Integer probability;

    private BigDecimal maxMoney;

    private BigDecimal minMoney;

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

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public BigDecimal getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }
}