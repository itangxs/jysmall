package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.VoucherInfo;
import cn.qhjys.mall.entity.VoucherInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoucherInfoMapper {
    int countByExample(VoucherInfoExample example);

    int deleteByExample(VoucherInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VoucherInfo record);

    int insertSelective(VoucherInfo record);

    List<VoucherInfo> selectByExample(VoucherInfoExample example);

    VoucherInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VoucherInfo record, @Param("example") VoucherInfoExample example);

    int updateByExample(@Param("record") VoucherInfo record, @Param("example") VoucherInfoExample example);

    int updateByPrimaryKeySelective(VoucherInfo record);

    int updateByPrimaryKey(VoucherInfo record);
}