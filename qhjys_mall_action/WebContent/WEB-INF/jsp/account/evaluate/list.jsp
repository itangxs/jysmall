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
<link href="/css/public.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/account/evaluate/list.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<!-------------------top---------------------------------------------------------------------->
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="4"/></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <div class="member_myorder">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td588">项目</th>
                    <th class="td80">金额</th>
                    <th class="td80">订单状态</th>
                    <th class="td80">操作</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<c:forEach items="${pageinfo}" var="orderDetailVo">           
                <tr>
                    <td class="td588">
                    	<a href="/getProdDetail.do?prodId=${orderDetailVo.prodId}"><img src="${orderDetailVo.prodImage}" /></a>
                    	<p class="textheight25">${orderDetailVo.prodName}</p>
                    	<p><span class="zhibg">有效期限</span><fmt:formatDate value="${orderDetailVo.prodEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p></td>
                    <td class="td80">￥<fmt:formatNumber value="${orderDetailVo.payPric}"  pattern="#,#0.00#"/></td>
                    <td class="td80">
                    	<c:choose>
	            			<c:when test="${orderDetailVo.status==1}"><p>未付款</p></c:when>
	            			<c:when test="${orderDetailVo.status==2}"><p>已付款</p></c:when>
	            			<c:when test="${orderDetailVo.status==3}"><p>已消费</p></c:when>
	            			<c:when test="${orderDetailVo.status==4}"><p>已评论</p></c:when>
	            			<c:when test="${orderDetailVo.status==5}"><p>申请退款</p></c:when>
	            			<c:when test="${orderDetailVo.status==6}"><p>退款成功</p></c:when>
	            		</c:choose>
	            		
                    </td>
                    <td class="td80">
                    	 <p>
                    	 	<c:choose>
		            			<c:when test="${orderDetailVo.status==3}">
		            				<a href="/managermall/account/evaluate/toAddPage.do?id=${orderDetailVo.detailId}" class="fontred">待评价</a>
		            			</c:when>
		            			<c:when test="${orderDetailVo.status==4}"><p>已评论</p></c:when>
	            			</c:choose>
                    	</p>
                       <%--  <p><a onclick="delet(${orderDetailVo.detailId})" style="cursor: pointer;">删除</a></p> --%>
                    </td>
                </tr>
                </c:forEach>  
            </table>
        </div>
    </div>
	<div class="clear"></div>
</div>
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
