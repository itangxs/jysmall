package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.RefundConsult;
import cn.qhjys.mall.entity.RefundConsultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RefundConsultMapper {
    int countByExample(RefundConsultExample example);

    int deleteByExample(RefundConsultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RefundConsult record);

    int insertSelective(RefundConsult record);

    List<RefundConsult> selectByExample(RefundConsultExample example);

    RefundConsult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RefundConsult record, @Param("example") RefundConsultExample example);

    int updateByExample(@Param("record") RefundConsult record, @Param("example") RefundConsultExample example);

    int updateByPrimaryKeySelective(RefundConsult record);

    int updateByPrimaryKey(RefundConsult record);
}