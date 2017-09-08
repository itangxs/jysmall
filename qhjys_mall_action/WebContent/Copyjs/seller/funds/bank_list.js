
function delet(id){
	if(confirm("确定要删除吗?")){
		var param = 'id='+id;
		var urlval = '/managermall/seller/funds/deleteBank.do';
	   $.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	data:param,//传给后台的数据，多个参数用&连接
	    	success:function(msg){
	    		if (msg=='success'){
	    			alert("删除成功");
	    			window.location.href="/managermall/seller/funds/bank.do";
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
	}
	
}

function set(id){
	if(confirm("确定要设为默认吗?")){
		var param = 'id='+id;
		var urlval = '/managermall/seller/funds/setBank.do';
	   $.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	data:param,//传给后台的数据，多个参数用&连接
	    	success:function(msg){
	    		if (msg=='success'){
	    			alert("设为默认成功");
	    			window.location.href="/managermall/seller/funds/bank.do";
	    		}
				if (msg=='error'){
					alert("设为默认失败");
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    	}
	    });
	}
	
}
