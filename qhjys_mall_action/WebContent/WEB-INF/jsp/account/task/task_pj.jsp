<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/task.css" rel="stylesheet" type="text/css" />

</head>

<body style="background:#cfeaff;">
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="pingjia-banner">
    <div class="pingjiabkbox">
    	<div class="pwg-title"><em></em>${task.taskName }<br/></div><!-- 任务标题 -->
    	<div>${task.taskContent }</div><!-- 任务内容 -->
    	<div class="pwg-anniu"><a href="/account/systask/doreview.do?storeId=${storeId }&taskId=${task.id}">前往商家</a><!-- 任务超链接,做成按钮形式 --></div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
