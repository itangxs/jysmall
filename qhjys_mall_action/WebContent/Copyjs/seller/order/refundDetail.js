$(function() {
	$("#rejectReason").blur(function(){
		if($("#rejectReason").val().trim() == ""){
			$("#rejectReason").next().remove();
			var html = $('<label class="one1" style="width:200px;">请输入理由</label>');
			$("#rejectReason").after(html);
		}
	});
	$("#form1").submit(function() {
		$("#form1 label[class=one1]").remove();
		if($("#rejectReason").val().trim() == ""){
			$("#rejectReason").next().remove();
			var html = $('<label class="one1" style="width:200px;">请输入理由</label>');
			$("#rejectReason").after(html);
		}
		if($("#form1 label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/order/auditRefund.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "success"){
					alert("审核成功");
					window.location.href = '/managermall/seller/order/refundOrder.do';
				}else if(data == "表单重复提交"){
					alert("表单重复提交");
				}else{
					alert("审核失败");
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
function refund(){
	window.location.href = '/managermall/seller/order/refundOrder.do';
}