<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>金钥匙商城</title>
<link href="/css/taskjindu1.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/account/selleruser.js"></script>  
</head>


<body class="taskjindubg">
<div class="taskjindubox">
<form id="selleruserform6" action="/account/task/selleruser6.do" method="post">
	<!--进度条-->  
    <div class="jindu jd84"><i class="text40">84</i><em class="text25">%</em></div>	
	<div class="taskjindubox1">        
        <div class="taskjindunr">
        	<input type="hidden" name="userId" value="${user.id }">
            <!--问卷-->       	
            <div class="xuanbox"> 
            	<p class="qqimg"><a href="#"><img src="/images/qqimg.png" /></a></p>
           	   <div class="qqtext"><strong></strong>
               <p><c:if test="${user.qqOpenId == null || user.qqOpenId=='' || empty user.qqOpenId}">
              	 	<p><a href="/qqLogin.do">点击绑定QQ</a></p>
              	 	<input type="hidden" value="1" id="isokqq"></c:if>
              	 <c:if test="${user.qqOpenId != null && user.qqOpenId!=''&& not empty user.qqOpenId}">
              	 	已绑定<input type="hidden" value="0" id="isokqq">
              	 </c:if><span class="gray">（可使用QQ号登陆）</span></p>    
               </div>            
          </div>
          <div class="clear"></div>
        </div>
        <div class="centerdiv"> <c:if test="${user.qqOpenId != null && user.qqOpenId!=''&& not empty user.qqOpenId}">
        <input class="nextanniu" type="submit" value="提交" /></c:if></div>


    </div>
    </form>
</div>

</body>
</html>
