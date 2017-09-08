$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 
	
	 
	 $("#yzm").formValidator({onShow:"请输入验证码",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:2,max:100,onError:"位数错误"}).functionValidator({fun:codeYzm});

});



//验证吗
function codeYzm(code){
	var result;
	//获取到验证证码
	var margin=code;
	var param = 'codeYzm='+margin;
	var urlval = '/valiCode/codeyzm.do';
	   $.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    	data:param,//传给后台的数据，多个参数用&连接
	    /*	dataType:'json',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
*/	    	async:false,
	    	success:function(msg){
		    	if(msg=='success'){
		    		result=true;
		    	}
		    	if(msg=='error'){
		    		result=false;
		    	}
		    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(e){
	    	//ajax提交失败的处理函数！
	    	}
	    });
	  if(result==true){return true;}else{return "验证码错误"};
}

