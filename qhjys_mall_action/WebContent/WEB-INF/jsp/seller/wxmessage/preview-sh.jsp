<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="referrer" content="never">
<title>图文预览</title>
<link type="text/css" rel="stylesheet" href="/css/seller/style1.css" />
<script type="text/javascript" src="/js/jquery-1.11.2.min.js"></script>
<script>
	
function closetanchu(){
	$("#sjsh",parent.document).remove();
}

function shenhe(id,status){
	var remark = $("#remark").val();
	$.ajax({
	    	type:'post',//可选get
	    	url:'/managermall/systemmall/apply/checkMessage.do',//这里是接收数据的后台程序
	    	data:{"id":id,"status":status,"remark":remark},//传给后台的数据，多个参数用&连接
	    	success:function(msg){
	    		if (msg=="SUCCESS"){
	    			alert("审核成功");
	    			window.parent.location.reload();
	    		}else{
	    			alert("审核失败");
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    		alert("服务器忙");
	    	}
	    });
	
}


</script>
</head>
<body id="fade">
	<div class="center">
    <input type="hidden" value="${id }" id="messageId">
    <div class="right-part">
<dl>
	<dd>
    	<a class="main_body" href="javascript:shenhe('${id }',1)">审核通过</a>
    </dd>
</dl>
<div class="close" style="margin-top:226px">



<dl>
	<dd>备注
    	<textarea id="remark"></textarea>
    </dd>
   	<dd>	
        <a class="check-tg" style="margin:10px 0" href="javascript:shenhe('${id }',2)">审核不通过</a> 
    </dd>
    <dd>	
        <a class="close-tc" href="javascript:closetanchu()">关闭</a> 
    </dd>
</dl>

</div>
</div>


<!--图显示-->

<div class="phone_content" id="show1" style="display:block;">
	<div class="top_nav">
    	飞券
    </div>
    <div class="content" style="height:438px;overflow-y:scroll; overflow-x:hidden;">
    	<ul>
            <li><img src="${fileName }" width="243" height=""></li>           
        </ul>
</div>
</div>

    
    <!--文显示-->

<div class="phone_content" id="show2" style="display: block;">
	<div class="top_nav">
    	飞券
    </div>
    <div class="content" style="height:438px;overflow-y:scroll; overflow-x:hidden;">
    	<ul>
            <li class="text" style="word-wrap:break-word;word-break:break-all;">${content }</li>           
        </ul>
</div>
</div>

    <!--图文显示-->

<div class="phone_content" id="show3" style="display:block;">
	<div class="top_nav">
    	飞券
    </div>
    <div class="content" style="height:438px;overflow-y:scroll; overflow-x:hidden;">
    	<ul>
            <li><img src="${fileName }" width="243" height=""></li>
            <li class="text" style="word-wrap:break-word;word-break:break-all;">${content }</li>
            
        </ul>
</div>
</div>

    
    
    
    
    
</div>




</body>
</html>