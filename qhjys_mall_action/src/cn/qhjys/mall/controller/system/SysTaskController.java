package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.TaskQuestion;
import cn.qhjys.mall.service.system.SysTaskService;
import cn.qhjys.mall.service.system.TaskQuestionService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/managermall/systemmall/sysTask")
public class SysTaskController extends Base{

	@Autowired
	private SysTaskService sysTaskService;
	@Autowired
	private TaskQuestionService taskQuestionService;
	
	@RequestMapping("/list")
	public ModelAndView list(Integer taskType, Integer status,
			Long sellerId,Integer pageNum,Integer pageSize){
		ModelAndView view = new ModelAndView("system/sysTask/listTask");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		Page<SysTask> page = sysTaskService.listSysTask(taskType, status, sellerId, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("taskType", taskType);
		view.addObject("status", status);
		view.addObject("sellerId", sellerId);
		return view;
	}
	
	@RequestMapping("/goWenjuan")
	public String goWenjuan(){
		return "system/sysTask/addTask_wenjuan";
	}
	@RequestMapping("/goPinlun")
	public String goPinlun(){
		return "system/sysTask/addTask_pinlun";
	}
	@RequestMapping("/goGuanzhu")
	public String goGuanzhu(){
		return "system/sysTask/addTask_guanzhu";
	}
	@RequestMapping("/modifyTask")
	public ModelAndView modifyTask(Long id){
		ModelAndView view = new ModelAndView();
		SysTask task = sysTaskService.getSysTask(id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		view.addObject("task", task);
		view.addObject("beginTime", sdf.format(task.getBeginTime()));
		view.addObject("endTime", sdf.format(task.getEndTime()));
		if (task.getTaskType() == 1) {
			view.setViewName("system/sysTask/updateTask_wenjuan");
		}else if(task.getTaskType() == 3){
			view.setViewName("system/sysTask/updateTask_pinlun");
		}else{
			view.setViewName("system/sysTask/updateTask_guanzhu");
		}
		return view;
	}
	
	@RequestMapping("/saveTask")
	public Object saveTask(SysTask task,String beginTime1,String endTime1){
		task.setCreateTime(new Date());
		task.setTaskFulfil(0);
		if (!StringUtils.isEmpty(beginTime1)) {
			task.setBeginTime(BaseUtil.toDate(beginTime1));
		}
		if (!StringUtils.isEmpty(endTime1)) {
			task.setEndTime(BaseUtil.toDate(endTime1));
		}
		if (task.getUnfulfilReward() == null) {
			task.setUnfulfilReward(new BigDecimal(0));
		}
		task.setStatus(1);
		int a = sysTaskService.insertSysTask(task);
		if (a>0) {
			return super.goUrl("/managermall/systemmall/sysTask/list.do?taskType="+task.getTaskType(), "添加成功");
		}
		return super.goUrl("/managermall/systemmall/sysTask/list.do?taskType="+task.getTaskType(), "添加失败");
	}
	
	@RequestMapping("/updateTask")
	public Object updateTask(SysTask task,String beginTime1,String endTime1){
		if (!StringUtils.isEmpty(beginTime1)) {
			task.setBeginTime(BaseUtil.toDate(beginTime1));
		}
		if (!StringUtils.isEmpty(endTime1)) {
			task.setEndTime(BaseUtil.toDate(endTime1));
		}
		if (task.getUnfulfilReward() == null) {
			task.setUnfulfilReward(new BigDecimal(0));
		}
		int a = sysTaskService.updateSysTask(task);
		if (a>0) {
			return super.goUrl("/managermall/systemmall/sysTask/list.do?taskType="+task.getTaskType(), "修改成功");
		}
		return super.goUrl("/managermall/systemmall/sysTask/list.do?taskType="+task.getTaskType(), "修改失败");
	}
	
	@RequestMapping("/getTask")
	public ModelAndView getTask(Long id){
		ModelAndView view = new ModelAndView();
		SysTask task = sysTaskService.getSysTask(id);
		view.addObject("task", task);
		if (task.getTaskType() == 1) {
			List<TaskQuestion> questions = taskQuestionService.listAllTaskQuestion(id);
			view.addObject("questions", questions);
			view.setViewName("system/sysTask/getTask_wenjuan");
		}else if(task.getTaskType() == 3){
			view.setViewName("system/sysTask/getTask_pinlun");
		}else{
			view.setViewName("system/sysTask/getTask_guanzhu");
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("changeStatus")
	public void changeStatus(HttpServletRequest request, HttpServletResponse response,Long[] ids ,Integer status){
		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			SysTask task = sysTaskService.getSysTask(id);
			if ((task.getStatus() == 1 || task.getStatus() == 3) && status == 2) {
				SysTask ti = new SysTask();
				ti.setId(id);
				ti.setStatus(2);
				sysTaskService.updateSysTask(ti);
			}else if(task.getStatus() == 2  && status == 3){
				SysTask ti = new SysTask();
				ti.setId(id);
				ti.setStatus(3);
				sysTaskService.updateSysTask(ti);
			}
		}
			HtmlUtil.writerJson(response, "success");
	}
}
