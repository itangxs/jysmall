<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!--top菜单-->
<div class="topmenu0">
	<div class="topmenu2">
		<h3>
			<b>您好，欢迎来飞券网！</b>
			<span><c:if test="${empty systemmanager}" ><a href="/systemlogin/login.do">登陆</a></c:if>
			<c:if test="${!empty systemmanager}" ><a href="/managermall/systemmall/loginout.do">退出</a></c:if></span>
			<span></span>
		</h3>
	</div>
</div>
<div class="wrapper">
	<div class="top_center">
		<h1>
			<a href="/"><img src="/images/logo.png" /></a>
		</h1>

	</div>
</div>
<div class="top_nav2">
	<div class="wrapper">
		<h3>
			<a href="/managermall/systemmall/product/list.do" class="${param.val eq '2'?'top_nav_current2':''}">商品管理</a>
			<a href="/managermall/system/order/list.do" class="${param.val eq '3'?'top_nav_current2':''}">订单管理</a>
			<a href="/managermall/systemmall/malluser/list.do" class="${param.val eq '4'?'top_nav_current2':''}">会员管理</a>
			<a href="/managermall/systemmall/store/storeAccountList.do" class="${param.val eq '5'?'top_nav_current2':''}">店铺管理</a>
			<a href="/managermall/systemmall/finance/userList.do" class="${param.val eq '6'?'top_nav_current2':''}">财务管理</a>
			<%-- <a href="javascript:;" class="${param.val eq '7'?'top_nav_current2':''}">营销推广</a> --%>
			<a href="/managermall/systemmall/statistics/salesRankingList.do" class="${param.val eq '8'?'top_nav_current2':''}"">运营统计</a>
			<a href="/managermall/systemmall/user/userList.do" class="${param.val eq '9'?'top_nav_current2':''}"">用户权限</a>
			<a href="/managermall/systemmall/cms/cmsList.do" class="${param.val eq '10'?'top_nav_current2':''}"">CMS管理</a>
			<a href="/managermall/systemmall/message/messagePage.do" class="${param.val eq '11'?'top_nav_current2':''}"">营销推广</a>
			<%-- <a href="/managermall/systemmall/task/listTask.do" class="${param.val eq '12'?'top_nav_current2':''}"">任务管理</a> --%>
			<a href="/managermall/systemmall/borrow/wxstorelist.do" class="${param.val eq '13'?'top_nav_current2':''}"">借贷管理</a>
		</h3>
		<div class="clear"></div>
	</div>
</div>