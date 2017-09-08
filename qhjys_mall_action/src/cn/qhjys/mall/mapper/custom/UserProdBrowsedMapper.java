package cn.qhjys.mall.mapper.custom;

import org.apache.ibatis.annotations.Param;

public interface UserProdBrowsedMapper {
	/**
	 * 删除超出范围的浏览记录
	 * 
	 * @param userId
	 *            用户变厚啊
	 * @param size
	 *            记录范围
	 * @return
	 */
	int deleteExceedBrowsed(@Param("userId") Long userId, @Param("size") Integer size);
}