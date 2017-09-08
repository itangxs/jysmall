package cn.qhjys.mall.service.system;

/*import cn.qhjys.mall.entity.AppImg;*/
import java.util.List;

import cn.qhjys.mall.entity.DistrictInfo;
import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.WebsiteImg;

import com.github.pagehelper.Page;

public interface SystemSetService {

	/**
	 * 根据ID的到商圈信息
	 * 
	 * @param id
	 * @return
	 */
	public DistrictInfo getDistrictById(Long id) throws Exception;

	/**
	 * 更新某一商圈信息
	 * 
	 * @param id
	 * @param province
	 * @param city
	 * @param area
	 * @param name
	 * @param adminId
	 * @return
	 */
	public boolean updateDistrict(Long id, Long province, Long city, Long area, String name, Integer enabled,
			Long adminId) throws Exception;

	/**
	 * 查询符合要求的商圈
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param enabled
	 * @param province
	 * @param city
	 * @return
	 */
	public Page<DistrictInfo> getDistrictByPage(Integer pageNum, Integer pageSize, Integer enabled, Long province,
			Long city) throws Exception;

	public WebsiteImg getWebsiteImg() throws Exception;

	public boolean updateWebsiteImg(String pcBanner, String pcMenuMeis, String pcMenuDiany, String pcMenuGouw,
			String pcMenuLvyou, String pcMenuXiux, String pcMenuLiren, String pcMenuShengh, String pcMenuLicai,
			String pcMenuOther1, String pcMenuOther2, String appBanner1, String appBanner2, String appBanner3,
			String appBanner4, String appBanner5, String appBanner6, String appBanner7, Long adminId) throws Exception;

	public List<SysImg> getSysImg(byte type1, Byte type2) throws Exception;

	public boolean updateSysImgg(List<SysImg> oldList, List<SysImg> newList) throws Exception;
	
	public List<SysImg> getSysImgByCity(byte type1,Long cityId) throws Exception;
}
