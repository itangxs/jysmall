package cn.qhjys.mall.mapper;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.vo.system.FqCommissionRoleVo;

public interface FqCommissionRoleVoMapper {
	
    List<FqCommissionRoleVo> queryFqCommissionRole(Map<String,Object> map);
}