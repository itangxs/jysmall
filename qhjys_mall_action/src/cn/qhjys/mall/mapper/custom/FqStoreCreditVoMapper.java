package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.vo.system.FqStoreCreditVo;

public interface FqStoreCreditVoMapper {
	
	List<FqStoreCreditVo> queryFqStoreFromCredit(Map<String, Object> map) throws Exception;
	
}
