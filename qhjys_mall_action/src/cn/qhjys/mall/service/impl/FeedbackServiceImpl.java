package cn.qhjys.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.Feedback;
import cn.qhjys.mall.entity.FeedbackExample;
import cn.qhjys.mall.mapper.FeedbackMapper;
import cn.qhjys.mall.service.FeedbackService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackMapper feedbackMapper;

	@Override
	public int insertFeedback(Feedback feedback) {
		return feedbackMapper.insertSelective(feedback);
	}

	@Override
	public int updateFeedback(Feedback feedback) {
		return feedbackMapper.updateByPrimaryKeySelective(feedback);
	}

	@Override
	public Page<Feedback> listFeedback(int pageNum, int pageSize) {
		FeedbackExample example = new FeedbackExample();
		example.setOrderByClause("feedback_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<Feedback>) feedbackMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Feedback getFeedback(Long id) {
		return feedbackMapper.selectByPrimaryKey(id);
	}

}
