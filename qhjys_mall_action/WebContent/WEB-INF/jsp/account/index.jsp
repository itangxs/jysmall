<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"  href="/css/index.css"/>

<script type="text/javascript" src="/js/jquery.superslide.2.1.1.banner.js"></script>
<title>飞券网-网上生活服务首选（jysmall.com）-快捷、舒适、方便，及时、放心-乐享购物！</title>
<meta name="keywords" content="美食、电影、购物、旅游、休闲娱乐、丽人、生活服务、网上购物、积分购物、免费购物、金元宝、理财商城、积分兑换、积分商城、飞券网、理财购物、本地生活服务、O2O商城" />
<meta name="description" content="飞券网（jysmall.com），全国首个p2p平台推出的大型生活服务平台，通过它您不仅能享受积分返利，尊享积分兑换、理财等会员权益，同时还能提供O2O模式服务，包括美食、电影、旅游、休闲娱乐、丽人、生活服务等线上购物及线下消费体验。理财购物两不误，双赢的网上购物体验！" />

</head>
<body class="indexbg">
<jsp:include page="/WEB-INF/jsp/account/uHeader-index.jsp" flush="true" />
<!--banner包含部分-s-->	    
<div class="index_banner">
    <!--banner左侧菜单-s-->
    <div class="toppro_menu">
        <ul id="menu">
	        <c:forEach var="node" items="${category}">
	        <li lang="${node.id}">
	        	<h2
	        	  <c:if test="${node.id ==1}"> class="shipin"</c:if>
	        	  <c:if test="${node.id ==2}"> class="dianying"</c:if>
	        	  <c:if test="${node.id ==3}"> class="gouwu"</c:if>
	        	  <c:if test="${node.id ==4}"> class="lvyou"</c:if>
	        	  <c:if test="${node.id ==5}"> class="xiuxianyule"</c:if>
	        	  <c:if test="${node.id ==6}"> class="liren"</c:if>
	        	  <c:if test="${node.id ==7}"> class="shenghuofuwu"</c:if>
	        	></h2>
	        	<div class="memunav">
	        		<p class="menu2"><a href="searchProduct.do?type=sort&category=${node.id}">${node.text}</a></p>
	        		<p class="menu1">
		        		<c:forEach var="it" items="${node.childs}">
		        		<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a> 
		        		</c:forEach>
	        		</p>
	        	</div>
	        </li>
	        </c:forEach>
        </ul>
    </div>
    <!--banner左侧菜单弹出窗口-s-->
	<div id="menu_pop" class="toppro_menu_tanchu" style="display: none">
		<c:forEach var="node" items="${category}">
		<div lang="${node.id}" class="bannShow">
			<div class="tanchuadv">
				<c:forEach var="img" items="${sysImg}">
				<c:if test="${img.type==node.id}"><a href="${img.field}"><img src="${img.img}" /></a></c:if>
				</c:forEach>
			</div>
			<ul>
            	<li>
                    <h1><a href="searchProduct.do?type=sort&category=${node.id}">${node.text}</a></h1>
                    <h2><c:forEach var="it" items="${node.childs}">
                    	|<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>
                    </c:forEach></h2>
				</li>
            </ul>
		</div>
		</c:forEach>
	</div>
    <!--首页登录-s-->
    <div class="indexright_login">
    	<c:if test="${!empty sessionScope.user}">
	    	<h1><a href="/account/task/mytask.do?mstatus=11"><img src="${empty sessionScope.user.avatar?'/images/login_img.jpg':sessionScope.user.avatar}" /></a>
	   		<span id="say">上午好</span><br /><c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  != 'JYS' && sessionScope.user.nickname != ''}">${sessionScope.user.nickname }</c:if>
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  == 'JYS' || sessionScope.user.nickname == ''}">
							${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}
						</c:if></h1>
	   		<div class="indexloignafter">
	      		<em>金元宝： <span><fm:formatNumber value="${sessionScope.userCashAccount.balance}"/></span></em><br />
	      		<c:if test="${signIn}">
	      			<a href="javascript:;" class="button_red">已签到</a>
	      		</c:if>
	      		<c:if test="${!signIn}">
	      			<a id="signIn" href="javascript:;" class="button_red">签到</a>
	      		</c:if>
	      		<a href="/mallcms/info2.do?id=12" class="tixian">
	      			<c:if test="${sessionScope.user.level==0}">初级会员</c:if>
					<c:if test="${sessionScope.user.level!=0}">VIP${sessionScope.user.level}</c:if>
				</a>
	       		<!-- <a href="/managermall/account/myRecharge.do" class="button_red"> 充 值 </a> -->
	       		<!-- <a href="/managermall/account/myWithdraws.do" class="tixian"> 提 现 </a> -->
	   		</div>
    	</c:if>
    	<c:if test="${empty sessionScope.user}">
	    	<h1><img src="/images/login_img.jpg" /><span id="say">上午好</span><br />欢迎来到飞券网</h1>
	        <form id="login" action="" method="get">
				<input id="username" type="text" value="手机号/用户名/邮箱" />
				<input id="password" type="password" />
				<input class="anniu" type="submit" value="登 陆" />
	        </form>
	        <p>
	        	<a href="/account/registrationByTel.do">免费注册</a>
	        	<a href="/account/forgetpassword.do">忘记密码?</a>
	        </p>
    	</c:if>
    </div>
    <!-- 三张广告图 开始 -->
	<div class="banner">
        <ul class="bannerpic">
            <li><a href="#" target="_blank"><img src="images/banner_index1.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/banner_index2.jpg" /></a></li>
            <li><a href="#" target="_blank"><img src="images/banner_index3.jpg" /></a></li>
        </ul>
        <a class="prev" href="javascript:;"></a>
        <a class="next" href="javascript:;"></a>
        <div class="num">
            <ul></ul>
        </div>
    </div>
</div>
<!--推荐类别-s-->
<div class="wrapper index_type">
	<c:forEach var="node" items="${category}">
	<div 
	  	  <c:if test="${node.id ==1}"> class="title index_type_titlebg1"</c:if>
      	  <c:if test="${node.id ==2}"> class="title index_type_titlebg2"</c:if>
      	  <c:if test="${node.id ==3}"> class="title index_type_titlebg3"</c:if>
      	  <c:if test="${node.id ==4}"> class="title index_type_titlebg4"</c:if>
      	  <c:if test="${node.id ==5}"> class="title index_type_titlebg5"</c:if>
      	  <c:if test="${node.id ==6}"> class="title index_type_titlebg6"</c:if>
      	  <c:if test="${node.id ==7}"> class="title index_type_titlebg7"</c:if>
	>
		<b><a href="searchProduct.do?type=sort&category=${node.id}">${node.text}</a></b> |
		<i>
			<c:forEach var="it" begin="0" end="4" items="${node.childs}">
			<%-- <a href="searchProduct.do?type=sort&category=${it.id}">${it.text}</a> --%>
			<a href="/searchProduct.do?type=sort&category=${node.id}&sort=${it.id}">${it.text}</a>  •
			</c:forEach>
			<a href="searchProduct.do?type=sort&category=${node.id}">更多</a> >
		</i>
	</div>
	<div class="index_type_list">
		<div class="adv">
		  <c:if test="${node.id ==1}"> <img src="${img.pcMenuMeis}" /></c:if>
      	  <c:if test="${node.id ==2}"> <img src="${img.pcMenuDiany}" /></c:if>
      	  <c:if test="${node.id ==3}"> <img src="${img.pcMenuGouw}" /></c:if>
      	  <c:if test="${node.id ==4}"> <img src="${img.pcMenuLvyou}" /></c:if>
      	  <c:if test="${node.id ==5}"> <img src="${img.pcMenuXiux}" /></c:if>
      	  <c:if test="${node.id ==6}"> <img src="${img.pcMenuLiren}" /></c:if>
      	  <c:if test="${node.id ==7}"> <img src="${img.pcMenuShengh}" /></c:if>
		</div>
    	<ul>
        	<c:forEach var="prod" items="${product[node.text]}">
        	<li title="${prod.title}">
            	<a href="/getProdDetail.do?prodId=${prod.id}"><img src="${fn:substring(prod.images, 0, fn:indexOf(prod.images, '|'))}"/></a>
            	<h1><a href="/getProdDetail.do?prodId=${prod.id}">${prod.name}</a></h1>
                <h2><a href="/getProdDetail.do?prodId=${prod.id}">${prod.title}</a></h2>
                <h3>
                	<em><fm:formatNumber value="${prod.unitPrice}" type="CURRENCY"/></em>
                	<%-- <span><fm:formatNumber value="${prod.origPrice}" type="currency"/></span> --%>
                	<a href="/getProdDetail.do?prodId=${prod.id}">.......................</a>
                </h3>
            </li>
        	</c:forEach>
        </ul>
        <div class="clear"></div>
	</div>
	</c:forEach>
</div>
<!--推荐类别-e-->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/account/index.js"></script>
</body>
<script type="text/javascript">
	$(function(){
		
	})
</script>
</html>