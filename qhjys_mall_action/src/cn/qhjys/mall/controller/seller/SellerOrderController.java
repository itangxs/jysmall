package cn.qhjys.mall.controller.seller;

import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.UserRefund;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.RefundVo;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/seller/order")
public class SellerOrderController extends Base {
	@Autowired
	private OrderService orderService;

	/**
	 * 商品订单
	 * 
	 * @param session
	 * @param detailNo
	 * @param prodName
	 * @param orStatus
	 * @param begin
	 * @param ending
	 * @param userName
	 * @param reStatus
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderList")
	public ModelAndView queryOrderList(HttpSession session, String detailNo, String prodName, Integer orStatus,
			String begin, String ending, String userName, Integer reStatus, Integer pageNum, Integer pageSize)
			throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Long[] orstat = null, restat = null;
		orstat = new Long[1];
		if (orStatus != null && orStatus != 0) {
			orstat[0] = Long.valueOf(orStatus);
		} else
			orstat[0] = 3L;
		if (reStatus != null && reStatus != 0) {
			restat = new Long[1];
			restat[0] = Long.valueOf(reStatus);
		}
		Page<OrderDetailVo> page = orderService.searchOrderListBySellerId(seller.getId(), detailNo, prodName, orstat,
				begin, ending, userName, restat, pageNum, pageSize);
		view.setViewName("/seller/order/list");
		view.addObject("page", page);
		view.addObject("detailNo", detailNo);
		view.addObject("prodName", prodName);
		view.addObject("userName", userName);
		view.addObject("begin", begin);
		view.addObject("ending", ending);
		return view;
	}

	/**
	 * 未完成订单
	 * 
	 * @param session
	 * @param detailNo
	 * @param prodName
	 * @param begin
	 * @param ending
	 * @param userName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/unfinishOrder")
	public ModelAndView queryUnfinishOrderList(HttpSession session, String detailNo, String prodName, String begin,
			String ending, String userName, Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Long[] orstat = { 2L }, restat = null;
		int count = orderService.getOrderCountBySeller(seller.getId(), detailNo, prodName, orstat, begin, ending,
				userName);
		Page<OrderDetailVo> page = orderService.searchOrderListBySellerId(seller.getId(), detailNo, prodName, orstat,
				begin, ending, userName, restat, pageNum, pageSize);
		view.setViewName("/seller/order/unfinishOrder");
		view.addObject("count", count);
		view.addObject("page", page);
		view.addObject("detailNo", detailNo);
		view.addObject("prodName", prodName);
		view.addObject("begin", begin);
		view.addObject("ending", ending);
		view.addObject("userName", userName);
		return view;
	}

	/**
	 * 客户退款订单
	 * 
	 * @param session
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refundOrder")
	public ModelAndView queryRefundOrder(HttpSession session, String detailNo, Long orderStatus, Integer pageNum,
			Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		if (null == orderStatus)
			orderStatus = 5L;
		Page<RefundVo> page = orderService.queryRefundListBySellerId(seller.getId(), detailNo, orderStatus, pageNum,
				pageSize);
		view.addObject("orderStatus", orderStatus);
		view.setViewName("/seller/order/refundOrder");
		view.addObject("page", page);
		return view;
	}

	/**
	 * 退款详情
	 * 
	 * @param session
	 * @param refundId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refundDetail")
	public ModelAndView getRefundDetail(HttpSession session, Long id) throws Exception {
		ModelAndView view = new ModelAndView();
		view.setViewName("/seller/order/refundDetail");
		UserRefund refund = orderService.getRefundById(id);
		view.addObject("refund", refund);
		String s = UUID.randomUUID().toString();
		view.addObject("refundToken", s);
		SessionUtil.setSession(session, "refundToken", s);
		return view;
	}

	/***
	 * 商家退款
	 * 
	 * @param session
	 * @param id
	 *            退款编号
	 * @param isRefund
	 *            是否退款
	 * @param rejectReason
	 *            拒绝理由
	 * @throws Exception
	 */
	@RequestMapping("/auditRefund")
	public void auditRefund(HttpSession session, HttpServletResponse response, Long id, int isRefund,
			String rejectReason, String token) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "refundToken");
		SessionUtil.removeSession(session, "refundToken");
		if (null != tokenSession && tokenSession.equals(token)) {
			UserRefund refund = new UserRefund();
			refund.setId(id);
			// 审核通过
			refund.setStatus(3);
			if (isRefund == 0) {
				// 审核失败
				refund.setRejectReason(rejectReason);
			}
			try {
				boolean isSuccess = orderService.updateRefund(isRefund, refund);
				if (isSuccess) {
					HtmlUtil.writerJson(response, "success");
				} else {
					HtmlUtil.writerJson(response, "error");
				}
			} catch (Exception e) {
				this.logger.error("审核退款订单异常：", e);
				HtmlUtil.writerJson(response, "审核退款订单异常");
			}
		} else {
			HtmlUtil.writerJson(response, "表单重复提交");// 表单重复提交
			return;
		}
	}

	/***
	 * 商家发货
	 * 
	 * @param session
	 * @param response
	 * @param orderId
	 *            详单编号
	 * @param express
	 *            快递供应商
	 * @param expressId
	 *            快递单号
	 */
	@RequestMapping(value = "/delivery")
	public void delivery(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "orderId", required = true) Long orderId,
			@RequestParam(value = "express", required = true) String express,
			@RequestParam(value = "expressId", required = true) String expressId) throws Exception {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setId(orderId);
		orderDetail.setExpressId(expressId);
		orderDetail.setExpress(express);
		try {
			boolean isSuccess = orderService.updateOrderDetailById(orderDetail);
			if (isSuccess) {
				HtmlUtil.writerJson(response, "success");
			} else {
				HtmlUtil.writerJson(response, "error");
			}
		} catch (Exception e) {
			this.logger.error("商家发货异常：", e);
			HtmlUtil.writerJson(response, "商家发货异常");
		}
	}
}