package cn.qhjys.mall.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SHA256Util;

import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/callback")
public class AppSdkCallbackController extends Base{
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private UserInfoService userInfoService;
	
	private String DR_KEY="jysmall";
	
	private String BD_KEY="jysmall";
	
	private String DTN_KEY="jysmall";
	
	private String DC_KEY="jysmall";
	
	private String DL_KEY="jysmall";
	
	private String YM_KEY="c83f011a7a2134bc";
	
	private String JY_KEY="jysmall";
	
	@RequestMapping("/jiongyou")
	public void jiongyou(String appid,String adname,String orderid,Long user,
			BigDecimal points,String sig,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		String json = "1";
		String parameter = JY_KEY + "||" + orderid + "||" + appid + "||" + user + "||" + URLEncoder.encode(adname,"UTF-8") + "||" + points; 
		logger.info("-------parameter--"+parameter);
		String vcode= EncodeMD5.GetMD5CodeUTF8(parameter).substring(12, 20);;
		logger.info("-------vcode------"+vcode);
		logger.info("-------sig--------"+sig);
		if (sig.equalsIgnoreCase(vcode)) {
			UserInfo ui = userInfoService.selectUserById(user);
			if (ui != null) {
					UserTask ut = userTaskService.getUserTask(orderid);
					if (ut == null) {
						TaskInfo ti = taskInfoService.getTaskInfoNoStatus("jiongyou");
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setGameName(orderid);
						ut.setStatus("cp");
						ut.setTotamt(points);
						ut.setUserId(user);
						ut.setTaskId(ti.getId());
						userTaskService.insertUserTask(ut);
					}else{
						json=  "任务记录已存在";
					}
			}else{
				json = "用户不存在";
			}
		}else{
			json = "签名验证失败";
		}
		if (!json.equals("1")) {
			response.setStatus(200);
		}
		logger.info("------jiongyou-json----------"+json);
		HtmlUtil.writerJson(response, json);
	}
	@RequestMapping("/youmi")
	public void youmi(String app,String ad,String clientParams,String order,Long user,Integer chn,
			BigDecimal points,Integer trade_type,String sig,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		String json = "200";
		String parameter = YM_KEY + "||" + order + "||" + app + "||" + user + "||" + chn + "||" + URLDecoder.decode(ad,"UTF-8") + "||" + points;
		String vcode= EncodeMD5.GetMD5CodeUTF8(parameter).substring(12, 20);;
		logger.info("-------parameter-----app-"+app+"-ad-"+ad+"-clientParams-"+clientParams+"-order-"+order+"-user-"+user
				+"-chn-"+chn+"-trade_type-"+trade_type+"-sig-"+sig);
		logger.info("-------vcode------"+vcode);
		logger.info("-------sig--------"+sig);
		if (sig.equalsIgnoreCase(vcode)) {
			UserInfo ui = userInfoService.selectUserById(user);
			if (ui != null) {
				UserTask ut = userTaskService.getUserTask(order);
				if (ut == null) {
					TaskInfo ti = taskInfoService.getTaskInfoNoStatus("youmi");
					ut = new UserTask();
					ut.setCreateTime(new Date());
					ut.setGameName(order);
					ut.setStatus("cp");
					ut.setTotamt(points);
					ut.setUserId(user);
					ut.setTaskId(ti.getId());
					userTaskService.insertUserTask(ut);
				}else{
					json= "任务记录已存在";
				}
			}else{
				json = "用户不存在";
			}
		}else{
			json = "签名验证失败";
		}
		logger.info("------youmi-json----------"+json);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/dianle")
	public void dianle(Long time_stamp,String device_id,String app_id,String pack_name,Long snuid,Double app_ratio,
			String task_id,BigDecimal currency,Integer trade_type,String token,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		String json = "200";
		String vcode= EncodeMD5.GetMD5Code(time_stamp+DL_KEY);
		logger.info("-------parameter-----timestamp-"+time_stamp+"-deviceId-"+device_id+"-appId-"+app_id+"-pack_name-"+pack_name+"-snuid-"+snuid
				+"-app_ratio-"+app_ratio+"-task_id-"+task_id+"-currency-"+currency+"-trade_type-"+trade_type+"-token-"+token);
		logger.info("-------vcode------"+vcode);
		logger.info("-------token--------"+token);
		if (token.equals(vcode)) {
			UserInfo ui = userInfoService.selectUserById(snuid);
			if (ui != null) {
				UserTask ut = null;
				if (task_id == null) {
					ut = userTaskService.getUserTask(snuid, pack_name, trade_type);
				}else{
					ut = userTaskService.getUserTask(task_id);
				}
				if (ut == null) {
					TaskInfo ti = taskInfoService.getTaskInfoNoStatus("dianle");
					ut = new UserTask();
					ut.setCreateTime(new Date());
					if (task_id == null) {
						ut.setGameName(pack_name);
					}else{
						ut.setGameName(task_id);
					}
					ut.setStatus("cp");
					ut.setTotamt(currency);
					ut.setUserId(snuid);
					ut.setGameLevel(trade_type);
					ut.setTaskId(ti.getId());
					userTaskService.insertUserTask(ut);
				}else{
					json = "403";
					logger.info("任务记录已存在");
				}
			}else{
				json = "403";
				logger.info("用户不存在");
			}
		}else{
			json = "403";
			logger.info("签名验证失败");
		}
		logger.info("-----dianle--json----------"+json);
		HtmlUtil.writerJson(response, json);
	}
	@RequestMapping("/diancai")
	public void diancai(Long timestamp,String deviceId,String appId,String adPackageName,Long userId,Integer rate,
			BigDecimal score,Integer tradeType,String token,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		String json = "200";
		String vcode= EncodeMD5.GetMD5Code(timestamp+deviceId+appId+score+rate+tradeType+adPackageName+userId+DC_KEY);
		logger.info("-------parameter-----timestamp-"+timestamp+"-deviceId-"+deviceId+"-appId-"+appId+"-adPackageName-"+adPackageName+"-userID-"+userId
				+"-rate-"+rate+"-score-"+score+"-tradeType-"+tradeType+"-token-"+token);
		logger.info("-------vcode------"+vcode);
		logger.info("-------token--------"+token);
		if (token.equals(vcode)) {
			UserInfo ui = userInfoService.selectUserById(userId);
			if (ui != null) {
				UserTask ut = userTaskService.getUserTask(userId, adPackageName, tradeType);
				if (ut == null) {
					TaskInfo ti = taskInfoService.getTaskInfoNoStatus("diancai");
					ut = new UserTask();
					ut.setCreateTime(new Date());
					ut.setGameName(adPackageName);
					ut.setStatus("cp");
					ut.setTotamt(score);
					ut.setUserId(userId);
					ut.setGameLevel(tradeType);
					ut.setTaskId(ti.getId());
					userTaskService.insertUserTask(ut);
				}else{
					json = "403";
					logger.info("任务记录已存在");
				}
			}else{
				json = "403";
				logger.info("用户不存在");
			}
		}else{
			json = "403";
			logger.info("签名验证失败");
		}
		logger.info("-----diancai--json----------"+json);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/datouniao")
	public void datouniao(String app_id,String udid,String clientParams,String orderID,Long userID,
			BigDecimal currency,String timestamp,String verifier,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		String json = "1";
		String parameter = app_id+currency+orderID+clientParams+DTN_KEY+timestamp+udid+userID;
		String vcode= SHA256Util.sha256(parameter);
		logger.info("-------parameter-----app_id-"+app_id+"-udid-"+udid+"-clientParams-"+clientParams+"-orderID-"+orderID+"-userID-"+userID
				+"-currency-"+currency+"-timestamp-"+timestamp+"-verifier-"+verifier);
		logger.info("-------vcode------"+vcode);
		logger.info("-------verifier--------"+verifier);
		if (verifier.equalsIgnoreCase(vcode)) {
			UserInfo ui = userInfoService.selectUserById(userID);
			if (ui != null) {
					UserTask ut = userTaskService.getUserTask(orderID);
					if (ut == null) {
						TaskInfo ti = taskInfoService.getTaskInfoNoStatus("datouniao");
						ut = new UserTask();
						ut.setCreateTime(new Date());
						ut.setGameName(orderID);
						ut.setStatus("cp");
						ut.setTotamt(currency);
						ut.setUserId(userID);
						ut.setTaskId(ti.getId());
						userTaskService.insertUserTask(ut);
					}else{
						json=  "任务记录已存在";
					}
			}else{
				json = "用户不存在";
			}
		}else{
			json = "签名验证失败";
		}
		logger.info("------datouniao-json----------"+json);
		HtmlUtil.writerJson(response, json);
	}
	@RequestMapping("/dianru")
	public void dianru(String hashid,String appid,String adid,String adname,Long userid,String mac,String deviceid,String source,
			BigDecimal point,Integer time,String checksum,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		JSONObject json = new JSONObject();
		String adname1 = URLDecoder.decode(adname,"UTF-8");
		String parameter = "?hashid="+hashid+"&appid="+appid+"&adid="+adid+"&adname="+adname1+"&userid="+userid;
		if(!StringUtils.isEmpty(mac)){
			parameter+="&mac="+mac;
		}
		parameter+="&deviceid="+deviceid+"&source="+source+"&point="+point+"&time="+time+"&appsecret="+DR_KEY;
		String vcode= EncodeMD5.GetMD5CodeUTF8(parameter);
		logger.info("-------parameter------"+parameter);
		logger.info("-------vcode------"+vcode);
		logger.info("-------checksum--------"+checksum);
		if (checksum.equals(vcode)) {
			UserInfo ui = userInfoService.selectUserById(userid);
			if (ui != null) {
				UserTask ut = userTaskService.getUserTask(hashid);
				if (ut == null) {
					TaskInfo ti = taskInfoService.getTaskInfoNoStatus("dianru");
					ut = new UserTask();
					ut.setCreateTime(new Date());
					ut.setGameName(hashid);
					ut.setStatus("cp");
					ut.setTotamt(point);
					ut.setUserId(userid);
					ut.setTaskId(ti.getId());
					userTaskService.insertUserTask(ut);
					json.put("message", "成功");
					json.put("success", true);
				}else{
					json.put("message", "任务记录已存在");
					json.put("success", false);
				}
			}else{
				json.put("message", "用户不存在");
				json.put("success", false);
			}
		}else{
			json.put("message", "签名验证失败");
			json.put("success", false);
		}
		logger.info("----dianru---json----------"+json);
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/beiduo")
	public void beiduo(Long time_stamp,String ad_packname,Long userid,
			BigDecimal currency,Integer trade_type,String token,HttpServletResponse response,HttpServletRequest request) throws Exception{
		String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
		logger.info("-------url------"+url);
		String json = "200";
		logger.info(time_stamp+BD_KEY);
		String vcode= EncodeMD5.GetMD5Code(time_stamp+BD_KEY);
		logger.info("-------parameter-----time_stamp-"+time_stamp+"-ad_packname-"+ad_packname+"-currency-"+currency+"-trade_type-"+trade_type+"-token-"+token);
		logger.info("-------vcode------"+vcode);
		logger.info("-------token--------"+token);
		if (token.equals(vcode)) {
			UserInfo ui = userInfoService.selectUserById(userid);
			if (ui != null) {
				UserTask ut = userTaskService.getUserTask(userid, ad_packname, trade_type);
				if (ut == null) {
					TaskInfo ti = taskInfoService.getTaskInfoNoStatus("beiduo");
					ut = new UserTask();
					ut.setCreateTime(new Date());
					ut.setGameName(ad_packname);
					ut.setStatus("cp");
					ut.setTotamt(currency);
					ut.setUserId(userid);
					ut.setGameLevel(trade_type);
					ut.setTaskId(ti.getId());
					userTaskService.insertUserTask(ut);
				}else{
					json = "403";
					logger.info("任务记录已存在");
				}
			}else{
				json = "403";
				json = "用户不存在";
			}
		}else{
			json = "403";
			json = "签名验证失败";
		}
		logger.info("----beiduo---json----------"+json);
		HtmlUtil.writerJson(response, json);
	}

}
