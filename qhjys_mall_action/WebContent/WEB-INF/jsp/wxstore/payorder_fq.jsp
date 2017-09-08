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
<title>订单确认</title>
<link href="/css/zhifu.css" type="text/css" rel="stylesheet" />
<link href="/css/form.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script src="/js/weixin/jweixin-1.0.0.js"/></script>
<script type="text/javascript" src="/js/formcheck.js"></script>
<script type="text/javascript" src="/js/wxstore/topayorder.js"></script>
</head>
<body>
<div class="feiquandiv">
  <ul>        	
      <li><span class="textblack">资料信息：</span></li>
      <li><div class="left">商家名称</div><div class="right">${order.peopleNum }</div></li>
      <li><div class="left">收件人姓名</div><div class="right">${order.contacts }</div></li>
      <li><div class="left">联系电话</div><div class="right">${order.phoneNum }</div></li>
      <li><div class="left">收件地址</div><div class="right">${order.deskNo }</div></li>
  </ul>
</div>
<div class="orderdetail">
	<div class="orderdetailbox">
   		<ul>
        	<li><span class="textblack">当前已选择商品：</span></li>
            <li>
                <ul class="feiquaninfo">
                <c:forEach items="${details }" var="detail">
                	<li><span class="arr">·</span>${detail.productName }<span class="red">×${detail.quantity }</span></li>
                </c:forEach>
                </ul>
            </li>
            <li>共计<span class="red">${totleNum }</span>份 合计：<span class="red">¥<fm:formatNumber value="${order.totalAmount }" type="currency" pattern="0.00"/></span>
            <div class="right">折扣价：<span class="red">¥<fm:formatNumber value="${order.rebateAmount }" type="currency" pattern="0.00"/></span></div>
            </li>
        </ul>
   </div>   
</div>
<div class="orderbottombox">
	<input type="hidden" value="${channelVali }" id="channelVali">
	<input type="hidden" value="${order.id }" id="orderId">
	<input type="hidden" value="${order.rebateAmount }" id="rebateAmount">
	<input type="hidden" value="${store.proportion }" id="proportion">
	<input type="hidden" value="1" id="payType">
	<div class="paybutton"><a href="javascript:payorder();">确认支付</a></div>
</div>
<div id="alipaydiv"></div>
</body>
</html>