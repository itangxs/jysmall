package cn.qhjys.mall.weixin.bean.user;

import cn.qhjys.mall.weixin.bean.BaseResult;

public class TagsGetidlistResult extends BaseResult{

	private Integer[] tagid_list;

	public Integer[] getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(Integer[] tagid_list) {
		this.tagid_list = tagid_list;
	}
	
}
