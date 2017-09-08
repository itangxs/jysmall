function xingjian(){
	window.location.href = "/managermall/systemmall/ad/hongbao_create.do";
}

function updateStatus(status,id){
	if (confirm("修改状态?")) {
		var b = "/managermall/systemmall/ad/update_status.do";
		var a = "id=" + id + "&status=" + status;
		$.ajax({
			async : false,
			type : "post",
			url : b,
			data : a,
			success : function(g) {
				if (g == "success") {
					$("#from").submit();
				} else if (g == "already") {
					alert("已存在正在进行的活动");
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

function deleteactivity(id) {
	if (confirm("确定删除?")) {
		var b = "/managermall/systemmall/ad/delete.do";
		var a = "id=" + id;
		$.ajax({
			async : false,
			type : "post",
			url : b,
			data : a,
			success : function(g) {
				if (g == "success") {
					alert("删除成功");
					$("#from").submit();
				} else {
					alert("删除失败");
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


