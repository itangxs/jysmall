package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.BargainInfo;
import cn.qhjys.mall.entity.BargainInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BargainInfoMapper {
    int countByExample(BargainInfoExample example);

    int deleteByExample(BargainInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BargainInfo record);

    int insertSelective(BargainInfo record)throws Exception;
    
    int insertsimple(BargainInfo record)throws Exception;

    List<BargainInfo> selectByExample(BargainInfoExample example);

    BargainInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BargainInfo record, @Param("example") BargainInfoExample example);

    int updateByExample(@Param("record") BargainInfo record, @Param("example") BargainInfoExample example);

    int updateByPrimaryKeySelective(BargainInfo record);

    int updateByPrimaryKey(BargainInfo record);
}