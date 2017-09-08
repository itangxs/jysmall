package cn.qhjys.mall.service;

import java.util.Map;
import cn.qhjys.mall.weixin.reqsq.Article;




/**
 * 微信消息管理接口
 * @author llw
 * 2015-01-20
 */
public interface WxOpenService   {
	
	/**添加关注*/
	public String insertFansRecord(Map<String, String> requestMap) throws Exception ;
	
	/**修改粉丝关注状态*/
	public String updateFansAttention(Map<String, String> requestMap,Integer isAttention) throws Exception ;
	
	/**文本消息回复*/
	public String replayText(Map<String, String> requestMap) throws Exception;
	
	/**view消息回复*/
	public String replayView(Map<String, String> requestMap) throws Exception;
	
	/**单图文消息回复*/
	public String replaySingleImageText(Map<String, String> requestMap,Article article) throws Exception;

	/**音频消息回复*/
	public String replayVoice(Map<String, String> requestMap) throws Exception;
	
	
	 
}
