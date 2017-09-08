$(function(){
	var orderId = $("#orderId").val();
	var id = $("#express").val();
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/account/order/kuaidiapi.do',
		data : {"order" : orderId, "id" : id},
		dataType : 'json',
		success : function(data) {
			for(var i = 0; i < data.data.length; i++){
				$("#orderdetail").append("<li>"+data.data[i].time+data.data[i].content+"</li>")
			}
		},
		error : function(e) {
			alert("error");
			return false;
		}
	});
});