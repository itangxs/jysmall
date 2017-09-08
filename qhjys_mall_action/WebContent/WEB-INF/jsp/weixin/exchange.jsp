<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>飞券网-抽奖拼团免费试吃</title>
<link href="/css/weixin/public.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div class="wrapper">
  <div class="index_img"><img src="/images/weixin/duihuan01.jpg"></div>
  <div class="imgbottom ">
  	<div class="textdh"><center>
  	  <p>您已成功兑换  	   </p>
  	  <p> ${store.name}【${dish.dishName }】  	    </p>
  	  <p>${dish.limitNum }人或以上使用</p>
  	</center></div>
    <h2><img src="/images/weixin/duihuan02.jpg"></h2>
  </div>
  <div class="index_img"><img src="/images/weixin/duihuan03.jpg"></div>
  <div class="imgbottom imgbottombg">
  <!--<c:if test="${not empty pageNum && pageNum != ''}">
  	<c:if test="${pageNum != 1 }"><a href="/lottery/getCoupons.do?openId=${openId }&lotteryId=${lotteryId }&pageNum=${pageNum-1}"><div class="leftarr"></div></a></c:if>
  	<c:if test="${pageNum != total }"><a href="/lottery/getCoupons.do?openId=${openId }&lotteryId=${lotteryId }&pageNum=${pageNum+1}"> <div class="rightarr"></div></a></c:if></c:if>
  	-->
  	<div class="ewm"><center><img class="ewmimg" src="${ci.couponsImage }"></center></div>
    <!-- <h2><img src="/images/weixin/duihuan04.jpg"></h2> -->
  </div>
  <div class="shiyongguizebox">
  	
      <div class="shiyongguize">
      	<center><img src="/images/weixin/duihuan05.png"></center>
        <p>1、每次消费每桌限用一张券;</p>
        <p>2、具体菜品以商家为准,消费前请提前致电商家预约;</p>
		<p> 3、有效时间<fmt:formatDate value="${ci.createTime}" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${ci.endTime}" pattern="yyyy-MM-dd"/>，请在有效日期前使用;</p>
		<p> 4、身高1米2以下的儿童不计入活动人数。</p>
      </div>
  </div>
</div>
</body>

</html>