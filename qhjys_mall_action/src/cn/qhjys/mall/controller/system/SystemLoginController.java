package cn.qhjys.mall.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.RoleMenu;
import cn.qhjys.mall.service.AddressService;
import cn.qhjys.mall.service.system.AdminUserRoleService;
import cn.qhjys.mall.service.system.AdminUserService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.system.CityVo;
import cn.qhjys.mall.vo.system.DistrictVo;
import cn.qhjys.mall.vo.system.ProvinceVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class SystemLoginController extends Base {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminUserRoleService adminUserRoleService;
	@Autowired
	private AddressService addressService;

	@RequestMapping(value = "/queryProvinceInfoSe")
	public void queryProvinceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProvinceInfo> provinceInfoList = addressService.queryProvinceInfo();
		List<ProvinceVo> provinces = new ArrayList<ProvinceVo>();
		for (ProvinceInfo pi : provinceInfoList) {
			ProvinceVo pv = new ProvinceVo();
			pv.setId(pi.getId());
			pv.setName(pi.getName());
			List<CityVo> listcity = new ArrayList<CityVo>();
			List<CityInfo> cityInfoList = addressService.queryCityInfo(pi.getId());
			for (CityInfo ci : cityInfoList) {
				CityVo cv = new CityVo();
				cv.setId(ci.getId());
				cv.setName(ci.getName());
				List<DistrictVo> listDistrictVo = new ArrayList<DistrictVo>();
				List<AreaInfo> districtInfoList = addressService.queryAreaInfo(ci.getId());
				for (AreaInfo ai : districtInfoList) {
					DistrictVo dv = new DistrictVo();
					dv.setId(ai.getId());
					dv.setName(ai.getName());
					listDistrictVo.add(dv);
				}
				cv.setDistricts(listDistrictVo);
				listcity.add(cv);
			}
			pv.setCitys(listcity);
			provinces.add(pv);
		}
		JSONObject obj = new JSONObject();
		obj.put("provinceList", JSON.toJSON(provinces));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/queryCityInfoSe")
	public void queryCityInfo(Long provinceId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<CityInfo> cityInfoList = addressService.queryCityInfo(provinceId);
		obj.put("cityInfoList", JSON.toJSON(cityInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/queryDistrictInfoSe")
	public void queryDistrictInfo(Long cityId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<AreaInfo> districtInfoList = addressService.queryAreaInfo(cityId);
		obj.put("districtInfoList", JSON.toJSON(districtInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/queryCityInfoSe1")
	public void queryCityInf1(Long provinceId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<CityInfo> cityInfoList = addressService.queryCityInfo();
		obj.put("cityInfoList", JSON.toJSON(cityInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/queryDistrictInfoSe1")
	public void queryDistrictInfo1(Long cityId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<AreaInfo> districtInfoList = addressService.queryAreaInfo();
		obj.put("districtInfoList", JSON.toJSON(districtInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping("/systemlogin/login")
	public ModelAndView login(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/login");
		SessionUtil.setSession(session, "systemlogin", UUID.randomUUID().toString());
		view.addObject("token", SessionUtil.getSession(session, "systemlogin"));
		return view;
	}

	@RequestMapping("/managermall/systemmall/norole")
	public ModelAndView norole(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/norole");
		return view;
	}

	@RequestMapping("/managermall/systemmall/loginout")
	public ModelAndView loginout(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("redirect:/systemlogin/login.do");
		SessionUtil.removeSession(session, ConstantsConfigurer.getProperty("system_key"));
		SessionUtil.removeSession(session, "systemmallurl");
		return view;
	}

	@RequestMapping("/systemlogin/loginCommit")
	public Object loginCommit(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "token", required = true) String token) throws Exception {
		Object token1 = SessionUtil.getSession(session, "systemlogin");
		SessionUtil.removeSession(session, "systemlogin");
		if (null != token1 && token1.equals(token)) {
			AdminUser ad = adminUserService.updateAdminUserByLogin(username, password, ServletUtils.getIpAddr(request));
			if (null != ad) {
				SessionUtil.setSession(session, ConstantsConfigurer.getProperty("system_key"), ad);
				List<RoleMenu> list = adminUserRoleService.getRoleMenuByGroup(ad.getGroupId());
				SessionUtil.setSession(session, "systemmallurl", list);
				return super.goUrl("/managermall/systemmall/cms/cmsList.do", "登录成功");
			}
		} else
			return super.goUrl("/systemlogin/login.do", "重复提交");
		return super.goUrl("/systemlogin/login.do", "没有权限");
	}
}
