function yudingfukuan(){
	$("#yuding").attr("class","current");
	$("#quane").attr("class","");
	$("#payType").val(0);
}
function quanefukuan(){
	$("#yuding").attr("class","");
	$("#quane").attr("class","current");
	$("#payType").val(1);
}
function payorder(){
	
	var orderId = $("#orderId").val();
	var data = "orderId="+orderId;
	var payType = $("#payType").val();
	var channelVali = $("#channelVali").val();
//	if (payType == 1) {
	if(channelVali == 1){
		$.ajax({
			url: "/wxMall/toPayOrderFqStoreByWXMS.do",
			type: "POST",
			data: data,
			success: function(result){
				if(result.code == "0"){
					callpay(result); //微支付
				}else if (result.code == "40007"){//打折参数不符合
					window.confirm('打折信息有变动请确定重新刷新！');
					location.href="/user/fqorder/toOrderDetail.do?orderId="+orderId;
				}else if (result.code == "99999"){//系统错误
					alert("系统繁忙请稍后再试！");
				}else{
					alert("调用支付异常！");
				}
			},
			error: function(){
				alert("系统繁忙请稍后再试！");
			}
		});
	}else{
		$.ajax({
			url: "/user/fqorder/toPayOrderByGd.do",
			type: "POST",
			data: data,
			success: function(result){
				if(result.code == "0"){
					callpay(result); //微支付
				}else if (result.code == "40007"){//打折参数不符合
					window.confirm('打折信息有变动请确定重新刷新！');
					location.href="/user/fqorder/toOrderDetail.do?orderId="+orderId;
				}else if (result.code == "99999"){//系统错误
					alert("系统繁忙请稍后再试！");
				}else{
					alert("调用支付异常！");
				}
			},
			error: function(){
				alert("系统繁忙请稍后再试！");
			}
		});
	}
		
//	}else if(payType == 0){
//		window.location.href="/user/fqorder/toAlipay.do?"+data;
//	}
	
}
function callpay(res){
	var orderId = $("#orderId").val();
	var openid = $("#openId").val();
	var orderNo = res.orderNo;
	 WeixinJSBridge.invoke('getBrandWCPayRequest',
			 {"appId" : res.appId,"timeStamp" : res.timeStamp, "nonceStr" : res.nonceStr, "package" : res.package,"signType" : res.signType, "paySign" : res.paySign
	}, function(rs) {
		if(rs.err_msg == "get_brand_wcpay_request:cancel"){
			$('#wxpaySubmitBtn').attr("onclick","submitOrder()");
		}else if(rs.err_msg == "get_brand_wcpay_request:ok"){
			$.ajax({
				url: "/wxMall/queryPayStatus.do",
				type: "POST",
				data: "orderNo="+orderNo,
				success: function(result){
					if(result.code==0){
						alert("支付已完成!");
						location.href = '/wxMall/fqorderDetail.do?id='+orderId+"&openId="+openid;
					}
				}
			});
		}else{
			alert("支付出错,请刷新页面重新支付!");
		}
	});
}