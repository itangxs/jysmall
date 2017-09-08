$(function(){
	$("#sellerPass").focus(function(){
		var pass = $("#sellerPass").val();
		if (pass == "请输入商户推荐码") {
			$("#sellerPass").val("")
		}
	});
	$("#sellerPass").blur(function(){
		var pass = $("#sellerPass").val();
		if (pass == "") {
			$("#sellerPass").val("请输入商户推荐码");
		}
	});
})

function submit1(){
	var pass = $("#sellerPass").val();
		if(pass != null && pass!=''){
			var urlval = '/appUser/task/doSellerTask.do';
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	async: false,
		    	data:$("#sellerTask").serialize(),//传给后台的数据，多个参数用&连接
		    	success:function(msg){
		    		if(msg="活动已提交,明日审核获得商家消费券"){
		    			$("#stbut").attr("disabled","disabled");
		    			$("#stbut").attr("value","已完成");
		    			$("#stbut").css({background:'#ccc', color:'#000'});
		    		}else if(msg == "你已做过此商家任务"){
		    			$("#stbut").attr("disabled","disabled");
		    			$("#stbut").attr("value","已完成");
		    			$("#stbut").css({ backgroundColor:gray})
		    			$("#stbut").css({background:'#ccc', color:'#000'});
		    		}
		    		alert(msg);
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(){
		    	//ajax提交失败的处理函数！
		    		alert("任务失败");
		    	}
		    });
		}
}