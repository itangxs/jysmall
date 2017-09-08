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
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="10" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/cms/uLeft.jsp" flush="true" ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">CMS大类列表</h3>
			<form id="queryOrder" action="/managermall/systemmall/cms/cmsListCategory.do" method="post" class="zcform1">
				<p class="clearfix" style="margin-bottom:10px;">
					<label class="one" for="con-email4">父类：</label>
					<select name="fatherId">
					 <option value="">请选择</option>
		        	 <c:forEach items="${cmsCategoryList }" var="cmsCategory1" >
		             <option value="${cmsCategory1.id }"<c:if test="${fatherId==cmsCategory1.id }"> selected="selected"</c:if>>${cmsCategory1.name }</option>
		             </c:forEach>
		            </select>
					<label class="one" for="con-email4">是否启用：</label>
					<select name="enabled">
					<option value="">请选择</option>
		        	<option value="0"<c:if test="${enabled==0 }"> selected="selected"</c:if>>不启用</option>
		        	<option value="1"<c:if test="${enabled==1 }"> selected="selected"</c:if>>启用</option>
		        	</select>
					<input type="submit" value="查询" class="submit8" style="margin-left:20px">
					
				</p>
				
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td160">类别名称</th>
						<th class="td160">是否启用</th>
						<th class="td160">是否可以有下级</th>
						<th class="td160">修改</th>
					</tr>
					<c:forEach var="it" items="${cmsCategoryList}">
					<tr>
						<td class="td160">${it.name}</td>
						<td class="td160"><c:if test="${it.enabled==0}">不启用</c:if><c:if test="${it.enabled==1}">启用</c:if> </td>
						<td class="td160"><c:if test="${it.yezi==0}">允许</c:if><c:if test="${it.yezi==1}">不允许</c:if></td>
						<td class="td160"><a href="/managermall/systemmall/cms/addCmsCategory.do?id=${it.id}">修改</a></td>
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
