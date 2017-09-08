<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>飞券网商家后台中心</title>
<jsp:include page="/WEB-INF/jsp/public_header.jsp"/>
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/seller/seller_header.jsp"><jsp:param name="val" value="1" /></jsp:include>
<div class="membercontent">
	<jsp:include page="/WEB-INF/jsp/seller/seller_left.jsp"><jsp:param name="position" value="" /></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">	
		<div class="member_myorder">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="td509">
						<a href="/managermall/seller/queryStoreInfo.do"><img src="${vo.imageUrl}" /></a>
						<p><h3>${vo.name}</h3></p>
						<p class="fontred">会员：Lv
						<c:if test="${empty vo.vipLv}">0</c:if>
						<c:if test="${!empty vo.vipLv}">${vo.vipLv}</c:if>
						</p>
						<p>店铺分类：${vo.typeName}</a></p>
					</td>
					<td class="td202">
						<p><h3>&nbsp;</h3></p>
						<p>账户管理&nbsp;余额：<a href="/managermall/seller/funds/account/overview.do"><span class="fontred"><fmt:formatNumber value="${sessionScope.sellerCashAccount.balance}" pattern="#,###"/>金元宝元</span></a>&nbsp;&nbsp;</span></a></p>
						<p>店铺评分：<span class="score"><em style="width:${vo.mark*20}%;"></em>&nbsp;</span> ${vo.mark}分</p>
					</td>
					<td class="td130">
                      <p>&nbsp;</p>
                      <p><a href="#" class="fontred">&nbsp;</a></p>
                    </td>
                <td class="td80"><a href="#">&nbsp;</a></td>
                </tr>
            </table>
            <br />
          <form id="form1" name="form1">
            <h3 style="padding-left:10px;">店铺提示</h3>
        </form>
    
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                <th width="206" style="text-align:left; padding-left:15px; padding-top:10px;"><h3>你需要关注的店铺情况：</h3></th>
                <th width="206">&nbsp;</th>
                <th width="161">&nbsp;</th>
                <th width="154">&nbsp;</th>
                <th width="201">&nbsp;</th>
                </tr>
                <tr>
                    <th style="text-align:left; padding-left:15px; padding-top:10px;padding-bottom:10px;">
                    				      发布的商品：（${vo.releaseGoods}）</th>
                    <th class="td170">交易中的订单：（${vo.tradeOrder}）</th>
                    <th class="td170">退款中的订单：（${vo.refundmentOrder }）</th>
                    <th class="td170">&nbsp;</th>
                    <th class="td170">&nbsp;</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr><td></td></tr>
            </table>
            <br />
             <form id="form1" name="form1">
            <h3 style="padding-left:10px;">交易数据</h3>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td80"></th>
                    <th class="td130">成交金额（元）</th>
                    <th class="td130">成交积分</th>
                    <th class="td130">成交用户数</th> 
                    <th class="td130">退款订单量</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td80">今天</th>
                    <td class="td130"><fmt:formatNumber value="${vo.toDayTurnover/20}" pattern="#,###"/></th>
                    <td class="td130">${vo.toDayTurnover}</th> 
                    <td class="td130">${vo.toDayUserNumber}</th> 
                    <td class="td130">${vo.toDayRefundOrders}</th>
                </tr>
            </table>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td80">昨天</th>
                    <td class="td130"><fmt:formatNumber value="${vo.yesterDayTurnover/20}" pattern="#,###"/></th>
                    <td class="td130">${vo.yesterDayTurnover}</th> 
                    <td class="td130">${vo.yesterDayUserNumber}</th> 
                    <td class="td130">${vo.yesterDayRefundOrders}</th>
                </tr>
            </table>
            <br />
            <form id="form1" name="form1">
            <h3 style="padding-left:10px;">评论数据</h3>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="td80"></th>
                    <th class="td130">一星</th>
                    <th class="td130">二星</th> 
                    <th class="td130">三星</th>
                     <th class="td130">四星</th>
                      <th class="td130">五星</th>
                </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td80">今天</td>
                    <td class="td130">${vo.toDayComment1 }</td>
                    <td class="td130">${vo.toDayComment2 }</td>
                    <td class="td130">${vo.toDayComment3 }</td>
                    <td class="td130">${vo.toDayComment4 }</td>
                    <td class="td130">${vo.toDayComment5 }</td>
                </tr>
            </table>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td80">昨天</td>
                    <td class="td130">${vo.yesterDayComment1 }</td>
                    <td class="td130">${vo.yesterDayComment2 }</td>
                    <td class="td130">${vo.yesterDayComment3 }</td>
                    <td class="td130">${vo.yesterDayComment4 }</td>
                    <td class="td130">${vo.yesterDayComment5 }</td>
                </tr>
            </table>
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td style="text-align:right; padding-top:50px;">日期：<fmt:formatDate value="${vo.date}" pattern="yyyy-MM-dd "/></th>
                    
                </tr>
            </table>
        </div>
    	</div>
	<div class="clear"></div>

</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/seller/seller_footer.jsp" flush="true" />
<!--底部-e-->
</body>
</html>
