package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.TaskInfoExample;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.entity.UserTaskExample;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.CouponInfoMapper;
import cn.qhjys.mall.mapper.TaskInfoMapper;
import cn.qhjys.mall.mapper.UserTaskMapper;
import cn.qhjys.mall.mapper.custom.TaskMapper;
import cn.qhjys.mall.service.IntegralExpiredService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.util.ConstantsConfigurer;

@Service("userTaskService")
public class UserTaskServiceImpl extends Base implements UserTaskService {

	@Autowired
	private UserTaskMapper userTaskMapper;
	@Autowired
	private TaskInfoMapper taskInfoMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashLogMapper cashLogMapper;
	@Autowired
	private IntegralExpiredService integralExpiredService;
	@Autowired
	private CouponInfoMapper couponInfoMapper;
	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public UserTask getUserTask(Long userId, Long taskId) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId);
		example.setOrderByClause("create_time desc");
		List<UserTask> list = userTaskMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int insertUserTask(UserTask userTask) throws Exception {
		int num = 0;
		num += userTaskMapper.insertSelective(userTask);
		if(userTask.getTotamt().intValue() >0){
			CashAccountExample example = new CashAccountExample();
			example.createCriteria().andAccountTypeEqualTo(0).andAccountIdEqualTo(userTask.getUserId());
			CashAccount account = cashAccountMapper.selectByExample(example).get(0);
			Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
			BigDecimal after;
			CashLog log = new CashLog();
			after = account.getBalance().add(userTask.getTotamt());
			log.setSendId(jysmall);
			log.setReviewId(userTask.getUserId());
			log.setAmount(userTask.getTotamt());
			log.setPayType(10);
			log.setPayWay(4);
			log.setBusinessCode(PAYCODE.B010);
			log.setBusinessName(PAYCODE.B010N);
			log.setReviewBefor(account.getBalance());
			log.setReviewAfter(after);
			log.setCreateTime(new Date());
			integralExpiredService.updateIntegralExpiredByEvaluate(userTask.getUserId(), userTask.getTotamt().intValue());
			account.setBalance(after);
			account.setCreateDate(new Date());
			num += cashAccountMapper.updateByPrimaryKeySelective(account);
			log.setCreateTime(new Date());
			num += cashLogMapper.insertSelective(log);
			if (userTask.getStatus().equals("cp")) {
				TaskInfo task = taskInfoMapper.selectByPrimaryKey(userTask.getTaskId());
				task.setTaskFulfil(task.getTaskFulfil().add(new BigDecimal(1)));
				taskInfoMapper.updateByPrimaryKeySelective(task);
			}
		}
		return num;
	}

	@Override
	public int updateUserTask(UserTask userTask) {
		return userTaskMapper.updateByPrimaryKeySelective(userTask);
	}

	@Override
	public int countUserTask(Long userId) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andUserIdEqualTo(userId);
		int a = userTaskMapper.countByExample(example);
		return a;
	}

	@Override
	public int insertUserTaskByC(Long userId, String project) {
		TaskInfoExample example = new TaskInfoExample();
		example.createCriteria().andProjectEqualTo(project);
		List<TaskInfo> list = taskInfoMapper.selectByExample(example);
		if(list.size()>0){
			TaskInfo task = list.get(0);
			UserTask ut = new UserTask();
			ut.setCreateTime(new Date());
			ut.setStatus("c");
			ut.setTaskId(task.getId());
			ut.setTotamt(task.getFulfilReward());
			ut.setUserId(userId);
			return userTaskMapper.insertSelective(ut);
		}
		return 0;
	}

	@Override
	public List<UserTask> listUserInfoByStatus(Long taskId, String status) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andTaskIdEqualTo(taskId).andStatusEqualTo(status);
		return userTaskMapper.selectByExample(example);
	}

	@Override
	public int updateUserTaskByCp(UserTask userTask) throws Exception {
		int num = 0;
		num += userTaskMapper.updateByPrimaryKeySelective(userTask);
		if(userTask.getTotamt().intValue() >0){
			CashAccountExample example = new CashAccountExample();
			example.createCriteria().andAccountTypeEqualTo(0).andAccountIdEqualTo(userTask.getUserId());
			CashAccount account = cashAccountMapper.selectByExample(example).get(0);
			Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
			BigDecimal after;
			CashLog log = new CashLog();
			after = account.getBalance().add(userTask.getTotamt());
			log.setSendId(jysmall);
			log.setReviewId(userTask.getUserId());
			log.setAmount(userTask.getTotamt());
			log.setPayType(10);
			log.setPayWay(4);
			log.setBusinessCode(PAYCODE.B010);
			log.setBusinessName(PAYCODE.B010N);
			log.setReviewBefor(account.getBalance());
			log.setReviewAfter(after);
			log.setCreateTime(new Date());
			integralExpiredService.updateIntegralExpiredByEvaluate(userTask.getUserId(), userTask.getTotamt().intValue());
			account.setBalance(after);
			account.setCreateDate(new Date());
			num += cashAccountMapper.updateByPrimaryKeySelective(account);
			log.setCreateTime(new Date());
			num += cashLogMapper.insertSelective(log);
			if (userTask.getStatus().equals("cp")) {
				TaskInfo task = taskInfoMapper.selectByPrimaryKey(userTask.getTaskId());
				task.setTaskFulfil(task.getTaskFulfil().add(new BigDecimal(1)));
				taskInfoMapper.updateByPrimaryKeySelective(task);
			}
		}
		return num;
	}

	@Override
	public int deleteUserTask(Long id) {
		return userTaskMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertUserTaskBySeller(Long userId, Long taskId) {
		TaskInfo ti = taskInfoMapper.selectByPrimaryKey(taskId);
		if(ti!=null){
			UserTask ut = new UserTask();
			ut.setCreateTime(new Date());
			ut.setStatus("c");
			ut.setTaskId(ti.getId());
			ut.setTotamt(ti.getFulfilReward());
			ut.setUserId(userId);
			int a = userTaskMapper.insertSelective(ut);
			return a;
		}
		return 0;
	}

	@Override
	public List<UserTask> queryUserTask(Long userId, Long taskId) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId).andMoneyGreaterThan(0);
		List<UserTask> list = userTaskMapper.selectByExample(example);
		return list;
	}

	@Override
	public UserTask getUserTask(Long userId, Long taskId, Integer level) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId).andGameLevelEqualTo(level);
		List<UserTask> list = userTaskMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public UserTask getUserTask(Long userId, Long taskId, String status) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andUserIdEqualTo(userId).andTaskIdEqualTo(taskId).andStatusEqualTo(status);
		List<UserTask> list = userTaskMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int countMoney(Long userId, Long taskId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("taskId", taskId);
		return taskMapper.countMoney(map);
	}

	@Override
	public int insertUserTaskNotChange(UserTask userTask) {
		
		return userTaskMapper.insertSelective(userTask);
	}

	@Override
	public UserTask getUserTask(Long id) {
		return userTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserTask getUserTask(String orderId) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andGameNameEqualTo(orderId);
		List<UserTask> uts = userTaskMapper.selectByExample(example);
		return uts.size()>0?uts.get(0):null;
	}

	@Override
	public UserTask getUserTask(Long userId, String gameName, Integer level) {
		UserTaskExample example = new UserTaskExample();
		example.createCriteria().andUserIdEqualTo(userId).andGameNameEqualTo(gameName).andGameLevelEqualTo(level);
		List<UserTask> list = userTaskMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

}
