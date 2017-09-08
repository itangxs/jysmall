package cn.qhjys.mall.controller.seller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.SellerScheduleService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.TIMECONST;
import cn.qhjys.mall.vo.SellerScheduleVo;
import cn.qhjys.mall.vo.seller.SellerScheduleListVo;
import cn.qhjys.mall.vo.seller.SellerScheduleProductInfoVo;

/**
 * 商家(卖家) 预约管理
 * 
 * @author c zhao
 *
 */
@Controller
@RequestMapping("/managermall/seller/schedule")
public class SellerScheduleController extends Base {

	@Autowired
	private SellerScheduleService sellerScheduleService;

	public SellerScheduleService getSellerScheduleService() {
		return sellerScheduleService;
	}

	public void setSellerScheduleService(SellerScheduleService sellerScheduleService) {
		this.sellerScheduleService = sellerScheduleService;
	}

	/**
	 * 预约管理页面
	 * 
	 * @param session
	 * @param date
	 *            开始时间
	 * @param dateadd
	 *            开始时间加多少
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productList")
	public ModelAndView productList(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		view.setViewName("/seller/schedule/list");
		List<SellerScheduleProductInfoVo> sspv = sellerScheduleService.getSellerScheduleProductInfoVoList(seller
				.getId());
		view.addObject("sellerScheduleProductList", sspv);
		return view;
	}

	/**
	 * 更新某一个产品的预订表单页面
	 * 
	 * @param session
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateScheduleProduct")
	public ModelAndView updateScheduleProduct(HttpSession session, @RequestParam(value = "id", required = true) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		view.setViewName("/seller/schedule/updateScheduleProduct");
		SellerScheduleProductInfoVo sspv = sellerScheduleService.getSellerScheduleProductInfoVo(seller.getId(), id);
		view.addObject("sellerScheduleProductInfoVo", sspv);
		return view;
	}

	/**
	 * 保存某一个产品的预约信息
	 * 
	 * @param session
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveScheduleProduct")
	public void saveScheduleProduct(HttpSession session, @RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "scheduleType", required = true) Integer scheduleType,
			@RequestParam(value = "scheduleer", required = true) Integer scheduleer) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		boolean bl = sellerScheduleService.updateProductSchedule(seller.getId(), id, scheduleType, scheduleer);
		if (bl)
			super.goUrl("/managermall/seller/schedule/updateScheduleProduct.do?id=" + id, "操作成功!");
		super.goUrl("/managermall/seller/schedule/updateScheduleProduct.do?id=" + id, "操作失败!");
	}

	/**
	 * 预约管理页面数据
	 * 
	 * @param session
	 * @param dateadd
	 *            开始时间加多少
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productListAjax")
	public void productListAjax(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "dateadd", required = true) Integer dateadd) throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		view.setViewName("/seller/schedule/list");
		SellerScheduleListVo result = sellerScheduleService.getSellerScheduleListVoBySellerId(seller.getId(), new Date(
				new Date().getTime() + TIMECONST.DAYLONG * 7 * dateadd));
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 商品预约管理
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/businessOrderlistManage")
	public ModelAndView businessOrderlistManage(HttpSession session,
			@RequestParam(value = "phone", required = false) String phone) throws Exception {
		ModelAndView view = new ModelAndView("/seller/schedule/businessOrderlistManage");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		List<SellerScheduleVo> list = sellerScheduleService.getSellerScheduleVoList(seller.getId(), 1, phone);
		List<SellerScheduleVo> list2 = sellerScheduleService.getSellerScheduleVoListThan(seller.getId(), 1, phone);
		view.addObject("sellerScheduleVoList1", list);
		view.addObject("sellerScheduleVoList2", list2);
		return view;
	}

	/**
	 * 同意或者拒绝某订单的预约
	 * 
	 * @param session
	 * @param id
	 *            预约ID
	 * @param status
	 *            需修改的状态
	 * @throws Exception
	 */
	@RequestMapping("/agreement")
	public void agreement(HttpSession session, @RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "status", required = true) Integer status) throws Exception {
		if (status.equals(2) || status.equals(3)) {
			SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
			if (sellerScheduleService.updateSellerSchedule(seller.getId(), id, status))
				super.goUrl("/managermall/seller/schedule/businessOrderlistManage.do", "操作成功!");
		}
		super.goUrl("/managermall/seller/schedule/businessOrderlistManage.do", "操作失败!");

	}

	/**
	 * 商户预约管理
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/businessOrderlistOperate")
	public ModelAndView businessOrderlistOperate(HttpSession session,
			@RequestParam(value = "phone", required = false) String phone) throws Exception {
		ModelAndView view = new ModelAndView("/seller/schedule/businessOrderlistOperate");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		List<SellerScheduleVo> list = sellerScheduleService.getSellerScheduleVoBySellerList(seller.getId(), 1, phone);
		List<SellerScheduleVo> list2 = sellerScheduleService.getSellerScheduleVoBySellerListThan(seller.getId(), 1,
				phone);
		view.addObject("sellerScheduleVoList1", list);
		view.addObject("sellerScheduleVoList2", list2);
		return view;
	}

}
