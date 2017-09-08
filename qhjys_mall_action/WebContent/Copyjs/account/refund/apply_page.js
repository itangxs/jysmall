function submit1(id){ 
	var token = $("#token").val();
	var detailId = $("#detailId").val();
	var volumes = $("#volumes").val();
	var isType = 1;
	if(volumes == undefined){
		isType = 0;
	}
/*    var input=document.getElementsByName("reason");
	var ids = new Array();
	for(var i=0; i<input.length;i++){
       if(input[i].checked==true){
    	   ids[i]=input[i].value;
       }
    }
	var reason = ids.join(",");*/
	var reason = $('#reason').val();
		if(confirm("确定退款吗?")){
			var urlval = '/managermall/account/refund/submitApply.do';
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:{"token":token,"reason":reason,"detailId":detailId,"isType":isType},
		    	success:function(msg){
		    		if (msg=='success'){
		    			alert("退款提交成功");
		    			window.location.href="/managermall/account/refund/list.do";
		    		}
					if (msg=='error'){
						alert("退款提交失败");
		    		}
					if (msg=='tokenError'){
						alert("请不要重复提交");
						window.location.href="/managermall/account/refund/list.do";
					}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(){
		    		alert("服务器忙");
		    	//ajax提交失败的处理函数！
		    	}
		    });
		}else{
			//alert("删除失败");
		}
	}