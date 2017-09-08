package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.Page;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.TaskExpand;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.system.TaskExpandService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/managermall/systemmall/task")
public class TaskController extends Base{
	
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private TaskExpandService taskExpandService;
	
	@RequestMapping("/listTask")
	public ModelAndView listTaskInfo(Integer taskType,String taskFrom,String taskName,
			String startTime,String endTime,Integer status,Integer pageNum,Integer pageSize) throws ParseException{
		ModelAndView view = new ModelAndView("system/task/listTask");
		if(pageNum == null){
			pageNum = 1;
		}
		if(pageSize == null){
			pageSize = 15;
		}
		Page<TaskInfo> tasks = taskInfoService.listTaskInfoByParam(taskType, taskName, taskFrom, startTime, endTime, status, pageNum, pageSize);
		view.addObject("tasks", tasks);
		view.addObject("taskType", taskType);
		view.addObject("taskFrom", taskFrom);
		view.addObject("taskName", taskName);
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		view.addObject("status", status);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}
	
	@RequestMapping("/addTask")
	public ModelAndView addTask(){
		ModelAndView view = new ModelAndView("system/task/addTask");
		return view;
	}
	@RequestMapping("/saveTask")
	public Object saveTask(TaskInfo task,String timage,String timage1,String beginTime1,String endTime1,String offShelf1){
		logger.info("----go saveTask--");
		logger.info("----task--"+task.getTaskDetail());
		task.setCreateTime(new Date());
		task.setTaskFulfil(new BigDecimal(0));
		if (!StringUtils.isEmpty(beginTime1)) {
			task.setBeginTime(BaseUtil.toDate(beginTime1));
		}
		if (!StringUtils.isEmpty(endTime1)) {
			task.setEndTime(BaseUtil.toDate(endTime1));
		}
		if (!StringUtils.isEmpty(offShelf1)) {
			task.setOffShelf(BaseUtil.toDate(offShelf1));
		}
		String projectUrl = task.getProjectUrl();
		if (projectUrl!=null &&projectUrl.length()>10&&projectUrl.contains("#userID#")) {
			task.setProjectUrl(projectUrl.replace("#userID#", ""));
		}
		if((task.getTaskType() == 5 || task.getTaskType() == 6) && projectUrl.contains("&userid=123456&wtid=1") ){
			task.setProjectUrl(projectUrl.replace("&userid=123456&wtid=1", "&wtid="+task.getProject()+"&userid="));
		}
		task.setImages(timage);
		task.setBgimg(timage1);
		task.setStatus(1);
		int a = taskInfoService.insertTaskInfo(task);
		if (a>0) {
			return super.goUrl("/managermall/systemmall/task/listTask.do", "添加成功");
			//return super.goUrl("/managermall/systemmall/task/addTaskExpand.do", "添加成功");
		}
		return super.goUrl("/managermall/systemmall/task/listTask.do", "添加失败");
	}
	
	@RequestMapping("/addTaskExpand")
	public ModelAndView addTaskExpand(
			@RequestParam(value="taskId",required = true) Long id
			){
		ModelAndView view = new ModelAndView("system/task/addTaskExpand");
		view.addObject("taskId",id);
		return view;
	}
	@RequestMapping("/saveTaskExpand")
	public Object saveTaskExpand(
			@RequestParam(value="taskId",required = true) Long taskId,
			@RequestParam(value="step",required = false) Integer step,
			@RequestParam(value="requirements",required = false) String requirements,
			@RequestParam(value="prize",required = false) String prize
			) throws Exception{
		TaskExpand taskExpand = new  TaskExpand();
		taskExpand.setTaskId(taskId);
		taskExpand.setStep(step);
		taskExpand.setRequirements(requirements);
		taskExpand.setPrize(prize);
		int inserTaskExpand = taskExpandService.saveTaskExpand(taskExpand);
		if(inserTaskExpand>0){
			return super.goUrl("/managermall/systemmall/task/getTask.do?id="+taskId, "添加成功");
		}else{
			return super.goUrl("/managermall/systemmall/task/getTask.do?id="+taskId,"添加失败");
		}
	}
	@RequestMapping("/toTaskExpand")
	public ModelAndView toTaskExpand (
			@RequestParam(value="id",required = true) Long id
			) throws Exception{
			TaskExpand taskExpandById = taskExpandService.getTaskExpandById(id);
			ModelAndView view = new ModelAndView("system/task/toTaskExpand");
			view.addObject("taskExpand",taskExpandById);
			return view;
	}
	@RequestMapping("/updateTaskExpand")
	public Object updateTaskExpand (
			@RequestParam(value="id",required = true) Long id,
			@RequestParam(value="taskId",required = true) Long taskId,
			@RequestParam(value="step",required = false) Integer step,
			@RequestParam(value="requirements",required = false) String requirements,
			@RequestParam(value="prize",required = false) String prize
			) throws Exception{
			TaskExpand taskExpandById = taskExpandService.getTaskExpandById(id);
			if(!StringUtils.isEmpty(step)){
				taskExpandById.setStep(step);
			}
			if(!StringUtils.isEmpty(requirements)){
				taskExpandById.setRequirements(requirements);		
			}
			if(!StringUtils.isEmpty(prize)){
				taskExpandById.setRequirements(prize);
			}
			int updateTaskExpand = taskExpandService.updateTaskExpand(taskExpandById);
			if(updateTaskExpand>0){
				return super.goUrl("/managermall/systemmall/task/getTask.do?id="+taskId, "修改成功");
			}else{
				return super.goUrl("/managermall/systemmall/task/getTask.do?id="+taskId,"修改失败");
			}
			
	}
	
	
	
	
	@RequestMapping("/getTask")
	public ModelAndView getTask(Long id) throws Exception{
		ModelAndView view = new ModelAndView("system/task/getTask");
		TaskInfo task = taskInfoService.getTask(id);
		String[] images = task.getImages().split("|");
		view.addObject("task", task);
		view.addObject("images", images);
		List<TaskExpand> listByTask = taskExpandService.getTaskExpandListByTaskId(id);
		view.addObject("listTaskE",listByTask);
		return view;
	}
	
	
	@RequestMapping("/updateTask")
	public ModelAndView updateTask(Long id ){
		ModelAndView view = new ModelAndView("system/task/updateTask");
		TaskInfo task = taskInfoService.getTask(id);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		view.addObject("task", task);
		view.addObject("beginTime", sdf.format(task.getBeginTime()));
		view.addObject("endTime", sdf.format(task.getEndTime()));
		view.addObject("offShelf", sdf.format(task.getOffShelf()));
		return view;
	}
	
	@RequestMapping("/modifyTask")
	public Object modifyTask(TaskInfo task,String timage,String timage1,String beginTime1,String endTime1,String offShelf1){
		logger.info("----go modifyTask--");
		logger.info("----task--"+task.getTaskDetail());
		if (!StringUtils.isEmpty(beginTime1)) {
			task.setBeginTime(BaseUtil.toDate(beginTime1));
		}
		if (!StringUtils.isEmpty(endTime1)) {
			task.setEndTime(BaseUtil.toDate(endTime1));
		}
		if (!StringUtils.isEmpty(offShelf1)) {
			task.setOffShelf(BaseUtil.toDate(offShelf1));
		}
		
		String projectUrl = task.getProjectUrl();
		if (projectUrl!=null &&projectUrl.length()>10&&projectUrl.contains("#userID#")) {
			task.setProjectUrl(projectUrl.replace("#userID#", ""));
		}
		if((task.getTaskType() == 5 || task.getTaskType() == 6) && projectUrl.contains("&userid=123456&wtid=1") ){
			task.setProjectUrl(projectUrl.replace("&userid=123456&wtid=1", "&wtid="+task.getProject()+"&userid="));
		}
		task.setImages(timage);
		task.setBgimg(timage1);
		int a = taskInfoService.updateTaskInfo(task);
		if (a>0) {
			return super.goUrl("/managermall/systemmall/task/listTask.do", "修改成功");
		}
		return super.goUrl("/managermall/systemmall/task/listTask.do", "修改失败");
	}
	
	@ResponseBody
	@RequestMapping("changeStatus")
	public void changeStatus(HttpServletRequest request, HttpServletResponse response,Long[] ids ,Integer status){
		List<TaskInfo> list = new ArrayList<TaskInfo>();
		for (int i = 0; i < ids.length; i++) {
			Long id = ids[i];
			TaskInfo task = taskInfoService.getTask(id);
			if ((task.getStatus() == 1 || task.getStatus() == 3) && status == 2) {
				TaskInfo ti = new TaskInfo();
				ti.setId(id);
				ti.setStatus(2);
				list.add(ti);
			}else if(task.getStatus() == 2  && status == 3){
				TaskInfo ti = new TaskInfo();
				ti.setId(id);
				ti.setStatus(3);
				list.add(ti);
			}
		}
		int a = taskInfoService.updateTaskInfos(list);
		if (a > 0) {
			HtmlUtil.writerJson(response, "success");
		}else{
			HtmlUtil.writerJson(response, "error");
		}
	}
	
	@RequestMapping("zhiding")
	public void zhiding(HttpServletRequest request, HttpServletResponse response,Long id){
		TaskInfo task = new TaskInfo();
		task.setId(id);
		task.setCreateTime(new Date());
		int a = taskInfoService.updateTaskInfo(task);
		if (a > 0) {
			HtmlUtil.writerJson(response, "success");
		}else{
			HtmlUtil.writerJson(response, "error");
		}
	}
	
}
