<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网-店铺信息</title>
<link type="text/css" rel="stylesheet" href="/umeditor//themes/default/css/umeditor.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/uploadify.css" />
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.config.js"></script>
<script type="text/javascript" src="/umeditor/umeditor.js"></script>
<script type="text/javascript" src="/umeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/js/storeInfo.js"></script>
<style type="text/css">
.uploadify {margin-left:100px;}
.uploadify-queue {margin-left:100px;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="2" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div id="member_myorder">
				<ul id="menuShow" class="menu0" style="margin-left:145px;">
					<li onclick="setTab(0)" class="hover">店铺设置</li>
					<li onclick="setTab(1)">地理位置</li>
					<li onclick="setTab(2)">店铺图片</li>
				</ul>
				<div id="mainShow" class="main">
					<div class="member_myorder">
						<form class="zcform" style="margin-left:150px;text-align:left;">
							<p class="clearfix">
								<label class="one" for="con-email">商户名称：</label>
								<input name="name" class="con-email" value="${store.name}">
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">联系人：</label>
								<input name="contactName" class="con-email" value="${store.contactName}">
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">联系电话：</label>
								<input name="contactTel" class="con-email" value="${store.contactTel}">
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">手机号码：</label>
								<input name="contactPhone" class="con-email" value="${store.contactPhone}">
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">商户地址：</label>
								<input id="prov" name="province" value="${store.province}" type="text" style="width:130px;height:32px;">
								<input id="city" name="city" value="${store.city}" type="text" style="width:130px;height:32px;">
								<input id="area" name="area" value="${store.area}" type="text" style="width:130px;height:32px;">
							</p>
							<p class="clearfix">
								<label class="one" for="contactPhone">详细地址：</label>
								<input name="address" class="con-email" value="${store.address}">
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">店铺属性：</label>
								<label style="color: #000;">
									<input name="promise" type="checkbox" value="1" style="margin-top:10px;"<c:if test="${fn:contains(store.promise,'1')}">checked</c:if>>
									<span style="padding-left:2px;padding-right:10px;">过期退</span>
								</label>
								<label style="color: #000;">
									<input name="promise" type="checkbox" value="2" style="margin-top:10px;"<c:if test="${fn:contains(store.promise,'2')}">checked</c:if>>
									<span style="padding-left:2px; padding-right:10px;">优惠券</span>
								</label>
								<label style="color: #000;">
									<input name="promise" type="checkbox" value="3" style="margin-top:10px;"<c:if test="${fn:contains(store.promise,'3')}">checked</c:if>>
									<span style="padding-left:2px; padding-right:10px;">随时退</span>
								</label>
								<label style="color: #000;">
									<input name="promise" type="checkbox" value="4" style="margin-top:10px;"<c:if test="${fn:contains(store.promise,'4')}">checked</c:if>>
									<span style="padding-left:2px;padding-right:10px;">免预约</span>
								</label>
							</p>
							<p class="clearfix">
								<label class="one" for="con-email">店铺LOGO：</label>
								<img id="logoImage" width="150" height="120" src="${store.logo}" alt="" />
								<input id="logo" type="hidden" name="logo" value="${store.logo}" />
								<br>
								<br>
							</p>
							 <p class="clearfix">
								<label class="one" for="con-email"></label>
								 <input id="logoButn" type="file" multiple="true">
							</p> 
							<p class="clearfix">
								<label class="one" for="con-email">商家介绍：</label>
							</p>
							<p class="clearfix">
								<script id="detail" type="text/plain" style="width:1000px;height:240px;">${store.storeDetail}</script>
							</p>
							<p class="clearfix">
								<input class="submit22" type="submit" value="提交修改" style="margin-left:200px;" />
							</p>
						</form>
					</div>
					<div class="member_myorder" style="display:none;">
						<form class="zcform" style="margin-left:150px;text-align:left;">
						<input name="name" class="con-email" type="hidden" value="${store.name}">
							<p class="clearfix">
								<label style="padding-left: 25px; color: #000;">精度：</label>
								<input id="longitude" name="longitude" class="con-email" type="text" value="${store.longitude}" />
								<span style="margin-left: 10px;"><a href="http://api.map.baidu.com/lbsapi/getpoint/" target="_blank">选取经纬度</a></span>
							</p>
							<p class="clearfix">
								<label style="padding-left: 25px; color: #000;">纬度：</label>
								<input id="latitude" name="latitude" class="con-email" type="text" value="${store.latitude}" />
								<span style="margin-left: 10px;"><a href="http://api.map.baidu.com/lbsapi/getpoint/" target="_blank">选取经纬度</a></span>
							</p>
							提示:选取完经纬度后复制到当前位置即可,如果偏差请自行修改。
							<p class="clearfix">
								<input class="submit22" style="margin-left: 100px;" type="submit" value="提交" />
							</p>
						</form>
					</div>
					<div class="member_myorder" style="display:none;">
						<form class="zcform" style="margin-left: 150px; text-align: left;">
						<input name="name" class="con-email" type="hidden" value="${store.name}">
							<c:forTokens varStatus="i" var="img" items="${store.images}" delims=",">
								<p class="clearfix">
									<label class="one" for="con-email">商家图片${i.index+1}：</label>
									<img id="storeImage${i.index+1}" width="320" height="256" src="${img}" />
									<input type="hidden" id="images${i.index+1}" name="images" value="${img}" />
								</p>
								 <p class="clearfix">
									<input id="storeButn${i.index+1}" type="file" value="上传" />
								</p> 
							</c:forTokens>
							<c:forEach var="i" begin="${fn:length(fn:split(store.images, ','))+1}" end="4">
								<p class="clearfix">
									<label class="one" for="con-email">商家图片${i}：</label>
									<img id="storeImage${i}" width="320" height="256" src="/images/jys_store.jpg" />
									<input type="hidden" id="images${i}" name="images" value="" />
								</p>
								 <p class="clearfix">
									<input id="storeButn${i}" type="file" value="上传" />
								</p>
								
							</c:forEach>
							<p class="clearfix">
								<label class="one" for="con-email" style="text-align: right;">标签：</label>
								<input id="labels" name="labels" value="${store.labels}" class="con-email">
							</p>
							<p class="clearfix">
								<input class="submit22" style="margin-left: 120px;" type="submit" value="保存" />
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>