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
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<title>${store.storeName}</title>
<link href="/css/fqzhifu.css" type="text/css" rel="stylesheet" />
</head>

<body class="wholebg">
<div class="whole">
    <div class="store-detail">
    	<div class="store-box">
            <h1 class="store-title">${store.storeName}</h1>
            <div class="store-address">
                <p><span class="icon"></span><span class="ptext">${store.address}</span></p>
            </div>
        </div>
        <img class="imgmall" src="${store.storeLogo}">
    </div>
    <div class="online-box">
    	<c:if test="${store.id != 38 }">
        <div class="online-order">
            <a href="/store/fqproducts/onlineorder.do?storeId=${store.id}">在线点餐</a>
        </div>
        </c:if>
        <c:if test="${empty storerebate }">
          	<div class="online-pay"><a href="javascript:alert('暂不支持在线支付')">在线支付</a></div>
          </c:if>
        <c:if test="${not empty storerebate }">
        <div class="online-pay"><a href="/wxMall/goRebateDetail.do?storeId=${storerebate.storeId }">在线支付</a></div>
        </c:if>
	</div>
</div>
<!--下面是原来的内容-->
<div class="wrapper" style="display:none;">
  <div class="index_img11"><img src="${store.storeLogo}"></div>
    <!--end-->
  <div class="sellerdetail">
      <div class="sellertop">
          <p class="lineh25">${store.storeName}</p>
          <ul>
            <li>&nbsp;
            <!-- (${store.trafficBeginTime}-${store.trafficEndTime})
            <c:if test="${not empty storerebate }">
            <span class="greentext">
            <fmt:formatNumber value="${storerebate.rebate}" pattern="#,#0.0#"/>
         	 折</span></c:if> --></li>
            
            <li>&nbsp;<!-- <span class="red">注:</span>在线支付全单折扣 --></li>
            <div class="clear"></div>
          </ul>
          <div class="sellerbutton">
          <c:if test="${empty storerebate }">
          	<a class="buttongreen" href="javascript:alert('暂不支持在线支付')">在线支付</a>	
          </c:if>
           <c:if test="${not empty storerebate }">
            <a class="buttongreen" href="/wxMall/goRebateDetail.do?storeId=${storerebate.storeId }">在线支付</a></c:if>
         <c:if test="${store.id != 38 }"><a class="buttonred" href="/store/fqproducts/onlineorder.do?storeId=${store.id}">在线点餐</a></c:if></div>
      </div>  
    <div class="selleraddress">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="arr-wz"><img src="/images/weixin/arr-wz.png"></td>
            <td class="address">${store.address}</td>
            <!--<td class="arr-tel"><img src="/images/weixin/arr-tel.png"></td>-->
          </tr>
        </table>
    </div>
    <div class="sellerinfo" style="display:none">
        <ul class="listimg">
        	<c:if test="${!empty fn:split(store.storeImage, ',')[0]}">
            	<li><img width="100px;" height="100px;"  src="${fn:split(store.storeImage, ',')[0]}"></li>
            </c:if>
            <c:if test="${!empty fn:split(store.storeImage, ',')[1]}">
           		 <li><img width="100px;" height="100px;"  src="${fn:split(store.storeImage, ',')[1]}"></li>
            </c:if>
            <c:if test="${!empty fn:split(store.storeImage, ',')[1]}">
           		 <li><img width="100px;" height="100px;"  src="${fn:split(store.storeImage, ',')[2]}"></li>
            </c:if>
            <div class="clear"></div> 
        </ul>
        <div>
            <p class="title">${store.storeName }</p>
            <p>
            	${store.storeInfo}
            </p>
        </div>
    </div>
    
  </div> 
</div>

</body>
</html>
