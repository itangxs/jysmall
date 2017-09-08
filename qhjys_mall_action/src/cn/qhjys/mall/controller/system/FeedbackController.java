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
import cn.qhjys.mall.entity.Feedback;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.service.FeedbackService;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.FeedbackVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/feedback")
public class FeedbackController extends Base {
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private MessageInfoService messageInfoService;
	@Autowired
	private SellerUserCountService sellerUserCountService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/feedbackPage")
	public ModelAndView messagePage(Integer pageNum, Integer pageSize) throws ParseException {
		ModelAndView view = new ModelAndView("/system/message/feedback_list");
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 10;
//		Page<Feedback> page = feedbackService.listFeedback(pageNum, pageSize);
		Page<FeedbackVo> page = sellerUserCountService.queryFeedbackVo(pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}

	@RequestMapping("getFeedback")
	public ModelAndView getFeedback(Long id,Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/system/message/feedback_detail");
		Feedback mi = feedbackService.getFeedback(id);
		view.addObject("feedback", mi);
		view.addObject("pageNum", pageNum);
		return view;
	}

	@RequestMapping("updateFeedback")
	public void updateFeedback( HttpServletRequest request, HttpServletResponse response, Long id,String reply)
			throws Exception {
		Feedback fb = feedbackService.getFeedback(id);
		fb.setReply(reply);
		fb.setReplyTime(new Date());
		int a = feedbackService.updateFeedback(fb);
		if (a>0) {
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setContent(reply);
			messageInfo.setCreateDate(new Date());
			messageInfo.setMsgType(2);
			messageInfo.setSendee(fb.getSellerId());
			messageInfo.setTitle("反馈回复");
			messageInfo.setType(2);
			messageInfoService.insertMessageInfo(messageInfo);
			HtmlUtil.writerJson(response, "success");
		}
		HtmlUtil.writerJson(response, "error");
	}

}