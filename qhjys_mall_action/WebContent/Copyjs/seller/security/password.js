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
	var patrn = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._@]){4,19}$/;
	$("#oldCode").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			$(this).after('<label class="error">请输入旧密码！</label>');
			return false;
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#newCode").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			$(this).after('<label class="error">请输入新密码！</label>');
			return false;
		} else if (!patrn.exec(this.value)) {
			$(this).after('<label class="error">密码格式错误！</label>');
			return false;
		} else {
			$(this).siblings("label.error").remove();
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#confirm").on("blur", function() {
		var newCode = $("#newCode").val();
		$(this).siblings("label.error").remove();
		if (newCode != "" && this.value != newCode) {
			$(this).after('<label class="error">两次密码输入不一致！</label>');
			return false;
		} else {
			$(this).siblings("label.error").remove();
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$('#sendSMS').on("click", function() {
		var phone = $('#phone').val();
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
	$("#form").on("submit", function() {
		var captcha = $('#captcha'), oldCode = $("#oldCode"), newCode = $("#newCode"), confirm = $("#confirm");
		if (captcha.val() == "") {
			captcha.parent().append('<label class="error">请输入验证码！</label>');
			return false;
		}
		if (oldCode.val() == "") {
			oldCode.after('<label class="error">请输入旧密码！</label>');
			return false;
		}
		if (newCode.val() == "") {
			newCode.after('<label class="error">请输入新密码！</label>');
			return false;
		} else if (!patrn.exec(newCode.val())) {
			newCode.after('<label class="error">密码格式错误！</label>');
			return false;
		}
		if (newCode.val() != "" && confirm.val() != newCode.val()) {
			confirm.after('<label class="error">两次密码输入不一致！</label>');
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : 'changePassWord.do',
			data : {
				captcha : captcha.val(),
				oldCode : md5(oldCode.val()),
				newCode : md5(newCode.val())
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data == "error") {
					alert("旧密码错误！");
				} else if (data == "codeError") {
					alert("验证码错误！");
				} else if (data) {
					location.href = "center.do";
				} else {
					alert("密码修改异常！");
				}
			}
		});
		return false;
	});
});