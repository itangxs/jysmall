package cn.qhjys.mall.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qhjys.mall.service.PayCheckService;
import cn.qhjys.mall.util.DateUtil;

@Controller
@RequestMapping("/paycheck")
public class PayCheckController {
	@Autowired
	private PayCheckService payCheckService;
	
	@RequestMapping("/checkWxPay")
	@ResponseBody
	public int checkWxPay(String beginTime,String endTime){
		Date beginDate = new Date();
		Date endDate = new Date();
		if (StringUtils.isEmpty(beginTime)) {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			beginDate = c.getTime();
		}else{
			beginDate = DateUtil.convertStringToDate(beginTime);
		}
		if (!StringUtils.isEmpty(endTime)) {
			endDate = DateUtil.convertStringToDate(endTime);
		}
		
		int a = payCheckService.updatePayCheck(beginDate, endDate);
		System.out.println(a+"-----aa");
		return a;
	}
	
}
