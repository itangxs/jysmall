package cn.qhjys.mall.controller.seller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.VolumeService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.VolumeVo;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/seller/volume")
public class VerifyVolumeController extends Base {
	@Autowired
	private VolumeService volumeService;

	/**
	 * 跳转验证消费卷页
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/verifyVolume", method = RequestMethod.GET)
	public ModelAndView toVerifyVolume(HttpSession session, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/seller/volume/verifyVolume");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (null == seller) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		return view;

	}

	/**
	 * 验证消费卷
	 * 
	 * @param session
	 * @param request
	 * @param volumeId
	 * @throws Exception
	 */
	@RequestMapping(value = "/verifyVolume", method = RequestMethod.POST)
	public void doVerifyVolume(HttpSession session, HttpServletResponse response, String vCode) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		JSONObject json = new JSONObject();
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			VolumeVo vo = volumeService.queryVolumeVo(seller.getId(), vCode);
			
			if(vo == null){
				json.put("message", "飞券串码验证码不存在");
				
			}else if (vo.getStatus() == 1) {
				OrderDetail od = volumeService.queryOrderDetail(seller.getId(), vCode);
				if (od == null) {
					json.put("message", "飞券串码验证码不存在");
				} else if (od.getStatus() == 3) {
					json.put("message", "飞券串码验证码已消费");
				} else if (od.getStatus() == 9) {
					json.put("message", "飞券串码验证码已过期");
				} else if (od.getStatus() == 2) {
					json.put("message", "success");
					json.put("volume", vo);
					json.put("order", od);
				}
			}else if(vo.getStatus() == 2){
				json.put("message", "飞券串码验证码已消费");
			}else{
				json.put("message", "飞券串码验证码已退款");
			}
			HtmlUtil.writerJson(response, json);

		}
		
	}
	
	@RequestMapping(value = "/verifyVolume1", method = RequestMethod.POST)
	public void doVerifyVolume1(HttpSession session, HttpServletResponse response, String vCode) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		boolean result = false;
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			OrderDetail od = volumeService.queryOrderDetail(seller.getId(), vCode);
			if (od == null) {
				HtmlUtil.writerJson(response, "飞券串码验证码不存在");
				return;
			} else if (od.getStatus() == 3) {
				HtmlUtil.writerJson(response, "飞券串码验证码已消费");
				return;
			} else if (od.getStatus() == 9) {
				HtmlUtil.writerJson(response, "飞券串码验证码已过期");
				return;
			} else if (od.getStatus() == 2) {
				try {
					result = volumeService.updateVerifyVolume(seller.getId(), vCode);
				} catch (Exception e) {
					this.logger.error("验证消费卷异常：", e);
					HtmlUtil.writerJson(response, "验证飞券串码异常");
				}
			} else {
				HtmlUtil.writerJson(response, "飞券串码验证码无效");
				return;
			}
			
		}
		if (result) {
			HtmlUtil.writerJson(response, "success");
			return;
		} else {
			HtmlUtil.writerJson(response, "飞券串码验证失败");
			return;
		}
	}

	/**
	 * 获取已使用消费卷
	 * 
	 * @param session
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUsedVolume")
	public ModelAndView getUsedVolume(HttpSession session, Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/seller/volume/usedVolume");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (null == seller) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		Page<VolumeVo> page = volumeService.queryUsedVolume(seller.getId(), pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}
}