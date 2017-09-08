function smsg(id){
	$.ajax({
		async : false,
		type : "POST",
		url : '/managermall/seller/wxmessage/sendMessage.do',
		data : {"id":id},
	    success : function(data) {
			if(data.errcode == 0){
				alert("申请发送成功,请耐心等待系统推送");
				window.location.reload();
			}else
				alert("发送失败:"+data.errmsg);
		},
		error : function(e) {
			alert("error");
			return false;
		}
	});
}