package cn.qhjys.mall.mapper.custom;

import java.util.Date;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.MonthWithdrawMoney;

public interface WithdrawCustomMapper {
	
	MonthWithdrawMoney queryMonthWithdrawMoney(@Param("sellerId") Long sellerId,
			@Param("startTime") Date startTime,
			@Param("endTime") Date endTime) throws Exception;

}
