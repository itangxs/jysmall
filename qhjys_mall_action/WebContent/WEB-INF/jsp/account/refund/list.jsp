<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<script type="text/javascript" src="/js/account/refund/list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="6"/></jsp:include>
    <!--------------右侧------------->
    <div class="memberright">
		<div class="member_myorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th class="td370">商品信息</th>
					<th class="td80">数量</th>
					<th class="td130">总价</th>
					<th class="td80">订单状态</th>
					<th class="td130">操作</th>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<c:forEach var="list" items="${pageInfo}">
				<tr>
					<td class="td370">
						<a href="listdetail.do"><img src="${list.prodImage}" /></a>
						<p>${list.prodName}</p>
						<p>有效期：<fm:formatDate value="${list.prodEndDate}" pattern="yyyy-MM-dd" /></p>
					</td>
					<td class="td80">${list.quantity}</td>
					<td class="td130"><fm:formatNumber value="${list.moneny}" pattern="#,#0.00#" /></td>
					<td class="td80">
						<c:choose>
							<c:when test="${list.status==1}"><p>未付款</p></c:when>
							<c:when test="${list.status==2}"><p>已付款</p></c:when>
							<c:when test="${list.status==3}"><p>已消费</p></c:when>
							<c:when test="${list.status==4}"><p>已评论</p></c:when>
							<c:when test="${list.status==5}"><p>申请退款</p></c:when>
							<c:when test="${list.status==6}"><p>退款成功</p></c:when>
							<c:otherwise>申请售后</c:otherwise>
						</c:choose><br />
						<a href="/managermall/account/order/myOrderFormDatail.do?id=${list.orderId}" class="fontred">订单详情</a>
					</td>
					<td class="td130">
						<c:if test="${list.status==5}">
							<a href="/managermall/account/refund/datail.do?id=${list.detailId}">查看退款详情</a>
							<input type="reset" class="button_hui" onclick="cancelRefund(${list.detailId})" value="取消退款" />
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
   	    <c:if test="${pageInfo.getPages()==0}">
	    	<div align="center">
	  			<font  color="red">暂时没有数据!</font>
	    	</div>
    	</c:if>
		<!--上一页下一页-->
	    <div>
	        <div class="page">
	            	<c:if test="${pageInfo.getPageNum()>1}">
	            	<a href="javascript:;" class="prev">上一页</a>
	            	</c:if>
	            	<c:forEach var="i" begin="1" end="${pageInfo.getPages()}">
	            	<c:choose>
	            		<c:when test="${pageInfo.getPageNum()==i}"><em class="current">${i}</em></c:when>
	            		<c:when test="${pageInfo.getPageNum()<6 && i < 8}"><a href="javascript:;">${i}</a></c:when>
	            		<c:otherwise></c:otherwise>
	            	</c:choose>
	            	</c:forEach>
	            	<c:if test="${pageInfo.getPages()>7}"><em>...</em></c:if>
	            	<c:if test="${pageInfo.getPages()>pageInfo.getPageNum()}"><a href="javascript:;" class="next">下一页</a></c:if>
	                <span>共${pageInfo.getPages()}页</span><span>到第</span>
	                <input class="input1" id="jumPage" name="jumpPage" type="text" /><span>页</span>
	    	</div>
	    </div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>