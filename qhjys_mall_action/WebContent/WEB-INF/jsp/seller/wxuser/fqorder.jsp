<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script src="/js/pagingUtil.js"></script>
<title>飞券网商家后台中心</title>
</head>
<script>
</script>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="33" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
		<div class="member_myorder">
			<form action="/managermall/seller/wxuser/fqOrder.do" method="post" id="from">
    			<input type="hidden" id="page" name="page">
    			<input type="hidden" id="openId" name="openId" value="${openId }">
    		</form>
			<ul class="menu0" id="menu0">
				<a href="/managermall/seller/wxuser/fqOrder.do?openId=${openId }"><li class="hover">预定点餐记录</li></a>
				<a href="/managermall/seller/wxuser/rebateOrder.do?openId=${openId }"><li>优惠买单记录</li></a>
			</ul>
			<div class="main" id="main0">
				<ul class="block">
					<li>
						<div class="member_myorder">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th class="td130">订单编号</th>
									<th class="td80">实际支付</th>
									<th class="td130">创建时间</th>
									<th class="td130">菜品</th>
								</tr>
								<c:forEach items="${fovs}" var="info">
								<tr>
								
									  <td class="td130">${info.orderNo}</td>
										<td class="td80">￥<fm:formatNumber value="${info.rebateAmount}" type="currency" pattern="#,#0.00#"/></td>
										<td class="td130"><fm:formatDate value="${info.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<c:forEach items="${info.orderDetails}" var="detail">
												${detail.productName }&nbsp;X&nbsp;${detail.quantity }<br>
											</c:forEach>
										</td>
									</tr>
								</c:forEach>
							</table>
				   	   	    <c:if test="${page.getPages()==0}">
						    	<div align="center">
						  			<font  color="red">暂时没有数据!</font>
						    	</div>
					    	</c:if>
							<!--上一页下一页-->
					        <div class="page">
					          	<c:if test="${page.getPageNum()>1}">
					          	<a href="javascript:previousPage(${page.getPageNum()-1},'from','page')" class="prev">上一页</a>
					          	</c:if>
					          	<c:choose>
					          	<c:when test="${page.getPages()<7}">
					          		<c:forEach var="i" begin="1" end="${page.getPages()}">
					          			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
					          			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
					          		</c:forEach>
					          	</c:when>
					          	<c:when test="${page.getPages()>6}">
					          		<c:forEach var="i" begin="1" end="3">
					          			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
					          			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
					          		</c:forEach>
					          		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
					          		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
					          			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
					          		</c:forEach>
					          		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
					          		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
					          			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
					          			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
					          		</c:forEach>
					          	</c:when>
					          	</c:choose>
					          	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
					             <span>共${page.getPages()}页</span><span>到第</span>
					             <input class="input1" id="jumPage" name="page" type="text" value="${pageNum}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
					      </div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
</body>
</html>
