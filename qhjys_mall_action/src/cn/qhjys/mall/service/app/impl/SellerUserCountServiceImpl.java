package cn.qhjys.mall.service.app.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.mapper.custom.SellerUserMapper;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.vo.FeedbackVo;
import cn.qhjys.mall.vo.SellerUserCountVo;
import cn.qhjys.mall.vo.SellerUserInfoVo;

@Service("sellerUserCountService")
public class SellerUserCountServiceImpl implements SellerUserCountService {
	@Autowired
	private SellerUserMapper sellerUserMapper;

	@Override
	public SellerUserCountVo querySellerUserCountVo(Long sellerId) {
		return sellerUserMapper.querySellerUserCountVo(sellerId);
	}

	@Override
	public Page<SellerUserInfoVo> querySellerUserInfoVo(Long sellerId,
			String orderBy, String order,Integer pageNum,Integer pageSize) {
		if (pageNum == null) {
			pageNum =1 ;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		if (StringUtils.isEmpty(orderBy)) {
			orderBy = "conMoney";
			order = "DESC";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("orderBy", orderBy);
		map.put("order", order);
		PageHelper.startPage(pageNum, pageSize);
		return (Page<SellerUserInfoVo>) sellerUserMapper.querySellerUserInfoVo(sellerId, orderBy, order);
	}

	@Override
	public SellerUserInfoVo queryDayliyVo(Long sellerId, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdf.format(date);
		return sellerUserMapper.queryDayliyVo(sellerId, date1+" 00:00:00",date1+" 23:59:59");
	}
	@Override
	public SellerUserInfoVo queryJsDayliyVo(Long sellerId, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdf.format(date);
		return sellerUserMapper.queryJsDayliyVo(sellerId, date1+" 00:00:00",date1+" 23:59:59");
	}
	@Override
	public Page<FeedbackVo> queryFeedbackVo(Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FeedbackVo>) sellerUserMapper.queryFeedbackVo();
	}

	@Override
	public Integer queryUserNum(Long sellerId) {
		return sellerUserMapper.queryUserNum(sellerId);
	}
}
