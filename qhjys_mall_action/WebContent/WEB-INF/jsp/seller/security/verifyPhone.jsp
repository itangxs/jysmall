<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -验证消费卷</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/seller/security/verifyPhone.js"></script>
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
				<h3 style="padding-left: 10px;">绑定手机</h3>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th class="td135">
							<img width="28" align="right" height="28" src="/images/seller/dbg1.png">
							<span>1.验证原手机号码</span>
						</th>
						<th class="td136">
							<span>2.填写新手机号</span>
						</th>
						<th class="td136">3.绑定成功</th>
					</tr>
				</table>
				<br>
				<br>
				<form class="zcform" style="margin-left:150px;">
					<input id="phone" type="hidden" value="${seller.phone}">
					<p class="clearfix">
						<label for="con-email" class="one" style="width:120px;">原绑定手机号码：</label>
						<label>${fn:substring(seller.phone,0,3)}****${fn:substring(seller.phone,7,11)}</label>
					</p>
					<p class="clearfix">
						<label class="one" for="con-email" style="width:120px;">请输手机验证码：</label>
						<input id="captcha" name="captcha" class="con-email3">
						<input id="sendSMS" type="button" value="获取验证码" class="button1" />
					</p>
					<a id="verify" href="javascript:;" style="float:left;margin-left:120px;;color:#FFF;" class="button0">下一步</a>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>