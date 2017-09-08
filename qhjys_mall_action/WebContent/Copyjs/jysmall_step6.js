$(function() {
	$("#signupForm").submit(function() {
		$("#signupForm label[class=one1]").remove();
		if($("#categoryId").val().trim() == ""){
			var html = $('<label class="one1" style="width:120px;">请输入主营类目</label>');
			$("#categoryId").parent().append(html);
		}
		if($("#signupForm label[class=one1]").length > 0){
			return false;
		}
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/addCategory.do',
			data : $(this).serialize(),
			success : function(data) {
				 window.location.href = '/managermall/seller/addBankInfo.do';
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
		return false;
	});
});