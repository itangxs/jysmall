$(function() {
	$("#question").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			$(this).after('<label class="error">请输入密保问题！</label>');
			return false;
		} else {
			$(this).siblings("label.error").remove();
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#answer").on("blur", function() {
		$(this).siblings("label.error").remove();
		if (this.value == "") {
			$(this).after('<label class="error">请输入密保答案！</label>');
			return false;
		} else {
			$(this).siblings("label.error").remove();
		}
	}).on("change", function() {
		$(this).siblings("label.error").remove();
	});
	$("#addQuestion").on("click", function() {
		var question = $("#question"), answer = $("#answer");
		if (question.val() == "") {
			question.after('<label class="error">请输入密保问题！</label>');
			return false;
		}
		if (answer.val() == "") {
			email.after('<label class="error">请输入密保答案！</label>');
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : 'addQuestion.do',
			data : {
				question : question.val(),
				answer : md5(answer.val())
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data) {
					location.href = "center.do";
				} else {
					alert("添加邮箱错误！");
				}
			}
		});
	});
	$("#verifyAnswer").on("click", function() {
		var answer = $("#answer");
		if (answer.val() == "") {
			$("#answer").siblings("label.error").remove();
			answer.after('<label class="error">请输入密保答案！</label>');
			return false;
		}
		$.ajax({
			async : true,
			type : "POST",
			url : 'verifyAnswer.do',
			data : {
				answer : md5(answer.val())
			},
			success : function(data) {
				if (data == "timeout") {
					location.href = "/seller/login.do";
				} else if (data) {
					location.href = "toSetQuestion.do";
				} else {
					alert("密保答案错误！");
				}
			}
		});
	});
});