package cn.qhjys.mall.mapper.custom;

import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.system.RedpackRecordVo;

public interface RedpackRecordVoMapper {
	RedpackRecordVo queryRedpackRecordVoByRedpackId(@Param("redpackId") Long redpackId,
			@Param("status") Integer status) throws Exception;
}
