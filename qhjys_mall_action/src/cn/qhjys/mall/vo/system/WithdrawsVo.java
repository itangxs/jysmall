package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 
 * @author zengrong
 *
 */
public class WithdrawsVo {

	private Long id;
	//用户名
	private String userName;
	//商家名
//	private String sellerName;
	private String storeName;
	private Long storeId;
	//操作日期
	private Date createDate;
	//提现金额
	private BigDecimal money;
	private String bankUserName;
	private String carkNum;
	private String bankName;
	private String branch;
	private Long sellerId;
	//状态，0不成功，1成功,2未处理,3已出账
	private Integer status;
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Long getId() {
		return id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getBankUserName() {
		return bankUserName;
	}
	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCarkNum() {
		return carkNum;
	}
	public void setCarkNum(String carkNum) {
		this.carkNum = carkNum;
	}
	
}
