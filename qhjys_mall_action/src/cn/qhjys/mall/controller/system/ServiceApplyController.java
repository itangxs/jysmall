package cn.qhjys.mall.controller.system;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.FROMWHERE;
import cn.qhjys.mall.entity.FqFinancialSupport;
import cn.qhjys.mall.entity.FqServiceApply;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.WxMessage;
import cn.qhjys.mall.service.FqServiceApplyService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.WxMessageService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.vo.WxMessageInfo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/apply")
public class ServiceApplyController extends Base{
	
	@Autowired
	private FqServiceApplyService fqServiceApplyService;
	@Autowired
	private WxMessageService wxMessageService;
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/list")
	public ModelAndView applyList(Integer storeId,String storeName,String beginTime,String endTime,Integer type,Integer status,
			Integer pageNum,Integer pageSize){
		ModelAndView view = new ModelAndView("/system/message/service_apply");
		Map<String, Object> map = new HashMap<>();
		map.put("storeId", storeId);
		map.put("storeName", storeName);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		map.put("applyType", type);
		map.put("status", status);
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		Page<FqServiceApply> applys = fqServiceApplyService.queryFqServiceApply(map);
		view.addObject("page", applys);
		view.addObject("storeId", storeId);
		view.addObject("storeName", storeName);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("type", type);
		view.addObject("status", status);
		return view;
	}
	
	@RequestMapping("/detail")
	public ModelAndView applydetail(Long id){
		ModelAndView view = new ModelAndView("/system/message/service_apply_detail");
		FqServiceApply apply = fqServiceApplyService.getFqServiceApplyById(id);
		view.addObject("apply", apply);
		if(apply.getApplyType() == 4){
			FqFinancialSupport support = fqServiceApplyService.getFqFinancialSupportByApplyId(id);
			view.addObject("support", support);
		}
		return view;
	}
	@RequestMapping("/updateStatus")
	@ResponseBody
	public int updateStatus(String ids,Integer status){
		return fqServiceApplyService.updateFqServiceApplyStatus(ids, status);
	}
	
	/**
	 * 商家群发消息管理列表
	 * @param storeId
	 * @param storeName
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/wxMessageList")
	public ModelAndView wxMessageList(Long storeId, String storeName, Integer status,
			Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/message/business_mass");
		if (pageSize == null) {
			pageSize = 10;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		if(storeName == ""){
			storeName = null;
		}
		Page<WxMessage> page = wxMessageService.WxmessageList(storeId, storeName, status, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("storeId",storeId);
		view.addObject("storeName",storeName);
		view.addObject("status",status);
		return view;
	}
	
	@RequestMapping("/preview")
	public ModelAndView previewMessage(Long id){
		ModelAndView view = new ModelAndView("seller/wxmessage/preview-sh");
		WxMessage message = wxMessageService.getWxMessage(id);
		if (message == null) {
			view.setViewName("seller/wxmessage/preview1");
		}else{
			view.addObject("title", message.getTitle());
			view.addObject("id", id);
			view.addObject("content", message.getContent());
			view.addObject("fileName", message.getImage());
		}
		System.out.println(id);
		return view;
	}
	@RequestMapping("/reviewMessage")
	@ResponseBody
	public String reviewMessage(Long id,Integer status,String remark){
		WxMessage message = wxMessageService.getWxMessage(id);
		if (message == null) {
			return "nomessage";
		}else{
			message.setStatus(status);
			message.setRemark(remark);
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/getStoreName")
	public StoreInfo getStoreName(Long storeId) throws Exception {
		StoreInfo storeInfo = storeService.getStoreInfoById(storeId);
		if(null != storeInfo){
			return storeInfo;
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/resetMessageNum")
	public Integer resetMessageNum(Long storeId, Integer messageNum) throws Exception {
		if(null != storeId && !storeId.equals("")){
			StoreInfo storeInfo = storeService.getStoreInfoById(storeId);
			return wxMessageService.updateMessageNum(storeInfo, messageNum);
		}
		return 0;
	}
	@ResponseBody
	@RequestMapping("/checkMessage")
	public String shenhe(Long id,Integer status,String remark){
		WxMessage wxMessage = new WxMessage();
		wxMessage.setId(id);
		wxMessage.setStatus(status);
		wxMessage.setRemark(remark);
		int a = wxMessageService.updateWxMessage(wxMessage);
		if (a>0) {
			return "SUCCESS";
		}
		return "ERROR"; 
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
//			File file = new File(path+message.getImage());
//			JSONObject json = WeixinUtil.uploadImage(AccessToken.getAccessToken(), "image", file);
//			String media_id = json.getString("media_id");
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
			JSONObject json3 = WeixinUtil.previewMessage(AccessToken.getAccessToken(), news.toJSONString());
			logger.info("------sendmessagejson3------"+json3);
			if (json3.getInteger("errcode").equals(0)) {
				return "SUCCESS";
			}
			return "ERROR";
		}
		return "SUCCESS";
	}
	
	@RequestMapping("/previewBySystem")
	public ModelAndView previewMessage(HttpSession session,Long id){
		ModelAndView view = new ModelAndView("system/message/preview2");
		WxMessage message = wxMessageService.getWxMessage(id);
		if (message == null) {
			view.setViewName("system/message/preview1");
		}else{
			view.addObject("id", id);
			view.addObject("title", message.getTitle());
			view.addObject("content", message.getContent());
			view.addObject("fileName", message.getImage());
			view.addObject("type", message.getType());
		}
		return view;
	}
}
