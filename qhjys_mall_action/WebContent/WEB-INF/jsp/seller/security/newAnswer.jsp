<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心 -验证消费卷</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/seller/security/setQuestion.js"></script>
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
				<h3 style="padding-left: 10px;">修改密保问题</h3>
				<br>
				<div class="zcform">
					<p class="clearfix">
						<label for="con-email" class="one">请输入密保问题：</label>
						<input id="question" name="question" class="con-email">
					</p>
					<p class="clearfix">
						<label class="one" for="con-email">请输入密保答案：</label>
						<input id="answer" name="answer" class="con-email">
					</p>
					<a id="addQuestion" href="javascript:;" style="float:left;margin-left:15em;color:#FFF;" class="button0">下一步</a>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>