package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqStoreRate implements Serializable {
    private Long id;

    private String rateName;

    private Long adminId;

    private String adminUsername;

    private BigDecimal wechatBaseRate;

    private BigDecimal wechatAppendRate;

    private BigDecimal wechatAppendMoney;

    private BigDecimal alipayBaseRate;

    private BigDecimal alipayAppendRate;

    private BigDecimal alipayAppendMoney;

    private BigDecimal qqpayBaseRate;

    private BigDecimal qqpayAppendRate;

    private BigDecimal qqpayAppendMoney;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName == null ? null : rateName.trim();
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername == null ? null : adminUsername.trim();
    }

    public BigDecimal getWechatBaseRate() {
        return wechatBaseRate;
    }

    public void setWechatBaseRate(BigDecimal wechatBaseRate) {
        this.wechatBaseRate = wechatBaseRate;
    }

    public BigDecimal getWechatAppendRate() {
        return wechatAppendRate;
    }

    public void setWechatAppendRate(BigDecimal wechatAppendRate) {
        this.wechatAppendRate = wechatAppendRate;
    }

    public BigDecimal getWechatAppendMoney() {
        return wechatAppendMoney;
    }

    public void setWechatAppendMoney(BigDecimal wechatAppendMoney) {
        this.wechatAppendMoney = wechatAppendMoney;
    }

    public BigDecimal getAlipayBaseRate() {
        return alipayBaseRate;
    }

    public void setAlipayBaseRate(BigDecimal alipayBaseRate) {
        this.alipayBaseRate = alipayBaseRate;
    }

    public BigDecimal getAlipayAppendRate() {
        return alipayAppendRate;
    }

    public void setAlipayAppendRate(BigDecimal alipayAppendRate) {
        this.alipayAppendRate = alipayAppendRate;
    }

    public BigDecimal getAlipayAppendMoney() {
        return alipayAppendMoney;
    }

    public void setAlipayAppendMoney(BigDecimal alipayAppendMoney) {
        this.alipayAppendMoney = alipayAppendMoney;
    }

    public BigDecimal getQqpayBaseRate() {
        return qqpayBaseRate;
    }

    public void setQqpayBaseRate(BigDecimal qqpayBaseRate) {
        this.qqpayBaseRate = qqpayBaseRate;
    }

    public BigDecimal getQqpayAppendRate() {
        return qqpayAppendRate;
    }

    public void setQqpayAppendRate(BigDecimal qqpayAppendRate) {
        this.qqpayAppendRate = qqpayAppendRate;
    }

    public BigDecimal getQqpayAppendMoney() {
        return qqpayAppendMoney;
    }

    public void setQqpayAppendMoney(BigDecimal qqpayAppendMoney) {
        this.qqpayAppendMoney = qqpayAppendMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}