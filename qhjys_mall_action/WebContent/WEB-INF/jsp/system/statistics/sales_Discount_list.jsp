<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/statistics/sales_discount_list.js"></script>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp" flush="true" ><jsp:param name="position" value="7" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">折扣明细报表</h3>
           <form id="from" method="post" action="/managermall/systemmall/statistics/salesDiscountList.do">
    			<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
            <p class="clearfix">
        	<label class="one" for="con-email4" >订单号：</label>
        	<input class="con-email4" name="orderNo" value="${orderNo}" >
            <label class="one" for="con-email4" >折扣名称：</label>
        	<input class="con-email4" name="rebateName" value="${rebateName }" >     
            <label class="one" for="con-email4" >店铺名称：</label>
        	<input class="con-email4" name="storeName" value="${storeName}" > 
        	 </p>
        	 <p class="clearfix" style="margin-top:8px;">
             <label class="one" for="con-email4" >商家ID：</label>
        	<input class="con-email4" name="sellerId" value="${sellerId}" >
        	<label class="one" for="con-email4" >支付时间：</label>       
        	<input id="begin" name="benginTime" class="easyui-datebox con-time" value="${benginTime}">
			<label class="one" for="con-email2">-</label>
			<input id="ending" name="endTime" class="easyui-datebox con-time" value="${endTime}">
            <label class="one" for="con-email4" ><!--每页显示：</label>
        	<input class="con-email4" name="pageSize" value="${pageSize}">条--> 
         </p>
         <p class="clearfix" style="margin-top:8px;">
             <label class="one" for="con-email4" >会员名称：</label>
        	<input class="con-email4" name="openId" value="${openId}" >
             <label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
             <input type="submit" value="查询" class="submit8">
         </p>
        </form>
         <p class="clearfix">
             <input type="button" value="导出Excel" class="submit9" onclick="Excel()"> 
            <!--  <input type="submit" value="导出Html" class="submit9">  -->         
           </p>
        <div class="member_myorder">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>  
                    <th class="td100">订单号</th>
                    <th class="td100">店铺名称</th>
                    <th class="td130">买家</th>
                    <th class="td50">商家ID</th>
                    <th class="td60">折扣名称</th>
                    <th class="td40">抵用券</th>          
                    <th class="td40">金元宝</th> 
                    <th class="td60">微信支付</th>
                    <th class="td60">实付金额</th>
                    <th class="td80">未折扣金额</th>
                    <th class="td50">原价</th>
                    <th class="td80">支付时间</th>
                    <th class="td40">操作</th>    
                </tr>

                <c:forEach items="${page}" var="s">
                <tr>
                    <td class="td100">${s.orderNo }</td>
                    <td class="td100">${s.storeName }</td>
                    <td class="td130">${s.openId }</td> 
                    <td class="td50">${s.sellerId }</td> 
                    <td class="td60">${s.rebateName }</td>  
                    <td class="td50">${s.couponId }</td>
                    <td class="td50">${s.integral }</td>
                    <td class="td60">${s.needPay }</td>
                    <td class="td60">${s.realPay }</td> 
                    <td class="td80">${s.noDiscount }</td>  
                    <td class="td50">${s.totamt }</td>  
                    <td class="td80"><fmt:formatDate value="${s.payTime }" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td class="td40"><a href="/managermall/systemmall/statistics/getRebateOrder.do?id=${s.id }">查看</a></td>
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
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
