package cn.qhjys.mall.controller.weixin;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.FROMWHERE;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.service.WeixinUserinfoService;
import cn.qhjys.mall.service.WxOpenService;
import cn.qhjys.mall.service.fq.FqAcardUserExchangeService;
import cn.qhjys.mall.service.fq.FqRedpackService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.weixin.reqsq.Article;
import cn.qhjys.mall.weixin.util.MessageUtil;
import cn.qhjys.mall.weixin.util.SignUtil;
import cn.qhjys.mall.weixin.util.SystemConstant;


/**
 * 微信开放接口控制类
 * @author llw
 */
@Controller
@RequestMapping(value="/wechat")
public class WxOpenControll extends Base  {

	@Autowired
	private WxOpenService wxOpenService;
	@Autowired
	private WeixinUserinfoService weixinUserinfoService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	@Autowired
	private FqAcardUserExchangeService fqAcardUserExchangeService;
	@Autowired
	private FqRedpackService fqRedpackService;
	/**
	 * 微信统一请求方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/messagereq")
	public void wxOpen(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		
		String method= request.getMethod();
		
		if("GET".equals(method.toUpperCase())){
			wxOpenGet(request,response);
		}else if("POST".equals(method.toUpperCase())){
			wxOpenPost(request,response,session);
		}
	}
	
	
	/**
	 *	确认请求来自微信服务器 
	 */
    public void wxOpenGet(HttpServletRequest request, HttpServletResponse response ) throws Exception {
    
		String signature = request.getParameter("signature");	// 微信加密签名
		String timestamp = request.getParameter("timestamp");	// 时间戳
		String nonce = request.getParameter("nonce");			// 随机数
		String echostr = request.getParameter("echostr");		// 随机字符串
		
		if(signature!=null && timestamp!=null && nonce!=null && echostr!=null){
			PrintWriter out = response.getWriter(); 
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
			String token = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_TOKEN);
			if (SignUtil.checkSignature(token,signature, timestamp, nonce)) {
				out.print(echostr);
			}else{
				logger.info("微信消息开放接口签名错误");
			}
			out.close();
			out = null;
		}
    }
    
    /**
     * 处理微信服务器发来的消息 
     * 
     */
    public void wxOpenPost(HttpServletRequest request, HttpServletResponse response,HttpSession session ) throws Exception {
    	
    	request.setCharacterEncoding("UTF-8");  
    	response.setCharacterEncoding("UTF-8");

    	//处理微信发来的请求 
		String respMessage = processRequest(request);
		
		if(respMessage != null){
			// 响应消息
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();
		}
    }
    
    
    /**
     * 处理微信发来的请求 
     * @param request
     * @return
     * @throws Exception
     */
    public String processRequest(
    		HttpServletRequest request
    		) throws Exception 
    {
    	String respMessage = null; 
    	try{
    		// xml请求解析
    		Map<String, String> requestMap = MessageUtil.parseXml(request);
    		String msgType = requestMap.get("MsgType");				// 消息类型
			if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){								//事件推送
		 		// 事件类型  
				if (MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(requestMap.get("Event"))){			// 订阅
					WeixinUserinfo weixinUserinfo = new WeixinUserinfo();
					String openId = requestMap.get("FromUserName");
					weixinUserinfo.setOpenId(openId);
					Integer fromwhere = FROMWHERE.fromwhere.get(openId);
					if (fromwhere != null) {
						weixinUserinfo.setFromWhere(fromwhere);
					}
					weixinUserinfoService.insertWeixinUserinfo(weixinUserinfo);
					fqUserInfoService.updateFqUserInfo(openId, null, null);
					requestMap.put("Content", "恭喜您成为飞券会员~\n"+
//						"戳→「最新活动」参与热门活动，赢大奖！\n"+
						"戳→「我是用户」查看我的特权、寻找美食！\n"+
						"戳→「我是商家」申请合作，下载APP享受方便快捷的飞券服务！\n"+
						"商家邀请商家入驻，百万营销基金等您拿！点击<a href='http://www.jysmall.com/app/seller.html'>【邀请有礼】</a>查看活动详情~\n"+
						"客服电话：400-6333-088（周一至周五，09:00-18:00）\n"
					);
					respMessage = wxOpenService.replayText(requestMap);
					fqRedpackService.updateFqRedpackRecordByOpenidAndstatus(openId);
					// 发半小时前获取的A券(如果有)。
//					fqAcardUserExchangeService.updateAcardUserExchangeByOpenId(openId);
				}else if (MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(requestMap.get("Event"))){ 	// 取消订阅
				
					//取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					weixinUserinfoService.deleteWeixinUserinfo(requestMap.get("FromUserName"));
					return null;
				}
			}
			else if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType))			//文本消息
			{
				String re = requestMap.get("Content");
				if(re.equals("特色")){
					/*StringBuffer content = new StringBuffer();
					content.append("公兴隆的盲公饼\n地点为：禅城区人民路104号").append("\n\n");
					content.append("南海粤园的佛山扎蹄\n地点为：南海区桂城海八路麦当劳以南，一汽大众西侧").append("\n\n");
					content.append("大可以饭店的西樵大饼\n地点为：禅城区祖庙路百花总汇B馆3楼").append("\n\n");
					content.append("民信老铺的双皮奶\n地点为：禅城区玫瑰东路3号印象城内").append("\n\n");
					requestMap.put("Content", content.toString());
					respMessage = wxOpenService.replayText(requestMap);*/
					Article article = new Article();  
                    article.setTitle("身在佛山，如果你这些美食都没有吃过的话，那就太OUT了！地址公布！");   
                    article.setDescription("");  
                    article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/9hL4ian5nWm8aXgcGicc0BGbsBcia2z6wk8eXr7k0CsOPIfKu5DL1GYhicgo1UwibqM1Fia2xXfPLK94SDUaUarZ0lAA/0?wx_fmt=jpeg");  
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0ODIxMTMxMQ==&mid=403225697&idx=1&sn=016eb93bff9ae614b57e20b988cc7e25#rd");  
					respMessage = wxOpenService.replaySingleImageText(requestMap, article);
				}
				if(re.equals("寿司")){
					Article article = new Article();  
                    article.setTitle("在佛山吃寿司，你一定不能错过这家店！地址公布！");  
                    article.setDescription("地址：季华五路12号沃尔玛首层（新都会华庭）\n电话：0757-82903728，18022231899！");  
                    article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/9hL4ian5nWmibgy3Njr4G59ibic0NDERnRlUlLnKRGXibABO4RBLCWJj6y1Iiac3Z0uxDogr1FKNDlMkAN3kGCbeZEicw/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");  
                    article.setUrl("http://mp.weixin.qq.com/s?__biz=MzI0ODIxMTMxMQ==&mid=403225697&idx=2&sn=b70dfbb965d270eb692d330ef215ef98#rd");  
					respMessage = wxOpenService.replaySingleImageText(requestMap, article);
				}
				if(re.equals("提现")){
					Article article = new Article();  
					article.setTitle("飞券商家自动结算及提现功能说明");  
					article.setDescription("");  
					article.setPicUrl("http://www.jysmall.com/images/caption.jpg");  
					article.setUrl("http://www.jysmall.com/html/article/caption.html");  
					respMessage = wxOpenService.replaySingleImageText(requestMap, article);
				}
				if(re.contains("绑定店铺账号")){
					requestMap.put("Content", "格式如下:\n\n**#**");
					respMessage = wxOpenService.replayText(requestMap);
				}
				if(re.contains("摇一摇")){
					requestMap.put("Content", "<a href='http://www.hixianchang.com/w/wallshake/0ff66032a91d4323ab210cba3b1b6d7f/index.html?from=standalone'>【点击参与摇一摇】</a>");
					respMessage = wxOpenService.replayText(requestMap);
				}
				//店铺账号:***,店铺密码:***
				if(re.contains("#")){
					
					String accout = "";
					String password = "";
					int indexOf = re.indexOf("#");
					if(indexOf>0){
						accout = re.substring(0, indexOf);
						password = re.substring(indexOf+1, re.length());
						accout= accout.replaceAll(" ", "");
						password = password.replaceAll(" ", "");
						if(!StringUtils.isEmpty(accout)&&!StringUtils.isEmpty(password)){
							String status = weixinUserinfoService.insertWeixinUserinfoAccount(accout,EncodeMD5.GetMD5Code(password),requestMap.get("FromUserName"));
							//0001 账号或者密码错误  0002 没有店铺  0003 已经绑定了 0000 绑定成功
							String msg="服务器忙,请稍候再试";
							if(status.equals("0000")){
								msg="绑定成功";
							}
							if(status.equals("0001")){
								msg="账号或者密码错误";
							}
							if(status.equals("0002")){
								msg="您暂时没有店铺";
							}
							if(status.equals("0003")){
								msg="店铺已经绑定过了";
							}
							if(status.equals("0004")){
								msg="微信已经绑定过了";
							}
							requestMap.put("Content", msg);
							respMessage = wxOpenService.replayText(requestMap);
						}
					}
				}
				
			}
			else if(MessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType))			//图片消息
			{	
			}
			else if(MessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType))		//地理位置消息
			{
				logger.info("地理位置信息");
			}
			else if(MessageUtil.REQ_MESSAGE_TYPE_LINK.equals(msgType))			//链接消息
			{
			}
			else if(MessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType))			//音频消息
			{
				respMessage = wxOpenService.replayVoice(requestMap);
			}
			else if(MessageUtil.EVENT_TYPE_CLICK.equals(msgType))   //自定义菜单点击事件 
			{
				
			}
    	}
    	catch(Exception e)
    	{
    		logger.error("处理微信发来的请求错误", e);
    	}
    	
    	return respMessage;
    }
    

}
