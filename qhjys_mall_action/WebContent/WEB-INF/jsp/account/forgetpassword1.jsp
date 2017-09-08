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
<script type="text/javascript" src="/js/account/forgetpassword1.js"></script>
<script type="text/javascript" src="/js/changeimg.js"></script>


</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--banner包含部分-s-->	    
<!--------------------------找回密码-------------------------------------------------------->
<div class="forgetpassword">
	<div class="title">找回登陆密码</div>
    <!--步骤一-->
	<div class="steps-bar">
    	<ul class=" steps-bar--dark cf">
            <li class="step step--first step--current" style="z-index:4">
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
            <li class="step step--pre" style="z-index:2">
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
    <!--找回密码第一步-->
    <div class="confirm-account">
    	<form action="/account/forgetpassword2.do" method="post" id="signupForm">
        	<div class="form-field">
            	<label>账户</label><input type="text" class="f-text account" name="userName" id="userName" value="" />
            	<span id="userNameTip"></span>
            </div>
            <div class="form-field">
            	<label>验证码</label><input class="f-text verify-code" name="yzm" type="text" id="yzm"/>
                <span id="yanzhengmaimg" style="cursor: pointer;" onclick="changeImg('imageId','','');"> <img id="imageId" src="/valiCode.do" border="0"  style="margin-top:10px;"/></span>
           		<span id="yzmTip"></span>
            </div>
            <div class="form-field">
            	<label></label><input type="submit" class="button_red" value="下一步" />
            </div>
            <input name="token" value="${forgetPassword1Token}" type="hidden">
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>