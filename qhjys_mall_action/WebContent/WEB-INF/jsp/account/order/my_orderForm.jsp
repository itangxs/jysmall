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
<script type="text/javascript" src="/js/account/order/my_orderForm.js"></script>
<script src="/js/pagingUtil.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="1"/></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
    	<form id="from" name="form1" method="post" action="/managermall/account/order/myOrderForm.do">
    	<input name="page" id="page" type="hidden">
            <label for="select"></label>
            <select name="status" id="select" onchange="seletct(this)">
        		<option value="0"  <c:if test="${status==0 or empty status}">selected="selected"</c:if>>全部状态</option>
                <option value="1"  <c:if test="${status==1 }">selected="selected"</c:if>>未付款</option>
                <option value="2"  <c:if test="${status==2 }">selected="selected"</c:if>>未消费</option>
                <option value="3"  <c:if test="${status==3 }">selected="selected"</c:if>>已使用</option>
                <option value="9"  <c:if test="${status==9 }">selected="selected"</c:if>>已过期</option>
            </select>
        </form>
		<div class="member_myorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th class="td298">商品信息</th>
					<th class="td80">数量</th>
					<th class="td80">兑换金额</th>
					<th class="td80">兑换总额</th>
					<th class="td80">支付元宝</th>
					<th class="td130">飞券串码</th>
					<th class="td130">订单状态</th>
				</tr>
			</table>
			<c:forEach var="node" items="${pageInfo}">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th class="td298" style="text-align: left;"><em>&nbsp;&nbsp;订单编号：${node.orderNo}</em></th>
					<th class="td80"><em></em></th>
					<th class="td80"><em></em></th>
					<th class="td80"><em></em></th>
					<th class="td80"><em></em></th>
					<th class="td130"><em><a href="/managermall/account/order/myOrderFormDatail.do?id=${node.orderId}" class="fontred">订单详情</a></em></th>
					<th class="td130">
					<c:choose>
            			<c:when test="${node.status==1}"><a href="/managermall/account/order/toPay.do?orderId=${node.orderId}" class="fukuan">付款</a><a onclick="delet(${node.orderId})" style="cursor:pointer;" class="delete"></a></c:when>
						<c:when test="${node.status==3||node.status==4}"><a onclick="delet(${node.orderId})" style="cursor: pointer;"  class="delete"></a></c:when> 
            		</c:choose>
					</th>
				</tr>
				<c:forEach var="list" items="${node.list}">
				<tr>
				<c:if test="${list.prodId != null }">
					<td class="td298">
						<a href="/getProdDetail.do?prodId=${list.prodId}"><img src="${list.prodImage}" /></a>
						<p class="textheight25">${list.prodName}</p>
						<p><span class="zhibg">限</span><fm:formatDate value="${list.prodEndDate}" pattern="yyyy-MM-dd"/></p>
					</td>
					</c:if>
				<c:if test="${list.prodId == null }">
					<td class="td298">
						<a href="/getStoreDetail.do?storeId=${list.stoId}"><img src="${list.stoImg}" /></a>
						<p><br>${list.stoName}</p>
						<p></p>
					</td>
					</c:if>
					<td class="td80">${list.quantity}</td>
					<td class="td80"><fm:formatNumber type="currency" value="${list.payPric.divide(20)}" pattern="#,###.##"/></td>
					<td class="td80"><fm:formatNumber type="currency" value="${list.moneny.divide(20)}" pattern="#,###.##"/></td>
					<td class="td80"><fm:formatNumber value="${list.moneny}"/></td>
					<td class="td129">
						<p>
						
						<c:forEach var="volume" items="${list.volumes}">
							${volume.volumeCode }<br>
						</c:forEach></p>
						
					</td>
					<td class="td129">
						<c:choose>
            			<c:when test="${list.status==1}"><p>未付款</p></c:when>
            			<c:when test="${list.status==2}">
            				<p>已付款</p>
            				<p>
            					<c:if test="${empty list.volumes}">
            						<a onclick="delivery(${list.detailId})" style="cursor:pointer;"  class="fukuan">确认收货</a>
            					</c:if>
            				</p>
            			</c:when>
            			<c:when test="${list.status==3}">
            				<c:if test="${empty list.volumes}">
            					<p>已验证</p>
            				</c:if>
            				<c:if test="${not empty list.volumes}">
            					<p>已消费</p>
            				</c:if>
            			</c:when>
            			<c:when test="${list.status==4}"><p>已评论</p></c:when>
            			<c:when test="${list.status==5}"><p>申请退款</p></c:when>
            			<c:when test="${list.status==6}"><p>退款成功</p></c:when>
            			<%-- <c:otherwise>申请售后</c:otherwise> --%>
	            		</c:choose>
							<c:if test="${list.status==2}"><p>
								<a href="/managermall/account/refund/toApply.do?id=${list.detailId}" class="tuikuan">退款/退货</a></p>
							</c:if>
						<c:if test="${!empty list.volumes &&$list.prodType==2}">
							<p><a href="javascript:void(${list.express});">查看物流</a></p>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
			</c:forEach>
		</div>
   	    <c:if test="${pageInfo.getPages()==0}">
  	    	<div align="center">
 		  		<font  color="red">暂时没有数据!</font>
 		    </div>
 	    </c:if>
        <!--上一页 下一页-->
         <div class="page">
            	<c:if test="${pageInfo.getPageNum()>1}">
            	<a href="javascript:previousPage(${pageInfo.getPageNum()-1},'from','page')" class="prev">上一页</a>
            	</c:if>
            	<c:choose>
            	<c:when test="${pageInfo.getPages()<7}">
            		<c:forEach var="i" begin="1" end="${pageInfo.getPages()}">
            			<c:choose><c:when test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	<c:when test="${pageInfo.getPages()>6}">
            		<c:forEach var="i" begin="1" end="3">
            			<c:choose><c:when test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            		<c:if test="${pageInfo.getPageNum()>4}"><em>...</em></c:if>
            		<c:forEach var="i" begin="4" end="${pageInfo.getPages()-3}">
            			<c:if test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:if>
            		</c:forEach>
            		<c:if test="${pageInfo.getPageNum()<pageInfo.getPages()-3}"><em>...</em></c:if>
            		<c:forEach var="i" begin="${pageInfo.getPages()-2}" end="${pageInfo.getPages()}">
            			<c:choose><c:when test="${i==pageInfo.getPageNum()}"><em class="current">${i}</em></c:when>
            			<c:otherwise><a href="javascript:previousPage(${i},'from','page')">${i}</a></c:otherwise></c:choose>
            		</c:forEach>
            	</c:when>
            	</c:choose>
            	<c:if test="${pageInfo.getPages()>pageInfo.getPageNum()}"><a href="javascript:previousPage(${pageInfo.getPageNum()+1},'from','page')" class="next">下一页</a></c:if>
               <span>共${pageInfo.getPages()}页</span><span>到第</span>
               <input class="input1" id="jumPage" name="pageNum" type="text" value="${pageInfo.getPageNum()}" onkeydown="if(event.keyCode==13){previousPage(this.value,'from','page')}"/><span>页</span>
        </div>
	</div>
	<div class="clear"></div>
</div>
<!----------------底部-------------->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
