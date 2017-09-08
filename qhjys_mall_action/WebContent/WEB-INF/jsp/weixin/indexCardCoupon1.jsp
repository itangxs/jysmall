<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <title>优惠大放送--飞券</title>
  <link rel="stylesheet" href="/css/weixin/cardcoupon/getraffle.css">
  <meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body class="body page-index clearfix">
 <div class="toptext">
   实际支付：<strong> ¥ ${order.realPay }</strong><br>
   <c:if test="${not empty coupon}">
   已使用${coupon.templateCouponName }
   </c:if>
   
     </div>
     <div class="toppic">
  <img class="toppic" src="/images/weixin/cardcoupon/u1060.jpg">
     </div> 
   <div class="image-1">
  		<img class="image image-1" src="/images/weixin/cardcoupon/u0.jpg">
   </div>
   <div class="image-2">
 		 <bg class="image image-2" src="/images/weixin/cardcoupon/u4.jpg">
   </div>

<c:choose>
		<c:when test="${sum == 3}">
			<div style="display:;">
				  <div class="">
				  		<img class="image" style="position: relative;clear: both;z-index: 5; width: 40%;margin: 0% 20% 5% 28%;" src="/images/weixin/cardcoupon/u12.png"  onclick="location.href ='/wxMall/jump/orderRedpack.do?orderId=${orderId}&id=${id }&status=${status }&redpackMoney=${redpackMoney }'" >    
				  </div>
		 		  <img class="image" style=" position: relative;clear: both;z-index: 6;width: 40%;margin:0% 0% 0% 8%;" src="/images/weixin/cardcoupon/u16.png" onclick="location.href ='/wxMall/jump/getCardCoupon.do?flag=1&sellerId=${sellerId }&orderId=${orderId}'">
		 		  <img class="image" style=" position: relative;clear: both;z-index: 6;width: 40%;margin:-42% 0% 0% 50%;"  src="/images/weixin/cardcoupon/u14.png" onclick="location.href ='/wxMall/jump/localCardCoupon.do?orderId=${orderId}'">
		  </div>
		</c:when>
		<c:when test="${ sum==2 }">
			<div style=";">
				<c:if test="${peripheralFlag ==1 && proprietaryFlag ==1 }">
					   <div>
					  		<img class="image" style="position: relative; clear: both;z-index: 6;width: 50%;margin: 0% 0% 0% 0%" src="/images/weixin/cardcoupon/u14.png"  onclick="location.href ='/wxMall/jump/localCardCoupon.do?orderId=${orderId}'">
					  </div>
					   <div class="">
					  		   	<img class="image" style="position: relative; clear: both;z-index: 6;width: 50%;margin: -51% 50% 0% 50%" src="/images/weixin/cardcoupon/u16.png" onclick="location.href ='/wxMall/jump/getCardCoupon.do?flag=1&sellerId=${sellerId }&orderId=${orderId}'">
					 	</div>
				</c:if>
				
				<c:if test="${peripheralFlag ==1 && redpackFlag==1}">
				      <div>
					  		<img class="image" style="position: relative; clear: both;z-index: 6;width: 50%;margin: 0% 0% 0% 0%" src="/images/weixin/cardcoupon/u14.png"  onclick="location.href ='/wxMall/jump/localCardCoupon.do?orderId=${orderId}'">
					  </div>
				      <div class="">
					  		<img class="image" style="position: relative; clear: both;z-index: 6;width: 50%;margin:-51% 50% 0% 50%;" src="/images/weixin/cardcoupon/u12.png" onclick="location.href ='/wxMall/jump/orderRedpack.do?orderId=${orderId}&id=${id }&status=${status }&redpackMoney=${redpackMoney }'" >
					   </div>
				</c:if>
				
				<c:if test="${proprietaryFlag ==1 && redpackFlag==1}">
						<div class="">
					  		   	<img class="image" style="position: relative; clear: both;z-index: 6;width: 50%;margin: 0% 0% 0% 0%" src="/images/weixin/cardcoupon/u16.png" onclick="location.href ='/wxMall/jump/getCardCoupon.do?flag=1&sellerId=${sellerId }&orderId=${orderId}'">
					 	</div>
					
						<div class="">
					  		<img class="image" style="position: relative; clear: both;z-index: 6;width: 50%;margin:-51% 50% 0% 50%;" src="/images/weixin/cardcoupon/u12.png" onclick="location.href ='/wxMall/jump/orderRedpack.do?orderId=${orderId}&id=${id }&status=${status }&redpackMoney=${redpackMoney }'" >
					    </div>
				</c:if>
		    </div>
		</c:when>
		
		<c:when test="${ sum==1 }">
				<div style=";">
					<c:if test="${peripheralFlag ==1 }">
						   <div class="">
						  		<img class="image" style="position: relative;clear: both;z-index: 5; width: 70%;margin:0% 0% 0 15%;"  src="/images/weixin/cardcoupon/u14.png"  onclick="location.href ='/wxMall/jump/localCardCoupon.do?orderId=${orderId}'">
						  </div>
					</c:if>
					<c:if test="${proprietaryFlag ==1 }">
				             <div class="">
					  		   	<img class="image" style="position: relative;clear: both;z-index: 5; width: 70%;margin: 0% 0% 0 15%;" src="/images/weixin/cardcoupon/u16.png" onclick="location.href ='/wxMall/jump/getCardCoupon.do?flag=1&sellerId=${sellerId }&orderId=${orderId}'">
					 		 </div>
					</c:if>
						<c:if test="${redpackFlag==1 }">
							<div class="">
						  		<img class="image" style="position: relative;clear: both;z-index: 5; width: 70%;margin: 0% 0% 0 15%;" src="/images/weixin/cardcoupon/u12.png" onclick="location.href ='/wxMall/jump/orderRedpack.do?orderId=${orderId}&id=${id }&status=${status }&redpackMoney=${redpackMoney }'" >
						    </div>
					</c:if>
			    </div>
		</c:when>
		
</c:choose>
 
  <div class="image8">
  		<img class="image image-8" src="/images/weixin/cardcoupon/u10.jpg">
  </div>
  
</body>
</html>