<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js "></script>
<script type="text/javascript" src="/js/system/task/task_list.js"></script>

<style type="text/css">.uploadify {margin-left: 50px;}</style>

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
	<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">CMS列表</h3>
			<form id="from" action="/managermall/systemmall/task/listTask.do" method="post" class="zcform1">
			<input type="hidden" id="page" name="pageNum" value="${tasks.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${tasks.getPageSize()}">
				<p class="clearfix" style="margin-bottom:10px;">
					
		        	<label class="one" for="con-email4" >任务来源:</label>
        			<input class="con-email4" name="taskFrom" id="taskFrom" value="${taskFrom }"/>
		        	<label class="one" for="con-email4" >任务名称:</label>
        			<input class="con-email4" name="taskName" id="taskName" value="${taskName }"/>
		        	<label class="one" for="con-email4" >创建时间:</label>
        			<input id="startTime" name="startTime" class="easyui-datebox con-time" value="${startTime}">
					<label class="one" for="con-email2">-</label>
					<input id="endTime" name="endTime" class="easyui-datebox con-time" value="${endTime}">
		        	<label class="one" for="con-email4" >类型:</label>
        			<select id="taskType" name="taskType">
        				<option value="">全部</option>
        				<option value="0" <c:if test="${taskType ==0 }">selected="selected"</c:if>>新手任务</option>
        				<option value="1" <c:if test="${taskType ==1 }">selected="selected"</c:if>>问卷</option>
        				<option value="4" <c:if test="${taskType ==4 }">selected="selected"</c:if>>商家任务</option>
        				<option value="5" <c:if test="${taskType ==5 }">selected="selected"</c:if>>注册</option>
        				<option value="6" <c:if test="${taskType ==6 }">selected="selected"</c:if>>金融</option>
        				<option value="7" <c:if test="${taskType ==7 }">selected="selected"</c:if>>游戏</option>
        			</select>
		        	<label class="one" for="con-email4" >状态:</label>
		        	<select id="status" name="status">
        				<option value="">全部</option>
        				<option value="1" <c:if test="${status ==1 }">selected="selected"</c:if>>未启用</option>
        				<option value="2" <c:if test="${status ==2 }">selected="selected"</c:if>>启用</option>
        				<option value="3" <c:if test="${status ==3 }">selected="selected"</c:if>>暂停</option>
        				<option value="4" <c:if test="${status ==4 }">selected="selected"</c:if>>已完成</option>
        			</select>
					<input type="submit" value="查询" class="submit8" style="margin-left:20px">
				</p>
			</form>
			<form id="form1" name="form1"  class="zcform1">
	           	<p class="clearfix">
	             	<input type="submit" value="启用" class="submit9" onclick="updateStauts(2)">
	             	<label for="select">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
	             	<input type="submit" value="暂停" class="submit9"  onclick="updateStauts(3)">
	           	</p>
         	</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td80">
                  	  	<input id="quan" type="checkbox" name="quan" onclick="xuan('qxx')"/>
						<th class="td160">任务来源</th>
	                    <th class="td160">任务名称</th>
	                    <th class="td160">任务编号</th>
	                    <th class="td160">商家密码</th>
	                    <th class="td160">奖励</th>
	                    <th class="td160">领取数</th>        
	                    <th class="td160">已完成数</th>        
	                    <th class="td160">开始日期</th>        
	                    <th class="td160">结束日期</th>        
	                    <th class="td160">下架日期</th>        
	                    <th class="td160">状态</th>     
	                    <th class="td240">操作</th>
					</tr>
					<c:forEach var="it" items="${tasks}">
					<tr>
						<td class="td80">
	                   	 <input name="ids" type="checkbox" value="${it.id}" onclick="xuan('dx')" />
	                   	 </td>
						<td class="td160">${it.taskFrom}</td>
						<td class="td160">${it.taskName}</td>
						<td class="td160">${it.project }</td>
						<td class="td160">${it.projectPass }</td>
						<td class="td160">${it.fulfilReward}</td>
						<td class="td160">${it.taskTotal}</td>
						<td class="td160">${it.taskFulfil}</td>
						<td class="td160"><fmt:formatDate value="${it.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="td160"><fmt:formatDate value="${it.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="td160"><fmt:formatDate value="${it.offShelf}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class="td160">
						<c:if test="${it.status==1}">未启用</c:if>
						<c:if test="${it.status==2}">启用</c:if> 
						<c:if test="${it.status==3}">暂停</c:if> 
						<c:if test="${it.status==4}">已完成</c:if> 
						</td>
						<td class="td160"><a href="/managermall/systemmall/task/getTask.do?id=${it.id}">查看</a>
						<a href="/managermall/systemmall/task/updateTask.do?id=${it.id}">修改</a><br>
						<a href="javascript:zhiding('${it.id}')">置顶</a></td>
					</tr>					
					</c:forEach>
				</table>
			</div>
			<!--上一页下一页-->
		 <div class="page">
            	<c:if test="${tasks.getPageNum()>1}"> 
            	<a href="javascript:previousPage(${tasks.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${tasks.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${tasks.getPages()}">
            			<c:choose><c:when test="${i==tasks.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${tasks.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==tasks.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${tasks.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${tasks.getPages()-3}">
            			<c:if test="${i==tasks.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${tasks.getPageNum()<tasks.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${tasks.getPages()-2}" end="${tasks.getPages()}">
            			<c:choose><c:when test="${i==tasks.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${tasks.getPages()>tasks.getPageNum()}"><a href="javascript:previousPage(${tasks.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
                <span>共${tasks.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${tasks.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
		</div>
		<div class="clear"></div>
	</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/uFooter.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
