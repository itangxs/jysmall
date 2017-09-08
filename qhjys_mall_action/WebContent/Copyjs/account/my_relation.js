
$(function(){
	$("#phonecode").fouce(function(){
		if($("#phonecode").val()==""){
			$("#phonecode").val("");
		}
	}).blur(function(){
		if($("#phonecode").val()==""){
			$("#phonecode").val("请输入手机收到的验证码");
		}
	})
})
function vifitAccount(){
	var username = $("#account").val();
	var phone = $("#phoneNum").val();
	var token = $("#thirdtoken").val();
	var tokentime = $("#thirdtokentime").val();
	if(username == ""){
		alert("账号不能为空!");
		return false;
	}
	var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
	if(!isMobile.test(phone)){
		alert("请输入正确的手机号码!");
		return false;
	}
	$.ajax({
		type : "POST",
		url : '/managermall/account/verifyAccount.do',
		data : {username:username,phone:phone,token:token,tokentime:tokentime},
		success : function(data) {
			if (data.message == "success"){
				$("#username").val(data.username);
				$("#phone").val(data.phone);
				closeWindow1();
				setCodeShow();
			}else if(data.message == "timeerror"){
				closeWindow1();
				alert("本次验证操作超时！");
			}else if(data.message == "accounterror"){
				closeWindow1();
				alert("验证账号错误!！");
			}else if(data.message == "thirddayerror"){
				closeWindow1();
				alert("今日验证账号次数过多,请明日再试!！");
			}else{
				closeWindow1();
				alert("验证失败!！");
			}
		}
	});
}
function vifitCode(){
	var username = $("#username").val();
	var phone = $("#phone").val();
	var phonecode = $("#phonecode").val();
	if(phonecode.length != 6){
		alert("请输入正确的验证码!");
		return false;
	}
	$.ajax({
		type : "POST",
		url : '/managermall/account/verifyCode.do',
		data : {username:username,phone:phone,code:phonecode},
		success : function(data) {
			if (data.message == "success"){
				window.location.href=window.location.href;
			}else if(data.message == "codeerror"){
				alert("验证码错误！");
			}else{
				closeWindow2();
				alert("验证失败!！");
			}
		}
	});
}
function setAccountShow(thirdDay){
	if(thirdDay ==null || thirdDay <= 0 )
		return false;
	$.ajax({
		type : "POST",
		url : '/managermall/account/gettoken.do',
		data : {},
		success : function(data) {
			if (data.issuccess == "success"){
				$("#thirdtoken").val(data.thirdtoken);
				$("#thirdtokentime").val(data.thirdtokentime);
				$("#setAccount").show();
			}else {
					alert("未知错误！");
			}
		}
	});
}
function setCodeShow(){
	$("#setCode").show();
}
function closeWindow1(){
	$("label[class=one1]").remove();
	$("#account").val("");
	$("#phoneNum").val("");
	$("#setAccount").hide();
}
function closeWindow2(){
	$("label[class=one1]").remove();
	$("#phonecode").val("");
	$("#setCode").hide();
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