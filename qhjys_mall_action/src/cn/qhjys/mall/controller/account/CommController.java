package cn.qhjys.mall.controller.account;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CmsInfo;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.AdService;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CategoryService;
import cn.qhjys.mall.service.CollectService;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.PositionService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.ReviewService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.system.CmsService;
import cn.qhjys.mall.service.system.SystemSetService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.ReviewVo;
import cn.qhjys.mall.vo.StoreVo;
import cn.qhjys.mall.vo.TaskVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

@Controller
public class CommController extends Base {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private SystemSetService systemSetService;
	@Autowired
	private CmsService cmsService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private AdService adService;
	@Autowired
	private MessageInfoService messageInfoService;

	/**
	 * 生成验证码图片
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/valiCode")
	public void valiCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedImage img = new BufferedImage(90, 23, BufferedImage.TYPE_INT_RGB);
		try {
			String code = BaseUtil.valiCodeImage(img, 4, 90, 23);
			request.getSession().setAttribute("validateCode", code);
			ImageIO.write(img, "JPG", response.getOutputStream());
		} catch (IOException e) {
			this.logger.error("生成验证码图片:", e);
			throw new IOException("生成验证码图片:", e);
		}
	}

	@RequestMapping("/valiCode/codeyzm")
	public void codeyzm(@RequestParam(value = "codeYzm", required = true) String codeYzm, HttpServletRequest request,
			HttpServletResponse response) {
		String cod = (String) request.getSession().getAttribute("validateCode");
		if (codeYzm.equalsIgnoreCase(cod)) {
			HtmlUtil.writerJson(response, "success");
		} else {
			HtmlUtil.writerJson(response, "error");
		}
	}

	@RequestMapping("/mallcms/info")
	public ModelAndView info(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("account/cmsinfo");
		if (id.equals(5l) || id.equals(4l) || id.equals(3l) || id.equals(2l) || id.equals(1l) || id.equals(19l)
				|| id.equals(20l)) {
			CmsInfo cms = cmsService.getCmsInfoById(id);
			if (null != cms && 1 == cms.getEnabled()) {
				view.addObject("cms", cms);
			}
		}
		return view;
	}

	@RequestMapping("/mallcms/info2")
	public ModelAndView info2(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("account/cmsinfo2");
		if (id.equals(11l) || id.equals(12l) || id.equals(14l) || id.equals(15l) || id.equals(16l) || id.equals(17l)) {
			CmsInfo cms = cmsService.getCmsInfoById(id);
			if (null != cms && 1 == cms.getEnabled()) {
				view.addObject("cms", cms);
			}
		}
		return view;
	}

	@RequestMapping("/mallcms/info3")
	public ModelAndView info3(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("account/cmsinfo3");
		CmsInfo cms = cmsService.getCmsInfoById(id);
		if (id.equals(10l) || id.equals(9l) || id.equals(24l)) {
			if (null != cms && 1 == cms.getEnabled()) {
				view.addObject("cms", cms);
			}
		}
		return view;
	}

	@RequestMapping("/mallcms/info4")
	public ModelAndView info4(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("account/cmsinfo4");
		if (id.equals(8l) || id.equals(6l) || id.equals(7l) || id.equals(21l)) {
			CmsInfo cms = cmsService.getCmsInfoById(id);
			if (null != cms && 1 == cms.getEnabled()) {
				view.addObject("cms", cms);
			}
		}
		return view;
	}

	/**
	 * 生成首页
	 * 
	 * @param session
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index1")
	public ModelAndView index(HttpSession session, HttpServletRequest request) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}
		List<CategoryInfo> list = null;
		Map<String, List<ProductInfo>> map = null;
		JSONArray array = null;
		List<SysImg> sysImg = null;
		try {
			list = categoryService.queryCategoryByParent(0);
			map = productService.searchProductByCategory(list, cityId, "sales_num", 4);
			array = categoryService.queryCategoryList();
			sysImg = systemSetService.getSysImg((byte) 1, (byte) 7);
		} catch (Exception e) {
			this.logger.error("主页查询产品异常", e);
		}
		ModelAndView view = new ModelAndView("account/index");
		if (user != null) {
			boolean result = userInfoService.judgeSignIn(user.getId());
			view.addObject("signIn", result);
			view.addObject("user", user);
		}
		view.addObject("category", array);
		view.addObject("product", map);
		view.addObject("sysImg", sysImg);
		view.addObject("img", systemSetService.getWebsiteImg());
		return view;
	}
	@RequestMapping("/index")
	public ModelAndView index1(HttpSession session, HttpServletRequest request) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}
		List<CategoryInfo> list = null;
		Map<String, List<ProductInfo>> map = null;
		JSONArray array = null;
		List<SysImg> sysImg = null;
		List<ProductInfo> plist = null;
		try {
			list = categoryService.queryCategoryByParent(0);
			map = productService.searchProductByCategory(list, cityId, "sales_num", 4);
			array = categoryService.queryCategoryList();
			sysImg = systemSetService.getSysImg((byte) 1, (byte) 7);
			plist = productService.listProductInfoBycity(0, 12, cityId);
		} catch (Exception e) {
			this.logger.error("主页查询产品异常", e);
		}
		ModelAndView view = new ModelAndView("account/index1");
		Long userId = null;
		Integer userLevel = null;
		if (user != null) {
			userId = user.getId();
			userLevel = user.getLevel();
		}
		Page<TaskInfo> npage = taskInfoService.listTaskInfos(userId, userLevel, 0, 3);
		view.addObject("npage", npage);
		if (user != null) {
			boolean result = userInfoService.judgeSignIn(user.getId());
			view.addObject("signIn", result);
			view.addObject("user", user);
			String [] status = {"cp"};
			Integer [] status10 = {1};
			Page<TaskVo>  upage = taskInfoService.listTaskAll(user.getId(),status,status10, 0, 3);
			String [] status1 = {"c"};
			Integer [] status11 = {0};
			Page<TaskVo> unpage = taskInfoService.listTaskAll(user.getId(),status1,status11, 0, 3);
			
			view.addObject("upage", upage);
			view.addObject("unpage", unpage);
			
		}
		
		List<AdInfo> ads1 = adService.listAdInfoByAp(1L,cityId, 0, 4);
		List<AdInfo> ads2 = adService.listAdInfoByAp(2L,cityId,0, 3);
		AdInfo ads3 = adService.getAdInfoByAp(3L,cityId);
		view.addObject("category", array);
		view.addObject("product", map);
		view.addObject("sysImg", sysImg);
		view.addObject("ads1", ads1);
		view.addObject("ads2", ads2);
		view.addObject("ads3", ads3);
		
		view.addObject("plist", plist);
		view.addObject("img", systemSetService.getWebsiteImg());
		return view;
	}
	@RequestMapping("/getNotRead")
	@ResponseBody
	public void getNotRead(HttpSession session, HttpServletRequest request,HttpServletResponse response){
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		Integer messagenum = null;
		if(user != null){
		    messagenum = messageInfoService.countMessageByNotRead(user.getId());
		}
		HtmlUtil.writerJson(response, messagenum);
	}

	/**
	 * 修改所在城市
	 * 
	 * @param session
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeCity")
	public ModelAndView changeCity(HttpSession session, Long cityId) throws Exception {
		ModelAndView view = new ModelAndView("redirect:/");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user != null && user.getDefaultCity() != null && cityId != null) {
			user.setDefaultCity(cityId);
			userInfoService.updateUserById(user);
		} else
			cityId = cityId == null ? 200L : cityId;
		session.setAttribute("cityId", cityId);
		return view;
	}

	/**
	 * 类别商品搜索
	 * 
	 * @param session
	 * @param category
	 * @param sort
	 * @param area
	 * @param maxUse
	 * @param clause
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchProduct", params = "type=sort")
	public ModelAndView searchProdBySort(HttpSession session, Long category, Long sort, Long area, Integer maxUse,
			Integer priceRange, String clause, Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("account/prodList");
		if (category == null)
			return view;
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null)
			cityId = 200L;
		List<AreaInfo> areas = positionService.queryAreaByCity(cityId);
		List<CategoryInfo> list = categoryService.queryCategoryByParent(category);
		List<ProductInfo> prodCate = productService.searchProductListByRand(cityId);
		Page<ProductInfo> page = productService.searchProductByCategory(sort == null ? category : sort, cityId, area,
				maxUse, priceRange, clause, pageNum, pageSize);
		view.addObject("category", list);// 分类
		view.addObject("categoryID", category);// 分类
		view.addObject("detail", categoryService.queryCategory(category));
		view.addObject("page", page); // 商品
		view.addObject("areas", areas); // 区域
		view.addObject("parCate", category);
		view.addObject("sort", sort);
		view.addObject("area", area);
		view.addObject("maxUse", maxUse);
		view.addObject("priceRange", priceRange);
		view.addObject("clause", clause);
		view.addObject("type", "sort");
		view.addObject("prodCate", prodCate);
		List<AdInfo> ads1 = adService.listAdInfoByAp(9L,cityId, 0, 1);
		view.addObject("ad1", ads1.size()>0?ads1.get(0):new AdInfo());
		List<AdInfo> ads2 = adService.listAdInfoByAp(11L,cityId, 0, 1);
		view.addObject("ad2", ads2.size()>0?ads2.get(0):new AdInfo());
		List<AdInfo> ads3 = adService.listAdInfoByAp(12L,cityId, 0, 1);
		view.addObject("ad3", ads3.size()>0?ads3.get(0):new AdInfo());
		return view;
	}

	/**
	 * 关键字商品搜索
	 * 
	 * @param keywork
	 * @param category
	 * @param area
	 * @param store
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchProduct", params = "type=pord")
	public ModelAndView searchProdByKeyword(HttpSession session, String keywork, Long category, Long area,
			Integer priceRange, Long store, String clause, Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("account/prodList");
		keywork = java.net.URLDecoder.decode(keywork, "UTF-8");
		
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null)
			cityId = 200L;
		List<ProductInfo> prodCate = productService.searchProductListByRand(cityId);
		Page<ProductInfo> page = productService.searchProductByKeyWord(keywork, category, cityId, area, priceRange,
				store, clause, pageNum, pageSize);
		List<AreaInfo> areas = positionService.queryAreaByCity(cityId);
		view.addObject("page", page); // 商品
		view.addObject("areas", areas); // 区域
		view.addObject("keywork", keywork);
		view.addObject("parCate", category);
		view.addObject("area", area);
		view.addObject("priceRange", priceRange);
		view.addObject("clause", clause);
		view.addObject("type", "pord");
		view.addObject("prodCate", prodCate);
		List<AdInfo> ads1 = adService.listAdInfoByAp(9L,cityId, 0, 1);
		view.addObject("ad1", ads1.size()>0?ads1.get(0):new AdInfo());
		List<AdInfo> ads2 = adService.listAdInfoByAp(11L,cityId, 0, 1);
		view.addObject("ad2", ads2.size()>0?ads2.get(0):new AdInfo());
		List<AdInfo> ads3 = adService.listAdInfoByAp(12L,cityId, 0, 1);
		view.addObject("ad3", ads3.size()>0?ads3.get(0):new AdInfo());
		return view;
	}

	/**
	 * 搜索店铺
	 * 
	 * @param session
	 * @param keywork
	 * @param category
	 * @param areaId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/searchStore")
	public ModelAndView searchStoreByKeyword(HttpSession session, String keywork, Long category, Long area,
			String clause, Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("account/storeList");
		keywork = java.net.URLDecoder.decode(keywork, "UTF-8");
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null)
			cityId = 200L;
		List<CategoryInfo> list = categoryService.queryCategoryByParent(0);
		List<AreaInfo> areas = positionService.queryAreaByCity(cityId);
		Page<StoreVo> page = storeService.searchStores(keywork, category, cityId, area, clause, pageNum, pageSize);
		List<ProductInfo> prodCate = productService.searchProductListByRand(cityId);
		view.addObject("page", page); // 查询结果
		view.addObject("list", list); // 所有大分类
		view.addObject("areas", areas); // 所有区域
		view.addObject("prodCate", prodCate); // 随机商品
		view.addObject("category", category); // 分类条件
		view.addObject("area", area); // 区域条件
		view.addObject("clause", clause); // 排序条件
		view.addObject("keywork", keywork); // 关键字
		List<AdInfo> ads1 = adService.listAdInfoByAp(10L,cityId, 0, 1);
		view.addObject("ad1", ads1.size()>0?ads1.get(0):new AdInfo());
		List<AdInfo> ads2 = adService.listAdInfoByAp(13L,cityId, 0, 1);
		view.addObject("ad2", ads2.size()>0?ads2.get(0):new AdInfo());
		List<AdInfo> ads3 = adService.listAdInfoByAp(14L,cityId, 0, 1);
		view.addObject("ad3", ads3.size()>0?ads3.get(0):new AdInfo());
		return view;
	}

	/**
	 * 查询商品详细信息
	 * 
	 * @param session
	 * @param request
	 * @param prodId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getProdDetail")
	public ModelAndView getProdDetail(HttpSession session, HttpServletRequest request, Long prodId, Integer pageNum,
			Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("account/prodDetail");
		if (prodId == null)
			return null;
		ProductInfo product = productService.getProductInfoById(prodId);
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}
		view.addObject("product", product);
		if (product != null) {
			boolean colleted = false;
			if (user != null) {
				colleted = collectService.judgeProductIsCollect(user.getId(), prodId);
				productService.addUserBrowsed(user.getId(), prodId);
				List<OrderDetailVo> list = orderService.queryOrderDetaiVoByProdIdAndUserId(prodId, user.getId(), 3);
				System.out.println("----list.size()------"+list.size());
				if (list.size() > 0) { // 说明有购买记录 已消费状态
					view.addObject("shop", list);
					// 查看订单是否评论过
					outer: for (int i = 0; i < list.size(); i++) {
						List<EvaluateVo> pageo = reviewService.findEvaluateBySaUoSId(prodId, user.getId(), list.get(i)
								.getDetailId());
						System.out.println("----pageo.size()------"+pageo.size());
						if (pageo.size() < 1) {// 该商品用户没有评价
							view.addObject("evaNull", list.get(i));
							System.out.println("----list.get(i)------"+list.get(i));
							break outer;
						}
					}
				}
			}
			ReviewVo review = reviewService.getReviewByPordId(product.getId());
			StoreInfo store = storeService.getStoreDetaile(product.getStoreId());
			List<ProductInfo> other = productService.selectProductByStoreId(product.getStoreId());
			List<ProductInfo> prodCate = productService.searchProductListByRand(cityId);
			List<AdInfo> ads1 = adService.listAdInfoByAp(4L,cityId, 0, 1);
			List<AdInfo> ads2 = adService.listAdInfoByAp(5L,cityId, 0, 1);
			view.addObject("colleted", colleted);
			view.addObject("review", review);
			view.addObject("store", store);
			view.addObject("other", other);
			view.addObject("prodCate", prodCate);
			view.addObject("ad1", ads1.size()>0?ads1.get(0):new AdInfo());
			view.addObject("ad2", ads2.size()>0?ads2.get(0):new AdInfo());
		} else {
			view.setViewName("account/prodNotFind");
		}
		return view;
	}

	/**
	 * 获取商品评论信息
	 * 
	 * @param response
	 * @param prodId
	 * @param level
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping("/queryProdReviews")
	public void queryProdReviews(HttpServletResponse response, Long prodId, Integer level, Integer pageNum,
			Integer pageSize) throws Exception {
		if (prodId == null)
			return;
		if (level == null || level < 1)
			level = 1;

		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Page<EvaluateVo> page = reviewService.selectReviewLevelByProdId(prodId, level, pageNum, pageSize);
		JSONObject json = new JSONObject();
		json.put("num", page.getPageNum());
		json.put("size", page.getPageSize());
		json.put("pages", page.getPages());
		json.put("page", JSON.toJSON(page));
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 获取店铺详情
	 * 
	 * @param session
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStoreDetail")
	public ModelAndView getStoreDetail(HttpSession session, Long storeId) throws Exception {
		ModelAndView view = new ModelAndView("account/storeDetail");
		
		if (storeId == null)
			return null;
		StoreVo store = storeService.getStoreById(storeId);
		ReviewVo review = reviewService.getReviewByStoreId(storeId);
		List<ProductInfo> other = productService.selectProductByStoreId(storeId);
		
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		boolean collected = false;
		if (user != null && user.getId() != null)
			collected = collectService.judgeStroeIsCollect(user.getId(), storeId);
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}
		List<ProductInfo> prodCate = productService.searchProductListByRand(cityId);
		List<AdInfo> ads1 = adService.listAdInfoByAp(4L,cityId, 0, 1);
		List<AdInfo> ads2 = adService.listAdInfoByAp(5L,cityId, 0, 1);
		view.addObject("store", store);
		view.addObject("collected", collected);
		view.addObject("review", review);
		view.addObject("other", other);
		view.addObject("prodCate", prodCate);
		view.addObject("ad1", ads1.size()>0?ads1.get(0):new AdInfo());
		view.addObject("ad2", ads2.size()>0?ads2.get(0):new AdInfo());
		return view;
	}

	/**
	 * 获取店铺评论
	 * 
	 * @param response
	 * @param storeId
	 * @param level
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 */
	@RequestMapping("/queryStoreReviews")
	public void queryStoreReviews(HttpServletResponse response, Long storeId, Integer level, Integer pageNum,
			Integer pageSize) throws Exception {
		if (storeId == null)
			return;
		if (level == null || level < 1)
			level = 1;
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Page<EvaluateVo> page = reviewService.selectReviewLevelByStoreId(storeId, level, pageNum, pageSize);
		JSONObject json = new JSONObject();
		json.put("num", page.getPageNum());
		json.put("size", page.getPageSize());
		json.put("pages", page.getPages());
		json.put("page", JSON.toJSON(page));
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 获取分类菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCategoryHtml")
	public ModelAndView getCategoryHtml() throws Exception {
		ModelAndView view = new ModelAndView("account/categoryHtml");
		JSONArray array = categoryService.queryCategoryList();
		List<SysImg> sysImg = systemSetService.getSysImg((byte) 1, (byte) 7);
		view.addObject("array", array);
		view.addObject("sysImg", sysImg);
		return view;
	}

	/**
	 * 会员签到
	 * 
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/signIn")
	public void signIn(HttpSession session, HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			HtmlUtil.writerJson(response, "logOut");
			return;
		}
		boolean judge = userInfoService.judgeSignIn(user.getId());
		if (judge) {
			HtmlUtil.writerJson(response, false);
			return;
		}
		boolean result = userInfoService.addSignIn(user.getId());
		if (result) {
			CashAccount cashAccount = cashAccountService.queryCashAccount(null, user.getId());
			session.setAttribute("judgeSignIn", true);
			session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"), cashAccount);
		}
		HtmlUtil.writerJson(response, result);
	}
}