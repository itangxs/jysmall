<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="/common/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/md5.js"></script>
<script type="text/javascript" src="/js/seller/funds/exchange.js"></script>
<script src="/js/pagingUtil.js"></script>
<title>飞券网商家后台中心</title>
</head>
<script>
</script>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="19" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
		<div class="member_myorder">
			<form action="/managermall/seller/funds/toExchange.do" method="post" id="from">
    			<input type="hidden" id="page" name="page">
    		</form>
			<ul class="menu0" id="menu0">
				<li onclick="setTab(0,0)" class="hover">积分兑换列表</li>
				<li onclick="setTab(0,1)">积分兑换申请</li>
			</ul>
			<div class="main" id="main0">
				<ul class="block">
					<li>
						<div class="member_myorder">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th class="td160">时间</th>
									<th class="td160">兑换积分</th>
									<th class="td160">兑换金额</th>
								</tr>
								<c:forEach items="${page}" var="r">
									<tr>
										<th class="td160"><fmt:formatDate value="${r.createTime}" pattern="yyyy-MM-dd " /></th>
										<th class="td160">${r.integral}</th>
										<th class="td160"><fmt:formatNumber value="${r.integral/20}" pattern="#,##0.00#"/></th>
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
				<ul>
					<li>
						<div class="member_myorder">
							<form id="form1" name="form1" method="post">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="80" style="text-align: right;">用户名：</td>
										<td width="848" class="td509">${sessionScope.seller.username}</td>
									</tr>
									<tr>
										<td width="80" style="text-align: right;">当前金额：</td>
										<td width="848" class="td509">${cashAccount.balance}</td>
									</tr>
									<tr>
										<td width="80" style="text-align: right;">当前积分：</td>
										<td width="848" class="td509">${cashAccount.integral}</td>
									</tr>
									<tr>
										<td width="80" style="text-align: right;">兑换积分：</td>
										<td width="848" class="td509">
										<input id="exchangeIntegral" name="exchangeIntegral" type="text" class="con-email3">
										</td>
									</tr>
									<tr>
										<td style="text-align: right; padding-top: 10px;">
											<input type="submit" name="button" id="button" value="提交" class="submit6" />
											<input class="submit6" type="hidden" id="token" name="token" value="${exchangeToken}" />
										</td>
										<td class="td508"></td>
									</tr>
								</table>
							</form>
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
