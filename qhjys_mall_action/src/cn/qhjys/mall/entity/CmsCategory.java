package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class CmsCategory implements Serializable {
    private Long id;

    private String name;

    private String shuoming;

    private Integer yezi;

    private Long fatherId;

    private Date createDate;

    private Long adminId;

    private Integer paixuhao;

    private Integer enabled;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShuoming() {
        return shuoming;
    }

    public void setShuoming(String shuoming) {
        this.shuoming = shuoming == null ? null : shuoming.trim();
    }

    public Integer getYezi() {
        return yezi;
    }

    public void setYezi(Integer yezi) {
        this.yezi = yezi;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Integer getPaixuhao() {
        return paixuhao;
    }

    public void setPaixuhao(Integer paixuhao) {
        this.paixuhao = paixuhao;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}