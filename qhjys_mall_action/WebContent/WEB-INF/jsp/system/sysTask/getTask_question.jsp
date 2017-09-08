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
<script type="text/javascript" src="/js/system/systask/add_question.js"></script>
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
            <h3 class="renwutitle">题目编辑</h3>
         <p class="clearfix">
        	<label class="textw100" for="con-email4" >题干内容:</label>
        	${question.questionTitle }
        	<input type="hidden" name="id" value="${question.id }">
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >形&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;式:</label>
        					<c:if test="${question.questionType ==1 }">文本</c:if>
			        		<c:if test="${question.questionType ==2 }">图片</c:if>
			        		<c:if test="${question.questionType ==3 }">视频</c:if>
          </p>          
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >题目时长:</label>
        	${question.questionTime }&nbsp;&nbsp;秒
          </p>
          <c:if test="${question.questionType ==2 || question.questionType ==3 }">
          <p class="clearfix" id="imageUrl">
        	<label class="textw100" for="con-email4" >视频/图片:</label>
        	${question.questionContent }
          </p> </c:if>
           <c:if test="${question.questionType ==1 }">
          <p class="clearfix" id="timuleixing" >
        	<label class="textw100" for="con-email4" >题目类型:</label>
        					<c:if test="${question.answerType ==0 }">选择</c:if>
			        		<c:if test="${question.answerType ==1}">填空</c:if>
          </p> 
          <c:if test="${question.answerType ==0 }">
          <p class="clearfix" id="answernum" >
        	<label class="textw100" for="con-email4" >可选答案数量:</label>
        					${question.maxAnswer}
          </p>     
          </c:if>
          </c:if>   
          <c:if test="${question.answerType ==0 }">
           <h3 class="renwutitle">答案区</h3>
          <div class="member_myorder">
             <div class="renwuanniubox3"><a href="/managermall/systemmall/taskAnswer/goAdd.do?questionId=${question.id }"><input type="submit" value="+增加答案" class="renwuanniublue"></a></div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td80"></th>
                        <th class="td120">类型</th>
						<th class="tdauto" style="padding-left:100px;">答案内容</th>    
	                    <th class="td160">操作</th>
					</tr>
					<c:forEach items="${answers }" var="answer" varStatus="status">
						<tr>
	                        <td class="td80">答案${status.count }</td>
	                        <td class="td120">
							<c:if test="${answer.answerType ==0 }">文本</c:if>
			        		<c:if test="${answer.answerType ==1}">图片</c:if>
							</td>
							<td class="tdauto">
								<c:if test="${answer.answerType ==0 }">
									${answer.answerContent }
								</c:if>
								<c:if test="${answer.answerType ==1}">
									<a target="_blank" href="${answer.answerContent }">点击查看</a>
								</c:if>
							</td>
							<td class="td80"><a href="javascript:deleteAnswer('${answer.id }')">删除</a>
								<a href="javascript:changeCorrect('${answer.id }')">
									<c:if test="${answer.isCorrect ==0 }">
										取消正确
									</c:if>
									<c:if test="${answer.isCorrect ==1 }">
										正确答案
									</c:if>
								</a>
							</td>
						</tr>
					</c:forEach>
					
				</table>
			</div>
                </c:if>
           <p class="renwuanniubox">
           <a href="/managermall/systemmall/sysTask/list.do"><input type="submit" value="返回列表" class="renwuanniublue"></a> 
           <a href="/managermall/systemmall/sysTask/getTask.do?id=${question.taskId }"><input type="submit" value="返回任务" class="renwuanniublue"></a>     
           </p> 
	</div>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
