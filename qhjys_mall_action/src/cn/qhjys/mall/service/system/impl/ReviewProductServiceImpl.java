package cn.qhjys.mall.service.system.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.mapper.ReviewsLogMapper;
import cn.qhjys.mall.mapper.custom.ReviewProductMapper;
import cn.qhjys.mall.service.system.ReviewProductService;
import cn.qhjys.mall.vo.system.ReviewProductVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class ReviewProductServiceImpl implements ReviewProductService {

	@Autowired
	private ReviewProductMapper reviewProductMapper;
	@Autowired
	private ReviewsLogMapper reviewMapper;

	@Override
	public Page<ReviewProductVo> queryReviewProductList(String productName, String userName, String startTime,
			String endTime, String storeName, Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productName", productName);
		map.put("userName", userName);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("storeName", storeName);
		PageHelper.startPage(pageNum, pageSize);
		Page<ReviewProductVo> page = (Page<ReviewProductVo>) reviewProductMapper.queryReviewProductList(map);
		return page;
	}

	@Override
	public boolean deleteReviewProduct(String[] ids) throws Exception {
		int row = 0;
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				row = reviewMapper.deleteByPrimaryKey(Long.parseLong(ids[i]));
			}
		}
		return row > 0 ? true : false;
	}
}