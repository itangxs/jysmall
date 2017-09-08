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
<div class="wenquantongzhi renwuqiangbg">
	<div class="title">
    	以下内容与您使用任务墙息息相关，请仔细查看！
    </div>
    <div class="infobox">
    ${task.taskDetail }
           </div>
	<div class="wenquananniu"><!--<img src="images/wenjuananniu.png"/>-->
	<c:if test="${empty userId}">
	<a href="/account/login.do">立即答题</a>
	</c:if>
	<c:if test="${not empty userId}">
	<a href="/account/task/taskwall.do?userId=${userId }&project=${task.project}">立即答题</a>
	</c:if>
    </div>
</div>
</body>
</html>