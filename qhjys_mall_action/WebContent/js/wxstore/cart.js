var productcookie = null;
var productNum = 0;
var producttotalprice = 0;
var storeId = 0;
$(document).ready(function() {
	storeId = $("#storeId").val();
	if ( $.cookie('productcookie'+storeId) != null) {
		productcookie = $.cookie('productcookie'+storeId);
	}
	if ( $.cookie('productNum'+storeId) != null) {
		productNum = $.cookie('productNum'+storeId);
	}
	if ( $.cookie('producttotalprice'+storeId) != null) {
		producttotalprice = $.cookie('producttotalprice'+storeId);
	}
	if (productcookie == null) {
		$(".number").val(0);
	}
	var json = $.parseJSON(productcookie);
	$.each(json, function(key, value) {
			$("#numberinput"+key).val(value);
		}); 
	$("#howmany").html(productNum);
	$("#howmuch").html(parseFloat(producttotalprice).toFixed(2));
	if (storeId != 3156) {
	$("#deskNo").focus(function(){
		var deskNo = $("#deskNo").val();
		if (deskNo=="请输入您的桌号") {
			$("#deskNo").val("");
		}
	}).blur(function(){
		var deskNo = $("#deskNo").val();
		if (deskNo=="") {
			$("#deskNo").val("请输入您的桌号");
		}
	});
	}
});
function cartminusnum(id){
	var num = $("#numberinput"+id).val();
	if(num > 0){
		$("#numberinput"+id).val(num-1);
		var howmany = parseInt($("#howmany").html())-1;
		var howmuch = parseFloat($("#howmuch").html())-$("#productprice"+id).val()*1;
		$("#howmany").html(howmany);
		$("#howmuch").html(parseFloat(howmuch).toFixed(2));
		if (productcookie != null) {
			var json = $.parseJSON(productcookie);
			json[id]=num-1;
			productcookie = JSON.stringify(json);
			$.cookie("productcookie"+storeId, productcookie,  {path:'/'}); 
		}
		$.cookie("productNum"+storeId, howmany, {path:'/'}); 
		$.cookie("producttotalprice"+storeId, howmuch, {path:'/'}); 
	}
}

function cartplusnum(id){
	var num = parseInt($("#numberinput"+id).val());
	var stock = $("#productstock"+id).val();
	if (parseInt(stock)<=num) {
		alert("该商品库存不足");
	}else{
		var howmany = parseInt($("#howmany").html())+1;
		var howmuch = parseFloat($("#howmuch").html())+$("#productprice"+id).val()*1;
		$("#numberinput"+id).val(num + 1);
		$("#howmany").html(howmany);
		$("#howmuch").html(parseFloat(howmuch).toFixed(2));
		if (productcookie != null) {
			var json = $.parseJSON(productcookie);
			json[id]=num+1;
			productcookie = JSON.stringify(json);
			$.cookie("productcookie"+storeId, productcookie,  {path:'/'}); 
		}
		$.cookie("productNum"+storeId, howmany, {path:'/'}); 
		$.cookie("producttotalprice"+storeId, howmuch, {path:'/'}); 
	}
}

function godishesdetail(storeId,prodId){
	var input=document.getElementsByName("productId");
	var ids = "";
	var nums = "";
	for(var i=0; i<input.length; i++){
		ids += input[i].value+",";
		nums += $("#numberinput"+input[i].value).val()+",";
	}
	ids = ids.substring(0,ids.length-1);
	nums = nums.substring(0,nums.length-1);
	window.location.href = "/store/fqproducts/dishesdetail.do?storeId="+storeId+"&prodId="+prodId+"&ids="+
	ids+"&nums="+nums;
}

function confirmcart(){
	var deskNo = $("#deskNo").val();
	var peopleNum = $("#peopleNum").val();
	var username = $("#username").val();
	var phoneNum = $("#phoneNum").val();
	if (storeId != 3156) {
		if (deskNo=="请输入您的桌号" || deskNo.trim()=="" || deskNo.trim().length<1) {
			alert("请填写你的桌号");
			return false;
		}else if(deskNo.length>10){
			alert("桌号长度超出限制");
			return false;
		}
	}else{
		if (peopleNum.trim()=="" || peopleNum.trim().length<1) {
			alert("请填写你的商家名称");
			return false;
		}
		if (username.trim()=="" || username.trim().length<1) {
			alert("请填写你的姓名");
			return false;
		}
		if (phoneNum.trim()=="" || phoneNum.trim().length<1) {
			alert("请填写你的电话");
			return false;
		}
		if (deskNo.trim()=="" || deskNo.trim().length<1) {
			alert("请填写你的地址");
			return false;
		}
	}
	var ids = "";
	var nums = "";
	var json = $.parseJSON(productcookie);
	$.each(json, function(key, value) {
		if (value >0) {
			ids+=key+",";
			nums+=value+",";
		}	
	}); 
	ids = ids.substring(0,ids.length-1);
	nums = nums.substring(0,nums.length-1);
	var userId= $("#userId").val();
	if (ids.length >0) {
		$.ajax({
	    	type:'post',//可选get
	    	url:"/user/fqorder/confrimOrderPF.do",//这里是接收数据的后台程序
	    	data:{"ids":ids,"nums":nums,"storeId":storeId,"userId":userId,"deskNo":deskNo,"peopleNum":peopleNum,"username":username,"phoneNum":phoneNum},//传给后台的数据，多个参数用&连接
	    	success:function(data){
	    		if (data.code=='success'){
	    			$.cookie("productcookie"+storeId, null,  {path:'/'}); 
	    			$.cookie("productNum"+storeId, null, {path:'/'}); 
	    			$.cookie("producttotalprice"+storeId, null, {path:'/'}); 
	    			window.location.href="/user/fqorder/toOrderDetail.do?orderId="+data.orderId;
	    		}else if (data.code=='error'){
					if (data.errorcode == -3) {
						alert("部分菜品库存不足请重新选择");
					}else{
						alert("提交订单失败");
					}
	    		}
	    	//这里是ajax提交成功后，java程序返回的数据处理函数。msg是返回的数据，数据类型在dataType参数里定义！如果是josn,就可以直接点属性出来，列如：msg.name,msg.url 等等
	    	},
	    	error:function(){
	    		alert("提交订单出现异常");
	    	}
	    });
	}else{
		alert("未选择菜品!");
	}
	
}
function hidefade(){
	$("#zhuohao").hide();
	$("#fade").hide();
}