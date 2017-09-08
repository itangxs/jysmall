package cn.qhjys.mall.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.WxMessage;
import cn.qhjys.mall.vo.WxMessageInfo;

public class FROMWHERE {
	public static Map<String, Integer> fromwhere = new HashMap<String, Integer>();
	
	public static List<WxMessageInfo> messages = new ArrayList<WxMessageInfo>();
	
	public static List<WxMessageInfo> ylmessages = new ArrayList<WxMessageInfo>();
	
	public static Map<String, WxMessage> lsmessages = new HashMap<String, WxMessage>();
	
}
