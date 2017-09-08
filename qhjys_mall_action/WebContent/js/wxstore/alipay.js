function getstatus(){
		var orderId = $("#orderId").val();
		var data = "orderId="+orderId;
		$.ajax({
		url: "/user/fqorder/getOrderStatus.do",
		type: "POST",
		data: data,
		success: function(result){
			if(result == "2"){
				alert("支付完成");
				window.location.href="/wxMall/fqStoreList.do";
			}
		}
		});
}
$(function(){
//	 var status = $("#status").val();
//	 if (status == 2) {
//		 $("#banner").hide();
//		 return false;
//	}else{
	    var ua = navigator.userAgent.toLowerCase();  
	    if(ua.match(/MicroMessenger/i)=="micromessenger") {  
	    	$("#banner").show();
	    	var t1 = window.setInterval(getstatus,5000); 
	    } else {  
	       $("#banner").hide();
	       var orderId = $("#orderId").val();
	   		var data = "orderId="+orderId;
	   		$.ajax({
				url: "/user/fqorder/toPayOrderAlipay.do",
				type: "POST",
				data: data,
				success: function(result){
					if(result.code == "0"){
						$("#alipaydiv").append(result.sHtmlText);
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
//	    }  
	}
});