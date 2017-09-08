package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqStoreLoan implements Serializable {
    private Long id;

    private Long storeId;

    private String storeName;

    private Long sellerId;

    private BigDecimal loanTotal;

    private BigDecimal norepayment;

    private BigDecimal interestTotal;

    private Integer status;

    private Integer loanCycle;

    private Date loanDate;

    private Date endDate;

    private String bankName;

    private String cardAccount;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public BigDecimal getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(BigDecimal loanTotal) {
        this.loanTotal = loanTotal;
    }

    public BigDecimal getNorepayment() {
        return norepayment;
    }

    public void setNorepayment(BigDecimal norepayment) {
        this.norepayment = norepayment;
    }

    public BigDecimal getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(BigDecimal interestTotal) {
        this.interestTotal = interestTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLoanCycle() {
        return loanCycle;
    }

    public void setLoanCycle(Integer loanCycle) {
        this.loanCycle = loanCycle;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount == null ? null : cardAccount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}