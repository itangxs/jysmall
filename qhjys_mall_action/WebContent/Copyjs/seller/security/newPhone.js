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
	var phoneReg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	$("#newPhone").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			$(this).after('<label class="error">请输入手机号码！</label>');
			return false;
		}
		if (!phoneReg.test(this.value)) {
			$(this).after('<label class="error">手机号码格式错误！</label>');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$('#sendSMS').on("click", function() {
		var newPhone = $("#newPhone");
		if (!phoneReg.test(newPhone.val())) {
			newPhone.siblings("label.error").remove().after('<label class="error">手机号码格式错误！</label>');
			return false;
		}
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendSMSCaptcha.do',
			data : {
				phone : newPhone.val()
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
	$("#update").on("click", function() {
		var newPhone = $("#newPhone"), captcha = $('#captcha');
		if (captcha.val() == "") {
			captcha.siblings("label.error").remove().parent().append('<label class="error">请输入验证码！</label>');
			return false;
		}
		if (newPhone.val() == "") {
			newPhone.after('<label class="error">请输入手机号码！</label>');
			return false;
		}
		if (!phoneReg.test(newPhone.val())) {
			newPhone.after('<label class="error">手机号码格式错误！</label>');
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : 'changePhone.do',
			data : {
				captcha : captcha.val(),
				newPhone : newPhone.val()
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data) {
					location.href = "updatedPhone.do";
				} else {
					alert("短信验证码错误！");
				}
			}
		});
	});
});