package cn.qhjys.mall.vo.system;

import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

public class CardDeliveryCountVo extends BaseVo {

	private Integer couponCfg; // 卡券类型
	private Integer deliverType; // 投放类型
	private Integer getWay;//投放方式
	private String storeName; // 店铺名称
	private Long storeId; // 商家ID
	private String name; // 卡券名称
	private Integer cardReciveTrue; // 卡券领取真实数据
	private Integer cardReciveDisplay; // 卡券领取展示
	private Integer cardPushTrue; // 卡券推送真实数据
	private Integer cardPushDisplay; // 卡券推送显示
	private Integer cardShareTrue; // 卡券分享真实数据
	private Integer cardShareDisplay; // 卡券分享展示
	private Integer cardShareReciveTrue; // 卡券分享领取真实数据
	private Integer cardShareReciveDisplay; // 卡券分享领取展示
	private Date countDate; // 统计日期
	private String code; //认证编号
	private String valiDate; //核销时间
	private String nickName; //用户昵称
	private Long id;
	private Long cardTemplateId; // 卡券模板ID
	private Date startDate; //开始时间
	private Date endDate;//结束时间
	private Integer valiDateCfg;
	private Integer writeOff;
	
	
	
	public Integer getGetWay() {
		return getWay;
	}
	public void setGetWay(Integer getWay) {
		this.getWay = getWay;
	}
	public Integer getValiDateCfg() {
		return valiDateCfg;
	}
	public void setValiDateCfg(Integer valiDateCfg) {
		this.valiDateCfg = valiDateCfg;
	}
	public Integer getCouponCfg() {
		return couponCfg;
	}
	public void setCouponCfg(Integer couponCfg) {
		this.couponCfg = couponCfg;
	}
	public Integer getDeliverType() {
		return deliverType;
	}
	public void setDeliverType(Integer deliverType) {
		this.deliverType = deliverType;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCardReciveTrue() {
		return cardReciveTrue;
	}
	public void setCardReciveTrue(Integer cardReciveTrue) {
		this.cardReciveTrue = cardReciveTrue;
	}
	public Integer getCardReciveDisplay() {
		return cardReciveDisplay;
	}
	public void setCardReciveDisplay(Integer cardReciveDisplay) {
		this.cardReciveDisplay = cardReciveDisplay;
	}
	public Integer getCardPushTrue() {
		return cardPushTrue;
	}
	public void setCardPushTrue(Integer cardPushTrue) {
		this.cardPushTrue = cardPushTrue;
	}
	public Integer getCardPushDisplay() {
		return cardPushDisplay;
	}
	public void setCardPushDisplay(Integer cardPushDisplay) {
		this.cardPushDisplay = cardPushDisplay;
	}
	public Integer getCardShareTrue() {
		return cardShareTrue;
	}
	public void setCardShareTrue(Integer cardShareTrue) {
		this.cardShareTrue = cardShareTrue;
	}
	public Integer getCardShareDisplay() {
		return cardShareDisplay;
	}
	public void setCardShareDisplay(Integer cardShareDisplay) {
		this.cardShareDisplay = cardShareDisplay;
	}
	public Integer getCardShareReciveTrue() {
		return cardShareReciveTrue;
	}
	public void setCardShareReciveTrue(Integer cardShareReciveTrue) {
		this.cardShareReciveTrue = cardShareReciveTrue;
	}
	public Integer getCardShareReciveDisplay() {
		return cardShareReciveDisplay;
	}
	public void setCardShareReciveDisplay(Integer cardShareReciveDisplay) {
		this.cardShareReciveDisplay = cardShareReciveDisplay;
	}
	public Date getCountDate() {
		return countDate;
	}
	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValiDate() {
		return valiDate;
	}
	public void setValiDate(String valiDate) {
		this.valiDate = valiDate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCardTemplateId() {
		return cardTemplateId;
	}
	public void setCardTemplateId(Long cardTemplateId) {
		this.cardTemplateId = cardTemplateId;
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
	public Integer getWriteOff() {
		return writeOff;
	}
	public void setWriteOff(Integer writeOff) {
		this.writeOff = writeOff;
	}
	
}