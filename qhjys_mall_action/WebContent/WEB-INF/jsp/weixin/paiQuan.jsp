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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<title>派劵啦</title>
<style type="text/css">
.top{
  position: relative;
  z-index: 0;
  width: auto;width: 100%; height: auto;
     background-repeat: no-repeat;
    background-position: 0 0;
    -webkit-background-size:100% auto;
    -moz-background-size:100% auto;
    -o-background-size:100% auto;
    background-size:100% auto;
}
.center{
	position:absolute;
    height:auto; position: relative;
    width: 100%;
    background-repeat: no-repeat;
    background-position: 0 0;
    -webkit-background-size:100% auto;
    -moz-background-size:100% auto;
    -o-background-size:100% auto;
    background-size:100% auto;
}
.quan{
	position:absolute;
	left:5%;
	top:8%;
	width:90%;
    height:auto; z-index: 4;
    background-repeat: no-repeat;
    background-position: 0 0;
    -webkit-background-size:100% auto;
    -moz-background-size:100% auto;
    -o-background-size:100% auto;
    background-size:100% auto;
	 
}
.pack{
	position:absolute;
	left:5%;
	top:70%;
	width:90%;z-index: 0;
    height:auto; 
    background-repeat: no-repeat;
    background-position: 0 0;
    -webkit-background-size:100% auto;
    -moz-background-size:100% auto;
    -o-background-size:100% auto;
    background-size:100% auto;
}
.buttom_ {
	position:absolute;
	background-color:#f3f3f3;
	width:100%;
	height:10em;
}
.textdetail{
	text-align: center;
	padding-top: 4%;font-size: 0.5em;font-family:Microsoft YaHei;
	color: #011199F;
	margin: auto 0;
	height: auto;
	overflow: hidden;
}
.textdetail1{
	text-align: center;
	padding-top: 3%;font-size: 0.5em;font-family:Microsoft YaHei;
	color: #ff0000;
	margin: auto 0;
	height: auto;
	overflow: hidden;
	
}
.detail1{position:absolute;top:9%;left:33%;z-index: 5;color: #ffffff;font-size:0.5em;}
.detail2{position:absolute;top:12%;left:46%;z-index: 5;color: #ffffff;font-size:0.9em;}
.detail3{position:absolute;top:17%;left:56%;z-index: 5;color: #ffffff;font-size:0.7em}
</style>

</head>
	<input type="hidden" name="source" value="${source }">
	<input type="hidden" name="id" value="${ cardCouponTem.id}">
<body style="background-color:#f3f3f3;">
 	
	<div class="center">
		<img class="center" src="/images/weixin/cardcoupon/paiquan3.jpg"  alt="" />
	</div>
   <div class="quan">
		
		 <c:choose>
		  		<c:when test="${ cardCouponTem.couponCfg ==0}">
		  			<img class="quan" src="/images/weixin/cardcoupon/lpq_detail.png" alt="" />
		  		</c:when>
		  		<c:when test="${ cardCouponTem.couponCfg ==1}">
		  			 <img class="quan" src="/images/weixin/cardcoupon/djq_detail.png" alt="" />
		  		</c:when>
		  		<c:when test="${ cardCouponTem.couponCfg ==2}">
		  			<img class="quan" src="/images/weixin/cardcoupon/zkq_detail.png" alt="" />
		  		</c:when>
		  </c:choose>
		
	</div>
     <div class="detail1">
       <h2>${cardCouponTem.storeName}</h2>
     </div>
     <div class="detail2">
      <h3>${cardCouponTem.name}</h3>
     </div>
      <div class="detail3">
      <h4>
	     <fmt:formatDate value="${cardCouponTem.validityEndtime}" type="both" pattern="yyyy.MM.dd"/>前有效
      </h4>
     </div>
       
    <div class="textdetail">
		<h1>恭喜您获得一张</h1>
	</div>
     <div class="textdetail1">
		<h1>
		  <c:choose>
		  		<c:when test="${ cardCouponTem.couponCfg ==0}">
		  				礼品券【${cardCouponTem.name}】
		  		</c:when>
		  		<c:when test="${ cardCouponTem.couponCfg ==1}">
		  				代金券【${cardCouponTem.name}】
		  		</c:when>
		  		<c:when test="${ cardCouponTem.couponCfg ==2}">
		  				折扣券【${cardCouponTem.name}】
		  		</c:when>
		  </c:choose>
		</h1>
	</div>
<div class="pack">
		<img class="pack" src="/images/weixin/cardcoupon/paiquan11.jpg" onclick="receiveCoupon()" alt="" />
	</div>
<div class="buttom">
</div>
</body>

<script type="text/javascript">
  		
	function receiveCoupon(){
		var source = $("input[name='source']").val();
		var id = $("input[name='id']").val();
		$.ajax({
            type: 'GET',
            url: '/wxMall/receiveCardCoupon.do',
            data: {id:id,source:source},
            dataType: 'json',
            success: function(obj){
				if(obj.flag==0||obj.flag=="0"){
					alert(obj.reason);
					location.href ='/wxMall/jump/getMyCardCoupon.do';//跳转到我的卡包页面
				}else{
					alert(obj.reason);
				}
            },
            error: function(xhr, type){
                alert('请求网络失败!');
                // 即使加载出错，也得重置
                me.resetload();
            }
		 });
	}
</script>



</html>
