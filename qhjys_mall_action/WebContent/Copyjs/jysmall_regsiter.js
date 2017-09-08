$(function() {
	$("#username").blur(function(){
		validate(this, "请输入用户名",20);
	});
	$("#password").blur(function(){
		validate(this, "请输入密码",20);
	});
	$("#confirm_password").blur(function(){
		if($("#confirm_password").val().trim() != $("#password").val().trim()){
			$("#confirm_password").next().remove();
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_password").after(html);
		}else{
			validate(this, "请再次输入密码",20);
		}
	});
	$("#sellerPhone").blur(function(){
		$("#sellerPhone").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
		var val = $("#sellerPhone").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#sellerPhone").after(html);
		} else if (!isMobile.test($("#sellerPhone").val())) {
			str = "请输入正确的手机号";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#sellerPhone").after(html);
		}
	});
	$("#identifying_code").blur(function(){
		if ($("#identifying_code").val().trim() == "") {
			$(".get_code").next().remove();
			var html = $('<label class="one1" style="width:180px;">请输入校验码</label>');
			$(".get_code").after(html);
		} else if ($("#identifying_code").val().length > 6) {
			var html = $('<label class="one1" style="width:180px;">长度不能大于6位</label>');
			$(".get_code").after(html);
		}
	});
	$("#isAgree").blur(function(){
		$("#isAgree").next().next().remove();
		if($('#isAgree').is(':checked') == false){
			var html = $('<label class="one1" style="width:180px;">请选择复选框</label>');
			$("#isAgree").next().after(html);
		}
	});
	$("#signupForm").submit(function() {
		$("#signupForm label[class=one1]").remove();
		validate("#username", "请输入用户名",20);
		validate("#password", "请输入密码",20);
		validate("#confirm_password", "请再次输入密码",20);
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#sellerPhone").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
			$("#sellerPhone").after(html);
		} else if (!isMobile.test($("#sellerPhone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#sellerPhone").after(html);
		}
		if ($("#identifying_code").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入校验码</label>');
			$(".get_code").after(html);
		} else if ($("#identifying_code").val().length > 6) {
			var html = $('<label class="one1" style="width:180px;">长度不能大于6位！</label>');
			$(".get_code").after(html);
		}
		if($('#isAgree').is(':checked') == false){
			var html = $('<label class="one1" style="width:180px;">请选择复选框</label>');
			$("#isAgree").next().after(html);
		}
		if($("#signupForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : 'saveSeller.do',
			data : $(this).serialize(),
			success : function(data) {
				window.location.href = 'serviceAgreement.do';
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
});

function validate(el, str, length){
	$(el).next().remove();
	var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:180px;">' + str + '</label>');
		$(el).after(html);
	}
}