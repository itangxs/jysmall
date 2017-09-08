$(function() {
	//var phoneReg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	var phoneReg= /^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
	var userReg = /^[A-Za-z0-9]{6,16}$/;
	var passReg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._@]){6,16}$/;
	$("#username").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			errMsg($(this), '请输入用户帐号！');
			return false;
		} else if (!userReg.exec(this.value)) {
			errMsg($(this), '帐号格式错误,请输入6-16英文或字母!');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#password").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			errMsg($(this), '请输入登录密码！密码格式必须为：字母+数字并且大于6位数');
			return false;
		} else if (!passReg.exec(this.value)) {
			errMsg($(this), '密码格式错误，密码格式必须为：字母+数字并且大于6位数');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#confirm").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value != $("#password").val()) {
			errMsg($(this), '两次密码不一致！');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#phone").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			errMsg($(this), '请输入手机号码！');
			return false;
		}
		if (!phoneReg.test(this.value)) {
			errMsg($(this), '手机号码格式错误,请输入13|14|15|17|18的有效手机号码！');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#captcha").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			errMsg($(this), '请输入短信验证码！');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#sendSms").on("click", function() {
		
		//校验验证码
		var result=true;
		var margin=$('#identifyingCode').val();
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
			    	}else{
			    		alert("验证码错误,请输入正确的验证码");
			    		result=false;
			    	}
			    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(e){
		    	//ajax提交失败的处理函数！
		    		result=false;
		    	}
		    });
		
		if(!result){
			return;
		}
		
		//校验验证码 结束
		
		
		
		
		
		var phone = $("#phone").val();
		if (!phoneReg.test(phone)) {
			errMsg($(this), '手机号码格式错误！');
			return false;
		}
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendSMSCaptcha.do',
			data : {phone : phone},
			success : function(data) {
				if (data == "0000")
					timeWait($el);
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
	$("#regsiter").on("submit", function() {
		var username = $("#username"), password = $("#password"), confirm = $("#confirm"), phone = $("#phone"), captcha = $("#captcha");
		if (username.val() == "") {
			errMsg(username, '请输入用户帐号！');
			return false;
		} else if (!userReg.exec(username.val())) {
			errMsg(username, '帐号格式错误！');
			return false;
		}
		if (password.val() == "") {
			errMsg(password, '请输入登录密码！');
			return false;
		} else if (!passReg.exec(password.val())) {
			errMsg(password, '密码格式错误！');
			return false;
		}
		if (confirm.val() != password.val()) {
			errMsg(confirm, '两次密码不一致！');
			return false;
		}
		if (phone.val() == "") {
			errMsg(phone, '请输入手机号码！');
			return false;
		} else if (!phoneReg.test(phone.val())) {
			errMsg(phone, '手机号码格式错误！');
			return false;
		}
		//if (captcha.val() == "") {
		//	errMsg(captcha, '请输入短信验证码！');
		//	return false;
		//}
		if (!$("#protocol").prop('checked')) {
			alert("请同意协议！");
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : 'addSeller.do',
			data : {
				token : $("#token").val(),
				username : username.val(),
				password : md5(password.val()),
				phone : phone.val(),
				captcha : captcha.val()
			},
			success : function(data) {
				if (data == "error") {
					alert("帐号已存在！");
				} else if (data == "timeout") {
					alert("操作超时！");
					location.reload();
				} else if (data == 'codeError') {
					alert('短信验证码错误！');
				} else {
					alert('注册成功！');
					location.href = "/seller/login.do";
				}
			}
		});
		return false;
	});
});

function errMsg($el, text) {
	$el.siblings("label.error").remove();
	$el.parent().append('<label class="error">' + text + '</label>');
}

var wait = 120;
function timeWait($el) {
	if (wait == 0) {
		$el.sttr("disabled","");
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