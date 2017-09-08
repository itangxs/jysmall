var wait = 120;
function timeWait($el) {
	if (wait == 0) {
		$el.attr("disabled","");
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

var sendEmailwait = 120;
function sendEmailtimeWait($el) {
	if (wait == 0) {
		$el.attr("disabled","");
		$el.val("发送验证码到邮箱");
		sendEmailwait = 120;
	} else {
		$el.attr("disabled", true);
		$el.val("重新发送(" + sendEmailwait + ")");
		sendEmailwait--;
		setTimeout(function() {
			sendEmailtimeWait($el)
		}, 1000)
	}
}


var sendEmail1wait = 120;
function sendEmail1timeWait($el) {
	if (wait == 0) {
		$el.attr("disabled","");
		$el.val("发送验证码到旧邮箱");
		sendEmail1wait = 120;
	} else {
		$el.attr("disabled", true);
		$el.val("重新发送(" + sendEmail1wait + ")");
		sendEmail1wait--;
		setTimeout(function() {
			sendEmail1timeWait($el)
		}, 1000)
	}
}


var sendEmail2wait = 120;
function sendEmail2timeWait($el) {
	if (wait == 0) {
		$el.removeAttr("disabled");
		$el.val("发送验证码到旧邮箱");
		sendEmail2wait = 120;
	} else {
		$el.attr("disabled", true);
		$el.val("重新发送(" + sendEmail2wait + ")");
		sendEmail2wait--;
		setTimeout(function() {
			sendEmail2timeWait($el)
		}, 1000)
	}
}

$(function() {
	$('#sendSMS1').on("click", function() {
		var phone = $('#phoneNum').val();
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
	$('#sendSMS2').on("click", function() {
		var phone = $('#nowPhoneNum').val();
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
	$('#sendSMS3').on("click", function() {
		var newPhone = $('#phoneNum1').val();
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendNewSMSCaptcha.do',
			data : {newPhone : newPhone},
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
	$('#sendEmail').on("click", function() {
		$("#email").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
		var val = $("#email").val().trim();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#email").after(html);
			return false;
		} else if (!isEmail.test($("#email").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email").after(html);
			return false;
		}
		var email = $('#email').val();
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendRequestEmailCaptcha.do',
			data : {"emailUrl" : email},
			success : function(data) {
				if (data != "succeess"){
					alert("邮箱发送异常！");
					return null;
				}else{
					alert("邮箱发送成功！");
					sendEmailtimeWait($el);
				}
				}
		});
	});
	$('#sendEmail1').on("click", function() {
		$("#nowEmail").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
		var val = $("#nowEmail").val().trim();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#nowEmail").after(html);
			return false;
		} else if (!isEmail.test($("#nowEmail").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#nowEmail").after(html);
			return false;
		}
		var email = $('#nowEmail').val();
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendRequestEmailCaptcha.do',
			data : {"emailUrl" : email},
			success : function(data) {
				if (data != "succeess"){
					alert("邮箱发送异常！");
					return null;
				}else{
					alert("邮箱发送成功！");
					sendEmail1timeWait($el);
				}
					
				}
		});
	});
	$('#sendEmail2').on("click", function() {
		$("#email1").next().remove();
		$("#email1").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
		var val = $("#email1").val().trim();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#email1").after(html);
			return false;
		} else if (!isEmail.test($("#email1").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email1").after(html);
			return false;
		}
		var email = $('#email1').val();
		var $el = $(this).attr("disabled", true);
		$.ajax({
			async : true,
			type : "POST",
			url : '/sendRequestNewEmailCaptcha.do',
			data : {"emailUrl" : email},
			success : function(data) {
				if (data != "succeess"){
					alert("邮箱发送异常！");
					return null;
				}else{
					alert("邮箱发送成功！");
					sendEmail2timeWait($el);
				}
				}
		});
	});
	$("#nowPassword").blur(function(){
		validate(this, "请输入当前密码",20);
	});
	$("#password").blur(function(){
		validate(this, "请输入密码",20);
	});
	$("#validateCode").blur(function(){
		validate(this, "请输入动态码",20);
	});
	$("#validateCode1").blur(function(){
		validate(this, "请输入动态码",20);
	});
	$("#newValidateCode").blur(function(){
		validate(this, "请输入动态码",20);
	});
	$("#phoneNum").blur(function(){
		$("#phoneNum").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
		var val = $("#phoneNum").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phoneNum").after(html);
		} else if (!isMobile.test($("#phoneNum").val())) {
			str = "请输入正确的手机号";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phoneNum").after(html);
		}
	});
	$("#phoneNum1").blur(function(){
		$("#phoneNum1").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
		var val = $("#phoneNum1").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phoneNum1").after(html);
		} else if (!isMobile.test($("#phoneNum1").val())) {
			str = "请输入正确的手机号";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phoneNum1").after(html);
		}
	});
	$("#validateCode").blur(function(){
		validate(this, "请输入动态码",20);
	});
	$("#nowPhoneNum").blur(function(){
		validate(this, "请输入手机号",20);
	});
	$("#newValidateCode").blur(function(){
		validate(this, "请输入动态码",20);
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
	$("#email").blur(function(){
		$("#email").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
		var val = $("#email").val().trim();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#email").after(html);
		} else if (!isEmail.test($("#email").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email").after(html);
		}
	});
	$("#emailValidateCode").blur(function(){
		validate(this, "请输入验证码",20);
	});
	$("#emailValidateCode1").blur(function(){
		validate(this, "请输入验证码",20);
	});
	$("#email1").blur(function(){
		$("#email1").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
		var val = $("#email1").val().trim();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (val == "") {
			$("#email1").after(html);
		} else if (!isEmail.test($("#email1").val())) {
			str = "请输入正确的邮箱";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email1").after(html);
		}
	});
	$("#nowEmailValidateCode").blur(function(){
		validate(this, "请输入验证码",20);
	});
	$("#payCode").blur(function(){
		validate(this, "请输入支付密码",20);
	});
	$("#confirm_payCode").blur(function(){
		if($("#confirm_payCode").val().trim() != $("#payCode").val().trim()){
			$("#confirm_payCode").next().remove(); 
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_payCode").after(html);
		}else{
			validate(this, "请再次输入支付密码",20);
		}
	});
	$("#payCode1").blur(function(){
		validate(this, "请输入新支付密码",20);
	});
	$("#confirm_payCode1").blur(function(){
		if($("#confirm_payCode1").val().trim() != $("#payCode1").val().trim()){
			$("#confirm_payCode1").next().remove(); 
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_payCode1").after(html);
		}else{
			validate(this, "请再次输入新支付密码",20);
		}
	});
	$("#question").blur(function(){
		validate(this, "请输入密保问题",20);
	});
	$("#answer").blur(function(){
		validate(this, "请输入密保答案",20);
	});
	$("#realname").blur(function(){
		validate(this, "请输入真实姓名",20);
	});
	$("#cardId").blur(function(){
		$("#cardId").next().remove();
		var html = $('<label class="one1" style="width:180px;">请输入身份证号码</label>');
		var val = $("#cardId").val().trim();
		if (val == "") {
			$("#cardId").after(html);
		} else if (isNaN($("#cardId").val()) || $("#cardId").val().length !=18) {
			str = "请输入正确的身份证号码";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#cardId").after(html);
		}
	});
	$("#setupPasswordForm").submit(function() {
		$("#setupPasswordForm label[class=one1]").remove();
		validate("#nowPassword", "请输入当前密码",20);
		validate("#password", "请输入密码",20);
		validate("#confirm_password", "请再次输入密码",20);
		if($("#confirm_password").val().trim() != $("#password").val().trim()){
			$("#confirm_password").next().remove();
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_password").after(html);
		}
		if($("#setupPasswordForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : {"nowPassword" : md5($("#nowPassword").val()), "password" : md5($("#password").val())},
			success : function(data) {
				if(data == "修改成功"){
					alert("修改密码成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else if(data == "当前密码错误"){
					alert(data);
				}else{
					alert("修改密码失败");
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#setTelFrom").submit(function() {
		$("#setTelFrom label[class=one1]").remove();
		validate("#phoneNum", "请输入手机号",20);
		validate("#validateCode", "请输入动态码",20);
		if($("#setTelFrom label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "修改成功"){
					alert("绑定手机号成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else if (data == "codeError") {
					alert("验证码错误"); 
				}else{
					alert("用户名或手机号码已存在");
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#updateTelFrom").submit(function() {
		$("#updateTelFrom label[class=one1]").remove();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#phoneNum1").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
			$("#phoneNum1").after(html);
		} else if (!isMobile.test($("#phoneNum1").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phoneNum1").after(html);
		}
		validate("#validateCode1", "请再次输入密码",20);
		validate("#newValidateCode", "请再次输入密码",20);
		if($("#updateTelFrom label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "修改成功"){
					alert("绑定手机号成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else if (data == "codeError") {
					alert("验证码错误"); 
				}else{
					alert("用户名或手机号码已存在");
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#setEmailFrom").submit(function() {
		$("#setEmailFrom label[class=one1]").remove();
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if ($("#email").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
			$("#email").after(html);
		} else if (!isEmail.test($("#email").val())) {
			var str = "请输入正确的邮箱";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email").after(html);
		}
		validate("#emailValidateCode", "请输入验证码",20);
		if($("#setEmailFrom label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "修改成功"){
					alert("添加邮箱成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else{
					alert(data);
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#updateEmailFrom").submit(function() {
		$("#updateEmailFrom label[class=one1]").remove();
		validate("#emailValidateCode1", "请输入验证码",20);
		var isEmail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if ($("#email1").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入邮箱</label>');
			$("#email1").after(html);
		} else if (!isEmail.test($("#email1").val())) {
			var str = "请输入正确的邮箱";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#email1").after(html);
		}
		validate("#nowEmailValidateCode", "请输入验证码",20);
		if($("#updateEmailFrom label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "修改成功"){
					alert("修改邮箱成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else{
					alert(data);
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#setupPayCodeForm").submit(function() {
		$("#setupPayCodeForm label[class=one1]").remove();
		var html = $('<label class="one1" style="width:180px;">请输入支付密码</label>');
		var val = $("#payCode").val().trim();
		if (val == "") {
			$("#payCode").after(html);
		} else if ($("#payCode").val().length > 20) {
			var html = $('<label class="one1" style="width:180px;">长度不能大于20位</label>');
			$("#payCode").after(html);
		}
		if($("#confirm_payCode").val().trim() != $("#payCode").val().trim()){
			$("#confirm_payCode").next().remove(); 
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_payCode").after(html);
		}else{
			validate("#confirm_payCode", "请再次输入支付密码",20);
		}
		if($("#setupPayCodeForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : {"nowPayCode" :  md5($("#nowPayCode").val()), "payCode" : md5($("#payCode").val())},
			success : function(data) {
				if(data == "修改成功"){
					alert("添加支付密码成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else{
					alert("添加支付密码失败");
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#updatePayCodeForm").submit(function() {
		$("#updatePayCodeForm label[class=one1]").remove();
		var html = $('<label class="one1" style="width:180px;">请输入新支付密码</label>');
		var val = $("#payCode1").val().trim();
		if (val == "") {
			$("#payCode1").after(html);
		} else if ($("#payCode1").val().length > 20) {
			var html = $('<label class="one1" style="width:180px;">长度不能大于20位</label>');
			$("#payCode1").after(html);
		}
		if($("#confirm_payCode1").val().trim() != $("#payCode1").val().trim()){
			$("#confirm_payCode1").next().remove(); 
			var html = $('<label class="one1" style="width:180px;">两次密码不一致</label>');
			$("#confirm_payCode1").after(html);
		}else{
			validate("#confirm_payCode1", "请再次输入新支付密码",20);
		}
		if($("#updatePayCodeForm label[class=one1]").length > 0){
			return false;
		}
		var payCode = md5($("#payCode1").val());
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : {"nowPayCode" : md5($("#nowPayCode").val()), "payCode" : payCode},
			success : function(data) {
				if(data == "修改成功"){
					alert("修改支付密码成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else{
					alert("旧支付密码错误");
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#setupQuestionForm").submit(function() {
		$("#setupQuestionForm label[class=one1]").remove();
		validate("#validateCode", "请输入密保问题",20);
		validate("#validateCode", "请输入密保答案",20);
		if($("#setupQuestionForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "修改成功"){
					alert("设置安全问题成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else{
					alert("设置安全问题失败");
				}
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
	
	$("#setCertifiedForm").submit(function() {
		$("#setCertifiedForm label[class=one1]").remove();
		validate("#realname", "请输入真实姓名",20);
		validate("#cardId", "请输入身份证号码",18);
		if($("#setCertifiedForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/setupAccount.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "修改成功"){
					alert("设置实名认证成功");
					window.location.href = '/managermall/account/setupUserSafe.do';
				}else if(data == "身份证号码已存在"){
					alert(data);
				}else{
					alert("设置实名认证失败");
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
function setupPassword(){
	$("#setPassword").show();
}
function setTel(){
	$("#setTel").show();
}
function updateTel(){
	$("#updateTel").show();
}
function setEmail(){
	$("#setEmail").show();
}
function updateEmail(){
	$("#updateEmail").show();
}
function setPayCode(){
	$("#setPayCode").show();
}
function updatePayCode(){
	$("#updatePayCode").show();
}
function setQuestion(){
	$("#setQuestion").show();
}
function setCertified(){
	$("#setCertified").show();
}
function closeWindow(){
	$(".member_tanchubangding").hide();
	$("label[class=one1]").remove();
	$("#setTelFrom")[0].reset();
	$("#setupPasswordForm")[0].reset();
	$("#setEmailFrom")[0].reset();
	//$("#setupPayCodeForm")[0].reset();
	$("#setupQuestionForm")[0].reset();
	$("#updateTelFrom")[0].reset();
	$("#updateEmailFrom")[0].reset();

}
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