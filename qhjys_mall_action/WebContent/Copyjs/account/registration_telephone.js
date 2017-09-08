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
	
	$("#registrationByEmail").removeClass("tabaction");
	$('#sendSMS').on("click", function() {
		
		//校验验证码
		var result=true;
		var margin=$('#identifyingCode').val();
		var param = 'codeYzm='+margin;
		var urlval = '/valiCode/codeyzm.do';
		   $.ajax({
		    	type:'post',//可选get
		    	url:urlval,//这里是接收数据的后台程序
		    	data:param,//传给后台的数据，多个参数用&连接
		    /*	dataType:'json',//服务器返回的数据类型 可选xml, json, script, or html,text等,建议还回json
	*/	    	async:false,
		    	success:function(msg){
			    	if(msg=='success'){
			    		result=true;
			    	}else{
			    		alert("验证码错误,请输入正确的验证码");
			    		result=false;
			    	}
			    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function(e){
		    	//ajax提交失败的处理函数！
		    		result=false;
		    	}
		    });
		
		if(!result){
			return;
		}
		
		//校验验证码 结束
		
		
		
		
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
	$("#phoneNum").blur(function(){
		$("#phoneNum").next().remove();
		$("#phoneNum").attr("class","inputkk");
		var html = $('<label class="one1" style="width:180px;">请输入手机号码</label>');
		var val = $("#phoneNum").val().trim();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if (val == "") {
			$("#phoneNum").after(html);
			$("#phoneNum").attr("class","inputkkred");
		} else if (!isMobile.test($("#phoneNum").val())) {
			str = "请输入正确的手机号";
			html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phoneNum").after(html);
			$("#phoneNum").attr("class","inputkkred");
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
	$("#validateCode").blur(function(){
		validate(this, "请输入验证码",20);
	});
	$("#registrationForm").submit(function() {
		$("#registrationForm label[class=one1]").remove();
		var isMobile=/^(?:13\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
		if ($("#phoneNum").val().trim() == "") {
			var html = $('<label class="one1" style="width:180px;">请输入手机号码,</label>');
			$("#phoneNum").after(html);
			$("#phoneNum").attr("class","inputkkred");
		} else if (!isMobile.test($("#phoneNum").val())) {
			var str = "请输入正确的手机号";
			var html = $('<label class="one1" style="width:180px;">' + str + '</label>');
			$("#phoneNum").after(html);
			$("#phoneNum").attr("class","inputkkred");
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
		validate("#validateCode", "请输入验证码",20);
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