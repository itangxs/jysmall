package cn.qhjys.mall.weixin.bean.user;

import java.util.List;

import cn.qhjys.mall.weixin.bean.BaseResult;


/**
 * 标签
 * 
 */
public class TagsGetResult extends BaseResult {

	private List<Tag> tags;

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
