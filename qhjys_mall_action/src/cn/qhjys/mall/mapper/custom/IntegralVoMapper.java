package cn.qhjys.mall.mapper.custom;

import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.IntegralLog;
import com.github.pagehelper.Page;

public interface IntegralVoMapper {

	Page<IntegralLog> queryIntegralLog(@Param("userId") Long userId, @Param("code") String[] code,
			@Param("start") String start, @Param("end") String end);

	Page<CashLog> queryCahsLog(@Param("userId") Long userId, @Param("code") String[] code,
			@Param("start") String start, @Param("end") String end);
}