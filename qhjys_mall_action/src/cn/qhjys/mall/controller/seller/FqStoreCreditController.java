package cn.qhjys.mall.controller.seller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.fq.FqStoreCreditService;
import cn.qhjys.mall.util.ConstantsConfigurer;

@Controller
@RequestMapping("/managermall/seller/credit")
public class FqStoreCreditController {
	@Autowired
	private FqStoreCreditService fqStoreCreditService;
	
	@RequestMapping("/list")
	public ModelAndView list(HttpSession session,Integer pageNum,Integer pageSize){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("/seller/credit/list");
		FqStoreCredit credit = fqStoreCreditService.getLastCredit(seller.getId());
		if (credit != null) {
			view.addObject("credit", credit);
			List<FqThirdPay> page = fqStoreCreditService.queryFqThirdPayBySeller(seller.getId(), pageSize, pageNum);
			view.addObject("page", page);
		}
		return view;
	}
}
