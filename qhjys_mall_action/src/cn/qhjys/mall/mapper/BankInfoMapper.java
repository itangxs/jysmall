package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.BankInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BankInfoMapper {
    int countByExample(BankInfoExample example);

    int deleteByExample(BankInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BankInfo record);

    int insertSelective(BankInfo record);

    List<BankInfo> selectByExample(BankInfoExample example);

    BankInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BankInfo record, @Param("example") BankInfoExample example);

    int updateByExample(@Param("record") BankInfo record, @Param("example") BankInfoExample example);

    int updateByPrimaryKeySelective(BankInfo record);

    int updateByPrimaryKey(BankInfo record);
}