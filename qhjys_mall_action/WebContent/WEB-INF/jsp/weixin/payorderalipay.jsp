<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
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
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>支付宝支付</title>
<link href="/css/weixin/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/weixin/alipay.js"></script>
</head>
<body>
 <div class="banner" id="banner">
    <img src="/images/banner.png">
 </div>  
 
 <div class="content">
 	<ul>
    	<li>
    	<input type="hidden" id="orderId" value="${order.id }"/>
    	<input type="hidden" id="status" value="${order.status }"/>
        	<span class="left-float">订单</span>
            <span class="right-float">${order.orderNo }</span>
            <div class="clearfix"></div>
        </li>
        <li>
        	<span class="left-float">下单时间</span>
            <span class="right-float"><fm:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
            <div class="clearfix"></div>
        </li>
        <li>
        	<span class="left-float">共计金额</span>
            <span class="right-float new">¥<fm:formatNumber value="${order.realPay}" type="currency" pattern="0.00"/></span>
            <div class="clearfix"></div>
        </li>
    </ul>
 </div>     
 <div id="alipaydiv"></div>          
</body>
</html>