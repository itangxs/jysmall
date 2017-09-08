<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta property="qc:admins" content="24217701266213514463757" />
<title>飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/login.js"></script>
</head>
<body>
	<div class="loginlogo">
		<a href="/"><img src="/images/logo.png" /></a>
	</div>
	<!--top菜单-->
	<div class="login">
		<form id="loginFrom" action="" method="post">
			<p>
				<strong>账号登录</strong>
			</p>
			<p>
				<input class="input1" id="username" name="username" type="text" value="手机号/用户名/邮箱" />
			</p>
			<p>
				<input class="input2" id="password" name="password" type="password" />
			</p>
			<p>
				<label>
					<em><a href="/account/forgetpassword.do" class="fontred">忘记密码？</a></em>
					<input type="checkbox" name="CheckboxGroup1" id="CheckboxGroup1" />
					七天内自动登录
				</label>
			</p>
			<p>
				<input class="anniu" type="submit" value="登录" />
			</p>
			<p>
				还没有账号？<a href="/account/registrationByTel.do" class="fontred">免费注册</a>&nbsp;&nbsp;&nbsp;<a href="/qqLogin.do"><img src="/images/Connect_logo_6.png"/></a>
			</p>
		</form>
	</div>
	<!--底部-s-->
	<div class="bottomlogin">
		<h1><!--<span><em>随时退</em><em>过期退</em><em>支持代金券</em></span>-->
			<a href="/mallcms/info.do?id=5">关于我们</a>|<a href="/mallcms/info2.do?id=11">常见问题</a>|<a href="/seller/regsiter.do">商家入驻</a>|<a href="/mallcms/info2.do?id=15">服务中心</a>|<a href="#">商城手机版</a>
		</h1>
		<p>粤ICP备14023008号-1 版权所有©2013-2015 深圳市前海金钥匙电子商务有限公司</p>
	</div>
</body>
</html>