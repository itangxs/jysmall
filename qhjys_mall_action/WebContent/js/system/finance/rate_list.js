function insertStoreRate() {
	var rateName = $("#rateName").val();
	var wechatBaseRate = $("#wechatBaseRate").val();
	var wechatAppendRate = $("#wechatAppendRate").val();
	var wechatAppendMoney = $("#wechatAppendMoney").val();
	var alipayBaseRate = $("#alipayBaseRate").val();
	var alipayAppendRate = $("#alipayAppendRate").val();
	var alipayAppendMoney = $("#alipayAppendMoney").val();
	var qqpayBaseRate = $("#qqpayBaseRate").val();
	var qqpayAppendRate = $("#qqpayAppendRate").val();
	var qqpayAppendMoney = $("#qqpayAppendMoney").val();
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/insertFqStoreRate.do",
		data : {
			"rateName" : rateName,
			"wechatBaseRate" : wechatBaseRate,
			"wechatAppendRate" : wechatAppendRate,
			"wechatAppendMoney" : wechatAppendMoney,
			"alipayBaseRate" : alipayBaseRate,
			"alipayAppendRate" : alipayAppendRate,
			"alipayAppendMoney" : alipayAppendMoney,
			"qqpayBaseRate" : qqpayBaseRate,
			"qqpayAppendRate" : qqpayAppendRate,
			"qqpayAppendMoney" : qqpayAppendMoney,
		},
		success : function(rate) {
			if (rate == "success") {
				window.location.href = "/managermall/systemmall/finance/rateList.do";
			}
		},
		error : function(rate) {
			console.log(rate);
		}
	});
}
function deleteFun(divId,numId) {
	document.getElementById(divId).style.display = 'block';
	document.getElementById('fade').style.display = 'block';
	$("#numId").val(numId);
}

function onLoad() {
	document.getElementById('deleterate').style.display = 'none';
	document.getElementById('fade').style.display = 'none';
}

function delet(id) {
	console.log(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/deleteFqStoreRate.do",
		data : {"id":id},
		success : function(b) {
			if (b == "success") {
				window.location.href = "/managermall/systemmall/finance/rateList.do";
			} else {
				alert("删除失败");
			}
		},
		error : function(e) {
			console.log(e);
		}
	});
}
function updateRate(id){
	var wechatBaseRate1 = $("#wechatBaseRate1").val();
	var wechatAppendRate1 = $("#wechatAppendRate1").val();
	var wechatAppendMoney1 = $("#wechatAppendMoney1").val();
	var alipayBaseRate1 = $("#alipayBaseRate1").val();
	var alipayAppendRate1 = $("#alipayAppendRate1").val();
	var alipayAppendMoney1 = $("#alipayAppendMoney1").val();
	var qqpayBaseRate1 = $("#qqpayBaseRate1").val();
	var qqpayAppendRate1 = $("#qqpayAppendRate1").val();
	var qqpayAppendMoney1 = $("#qqpayAppendMoney1").val();
	console.log(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/updateFqStoreRate.do",
		data : {
			"id" : id,
			"wechatBaseRate" : wechatBaseRate1,
			"wechatAppendRate" : wechatAppendRate1,
			"wechatAppendMoney" : wechatAppendMoney1,
			"alipayBaseRate" : alipayBaseRate1,
			"alipayAppendRate" : alipayAppendRate1,
			"alipayAppendMoney" : alipayAppendMoney1,
			"qqpayBaseRate" : qqpayBaseRate1,
			"qqpayAppendRate" : qqpayAppendRate1,
			"qqpayAppendMoney" : qqpayAppendMoney1,
		},
		success : function(storeRate) {
			if (storeRate == "success") {
				window.location.href = "/managermall/systemmall/finance/rateList.do";
			}
		},
		error : function(ra) {
			console.log(ra);
		}
	});
}
function detail(id) {
	console.log(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/detail.do",
		data : {
			"ids" : id,
			},
		success : function(rate) {
			$("#id").val(rate.id);
			$("#rateName1").html(rate.rateName);
			$("#wechatBaseRate1").val(rate.wechatBaseRate);
			$("#wechatAppendRate1").val(rate.wechatAppendRate);
			$("#wechatAppendMoney1").val(rate.wechatAppendMoney);
			$("#alipayBaseRate1").val(rate.alipayBaseRate);
			$("#alipayAppendRate1").val(rate.alipayAppendRate);
			$("#alipayAppendMoney1").val(rate.alipayAppendMoney);
			$("#qqpayBaseRate1").val(rate.qqpayBaseRate);
			$("#qqpayAppendRate1").val(rate.qqpayAppendRate);
			$("#qqpayAppendMoney1").val(rate.qqpayAppendMoney);
			document.getElementById('modifyrate').style.display='block';
			document.getElementById('fade').style.display='block'
		},
		error : function(e) {
			console.log(e);
		}
	});
}