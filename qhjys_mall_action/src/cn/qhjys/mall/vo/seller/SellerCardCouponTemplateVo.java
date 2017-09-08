package cn.qhjys.mall.vo.seller;

import java.io.Serializable;
import java.util.Date;

public class SellerCardCouponTemplateVo implements Serializable {
	private Long id;
	
	private Integer couponCfg;

	private Integer getWay;
	
	private String storeName;
	
    private Long storeId;
    
    private Long sellerId;
    
    private String name;
    
    private Integer cardReceiveDisplay;

    private Integer cardReceiveTrue;

    private Integer cardPushDisplay;

    private Integer cardPushTrue;

    private Integer cardShareDisplay;

    private Integer cardShareTrue;

    private Integer cardShareReceiveDisplay;

    private Integer cardShareReceiveTrue;
    
    private String code;
    
    private Date validateDate;
    
    private String nickName;
    
    private String openId;
    
    private Integer statusCfg;
    
    private Date countDate;
    
    private Integer writeOff;

    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCouponCfg() {
		return couponCfg;
	}

	public void setCouponCfg(Integer couponCfg) {
		this.couponCfg = couponCfg;
	}

	public Integer getGetWay() {
		return getWay;
	}

	public void setGetWay(Integer getWay) {
		this.getWay = getWay;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName == null ? null : storeName.trim();
	}
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCardReceiveDisplay() {
		return cardReceiveDisplay;
	}

	public void setCardReceiveDisplay(Integer cardReceiveDisplay) {
		this.cardReceiveDisplay = cardReceiveDisplay;
	}

	public Integer getCardReceiveTrue() {
		return cardReceiveTrue;
	}

	public void setCardReceiveTrue(Integer cardReceiveTrue) {
		this.cardReceiveTrue = cardReceiveTrue;
	}

	public Integer getCardPushDisplay() {
		return cardPushDisplay;
	}

	public void setCardPushDisplay(Integer cardPushDisplay) {
		this.cardPushDisplay = cardPushDisplay;
	}

	public Integer getCardPushTrue() {
		return cardPushTrue;
	}

	public void setCardPushTrue(Integer cardPushTrue) {
		this.cardPushTrue = cardPushTrue;
	}

	public Integer getCardShareDisplay() {
		return cardShareDisplay;
	}

	public void setCardShareDisplay(Integer cardShareDisplay) {
		this.cardShareDisplay = cardShareDisplay;
	}

	public Integer getCardShareTrue() {
		return cardShareTrue;
	}

	public void setCardShareTrue(Integer cardShareTrue) {
		this.cardShareTrue = cardShareTrue;
	}

	public Integer getCardShareReceiveDisplay() {
		return cardShareReceiveDisplay;
	}

	public void setCardShareReceiveDisplay(Integer cardShareReceiveDisplay) {
		this.cardShareReceiveDisplay = cardShareReceiveDisplay;
	}

	public Integer getCardShareReceiveTrue() {
		return cardShareReceiveTrue;
	}

	public void setCardShareReceiveTrue(Integer cardShareReceiveTrue) {
		this.cardShareReceiveTrue = cardShareReceiveTrue;
	}
	
	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

	public Date getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(Date validateDate) {
		this.validateDate = validateDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	public Integer getStatusCfg() {
		return statusCfg;
	}

	public void setStatusCfg(Integer statusCfg) {
		this.statusCfg = statusCfg;
	}

	public Date getCountDate() {
		return countDate;
	}

	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}

	public Integer getWriteOff() {
		return writeOff;
	}

	public void setWriteOff(Integer writeOff) {
		this.writeOff = writeOff;
	}
	
}
