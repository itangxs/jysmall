package cn.qhjys.mall.vo.system;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 标题：民生商户提现报表导出Vo
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月16日 下午12:09:07
 * 修改时间：
 *
 */
public class MsWithdrawExportVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4303979736649656297L;

	private String orderNo;					//提现订单号
	private Date createDate;				//创建时间
	private Long storeId;					//店铺ID
	private String storeName;				//店铺名称
	private BigDecimal payMoney;			//交易金额
	private BigDecimal notFeeMoney;			//未扣费金额
	private BigDecimal allFeeMoney;			//已扣费金额
	private BigDecimal rateFee;				//手续费
	private BigDecimal withdrawMoeny;		//提现金额
	private Integer state;					//状态0:未审核1:已审核2:提现中3:提现失败4:提现成功
	private String cardholder;				//开户人
	private String bankName;				//银行名称
	private String bankCard;				//银行卡号
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
		this.storeName = storeName;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public BigDecimal getNotFeeMoney() {
		return notFeeMoney;
	}
	public void setNotFeeMoney(BigDecimal notFeeMoney) {
		this.notFeeMoney = notFeeMoney;
	}
	public BigDecimal getAllFeeMoney() {
		return allFeeMoney;
	}
	public void setAllFeeMoney(BigDecimal allFeeMoney) {
		this.allFeeMoney = allFeeMoney;
	}
	public BigDecimal getRateFee() {
		return rateFee;
	}
	public void setRateFee(BigDecimal rateFee) {
		this.rateFee = rateFee;
	}
	public BigDecimal getWithdrawMoeny() {
		return withdrawMoeny;
	}
	public void setWithdrawMoeny(BigDecimal withdrawMoeny) {
		this.withdrawMoeny = withdrawMoeny;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCardholder() {
		return cardholder;
	}
	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
