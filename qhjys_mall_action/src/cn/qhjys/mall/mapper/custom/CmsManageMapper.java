package cn.qhjys.mall.mapper.custom;

import java.util.Map;
import cn.qhjys.mall.vo.system.CmsInfoVo;
import com.github.pagehelper.Page;

public interface CmsManageMapper {

	Page<CmsInfoVo> queryCmsList(Map<String, Object> map);
}