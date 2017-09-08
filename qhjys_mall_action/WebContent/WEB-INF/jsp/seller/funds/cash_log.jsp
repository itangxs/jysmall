<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/funds/account_overview.js"></script>
<title>飞券网商家后台中心</title>
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<!--------------右侧------------------>
	<div class="memberright">
		<h3 style="padding-left:10px; padding-bottom:20px;">资金流水表</h3>
      	<!--<span style="margin-left:200px;">免费提现额度：￥200,000.00元</span> -->
      	<form action="/managermall/seller/funds/cashLog.do"  method="post" id="from">
       		<input type="hidden" id="page" name="page" value="${page.getPageNum()}">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
	    </form>
		<input style="position:absolute;left:880px;" type="button" value="返回" class="submit6" onclick="location='/managermall/seller/funds/account/overview.do'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <div class="member_myorder">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th>日期</th>
                    <th>流动资金</th>
                    <th>资金类型</th>
                    <th>现有资金</th>
                </tr>
                <c:forEach items="${page }" var="wd" varStatus="status">
	                <tr style="text-align:center">
	                    <td><p><fmt:formatDate value="${wd.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p></td>
	                    <td>￥
	                     <c:if test="${wd.businessCode == '002' || wd.businessCode == '008' || wd.businessCode == '021'|| wd.businessCode == '022'}">
	          				 	-
	          				 </c:if>    
	                     	<c:if test="${wd.businessCode != '002' && wd.businessCode != '008' && wd.businessCode != '021'&& wd.businessCode != '022'&& wd.businessCode != '031'}">
	          				 	+
	          				 </c:if>  
	                    ${wd.amount }</td>
	                    <td>${wd.businessName }</td>
	                    <td>
		                    <c:if test="${wd.sendAfter!=null}">${wd.sendAfter}</c:if>
							<c:if test="${wd.sendAfter==null}">${wd.reviewAfter}</c:if>
					    </td>
	                </tr>
                </c:forEach>
            </table>
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
               <input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        	</div>
        </div>
    </div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
</body>
</html>
