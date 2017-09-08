package cn.qhjys.mall.service.system.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.IntegralLogExample;
import cn.qhjys.mall.mapper.IntegralLogMapper;
import cn.qhjys.mall.service.system.IntegralLogService;
import cn.qhjys.mall.util.BaseUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class IntegralLogServiceImpl implements IntegralLogService {
	@Autowired
	private IntegralLogMapper IntegralLogMapper;

	@Override
	public Page<IntegralLog> queryPage(Long userId, String type, String start, String end, Integer pageNum,
			Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		IntegralLogExample example = new IntegralLogExample();
		if (userId != null)
			example.createCriteria().andReviewIdEqualTo(userId);
		if (!BaseUtil.judgeNull(type))
			example.createCriteria().andBusinessCodeEqualTo(type);
		try {
			if (!BaseUtil.judgeNull(start))
				example.createCriteria().andCreateTimeGreaterThan(format.parse(start));
			if (!BaseUtil.judgeNull(end))
				example.createCriteria().andCreateTimeLessThan(format.parse(end));
		} catch (ParseException e) {
			throw new Exception(e);
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<IntegralLog>) IntegralLogMapper.selectByExample(example);
	}

	@Override
	public Page<IntegralLog> queryPageBySellerExchange(Long sellerId, String type, String start, String end,
			Integer pageNum, Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		IntegralLogExample example = new IntegralLogExample();
		example.setOrderByClause(" create_time desc");
		if (sellerId != null)
			example.createCriteria().andSendIdEqualTo(sellerId);
		try {
			if (!BaseUtil.judgeNull(type))
				example.createCriteria().andBusinessCodeEqualTo(type);
			if (!BaseUtil.judgeNull(start))
				example.createCriteria().andCreateTimeGreaterThan(format.parse(start));
			if (!BaseUtil.judgeNull(end))
				example.createCriteria().andCreateTimeLessThan(format.parse(end));
		} catch (ParseException e) {
			throw new Exception(e);
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<IntegralLog>) IntegralLogMapper.selectByExample(example);
	}
}
