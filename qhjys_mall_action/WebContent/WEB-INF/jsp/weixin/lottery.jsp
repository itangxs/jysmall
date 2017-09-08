<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>飞券网—带你免费抽啥吃啥</title>
<link href="/css/weixin/public.css" type="text/css" rel="stylesheet" />
<script src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/a1.js"></script>
<script src="/js/jQueryRotate.2.2.js"></script>
<script src="/js/jquery.easing.min.js"></script>
<script src="/js/lottery.js" type="text/javascript"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script type="text/javascript">
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: 'wx6e0532e3ad8d3b82', // 必填，公众号的唯一标识
	    timestamp:'${datetime}', // 必填，生成签名的时间戳
	    nonceStr: '${nonceStr}', // 必填，生成签名的随机串
	    signature: '${sha}',// 必填，签名，见附录1
	    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function () {
	    wx.checkJsApi({
	        jsApiList: [
	            'onMenuShareTimeline','onMenuShareAppMessage'
	        ]
	    });

	wx.onMenuShareTimeline({
	    title: '我颜值不够，能不能拿下大奖就看你的了！', // 分享标题
	    link: 'http://www.jysmall.com/lottery/userLottery.do?openid=${ul.openId}&userLotteryId=${ul.id}', // 分享链接
	    imgUrl: '${ul.portrait}', // 分享图标
	    success: function () { 
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	wx.onMenuShareAppMessage({
		 title: '我颜值不够，能不能拿下大奖就看你的了！', // 分享标题
	    desc: '听说颜值高的能拿特等奖哦！', // 分享描述
	    link: 'http://www.jysmall.com/lottery/userLottery.do?openid=${ul.openId}&userLotteryId=${ul.id}', // 分享链接
	    imgUrl: '${ul.portrait}', // 分享图标
	    type: '', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	        // 用户确认分享后执行的回调函数
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	});
</script>
</head>

<body>
<input type="hidden" id="isguanzhu" value="${isguanzhu }">
<input type="hidden" id="already" value="${already }">
<input type="hidden" id="userLotteryId" value="${ul.id }">
<input type="hidden" id="iscli" value="0">
<div class="wrapper">
  <div class="rafflebox">
  	<div class="buttontop">
    	<ul>
        	<li><div class="buttonleft">当前商户<br>【${store.name }】</div></li>
            <li style=" text-align:right;"><a href="javascript:woyaofaqi()"><input class="buttonright" name="" type="image" src="/images/weixin/buttonbg2.png"></a></li>
            <div class="clear"></div>
        </ul>
    </div>
  	<img class="raffleboxbg" src="/images/weixin/bg.jpg">
  </div>
  <!--转盘-->
  <div class="ly-plate">
      <div class="rotate-bg"></div>
      <div class="lottery-star"><img src="/images/weixin/zhizhen.png" id="lotteryBtn"></div>
  </div>
  <script type="text/javascript">
     function init_Scroll(){
     var scrollPics = new scrollVertical('listbox','userlist');
         scrollPics.speed = 10; /* 调节speed值可以改变滚动间隙 */
         scrollPics.isPause = true;
 
     var timer_start = setTimeout(function(){clearTimeout(timer_start);scrollPics.isPause = false;},1000);

     Event.addEvent(scrollPics.scrollArea,"mouseover",function(){scrollPics.isPause = true;});
     Event.addEvent(scrollPics.scrollArea,"mouseout",function(){scrollPics.isPause = false;});
     }
      Event.addEvent(window,'load',init_Scroll);
     </script>
   <!--按钮-->
    <!--关注公众号-->
		  <div class="tanchubox" id="woyaochanyudiv" style="display:none;">
		    <div class="tanchu">
		        <div class="title"><span><a href="javascript:gbwoyaochanyu();">×</a></span>关注飞券</div>
		        <div class="tishitext">
		          <p><img class="img11" src="/images/2wm.jpg" ></p>
		          <p>
		            	长按二维码，关注公众号后<br>
						点击美食折扣中的抽奖拼团即可参与</p>
		        </div>
		    </div>
		  </div>
  <!--end-->
  <div class="buttonbox">
  	<ul>
    	<li class="mtop"><a href="javascript:choujiangguize()"><input class="img90" name="" type="image" src="/images/weixin/guize.png"></a></li>
      <li class="mtop" style="text-align:right;"><a href="javascript:caipingzhanshi();"><input class="img90" name="" type="image" src="/images/weixin/guize1.png"></a></li>
      <li><input class="img90" name="" type="image" src="/images/weixin/buttonbg3gray.png"></li>
      <li style="text-align:right;"><input class="img90" name="" type="image" src="/images/weixin/buttonbg4gray.png"></li>
      <div class="clear"></div>
  </ul>
  </div>
  <!--抽奖规则弹出-->
  <div class="fenxiangbox" id="choujiangguizediv" style="display:none;">
      <div class="fenxiangbg"></div>
      <div class="fenxiangtanchu">
          <div class="tanchutitlebg">【转啥吃啥】活动说明<span class=""><input class="guanbibutton" name="" type="button" onclick="gbchoujiangguize()" value="×"></span></div>
          <div class="tanchuinfo">
           <p>
              1、用户可在每家商户分别发起一次抽奖团；
  </p>
            <p>2、自己可以有一次抽取机会，分享到朋友圈，发动好友助力召唤想要的美食，每个好友均可帮你抽取一次；
            </p>
            <p>3、助力的任意一位好友抽中奖品，您即可兑换试吃券，每个商户只能选取一个等次兑换，券在兑换后7日内使用有效；
              </p>
            <p>4、可同时保留多张券，从【飞券公众号--我的优惠券】进入查看，每次每桌消费限使用一张；</p>
            <p>5、试吃券用于换取商户提供的指定商品，在商户处出示券并由商户扫码完成费用抵扣； </p>
            <p>6、每张试吃券有不同的人数要求与使用说明，详见试吃券； </p>
            <p>7、部分活动不参与到商家自身活动中，详情请咨询商家确认. </p>
          </div>
      </div>
  </div>
  <!--本店奖品弹出-->
  <div class="fenxiangbox" id="caipingzhanshidiv" style="display:none;">
      <div class="fenxiangbg"></div>
      <div class="fenxiangtanchu">
          <div class="tanchutitlebg">商户活动菜品展示<span class=""><input class="guanbibutton" name="" onclick="gbcaipingzhanshi();" type="button" value="×"></span></div>
          <div class="tanchuinfo">
            <div class="caipin">
            	<span class="bgbkleft"></span>
                <div class="caipinimg">
                	<center><img src="${ld1.dishImage }"></center>
                	<p>${ld1.dishName }</p>
                </div>
                <span class="bgbkimg"><img src="/images/weixin/jiang-te.jpg"></span>
                <span class="bgbkright"></span>
                <div class="clear"></div>
            </div>
            <div class="caipin">
            	<span class="bgbkleft"></span>
                <div class="caipinimg">
                	<center><img src="${ld2.dishImage }"></center>
                	<p>${ld2.dishName }</p>
                </div>
                <span class="bgbkimg"><img src="/images/weixin/jiang-yi.jpg"></span>
                <span class="bgbkright"></span>
                <div class="clear"></div>
            </div>
            <div class="caipin">
            	<ul>
                	<li class="jiangerbg">
                        <center><img src="${ld3.dishImage }"></center>                	
                        <center><img src="/images/weixin/jiang-er.jpg"></center>
                        <p>二等奖</p>
                        <p>${ld3.dishName }</p>
                        <div class="clear"></div>
                    </li>
                    <li class="jiangerbg">
                        <center><img src="${ld4.dishImage }"></center>                	
                        <center><img src="/images/weixin/jiang-er.jpg"></center>
                        <p>三等奖</p>
                        <p>${ld4.dishName }</p>
                        <div class="clear"></div>
                    </li>
                    <div class="clear"></div>
                </ul>
            </div>
          </div>
      </div>
  </div>
  <!--分享朋友圈弹出-->
  <div class="fenxiangbox" id="fenxiangpyqdiv" style="display:none;" onclick="gbfenxiangpyq()">
      <div class="fenxiangbg"></div>
      <div class="fenxiangtanchu">
          <div class="index_img"><img src="/images/weixin/fenxiangtanchu.png"></div>
      </div>
  </div>
  <!--奖项-->
  <div class="jiangdh">
  	<div class="duihuanbox" style="padding-top:12px;">
    	<div class="leftbox">
        	<div class="bg">
    			<h1>三等奖</h1>
            	<p id="sandengjiangp"></p>
            </div>
    	</div>
        <div class="centerbox">
    		<ul id="sandengjiangul">
            </ul>
    	</div>
		<div class="rightbox">
    		<input  id="sandengjiangbut" class="buttonimg" name="" type="image" src="/images/weixin/weiduihuan.png"><!--已兑换状态按钮-->
    	</div>
        <div class="clear"></div>
    </div>
    <div class="duihuanbox">
    	<div class="leftbox">
        	<div class="bg">
    			<h1>二等奖</h1>
            	<p id="erdengjiangp"></p>
            </div>
    	</div>
        <div class="centerbox">
    		<ul id="erdengjiangul">
            </ul>
    	</div>
		<div class="rightbox">
    		<input class="buttonimg"  id="erdengjiangbut" name=""  type="image" src="/images/weixin/weiduihuan.png"><!--不能兑换状态按钮-->
    	</div>
        <div class="clear"></div>
    </div>
    <div class="duihuanbox">
    	<div class="leftbox">
        	<div class="bg">
    			<h1>一等奖</h1>
            	<p id="yidengjiangp"></p>
            </div>
    	</div>
        <div class="centerbox">
    		<ul id="yidengjiangul">
            </ul>
    	</div>
		<div class="rightbox">
    		<input class="buttonimg"  id="yidengjiangbut" name="" type="image" src="/images/weixin/weiduihuan.png"><!--不能兑换状态按钮-->
    	</div>
        <div class="clear"></div>
    </div>
    <div class="duihuanbox">
    	<div class="leftbox">
        	<div class="bg">
    			<h1>特等奖</h1>
            	<p id="tedengjiangp"></p>
            </div>
    	</div>
        <div class="centerbox">
    		<ul id="tedengjiangul">
            	
            </ul>
    	</div>
		<div class="rightbox">
    		<input class="buttonimg"  id="tedengjiangbut" name="" type="image" src="/images/weixin/weiduihuan.png"><!--兑换状态按钮-->
    	</div>
        <div class="clear"></div>
    </div>
  </div>  
</div>
<script>
$(function(){
	var timeOut = function(){  //超时函数
		$("#lotteryBtn").rotate({
			angle:0, 
			duration: 10000, 
			animateTo: 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
			callback:function(){
				alert('亲！没有中奖哦！！')
			}
		}); 
	}; 
	var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度
		$('#lotteryBtn').stopRotate();
		$("#lotteryBtn").rotate({
			angle:0, 
			duration: 5000, 
			animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
			callback:function(){
				alert(text)
				$(".dst").fadeIn();
				window.location.reload();
				//setTimeout("top.location.href='http://fuwu.taobao.com/ser/assembleParam.htm?spm=a1z2j.6989585.0.0.o9dyAn&tracelog=qianniu&subParams=itemCode:FW_GOODS-1884032-1,cycleNum:7,cycleUnit:3,freeTry:1;'",5000);
			}
		}); 
	};
	
	$("#lotteryBtn").rotate({ 
		   bind: 
			 { 
				click: function(){
					var isguanzhu = $("#isguanzhu").val();
					var already = $("#already").val();
					if(isguanzhu == 0){
						woyaochanyu();
					}else{
						if(already == 1){
							alert("你已经抽过奖了,快去分享找朋友帮忙吧!");
						}else{
							var iscli = $("#iscli").val();
							if(iscli == 0){
								$("#iscli").val(1);
								$.ajax({
									url: "/lottery/choujiang.do",
									type: "POST",
									data: {'openid':"${ul.openId}","userLotteryId":"${ul.id}"},
									success: function(data){
										if(data==1){
											rotateFunc(1,0,'天啊~颜值爆表！恭喜您帮${ul.nickName}抽中了特等奖！');
										}
										if(data==2){
											rotateFunc(1,90,'哇~人品大爆发！恭喜您帮${ul.nickName}抽到了一等奖！');
										}
										if(data==3){
											rotateFunc(2,270,'哎哟~手气不错！恭喜您帮${ul.nickName}抽到了二等奖！');
										}
										if(data==4){
											rotateFunc(3,180,'Duang~恭喜您帮${ul.nickName}抽到了三等奖！');
										}
										
									},
									error:function (XMLHttpRequest, textStatus, errorThrown) 
							    	{ 
							    	}
									});
							}else{
								
							}
						}
					}
					}
			 } 
		   
		});
	
})
</script>
</body>
</html>