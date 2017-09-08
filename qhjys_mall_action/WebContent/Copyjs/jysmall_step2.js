$(function() {
	$("#contactName").blur(function(){
		validate(this, "请输入联系人",20);
	});
	$("#contactPhone").blur(function(){
		$("#contactPhone").next().remove();
		var html = $('<label class="one1" style="width:180px; color: red; ">请输入手机号码</label>');
		var val = $("#contactPhone").val().trim();
		var isMobile=/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#contactPhone").after(html);
		} else if (!isMobile.test($("#contactPhone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:180px; color: red; ">' + str + '</label>');
			$("#contactPhone").after(html);
		}
	});
	$("#contactEmail").blur(function(){
		$("#contactEmail").next().remove();
		var html = $('<label class="one1" style="width:180px; color: red; ">请输入邮箱</label>');
		var val = $("#contactEmail").val().trim();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#contactEmail").after(html);
		} else if (!isEmail.test($("#contactEmail").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px; color: red; ">' + str + '</label>');
			$("#contactEmail").after(html);
		}
	});
	$("#signupForm").submit(function() {
		$("#signupForm label[class=one1]").remove();
		validate("#contactName", "请输入联系人",20);
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#contactPhone").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px; color: red; ">请输入手机号码</label>');
			$("#contactPhone").after(html);
		} else if (!isMobile.test($("#contactPhone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:180px; color: red; ">' + str + '</label>');
			$("#contactPhone").after(html);
		}
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if ($("#contactEmail").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px; color: red; ">请输入邮箱</label>');
			$("#contactEmail").after(html);
		} else if (!isEmail.test($("#contactEmail").val())) {
			var str = "请输入正确的邮箱";
			var html = $('<label class="one1" style="width:180px; color: red; ">' + str + '</label>');
			$("#contactEmail").after(html);
		}
		if($("#signupForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/updateSeller.do',
			data : $(this).serialize(),
			success : function(data) {
				window.location.href = '/managermall/seller/addCompany.do';
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
	var html = $('<label class="one1">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1">' + str + '</label>');
		$(el).after(html);
	}
}