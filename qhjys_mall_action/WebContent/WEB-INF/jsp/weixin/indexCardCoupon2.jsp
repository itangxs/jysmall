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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <title>index</title>
  <link rel="stylesheet" href="/css/weixin/cardcoupon/getraffle.css">
  <meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>本店优惠劵领取</title>

<script src="/js/weixin/jquery.minA.js"></script>
</head>
<body class="body page-index clearfix">
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