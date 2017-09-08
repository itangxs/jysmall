package cn.qhjys.mall.vo.system;

import java.io.Serializable;
import java.util.Date;

public class StoreLotteryVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long storeId;

    private String storeName;
    
    private String address;
    
    private String lotteryName;

    private String lotteryContent;

    private Date startDate;

    private Date endDate;

    private Integer status;

    private Date createDate;


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

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName == null ? null : lotteryName.trim();
    }

    public String getLotteryContent() {
        return lotteryContent;
    }

    public void setLotteryContent(String lotteryContent) {
        this.lotteryContent = lotteryContent == null ? null : lotteryContent.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public String getStoreName() {
		return storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}