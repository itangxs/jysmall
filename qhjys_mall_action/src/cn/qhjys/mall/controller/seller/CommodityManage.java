package cn.qhjys.mall.controller.seller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.CategoryService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.EvaluateVo;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;

/**
 * 商品管理
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/seller/commoditymanage")
public class CommodityManage extends Base {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	/**
	 * 
	 * @Title: pageList 商品管理分页 //通过的
	 * @param session
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @param name
	 *            商品名字
	 * @param jzsjs
	 *            截止时间 开始
	 * @param jzsje
	 *            截止时间 结束
	 * @param tjsjs
	 *            添加时间 开始
	 * @param tjsje
	 *            添加时间 结束
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/list")
	public Object pageList(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pagesize", required = false) Integer pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "jzsjs", required = false) String jzsjs,
			@RequestParam(value = "jzsje", required = false) String jzsje,
			@RequestParam(value = "tjsjs", required = false) String tjsjs,
			@RequestParam(value = "tjsje", required = false) String tjsje) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/list");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (!StringUtils.isEmpty(jzsjs))
			view.addObject("jzsjs", jzsjs);
		if ("".equals(jzsjs))
			jzsjs = null;
		if (!StringUtils.isEmpty(jzsje))
			view.addObject("jzsje", jzsje);
		if ("".equals(jzsje))
			jzsje = null;
		if (!StringUtils.isEmpty(tjsjs))
			view.addObject("tjsjs", tjsjs);
		if ("".equals(tjsjs))
			tjsjs = null;
		if (!StringUtils.isEmpty(tjsje))
			view.addObject("tjsje", tjsje);
		if ("".equals(tjsje))
			tjsje = null;
		if (null == page || page < 1)
			page = 1;
		view.addObject("name", name);
		if (page > 0)
			view.addObject("page", page);
		pageSize = 10;
		StoreInfo info = productService.queryStoreBySid(seller.getId());
		if (null == info)
			return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
		Page<ProductInfo> productInfo = productService.searchProductList(info.getId(), name, "2", tjsjs, tjsje, jzsjs,
				jzsje, page, pageSize);
		view.addObject("productInfo", productInfo);
		return view;
	}

	@RequestMapping("/examinelist")
	public Object examinelist(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pagesize", required = false) Integer pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "jzsjs", required = false) String jzsjs,
			@RequestParam(value = "jzsje", required = false) String jzsje,
			@RequestParam(value = "tjsjs", required = false) String tjsjs,
			@RequestParam(value = "tjsje", required = false) String tjsje) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/examinelist");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (!StringUtils.isEmpty(jzsjs))
			view.addObject("jzsjs", jzsjs);
		if ("".equals(jzsjs))
			jzsjs = null;
		if (!StringUtils.isEmpty(jzsje))
			view.addObject("jzsje", jzsje);
		if ("".equals(jzsje))
			jzsje = null;
		if (!StringUtils.isEmpty(tjsjs))
			view.addObject("tjsjs", tjsjs);
		if ("".equals(tjsjs))
			tjsjs = null;
		if (!StringUtils.isEmpty(tjsje))
			view.addObject("tjsje", tjsje);
		if ("".equals(tjsje))
			tjsje = null;
		if (null == page || page < 1)
			page = 1;
		view.addObject("name", name);
		if (page > 0)
			view.addObject("page", page);
		pageSize = 10;
		if (StringUtils.isEmpty(status))
			status = "01";
		view.addObject("status", status);
		StoreInfo info = productService.queryStoreBySid(seller.getId());
		if (null == info) {
			return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
		}
		Page<ProductInfo> productInfo = productService.searchProductList(info.getId(), name, status, tjsjs, tjsje,
				jzsjs, jzsje, page, pageSize);
		view.addObject("productInfo", productInfo);
		return view;
	}

	/**
	 */
	@RequestMapping("/shelvesList")
	public Object shelvesList(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pagesize", required = false) Integer pageSize,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "jzsjs", required = false) String jzsjs,
			@RequestParam(value = "jzsje", required = false) String jzsje,
			@RequestParam(value = "tjsjs", required = false) String tjsjs,
			@RequestParam(value = "tjsje", required = false) String tjsje) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/shelves_list");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (!StringUtils.isEmpty(jzsjs))
			view.addObject("jzsjs", jzsjs);
		if ("".equals(jzsjs))
			jzsjs = null;
		if (!StringUtils.isEmpty(jzsje))
			view.addObject("jzsje", jzsje);
		if ("".equals(jzsje))
			jzsje = null;
		if (!StringUtils.isEmpty(tjsjs))
			view.addObject("tjsjs", tjsjs);
		if ("".equals(tjsjs))
			tjsjs = null;
		if (!StringUtils.isEmpty(tjsje))
			view.addObject("tjsje", tjsje);
		if ("".equals(tjsje))
			tjsje = null;
		if (null == page || page < 1)
			page = 1;
		view.addObject("name", name);
		if (page > 0)
			view.addObject("page", page);
		pageSize = 10;
		StoreInfo info = productService.queryStoreBySid(seller.getId());
		if (null == info)
			return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
		Page<ProductInfo> productInfo = productService.searchProductList(info.getId(), name, "3", tjsjs, tjsje, jzsjs,
				jzsje, page, pageSize);
		view.addObject("productInfo", productInfo);
		return view;
	}

	/**
	 * 
	 * @Title: add_page 跳转添加商品页面
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/add_page")
	public Object add_page(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/add_page");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		StoreInfo info = productService.queryStoreBySid(seller.getId());
		if (null == info) {
			return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
		}
		String token = UUID.randomUUID().toString();
		JSONArray array = categoryService.queryCategoryList();
		SessionUtil.setSession(session, "add_product", token);
		view.addObject("token", token);
		view.addObject("array", array);
		view.addObject("sx", info.getCategoryId());
		return view;
	}

	/**
	 * 添加商品
	 * 
	 * @param session
	 * @param token
	 *            token
	 * @param spmc
	 *            商品名称
	 * @param splb
	 *            商品类别
	 * @param spms
	 *            商品描述
	 * @param fwcr
	 *            服务承诺
	 * @param kssj
	 *            开始日期
	 * @param jssj
	 *            结束日期
	 * @param scj
	 *            市场价
	 * @param bdj
	 *            本店价
	 * @param kssl
	 *            可售数量
	 * @param mrxg
	 *            每人限购
	 * @param sptpdz
	 *            商品展示图片地址
	 * @param gmxz
	 *            购买须知
	 * @param cpxq
	 *            产品详情
	 * @return
	 */
	@RequestMapping("/add")
	public Object add(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "spmc", required = true) String spmc,
			@RequestParam(value = "splb", required = false) String splb,
			@RequestParam(value = "spsx", required = true) Long spsx,
			@RequestParam(value = "spms", required = true) String spms,
			@RequestParam(value = "fwcr", required = false) String[] fwcr,
			@RequestParam(value = "kssj", required = true) String kssj,
			@RequestParam(value = "jssj", required = true) String jssj,
			@RequestParam(value = "xjsj", required = true) String xjsj,
			@RequestParam(value = "scj", required = true) String scj,
			@RequestParam(value = "unit", required = true) String unit,
			@RequestParam(value = "kssl", required = true) String kssl,
			@RequestParam(value = "mrxg", required = true) String mrxg,
			@RequestParam(value = "imgs", required = false) String[] sptpdz,
			@RequestParam(value = "gmxz", required = false) String gmxz,
			@RequestParam(value = "cpxq", required = false) String cpxq,
			@RequestParam(value = "werwer", required = false) String werwer) {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		Object tokenSession;
		try {
			tokenSession = SessionUtil.getSession(session, "add_product");
		
		SessionUtil.removeSession(session, "add_product");
		if (null != tokenSession && tokenSession.equals(token)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ProductInfo product = new ProductInfo();
			product.setName(spmc);// 商品名称
			CategoryInfo cate = categoryService.queryCategory(spsx);
			if (cate == null || cate.getId() == 3 || cate.getParentId() == 3)
				product.setProdType(2);
			else
				product.setProdType(1);
			product.setTitle(spms);// 商品描述
			if (null != fwcr) {
				String f = "";
				for (int i = 0; i < fwcr.length; i++)
					f += fwcr[i] + "|";
				product.setPromise(f); // 服务承诺
			}
			product.setCreateTime(new Date()); // 创建日期
			product.setStarDate(sdf.parse(kssj)); // 开始日期
			product.setEndDate(sdf.parse(jssj)); // 结束日期
			product.setOffShelf(sdf.parse(xjsj)); // 下架日期
			product.setOrigPrice(new BigDecimal(scj)); // 市场价
			product.setUnitPrice(new BigDecimal(unit)); // 折扣价
			product.setProdStock(Integer.valueOf(kssl));// 可售数量 --->商品库存
			product.setMaxPay(Integer.valueOf(mrxg)); // 每人限购 --> 限购量
			product.setHaoping(0);
			product.setZhongping(0);
			product.setChaping(0);
			product.setCategoryId(spsx);
			String imag = "";
			for (int i = 0; i < sptpdz.length; i++) {
				if (null != sptpdz[i] && !StringUtils.isEmpty(sptpdz[i]) && sptpdz[i].length() > 1) {
					imag += sptpdz[i] + "|";
				}
			}
			product.setImages(imag); // 商品展示图片
			product.setBuyingTips(gmxz); // 购买须知
			product.setProdDetail(cpxq); // 产品详情
			StoreInfo info = productService.queryStoreBySid(seller.getId());
			if (null == info) {
				return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
			}
			product.setStoreId(info.getId()); // 设置ID
			product.setStoreName(info.getName());
			/*
			 * //根据店铺设置是虚拟还是实物 if(info.getCategoryId()!=3){//不是3就是虚拟
			 * product.setProdType(Integer.valueOf(1));// 商品类型1 虚拟， }else{
			 * product.setProdType(Integer.valueOf(2));// 商品类型 2 实物', }
			 */
			Boolean bl = false;
			try {
				bl = productService.saveProduct(product);
			} catch (Exception e) {
				this.logger.error("添加商品异常", e);
			}

			if (bl) {
				return super.goUrl("/managermall/seller/commoditymanage/examinelist.do", "添加成功");
			} else {
				return super.goUrl("/managermall/seller/commoditymanage/list.do", "添加失败");
			}
		} else {// 提示错误
			return super.goUrl("/managermall/seller/commoditymanage/list.do", "请不要重复提交");
		}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			this.logger.error("添加商品异常", e1);
		}
		return super.goUrl("/managermall/seller/commoditymanage/list.do", "添加失败");
	}

	/**
	 * 跳转修改商品页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/update_page")
	public Object add_page(HttpSession session, @RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/update_page");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		StoreInfo info = productService.queryStoreBySid(seller.getId());
		if (null == info) {
			return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
		}
		ProductInfo pro = productService.getProductInfoById(info.getId(), id);
		if (pro.getStatus() == 2) {
			return super.goUrl("/managermall/seller/commoditymanage/list.do", "该商品不能修改");
		}
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "update_product", token);
		view.addObject("token", token);
		view.addObject("p", pro);
		JSONArray array = categoryService.queryCategoryList();
		view.addObject("array", array);
		view.addObject("sx", info.getCategoryId());
		return view;
	}

	@RequestMapping("/update")
	public Object add(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "spmc", required = true) String spmc,
			@RequestParam(value = "spsx", required = true) Long spsx,
			@RequestParam(value = "spms", required = true) String spms,
			@RequestParam(value = "fwcr", required = false) String[] fwcr,
			@RequestParam(value = "kssj", required = true) String kssj,
			@RequestParam(value = "jssj", required = true) String jssj,
			@RequestParam(value = "xjsj", required = true) String xjsj,
			@RequestParam(value = "scj", required = true) String scj,
			@RequestParam(value = "unit", required = true) String unit,
			@RequestParam(value = "kssl", required = true) String kssl,
			@RequestParam(value = "mrxg", required = true) String mrxg,
			@RequestParam(value = "imgs", required = false) String[] sptpdz,
			@RequestParam(value = "gmxz", required = false) String gmxz,
			@RequestParam(value = "cpxq", required = false) String cpxq,
			@RequestParam(value = "werwer", required = false) String werwer) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		logger.info("----------------------spmc----------------------" + spmc);
		logger.info("----------------------spsx----------------------" + spsx);
		logger.info("----------------------fwcr----------------------" + fwcr);
		logger.info("----------------------kssj----------------------" + kssj);
		logger.info("----------------------jssj----------------------" + jssj);
		logger.info("----------------------xjsj----------------------" + xjsj);
		logger.info("----------------------scj----------------------" + scj);
		logger.info("----------------------unit----------------------" + unit);
		logger.info("----------------------kssl----------------------" + kssl);
		logger.info("----------------------mrxg----------------------" + mrxg);
		logger.info("----------------------sptpdz----------------------" + sptpdz);
		logger.info("----------------------gmxz----------------------" + gmxz);
		logger.info("----------------------cpxq----------------------" + cpxq);
		logger.info("----------------------werwer----------------------" + werwer);
		logger.info("----------------------商家ID----------------------" + seller.getId());
		Object tokenSession = SessionUtil.getSession(session, "update_product");
		SessionUtil.removeSession(session, "update_product");
		if (null != tokenSession && tokenSession.equals(token)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StoreInfo info = productService.queryStoreBySid(seller.getId());
			if (null == info)
				return super.goUrl("/managermall/seller/center/page.do", "系统检测你没有店铺");
			ProductInfo product = productService.getProductInfoById(info.getId(), id);
			if (null == product)
				return super.goUrl("/managermall/seller/commoditymanage/list.do", "服务器忙,请稍候再试");
			product.setName(spmc);// 商品名称
			product.setTitle(spms);// 商品描述
			if (null != fwcr) {
				String f = "";
				for (int i = 0; i < fwcr.length; i++)
					f += fwcr[i] + "|";
				product.setPromise(f); // 服务承诺
			}
			product.setStarDate(sdf.parse(kssj)); // 开始日期
			product.setEndDate(sdf.parse(jssj)); // 结束日期
			product.setOffShelf(sdf.parse(xjsj)); // 下架日期
			product.setOrigPrice(new BigDecimal(scj)); // 市场价
			product.setUnitPrice(new BigDecimal(unit)); // 折扣价
			product.setProdStock(Integer.valueOf(kssl));// 可售数量 --->商品库存
			product.setMaxPay(Integer.valueOf(mrxg)); // 每人限购 --> 限购量
			product.setHaoping(0);
			product.setZhongping(0);
			product.setChaping(0);
			product.setCategoryId(spsx);
			String imag = "";
			for (int i = 0; i < sptpdz.length; i++)
				if (null != sptpdz[i] && !StringUtils.isEmpty(sptpdz[i]) && sptpdz[i].length() > 1)
					imag += sptpdz[i] + "|";
			product.setImages(imag); // 商品展示图片
			product.setBuyingTips(gmxz); // 购买须知
			product.setProdDetail(cpxq); // 产品详情
			product.setStatus(0);// 状态变为审核中
			product.setStoreId(info.getId());
			Boolean bl = productService.updateProduct(seller.getId(), product);
			if (bl)
				return super.goUrl("/managermall/seller/commoditymanage/examinelist.do", "修改成功");
			else
				return super.goUrl("/managermall/seller/commoditymanage/list.do", "修改失败");
		} else
			return super.goUrl("/managermall/seller/commoditymanage/list.do", "请不要重复提交");
	}

	@ResponseBody
	@RequestMapping("/updateload")
	public Object updateload(@RequestParam(value = "id", required = true) Long id) throws Exception {
		return id;
	}

	/**
	 * 
	 * @Title: shelves 下架
	 * @param session
	 * @param ids
	 *            下架的ID数组
	 * @return
	 * @throws Exception
	 * @return Object
	 */
	@RequestMapping("/shelves")
	public Object shelves(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		logger.info("----------------------下架的IDs----------------------" + ids);
		logger.info("----------------------商家ID----------------------" + seller.getId());
		/* Boolean bln = productService.deleteProduct(seller.getId(), ids); */
		boolean bln = productService.updateProduct(seller.getId(), ids, 3);// 3是下架
		logger.info("----------------------bln----------------------" + bln);
		if (bln) {
			/* HtmlUtil.writerJson(response, "success");// "下架成功"; */
			return super.goUrl("/managermall/seller/commoditymanage/list.do", "下架成功");
		} else {
			/* HtmlUtil.writerJson(response, "error");// "下架失败"; */
			return super.goUrl("/managermall/seller/commoditymanage/list.do", "下架失败");
		}
	}

	/***
	 * 商品评论列表(后台)
	 * 
	 * @param request
	 * @param session
	 * @param pageNum
	 *            分页条件
	 * @param pageSize
	 *            分页条件
	 * @param sellerId
	 *            商家编号
	 * @param productName
	 *            商品名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryEvaluateList")
	public ModelAndView queryEvaluateList(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pagesize", required = false) Integer pageSize,
			@RequestParam(value = "productName", required = false) String productName) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/evaluate_list");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			if (pageNum == null || pageNum < 1)
				pageNum = 1;
			if (pageSize == null || pageSize < 0)
				pageSize = 10;
			Page<EvaluateVo> evaluateList = productService.queryEvaluateList(seller.getId(), productName, pageNum,
					pageSize);
			view.addObject("page", evaluateList);
			view.addObject("productName", productName);
		}
		return view;
	}

	@RequestMapping(value = "/queryEvaluateDetail")
	public ModelAndView queryEvaluateDetail(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "reviewId", required = false) Long reviewId) throws Exception {
		ModelAndView view = new ModelAndView("/seller/commoditymanage/evaluate_detail");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			EvaluateVo evaluate = productService.queryEvaluateDetail(reviewId);
			view.addObject("evaluate", evaluate);
			String s = UUID.randomUUID().toString();
			view.addObject("sellerEvaluateToken", s);
			SessionUtil.setSession(session, "sellerEvaluateToken", s);
		}
		return view;
	}

	/***
	 * 商家回复评论
	 * 
	 * @param response
	 * @param request
	 * @param session
	 * @param id
	 *            评论编号
	 * @param sellerReplyContent
	 *            回复内容
	 * @throws Exception
	 */
	@RequestMapping(value = "/sellerEvaluate", method = RequestMethod.POST)
	public void sellerEvaluate(HttpServletResponse response, HttpServletRequest request, HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "sellerReplyContent", required = true) String sellerReplyContent) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "sellerEvaluateToken");
		SessionUtil.removeSession(session, "sellerEvaluateToken");
		if (null != tokenSession && tokenSession.equals(token)) {
			ReviewsLog review = new ReviewsLog();
			review.setId(id);
			review.setSellerReplyContent(sellerReplyContent);
			review.setSellerReplyTime(new Date());
			boolean isSuccess = productService.insertSellerEvaluate(review);
			if (isSuccess) {
				HtmlUtil.writerJson(response, "success");// 回复成功
				return;
			} else {
				HtmlUtil.writerJson(response, "failure");// 回复失败
				return;
			}
		} else {
			HtmlUtil.writerJson(response, "表单重复提交");// 表单重复提交
			return;
		}

	}
	
	@RequestMapping("/sellerexaminelist")
	public String test1(){
		return "/seller/commoditymanage/seller_examinelist";
	}
	@RequestMapping("/sellerlist")
	public String test2(){
		return "/seller/commoditymanage/seller_list";
	}
}
