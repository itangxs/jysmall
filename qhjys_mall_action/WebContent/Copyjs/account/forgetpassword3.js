$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#forgetYzm").formValidator({onShow:"请输入验证码",onFocus:"输入中...",onCorrect:""}).inputValidator({min:1,max:33,onError:"错误"});
	/* $("#yzm").formValidator({onShow:"请输入验证码",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:2,max:100,onError:"位数错误"}).functionValidator({fun:codeYzm});*/

});


$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm1",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#question").formValidator({onShow:"请输入问题",onFocus:"输入中...",onCorrect:""}).inputValidator({min:1,max:200,onError:"太短或者太长了"});
	 $("#answer").formValidator({onShow:"请输入回答",onFocus:"输入中...",onCorrect:""}).inputValidator({min:1,max:200,onError:"太短或者太长了"});
	/* $("#yzm").formValidator({onShow:"请输入验证码",onFocus:"输入中...",onCorrect:"正确"}).inputValidator({min:2,max:100,onError:"位数错误"}).functionValidator({fun:codeYzm});*/

});
var wait = 120;
function timeWait($el) {
	if (wait == 0) {
		$el.removeAttr("disabled");
		$el.val("获取验证码");
		wait = 120;
	} else {
		$el.attr("disabled", true);
		$el.val("重新发送(" + wait + ")");
		wait--;
		setTimeout(function() {
			timeWait($el)
		}, 1000)
	}
}
	$(function(){
		$('#sendSMS').on("click", function() {
			var phone = $('#phoneNum').val();
			var $el = $(this).attr("disabled", true);
			$.ajax({
				async : true,
				type : "POST",
				url : '/sendSMSCaptcha.do',
				data : {phone : phone},
				success : function(data){
					if (data == "0000"){
						alert("发送成功,请留意手机");
						timeWait($el);
					}
					else {
						if (data == "0001")
							alert("手机号码格式错误！");
						else
							alert("发送短信异常！");
						$el.removeAttr("disabled");
					}
				}
			});
		});
	});
		

