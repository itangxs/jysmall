package cn.qhjys.mall.controller.weixin;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.SysUserTask;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.system.SysTaskService;
import cn.qhjys.mall.service.system.SysUserTaskService;
import cn.qhjys.mall.weixin.common.AccessToken;
import cn.qhjys.mall.weixin.common.WeiXinConstant;
import cn.qhjys.mall.weixin.common.WeiXinUser;
import cn.qhjys.mall.weixin.common.WeiXinUserList;
import cn.qhjys.mall.weixin.util.GetWeiXinCode;
import cn.qhjys.mall.weixin.util.WeiXinUtil;

@Controller
public class WeiXinController extends Base {

	@Autowired
	private SysTaskService sysTaskService;
	@Autowired
	private SysUserTaskService sysUserTaskService;
	@Autowired
	private UserInfoService  userInfoService;
	
	
	
	//JS 网络授权
	//第一步
	@RequestMapping("/weixin_task")
		public Object textWeiXieu(
				HttpSession session,
				@RequestParam(value = "user_id", required = false) Long user_id, 
				@RequestParam(value = "task_id", required = false) Long task_id 
				) throws Exception{
			SysTask sysTask = sysTaskService.getSysTask(task_id);
			if(null!=sysTask&&!StringUtils.isEmpty(sysTask.getAppid())&&!StringUtils.isEmpty(sysTask.getSecret())){
			 String appid = sysTask.getAppid()+"";
					//ConstantsConfigurer.getProperty("weixin_appid");
			String utl = WeiXinConstant.getWeixinAppUrl()+"/weixin_task2.do?user_id="+user_id+"&task_id="+task_id;
			String request = GetWeiXinCode.getCodeRequest(appid,utl);//获取到code码
			return "redirect:"+request;
			}else{
				ModelAndView view = new ModelAndView("/weixin/weixin_task");
				return view;
			}
		}
		
	   //第二步 
		@RequestMapping("/weixin_task2")
		public Object textWeiXieu2(
				HttpSession session,
				@RequestParam(value = "state", required = false) String state,
				@RequestParam(value = "code", required = false) String code, //code码 
				@RequestParam(value = "openid", required = false) String weiXinId, 
				@RequestParam(value = "user_id", required = true) Long user_id ,
				@RequestParam(value = "task_id", required = true) Long task_id
				) throws Exception{
			ModelAndView view = new ModelAndView("/weixin/weixin_task2");
			try {
				/*String appid = ConstantsConfigurer.getProperty("weixin_appid");
				String appsecret = ConstantsConfigurer.getProperty("weixin_appsecret");*/
				
				SysTask sysTask2 = sysTaskService.getSysTask(task_id);
				if(null!=sysTask2&&!StringUtils.isEmpty(sysTask2.getAppid())&&!StringUtils.isEmpty(sysTask2.getSecret())){
					String appid=sysTask2.getAppid();
					String appsecret=sysTask2.getSecret();
				String currentOpenIdurl = GetWeiXinCode.getCurrentOpenId(code,appid,appsecret);
				JSONObject httpRequest = WeiXinUtil.httpRequest(currentOpenIdurl, "GET", null);
				Object openid = httpRequest.get("openid");
				SysTask sysTask = sysTaskService.getSysTask(task_id);
				view.addObject("object",openid);
				view.addObject("user_id",user_id);
				view.addObject("task_id",task_id);
				view.addObject("sysTask",sysTask);
				if(null!=openid){//openid
					//判断该用户是否做过任务
					SysUserTask userTask = sysUserTaskService.getSysUserTask(task_id,user_id);
					if(userTask==null){
						 userTask = new SysUserTask();
						 userTask.setCreateTime(new Date());
						 userTask.setOpenId(openid+"");
						 userTask.setUserId(user_id);
						 userTask.setTaskId(task_id);
						 userTask.setTotamt(sysTask.getFulfilReward());
						 userTask.setStatus(0);
						 sysUserTaskService.insertSysUserTask(userTask);
						}
					}
				}else{
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return view;
		}
		//JS 网络授权结束
		
		
		//定时任务
		public void timing() throws Exception{
			//获取所有有效的关注任务 2
			List<SysTask> sysTask = sysTaskService.getSysTask(2, 2,new Date());
			if(null!=sysTask&&sysTask.size()>0){
				for (int i = 0; i < sysTask.size(); i++){
					SysTask task = sysTask.get(i);
					//审核中的用户
					List<SysUserTask> list = sysUserTaskService.getSysUserTasksListByTaskIdAndStatus(task.getId(), 0);
					if(null!=list&&list.size()>0){
						//获取公众号用户列表
						List<WeiXinUser> weixinList = test(task.getAppid(), task.getSecret(), null);
						if(null!=weixinList&&weixinList.size()>0){
						f:for (int j = 0; j < list.size(); j++) {
								SysUserTask sysUserTask = list.get(j);
								Boolean statusE=true;//记录是否改变状态
								k:for (int k = 0; k < weixinList.size(); k++) {
									WeiXinUser user = weixinList.get(k);
									if(sysUserTask.getOpenId().equals(user.getOpenid())
										||sysUserTask.getOpenId()==user.getOpenid()){
										//这里给他加奖励    sysUserTask.getUserId() //用户ID  sysUserTask.getTaskId() //任务ID
										sysUserTaskService.updateSysUserTaskStatus(sysUserTask.getId(),1,task.getFulfilReward());//改变状态   成功
										statusE = false;
										break k;
									}
								}
								if(statusE){
									sysUserTaskService.updateSysUserTaskStatus(sysUserTask.getId(),2,task.getFulfilReward());//改变状态  失败 
								}
							}
						}
					}
				}
			}
		}
		
		
		public 	List<WeiXinUser> test(String appid,String appat,String e){
			AccessToken at = WeiXinUtil.getAccessToken(appid, appat);
			if(null!=at&&null!=at.getToken()){
				WeiXinUserList users = WeiXinUtil.getUser(at.getToken(), "");//     Null默认
				//获取详情
		/*		List<WeiXinUser> re = new ArrayList<WeiXinUser>();*/
				List<WeiXinUser> data = users.getData();
					/*for (int i = 0; i < data.size(); i++) {
						WeiXinUser weiXinUser = data.get(i);
						WeiXinUser userd = WeiXinUtil.getUserd(at.getToken(), weiXinUser.getOpenid());
						//re.add(userd);
					}*/
				if(null!=data&&data.size()>0){
					return data; 
				}else{
					return null;
				}
			}
			return null;
		}
		
		//提交任务
		@ResponseBody
		@RequestMapping("/wenxin_task_save")
		public void wenxin_task_save(
				@RequestParam(value = "task_id", required = true) Long task_id,
				@RequestParam(value = "user_id", required = true) Long user_id,
				@RequestParam(value = "open_id", required = true) Long open_id
				){
			logger.info("-------------y-------------"+open_id);
			logger.info("--------------y------------"+user_id);
			logger.info("------------y--------------"+task_id); 
		}
}
