<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="/js/pagingUtil.js"></script>

</head>

<script language="javascript" type="text/javascript">

function closeDiv(){
document.getElementById('setDelivery').style.display='none';
}
</script>
<body>
	<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
	<div class="membercontent">
		<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="16" /></jsp:include>
		<!--------------右侧------------------>
		<div class="memberright">
			<form id="query" name="query" method="post" class="zcform1">
				<input type="hidden" name="pageNum" id="page">
				<p class="clearfix">
					<label class="one" for="con-email2">订单编号：</label>
					<input name="detailNo" type="text" class="con-input" value="${detailNo}"><em class="space"></em>
					<label class="one" for="con-email2">商品名称：</label>
					<input name="prodName" class="con-input" value="${prodName}"><em class="space"></em>
					<label for="select">交易状态：</label>
					<span>未完成订单(<a href="javascript:;" class="fontred">${count}</a>)件</span>
				</p>
				<p class="clearfix">
					<label class="one" for="con-email2">下单时间：</label>
					<input id="begin" name="begin" class="easyui-datebox con-time" value="${begin}">
					<label class="one" for="con-email2">-</label>
					<input id="ending" name="ending" class="easyui-datebox con-time" value="${ending}"><em class="space"></em>
					<label class="one" for="con-email2">会员名称：</label>
					<input name="userName" class="con-input" value="${userName}"><em class="space"></em>
					<input type="submit" value="查询" class="submit6">
				</p>
			</form>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td508">商品名称</th>
						<th class="td80">单价</th>
						<th class="td130">买家</th>
						<th class="td130">数量</th>
						<th class="td130">金额</th>
						<th class="td130">下单时间</th>
						<th class="td130">交易状态</th>
						<th class="td130">操作</th>
					</tr>
					<c:forEach var="info" items="${page}">
					<tr>
						<td class="td508">订单编号：${info.detailNo}</td>
						<td class="td130" colspan="8"></td>
					</tr>
					<tr>
						<td class="td508">
							<a href="/getProdDetail.do?prodId=${info.prodId}" target="_blank"><img src="/${fn:substring(info.prodImage,0,fn:indexOf(info.prodImage,'|'))}"   /></a>
							<p>${info.prodName}</p>
							<p>有效期：<fm:formatDate value="${info.prodEndDate}" pattern="yyyy-MM-dd"/></p>
						</td>
						<td class="td80">￥<fm:formatNumber value="${info.unitPric}" type="currency" pattern="#,#0.00#"/></td>
						<td class="td130">${info.userName}</td>
						<td class="td130">${info.quantity}</td>
						<td class="td130">￥<fm:formatNumber value="${info.moneny}" type="currency" pattern="#,#0.00#"/></td>
						<td class="td130"><fm:formatDate value="${info.orderDate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
						<td class="td130">
						<c:choose>
	            			<c:when test="${info.prodType==2}">
	            				<c:if test="${empty info.expressId}">
	            					<a href="javascript:;" class="fontred">待发货</a>
	            				</c:if>
	            				<c:if test="${not empty info.expressId}">
	            					<a href="javascript:;" class="fontred">已发货</a>
	            				</c:if>
	            			</c:when>
	            			<c:when test="${info.prodType==1}"><a href="javascript:;" class="fontred">已付款</a></c:when>
	            		</c:choose>
						</td>
						<td class="td130">
						<c:if test="${info.prodType==2}">
							<c:if test="${empty info.expressId}">
								<input type="button" class="submit6" value="发货" onclick="setDelivery(${info.detailId})">
							</c:if>
						</c:if>
						<c:if test="${info.prodType==1}">
							<input type="button" class="submit6" value="查看" onclick="window.open('/getProdDetail.do?prodId=${info.prodId}')" >
						</c:if>
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
			<!-----------------------发货弹出框-->
			<div id="setDelivery" class="mydiv" style="display:none;">
				<h5>
					<a href="javascript:closeDiv()"><span>关闭</span></a>
				</h5>
				
					<form id="setDeliveryFrom">
						<p>
							<span>选择快递：</span>
							<select name="express" id="express" style="widht:300px; height:28px;">
				        		<option value="顺丰快递" selected="selected">顺丰快递</option>
				                <option value="申通快递">申通快递</option>
				                <option value="中通快递">中通快递</option>
				                <option value="圆通快递">圆通快递</option>
				                <option value="汇通快递 ">汇通快递 </option>
				                <option value="韵达快递">韵达快递</option>
				                <option value="天天快递">天天快递</option>
				                <option value="广东ems快递">广东ems快递</option>
				            </select>
						</p>
						<p>
							<span>快递单号：</span><input class="input" id="expressId" name="expressId" type="text" />
							<input id="orderId" type="hidden" name="orderId"/>
						</p>
						<p>
							<span></span><input class="submit6" name="saveValue" type="submit" style="width:80px; margin-left:60px; margin-top:10px;"  value="发货" />
						</p>
					</form>
			</div>
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
		<div class="clear"></div>
	</div>
	<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" />
</body>
</html>