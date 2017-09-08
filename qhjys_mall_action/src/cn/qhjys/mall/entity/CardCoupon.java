package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CardCoupon implements Serializable {
    private Long id;

    private String code;

    private String zxingImg;

    private Date validityStarttime;

    private Date validityEndtime;

    private Integer validityUseStarttime;

    private Integer validityUserEndtime;

    private BigDecimal useOverAmount;

    private Date receiveDate;

    private Date validateDate;

    private Integer validateCfg;

    private Integer getWay;

    private Integer statusCfg;

    private Long userId;

    private String openId;
    
    private Long sellerId;

    private Long templateId;

    private String templateCouponName;

    private Integer templateCouponCfg;

    private String templateCouponAmount;
    
    private String templateUseExplain;

    private Long storeId;

    private String storeName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getZxingImg() {
        return zxingImg;
    }

    public void setZxingImg(String zxingImg) {
        this.zxingImg = zxingImg == null ? null : zxingImg.trim();
    }

    public Date getValidityStarttime() {
        return validityStarttime;
    }

    public void setValidityStarttime(Date validityStarttime) {
        this.validityStarttime = validityStarttime;
    }

    public Date getValidityEndtime() {
        return validityEndtime;
    }

    public void setValidityEndtime(Date validityEndtime) {
        this.validityEndtime = validityEndtime;
    }

    public Integer getValidityUseStarttime() {
        return validityUseStarttime;
    }

    public void setValidityUseStarttime(Integer validityUseStarttime) {
        this.validityUseStarttime = validityUseStarttime;
    }

    public Integer getValidityUserEndtime() {
        return validityUserEndtime;
    }

    public void setValidityUserEndtime(Integer validityUserEndtime) {
        this.validityUserEndtime = validityUserEndtime;
    }

    public BigDecimal getUseOverAmount() {
        return useOverAmount;
    }

    public void setUseOverAmount(BigDecimal useOverAmount) {
        this.useOverAmount = useOverAmount;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }

    public Integer getValidateCfg() {
        return validateCfg;
    }

    public void setValidateCfg(Integer validateCfg) {
        this.validateCfg = validateCfg;
    }

    public Integer getGetWay() {
        return getWay;
    }

    public void setGetWay(Integer getWay) {
        this.getWay = getWay;
    }

    public Integer getStatusCfg() {
        return statusCfg;
    }

    public void setStatusCfg(Integer statusCfg) {
        this.statusCfg = statusCfg;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateCouponName() {
        return templateCouponName;
    }

    public void setTemplateCouponName(String templateCouponName) {
        this.templateCouponName = templateCouponName == null ? null : templateCouponName.trim();
    }

    public Integer getTemplateCouponCfg() {
        return templateCouponCfg;
    }

    public void setTemplateCouponCfg(Integer templateCouponCfg) {
        this.templateCouponCfg = templateCouponCfg;
    }

    public String getTemplateCouponAmount() {
        return templateCouponAmount;
    }

    public void setTemplateCouponAmount(String templateCouponAmount) {
        this.templateCouponAmount = templateCouponAmount == null ? null : templateCouponAmount.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    
    public String getTemplateUseExplain() {
		return templateUseExplain;
	}

	public void setTemplateUseExplain(String templateUseExplain) {
		this.templateUseExplain = templateUseExplain;
	}

	public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
    
}