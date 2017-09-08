package cn.qhjys.mall.controller.system;

import java.util.List;

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
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.service.fq.FqUserInfoService;

@Controller
@RequestMapping("/managermall/systemmall/fqorder")
public class FqOrderController extends Base {
	
	@Autowired
	private FqOrderService fqOrderService;
	@Autowired
	private FqStoreService fqStoreService;
	@Autowired
	private FqUserInfoService fqUserInfoService;
	
	@RequestMapping("/list")
	public ModelAndView listFqStore(String storeName, String orderNo, Integer status,Integer payType,
			String createTimeBegin, String createTimeEnding, String bookTimeBegin,
			String bookTimeEnding,Integer pageNum, Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("system/fqorder/list");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		Page<FqOrder> page = fqOrderService.searchOrderListByParams(storeName, orderNo, status, payType, createTimeBegin, createTimeEnding, bookTimeBegin, bookTimeEnding, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("storeName", storeName);
		view.addObject("orderNo", orderNo);
		view.addObject("status", status);
		view.addObject("payType", payType);
		view.addObject("createTimeBegin", createTimeBegin);
		view.addObject("createTimeEnding", createTimeEnding);
		view.addObject("bookTimeBegin", bookTimeBegin);
		view.addObject("bookTimeEnding", bookTimeEnding);
		return view;
		
	}
	
	@RequestMapping("/getOrderDetail")
	public ModelAndView getOrderDetail(Long id){
		ModelAndView view = new ModelAndView("system/fqorder/detail");
		FqOrder order = fqOrderService.getFqOrder(id);
		List<FqOrderDetail> details = fqOrderService.queryOrderDetailByOrderId(order.getId());
		FqStore store = fqStoreService.getFqStoreById(order.getStoreId());
		FqUserInfo user = fqUserInfoService.getFqUserInfoById(order.getUserId());
		view.addObject("order", order);
		view.addObject("details", details);
		view.addObject("store", store);
		view.addObject("user", user);
		return view;
	}
}
