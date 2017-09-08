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
<link type="text/css" rel="stylesheet" href="/umeditor/themes/default/css/umeditor.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/js/system/task/add_task.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script>window.UMEDITOR_HOME_URL = "/umeditor/"</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="12" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="1" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">查看任务</h3>
           <p class="clearfix">
        	<label class="one" for="con-email4" >任务名称:</label>
        	${task.taskName }
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务类别:</label>
        		<c:if test="${task.taskType ==0 }">新手任务</c:if>
        		<c:if test="${task.taskType ==1 }">问卷</c:if>
        		<c:if test="${task.taskType ==4 }">商家任务</c:if>
        		<c:if test="${task.taskType ==5 }">注册</c:if>
        		<c:if test="${task.taskType ==6 }">金融</c:if>
        		<c:if test="${task.taskType ==7 }">游戏</c:if>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务来源:</label>
        	${task.taskFrom }
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" > <c:if test="${task.taskType ==4 }">商家ID</c:if><c:if test="${task.taskType !=4 }">任务编号</c:if>:</label>
        	 ${task.project } 
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务地址:</label>
        	 ${task.projectUrl } 
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >信息提交名称:</label>
        	 ${task.infoName } 
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >信息提交地址:</label>
        	 ${task.infoUrl } 
          </p>
          <c:if test="${task.taskType ==4 }">
           <p class="clearfix">
        	<label class="one" for="con-email4" >商家密码:</label>
        	 ${task.projectPass } 
          </p>
          </c:if>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务等级:</label>
        	<c:if test="${task.taskLevel ==0}">不限</c:if>
        	<c:if test="${task.taskLevel ==1 }">VIP1</c:if>
        	<c:if test="${task.taskLevel ==2 }">VIP2</c:if>
        	<c:if test="${task.taskLevel ==3 }">VIP3</c:if>
        	<c:if test="${task.taskLevel ==4 }">VIP4</c:if>
        	<c:if test="${task.taskLevel ==5 }">VIP5</c:if>
        	<c:if test="${task.taskLevel ==6 }">VIP6</c:if>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >开始时间:</label>
        	<fmt:formatDate value="${task.beginTime}" pattern="yyyy-MM-dd "/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >结束时间:</label>
        	<fmt:formatDate value="${task.endTime}" pattern="yyyy-MM-dd "/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >下架时间:</label>
        	<fmt:formatDate value="${task.offShelf}" pattern="yyyy-MM-dd "/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务总量:</label>
        	${task.taskTotal }
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >预估完成时长:</label>
        	${task.planTime }
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务完成奖励:</label>
        	${task.fulfilReward }
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务参与奖励:</label>
        	${task.unfulfilReward }
          </p>
           
          <p class="clearfix">
        	<label class="one" for="con-email">商品图片：</label><br>
	        	<img  id="img1"  src="${task.images}" width="186" height="123" alt="" /></p>
	     <p class="clearfix">
        	<label class="one" for="con-email4" >任务背景色:</label>
        	<div style="width: 100px;height: 60px;background-color: ${task.bgcolor };">${task.bgcolor }</div>
        	
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email">任务背景图：</label><br>
	        	<img  id="img1"  src="${task.bgimg}" width="186" height="123" alt="" /></p>
	        	
                <p class="clearfix">
        		<label class="one" for="con-email">任务介绍：</label>
        		${task.fulfilDetail }
        	 </p>     
	        	
             <p class="clearfix">
        		<label class="one" for="con-email">任务步骤：</label>
        		${task.taskDetail }
        	 </p>    
        	
        	
              <p class="clearfix">
        		<label >说明：<a href="/managermall/systemmall/task/addTaskExpand.do?taskId=${task.id}"><input type="button" value="添加说明" onclick="window.location.href('/managermall/systemmall/task/addTaskExpand.do?taskId=${task.id}')" ></a></label>
        			<c:if test="${!empty listTaskE}">
        			<table width="100%" border="0" cellspacing="0" cellpadding="0">
						  <tr>
						    <th width="10%">步骤</th>
						    <th width="60%">要求</th>
						    <th width="15%" align="center">奖励</th>
						    <th width="15%" align="center">操作</th>
						  </tr>
        			<c:forEach items="${listTaskE}" var="e">
        				  <tr>
						    <td  align="center">${e.step}</td>
						    <td>${e.requirements}</td>
						    <td  align="center">${e.prize}</td>
						    <td  align="center"><a href="/managermall/systemmall/task/toTaskExpand.do?id=${e.id}">修改</a></td>
						  </tr>	
        			</c:forEach>
        			</table>
        			</c:if>
        	 </p>     
        	  
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>

</html>
