<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网平台管理中心</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/system/order/list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp"><jsp:param name="val" value="3" /></jsp:include>
	<div class="membercontent">
		<!----------------左侧-------------------->
		<div class="memberleft">
			<!--左侧菜单-->
			<div class="membermenu">
				<ul>
					<li>
						<h1>订单管理</h1>
						<p>
							<a href="/managermall/system/order/list.do" class="menucurrent2"><span>·</span>订单列表</a>
						</p>
					</li>
					<li>
						<h1>微信店铺订单管理</h1>
						<p>
							<a href="/managermall/systemmall/fqorder/list.do"><span>·</span>订单列表</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 5px; margin-bottom: 25px;">订单详情</h3>
			<div style="width:100%;text-align:right;"><a href="/managermall/system/order/list.do">返回列表</a></div>
			<div class="member_myorder">
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th class="td202" colspan="3">买家信息</th>
					</tr>
					<tr>
						<td class="td160">会员帐号</td>
						<td class="td160">真实姓名</td>
						<td class="td160">联系电话</td>
					</tr>
					<tr>
						<td class="td160">${user.username}</td>
						<td class="td160">${user.realname}</td>
						<td class="td160">${user.phoneNum}</td>
					</tr>
				</table>
				<br>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th class="td202" colspan="4">商品属性</th>
					</tr>
					<tr>
						<td class="td160">商品名称</td>
						<td class="td160">商品单价</td>
						<td class="td160">购买数量</td>
						<td class="td160">购买价格</td>
					</tr>
					<tr>
						<td class="td160">${product.name}</td>
						<td class="td160"><fm:formatNumber value="${product.unitPrice}" type="currency" pattern="0.0"/></td>
						<td class="td160">${detail.quantity}</td>
						<td class="td160"><fm:formatNumber value="${detail.moneny}" type="currency" pattern="0.0"/></td>
					</tr>
				</table>
				<br>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th class="td202" colspan="8">订单信息</th>
						</tr>
						<tr>
							<td class="td161">订单号：</td>
							<td class="td160">${detail.detailNo}</td>
							<td class="td161">店铺名称：</td>
							<td class="td160">${store.name}</td>
							<td class="td161">下单时间：</td>
							<td class="td160"><fm:formatDate value="${detail.orderDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td class="td161">支付时间：</td>
							<td class="td160"></td>
						</tr>
						<tr>
							<td class="td161">支付方式：</td>
							<td class="td160"></td>
							<td class="td161">有效期至：</td>
							<td class="td160"><fm:formatDate value="${product.endDate}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td class="td161">订单金额：</td>
							<td class="td160"><fm:formatNumber value="${detail.moneny}" type="currency" pattern="0.0"/></td>
							<td class="td161">订单状态：</td>
							<td class="td160">
								<c:choose>
								<c:when test="${detail.status==1}">未付款</c:when>
								<c:when test="${detail.status==2}">已付款</c:when>
								<c:when test="${detail.status==3}">已消费(已收货)</c:when>
								<c:when test="${detail.status==4}">已评论</c:when>
								<c:when test="${detail.status==5}">申请退款</c:when>
								<c:when test="${detail.status==6}">退款成功</c:when>
								<c:when test="${detail.status==7}">申请售后</c:when>
								<c:otherwise>归档</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<br>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<th class="td202" colspan="4">消费码明细</th>
					</tr>
					<tr>
						<td class="td160">消费码</td>
						<td class="td160">使用状态</td>
						<td class="td160">退款状态</td>
						<td class="td160">验证时间</td>
					</tr>
					<c:forEach var="it" items="${detail.volumes}">
					<tr>
						<td class="td160">${it.volumeCode}</td>
						<td class="td160"><c:choose>
							<c:when test="${it.status==1}">未使用</c:when>
							<c:when test="${it.status==2}">已使用</c:when>
							<c:when test="${it.status==3}">已退款</c:when>
							<c:otherwise></c:otherwise>
						</c:choose></td>
						<td class="td160">
							<c:if test="${it.status==3}">
							<c:if test="${detail.status==5}">申请退款</c:if>
							<c:if test="${detail.status==6}">退款成功</c:if>
							</c:if>
						</td>
						<td class="td160"><fm:formatDate value="${it.useDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>