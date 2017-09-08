<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -验证消费卷</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/seller/security/verifyEmail.js"></script>
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
				<h3 style="padding-left: 10px;">绑定邮箱</h3>
				<c:if test="${sessionScope.hasEmail}">
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th class="td135">
							<img width="28" align="right" height="28" src="/images/seller/dbg1.png">
							<span>1.验证原邮箱</span>
						</th>
						<th class="td136">2.填写新邮箱</th>
						<th class="td136">3.绑定成功</th>
					</tr>
				</table>
				<br>
				<br>
				<div class="zcform" style="margin-left:150px;">
					<p class="clearfix">
						<label for="con-email" class="one" style="width:120px;">原绑定邮箱地址：</label>
						<label>${fn:substring(seller.email,0,3)}****${fn:substring(seller.email,fn:indexOf(seller.email,'@'),fn:length(seller.email))}</label>
					</p>
					<p class="clearfix">
						<label class="one" for="con-email" style="width:120px;">请输邮箱验证码：</label>
						<input id="captcha" name="captcha" class="con-email3">
						<input type="hidden" id="sellerEmail" value="${seller.email}" />
						<label><input id="sendEmail" type="sbumit" value="获取验证码" class="button1" /></label>
					</p>
					<a id="verifyEmail" href="javascript:;" style="float:left;margin-left:125px;;color:#FFF;" class="button0">下一步</a>
				</div>
				</c:if>
				<c:if test="${!sessionScope.hasEmail}">
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th class="td135">
							<img width="28" align="right" height="28" src="/images/seller/dbg1.png">
							<span>1.填写新邮箱</span>
						</th>
						<th class="td136">
							<span>2.绑定成功</span>
						</th>
					</tr>
				</table>
				<br>
				<br>
				<form class="zcform">
					<p class="clearfix">
						<label for="con-email" class="one" style="width: 120px">请输入邮箱地址：</label>
						<input id="email" name="email" class="con-email">
					</p>
					<p class="clearfix">
						<label class="one" for="con-email" style="width: 120px">请输邮箱验证码：</label>
						<input id="captcha" name="captcha" class="con-email3">
						<label><a class="button1">获取验证码</a></label>
					</p>
					<a id="addEmail" href="javascript:;" style="float:left;margin-left:1px;color:#FFF;" class="button0">下一步</a>
				</form>
				</c:if>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>