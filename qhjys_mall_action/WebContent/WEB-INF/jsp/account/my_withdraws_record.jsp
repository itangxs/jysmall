<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/pagingUtil.js"></script>
<title>飞券网 - 提现记录</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
    <!--------------左侧------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="8"/></jsp:include>
    <!--------------右侧------------->
    <div class="memberright">
		<form action="/managermall/account/withdrawlist.do" method="post" id="from">
			<input type="hidden" id="page" name="page">
		</form>
		<div class="memberright_title">我的余额</div>
		<div class="member_money">
			<h1>
				您当前的账号余额：
				<b class="fontred">¥<fm:formatNumber value="${sessionScope.userCashAccount.balance}" type="currency" pattern="#,###.##"/></b>
				<!-- <span>
					<a href="/managermall/account/myRecharge.do" class="button_red">充值</a>
					<a href="/managermall/account/myWithdraws.do" class="button_hui">提现</a>
				</span> -->
			</h1>
			<!--<div class="tabs">
				<a href="/managermall/account/rechargeList.do">充值记录</a>
				<a href="/managermall/account/withdrawlist.do" class="tabaction">提现记录</a>
				<div class="clear"></div>
			</div>
			提现记录-->
			<!--<table id="withdrawals" width="100%" border="0" cellspacing="1" cellpadding="0">
				<tr>
					<th>提现金额</th>
					<th>提现时间</th>
					<th>处理状态</th>
				</tr>
				<c:forEach var="page" items="${page}">
				<tr>
					<td>${page.amount}</td>
					<td><fm:formatDate value="${page.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					<td>已成功</td>
				</tr>
				</c:forEach>
			</table>
			<c:if test="${page.getPages()==0}">
				<div align="center">
					<font color="red">暂时没有数据!</font>
				</div>
			</c:if>-->
			<!--上一页下一页
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
        	</div>-->
		</div>
	</div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>