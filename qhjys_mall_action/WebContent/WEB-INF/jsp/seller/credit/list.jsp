<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/seller/funds/account_overview.js"></script>
<title>飞券网商家后台中心</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="4" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    	<div class="excelbutton"></div>
        <h3 style="padding-left:10px; padding-bottom:20px;">借款信息总览</h3>
       
        <c:if test="${empty credit }">
        	<p class="clearfix">
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 暂无借贷数据</label>            
        </p>
        </c:if>
        <c:if test="${not empty credit }">
        	<p class="clearfix">
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 本期借贷金额：</label>            
	        <label class="one4" for="con-email2"  >
	        <fmt:formatNumber value="${credit.creditMoney}"/></label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 上期未还金额：</label>            
	        <label class="one4" for="con-email2"  >
	        <fmt:formatNumber value="${credit.beforeMoney}"/></label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        </p>
        <p class="clearfix">
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 总欠款金额：</label>            
	        <label class="one4" for="con-email2"  >
	        <fmt:formatNumber value="${credit.totalMoney}"/></label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>	   
	        <label class="one" for="con-email2" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 未还金额：</label>            
	        <label class="one4" for="con-email2"  >
	        <fmt:formatNumber value="${credit.noRevert}"/></label>
	        <label for="sunbmit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>	   
        </p>
        <form action="/managermall/seller/credit/list.do"  method="post" id="from">
            <input type="hidden" id="page" name="page">
    		<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
			<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
        </form>
        <div class="member_myorder">
   	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                   <th class="td130">日期</th>
                    <th class="td130">金额</th>
                    <th class="td130">订单号</th>
                    <th class="td130">收入来源</th>
                    <th class="td130">支付方式</th> 
                </tr>
              <c:forEach items="${page}" var="page">
                <tr style="text-align: center;">
                   <th class="td130" style="text-align: center;"><fmt:formatDate value="${page.payTime}" pattern="yyyy-MM-dd "/></th>
						
						<td><fmt:formatNumber value="${page.money}" type="currency"/></td>
						<td>${page.orderNo }</td>
						<td><c:if test="${page.sort == 1 }">优惠买单</c:if><c:if test="${page.sort == 2 }">优惠点餐</c:if></td>
						<td><c:if test="${page.type == 1 }">微信支付</c:if><c:if test="${page.type == 2 }">支付宝支付</c:if></td>
						
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
        </c:if>
        
    </div>
  <div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
