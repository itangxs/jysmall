package cn.qhjys.mall.entity;

import java.io.Serializable;

public class CardDeliveryProperty implements Serializable {
    private Long id;

    private Integer deliverType;

    private Integer cardReceiveMin;

    private Integer cardReceiveMax;

    private Integer cardPushMin;

    private Integer cardPushMax;

    private Integer cardShareMin;

    private Integer cardShareMax;

    private Integer cardShareReceiveMin;

    private Integer cardShareReceiveMax;

    private Integer firstRankProbability;

    private Integer secondRankProbability;

    private Integer thirdRankProbability;

    private Integer fourthRankProbability;

    private Integer deliveryNum;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    public Integer getCardReceiveMin() {
        return cardReceiveMin;
    }

    public void setCardReceiveMin(Integer cardReceiveMin) {
        this.cardReceiveMin = cardReceiveMin;
    }

    public Integer getCardReceiveMax() {
        return cardReceiveMax;
    }

    public void setCardReceiveMax(Integer cardReceiveMax) {
        this.cardReceiveMax = cardReceiveMax;
    }

    public Integer getCardPushMin() {
        return cardPushMin;
    }

    public void setCardPushMin(Integer cardPushMin) {
        this.cardPushMin = cardPushMin;
    }

    public Integer getCardPushMax() {
        return cardPushMax;
    }

    public void setCardPushMax(Integer cardPushMax) {
        this.cardPushMax = cardPushMax;
    }

    public Integer getCardShareMin() {
        return cardShareMin;
    }

    public void setCardShareMin(Integer cardShareMin) {
        this.cardShareMin = cardShareMin;
    }

    public Integer getCardShareMax() {
        return cardShareMax;
    }

    public void setCardShareMax(Integer cardShareMax) {
        this.cardShareMax = cardShareMax;
    }

    public Integer getCardShareReceiveMin() {
        return cardShareReceiveMin;
    }

    public void setCardShareReceiveMin(Integer cardShareReceiveMin) {
        this.cardShareReceiveMin = cardShareReceiveMin;
    }

    public Integer getCardShareReceiveMax() {
        return cardShareReceiveMax;
    }

    public void setCardShareReceiveMax(Integer cardShareReceiveMax) {
        this.cardShareReceiveMax = cardShareReceiveMax;
    }

    public Integer getFirstRankProbability() {
        return firstRankProbability;
    }

    public void setFirstRankProbability(Integer firstRankProbability) {
        this.firstRankProbability = firstRankProbability;
    }

    public Integer getSecondRankProbability() {
        return secondRankProbability;
    }

    public void setSecondRankProbability(Integer secondRankProbability) {
        this.secondRankProbability = secondRankProbability;
    }

    public Integer getThirdRankProbability() {
        return thirdRankProbability;
    }

    public void setThirdRankProbability(Integer thirdRankProbability) {
        this.thirdRankProbability = thirdRankProbability;
    }

    public Integer getFourthRankProbability() {
        return fourthRankProbability;
    }

    public void setFourthRankProbability(Integer fourthRankProbability) {
        this.fourthRankProbability = fourthRankProbability;
    }

    public Integer getDeliveryNum() {
        return deliveryNum;
    }

    public void setDeliveryNum(Integer deliveryNum) {
        this.deliveryNum = deliveryNum;
    }
}