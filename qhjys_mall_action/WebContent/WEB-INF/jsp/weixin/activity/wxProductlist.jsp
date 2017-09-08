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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>飞券网—带你免费抽啥吃啥</title>
<link href="/css/weixin/public.css" type="text/css" rel="stylesheet" />

</head>

<body>
<div class="wrapper">
  <div class="index_img"><img src="/images/weixin/freebanner.jpg"></div>
  <div class="probox">
  	<ul>
  		<c:forEach items="${list}" var="storeLottery">
  			<a href="/lottery/getLottery.do?lotteryId=${storeLottery.id }&fromwhere=${fromwhere}">
	   	  	<li>
	    		<div class="leftinfo">
	            	<h1><span class="icon"></span>${storeLottery.storeName}</h1>
	                <h2>${storeLottery.address}</h2>
	            </div>
	    		<!-- <div class="proimg">
	    			<input class="img1" name="" type="image" src="/images/weixin/arr02.png">
	    		</div> -->
	            <div class="clear"></div>
	        </li>
	        </a>
        </c:forEach>
     
    </ul> 
  <!--   <div class="button100"><a href="raffle.html">选择</a></div> -->
  </div>   
</div>
</body>
</html>
