$(function() {
	$("#form1").submit(function() {
		$("#form1 label[class=one1]").remove();
		if($("#sellerReplyContent").val().trim() == ""){
			var html = $('<label class="one1" style="width:120px;">请输入商家回复内容</label>');
			$("#sellerReplyContent").parent().append(html);
		}
		if($("#form1 label[class=one1]").length > 0){
			return false;
		}
		var sellerReplyContent = $("#sellerReplyContent").val();
		var id = $("#id").val();
		var token = $("#token").val();
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/commoditymanage/sellerEvaluate.do',
			data : {"id":id,"sellerReplyContent":sellerReplyContent,"token":token},
			success : function(data) {
				if(data == "success"){
					alert("回复评论成功");
					window.location.href = '/managermall/seller/commoditymanage/queryEvaluateList.do';
				}else if(data == "failure"){
					alert("回复评论失败");
				}else if(data == "表单重复提交"){
					alert("表单重复提交");
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