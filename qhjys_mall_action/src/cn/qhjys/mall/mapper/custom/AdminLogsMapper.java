package cn.qhjys.mall.mapper.custom;

import java.util.Map;
import com.github.pagehelper.Page;

public interface AdminLogsMapper {

	Page<Map<String, Object>> queryAdminLogs();
}