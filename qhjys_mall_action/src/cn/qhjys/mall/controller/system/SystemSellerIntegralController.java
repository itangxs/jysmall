package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.CashIntegralLog;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashIntegralLogService;

@Controller
@RequestMapping("/managermall/systemmall/integral")
public class SystemSellerIntegralController {
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private CashIntegralLogService cashIntegralLogService;
	
	@RequestMapping("/goRecharge")
	public ModelAndView goRecharge(){
		ModelAndView view = new ModelAndView("system/yingxiao/yingxiao_chongzhi");
		return view;
	}
	
	@RequestMapping("/recharge")
	@ResponseBody
	public String recharge(Long storeId,BigDecimal money,String remark){
		try {
			int a = cashAccountService.updateIntegralByRecharge(storeId, money,remark);
			if (a == -1) {
				return "店铺错误";
			}else if(a == 1){
				return "充值成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "充值失败";
		}
		return "充值失败";
	}
	@RequestMapping("/list")
	public ModelAndView list(Long storeId,String storeName,Integer type,Integer pageNum,Integer pageSize){
		ModelAndView view = new ModelAndView("system/yingxiao/yingxiao_list");
		Page<CashIntegralLog> page = cashIntegralLogService.queryCashIntegralLog(storeId, storeName,type, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("storeId", storeId);
		view.addObject("storeName", storeName);
		view.addObject("type", type);
		return view;
	}
}
