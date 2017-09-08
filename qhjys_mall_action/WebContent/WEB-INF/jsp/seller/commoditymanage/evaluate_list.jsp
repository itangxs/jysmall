<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/pagingUtil.js"></script>
</head> 
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="11" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
    	<form action="/managermall/seller/commoditymanage/queryEvaluateList.do" method="post" id="from">
    		<input name="page" id="page" type="hidden">
           <p class="clearfix">
           <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品名称：</label>
        	<input id="productName" class="con-email" name="productName" value="${productName }">
    
             <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit6">
           </p>
        </form>
        <div class="member_myorder">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                    <th class="td130">会员名</th>
                    <th class="td508">商品名称</th>
                    <th class="td130">评分</th>
                    <th class="td130">评论时间</th>
                    <th class="td80">操作</th>
                </tr>
            </table>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<c:forEach var="page" items="${page}">
				<tr>
					<td class="td130">${page.userAccount}***</td>
					<td class="td508">${page.prodName}</td>
					<td class="td130">${page.score}</td>
					<td class="td130"><fmt:formatDate value="${page.revDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="td80"><a href="/managermall/seller/commoditymanage/queryEvaluateDetail.do?reviewId=${page.id}" class="fontred">查看</a></td>
				</tr>
				</c:forEach>
            </table>
   	   	    <c:if test="${page.getPages()==0}">
		    	<div align="center">
		  			<font  color="red">暂时没有数据!</font>
		    	</div>
	    	</c:if>
            <!--分页-->
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
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" /> 
</body>
</html>