package cn.qhjys.mall.controller.seller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.WxUserVoService;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.service.fq.FqOrderService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.vo.FqOrderVo;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.SellerUserInfoVo;
import cn.qhjys.mall.vo.WxUserVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/seller/wxuser")
public class SellerWxUserController {
	@Autowired
	private WxUserVoService wxUserVoService;
	@Autowired
	private RebateOrderService rebateOrderService;
	@Autowired
	private FqOrderService fqOrderService;
	@Autowired
	private SellerUserCountService sellerUserCountService;
	
	@RequestMapping("/list")
	public ModelAndView wxUserList(HttpSession session,Integer pageSize,Integer pageNum){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view =new  ModelAndView("seller/wxuser/list");
		Page<SellerUserInfoVo> page = sellerUserCountService.querySellerUserInfoVo(seller.getId(), "beTime", "DESC", pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}
	@RequestMapping("/rebateOrder")
	public ModelAndView userRebateOrder(HttpSession session,Integer pageNum,Integer pageSize,String openId){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view =new  ModelAndView("seller/wxuser/rebateorder");
		if (pageNum == null || pageNum < 1)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		
//		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVo(null, null, null, seller.getId(), null, null, openId, pageNum, pageSize);
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVo(seller.getId(),null, null, openId, null, null, pageNum, pageSize,2, null, null);
		view.addObject("page", page);
		view.addObject("openId", openId);
		return view;
	}
	@RequestMapping("/fqOrder")
	public ModelAndView userFqOrder(HttpSession session,Integer pageNum,Integer pageSize,String openId){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view =new  ModelAndView("seller/wxuser/fqorder");
		if (pageNum == null || pageNum < 1)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Page<FqOrder> page = fqOrderService.listFqOrderByStoreIdAndOpenId(seller.getId(), openId, pageSize, pageNum);
		List<FqOrderVo> fovs = new ArrayList<FqOrderVo>();
		if (null != page) {
			for (int i = 0; i < page.size(); i++) {
				FqOrderVo fov = new FqOrderVo(page.get(i));
				fov.setOrderDetails(fqOrderService.queryOrderDetailByOrderId(page.get(i).getId()));
				fovs.add(fov);
			}
		}
		view.addObject("page", page);
		view.addObject("fovs", fovs);
		view.addObject("openId", openId);
		return view;
	}
	
		
}
