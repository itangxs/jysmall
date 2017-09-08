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
	<jsp:include page="/WEB-INF/jsp/system/finance/uLeft.jsp" flush="true" ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">资金日志——资金操作日志</h3>
           <form id="from" name="from"  class="zcform1">
    			<input type="hidden" id="page" name="page">
        	</form>
         <form id="form1" name="form1"  class="zcform1">
           <p class="clearfix">
             <input type="submit" value="导出全部" class="submit9">
           </p>
        </form>
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td160">会员名</th>
                    <th class="td160">操作日期</th>          
                    <th class="td160">金额</th>
                    <th class="td160">业务类型</th>
                </tr>
                <c:forEach items="${page}" var="page">
	                <tr>
	                    <td class="td160">${page.userName}</td>
	                    <td class="td160"><fmt:formatDate value="${page.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    <td class="td160">${page.amount}</td>
	                    <c:if test="${page.payType == 1}">
	                    	<td class="td160">会员充值</td>
	                    </c:if>
	                     <c:if test="${page.payType == 2}">
	                    	<td class="td160">会员提现</td>
	                    </c:if>
	                     <c:if test="${page.payType == 5}">
	                    	<td class="td160">购物</td>
	                    </c:if>
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