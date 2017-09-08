<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<jsp:include page="/WEB-INF/jsp/public_header.jsp"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网平台管理中心</title>
<script type="text/javascript" src="/js/pagingUtil.js"></script>


<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/system/statistics/sales_detail_list.js"></script>

</head>

<body>
<!-------------------top---------------------------------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_header.jsp" flush="true" ><jsp:param name="val" value="8" /></jsp:include>
<!--------------------------我的账户-------------------------------------------------------->
<div class="membercontent">
	<!----------------左侧----------------------->
	<jsp:include page="/WEB-INF/jsp/system/statistics/uLeft.jsp" flush="true" ><jsp:param name="position" value="2" /></jsp:include>
    <!--------------右侧------------------>
	<div class="memberright">
        <h3 style="padding-left:0px; margin-bottom:25px;">订单详情</h3>
        <div class="member_myorder">
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>  
                    <th colspan="3" class="tdleft">买家信息</th>
                </tr>
                <tr>  
                    <td align="center">会员账号</td>
                    <td class="td300">真实姓名</td>
                    <td class="td300">联系电话</td>
                </tr>
                <tr>
                    <td align="center">${order.openId }</td>
                    <td class="td300"></td>
                    <td class="td300"></td> 
                </tr>
              </table><br>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>  
                    <th colspan="4" class="tdleft">折扣属性</th>
                </tr>
                <tr>  
                    <td class="td120center">折扣名称描述</td>
                    <td colspan="3" class="tdleft">${rebate.rebateName }&nbsp;&nbsp;&nbsp;${rebate.rebate}折</td>
                </tr>
                <tr>
                    <td class="td120center">开始日期</td>
                    <td class="td350left"><fmt:formatDate value="${rebate.beginDate }" pattern="yyyy-MM-dd"/></td>
                    <td class="td120center">结束日期</td> 
                    <td class="tdleft"><fmt:formatDate value="${rebate.endDate }" pattern="yyyy-MM-dd"/></td> 
                </tr>
              </table><br>
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
               <tr>
                    <th colspan="4" class="tdleft">详细信息</th>
                </tr>
                <tr>
                    <td class="td120center">订单号</td>
                    <td class="td350left">${order.orderNo }</td>
                    <td class="td120center">折扣名称</td> 
                    <td class="tdleft">${rebate.rebateName }</td>                     
                </tr>
                <tr>
                    <td class="td120center">支付渠道</td>
                    <td class="td350left">
          				<c:if test="${order.payType == 0 }">微信支付</c:if>
          				<c:if test="${order.payType != 0 }">其他支付</c:if>          
                    </td>
                    <td class="td120center">支付时间</td> 
                    <td class="tdleft"><fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td class="td120center">微信支付</td>
                    <td class="td350left">${order.needPay }</td>
                    <td class="td120center">订单总额</td> 
                    <td class="tdleft">${order.realPay }</td> 
                </tr>
                <tr>
                    <td class="td120center">金元宝支付</td>
                    <td class="td350left">${order.integral }</td>
                    <td class="td120center">抵用券</td> 
                    <td class="tdleft">${order.couponId }</td> 
                </tr>
              </table><br>
        </div>
         
	</div>
	<div class="clear"></div>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/system/system_footer.jsp" />
<!--底部-e-->
</body>
</html>
