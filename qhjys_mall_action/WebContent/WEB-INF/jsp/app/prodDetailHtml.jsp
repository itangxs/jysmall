<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<link rel="stylesheet" type="text/css" href="/css/app/public.css">
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
</head>
<body>
	<div class="global">
		<div class="prodetailtop">
			<img src="${fn:substring(info.images, 0, fn:indexOf(info.images, '|'))}">
			<div class="textbg"></div>
			<div class="textbgfont">
				<h1>${info.name}</h1>
				<p>${info.title}</p>
			</div>
		</div>
		<div class="prodetail">
			<div class="prodetailli">
				<span class="qianggou">立即抢购</span>
				<span class="tuangoujia"><fm:formatNumber value="${info.unitPrice}" type="currency" pattern="#,###.##"/>元</span>
				<span class="yuanjia"><fm:formatNumber value="${info.origPrice}" type="currency" pattern="#,###.##"/>元</span>
			</div>
			<div class="prodetailli">
				<span class="ico1"></span>${store.province} ${store.city} ${store.areaName} ${store.address}
			</div>
			<div class="prodetailli">
				<span class="ico2"></span>${store.contactTel}
			</div>
			<div class="prodetailli">
				<div class="pingjiafen3">
					<div class="starbg">
						<span style="width:${review.avgScore*20}%;"></span>
					</div>
				</div>
				<span class="fontred"><fm:formatNumber value="${review.avgScore}" type="currency" pattern="0.0"/></span>
			</div>
			<div class="prodetailli2">套餐内容</div>
			<div class="prodetailli1">${info.title}</div>
			<div class="prodetailli">
				<a class="fontred" href="getProdImgContext.do?prodId=${info.id}">查看图文详情</a>
			</div>
			<div class="prodetailli2">消费提示</div>
			<div class="prodetailli1">
				${info.buyingTips}
			</div>
			<div class="prodetailli2">本商家的其他热卖套餐</div>
			<c:forEach var="i" items="${page}"><div class="prodetailli">
				<a href="toProdDetailHtml.do?prodId=${i.prodId}"><span class="jiantou"></span></a>
				<h1>${i.prodName}</h1>
				<p>
					<span class="tuangoujia"><fm:formatNumber value="${i.price}" type="currency" pattern="#,###.##"/>元</span>
					<span class="yuanjia"><fm:formatNumber value="${i.dePrice}" type="currency" pattern="#,###.##"/>元</span>
				</p>
			</div></c:forEach>
			<div class="prodetailli2">为您推荐</div>
			<div class="protuijian">
				<ul><c:forEach var="i" items="${other}">
					<li>
						<a href="getProdImgContext.do?prodId=${info.id}"><img src="${fn:substring(i.images, 0, fn:indexOf(i.images, '|'))}"></a>
						<div class="protuijiantext">
							<h1>${i.name}</h1>
							<h2>${i.title}</h2>
							<h3>
								￥<fm:formatNumber value="${i.unitPrice}" type="currency" pattern="#,###.##"/>
								<span>￥<fm:formatNumber value="${i.origPrice}" type="currency" pattern="#,###.##"/></span>
							</h3>
						</div>
					</li>
				</c:forEach></ul>
			</div>
		</div>
	</div>
</body>
</html>