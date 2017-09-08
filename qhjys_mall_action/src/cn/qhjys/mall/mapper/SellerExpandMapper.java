package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SellerExpand;
import cn.qhjys.mall.entity.SellerExpandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SellerExpandMapper {
    int countByExample(SellerExpandExample example);

    int deleteByExample(SellerExpandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SellerExpand record);

    int insertSelective(SellerExpand record);

    List<SellerExpand> selectByExample(SellerExpandExample example);

    SellerExpand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SellerExpand record, @Param("example") SellerExpandExample example);

    int updateByExample(@Param("record") SellerExpand record, @Param("example") SellerExpandExample example);

    int updateByPrimaryKeySelective(SellerExpand record);

    int updateByPrimaryKey(SellerExpand record);
}