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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<title>${store.name }</title>
<link href="/css/getkaquan.css" type="text/css" rel="stylesheet" />
<script src="/js/jquery.min.js"></script>
<script src="/js/jQueryRotate.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>
<input type="hidden" name="userLotteryId" value="${userLotteryId }">
  <input type="hidden" name="statusCfg" value="${statusCfg }">
  <input type="hidden" name="sellerId" value="${sellerId }">
  <input type="hidden" name="orderId" value="${orderId}">
  <input type="hidden" name="isguanzhu" value="${isguanzhu }">
  <input type="hidden" name="forOpenId" value="${forOpenId }">
    <input type="hidden" name="id">
    
<body class="a_zhuanpanbg">
<div class="wrapper">
  <!--转盘-->
  <div class="ly-plate">
      <div class="rotate-bg"></div>
      <div class="rotate-jp">
      	<ul>
             <c:forEach items="${list }" var="obj" varStatus="status">
      	   		 <li>
					 <div class="picbox"> 
						 <c:if test="${obj.couponCfg == 2}">
						 <div class="textkq"><h5>折扣券<br>${obj.name }</h5></div>
						   <img src="/images/get-pic3.png" id="jpimg3">
						</c:if>
      	   				 <c:if test="${obj.couponCfg == 1}">
      	   				  <div class="textkq"><h5>代金券<br>${obj.name }</h5></div>
						   <img src="/images/get-pic2.png" id="jpimg2">
						</c:if>
						 <c:if test="${obj.couponCfg == 0}">
						  <div class="textkq"><h5>礼品券<br>${obj.name }</h5></div>
						   <img src="/images/get-pic1.png" id="jpimg1">
						</c:if>
      	   			 </div>
      	   		 </li>
      	   </c:forEach>
        </ul>
      </div>
    <div class="lottery-star"><a href="javascript:void(0)"><img src="/images/a-zhizhen.png" id="lotteryBtn"></a></div>
  </div>
<!--请朋友帮忙按钮-->

 <c:if test="${isShare==0}">
 <div class="help_button"> <a href="javascript:void(0)" onclick="document.getElementById('fenxiang').style.display='block'; document.getElementById('fade').style.display='block'"><img src="/images/a-friendhelp.png"></a></div></c:if>
   <c:if test="${isShare==1}">
   <div class="help_button"><a href="/wxMall/jump/getCardCoupon.do?flag=${statusCfg }&sellerId=${sellerId}&orderId=${orderId}"><img src="/images/a-share.png"></a></div>
	</c:if>
<!--中奖弹出层-->
<div id="zhongjiang" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:window.location.reload()"  onclick="window.location.reload()"><img class="img100" src="/images/a-zhongjiang.png" ></a> 
    <div class="tanchu_content">  
    	<div class="zhongjiangbox">
    		<c:if test="${isShare==0}">
        	<p>恭喜你颜值爆表，抽到了一张</p>
            <h1></h1>
            <div><input onclick="doExchange('zhongjiang')" class="buttonred100" name="" type="submit" value="收入卡包"></div>
            <h2>提示：还有<span class="ticket-number">3</span>张优惠券哦！！！</h2>
            <div class="buttonbkbox"><input onclick="document.getElementById('zhongjiang').style.display='none';document.getElementById('fenxiang').style.display='block'; document.getElementById('fade').style.display='block'" class="button60k" name="" type="submit" value="请朋友帮忙抽"><input onclick="window.location.reload()" class="button40k" name="" type="submit" value="我知道了"></div>
          </c:if>
          <c:if test="${isShare==1}">
          <p>恭喜你颜值爆表，帮好友抽到了一张</p>
            <h1></h1>
            <p></p>
            <div><input onclick="window.location.href='/wxMall/jump/getCardCoupon.do?flag=${statusCfg }&sellerId=${sellerId}&orderId=${orderId}'" class="buttonred100" name="" type="submit" value="我也要参与"></div>
         </c:if>
        </div>      
    </div>          
  </div>
</div>

<!--已经抽过弹出层-->
<div id="yichouguo" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/a-zhongjiang.png" ></a> 
    <div class="tanchu_content">  
    	<div class="zhongjiangbox">
           <c:if test="${isShare==0}">
            <h2><span class="red">温馨提示：</span><br>您已经抽过奖了，如果对抽到的奖品不满意，可以转发邀请朋友帮忙抽！</h2>
          <div class="buttonbkbox"><input onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fenxiang').style.display='block'; document.getElementById('fade').style.display='block'" class="button60k" name="" type="submit" value="请朋友帮忙"><input onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'" class="button40k" name="" type="submit" value="我知道了"></div>
        </c:if>
        <c:if test="${isShare==1 }">
	               <h2><span class="red">温馨提示：</span><br>您已经帮您的好友抽过奖了，非常感谢您的支持！</h2>
          <div class="buttonbkbox"><input onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'" class="button40k" name="" type="submit" value="我知道了"></div>
            </c:if>
            </div>      
    </div>          
  </div>
</div>
<!--未关注弹出层-->
<div id="weiguanzhu" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="window.location.reload()"><img class="img100" src="/images/a-weiguanzhu.png" ></a> 
    <div class="tanchu_content">  
    	<div class="weiguanzhubox">
        	<div class="nr"><p>已收入到您的优惠券啦！<br>请进入飞券公众号查看噢！</p>
            <p><img src="/images/a-ewm.jpg" ></p>
            <p>长按识别二维码</p></div>
            <h2>进入公众号后点击【我的优惠券】查看您的所有券</h2>
            <div class="buttonbkbox">
            	<input class="button40k" type="button" name="Submit" onclick="window.location.reload()" value="我知道了">
            </div>
        </div>      
    </div>          
  </div>
</div>
<!--请朋友帮忙弹出层-->
<div id="fenxiang" class="white_content">	
	<a href="javascript:void(0)" onclick="document.getElementById('fenxiang').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/a-fenxiang.png" ></a>    
</div>
<!--兑换弹出层-->
<div id="duihuan" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="window.location.reload()"><img class="img100" src="/images/a-duihuantop.png" ></a> 
    <div class="tanchu_content">
        <div class="tc_dhq">
            <div class="left">
            	<span><img id="img" class="" src="" ></span>
            </div>
            <div class="right">
            	<div class="title"><span></span><i><img class="" src="/images/a-dhj.jpg" ></i></div>
                <h2>中奖后15天内使用有效</h2>
            </div>
            <div class="clear"></div>
        </div>
        <div><input onclick="document.getElementById('duihuan').style.display='none';document.getElementById('fade').style.display='none'" class="buttonred100" name="" type="submit" value="收入卡包"></div>        
        <a href="javascript:;" onclick="window.location.reload()'"><img class="img100" src="/images/a-kabaozhiyin.jpg" ></a>
    </div>          
  </div>
</div>
<!--马上兑换弹出层-->
<div id="mashangduihuan" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="window.location.reload()"><img class="img100" src="/images/a-duihuantop.png" ></a>
    <div class="tanchu_content">
        <div class="tc_dhq">
            <div class="left">
            	<span><img class="" src="/images/dui-pic3.png" ></span>
            </div>
            <div class="right">
            	<div class="title"><span>全场9.5折</span><i><img class="" src="/images/a-dhj.jpg" ></i></div>
                <h2>中奖后15天内使用有效</h2>
            </div>
            <div class="clear"></div>
        </div>
        <div style="font-size:0.9625em; padding:10px;">优惠券已收入你的优惠券包！</div>
        <a href="javascript:;" onclick="window.location.reload()"><img class="img100" src="/images/a-kabaozhiyin.jpg" ></a>
    </div>          
  </div>
</div>
<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
  <!--抽奖奖项-->
<div class="jiangpinlist a_rafflebg">
 
  	<c:forEach var="m" items="${friendMap }">
	    <c:set var="couponCfg" value=""></c:set>
		<div class="duihuanbox">
	    	<div class="leftbox">
	        	<div class="bg">    			
	            	<p>已有${fn:length(m.value)}个好友<br>帮
			<c:if test="${isShare==0}">你</c:if>
			<c:if test="${isShare==1}">他</c:if>
					抽到</p>
	                <h1>${m.key}</h1>
	            </div>
	    	</div>
	    	<c:if test="${isShare==0}">
	        <div class="centerbox">
	    		<ul>
	    		    <c:forEach var="friend" items="${m.value }" varStatus="status">
	    		        <c:if test="${status.count<=6}">
	    		             <c:choose>
	    		               <c:when test="${friend['portrait']!=null}">
	    		              		 <li><img src="${friend['portrait']}"></li>
	    		               </c:when>
	    		               <c:otherwise>
	    		                    <li><img src="/images/weixin/cardcoupon/get-no.png"></li>
	    		               </c:otherwise>
	    		             </c:choose>
	    		        </c:if>
	                </c:forEach>
	            </ul>
	    	</div>
			<div class="rightbox">
	        	<p><a href="javascript:void(0)" onclick="duihuan('${m.key}',${m.value[0]['templateId'] },${m.value[0]['couponCfg'] },${m.value[0]['lotteryId'] },'${m.value[0]['openId'] }')"><img src="/images/a-duihuan.png"></a></p>
	    	</div></c:if>
	    	
	    	<c:if test="${isShare==1}">
		    	<div class="friendlist">
		    		<ul>
		    		    <c:forEach var="friend" items="${m.value }" varStatus="status">
		    		        <c:if test="${status.count<=6}">
		    		             <c:choose>
		    		               <c:when test="${friend['portrait']!=null}">
		    		              		 <li><img src="${friend['portrait']}"></li>
		    		               </c:when>
		    		               <c:otherwise>
		    		                    <li><img src="/images/weixin/cardcoupon/get-no.png"></li>
		    		               </c:otherwise>
		    		             </c:choose>
		    		        </c:if>
		                </c:forEach>
		            </ul>
		    	</div>
	    	</c:if>
	        <div class="clear"></div>
	    </div>	
	</c:forEach>
	<div class="active-rule" id="active-rule-bc">
		<div class="active-title"><span class="red-circle"></span><span class="red-line"></span><span class="ticket-introuct">活动规则</span><span class="red-line"></span><span class="red-circle"></span> </div>
		<div class="active-content">
			<span>1.点击转盘指针自由转动，停止时指针所指的物品为本次抽中的奖品；</span><br>
			<span>2.奖品以卡券的形式放入您的卡包，请于公众号“飞券”“我的卡包”中查收；</span><br>
			<span>3.如果未抽中自己想要的奖品，可以“请朋友帮忙”；</span><br>
			<span>4.您可以在“飞券”公众号中，找到“我的抽奖”查看哪些朋友帮您抽到了奖品。</span><br>
			<span>5.优惠券使用的解释权归制作方所有。</span>
		</div>
	</div>
	
</div></div>
<script>
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${appId}', // 必填，公众号的唯一标识
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
		    title: '我运气不够，帮我拿下好吃的就看你的了！', // 分享标题
		    link: 'http://www.jysmall.com/wxMall/jump/getCardCoupon.do?flag=${statusCfg }&sellerId=${sellerId}&orderId=${orderId}&forOpenId=${selfOpenId}', // 分享链接
		    imgUrl: '${fquser.portrait}', // 分享图标
		    success: function () { 
		    	var orderId = $("input[name='orderId']").val();
		    	$.ajax({
			        type: 'GET',
			        url: 'wxMall/updateCardCouponShareCount.do',
			        data: {orderId:orderId},
			        dataType: 'json',
			        success: function(obj){
			        	var orderId = $("input[name='orderId']").val();
				    	$.ajax({
					        type: 'GET',
					        url: 'wxMall/updateCardCouponShareCount.do',
					        data: {orderId:orderId},
					        dataType: 'json',
					        success: function(obj){
					        },
					        error: function(xhr, type){
					        }
					    }); 
			        },
			        error: function(xhr, type){
			        }
			    }); 
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
			title: '我运气不够，帮我拿下好吃的就看你的了！', // 分享标题
		    desc: '听说运气好的能拿好吃的哟！', // 分享描述
		    link: 'http://www.jysmall.com/wxMall/jump/getCardCoupon.do?flag=${statusCfg }&sellerId=${sellerId}&orderId=${orderId}&forOpenId=${selfOpenId}', // 分享链接
		    imgUrl: '${fquser.portrait}', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		    	var orderId = $("input[name='orderId']").val();
		    	$.ajax({
			        type: 'GET',
			        url: 'wxMall/updateCardCouponShareCount.do',
			        data: {orderId:orderId},
			        dataType: 'json',
			        success: function(obj){
			        },
			        error: function(xhr, type){
			        }
			    }); 
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
	});
	$(function(){
		var rotateFunc = function(awards,angle,text,cfg){  //awards:奖项，angle:奖项对应的角度
			$('#lotteryBtn').stopRotate();
			$("#lotteryBtn").rotate({
				angle:0, 
				duration: 5000, 
				animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
				callback:function(){
					if(cfg=="2"){
						$("#zhongjiang h1").html("【折扣劵】"+text);
						$("#zhongjiang p > img").attr("src","/images/get-pic3.png"); 
						
						$("#mashangduihuan .title span").html(text);
						$("#mashangduihuan #img").attr("src","/images/get-pic3.png"); 
						$("#zjtbdiv").html("恭喜您获得折扣劵<p>【"+text+"】</p>")
					}else if(cfg=="1"){
						$("#zhongjiang h1").html("【代金券】"+text);
						$("#zhongjiang p > img").attr("src","/images/get-pic2.png"); 
						
						$("#mashangduihuan .title span").html(text);
						$("#mashangduihuan #img").attr("src","/images/get-pic2.png"); 
						$("#zjtbdiv").html("恭喜您获得代金券<p>【"+text+"】</p>")
					}else if(cfg=="0"){
						$("#zhongjiang h1").html("【礼品券】"+text);
						$("#zhongjiang p > img").attr("src","/images/get-pic1.png"); 
						
						$("#mashangduihuan .title span").html(text);
						$("#mashangduihuan #img").attr("src","/images/get-pic1.png"); 
						$("#zjtbdiv").html("恭喜您获得礼品券<p>【"+text+"】</p>")
					}
				   document.getElementById('zhongjiang').style.display='block';
				   document.getElementById('fade').style.display='block';
				}
			}); 
		};
	     
		$("#lotteryBtn").rotate({ 
		   bind: 
			 { 
				click: function(){
						var userLotteryId = $("input[name='userLotteryId']").val();
						var statusCfg = $("input[name='statusCfg']").val();
						var sellerId = $("input[name='sellerId']").val();
						var orderId = $("input[name='orderId']").val();
						var forOpenId = $("input[name='forOpenId']").val();
						$.ajax({
			                type: 'GET',
			                url: '/wxMall/lottery/getCardCoupon.do',
			                data: {statusCfg:statusCfg,sellerId:sellerId,userLotteryId:userLotteryId,orderId:orderId,forOpenId:forOpenId},
			                dataType: 'json',
			                success: function(obj){
			                		if(obj.flag==0||obj.flag=="0"){
			                			$("input[name='id']").val(obj.data.cardTemplateId);
			                			if(obj.data.index=="0"){
			                				rotateFunc(3,300,obj.data.name,obj.data.couponCfg);
			                			}else if(obj.data.index=="1"){
			                				rotateFunc(0,22,obj.data.name,obj.data.couponCfg);
			                			}else if(obj.data.index=="2"){
			                				rotateFunc(2,247,obj.data.name,obj.data.couponCfg);
			                			}else if(obj.data.index=="3"){
			                				rotateFunc(1,157,obj.data.name,obj.data.couponCfg);
			                			}
			                		}else if(obj.flag==2||obj.flag=="2"){
			                			//已抽过奖
			                			if(obj.data.statusCfg=="0"||obj.data.statusCfg==0){
			                				$("#yichouguo #duihuan_btn").show();
			                			}else{
			                				$("#yichouguo #duihuan_btn").hide();
			                			}
			                			$("input[name='id']").val(obj.data.cardTemplateId);
			                			document.getElementById('yichouguo').style.display='block';
			                			document.getElementById('fade').style.display='block';
			                		}else{
			                			 alert(obj.reason);
			                		}
			                },
			                error: function(xhr, type){
			                	$("#lotteryBtn").rotate({
			            			angle:0, 
			            			duration: 10000, 
			            			animateTo: 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
			            			callback:function(){
			            				alert('亲！没有中奖哦！！')
			            			}
			            		}); 
			                }
			            }); 
				}
			 } 
		   
		});
		
	})
	function doExchange(hiDivId){
		var cardTemplateId = $("input[name='id']").val();
		var statusCfg = $("input[name='statusCfg']").val();
		var userLotteryId = $("input[name='userLotteryId']").val();
		var isguanzhu = $("input[name='isguanzhu']").val();
		$.ajax({
	        type: 'GET',
	        url: '/wxMall/receiveCardCoupon.do',
	        data: {id:cardTemplateId,source:statusCfg,userLotteryId:userLotteryId},
	        dataType: 'json',
	        success: function(obj){
	        		if(obj.flag==0||obj.flag=="0"){
	        			document.getElementById(hiDivId).style.display='none';
	        			
	        			if(isguanzhu=="1"||isguanzhu==1){
	        				document.getElementById('mashangduihuan').style.display='block';
	        			}else{
	        				document.getElementById('weiguanzhu').style.display='block';
	        			}
	        			
	        			document.getElementById('fade').style.display='block';
	        		}else{
	        			 alert(obj.reason);
	        		}
	        },
	        error: function(xhr, type){
	        	alert("请求网络失败!");
	        }
	    }); 
	}

	function duihuan(name,templateId,couponCfg,lotteryId,forOpenId){
		var statusCfg = $("input[name='statusCfg']").val();
		$.ajax({
	        type: 'GET',
	        url: '/wxMall/receiveCardCoupon.do',
	        data: {id:templateId,source:statusCfg,userLotteryId:lotteryId,isFriend:"1",forOpenId:forOpenId},
	        dataType: 'json',
	        success: function(obj){
	        		if(obj.flag==0||obj.flag=="0"){
	        			if(couponCfg=="2"){
	        				$("#duihuan .title span").html(name);
	        				$("#duihuan #img").attr("src","/images/weixin/cardcoupon/get-pic3.png"); 
	        			}else if(couponCfg=="1"){
	        				$("#duihuan .title span").html(name);
	        				$("#duihuan #img").attr("src","/images/weixin/cardcoupon/get-pic2.png"); 
	        			}else if(couponCfg=="0"){
	        				$("#duihuan .title span").html(name);
	        				$("#duihuan #img").attr("src","/images/weixin/cardcoupon/get-pic1.png"); 
	        			}
	        			document.getElementById('duihuan').style.display='block';
	        			document.getElementById('fade').style.display='block';
	        		}else{
	        			 alert(obj.reason);
	        		}
	        },
	        error: function(xhr, type){
	        	alert("请求网络失败!");
	        }
	    })
	}
	</script>
	
</body>
</html>
