package cn.qhjys.mall.vo.seller;

/**
 * 商家(卖家) 银行卡Vo
 * @author JiangXiaoPing
 *
 */
public class SellerBankVo {

	//银行卡ID
	private Long id;
	//商家ID
	private Long sellerId;
	//地区
	private String address;
	//真实姓名
	private String realName;
	//银行名称
	private String bankName;
	//银行卡号码
	private String bankNumber;
	//是否默认
	private Integer defaultStaus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public Integer getDefaultStaus() {
		return defaultStaus;
	}
	public void setDefaultStaus(Integer defaultStaus) {
		this.defaultStaus = defaultStaus;
	}
	
	
}
