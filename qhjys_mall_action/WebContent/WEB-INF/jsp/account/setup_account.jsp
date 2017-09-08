<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="9"/></jsp:include>
    <!--------------右侧------------->
    <c:set var="percent" value="6" />
	<c:if test="${empty user.password}"><c:set var="percent" value="${percent-1}" /></c:if>
	<c:if test="${empty userCashAccount.payCode}"><c:set var="percent" value="${percent-1}" /></c:if>
	<c:if test="${empty user.phoneNum}"><c:set var="percent" value="${percent-1}" /></c:if>
	<c:if test="${empty user.email}"><c:set var="percent" value="${percent-1}" /></c:if>
	<c:if test="${empty user.question}"><c:set var="percent" value="${percent-1}" /></c:if>
	<c:if test="${empty user.realname}"><c:set var="percent" value="${percent-1}" /></c:if>
    <div class="memberright">
	<div class="memberright_title">账户设置</div>
		<div class="member_setup">
			<ul>
				<li>
					<h1>用户：${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}</h1>
					<h2></h2>
				</li>
				<li>
					<c:if test="${isUserExpand == true}">
						<h1>个人资料：已完善</h1>
						<h2><a href="/managermall/account/setupUserInfo.do">修改个人资料</a></h2>
					</c:if>
					<c:if test="${isUserExpand == false}">
						<h1>个人资料：尚未完善</h1>
						<h2><a href="/managermall/account/setupUserInfo.do">完善个人资料</a></h2>
					</c:if>
				</li>
				<li>
					<h1>安全中心：您的账号安全等级：
							<c:if test="${percent<3}">${percent}低</c:if>
							<c:if test="${percent > 2 && percent<6}">中</c:if>
							<c:if test="${percent==6}">高</c:if>
					</h1>
					<h2><a href="/managermall/account/setupUserSafe.do">设置</a></h2>
				</li>
				<!-- <li>
					<h1>收货地址</h1>
					<h2><a href="/managermall/account/queryAddressList.do">添加新地址</a></h2>
				</li> -->
			</ul>
			<!-- <h3>
				<span>可用余额：</span>
				<a href="/managermall/account/rechargeList.do" class="fontblue">查询</a> |
				<a href="/managermall/account/myRecharge.do" class="fontblue">充值</a> |
				<a href="/managermall/account/myWithdraws.do" class="fontblue">提现</a> |
			</h3> -->
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>