<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<script type="text/javascript" src="/js/account/preordain/list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="2"/></jsp:include>
    <!--------------右侧------------->
	<div class="memberright">
		<div class="memberright_title">我的预定</div>
		<div class="tabs">
			<a href="/managermall/account/preordain/list.do" <c:if test="${empty status}">class="tabaction"</c:if>>全部</a>
			<a href="/managermall/account/preordain/list.do?status=1" <c:if test="${status==1}">class="tabaction"</c:if>>待确认</a>
			<a href="/managermall/account/preordain/list.do?status=2" <c:if test="${status==2}">class="tabaction"</c:if>>已确认</a>
			<div class="clear"></div>
		</div>
		<div class="member_myorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th class="td588">项目</th>
					<th class="td130">预定时间</th>
					<th class="td130">状态</th>
					<th class="td80">操作</th>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<c:forEach items="${pageinfo}" var="p">
				<tr>
					<td class="td588">
						<a href="listdetail.do"><img src="images/pro_adv1.jpg" /></a>
						<!-- <p><a href="listdetail.do" class="fontred">三个贵州人</a></p> -->
						<ul>
							<li>预定商品：${p.prodName }</li>
							<li><span>人数：${p.reserNum}</span>预定人：${p.userName}</li>
						</ul></td>
					<td class="td130">
						<fm:formatDate value="${p.reserTime}" pattern="yyyy-MM-dd " /> <br />
						<fm:formatDate value="${p.reserTime}" pattern="HH:mm:ss" /></td>
					<td class="td130"><c:if test="${p.status==1}">待确认</c:if> <c:if test="${p.status==2}">已确认</c:if></td>
					<td class="td80">
						<p>
							<a onclick="delet(${p.id})" style="cursor: pointer;">取消</a> <br /> <a href="${p.prodId}">查看</a>
						</p>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>