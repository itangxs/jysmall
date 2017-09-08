package cn.qhjys.mall.mapper.custom;

import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;

public interface ReceivAddressMapper {
	             
	/**
	 * 获取收货地址
	 * 
	 * @param userId
	 * @return
	 */
	Page<Map<String, Object>> queryDeliveryAddr(@Param("userId") Long userId);

}