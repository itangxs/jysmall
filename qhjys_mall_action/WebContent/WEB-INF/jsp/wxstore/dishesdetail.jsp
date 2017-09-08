<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>菜品详情</title>
<link href="/css/zhifu.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/wxstore/dishsdetail.js"></script>
</head>

<body>
<div class="wrapper">
  <div class="index_img"><img src="${product.productImages}"></div>
    <!--end-->
  <div class="sellerdetail">
  <input type="hidden" id="storeId" value="${storeId}">
  <input type="hidden" id="productId" value="${product.id}">
  <input type="hidden" id="currentNum" value="${currentNum}">
      <div class="sellertop">
          <p class="lineh25">${product.productName }</p>
          <div class="">
          	<div class="floatred">￥ ${product.price } </div>
          	<input type="hidden" id="price${product.id }" value="${product.price }"/>
          	<input type="hidden" id="stock${product.id }" value="${product.stock }"/>
          	<div class="addbuy"><input class="add" type="button" value="-" 
          		id="minusnum${product.id }" onclick="detailminusnum('${product.id}')"/>
          	<input id="numberinput${product.id }" class="number" type="text" value="${currentNum }" />
          	<input class="add" type="button" value="+" id="plusnum${product.id }" onclick="detailplusnum('${product.id}')"/>
          	</div>
            <div class="clear"></div>
          </div>
      </div>  
    <div class="sellerinfo topbk">
        <div class="description">
            <p class="title">${product.productName }</p>
            <p>${product.productInfo }
            </p>
        </div>
    </div>
  </div> 
</div>
<div class="onlinebottom">
  <div class="left"><h1><span class=""><img src="/images/car.png"></span><span id="howmany">${currentNum}</span>份</h1><span class="price"><span id="howmuch">${totalPrice}</span>元</span></div>
  <div><a href="javascript:dishesdetailcart('${storeId }');" class="buttomred">选好了</a></div>
</div>
</body>
</html>