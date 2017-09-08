function withdraws() {
	var a = document.getElementsByName("ids");
	var c = new Array();
	for (var b = 0; b < a.length; b++) {
		if (a[b].checked == true) {
			c[b] = a[b].value
		}
	}
	if (c.length > 0) {
		id = c.join(",");
		if (confirm("是否确认给该商家提现?")) {
			$
					.ajax({
						async : false,
						type : "POST",
						url : "/managermall/systemmall/finance/auditSellerCashSaveWithd.do",
						data : {
							id : id
						},
						success : function(d) {
							if (d == "审核成功") {
								alert(d);
								window.location.href = "/managermall/systemmall/finance/sellerWithdrawsRecordList.do"
							} else {
								alert(d)
							}
						}
					})
		}
	} else {
		alert("请先选择要审核的商家")
	}
}

// 
function yichuzhang() {
	var a = document.getElementsByName("ids");
	var c = new Array();
	for (var b = 0; b < a.length; b++) {
		if (a[b].checked == true) {
			c[b] = a[b].value;
		}
	}
	if (c.length > 0) {
		id = c.join(",");
		if (confirm("是否确认出账?")) {
			$.ajax({
				async : false,
				type : "POST",
				url : "/managermall/systemmall/finance/auditSellerCashWithdrawOff.do",
				data : {
					id : id
				},
				success : function(d) {
					if (d == "审核成功") {
						alert(d);
						window.location.href = "/managermall/systemmall/finance/sellerWithdrawsRecordList.do";
					} else {
						alert(d);
					}
				}
			})
		}
	} else {
		alert("请先选择要出账的商家");
	}
}

function unwithdraws() {
	var a = document.getElementsByName("ids");
	var c = new Array();
	for (var b = 0; b < a.length; b++) {
		if (a[b].checked == true) {
			c[b] = a[b].value
		}
	}
	if (c.length > 0) {
		id = c.join(",");
		if (confirm("是否确认拒绝该商家提现请求?")) {
			$
					.ajax({
						async : false,
						type : "POST",
						url : "/managermall/systemmall/finance/unauditSellerCashSaveWithd.do",
						data : {
							id : id
						},
						success : function(d) {
							if (d == "审核成功") {
								alert(d);
								window.location.href = "/managermall/systemmall/finance/sellerWithdrawsRecordList.do"
							} else {
								alert(d)
							}
						}
					})
		}
	} else {
		alert("请先选择要审核的商家")
	}
}
function selectAll() {
	var b = document.getElementsByName("ids");
	if ($("#selectAll").is(":checked") == true) {
		for (var a = 0; a < b.length; a++) {
			b[a].checked = true
		}
	} else {
		for (var a = 0; a < b.length; a++) {
			b[a].checked = false
		}
	}
}
function Excel() {
	var b = $("#from").serialize();
	var a = "/managermall/systemmall/finance/sellerWithdrawsRecordListExcel.do?"
			+ b;
	window.open(a)
};