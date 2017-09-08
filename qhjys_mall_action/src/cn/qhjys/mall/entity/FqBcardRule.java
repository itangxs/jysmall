package cn.qhjys.mall.entity;

import java.io.Serializable;

public class FqBcardRule implements Serializable {
    private Long id;

    private Long bcardId;

    private Long cityId;

    private Long districtId;

    private Long areaId;

    private Long industryId;

    private Long industryDetailId;

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public Long getIndustryDetailId() {
        return industryDetailId;
    }

    public void setIndustryDetailId(Long industryDetailId) {
        this.industryDetailId = industryDetailId;
    }
}