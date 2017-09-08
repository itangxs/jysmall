function del(id){
	
	if(confirm("确定要删除吗?.")){
		var urlval ="/managermall/systemmall/malluser/delUserLv.do";
			var parma ="id="+id;
   		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:parma,//传给后台的数据，多个参数用&连接
		    	success:function(msg){
		    		if (msg=='success'){
		    			alert("删除成功");
		    			window.location.href=window.location.href;
		    		}else{
		    			alert("删除失败");
		    			window.location.href=window.location.href;
		    		}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(){
		    	//ajax提交失败的处理函数！
		    		alert("服务器忙");
		    	}
		    });
	}
	
}


function upda(id,status){
	
	if(confirm("确定要设置默认吗?.")){
		var urlval ="/managermall/systemmall/malluser/updateUserLvStatus.do";
			var parma ="id="+id+"&status="+status;
   		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:parma,//传给后台的数据，多个参数用&连接
		    	success:function(msg){
		    		if (msg=='success'){
		    			alert("设置默认成功");
		    			window.location.href=window.location.href;
		    		}else{
		    			alert("设置默认失败");
		    			window.location.href=window.location.href;
		    		}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(){
		    	//ajax提交失败的处理函数！
		    		alert("服务器忙!");
		    	}
		    });
	}
	
}