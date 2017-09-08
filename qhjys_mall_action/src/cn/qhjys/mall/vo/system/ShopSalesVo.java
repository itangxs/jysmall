package cn.qhjys.mall.vo.system;

import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

/**
 * 
 * 类名称:ShopSales
 * 类描述:·店铺销售额统计/·店铺每日收入报表/·店铺月收入报表
 * 创建人:JiangXiaoPing
 * 创建时间:2015年5月26日上午9:59:45
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class ShopSalesVo extends BaseVo {
	
	//商家ID
	private Long sellerId; 
	//店铺名称
	private String storeName;
	//销售额
	private String salesVolume;
	
	//时间
	private Date date;
	
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getSalesVolume() {
		return salesVolume;
	}
	public void setSalesVolume(String salesVolume) {
		this.salesVolume = salesVolume;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
