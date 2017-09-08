package cn.qhjys.mall.mapper.custom;

import java.util.Map;

import cn.qhjys.mall.entity.FqClerkCount;
import cn.qhjys.mall.entity.FqClerkMonth;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.StoreCountVo;

import com.github.pagehelper.Page;

public interface RebateOrderVoMapper {
	public Page<RebateOrderVo> queryRebateOrderVo(Map<String, Object> map);
	public Page<RebateOrderVo> queryRebateOrderVoByThird(Map<String, Object> map);
	public Page<StoreCountVo> queryStoreCountVo(Map<String, Object> map);
	public Integer insertFqClerkCount(Map<String, Object> map);
	public Page<FqClerkCount> queryFqClerkCountBySeller(Map<String, Object> map);
	public FqClerkMonth countFqClerkMonth(Map<String, Object> map);
}
