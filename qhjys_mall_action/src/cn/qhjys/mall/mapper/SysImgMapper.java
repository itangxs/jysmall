package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SysImg;
import cn.qhjys.mall.entity.SysImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysImgMapper {
    int countByExample(SysImgExample example);

    int deleteByExample(SysImgExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysImg record);

    int insertSelective(SysImg record);

    List<SysImg> selectByExample(SysImgExample example);

    SysImg selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysImg record, @Param("example") SysImgExample example);

    int updateByExample(@Param("record") SysImg record, @Param("example") SysImgExample example);

    int updateByPrimaryKeySelective(SysImg record);

    int updateByPrimaryKey(SysImg record);
}