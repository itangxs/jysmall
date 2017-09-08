package cn.qhjys.mall.controller.fq;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.service.fq.FqUserInfoService;
import cn.qhjys.mall.util.ConstantsConfigurer;

@Controller
@RequestMapping("/managermall/seller/fqorder")
public class SellerFqOrderController extends Base{
	@Autowired
	private FqOrderService fqOrderService;
	@Autowired 
	private FqStoreService fqStoreService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	
	@RequestMapping("/orderList")
	public Object queryOrderList(HttpSession session, String orderNo, Integer status,
			String createTimeBegin, String createTimeEnding, String bookTimeBegin,
			String bookTimeEnding,Integer pageNum, Integer pageSize)
			throws Exception {
		ModelAndView view = new ModelAndView("/seller/fqorder/list");
		if (pageNum == null || pageNum < 1)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		FqStore fqStore = fqStoreService.getFqStoreBySellerId(seller.getId());
		if (fqStore == null || fqStore.getStatus() != 1 ) {
			// 店铺不存在 
			return super.goUrl("/managermall/seller/queryWXStoreInfo.do", "店铺信息不存在或未通过审核");
		}
		Page<FqOrder> page = fqOrderService.searchOrderListByStoreId(fqStore.getId(), orderNo, status, 
				createTimeBegin, createTimeEnding, bookTimeBegin, bookTimeEnding, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("orderNo", orderNo);
		view.addObject("status", status);
		view.addObject("createTimeBegin", createTimeBegin);
		view.addObject("createTimeEnding", createTimeEnding);
		view.addObject("bookTimeBegin", bookTimeBegin);
		view.addObject("bookTimeEnding", bookTimeEnding);
		return view;
	}
	
	@RequestMapping("/orderDetail")
	public ModelAndView getOrderDetail(Long id){
		ModelAndView view = new ModelAndView("/seller/fqorder/detail");
		FqOrder order = fqOrderService.getFqOrder(id);
		List<FqOrderDetail> details = fqOrderService.queryOrderDetailByOrderId(order.getId());
		FqUserInfo user = fqUserInfoService.getFqUserInfoById(order.getUserId());
		view.addObject("order", order);
		view.addObject("details", details);
		view.addObject("user", user);
		return view;
	}
	
}
