<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/adminuser/addrole.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
	<!-------------------top---------------------------------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="9" /></jsp:include>
	<!--------------------------我的账户-------------------------------------------------------->
	<div class="membercontent">
		<!----------------左侧----------------------->
		<jsp:include page="/WEB-INF/jsp/system/adminuser/uLeft.jsp" flush="true"><jsp:param name="val"
				value="4" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">增加权限</h3>
			<form id="form1" name="form1" class="zcform1" method="post" action="/managermall/systemmall/userrole/saveRole.do">
				<input type="hidden" value="${roleMenu.id}" name="id" />
				<p class="clearfix">
					<label class="one" for="con-email4">菜单名:</label> <input class="con-email4" name="name" id="name"
						value="${roleMenu.name }" />
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4">权限URL:</label>
					<textarea rows="9" cols="90" name="roleText">${roleMenu.roleText }</textarea>
				</p>
				<p class="clearfix">
					<input type="submit" value="增加" class="submit10">
				</p>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<!------------------------------底部---------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
	<!--底部-e-->
</body>
</html>
