package cn.qhjys.mall.entity;

import java.io.Serializable;

public class FqFinancialSupport implements Serializable {
    private Long id;

    private Long applyId;

    private String businessImages;

    private String idcardImages;

    private String storeImages;

    private String address;

    private String phoneNum;

    private String useInfo;

    private String bankcardNum;

    private String bankName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public String getBusinessImages() {
        return businessImages;
    }

    public void setBusinessImages(String businessImages) {
        this.businessImages = businessImages == null ? null : businessImages.trim();
    }

    public String getIdcardImages() {
        return idcardImages;
    }

    public void setIdcardImages(String idcardImages) {
        this.idcardImages = idcardImages == null ? null : idcardImages.trim();
    }

    public String getStoreImages() {
        return storeImages;
    }

    public void setStoreImages(String storeImages) {
        this.storeImages = storeImages == null ? null : storeImages.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getUseInfo() {
        return useInfo;
    }

    public void setUseInfo(String useInfo) {
        this.useInfo = useInfo == null ? null : useInfo.trim();
    }

    public String getBankcardNum() {
        return bankcardNum;
    }

    public void setBankcardNum(String bankcardNum) {
        this.bankcardNum = bankcardNum == null ? null : bankcardNum.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }
}