package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.StoreAudit;
import cn.qhjys.mall.entity.StoreAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreAuditMapper {
    int countByExample(StoreAuditExample example);

    int deleteByExample(StoreAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreAudit record);

    int insertSelective(StoreAudit record);

    List<StoreAudit> selectByExample(StoreAuditExample example);

    StoreAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreAudit record, @Param("example") StoreAuditExample example);

    int updateByExample(@Param("record") StoreAudit record, @Param("example") StoreAuditExample example);

    int updateByPrimaryKeySelective(StoreAudit record);

    int updateByPrimaryKey(StoreAudit record);
}