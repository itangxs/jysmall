package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SellerLoginLog;
import cn.qhjys.mall.entity.SellerLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SellerLoginLogMapper {
    int countByExample(SellerLoginLogExample example);

    int deleteByExample(SellerLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerLoginLog record);

    int insertSelective(SellerLoginLog record);

    List<SellerLoginLog> selectByExample(SellerLoginLogExample example);

    SellerLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerLoginLog record, @Param("example") SellerLoginLogExample example);

    int updateByExample(@Param("record") SellerLoginLog record, @Param("example") SellerLoginLogExample example);

    int updateByPrimaryKeySelective(SellerLoginLog record);

    int updateByPrimaryKey(SellerLoginLog record);
}