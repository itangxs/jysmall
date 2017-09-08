<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/js/jquery.superslide.2.1.1.banner.js"></script>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/forgetpassword.css" rel="stylesheet" type="text/css" />


<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/account/forgetpassword3.js"></script>


</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--banner包含部分-s-->	    
<!--------------------------找回密码-------------------------------------------------------->
<div class="forgetpassword">
	<div class="title">找回登陆密码</div>
    <!--步骤三-->
	<div class="steps-bar">
    	<ul class=" steps-bar--dark cf">
			<li class="step step--first" style="z-index:4">
                <span class="step__num">1.</span>
                <span>确认账号</span>
                
                <span class="arrow__background"></span>
                <span class="arrow__foreground"></span>
            </li>
			<li class="step step--pre" style="z-index:3">
                <span class="step__num">2.</span>
                <span>选择验证方式</span>
                
                <span class="arrow__background"></span>
                <span class="arrow__foreground"></span>
            </li>
            <li class="step step--pre step--current" style="z-index:2">
                <span class="step__num">3.</span>
                <span>验证/修改</span>
                
                <span class="arrow__background"></span>
                <span class="arrow__foreground"></span>
            </li>
            <li class="step step--last step--pre" style="z-index:1">
                <span class="step__num">4.</span>
                <span>完成</span>
                
            </li>
        </ul>
    </div>
    <!--通过手机找回密码-->
    <c:if test="${1==type}">
	    <div class="confirm-account">
	    	<div class="tishititle"><img class="" src="/images/mobile-phone22.png" /><em>为了您的账户安全，请先验证手机</em></div>
	        <div class="form-field">
		      <label>您绑定的手机号</label>
		      ${fn:substring(forgetUserInfo.phoneNum, 0, 4)}****${fn:substring(forgetUserInfo.phoneNum, fn:length(forgetUserInfo.phoneNum)-4 , fn:length(forgetUserInfo.phoneNum))}
	        </div>
	        <div class="form-field">
	        	<input id="phoneNum" type="hidden" value="${forgetUserInfo.phoneNum}">
	        
		      <label></label>	<input id="sendSMS" type="button"   value="获取动态码" class="button1" />
		    <!--   <button class="button_hui" onclick="getVcode()" id="sendSMS" >获取验证码</button> -->
	        </div>
	        <form action="/account/phoneBack.do" id="signupForm" method="post"> 
			    <div class="form-field">
			      <label>短信动态码</label>
			      <input type="text" class="f-text account" name="forgetYzm" id="forgetYzm" />
			      <span id="forgetYzmTip"></span>
			    </div>
			    <div class="form-field">
			      <label></label>
			      <input type="hidden" value="${forgetPassword3Token}" name="token">
			      <input type="submit" class="button_red" value="下一步" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);" class="fontred">上一步</a>
		        </div>
	        </form>
	  	</div>
  	</c:if>
    <!--通过邮箱找回密码-->
     <c:if test="${2==type}">
    <div class="confirm-account ">
    	<div class="confirm-findways confirm-findways-lineno">
       	  <img class="findimg" src="/images/mail.png" />
            <span class="findtitle"><em>邮件已发送</em><br /><a>${fn:substring(forgetUserInfo.email, 0, 4)}****${fn:substring(forgetUserInfo.email, fn:length(forgetUserInfo.email)-4 , fn:length(forgetUserInfo.email))}
             </a>查阅来自飞券网的邮件<br />点击邮件中的链接重设您的登录密码</span>
        </div>
        <span class="confirm-findways-next"><input type="submit" class="button_red" value="去邮箱收信" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);" class="fontred">上一步</a></span>
	</div>
	</c:if>
    <!--通过密保找回密码-->
    <c:if test="${3==type}">
    <div class="confirm-account">
    	<div class="tishititle"><img class="" src="/images/mibao.png"/><em>你正在通过密保问题找回密码，需要提供密保问题及答案</em></div>
	  <form action="/account/problemBack" method="get" id="">
	    
        <div class="form-field">
	      <label>你的密保问题</label>
	      <input type="text" class="f-text account" name="question" id="question"/><span id="questionTip"></span>
        </div>
	    <div class="form-field">
	      <label>你的密保答案</label>
	      <input type="text" class="f-text account" name="answer" id="answer"/><span id="answerTip"></span>
	    </div>
	    <div class="form-field">
	      <label></label>
	       <input type="hidden" value="${forgetPassword3Token}" name="token">
	      <input type="submit" class="button_red" value="提交" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);" class="fontred">上一步</a>
        </div>
      </form>
  	</div>
  	</c:if>
</div>

<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>