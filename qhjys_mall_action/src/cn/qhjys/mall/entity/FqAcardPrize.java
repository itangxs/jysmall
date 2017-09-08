package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class FqAcardPrize implements Serializable {
    private Long id;

    private Long acardId;

    private String prizeName;

    private BigDecimal prizeLine;

    private String prizeInfo;

    private String prizeImage;

    private Integer probability;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAcardId() {
        return acardId;
    }

    public void setAcardId(Long acardId) {
        this.acardId = acardId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public BigDecimal getPrizeLine() {
        return prizeLine;
    }

    public void setPrizeLine(BigDecimal prizeLine) {
        this.prizeLine = prizeLine;
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

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }
}