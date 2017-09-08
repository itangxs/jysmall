package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.WxMessage;
import cn.qhjys.mall.entity.WxMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMessageMapper {
    int countByExample(WxMessageExample example);

    int deleteByExample(WxMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WxMessage record);

    int insertSelective(WxMessage record);

    List<WxMessage> selectByExampleWithBLOBs(WxMessageExample example);

    List<WxMessage> selectByExample(WxMessageExample example);

    WxMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WxMessage record, @Param("example") WxMessageExample example);

    int updateByExampleWithBLOBs(@Param("record") WxMessage record, @Param("example") WxMessageExample example);

    int updateByExample(@Param("record") WxMessage record, @Param("example") WxMessageExample example);

    int updateByPrimaryKeySelective(WxMessage record);

    int updateByPrimaryKeyWithBLOBs(WxMessage record);

    int updateByPrimaryKey(WxMessage record);
}