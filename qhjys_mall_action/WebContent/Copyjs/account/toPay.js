$(function() {
	$("input[name='payType']").change(function() {
		var payType = $("input[name='payType']:checked").val();
		var payNo = document.getElementById("payNo");
		if (payType == 1) {
			payNo.style.display = "block";
		} else {
			payNo.style.display = "none";
		}
	});

	$("#pointinput").change(function() {
		var cashIntegral = $("#cashIntegral").val();
		var payPoint = $("#pointinput").val();
		var payMoney = $("#payMoneyHidden").val();
		if (payPoint > cashIntegral) {
			$("#pointinput").val("");
			$("#payPoints").val(0);
			alert("积分不足!");
		} else if (payPoint > payMoney * 20) {
			$("#pointinput").val("");
			$("#payPoints").val(0);
			alert("使用积分超过商品价格!");
		} else {
			$("#payPoints").val(payPoint);
			$("#payMoney").html((payMoney - payPoint / 20).toFixed(2));
		}
	});

/*	$("#toPayForm").submit(function() {
		var payType = $("#payType").prop('checked');
		var token = $("#token").val();
		if(!$("#payType").prop('checked')){
			payType = 0;
		}else{
			payType = 1;
		}
		var orderId = $("#orderId").val();
		
		var payMoney = $("#payMoney").text();
		var accountMoney = $("#accountMoney").val();
		alert(payMoney+"||"+accountMoney)
		if(payMoney>accountMoney){
			alert("账号余额不足.请充值");
			return ;
		}
		var rechargeMoney = $("#rechargeMoney").val();
		var cardNo = $("#cardNo").val();
		$.ajax({
			async : true,
			type : "POST",
			url : '/managermall/account/order/payOrder.do',
			data : {"token" : token,
				"payType" : payType,
				"orderId" : orderId,
				"rechargeMoney": rechargeMoney,
				"cardNo" : cardNo},
			success : function(data) {
				if(data == "支付成功"){
					alert(data);
					window.location.href = '/index.do';
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
	});*/
	
});