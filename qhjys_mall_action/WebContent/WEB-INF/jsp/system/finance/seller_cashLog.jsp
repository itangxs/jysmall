<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<title>飞券网平台管理中心</title>
</head>
<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="6" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="5" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">提现管理——商家元宝流水</h3>
           <form id="from" name="from"  class="zcform1" action="/managermall/systemmall/finance/querySellerCash.do">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
        		<input type="hidden" name="pageSize" value="${page.getPageSize()}">
        		<input type="hidden" name="sellerId" value="${seller.id}">
		        
        	</form>
        	 
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        	<td>商家账号:</td><td>${seller.username }</td><td>商家手机:</td><td>${seller.phone }</td>
        </tr>
        <tr>
        	<td>&nbsp;</td>
        </tr>
                <tr>
                   <th class="td200">日期</th>
                    <th class="td200">变更前金元宝</th>
                    <th class="td200">变更数量</th>
                    <th class="td200">变更后金元宝</th>
                    <th class="td200">备注</th>
                </tr>
               <c:forEach items="${page}" var="page">
                <tr align="center">
                   <td class="td130"><fmt:formatDate value="${page.date}" pattern="yyyy-MM-dd "/></td>
					<c:if test="${empty page.reviewBefor}">
						<td><fmt:formatNumber value="${page.sendBefor}" /></td>
						<td>-<fmt:formatNumber value="${page.changeMoney}"/></td>
						<td><fmt:formatNumber value="${page.sendAfter}" /></td>
					</c:if>
					<c:if test="${empty page.sendBefor}">
						<td><fmt:formatNumber value="${page.reviewBefor}"/></td>
						<td>+<fmt:formatNumber value="${page.changeMoney}"/></td>
						<td><fmt:formatNumber value="${page.reviewAfter}"/></td>
					</c:if>
					<td class="td508">${page.remarks}</td>
                </tr>
                </c:forEach>
              </table>
        </div>
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
               		<input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
    </div>            
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>