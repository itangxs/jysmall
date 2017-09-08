package cn.qhjys.mall.controller.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.DistrictInfo;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.WebsiteImg;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.SystemSetService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ServletUtils;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/set")
public class SystemSetController extends Base {
	@Autowired
	private SystemSetService systemSetService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/addImg")
	public ModelAndView addImg(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/system/set/add_img");
		WebsiteImg admin = systemSetService.getWebsiteImg();
		view.addObject("img", admin);
		return view;
	}

	@RequestMapping("/saveImg")
	public Object saveImg(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "pcBanner", required = true) String pcBanner,
			@RequestParam(value = "pcMenuMeis", required = true) String pcMenuMeis,
			@RequestParam(value = "pcMenuDiany", required = true) String pcMenuDiany,
			@RequestParam(value = "pcMenuGouw", required = true) String pcMenuGouw,
			@RequestParam(value = "pcMenuLvyou", required = true) String pcMenuLvyou,
			@RequestParam(value = "pcMenuXiux", required = true) String pcMenuXiux,
			@RequestParam(value = "pcMenuLiren", required = true) String pcMenuLiren,
			@RequestParam(value = "pcMenuShengh", required = true) String pcMenuShengh,
			@RequestParam(value = "pcMenuLicai", required = true) String pcMenuLicai,
			@RequestParam(value = "appBanner1", required = true) String appBanner1,
			@RequestParam(value = "appBanner2", required = true) String appBanner2,
			@RequestParam(value = "appBanner3", required = true) String appBanner3,
			@RequestParam(value = "appBanner4", required = true) String appBanner4,
			@RequestParam(value = "appBanner5", required = true) String appBanner5) throws Exception {
		boolean result = systemSetService.updateWebsiteImg(pcBanner, pcMenuMeis, pcMenuDiany, pcMenuGouw, pcMenuLvyou,
				pcMenuXiux, pcMenuLiren, pcMenuShengh, pcMenuLicai, null, null, appBanner1, appBanner2, appBanner3,
				appBanner4, appBanner5, null, null, getAdminUserSession(session).getId());
		if (result) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("修改类别广告图片", "WebsiteImg", 1l);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/set/addImg.do", "操作成功");
		}
		return super.goUrl("/managermall/systemmall/set/addImg.do", "操作失败");

	}

	@RequestMapping("/addAppImg")
	public ModelAndView addAppImg(HttpSession session,Long cityId) throws Exception {
		ModelAndView view = new ModelAndView("/system/set/add_app_img");
		if (cityId == null) {
			cityId = 200L;
		}
		List<SysImg> appImg = systemSetService.getSysImgByCity((byte)0, cityId);
		view.addObject("img", appImg);
		view.addObject("cityId", cityId);
		return view;
	}

	@RequestMapping("/saveAppImg")
	public Object saveAppImg(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "appImg1", required = true) String appImg1,
			@RequestParam(value = "storeId1", required = true) String storeId1,
			@RequestParam(value = "appImg2", required = true) String appImg2,
			@RequestParam(value = "storeId2", required = true) String storeId2,
			@RequestParam(value = "appImg3", required = true) String appImg3,
			@RequestParam(value = "storeId3", required = true) String storeId3,
			@RequestParam(value = "appImg4", required = true) String appImg4,
			@RequestParam(value = "storeId4", required = true) String storeId4,
			@RequestParam(value = "appImg5", required = false) String appImg5,
			@RequestParam(value = "storeId5", required = false) String storeId5,
			@RequestParam(value = "appImg6", required = false) String appImg6,
			@RequestParam(value = "storeId6", required = false) String storeId6,Long cityId) throws Exception {
		List<SysImg> newList = new ArrayList<SysImg>();
		SysImg img = new SysImg();
		img.setImg(appImg1);
		img.setField(storeId1);
		newList.add(img);
		img = new SysImg();
		img.setImg(appImg2);
		img.setField(storeId2);
		newList.add(img);
		img = new SysImg();
		img.setImg(appImg3);
		img.setField(storeId3);
		newList.add(img);
		img = new SysImg();
		img.setImg(appImg4);
		img.setField(storeId4);
		newList.add(img);
		img = new SysImg();
		img.setImg(appImg5);
		img.setField(storeId5);
		newList.add(img);
		img = new SysImg();
		img.setImg(appImg6);
		img.setField(storeId6);
		newList.add(img);
		List<SysImg> oldList = systemSetService.getSysImgByCity((byte)0, cityId);
		if (systemSetService.updateSysImgg(oldList, newList)) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("APP端九宫格管理", "SysImg", 1l);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/set/addAppImg.do?cityId="+cityId, "操作成功");
		}
		return super.goUrl("/managermall/systemmall/set/addAppImg.do?cityId="+cityId, "操作失败");

	}

	@RequestMapping("/addDistrict")
	public ModelAndView addDistrict(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/set/add_district");
		if (null != id) {
			DistrictInfo admin = systemSetService.getDistrictById(id);
			view.addObject("district", admin);
		}
		return view;
	}

	@RequestMapping("/saveDistrict")
	public Object saveDistrict(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "province", required = true) Long province,
			@RequestParam(value = "enabled", required = true) Integer enabled,
			@RequestParam(value = "city", required = true) Long city,
			@RequestParam(value = "area", required = true) Long area,
			@RequestParam(value = "name", required = true) String name) throws Exception {
		if (systemSetService.updateDistrict(id, province, city, area, name, enabled, getAdminUserSession(session)
				.getId())) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("修改商圈信息", "DistrictInfo", 1l);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/set/districtList.do", "操作成功");
		}
		return super.goUrl("/managermall/systemmall/set/districtList.do", "操作失败");

	}

	@RequestMapping("/districtList")
	public ModelAndView districtList(HttpSession session,
			@RequestParam(value = "province", required = false) Long province,
			@RequestParam(value = "enabled", required = false) Integer enabled,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "city", required = false) Long city) throws Exception {
		ModelAndView view = new ModelAndView("/system/set/list_shangquan");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (null != province)
			view.addObject("province", province);
		if (null != city)
			view.addObject("city", city);
		Page<DistrictInfo> list2 = systemSetService.getDistrictByPage(pageNum, pageSize, enabled, province, city);
		view.addObject("shangquanInfoList", list2);
		return view;
	}

	@RequestMapping("/categoryImgList")
	public ModelAndView categoryImgList() throws Exception {
		ModelAndView view = new ModelAndView("/system/set/categoryImgList");
		List<SysImg> list = systemSetService.getSysImg((byte) 1, (byte) 7);
		view.addObject("list", list);
		return view;
	}

	@RequestMapping("/updateCategoryImg")
	public Object updateCategoryImg(HttpSession session, HttpServletRequest request) throws Exception {
		String start = "img";
		Map<String, String[]> map = request.getParameterMap();
		List<Integer> sort = new ArrayList<Integer>();
		for (String key : map.keySet())
			if (key.startsWith(start))
				sort.add(Integer.valueOf(key.substring(key.indexOf(start) + start.length(), key.length())));
		Collections.sort(sort);
		List<SysImg> oldList = systemSetService.getSysImg((byte) 1, (byte) 7);
		List<SysImg> newList = new ArrayList<SysImg>();
		if (oldList.size() != sort.size())
			return super.goUrl("/managermall/systemmall/set/categoryImgList.do", "参数错误");
		for (int i = 0; i < oldList.size(); i++) {
			SysImg img = new SysImg();
			img.setImg(map.get(start + sort.get(i))[0]);
			img.setField(map.get("field" + sort.get(i))[0]);
			newList.add(img);
		}
		if (systemSetService.updateSysImgg(oldList, newList)) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("修改主菜单类别广告图片", "SysImg", 1l);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/set/categoryImgList.do", "更新成功");
		} else
			return super.goUrl("/managermall/systemmall/set/categoryImgList.do", "更新失败");
	}
}