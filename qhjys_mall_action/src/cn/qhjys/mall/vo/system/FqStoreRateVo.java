package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;
import java.util.Date;

public class FqStoreRateVo {
	private int id;//项次
	private String rateName;
	private String adminUserName;
	private String startTime;//开始时间
	private String endTime;//结束时间
	//微信
	private BigDecimal wechatBaseRate;
	private BigDecimal wechatAppendRate;
	private BigDecimal wechatAppendMoney;
	//支付宝
	private BigDecimal alipayBaseRate;
	private BigDecimal alipayAppendRate;
	private BigDecimal alipayAppendMoney;
	//qq
	private BigDecimal qqpayBaseRate;
	private BigDecimal qqpayAppendRate;
	private BigDecimal qqpayAppendMoney;
	private Date createTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public String getAdminUserName() {
		return adminUserName;
	}
	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	
	
}
