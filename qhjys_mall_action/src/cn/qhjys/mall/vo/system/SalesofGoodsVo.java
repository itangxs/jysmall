package cn.qhjys.mall.vo.system;

import cn.qhjys.mall.common.BaseVo;


/**
 * 
 * 类名称:SalesofGoodsVo
 * 类描述:系统 商品销量Vo
 * 创建人:JiangXiaoPing
 * 创建时间:2015年5月25日下午2:39:50
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class SalesofGoodsVo extends BaseVo {

	private String productName; //商品名称
	
	private	String storeName; //店铺名称
	
	public Integer  statisticsCount; //销售数量
	
	private String salesVolume; //销售额度

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getStatisticsCount() {
		return statisticsCount;
	}

	public void setStatisticsCount(Integer statisticsCount) {
		this.statisticsCount = statisticsCount;
	}

	public String getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(String salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	
	
	
}
