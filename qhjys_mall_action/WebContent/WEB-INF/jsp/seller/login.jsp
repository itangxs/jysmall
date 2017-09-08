<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>飞券网商家后台中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js">
</script>
<link href="/css/seller/public.css" rel="stylesheet" type="text/css" />
<link href="/css/seller/membernew.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="/common/formValidator4.0.1/style/validator.css"></link>
<script src="/common/formValidator4.0.1/js/formValidator-4.0.1.js" type="text/javascript" charset="UTF-8"></script>
<script src="/common/formValidator4.0.1/js/formValidatorRegex.js" charset="UTF-8"></script>
<script language="javascript" src="/common/formValidator4.0.1/js/DateTimeMask.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/seller/login.js"></script>
</head>
<body>
	<!-------------------top--------------------->
	<div class="topmenu03">
		<div class="topmenu3">
			<h1>飞券网</h1>
			<h3>商家中心</h3>
			<h2 style="font-size:14px;">
				<a href="/">首页</a>| <a href="/seller/regsiter.do">注册</a>
			</h2>
		</div>
	</div>
	<div class="membercontent1">
		<div class="membertop">
			<table width="75%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>商家登录</td>
				</tr>
			</table>
		</div>
		<div class="memberbut">
			<!----------------左侧----------------------->
			<div class="memberleft1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td style="text-align: left; background: #fffef5;">
							<form id="signupForm" method="post" action="/seller/loginCommit.do" class="zcform6">
								<input type="hidden" name="token" value="${token}" />
								<p class="clearfix">
									<label class="one" for="contactname">账号：</label>
									<input id="contactname" name="contactname" class="con-email" />
									<span id="contactnameTip"></span>
								</p>
								<p class="clearfix">
									<label class="one" for="contactname">密码：</label>
									<input name="password" type="password" id="password"  class="con-email" />
									<span id="passwordTip"></span>
								</p>
								<!-- <p class="clearfix">
									<label class="one5"><input name="fmale" type="checkbox" value=""  style="margin-top:10px; margin-left:50px;"/>下次自动登录 </label>
								</p> -->
								<p class="clearfix">
									<label class="one" for="con-email"></label>
									<input class="submit9" type="submit" value="登录"  />
									<span style="padding-left: 5px;"><a href="/seller/toBackPassword.do">忘记密码</a></span>
								</p>
							</form>
						</td>
					</tr>
				</table>
			</div>
			<!--------------右侧------------------>
			
			<div class="memberright1">
            	<a href="/seller/financial_support.do"><img src="/images/seller/login-banner.jpg" /></a>
            </div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
    
    
   
    
    
</body>
</html>
