<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
	<link href="/css/public.css" rel="stylesheet" type="text/css" />
	<link href="/css/taskjindu.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
	<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
	<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="/js/account/selleruser.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<div class="taskjindubox">	
	<form id="selleruserform1" action="/account/task/selleruser1.do" method="post">
		<div class="taskjindubox1">
	    	<div class="taskjindutop"></div>        
	        <div class="taskjindunr">
	        	<!--进度条-->  
	            <div class="jindu jd0"><i class="text40">0</i><em class="text25">%</em></div>
	            <!--问卷-->
	            <input type="hidden" name="userId" value="${user.id }">
	            <input type="hidden" name="ispc" value="${ispc }">
	            <label class="marginbottom30"><span class="namebox">姓名：</span><input class="inputbox" id="realname" name="realname" type="text" value="${user.realname }" /></label>
	            <label class="marginbottom30"><span class="namebox">生日：</span><input class="easyui-datebox" id="birthday" name="brithday" type="text" value="${expand.birthday }" />
	            </label>
	            <label class="marginbottom30"><span class="namebox">站内昵称：</span><input class="inputbox" id="nickname" name="nickname" type="text" value="${user.nickname }" /></label>
	        </div>
	   	  <div class="taskjindubottom"></div>
	        <div><input class="nextanniu" type="button" value="下一题" onclick="submit1();"/></div>
	    </div>   
	    </form> 
	</div>
	<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>