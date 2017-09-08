package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class SigninInfo implements Serializable {
    private Long id;

    private Long userId;

    private Date signinDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSigninDate() {
        return signinDate;
    }

    public void setSigninDate(Date signinDate) {
        this.signinDate = signinDate;
    }
}