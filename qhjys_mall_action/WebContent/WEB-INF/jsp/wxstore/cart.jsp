<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>购物车</title>
<link href="/css/zhifu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/wxstore/cart1.js"></script>
</head>
<body>
<input type="hidden" id="storeId" value="${storeId}">
<input type="hidden" id="userId" value="${userId}">
<c:forEach items="${products}" var="p" varStatus="status">
		<div class="cartlist">
			<div class="foodbox">
				<div class="proimg">
					<%-- <a href="javascript:godishesdetail('${storeId}','${p.id}')"> --%>
					<img src="${p.productImages }">
					<!-- </a> -->
				</div>
				<div class="textbox10">
					<p class="textbox12">
						${p.productName}
					</p>
					<p class="textbox11">
						<span class="red">${p.price}</span> 元/份
						<input type="hidden" name="productId" id="productId${p.id }" value="${p.id}">
						<input type="hidden" id="productprice${p.id }" value="${p.price}">
						<input type="hidden" id="productstock${p.id }" value="${p.stock}">
					</p>
				</div>
				<div class="addbuy">
					<input class="add" type="button" value="-" 
					id="minusnum${p.id }" onclick="cartminusnum('${p.id}')"/>
					<input class="number" id="numberinput${p.id }"
						type="text" value="${nums[status.index]}" />
						<input class="add" type="button"
						value="+" id="plusnum${p.id }" onclick="cartplusnum('${p.id}')"/>
				</div>
				<div class="clear"></div>
			</div>
		</div>
</c:forEach>

<div class="mbottom60"></div>
<div class="onlinebottom">
  <div class="left"><h1><span class=""><img src="/images/car.png"></span>合计:￥<span id="howmuch">0.00</span></h1></div>
  <div><a href="javascript:void(0)" onclick="document.getElementById('zhuohao').style.display='block'; document.getElementById('fade').style.display='block'" class="buttomred">确认(<span id="howmany">0</span>)</a></div>
</div>
<c:if test="${storeId == 3156}">
<!--飞券弹出层-->
<div id="zhuohao" class="white_content">
	<div class="zhuohaoform">
      <p>商家名称</p>
      <input class="input2" id="peopleNum" type="text" value="" />
      <p>收件人姓名</p>
      <input class="input2" id="username"  type="text" value="" />
      <p>联系电话</p>
      <input class="input2" id="phoneNum"  type="text" value="" />
      <p>收件地址</p>
      <div class="input3box">
      <textarea class="input3" id="deskNo"  name="" cols=""></textarea>
      </div>
      <input class="button1" type="button" value="提交" onclick="confirmcart()"/>	
    </div>
</div>
</c:if>
<c:if test="${storeId != 3156}">
<!--桌号弹出层-->
<div id="zhuohao" class="white_content">
	<div class="zhuohaoform">
    	<input class="input1" type="text" id="deskNo" value="请输入您的桌号" />
        <p>温馨提示：<br />请查看桌上号码牌或询问服务员</p>
        <input class="button1" type="button" value="提交" onclick="confirmcart()"/>	
    </div>
</div>
</c:if>

<!--弹出幕布-->
<div id="fade" class="black_overlay" onclick="hidefade()"></div>
</body>
</html>