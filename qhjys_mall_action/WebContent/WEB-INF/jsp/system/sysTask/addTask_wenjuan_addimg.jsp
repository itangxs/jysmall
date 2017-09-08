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
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 class="renwutitle">题目编辑（图片型）</h3>
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/task/saveTask.do" method="post">
           <p class="clearfix">
        	<label class="textw100" for="con-email4" >题干内容:</label>
        	<input class="con-email" name="taskName" id="taskName" value="请输入"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >形&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;式:</label>
        	<select  name="taskType" id="taskType">
        		<option value="">请选择...</option>
        		<option value="0">文本</option>
        		<option value="1">图片</option>
        		<option value="2">视频</option>
        	</select>
          </p>          
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >题目时长:</label>
        	<input class="con-email3" name="fulfilReward" id="fulfilReward"/>&nbsp;&nbsp;秒
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >视频/图片:</label>
        	<input class="con-email" name="taskFrom" value="选择" id="taskFrom"/>
          </p>          
                
           <p class="renwuanniubox2">
             <input type="submit" value="确认" class="renwuanniublue"><input type="submit" value="取消" class="renwuanniublue">          
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
