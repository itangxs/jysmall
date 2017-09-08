package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class WebsiteImg implements Serializable {
    private Long id;

    private String pcBanner;

    private String pcMenuMeis;

    private String pcMenuDiany;

    private String pcMenuGouw;

    private String pcMenuLvyou;

    private String pcMenuXiux;

    private String pcMenuLiren;

    private String pcMenuShengh;

    private String pcMenuLicai;

    private String pcMenuOther1;

    private String pcMenuOther2;

    private String appBanner1;

    private String appBanner2;

    private String appBanner3;

    private String appBanner4;

    private String appBanner5;

    private String appBanner6;

    private String appBanner7;

    private Date createDate;

    private Long adminId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPcBanner() {
        return pcBanner;
    }

    public void setPcBanner(String pcBanner) {
        this.pcBanner = pcBanner == null ? null : pcBanner.trim();
    }

    public String getPcMenuMeis() {
        return pcMenuMeis;
    }

    public void setPcMenuMeis(String pcMenuMeis) {
        this.pcMenuMeis = pcMenuMeis == null ? null : pcMenuMeis.trim();
    }

    public String getPcMenuDiany() {
        return pcMenuDiany;
    }

    public void setPcMenuDiany(String pcMenuDiany) {
        this.pcMenuDiany = pcMenuDiany == null ? null : pcMenuDiany.trim();
    }

    public String getPcMenuGouw() {
        return pcMenuGouw;
    }

    public void setPcMenuGouw(String pcMenuGouw) {
        this.pcMenuGouw = pcMenuGouw == null ? null : pcMenuGouw.trim();
    }

    public String getPcMenuLvyou() {
        return pcMenuLvyou;
    }

    public void setPcMenuLvyou(String pcMenuLvyou) {
        this.pcMenuLvyou = pcMenuLvyou == null ? null : pcMenuLvyou.trim();
    }

    public String getPcMenuXiux() {
        return pcMenuXiux;
    }

    public void setPcMenuXiux(String pcMenuXiux) {
        this.pcMenuXiux = pcMenuXiux == null ? null : pcMenuXiux.trim();
    }

    public String getPcMenuLiren() {
        return pcMenuLiren;
    }

    public void setPcMenuLiren(String pcMenuLiren) {
        this.pcMenuLiren = pcMenuLiren == null ? null : pcMenuLiren.trim();
    }

    public String getPcMenuShengh() {
        return pcMenuShengh;
    }

    public void setPcMenuShengh(String pcMenuShengh) {
        this.pcMenuShengh = pcMenuShengh == null ? null : pcMenuShengh.trim();
    }

    public String getPcMenuLicai() {
        return pcMenuLicai;
    }

    public void setPcMenuLicai(String pcMenuLicai) {
        this.pcMenuLicai = pcMenuLicai == null ? null : pcMenuLicai.trim();
    }

    public String getPcMenuOther1() {
        return pcMenuOther1;
    }

    public void setPcMenuOther1(String pcMenuOther1) {
        this.pcMenuOther1 = pcMenuOther1 == null ? null : pcMenuOther1.trim();
    }

    public String getPcMenuOther2() {
        return pcMenuOther2;
    }

    public void setPcMenuOther2(String pcMenuOther2) {
        this.pcMenuOther2 = pcMenuOther2 == null ? null : pcMenuOther2.trim();
    }

    public String getAppBanner1() {
        return appBanner1;
    }

    public void setAppBanner1(String appBanner1) {
        this.appBanner1 = appBanner1 == null ? null : appBanner1.trim();
    }

    public String getAppBanner2() {
        return appBanner2;
    }

    public void setAppBanner2(String appBanner2) {
        this.appBanner2 = appBanner2 == null ? null : appBanner2.trim();
    }

    public String getAppBanner3() {
        return appBanner3;
    }

    public void setAppBanner3(String appBanner3) {
        this.appBanner3 = appBanner3 == null ? null : appBanner3.trim();
    }

    public String getAppBanner4() {
        return appBanner4;
    }

    public void setAppBanner4(String appBanner4) {
        this.appBanner4 = appBanner4 == null ? null : appBanner4.trim();
    }

    public String getAppBanner5() {
        return appBanner5;
    }

    public void setAppBanner5(String appBanner5) {
        this.appBanner5 = appBanner5 == null ? null : appBanner5.trim();
    }

    public String getAppBanner6() {
        return appBanner6;
    }

    public void setAppBanner6(String appBanner6) {
        this.appBanner6 = appBanner6 == null ? null : appBanner6.trim();
    }

    public String getAppBanner7() {
        return appBanner7;
    }

    public void setAppBanner7(String appBanner7) {
        this.appBanner7 = appBanner7 == null ? null : appBanner7.trim();
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
}