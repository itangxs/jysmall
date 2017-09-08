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
<script type="text/javascript" src="/js/account/forgetpassword3_3.js"></script>


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
	<div class="confirm-account">
    	<div class="tishititle"><img class="" src="/images/tongguo.jpg" />您的验证已经成功通过，请立即修改您的登录密码</div>
	  <form action="/account/backPassword.do" method="get" id="signupForm">
        <div class="form-field">
	      <label>新的登陆密码</label>
	      <input type="password" class="f-text account" name="password" id="password"  /><span id="passwordTip"></span>
        </div>
	    <div class="form-field">
	      <label>确认登陆密码</label>
	     <input type="password" class="f-text account" name="password1" id="password1" /><span id="password1Tip"></span>
	    </div>
	    <div class="form-field">
	      <label></label>
	      	<input type="hidden" value="${updatePasswordToken}" name="token"/>
	      <input type="submit" class="button_red" value="确认提交" />
        </div>
      </form>
  </div>
</div>


<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>