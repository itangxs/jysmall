<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/message/addmessage.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="11" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/message/uLeft.jsp" flush="true" ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
         <h3 style="padding-left:10px; margin-bottom:25px;">站内消息——消息详情</h3>
            
           <p class="clearfix">
        	<label class="one" for="con-email4" >消息标题：</label>
        		${message.title }
          </p>
          <br>
           <p class="clearfix">
        <label class="one" for="con-email4" >消息类型：</label>
        	<c:if test="${message.type==1}">用户消息</c:if>
        	<c:if test="${message.type==2}">商家消息</c:if>
           </p>
           <br>
           <p class="clearfix">
       		 <label class="one" for="con-email4" >接收人：</label>
        	${message.account}
           </p>
           <p class="clearfix">
       		 <label class="one" for="con-email4" >发送时间：</label>
        	<fmt:formatDate value="${message.createDate}" pattern="yyyy-MM-dd hh:mm"/>
           </p>
           <p class="clearfix">
       		 <label class="one" for="con-email4" >是否已读：</label>
        		<c:if test="${message.seen==1}">已读</c:if>
        		<c:if test="${message.seen==0}">未读</c:if>
           </p>
           <br>
          <p class="clearfix">
        	<label class="one" for="con-email4" >消息内容：</label>
            ${message.content}
          </p>
          
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
