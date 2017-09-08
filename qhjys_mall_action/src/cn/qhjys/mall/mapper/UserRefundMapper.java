package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.UserRefund;
import cn.qhjys.mall.entity.UserRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRefundMapper {
    int countByExample(UserRefundExample example);

    int deleteByExample(UserRefundExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRefund record);

    int insertSelective(UserRefund record);

    List<UserRefund> selectByExample(UserRefundExample example);

    UserRefund selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRefund record, @Param("example") UserRefundExample example);

    int updateByExample(@Param("record") UserRefund record, @Param("example") UserRefundExample example);

    int updateByPrimaryKeySelective(UserRefund record);

    int updateByPrimaryKey(UserRefund record);
}