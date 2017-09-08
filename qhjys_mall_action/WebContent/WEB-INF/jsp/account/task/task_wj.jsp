<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券问卷</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/task.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/js/account/systask/wenjuan.js"></script>

</head>

<body style="background:url(/images/wenjuanboxbg.jpg) repeat;">
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="wenjuan-banner">
	<div class="wenjuanbkboxtop"></div>
    <div class="wenjuanbkbox">
    <form action="/account/systask/answerQuestion.do" method="post" id="wjform">
   		 <c:if test="${not empty message }">
   		 	${message }
   		 </c:if>
    	<c:if test="${empty message }">
    		<input type="hidden" name="questionId" value="${question.id }"/>
    		<input type="hidden" name="questionNo" value="${question.questionNo }"/>
    		<input type="hidden" name="questionNum" value="${questionNum }"/>
    		<input type="hidden" id="answerType" value="${question.answerType }"/>
    		<input type="hidden" id="questionType" value="${question.questionType }"/>
    		<input type="hidden" id="maxAnswer" name="maxAnswer" value="${question.maxAnswer }"/>
    		<!-- 任务标题开始 -->
    		
    		<div class="pwg-title"><i>[<c:if test="${question.questionType == 1 }">
    		<c:if test="${question.answerType == 0 }">
    			<c:if test="${question.maxAnswer == 1 }">单选</c:if>
    			<c:if test="${question.maxAnswer > 1 }">多选</c:if>
    		</c:if>
    		<c:if test="${question.answerType == 1 }">填空</c:if>
    		</c:if>
    		<c:if test="${question.questionType == 2 }">图片</c:if>	
    		<c:if test="${question.questionType == 3 }">视频</c:if>
    		]</i>${question.questionTitle }</div>
    		<!-- 任务标题结束 -->
    		<c:if test="${question.questionType == 1 }">
    			<c:if test="${question.answerType == 0 }">
    				<ul><!-- ul列表 -->
    					<c:forEach items="${answers }" var="answer" varStatus="status">
    					<li><!-- li里面为竖列的多选框 -->
    						<input id="chack${status.index }" type="checkbox" name="answers" value="${answer.id }"/>
    						<c:if test="${answer.answerType ==0 }">${answer.answerContent }</c:if>
    						<c:if test="${answer.answerType ==1 }"><img src="${answer.answerContent }" alt="" />   </c:if>
    					</li>
    					</c:forEach>
    				</ul>
    			</c:if>
    			<c:if test="${question.answerType == 1 }">
    				输入答案:<input type="text" name="answer" style="BORDER-TOP-STYLE: none; BORDER-RIGHT-STYLE: none; BORDER-LEFT-STYLE: none; BORDER-BOTTOM-STYLE: ridge;"/><!-- 只有下边线的文本框 -->
    			</c:if>
    		</c:if>
    		<c:if test="${question.questionType == 2 }">
    			<img src="${question.questionContent }" style="max-width: 700px;"/><!-- 图片 -->
    		</c:if>
    		<c:if test="${question.questionType == 3 }">
    			<img src="${question.questionContent }" style="max-width: 700px;"/><!-- 图片 -->
    		    		</c:if>
    	
    	<br/>
    	<c:if test="${question.questionNo == questionNum }">
    		<div class="wj-anniu"><input type="button" value="完成答题"  class="pwg-anniu1" onclick="submit1();"/></div>
    	</c:if>
    	<c:if test="${question.questionNo < questionNum }">
    	<div class="wj-anniu"><input type="button" class="pwg-anniu1" value="下一题" onclick="submit1();"/></div></c:if></c:if>
    	</form>
    </div>
    <div class="wenjuanbkboxbottom"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
