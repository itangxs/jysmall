package cn.qhjys.mall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService; 
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.util.ConstantsConfigurer;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

@Controller
public class QqLoginController extends Base{
	
	@Autowired 
	private UserInfoService userInfoService;
	
	@Autowired 
	private TaskInfoService taskInfoService;
	
	@Autowired 
	private CashAccountService cashAccountService;
	@Autowired 
	private UserTaskService userTaskService;
	
	@RequestMapping("/qqLogin")
	public void qqLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		  response.setContentType("text/html;charset=utf-8");
	        try {
	            response.sendRedirect(new Oauth().getAuthorizeURL(request));
	        } catch (QQConnectException e) {
	            e.printStackTrace();
	        }
	}
	
	@RequestMapping("/afterlogin")
	public Object loginAfter(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 	ModelAndView model = new ModelAndView("account/index");
	        try {
	            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

	            String accessToken   = null,
	                   openID        = null;
	            long tokenExpireIn = 0L;

	            if (accessTokenObj.getAccessToken().equals("")) {
	            	this.logger.info("没有获取到响应参数");
	            	model.setViewName("redirect:index.do");
	            } else {
	                accessToken = accessTokenObj.getAccessToken();
	                tokenExpireIn = accessTokenObj.getExpireIn();

	                request.getSession().setAttribute("demo_access_token", accessToken);
	                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

	                // 利用获取到的accessToken 去获取当前用的openid 
	                OpenID openIDObj =  new OpenID(accessToken);
	                openID = openIDObj.getUserOpenID();
	                request.getSession().setAttribute("openID", openID);
	                com.qq.connect.api.qzone.UserInfo qzoneUserInfo = new com.qq.connect.api.qzone.UserInfo(accessToken, openID);
	                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
	                request.getSession().setAttribute("nickname", userInfoBean.getNickname());
	                UserInfo ui = userInfoService.getUserByOpenId(openID);
	                if (ui != null) {
	                	CashAccount cashAccount = cashAccountService.queryCashAccount(null, ui.getId());
	                	request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"), cashAccount);
            			request.getSession().setAttribute(ConstantsConfigurer.getUser(), ui);
            			boolean judge = userInfoService.judgeSignIn(ui.getId());
            			request.getSession().setAttribute("judgeSignIn", judge);
            			TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
            	  		UserTask ut = userTaskService.getUserTask(ui.getId()+1000000000, ti.getId());
            	  		if (ut!=null && ut.getGameLevel() == 5) {
            	  			String ispc = "";
            	  			if (ut.getGameName() != null && ispc.equals("1")) {
            	  				ispc = "?ispc=1";
							}
            	  			model.setViewName("redirect:/account/task/doNewUserTask.do"+ispc);
            			}else{
                			model.setViewName("redirect:index.do");
            			}
					}else{
						model.setViewName("redirect:/account/registrationByTel.do");
	                }
	            }
	        } catch (QQConnectException e) {
	        }
			return model;
	}
	@RequestMapping("/saveQqOpenId")
	public Object saveQqOpenId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		String openId =  (String) request.getSession().getAttribute("openID");
		user.setQqOpenId(openId);
		userInfoService.updateUserById(user);
		TaskInfo ti = taskInfoService.getTaskInfo("selleruser");
  		UserTask ut = userTaskService.getUserTask(user.getId()+1000000000, ti.getId());
  		if (ut!=null && ut.getGameLevel() == 5) {
  			return "redirect:/account/task/doNewUserTask.do";
		}else{
//			boolean a =taskInfoService.insertNewUserTask(user.getId(), NewTaskCode.N005);
//			if (a) {
//	    		request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, user.getId()));
//			}
			return "redirect:/managermall/account/setupUserSafe.do";
		}
	}
}
