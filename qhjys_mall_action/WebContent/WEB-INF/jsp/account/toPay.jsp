<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
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
	<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
	<div class="member_pay">
		<h3>
			<img src="/images/pay_liucheng2.jpg" />
		</h3>
		<div class="cartgouwu">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>项目</th>
					<th class="td100">单价</th>
					<th class="td100">数量</th>
					<th class="td100">总价</th>
				</tr>
				<c:forEach var="detail" items="${order.list}">
					<tr>
						<c:if test="${detail.prodId == null ||  detail.prodId == ''}">
					<td><a href="/getStoreDetail.do?storeId=${detail.stoId}">${detail.stoName}</a></td>
				</c:if>
				<c:if test="${detail.prodId != null &&  detail.prodId != ''}">
					<td><a href="/getProdDetail.do?prodId=${detail.prodId}">${detail.prodName}</a></td>
				</c:if>
						<td class="td100">${detail.payPric}金元宝</td>
						<td class="td100">${detail.quantity}</td>
						<td class="td100"><fm:formatNumber value="${detail.moneny}"/>金元宝</td>
					</tr>
				</c:forEach>
				<c:if test="${!empty coupon}">
				<tr>
					<td>${coupon.name}</td>
					<td class="td100"></td>
					<td class="td100">1</td>
					<td class="td100">-${coupon.amount}</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="4" class="tdright">
						<strong>应付总额：</strong>
						<c:if test="${order.integral<=coupon.amount}">
						<em class="fontred" id="payMoney">0</em>
						</c:if>
						<c:if test="${order.integral>coupon.amount}">
						<em class="fontred" id="payMoney">${order.integral-coupon.amount}</em>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
		<br /> 温馨提示：虚拟商品预订成功后，飞券串码将发送到手机：${sessionScope.user.phoneNum}，凭飞券串码去商家消费。
		<form id="toPayForm" action="/managermall/account/order/payOrder.do" method="post">
			<!-- <div class="pay-type">
				支付密码
				<div class="paymentpassword">
                	<span class="close"><a href="#">×</a></span>
                  	<p>请输入支付密码</p>
                    <p><input id="paymentpassword" name="password" class="inputkk" type="text" /></p>
                    <input class="button_red" type="submit" name="button" id="" value="提交" />
                    <input class="button_hui" type="reset" name="button" id="" value="取消" />
                </div>
				<h1>选择支付方式</h1>
				<input name="payType" type="radio" value="1" />
				<img src="/images/lianlianzhifu.png" />
				<input name="payType" type="radio" value="0" checked="checked" /><b>账户余额支付</b>
			</div> -->
			<div class="pay-type" id="payNo" style="display: none;">
				<b>银行卡号</b> <input id="cardNo" name="cardNo" type="text" class="input1" />
			</div>
			<h2>
				<input type="hidden" id="token" name="token" value="${payToken}" />
				<input type="hidden" id="orderId" name="orderId" value="${order.orderId}" />
				<input class="button_red anniu" type="submit" name="button" id="button" value="去兑换" />
			</h2>
		</form>
	</div>
	<jsp:include page="uFooter.jsp" flush="true" />
	<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="/js/account/toPay.js"></script>
</body>
</html>