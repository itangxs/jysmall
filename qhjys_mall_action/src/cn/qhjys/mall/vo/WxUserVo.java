package cn.qhjys.mall.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import cn.qhjys.mall.common.BaseVo;

public class WxUserVo extends BaseVo{
	
	private String openId;
	
	private String nickname;

    private String headimgurl;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) throws UnsupportedEncodingException {
		this.nickname = URLDecoder.decode(nickname,"UTF-8");
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
    
}
