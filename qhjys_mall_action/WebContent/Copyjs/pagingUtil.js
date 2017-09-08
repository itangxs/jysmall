function previousPage(page,formid,pageid){
	 //page  页数
	// formid 表单ID.
	// pageid 表单下隐藏的pageID 
	 document.getElementById(pageid).value=page;
	//获取表单
	 document.getElementById(formid).submit();
	 
	 /*  var urlval = document.getElementById(formid).action;
	   var parma = $("#"+formid).serialize();
	   +"?name=erw";
	   +$("#"+formid).serialize();
	   alert(urlval);
	   
	   
	   
	   
	   $.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	data:parma,//传给后台的数据，多个参数用&连接  
	    	async:false,
	    	success:function(msg){
	    		return ; 
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    	//ajax提交失败的处理函数！
	    		return ; 
	    	}
	    });*/
}