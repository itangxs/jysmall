function delet(id){ 
	if(confirm("确定删除吗?")){
		var param = 'id='+id;
		var urlval = '/managermall/account/order/deleteOrderForm.do';
	   $.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	data:param,//传给后台的数据，多个参数用&连接
	    	dataType:'html',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
	    	success:function(msg){
	    		if (msg=='scuess'){
	    			alert("删除成功");
	    			window.location.href="/managermall/account/order/myOrderForm.do";
	    		}
				if (msg=='error'){
					alert("删除失败");
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    	}
	    });
	}else{
		//alert("删除失败");
	}
}

function seletct(o){
	window.location.href="/managermall/account/order/myOrderForm.do?status="+o.value;
}
function submitApply(detailId){
	   $.ajax({
	    	type:'post',//可选get
	    	url:"/managermall/account/refund/virtualProRefund.do",//这里是接收数据的后台程序
	    	data:{"detailId":detailId},//传给后台的数据，多个参数用&连接
	    	dataType:'html',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
	    	success:function(msg){
	    		if (msg=='success'){
	    			alert("退款成功");
	    			window.location.href="/managermall/account/order/myOrderForm.do";
	    		}else{
	    			alert(msg);
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    	}
	    });
}
function delivery(detailId){
	if(confirm("是否确认收货")){
	 $.ajax({
	    	type:'post',//可选get
	    	url:"/managermall/account/refund/delivery.do",//这里是接收数据的后台程序
	    	data:{"detailId":detailId},//传给后台的数据，多个参数用&连接
	    	dataType:'html',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
	    	success:function(msg){
	    		if (msg=='确认收货成功'){
	    			alert(msg);
	    			window.location.href="/managermall/account/order/myOrderForm.do";
	    		}else{
	    			alert(msg);
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    	}
	    });
	}
}
