<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/task.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.qrcode.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#code").qrcode({
			width: 100, //宽度
			height:100, //高度
			
			text: "${url}/weixin_task.do?user_id=${userId}&task_id=${task.id}" //任意内容
		});
	});
</script>
</head>

<body style="background:#fed9d8;">
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="guanzhu-banner">
    <div class="guanzhubkbox">
  		   	<div class="pwg-title"><em></em>${task.taskName } <!-- 标题 --></div>
  		 	 使用微信扫描开始做任务<!-- 内容-->
    	<div id="code" class="saoyisaobox"></div><br/><!-- 二维码图片 -->
    	<%-- ${task.taskContent }--%><!-- 描述 -->
    </div>
    <div class="guanzhubkboxbottom"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
