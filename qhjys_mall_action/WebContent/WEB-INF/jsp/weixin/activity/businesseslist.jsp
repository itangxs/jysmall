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
<title>飞券网-商家记录</title>
<link href="/css/weixin/public.css" type="text/css" rel="stylesheet" />

</head>

<body>
<div class="wrapper" style="text-align: center;">
  <div class="index_img"><img src="/images/weixin/freebanner.jpg"></div>
  <div class="probox" style="text-align: center;">
  	  <div class="probox">
     	<table style="text-align: center;" width="100%">
     		<tr>
     			<th>微信昵称</th>
     			<th>奖项等级</th>
     			<th>消费时间</th>
     		</tr>
     			<c:forEach items="${list}" var="couponsInfo">
		     		<tr>
		     			<td>
		     				${couponsInfo.nickName}
		     			</td>
		     			<td>
		     			
		     					<c:if test="${couponsInfo.rank==1}">特等奖</c:if>
		     					<c:if test="${couponsInfo.rank==2}">一等奖</c:if>
		     					<c:if test="${couponsInfo.rank==3}">二等奖</c:if>
		     					<c:if test="${couponsInfo.rank==4}">三等奖</c:if>
		     				
		     			</td>
		     			<td><fmt:formatDate value="${couponsInfo.verifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		     		</tr>
     			 </c:forEach>
     	</table>
     	</div>
  <!--   <div class="button100"><a href="raffle.html">选择</a></div> -->
  </div>   
</div>
</body>
</html>
