<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="referrer" content="never">
<title>图文预览</title>
<link type="text/css" rel="stylesheet" href="/css/seller/style.css" />
<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function show1(){
		$("#show1").hide();
		$("#show2").show();
		var $a = $(".main_body");
		var $b = $(".image_text");
		$a.removeClass("main_body").addClass("image_text");
		$b.removeClass("image_text").addClass("main_body");
	}
	function show2(){
		$("#show1").show();
		$("#show2").hide();
		var $a = $(".main_body");
		var $b = $(".image_text");
		$a.removeClass("main_body").addClass("image_text");
		$b.removeClass("image_text").addClass("main_body");
	}
	function fasongweixinyl(){
		var content = '${content }';
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/wxmessage/previewByWechart.do',
			data : {'title':'${title }','content':content,'fileName':'${fileName}','username':$("#wxusername").val()},
		    success : function(data) {
				if(data == "SUCCESS"){
					document.getElementById('fssj').style.display='none';
					alert("发送成功");
				}else
					alert("发送失败");
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
	}
</script>
</head>
<body>
	<div class="center">
<dl>
	<dd>
    	<a class="main_body" href="javascript:show1();">图文消息</a>
    </dd>
   	<dd>	
        <a class="image_text" href="javascript:show2();">消息正文</a> 
    </dd>
</dl>

	<!--发送手机预览弹窗-->
	<div id="fssj" class="white_content" style="">
		<input type="hidden" id="numId"/>
		<input type="hidden" id="sellerId">
		<p class="close03">发送预览</p>
		<div class="nr02">
          <div class="nr-box">
			<h1>关注公众号后，才能接收图文消息预览</h1>
            <p><input placeholder="请输入微信号" value="" id="wxusername"></p>
            <p>预览功能仅用于公众号查看文章效果，不适用于公众传播，预览
链接会在短期内失效。</p>
		  </div>
		</div>
		<div class="kqanniu01">
			<a href="javascript:void(0)" style="background-color: #ccc;"
				onclick="document.getElementById('fssj').style.display='none';">取消</a>
			<a href="javascript:fasongweixinyl()" style="background-color: #46b54a;"
				onclick="">确认</a>
		</div>
	</div>

<div class="close">
<dl>
	<dd>
    	<a class="send-phone" href="javascript:void(0)" onclick="document.getElementById('fssj').style.display='block';">发送手机预览</a>
    </dd>
   	<dd>	
        <a class="close-tc" href="javascript:window.close()">关闭</a> 
    </dd>
</dl>

</div>

<div class="phone_content" id="show1" style="display: none;">
	<div class="top_nav">
    	飞券
    </div>
    <div class="content">
    	<ul>
        	<li class="title">${title }</li>
            <li class="time">2016-07-01  <span>飞券</span></li>
            <li><img src="${fileName }" width="243" height=""></li>
            <li class="text" style="word-wrap:break-word;word-break:break-all;">${content }</li>
            
        </ul>
</div>
</div>
	<div class="phone_content" id="show2">
		<div class="top_nav">
	    	飞券
	    </div>
	    <div class="content1">
	    	<ul>
	        	<li class="title">${title }</li>
	            <li class="time">07月01日</li>
	            <li><img src="${fileName }" width="243"></li>
	            <li class="line"></li>
	            <li class="next"><a href="javascript:show2();">阅读原文</a></li>
	            
	        </ul>
	    </div>
	</div>
</div>




</body>
</html>