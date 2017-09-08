package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.util.Date;

public class AuthenticationChnnel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long storeId;
    private Integer bankNameId;
    private Integer payChannelId;
    private Integer state;
    private String respCode;
    private String respDesc;
    private Date createTime;
    private String applyId;
    private String chnlState;
    private String xyMerchantNum;
    private String xyBankKey;
    
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

    public Integer getBankNameId() {
		return bankNameId;
	}

	public void setBankNameId(Integer bankNameId) {
		this.bankNameId = bankNameId;
	}

	public Integer getPayChannelId() {
		return payChannelId;
	}

	public void setPayChannelId(Integer payChannelId) {
		this.payChannelId = payChannelId;
	}

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getChnlState() {
        return chnlState;
    }

    public void setChnlState(String chnlState) {
        this.chnlState = chnlState;
    }

	public String getXyMerchantNum() {
		return xyMerchantNum;
	}

	public void setXyMerchantNum(String xyMerchantNum) {
		this.xyMerchantNum = xyMerchantNum;
	}

	public String getXyBankKey() {
		return xyBankKey;
	}

	public void setXyBankKey(String xyBankKey) {
		this.xyBankKey = xyBankKey;
	}
    
}