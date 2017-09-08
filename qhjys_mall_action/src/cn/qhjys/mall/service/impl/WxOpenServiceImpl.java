 package cn.qhjys.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.service.WxOpenService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.weixin.reqsq.Article;
import cn.qhjys.mall.weixin.reqsq.MusicMessage;
import cn.qhjys.mall.weixin.reqsq.NewsMessage;
import cn.qhjys.mall.weixin.reqsq.TextMessage;
import cn.qhjys.mall.weixin.reqsq.ViewMessage;
import cn.qhjys.mall.weixin.util.MessageUtil;

/**
 * 微信消息管理接口实现类
 * @Description: 
 * @author llw
 */
@Service
public class WxOpenServiceImpl implements WxOpenService {

	private static final Log    logger = LogFactory.getLog(WxOpenServiceImpl.class);
	
	
	/**
	 * 添加关注
	 */
	@Override
	public String insertFansRecord(Map<String, String> requestMap) throws Exception 
	{
		String resultStr = null;
		try
		{
			String fromUserName = requestMap.get("FromUserName");	// 发送方帐号（open_id）
			String toUserName = requestMap.get("ToUserName");		// 公众帐号
			String msgType = requestMap.get("MsgType");				// 消息类型
 
			//检查该用户是否己关注，如有历史记录，修改标记
		 
			//添加//修改
			String appUrl = ConstantsConfigurer.getProperty("xiaohe.mobile.basePath");
			StringBuffer str = new StringBuffer();
			str.append("\t\n<a href=\"");
			str.append(appUrl);
			str.append("?openid="+fromUserName);
			str.append("\">");
			str.append("您好，欢迎关注98圈，点击进入98圈");
			str.append("</a>\n");
			
			//设置默认回复
			resultStr =getReplayTextDefault(requestMap,str.toString());
			
		}
		catch(Exception e)
		{
			logger.error("添加关注错误", e);
			throw e;
		}
		return resultStr;
	}
	
	/**
	 * 修改粉丝关注状态
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	@Override
	public String updateFansAttention(Map<String, String> requestMap,Integer isAttention) throws Exception 
	{
		try
		{
			String openId = requestMap.get("FromUserName");	// 发送方帐号（open_id）
		}
		catch(Exception e)
		{
			logger.error("修改粉丝关注状态错误", e);
			throw e;
		}
		return null;
	}
 
	
	
	/**
	 * 文本消息回复
	 */
	@Override
	public String replayText(Map<String, String> requestMap) throws Exception
	{
		String resultStr = null;
		try
		{
			String fromUserName = requestMap.get("FromUserName");	// 发送方帐号（open_id）
			String toUserName = requestMap.get("ToUserName");		// 公众帐号
			String msgType = requestMap.get("MsgType");				// 消息类型
			String content = requestMap.get("Content");				// 接收内容
			//添加微信聊天记录
			//设置回复
			if(!StringUtils.isEmpty(content)){
				resultStr =getReplayTextDefault(requestMap,content);
			}
		}
		catch(Exception e)
		{
			logger.info("文本消息回复错误",e);
			throw e;
		}
		return resultStr;
	}
	
	/**
	 * view消息回复
	 */
	@Override
	public String replayView(Map<String, String> requestMap) throws Exception
	{
		String resultStr = null;
		try
		{
			String fromUserName = requestMap.get("FromUserName");	// 发送方帐号（open_id）
			String toUserName = requestMap.get("ToUserName");		// 公众帐号
			Long createTime = new Date().getTime();
					//requestMap.get("CreateTime");		// 消息创建时间 （整型）
			String msgType = "event";
					//requestMap.get("MsgType");				// 消息类型
			String event ="VIEW";
					//requestMap.get("Event");				// 事件类型 VIEW
			String eventKey = requestMap.get("EventKey");				// 事件KEY值，与自定义菜单接口中KEY值对应
			ViewMessage viewMessage = new ViewMessage();
			viewMessage.setFromUserName(fromUserName);
			viewMessage.setToUserName(toUserName);
			viewMessage.setCreateTime(createTime);
			viewMessage.setMsgType(msgType);
			viewMessage.setEvent(event);
			viewMessage.setEventKey(eventKey);
			resultStr = MessageUtil.viewMessageToXml(viewMessage);
			/*//添加微信聊天记录
			//设置回复
			if(!StringUtils.isEmpty(content)){
				resultStr =getReplayTextDefault(requestMap,content);
			}*/
		}
		catch(Exception e)
		{
			logger.info("view消息回复错误",e);
			throw e;
		}
		return resultStr;
	}
	
	/**
	 * 音频消息回复
	 */
	@Override
	@SuppressWarnings("unused")
	public String replayVoice(Map<String, String> requestMap) throws Exception
	{
		String resultStr = null;
		try
		{

			String fromUserName = requestMap.get("FromUserName");	// 发送方帐号（open_id）
			String toUserName = requestMap.get("ToUserName");		// 公众帐号
			String msgType = requestMap.get("MsgType");				// 消息类型
			String recognition = requestMap.get("Recognition");			// 语音识别结果
			
			//添加微信聊天记录
		}
		catch(Exception e)
		{
			logger.info("音频消息回复错误",e);
			throw e;
		}
		return resultStr;
	}
	

	
	/**
	 * 微信默认回复文本消息
	 */
	@SuppressWarnings("unused")
	public String getReplayTextDefault(Map<String, String> requestMap,String content) throws Exception
	{
		String resultStr="您好！";
		try
		{
			String fromUserName = requestMap.get("FromUserName");	// 发送方帐号（open_id）
			String toUserName = requestMap.get("ToUserName");		// 公众帐号
			String msgType =MessageUtil.RESP_MESSAGE_TYPE_TEXT;				// 消息类型
			String reqContent = requestMap.get("Content");				// 请求内容
			// 回复文本消息  
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(msgType);
			textMessage.setFuncFlag(0);
			
			
			if(StringUtils.isNotBlank(reqContent) )
			{
				//
				if(reqContent.contains(""))
				{
					 
				/*	content="自定义回复";*/
				}
				else if(reqContent.startsWith("图文"))
				{
					//直接返回图文
					NewsMessage newsMessage = null;
					if(newsMessage!=null)
					{
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						return MessageUtil.newsMessageToXml(newsMessage);
					}
				}
				else if(reqContent.startsWith("电影+") && reqContent.split("\\+").length== 2)
				{
					//直接返回多图文
					NewsMessage newsMessage = null;
					 
					if(newsMessage!=null)
					{
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						
						return MessageUtil.newsMessageToXml(newsMessage);
					}
				}
				else if(reqContent.startsWith("音乐+"))
				{
					//直接返回音乐消息
					MusicMessage musicMessage = null;
					if(musicMessage!=null)
					{
						musicMessage.setToUserName(fromUserName);
						musicMessage.setFromUserName(toUserName);
						musicMessage.setCreateTime(new Date().getTime());
						musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
						resultStr= MessageUtil.musicMessageToXml(musicMessage);
						return resultStr;
					}
				}
			}
			//添加微信回复聊天记录

            textMessage.setContent(content);  
			resultStr = MessageUtil.textMessageToXml(textMessage);  
		}
		catch(Exception e)
		{
			logger.info("微信默认回复文本消息错误",e);
		}
		return resultStr;
	}

	@Override
	public String replaySingleImageText(Map<String, String> requestMap,Article article) throws Exception {
		String resultStr="服务器忙.!！";
		try
		{
			String fromUserName = requestMap.get("FromUserName");	// 发送方帐号（open_id）
			String toUserName = requestMap.get("ToUserName");		// 公众帐号
			NewsMessage newsMessage = new  NewsMessage() ;
			List<Article> articleList = new ArrayList<Article>();  
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			articleList.add(article);  
            // 设置图文消息个数  
            newsMessage.setArticleCount(articleList.size());  
            // 设置图文消息包含的图文集合  
            newsMessage.setArticles(articleList);  
			return MessageUtil.newsMessageToXml(newsMessage);
				
		}
		catch(Exception e)
		{
			logger.info("微信图文消息错误",e);
		}
		return resultStr;
	}
}
