$(function() {
	$("#name").blur(function(){
		validate(this, "请输入所属银行",20);
	});
	$("#branch").blur(function(){
		validate(this, "请输入银行支行名称",20);
	});
	$("#cardholder").blur(function(){
		validate(this, "请输入真实姓名",20);
	});
	$("#carkNumber").blur(function(){
		validate(this, "请输入银行账号",21);
	});
	$("#phone").blur(function(){
		$("#phone").next().remove();
		var html = $('<label class="one1" style="width:150px; color: red; ">请输入手机号码</label>');
		var val = $("#phone").val().trim();
		var isMobile=/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phone").after(html);
		} else if (!isMobile.test($("#phone").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:150px; color: red; ">' + str + '</label>');
			$("#phone").after(html);
		}
	});
	$("#signupForm").submit(function() {
		$("#signupForm label[class=one1]").remove();
		validate("#name", "请输入所属银行",20);
		validate("#branch", "请输入银行支行名称",20);
		validate("#cardholder", "请输入真实姓名",20);
		validate("#carkNumber", "请输入银行账号",21);
		var isMobile=/^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#phone").val().trim() == "") {
			var html = $('<label class="one1" style="width:150px; color: red; ">请输入手机号码</label>');
			$("#phone").after(html);
		} else if (!isMobile.test($("#phone").val())) {
			var html = $('<label class="one1" style="width:150px; color: red; ">请输入正确的手机号</label>');
			$("#phone").after(html);
		}
		if($("#signupForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : 'saveBankInfo.do',
			data : $(this).serialize(),
			success : function(data) {
				window.location.href = 'saveBankInfo.do';
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
	var html = $('<label class="one1" style="width:150px; color: red; ">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
	} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:150px; color: red; ">' + str + '</label>');
		$(el).after(html);
	}
}