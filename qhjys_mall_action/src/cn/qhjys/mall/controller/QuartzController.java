package cn.qhjys.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.service.fq.FqSellerStatementService;

@Controller
@RequestMapping("/quartz")
public class QuartzController extends Base{
	
	@Autowired
	private FqSellerStatementService fqSellerStatementService;
	

	@RequestMapping("/updateSellerQuartz") 
	@ResponseBody
	public boolean updateSellerQuartz() throws Exception{
		return fqSellerStatementService.updateSellerStatementAndAutoWithdrawByQuartz();
	}
	
	
}
