package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CardDeliveryCount implements Serializable {
    private Long id;

    private Long cardTemplateId;

    private Integer deliverType;

    private Integer cardReceiveDisplay;

    private Integer cardReceiveTrue;

    private Integer cardPushDisplay;

    private Integer cardPushTrue;

    private Integer cardShareDisplay;

    private Integer cardShareTrue;

    private Integer cardShareReceiveDisplay;

    private Integer cardShareReceiveTrue;

    private Date countDate;

    private Long sellerId;

    private Integer writeOff;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardTemplateId() {
        return cardTemplateId;
    }

    public void setCardTemplateId(Long cardTemplateId) {
        this.cardTemplateId = cardTemplateId;
    }

    public Integer getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(Integer deliverType) {
        this.deliverType = deliverType;
    }

    public Integer getCardReceiveDisplay() {
        return cardReceiveDisplay;
    }

    public void setCardReceiveDisplay(Integer cardReceiveDisplay) {
        this.cardReceiveDisplay = cardReceiveDisplay;
    }

    public Integer getCardReceiveTrue() {
        return cardReceiveTrue;
    }

    public void setCardReceiveTrue(Integer cardReceiveTrue) {
        this.cardReceiveTrue = cardReceiveTrue;
    }

    public Integer getCardPushDisplay() {
        return cardPushDisplay;
    }

    public void setCardPushDisplay(Integer cardPushDisplay) {
        this.cardPushDisplay = cardPushDisplay;
    }

    public Integer getCardPushTrue() {
        return cardPushTrue;
    }

    public void setCardPushTrue(Integer cardPushTrue) {
        this.cardPushTrue = cardPushTrue;
    }

    public Integer getCardShareDisplay() {
        return cardShareDisplay;
    }

    public void setCardShareDisplay(Integer cardShareDisplay) {
        this.cardShareDisplay = cardShareDisplay;
    }

    public Integer getCardShareTrue() {
        return cardShareTrue;
    }

    public void setCardShareTrue(Integer cardShareTrue) {
        this.cardShareTrue = cardShareTrue;
    }

    public Integer getCardShareReceiveDisplay() {
        return cardShareReceiveDisplay;
    }

    public void setCardShareReceiveDisplay(Integer cardShareReceiveDisplay) {
        this.cardShareReceiveDisplay = cardShareReceiveDisplay;
    }

    public Integer getCardShareReceiveTrue() {
        return cardShareReceiveTrue;
    }

    public void setCardShareReceiveTrue(Integer cardShareReceiveTrue) {
        this.cardShareReceiveTrue = cardShareReceiveTrue;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getWriteOff() {
        return writeOff;
    }

    public void setWriteOff(Integer writeOff) {
        this.writeOff = writeOff;
    }
}