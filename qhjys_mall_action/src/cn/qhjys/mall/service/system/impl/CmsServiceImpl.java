package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CmsCategory;
import cn.qhjys.mall.entity.CmsCategoryExample;
import cn.qhjys.mall.entity.CmsInfo;
import cn.qhjys.mall.mapper.CmsCategoryMapper;
import cn.qhjys.mall.mapper.CmsInfoMapper;
import cn.qhjys.mall.mapper.custom.CmsManageMapper;
import cn.qhjys.mall.service.system.CmsService;
import cn.qhjys.mall.vo.system.CmsInfoVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class CmsServiceImpl extends Base implements CmsService {
	@Autowired
	private CmsInfoMapper cmsInfoMapper;
	@Autowired
	private CmsCategoryMapper cmsCategoryMapper;
	@Autowired
	private CmsManageMapper cmsManageMapper;

	public CmsManageMapper getCmsManageMapper() throws Exception {
		return cmsManageMapper;
	}

	public void setCmsManageMapper(CmsManageMapper cmsManageMapper) throws Exception {
		this.cmsManageMapper = cmsManageMapper;
	}

	public CmsInfoMapper getCmsInfoMapper() throws Exception {
		return cmsInfoMapper;
	}

	public void setCmsInfoMapper(CmsInfoMapper cmsInfoMapper) throws Exception {
		this.cmsInfoMapper = cmsInfoMapper;
	}

	public CmsCategoryMapper getCmsCategoryMapper() throws Exception {
		return cmsCategoryMapper;
	}

	public void setCmsCategoryMapper(CmsCategoryMapper cmsCategoryMapper) throws Exception {
		this.cmsCategoryMapper = cmsCategoryMapper;
	}

	@Override
	public List<CmsCategory> getCmsCategoryEnandle1() throws Exception {
		CmsCategoryExample example = new CmsCategoryExample();
		example.createCriteria().andYeziEqualTo(0).andEnabledEqualTo(1);
		example.setOrderByClause("paixuhao asc");
		return cmsCategoryMapper.selectByExample(example);
	}

	@Override
	public CmsCategory getCmsCategoryById(Long id) throws Exception {
		return cmsCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveCmsCategory(Long id, Long fatherId, String name, Integer paixuhao, Integer yezi,
			Integer enabled, Long adminUserId) throws Exception {
		if (null != getCmsCategoryById(fatherId)) {
			CmsCategory cc = new CmsCategory();
			cc.setAdminId(adminUserId);
			cc.setCreateDate(new Date());
			cc.setEnabled(enabled);
			cc.setFatherId(fatherId);
			cc.setName(name);
			cc.setPaixuhao(paixuhao);
			cc.setYezi(yezi);
			if (null != id) {
				cc.setId(id);
				if (cmsCategoryMapper.updateByPrimaryKeySelective(cc) > 0) {
					return true;
				}
			} else {
				cmsCategoryMapper.insertSelective(cc);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<CmsCategory> getCmsCategoryYezi() throws Exception {
		CmsCategoryExample example = new CmsCategoryExample();
		example.createCriteria().andYeziEqualTo(0);
		example.setOrderByClause("id asc");
		return cmsCategoryMapper.selectByExample(example);
	}

	@Override
	public List<CmsCategory> getCmsCategoryEnandle1Yezi1() throws Exception {
		CmsCategoryExample example = new CmsCategoryExample();
		example.createCriteria().andYeziEqualTo(1).andEnabledEqualTo(1);
		example.setOrderByClause("paixuhao asc");
		return cmsCategoryMapper.selectByExample(example);
	}

	@Override
	public CmsInfo getCmsInfoById(Long id) throws Exception {
		return cmsInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveCms(Long id, Long parentId, String name, String title, String keywords, String description,
			Integer enabled, String content, Long adminUserId) throws Exception {
		CmsCategory cc = getCmsCategoryById(parentId);
		if (null != cc && 1 == cc.getEnabled() && 1 == cc.getYezi()) {
			CmsInfo ci = new CmsInfo();
			ci.setAdminId(adminUserId);
			ci.setContent(content);
			ci.setCreateTime(new Date());
			ci.setEnabled(enabled);
			ci.setName(name.trim());
			ci.setTitle(title);
			ci.setKeywords(keywords);
			ci.setDescription(description);
			ci.setParentId(parentId);
			if (null != id) {
				ci.setId(id);
				if (cmsInfoMapper.updateByPrimaryKeySelective(ci) > 0)
					return true;
			} else if (cmsInfoMapper.insertSelective(ci) > 0)
				return true;
			id = ci.getId();
		}
		return false;
	}

	@Override
	public Page<CmsInfoVo> getCmsInfoVoByPage(Integer pageNum, Integer pageSize, Long parentId, String name,
			Integer enabled) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("enabled", enabled);
		map.put("parentId", parentId);
		PageHelper.startPage(pageNum, pageSize);
		return cmsManageMapper.queryCmsList(map);
	}

	@Override
	public List<CmsCategory> getCmsCategory(Long fatherId, Integer enabled) throws Exception {
		CmsCategoryExample example = new CmsCategoryExample();
		if (null != fatherId)
			example.createCriteria().andFatherIdEqualTo(fatherId);
		if (null != enabled)
			example.createCriteria().andEnabledEqualTo(enabled);
		return cmsCategoryMapper.selectByExample(example);
	}

}
