<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form id="selleruserform2" action="/account/task/selleruser2.do" method="post">
		<div class="taskjindubox1">
	    	<div class="taskjindutop"></div>
	        <div class="taskjindunr">
	        	<!--进度条-->  
	            <div class="jindu jd17"><i class="text40">17</i><em class="text25">%</em></div>
	            <!--问卷-->
	             <input type="hidden" name="userId" value="${user.id }">
	             <input type="hidden" name="ispc" value="${ispc }">
	            <label class="marginbottom30"><span class="namebox">手机号：</span><input class="inputbox" id="phoneNum" name="phoneNum" type="text" value="${user.phoneNum }" /></label>
           	 <label class="marginbottom30"><span class="namebox">邮箱号：</span><input class="inputbox" id="email" name="email" type="text" value="${user.email }" /></label>
	        </div>
	   	  <div class="taskjindubottom"></div>
	        <div><input class="nextanniu" type="button" value="下一题" onclick="submit2();"/></div>
	    </div>  
	    </form>  
	</div>
	<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>