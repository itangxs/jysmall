package cn.qhjys.mall.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.qhjys.mall.vo.FeedbackVo;
import cn.qhjys.mall.vo.SellerUserCountVo;
import cn.qhjys.mall.vo.SellerUserInfoVo;

public interface SellerUserMapper {
	public SellerUserCountVo querySellerUserCountVo(Long sellerId);
	public List<SellerUserInfoVo> querySellerUserInfoVo(@Param("sellerId")Long sellerId,@Param("orderBy")String orderBy,@Param("order")String order);
	public SellerUserInfoVo queryDayliyVo(@Param("sellerId")Long sellerId,@Param("startTime")String startTime,@Param("endTime")String endTime);
	public SellerUserInfoVo queryJsDayliyVo(@Param("sellerId")Long sellerId,@Param("startTime")String startTime,@Param("endTime")String endTime);
	public SellerUserInfoVo querySellerVo(@Param("sellerId")Long sellerId);
	public List<FeedbackVo> queryFeedbackVo();
	public Integer queryUserNum(Long sellerId);

}