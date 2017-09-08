package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqThirdPayExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FqThirdPayMapper {
    int countByExample(FqThirdPayExample example);

    int deleteByExample(FqThirdPayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqThirdPay record);

    int insertSelective(FqThirdPay record);

    List<FqThirdPay> selectByExample(FqThirdPayExample example);

    FqThirdPay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqThirdPay record, @Param("example") FqThirdPayExample example);

    int updateByExample(@Param("record") FqThirdPay record, @Param("example") FqThirdPayExample example);

    int updateByPrimaryKeySelective(FqThirdPay record);

    int updateByPrimaryKey(FqThirdPay record);
    
    
    /**
     * 查询在本店消费过的用户openId并去重
     * @param sellerId
     * @return
     */
    List<String> queryConsumerBySellerId(Long sellerId);
    
    /**
     * 查询某一段时间内  所有商家的交易笔数（分组 seller_id）
     * 
     * @param map   startDate    endDate
     * @return List
     */
    List<Map> querySellerTransactionNumberByDate(Map map);
}