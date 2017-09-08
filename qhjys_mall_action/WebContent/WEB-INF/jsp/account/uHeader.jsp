<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!--top菜单-->
<div class="topmenu">
	<div class="topmenu1">
    	    	
		<div class="left-city" >
			<input id="city1" type="hidden" value="${cityId}"  />
			<span id="city"  style="text-align: right;" ></span>
			<h4 style="margin-left: 20px; ">
				<a id="switchCity" href="javascript:;">切换城市<span class="arrow-down-logo"></span></a>
			</h4>
			<div id="citys" class="city-list" style="display:none;">
				<h3>热门城市</h3>
				<ul>
					<li><a href="/changeCity.do?cityId=200">深圳市</a></li>
					<li><a href="/changeCity.do?cityId=203">佛山市</a></li>
				</ul>
			</div>
		</div>
		<h2>
			<ul>
				<c:if test="${empty sessionScope.user}">
				<li>
					<i><a href="/account/login.do">登录</a>|<a href="/account/registrationByTel.do">注册</a></i>
				</li>
				</c:if>
				<c:if test="${!empty sessionScope.user}">
				<li>
					<i>
						<em>hi，<a href="/account/task/mytask.do?mstatus=11">
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  != 'JYS' && sessionScope.user.nickname != ''}">${sessionScope.user.nickname }</c:if>
						<c:if test="${fn:substring(sessionScope.user.nickname, 0, 3)  == 'JYS' || sessionScope.user.nickname == ''}">
							${empty sessionScope.user.realname?sessionScope.user.username:sessionScope.user.realname}
						</c:if>
						
						</a></em>
						<em id="xiaoxi"></em><a href="/managermall/account/logoutUser.do">退出</a>
					</i>
				</li>
				<li><a href="/account/task/mytask.do?mstatus=11">我的账户</a><span class="line"></span></li>
				<!--<li><a href="/managermall/account/toCartConfirm.do">购物车</a><span class="line"></span></li>-->
				</c:if>
				<li>
				
					<h4>
						<a id="switchCity1" href="javascript:;" class="menuyes">服务中心<span class="arrow-down-logo"></span></a>
					</h4>
					<div id="citys1" class="servicexiala" style="display:none;z-index: 9999">
						<ul>
							<!--<li><a href="/mallcms/info3.do?id=24">使用指南</a></li>-->
							<li><a href="/mallcms/info.do?id=5">关于我们</a></li>
						 </ul>
					</div>				
				</li>
                
				<li>
				
					<h4>
						<a id="switchCity2" href="javascript:;" class="menuyes">微信支付<span class="arrow-down-logo"></span></a>
					</h4>
					<div id="citys2" class="city-list2" style="text-align: center;display:none;">
						<p style="text-align: center;">飞券网</p>
						<P style="text-align: center;">
							<img src="/images/weixinpay.jpg" />
						</P >
						<p style="text-align: center;">关注公众号进行微信支付</p>
					</div>
					
					
					<!-- 
                    <h4>
						<a id="switchCity2" href="javascript:;" class="menuyes">APP下载<span class="arrow-down-logo"></span></a>
					</h4>
					<div id="citys2" class="city-list2" style="text-align: center;display:none;">
						<p style="text-align: center;">飞券网：jysmall</p>
						<P style="text-align: center;">
							<img src="/images/weixin.jpg" />
						</P >
						<p style="text-align: center;">下载飞券手机版，开启免费之旅</p>
					</div>
                    <a href="javascript:;" class="menuyes">微信公众号
					<span class="arrow-down-logo"></span></a><span class="line"></span>
					<div class="weixinxiala" style="display: none">
						<p>飞券网：jysmall</p>
						<P>
							<img src="/images/weixin.jpg" />
						</P>
						<p>扫一扫，关注金钥匙公众号</p>
					</div> -->
				</li>
                <!--<li>
					
						<h4>
							<a id="switchCity3" href="javascript:;" class="menuyes">商家APP下载<span class="arrow-down-logo"></span></a>
						</h4>
						<div id="citys3" class="city-list2" style="text-align: center;display:none;">
							<p style="text-align: center;">飞券网商家版</p>
							<P style="text-align: center;">
								<img src="/images/shangjia2.png" />
							</P >
							<p style="text-align: center;">下载飞券商家手机版，开启赚钱之旅</p>
						</div>
					</li>-->
			</ul>
		</h2>
	</div>
</div>
<div class="topmain">
	<!--top广告-->

	<div class="top_center">
		<h1>
			<a href="/"><img src="/images/logo.png" /></a>
		</h1>
		<!--top搜索-s-->
		<div class="topsearch">
			<div id="searchType" class="topsearch_xxk">
				<a href="javascript:;" class="xxkcur">搜商品</a>
				<a href="javascript:;">搜商铺</a>
				<div class="clear"></div>
			</div>
			<input id="keywork" name="keywork" onkeydown="javascript:if(event.keyCode==13) search();" value="${keywork }" class="inputsearch" placeholder="请输入搜索文字"/>
			<input name="" type="submit" value="搜索" class="inputanniu" onclick="search();" />
		</div>
		 <c:if test="${!empty sessionScope.user}">
			 <script type="text/javascript">
					 gwc();
			</script>
		</c:if>
		<!--<div id="myCart">
			<div class="indextop_jiesuan_cishu" id="jesuan_cisuan">0</div>
			<div class="indextop_jiesuan"><a href="javascript:;">去购物车兑换</a></div>
			<div class="indexgouwuche_tanchu" style="display:none;"><ul id="cartDet"></ul></div>
		</div>-->
	</div>
</div>
<!--右侧滚动条-->
<!--<div class="saomaall"><div class="saoma"><div class="saoma1"><span><a href="#"></a></span><img src="/images/saoma.png" /></div></div></div>-->
<!--主导航条-->
<div class="top_nav">
	<div class="top_nav_content">
		<div class="alltypenav"><a href="javascript:void(0);" class="linkbg">全部分类</a></div>
		<div id="categoryHtml" class="alltypenavxiala" style="display:none;"></div>
		<!--<div class="sellenter"><a href="/seller/regsiter.do" class="bg2">商家入驻</a></div>-->
		<div class="navmain">
			<a href="/">首页</a>
            <!--<a href="/account/task/index.do">任务</a>
            <a href="/account/about.do">了解飞券<span class="topfeijuan"></span></a>-->
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</div>

<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript">
$(function() {
	var _url = window.location.href;
	if (_url.indexOf("searchStore") > -1)
		$($("#searchType>a").removeClass("xxkcur")[1]).addClass("xxkcur");
	$.ajax({
		async : true,
		type : "POST",
		url : '/getCategoryHtml.do',
		success : function(data) {
			$("#categoryHtml").html(data);
			$("div.alltypenav").mouseover(function() {
				$(this).next().css("display", "block");
				$("a", this).addClass("_hover");
			}).mouseout(function() {
				$(this).next().css("display", "none");
				$("a", this).removeClass("_hover");
			});
			$("div.alltypenavxiala").mouseover(function() {
				$(this).css("display", "block");
				$("div.alltypenav>a").addClass("_hover");
			}).mouseout(function() {
				$(this).css("display", "none");
				$("div.alltypenav>a").removeClass("_hover");
			});
			var menu = $("#menu_pop"), mendiv = null;
			$("#menu li").mouseover(function() {
				$(this).addClass("current");
				menu.children().css("display", "none");
				mendiv = $("div[lang=" + this.lang + "]", menu).css("display", "block");
				menu.css("display", "block");
			}).mouseout(function() {
				$(this).removeClass("current");
				menu.css("display", "none");
			});
			menu.mouseover(function() {
				var lang = mendiv.attr("lang");
				$("#menu li[lang=" + lang + "]").addClass("current");
				$(this).css("display", "block");
			}).mouseout(function() {
				var lang = mendiv.attr("lang");
				$("#menu li[lang=" + lang + "]").removeClass("current");
				$(this).css("display", "none");
			});
		}
	});
});
</script>
<script type="text/javascript">
if ($.browser.msie && parseInt($.browser.version, 10) <= 7) { 
strAlertWrapper.css({position:'fixed', bottom:'0', height:'auto', left:'auto', right:'auto'}); 
} 
</script>