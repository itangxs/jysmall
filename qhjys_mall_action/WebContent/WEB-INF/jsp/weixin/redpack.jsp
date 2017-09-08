<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="/css/red-packet-lottery.css" type="text/css" rel="stylesheet">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/js/weixin/easing.js"></script>
<title>支付返现</title>
</head>
<body>
<div id="whole-bg">

<div class="cloud-bg">
   <div class="lottery-machine">
    <img class="lottery-machine-img" src="/images/lottery-machine5.png">
    <div class="num_box">
      <div class="num num1"></div>
      <div class="num"></div>
      <div class="num"></div>
      <div class="point">
         <img src="/images/point.png">
      </div>

     	 <div class="num num4"></div>
      	 <div class="num"></div>
		 <div class="btn">
       		<a href = "javascript:void(0)" onclick = ""></a>
        </div>
         <input type="hidden" id="orderId" value="${orderId }"/>
        <input type="hidden" id="redpackrecordId" value="${fpr.id }"/>
        <input type="hidden" id="redpackrecordStatus" value="${fpr.status }"/>
        <input type="hidden" id="redpackrecordMoney" value="${fpr.redpackMoney *100}"/>
    </div>
        
      <div class="lottery-times">
        	<h1>你当前剩余的抽取红包次数：<span id="cishu">1</span> 次</h1>
            <p>随机消费即可获得最高999元的随机红包噢！</p>
            <p>消费金额越高，收到的随机红包金额也越高！</p>
      </div>
        
      <div class="tips">
          <p>温馨提示：赶紧抽到的红包可在【飞券公众号】界面领取，领取后将直接转入微信零钱账户。</p>
    </div>
  </div>
 </div>
   
   
   
 
  <div id="light" class="gray-bg">
  	  	<div class="tanchu-bg">
  		<img src="/images/tanc1.png">      
        <div class="content">
        	<h1>您抽到<span class="res"></span>元！</h1>
            <div class="close-btn">
            	<a href = "javascript:void(0)" onclick = "document.getElementById('light').style.display='none'"></a>
            </div>
        </div>
        </div>
      </div>    
      
     <div id="light2" class="gray-bg"> 
     <div class="tanchu-bg">
  		<img src="/images/tanc3.png">
        
        <div class="content2">
        	<h1>您抽到<span class="res"></span>元！</h1>

            <div class="close-btn2">
            	<a href = "javascript:void(0)" onclick = "document.getElementById('light2').style.display='none'"></a>
            </div>
        </div>
      </div>   
   </div>    
   
   <div id="light3" class="gray-bg">  
  	  <div class="tanchu-bg">
  		<img src="/images/tanc4.png">        
        <div class="content3">
            <div class="close-btn3">
            	<a href = "javascript:void(0)" onclick = "document.getElementById('light3').style.display='none'"></a>
            </div>
        </div>
      </div>   
       
  </div> 
   
</div>

</body>
</html>





<script>
function numRand() {
	var x = 19999; //上限
	var y = 0; //下限
	var rand = parseInt(Math.random() * (x - y + 1) + y);
	return rand;
}
var isBegin = false;
$(function(){
	var u = $('.num_box').innerHeight();
	$('.btn a').click(function(){
		if(isBegin || $("#redpackrecordStatus").val() != 6){
			document.getElementById('light3').style.display='block';
			return false;
		};
		isBegin = true;
		$(".num").css('backgroundPositionY',0);
		var result = parseInt($("#redpackrecordMoney").val())+'';
		if(result.length<5){
			var a='0';
			var addlen = 5-result.length;
			for(var i=0;i<addlen;i++){
				result = ''+a+result;
			}
		}
		$('.res').text(result/100);
		var num_arr = (result+'').split('');
		$(".num").each(function(index){
			var _num = $(this);
			setTimeout(function(){
				_num.animate({ 
					backgroundPositionY: ((u-1)*60) - ((u-1)*num_arr[index])
				},{
					duration: 600+index*300,
					easing: "easeInOutCirc",
					complete: function(){
						if(index==4){
							$.ajax({
								url:"/wxMall/doOrderRedpack.do",
								type: "POST",
								data: "rprid="+$("#redpackrecordId").val(),
								success: function(result){
									if(result == -1){
										isBegin=false;
										alert("抽奖订单异常");
									}else if(result == 5){
										document.getElementById('light2').style.display='block';
										$("#cishu").html("0");
										$("#redpackrecordStatus").val(result);
									}else{
										document.getElementById('light').style.display='block';
										$("#cishu").html("0");
										$("#redpackrecordStatus").val(result);
									}
								},
								error: function(){
									alert("请求后台数据失败！");
								}
							});
						}
					}
				});
			}, index * 300);
		});
	});	
});
</script>