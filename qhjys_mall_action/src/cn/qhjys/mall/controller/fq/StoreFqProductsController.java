package cn.qhjys.mall.controller.fq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.fq.FqProductService;
import cn.qhjys.mall.service.fq.FqProductTypeService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.vo.FqTypeAndProductVo;

@Controller
@RequestMapping("/store/fqproducts")
public class StoreFqProductsController extends Base{
	
	@Autowired
	private FqProductService fqProductService;
	
	@Autowired
	private FqProductTypeService fqProductTypeService;
	@Autowired
	private SellerWXService sellerWXService;
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/onlineorder")
	public Object onlineOrder(HttpSession session, @RequestParam(value = "storeId", required = true) Long storeId)
			throws Exception {
		ModelAndView view = new ModelAndView("/wxstore/onlineorder");
		FqStore store = sellerWXService.queryFqStoreById(storeId, 1);
		String a = DateUtil.convertDateToString("HH:mm", new Date());
		if (a.compareTo(store.getTrafficBeginTime())<0 || a.compareTo(store.getTrafficEndTime())>0) {
			view.setViewName("/weixin/fqStore/detail-xx");
			return view;
		}
		StoreInfo store1 = storeService.queryStoreInfoBySeller(store.getSellerId());
		if (null == store1.getOpenOrder() || store1.getOpenOrder().equals(0)) {
			view.setViewName("/weixin/fqStore/detail-tc");
		}
		// 根据商家id查询商家分类
		List<FqProductType> productTypes = fqProductTypeService.queryEnableProductTypeByStoreId(storeId);
		// 根据商家id和分类id查询菜品
		List<FqTypeAndProductVo> typeAndProducts = fqProductService.queryProductByTypesAndStoreId(productTypes, storeId);
		view.addObject("storeId",storeId);
		view.addObject("productTypes", productTypes);
		view.addObject("typeAndProducts", typeAndProducts);
		return view;
	}
	
	@RequestMapping("/dishesdetail")
	public Object dishesDetail(HttpSession session, @RequestParam(value = "storeId", required = true) Long storeId,
			@RequestParam(value = "prodId", required = true) Long prodId,
			@RequestParam(value = "currentNum", required = true) Integer currentNum)
			throws Exception {
		ModelAndView view = new ModelAndView("/wxstore/dishesdetail");
		// 根据店铺和产品id查询产品
		FqProduct product = fqProductService.queryProductByIdAndStoreId(prodId, storeId);
		view.addObject("product", product);
		view.addObject("currentNum", currentNum);
		view.addObject("storeId", storeId);
		view.addObject("totalPrice", product.getPrice().multiply(new BigDecimal(currentNum)));
		return view;
	}
}
