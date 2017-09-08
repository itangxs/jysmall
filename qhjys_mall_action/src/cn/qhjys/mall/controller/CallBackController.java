package cn.qhjys.mall.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/callback")
public class CallBackController extends Base{
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private TaskInfoService taskInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	private String A_KEY="dbf7b54f7128b4eb11c45ae2dd377f32";
	private String W_KEY="jysmall";
	
	private String T_KEY="jysmall";
	
	private String Y_KEY="jysmall";
	
	private String G_KEY="jysmall";

	private String Q_KEY="jysmall";
	
	@RequestMapping("/qianlaiwang")
	public void qianlaiwang(HttpServletResponse response, HttpServletRequest request,Long uid,Integer coin,String order_id,Long time,String sign) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		JSONObject json = new JSONObject();
		logger.info("-----uid--------"+uid);
		logger.info("--------coin-----"+coin);
		logger.info("----------order_id---"+order_id);
		logger.info("----------time---"+time);
		logger.info("-------sign------"+sign);
		TaskInfo ti = taskInfoService.getTaskInfo("qianlaiwang");
		if (ti != null) {
			UserInfo ui = userInfoService.selectUserById(uid);
			if(ui != null){
				UserTask ut = userTaskService.getUserTask(order_id);
				if (ut == null) {
					String sign1 = EncodeMD5.GetMD5Code(uid+""+coin+order_id+Q_KEY);
					if (sign1.equals(sign)) {
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setStatus("cp");
						ut.setTaskId(ti.getId());
						ut.setTotamt(new BigDecimal(coin));
						ut.setUserId(uid);
						ut.setGameName(order_id);
						 int a =userTaskService.insertUserTask(ut);
						json.put("status", "success");
						json.put("errno", 0);
					}else{
						json.put("status", "failure");
						json.put("errno", 1002);
					}
				}else {
					json.put("status", "failure");
					json.put("errno", 1003);
				}
			}else{
				json.put("status", "failure");
				json.put("errno", 1005);
			}
		}else{
			json.put("status", "failure");
			json.put("errno", 1007);
		}
		logger.info("-----json---qianlaiwang-----"+json.toJSONString());
		HtmlUtil.writerJson(response, json);
		
	}
	@RequestMapping("/aidiaoyan")
	public void aidiaoyan(String userid,String project,String	status,String vcode) throws Exception{
		
		logger.info("-----userid--------"+userid);
		logger.info("--------status-----"+status);
		logger.info("----------project---"+project);
		logger.info("-------ycode------"+vcode);
		TaskInfo ti = taskInfoService.getTaskInfo(project);
		if (ti != null) {
			UserInfo ui = userInfoService.selectUserById(Long.parseLong(userid));
			if(ui != null){
				UserTask ut = userTaskService.getUserTask(Long.parseLong(userid), ti.getId());
				if (ut == null) {
					String sign = EncodeMD5.GetMD5Code(userid+project+status+A_KEY);
					if (sign.equals(vcode)) {
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setStatus(status);
						ut.setTaskId(ti.getId());
						if (status.equals("c")) {
							ut.setTotamt(new BigDecimal(0));
						}else{
							if (null == ti.getUnfulfilReward()) {
								ut.setTotamt(new BigDecimal(0));
							}else{
								ut.setTotamt(ti.getUnfulfilReward());
							}
						}
						ut.setUserId(Long.parseLong(userid));
						int a =userTaskService.insertUserTask(ut);
					}
				}else if(ut.getStatus().equals("c")){
					if (status == "cp") {
						ut.setStatus(status);
						ut.setTotamt(ti.getFulfilReward());
						userTaskService.updateUserTaskByCp(ut);
					}else if(status == "cr"){
						ut.setStatus(status);
						ut.setTotamt(ti.getUnfulfilReward());
						userTaskService.updateUserTaskByCp(ut);
					}
				}
//				int a = userTaskService.countUserTask(Long.parseLong(userid));
//				if(a == 1){
//					taskInfoService.insertNewUserTask(Long.parseLong(userid), NewTaskCode.N003);
//				}
			}
		}
	}
	@RequestMapping("/wamila")
	public void wamila(HttpServletResponse response, HttpServletRequest request,Long userid,String tradid,String wtid,Integer step,
			Integer gold,Integer time,String sign) throws Exception{
		JSONObject json = new JSONObject();
		TaskInfo ti = taskInfoService.getTaskInfo(wtid);
		if (ti != null) {
			UserInfo ui = userInfoService.selectUserById(userid);
			if(ui != null){
				UserTask ut = userTaskService.getUserTask(userid, ti.getId(),step);
				if (ut == null) {
					String vcode = EncodeMD5.GetMD5Code(W_KEY+userid+tradid+wtid+step);
					logger.info("-----vcode--------"+vcode);
					if (sign.equalsIgnoreCase(vcode)) {
						ut = new UserTask();
						ut.setCreateTime(new Date());
						
						ut.setStatus("cp");
						ut.setTaskId(ti.getId());
						ut.setGameLevel(step);
						ut.setTotamt(new BigDecimal(gold));
						ut.setUserId(userid);
						userTaskService.insertUserTask(ut);
						json.put("status", "success");
						json.put("errno", 0);
					}else{
						json.put("status", "failure");
						json.put("errno", 1002);
					}
				}else{
					if (ut.getStatus().equals("c")) {
						ut.setStatus("cp");
						ut.setTotamt(new BigDecimal(gold));
						userTaskService.updateUserTaskByCp(ut);
						json.put("status", "success");
						json.put("errno", 0);
					}else{
					json.put("status", "failure");
					json.put("errno", 1003);
					}
				}
			}else{
				json.put("status", "failure");
				json.put("errno", 1004);
			}
		}else{
			json.put("status", "failure");
			json.put("errno", 1005);
		}
		logger.info("-----json---wamila-----"+json.toJSONString());
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/wamilafailure")
	public void wamilafailure(HttpServletResponse response, HttpServletRequest request,Long userid,String tradid,String wtid,
			Integer time,String sign) throws Exception{

		JSONObject json = new JSONObject();
		TaskInfo ti = taskInfoService.getTaskInfo(wtid);
		if (ti != null) {
			UserInfo ui = userInfoService.selectUserById(userid);
			if(ui != null){
				UserTask ut = userTaskService.getUserTask(userid, ti.getId());
				if (ut == null) {
					String vcode = EncodeMD5.GetMD5Code(W_KEY+userid+tradid+wtid);
					if (sign.equalsIgnoreCase(vcode)) {
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setStatus("cr");
						ut.setTaskId(ti.getId());
						ut.setTotamt(ti.getUnfulfilReward());
						ut.setUserId(userid);
						userTaskService.insertUserTask(ut);
						json.put("status", "success");
						json.put("errno", 0);
					}else{
						json.put("status", "failure");
						json.put("errno", 1002);
					}
				}else{
					if (ut.getStatus().equals("c")) {
					ut.setStatus("cr");
					userTaskService.updateUserTask(ut);
					json.put("status", "success");
					json.put("errno", 0);
					}else{
					json.put("status", "failure");
					json.put("errno", 1003);
					}
				}
			}else{
				json.put("status", "failure");
				json.put("errno", 1004);
			}
		}else{
			json.put("status", "failure");
			json.put("errno", 1005);
		}

		logger.info("-----json---- wamilafailure----"+json.toJSONString());
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/wamilarwq")
	public void wamilarwq(HttpServletResponse response, HttpServletRequest request,Long userid,String tid,String tname,String  status,
			Integer gold,Integer time,String sign) throws Exception{
		JSONObject json = new JSONObject();
		json.put("userid", userid);
		json.put("tid", tid);
		json.put("tname", tname);
		json.put("gold", gold);
		json.put("time", time);
		json.put("status", "failure");
		json.put("errno", 1003);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/tuijieke")
	public void tuijieke(HttpServletResponse response, HttpServletRequest request,Long uid,Integer coin,String order_id,String sign) throws Exception{
		JSONObject json = new JSONObject();
		json.put("uid", uid);
		json.put("coin",coin );
		json.put("order_id", order_id);
		TaskInfo ti = taskInfoService.getTaskInfo("tuijieke");
		if (ti != null) {
			UserInfo ui = userInfoService.selectUserById(uid);
			if (ui != null) {
				String vcode = EncodeMD5.GetMD5Code(""+uid+coin+order_id+T_KEY);
				if (vcode.equals(sign)) {
					UserTask ut = userTaskService.getUserTask(order_id);
					if (ut == null) {
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setGameName(order_id);
						ut.setStatus("cp");
						ut.setTotamt(new BigDecimal(coin));
						ut.setUserId(uid);
						ut.setTaskId(ti.getId());
						userTaskService.insertUserTask(ut);
						json.put("status", "success");
						json.put("errno", "");
					}else{
						json.put("status", "failure");
						json.put("errno", "1003");
					}
				}else{
					json.put("status", "failure");
					json.put("errno", "1002");
				}
			}else{
				json.put("status", "failure");
				json.put("errno", "1007");
			}
		}else{
			json.put("status", "failure");
			json.put("errno", "1005");
		}
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/ninetyoneProfile")
	public void Profile(HttpServletResponse response, HttpServletRequest request,String app_mid,Integer app_id,
			String hash,String name,Integer time,String sig) throws Exception{
//		JSONObject json1 = new JSONObject();
//		json1.put("app_mid", app_mid);
//		json1.put("app_id", app_id);
//		json1.put("hash", hash);
//		json1.put("name", name);
//		json1.put("time", time);
//		json1.put("sig", sig);
//		System.out.println("ninetyoneProfile--json1--"+json1);
		String a = "{\"meta\": {\"code\": 200}}";
		JSONObject json = JSON.parseObject(a);
		HtmlUtil.writerJson(response, json);
	}
	
	
	@RequestMapping("/ninetyone")
	public void ninetyone(HttpServletResponse response, HttpServletRequest request,String app_mid,
			Integer survey_id,Float cpi) throws Exception{
		JSONObject json1 = new JSONObject();
		json1.put("app_mid", app_mid);
		json1.put("survey_id", survey_id);
		json1.put("cpi", cpi);
//		System.out.println("ninetyone--json1--"+json1);
//		TaskInfo ti = taskInfoService.getTaskInfo("91wenjuan");
//		if (ti != null) {
//			UserInfo ui = userInfoService.selectUserById(app_mid);
//			if (ui != null) {
//				UserTask ut = userTaskService.getUserTask(app_mid, ti.getId(),survey_id);
//				if (ut == null) {
//					ut = new UserTask();
//					ut.setCreateTime(new Date());
//					ut.setGameLevel(survey_id);
//					ut.setStatus("cp");
//					ut.setTaskId(ti.getId());
//					Float a = cpi * 96;
//					ut.setTotamt(new BigDecimal(a.intValue()));
//					ut.setUserId(app_mid);
//					userTaskService.insertUserTask(ut);
//				}
//			}else{
//				logger.info("用户不存在");
//			}
//		}
		String a = "{\"meta\": {\"code\": 200}}";
		JSONObject json = JSON.parseObject(a);
		HtmlUtil.writerJson(response, json);
	}
	@RequestMapping("/yiruite")	
	public void yiruite(HttpServletResponse response, HttpServletRequest request,Long uid,Integer vcpoints,String tid,String offer_name,String pass) throws Exception{
		JSONObject json = new JSONObject();
		json.put("uid", uid);
		json.put("vcpoints",vcpoints );
		json.put("tid", tid);
		json.put("offer_name", offer_name);
		String sign = EncodeMD5.GetMD5Code(""+uid+vcpoints+tid+Y_KEY);
		if (sign.equals(pass)) {
			UserInfo ui = userInfoService.selectUserById(uid);
			if (ui != null) {
				if (tid.length() != 32) {
					json.put("status", "failure");
					json.put("errno", 1001);
				}else{
					UserTask ut = userTaskService.getUserTask(tid);
					if (ut == null) {
						TaskInfo ti = taskInfoService.getTaskInfo("yiruite");
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setGameName(tid);
						ut.setStatus("cp");
						ut.setTotamt(new BigDecimal(vcpoints));
						ut.setUserId(uid);
						ut.setTaskId(ti.getId());
						userTaskService.insertUserTask(ut);
						json.put("status", "success");
					}else{
						json.put("status", "failure");
						json.put("errno", 1003);
					}
				}
			}else{
				json.put("status", "failure");
				json.put("errno", 1005);
			}
		}else{
			json.put("status", "failure");
			json.put("errno", 1002);
		}
		HtmlUtil.writerJson(response, json);
	}
	@RequestMapping("/guote")	
	public void guote(HttpServletResponse response, HttpServletRequest request,Long uid,Integer coin,String order_id,String gamename,String sign) throws Exception{
		JSONObject json = new JSONObject();
		json.put("uid", uid);
		json.put("coin",coin );
		json.put("order", order_id);
		String vcode = EncodeMD5.GetMD5Code(""+uid+coin+order_id+G_KEY);
		if (sign.equals(vcode)) {
			UserInfo ui = userInfoService.selectUserById(uid);
			if (ui != null) {
					UserTask ut = userTaskService.getUserTask(order_id);
					if (ut == null) {
						TaskInfo ti = taskInfoService.getTaskInfo("guote");
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setGameName(order_id);
						ut.setStatus("cp");
						ut.setTotamt(new BigDecimal(coin));
						ut.setUserId(uid);
						ut.setTaskId(ti.getId());
						userTaskService.insertUserTask(ut);
						json.put("status", "success");
					}else{
						json.put("status", "failure");
						json.put("errno", 1003);
					}
			}else{
				json.put("status", "failure");
				json.put("errno", 1005);
			}
		}else{
			json.put("status", "failure");
			json.put("errno", 1002);
		}
		HtmlUtil.writerJson(response, json);
	}
}
