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
<title>抽取${fqStore.storeName }兑换券</title>
<link href="/css/abcard/A-quan.css" type="text/css" rel="stylesheet" />
<script src="/js/jquery-1.7.2.min.js"></script>
<script src="/js/abcard/jQueryRotate.js"></script>
<script src="/js/abcard/a_raffle.js"></script>
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
	    link: 'http://www.jysmall.com/user/fqcardactivity/a_share.do?recordId=${recordId}', // 分享链接
	    imgUrl: '${user.portrait}', // 分享图标
	    success: function () { 
	    },
	    cancel: function () { 
	        // 用户取消分享后执行的回调函数
	    }
	});
	wx.onMenuShareAppMessage({
		 title: '我颜值不够，能不能拿下大奖就看你的了！', // 分享标题
	    desc: '听说颜值高的能拿特等奖哦！', // 分享描述
	    link: 'http://www.jysmall.com/user/fqcardactivity/a_share.do?recordId=${recordId}', // 分享链接
	    imgUrl: '${user.portrait}', // 分享图标
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

<body class="a_zhuanpanbg">
<div class="wrapper">
  <!--转盘-->
  <div class="ly-plate">
      <div class="rotate-bg"></div>
      <input id="recordId" name="recordId" type="hidden" value="${recordId}">
      <input id="userId" name="userId" type="hidden" value="${user.id}">
      <input id="acardId" name="acardId" type="hidden" value="${acardId}">
      <input id="storeId" name="storeId" type="hidden" value="${storeId}">
      <div class="rotate-jp">
      	<ul>
        	<li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg1"></div></li>
            <li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg2"></div></li>
            <li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg3"></div></li>
            <li><div class="picbox"><img src="/images/abcard/a-pic.png" id="jpimg4"></div></li>
        </ul>
      </div>
    <div class="lottery-star"><a href="javascript:void(0)" onclick=""><img src="/images/abcard/a-zhizhen.png" id="lotteryBtn"></a></div>
  </div>
<!--请朋友帮忙按钮-->
<div class="help_button"> <a href="javascript:void(0)" onclick="helpchou(0)"><img src="/images/abcard/a-friendhelp.png"></a>
</div>
<!--中奖弹出层-->
<div id="zhongjiang" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('zhongjiang').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-zhongjiang.png" ></a> 
    <div class="tanchu_content">  
    	<div class="zhongjiangbox">
        	<p>恭喜你运气爆表，抽到了一份</p>
            <h1><input id="zhongjiangName" name="zhongjiangName" readonly="readonly"/></h1>
            <p><img class="img50" src="" id="zhongjiangImage" ></p>
            <input id="zjprizeId" name="zjprizeId" type="hidden">
            <div><input onclick="zjduihuan()" class="buttonred100" type="button" value="马上兑换"></div>
            <h2>提示：<br>如果对抽到的奖品不满意，可以转发邀请朋友帮忙抽！</h2>
            <div class="buttonbkbox"><input onclick="helpchou(1)" class="button60k" type="button" value="请朋友帮忙">
            <input onclick="document.getElementById('zhongjiang').style.display='none';document.getElementById('fade').style.display='none'" class="button40k" type="button" value="我知道了"></div>
        </div>
    </div>
  </div>
</div>

<!--已经抽过弹出层-->
<div id="yichouguo" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-zhongjiang.png" ></a> 
    <div class="tanchu_content">
    	<div class="zhongjiangbox">
            <h2><span class="red">温馨提示：</span><br>您已经抽过奖了，如果对抽到的奖品不满意，可以转发邀请朋友帮忙抽！</h2>
          <div class="buttonbkbox"><input onclick="helpchou(2)" class="button60k" name="" type="button" value="请朋友帮忙">
          <input onclick="document.getElementById('yichouguo').style.display='none';document.getElementById('fade').style.display='none'" class="button40k" name="" type="button" value="我知道了"></div>
        </div>
    </div>
  </div>
</div>
<!--未关注弹出层-->
<div id="weiguanzhu" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('weiguanzhu').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-weiguanzhu.png" ></a> 
    <div class="tanchu_content">
    	<div class="weiguanzhubox">
        	<div class="nr"><p>兑换券已收入到您的兑换券啦！<br>请进入飞券公众号查看噢！</p>
            <p><img src="/images/abcard/a-ewm.jpg" ></p>
            <p>长按识别二维码</p></div>
            <h2>进入公众号后点击【我的兑换券】查看您的所有券</h2>
            <div class="buttonbkbox">
            	<input class="button40k" type="button" name="Submit" onclick="document.getElementById('weiguanzhu').style.display='none';document.getElementById('fade').style.display='none'" value="我知道了">
            </div>
        </div>      
    </div>
  </div>
</div>
<!--请朋友帮忙弹出层-->
<div id="fenxiang" class="white_content">
	<a href="javascript:void(0)" onclick="document.getElementById('fenxiang').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-fenxiang.png" ></a>    
</div>
<!--兑换弹出层-->
<div id="duihuan" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('duihuan').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-duihuantop.png" ></a> 
    <div class="tanchu_content">
        <div class="tc_dhq">
            <div class="left">
            	<span><img class="" src="" id="duihuanImage" ></span>
            </div>
            <div class="right">
            	<div class="title"><span><input id="duihuanName" readonly="readonly"/></span>
            	<input id="dhprizeId" name="dhprizeId" type="hidden">
            	<i><img class="" src="/images/abcard/a-dhj.jpg" ></i></div>
                <h2>兑换后15天内使用有效</h2>
            </div>
            <div class="clear"></div>
        </div>
        <div><input onclick="shourukabao()" class="buttonred100" name="" type="button" value="收入卡包"></div>        
        <a href="javascript:;" onclick="document.getElementById('duihuan').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-kabaozhiyin.jpg" ></a>
    </div>
  </div>
</div>
<!--收入卡包弹出层-->
<div id="mashangduihuan" class="white_content">
  <div class="tanchubox">
  	<a href="javascript:void(0)"  onclick="document.getElementById('mashangduihuan').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-duihuantop.png" ></a> 
    <div class="tanchu_content">
        <div class="tc_dhq">
            <div class="left">
            	<span><img class="" src="" id="kabaoImage"></span>
            </div>
            <div class="right">
            	<div class="title"><span><input id="kabaoName" readonly="readonly"/></span>
            	<input id="kbprizeId" name="kbprizeId" type="hidden">
            	<i><img class="" src="/images/abcard/a-dhj.jpg" ></i></div>
                <h2>兑换后15天内使用有效</h2>
            </div>
            <div class="clear"></div>
        </div>
        <div style="font-size:0.9625em; padding:10px;">兑换券已收入你的兑换券包！</div>        
        <a href="javascript:;" onclick="document.getElementById('mashangduihuan').style.display='none';document.getElementById('fade').style.display='none'"><img class="img100" src="/images/abcard/a-kabaozhiyin.jpg" ></a>
    </div>
  </div>
</div>
<!--弹出幕布-->
<div id="fade" class="black_overlay"></div>
  <!--抽奖奖项-->
  <div class="jiangpinlist a_rafflebg">
  
  <c:forEach items="${lotteryInfoVos }" var="lotteryInfo" varStatus="status">
  <div class="duihuanbox">
    	<div class="leftbox">
        	<div class="bg">
            	<p>已有${fn:length(lotteryInfo.infos)}个好友<br>帮你抽到</p>
                <h1>${lotteryInfo.prizeName }</h1>
            </div>
    	</div>
        <div class="centerbox">
    		<ul>
    			<c:forEach items="${lotteryInfo.infos }" var="uinfo" varStatus="uinstatus">
    				<li><img src="${uinfo.portrait }"></li>
    			</c:forEach>
            </ul>
    	</div>
		<div class="rightbox">
        	<p><a href="javascript:void(0)" onclick="listduihuan()"><img src="/images/abcard/a-duihuan.png"></a></p>
    	</div>
        <div class="clear"></div>
    </div>
  </c:forEach>
    <!-- <div class="duihuanbox">
    	<div class="leftbox">
        	<div class="bg">
            	<p>已有10个好友<br>帮你抽到</p>
                <h1>[番茄炒蛋]</h1>
            </div>
    	</div>
        <div class="centerbox">
    		<ul>
              <li><img src="/images/abcard/a-photo.jpg"></li>
              <li><img src="/images/abcard/a-photo.jpg"></li>
              <li><img src="/images/abcard/a-photo.jpg"></li>
              <li><img src="/images/abcard/a-photo.jpg"></li>
              <li><img src="/images/abcard/a-photo.jpg"></li>
              <li><img src="/images/abcard/a-photo.jpg"></li>
    		</ul>
    </div>
		<div class="rightbox">
    		<p><a href="javascript:void(0)" onclick="document.getElementById('duihuan').style.display='block'; document.getElementById('fade').style.display='block'"><img src="/images/abcard/a-duihuan.png"></a></p>
    	</div>
        <div class="clear"></div>        
    </div> -->
    <!--飞券本店兑换券说明-->
    <div class="rulesbox">
        <h1>飞券本店兑换券说明<span><img src="/images/abcard/a-ico1.png"></span></h1>
        <div class="rules_text rules_text_a" style="display:none;">
        	1.可邀请好友帮忙抽奖；<br>
            2.被邀请抽奖的好友也可以发起抽奖活动；<br>
            3.每种兑换券最多可兑换1次；<br>
            4.兑换券有效期限为15天，限下次消费时使用；<br>
            5.所有券可在「菜单」-「我的兑换券」中查看；<br>
            6.本活动最终解释权归飞券网所有。
        </div>
    </div> 
  </div> 
</div>

</body>
</html>
