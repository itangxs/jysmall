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
<link type="text/css" rel="stylesheet" href="/css/system/renwu.css">
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
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 style="padding-left:10px; margin-bottom:25px;">添加任务</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/task/saveTask.do" method="post">
           <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务名称:</label>
        	<input class="con-email4" name="taskName" id="taskName"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务类别:</label>
        	<select  name="taskType" id="taskType">
        		<option value="">请选择...</option>
        		<option value="0">新手任务</option>
        		<option value="1">问卷</option>
        		<option value="4">商家任务</option>
        		<option value="5">注册</option>
        		<option value="6">金融</option>
        		<option value="7">游戏</option>
        	</select>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务来源:</label>
        	<input class="con-email4" name="taskFrom" id="taskFrom"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" id="renwubianhao">任务编号:</label>
        	<input class="con-email4" name="project" id="project"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务地址:</label>
        	<input class="con-email4" name="projectUrl" readonly="" id="projectUrl"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >信息提交名称:</label>
        	<input class="con-email4" name="infoName" id="infoName"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >信息提交地址:</label>
        	<input class="con-email4" name="infoUrl" id="infoUrl"/>
          </p>
          <p class="clearfix" id="propass" style="display: none;">
        	<label class="textw100" for="con-email4" >商家密码:</label>
        	<input class="con-email4" name="projectPass" id="projectPass"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务等级:</label>
        	<select  name="taskLevel" id="taskLevel">
        		<option value="">请选择...</option>
        		<option value="0">不限</option>
        		<option value="1">VIP1</option>
        		<option value="2">VIP2</option>
        		<option value="3">VIP3</option>
        		<option value="4">VIP4</option>
        		<option value="5">VIP5</option>
        		<option value="6">VIP6</option>
        	</select>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >开始时间:</label>
        	<input id="beginTime" name="beginTime1" class="easyui-datetimebox con-time">
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >结束时间:</label>
        	<input id="endTime" name="endTime1" class="easyui-datetimebox con-time">
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >下架时间:</label>
        	<input id="offShelf" name="offShelf1" class="easyui-datetimebox con-time">
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务总量:</label>
        	<input class="con-email4" name="taskTotal" id="taskTotal"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >预估完成时长:</label>
        	<input class="con-email4" name="planTime" id="planTime"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务完成奖励:</label>
        	<input class="con-email4" name="fulfilReward" id="fulfilReward"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务参与奖励:</label>
        	<input class="con-email4" name="unfulfilReward" id="unfulfilReward"/>
          </p>
          <p class="clearfix" id="upimg" >
        	<label class="textw100" for="con-email">商家图片：</label>
        	<input type="hidden" id="sptp" name="sptp"/>
     		<div id="upimgdiv" class="textareabox"  >
	        	<img style="border:1px solid #ccc;"  id="img1"  src="" width="186" height="123" alt="图片" />
	        	<input type="hidden" id="img1u" name="timage" value="图片 "/>
	        	  <input  class="uploadify" style="margin-left:100px;"  type="file" value="上传" id="update1"/>
        	</div></p>
        	 <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务背景色:</label>
        	<input class="con-email4" name="bgcolor" id="bgcolor"/>
          </p>
          <p class="clearfix" id="upimg" >
        	<label class="textw100" for="con-email">任务背景图：</label>
        	<input type="hidden" id="sptp" name="sptp"/>
     		<div id="upimgdiv" class="textareabox"  >
	        	<img  id="img2" style="border:1px solid #ccc;"   src="" width="186" height="123" alt="图片" />
	        	<input type="hidden" id="img2u" name="timage1" value=" "/>
	        	  <input  class="uploadify" type="file" value="上传" id="update2"/>
        	</div></p>
        	<!-- <div style="position:relative;display: inline-block;">
	        	<img  id="img2"  src="" width="150" height="120" alt="" />
	        	<input type="hidden" id="img2u" name="timage" value=" "/>
	        	 <input  class="uploadify" type="file" value="上传" id="update2"/>
        	</div>
        	<div style="position:relative;display: inline-block;">
	        	<img  id="img3"  src="" width="150" height="120" alt="" />
	        	<input type="hidden" id="img3u" name="timage" value=" " />
	        	<input  class="uploadify" type="file" value="上传" id="update3"/>
        	</div>
        	<div style="position:relative;display: inline-block;">
		       	<img  id="img4"  src="" width="150" height="120" alt="" />
		       	<input type="hidden" id="img4u" name="timage" value=" " />
		       	 <input  class="uploadify" type="file" value="上传" id="update4"/> 
        	</div></p> -->
        	<p class="clearfix">
	        	<label class="textw100" for="con-email4" >任务介绍:</label>
	        	<textarea class="textareawidth" id="fulfilDetail" name="fulfilDetail" cols="70" rows="7"></textarea>
         	 </p>
                <p class="clearfix">
        		<label class="textw100" for="con-email">任务步骤：</label>
        	 </p>
        	 <p class="clearfix textareabox">
        	 	<textarea id="taskDetail1" style="display: none;"></textarea>
				<script type="text/plain" id="taskDetail" name="taskDetail"></script>
				<script type="text/javascript">
					//实例化编辑器
					var um = UM.getEditor('taskDetail',{initialFrameHeight : 329,initialFrameWidth : 533,autoHeightEnabled : false});
				</script>
			</p> 
           <p  class="renwuanniubox" style="padding:0 150px 0 0;">
             <input type="submit" value="增加任务" class="renwuanniublue">          
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
