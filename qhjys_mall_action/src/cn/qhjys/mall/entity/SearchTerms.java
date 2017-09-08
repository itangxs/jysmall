package cn.qhjys.mall.entity;

import java.io.Serializable;

public class SearchTerms implements Serializable {
    private Long prodId;

    private Long category;

    private String district;

    private Integer number;

    private static final long serialVersionUID = 1L;

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}