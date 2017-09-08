package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinUserinfoMapper {
    int countByExample(WeixinUserinfoExample example);

    int deleteByExample(WeixinUserinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WeixinUserinfo record);

    int insertSelective(WeixinUserinfo record);

    List<WeixinUserinfo> selectByExample(WeixinUserinfoExample example);

    WeixinUserinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WeixinUserinfo record, @Param("example") WeixinUserinfoExample example);

    int updateByExample(@Param("record") WeixinUserinfo record, @Param("example") WeixinUserinfoExample example);

    int updateByPrimaryKeySelective(WeixinUserinfo record);

    int updateByPrimaryKey(WeixinUserinfo record);
}