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
<script type="text/javascript" src="/js/account/order/my_orderForm_datail.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/account/uHeader.jsp" flush="true" />
<div class="membercontent">
	<jsp:include  page="/WEB-INF/jsp/account/navigation1.jsp"><jsp:param name="position" value="1"/></jsp:include>
	<!--------------右侧------------------>
	<div class="memberright">
		<div class="memberright_title">订单详情</div>
		<div class="member-orderdetail">
			<c:choose>
			<c:when test="${orderVo.status==1}">
			<div class="orderdetail1">
				<h1>当前状态：<span>未付款</span></h1>
				<p>订单已生成，请及时付款，不然都被抢光啦！</p>
				<p>
					<input type="button"  style="cursor:pointer;" class="button_red"  onclick="window.location.href='/managermall/account/order/toPay.do?orderId=${orderVo.orderId}'" value="付款" />
					<input type="button" style="cursor:pointer;"  class="button_hui"  onclick="delet(${orderVo.orderId})" value="删除" />
				</p>
			</div>
			</c:when>
			<c:when test="${orderVo.status==2}">
				<div class="orderdetail1">
					<h1>
						当前状态：<span>
					<%-- 	<c:if test="${orderVo.prodType==1}">等待消费</c:if>
						<c:if test="${orderVo.prodType==2}">等待收货</c:if> --%>
						等待收货/消费
						</span>
					</h1>
					<p>
						您已经支付成功，请尽快去商家消费！<br />小提示：如果没收到短信，记下或拍下消费记录向商家出示。
					</p>
				</div>
			</c:when>
			<c:otherwise>
			<div class="orderdetail1">
				<h1>
					当前状态：<span>
					<c:if test="${orderVo.status==1}">未付款</c:if>
					<c:if test="${orderVo.status==2}">已付款</c:if>
					<c:if test="${orderVo.status==3}">已收货/已消费</c:if>
					<c:if test="${orderVo.status==4}">已评论</c:if>
					<c:if test="${orderVo.status==5}">申请退款</c:if>
					<c:if test="${orderVo.status==6}">退款成功</c:if>
					<c:if test="${orderVo.status==7}">申请售后</c:if>
					<c:if test="${orderVo.status==8}">归档</c:if>
					<c:if test="${orderVo.status==9}">已过期</c:if>
					</span>
				</h1>
				<p>
					您已经支付成功，请尽快去商家消费！<br />小提示：如果没收到短信，记下或拍下消费记录向商家出示。
				</p>
			</div>
			</c:otherwise>
			</c:choose>
			<div class="orderdetail2">
				<h1>订单信息</h1>
				<h2>
					<p><em>下单日期：<fm:formatDate value="${orderVo.orderDate}" pattern="yyyy-MM-dd"/></em>订单编号：${orderVo.orderNo}</p>
					<p><em>使用积分：${orderVo.integral}</em>总&nbsp;金 &nbsp;额：<fm:formatNumber value="${orderVo.totamt}" type="currency" pattern="#,###.##"/></p>
					<c:forEach items="${orderVo.list}" var="detailList">
						<c:forEach items="${detailList.volumes}" var="volumes">
							<c:if test="${not empty volumes.volumeCode}">
								<p><em>&nbsp;</em><h1 style="color:red;">飞券串码：${volumes.volumeCode}</h1></p>
							</c:if>
						</c:forEach>
					</c:forEach>
				</h2>
				<c:if test="${!empty orderVo.addr}">
				<h2>
					<p>
						<strong>收货人信息</strong>
					</p>
					<p>收货地址：${provinceName} ${cityName} ${areaName} ${orderVo.addr.address}</p>
					<p>收货人：${orderVo.addr.recipient}</p>
					<p>电话：${orderVo.addr.phone}</p>
				</h2>
				</c:if>
				<c:if test="${!empty orderVo.cashLog}">
				<h2>
					<p>
						<strong>支付及配送方式</strong>
					</p>
					<p>支付方式：
					<c:choose>
						<c:when test="${orderVo.cashLog.payWay==1}">在线支付</c:when>
						<c:when test="${orderVo.cashLog.payWay==2}">网银支付</c:when>
						<c:when test="${orderVo.cashLog.payWay==3}">支付宝支付</c:when>
						<c:when test="${orderVo.cashLog.payWay==4}">平台支付</c:when>
						<c:otherwise>其它支付类型</c:otherwise>
					</c:choose>
					</p>
					<p>运费：<fm:formatNumber value="${orderVo.expFare}" type="currency" pattern="#,###.##"/></p>
					<c:if test="${!empty orderVo.delivDate}"><p>配送日期：<fm:formatDate value="${orderVo.delivDate}" pattern="yyyy-MM-dd"/></p></c:if>
				</h2>
				</c:if>
			</div>
			<div class="member_myorder">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="td370">项目</th>
						<th class="td80">数量</th>
						<th class="td80">兑换金额</th>
						<th class="td80">兑换总额</th>
						<th class="td80">支付元宝</th>
						<th class="td130">订单状态</th>
						<th class="td130">操作</th>
					</tr>
					<c:forEach var="list" items="${orderVo.list}">
					<tr>
						<td class="td370">
							<a href="javascript:;"><img src="${list.prodImage}" /></a>
							<p class="textheight25">${list.prodName}</p>
							<p><span class="zhibg">限</span><fm:formatDate value="${list.prodEndDate}" pattern="yyyy-MM-dd"/></p>
						</td>
						<td class="td80">${list.quantity}</td>
						<td class="td80"><fm:formatNumber type="currency" value="${list.payPric.divide(20)}" pattern="#,###.##"/></td>
						<td class="td80"><fm:formatNumber type="currency" value="${list.moneny.divide(20)}" pattern="#,###.##"/></td>
						<td class="td80"><fm:formatNumber value="${list.moneny}"/></td>
						<td class="td130">
							<c:choose>
		            			<c:when test="${list.status==1}"><p>未付款</p></c:when>
		            			<c:when test="${list.status==2}"><p>已付款</p></c:when>
		            			<c:when test="${list.status==3}"><p>已消费</p></c:when>
		            			<c:when test="${list.status==4}"><p>已评论</p></c:when>
		            			<c:when test="${list.status==5}"><p>申请退款</p></c:when>
		            			<c:when test="${list.status==6}"><p>退款成功</p></c:when>
		            			<c:otherwise>申请售后</c:otherwise>
		            		</c:choose>
	            		</td>
	            		<td class="td130">
	            			<c:if test="${list.status==2&&list.prodType==2}">
								<p>
									<a href="/managermall/account/refund/toApply.do?id=${list.detailId}" class="tuikuan">我要退款</a>
								</p>
							</c:if>
							<c:if test="${list.status==2&&list.prodType==2}">
								<p>
									<a href="/managermall/account/order/myLogistics.do?orderId=${list.detailId}" class="tuikuan">查看物流</a>
								</p>
							</c:if>
							<c:if test="${list.status==3}">
							<p>
								<a href="/managermall/account/evaluate/toAddPage.do?id=${list.detailId}" class="fukuan">我要评论</a>
							</p>
							</c:if>                            
						</td>
					</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<%-- <div class="memberright">
   	    <div class="memberright_title">订单详情</div>
   	  <div class="member-orderdetail">
       	  <div class="orderdetail1">
            	<h1>当前状态：<span>
            			<c:if test="${orderVo.ostatus==0}">未付款</c:if>
                   		<c:if test="${orderVo.ostatus==1}">已支付</c:if>
            	</span></h1>
              	<p>订单已生成，请及时付款，不然都被抢光啦！</p>
                <p>
                	<input type="button" style="cursor: pointer;" class="button_red" value="付款"/>
                	<input type="button" style="cursor:pointer;" class="button_hui" onclick="delet(${orderVo.orderId})" value="删除">
                	<input type="button" style="cursor:pointer;" class="button_hui" onclick="location='/account/refund/toApply.do?id=${orderVo.orderId}'" value="退款">
                	</p>
          </div>
        	<div class="orderdetail1">
            	<h1>当前状态：<span>等待消费</span></h1>
                <p>您已经支付成功，请尽快去商家消费！<br />小提示：如果没收到短信，记下或拍下消费记录向商家出示。</p>
          	</div>
       	<div class="orderdetail2">            	
       	  <h1>订单信息</h1>
                <h2>
                	<p><strong>收货人信息</strong></p>
					<p>收货地址：${orderVo.delivery.province} ${orderVo.delivery.city} ${orderVo.delivery.area} ${orderVo.delivery.address} 
					${orderVo.delivery.zip} </p>
                    <p>收货人：${orderVo.delivery.recipient}</p>
                    <p>电话：${orderVo.delivery.tel_no}</p>
                    <p>手机：${orderVo.delivery.phone}</p>
                </h2>	
                <h2>
           	    	<p><strong>支付及配送方式</strong></p>
               	 	<p>支付方式：在线支付</p>
                    <p>运费：${orderVo.expressFare}</p>
                    <p>工作日、双休日及节假日均可送货</p>
                </h2>
          </div>
          <div class="member_myorder">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <th class="td370">项目</th>
                <th class="td80">数量</th>
                <th class="td130">金额</th>
                <th class="td130">订单状态</th>
                <th class="td130">操作</th>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            
            <c:forEach items="${orderVo.list}" var="i">
              <tr>
                <td class="td370">
                	<a href="/getProductById.do?id=${i.prodId}">
                		<img src="/${i.prodImage}" />
                	</a>
                  <p><a href="listdetail.do">${i.prodName}</a></p>
                  <p>有效期：<fmt:formatDate value="${i.prodEndDate}" pattern="yyyy-MM-dd"/></p>
                  <p><a href="/getProductById.do?id=${i.prodId}" class="fontred">查看</a></p></td>
                <td class="td80">${i.quantity}</td>
                <td class="td130">￥<fmt:formatNumber value="${i.moneny}" pattern="#,#00.00#"/></td>
                <td class="td130">
                		<c:if test="${i.dstatus==1}">未使用</c:if>
                		<c:if test="${i.dstatus==2}">已使用</c:if>
                </td>
                <td class="td130"><p><a href="member-refundapply.do" class="fontred">我要退款</a><br /><a href="member-orderlogistics.html">查看物流</a></p></td>
              </tr>
             </c:forEach>
             
            <!--   <tr>
                <td class="td370"><a href="#"><img src="images/pro_adv1.jpg" /></a>
                  <p>城市厨子-鱼趣代金券1张</p>
                  <p>有效期：2015-5-1</p>
                  <p><a href="#" class="fontred">查看</a></p></td>
                <td class="td80">2</td>
                <td class="td130">￥10,000</td>
                <td class="td130">已消费</td>
                <td class="td130"><p>已评价</p></td>
              </tr> -->
              
            </table>
          </div>
      </div>
    </div>
	<div class="clear"></div> --%>
</div>
<!------------------------------底部---------------------------------------------->
<jsp:include page="/WEB-INF/jsp/account/uFooter.jsp" flush="true" />
</body>
</html>
