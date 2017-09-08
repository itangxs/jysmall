<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-商品订单</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">

<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/seller/order/list.js"></script>
<script type="text/javascript" src="/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/pagingUtil.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="24" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 5px; margin-bottom: 25px;">订单详情</h3>
			<div style="width:100%;text-align:right;"><a href="javascript:window.history.go(-1);">返回列表</a></div>
			<div class="member_myorder">
				
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<th class="td202" colspan="3">订单信息</th>
						</tr>
						<tr>
							<td class="td161">订单编号：</td>
							<td class="td360" colspan="2">${order.orderNo}</td>
						</tr>
						<tr>
							<td class="td161">订单状态：</td>
							<td class="td360" colspan="2"><c:choose>
							<c:when test="${order.status==0}">未付款</c:when>
							<c:when test="${order.status==1}">预定付款</c:when>
							<c:when test="${order.status==2}">全额付款</c:when>
						</c:choose></td>
						</tr>
						<c:if test="${order.storeId == 3156 }">
						<tr><td class="td161">商家：</td>
							<td class="td360" colspan="2">${order.peopleNum}</td>
						</tr>
						<tr><td class="td161">姓名：</td>
							<td class="td360" colspan="2">${order.contacts}</td>
						</tr>
						<tr><td class="td161">电话：</td>
							<td class="td360" colspan="2">${order.phoneNum}</td>
						</tr>
						<tr><td class="td161">地址：</td>
							<td class="td360" colspan="2">${order.deskNo}</td>
						</tr>
						</c:if>
						<tr>
							<td class="td161">下单时间：</td>
							<td class="td360" colspan="3"><fm:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="td120">菜品</td>
							<td class="td120">数量</td>
							<td class="td120">小计</td>
						</tr>
						
					<c:forEach items="${details }" var="detail">
						<tr>
							<td class="td120">${detail.productName }</td>
							<td class="td120">${detail.quantity}</td>
							<td class="td120"><fm:formatNumber value="${detail.price * detail.quantity}" type="currency" pattern="0.00"/></td>
							</tr>
						
					</c:forEach>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="td161">总价：</td>
							<td class="td120"></td>
							<td class="td120"><fm:formatNumber value="${order.totalAmount }" type="currency" pattern="0.00"/></td>
						</tr>
						<tr>
							<td class="td161">折扣:<td>
							<td class="td120">${order.rebate }折</td>
						</tr>
						<tr>
							<td class="td161">折扣价:<td>
							<td class="td120"><fm:formatNumber value="${order.rebateAmount }" type="currency" pattern="0.00"/></td>
						</tr>
						<tr>
							<td class="td161">已付款：</td>
							<td class="td120"></td>
							<td class="td120"><fm:formatNumber value="${order.payAmount }" type="currency" pattern="0.00"/></td>
							
						</tr>
					</tbody>
				</table>
				
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>