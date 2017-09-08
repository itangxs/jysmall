function ifIdentify(){
	var code = document.getElementById('code').value;
	if(""==code){
		alert("认证编号不能为空");
	}else{
		document.getElementById('kaquan01').style.display='block';
		document.getElementById('fade').style.display='block';
	}
}

function identify(){
	var url = "/managermall/seller/kaquan/identify.do";
	var data = "code=" + $("#code").val();
	$.ajax({
		async : false,
		type : "post",
		url : url,
		data : data,
		success : function(result) {
			if (result == "success") {
				alert("认证成功");
				window.location.reload();
			}else if(result == "already"){
				document.getElementById('kaquan02').style.display='block';
				document.getElementById('fade').style.display='block';
			}else if(result == "expire"){
				alert("卡券已过期");
			}else if(result == "null"){
				alert("认证编号有误");
			}else if(result == "incorrect"){
				alert("非礼品券不能认证");
			}else{
				alert("认证失败");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
	document.getElementById('kaquan01').style.display='none';
	document.getElementById('fade').style.display='none';
}

function excel(){
	var b=$("#excel").serialize();
	var c="&conpouCfg="+$('#cardType').val()+"&pageNum="+$('#cardExcel').val();
	if($('#tjsjs').val()!=null&&$('#tjsjs').val()!=""&&$('#tjsje').val()!=null&&$('#tjsje').val()!=""){
		c += "&startDate="+$('#tjsjs').val()+"&endDate="+$('#tjsje').val();
	}
	var a="/managermall/seller/kaquan/cardExcel.do?"+b+c;
	window.open(a);
}

function delivery(){
	var b=$("#excel1").serialize();
	var c="&getWay="+$('#deliveryType').val()+"&pageNum="+$('#deliExcel').val();
	if($('#tjsjs').val()!=null&&$('#tjsjs').val()!=""&&$('#tjsje').val()!=null&&$('#tjsje').val()!=""){
		c += "&startDate="+$('#tjsjs').val()+"&endDate="+$('#tjsje').val();
	}
	var a="/managermall/seller/kaquan/deliveryExcel.do?"+b+c;
	window.open(a);
}
function validate(){
	var b=$("#excel2").serialize();
	var c="&conpouCfg="+$('#validateType').val()+"&pageNum="+$('#valiExcel').val();
	if($('#tjsjs').val()!=null&&$('#tjsjs').val()!=""&&$('#tjsje').val()!=null&&$('#tjsje').val()!=""){
		c += "&startDate="+$('#tjsjs').val()+"&endDate="+$('#tjsje').val();
	}
	var a="/managermall/seller/kaquan/validateExcel.do?"+b+c;
	window.open(a);
}

function getType(selObj,which){
	var selectValue = selObj.options[selObj.selectedIndex].value;
	var url="/managermall/seller/kaquan/";
	var data = "";
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var card = $("#cardType").val();
	var delivery = $('#deliveryType').val();
	var validate = $('#validateType').val();
	if(which=="card"){
		url = url+"carddata.do";
		data = "couponCfg="+selectValue;
	}else if (which=="delivery") {
		url = url+"deliverydata.do";
		data = "getWay="+selectValue;
	}else if (which=="validate") {
		url = url+"validatedata.do";
		data = "couponCfg="+selectValue;
	}
	data += "&startTime="+startTime+"&endTime"+endTime;
	$.ajax({
		async : false,
		type : "get",
		url : url,
		data : data,
		success : function(result) {
			var data = $(result);
			if(which=="card"){
				$("#excel").html(data.find("#excel"));
				$("#cardType").val(card);
				$("#couponCfg").val(data.find("#couponCfg")[0].value);
			}else if (which=="delivery") {
				$("#excel1").html(data.find("#excel1"));
				$('#deliveryType').val(delivery);
				$("#getWay").val(data.find("#getWay")[0].value);
			}else if (which=="validate") {
				$("#excel2").html(data.find("#excel2"));
				$('#validateType').val(validate);
				$("#couponCfg").val(data.find("#couponCfg")[0].value);
			}
			if(selectValue!=""){
				$(".page").html(data.find(".page"));
				$("#center").html(data.find("#center"));
			}
			console.log("成功");
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function changedatenum(datenum){
	$("#datenum").val(datenum);
	if (datenum!='') {
		document.getElementById('from').submit();
	}
}
