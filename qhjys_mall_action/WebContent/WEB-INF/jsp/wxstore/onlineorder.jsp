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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>在线点单</title>
<link href="/css/zhifu.css" type="text/css" rel="stylesheet" />
<link href="/css/form.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/formcheck.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/wxstore/productright.js"></script>
</head>
<body scroll="">
<!--<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="30%"><iframe name="t1" src="left.html" style="overflow:auto; position:fixed; height:100%; width:100%;" >左边列表菜单</iframe></td>
    <td width="70%"><iframe name="t2" src="right.html" style="overflow:auto; position:fixed; height:100%; width:100%;">右边列表显示页</iframe></td>
  </tr>
</table>-->
<div class="">
    <!--在线点单left-->
    <div class="iframe_left">
    <input type="hidden" id="storeId" value="${storeId}">
        <ul>
        <c:forEach items="${productTypes}" var="pts" varStatus="status">
	    <li <c:if test="${status.index == 0}">class="current"</c:if>>
	    <p><span><a  href="/store/fqproducts/onlineorder.do?storeId=${storeId}#typeindex${status.index}">${pts.typeName}</a></span></p>
	    </li>
	    <%-- <li ><a href="javascript:leftselect('${status.index }')">${pts.typeName}</a></li> --%>
    	</c:forEach>
        </ul>
        <div class="mbottom60"></div>
    </div>
	<!--在线点单right-->
    <div class="iframe_right">
    	 <c:forEach items="${typeAndProducts}" var="tps" varStatus="status">
		<div class="foodlist" id="typeindex${status.index}">
			<a name="typeindex${status.index}" id="typeindex${status.index}"></a>
			<div class="title">${tps.typeName}</div>
			<ul>
				<c:forEach items="${tps.products}" var="pt" varStatus="status">
					<li class="current">
		        		<div class="foodbox">
							<div class="gou">
								<form accept-charset="utf-8" method="get" action="#">
									<fieldset class="checkboxes">
										<label for="btn${pt.id }" class="label_check">
										<input
											type="checkbox" value="${pt.id }" id="btn${pt.id }"
											name="sample-checkbox-01" onclick="showselect('${pt.id}')" />
										</label>
									</fieldset>
								</form>
							</div>
							<div class="textbox">
								<p class="textbox2">
									${pt.productName}
								</p>
								<p class="textbox1">
									<span class="red">${pt.price}</span> 元/份
									<input type="hidden" id="productprice${pt.id }" value="${pt.price}">
									<input type="hidden" id="productstock${pt.id }" value="${pt.stock}">
								</p>
							</div>
							<div id="div1${pt.id }" class="proimg" style="display: block">
								<a href="javascript:dishesdetail('${storeId }','${pt.id }')" target="_blank"><img
									src="${pt.productImages }"></a>
									<!-- src="/images/food.jpg"></a> -->
							</div>
							<div id="div2${pt.id }" class="addbuy aligncenter" style="display: none">
								<input class="add" type="button" value="-" id="minusnum${pt.id }" onclick="minusnum('${pt.id}')"/>
								<input id="numberinput${pt.id }"
									class="number" type="text" value="0" /><input class="add"
									type="button" value="+" id="plusnum${pt.id }" onclick="plusnum('${pt.id}')"/>
							</div>
							<div class="clear"></div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</c:forEach>
      <div class="mbottom60"></div>
  </div>
  <div class="clear"></div>
</div>

<div class="onlinebottom">
  <div class="left"><h1><span class=""><img src="/images/car.png"></span><span id="howmany">0</span>份</h1><span class="price"><span  id="howmuch" >0</span>元</span></div>
  <div><a href="javascript:commitproduct('${storeId }');" class="buttomred">选好了</a></div>
</div>
</body>
</html>