package cn.qhjys.mall.controller.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.SysTaskLog;
import cn.qhjys.mall.entity.SysUserTask;
import cn.qhjys.mall.entity.TaskAnswer;
import cn.qhjys.mall.entity.TaskQuestion;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.system.SysTaskLogService;
import cn.qhjys.mall.service.system.SysTaskService;
import cn.qhjys.mall.service.system.SysUserTaskService;
import cn.qhjys.mall.service.system.TaskAnswerService;
import cn.qhjys.mall.service.system.TaskQuestionService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.vo.SysTaskVo;

@Controller
@RequestMapping("/account/systask")
public class AccountSysTaskController extends Base{
	@Autowired
	private SysTaskService sysTaskService;
	@Autowired
	private SysUserTaskService sysUserTaskService;
	@Autowired
	private SysTaskLogService sysTaskLogService;
	@Autowired
	private TaskQuestionService taskQuestionService;
	@Autowired
	private TaskAnswerService taskAnswerService;
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/index")
	public ModelAndView systaskIndex(HttpSession session,Integer page,Integer pageSize,Integer taskType,Integer status){
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/systask");
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (status ==null) {
			status = 0;
		}
		if (status != 0 && user == null) {
			view.setViewName("account/login");
			return view;
		}
		if (status == 0) {
			if (user == null) {
				user = new UserInfo();
			}
			List<SysTask> spage = sysTaskService.listSysTaskByUserAndStatus(user.getId(), user.getLevel(), taskType, page, pageSize);
			view.addObject("page", spage);
		}else if (status == 1) {
			List<SysTaskVo> spage = sysTaskService.listSysTaskByUser(user.getId(), new Integer[]{0}, taskType, page, pageSize);
			view.addObject("page", spage);
		}else if (status == 2) {
			List<SysTaskVo> spage = sysTaskService.listSysTaskByUser(user.getId(), new Integer[]{1}, taskType, page, pageSize);
			view.addObject("page", spage);
		}else if (status == 3) {
			List<SysTaskVo> spage = sysTaskService.listSysTaskByUser(user.getId(), new Integer[]{2,3}, taskType, page, pageSize);
			view.addObject("page", spage);
		}
		view.addObject("status", status);
		view.addObject("taskType", taskType);
		return view;
	}
	
	@RequestMapping("/dotask")
	public ModelAndView dotask(Long taskId,HttpSession session) throws Exception{
		int questionNo = 1;
		ModelAndView view = new ModelAndView("account/task/task_wj");
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			view.setViewName("account/login");
			return view;
		}
		SysTask task = sysTaskService.getSysTask(taskId);
		if(task == null || task.getStatus() != 2){
			view.addObject("message", "任务不存在或已过期");
			return view;
		}
		if (task.getTaskType() == 1) {
			SysUserTask userTask = sysUserTaskService.getSysUserTask(taskId, user.getId());
			if (userTask != null) {
				if (userTask.getStatus() == 3) {
					view.addObject("message", "您不符合本次问卷调查的条件,感谢您的参与");
					return view;
				}
				SysTaskLog sysTaskLog = sysTaskLogService.getLastSysTaskLog(userTask.getId());
				questionNo = sysTaskLog.getQuestionId()+1;
			}
			TaskQuestion question = taskQuestionService.getTaskQuestion(taskId, questionNo);
			if (question == null) {
				view.addObject("message", "答题已完成");
			}else{
				int questionNum = taskQuestionService.countQuestionByTask(taskId);
				view.addObject("question", question);
				view.addObject("questionNum", questionNum);
				if (question.getQuestionType() == 1 && question.getAnswerType() == 0) {
					List<TaskAnswer> answers = taskAnswerService.listAllTaskAnswer(question.getId());
					view.addObject("answers", answers);
				}
			}
		}else if (task.getTaskType() == 2) {
			view.addObject("task", task);
			view.addObject("userId", user.getId());
			view.addObject("url", ConstantsConfigurer.getProperty("weixin_app_url"));
			view.setViewName("account/task/task_gz");
		}else if (task.getTaskType() == 3){
			Long storeId = storeService.queryStoreInfoBySeller(task.getTaskFrom()).getId();
			view.addObject("task", task);
			view.addObject("storeId", storeId);
			view.setViewName("account/task/task_pj");
		}
		return view;
	}
	
	@RequestMapping("/answerQuestion")
	public ModelAndView answerQuestion(Long questionId ,Integer questionNo,Integer questionNum,String [] answers,String answer,HttpSession session) throws Exception{
		TaskQuestion question = taskQuestionService.getTaskQuestion(questionId);
		ModelAndView view = new ModelAndView("redirect:/account/systask/dotask.do?taskId="+question.getTaskId());
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			view.setViewName("account/login");
			return view;
		}
		int status = 4;
		if (questionNum == questionNo) {
			status = 0;
		}
		SysTaskLog log = new SysTaskLog();
		log.setQuestionId(questionNo);
		if (question.getQuestionType() == 1 ) {
			if (question.getAnswerType() == 0) {
			List<TaskAnswer> taskanswers = taskAnswerService.listAllTaskAnswer(question.getId());
			String an = "";
			for (int i = 0; i < answers.length; i++) {
				Long aid = Long.parseLong(answers[i]);
				for (int j = 0; j < taskanswers.size(); j++) {
					TaskAnswer taskanswer = taskanswers.get(j);
					if (taskanswer.getId() == aid && taskanswer.getIsCorrect() == 1) {
						status = 3;
						break;
					}
				}
				an += aid+",";
			}
				an = an.substring(0, an.length()-1);
				log.setAnswer(an);
			}else{
				log.setInformation(answer);
			}
		}
		SysUserTask userTask = sysUserTaskService.getSysUserTask(question.getTaskId(), user.getId());
		if (userTask == null) {
			userTask = new SysUserTask();
			userTask.setCreateTime(new Date());
			userTask.setStatus(status);
			userTask.setTaskId(question.getTaskId());
			if (status == 0) {
				SysTask sysTask = sysTaskService.getSysTask(question.getTaskId());
				userTask.setTotamt(sysTask.getFulfilReward());
			}else{
				userTask.setTotamt(new BigDecimal(0));
			}
			userTask.setUserId(user.getId());
			sysUserTaskService.insertSysUserTask(userTask);
			userTask = sysUserTaskService.getSysUserTask(question.getTaskId(), user.getId());
		}else{
			if (userTask.getStatus() != status) {
				userTask.setStatus(status);
			}
			if (status == 0) {
				SysTask sysTask = sysTaskService.getSysTask(question.getTaskId());
				userTask.setTotamt(sysTask.getFulfilReward());
			}
			sysUserTaskService.updateSysUserTask(userTask);
		}
		log.setUserTaskId(userTask.getId());
		sysTaskLogService.insertSysTaskLog(log);
		return view;
	}
	@RequestMapping("/doreview")
	public String  doreview(Long taskId,Long storeId,HttpSession session) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			return "account/login";
		}
		SysTask sysTask = sysTaskService.getSysTask(taskId);
		SysUserTask userTask = new SysUserTask();
		userTask.setCreateTime(new Date());
		userTask.setStatus(0);
		userTask.setTaskId(taskId);
		userTask.setTotamt(sysTask.getFulfilReward());
		userTask.setUserId(user.getId());
		sysUserTaskService.insertSysUserTask(userTask);
		return "redirect:/getStoreDetail.do?storeId="+storeId;
	}
}
	
