$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#jym").formValidator({onShow:"",onFocus:"输入中...",onCorrect:""}).inputValidator({min:1,max:11,onError:"校验码过短或者过长"});
	/* .regexValidator({regExp:"mobile",dataType:"enum",onError:"格式错误"});*/
	 $("#password").formValidator({onShow:"请输入密码",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:1,max:12,onError:"密码过短或者过长"});
	 $("#password2").formValidator({onShow:"请重复输入密码",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:1,max:12,onError:"密码过短或者过长"}).compareValidator({desID:"password",operateor:"=",onError:"两次密码不一致"});
	 $("#ymyzm").formValidator({onShow:"请输入验证码",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:1,max:12,onError:"验证码过短或者过长"}).functionValidator({fun:codeYzm});

	 
	/* var phoneReg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		$("#sendSms").on("click", function() {
			alert("1")
			var phone = $("#phone").val();
			if (!phoneReg.test(phone)) {
				errMsg($(this), '手机号码格式错误！');
				return false;
			}
			alert("1")
			var $el = $(this).attr("disabled", true);
			alert("1")
			$.ajax({
				async : true,
				type : "POST",
				url : '/sendSMSCaptcha.do',
				data : {phone : phone},
				success : function(msg) {
					alert(msg);
					if (msg=='0000'){
		    			alert("短信发送成功");
		    			timeWait($el);
		    		} else if (msg=='0001'){
						alert("手机号码错误！");
						$el.removeAttr("disabled");
					}else if (msg=='0002'){
						alert("发送短信失败");
						$el.removeAttr("disabled");
		    		}else{
		    			alert("网络忙!");
		    			$el.removeAttr("disabled");
		    		}
				}
			});
		});*/
});

function getPhoneCode(phone) {
	var $el = $(this).attr("disabled", true);
	var urlval = "/sendSMSCaptcha.do";
	var param = "phone="+phone;
	 $.ajax({
	    	type:'post',//可选get
	    	url:urlval,//这里是接收数据的后台程序
	    		data:param,//传给后台的数据，多个参数用&连接
	    		/*	dataType:'json',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
*/	    	success:function(msg){
	    		if (msg=='0000'){
	    			alert("短信发送成功");
	    			timeWait($el);
	    		} else if (msg=='0001'){
					alert("手机号码错误！");
					$el.removeAttr("disabled");
				}else if (msg=='0002'){
					alert("发送短信失败");
					$el.removeAttr("disabled");
	    		}else{
	    			alert("网络忙!");
	    			$el.removeAttr("disabled");
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    		alert("服务器忙");
	    	//ajax提交失败的处理函数！
	    	}
	    });
}




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

////


function errMsg($el, text) {
	$el.siblings("label.error").remove();
	$el.parent().append('<label class="error">' + text + '</label>');
}

var wait = 120;
function timeWait($el) {
	if (wait == 0) {
		$("#sendSms").removeAttr("disabled");
		$el.val("获取验证码");
		wait = 120;
	} else {
		$("#sendSms").attr("disabled", true);
		$("#sendSms").val("重新发送(" + wait + ")");
		/*alert($("#sendSms").val()+"sdf");*/
		wait--;
		setTimeout(function() {
			timeWait($el)
		}, 1000)
	}
}
