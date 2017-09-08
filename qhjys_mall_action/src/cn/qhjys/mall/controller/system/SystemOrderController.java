package cn.qhjys.mall.controller.system;

import java.text.ParseException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.OrderQuery;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/system/order")
public class SystemOrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StoreService storeService;

	/**
	 * 订单列表
	 * 
	 * @param session
	 * @param query
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/list")
	public ModelAndView queryOrderList(HttpSession session, OrderQuery query, Integer pageNum, Integer pageSize)
			throws ParseException {
		ModelAndView view = new ModelAndView("system/order/list");
		String orderNo = query.getOrderNo();
		String detailNo = query.getDetailNo();
		Integer status = query.getStatus();
		String storeName = query.getStoreName();
		String userName = query.getUserName();
		String begin = query.getBegin();
		String ending = query.getEnding();
		Page<OrderDetailVo> page = orderService.queryOrderListByAdmin(orderNo, detailNo, status, storeName, userName,
				begin, ending, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("query", query);
		return view;
	}

	/**
	 * 获取订单详情
	 * 
	 * @param session
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getOrderDetail")
	public ModelAndView getOrderDetail(HttpSession session, Long det) throws Exception {
		ModelAndView view = new ModelAndView("system/order/detail");
		if (det == null) {
			view.setViewName("redirect:/managermall/system/order/list.do");
			return view;
		}
		OrderDetailVo detail = orderService.getOrderDetail(null, det);
		UserInfo user = userInfoService.selectUserById(detail.getUserId());
		ProductInfo product = productService.findProduct(detail.getProdId());
		StoreInfo store = storeService.getStoreByProdId(detail.getProdId());
		view.addObject("user", user);
		view.addObject("product", product);
		view.addObject("store", store);
		view.addObject("detail", detail);
		return view;
	}
}