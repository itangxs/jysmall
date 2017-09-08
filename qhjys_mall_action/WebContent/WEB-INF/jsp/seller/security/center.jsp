<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -验证消费卷</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
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
		<c:set var="percent" value="5" />
		<c:if test="${empty seller.password}"><c:set var="percent" value="${percent-1}" /></c:if>
		<c:if test="${empty seller.email}"><c:set var="percent" value="${percent-1}" /></c:if>
		<c:if test="${empty seller.phone}"><c:set var="percent" value="${percent-1}" /></c:if>
		<c:if test="${empty seller.answer}"><c:set var="percent" value="${percent-1}" /></c:if>
		<div class="memberright">
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="td133" style="border-bottom: none; padding-top:10px;">
							您的账号安全等级：
							<c:if test="${percent<3}">低</c:if>
							<c:if test="${percent<5}">中</c:if>
							<c:if test="${percent==5}">高</c:if>
						</td>
					</tr>
					<tr>
						<td class="td133" style="border-bottom:none;">
							<div class="jindu">
	                        	<div style="width:${percent*100/5}%;" class="jinduconp2p">
	                        		<i><fm:formatNumber value="${percent*100/5}" />%</i>
	                        	</div>
	                        </div>
						</td>
					</tr>
					<tr>
						<td class="td133" colspan="3">账号已经设置了交易密码和手机绑定，请保管好手机，交易密码可重置</td>
					</tr>
				</table>
				<br />
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="td132"><img src="/images/seller/tu01.png" width="16" height="16" /> &nbsp;登录密码</td>
						<td class="td134">登录商城时需要使用的密码。<br />
						<span style="color:#F30">最基本的账号保护手段，请不要轻易告知他人。</span></td>
						<td class="td80"><a class="button0" href="/managermall/seller/center/updatePassWord.do">修改</a></td>
					</tr>
					<tr>
						<td class="td132">
							<c:if test="${empty seller.phone}">
								<img src="/images/seller/tu02.png" width="16" height="16" />
							</c:if>
							<c:if test="${!empty seller.phone}">
								<img src="/images/seller/tu01.png" width="16" height="16" />
							</c:if>
							&nbsp;绑定手机
						</td>
						<td class="td134">绑定手机可以方便您能及时收到平台发送的消息，了解平台动态！<br />
						<span style="color:#F30">绑定手机后，建议您不要频繁更换手机，以免您忘记修改绑定手机号而收不到信息！</span></td>
						<td class="td80"><a class="button0" href="/managermall/seller/center/updatePhone.do">修改</a></td>
					</tr>
					<tr>
						<td class="td132">
							<c:if test="${empty seller.email}">
								<img src="/images/seller/tu02.png" width="16" height="16" />
							</c:if>
							<c:if test="${!empty seller.email}">
								<img src="/images/seller/tu01.png" width="16" height="16" />
							</c:if>
							&nbsp;绑定邮箱
						</td>
						<td class="td134">绑定邮箱可以方便您能进行密码找回，以便你的登录密码忘记后能迅速找回！<br />
						<span style="color:#F30">绑定邮箱后，建议您不要频繁更换邮箱账号，以免您忘记修改绑定邮箱账号而收不到邮件！</span></td>
						<td class="td80"><a class="button0" href="/managermall/seller/center/toChangEmail.do">修改</a></td>
					</tr>
					<tr>
						<td class="td132">
							<c:if test="${empty seller.answer}">
								<img src="/images/seller/tu02.png" width="16" height="16" />
							</c:if>
							<c:if test="${!empty seller.answer}">
								<img src="/images/seller/tu01.png" width="16" height="16" />
							</c:if>
							&nbsp;密保问题
						</td>
						<td class="td134">密保问题是您找回登录密码的方式之一。<br />
						<span style="color:#F30">建议您设置容易记住，且最不容易被他人获取的问题及答案，更有效保障您的密码安全。</span></td>
						<td class="td80"><a class="button0" href="/managermall/seller/center/setQuestion.do">
						<c:if test="${empty seller.question}">设置</c:if>
						<c:if test="${!empty seller.question}">修改</c:if>
						</a></td>
					</tr>
					<tr>
						<td class="td132"><img src="/images/seller/tu01.png" width="16" height="16" /> &nbsp;实名认证</td>
						<td class="td134">用于提升账号的安全性和信任级别。<br />
						<span style="color:#F30">认证成功后的账号不能修改认证信息。</span></td>
						<td class="td80"><span style="color:#3C0">已认证</span></td>
					</tr>
				</table>
				<br><BR>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>