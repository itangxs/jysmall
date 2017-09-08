function excel() {
	var urlval = '/managermall/seller/funds/account/overviewExcel.do?';
	 window.open(urlval);
}

function goTiXian(){
		var token = $("#token").val();
		var money = $("#tiMoney").val();
		if (money <= 0 ) {
			alert("提现金额必须大于0");
		}else {
			var b = "/managermall/seller/funds/account/sellerWithdraw.do";
			var a = "token=" + token + "&withdrawsMoney=" + money;
			$.ajax({
				async : true,
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "login") {
						alert("请先登录");
						window.location.href = "/seller/login.do";
					}else if (g == "timelimit") {
						alert("系统正在出账中,请稍后1分钟!");
						window.location.href = "/managermall/seller/funds/account/overview.do";
					}else if (g == "tokenError") {
						alert("请不要重复提交");
						window.location.href = "/managermall/seller/funds/account/overview.do";
					}else if (g == "success") {
						alert("申请成功");
						window.location.href = "/managermall/seller/funds/account/overview.do";
					}else if (g == "less") {
						alert("余额不足");
						window.location.href = "/managermall/seller/funds/account/overview.do";
					}else if (g == "bank") {
						alert("银行账号异常");
						window.location.href = "/managermall/seller/funds/account/overview.do";
					}else {
						alert("提现异常");
						window.location.href = "/managermall/seller/funds/account/overview.do";
					}
				},
				error : function() {
					alert("服务器忙");
					window.location.href = "/managermall/seller/funds/account/overview.do";
				}
			});
		}
}

function showRecords(){
	document.getElementById('cktxjl').style.display='block';
	document.getElementById('fade').style.display='block';
}

