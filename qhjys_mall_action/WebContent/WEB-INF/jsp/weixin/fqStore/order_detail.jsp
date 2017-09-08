<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>订单详情</title>
<link href="/css/fqzhifu.css" type="text/css" rel="stylesheet" />
</head>

<body>

<div class="orderdetail">
   <div class="orderdetailbox">
   		<ul>
            <!--<li>当前已选择时间<div class="right"><span class="red"><fmt:formatDate value="${order.preordainDate}" pattern="yyyy-MM-dd HH:mm"/> </span></div></li>
            <li>当前已确定用餐人数<div class="right"><span class="red">${order.peopleNum}位</span></div></li> 
            <li>联系人姓名
                <div class="right"><span class="red">${order.contacts} 
                <c:if test="${empty order.contactsSex}">先生/女士</c:if>
                <c:if test="${order.contactsSex==0}">女士</c:if><c:if test="${order.contactsSex==1}">先生</c:if></span>
                </div>
            </li>
            <li>联系人电话<div class="right"><span class="red">${order.phoneNum}</span></div></li>
              <li>订单编号<div class="right"><span class="red">${order.orderNo }</span></div></li>-->
              <li>餐桌号<div class="right"><span class="red">${order.deskNo }</span></div></li>
            <li>当前已选择菜品：</li>
            <li>
                <ul>
                <c:set var="quantitys" value="0"></c:set>
                <c:set var="prices" value="0"></c:set>
                <c:forEach items="${detail}" var="detail">
                	<li><span class="arr">·</span>${detail.productName}<span class="red">×${detail.quantity}</span></li>
                		 <c:set value="${quantitys + detail.quantity}" var="quantitys" />
                		 <c:set value="${prices + detail.price}" var="prices" />
                </c:forEach>
                    <div class="clear"></div>
                </ul>
            </li>
            <li>共计<span class="red">${quantitys}</span>份 合计：<span class="red">¥<fmt:formatNumber value="${prices}" pattern="#,#0.00#"/></span><div class="right">折扣价：<span class="red">¥<fmt:formatNumber value="${order.rebateAmount}" pattern="#,#0.00"/></span></div></li>
        </ul>
   </div>
   
</div>
</body>
</html>
