package cn.qhjys.mall.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.FqStoreVo;

public interface FqStoreVoMapper {
	List<FqStoreVo> selectByExample(@Param(value="locationId")Long locationId);
}
