package cn.qhjys.mall.controller.account;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.OrderService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.ReviewService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.vo.OrderDetailVo;

import com.github.pagehelper.Page;

/**
 * 我的评价
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/account/evaluate")
public class EvaluateController extends Base {
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ProductService productService;

	/**
	 * 
	 * @Title: MyEclist 评价分页
	 * @param session
	 * @param page
	 * @param pageSize
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/list")
	public ModelAndView MyEclist(HttpSession session, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/account/evaluate/list");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
			return view;
		} else {
			if (null == page || page < 1)
				page = 1;
			if (page > 0)
				view.addObject("page", page);
			pageSize = 10;
			Long[] status = { 3L, 4L };
			Page<OrderDetailVo> pageInfo = orderService.queryOrderDetailList(user.getId(), status, page, pageSize);
			view.addObject("pageinfo", pageInfo);
		}

		return view;
	}

	// 去添加评论页面
	@RequestMapping("/toAddPage")
	public ModelAndView toAddPage(HttpSession session, @RequestParam(value = "id", required = true) Long id,Long proId)
			throws Exception {
		ModelAndView view = new ModelAndView("/account/evaluate/add_page");
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
			return view;
		}
		if (id != null) {
			OrderDetailVo orderDetailVo = orderService.getOrderDetail(user.getId(), id);
			view.addObject("orderDetailVo", orderDetailVo);
		}else{
			ProductInfo pi = productService.findProduct(proId);
			String images = pi.getImages();

			OrderDetailVo orderDetailVo = new OrderDetailVo();
			orderDetailVo.setProdId(pi.getId());
			orderDetailVo.setProdImage(images.substring(0, images.indexOf("|")));
			orderDetailVo.setProdName(pi.getName());
			view.addObject("orderDetailVo", orderDetailVo);	
		}
		view.addObject("token", "设置token");
		return view;
	}

	// 添加评价
	@RequestMapping("/addEc")
	public void addEc(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "prodId", required = true) Long prodId,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "detailId", required = true) Long detailId,
			@RequestParam(value = "ym", required = false) String anonymous,
			@RequestParam(value = "imgs", required = false) String[] imgs,
			@RequestParam(value = "pf", required = true) String pf,
			@RequestParam(value = "content", required = true) String content) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		ReviewsLog reviews = new ReviewsLog();
		reviews.setReplyId(user.getId());
		reviews.setCreateTime(new Date());
		reviews.setDetailId(detailId);
		reviews.setProdId(prodId);
		reviews.setAnonymous(BaseUtil.judgeNull(anonymous) ? 0 : 1);
		reviews.setReviews(content);
		if (StringUtils.isBlank(pf))
			pf = "0";
		reviews.setScore(Integer.valueOf(pf));
		String imag = "";
		for (int i = 0; i < imgs.length; i++)
			if (null != imgs[i] && !StringUtils.isEmpty(imgs[i]) && imgs[i].length() > 1)
				imag += imgs[i] + "|";
		reviews.setImages(imag);
		reviews.setIp(ServletUtils.getIpAddr(request));
		reviews.setEnabled(1);
		boolean result = false;
		try {
			result = reviewService.addProductEvaluate(reviews);
		} catch (Exception e) {
			result = false;
			this.logger.error("添加商品评论异常", e);
		}
		if (result)
			HtmlUtil.writerJson(response, "success");// 成功
		else
			HtmlUtil.writerJson(response, "error");// 失败
	}
}