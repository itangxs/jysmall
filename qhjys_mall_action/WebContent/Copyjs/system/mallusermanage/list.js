$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};

$.fn.datebox.defaults.parser = function(s) {
	if (!s)
		return new Date();
	var ss = s.split('-');
	var y = parseInt(ss[0], 10);
	var m = parseInt(ss[1], 10);
	var d = parseInt(ss[2], 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
};

function gehang() {
	// 获取tr节点
	var tr = document.getElementsByTagName("tr");
	// 为第一行添加背景颜色
	tr[0].style.background = "#00FF66";
	// 为最后一行添加背景颜色
	tr[tr.length - 1].style.background = "#00FF66";
}
// 创建全选反选函数
function xuan(type) {
	// 获取name值
	var qcheck = document.getElementsByName("ids");
	// 获取选的按钮
	if (type == "qx") {
		for (var i = 0; i <= qcheck.length; i++) {
			qcheck[i].checked = true;
		}
	}
	if (type == "fx") {
		for (var i = 0; i <= qcheck.length; i++) {
			qcheck[i].checked = false;
		}
	}
	if (type == "qxx") {
		var quan = document.getElementById("quan");
		for (var i = 0; i <= qcheck.length; i++) {
			qcheck[i].checked = quan.checked;
		}
	}
	if (type == "dx") {// 单选
		var qcheck = document.getElementsByName("ids");
		var e = true;
		var quan = document.getElementsByName("quan");
		for (var i = 0; i <= qcheck.length; i++)
			if (qcheck[i].checked)
				e = false;
	}
}

function resetPassword() {// 重置密码
	var input = document.getElementsByName("ids");
	var ids = new Array();
	for (var i = input.length - 1; i >= 0; i--) {
		if (input[i].checked == true)
			if (input[i].value >= 1){      
				ids.push(input[i].value);
			}
	}
	if (ids.length <0) {
		if (confirm("id=" + ids + "确定重置吗?")) {
			var urlval = "resetPassword.do";
			var parma = "ids=" + ids;
			$.ajax({
				type : 'post',
				url : urlval,
				data : parma,
				success : function(msg) {
					if (msg == 'success') {
						alert("重置成功");
						window.location.href = window.location.href;
					} else {
						alert("重置失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		}
	} else
		alert("请先选择要重置的用户");
}

function status1(type) {// 开启 ,关闭
	var o = "";
	if (type == "off")
		o = "关闭";
	else
		o = "开启";
	var input = document.getElementsByName("ids");
	var ids = new Array();
	for (var i = input.length - 1; i >= 0; i--)
		if (input[i].checked == true)
			if (input[i].value >= 1){
				ids.push(input[i].value);
			}
	if (ids.length > 0) {
		if (confirm("id=" + ids + "确定要" + o + "吗?")) {
			var urlval = "openandClose.do";
			var parma = "type=" + type + "&ids=" + ids;
			$.ajax({
				type : 'post',
				url : urlval,
				data : parma,
				success : function(msg) {
					if (msg == 'success') {
						alert("重置成功");
						window.location.href = window.location.href;
					} else {
						alert("重置失败");
						window.location.href = window.location.href;
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		}
	} else
		alert("请先选择要" + o + "的用户");
}