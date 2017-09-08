<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link href="/css/account/evaluate/add_page.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/account/evaluate/add_page.js"></script>
<script src="/js/pagingUtil.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="5"/></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
   	    <div class="member_message">
        	<div class="memberright_title">站内消息</div>
        	<ul>
        		<c:forEach items="${messageVo}" var="m">
            		<li>
            			<span><fmt:formatDate value="${m.createDate}" pattern="yyyy-MM-dd "/></span>
            			<a href="/managermall/account/message/datail.do?id=${m.id}">· ${m.title}</a>
            		</li>
            	 </c:forEach>
            </ul>
        </div>
	</div>
	<form action="/managermall/account/message/zlist.do" id="from" >
		<input id="page" name="page" type="hidden">
	</form>
	 <!--上一页下一页-->
        <div class="page">
            	<c:if test="${messageVo.getPageNum()>1}"> 
            	<a href="javascript:previousPage(${messageVo.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${messageVo.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${messageVo.getPages()}">
            			<c:choose><c:when test="${i==messageVo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${messageVo.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==messageVo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${messageVo.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${messageVo.getPages()-3}">
            			<c:if test="${i==messageVo.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${messageVo.getPageNum()<messageVo.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${messageVo.getPages()-2}" end="${messageVo.getPages()}">
            			<c:choose><c:when test="${i==messageVo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${messageVo.getPages()>messageVo.getPageNum()}"><a href="javascript:previousPage(${messageVo.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
                <span>共${messageVo.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${messageVo.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
