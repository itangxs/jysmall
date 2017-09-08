package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.UserTask;

public interface UserTaskService {
	public UserTask getUserTask(Long userId ,Long taskId);
	public int countUserTask(Long userId);
	public int insertUserTask(UserTask userTask) throws Exception ;
	public int updateUserTask(UserTask userTask);
	
	public int insertUserTaskByC(Long userId,String project);
	
	public List<UserTask> listUserInfoByStatus(Long taskId,String status);
	
	public int updateUserTaskByCp(UserTask userTask)throws Exception ;
	
	public int deleteUserTask(Long id);
	
	public int insertUserTaskBySeller(Long userId, Long taskId);
	
	public List<UserTask> queryUserTask(Long userId ,Long taskId);
	
	public UserTask getUserTask(Long userId ,Long taskId,Integer level);
	
	public UserTask getUserTask(Long userId ,String gameName,Integer level);
	
	public UserTask getUserTask(Long userId ,Long taskId,String status);
	
	public int countMoney(Long userId ,Long taskId);
	public int insertUserTaskNotChange(UserTask userTask);
	public UserTask getUserTask(Long id);
	
	public UserTask getUserTask(String orderId);
}
