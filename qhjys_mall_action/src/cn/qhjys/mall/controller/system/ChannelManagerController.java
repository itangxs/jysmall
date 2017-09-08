package cn.qhjys.mall.controller.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.entity.FqChannelRole;
import cn.qhjys.mall.service.FqChannelRoleService;
import cn.qhjys.mall.util.DateUtil;

@RequestMapping("/managermall/systemmall/channel")
@Controller
public class ChannelManagerController {
	
	@Autowired
	private FqChannelRoleService  fqChannelRoleService;
	
	@RequestMapping("/effectiveStore")
	public ModelAndView effectiveStore(){
		ModelAndView view = new ModelAndView("system/strategySet/effective_store_strategy");
		FqChannelRole fqChannelRole = fqChannelRoleService.getLastFqChannelRole(1);
		view.addObject("fqChannelRole", fqChannelRole);
		return view;
	}
	@RequestMapping("/cashOrder")
	public ModelAndView cashOrder(){
		ModelAndView view = new ModelAndView("system/strategySet/cash_order_strategy");
		FqChannelRole fqChannelRole = fqChannelRoleService.getLastFqChannelRole(2);
		view.addObject("fqChannelRole", fqChannelRole);
		return view;
	}
	
	@RequestMapping("/addEffectiveStore")
	public String  addEffectiveStore(FqChannelRole fqChannelRole,String effectiveDate){
		fqChannelRole.setCreateTime(new Date());
		if (StringUtils.isEmpty(effectiveDate)) {
			fqChannelRole.setEffectiveTime(fqChannelRole.getCreateTime());
		}else{
			effectiveDate += " 1:00:00";
			fqChannelRole.setEffectiveTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", effectiveDate));
		}
		int a = fqChannelRoleService.insertFqChannelRole(fqChannelRole);
		if (a>0) {
			return "SUCCESS";
		}
		return "ERROR";
	}
}
