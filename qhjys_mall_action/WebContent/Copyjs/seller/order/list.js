$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};

$.fn.datebox.defaults.parser = function(s) {
	if (!s)
		return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
};
$(function (){
	$("#setDeliveryFrom").submit(function() {
		$("#setDeliveryFrom label[class=one1]").remove();
		var html = $('<label class="one1" style="width:180px;">请输入快递供应商</label>');
		var val = $("#express").val().trim();
		if (val == "") {
			$("#express").after(html);
		}
		var html = $('<label class="one1" style="width:180px;">请输入快递单号</label>');
		var val = $("#expressId").val().trim();
		if (val == "") {
			$("#expressId").after(html);
		}
		if($("#setDeliveryFrom label[class=one1]").length > 0){
			return false;
		}
		var orderId = $("#orderId").val();
		var express = $("#express").val();
		var expressId = $("#expressId").val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/order/delivery.do',
			data : {"orderId" : orderId, "expressId" : expressId, "express" :express},
			success : function(data) {
				if(data == "success"){
					alert("发货成功");
					window.location.href = '/managermall/seller/order/unfinishOrder.do';
				}else if(data == "error"){
					alert("发货失败");
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
	});
});
function setDelivery(orderId){
	$("#orderId").val(orderId);
	$("#setDelivery").show();
}