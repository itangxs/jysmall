package cn.qhjys.mall.entity;

import java.io.Serializable;

public class CardCouponDeliveryTemplateRelation implements Serializable {
    private Long id;

    private Long sellerId;

    private Long cardCouponDeliveryId;

    private Long cardCouponTemplateId;

    private Integer statusCfg;

    private Integer sort;

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

    public Long getCardCouponDeliveryId() {
        return cardCouponDeliveryId;
    }

    public void setCardCouponDeliveryId(Long cardCouponDeliveryId) {
        this.cardCouponDeliveryId = cardCouponDeliveryId;
    }

    public Long getCardCouponTemplateId() {
        return cardCouponTemplateId;
    }

    public void setCardCouponTemplateId(Long cardCouponTemplateId) {
        this.cardCouponTemplateId = cardCouponTemplateId;
    }

    public Integer getStatusCfg() {
        return statusCfg;
    }

    public void setStatusCfg(Integer statusCfg) {
        this.statusCfg = statusCfg;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}