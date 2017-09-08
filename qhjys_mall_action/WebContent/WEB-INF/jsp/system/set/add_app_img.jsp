<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/set/addappimg.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="10" /></jsp:include>
	<div class="membercontent">
		<!----------------左侧------------------>
		<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true"><jsp:param name="val" value="8" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">网站设置——APP端九宫格管理</h3>
			<form id="form1" method="post" class="zcform" action="/managermall/systemmall/set/saveAppImg.do">
				城市选择:<select id="cityId" name="cityId" onchange="changecity()">
					<option <c:if test="${cityId == 200 }">selected="selected"</c:if> value="200">深圳</option>
					<option <c:if test="${cityId == 203 }">selected="selected"</c:if> value="203">佛山</option>
				</select>
				<c:forEach var="img" varStatus="i" items="${img}">
				<p class="clearfix">
					<label class="one" for="con-email4" style="width:120px;">九宫格图片${i.index + 1}地址： </label>
					<img id="appImg${i.index + 1}1" width="150" height="120" src="${img.img}" />
					<br><label class="one" for="con-email4">店铺ID: </label>
					<input type="text" name="storeId${i.index + 1}" value="${img.field}"/>
					<input type="hidden" id="appImg${i.index + 1}" name="appImg${i.index + 1}" value="${img.img}" /><br><br>
					<input type="file" value="上传" id="appImg${i.index + 1}Butn" class="submit8">
				</p>
				</c:forEach>
				<p class="clearfix">
					<input type="submit" value="保存" class="submit8" style="margin-left: 150px; margin-top:10px; color:#fff;">
				</p>
			</form>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
</body>
</html>
