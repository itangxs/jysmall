package cn.qhjys.mall.mapper.custom;

import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.ProdSchedule;
import com.github.pagehelper.Page;

public interface ProductScheduleMapper {

	/**
	 * 查询商品预定列表
	 * 
	 * @param userId
	 *            用户编号
	 * @param status
	 *            预定状态
	 * @return
	 */
	Page<ProdSchedule> queryProductSchedule(@Param("userId") Long userId, @Param("status") Integer status);
}