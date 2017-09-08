package cn.qhjys.mall.controller.system;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/message")
public class MessageInfoController extends Base {
	@Autowired
	private MessageInfoService messageInfoService;
	@Autowired
	private AdminLogService adminLogService;
	@Autowired
	private FqPushInfoService fqPushInfoService;
	
	
	@RequestMapping("/addPush")
	public ModelAndView addPush() {
		ModelAndView view = new ModelAndView("/system/message/add_push");
		return view;
	}
	
	@RequestMapping("/savePush")
	public Object savePush(HttpSession session, HttpServletRequest request,
			String title, String content) throws Exception {
		FqPushInfo fqPushInfo = new FqPushInfo();
		fqPushInfo.setContent(content);
		fqPushInfo.setPushTime(new Date());
		fqPushInfo.setSellerId(0L);
		fqPushInfo.setTitle(title);
		fqPushInfo.setType(2);
		int isSave = fqPushInfoService.insertSystemFqPushInfo(fqPushInfo);
		if (isSave>0) {
			return super.goUrl("/managermall/systemmall/message/pushPage.do", "操作成功!");
		}
		return super.goUrl("/managermall/systemmall/message/pushPage.do", "操作失败!");
	}
	
	@RequestMapping("/pushPage")
	public ModelAndView pushPage(Integer pageNum) throws ParseException {
		ModelAndView view = new ModelAndView("/system/message/push_list");
		if (pageNum == null)
			pageNum = 1;
		Page<FqPushInfo> page = fqPushInfoService.queryFqPushInfo(2, pageNum, 10);
		view.addObject("page", page);
		return view;
	}
	
	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/addMessage")
	public ModelAndView addMessage() {
		ModelAndView view = new ModelAndView("/system/message/add_message");
		return view;
	}

	@RequestMapping("/saveMessage")
	public Object saveMessage(HttpSession session, HttpServletRequest request, Integer sendType, Integer vipRank,
			String title, String content) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean isSave = messageInfoService.saveMessage(sendType, vipRank, admin.getId(), title, content);
		if (isSave) {
			String info = BaseUtil.getLogInfo("新增站点消息", "MessageInfo", null);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "营销推广", IP);
			return super.goUrl("/managermall/systemmall/message/messagePage.do", "操作成功!");
		}
		return super.goUrl("/managermall/systemmall/message/messagePage.do", "操作失败!");
	}

	@RequestMapping("/messagePage")
	public ModelAndView messagePage(String account, String title, String beginTime, String endTime, Integer seen,
			Integer pageNum, Integer pageSize) throws ParseException {
		ModelAndView view = new ModelAndView("/system/message/message_list");
		if (pageNum == null)
			pageNum = 1;
		if (seen == null)
			seen = -1;
		pageSize = 10;
		Page<MessageInfo> page = messageInfoService.queryMessagesByParams(account, title, beginTime, endTime, seen,
				pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("account", account);
		view.addObject("title", title);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("seen", seen);
		view.addObject("pageNum", pageNum);
		return view;
	}

	@RequestMapping("getMessage")
	public ModelAndView getMessage(Long id) throws Exception {
		ModelAndView view = new ModelAndView("/system/message/message_detail");
		MessageInfo mi = messageInfoService.getMessage(id);
		view.addObject("message", mi);
		return view;
	}
	
	@RequestMapping("delMessage")
	public void delMessage(HttpSession session, HttpServletRequest request, HttpServletResponse response, Long[] ids)
			throws Exception {
		logger.info(ids + "=ids");
		boolean isDel = messageInfoService.deleteMessage(ids);
		if (isDel) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("删除站点消息", "MessageInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "营销推广", IP);
			HtmlUtil.writerJson(response, "success");
		}
		HtmlUtil.writerJson(response, "error");
	}

}