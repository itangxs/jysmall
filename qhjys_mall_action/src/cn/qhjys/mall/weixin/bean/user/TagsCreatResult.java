package cn.qhjys.mall.weixin.bean.user;

import cn.qhjys.mall.weixin.bean.BaseResult;

/**
 * 标签
 * 
 * @author SLYH
 * 
 */
public class TagsCreatResult extends BaseResult {

	private Tag tag;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
