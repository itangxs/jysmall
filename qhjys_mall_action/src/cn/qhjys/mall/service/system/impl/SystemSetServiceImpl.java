package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.DistrictInfo;
import cn.qhjys.mall.entity.DistrictInfoExample;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.SysImgExample;
import cn.qhjys.mall.entity.WebsiteImg;
import cn.qhjys.mall.mapper.DistrictInfoMapper;
import cn.qhjys.mall.mapper.SysImgMapper;
import cn.qhjys.mall.mapper.WebsiteImgMapper;
import cn.qhjys.mall.service.ProvinceCityAreaService;
import cn.qhjys.mall.service.system.SystemSetService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SystemSetServiceImpl extends Base implements SystemSetService {
	@Autowired
	private DistrictInfoMapper districtInfoMapper;
	@Autowired
	private ProvinceCityAreaService provinceCityAreaService;
	@Autowired
	private WebsiteImgMapper websiteImgMapper;
	@Autowired
	private SysImgMapper sysImgMapper;

	@Override
	public DistrictInfo getDistrictById(Long id) throws Exception {
		return districtInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateDistrict(Long id, Long province, Long city, Long area, String name, Integer enabled,
			Long adminId) throws Exception {
		ProvinceInfo pi = provinceCityAreaService.getProvinceInfoById(province);
		CityInfo ci = provinceCityAreaService.getCityInfoById(city);
		AreaInfo ai = provinceCityAreaService.getAreaInfoById(area);
		if (null != pi && null != ci) {
			DistrictInfo si = new DistrictInfo();
			si.setAdminId(adminId);
			si.setCityId(city);
			si.setCityName(ci.getName());
			si.setCreateDate(new Date());
			si.setEnabled(enabled);
			si.setName(name);
			si.setProvinceId(province);
			si.setProvinceName(pi.getName());
			si.setAreaId(area);
			si.setAreaName(ai.getName());
			if (null != id) {
				si.setId(id);
				if (districtInfoMapper.updateByPrimaryKeySelective(si) > 0)
					return true;
			} else {
				districtInfoMapper.insertSelective(si);
				return true;
			}
		}
		return false;
	}

	@Override
	public Page<DistrictInfo> getDistrictByPage(Integer pageNum, Integer pageSize, Integer enabled, Long province,
			Long city) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		DistrictInfoExample example = new DistrictInfoExample();
		if (null != province) {
			example.createCriteria().andProvinceIdEqualTo(province);
		}
		if (null != city) {
			example.createCriteria().andCityIdEqualTo(city);
		}
		if (null != enabled) {
			example.createCriteria().andEnabledEqualTo(enabled);
		}
		return (Page<DistrictInfo>) districtInfoMapper.selectByExample(example);
	}

	@Override
	public WebsiteImg getWebsiteImg() throws Exception {
		return websiteImgMapper.selectByPrimaryKey(1l);
	}

	@Override
	public boolean updateWebsiteImg(String pcBanner, String pcMenuMeis, String pcMenuDiany, String pcMenuGouw,
			String pcMenuLvyou, String pcMenuXiux, String pcMenuLiren, String pcMenuShengh, String pcMenuLicai,
			String pcMenuOther1, String pcMenuOther2, String appBanner1, String appBanner2, String appBanner3,
			String appBanner4, String appBanner5, String appBanner6, String appBanner7, Long adminId) throws Exception {
		WebsiteImg record = new WebsiteImg();
		record.setPcBanner(pcBanner);
		record.setAdminId(adminId);
		record.setAppBanner1(appBanner1);
		record.setAppBanner2(appBanner2);
		record.setAppBanner3(appBanner3);
		record.setAppBanner4(appBanner4);
		record.setAppBanner5(appBanner5);
		if (StringUtils.isNotBlank(appBanner6))
			record.setAppBanner6(appBanner6);
		if (StringUtils.isNotBlank(appBanner7))
			record.setAppBanner7(appBanner7);
		record.setCreateDate(new Date());
		record.setId(1l);
		record.setPcMenuDiany(pcMenuDiany);
		record.setPcMenuGouw(pcMenuGouw);
		record.setPcMenuLicai(pcMenuLicai);
		record.setPcMenuLiren(pcMenuLiren);
		record.setPcMenuLvyou(pcMenuLvyou);
		record.setPcMenuMeis(pcMenuMeis);
		record.setPcMenuShengh(pcMenuShengh);
		record.setPcMenuXiux(pcMenuXiux);
		if (StringUtils.isNotBlank(pcMenuOther1))
			record.setPcMenuOther1(pcMenuOther1);
		if (StringUtils.isNotBlank(pcMenuOther2))
			record.setPcMenuOther2(pcMenuOther2);
		if (websiteImgMapper.updateByPrimaryKeySelective(record) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<SysImg> getSysImg(byte type1, Byte type2) throws Exception {
		SysImgExample example = new SysImgExample();
		if (type2 == null)
			example.createCriteria().andTypeEqualTo(type1);
		else
			example.createCriteria().andTypeBetween(type1, type2);
		example.setOrderByClause("type ASC");
		List<SysImg> list = sysImgMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean updateSysImgg(List<SysImg> oldList, List<SysImg> newList) throws Exception {
		if (oldList == null || newList == null || newList.size() != oldList.size())
			return false;
		SysImg oldImg, newImg;
		for (int i = 0; i < oldList.size(); i++) {
			oldImg = oldList.get(i);
			newImg = newList.get(i);
			oldImg.setImg(newImg.getImg());
			oldImg.setField(newImg.getField());
			sysImgMapper.updateByPrimaryKeySelective(oldImg);
		}
		for (int i = oldList.size(); i < newList.size(); i++) {
			newImg = newList.get(i);
			newImg.setType((byte) 0);
			newImg.setCreateTime(new Date());
			sysImgMapper.insertSelective(newImg);
		}
		return true;
	}

	@Override
	public List<SysImg> getSysImgByCity(byte type1,Long cityId)
			throws Exception {
		SysImgExample example = new SysImgExample();
		example.createCriteria().andTypeEqualTo(type1).andCityIdEqualTo(cityId);
		return  sysImgMapper.selectByExample(example);
	}
}