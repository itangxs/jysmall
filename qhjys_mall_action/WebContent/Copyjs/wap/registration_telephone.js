$(document).ready(function() {
	 $.formValidator.initConfig({formID:"registrationForm",autoTip:true,onError:function(msg){alert(msg);},inIframe:true});
	 $("#phone").formValidator({onShow:"请输入你的手机号码",onFocus:"必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"});;
	 $("#username").formValidator({onShow:"请输入用户名",onFocus:"用户名至少6个字符,最多30个字符",onCorrect:""}).inputValidator({min:6,max:30,onError:"你输入的用户名非法,请确认"})
	 $("#margin5").formValidator({onShow:"请输入验证码",onFocus:"6位数验证码",onCorrect:""}).inputValidator({min:6,max:6,onError:"错误"});
	 $("#password").formValidator({onShow:"请输入密码",onFocus:"至少6个长度,最多20",onCorrect:""}).inputValidator({min:6,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
	 $("#confirm_password").formValidator({onShow:"输再次输入密码",onFocus:"至少6个长度,最多20",onCorrect:""}).inputValidator({min:6,max:20,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"2次密码不一致,请确认"});
	 $("#recomId").formValidator({empty:true,onShow:"请输入你的推荐码，可以为空",onFocus:"必须是数字",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留推荐码？"}).regexValidator({regExp:"^[0-9]\\d*$",onError:"推荐码必须是数字.!"});
 
}); 



 //倒计时
var wait=120;
function settime() {
	var reg = /^(13|14|15|17|18)[0-9]{9}$/;
	var phone = document.getElementById("phone").value;
	
	
	if(!reg.test(phone)){
		alert("请输入有效的手机号码");
	}else{
		var param = 'phone='+phone;
		var urlval = '/sendSMSCaptcha.do?phone='+phone;
		   $.ajax({
			   async : false,
				type : "POST",
				dataType:"text",
				url :  urlval,
		    	success:function(data){
		    		if (data == "0000"){ 
		    			result=true;
		    			settime1();
		    			return null;
		    		} 
					else {
						result=false;
						if (data == "0001")
							alert("手机号码格式错误！");
						else
							alert("发送短信异常！");
						return null;
					}
		    		
		    		
			    	/*if(msg.state=='false'){
			    		result=true;
			    		settime1();
			    	}
			    	if(msg.state=='true'){
			    		result=false;
			    		alert('手机号码已经存在');
			    	}*/
			    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
		    	},
		    	error:function (XMLHttpRequest, textStatus, errorThrown) 
		    	{ 
		    		if (4==XMLHttpRequest.readyState){
		    			settime1();
		    			return null;
		    		}else{
		    			alert(textStatus);
			    		alert(XMLHttpRequest.readyState);
		    		}
		    		
		    		
		    	} 
		    });
	}
}

function settime1() {
 	if (wait == 0) {
 		var der = document.getElementById("huoqu");
		der.innerHTML="<a href=\"javascript:settime()\" >获取验证码</a>"; 
		wait = 120;
	}else {
		if(wait == 120){
	 		wait--;
	 		//phoneCode();	
	 	}
		var der = document.getElementById("huoqu");
		der.innerText="重新发送(" + wait + ")"; 
		der.href="javascript:settime()";
		wait--;
		setTimeout(function() {
			settime1();
		},1000);
	}
}






