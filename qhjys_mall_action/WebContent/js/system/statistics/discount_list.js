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
function Excel() {
	var b = $("#from").serialize();
	var a = "/managermall/systemmall/statistics/discountListExcel.do?" + b;
	window.open(a)
};

function changeiscash(){
	var iscashs = new Array();
	$('input:radio:checked').each(function(){
	    iscashs.push($(this).val())
	});
	if (iscashs.length > 0) {
		$.ajax({
			async : false,
			type : "post",
			url : "/managermall/systemmall/statistics/updateOrderCash.do",
			data : "iscashs="+iscashs,
			success : function(g) {
				if (g == "SUCCESS") {
					alert("修改成功");
					window.location.reload();
				} else {
					alert("修改失败");
				}
			},
			error : function() {
				alert("服务器忙");
			}
		});
		
	}
}