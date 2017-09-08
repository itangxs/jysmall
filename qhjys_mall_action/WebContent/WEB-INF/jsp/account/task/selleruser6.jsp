<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
	<link href="/css/public.css" rel="stylesheet" type="text/css" />
	<link href="/css/taskjindu.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/account/selleruser.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="taskjindubox">	
<form id="selleruserform6" action="/account/task/selleruser6.do" method="post">
	<div class="taskjindubox1">
    	<div class="taskjindutop"></div>        
        <div class="taskjindunr">
        	<!--进度条-->  
            <div class="jindu jd84"><i class="text40">84</i><em class="text25">%</em></div>
            <!--问卷-->       
             <input type="hidden" name="userId" value="${user.id }">	
             <input type="hidden" name="ispc" value="${ispc }">
            <div class="xuanbox"> <a href="#" class="qqimg"><img src="/images/qqimg.png" /></a>
           	   <div class="qqtext"><strong></strong>
           	   	<c:if test="${user.qqOpenId == null || user.qqOpenId=='' || empty user.qqOpenId}">
              	 	<p><a href="/qqLogin.do">点击绑定QQ</a></p>
              	 	<input type="hidden" value="1" id="isokqq"></c:if>
              	 <c:if test="${user.qqOpenId != null && user.qqOpenId!=''&& not empty user.qqOpenId}">
              	 	已绑定<input type="hidden" value="0" id="isokqq">
              	 </c:if>
            	<p class="gray">（可使用QQ号登陆）</p>    
               </div>            
          </div>
        </div>
   	  <div class="taskjindubottom"></div>
       <div>
        <c:if test="${user.qqOpenId != null && user.qqOpenId!=''&& not empty user.qqOpenId}">
       <input class="nextanniu" type="button" value="提交" onclick="submit6();"/></c:if></div>
    </div>    </form>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>