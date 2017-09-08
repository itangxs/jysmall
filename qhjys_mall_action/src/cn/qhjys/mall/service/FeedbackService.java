package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.Feedback;

import com.github.pagehelper.Page;

public interface FeedbackService {
	public int insertFeedback(Feedback feedback);
	public int updateFeedback(Feedback feedback);
	public Feedback getFeedback(Long id);
	public Page<Feedback> listFeedback(int pageNum,int pageSize);
}
