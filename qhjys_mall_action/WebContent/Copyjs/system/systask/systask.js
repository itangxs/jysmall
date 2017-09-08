
function changeno(id1,id2){
	$.ajax({
		async : true,
		type : "POST",
		data:{'id1':id1,'id2':id2},
		url : '/managermall/systemmall/taskQuestion/changeNo.do',
		success : function(data) {
			if (data == "success") {
				window.location.href=window.location.href;
			}
		}
	});
}