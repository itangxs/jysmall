<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="/js/pagingUtil.js"></script>
<title>飞券网商家后台中心 -客户退款</title>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="17" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<form id="query" name="query" method="post" action="/managermall/seller/order/refundOrder.do" class="zcform1">
				<p class="clearfix">
					<input name="pageNum" id="page" type="hidden">
					<label class="one" for="con-email2">订单编号：</label>
					<input name="detailNo" type="text" class="con-input"><em class="space"></em>
					<label for="select">退款状态：</label>
					<select name="orderStatus" class="orderSel">
						<option value="5" <c:if test="${orderStatus =='5' }">selected="selected"</c:if>>申请退款</option>
                		<option value="6" <c:if test="${orderStatus =='6' }">selected="selected"</c:if>>已处理退款</option>
					</select><em class="space"></em>
					<input type="submit" value="搜索" class="submit6" />
				</p>
			</form>
			<div id="member_myorder">
				<div class="member_myorder">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th class="td130">订单编号</th>
							<th class="td130">买家登录名</th>
							<th class="td130">退款金额</th>
							<th class="td130">退款状态</th>
							<th class="td80">操作</th>
						</tr>
						<c:forEach var="info" items="${page}">
						<tr>
							<td class="td130">${info.orderNo}</td>
							<td class="td130">${fn:substring(info.name,0,3)}***</td>
							<td class="td130">￥<fm:formatNumber value="${info.price}" type="currency" pattern="#,#0.00#"/></td>
							<td class="td130">
							<c:choose>
								<c:when test="${info.status==0}">已删除</c:when>
		            			<c:when test="${info.status==3}">已提交退款</c:when>
		            			<c:when test="${info.status==2}">商家已跟进</c:when>
		            			<c:when test="${info.status==3}">退款成功</c:when>
		            			<c:otherwise>平台接管</c:otherwise>
							</c:choose>
							</td>
							<td class="td80"><a href="refundDetail.do?id=${info.id}" class="fontred">查看详细</a></td>
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
            	<a href="javascript:previousPage(${page.getPageNum()-1},'query','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'query','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'query','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'query','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'query','page')" class="next">下一页</a></c:if>
                <span>共${page.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'query','page')}"/><span>页</span>
        </div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>