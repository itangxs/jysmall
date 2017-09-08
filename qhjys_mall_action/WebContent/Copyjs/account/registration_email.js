$(function() {

	$("#registrationByTel").removeClass("tabaction");
	$("#email").blur(function() {
		$("#email").next().remove();
		$("#email").attr("class","inputkk");
		var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
		var val = $("#email").val().trim();
		var isEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#email").after(html);
			$("#email").attr("class","inputkkred");
		} else if (!isEmail.test($("#email").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email").after(html);
			$("#email").attr("class","inputkkred");
		}
	});
	$("#username").blur(function(){
		$("#username").next().remove();
		$("#username").attr("class","inputkk");
		var html = $('<label class="one1" style="width:180px;">请输入用户名</label>');
		var val = $("#username").val().trim();
		if (val == "") {
			$("#username").after(html);
			$("#username").attr("class","inputkkred");
		} else if ($("#username").val().length > 20) {
			html = $('<label class="one1" style="width:180px;">长度不能大于20位！</label>');
			$("#username").after(html);
			$("#username").attr("class","inputkkred");
		}else if($("#username").val().length < 6){
			html = $('<label class="one1" style="width:180px;">长度不能小于6位！</label>');
			$("#username").after(html);
			$("#username").attr("class","inputkkred");
		}
	});
	$("#password").blur(function(){
		$("#password").next().remove();
		$("#password").attr("class","inputkk");
		var html = $('<label class="one1" style="width:180px;">请输入密码</label>');
		var val = $("#password").val().trim();
		if (val == "") {
			$("#password").after(html);
			$("#password").attr("class","inputkkred");
		} else if ($("#password").val().length > 20) {
			var html = $('<label class="one1" style="width:180px;">长度不能大于20位</label>');
			$("#password").after(html);
			$("#password").attr("class","inputkkred");
		}else{
			var html = $('<div class="mimazhuangtai">'+'<ul>'+'<li>弱</li>'+'<li>中</li>'+'<li>强</li>'+'<div class="clear"></div>'+'</ul>'+'</div>');
			$("#password").after(html);
			$("#password").attr("class","inputkkred");
		}
		//验证输入的内容同时包含字母数字特殊符号，且长度在10-20
		var isNumAndLetterAndSpecial = /(?=.*[\d]+)(?=.*[a-zA-Z]+)(?=.*[^a-zA-Z0-9]+).{10,20}/;
		//验证输入的内容同时包含字母和特殊符号，且长度在10-20
		var isLetterAndSpecial = /(?=.*[a-zA-Z]+)(?=.*[^a-zA-Z0-9]+).{10,20}/;
		//验证输入的内容同时包含字母和数字，且长度在10-20
		var isNumAndLetter = /(?=.*[\d]+)(?=.*[a-zA-Z]+).{10,20}/;
		//验证输入的内容同时包含特殊符号和数字，且长度在10-20
		var isNumAndSpecial = /(?=.*[\d]+)(?=.*[^a-zA-Z0-9]+).{10,20}/;
		if(isNumAndLetterAndSpecial.test($("#password").val().trim())){//当密码输入框同时包含数字、字母、特殊符号时密码强度为强
			$(".mimazhuangtai ul li").last().addClass("ruored");
		}else if(isNumAndLetter.test($("#password").val().trim())
				|| isNumAndSpecial.test($("#password").val().trim())
				|| isLetterAndSpecial.test($("#password").val().trim())){//当密码输入框同时包含数字、字母、特殊符号中的任意两种时密码强度为中
			$(".mimazhuangtai ul li").first().next().addClass("ruored");
		}else{//当密码输入框同时包含数字、字母、特殊符号中的任意一种时密码强度为弱
			$(".mimazhuangtai ul li").first().addClass("ruored");
		}
	});
	$("#confirm_password").blur(function(){
		if($("#confirm_password").val().trim() != $("#password").val().trim()){
			$("#confirm_password").next().remove(); 
			$("#confirm_password").attr("class","inputkk");
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_password").after(html);
			$("#confirm_password").attr("class","inputkkred");
		}else{
			validate(this, "请再次输入密码",20);
		}
	});
/*	$("#validateCode").blur(function(){
		validate(this, "请输入验证码",20);
	});*/
	$("#registrationForm").submit(function() {
		$("#registrationForm label[class=one1]").remove();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if ($("#email").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
			$("#email").after(html);
			$("#email").attr("class","inputkkred");
		} else if (!isEmail.test($("#email").val())) {
			var str = "请输入正确的邮箱";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email").after(html);
			$("#email").attr("class","inputkkred");
		}
		var html = $('<label class="one1" style="width:180px;">请输入用户名</label>');
		var val = $("#username").val().trim();
		if (val == "") {
			$("#username").after(html);
			$("#username").attr("class","inputkkred");
		} else if ($("#username").val().length > 20) {
			html = $('<label class="one1" style="width:180px;">长度不能大于20位！</label>');
			$("#username").after(html);
			$("#username").attr("class","inputkkred");
		}else if($("#username").val().length < 6){
			html = $('<label class="one1" style="width:180px;">长度不能小于6位！</label>');
			$("#username").after(html);
			$("#username").attr("class","inputkkred");
		}
		var html = $('<label class="one1" style="width:180px;">请输入密码</label>');
		var val = $("#password").val().trim();
		if (val == "") {
			$("#password").after(html);
			$("#password").attr("class","inputkkred");
		} else if ($("#password").val().length > 20) {
			var html = $('<label class="one1" style="width:180px;">长度不能大于20位</label>');
			$("#password").after(html);
			$("#password").attr("class","inputkkred");
		}
		if($("#confirm_password").val().trim() != $("#password").val().trim()){
			$("#confirm_password").next().remove(); 
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_password").after(html);
			$("#confirm_password").attr("class","inputkkred");
		}else{
			validate("#confirm_password", "请再次输入密码",20);
		}
//		validate("#validateCode", "请输入验证码",20);
		if($("#registrationForm label[class=one1]").length > 0){
			return false;
		}
		$("#password").val(md5($("#password").val()));
		$.ajax({
			async : false,
			type : "POST",
			url : '/account/insertUser.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "注册成功"){
					alert("注册成功");
					window.location.href = '/account/login.do';
				}else{
					alert("用户名或邮箱已存在");
				}
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
	$(el).attr("class","inputkk");
	var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
	var val = $(el).val().trim();
	if (val == "") {
		$(el).after(html);
		$(el).attr("class","inputkkred");
		} else if ($(el).val().length > length) {
		str = "长度不能大于"+length+"位！";
		html = $('<label class="one1" style="width:180px;">' + str + '</label>');
		$(el).after(html);
		$(el).attr("class","inputkkred");
	}
}