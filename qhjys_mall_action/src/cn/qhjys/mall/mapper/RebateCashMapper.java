package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.RebateCash;
import cn.qhjys.mall.entity.RebateCashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RebateCashMapper {
    int countByExample(RebateCashExample example);

    int deleteByExample(RebateCashExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RebateCash record);

    int insertSelective(RebateCash record);

    List<RebateCash> selectByExample(RebateCashExample example);

    RebateCash selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RebateCash record, @Param("example") RebateCashExample example);

    int updateByExample(@Param("record") RebateCash record, @Param("example") RebateCashExample example);

    int updateByPrimaryKeySelective(RebateCash record);

    int updateByPrimaryKey(RebateCash record);
    
    List<RebateCash> selectLastRebateCashBySerller(Long sellerId);
    int updateStatMoney(RebateCash record);
}