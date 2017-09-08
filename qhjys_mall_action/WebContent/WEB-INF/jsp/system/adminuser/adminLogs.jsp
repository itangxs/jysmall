<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>
<body>
	<jsp:include page="../system_header.jsp" flush="true"><jsp:param name="val" value="4" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="uLeft.jsp" flush="true"><jsp:param name="position"
				value="3" /></jsp:include>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">消费积分操作日志</h3>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="">
					<tr>
						<th class="td80">日志编号</th>
						<th class="td80">管理员编号</th>
						<th class="td132">管理员名字</th>
						<th class=td134>操作日志</th>
						<th class="td80">操作类型</th>
						<th class="td80">操作IP地址</th>
						<th class="td160">操作时间</th>
					</tr>
					<c:forEach items="${page}" var="p">
					<tr>
						<td class="td80">${p.id}</td>
						<td class="td80">${p.adminId}</td>
						<td class="td132">${p.name}</td>
						<td class="td134">${p.info}</td>
						<td class="td80">${p.action}</td>
						<td class="td80">${p.IP}</td>
						<td class="td160">${p.time}</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<!--上一页下一页-->
			<div class="page">
				<c:if test="${page.getPageNum()>1}"><a href="javascript:;" class="prev">上一页</a></c:if>
				<c:choose>
				<c:when test="${page.getPages()<7}">
					<c:forEach var="i" begin="1" end="${page.getPages()}">
						<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
						<c:otherwise><a href="javascript:;">${i}</a></c:otherwise></c:choose>
					</c:forEach>
				</c:when>
				<c:when test="${page.getPages()>6}">
					<c:forEach var="i" begin="1" end="3">
						<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
						<c:otherwise><a href="javascript:;">${i}</a></c:otherwise></c:choose>
					</c:forEach>
					<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
					<c:forEach var="i" begin="4" end="${page.getPages()-3}">
						<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
					</c:forEach>
					<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
					<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
						<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
						<c:otherwise><a href="javascript:;">${i}</a></c:otherwise></c:choose>
					</c:forEach>
				</c:when>
				</c:choose>
				<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:;" class="next">下一页</a></c:if>
				<span>共${page.getPages()}页</span>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<!------------------------------底部---------------------------------------------->
	<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
	<!--底部-e-->
</body>
</html>
