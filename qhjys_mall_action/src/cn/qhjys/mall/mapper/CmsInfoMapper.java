package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CmsInfo;
import cn.qhjys.mall.entity.CmsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsInfoMapper {
    int countByExample(CmsInfoExample example);

    int deleteByExample(CmsInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsInfo record);

    int insertSelective(CmsInfo record);

    List<CmsInfo> selectByExampleWithBLOBs(CmsInfoExample example);

    List<CmsInfo> selectByExample(CmsInfoExample example);

    CmsInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsInfo record, @Param("example") CmsInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsInfo record, @Param("example") CmsInfoExample example);

    int updateByExample(@Param("record") CmsInfo record, @Param("example") CmsInfoExample example);

    int updateByPrimaryKeySelective(CmsInfo record);

    int updateByPrimaryKeyWithBLOBs(CmsInfo record);

    int updateByPrimaryKey(CmsInfo record);
}