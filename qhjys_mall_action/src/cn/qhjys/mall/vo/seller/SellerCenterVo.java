package cn.qhjys.mall.vo.seller;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 卖家(商家) 商家中心用到的Vo
 * 
 * @author JiangXiaoPing
 *
 */
public class SellerCenterVo {
	
	
	//商家ID
	private Long id ;
	
	//商家(卖家) 头像
	public String imageUrl  ;
	
	//商家(卖家) 名称
	public String  name;
	
	//vip等级 
	public Integer  vipLv;
	
	//账户管理余额
	public BigDecimal  moeny;
	
	//店铺分类名称
	public String  typeName;
	
	//店铺评分
	public float mark;
	
	//发布的商品数量
	public Integer  releaseGoods;
	
	//交易中的订单数量
	public Integer  tradeOrder;
	
	//退款中的订单数量
	public Integer  refundmentOrder;
	
	// 今天  成交金额（元）
	public BigDecimal  toDayTurnover;
	// 今天  成交积分
	public BigDecimal  toDayIntegral;
	
	// 今天 成交用户数
	public Integer   toDayUserNumber;
	
	// 今天 退款订单量
	public Integer   toDayRefundOrders;
	
	// 昨天  成交金额（元）
	public BigDecimal  yesterDayTurnover;
	// 昨天  成交积分
	public BigDecimal  yesterDayIntegral;
	
	// 昨天 成交用户数
	public Integer  yesterDayUserNumber;
	
	// 昨天 退款订单量
	public Integer  yesterDayRefundOrders;
	
		
	//今天    评论1星
	public Integer toDayComment1;
	
	//今天    评论2星
	public Integer toDayComment2;
	
	//今天    评论3星
	public Integer toDayComment3;
	
	//今天    评论4星
	public Integer toDayComment4;
	
	//今天    评论5星
	public Integer toDayComment5;
	
	//昨天  评论1星
	public Integer  yesterDayComment1;
	
	//昨天  评论2星
	public Integer  yesterDayComment2;
	
	//昨天  评论3星
	public Integer  yesterDayComment3;
	
	//昨天  评论4星
	public Integer  yesterDayComment4;
	
	//昨天  评论5星
	public Integer  yesterDayComment5;

	
	//时间 ..今天  一般是传递过来的.
	public Date date;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVipLv() {
		return vipLv;
	}

	public void setVipLv(Integer vipLv) {
		this.vipLv = vipLv;
	}

	public BigDecimal getMoeny() {
		return moeny;
	}

	public void setMoeny(BigDecimal moeny) {
		this.moeny = moeny;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public Integer getReleaseGoods() {
		return releaseGoods;
	}

	public void setReleaseGoods(Integer releaseGoods) {
		this.releaseGoods = releaseGoods;
	}

	public Integer getTradeOrder() {
		return tradeOrder;
	}

	public void setTradeOrder(Integer tradeOrder) {
		this.tradeOrder = tradeOrder;
	}

	public Integer getRefundmentOrder() {
		return refundmentOrder;
	}

	public void setRefundmentOrder(Integer refundmentOrder) {
		this.refundmentOrder = refundmentOrder;
	}

	public BigDecimal getToDayTurnover() {
		return toDayTurnover;
	}

	public void setToDayTurnover(BigDecimal toDayTurnover) {
		this.toDayTurnover = toDayTurnover;
	}

	public Integer getToDayUserNumber() {
		return toDayUserNumber;
	}

	public void setToDayUserNumber(Integer toDayUserNumber) {
		this.toDayUserNumber = toDayUserNumber;
	}

	public Integer getToDayRefundOrders() {
		return toDayRefundOrders;
	}

	public void setToDayRefundOrders(Integer toDayRefundOrders) {
		this.toDayRefundOrders = toDayRefundOrders;
	}

	public BigDecimal getYesterDayTurnover() {
		return yesterDayTurnover;
	}

	public void setYesterDayTurnover(BigDecimal yesterDayTurnover) {
		this.yesterDayTurnover = yesterDayTurnover;
	}

	public Integer getYesterDayUserNumber() {
		return yesterDayUserNumber;
	}

	public void setYesterDayUserNumber(Integer yesterDayUserNumber) {
		this.yesterDayUserNumber = yesterDayUserNumber;
	}

	public Integer getYesterDayRefundOrders() {
		return yesterDayRefundOrders;
	}

	public void setYesterDayRefundOrders(Integer yesterDayRefundOrders) {
		this.yesterDayRefundOrders = yesterDayRefundOrders;
	}

	public Integer getToDayComment1() {
		return toDayComment1;
	}

	public void setToDayComment1(Integer toDayComment1) {
		this.toDayComment1 = toDayComment1;
	}

	public Integer getToDayComment2() {
		return toDayComment2;
	}

	public void setToDayComment2(Integer toDayComment2) {
		this.toDayComment2 = toDayComment2;
	}

	public Integer getToDayComment3() {
		return toDayComment3;
	}

	public void setToDayComment3(Integer toDayComment3) {
		this.toDayComment3 = toDayComment3;
	}

	public Integer getToDayComment4() {
		return toDayComment4;
	}

	public void setToDayComment4(Integer toDayComment4) {
		this.toDayComment4 = toDayComment4;
	}

	public Integer getToDayComment5() {
		return toDayComment5;
	}

	public void setToDayComment5(Integer toDayComment5) {
		this.toDayComment5 = toDayComment5;
	}

	public Integer getYesterDayComment1() {
		return yesterDayComment1;
	}

	public void setYesterDayComment1(Integer yesterDayComment1) {
		this.yesterDayComment1 = yesterDayComment1;
	}

	public Integer getYesterDayComment2() {
		return yesterDayComment2;
	}

	public void setYesterDayComment2(Integer yesterDayComment2) {
		this.yesterDayComment2 = yesterDayComment2;
	}

	public Integer getYesterDayComment3() {
		return yesterDayComment3;
	}

	public void setYesterDayComment3(Integer yesterDayComment3) {
		this.yesterDayComment3 = yesterDayComment3;
	}

	public Integer getYesterDayComment4() {
		return yesterDayComment4;
	}

	public void setYesterDayComment4(Integer yesterDayComment4) {
		this.yesterDayComment4 = yesterDayComment4;
	}

	public Integer getYesterDayComment5() {
		return yesterDayComment5;
	}

	public void setYesterDayComment5(Integer yesterDayComment5) {
		this.yesterDayComment5 = yesterDayComment5;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getToDayIntegral() {
		return toDayIntegral;
	}

	public void setToDayIntegral(BigDecimal toDayIntegral) {
		this.toDayIntegral = toDayIntegral;
	}

	public BigDecimal getYesterDayIntegral() {
		return yesterDayIntegral;
	}

	public void setYesterDayIntegral(BigDecimal yesterDayIntegral) {
		this.yesterDayIntegral = yesterDayIntegral;
	}
	
	
	

}
