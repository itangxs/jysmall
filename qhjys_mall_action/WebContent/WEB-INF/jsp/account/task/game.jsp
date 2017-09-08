<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>金钥匙商城</title>
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/gamedetail.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script src="/js/account/game.js" type="text/javascript"></script>

</head>

<body style="background:#ffffff;">

<div class="gamedetailall">
	<div class="gamedetailbanner"  style="background: url(${empty task.bgimg?'/images/gamebanner.jpg':task.bgimg}) no-repeat top center"></div>
    <div class="game-detailbox1">
    	<div class="game-detailbox">
        	<div class="gametop">
            	<dl>
                	<dd class="leftbox">游戏名称：<span>${task.taskName }</span>游戏类型：<span>页游</span></dd>
                	<c:if test="${empty userTask }">
                		<dd class="rightbox"><p>您<span class="gameorg">还未注册</span>${task.taskName }，请先体验该游戏</p>
                		<a target="_blank" href="${task.projectUrl }"><img src="/images/gamebutton.png" /></a></dd>
                	</c:if>
                	<c:if test="${not empty userTask }">
                		<dd class="rightbox"><p>您<span class="gameorg">已注册</span>${task.taskName }，可以继续体验该游戏</p>
                		<a target="_blank" href="${task.projectUrl }"><img src="/images/gamebutton.png" /></a></dd>
                	</c:if>
                    
                    
                </dl>
       	    	<img src="/images/game-buzhou2.jpg" />
            </div>
            <!--内容区域-->
            <div class="gamecontent">
            	<div class="gametitle">充值返利</div>
                  	<div class="gamerightbox2">
                    	<p>体验期内所有玩家参与单笔充值<span class="gameorg">10元</span>之间金额返利<span class="gameorg">30%</span>同等金元宝；（如：充值<span class="gameorg">10元</span>返<span class="gameorg">60个金元宝）</span><br />
               	    注：奖励将于每5-7个工作日直接到账！</p>
                    	<table width="100%" border="0" cellspacing="1" cellpadding="0">
                    	<tr>
                            <th class="gametd33">充值时间</th>   
                            <th class="gametd33">充值金额</th>              	   
                            <th class="gametd33">奖励金额</th>
                          </tr>
                    	<c:forEach items="${userTasks }" var="ut">
	                    	  <tr>
	                            <td class="gametd33"><fmt:formatDate value="${ut.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td class="gametd33">${ut.money }元</td>
	                            <td class="gametd33 colorred">${ut.totamt }金元宝
	                            	<c:if test="${ut.status == 'c' }">
	                            		<a href="javascript:changec('${ut.id }')">点击领取</a>
	                            	</c:if>
	                            </td>
	                          </tr>
                    	</c:forEach>
                         
                      </table>
                    </div>
            	<div class="leftbox">
                	<div class="gametitle">我的信息<span class="refresh"><a href="javascript:refresh('${userId }','${task.project }','${task.id}')"><img src="/images/refresh.jpg" /></a></span></div>
               	  <div class="gameleftbox1">
               	  <c:if test="${empty userTask }">
                   	暂无游戏信息 </c:if>
               	  <c:if test="${not empty userTask }">
                    	角色名：<span class="gameorg">${userTask.gameName }</span><br />
                    	等级：<span class="gameorg">LV.${userTask.gameLevel }</span><br />
               	  		最新更新时间：<fmt:formatDate value="${userTask.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </c:if>
               	  	</div>                  	
                </div>
                <div class="rightbox">  
                    <div class="gametitle">温馨提示</div>
                  	<div class="gamerightbox2">
                   	 	${task.taskDetail }
                   	 	 
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>