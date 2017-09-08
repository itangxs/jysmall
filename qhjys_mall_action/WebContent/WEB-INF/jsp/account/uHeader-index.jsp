<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="/js/index.js"></script>
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
							<a href="/managermall/account/message/zlist.do">
								<c:if test="${!empty messagenum}">
									[${messagenum}]
								</c:if>
							</a>
							<a href="/managermall/account/logoutUser.do">退出</a>
						</i>
					</li>
					<li><a href="/account/task/mytask.do?mstatus=11">我的账户</a><span class="line"></span></li>
					<li><a href="/managermall/account/toCartConfirm.do">购物车</a><span class="line"></span></li>
					</c:if>
					<li>
					
						<h4>
							<a id="switchCity1" href="javascript:;" class="menuyes">服务中心<span class="arrow-down-logo"></span></a>
						</h4>
						<div id="citys1" class="servicexiala" style="display:none;z-index: 9999">
							<ul>
								<li><a href="/mallcms/info3.do?id=24">使用指南</a></li>
								<li><a href="/mallcms/info.do?id=5">关于我们</a></li>
							 </ul>
						</div>
						<!-- <a href="javascript:;">服务中心<span class="arrow-down-logo"></span></a><span class="line"></span>
						<div class="servicexiala" style="display:none;">
							<a href="javascript:;">购物指南</a><a href="javascript:;">售后服务</a><a href="javascript:;">支付方式</a><a href="javascript:;">关于我们</a>
						</div> -->
					</li>
					<li>
					
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
						<!-- <a href="javascript:;" class="menuyes">微信公众号
						<span class="arrow-down-logo"></span></a><span class="line"></span>
						<div class="weixinxiala" style="display: none">
							<p>飞券网：jysmall</p>
							<P>
								<img src="/images/weixin.jpg" />
							</P>
							<p>扫一扫，关注金钥匙公众号</p>
						</div> -->
					</li>
                    <li>
					
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
					</li>
				</ul>
			</h2>
		</div>
	</div>
	<div class="topmain">
		<div class="top_center">
			<h1>
				<a href="/"><img src="/images/logo.png" /></a>
			</h1>
			<!--top搜索-s-->
			<div class="topsearch">
				<div class="topsearch_xxk">
					<c:if test="${'store' == sType}">
						<a href="javascript:;">搜商品</a>
						<a href="javascript:;" class="xxkcur">搜商铺</a>
					</c:if>
					<c:if test="${empty sType || 'store' != sType}">
						<a href="javascript:;" class="xxkcur">搜商品</a>
						<a href="javascript:;">搜商铺</a>
					</c:if>
					<div class="clear"></div>
				</div>
				<input id="keywork" name="keywork" onkeydown="javascript:if(event.keyCode==13) search();" value="${keywork }" class="inputsearch" placeholder="请输入搜索文字"/>
				<input name="" type="submit" value="搜索" class="inputanniu" onclick="search();" />
			</div>
			 <c:if test="${!empty sessionScope.user}">
			 <script type="text/javascript">gwc();</script>
			</c:if>
			<div id="myCart">
				<div class="indextop_jiesuan_cishu" id="jesuan_cisuan">0</div>
				<div class="indextop_jiesuan"><a href="javascript:;">去购物车结算</a></div>
				<div class="indexgouwuche_tanchu" style="display:none;"><ul id="cartDet"></ul></div>
			</div>
		</div>
	</div>
	<!--主导航条-->
	<div class="top_nav">
		<div class="top_nav_content">
        	<div class="alltypenav"><a href="javascript:void(0);" class="linkbg">全部分类</a></div>            
            <div class="sellenter"><a href="/seller/login.do" class="bg2">商家入口</a></div>
			<div class="navmain">
                <a href="/">首页</a>
                <a href="/account/task/index.do">任务</a>
            	<!--<a href="#">活动</a>
            	<a href="#">换购</a>-->           
              <div class="clear"></div>
            </div>            
			<div class="clear"></div>
		</div>
	</div>
<div class="saomaall"><div class="saoma"><div class="saoma1"><span><a href="#"></a></span><img src="/images/saoma.png" /></div></div></div>