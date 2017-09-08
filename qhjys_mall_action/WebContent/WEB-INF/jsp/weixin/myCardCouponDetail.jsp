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

	return ;
}  
%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/js/weixin/qrcode.min.js" type="text/javascript"></script>
<title>卡包</title>
<link href="/css/weixin/cardcoupon/card-holder.css" type="text/css" rel="stylesheet" />
</head>
<body style="background-color:#eaeaea;">
<div class="card-detail">	
	<img src="/images/weixin/cardcoupon/detail_bg.jpg">
   	<div class="lpq_detail">
   	    <c:choose>
              <c:when test="${cardCoupon.templateCouponCfg==0}">
                   <img src="/images/weixin/cardcoupon/lpq_detail.png">
              </c:when>
              <c:when test="${cardCoupon.templateCouponCfg==1}">
                   <img src="/images/weixin/cardcoupon/djq_detail.png">
              </c:when>
              <c:when test="${cardCoupon.templateCouponCfg==2}">
                   <img src="/images/weixin/cardcoupon/zkq_detail.png">
              </c:when>
        </c:choose>
      	<div class="coupon-name add-type01">
    		<p>${cardCoupon.templateCouponName}</p>
        </div>
   	    <p class="store-name add-type02">${cardCoupon.storeName}</p>
    	<div class="deadline">   	
        	<p class="right-float add-type03"><fmt:formatDate value="${cardCoupon.validityEndtime}" pattern="yyyy/MM/dd"/>前有效</p>
		</div>
    </div>
</div>

<div class="code">
	     <c:choose>
              <c:when test="${cardCoupon.templateCouponCfg==0}">
               		 <div id="qrcode"></div>
              		<script type="text/javascript">
              			new QRCode(document.getElementById('qrcode'), '${cardCoupon.code }');
              		</script>
              </c:when>
              <c:when test="${cardCoupon.templateCouponCfg==1}">
                   <img src="/images/weixin/cardcoupon/money-yellow.png">
              </c:when>
              <c:when test="${cardCoupon.templateCouponCfg==2}">
                   <img src="/images/weixin/cardcoupon/money-purple.png">
              </c:when>
        </c:choose>
    <p class="renzheng-num">认证编号：${cardCoupon.code }</p>
</div>

<div class="use_production">
	<h1>使用说明:</h1>
    <p>${cardCoupon.templateUseExplain }</p>
</div>

</body>
</html>
