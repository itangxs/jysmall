$(function() {
	var patrn = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	$("#email").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			$(this).after('<label class="error">请邮箱地址密码！</label>');
			return false;
		} else if (!patrn.exec(this.value)) {
			$(this).after('<label class="error">邮箱地址格式错误！</label>');
			return false;
		} else {
			$(this).siblings("label.error").remove();
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$('#sendEmail1').on("click", function() {
		var email = $('#email').val();
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendRequestEmailCaptcha.do',
			data : {"emailUrl" : email},
			success : function(data) {
					if (data != "succeess"){
						alert("邮箱发送异常！");
					}
				}
		});
	});
	$("#addEmail").on("click", function() {
		var email = $("#email");
		var captcha = $("#captcha");
		if (email.val() == "") {
			email.after('<label class="error">请输入邮箱地址！</label>');
			return false;
		} else if (!patrn.exec(email.val())) {
			email.after('<label class="error">邮箱地址格式错误！</label>');
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : 'addEmail.do',
			data : {
				email : email.val(),
				captcha : captcha.val()
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data == "验证码错误") {
					alert(data);
				} else if (data == "您修改的邮箱和原邮箱重复！") {
					alert("您修改的邮箱和原邮箱重复,请重新输入邮箱！");
				} else if (data) {
					location.href = "changedEmail.do";
				}else {
					alert("添加邮箱错误！");
				}
			}
		});
	});
	$('#sendEmail').on("click", function() {
		var email = $('#sellerEmail').val();
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendRequestEmailCaptcha.do',
			data : {"emailUrl" : email},
			success : function(data) {
					if (data != "succeess"){
						alert("邮箱发送异常！");
					}
				}
		});
	});
	$("#verifyEmail").on("click", function() {
		var emailCode = $("#captcha").val();
		$.ajax({
			async : true,
			type : "POST",
			url : 'verifyEmail.do',
			data : {
				captcha : emailCode
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data == "验证码错误") {
					alert(data);
				} else if (data) {
					location.href = "changNewEmail.do";
				}
			}
		});
	});
});