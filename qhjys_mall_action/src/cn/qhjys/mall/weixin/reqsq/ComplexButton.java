package cn.qhjys.mall.weixin.reqsq;

/**
 * 
 * 类名称:ComplexButton 父类按钮
 * 类描述:
 * 创建人:JiangXiaoPing
 * 创建时间:2015年9月23日上午9:46:21
 * 修改人
 * 修改时间:
 * 修改备注:
 */
public class ComplexButton extends BaseButton{
	private BaseButton[] sub_button;

	public BaseButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(BaseButton[] sub_button) {
		this.sub_button = sub_button;
	}
	
}
