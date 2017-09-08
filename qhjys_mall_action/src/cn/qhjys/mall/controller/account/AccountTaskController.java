package cn.qhjys.mall.controller.account;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
















import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.UserExpand;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.service.AdService;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.HMACSHA256Util;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.HttpUtil;
import cn.qhjys.mall.vo.TaskVo;

@Controller
@RequestMapping("/account/task")
public class AccountTaskController extends Base{
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserInfoService userService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private AdService adService;
	
	@RequestMapping("/index")
	public ModelAndView  taskIndex(HttpSession session,Integer page,Integer pageSize,Integer taskType,Integer status){
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView();
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if (taskType ==null) {
			taskType = 11;
		}
		if (status ==null) {
			status = 0;
		}
		if (taskType == 11) {
			view.setViewName("account/task");
			Page<TaskInfo> tpage = taskInfoService.listTaskInfos(null, null,taskType, page, pageSize);
			view.addObject("page", tpage);
		}else{	
			if (status != 0 && user == null) {
				view.setViewName("account/login");
			}else{
				view.setViewName("account/task");
				if (status == 0) {
					if(user == null)
						user = new UserInfo();
					Page<TaskInfo> tpage = taskInfoService.listTaskInfos(user.getId(), user.getLevel(),taskType, page, pageSize);
					view.addObject("page", tpage);
				}
				if (status == 1) {
					String [] statuss = {"c"};
					Page<TaskVo> upage = taskInfoService.listTaskInfoByUser(user.getId(),statuss,taskType, page, pageSize);
					view.addObject("page", upage);
				}
				if (status == 2) {
					String [] statuss = {"cp"};
					Page<TaskVo> upage = taskInfoService.listTaskInfoByUser(user.getId(),statuss,taskType, page, pageSize);
					view.addObject("page", upage);
				}
				if (status == 3) {
					String [] statuss = {"s","q","cr"};
					Page<TaskVo> upage = taskInfoService.listTaskInfoByUser(user.getId(),statuss,taskType, page, pageSize);
					view.addObject("page", upage);
				}
			}
		}
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}
		List<AdInfo> ads1 = adService.listAdInfoByAp(6L,cityId, 0, 1);
		view.addObject("ad", ads1.size()>0?ads1.get(0):new AdInfo());
		view.addObject("taskType", taskType);
		view.addObject("status", status);
		return view;
	}
	@RequestMapping("/dotask")
	public ModelAndView getTaskInfo(HttpServletRequest request,HttpSession session,Long userId,String project,Integer ispc){
		ModelAndView view = new ModelAndView();
		TaskInfo task = taskInfoService.getTaskInfo(project);
		if (userId==null) {
			view.setViewName("account/login");
		}else{
			if (task.getTaskType() == 1) {
				if(ispc != null){
					view.setViewName("account/task/wenjuan_pc");
				}else{
					view.setViewName("account/task/wenjuan");
				}
			}else if(task.getTaskType() == 5 || task.getTaskType() == 6){
				if(ispc != null){
					view.setViewName("account/task/taskinfo_pc");
				}else{
					view.setViewName("account/task/taskinfo");
				}
			}else if(task.getTaskType() == 4){
				view.setViewName("redirect:"+task.getProjectUrl()+userId);
			}
			else if(task.getTaskType() == 7){
				task.setProjectUrl(task.getProjectUrl().replace("${uid}", userId+""));
				List<UserTask> uts = userTaskService.queryUserTask(userId, task.getId());
				view.setViewName("account/task/game");
				view.addObject("userTasks", uts);
			}else if (task.getTaskType() == 8){
				view.setViewName("account/task/game1");
			}else if(task.getTaskType() == 11){
				view.setViewName("account/task/renwuqiang_pc");
			}else{
				System.out.println(task.getProjectUrl());
				if (ispc != null) {
					view.setViewName("redirect:"+task.getProjectUrl()+"?ispc=1");
				}else{
					view.setViewName("redirect:"+task.getProjectUrl());
				}
				
			}
			view.addObject("task", task);
			view.addObject("userId", userId);
			if (userId != null) {
				UserTask ut = userTaskService.getUserTask(userId, task.getId());
				view.addObject("userTask", ut);
			}
		}
		return view;
	}
	@RequestMapping("/dousertask")
	public void dousertask(HttpServletRequest request,HttpServletResponse response,HttpSession session,Long userId,String project,String inputinfo) throws Exception{
		TaskInfo task = taskInfoService.getTaskInfo(project);
		UserTask ut = userTaskService.getUserTask(userId, task.getId());
		if (ut == null) {
		 ut = new UserTask();
		 ut.setCreateTime(new Date());
		 ut.setStatus("c");
		 ut.setTaskId(task.getId());
		 ut.setGameLevel(1);
		 ut.setTotamt(new BigDecimal(0));
		 ut.setUserId(userId);
			String infourl = task.getInfoUrl();
			if (infourl.length() >10) {
				infourl = infourl.replace("userid=123456", "userid="+userId);
				infourl = infourl.replace("info=15812341234&ip=127.0.0.1", "ip="+getIp(request)+"&info=");
				String b = new String(HttpUtil.doSend(infourl+inputinfo, "GET", null));
				JSONObject json = JSON.parseObject(b);
				if (json.getInteger("status") == 0) {
					int a = userTaskService.insertUserTask(ut);
					HtmlUtil.writerJson(response, "success");
				}else{
					HtmlUtil.writerJson(response, json.getInteger("status"));
				}
			}else{
				int a = userTaskService.insertUserTask(ut);
				HtmlUtil.writerJson(response, "success");
			}
		}else{
				HtmlUtil.writerJson(response, "success");
		}
	}
	@RequestMapping("/mytask")
	public ModelAndView  mytask(HttpSession session,Integer page,Integer pageSize,Integer mstatus) throws Exception{
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView();
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		if(mstatus == null){
			mstatus = 11;
		}
		if (user == null) {
			view.setViewName("account/login");
		}else{
			Page<TaskVo> upage = null;
			String [] status = {"cp"};
			String [] status1 = {"c"};
			String [] status2 = {"s","q","cr"};
			Integer [] status10 = {1};
			Integer [] status11 = {0};
			Integer [] status12 = {2,3};
			view.setViewName("account/task/mytask");
			if(mstatus == 11){
				 upage = taskInfoService.listTaskAll(user.getId(),status,status10, page, pageSize);
			}else if(mstatus == 12){
				 upage = taskInfoService.listTaskAll(user.getId(),status1,status11, page, pageSize);
			}else if(mstatus == 13){
				 upage = taskInfoService.listTaskAll(user.getId(),status2,status12, page, pageSize);
			}
			UserExpand ue = userService.getUserExpandByUserId(user.getId());
			int a = 0;
			if(ue!=null){
				if(ue.getSex() != null && ue.getSex() != 0)
					a +=1;
				if(ue.getBirthday() != null)
					a +=1;
				if(ue.getIdentity() != null)
					a +=1;
				if(ue.getMaritalStatus() != null)
					a +=1;
				if(ue.getInterest() != null)
					a +=1;
				if(ue.getInterest() != null)
					a +=1;
			}
			view.addObject("jindu", a);
			view.addObject("upage", upage);
			view.addObject("mstatus", mstatus);
			Long cityId = (Long) session.getAttribute("cityId");
			if (cityId == null) {
				cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
				session.setAttribute("cityId", cityId);
			}
			List<AdInfo> ads1 = adService.listAdInfoByAp(7L, cityId,0, 1);
			view.addObject("ad", ads1.size()>0?ads1.get(0):new AdInfo());
		}
		return view;
	}
	
	@RequestMapping("/uright")
	public ModelAndView  uright(HttpSession session,Integer page,Integer pageSize) throws Exception{
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView();
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 5;
		}
		if (user == null) {
			view.setViewName("account/login");
		}else{
			view.setViewName("account/uright");
			List<TaskVo> newlist = taskInfoService.selectTaskInfoByNewUser(user.getId());
			List<ProductInfo> plist = productService.searchProductListByRand(cityId);
			view.addObject("newlist", newlist);
			view.addObject("plist", plist);
			view.addObject("page", page);
		}
		return view;
	}
	
	public String getIp(HttpServletRequest request){
		 String ip = request.getHeader("x-forwarded-for");
		    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("Proxy-Client-IP");
		    }
		    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
		        ip = request.getHeader("WL-Proxy-Client-IP");
		    }
		    if(ip == null || ip.length() == 0 ||"unknown".equalsIgnoreCase(ip)) {
		        ip = request.getRemoteAddr();
		    }
		    return ip;
	}
	
	@RequestMapping("/refresh")
	public void refresh(HttpServletRequest request,HttpServletResponse response,Long userId,String project,Long taskId) throws IOException{
		request.setCharacterEncoding("UTF-8");
		String url = "http://api.ainiwan.com/api/otherquery";
		String key = "b3e0ae2973";
		long time = System.currentTimeMillis()/1000L;
		Long uid = userId;
		int cid = 204;
		int gameid = Integer.valueOf(project);
		Map<String, String> params = new HashMap<String, String>();
		params.put("key", key);
		params.put("time",time+"");
		params.put("uid", uid+"");
		params.put("cid", cid+"");
		params.put("gameid", gameid+"");
		params.put("sign", EncodeMD5.GetMD5Code(cid+""+time+""+uid+""+gameid+""+key));
		String a = new String(HttpUtil.doSend(url, "GET", params),"UTF8");
		System.out.println(a);
		JSONObject json = JSON.parseObject(a);
		if (json.getBoolean("success")) {
			JSONObject data = json.getJSONArray("date").getJSONObject(0);
			String gamename = data.getString("username");
			Integer gamelevel = data.getInteger("level");
			Integer money = data.getInteger("money");
			UserTask ut = userTaskService.getUserTask(userId, taskId, "c");
			int amoney = userTaskService.countMoney(userId, taskId);
			if (ut != null) {
				ut.setGameLevel(gamelevel);
				ut.setGameName(gamename);
				ut.setCreateTime(new Date());
				ut.setMoney(money-amoney);
				ut.setTotamt(new BigDecimal((money-amoney)*0.16*20));
				userTaskService.updateUserTask(ut);
				HtmlUtil.writerJson(response, "success");
			}else{
				ut = new UserTask();
				ut.setGameLevel(gamelevel);
				ut.setGameName(gamename);
				ut.setCreateTime(new Date());
				ut.setMoney(money-amoney);
				ut.setTotamt(new BigDecimal((money-amoney)*0.3*20));
				ut.setStatus("c");
				ut.setTaskId(taskId);
				ut.setUserId(userId);
				userTaskService.insertUserTaskNotChange(ut);
				HtmlUtil.writerJson(response, "success");
			}
		}else{
			if (json.getJSONArray("date")==null) {
				HtmlUtil.writerJson(response, "noinfo");
			}
			HtmlUtil.writerJson(response, "error");
		}
	}
	@RequestMapping("/changec")
	public void changec(HttpServletRequest request,HttpServletResponse response,Long utid) throws Exception{
		UserTask ut = userTaskService.getUserTask(utid);
		if (ut.getStatus().equals("c")) {
			ut.setStatus("cp");
			userTaskService.updateUserTaskByCp(ut);
			HtmlUtil.writerJson(response, "success");
		}else{
			HtmlUtil.writerJson(response, "alread");
		}
		HtmlUtil.writerJson(response, "error");
	}
	@RequestMapping("/shopping")
	public ModelAndView  game1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView view = new ModelAndView("account/task/shopping_pc");
		TaskInfo ti = taskInfoService.getTaskInfo("tuijieke");
		view.addObject("task", ti);
		return view;
	}
	
	@RequestMapping("/taskwall")
	public ModelAndView  wen(HttpServletRequest request,HttpServletResponse response,Long userId,String project) throws Exception{
		ModelAndView view = new ModelAndView("account/task/survey");
		if (project.equals("91wenjuan")) {
			Long time = System.currentTimeMillis()/1000;
			view.addObject("time", time);
			view.addObject("app_id", 99);
			view.addObject("app_mid", userId);
			String sig = HMACSHA256Util.HMACSHA256("app_id=99&app_mid="+userId+"&time="+time, "1448419707-0693385fb409cf432100004a074eb5e4f8f3a7d8");
			view.addObject("sig", sig);
		}else if(project.equals("yiruite")){
			view.setViewName("redirect:http://app.offer99.com/index.php?pid=i4333490d060a50cbf9f2ea4dc46308c&alloffers=1&userid="+userId);
		}else if(project.equals("guote")){
			view.setViewName("redirect:http://www.guotelm.com/task/gamelist?coop_id=1396697&ukey="+userId);
		}
		return view;
	}
	
	@RequestMapping("/doNewUserTask")
	public ModelAndView doNewUserTask(HttpSession session,Long userId,Integer ispc) throws Exception{
		ModelAndView view = new ModelAndView("account/task/selleruser11");
		if (ispc != null) {
			view = new ModelAndView("account/task/selleruser1");
		}
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
				view.setViewName("account/login");
				return view;
			}
		}
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserExpand expand = userService.getUserExpandByUserId(user.getId());
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		UserTask ut1 = userTaskService.getUserTask(user.getId(), ti.getId());
		if (ut1 != null) {
			view.setViewName("account/task/selleruser0");
			return view;
		}
		if (ut != null) {
			int level = ut.getGameLevel();
			switch (level){
				case 1:
					if (ispc != null) {
						view.setViewName("account/task/selleruser2");
					}else{
						view.setViewName("account/task/selleruser12");
					}
					break;
				case 2:
					if (ispc != null) {
						view.setViewName("account/task/selleruser3");
					}else{
						view.setViewName("account/task/selleruser13");
					}
					break;
				case 3:
					if (ispc != null) {
						view.setViewName("account/task/selleruser4");
					}else{
						view.setViewName("account/task/selleruser14");
					}
					break;
				case 4:
					if (ispc != null) {
						view.setViewName("account/task/selleruser5");
					}else{
						view.setViewName("account/task/selleruser15");
					}
					break;
				case 5:
					if (ispc != null) {
						view.setViewName("account/task/selleruser6");
					}else{
						session.setAttribute(user_key,user);
						view.setViewName("account/task/selleruser16");
					}
					break;
				case 6:
					if (ispc != null) {
						view.setViewName("account/task/selleruser0");
					}else{
						view.setViewName("account/task/selleruser10");	
					}
					break;
			}
		}
		view.addObject("ispc", ispc);
		view.addObject("expand", expand);
		view.addObject("user",user);
		return view;
	}
	
	@RequestMapping("/selleruser1")
	public String selleruser1(HttpSession session,String realname,String  brithday,String nickname,Long userId,Integer ispc) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
			return "account/login";
			}
		}
		user.setRealname(realname);
		user.setNickname(nickname);
		userService.updateUserById(user);
		UserExpand expand = new UserExpand();
		expand.setUserId(user.getId());
		expand.setBirthday(BaseUtil.strToDate(brithday));
		userService.insertUserExpand(expand);
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		if (ut == null) {
			ut = new UserTask();
			ut.setCreateTime(new Date());
			ut.setGameLevel(1);
			ut.setStatus("c");
			ut.setTaskId(ti.getId());
			ut.setTotamt(ti.getFulfilReward());
			ut.setUserId(user.getId()+1000000000);
			userTaskService.insertUserTaskNotChange(ut);
		}else{
			ut.setGameLevel(1);
			userTaskService.updateUserTask(ut);
		}
		if (ispc == null ) {
			return "redirect:/account/task/doNewUserTask.do?userId="+userId; 
		}else{
			return "redirect:/account/task/doNewUserTask.do?userId="+userId+"&ispc="+ispc; 
		}
	}
	
	@RequestMapping("/selleruser2")
	public String selleruser2(HttpSession session,String phoneNum,String email,Long userId,Integer ispc) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
			return "account/login";
			}
		}
		user.setPhoneNum(phoneNum);
		user.setEmail(email);
		userService.updateUserById(user);
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		ut.setGameLevel(2);
		userTaskService.updateUserTask(ut);
		if (ispc == null ) {
			return "redirect:/account/task/doNewUserTask.do?userId="+userId; 
		}else{
			return "redirect:/account/task/doNewUserTask.do?userId="+userId+"&ispc="+ispc; 
		}
	}
	@RequestMapping("/selleruser3")
	public String selleruser3(HttpSession session,Integer identity,Integer marital,Long userId,Integer ispc) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
			return "account/login";
			}
		}
		UserExpand expand = userService.getUserExpandByUserId(user.getId());
		expand.setIdentity(identity);
		expand.setMaritalStatus(marital);
		userService.updateUserExpandSelective(expand);
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		ut.setGameLevel(3);
		userTaskService.updateUserTask(ut);
		if (ispc == null ) {
			return "redirect:/account/task/doNewUserTask.do?userId="+userId; 
		}else{
			return "redirect:/account/task/doNewUserTask.do?userId="+userId+"&ispc="+ispc; 
		}
	}
	@RequestMapping("/selleruser4")
	public String selleruser4(HttpSession session,Integer[] interest,Long userId,Integer ispc) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
			return "account/login";
			}
		}
		String interests ="";
		for (int i = 0; i < interest.length; i++) {
			interests+=interest[i]+",";
		}
		UserExpand expand = userService.getUserExpandByUserId(user.getId());
		expand.setInterest(interests.substring(0, interests.length()-1));
		userService.updateUserExpandSelective(expand);
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		ut.setGameLevel(4);
		userTaskService.updateUserTask(ut);
		if (ispc == null ) {
			return "redirect:/account/task/doNewUserTask.do?userId="+userId; 
		}else{
			return "redirect:/account/task/doNewUserTask.do?userId="+userId+"&ispc="+ispc; 
		}
	}
	@RequestMapping("/selleruser5")
	public String selleruser5(HttpSession session,Long licenseProvince,Long licenseCity,Long licenseArea,Long userId,Integer ispc) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
			return "account/login";
			}
		}
		UserExpand expand = userService.getUserExpandByUserId(user.getId());
		expand.setProvince(licenseProvince);
		expand.setCity(licenseCity);
		expand.setArea(licenseArea);
		userService.updateUserExpandSelective(expand);
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		ut.setGameLevel(5);
		if (ispc != null) {
			ut.setGameName("1");
		}
		userTaskService.updateUserTask(ut);
		if (ispc == null ) {
			return "redirect:/account/task/doNewUserTask.do?userId="+userId; 
		}else{
			return "redirect:/account/task/doNewUserTask.do?userId="+userId+"&ispc="+ispc; 
		}
	}
	@RequestMapping("/selleruser6")
	public String selleruser6(HttpSession session,Long userId,Integer ispc) throws Exception{
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null) {
			user = userService.selectUserById(userId);
			if (user == null) {
			return "account/login";
			}
		}
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
		ut.setGameLevel(6);
		ut.setUserId(user.getId());
		ut.setStatus("c");
		userTaskService.updateUserTask(ut);
		if (ispc == null ) {
			return "redirect:/account/task/doNewUserTask.do?userId="+userId; 
		}else{
			return "redirect:/account/task/doNewUserTask.do?userId="+userId+"&ispc="+ispc; 
		}
	}
}
