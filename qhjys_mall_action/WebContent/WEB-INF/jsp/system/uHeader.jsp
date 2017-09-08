<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 共用头部 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/qhjys_mall/seller-manage/css/public.css" rel="stylesheet" type="text/css" />
<link href="/qhjys_mall/seller-manage/css/membernew.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
</head>
<body>
<!--top菜单-->
<div class="topmenu0">	
    <div class="topmenu2">
		<h3>
            您好，欢迎来飞券网！ 
            <span><a href="../login.do">登陆</a></span> 
            <span><a href="../registration.do">注册</a></span>
        </h3>
	</div>
</div>
<div class="wrapper">
	<!--top广告-->
<div class="top_center">
    	<h1><a href="/"><img src="/qhjys_mall/seller-manage/images/logo.png" /></a></h1>
      
 </div>
</div>
<!------------------------------主导航--条-------------------------------------------->
<div class="top_nav2">
	<div class="wrapper">
        <h3>
        	<a href="web_set.do" class="${param.header eq '1'?'top_nav_current2':''}">网站设置</a>
            <a href="product_manage.do" class="${param.header eq '2'?'top_nav_current2':''}">商品管理</a>
            <a href="order_manage.do" class="${param.header eq '3'?'top_nav_current2':''}">订单管理</a>
            <a href="/managermall/systemmall/malluser/user_manage.do" class="${param.header eq '4'?'top_nav_current2':''}">会员管理</a>
            <a href="/managermall/systemmall/malluser/storeAccountList.do" class="${param.header eq '5'?'top_nav_current2':''}">商家管理</a>
            <a href="finance_manage.do" class="${param.header eq '6'?'top_nav_current2':''}">财务管理</a>
            <a href="Contrive.do" class="${param.header eq '7'?'top_nav_current2':''}">营销推广</a>
            <a href="statistics.do" class="${param.header eq '8'?'top_nav_current2':''}">运营统计</a>
            <div class="clear"></div>
        </h3>
        <div class="clear"></div>        
    </div>
</div>
</body>
</html>