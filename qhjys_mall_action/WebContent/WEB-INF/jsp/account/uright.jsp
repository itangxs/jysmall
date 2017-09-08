<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<link rel="stylesheet" type="text/css" href="/css/member.css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
$(function() {
	$('#signIn').on('click', function() {
		var $el = $(this).attr('disabled', "true");
		$.ajax({
			async : true,
			type : "POST",
			url : "/signIn.do",
			success : function(data) {
				if (data == 'logOut')
					alert("用户登录超时！");
				else if (data == false) {
					alert("用户已签到！");
					return;
				} else if (data == true)
					;
				else
					alert("用户签到异常，请稍后重试！");
				window.location.reload(true);
			}
		});
	});
	
function changeone() {
		var page = $("#page").val() +1;
		window.location.href="/account/task/uright.do?page="+page;
	}
});
</script>
</head>
<body>
	<div class="accountindex_right">
       	    <!--签到-->        	
            <div class="rightqiandao">
            	<div class="qiandaotitle">  
                    <script type="text/javascript">document.write((new Date().getMonth()+1)+'月'+new Date().getDate()+'日 星期'+'日一二三四五六'.charAt(new Date().getDay()))</script>
                   <c:if test="${!sessionScope.judgeSignIn}"> <a href="javascript:void(0)" id="signIn">今日签到</a> </c:if>
                     <c:if test="${sessionScope.judgeSignIn}"><a href="javascript:void(0)" class="link1">签到成功</a></c:if>                    
                </div>
                <div class="novicetask">
                	<h1>新手任务</h1>
                    <ul>
                    <c:forEach items="${newlist }" var="task">
                    	<li>${task.taskName }+${task.fulfilReward }元宝 
					<c:if test="${task.taskId ==null }"><a href="${task.projectUrl }">GO</a></c:if>
					<c:if test="${task.taskId !=null }"><a href="javascript:void(0)" class="wancheng"></a></c:if>
</li></c:forEach>
                       
                    </ul>
                </div>
            </div>
            <!--右侧推荐-->
        <div class="right_tuijian">
        <input type="hidden" id="page" value="${page }">
        	<h1><span><a href="javascript:window.location.reload();">换一批</a></span>猜你喜欢</h1>
            <ul>
            <c:forEach items="${plist }" var="prod">
            	<li> 
                	<a href="/getProdDetail.do?prodId=${prod.id}"><img src="${fn:substring(prod.images, 0, fn:indexOf(prod.images, '|'))}"/></a>
           		    <p><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></p>
                    <b><em>¥<fm:formatNumber  value="${prod.unitPrice}" pattern="#,#00.00#"/></em><i></i></b>
                </li>
                </c:forEach>
               
            </ul>
    	</div>
      	</div>
</body>
</html>