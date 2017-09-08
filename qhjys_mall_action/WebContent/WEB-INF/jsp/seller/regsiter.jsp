<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>飞券网商家后台中心-商家注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="/css/seller/public.css" />
<link rel="stylesheet" type="text/css" href="/css/seller/membernew.css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/seller/regsiter.js"></script>
<script type="text/javascript" src="/js/changeimg.js"></script>
</head>
<body>
	<div class="topmenu03">
		<div class="topmenu3">
			<h1>飞券网</h1>
			<h3>商家中心</h3>
			<h2 style="font-size:14px;">
				<a href="/">首页</a>|
				<a href="/seller/login.do">登录</a>
			</h2>
		</div>
	</div>
	<div class="membercontent1">
		<div class="membertop">
			<table width="75%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>商家注册</td>
				</tr>
			</table>
		</div>
		<div class="memberbut">
			<form id="regsiter" method="post" action="" class="zcform7">
				<input type="hidden" id="token" value="${token}" />
				<p class="clearfix">
					<label class="one" for="contactname">用户帐号：</label>
					<input id="username" name="username" type="text" class="con-email">
					<span style="padding-left: 5px; color: #F30">*</span>
				</p>
				<p class="clearfix">
					<label class="one" for="contactname">登录密码：</label>
					<input id="password" name="password" type="password" class="con-email">
					<span style="padding-left: 5px; color: #F30">*</span>
				</p>
				<p class="clearfix">
					<label class="one" for="contactname">确认密码：</label>
					<input id="confirm" name="confirm" type="password" class="con-email">
					<span style="padding-left: 5px; color: #F30">*</span>
				</p>
				<p class="clearfix">
					<label class="one" style="padding-left: 14px;">验证码：</label>
					 <input class="con-email" id="identifyingCode" name="identifyingCode" type="text" />
					<span style="padding-left: 5px; color: #F30">*</span>
					 <span id="yanzhengmaimg" style="cursor: pointer;" onclick="changeImg('imageId','','');"> <img id="imageId" src="/valiCode.do" border="0"  style="margin-top:10px;"/></span>
				</p>
				<p class="clearfix">
					<label class="one" for="telphone">手机号码：</label>
					<input id="phone" name="phone" type="text" class="con-email">
					<span style="padding-left: 5px; color: #F30">*</span>
				</p>
				<!-- <p class="clearfix">
					<label class="one" style="padding-left: 14px;">校验码：</label>
					<input class="identifying_code" id="captcha" type="text">
					<input class="get_code" type="button" id="sendSms" value="获取验证码" />
					<span style="padding-left: 5px; color: #F30">*</span>
				</p> -->
				<p class="clearfix">
					<label class="one5">
						<input id="protocol" type="checkbox" style="margin-top:10px;margin-left:73px;" />
						我已阅读并同意<a href="/mallcms/info2.do?id=16">《飞券网商户服务协议》</a><!-- 和<a href="/mallcms/info2.do?id=16">《隐私保密》</a> -->
					</label>
				</p>
				<p class="clearfix">
					<input class="submit9" type="submit" value="注册" />
				</p>
			</form>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/footer.jsp" flush="true" />
</body>
</html>
