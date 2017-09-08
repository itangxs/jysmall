package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ThirdAccount;
import cn.qhjys.mall.entity.ThirdAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThirdAccountMapper {
    int countByExample(ThirdAccountExample example);

    int deleteByExample(ThirdAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ThirdAccount record);

    int insertSelective(ThirdAccount record);

    List<ThirdAccount> selectByExample(ThirdAccountExample example);

    ThirdAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ThirdAccount record, @Param("example") ThirdAccountExample example);

    int updateByExample(@Param("record") ThirdAccount record, @Param("example") ThirdAccountExample example);

    int updateByPrimaryKeySelective(ThirdAccount record);

    int updateByPrimaryKey(ThirdAccount record);
}