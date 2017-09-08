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

$(function() {
	$('#sendSMS').on("click", function() {
		var phone = $('#phone').val();
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendSMSCaptcha.do',
			data : {
				phone : phone
			},
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
	$("#verify").on("click", function() {
		var captcha = $('#captcha');
		if (captcha.val() == "") {
			captcha.parent().append('<label class="error">请输入验证码！</label>');
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : 'verifyPhone.do',
			data : {
				captcha : captcha.val()
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data) {
					location.href = "updateNewPhone.do";
				} else {
					alert("短信验证码错误！");
				}
			}
		});
	});
});