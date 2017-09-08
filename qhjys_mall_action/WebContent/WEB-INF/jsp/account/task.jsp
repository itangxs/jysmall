<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"  href="/css/index2.css"/>
<link rel="stylesheet" type="text/css"  href="/css/public.css"/>
<link rel="stylesheet" type="text/css"  href="/css/task.css" />
<script type="text/javascript" src="/js/jquery.superslide.2.1.1.banner.js"></script>



<title>飞券网-网上生活服务首选（jysmall.com）-快捷、舒适、方便，及时、放心-乐享购物！</title>
<meta name="keywords" content="美食、电影、购物、旅游、休闲娱乐、丽人、生活服务、网上购物、积分购物、免费购物、金元宝、理财商城、积分兑换、积分商城、飞券网、理财购物、本地生活服务、O2O商城" />
<meta name="description" content="飞券网（jysmall.com），全国首个p2p平台推出的大型生活服务平台，通过它您不仅能享受积分返利，尊享积分兑换、理财等会员权益，同时还能提供O2O模式服务，包括美食、电影、旅游、休闲娱乐、丽人、生活服务等线上购物及线下消费体验。理财购物两不误，双赢的网上购物体验！" />

</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="dtlay_main">
	<div class="dtlay_left">
    	<div class="tasklist">
        	<div class="adv"><img src="/images/taskbanner.jpg"/></div>
        	<div class="taskbuzhou">
            	<span>20金元宝=1元</span><img src="/images/taskbuzhou.jpg"/>
            </div>  
                
            	
            </div>
            
        </div>
    <!--中通右侧-s-->
	<div class="dtlay_right">
    	<!--会员登陆-s-->
    	<div class="indexlogin">
    	<c:if test="${!empty sessionScope.user}">
    		<h1><span></span><a href="/account/task/mytask.do?mstatus=11"><strong>我的账户</strong></a></h1>
      <div class="indexlogin1">
            	<a href="/account/task/mytask.do?mstatus=11"><img class="img1" src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}"/></a>
                <div class="login1">
                	<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  != 'JYS' && sessionScope.user.nickname != ''}">${sessionScope.user.nickname }</c:if>
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  == 'JYS' || sessionScope.user.nickname == ''}">
							${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}
						</c:if><em>
					<c:if test="${sessionScope.user.level!=0}">VIP${sessionScope.user.level}</c:if></em><br />
                    现有元宝：<i><fm:formatNumber value="${sessionScope.userCashAccount.balance}"/></i><br />
                    冻结元宝：<fm:formatNumber value="${sessionScope.userCashAccount.freezeMoney}"/>        
                </div>
                <a href="/account/task/mytask.do?mstatus=11"><input class="anniu1" id="" name="" type="submit" value="我的账户" /></a>
            	<c:if test="${!sessionScope.judgeSignIn}"><input class="anniu" id="signIn" name="" type="submit" value="今日签到" /></c:if>
                <c:if test="${sessionScope.judgeSignIn}"><input class="anniuafter"  name="" type="submit" value="已签到" /></c:if>
            </div>  
    	</c:if>
    	<c:if test="${empty sessionScope.user}">
    	<h1><span></span>会员登录</h1>
            <div class="indexlogin1">
            	<form id="login" action="" method="get">
                    <input id="username" name="username" type="text" value="" class="inputtext inputbg1" />
                    <input id="password" name="password" type="password" value=""  class="inputtext inputbg2" />
                    <p><a href="/account/registrationByTel.do">免费注册</a> <span>|</span> <a href="/account/forgetpassword.do" class="fontred">忘记密码?</a></p>
                    <input class="anniu1" name="" type="submit" value="登 陆" />                
                </form>   
            </div>
    	</c:if>
                        	
        </div>
    </div>
    <div class="clear"></div>
</div>
<div class="tasktype">
	<ul>
    	<li><a href="/account/task/index.do?taskType=11" class="tasktype7"><span></span>任务街</a></li>
        <li><a href="/account/task/index.do?taskType=5" class="tasktype3"><span></span>注册</a></li> 
        <li><a href="/account/task/index.do?taskType=0" class="tasktype2"><span></span>新手</a></li>
        <li><a target="_blank" href="/account/task/shopping.do" class="tasktype6"><span></span>购物</a></li>
        <li><a href="/account/task/index.do?taskType=1" class="tasktype1"><span></span>问卷</a></li>
        <!--<li><a href="/account/task/index.do?taskType=6" class="tasktype4"><span></span>金融</a></li>-->
        <li><a href="/account/task/index.do?taskType=7" class="tasktype5"><span></span>游戏</a></li>
        <li><a href="/account/systask/index.do" class="tasktype4"><span></span>商家</a></li>
        <li class="tasktypemore1"><a href="javascript:;">暂无<br /><span>更多任务</span></a></li>
        <!--<li class="tasktypemore"><a href="/account/task/index.do?taskType=11">任务墙<br /><span>查看更多任务+</span></a></li>-->
        
        <div class="clear"></div>
    </ul>
</div>
<div class="taskcontent">
	<div class="tabs_task">
    	<div class="phone"><span></span> 用手机随时随地做任务</div>
    	<a href="/account/task/index.do?taskType=${taskType }&status=0" <c:if test="${status == 0 }">class="tabaction"</c:if>>可参与任务</a>
    	<c:if test="${taskType != 11 }"><a href="/account/task/index.do?taskType=${taskType }&status=1" <c:if test="${status == 1 }">class="tabaction"</c:if>>审核中任务</a>
    	<a href="/account/task/index.do?taskType=${taskType }&status=2" <c:if test="${status == 2 }">class="tabaction"</c:if>>已完成任务</a>
    	<a href="/account/task/index.do?taskType=${taskType }&status=3" <c:if test="${status == 3 }">class="tabaction"</c:if>>未通过任务</a></c:if>
        <div class="clear"></div>
    </div>
    <!--可参与任务-->
     <c:forEach var="it"  items="${page}">
	     <div class="tasknr"> 
	    	<div class="tasknrtop">
	        	<div class="img1"><img src="${empty it.images?'/images/wenjuan-nopic.jpg':it.images}"/></div>
	            <div class="nrright">
	            	<p class="colorblue">${it.taskName }</p>
	                <p class="tuijian"><span>${it.taskFulfil }人已完成</span>推荐<img src="/images/ding.png"/><img src="/images/ding.png"/><img src="/images/ding.png"/><img src="/images/ding.png"/><img src="/images/ding.png"/><img src="/images/ding.png"/></p>
	               <c:if test="${status == 0 }">
	               		<p class="jiage"><span><i>
	               		<a class="lingqu" href="/account/task/dotask.do?project=${it.project }&userId=${sessionScope.user.id}&ispc=1" target="_blank">开始任务</a>
	               		<!--  <c:if test="${it.taskType == 1||it.taskType == 5||it.taskType == 6}"><a class="lingqu" href="${it.projectUrl }${sessionScope.user.id}" target="_blank">立即领取</a></c:if>
            			 <c:if test="${it.taskType != 1 && it.taskType != 5 && it.taskType != 6}"><a class="lingqu" href="${it.projectUrl }" target="_blank">立即领取</a></c:if> -->
	               		</i></span><em>${it.fulfilReward }</em> 金元宝</p>
	               </c:if>
	               <c:if test="${status == 1 }">
	                <p class="jiage"><span><a href="/account/task/dotask.do?project=${it.project }&userId=${sessionScope.user.id}&ispc=1" target="_blank"><i class="shehezhong">审核中…</i></a></span><em>${it.fulfilReward }</em> 金元宝 </p>
	               </c:if>
	               <c:if test="${status == 2 }">
	                <p class="jiage"><span><a href="/account/task/dotask.do?project=${it.project }&userId=${sessionScope.user.id}&ispc=1" target="_blank"><i class="shehezhong">已完成</i></a></span><em>${it.fulfilReward }</em> 金元宝 </p>

	               </c:if>
	               <c:if test="${status == 3 }">
	                <p class="jiage"><span><i class="weitongguo">未通过</i></span><em>${it.totamt }</em> 金元宝 </p>
	               </c:if>
	            </div>
	            <div class="clear"></div>
	        </div>   	
	    	<div class="tasknrshenhe">
	        	<div class="overtime">结束日期 :<fm:formatDate value="${it.offShelf}" pattern="yyyy-MM-dd HH:mm"/></div>审核时间1-3个工作日
	        </div>
	    </div>
     </c:forEach>
  
    <div class="clear"></div>
	<!--上一页下一页-->
            <div class="page">
                <form action="/account/task/index.do" method="get">
                	<input type="hidden" name="taskType" value="${taskType }">
                	<input type="hidden" name="status" value="${status }">
                    <a href="/account/task/index.do?taskType=${taskType }&status=${status }&page=${page.getPageNum()-1 }" class="prev">上一页</a>
                    <a href="/account/task/index.do?taskType=${taskType }&status=${status }&page=${page.getPageNum()+1 }" class="next">下一页</a>
                    <span>第${page.getPageNum() }页,共${page.getPages()}页</span><span>到第</span>
                    <input class="input1" name="page" id="changepage" type="text" /><span>页</span><input class="anniu" id="changebut" type="button" value="确定" />
                </form>
            </div>
    <div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/index.js"></script>
<script type="text/javascript">
	$(function(){
		$("#changebut").click(function(){
			var pageNum = $("#changepage").val();
			if(pageNum != ""){
				window.location.href="/account/task/index.do?page="+pageNum+"&taskType=${taskType}&status=${status }";
			}
		}) 
	})
</script>
</body>
</html>