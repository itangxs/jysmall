package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AppVersion;
import cn.qhjys.mall.entity.AppVersionExample;
import cn.qhjys.mall.mapper.AppVersionMapper;
import cn.qhjys.mall.service.system.AppVersionService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AppVersionServiceImpl extends Base implements AppVersionService {

	@Autowired
	private AppVersionMapper appVersionMapper;

	@Override
	public int saveAppVersion(Long id, Long versionCode, String versionName, String downloadUrl, String content)
			throws Exception {
		AppVersion av = new AppVersion();
		av.setVersionCode(versionCode);
		av.setVersionName(versionName);
		av.setDownloadUrl(downloadUrl);
		av.setContent(content);
		// 验证版本号是否高于最新版本版本号
//		AppVersion lav = getLastAppVersion();
//		if(lav != null){
//			if (versionCode <= getLastAppVersion().getVersionCode())
//				return -1;
//		}
		int num = 0;
		if (id == null) {
			av.setVersionStatus(0);
			av.setCreateTime(new Date());
			num = appVersionMapper.insertSelective(av);
		}
		id = av.getId();
		if (num > 0)
			return 1;
		return 0;
	}

	@Override
	public Page<AppVersion> queryAppVersionPage(Integer pageNum, Integer pageSize) throws Exception {
		AppVersionExample example = new AppVersionExample();
		example.setOrderByClause(" version_code DESC,create_time DESC");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<AppVersion>) appVersionMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public AppVersion getLastAppVersion(Integer osType) throws Exception {
		AppVersionExample example = new AppVersionExample();
		example.createCriteria().andVersionStatusEqualTo(1).andOsTypeEqualTo(osType);
		example.setOrderByClause(" version_code DESC,create_time DESC");
		PageHelper.startPage(1, 1);
		List<AppVersion> avs = appVersionMapper.selectByExampleWithBLOBs(example);
		if (avs.size() == 1)
			return avs.get(0);
		return null;
	}


}