package cn.qhjys.mall.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.TaskAnswer;
import cn.qhjys.mall.entity.TaskQuestion;
import cn.qhjys.mall.service.system.SysTaskService;
import cn.qhjys.mall.service.system.TaskAnswerService;
import cn.qhjys.mall.service.system.TaskQuestionService;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/managermall/systemmall/taskQuestion")
public class TaskQuestionController extends Base{
	@Autowired
	private SysTaskService sysTaskService;
	
	@Autowired
	private TaskQuestionService taskQuestionService;
	
	@Autowired
	private TaskAnswerService taskAnswerService;
	
	@RequestMapping("/goAdd")
	public ModelAndView goAdd(Long id, Integer size){
		if (size == null) {
			size = 0;
		}
		ModelAndView view = new ModelAndView("system/sysTask/addTask_wenjuan_addtext");
		SysTask task = sysTaskService.getSysTask(id);
		view.addObject("task", task);
		view.addObject("questionNo", size+1);
		return view;
	}
	
	@RequestMapping("/saveQuestion")
	public Object saveQuestion(TaskQuestion question){
		int a = taskQuestionService.addTaskQuestion(question);
		if (a>0) {
			return super.goUrl("/managermall/systemmall/sysTask/getTask.do?id="+question.getTaskId(), "添加成功");
		}else{
			return super.goUrl("/managermall/systemmall/sysTask/getTask.do?id="+question.getTaskId(), "添加失败");
		}
	}
	@RequestMapping("/changeNo")
	public void changeNo(Long id1,Long id2,HttpServletRequest request, HttpServletResponse response){
		TaskQuestion question1 = taskQuestionService.getTaskQuestion(id1);
		TaskQuestion question2 = taskQuestionService.getTaskQuestion(id2);
		question1.setQuestionNo(question1.getQuestionNo()+1);
		question2.setQuestionNo(question2.getQuestionNo()-1);
		taskQuestionService.updateTaskQuestion(question1);
		taskQuestionService.updateTaskQuestion(question2);
		HtmlUtil.writerHtml(response, "success");
	}
	@RequestMapping("/deleteQuestion")
	public  Object deleteQuestion(Long id){
		TaskQuestion question = taskQuestionService.getTaskQuestion(id);
		List<TaskQuestion> questions = taskQuestionService.listTaskQuestionNo(question.getTaskId(),question.getQuestionNo());
		for (int i = 0; i < questions.size(); i++) {
			TaskQuestion question1 = questions.get(i);
			question1.setQuestionNo(question1.getQuestionNo()-1);
			taskQuestionService.updateTaskQuestion(question1);
		}
		taskQuestionService.deleteTaskQuestion(question.getId());
		return super.goUrl("/managermall/systemmall/sysTask/getTask.do?id="+question.getTaskId(), "删除成功");
	}
	
	@RequestMapping("/getQuestion")
	public ModelAndView getQuestion(Long id){
		ModelAndView view = new ModelAndView("system/sysTask/getTask_question");
		TaskQuestion question = taskQuestionService.getTaskQuestion(id);
		if (question.getQuestionType()== 1 && question.getAnswerType() == 0) {
			List<TaskAnswer> answers = taskAnswerService.listAllTaskAnswer(question.getId());
			view.addObject("answers", answers);
		}
		view.addObject("question", question);
		return view;
	}
}
