package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;
import cn.qhjys.mall.vo.SellerScheduleVo;

public interface SellerScheduleVoMapper {

	List<SellerScheduleVo> querySellerScheduleVoBySellerId(Map<String, Object> map);

	List<SellerScheduleVo> querySellerScheduleVoBySellerIdThan(Map<String, Object> map);
}