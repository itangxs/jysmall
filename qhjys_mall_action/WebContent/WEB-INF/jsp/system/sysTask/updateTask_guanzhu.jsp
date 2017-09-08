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
            <h3 class="renwutitle">添加关注任务</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/sysTask/updateTask.do" method="post">
           <p class="clearfix">
           <input type="hidden" name="taskType" id="taskType" value="2"/>
           <input type="hidden" name="id" id="id" value="${task.id }"/>
        	<label class="textw100" for="con-email4" >任务名称:</label>
        	<input class="con-email" name="taskName" id="taskName" value="${task.taskName }"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
        	<input name="project" class="con-email6" id="project" value="${task.project }"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >等级限制:</label>
        	<select  name="taskLevel" id="taskLevel">
        		<option value="">请选择...</option>
        		<option value="0" <c:if test="${task.taskLevel ==0 }">selected="selected"</c:if>>不限</option>
        		<option value="1" <c:if test="${task.taskLevel ==1 }">selected="selected"</c:if>>VIP1</option>
        		<option value="2" <c:if test="${task.taskLevel ==2 }">selected="selected"</c:if>>VIP2</option>
        		<option value="3" <c:if test="${task.taskLevel ==3 }">selected="selected"</c:if>>VIP3</option>
        		<option value="4" <c:if test="${task.taskLevel ==4 }">selected="selected"</c:if>>VIP4</option>
        		<option value="5" <c:if test="${task.taskLevel ==5 }">selected="selected"</c:if>>VIP5</option>
        		<option value="6" <c:if test="${task.taskLevel ==6 }">selected="selected"</c:if>>VIP6</option>
        	</select>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >开始时间:</label>
        	<input id="beginTime" name="beginTime1" class="easyui-datetimebox con-time" value="${beginTime }"> 
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >结束时间:</label>
        	<input id="endTime" name="endTime1" class="easyui-datetimebox con-time" value="${endTime }">
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务总量:</label>
        	<input class="con-email3" name="taskTotal" id="taskTotal" value="${task.taskTotal }"/>&nbsp;&nbsp;人
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务完成奖励:</label>
        	<input class="con-email3" name="fulfilReward" id="fulfilReward" value="${task.fulfilReward }"/>&nbsp;&nbsp;金元宝
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务参与奖励:</label>
        	<input class="con-email3" name="unfulfilReward" id="unfulfilReward" value="${task.unfulfilReward }"/>&nbsp;&nbsp;金元宝
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务来源:</label>
        	<input class="con-email" name="taskFrom"  id="taskFrom" value="${task.taskFrom }"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4">公司内部对接人:</label>
        	<input class="con-email" name="staff"  id="staff" value="${task.staff }"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >审核时间:</label>
        	<input class="con-email" name="verifyTime"  id="verifyTime" value="${task.verifyTime }"/> 天
          </p>
        	<p class="clearfix">
	        	<label class="textw100" for="con-email4" >任务内容:</label>
                <div class="textareabox">
                	<textarea id="taskContent1" style="display: none;">${task.taskContent }</textarea>
				<script type="text/plain" id="taskContent" name="taskContent"></script>
				<script type="text/javascript">
					var um = UM.getEditor('taskContent',{initialFrameHeight : 329,initialFrameWidth : 660,autoHeightEnabled : false});
					UM.getEditor('taskContent').setContent($("#taskContent1").val());
				</script>
                </div>
         	 </p>             
                
           <p class="renwuanniubox">
             <input type="submit" value="任务预览" class="renwuanniublue"><input type="submit" value="提交修改" class="renwuanniublue">          
           </p>
        </form>
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
