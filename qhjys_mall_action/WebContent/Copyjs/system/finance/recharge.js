$(function() {
	$("#form1").submit(function() {
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/systemmall/finance/recharge.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data == "充值成功"){
					alert(data);
					window.location.href = '/managermall/systemmall/finance/userList.do';
				}else {
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