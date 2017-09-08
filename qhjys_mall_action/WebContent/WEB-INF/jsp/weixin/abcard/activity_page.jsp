<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link href="/css/abcard/activity-page.css" type="text/css" rel="stylesheet">
<title>活动页面</title>
</head>
<body>
<div class="banner">
	<img src="/images/abcard/card-banner4.jpg">
    <div class="blue-coupon" <c:if test="${empty fqAcard}">style="display: none;"</c:if>>
    <a href="/user/fqcardactivity/a_raffle.do?acardId=${fqAcard.id }&storeId=${storeId}&orderId=${orderId}"><img src="/images/abcard/local-coupon4.png"></a>
      	<div class="blue-btn"></div>
    </div>
    
    <div class="yellow-coupon"><a href="b-raffle.html"><img src="/images/abcard/random-coupon4.png"></a>
      	<div class="yellow-btn"></div>
    </div>
    
     <div class="red-packet">
    	<img src="/images/abcard/red-packet4.png">
      	<div class="red-btn"><a href=""></a></div>
    </div>
</div>
</body>
</html>