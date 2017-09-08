package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.AdminLog;
import cn.qhjys.mall.mapper.AdminLogMapper;
import cn.qhjys.mall.mapper.custom.AdminLogsMapper;
import cn.qhjys.mall.service.AdminLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AdminLogServiceImpl implements AdminLogService {
	@Autowired
	private AdminLogMapper adminLogMapper;
	@Autowired
	private AdminLogsMapper adminLogsMapper;

	@Override
	public boolean insertAdminLog(Long adminId, String info, String operType, String ipAddr) throws Exception {
		AdminLog log = new AdminLog();
		log.setAdminId(adminId);
		log.setInfo(info);
		log.setOperType(operType);
		log.setIpAddr(ipAddr);
		log.setCreateTime(new Date());
		int result = adminLogMapper.insertSelective(log);
		return result > 0;
	}

	@Override
	public Page<Map<String, Object>> queryAdminLog(Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 1)
			pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		return adminLogsMapper.queryAdminLogs();
	}
}