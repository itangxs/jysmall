<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","No-cache");
	response.setDateHeader("Expires", -1);
	response.setHeader("Cache-Control","No-store");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 禁止浏览器从缓存中访问页面内容 -->
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/public.css" />
<link rel="stylesheet" type="text/css" href="/css/cart.css" />
</head>
<body>
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
							<a href="/managermall/account/logoutUser.do">退出</a>
						</i>
					</li>
					<li><a href="/managermall/account/order/myOrderForm.do">我的订单</a><span class="line"></span></li>
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
							<a id="switchCity2" href="javascript:;" class="menuyes">微信公众号<span class="arrow-down-logo"></span></a>
						</h4>
						<div id="citys2" class="city-list2" style="text-align: center;display:none;z-index: 9999">
							<p style="text-align: center;">飞券网：jysmall</p>
							<P style="text-align: center;">
								<img src="/images/weixin.jpg" />
							</P >
							<p style="text-align: center;">扫一扫，关注飞券网公众号</p>
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
				</ul>
			</h2>
		</div>
	</div>
	<div class="topcart">
    	<!--<span><em>随时退</em><em>支持代金券</em><em>过期退</em></span>-->
		<a href="/"><img src="/images/logo.png"></a>
	</div>
	<div class="cart">
		<h5>
			<img src="/images/pay_liucheng1.jpg" />
		</h5>
		<div class="title">
			<h1>
				我的购物车 <span><i>${cartProd.size()}</i>/20</span>
			</h1>
		</div>
		<c:if test="${empty cartProd || cartProd.size()==0}">
			<!---购物车空的--->
			<div class="cartkong">
				<h1>您的购物车还是空的</h1>
				点击购物车可以快速添加，您可以：<br /> 去<a href="/" class="fontred">首页</a>购买喜欢的产品
			</div>
		</c:if>
		<c:if test="${!empty cartProd && cartProd.size()>0}">
		<div class="cartgouwu">
			<table id="cartTable" width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>项目</th>
					<th class="td120">兑换比例</th>
					<th class="td100">数量</th>
					<th class="td100">总元宝数</th>
					<th class="td100">总等值金额</th>
					<th class="td100">操作</th>
				</tr>
				<c:set var="addre" value="false" />
				<c:forEach var="prod" items="${cartProd}">
				<c:if test="${prod.prodType==2}"><c:set var="addre" value="true" /></c:if>
				<tr>
				<c:if test="${prod.prodId == null ||  prod.prodId == ''}">
					<td><a href="/getStoreDetail.do?storeId=${prod.storeId}">${prod.storeName}</a></td>
				</c:if>
				<c:if test="${prod.prodId != null &&  prod.prodId != ''}">
					<td><a href="/getProdDetail.do?prodId=${prod.prodId}">${prod.prodName}</a></td>
				</c:if>
					
					<td class="td120 fontred"><strong>1飞券=${prod.prodPrice}<i class="yuanbaoimg"></i></strong></td>
					<td class="td100">
						<input type="hidden" name="unitp" value="${prod.prodPrice}"/>
						<div class="goumaishuliang">
						<c:if test="${prod.prodId == null ||  prod.prodId == ''}">
							<input type="hidden" name="storeId" value="${prod.storeId}"/>
							<input type="hidden" name="unitPrice" value="1000"/>
							<input type="hidden" name="stock" value="100000"/>
						</c:if>
						<c:if test="${prod.prodId != null &&  prod.prodId != ''}">
							<input type="hidden" name="prodId" value="${prod.prodId}"/>
							<input type="hidden" name="unitPrice" value="${prod.unitPrice}"/>
							<input type="hidden" name="stock" value="${prod.prodStock}"/>
						</c:if>
							<input type="button" name="sub" class="input2" value="-">
							<input type="text" name="cartNum" class="input1" value="${prod.prodNum}" onkeyup="this.value=this.value.replace(/[^1-9]\D*$/,'');" onpaste="return false"/>
							<input type="button" name="add" class="input2" value="+"/>
							<input type="hidden" name="money" value="${prod.prodPrice*prod.prodNum}"/>
							<input type="hidden" name="cartId" value="${prod.createTime}"/>
						</div>
					</td>
					<td class="td100"><label class="total"><fm:formatNumber value="${prod.prodPrice*prod.prodNum}"/></label></td>
					<td class="td100"><label class="totalmon">¥ <strong class="fontred"><fm:formatNumber value="${prod.prodPrice*prod.prodNum/20}" type="currency" pattern="#,###.##"/></strong></label></td>
					<td class="td100"><a href="javascript:del('${prod.prodId}','${prod.storeId}','${prod.createTime}');" class="fontblue">删除</a></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="6" class="tdright">
						<strong>总元宝：</strong> <em class="fontred" id="ordTotal"></em>
					</td>
				</tr>
				<tr>
					<td colspan="6" class="tdright" style="vertical-align:top;">
						<div style="height:35px;">
							<label><input id="coupon" type="checkbox" style="vertical-align:-2px"> 
							使用优惠券抵扣金元宝</label>
						</div>
						<div id="useCoupon" style="display:none;">
							<select id="couponId" style="margin: 12px 0 23px 0;">
								<option value="-1" lang="0">请选择优惠券</option>
								<c:forEach var="coupon" items="${coupons}">
								<option value="${coupon.id}" lang="${coupon.amount}">${coupon.name}</option>
								</c:forEach>
							</select>
						</div>
					</td>
				</tr>
			
			</table>
		</div>
		<c:if test="${addre=='true'}">
		<div class="goumaitishi">
			<h1>收货地址<span><a href="/managermall/account/setupAddress.do">管理</a></span></h1>
			<div class="tishinr"><ul id="delivery">
			<c:forEach var="i" items="${addr}">
				<li <c:if test="${i.isdefault==1}">class="licurrent"</c:if>>
					<input type="radio" name="address" value="${i.id}" <c:if test="${i.isdefault==1}">checked</c:if>>
					${i.provinceName} ${i.cityName} ${i.areaName} ${i.address} ${i.recipient} ${i.phone}
				</li>
			</c:forEach>
			</ul></div>
		</div>
		</c:if>
		<!-- <div class="goumaitishi">
			<h1>大额单购买提示</h1>
			<div class="tishinr">
				<p>
					* 如果本单总价超过500元，已超出工行口令卡、招行大众版等的单次支付限额。查看<a href="#" class="fontred">更多银行支付限额详情</a>
				</p>
				<p>
					* 您也可以先<a href="/managermall/account/myRecharge.do" class="fontred">为账户充值</a>，方便您的购买。
				</p>
			</div>
		</div> -->
		<c:if test="${empty sessionScope.user.phoneNum}">
		<div class="goumaitishi">
			<h1>您的手机</h1>
			<div class="tishinr" id="tishinr">
				<p>首次购买请先验证手机号（购买成功后，虚拟商品的消费券密码将发送到您的手机，凭消费券密码去商家消费）。</p>
				<p><em>手机号</em><input id="phone" name="phone" class="input1" type="text" /></p>
				<p><em></em><input id="getCaptcha" type="button" class="button_hui" value="获取手机动态码 " /></p>
				<p><em>动态码</em><input id="captcha" name="captcha" class="input1" type="text" /></p>
			</div>
		</div>
		</c:if>
		<div class="carttijiao">
			<form id="cartForm" action="" method="post">
				<input type="hidden" name="phone" />
				<input type="hidden" name="captcha" />
				<input type="hidden" name="address" />
				<input type="hidden" name="address" />
				<input type="hidden" name="address" />
				<input type="hidden" name="token" value="${token}" />
				<input id="phoneNum" type="hidden" value="${sessionScope.user.phoneNum}" />
				<input type="submit" value=" 提交订单 " class="button_red" name="">
			</form>
		</div>
	</c:if>
	</div>
	<jsp:include page="uFooter.jsp" flush="true" />
	<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/account/cartConfirm.js"></script>
</body>
</html>