package cn.qhjys.mall.vo;

import java.io.Serializable;

/**
 * 店铺设置支付渠道Vo转换
 * 
 * @author huangsy
 * @version :1.0
 *
 */
public class PayChnnelVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer channelId;
	private String channelName;
	private String target;

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
}
