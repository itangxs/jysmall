$(function() {
	$("#username").click(function() {
		if ($("#username").val() == "手机号/用户名/邮箱")
			$("#username").val("");
	}).blur(function() {
		$("#username").next().remove();
		var html = $('<label class="one1" style="width:180px; color:#a3a3a3;"> 请输入用户名</label>');
		var val = $("#username").val();
		if (val == "")
			$("#username").after(html);
		if ($("#username").val() == "")
			$("#username").val("手机号/用户名/邮箱");
	});
	$("#password").click(function() {
		if ($("#password").val() == "密码")
			$("#password").val("");
		$("#password").attr('type', 'password');
	}).blur(function() {
		$("#password").next().remove();
		var html = $('<label class="one1" style="width:180px; color:#a3a3a3;"> 请输入密码</label>');
		var val = $("#password").val();
		if (val == "")
			$("#password").after(html);
	});
	$("#loginFrom").submit(function() {
		$("#loginFrom label[class=one1]").remove();
		var html = $('<label class="one1" style="width:180px; color:#a3a3a3;"> 请输入用户名</label>');
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "") {
			$("#username").after(html);
		} else if (username == "手机号/用户名/邮箱") {
			$("#username").after(html);
		}
		var html = $('<label class="one1" style="width:180px; color:#a3a3a3;"> 请输入密码</label>');
		if (password == "") {
			$("#password").after(html);
		}
		if ($("#loginFrom label[class=one1]").length > 0) {
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/account/loginUser.do',
			data : {
				"username" : username,
				"password" : md5(password)
			},
			success : function(data) {
				if (data.message == "登录成功") {
					window.location.href = '/index.do';
				} else {
					alert(data.message);
				}
			}
		});
		return false;
	});
});

function loginCokie(name, value, time) {
	var exp = new Date();
	exp.setTime(exp.getTime() + time * 60 * 60 * 24 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}