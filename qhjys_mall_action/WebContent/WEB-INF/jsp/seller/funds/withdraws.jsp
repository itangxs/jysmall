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
<script type="text/javascript" src="/js/seller/funds/withdraws.js"></script>
<script src="/js/pagingUtil.js"></script>
<title>飞券网商家后台中心</title>
</head>
<script>
</script>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<!---------------top--------------------->
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="5" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
		<div class="member_myorder">
			<form action="/managermall/seller/funds/toRecharge.do" method="post" id="from">
    			<input type="hidden" id="page" name="page">
    		</form>
    		<input type="hidden" value="${scale}" id="scale">
			<ul class="menu0" id="menu0">
				<li onclick="setTab(0,0)" class="hover">提现列表</li>
				<li onclick="setTab(0,1)">提现申请</li>
			</ul>
			<div class="main" id="main0">
				<ul class="block">
					<li>
						<div class="member_myorder">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<th class="td160">时间</th>
									<th class="td160">提现金额</th>
									<th class="td160">状态</th>
								</tr>
								<c:forEach items="${page}" var="r">
									<tr>
										<th class="td160"><fmt:formatDate value="${r.createDate}" pattern="yyyy-MM-dd " /></th>
										<th class="td160"><fmt:formatNumber value="${r.money}" type="currency"/></th>
										<c:if test="${r.status ==0}">
											<th class="td160">未成功</th>
										</c:if>
										<c:if test="${r.status ==1}">
											<th class="td160">成功</th>
										</c:if>
										<c:if test="${r.status ==2}">
											<th class="td160">未处理</th>
										</c:if>
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
										<td width="80" style="text-align: right;">当前余额：</td>
										<td width="848" class="td509"><fmt:formatNumber value="${cashAccount.balance}"/></td>
									</tr>
									<tr>
										<td width="80" style="text-align: right;">收款人姓名：</td>
										<td width="848" class="td509" id="sname"><c:if test="${bank.getTotal()>0}">${bank.get(0).realName}</c:if></td>
									</tr>
									<tr>
										<td width="80" style="text-align: right;">银行卡号：</td>
						                <td width="848" class="td509">
							                <select name="bankId" id="bankId" >
							                    <c:forEach items="${bank}" var="b">
							        		  		<option lang="${b.realName }" value="${b.id}">${b.bankName} ${b.bankNumber}</option>
							        		    </c:forEach>
							            	</select>
					            	    </td>
									</tr>
									<tr>
										<td width="80" style="text-align: right;">提现金额：</td>
										<td width="848" class="td509">
										<input style="display:none">
										<input id="withdrawsMoney" name="withdrawsMoney" type="text" class="con-email3" autocomplete="off">
										</td>
									</tr>
									<tr>
										<td style="text-align: right; padding-top: 10px;">
											<input type="submit" name="button" id="button" value="提交" class="submit6" />
											<input class="submit6" type="hidden" id="token" name="token" value="${withdrawsToken}" />
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
