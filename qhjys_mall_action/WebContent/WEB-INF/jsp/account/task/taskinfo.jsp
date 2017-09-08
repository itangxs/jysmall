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
<link rel="stylesheet" type="text/css" href="/css/taskdetail.css">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="/js/account/task.js" type="text/javascript"></script>
</head>
<body>
<div class="module">
<!--banner-s-->
    	<div class="detailtop">
    	<img class="img1" src="${task.images }">
    	<div class="detailtoptext">
        	<p class="hui18">${task.taskName }</p>
            <p class="height100">${task.fulfilDetail }</p>
            <p class="hui14">奖励：<span class="red16">${task.fulfilReward }</span>金元宝</p>
            <p class="lingqubutton"><a  target="__blank"  href="${task.projectUrl }${userId}">开始任务</a></p>
        </div>
    </div>
    <div class="task-detailbox">
	
    <div class="detailtitle"><span></span>体验流程</div>
    <div class="liuchengbox">
    	<img src="/images/tiyanliucheng1.jpg">
    </div>
    <div class="detailtitle"><span></span>我的信息</div>
    <!--提交前-->
    <c:if test="${empty userTask}">
    <div class="infobox1" style="display:;">
    	提交信息:
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
    <div class="detailtitle"><span></span>任务介绍</div>
	<div class="infobox2">
    	${task.fulfilDetail }
	</div>
    <div class="detailtitle"><span></span>奖励规则</div>
  	<div class="infobox">
    	<ul>
        	<li>活动时间：长期活动</li>
        	<li>参与次数：1次</li>
        	<li>发放方式：自动发放奖励</li>
        	<li>审核时间：1-3工作日</li>
        	<div class="clear"></div>
      	</ul>
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

</body>
</html>