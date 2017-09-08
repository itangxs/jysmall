package cn.qhjys.mall.service.system;

import java.util.List;
import cn.qhjys.mall.entity.CmsCategory;
import cn.qhjys.mall.entity.CmsInfo;
import cn.qhjys.mall.vo.system.CmsInfoVo;
import com.github.pagehelper.Page;

public interface CmsService {

	/**
	 * 得到所有的CMS类别，可用的
	 * 
	 * @return
	 */
	public List<CmsCategory> getCmsCategoryEnandle1() throws Exception;

	public CmsCategory getCmsCategoryById(Long id) throws Exception;

	/**
	 * 增加或者修改某一个类别
	 * 
	 * @param id
	 * @param fatherId
	 *            该类别的上级
	 * @param name
	 *            类别名称
	 * @param paixuhao
	 *            排序号
	 * @param yezi
	 *            是否有下面的类别
	 * @param enabled
	 *            是否启用
	 * @param adminUserId
	 *            操作者ID
	 * @return
	 */
	public boolean saveCmsCategory(Long id, Long fatherId, String name, Integer paixuhao, Integer yezi,
			Integer enabled, Long adminUserId) throws Exception;

	/**
	 * 得到所有有下级的类别
	 * 
	 * @return
	 */
	public List<CmsCategory> getCmsCategoryYezi() throws Exception;

	/**
	 * 得到所有可供选择的CMS大类
	 * 
	 * @return
	 */
	public List<CmsCategory> getCmsCategoryEnandle1Yezi1() throws Exception;

	/**
	 * 得到某一条具体的CMS
	 * 
	 * @param id
	 * @return
	 */
	public CmsInfo getCmsInfoById(Long id) throws Exception;

	/**
	 * 增加或者修改某一个具体的CMS信息
	 * 
	 * @param id
	 * @param parentId
	 * @param name
	 * @param enabled
	 * @param content
	 * @param adminUserId
	 * @return
	 */
	public boolean saveCms(Long id, Long parentId, String name, String title, String keywords, String description,
			Integer enabled, String content, Long adminUserId) throws Exception;

	/**
	 * 查找所需要的CMS信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param parentId
	 *            父类
	 * @param name
	 *            标题
	 * @param enabled
	 *            是否启用
	 * @return
	 */
	public Page<CmsInfoVo> getCmsInfoVoByPage(Integer pageNum, Integer pageSize, Long parentId, String name,
			Integer enabled) throws Exception;

	/**
	 * 返回所有类别
	 * 
	 * @param enabled
	 * @param fatherId
	 * @return
	 */
	public List<CmsCategory> getCmsCategory(Long fatherId, Integer enabled) throws Exception;

}
