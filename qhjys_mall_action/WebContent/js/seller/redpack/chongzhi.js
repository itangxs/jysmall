function showRecords(){
	if(isNaN($("#czje").val())||$("#czje").val() == ''||$("#czje").val()<=0){
		alert("请输入正确金额")
		return false;
	}
	if($("#czpassw").val().length<=0){
		alert("请输入密码")
		return false;
	}
	$("#qrcz").html("¥"+$("#czje").val());
	document.getElementById('cktxjl').style.display='block';
	document.getElementById('fade').style.display='block';
}

function chongzhitijiao(){
	$.ajax({
		async : true,
		type : "post",
		url : "/managermall/seller/redpack/chongzhitijiao.do",
		data : {'password':$("#czpassw").val(),'money':$("#czje").val()},
		success : function(g) {
			if (g == "充值成功") {
				$("#czcgts").html("恭喜，您已成功充值"+$("#czje").val()+"元！<br>赶快去新建红包活动吧！");
				document.getElementById('cktxj2').style.display='block';
			}else if(g == "账户余额不足"){
				document.getElementById('cktxj3').style.display='block';
			}else{
				alert(g)
			}
		},
		error : function() {
			alert("服务器忙");
			window.location.href = window.location.href;
		}
	});
}
function jixuchongzhi(){
	window.location.href = window.location.href;
}