<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网</title>
<link href="/css/seller/public.css" rel="stylesheet" type="text/css" />
<link href="/css/seller/login.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="loginlogo">
		<a href="/"><img src="/images/logo.png" /></a>
	</div>
	<div class="loginmain">
		<div class="loginm">
			<div class="loginleft"></div>
			<form id="queryOrder" action="loginCommit.do" method="post" class="loginForm">
				<input type="hidden" name="token" value="${systemlogin}" />
				<p>
					<span>用户名：</span>
					<input name="username" type="text" />
				</p>
				<p>
					<span>密 &nbsp; 码：</span>
					<input name="password" type="password" />
				</p>
				<p>
					<input class="anniu" type="submit" name="button" id="button" value="登陆" />
				</p>
			</form>
		</div>
	</div>
	<!--底部-s-->
	<div class="bottomlogin">
		<h2>粤ICP备14023008号-1 版权所有©2013-2015 深圳市前海金钥匙电子商务有限公司</h2>
	</div>
</body>
</html>
