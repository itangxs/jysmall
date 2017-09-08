package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.util.ConstantsConfigurer;

public class CouponVo extends CouponInfo {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Long userId;

	private Long storeId;

	private String storeName;

	private Long prodId;

	private String couponId;

	private BigDecimal amount;

	private BigDecimal required;

	private Long orderId;

	private Date expireTime;

	private Integer consume;

	private String consumeIp;

	private Date consumeTime;
	// 兑换比例
	private BigDecimal scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Long getStoreId() {
		return storeId;
	}

	@Override
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
	public Long getProdId() {
		return prodId;
	}

	@Override
	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	@Override
	public String getCouponId() {
		return couponId;
	}

	@Override
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public BigDecimal getRequired() {
		return required;
	}

	@Override
	public void setRequired(BigDecimal required) {
		this.required = required;
	}

	@Override
	public Long getOrderId() {
		return orderId;
	}

	@Override
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public Date getExpireTime() {
		return expireTime;
	}

	@Override
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@Override
	public Integer getConsume() {
		return consume;
	}

	@Override
	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	@Override
	public String getConsumeIp() {
		return consumeIp;
	}

	@Override
	public void setConsumeIp(String consumeIp) {
		this.consumeIp = consumeIp;
	}

	@Override
	public Date getConsumeTime() {
		return consumeTime;
	}

	@Override
	public void setConsumeTime(Date consumeTime) {
		this.consumeTime = consumeTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getScale() {
		this.scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));
		return this.scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));
	}

}