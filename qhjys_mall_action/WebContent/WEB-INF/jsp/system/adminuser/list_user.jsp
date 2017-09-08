<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/adminuser/list.js"></script>
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
	<jsp:include page="/WEB-INF/jsp/system/adminuser/uLeft.jsp" flush="true"  ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">用户列表</h3>
			<form id="queryOrder" action="/managermall/systemmall/user/userList.do" method="post" class="zcform1">
				<input type="hidden" id="pageNum" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				<p class="clearfix" style="margin-bottom:10px;">
					<label class="one" for="con-email4">用户名：</label>
					<input name="username" class="con-email4" value="${username}">
					<label class="one" for="con-email4">姓名：</label>
					<input name="realname" class="con-email4"  value="${realname}">
					
				</p>
				<p class="clearfix">
					<label class="one" for="con-email4"> 用户组：</label>
					<select name="groupId">
					<option value="">请选择</option>
		        	 <c:forEach items="${adminGroupList }" var="adminGroup" >
		             <option value="${adminGroup.id }"<c:if test="${adminGroup.id==groupId }"> selected="selected"</c:if>>${adminGroup.groupName }</option>
		             </c:forEach>
		            </select>
					<label class="one" for="con-email4"> 启用：</label>
					<select name="enabled">
					<option value="">请选择</option>
		             <option value="1"<c:if test="${enabled==1 }"> selected="selected"</c:if>>启用</option>
		             <option value="0"<c:if test="${enabled==0 }"> selected="selected"</c:if>>未启用</option>
		            </select>
					<input type="submit" value="查询" class="submit8" style="margin-left:20px">
				</p>
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td160">用户名</th>
						<th class="td160">姓名</th>
						<th class="td160">最近登录时间</th>
						<th class="td160">所属组</th>
						<th class="td160">是否启用</th>
						<th class="td160">修改</th>
					</tr>
					<c:forEach var="it" items="${adminUserList}">
					<tr>
						<td class="td160">${it.username}</td>
						<td class="td160">${it.realname}</td>
						<td class="td160">
						<fmt:formatDate value="${it.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss "/>
						</td>
						<td class="td160">
						<select name="groupId${it.id}" id="groupId${it.id}" onchange="changeGroup(this.value,'${it.id}')">
			        	 <c:forEach items="${adminGroupList }" var="adminGroup" >
			             <option value="${adminGroup.id }"<c:if test="${adminGroup.id==it.groupId }"> selected="selected"</c:if>>${adminGroup.groupName }</option>
			             </c:forEach>
			            </select>
						</td>
						<td class="td160">
						<select name="enabled${it.id}" id="enabled${it.id}" onchange="changeEnabled(this.value,'${it.id}')">
			             <option value="1"<c:if test="${it.enabled==1 }"> selected="selected"</c:if>>启用</option>
			             <option value="0"<c:if test="${it.enabled==0 }"> selected="selected"</c:if>>未启用</option>
			            </select>
						</td>
						<td class="td160"><a href="/managermall/systemmall/user/addUser.do?id=${it.id}">修改</a></td>
					</tr>					
					</c:forEach>
				</table>
			</div>
			<!--上一页下一页-->
			<%-- <div class="page">
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
				<span>共${page.getPages()}页</span><span>到第</span>
				<input class="input1" id="jumPage" name="jumpPage" type="text" /><span>页</span>
			</div> --%>
		</div>
		<div class="clear"></div>
	</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
