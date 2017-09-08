package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.Bargain;
import cn.qhjys.mall.entity.BargainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BargainMapper {
    int countByExample(BargainExample example);

    int deleteByExample(BargainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bargain record);

    int insertSelective(Bargain record);

    List<Bargain> selectByExample(BargainExample example);

    Bargain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Bargain record, @Param("example") BargainExample example);

    int updateByExample(@Param("record") Bargain record, @Param("example") BargainExample example);

    int updateByPrimaryKeySelective(Bargain record);

    int updateByPrimaryKey(Bargain record);
}