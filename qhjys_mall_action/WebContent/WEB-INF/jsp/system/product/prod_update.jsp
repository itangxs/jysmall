<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<title>飞券网平台管理中心</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="2" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/system/product/uLeft.jsp" flush="true"><jsp:param name="val" value="1" /></jsp:include>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">商品管理——修改商品</h3>
			<form class="zcform1" method="post" action="updateProdTitle.do">
				<input type="hidden" name="prodId" value="${info.id}">
				<p class="clearfix">
					<label style="width:100px;display:inline-block;">商品名称：</label>
					<label>${info.name}</label>
				</p>
				<p class="clearfix">
					<label style="width:100px;display:inline-block;">商品Title：</label>
					<label>${info.title}</label>
				</p>
				<p class="clearfix">
					<label style="width:100px;display:inline-block;">置顶等级：</label>
					<input class="con-email" name="level" value="${info.level}">(0-10)
				</p>
				<p class="clearfix">
					<label style="width:100px;display:inline-block;">Keywords：</label>
					<input class="con-email" name="keywords" value="${info.keywords}">
				</p>
				<p class="clearfix">
					<label style="width:100px;display:inline-block;">description：</label>
					<input class="con-email" name="description" value="${info.description}">
				</p>
				<p class="clearfix">
					<input type="submit" value="保存" class="submit6" style="width:80px;margin-left:200px;">
				</p>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
</body>
</html>