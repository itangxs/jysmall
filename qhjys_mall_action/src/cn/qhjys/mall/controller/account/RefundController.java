package cn.qhjys.mall.controller.account;

import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserRefund;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.RefundService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.OrderDetailVo;
import com.github.pagehelper.Page;

/**
 * 用户退款
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/account/refund")
public class RefundController extends Base {
	@Autowired
	private OrderService orderService;
	@Autowired
	private RefundService refundService;

	/**
	 * 我的退款分页
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/account/refund/list");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (null == page || page < 1)
			page = 1;
		pageSize = 10;
		Long[] status = { 5L, 6L };
		Page<OrderDetailVo> pageInfo = orderService.queryOrderDetailList(user.getId(), status, page, pageSize);
		view.addObject("pageInfo", pageInfo);
		return view;
	}

	/**
	 * 退款详情
	 * 
	 * @param session
	 * @param id
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/datail")
	public ModelAndView myRefundDatail(HttpSession session, @RequestParam(value = "id", required = true) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/account/refund/datail");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		OrderDetailVo orderDetailVo = orderService.getOrderDetail(user.getId(), id);
		view.addObject("orderDetailVo", orderDetailVo);
		return view;
	}

	/**
	 * 去往退款页面
	 * 
	 * @param id
	 *            订单ID
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/toApply")
	public ModelAndView toApply(@RequestParam(value = "id", required = true) Long id, HttpSession session)
			throws Exception {
		ModelAndView view = new ModelAndView("/account/refund/apply_page");
		view.addObject("id", id);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "applyToken", token);
		view.addObject("token", token);
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		OrderDetailVo detailVo = orderService.getOrderDetail(user.getId(), id);
		view.addObject("detailVo", detailVo);
		return view;
	}

	/**
	 * 实物产品提交退款
	 * 
	 * @param session
	 * @param id
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/submitApply")
	public void submitApply(HttpSession session, HttpServletResponse response, String token, Long detailId,
			 String reason) throws Exception {
		String tk = (String) SessionUtil.getSession(session, "applyToken");

		SessionUtil.removeSession(session, "applyToken");
		if (!token.equals(tk)) {
			HtmlUtil.writerJson(response, "tokenError");
			return;
		}
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		OrderDetailVo vo = orderService.getOrderDetail(user.getId(), detailId);
		if (vo == null || vo.getStatus() == null || vo.getStatus() != 2) {
			HtmlUtil.writerJson(response, vo == null ? "该订单不存在！" : "该订单不能申请退款！");
			return;
		}
		
		UserRefund refund = new UserRefund();
		refund.setUserId(user.getId());
		refund.setDetailId(vo.getDetailId());
		refund.setRefundPrice(vo.getMoneny());
		refund.setRefundIntegral(vo.getIntegral());
		refund.setRefundReason(reason);
		System.out.println(refund.getRefundPrice());
		boolean result = orderService.insertRefund(refund);
		if (result)
			HtmlUtil.writerJson(response, "success");
		else
			HtmlUtil.writerJson(response, "error");
	}

	/***
	 * 确认收货
	 * 
	 * @param session
	 * @param response
	 * @param detailId
	 *            订单编号
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delivery")
	public void delivery(HttpSession session, HttpServletResponse response, Long detailId) {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		boolean isSuccess = false;
		try {
			isSuccess = orderService.updateDelivery(user.getId(), detailId, 3);
			if (isSuccess) {
				HtmlUtil.writerJson(response, "确认收货成功");
			} else {
				HtmlUtil.writerJson(response, "确认收货失败");
			}
		} catch (Exception e) {
			this.logger.error("确认收货异常：", e);
		}
	}
	
	/***
	 * 用户取消退款
	 * @param session
	 * @param response
	 * @param detailId 退款小订单号
	 */
	@ResponseBody
	@RequestMapping("/cancelRefund")
	public void cancelRefund(HttpSession session, HttpServletResponse response, Long detailId) {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		boolean isSuccess = false;
		try {
			isSuccess = orderService.updateCancelRefund(user.getId(), detailId);
			if (isSuccess) {
				HtmlUtil.writerJson(response, "success");
			} else {
				HtmlUtil.writerJson(response, "error");
			}
		} catch (Exception e) {
			this.logger.error("确认收货异常：", e);
		}
	}
}
