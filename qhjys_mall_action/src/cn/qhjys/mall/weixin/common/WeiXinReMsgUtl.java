package cn.qhjys.mall.weixin.common;

import java.util.ArrayList;
import java.util.Date;
import cn.qhjys.mall.weixin.reqsq.Article;
import cn.qhjys.mall.weixin.reqsq.NewsMessage;
import cn.qhjys.mall.weixin.reqsq.TextMessage;

public class WeiXinReMsgUtl {

	public static String initTextMessage(String fromUserName, String toUserName, String content) {
		TextMessage message = new TextMessage();//文本
		message.setToUserName(fromUserName);
		message.setFromUserName(toUserName);
		message.setCreateTime(new Date().getTime());
		message.setMsgType(WeiXinUtil.RESP_MESSAGE_TYPE_TEXT);
		message.setContent(content);
		return WeiXinUtil.textMessageToXml(message);
	}

	public static String initNewsMessage(String fromUserName, String toUserName, ArrayList<Article> list) {
		NewsMessage newsMessage = new NewsMessage();
        newsMessage.setArticleCount(list.size());
        newsMessage.setArticles(list);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setFromUserName(toUserName);
        newsMessage.setFuncFlag(0);
        newsMessage.setMsgType(WeiXinUtil.RESP_MESSAGE_TYPE_NEWS);
        newsMessage.setToUserName(fromUserName);
		return WeiXinUtil.newsMessageToXml(newsMessage);
	}
	
}
