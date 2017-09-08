$(function() {
	$("#bankForm").submit(function() {
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/account/insertBank.do',
			data : $(this).serialize(),
			success : function(data) {
				if(data.message == "添加成功"){
					alert("添加银行卡成功");
					$("#bankForm")[0].reset();
					window.location.href = '/managermall/account/myBank.do?status=2';
				}else if(data.message == "无效的银行卡"){
					alert("无效的银行卡");
				}else if(data.message == "银行卡数量超标"){
					alert("银行卡数量超标");
				}else{
					alert("添加银行卡失败");
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
function addBank(){
	$("#insertBank").show();
}
function closeWindow(){
	$("label[class=one1]").remove();
	$("#bankForm")[0].reset();
	$("#insertBank").hide();
}