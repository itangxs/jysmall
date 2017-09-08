<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath().equals("/")?"":request.getContextPath();
%>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	//return ;
}  
%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>周边优惠劵领取</title>
<link href="/css/weixin/cardcoupon/getraffle.css" type="text/css" rel="stylesheet" />
<script src="/js/weixin/jquery.minA.js"></script>
</head>

<body>
<div><img class="img100" src="/images/weixin/cardcoupon/u96.jpg"></div>
<div class="b_quanlist">
	<ul><!--此列表显示5条-->
		<c:forEach items="${list }" var="obj">
			 <li onclick="location.href ='/wxMall/jump/getCardCoupon.do?flag=2&sellerId=${obj.sellerId }&deliveryId=${obj.deliveryId}&orderId=${orderId }'">
	   	  		<p><span class="icon1"></span>【${obj.storeName }】送出的礼包</p>
	        	<input class="button_cj" name="" type="submit" value="去抽奖">
   	 	     </li>
		</c:forEach>
       <!--  <li onclick="location.href ='/wxMall/jump/getCardCoupon.do'">
   	  		<p><span class="icon1"></span>【九尾壶炭火烤肉】送出的礼包</p>
        	<input class="button_cj" name="" type="submit" value="去抽奖">
   	    </li> -->
    </ul>
</div>
<!--飞券随机兑换券说明-->
<div class="rulesbox2">
	<script type="text/javascript">
    // 收缩展开效果
    $(document).ready(function(){
       $(".rulesbox1 h1").toggle(function(){
         $(this).next(".rules_text1").animate({height: 'toggle', opacity: 'toggle'}, "slow");
       },function(){
         $(this).next(".rules_text1").animate({height: 'toggle', opacity: 'toggle'}, "slow");
       });
    });
    </script>
    <div class="rulesbox1">
        <h1>飞券随机兑换券说明<span><img src="/images/weixin/cardcoupon/get-ico1.png"></span></h1>
        <div class="rules_text1" style="display:none;">
            1.在您支付的同时，可获得多个商家的随机兑换券抽取机会；<br>
            2.可邀请好友帮忙抽奖；<br>
            3.被邀请抽奖的好友也可以发起抽奖活动；<br>
            4.所有商家可点击进入抽取兑换券，每间商家每种兑换券最多可兑换1次；<br>
            5.兑换券有效期限为15天，在商家消费时使用；<br>
            6.所有券可在「菜单」-「我的兑换券」中查看；<br>
            7.本活动最终解释权归飞券网所有。
      </div>
    </div>
</div>    
</body>
</html>
