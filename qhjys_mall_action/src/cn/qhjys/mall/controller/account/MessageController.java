package cn.qhjys.mall.controller.account;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.util.ConstantsConfigurer;

import com.github.pagehelper.Page;

/**
 * 我的消息
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/account/message")
public class MessageController extends Base {
	@Autowired
	private MessageInfoService messageInfoService;

	@RequestMapping("/zlist")
	public ModelAndView list(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/account/message/zlist");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
			return view;
		} else{
		if (null == page || page < 1)
			page = 1; 
		if (page > 0)
			view.addObject("page", page);
		pageSize = 10;
		Page<MessageInfo> infos = new Page<>();
		infos.pageNum(page);
		infos.pageSize(pageSize);
		Page<MessageInfo> messageVo = messageInfoService.queryUserMessages(user.getId(), infos);
		view.addObject("messageVo", messageVo);
		return view;
		}
	}

	@RequestMapping("/datail")
	public ModelAndView list(@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("/account/message/datail");
		MessageInfo message = messageInfoService.getMessage(id);
		if (message != null && message.getSeen() != 1) {
			message.setSeen(1);
			messageInfoService.updateMessage(message);
		}
		view.addObject("message", message);
		return view;

	}

}
