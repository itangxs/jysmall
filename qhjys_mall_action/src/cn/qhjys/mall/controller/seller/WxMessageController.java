package cn.qhjys.mall.controller.seller;

import java.util.Date;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.FROMWHERE;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.WxMessage;
import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.WxMessageService;
import cn.qhjys.mall.service.WxUserVoService;
import cn.qhjys.mall.service.app.SellerCardCouponService;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.vo.WxMessageInfo;

@Controller
@RequestMapping("/managermall/seller/wxmessage")
public class WxMessageController extends Base{
	
	@Autowired
	private WxMessageService wxMessageService;
	@Autowired
	private WxUserVoService wxUserVoService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private SellerUserCountService sellerUserCountService;
	

	@RequestMapping("/list")
	public ModelAndView listMessgae(HttpSession session,Integer status,Integer pageSize,Integer pageNum){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/wxmessage/list");
		if (pageSize == null) {
			pageSize = 10;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		Page<WxMessage> page = wxMessageService.queryWxmessage(seller.getId(), status, pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}
	
	@RequestMapping("/toSendMessage")
	public ModelAndView toSendMessage(HttpSession session){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/wxmessage/sendMessage");
		StoreInfo storeinfo =storeService.queryStoreInfoBySeller(seller.getId());
		view.addObject("storeinfo", storeinfo);
		view.addObject("openidnum", sellerUserCountService.queryUserNum(seller.getId()));
		return view;
	}
	@RequestMapping("/addMessage")
	@ResponseBody
	public String addMessage(HttpSession session,String title,String content,String fileName,Integer type){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		StoreInfo store = (StoreInfo) session.getAttribute(ConstantsConfigurer.getProperty("store_key"));
		
		WxMessage message = new WxMessage();
		message.setContent(content);
		message.setCreateTime(new Date());
		message.setImage(fileName);
		message.setSellerId(seller.getId());
		message.setStatus(0);
		message.setTitle(title);
		message.setType(type);
		message.setStoreId(store.getId());
		message.setStoreName(store.getName());
		int a = wxMessageService.insertWxMessage(message);
		if (a>0) {
			return "success";
		}
		return "error";
	}
	@RequestMapping("/toupdate")
	public ModelAndView toUpdateMessage(HttpSession session,Long id){
		ModelAndView view = new ModelAndView("seller/wxmessage/update");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		WxMessage message = wxMessageService.getWxMessage(id);
		if (message == null || !message.getSellerId().equals(seller.getId())) {
			view.setViewName("redirect:/managermall/seller/wxmessage/list.do");
		}else{
			view.addObject("message", message);
		}
		return view;
	}
	@RequestMapping("/updateMessage")
	@ResponseBody
	public String updateMessage(HttpSession session,Long id,String title,String content,String fileName){
		WxMessage message = wxMessageService.getWxMessage(id);
		message.setContent(content);
		message.setCreateTime(new Date());
		message.setImage(fileName);
		message.setStatus(0);
		message.setTitle(title);
		int a = wxMessageService.updateWxMessage(message);
		if (a>0) {
			return "success";
		}
		return "error";
	}
	
	@RequestMapping("/preview")
	public ModelAndView previewMessage(String id) throws Exception{
//		 WxMessage message =FROMWHERE.lsmessages.get(id);
		 WxMessage message = (WxMessage) redisService.getValueByKey(id);
		ModelAndView view = new ModelAndView("seller/wxmessage/preview");
		view.addObject("title", message.getTitle());
		view.addObject("content",message.getContent());
		view.addObject("fileName", message.getImage());
		return view;
	}
	@RequestMapping("/previewByRedis")
	@ResponseBody
	public String previewByRedis(String title,String content,String fileName) throws Exception{
        WxMessage message = new WxMessage();
        message.setContent(content);
        message.setTitle(title);
        message.setImage(fileName);
        String id = UUID.randomUUID().toString();
        redisService.setValueByKey(id, message);
//        FROMWHERE.lsmessages.put(id, message);
		return id;
	}
	@RequestMapping("/previewByWechart")
	@ResponseBody
	public String previewByWechart(HttpSession session,String title,String content,String fileName,String username){
		WxMessage message = new WxMessage();
		message.setContent(content);
		message.setImage(fileName);
		message.setTitle(title);
		String path = session.getServletContext().getRealPath("");
		WxMessageInfo info = new WxMessageInfo();
		info.setMessage(message);
		info.setUsername(username);
		info.setPath(path);
		FROMWHERE.ylmessages.add(info);
		return "SUCCESS";
	}
	@RequestMapping("/previewByWechart1")
	@ResponseBody
	public String previewByWechart1(HttpSession session,Long id,String username) throws Exception{
		WxMessage message = wxMessageService.getWxMessage(id);
		String path = session.getServletContext().getRealPath("");
		if (StringUtils.isEmpty(message.getWxImage())) {
			WxMessageInfo info = new WxMessageInfo();
			info.setMessage(message);
			info.setUsername(username);
			info.setPath(path);
			FROMWHERE.ylmessages.add(info);
		}else{
			JSONObject json1  = new JSONObject();
			json1.put("thumb_media_id", message.getWxImage());
			json1.put("author", "飞券");
			json1.put("title", message.getTitle());
			json1.put("content_source_url", null);
			json1.put("content", message.getWxContent());
			json1.put("show_cover_pic", 1);
			JSONArray array = new JSONArray();
			array.add(json1);
			JSONObject jsonnews = new JSONObject();
			jsonnews.put("articles", array);
			JSONObject json2 = WeixinUtil.uploadFodder(AccessToken.getAccessToken(), jsonnews.toJSONString());
			String mediaId = json2.getString("media_id");
			
			JSONObject media_id1 = new JSONObject();
			media_id1.put("media_id", mediaId);
			JSONObject news = new JSONObject();
			news.put("towxname", username);
			news.put("mpnews", media_id1);
			news.put("msgtype", "mpnews");
			logger.info(news.toJSONString());
			JSONObject json3 = WeixinUtil.previewMessage(AccessToken.getAccessToken(), news.toJSONString());
			logger.info("------sendmessagejson3------"+json3);
			if (json3.getInteger("errcode").equals(0)) {
				return "SUCCESS";
			}
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	@RequestMapping("/previewByseller")
	public ModelAndView previewMessage(HttpSession session,Long id){
		ModelAndView view = new ModelAndView("seller/wxmessage/preview2");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		WxMessage message = wxMessageService.getWxMessage(id);
		if (message == null || !message.getSellerId().equals(seller.getId())) {
			view.setViewName("seller/wxmessage/preview1");
		}else{
			view.addObject("id", id);
			view.addObject("title", message.getTitle());
			view.addObject("content", message.getContent());
			view.addObject("fileName", message.getImage());
			view.addObject("type", message.getType());
		}
		return view;
	}
	
	@RequestMapping("/sendMessage")
	@ResponseBody
	public JSONObject sendMessage(HttpSession session,Long id) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		StoreInfo store = (StoreInfo) session.getAttribute(ConstantsConfigurer.getProperty("store_key"));
		int a = wxMessageService.updateWxMessageBySend(id, seller.getId(), store.getId());
		if (a == -1) {
			return JSON.parseObject("{\"errmsg\":\"信息不存在或者不是本店铺信息\",\"errcode\":40130}");
		}else if (a == -2) {
			return JSON.parseObject("{\"errmsg\":\"您本月已没有群发消息次数!\",\"errcode\":40131}");
		}
		
		return JSON.parseObject("{\"errmsg\":\"success\",\"errcode\":0}");
	}
}
