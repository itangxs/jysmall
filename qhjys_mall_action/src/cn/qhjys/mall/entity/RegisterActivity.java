package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class RegisterActivity implements Serializable {
    private Long id;

    private Date beginTime;

    private Date endTime;

    private Integer commonCoupon;

    private Integer storeCoupon;

    private Integer enabled;

    private Integer couponValidity;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCommonCoupon() {
        return commonCoupon;
    }

    public void setCommonCoupon(Integer commonCoupon) {
        this.commonCoupon = commonCoupon;
    }

    public Integer getStoreCoupon() {
        return storeCoupon;
    }

    public void setStoreCoupon(Integer storeCoupon) {
        this.storeCoupon = storeCoupon;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getCouponValidity() {
        return couponValidity;
    }

    public void setCouponValidity(Integer couponValidity) {
        this.couponValidity = couponValidity;
    }
}