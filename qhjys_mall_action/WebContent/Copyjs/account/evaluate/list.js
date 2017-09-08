function delet(id){ 
		if(confirm("确定删除评论吗?")){
			var patr = "id"+id;
			var urlval = '/managermall/account/evaluate/delete.do';
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:patr,//传给后台的数据，多个参数用&连接
		    	dataType:'html',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
		    	success:function(msg){
		    		if (msg=='success'){
		    			alert("删除评论成功");
		    			window.location.href="/managermall/account/evaluate/list.do";
		    		}else if(msg=='timeout'){
		    			window.location.href= "/account/login.do";
		    		}else {
						alert("删除评论失败");
		    		}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function (XMLHttpRequest, textStatus, errorThrown) 
		    	{ 
		    		if (4==XMLHttpRequest.readyState){
		    			return null;
		    		}else{
		    			alert("服务器忙");
		    		}
		    	} 
		    });
		}else{
			//alert("删除失败");
		}
	}