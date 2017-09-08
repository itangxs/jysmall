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
	<script type="text/javascript" src="/js/system/task/add_task.js"></script>
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
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="4" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 class="renwutitle">关注任务</h3>
           <p class="clearfix">
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
        	<label class="textw100" for="con-email4">公司内部对接人:</label>
        	${task.staff }
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >审核时间:</label>
        	${task.verifyTime }天
          </p>
        	<p class="clearfix">
	        	<label class="textw100" for="con-email4" >任务内容:</label>
                <div class="textareabox">
                	${task.taskContent }
                </div>
         	 </p>
           <p class="renwuanniubox">
            
           </p>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
