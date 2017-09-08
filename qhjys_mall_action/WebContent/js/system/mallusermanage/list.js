$.fn.datebox.defaults.formatter = function(b) {
	var e = b.getFullYear();
	var a = b.getMonth() + 1;
	var c = b.getDate();
	return e + "-" + (a < 10 ? ("0" + a) : a) + "-" + (c < 10 ? ("0" + c) : c)
};
$.fn.datebox.defaults.parser = function(c) {
	if (!c) {
		return new Date()
	}
	var b = c.split("-");
	var f = parseInt(b[0], 10);
	var a = parseInt(b[1], 10);
	var e = parseInt(b[2], 10);
	if (!isNaN(f) && !isNaN(a) && !isNaN(e)) {
		return new Date(f, a - 1, e)
	} else {
		return new Date()
	}
};
function gehang() {
	var a = document.getElementsByTagName("tr");
	a[0].style.background = "#00FF66";
	a[a.length - 1].style.background = "#00FF66"
}
function xuan(d) {
	var a = document.getElementsByName("ids");
	if (d == "qx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = true
		}
	}
	if (d == "fx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = false
		}
	}
	if (d == "qxx") {
		var b = document.getElementById("quan");
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = b.checked
		}
	}
	if (d == "dx") {
		var a = document.getElementsByName("ids");
		var f = true;
		var b = document.getElementsByName("quan");
		for (var c = 0; c <= a.length; c++) {
			if (a[c].checked) {
				f = false
			}
		}
	}
}
function resetPassword() {
	var c = document.getElementsByName("ids");
	var e = new Array();
	for (var d = c.length - 1; d >= 0; d--) {
		if (c[d].checked == true) {
			if (c[d].value >= 1) {
				e.push(c[d].value)
			}
		}
	}
	if (e.length < 0) {
		if (confirm("id=" + e + "确定重置吗?")) {
			var b = "resetPassword.do";
			var a = "ids=" + e;
			$.ajax({
				type : "post",
				url : b,
				data : a,
				success : function(f) {
					if (f == "success") {
						alert("重置成功");
						window.location.href = window.location.href
					} else {
						alert("重置失败");
						window.location.href = window.location.href
					}
				},
				error : function() {
					alert("服务器忙")
				}
			})
		}
	} else {
		alert("请先选择要重置的用户")
	}
}
function status1(f) {
	var g = "";
	if (f == "off") {
		g = "关闭"
	} else {
		g = "开启"
	}
	var c = document.getElementsByName("ids");
	var e = new Array();
	for (var d = c.length - 1; d >= 0; d--) {
		if (c[d].checked == true) {
			if (c[d].value >= 1) {
				e.push(c[d].value)
			}
		}
	}
	if (e.length > 0) {
		if (confirm("id=" + e + "确定要" + g + "吗?")) {
			var b = "openandClose.do";
			var a = "type=" + f + "&ids=" + e;
			$.ajax({
				type : "post",
				url : b,
				data : a,
				success : function(h) {
					if (h == "success") {
						alert("重置成功");
						window.location.href = window.location.href
					} else {
						alert("重置失败");
						window.location.href = window.location.href
					}
				},
				error : function() {
					alert("服务器忙")
				}
			})
		}
	} else {
		alert("请先选择要" + g + "的用户")
	}
};