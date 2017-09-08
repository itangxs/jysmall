package cn.qhjys.mall.controller.seller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.seller.SellerMessageService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;

/***
 * 
 * @author zengrong
 * 2015-05-09
 */
@Controller
@RequestMapping("/managermall/seller/message")
public class SellerMessageController extends Base {
	
	@Autowired
	private SellerMessageService sellerMessageService;
	
	/***
	 * 商家消息列表
	 * @param session
	 * @param pageNum
	 * @param pageSize
	 * @param seen
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/messageCenterList")
	public ModelAndView messageCenter(
			HttpSession session,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "seen", required = false) Integer seen
			)throws Exception{
		ModelAndView view = new ModelAndView("/seller/message/business_msg_center");;
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(seller == null){
			view.setViewName("redirect:/seller/login.do");
			return view;
		}else{
			Long sellerId = seller.getId();
			if (page == null || page < 1)
				page = 1;
			if (pageSize == null || pageSize < 0)
				pageSize = 10;
			if(null == seen)
				seen = 0;
			Page<MessageInfo> list = sellerMessageService.queryMessageList(sellerId, seen, page, pageSize);
			view.addObject("page",list);
		}
		Page<MessageInfo> list = sellerMessageService.queryMessageList(seller.getId(), seen, page, pageSize);
		if(seen == 1){
			view.setViewName("/seller/message/business_msg_read");
		}
		view.addObject("page",list);
		return view;
	}
	
	
	/***
	 * 查看商家消息
	 * @param session
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryMessage")
	public ModelAndView queryMessage(
			HttpSession session,
			@RequestParam(value = "id", required = true) Long id
			)throws Exception{
		ModelAndView view = new ModelAndView("");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(seller == null){
			view.setViewName("redirect:/seller/login.do");
		}else{
			MessageInfo message = sellerMessageService.queryMessage(id);
			view.addObject("message", message);
		}
		return view;
	}
	
	/***
	 * 删除商家消息
	 * @param session
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
	public void deleteMessage(
			HttpSession session,
			HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id
			)throws Exception{
		JSONObject json = new JSONObject();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(seller == null){
			response.sendRedirect("/seller/login.do");
		}else{
			boolean isSuccess = sellerMessageService.deleteMessage(id);
			if(isSuccess == true){
				json.put("message", "删除成功");
			}else{
				json.put("message", "删除失败");
			}
		}
		HtmlUtil.writerJson(response, json);
	}
}
