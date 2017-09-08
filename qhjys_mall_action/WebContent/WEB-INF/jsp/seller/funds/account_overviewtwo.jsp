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
<script type="text/javascript" src="/js/seller/funds/account_overviewtwo.js"></script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    	<div class="excelbutton"><a href="javascript:excel();">导出Excel表格</a></div>
        <h3 style="padding-left:10px; padding-bottom:20px;">商家账户总览</h3>
        <%-- <p class="clearfix">
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;总金额：</label>
	        <label class="one4" for="con-email2"  >${sessionScope.sellerCashAccount.balance}元</label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	        <input type="button" value="充值" class="submit6" onclick="window.location.href='/managermall/seller/funds/toRecharge.do'" >
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	       
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        </p><br> --%>
        <p class="clearfix">
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 累积消费金额：</label>            
	        <label class="one4" for="con-email2"  >
	        <c:if test="${empty cash }">暂无数据</c:if>
	        <fmt:formatNumber value="${cash.realTotal}"/></label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        </p>
        <p class="clearfix">
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 累计总额：</label>            
	        <label class="one4" for="con-email2"  >
	        <c:if test="${empty cash }">暂无数据</c:if>
	        <fmt:formatNumber value="${cash.totamtTotal}"/></label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>	   
        </p>
        <form action="/managermall/seller/funds/account/overview.do"  method="post" id="from">
            <input type="hidden" id="page" name="page">
            <p class="clearfix">
	            <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;操作时间：</label>
	            <input id="kssj" name="startTime" class="con-email3"  type="text" readonly="readonly" value="${startTime}" />
	            <img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'kssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
	            <!-- <input class="con-email3" name="dates"  > --> 
	            <label class="one" for="con-email2" >-</label>
	            <input id="jssj" name="endTime"  class="con-email3"  type="text" readonly="readonly"  value="${endTime}"/>
	        	<img onclick="WdatePicker({startDate:'yyyy-MM-dd',dateFmt:'yyyy-MM-dd',readOnly:true,el:'jssj'})" src="/common/My97DatePicker/skin/datePicker.gif" width="16" height="22"  align="absmiddle"> 
	        	<!-- <input class="con-email3" name="datee" > -->
	           
	            <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
	            <input type="submit" value="查询" class="submit6">
            </p>
        </form>
        <div class="member_myorder">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                   <th class="td130">日期</th>
                    <th class="td130">抵用券</th>
                    <th class="td130">微信支付</th>
                    <th class="td130">实付金额</th> 
                    <th class="td130">原价</th> 
                    <th class="">备注</th>
                </tr>
              <c:forEach items="${page}" var="page">
                <tr style="text-align: center;">
                   <th class="td130" style="text-align: center;"><fmt:formatDate value="${page.createTime}" pattern="yyyy-MM-dd "/></th>
						<td>无</td>
						<td><fmt:formatNumber value="${page.needPay}" type="currency"/></td>
						<td><fmt:formatNumber value="${page.realPay}" type="currency"/></td>
						<td>+<fmt:formatNumber value="${page.totamt}" type="currency"/></td>
						<td>销售入账</td>
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
<!--底部-e-->
</body>
</html>