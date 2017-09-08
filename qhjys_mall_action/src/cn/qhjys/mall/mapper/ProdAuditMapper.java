package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ProdAudit;
import cn.qhjys.mall.entity.ProdAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProdAuditMapper {
    int countByExample(ProdAuditExample example);

    int deleteByExample(ProdAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProdAudit record);

    int insertSelective(ProdAudit record);

    List<ProdAudit> selectByExample(ProdAuditExample example);

    ProdAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProdAudit record, @Param("example") ProdAuditExample example);

    int updateByExample(@Param("record") ProdAudit record, @Param("example") ProdAuditExample example);

    int updateByPrimaryKeySelective(ProdAudit record);

    int updateByPrimaryKey(ProdAudit record);
}