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
	<jsp:include page="/WEB-INF/jsp/system/task/uLeft.jsp" flush="true" ><jsp:param name="val" value="3" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
            <h3 class="renwutitle">添加问卷任务</h3>
            
            <form id="form1" name="form1"  class="zcform1" action="/managermall/systemmall/sysTask/saveTask.do" method="post">
           <p class="clearfix">
           <input type="hidden" name="taskType" id="taskType" value="1"/>
        	<label class="textw100" for="con-email4" >任务名称:</label>
        	<input class="con-email" name="taskName" id="taskName"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</label>
        	<input name="project" class="con-email6" id="project"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >等级限制:</label>
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
        	<label class="textw100" for="con-email4" >任务总量:</label>
        	<input class="con-email3" name="taskTotal" id="taskTotal"/>&nbsp;&nbsp;人
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务完成奖励:</label>
        	<input class="con-email3" name="fulfilReward" id="fulfilReward"/>&nbsp;&nbsp;金元宝
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务参与奖励:</label>
        	<input class="con-email3" name="unfulfilReward" id="unfulfilReward"/>&nbsp;&nbsp;金元宝
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >任务来源:</label>
        	<input name="taskFrom" class="con-email" id="taskFrom" value="填写商户编码"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >公司内部对接人:</label>
        	<input class="con-email" name="staff" value="填写对接人姓名" id="staff"/>
          </p>
          <p class="clearfix">
        	<label class="textw100" for="con-email4" >审核时间:</label>
        	<input class="con-email" name="verifyTime" value="" id="verifyTime"/> 天
          </p>
        	<!-- <p class="clearfix">
	        	<label class="textw100" for="con-email4" >题目内容:</label>
	        	<textarea class="textareawidth" id="fulfilDetail" name="fulfilDetail" cols="50" rows="8"></textarea>
         	 </p>
             <div class="member_myorder">
             <div class="renwuanniubox1"><input onclick="window.open('/test/wenjuan_addtext.do')" type="submit" value="正确答案" class="renwuanniublue"><input onclick="window.open('#')" type="submit" value="+增加题目" class="renwuanniublue"></div>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td40"><input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/></th>
                        <th class="td80"></th>
						<th class="tdauto" style="padding-left:100px;">名称</th>
	                    <th class="td80">形式</th>
	                    <th class="td80">时长</th>      
	                    <th class="td160">操作</th>     
	                    <th class="td120">位置</th>
					</tr>
					<tr>
						<td class="td40"><input name="ids" type="checkbox" value="${it.id}" onclick="xuan('dx')" /></td>
                        <td class="td80">题目1</td>
						<td class="tdauto"><a href="#">中国移动或者联通3个月以上用户</a></td>
						<td class="td80">文本</td>
						<td class="td80">20秒</td>
						<td class="td160"><a href="/test/wenjuan_addtext.do">编辑</a>&nbsp;&nbsp;&nbsp;<a href="">删除</a>&nbsp;&nbsp;&nbsp;<a href="">预览</a></td>
						<td class="td120"><a href="">↑上移</a>&nbsp;&nbsp;<a href="">↓下移</a></td>
					</tr>
                    <tr>
						<td class="td40"><input name="ids" type="checkbox" value="${it.id}" onclick="xuan('dx')" /></td>
                        <td class="td80">题目2</td>
						<td class="tdauto"><a href="#">中国移动或者联通3个月以上用户</a></td>
						<td class="td80">图片</td>
						<td class="td80">20秒</td>
						<td class="td160"><a href="/test/wenjuan_addimg.do">编辑</a>&nbsp;&nbsp;&nbsp;<a href="">删除</a>&nbsp;&nbsp;&nbsp;<a href="">预览</a></td>
						<td class="td120"><a href="">↑上移</a>&nbsp;&nbsp;<a href="">↓下移</a></td>
					</tr>
                    <tr>
						<td class="td40"><input name="ids" type="checkbox" value="${it.id}" onclick="xuan('dx')" /></td>
                        <td class="td80">题目3</td>
						<td class="tdauto"><a href="#">中国移动或者联通3个月以上用户</a></td>
						<td class="td80">视频</td>
						<td class="td80">20秒</td>
						<td class="td160"><a href="">编辑</a>&nbsp;&nbsp;&nbsp;<a href="">删除</a>&nbsp;&nbsp;&nbsp;<a href="">预览</a></td>
						<td class="td120"><a href="">↑上移</a>&nbsp;&nbsp;<a href="">↓下移</a></td>
					</tr>
                    <tr>
						<td class="td40"><input name="ids" type="checkbox" value="${it.id}" onclick="xuan('dx')" /></td>
                        <td class="td80">题目4</td>
						<td class="tdauto"><a href="#">中国移动或者联通3个月以上用户</a></td>
						<td class="td80">文本</td>
						<td class="td80">20秒</td>
						<td class="td160"><a href="">编辑</a>&nbsp;&nbsp;&nbsp;<a href="">删除</a>&nbsp;&nbsp;&nbsp;<a href="">预览</a></td>
						<td class="td120"><a href="">↑上移</a>&nbsp;&nbsp;<a href="">↓下移</a></td>
					</tr>		
				</table>
			</div>
                 -->
           <p class="renwuanniubox">
             <input type="submit" value="任务预览" class="renwuanniublue"><input type="submit" value="提交发布" class="renwuanniublue">          
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
