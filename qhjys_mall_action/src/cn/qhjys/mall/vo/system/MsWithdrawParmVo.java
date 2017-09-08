package cn.qhjys.mall.vo.system;

import java.io.Serializable;

/**
 * 
 * 标题：商户提现查询条件Vo
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月15日 下午3:46:16
 * 修改时间：
 *
 */
public class MsWithdrawParmVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2393453972952835199L;
	private Long[] ids;				//id集合
	private String startDate;		//开始时间
	private String endDate;			//结束时间
	private String storeId;			//店铺ID
	private String storeName;		//店铺名称
	private String state;			//状态0:未审核1:已审核2:提现中3:提现失败4:提现成功
	private String sellerId;		//卖家ID
	private String orderNo;			//提现订单号
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
