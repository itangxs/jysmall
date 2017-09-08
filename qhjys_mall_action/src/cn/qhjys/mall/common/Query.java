package cn.qhjys.mall.common;

import java.io.Serializable;

/**
 * 翻页查询 参数
 * 
 */
public class Query implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5525597373702694480L;

	/**
	 * 跳转
	 */
	private int pageNum=1;

	/**
	 * 每页显示记录数
	 */
	private int pageSize = 10;
	
	public Query() {

	}
	public int getPageNum() {
		return 0 >= pageNum ? 1 : pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	public int getPageSize() {
		return 0 >= pageSize ? 10 : pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}

