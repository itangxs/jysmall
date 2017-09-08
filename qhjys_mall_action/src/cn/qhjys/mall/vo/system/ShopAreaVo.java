package cn.qhjys.mall.vo.system;

import cn.qhjys.mall.common.BaseVo;

/**
 * 
 * 类名称:ShopAreaVo
 * 类描述:··店铺区域分布 VO
 * 创建人:JiangXiaoPing
 * 创建时间:2015年5月25日下午4:51:35
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class ShopAreaVo  extends BaseVo{

	//市	
	private String  cityName;
	//区域
	private String area;
	//商家数量	
	private  Integer sellerCount;
	//交易金额
	private String tradingMoney;
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getSellerCount() {
		return sellerCount;
	}
	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}
	public String getTradingMoney() {
		return tradingMoney;
	}
	public void setTradingMoney(String tradingMoney) {
		this.tradingMoney = tradingMoney;
	}
	
	
}
