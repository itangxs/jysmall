package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ActivitDetail;
import cn.qhjys.mall.entity.ActivitDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivitDetailMapper {
    int countByExample(ActivitDetailExample example);

    int deleteByExample(ActivitDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivitDetail record);

    int insertSelective(ActivitDetail record);

    List<ActivitDetail> selectByExample(ActivitDetailExample example);

    ActivitDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivitDetail record, @Param("example") ActivitDetailExample example);

    int updateByExample(@Param("record") ActivitDetail record, @Param("example") ActivitDetailExample example);

    int updateByPrimaryKeySelective(ActivitDetail record);

    int updateByPrimaryKey(ActivitDetail record);
}