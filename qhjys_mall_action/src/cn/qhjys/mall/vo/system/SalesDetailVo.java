package cn.qhjys.mall.vo.system;

import java.util.Date;
import cn.qhjys.mall.common.BaseVo;



public class SalesDetailVo extends BaseVo {
	
	private String orderNo; //订单号
	
	private	String storeName; //店铺名称

    private Long sellerId;	//商家ID
    
	private String productName; //商品名称
	
	private String tradingMoeny; //交易金额
	
	private Date tradingDate; //交易时间

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTradingMoeny() {
		return tradingMoeny;
	}

	public void setTradingMoeny(String tradingMoeny) {
		this.tradingMoeny = tradingMoeny;
	}

	public Date getTradingDate() {
		return tradingDate;
	}

	public void setTradingDate(Date tradingDate) {
		this.tradingDate = tradingDate;
	}
	
}
