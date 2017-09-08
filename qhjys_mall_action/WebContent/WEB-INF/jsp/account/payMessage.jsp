<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 禁止浏览器从缓存中访问页面内容 -->
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/public.css" />
<link rel="stylesheet" type="text/css" href="/css/cart.css" />
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<div class="member_pay">
	  <h3><img src="/images/pay_liucheng2.jpg" /></h3>
	  <div class="cartgouwu">
	  	<h1 style="color:red;">${payMessage}</h1>
	  	<a href="/managermall/account/order/myOrderForm.do">我的订单</a>
	  </div>
	  <br />
	</div>
	<jsp:include page="uFooter.jsp" flush="true" />
	<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
</body>
</html>