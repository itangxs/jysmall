package cn.qhjys.mall.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.TaskAnswer;
import cn.qhjys.mall.service.system.TaskAnswerService;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/managermall/systemmall/taskAnswer")
public class TaskAnswerController extends Base{
	
	@Autowired
	private TaskAnswerService taskAnswerService;
	
	@RequestMapping("goAdd")
	public ModelAndView goAdd(Long questionId){
		ModelAndView view = new ModelAndView("system/sysTask/addAnswer");
		view.addObject("questionId", questionId);
		return view;
	}
	
	@RequestMapping("saveAnswer")
	public Object saveAnswer(TaskAnswer answer,String image){
		if (answer.getAnswerType() == 1) {
			answer.setAnswerContent(image);
		}
		int a = taskAnswerService.insertTaskAnswer(answer);
		if (a > 0) {
			return this.goUrl("/managermall/systemmall/taskQuestion/getQuestion.do?id="+answer.getQyestionId(), "添加成功");
		}
		return this.goUrl("/managermall/systemmall/taskQuestion/getQuestion.do?id="+answer.getQyestionId(), "添加失败");
	}
	
	@RequestMapping("deleteAnswer")
	public void deleteAnswer(Long id,HttpServletRequest request, HttpServletResponse response){
		int a = taskAnswerService.deleteTaskAnswer(id);
		if (a > 0) {
			HtmlUtil.writerJson(response, "success");
		}else{
			HtmlUtil.writerJson(response, "error");
		}
	}
	
	@RequestMapping("changeCorrect")
	public void changeCorrect(Long id,HttpServletRequest request, HttpServletResponse response){
		TaskAnswer answer = taskAnswerService.getTaskAnswer(id);
		if (answer.getIsCorrect() == 0) {
			answer.setIsCorrect(1);
		}else{
			answer.setIsCorrect(0);
		}
		int a = taskAnswerService.updateTaskAnswer(answer);
		if (a > 0) {
			HtmlUtil.writerJson(response, "success");
		}else{
			HtmlUtil.writerJson(response, "error");
		}
	}
}
