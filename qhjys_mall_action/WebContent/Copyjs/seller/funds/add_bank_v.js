$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#realName").formValidator({onShow:"请输入开户姓名",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:2,max:50,onError:"不符合要求"}).regexValidator({regExp:"regnew02",dataType:"enum",onError:"只能是中文"});
	 $("#theBank").formValidator({onShow:"请选择开户银行",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:1,max:50,onError:"不符合要求"});
	 $("#subbranchName").formValidator({onShow:"请认真填写分支行信息",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:5,max:50,onError:"不符合要求"});
	 $("#bankAccout").formValidator({onShow:"请填写正确的银行卡",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:12,max:20,onError:"长度过短或者过长"}).regexValidator({regExp:"yinhanka",dataType:"enum",onError:"不符合要求"});
});



function submit1(){
		if(confirm("确定要提交吗?")){
			var urlval = '/managermall/seller/funds/addBank.do';
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	async: false,
		    	data:$("#signupForm").serialize(),//传给后台的数据，多个参数用&连接
		    	success:function(msg){
		    		if (msg=='0000'){
		    			alert("添加成功");
		    			window.location.href="/managermall/seller/funds/bank.do";
		    		}
					if (msg=='0001'){
						alert("添加失败");
						window.location.href="/managermall/seller/funds/toAddBank.do";
		    		}
					if (msg=='0002'){
						alert("银行卡已经存在");
						window.location.href="/managermall/seller/funds/toAddBank.do";
					}
					if (msg=='0003'){
						alert("银行卡错误");
						window.location.href="/managermall/seller/funds/toAddBank.do";
					}
					
					if (msg=='tokenError'){
						alert("请不要重复提交");
						window.location.href="/managermall/seller/funds/bank.do";
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