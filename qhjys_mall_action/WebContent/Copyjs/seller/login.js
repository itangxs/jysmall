$(document).ready(function() {
	 $.formValidator.initConfig({formID:"signupForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#contactname").formValidator({onShow:"请输入账号",onFocus:"输入中...",onCorrect:""}).inputValidator({min:2,max:100,onError:"账号过长或者过短"});
	 $("#password").formValidator({onShow:"请输入密码",onFocus:"输入中...",onCorrect:""}).inputValidator({min:2,max:100,onError:"密码过长或者过短"});
});
