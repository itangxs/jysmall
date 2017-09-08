function setTab(n){ 
	if(n == 0){
		window.location.href = '/managermall/seller/message/messageCenterList.do?seen=0';
	}else{
		window.location.href = '/managermall/seller/message/messageCenterList.do?seen=1';
	}
}
function deleteMessage(id){
	 if(confirm("确定要删除吗？")){
		$.ajax({
			async : false,
			type : "POST",
			url : '/managermall/seller/message/deleteMessage.do',
			data : {"id": id},
			success : function(data) {
				alert(data.message);
				window.location.href = '/managermall/seller/message/messageCenterList.do?seen=0';
			},
			error : function(e) {
				alert("error");
				return false;
			}
		});
	 }
}