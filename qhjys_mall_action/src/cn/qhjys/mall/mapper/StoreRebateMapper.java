package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.entity.StoreRebateExample;
import cn.qhjys.mall.vo.RebateStoreVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface StoreRebateMapper {
    int countByExample(StoreRebateExample example);

    int deleteByExample(StoreRebateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreRebate record);

    int insertSelective(StoreRebate record);

    List<StoreRebate> selectByExample(StoreRebateExample example);

    StoreRebate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreRebate record, @Param("example") StoreRebateExample example);

    int updateByExample(@Param("record") StoreRebate record, @Param("example") StoreRebateExample example);

    int updateByPrimaryKeySelective(StoreRebate record);

    int updateByPrimaryKey(StoreRebate record);
    
    /**
     * 根据条件查询具体的打折商家等信息
     */
    Map<String, Object> selectRebateDetailById(Map<String, Object> map);
    
    /**
     * 查询打折店铺信息列表
     */
    List<RebateStoreVo> selectRebateStoreList(Map<String, Object> map);
}