<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath().equals("/")?"":request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<script src="<%=path %>/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/weixin/rebatestore.js" type="text/javascript"></script>
<script type="text/javascript">
 $(function(){
	 initStoreDetail();
 });
</script>
<title>飞券网-订单查询</title>
<link href="<%=path %>/css/zhifu1.css" type="text/css" rel="stylesheet" />
</head>

<body style="background-color:#f6f6f6;">
<input type="hidden" id="totamt" value="${map.totamt}"/>
<input type="hidden" id="realPay" value="${map.realPay}"/>
<input type ="hidden" id="rebate"  value="${map.rebate} ">
<input type ="hidden" id="noDiscount"  value="${map.noDiscount} ">
<div class="orderdetail">
   <div class="orderdetailbox">
   		<ul>
        	<li>
            	<div class="left">
                	<img src="<%=path %>/images/gou-ico.png">
                    <div class="leftfont"><p><span class="font18">${map.realPay}</span> <span class="font14">元</span></p>
                    <p class="font14">成功支付</p></div>                    
                </div>
                <div class="right"><span class="fontline">¥ ${map.totamt}</span></div>
            </li>
            <li><img class="img30" src="<%=path %>/images/ico-01.png">商家信息<span class="right">${map.name}</span></li>
            <li>
                <div><img class="img30" src="<%=path %>/images/yhje.png"><div class="right"><span class="red" id="totalDiscount"></span></div></div>
            </li>
            <li>
            	<div><span class="red">商家优惠${map.rebate}折</span><div class="right"><span class="red" id="storeDiscount"></span></div></div>
            </li>
            <li>
            	<div>不参与优惠金额<div class="right">${map.noDiscount}</div></div>
                <div>交易时间<div class="right">${map.payTime}</div></div>
                <div>交易订单 <div class="right">${map.orderNo}</div></div>
            </li>
        </ul>
   </div>
</div>
</body>
</html>
