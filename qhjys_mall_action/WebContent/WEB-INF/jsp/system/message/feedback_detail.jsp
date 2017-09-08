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
<script type="text/javascript">
	function replyFeedback(id,pageNum){
		var reply = $("#reply").val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/systemmall/feedback/updateFeedback.do',
			data : {"id":id,"reply":reply},
			success : function(data) {
				if(data== "success"){
					window.location.href = "/managermall/systemmall/feedback/feedbackPage.do?pageNum="+pageNum;
				}else{
					alert("回复失败");
				}
			},	
			error : function(e) {
				alert("回复失败");
			}
		});
	}
</script>
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
         <h3 style="padding-left:10px; margin-bottom:25px;">意见反馈——反馈回复</h3>
            <input id="page" name="pageNum" type="hidden" value="${pageNum }">
           <p class="clearfix">
        	<label class="one" for="con-email4" >意见反馈：</label>
        		${feedback.content }
          </p>
          <br>
	         	<p class="clearfix">
	        		<label class="one" for="con-email4" >反馈回复：</label>
	        		<c:if test="${empty feedback.reply}">
	        		<textarea rows="70" cols="5" id="reply"></textarea>
	        		<input type="button" value="回复" onclick="replyFeedback(${feedback.id },${pageNum })"/>
	            	</c:if>
	            	<c:if test="${not empty feedback.reply}">
	            	${feedback.reply}</c:if>
          </p>
         <br>
         <p class="clearfix">
         	<a href="/managermall/systemmall/feedback/feedbackPage.do?pageNum=${pageNum }">返回列表</a>
         </p>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
