function delet(proid,o){ 
	if(confirm("确定删除吗?")){
		var urlval = '/managermall/account/order/deleteCollet.do?prodId='+proid;
		if (0==parseInt(o)) {
			urlval = '/managermall/account/order/deleteCollet.do?storeId='+proid;
		}
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    /*	data:param,//传给后台的数据，多个参数用&连接
	*/	    	success:function(msg){
		    		if (msg=='addError'){
		    		}
		    		if (msg=='userNull'){
		    			alert("请先登录");
		    			window.location.href = "/account/login.do";
		    		}
					if (msg=='error'){
						
		    		}
					if (msg=='scuess'){
						window.location.href = window.location.href;
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