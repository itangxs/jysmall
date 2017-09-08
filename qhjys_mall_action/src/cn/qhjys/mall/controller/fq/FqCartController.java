package cn.qhjys.mall.controller.fq;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.WxLoginUrls;
import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.service.fq.FqProductService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;

@Controller
@RequestMapping("/user/fqcart")
public class FqCartController extends Base{
	
	@Autowired
	private FqProductService fqProductService;
	@Autowired
	private FqStoreService fqStoreService;

	@RequestMapping("/products")
	public Object products(HttpSession session,HttpServletRequest request, @RequestParam(value = "storeId", required = true) Long storeId,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "nums", required = true) Integer[] nums)
			throws Exception {
		ModelAndView view = new ModelAndView("/wxstore/cart");
		
		FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
//		if (storeId.equals(3156L)) {
//			user = new FqUserInfo();
//			user.setId(17L);
//			user.setOpenId("ob8WbwWQWmdZoykLjOvwCLw5CRmk");
//		}
		if (user == null) {
			String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
			String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
			WxLoginUrls.addMap(urlid, url);
			return "redirect:"+WxLoginUrls.getLoginUrl(urlid);
		}
		// 根据ids和店铺id获取商品内容
		List<FqProduct> products = fqProductService.queryProductsWithOrderedByIdsAndStoreId(ids, storeId);
		view.addObject("products", products);
		view.addObject("nums", nums);
		view.addObject("ids", ids);
		view.addObject("storeId", storeId);
		view.addObject("userId", user.getId());
		return view;
	}

	@RequestMapping("/confirmCart")
	public Object confirmCart(HttpSession session,HttpServletRequest request, @RequestParam(value = "storeId", required = true) Long storeId,@RequestParam(value = "ids", required = true) String ids,
			@RequestParam(value = "nums", required = true) String nums) throws Exception{
			FqUserInfo user =  (FqUserInfo) session.getAttribute(ConstantsConfigurer.getWxUser());
			if (user == null) {
				String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
				String urlid = System.currentTimeMillis()+BaseUtil.numRandom(6);
				WxLoginUrls.addMap(urlid, url);
				return "redirect:"+WxLoginUrls.getLoginUrl(urlid);
			}
			FqStore store = fqStoreService.getFqStoreById(storeId);
			ModelAndView view = new ModelAndView("/wxstore/confirmcart");
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
			view.addObject("nums", nums);
			view.addObject("ids", ids);
			view.addObject("userId", user.getId());
			view.addObject("store", store);
			view.addObject("today", sdf.format(new Date()));
			view.addObject("tomorrow", sdf.format(new Date(System.currentTimeMillis()+86400000L)));
			view.addObject("thridday", sdf.format(new Date(System.currentTimeMillis()+2*86400000L)));
			return view;
	}

}
