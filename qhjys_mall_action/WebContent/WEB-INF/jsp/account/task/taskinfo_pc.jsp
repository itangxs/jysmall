<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 理财赚钱-飞券-答题赚钱-网赚-游戏赚钱-飞券网</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/index2.css" rel="stylesheet" type="text/css" />
<link href="/css/task.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/js/account/task.js" type="text/javascript"></script> 
</head>
<body class="taskdetailbg" style="background:${task.bgcolor };">

<!--banner-s-->
<div class="taskdetailall">
		<input type="hidden" id="project" value="${task.project }" />
    	<input type="hidden" id="userId" value="${userId }" />
	<div class="taskdetailbanner"  style="background: url(${task.bgimg }) no-repeat top center"></div>
    <div class="task-detailbox1">
    	<div class="detailtop">
    	<img src="${task.images }">
    	<div class="detailtoptext">
        	<p class="hui18">${task.taskName }</p>
            <p class="height100">${task.fulfilDetail }</p>
            <p>奖励：<span class="red16">${task.fulfilReward }</span>金元宝</p>
         <p class="lingqubutton">  <c:if test="${empty userId}">
					<a href="/account/login.do">开始任务</a>
				</c:if>
             <c:if test="${not empty userId}">
             <a target="__blank" href="${task.projectUrl }${userId}">开始任务</a></c:if></p>
        </div>
    </div>
    <div class="task-detailbox">
	
    <div class="detailtitle"><span></span>体验流程</div>
    <div class="liuchengbox">
    	<img src="/images/tiyanliucheng2.jpg">
    </div>
    <div class="detailtitle"><span></span>我的信息</div>
    <!--提交前-->
    <c:if test="${not empty userId}">
    <c:if test="${empty userTask}">
    <div class="infobox1" style="display:;">
    	手动提交信息:
    	<input type="hidden" id="infohid" value="${task.infoName }" />
    	<input type="hidden" id="project" value="${task.project }" />
    	<input type="hidden" id="userId1" value="${userId }" />
        <input class="inputbk" name="" id="inputinfo" type="text" value="${task.infoName }">
      <input class="redbuttom" id="infobut" type="submit" name="button" id="button" value="提交">
    </div> 
    </c:if>
    <c:if test="${not empty userTask}">
    <!--提交后-->
    <div class="infobox1">
    	当前状态：<span class="fontred">已体验，等待审核</span><span class="lr100">|</span>体验日期：<span class="fontred"><fmt:formatDate value="${userTask.createTime }" pattern="yyyy-MM-dd HH:mm"/></span><span class="lr100">|</span>体验内容：<span class="fontred">[招募]</span>
  	</div> 
  	</c:if> 
  	</c:if> 
    <div class="detailtitle"><span></span>任务介绍</div>
	<div class="infobox2">
    	${task.fulfilDetail }
	</div>
    <div class="detailtitle"><span></span>奖励规则</div>
  	<div class="infobox">
    	【活动时间】长期活动<span class="kgw125"></span>【参与次数】1次<span class="kgw125"></span>【发放方式】自动发放奖励<span class="kgw125"></span>【审核时间】1-3工作日
        ${task.taskDetail }
  </div> 
    <div class="detailtitle"><span></span>温馨提示</div>
    <div class="infobox2">
    	<p>在体验过程中如发现以下行为，将不能获取奖励，严重者直接封号。<br>
        1、玩家请勿在完成任务中谈论奖励相关事情，例如："我是为了某某金元宝来的"，一经发现封号处理。<br>
2、所有任务只取第一次点击并注册成功的任务数据发放奖励。<br>
3、严禁在该网站注册多个网站账号以非法获取金币奖励。<br>
注：飞券网为信息发布平台，任务中用户所有充值及投资均属个人行为，一切后果由任务所属的机构负责。</p>
  </div> 
</div>
</div>
</div>

</body>
</html>