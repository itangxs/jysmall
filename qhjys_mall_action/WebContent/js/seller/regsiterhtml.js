$(function() {
	//var phoneReg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	var phoneReg= /^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
	var userReg = /^[A-Za-z0-9]{6,16}$/;
	var passReg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._@]){6,16}$/;

	$("#sendSms").on("click", function() {

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
			url : '/seller/addSellerHtml.do',
			data : {
				'username' : username.val(),
				'password' : md5(password.val()),
				'phone' : phone.val(),
				'captcha' : captcha.val()
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
//	$el.siblings("label.error").remove();
//	$el.parent().append('<label class="error">' + text + '</label>');
	alert(text);
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