function createNewCard(){
	window.location.href = "/managermall/systemmall/ad/bcard_create.do";
}

function updateStatus(status,id){
	if (confirm("修改状态?")) {
		var b = "/managermall/systemmall/ad/bcard_update_status.do";
		var a = "id=" + id + "&status=" + status;
		$.ajax({
			async : false,
			type : "post",
			url : b,
			data : a,
			success : function(g) {
				if (g == "success") {
					$("#from").submit();
				} else {
					alert("修改失败");
					$("#from").submit();
				}
			},
			error : function() {
				alert("服务器忙");
			}
		})
	} else {
	}
}

function deletebcard(id) {
	if (confirm("确定删除?")) {
		var b = "/managermall/systemmall/ad/bcard_delete.do";
		var a = "id=" + id;
		$.ajax({
			async : false,
			type : "post",
			url : b,
			data : a,
			success : function(g) {
				if (g == "success") {
					$("#from").submit();
				} else {
					alert("删除失败");
					$("#from").submit();
				}
			},
			error : function() {
				alert("服务器忙")
			}
		})
	} else {
	}
}
