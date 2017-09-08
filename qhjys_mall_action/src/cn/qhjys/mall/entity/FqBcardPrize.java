package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FqBcardPrize implements Serializable {
    private Long id;

    private Long bcardId;

    private String prizeName;

    private String prizeInfo;

    private String prizeImage;

    private BigDecimal prizeLine;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBcardId() {
        return bcardId;
    }

    public void setBcardId(Long bcardId) {
        this.bcardId = bcardId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeInfo() {
        return prizeInfo;
    }

    public void setPrizeInfo(String prizeInfo) {
        this.prizeInfo = prizeInfo == null ? null : prizeInfo.trim();
    }

    public String getPrizeImage() {
        return prizeImage;
    }

    public void setPrizeImage(String prizeImage) {
        this.prizeImage = prizeImage == null ? null : prizeImage.trim();
    }

    public BigDecimal getPrizeLine() {
        return prizeLine;
    }

    public void setPrizeLine(BigDecimal prizeLine) {
        this.prizeLine = prizeLine;
    }
}