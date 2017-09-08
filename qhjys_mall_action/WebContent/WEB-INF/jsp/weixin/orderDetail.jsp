<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath().equals("/")?"":request.getContextPath();
%>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	//return ;
}  
%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/js/weixin/rebatestore.js" type="text/javascript"></script>
<script type="text/javascript">
 $(function(){
	 initStoreDetail();
 });
</script>
<title>飞券网（jysmall.com）-比团购更划算的积分通兑、优惠消费商城平台。做任务！赢积分！换优惠!</title>
<link href="/css/zhifu1.css" type="text/css" rel="stylesheet" />
</head>

<body style="background-color:#f0eff5;">
<div class="orderover">
	<div class="orderover_succ">支付成功</div>	
    <div class="jyxq">
    	<h1>¥ ${map.realPay}</h1>
    	<ul>
        	<li>交易时间<span>${map.payTime}</span></li>
            <li>交易订单<span style="font-size:0.875em;">${map.orderNo}</span></li>
            <li>商家信息<span>${map.name}</span></li>
        </ul>
    </div> 
    <div class="orderover_adv">
        <h2><span>广告</span></h2>
        <div class="index_img"><a href="http://www.jysmall.com/app/p2p_introduce.html"><img src="/images/weixin/zfadv1.jpg"></a></div>
    </div>     
     
     <%if(request.getHeader("user-agent").toLowerCase().indexOf("micromessenger") > 0) { %> 
    	  <div class="wxbottom">
	      			<img src="/images/weixin/wxfq.png">
	      </div> 
	 <%}%> 
</div>
</body>
</html>
