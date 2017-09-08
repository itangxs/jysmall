<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-touch-fullscreen" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>飞券网</title>
<link href="/css/wap/css/public.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/wap/registration_telephone.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
</head>
 
<body>
<div class="global">  
<form method="post" id="registrationForm" method="post" action="/account/wap/saveRegister.do"> 
  <div class="module">
  	<div class="tabs"> 
  	
  		<a id="registrationByEmail" href="/account/wap/registrationByEmail.do?recomId=${recomId}&inviteCode=${inviteCode}" >邮箱注册</a>
  		<a id="registrationByTel" href="/account/wap/registrationByTel.do?recomId=${recomId}&inviteCode=${inviteCode}" class="tabaction">手机注册</a>
  		
        <div class="clear"></div>        
    </div>
    <div class="login">
       	<label class="loginbk"><b>
        			<span id="huoqu">
        			<a href="javascript:settime()" >获取验证码</a></span> </b> 
				<input name="phoneNum" id="phone" type="text" onFocus="if(value=='请输入手机号码'){value=''}" onBlur="if(value==''){value='请输入手机号码'}" class="inputphone inputtext" value="请输入手机号码"></label>
 					  <span id="phoneTip"></span>  
         					    
        <label class="loginbk">
        <input  name="validateCode" id="margin5"  onfocus="if(value=='请输入验证码'){value=''}" onBlur="if(value==''){value='请输入验证码'}"  type="text" class="inputyanzheng inputtext" value="请输入验证码"></label>
        <span id="margin5Tip"></span> 
       
        
        <label class="loginbk"><input id="username" name="username"  type="text" onFocus="if(value=='请输入用户名'){value=''}" onBlur="if(value==''){value='请输入用户名'}" class="inputname inputtext" value="请输入用户名"></label>
        <span id="usernameTip"></span>
        <label class="loginbk"><input id="password" name="password"
        			onfocus="if(value=='请输入密码'){document.getElementById('password').type ='password';value=''}" onBlur="if(value==''){document.getElementById('password').type ='text'; value='请输入密码'}"  
        			class="inputpassword inputtext" type="text" value="请输入密码">
        </label>
       	<span id="passwordTip"></span>
        <label class="loginbk1"><input id="confirm_password" name="confirm_password"  onFocus="if(value=='请输入确认密码'){document.getElementById('confirm_password').type ='password';value=''}" onBlur="if(value==''){document.getElementById('confirm_password').type ='text'; value='请输入确认密码'}"  type="text" class="inputpassword inputtext" value="请输入确认密码"></label>
        <span id="confirm_passwordTip"></span>
         <label class="loginbk">
         		<input id="recomId" name="recomId" class="inputpassword inputtext"  value="${recomId}"/>
         </label>  
        	<span id="recomIdTip"></span>
        	<div></div>          
        </div>
    </div>
    <div class="bottomanniu">
    	<input class="loginbutton1" type="submit" name="button" id="button" value="注 册">
    	<input type="hidden" name="token" value="${wapRegisterToken}">
    	<input type="hidden" name="inviteCode" value="${inviteCode}">
    	<input type="hidden" name="page" value="2">
        <div class="zhaohui"><input name="协议" type="radio" value="" checked /> 我已经看过并同意飞券网<a href="/html/app/term.html">《用户使用协议》</a></div>  
  </div>
   </form>
</div>
  
      
</body>
</html>