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
            <h3 style="padding-left:10px; margin-bottom:25px;">修改任务</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/task/modifyTask.do" method="post">
           <p class="clearfix">
        	<label class="one" for="con-email4" >任务名称:</label>
        	<input type="hidden" name="id" id="id" value="${task.id }"/>
        	<input class="con-email4" name="taskName" id="taskName" value="${task.taskName }"/>
          </p>
          <p class="clearfix"> 
        	<label class="one" for="con-email4" >任务类别:</label>
        	<select  name="taskType" id="taskType">
        		<option value="">请选择...</option>
        		<option value="0" <c:if test="${task.taskType ==0 }">selected="selected"</c:if>>新手任务</option>
        		<option value="1" <c:if test="${task.taskType ==1 }">selected="selected"</c:if>>问卷</option>
        		<option value="4" <c:if test="${task.taskType ==4 }">selected="selected"</c:if>>商家任务</option>
        		<option value="5" <c:if test="${task.taskType ==5 }">selected="selected"</c:if>>注册</option>
        		<option value="6" <c:if test="${task.taskType ==6 }">selected="selected"</c:if>>金融</option>
        		<option value="7" <c:if test="${task.taskType ==7 }">selected="selected"</c:if>>游戏</option>
        		<option value="11" <c:if test="${task.taskType ==11 }">selected="selected"</c:if>>任务墙</option>
        	</select>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务来源:</label>
        	<input class="con-email4" name="taskFrom" id="taskFrom" value="${task.taskFrom }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务编号:</label>
        	<input class="con-email4" name="project" id="project" value="${task.project }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务地址:</label>
        	<input class="con-email4" name="projectUrl" id="projectUrl" value="${task.projectUrl }"/>
          </p>
           <p class="clearfix">
        	<label class="one" for="con-email4" >信息提交名称:</label>
        	<input class="con-email4" name="infoName" id="infoName" value="${task.infoName }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >信息提交地址:</label>
        	<input class="con-email4" name="infoUrl"  id="infoUrl" value="${task.infoUrl }"/>
          </p>
          <c:if test="${task.taskType ==4 }">
           <p class="clearfix" id="propass" style="display: none;">
        	<label class="one" for="con-email4" >商家密码:</label>
        	<input class="con-email4" name="projectPass" id="projectPass" value="${task.projectPass } "/>
          </p>
          </c:if>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务等级:</label>
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
        	<label class="one" for="con-email4" >开始时间:</label>
        	<input id="beginTime" name="beginTime1" class="easyui-datetimebox con-time" value="${beginTime }">
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >结束时间:</label>
        	<input id="endTime" name="endTime1" class="easyui-datetimebox con-time" value="${endTime }">
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >下架时间:</label>
        	<input id="offShelf" name="offShelf1" class="easyui-datetimebox con-time" value="${offShelf }">
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务总量:</label>
        	<input class="con-email4" name="taskTotal" id="taskTotal" value="${task.taskTotal }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >预估完成时长:</label>
        	<input class="con-email4" name="planTime" id="planTime" value="${task.planTime }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务完成奖励:</label>
        	<input class="con-email4" name="fulfilReward" id="fulfilReward" value="${task.fulfilReward }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email4" >任务参与奖励:</label>
        	<input class="con-email4" name="unfulfilReward" id="unfulfilReward" value="${task.unfulfilReward }"/>
          </p>
          <p class="clearfix">
        	<label class="one" for="con-email">商品图片：</label>
        	<input type="hidden" id="sptp" name="sptp"/>
     		<div >
	        	<img  id="img1"  src="${task.images}" width="150" height="120" alt="" />
	        	<input type="hidden" id="img1u" name="timage" value="${task.images}"/>
	        	  <input  class="uploadify" type="file" value="上传" id="update1"/>
        	</div>
        	</p>
        	 <p class="clearfix">
        	<label class="one" for="con-email4" >任务背景色:</label>
        	<input class="con-email4" name="bgcolor" id="bgcolor" value="${task.bgcolor}"/>
          </p>
        	 <p class="clearfix" id="upimg" >
        	<label class="one" for="con-email">任务背景图：</label>
        	<input type="hidden" id="sptp" name="sptp"/>
     		<div id="upimgdiv" >
	        	<img  id="img2"  src="${task.bgimg}" width="186" height="123" alt="" />
	        	<input type="hidden" id="img2u" name="timage1" value="${task.bgimg}"/>
	        	  <input  class="uploadify" type="file" value="上传" id="update2"/>
        	</div></p>
        	<p class="clearfix">
	        	<label class="one" for="con-email4" >任务介绍:</label><br><label class="one" for="con-email4" >&nbsp;&nbsp;&nbsp;</label>
	        	<textarea id="fulfilDetail" name="fulfilDetail" cols="70" rows="7">${task.fulfilDetail }</textarea>
         	 </p>
               <p class="clearfix">
        		<label class="one" for="con-email">任务步骤：</label>
        	 </p>
        	 <p class="clearfix">
        	 	<textarea id="taskDetail1" style="display: none;">${task.taskDetail }</textarea>
				<script type="text/plain" id="taskDetail" name="taskDetail"></script>
				<script type="text/javascript">
					//实例化编辑器
					var um = UM.getEditor('taskDetail',{initialFrameHeight : 329,initialFrameWidth : 533,autoHeightEnabled : false});
					UM.getEditor('taskDetail').setContent($("#taskDetail1").val());
					</script>
			</p> 
           <p class="clearfix">
             <input type="submit" value="修改" class="submit10">
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
