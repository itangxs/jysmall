package cn.qhjys.mall.mapper.custom;

import java.util.Map;
import cn.qhjys.mall.vo.system.CashLogVo;
import cn.qhjys.mall.vo.system.ChongzhiVo;
import cn.qhjys.mall.vo.system.WithdrawsVo;
import com.github.pagehelper.Page;

public interface ChongzhiManageMapper {

	Page<ChongzhiVo> queryChongzhiList(Map<String, Object> map);
	
	Page<CashLogVo> queryUserAccountRecordPage(Map<String, Object> map);
	
	Page<CashLogVo> querySellerAccountRecordPage(Map<String, Object> map);
	
	Page<WithdrawsVo> userWithdrawsRecord(Map<String, Object> map);
	
	Page<WithdrawsVo> sellerWithdrawsRecord(Map<String, Object> map);
}