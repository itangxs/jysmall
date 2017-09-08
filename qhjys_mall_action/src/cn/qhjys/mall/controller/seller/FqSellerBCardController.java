package cn.qhjys.mall.controller.seller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;

@Controller
@RequestMapping("/managermall/seller/funds")
public class FqSellerBCardController extends Base {
	
	/**
	 * B卡券发放
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bcard_sent")
	public ModelAndView bcardSent(String startTime,String endTime,Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/bcard_sent");
		if (pageNum == null) {
			pageNum = 1;
		}
		
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		return view;
	}
	
	/**
	 * B卡券核销
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/bcard_off")
	public ModelAndView bcardOff(String startTime,String endTime,Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/bcard_off");
		if (pageNum == null) {
			pageNum = 1;
		}
		
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		return view;
	}
	
}