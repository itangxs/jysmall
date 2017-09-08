package cn.qhjys.mall.service.system;

import cn.qhjys.mall.entity.AppVersion;
import com.github.pagehelper.Page;

public interface AppVersionService {

	public int saveAppVersion(Long id, Long versionCode, String versionName, String downloadUrl, String content)
			throws Exception;

	public Page<AppVersion> queryAppVersionPage(Integer pageNum, Integer pageSize) throws Exception;

	public AppVersion getLastAppVersion(Integer osType) throws Exception;
}
