<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-折扣管理</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">

<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="21_1" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<form id="from" name="query" method="post" class="zcform1" action="/managermall/seller/fqRebate_list.do">
				<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
			</form>
			<input type="button" value="添加折扣" class="submit6" onclick="javascript:window.location.href='/managermall/seller/fqRebate_page.do'">
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td130">折扣</th>
						<th class="td80">是否可用</th>
						<th class="td130">开始时间</th>
						<th class="td80">结束时间</th>
						<th class="td130">操作</th>
					</tr>
					<c:forEach var="rebate" items="${page}">
					<tr>
					    <td class="td130">
					    	${rebate.rebate}
					   </td>
						
						<td class="td130">
							<c:if test="${rebate.enable==0}">不可用</c:if>
							<c:if test="${rebate.enable==1}">可用</c:if>
						</td>
						
						<td class="td130">${rebate.beginTime}
						</td>
						
						<td class="td130">${rebate.endTime}
						</td>
						
						<td class="td130">
							<a href="/managermall/seller/fqRebate_page.do?id=${rebate.id}">详情/修改</a><br />
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
  	   	    <c:if test="${page.getPages()==0}">
		    	<div align="center">
		  			<font  color="red">暂时没有数据!</font>
		    	</div>
	    	</c:if>
			<!--上一页下一页-->
	    <div class="page">
            	<c:if test="${page.getPageNum()>1}"> 
            	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
                <span>共${page.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        	</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>