package cn.qhjys.mall.weixin.common;

import java.util.List;
/**
 * 
 * 类名称:WeiXinUserList  微信用户列表
 * 类描述:
 * 创建人:JiangXiaoPing
 * 创建时间:2015年11月27日下午3:59:07
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class WeiXinUserList {

	private int total; // 关注该公众账号的总用户数
	private int count; // 拉取的OPENID个数，最大值为10000
	private List<WeiXinUser> data; // 列表数据，OPENID的列表
	private String next_openid; // 拉取列表的最后一个用户的OPENID

	

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<WeiXinUser> getData() {
		return data;
	}

	public void setData(List<WeiXinUser> data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

}
