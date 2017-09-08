package cn.qhjys.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.service.AddressService;
import cn.qhjys.mall.service.CategoryService;
import cn.qhjys.mall.util.HtmlUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/***
 * 省市区三级联动
 * 
 * @author zengrong
 *
 */
@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/queryProvinceInfo", method = RequestMethod.POST)
	public void queryProvinceInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProvinceInfo> provinceInfoList = addressService.queryProvinceInfo();
		JSONObject obj = new JSONObject();
		obj.put("provinceList", JSON.toJSON(provinceInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/queryCityInfo", method = RequestMethod.POST)
	public void queryCityInfo(Long provinceId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<CityInfo> cityInfoList = addressService.queryCityInfo(provinceId);
		obj.put("cityInfoList", JSON.toJSON(cityInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/queryDistrictInfo", method = RequestMethod.POST)
	public void queryDistrictInfo(Long cityId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<AreaInfo> districtInfoList = addressService.queryAreaInfo(cityId);
		obj.put("districtInfoList", JSON.toJSON(districtInfoList));
		HtmlUtil.writerJson(response, obj);
	}

	@RequestMapping(value = "/getCityInfo", method = RequestMethod.POST)
	public void getCityInfo(Long cityId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CityInfo cityInfo = addressService.getCityInfoById(cityId);
		HtmlUtil.writerJson(response, cityInfo);
	}
	
	@RequestMapping(value = "/queryCategoryInfo", method = RequestMethod.POST)
	public void queryCategoryInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CategoryInfo> categoryInfoList = categoryService.queryCategoryByParent(0L);
		JSONObject obj = new JSONObject();
		obj.put("categoryInfoList", JSON.toJSON(categoryInfoList));
		HtmlUtil.writerJson(response, obj);
	}
	@RequestMapping(value = "/queryCategoryInfo1", method = RequestMethod.POST)
	public void queryCategoryInfo1(HttpServletRequest request, HttpServletResponse response,Long parentid) throws Exception {
		if (null != parentid && !"".equals(parentid)) {
			List<CategoryInfo> categoryInfoList = categoryService.queryCategoryByParent(parentid);
			JSONObject obj = new JSONObject();
			obj.put("categoryInfoList", JSON.toJSON(categoryInfoList));
			HtmlUtil.writerJson(response, obj);
		}
	}

}
