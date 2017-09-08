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
<script type="text/javascript" src="/js/pagingUtil.js"></script>
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
							<a href="/managermall/system/order/list.do"><span>·</span>订单列表</a>
						</p>
					</li>
					<li>
						<h1>微信店铺订单管理</h1>
						<p>
							<a href="/managermall/systemmall/fqorder/list.do" class="menucurrent2"><span>·</span>订单列表</a>
						</p>
					</li>
				</ul>
			</div>
		</div>
		<!--------------右侧------------------>
		<div class="memberright">
			<h3 style="padding-left: 10px; margin-bottom: 25px;">订单列表</h3>
			<form id="queryOrder" action="list.do" method="post" class="zcform1">
				<input type="hidden" id="page" name="pageNum" value="${page.getPageNum()}">
				<input type="hidden" id="pageSize" name="pageSize" value="${page.getPageSize()}">
				<p class="clearfix" style="margin-bottom:10px;">
					<label class="one" for="con-email4">订单编号：</label>
					<input name="orderNo" class="con-email4" value="${orderNo}">
					<label class="one" for="con-email4"> 店铺名称：</label>
					<input name="storeName" class="con-email4"  value="${storeName}">
					</p>
				<p class="clearfix">
					<label class="one" for="con-email4">下单时间：</label>
					<input name="createTimeBegin" class="easyui-datebox" editable="false" value="${createTimeBegin}"> -
					<input name="createTimeEnding" class="easyui-datebox" editable="false" value="${createTimeEnding}">
					<label class="one" for="con-email4">交易状态：</label>
					<select name="status">
						<option value=""<c:if test="${empty status}">selected="selected"</c:if> >全部</option>
						<option value="0"<c:if test="${status==0}">selected="selected"</c:if>>未付款</option>
						<option value="2"<c:if test="${status==2}">selected="selected"</c:if>>全额付款</option>
					</select>
					<label class="one" for="con-email4">支付类型：</label>
					<input type="submit" value="查询" class="submit8" style="margin-left:20px">
				</p>
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td160">订单编号</th>
						<th class="td160">店铺名称</th>
						<th class="td160">总价</th>
						<th class="td160">折后价</th>
						<th class="td160">已支付费用</th>
						<th class="td160">状态</th>
						<th class="td160">创建时间</th>
						<th class="td160">操作</th>
					</tr>
					<c:forEach var="it" items="${page}">
					<tr>
						<td class="td160">${it.orderNo}</td>
						<td class="td160">${it.storeName}</td>
						<td class="td160"><fm:formatNumber value="${it.totalAmount}" type="currency" pattern="0.00"/></td>
						<td class="td160"><fm:formatNumber value="${it.rebateAmount}" type="currency" pattern="0.00"/></td>
						<td class="td160"><fm:formatNumber value="${it.payAmount}" type="currency" pattern="0.00"/></td>
						<td class="td160"><c:choose>
							<c:when test="${it.status==0}">未付款</c:when>
							<c:when test="${it.status==1}">预定付款</c:when>
							<c:when test="${it.status==2}">全额付款</c:when>
						</c:choose></td>
						<td class="td160"><fm:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						<td class="td160"><a href="getOrderDetail.do?id=${it.id}">查看</a></td>
					</tr>					
					</c:forEach>
				</table>
			</div>
			<!--上一页下一页-->
			<div class="page">
			  	<c:if test="${page.getPageNum()>1}"> 
            	<a href="javascript:previousPage(${page.getPageNum()-1},'queryOrder','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${page.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'queryOrder','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${page.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'queryOrder','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${page.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${page.getPages()-3}">
            			<c:if test="${i==page.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${page.getPageNum()<page.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${page.getPages()-2}" end="${page.getPages()}">
            			<c:choose><c:when test="${i==page.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'queryOrder','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${page.getPages()>page.getPageNum()}"><a href="javascript:previousPage(${page.getPageNum()+1},'queryOrder','page')" class="next">下一页</a></c:if>
                <span>共${page.getPages()}页</span><span>到第</span>
                <input class="input1" id="jumPage" name="page" type="text" value="${page.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'queryOrder','page')}"/><span>页</span>
        </div>
    </div>
		<div class="clear"></div>
	</div>
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
</body>
</html>