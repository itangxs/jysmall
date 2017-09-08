package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.SysUserTask;
import cn.qhjys.mall.entity.SysUserTaskExample;
import cn.qhjys.mall.entity.SysUserTaskExample.Criteria;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.SysUserTaskMapper;
import cn.qhjys.mall.mapper.custom.TaskSysMapper;
import cn.qhjys.mall.service.IntegralExpiredService;
import cn.qhjys.mall.service.ReviewService;
import cn.qhjys.mall.service.system.SysTaskService;
import cn.qhjys.mall.service.system.SysUserTaskService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.vo.SysTaskVo;
import cn.qhjys.mall.weixin.common.AccessToken;
import cn.qhjys.mall.weixin.common.WeiXinUser;
import cn.qhjys.mall.weixin.common.WeiXinUserList;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

@Service
public class SysUserTaskServiceImpl implements  SysUserTaskService{
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SysUserTaskMapper sysUserTaskMapper;
	@Autowired
	private TaskSysMapper taskSysMapper;
	@Autowired
	private SysTaskService sysTaskService;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private IntegralExpiredService integralExpiredService;
	@Autowired
	private CashLogMapper cashLogMapper;
	@Autowired
	private ReviewService reviewService;
	
	
	@Override
	public int insertSysUserTask(SysUserTask SysUserTask) throws Exception {
		return sysUserTaskMapper.insert(SysUserTask);
	}
 
	@Override
	public SysUserTask getSysUserTask(Long sysid, Long uid) throws Exception {
		SysUserTaskExample example = new  SysUserTaskExample();
		example.createCriteria().andTaskIdEqualTo(sysid).andUserIdEqualTo(uid);
		List<SysUserTask> list = sysUserTaskMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public SysUserTask getSysUserTask(Long id) throws Exception {
		return sysUserTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysUserTask> getSysUserTasksListByTaskIdAndStatus(Long taskid, Integer status) throws Exception {
		SysUserTaskExample example = new  SysUserTaskExample();
		Criteria createCriteria = example.createCriteria();
		if(null!=taskid){
			createCriteria.andTaskIdEqualTo(taskid);
		}
		if(null!=status){
			createCriteria.andStatusEqualTo(status);
		}
		return sysUserTaskMapper.selectByExample(example);
	}

	@Override
	public void updateSysUserTaskStatus(Long id, Integer status,BigDecimal totamt) throws Exception {
		SysUserTask record = getSysUserTask(id);
		record.setStatus(status);
		record.setTotamt(totamt);
		sysUserTaskMapper.updateByPrimaryKey(record);
	}


	@Override
	public int updateSysUserTask(SysUserTask SysUserTask) {
		return sysUserTaskMapper.updateByPrimaryKeySelective(SysUserTask);
	}


	@Override
	// 关注任务 自动审核
	public Boolean updatetiming() throws Exception {
			// 获取所有有效的关注任务 2
			try {
				List<SysTask> sysTask = sysTaskService.getSysTask(2, 2, new Date());
				//this.logger.info("-----sysTask任务有:----" + sysTask.size());
				if (null != sysTask && sysTask.size() > 0) {
					for (int i = 0; i < sysTask.size(); i++) {
						SysTask task = sysTask.get(i);
					//	this.logger.info("-----task----" + task.getId());
						// 审核中的用户
						List<SysUserTask> list = getSysUserTasksListByTaskIdAndStatus(task.getId(), 0);
						if (null != list && list.size() > 0&&!StringUtils.isEmpty(task.getAppid())&&!StringUtils.isEmpty(task.getSecret())) {
						//	this.logger.info("-----list----" + list.size());
							// 获取公众号用户列表
							List<WeiXinUser> weixinList = test(task.getAppid(), task.getSecret(), null);
							if (null != weixinList && weixinList.size() > 0) {
								for (int j = 0; j < list.size(); j++) {
									SysUserTask sysUserTask = list.get(j);
									//this.logger.info("-----sysUserTask----" + sysUserTask);
									Boolean statusE = true;// 记录是否改变状态
									k: for (int k = 0; k < weixinList.size(); k++) {
										WeiXinUser user = weixinList.get(k);
										if (sysUserTask.getOpenId().equals(user.getOpenid())
												|| sysUserTask.getOpenId() == user.getOpenid()) {
											insertCashLogBySysTask(sysUserTask.getUserId(),sysUserTask.getTotamt());
											updateSysUserTaskStatus(sysUserTask.getId(), 1,task.getFulfilReward());// 改变状态
											statusE = false;
											break k;
										}
									}
									if (statusE) {
										updateSysUserTaskStatus(sysUserTask.getId(), 2,task.getUnfulfilReward());// 改变状态
																											// 失败
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				this.logger.info("-----关注任务自动审核定时任务报错----");
				e.printStackTrace();
			}
			return true;
		}

		private List<WeiXinUser> test(String appid, String appat, String e) {
			AccessToken at = WeiXinUtil.getAccessToken(appid, appat);
			this.logger.info("-----at----token获取失败" + at); 
			if (null != at && null != at.getToken()) {
				WeiXinUserList users = WeiXinUtil.getUser(at.getToken(), "");// Null默认
				List<WeiXinUser> data = users.getData();
				if (null != data && data.size() > 0) {
					return data;
				} else {
					return null;
				}
			}
			return null;
		}
		
		public int insertCashLogBySysTask(Long userId,BigDecimal totamt) throws Exception{
			int num = 0;
			if (totamt.intValue() >0) {
			CashAccountExample example = new CashAccountExample();
			example.createCriteria().andAccountTypeEqualTo(0).andAccountIdEqualTo(userId);
			CashAccount account = cashAccountMapper.selectByExample(example).get(0);
			Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
			BigDecimal after;
			CashLog log = new CashLog();
			after = account.getBalance().add(totamt);
			log.setSendId(jysmall);
			log.setReviewId(userId);
			log.setAmount(totamt);
			log.setPayType(10);
			log.setPayWay(4);
			log.setBusinessCode(PAYCODE.B010);
			log.setBusinessName(PAYCODE.B010N);
			log.setReviewBefor(account.getBalance());
			log.setReviewAfter(after);
			log.setCreateTime(new Date());
			integralExpiredService.updateIntegralExpiredByEvaluate(userId, totamt.intValue());
			account.setBalance(after);
			account.setCreateDate(new Date());
			num += cashAccountMapper.updateByPrimaryKeySelective(account);
			log.setCreateTime(new Date());
			num +=  cashLogMapper.insertSelective(log);
			}
			return num;
		}

		@Override
		public boolean updateWenjuanTiming() throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskType", 1);
			map.put("status", new Integer[]{0});
			List<SysTaskVo> list = taskSysMapper.selectSysUserTaskByType(map);
			for (int i = 0; i < list.size(); i++) {
				SysTaskVo task = list.get(i);
				insertCashLogBySysTask(task.getUserId(), task.getTotamt());
				SysUserTask ut = new SysUserTask();
				ut.setId(task.getUid());
				ut.setStatus(1);
				sysUserTaskMapper.updateByPrimaryKeySelective(ut);
				SysTask st = sysTaskService.getSysTask(ut.getTaskId());
				st.setTaskFulfil(st.getTaskFulfil()+1);
				sysTaskService.updateSysTask(st);
			}
			
			return true;
		}

		@Override
		public boolean updatePinlunTiming() throws Exception {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskType", 3);
			map.put("status", new Integer[]{0});
			List<SysTaskVo> list = taskSysMapper.selectSysUserTaskByType(map);
			for (int i = 0; i < list.size(); i++) {
				SysTaskVo task = list.get(i);
				boolean a = reviewService.countReviewsLogByUserAndTime(task.getUserId(), task.getUcreateTime(), task.getTaskFrom());
				if (a) {
					insertCashLogBySysTask(task.getUserId(), task.getTotamt());
					SysUserTask ut = new SysUserTask();
					ut.setId(task.getUid());
					ut.setStatus(1);
					sysUserTaskMapper.updateByPrimaryKeySelective(ut);
					SysTask st = sysTaskService.getSysTask(task.getId());
					st.setTaskFulfil(st.getTaskFulfil()+1);
					sysTaskService.updateSysTask(st);
				}
			}
			return true;
		}

}
