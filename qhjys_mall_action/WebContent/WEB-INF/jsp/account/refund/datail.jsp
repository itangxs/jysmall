<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="6"/></jsp:include>
    <!--------------右侧------------->
	<div class="memberright">
		<div class="memberright_title">退款详情</div>
		<div class="member_refunddetail">
			<div class="detail">
				<h1>
					退款金额：<span>￥<fmt:formatNumber value="${orderDetailVo.moneny}" pattern="#,##0.00#" />元
					</span>
				</h1>
				<h2>
					<p>您已经提交申请，请耐心等待商家处理！</p>
					<!--四种状态跟下面的对应-->
					<!--<p>商家处理完成，请耐心等待平台处理！</p>
					<p>支付平台处理完成，即将退款到您的账户，请耐心等待！</p>
					<p><span>￥158.00</span>元已成功退款至您的账户！</p>-->
				</h2>
				<h3>
					<em><img src="/images/jiantou1.png" /></em> <img src="/images/tuikuan1.jpg" />
					<ul>
						<li><span>提交申请</span><br />
						<fmt:formatDate value="${orderDetailVo.process}" pattern="yyyy-MM-dd  HH:mm:ss" /></li>
						<li><span>商家处理</span><br />
						<fmt:formatDate value="${orderDetailVo.retime}" pattern="yyyy-MM-dd  HH:mm:ss" /></li>
						<li><span>退款</span><br /></li>
					</ul>
				</h3>
			</div>
		</div>
		<div class="member_myorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th class="td370">订单编号${orderDetailVo.detailNo}</th>
					<th class="td80">退款金额</th>
					<th class="td80">状态</th>
					<th class="td130">申请提交时间</th>
				</tr>
				<tr>
					<td class="td370"><a href="listdetail.do"><img src="${orderDetailVo.prodImage}" /></a>
						<p>${orderDetailVo.prodName}</p>
						<p>有效期：<fmt:formatDate value="${orderDetailVo.prodEndDate}" pattern="yyyy-MM-dd  HH:mm:ss" /></p>
					</td>
					<td class="td80">￥<fmt:formatNumber value="${orderDetailVo.moneny}" pattern="#,##0.00#" /></td>
					<td class="td80">
						<c:choose>
							<c:when test="${orderDetailVo.status==5}"><p>申请退款</p></c:when>
							<c:when test="${orderDetailVo.status==6}"><p>退款成功</p></c:when>
						</c:choose>
					</td>
					<td class="td130">
						<span class="td370"><fmt:formatDate value="${orderDetailVo.process}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>