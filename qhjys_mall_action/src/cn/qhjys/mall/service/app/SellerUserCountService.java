package cn.qhjys.mall.service.app;

import java.util.Date;
import java.util.List;

import cn.qhjys.mall.vo.FeedbackVo;
import cn.qhjys.mall.vo.SellerUserCountVo;
import cn.qhjys.mall.vo.SellerUserInfoVo;

import com.github.pagehelper.Page;

public interface SellerUserCountService {
	public SellerUserCountVo querySellerUserCountVo(Long sellerId);
	
	public Page<SellerUserInfoVo> querySellerUserInfoVo(Long sellerId,String orderBy ,String order,Integer pageNum,Integer pageSize);

	public SellerUserInfoVo queryDayliyVo(Long sellerId,Date date);
	public SellerUserInfoVo queryJsDayliyVo(Long sellerId,Date date);
	
	public Page<FeedbackVo> queryFeedbackVo(Integer pageNum,Integer pageSize);
	public Integer queryUserNum(Long sellerId);
}
