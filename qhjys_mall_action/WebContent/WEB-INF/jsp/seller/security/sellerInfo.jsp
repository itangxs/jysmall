<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -验证消费卷</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/security/setSeller.js"></script>
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
							<a href="/managermall/seller/center/setSellerInfo.do" class="menucurrent"><span>·</span>账户信息</a>
						</p>
						<p>
							<a href="/managermall/seller/center/center.do"><span>·</span>安全设置</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
		<!--------------右侧------------------>
		<div class="memberright">
			<div class="member_myorder">
				<h3 style="padding-left: 10px;">账户信息</h3>
				<form id="form" method="post" action="changeSellerExpand.do" class="zcform" style="margin-left:150px;">
					<p class="clearfix">
						<label class="one" for="con-email" style="width:50px;">姓名：</label>
						<input id="realname" name="name" class="con-email" value="${seller.realname}">
					</p>
					<p class="clearfix">
						<label for="con-email" class="one" style="width:50px;">性别：</label>
						<label class="one3">
							<input type="radio" style="margin-top: 10px;" value="1" name="sex" <c:if test="${expand.sellerSex==1}">checked="checked"</c:if>>男
						</label>
						<label class="one3">
							<input type="radio" style="margin-top: 10px;" value="2" name="sex" <c:if test="${expand.sellerSex==2}">checked="checked"</c:if>>女
						</label>
					</p>
					<%-- <p class="clearfix">
						<label class="one" for="con-email">电话：</label>
						<input id="phone" name="confirm" class="con-email" value="${seller.phone}" readonly="readonly">
					</p>
					<p class="clearfix">
						<label class="one" for="con-email">邮箱：</label>
						<input id="email" name="confirm" class="con-email" value="${seller.email}" readonly="readonly">
					</p> --%>
					<p class="clearfix" style="margin-bottom: 0px;">
						<label class="one" for="con-email" style="width:50px;">地址：</label>
						<input id="prov" name="prov" value="${expand.province}" type="text"
							style="width: 90px; height: 30px;"><input id="city" name="city" value="${expand.city}"
							style="width: 90px; height: 30px;"><input id="area" name="area" value="${expand.area}"
							style="width: 90px; height: 30px;">
					</p>
					<p class="clearfix" style="margin-top:5px;">
						<label class="one" for="con-email" style="width:50px;"></label>
						<input id="address" name="address" value="${expand.address}" class="con-email" >
					</p>
					
					<p class="clearfix">
						<input type="submit" value="确认修改" class="submit22" style="margin-left: 80px;">
					</p>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>