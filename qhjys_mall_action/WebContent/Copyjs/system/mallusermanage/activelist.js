
function activeEnabled(activeId) {// 开启 ,关闭
			var urlval = "/managermall/systemmall/active/updateEnable.do";
			var parma = "activeId=" + activeId ;
			$.ajax({
				type : 'post',
				url : urlval,
				data : parma,
				success : function(msg) {
					if (msg == 'success') {
						alert("修改成功");
						window.location.href = window.location.href;
					} else {
						alert("修改失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
}