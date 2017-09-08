<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 体验赚钱-飞券-答题赚钱-网赚-游戏赚钱-飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/index2.css" rel="stylesheet" type="text/css" />
<link href="/css/task.css" rel="stylesheet" type="text/css" />

</head>
<body>
<!--问卷调查须知-s-->
<div class="shoppingxzbox">
	<div class="shoppinginfo">购物返利：通过飞券的链接登录淘宝购物即可获得返利，没有特定商户限制，没有特定产品限制，你敢买我就敢返！</div>
	<div class="title">
    	以下内容与您使用购物返利息息相关，请仔细查看！
</div>
    <div class="infobox">
    ${task.taskDetail }
           </div>
	<div class="wenquananniu"><!--<img src="images/wenjuananniu.png"/>-->
	<c:if test="${empty sessionScope.user}">
       		 <a href="/account/login.do">立即购物</a>
     </c:if>
      <c:if test="${not empty sessionScope.user}">
       		 <a href="http://www.tuijieke.com/buy?coop_id=69&ukey=${sessionScope.user.id }&navshow=0">立即购物</a>
     </c:if> 
    </div>
</div>
</body>
</html>