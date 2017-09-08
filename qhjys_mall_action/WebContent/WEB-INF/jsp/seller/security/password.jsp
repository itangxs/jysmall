<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -验证消费卷</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/seller/security/password.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
	<div class="membercontent">
		<div class="memberleft">
			<!--左侧菜单-->
			<div class="membermenu">
				<ul>
					<li>
						<h1>账号管理</h1>
						<p>
							<a href="/managermall/seller/center/setSellerInfo.do"><span>·</span>账户信息</a>
						</p>
						<p>
							<a href="/managermall/seller/center/center.do" class="menucurrent"><span>·</span>安全设置</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
		<!--------------右侧------------------>
		<div class="memberright">
			<div class="member_myorder">
				<h3 style="padding-left: 10px;">修改密码</h3>
				<form id="form" method="post" class="zcform" style="margin-left:150px;">
					<input id="phone" name="phone" type="hidden" value="${seller.phone}">
					<br>
					<p class="clearfix">
						<label class="one" for="phonCode">手机号码：</label>
						${seller.phone }
					</p>
					<p class="clearfix">
						<label class="one" for="phonCode">手机验证码：</label>
						<input id="captcha" name="captcha" class="con-email3">
						<input id="sendSMS" type="button" value="获取验证码" class="button1" />
					</p>
					<p class="clearfix">
						<label class="one" for="con-email">旧登录密码：</label>
						<input id="oldCode" name="oldCode" class="con-email" type="password">
					</p>
					<p class="clearfix">
						<label class="one" for="con-email">新登录密码：</label>
						<input id="newCode" name="newCode" class="con-email" type="password">
					</p>
					<p class="clearfix">
						<label class="one" for="con-email">确认新密码：</label>
						<input id="confirm" name="confirm" class="con-email" type="password">
					</p>
					
					<p class="clearfix">
						<input type="submit" value="确认修改" class="submit23" style="margin-left:100px;">
					</p>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>