<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/public.css" />
<link rel="stylesheet" type="text/css" href="/css/login.css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/account/registration_telephone.js"></script>
<script type="text/javascript" src="/js/changeimg.js"></script>
<script type="text/javascript">
	if("${sessionScope.openID}"!= null&&"${sessionScope.openID}"!= ""&&"${sessionScope.user}" !=null && "${sessionScope.user}" != ""){
		window.location.href="/saveQqOpenId.do";
	}
</script>
</head>
<body>
	<div class="loginlogo">
		<span>已有飞券网账号？<a href="/account/login.do" class="button_red">登录</a></span>
		<a href="/"><img src="/images/logo.png" /></a>
	</div>
	<!--用户注册-->
	<div class="registration">
		<div class="tabs1">
			<a id="registrationByEmail" href="/account/registrationByEmail.do" class="tabaction">邮箱注册</a><a id="registrationByTel" href="/account/registrationByTel.do" class="tabaction">手机注册</a>
			<div class="clear"></div>
		</div>
		<form id="registrationForm" action="" method="get">
			<c:if test="${sessionScope.nickname != null && sessionScope.nickname !='' }"><h1>
				<i>昵称</i> ${sessionScope.nickname }
			</h1></c:if>
			<h1>
				<i>用户名</i> <input class="inputkk" id="username" name="username" type="text" />
			</h1>
			<h1>
				<i>创建密码</i> <input class="inputkk" id="password" name="password" type="password" />
			</h1>
			<h1>
				<i>确认密码</i> <input class="inputkk" id="confirm_password" name="confirm_password" type="password" />
			</h1>
			<h1>
				<i>验证码</i> <input class="inputkk" id="identifyingCode" name="identifyingCode" type="text" />
				 <span id="yanzhengmaimg" style="cursor: pointer;" onclick="changeImg('imageId','','');"> <img id="imageId" src="/valiCode.do" border="0"  style="vertical-align:middle;"/> <em style=" color:#999">点击可换一张</em></span>
			</h1>
			<h1>
				<i>手机号</i> <input class="inputkk" id="phoneNum" name="phoneNum" type="text" />
			</h1>
			<h1>
				<i></i> <input id="sendSMS" type="button" value="获取动态码" class="button_hui" />
			</h1>
			<h1>
				<i>短信动态码</i> <input class="inputkk" id="validateCode" name="validateCode" type="text" />
			</h1>
			<h1>
				<i>推荐码</i><input class="inputkk" id="inviteCode" name="inviteCode" type="text" />
			</h1>
			<h1>
				<i></i> <input id="isChecked" name="isChecked" type="radio" value="" checked="checked" />
				<a href="/mallcms/info2.do?id=17">我已阅读并同意《飞券网用户协议》</a>
			</h1>
			<h1>
				<i></i> <input type="submit" class="button_red" value="马上注册" />
			</h1>
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