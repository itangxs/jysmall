
//微信支付接口调用
function callpay(res){
	var orderId = res.orderId;
	 WeixinJSBridge.invoke('getBrandWCPayRequest',
			 {"appId" : res.appId,"timeStamp" : res.timeStamp, "nonceStr" : res.nonceStr, "package" : res.package,"signType" : res.signType, "paySign" : res.paySign
	}, function(rs) {
		if(rs.err_msg == "get_brand_wcpay_request:cancel"){
			$('#wxpaySubmitBtn').attr("onclick","submitOrder()");
		}else if(rs.err_msg == "get_brand_wcpay_request:ok"){
			location.href = '/wxMall/orderRedpack.do?orderId='+orderId;
		}else{
			alert("支付出错,请刷新页面重新支付!");
		}
	});
}

//QQ支付接口调用
function callCqpay(res){
	var prepay_id = res.prepay_id;
	var orderId = res.orderId;
	 mqq.tenpay.pay(
	    {tokenId: prepay_id},
	     function(result){
	    	var resultCode = result.resultCode;
	    	if(resultCode==0||resultCode=="0"){
	    		var transaction_id = result.data.transaction_id;
	    		var pay_time = result.data.pay_time;
	    		var total_fee = result.data.total_fee;
	    		location.href = "/wxMall/orderDetail.do?orderId="+orderId;
	    	}else{
	    		alert("支付出错,请刷新页面重新支付!"+result.retmsg);
	    	}
	 });
}


//打折店铺列表初始化
function initStoreList(){
	
	 $("#pageNum").val(1);
	 $("#getmore").attr("href","javascript:getNextPage()");
	 $("#getmore").text("加载更多");
	 storeList(1);
}

//获取下一页数据
function getNextPage(){
	var pageNum = $("#pageNum").val();
	var nextPage = parseInt(pageNum)+1;
	$("#pageNum").val(nextPage);
	storeList(nextPage);
}

//跳转到买单页面
function toTurnRebateDetail(rebateId){
	location.href="/wxMall/storeDetail.do?rebateId="+rebateId; 
}


//分页查询打折店铺列表
function storeList(pageNum){
	$.ajax({
		url:"/wxMall/rebateStoreList.do",
		type: "POST",
		data: "pageNum="+pageNum,
		success: function(result){
			if(result.code == "0"){
				if(result.data.page == null ){
					$("#getmore").attr("href","javascript:");
					$("#getmore").text("没有更多了");
				} else{
					$.each(result.data.page,function(index,item){
						$("#storeList").append("<li> <div class=\"proimg\"> "+
								"<a href=\"javascript:toTurnRebateDetail("+item.rebateId+")\"><img src=\""+item.logo+"\" ></a></div><div class=\"centerinfo\">"+
								"<div class=\"zhekoutext\"><b>"+item.rebate+"折</b><a href=\"javascript:toTurnRebateDetail("+item.rebateId+")\" class=\"orderbuttonbox\">去买单</a></div>"+
								"<h1><a href=\"javascript:toTurnRebateDetail("+item.rebateId+")\">"+ item.name+"</a></h1>" +
								"<h2>"+item.area+"<br>"+item.address+"</h2></div>"+
								/*"<div class=\"orderbutton\"><a href=\"javascript:toTurnRebateDetail("+item.rebateId+")\">去买单 /a></div>"+*/
								"<div class=\"clear\"></div></li>"
								
								/*$("#storeList").append("<li> <div class=\"proimg\"> "+
									"<img src=\""+path/item.logo+"\" ></div><div class=\"centerinfo\">"+
									"<div class=\"zhekoutext\"><b>"+item.rebate+"</b>折</div>"+
									"<h1>"+ item.name+"</h1>" +
									"<h2>"+item.area+"<br>"+item.address+"</h2></div>"+
									"<div class=\"orderbutton\"><a href=\"order.html?rebateId="+item.rebateId+"\">去买单</a></div>"+
									"<div class=\"clear\"></div></li>"*/
						);
					});
				}
			
			}else{
				alert(result.msg);
			}
		},
		error: function(){
			alert("请求后台数据失败！");
		}
	});
	
}

function initStoreDetail(){
	var totamt = parseFloat($("#totamt").val()); //消费总金额
	var noDiscount = parseFloat($("#noDiscount").val()); //不参与优惠的金额
	var rebate = parseFloat($("#rebate").val()); //折扣
	var realpay = parseFloat($("#realPay").val()); //实际支付金额
	$("#totalDiscount").text("¥"+Math.round((totamt-realpay)*100)/100);
	$("#storeDiscount").text("¥"+Math.round((totamt-noDiscount)*(1-rebate*0.1)*100)/100);
}

function removezoer(){
	var totamt = $("#totamt").val();
	if (totamt == 0) {
		$("#totamt").val("");
	}
}
function appendzoer(){
	var totamt = $("#totamt").val();
	if (totamt == "") {
		$("#totamt").val(0);
	}
}
function removezoer1(){
	var noDiscount = $("#noDiscount").val();
	if (noDiscount == 0) {
		$("#totamt").val("");
	}
}
function appendzoer1(){
	var noDiscount = $("#noDiscount").val();
	if (noDiscount == "") {
		$("#totamt").val(0);
	}
}
//计算总价格
function calcPrice(){
	var reg=/^\d+([.][0-9]{0,2}){0,2}$/;
	var totamt = parseFloat($("#totamt").val()); //消费总金额
	var noDiscount = parseFloat($("#noDiscount").val()); //不参与优惠的金额
	var rebate = parseFloat($("#rebate").val()); //折扣
	var realpay; //实际支付金额
	var dicount; //优惠金额
	if(totamt < 0){
		alert("请输入消费总金额");
		return;
	}
	
	if(!reg.test($("#totamt").val())&&$("#totamt").val()!=""&&parseFloat(totamt)>0){
		alert("请输入两位小数以内的真实金额,请不要带空格！");
		return;
		}
	if(!reg.test($("#noDiscount").val())&&$("#noDiscount").val()!=""&&parseFloat(noDiscount)>0){
		alert("请输入两位小数以内的真实金额,请不要带空格！");
		return;
		}
	
	if(totamt < noDiscount){
		alert("不参与优惠金额不能超过消费总金额");
		return;
	}
	//计算优惠金额
	if(noDiscount > 0){
		realpay = (totamt-noDiscount)*rebate*0.1 + noDiscount;
		dicount = totamt - realpay;
	}else{
		realpay = totamt*rebate*0.1;
		dicount = totamt - realpay;
	}
	if (dicount>=0) {
		$("#discount").text("- ¥"+Math.round(dicount*100)/100);
	}else{
		$("#discount").text("");
	}
	if (realpay>=0) {
		$("#realPay").text("¥"+Math.round(realpay*100)/100); 
	}else{
		$("#realPay").text(""); 
	}
	 //给优惠金额赋值
	   //给实付金额赋值
}

//提交订单请求，发起支付
function payOrder(){
	//var path = $("#path").val();
	var reg=/^\d+([.][0-9]{1,2}){0,2}$/;
	var totamt = $.trim($("#totamt").val().replace("￥",""));//消费总金额
	var noDiscount = 0; //不参与优惠的金额
	var rebate = $("#rebate").val(); //折扣
	var rebateId = $("#rebateId").val(); //折扣id
	var sellerId =$("#sellerId").val(); //卖家id
	var storeName =$("#storeName").val(); //店铺名字
	var openId =$("#openId").val(); //店铺名字
	var storeId =$("#storeId").val(); //店铺id
	var nickname =$("#nickname").val(); //店铺id
	var headimgurl =$("#headimgurl").val(); //店铺id
	
	var realpay; //实际支付金额
	
	if(parseFloat(totamt) < 0){
		alert("请输入消费总金额");
		return;
	}
	
//	if(parseFloat(totamt) < parseFloat(noDiscount)){
//		alert("不参与优惠金额不能超过消费总金额");
//		return;
//	}
	if(!reg.test(totamt)&&$("#totamt").val()!=""){
		alert("请输入两位小数以内的真实金额,请不要带空格");
		return;
		}
//	if(!reg.test(noDiscount)&&$("#noDiscount").val()!=""){
//		alert("请输入两位小数以内的真实金额,请不要带空格");
//		return;
//		}
	//计算优惠金额
//	if(parseFloat(noDiscount) > 0){
//		realpay = (parseFloat(totamt)-parseFloat(noDiscount))*parseFloat(rebate)*0.1 + parseFloat(noDiscount);
//		realpay = Math.round(realpay*100)/100;
//	}else{
		realpay = parseFloat(totamt)*parseFloat(rebate)*0.1;
		realpay = Math.round(realpay*100)/100;
//	}
	if (isNaN(realpay) || realpay<=0) {
		alert("请输入消费金额");
		return false;
	}
//	if (noDiscount == "") {
//		noDiscount = 0;
//	}
	var data = "totamt="+realpay+"&noDiscount="+noDiscount+"&rebate="+rebate+"&rebateId="
	           +rebateId+"&sellerId="+sellerId+"&storeId="+storeId+"&storeName="+storeName+"&realPay="+realpay+"&needPay="+totamt+"&openId="+openId+"&headimgurl="+headimgurl+"&nickname="+nickname;
	var payType = $("#payType").val();
	if (payType == 0) {
		$.ajax({
			url: "/wechatPay/toPayOrderAlipay.do",
			type: "POST",
			data: data,
			success: function(result){
				if(result.code == "0"){
					$(document.body).append(result.sHtmlText);
				}else if (result.code == "2002"){//打折参数不符合
					window.confirm('打折信息有变动请确定重新刷新！');
					location.href="/user/fqorder/toOrderDetail.do?orderId="+orderId;
				}else{
					alert("调用支付异常！请重试!");
				}
			},
			error: function(){
				alert("系统繁忙请稍后再试！");
			}
		});
		/*$.ajax({
			url: "/wechatPay/toPayOrderAlipayGD.do",
			type: "POST",
			data: data,
			success: function(result){
				if(result.code == "0"){
				    AlipayJSBridge.call("tradePay",{tradeNO:result.tradeNO},function(data){
				    	if(data.resultCode=="9000"||data.resultCode==9000){
				    		location.href = "/wxMall/orderDetail.do?orderId="+result.orderId;
				    	}
				    });
				}else if (result.code == "2002"){//打折参数不符合
					window.confirm('打折信息有变动请确定重新刷新！');
					location.href="/user/fqorder/toOrderDetail.do?orderId="+orderId;
				}else{
					alert("调用支付异常！请重试!");
				}
			},
			error: function(){
				alert("系统繁忙请稍后再试！");
			}
		});
		*/
	}else if(payType == 1){
		$.ajax({
			url: "/wechatPay/toPayOrderSZPFWX.do",
			type: "POST",
			data: data,
			success: function(result){
				if(result.code == "0"){
					callpay(result); //微支付
				}else if (result.code == "40007"){//打折参数不符合
					window.confirm('打折信息有变动请确定重新刷新！');
					location.href="/wxMall/storeDetail.do?rebateId="+rebateId;
				}else if (result.code == "99999"){//系统错误
					alert("系统繁忙请稍后再试！");
				}
			},
			error: function(){
				alert("系统繁忙请稍后再试！");
			}
		});

	}else if(payType == 2){ //qq钱包支付
		$.ajax({
			url: "/wechatPay/toPayOrderQpay.do",
			type: "POST",
			data: data,
			success: function(result){
				if(result.code == "0"){
					callCqpay(result); //QQ支付
				}else if (result.code == "40007"){//打折参数不符合
					window.confirm('打折信息有变动请确定重新刷新！');
					location.href="/wxMall/storeDetail.do?rebateId="+rebateId;
				}else if (result.code == "99999"){//系统错误
					alert("系统繁忙请稍后再试！");
				}
			},
			error: function(){
				alert("系统繁忙请稍后再试！");
			}
		});

	}
}
