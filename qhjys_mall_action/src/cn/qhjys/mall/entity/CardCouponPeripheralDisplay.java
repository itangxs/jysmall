package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CardCouponPeripheralDisplay implements Serializable {
    private Long id;

    private Long sellerId;

    private Integer peripheralDisplayNum;

    private Date createTime;

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

    public Integer getPeripheralDisplayNum() {
        return peripheralDisplayNum;
    }

    public void setPeripheralDisplayNum(Integer peripheralDisplayNum) {
        this.peripheralDisplayNum = peripheralDisplayNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}