package cn.qhjys.mall.mapper;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.vo.system.FqClerkVo;

public interface FqClerkVoMapper {
	
    List<FqClerkVo> queryFqClerkVo(Map<String,Object> map);
    
    FqClerkVo getFqClerkVo(Map<String,Object> map);
    
    FqClerkVo getClerkAndStore(Map<String,Object> map);
}