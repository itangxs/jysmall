<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网商家后台中心	-退款详情</title>
<script type="text/javascript" src="/js/compatible.js"></script>
<script type="text/javascript" src="/js/seller/order/refundDetail.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="17" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<div class="member_myorder">
				<form id="form1" name="form1" method="post" action="">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th  class="td130"><span style="text-align: left;">申请退款详情</span></th>
							<th><input name="id" id="id" type="hidden" value="${refund.id}" /></th>
							<th>&nbsp;</th>
						</tr>
						<tr>
							<td width="50" style="text-align:right;">退款金额：</td>
							<td class="td508">￥<fm:formatNumber value="${refund.refundPrice}" type="currency" pattern="#,#0.00#"/></td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="50" style="text-align: right;">退款方式：</td>
							<td class="td508">退回余额</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="50" style="text-align: right;">退款原因：</td>
							<td class="td508">${refund.refundReason}</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td width="50" style="text-align: right;">是否同意退款：</td>
							<c:choose>
								<c:when test="${refund.status==3}">
									<td class="td508">已处理</td>
								</c:when>
								<c:otherwise>
									<td class="td508">
										<input name="isRefund" type="radio" value="1" checked="checked" />同意
										<input name="isRefund" type="radio" value="0" />不同意
									</td>
								</c:otherwise>
							</c:choose>
							<td><input type="hidden" name="token" id="token" value="${refundToken}" /></td>
							
						</tr>
						<tr>
							<c:if test="${refund.status!=3}">
								<td width="50" style="text-align: right;">是否同意理由：</td>
								<td class="td508"><textarea id="rejectReason" name="rejectReason" cols="30" rows="3">${refund.rejectReason}</textarea></td>
							</c:if>
						</tr>
						<tr>
							<td style="text-align: right;">&nbsp;</td>
							<td class="td508">
								<c:choose>
									<c:when test="${refund.status==3}">
										<a style="width:120px;background:#ef8329;color:#fff;padding-top:5px;padding-bottom:5px;padding-left:12px;padding-right:12px;" href="/managermall/seller/order/refundOrder.do">返回</a>
									</c:when>
									<c:otherwise>
										<input type="submit" value="提交" class="submit6" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>