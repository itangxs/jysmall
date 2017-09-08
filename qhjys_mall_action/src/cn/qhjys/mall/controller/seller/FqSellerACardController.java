package cn.qhjys.mall.controller.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;

@Controller
@RequestMapping("/managermall/seller/funds")
public class FqSellerACardController extends Base {

	@RequestMapping("/acard_grant")
	public ModelAndView acardGrant(String startTime,String endTime,Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/acard_grant");
		if (pageNum == null) {
			pageNum = 1;
		}
		
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		return view;
	}
	
	@RequestMapping("/acard_verification")
	public ModelAndView acardVerification(String startTime,String endTime,Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/acard_verification");
		if (pageNum == null) {
			pageNum = 1;
		}
		
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		return view;
	}
	
}
