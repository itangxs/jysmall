<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath().equals("/")?"":request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% 
if(null!=request.getHeader("user-agent")&&((request.getHeader("user-agent").toLowerCase().indexOf("iphone")!=-1 || request.getHeader("user-agent").toLowerCase().indexOf("android")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("ipad")!=-1
|| request.getHeader("user-agent").toLowerCase().indexOf("linux")!=-1))) 
{ 

}else{

	return ;
}  
%> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-itunes-app"/>
<meta name="apple-mobile-web-app-capable" content="yes">

<title>霸王餐活动上线啦，满足你不花钱吃大餐的愿望！</title>
<link href="/css/kanjia.css" type="text/css" rel="stylesheet" />
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
<script src="/js/bargain.js" type="text/javascript"></script>
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
    title: '霸王餐活动上线啦，满足你不花钱吃大餐的愿望！', // 分享标题
    link: 'http://www.jysmall.com/bargain/userBargain.do?openid=${ub.openId}&bargainId=${ub.bargainId}', // 分享链接
    imgUrl: 'http://www.jysmall.com/images/bwc.jpg', // 分享图标
    success: function () { 
        // 用户确认分享后执行的回调函数
    },
    cancel: function () { 
        // 用户取消分享后执行的回调函数
    }
});
wx.onMenuShareAppMessage({
	 title: '霸王餐活动上线啦，满足你不花钱吃大餐的愿望！', // 分享标题
    desc: '', // 分享描述
    link: 'http://www.jysmall.com/bargain/userBargain.do?openid=${ub.openId}&bargainId=${ub.bargainId}', // 分享链接
    imgUrl: 'http://www.jysmall.com/images/bwc.jpg', // 分享图标
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
<!--分享朋友圈弹出-->
<div class="fenxiangbox" style="display:none;" onclick="gbzhaorenbangm()">
	<div class="fenxiangbg"></div>
    <div class="fenxiangtanchu">
        <div class="index_img"><img src="/images/fenxiangtanchu.png"></div>
    </div>
</div>
<!--end-->
<div class="wrapper">
  <div class="index_img"><img src="/images/kanjia01.jpg"></div>
  <div class="index_img"><img src="/images/kanjia02.jpg"></div>
  <div class="probox">
  	<div class="index_img"><img src="/images/kanjia03.jpg"></div>
	<div class="proinfo">
    	<div class="pline">
        	<p>石记麻辣烫</p>
        	<p>地址：福田区车公庙工业路203栋</p>
        </div>        
        <div class="kanjiabox">
        	目前砍价：￥${ub.nowPrice }
        </div>
        <div class="price"><span>低价：￥0</span>原价：￥500.00</div>
        <div class="buttonbox">
        	<ul>
            	<li><a href="javascript:woyaocanyu('${sessionScope.openId }')" class="mright">我要参与</a></li>
                <li><a href="javascript:bangtakanjia('${ub.id }')" class="mleft">帮他砍价</a></li>
            </ul>
        </div>
        
    </div>
    <!--我要砍价弹窗-->
      <div class="tanchubox" style="display:none;">
        <div class="tanchu">
            <div class="title"><span><a href="#">×</a></span></div>
            <div class="tishitext">
              <p><span class="ico-cuo"><img src="/images/ico-dui.png" ></span>恭喜您，已砍掉<span class="red">2元</span></p>
            </div>
        </div>
      </div>
      <!--end-->
      <!--我要参与弹窗-->
      <div class="tanchubox" id="woyaocydiv" style="display:none;">
        <div class="tanchu">
            <div class="title"><span><a href="javascript:guanbiwoyaocy()">×</a></span>我要参与</div>
            <div class="tishitext">
              <p><img class="img11" src="/images/2wm.jpg" ></p>
              <p>
                长按二维码，关注公众号后<br>
    点击最新活动中的砍价活动即可参与</p>
            </div>
        </div>
      </div>
      
      <div class="tanchubox" id="bangtkjdiv" style="display:none;">
        <div class="tanchu">
            <div class="title"><span><a href="javascript:guanbibangtkj()">×</a></span>帮他砍价</div>
            <div class="tishitext">
              <p><img class="img11" src="/images/2wm.jpg" ></p>
              <p>
                长按二维码，关注公众号后<br>
    帮助朋友砍价享优惠</p>
            </div>
        </div>
      </div>
      <!--end-->
      <!--帮他砍价弹窗-->
      <div class="tanchubox" style="display:none;">
        <div class="tanchu">
            <div class="title"><span><a href="#">×</a></span></div>
            <div class="tishitext">
              <p><span class="ico-cuo"><img src="/images/ico-dui.png" ></span>您已经帮您的好友砍了<span class="red">2元</span></p>
            </div>
        </div>
      </div>
      <!--end-->
      <!--兑换验证码弹窗-->
      <div class="tanchubox" style="display:none;">
        <div class="tanchu">
            <div class="title"><span><a href="#">×</a></span></div>
            <div class="tishitext">
               <p><span class="ico-cuo"><img src="/images/ico-dui.png" ></span>恭喜您，兑换成功！</p>
               <p>验证码：<span class="red">${ub.codes }</span></p>
               <p>凭验证码到此商家处即可进行兑换</p>
            </div>
        </div>
      </div>
      <!--end-->
  </div>
  <div class="index_guize">
  	<div id="tagsarea">  
    <ul id="tags">  
      <li class="selectTag"><a onClick="selectTag('tagContent0',this)" href="javascript:void(0)">活动规则</a></li>  
      <li><a onClick="selectTag('tagContent1',this)" href="javascript:void(0)">查看进度</a></li>  
    </ul>  
    <div id="tagContent">  
    <div class="tagContent selectTag" id="tagContent0">
        <div class="index_guize_nr" >
          <p>1、帮忙砍价的人员，每个微信号只可参与一次；<br>
            2、每桌只可使用1张优惠券；<br>
            3、当价格砍到0时，才可进行兑换；<br>
            4、优惠券码的使用时间不限（或限制时间）；<br>
          5、本活动最终解释权归飞券网所有。</p>
        </div>
    </div>  
    <div class="tagContent" id="tagContent1">
        <div class="kanlist" style="display:;">
            <ul>
            <c:forEach items="${bis }" var="bi">
             	<li><span class="right">帮您砍了${bi.money }元</span><img src="${bi.portrait }">${bi.nickName }</li>
            </c:forEach>
            </ul>
        </div>
    </div>    
    </div>  
</div>    
     
  </div>  
</div>
<script type=text/javascript>   
function selectTag(showContent,selfObj){   
    // 操作标签   
    var tag = document.getElementById("tags").getElementsByTagName("li");   
    var taglength = tag.length;   
    for(i=0; i<taglength; i++){   
        tag[i].className = "";   
    }   
    selfObj.parentNode.className = "selectTag";   
    // 操作内容   
    for(i=0; j=document.getElementById("tagContent"+i); i++){   
        j.style.display = "none";   
    }   
    document.getElementById(showContent).style.display = "block";   
}   
</script>  
</body>
</html>
