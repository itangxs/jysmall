<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/WEB-INF/jsp/public_uheader.jsp" flush="true" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>飞券网</title>
<script type="text/javascript" src="/js/account/order/my_logistics.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="1"/></jsp:include>
	<!--------------右侧------------------>
		<div class="memberright">
   	    <div class="memberright_title">物流信息</div>
   	  <div class="member-orderdetail">
   	  	<input type="hidden" name="orderId" id="orderId" value="${orderDetail.expressId}" />
   	  	<input type="hidden" name="express" id="express" value="${orderDetail.express}" />
   	    <div class="orderdetail1">
   	      <p>订单编号：${orderDetail.detailNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;创建时间： <fm:formatDate value="${orderDetail.orderDate}" pattern="yyyy-MM-dd HH:MM:SS"/></p>
          <p>物流公司：${orderDetail.express}</p>
   	    </div>
   	    <div class="orderdetail1">
		  <p><strong>物流动态</strong></p>
                <ul id="orderdetail">
                </ul>
   	    </div>
   	    <div class="member_myorder">
   	      <table width="100%" border="0" cellspacing="0" cellpadding="0">
   	        <tr>
   	          <td class="td370"><a href="/getProdDetail.do?prodId=${orderDetail.prodId}"><img src="${orderDetail.prodImage}" /></a>
   	            <p>${orderDetail.prodName}</p>
              <p>￥${orderDetail.unitPric}</p></td>
            </tr>
          </table>
        </div>
   	    <div class="orderdetail2">
   	      <h2>
   	        <p>收货信息： ${userAddr}</p>
   	        <p>发货信息：${sellerAddr}</p>
          </h2>
        </div>
      </div>
    </div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>