<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="/js/jquery-1.11.2.min.js" type="text/javascript"></script>

<title>飞券网（jysmall.com）-比团购更划算的积分通兑、优惠消费商城平台。做任务！赢积分！换优惠!</title>
</head>
<body> 
	<%-- object:${object}=====================
	user_id:${user_id}=====================
	task_id:${task_id}===================== 
	${sysTask.taskContent} 
	--%>
	请使用图片识别关注该公众号即可完成任务
	${sysTask.taskContent} 
</body>

<script type="text/javascript">
/* 
window.onload = function(){
	alert("加载成功"); 
	var param =
	var urlval = '/wenxin_task_save.do';
	 alert(param);
	alert("urlval:"+urlval); 
   $.ajax({
    	type:'post',//可选get
    	url:urlval,//这里是接收数据的后台程序
    	data:param,//传给后台的数据，多个参数用&连接
    	dataType:'text',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
    	success:function(msg){
    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
    	},
    	error:function(){
    	}
    });
} */
</script>
</html>