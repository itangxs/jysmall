<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/systask/systask.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script>window.UMEDITOR_HOME_URL = "/umeditor/"</script>
<link type="text/css" rel="stylesheet" href="/css/system/renwu.css">
</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="12" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 class="renwutitle">问卷任务</h3>
            
           <p class="clearfix">
           <input type="hidden" name="taskType" id="taskType" value="1"/>
           <input type="hidden" name="id" id="id" value="${task.id }"/>
        	<label class="textw100" for="con-email4" >任务名称:</label>
        		${task.taskName }
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
        	${task.project }
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >等级限制:</label>
        		<c:if test="${task.taskLevel ==0 }">不限</c:if>
        		<c:if test="${task.taskLevel ==1 }">VIP1</c:if>
        		<c:if test="${task.taskLevel ==2 }">VIP2</c:if>
        		<c:if test="${task.taskLevel ==3 }">VIP3</c:if>
        		<c:if test="${task.taskLevel ==4 }">VIP4</c:if>
        		<c:if test="${task.taskLevel ==5 }">VIP5</c:if>
        		<c:if test="${task.taskLevel ==6 }">VIP6</c:if>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >开始时间:</label>
        	<fmt:formatDate value="${task.beginTime}" pattern="yyyy-MM-dd "/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >结束时间:</label>
        	<fmt:formatDate value="${task.endTime}" pattern="yyyy-MM-dd "/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务总量:</label>
        	${task.taskTotal }&nbsp;&nbsp;人
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务完成奖励:</label>
        	${task.fulfilReward }&nbsp;&nbsp;金元宝
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务参与奖励:</label>
        	${task.unfulfilReward }&nbsp;&nbsp;金元宝
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务来源:</label>
        	${task.taskFrom }
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >公司内部对接人:</label>
        	${task.staff }
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >审核时间:</label>
        	${task.verifyTime }天
          </p>
           <h3 class="renwutitle">答案区</h3>
             <div class="member_myorder">
             <div class="renwuanniubox1">
            <a href="/managermall/systemmall/taskQuestion/goAdd.do?id=${task.id}&size=${fn:length(questions) }"><input  type="submit" value="+增加题目" class="renwuanniublue"></a> </div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
                        <th class="td80"></th>
						<th class="tdauto" style="padding-left:100px;">名称</th>
	                    <th class="td80">形式</th>
	                    <th class="td80">时长</th>      
	                    <th class="td160">操作</th>     
	                    <th class="td120">位置</th>
					</tr>
					<c:forEach items="${questions }" var="question" varStatus="status">
						<tr>
                        <td class="td80">题目${question.questionNo }</td>
						<td class="tdauto">${question.questionTitle }</td>
						<td class="td80">
							<c:if test="${question.questionType ==1 }">文本</c:if>
			        		<c:if test="${question.questionType ==2 }">图片</c:if>
			        		<c:if test="${question.questionType ==3 }">视频</c:if>
						</td>
						<td class="td80">${question.questionTime }秒</td>
						<td class="td160">
						<a href="/managermall/systemmall/taskQuestion/deleteQuestion.do?id=${question.id}">删除</a>
						&nbsp;&nbsp;&nbsp;<a href="/managermall/systemmall/taskQuestion/getQuestion.do?id=${question.id}">预览</a></td>
						<td class="td120">
						<c:if test="${fn:length(questions) >1}">
						<c:if test="${status.count  == 1}"><a href="javascript:changeno('${question.id }','${questions.get(status.index+1).id }')">↓下移</a></c:if>
						<c:if test="${status.count  > 1 && status.count  < fn:length(questions)}">
						<a href="javascript:changeno('${questions.get(status.index-1).id }','${question.id }')">↑上移</a>&nbsp;&nbsp;<a href="javascript:changeno('${question.id }','${questions.get(status.index+1).id }')">↓下移</a></c:if>
						<c:if test="${ status.count  == fn:length(questions)}"><a href="javascript:changeno('${questions.get(status.index-1).id }','${question.id }')">↑上移</a>
						</c:if></c:if>
						</td>
					</tr>
					</c:forEach>
					
				</table>
			</div>
           <p class="renwuanniubox">
             <a href="/managermall/systemmall/sysTask/list.do"><input type="submit" value="返回列表" class="renwuanniublue"></a> 
           </p>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
