$(function() { 
	$("#inputinfo").focus(function(){
		var infohid = $("#infohid").val();
		var inputinfo = $("#inputinfo").val();
		if (infohid == inputinfo) {
			$("#inputinfo").val("");
		}
	}).blur(function(){
		var infohid = $("#infohid").val();
		var inputinfo = $("#inputinfo").val();
		if (inputinfo == '') {
			$("#inputinfo").val(infohid);
		}
	});
	$("#infobut").click(function(){
		var project = $("#project").val();
		var userId = $("#userId1").val();
		var inputinfo = $("#inputinfo").val();
		$.ajax({
			async : false,
			type : "POST",
			url : 'dousertask.do',
			data : {"project":project,"userId":userId,"inputinfo":inputinfo},
			success : function(data) {
				if (data == 1001) {
					alert("缺少参数");
				}else if (data == 1002) {
					alert("签名验证失败");
				}else if (data == 1003) {
					alert("提交重复");
				}else if (data == 1004) {
					alert("wid不存在");
				}else if (data == 1005) {
					alert("tid不存在");
				}else if (data == 1006) {
					alert("userid不存在");
				}else if (data == 1007) {
					alert("同任务同info数据已被提交");
				}else if (data == 1008) {
					alert("此任务此userid已提交");
				}else if (data == 1009) {
					alert("非法IP");
				}else if (data == 1011) {
					alert("IP提交过于频繁");
				}else if (data == 1010) {
					alert("未知错误");
				}
				window.location.reload();
			},
			error : function(e) {
				alert("信息提交异常");
				
			}
		});
	});
}); 
