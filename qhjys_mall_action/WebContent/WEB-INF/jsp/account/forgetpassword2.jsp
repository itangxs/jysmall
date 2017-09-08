<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/forgetpassword.css" rel="stylesheet" type="text/css" />


</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--banner包含部分-s-->	    
<!--------------------------找回密码-------------------------------------------------------->
<div class="forgetpassword">
	<div class="title">找回登陆密码</div>
    <!--步骤二-->
	<div class="steps-bar">
    	<ul class=" steps-bar--dark cf">
			<li class="step step--first" style="z-index:4">
                <span class="step__num">1.</span>
                <span>确认账号</span>
                
                <span class="arrow__background"></span>
                <span class="arrow__foreground"></span>
            </li>
			<li class="step step--pre step--current" style="z-index:3">
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
    <div class="confirm-title"><strong>您正在为账户重置登录密码，请选择找回方式：</strong></div>
    <div class="confirm-account ">
    	<!--通过手机找回密码-->
    	<div class="confirm-findways">
        	<img class="findimg" src="/images/mobile-phone22.png" />
            <span class="findtitle"><em>通过绑定的手机</em><br />需要您绑定的手机可进行短信验证</span>
            <span class="findtext"><a href="/account/forgetpassword3.do?type=1&token=${forgetPassword2Token}" class="button_red">立即找回</a></span>
        </div>
        <!--通过邮箱找回密码-->
         <div class="confirm-findways">
        	<img class="findimg" src="/images/mail.png" />
            <span class="findtitle"><em>通过绑定的邮箱</em><br />安全链接将发送到您绑定的邮箱</span>
            <span class="findtext"><a href="/account/forgetpassword3.do?type=2&token=${forgetPassword2Token}" class="button_red">立即找回</a></span>
        </div> 
        <!--通过密保找回密码-->
        <div class="confirm-findways">
        	<img class="findimg" src="/images/mibao.png" />
            <span class="findtitle"><em>通过密保问题</em><br />需要提供密保问题及答案</span>
            <span class="findtext"><a href="/account/forgetpassword3.do?type=3&token=${forgetPassword2Token}" class="button_red">立即找回</a></span>
        </div>
  </div>
</div>


<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>