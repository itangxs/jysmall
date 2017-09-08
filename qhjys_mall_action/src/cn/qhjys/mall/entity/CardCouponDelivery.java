package cn.qhjys.mall.entity;

import java.io.Serializable;

public class CardCouponDelivery implements Serializable {
    private Long id;

    private Long sellerId;

    private Integer pushNum;

    private Integer peripheralStatus;

    private Integer proprietaryStatus;

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

    public Integer getPushNum() {
        return pushNum;
    }

    public void setPushNum(Integer pushNum) {
        this.pushNum = pushNum;
    }

    public Integer getPeripheralStatus() {
        return peripheralStatus;
    }

    public void setPeripheralStatus(Integer peripheralStatus) {
        this.peripheralStatus = peripheralStatus;
    }

    public Integer getProprietaryStatus() {
        return proprietaryStatus;
    }

    public void setProprietaryStatus(Integer proprietaryStatus) {
        this.proprietaryStatus = proprietaryStatus;
    }
}