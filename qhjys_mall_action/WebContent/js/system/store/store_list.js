$(function() {
	$.ajax({
		async : false,
		type : "POST",
		url : "/queryProvinceInfo.do",
		success : function(a) {
			$("#licenseProvince").combobox({
				data : a.provinceList,
				valueField : "id",
				textField : "name",
				editable : false,
				onChange : function(b) {
					var c = b;
					$.ajax({
						async : false,
						type : "POST",
						data : {
							provinceId : c
						},
						url : "/queryCityInfo.do",
						success : function(d) {
							$("#licenseCity").combobox({
								data : d.cityInfoList,
								valueField : "id",
								textField : "name",
								editable : false,
								onChange : function(e) {
									var f = e;
									$.ajax({
										async : false,
										type : "POST",
										data : {
											cityId : f
										},
										url : "/queryDistrictInfo.do",
										success : function(g) {
											$("#licenseArea").combobox({
												data : g.districtInfoList,
												valueField : "id",
												textField : "name",
												editable : false
											});
										}
									});
								}
							});
						}
					});
				}
			});
		}
	});
	$("#licenseProvince").combobox("setValue", $("#province1").val());
	$("#licenseCity").combobox("setValue", $("#city1").val());
	$("#licenseArea").combobox("setValue", $("#area1").val());
});
$.fn.datebox.defaults.formatter = function(b) {
	var e = b.getFullYear();
	var a = b.getMonth() + 1;
	var c = b.getDate();
	return e + "-" + (a < 10 ? ("0" + a) : a) + "-" + (c < 10 ? ("0" + c) : c);
};
$.fn.datebox.defaults.parser = function(c) {
	if (!c) {
		return new Date();
	}
	var b = c.split("-");
	var f = parseInt(b[0], 10);
	var a = parseInt(b[1], 10);
	var e = parseInt(b[2], 10);
	if (!isNaN(f) && !isNaN(a) && !isNaN(e)) {
		return new Date(f, a - 1, e);
	} else {
		return new Date();
	}
};
function gehang() {
	var a = document.getElementsByTagName("tr");
	a[0].style.background = "#00FF66";
	a[a.length - 1].style.background = "#00FF66";
}
function xuan(d) {
	var a = document.getElementsByName("ids");
	if (d == "qx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = true;
		}
	}
	if (d == "fx") {
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = false;
		}
	}
	if (d == "qxx") {
		var b = document.getElementById("quan");
		for (var c = 0; c <= a.length; c++) {
			a[c].checked = b.checked;
		}
	}
	if (d == "dx") {
		var a = document.getElementsByName("ids");
		var f = true;
		var b = document.getElementsByName("quan");
		for (var c = 0; c <= a.length; c++) {
			if (a[c].checked) {
				f = false;
			}
		}
	}
}
function updateStauts(c) {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改审核状态?")) {
			var b = "updateStoreStauts.do";
			var a = "ids=" + f + "&status=" + c;
			$.ajax({
				async : false,
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
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
		} else {
		}
	} else {
		alert("请先选择要审核的用户");
	}
}

function updateScope(mark){
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改授权状态?")) {
			var b = "updateScope.do";
			var a = "ids=" + f + "&scope=" + mark;
			$.ajax({
				async : false,
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
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
		} else {
		}
	} else {
		alert("请先选择要更改的店铺");
	}
}

function updateCashier(c) {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改收银台状态?")) {
			var b = "/managermall/systemmall/store/updateCashierStatus.do";
			var a = "ids=" + f + "&cashierStatus=" + c;
			$.ajax({
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
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
		} else {
		}
	} else {
		alert("请先选择要修改的店铺");
	}
}

function updateOrder(c,judge) {
	var d = document.getElementsByName("ids");
	var f = new Array();
	for (var e = d.length - 1; e >= 0; e--) {
		if (d[e].checked == true) {
			f.push(d[e].value);
		}
	}
	if(null == judge){
		judge = 1;
	}
	if (f.length > 0) {
		if (confirm("id=" + f + "修改点餐状态?")) {
			var b = "/managermall/systemmall/store/updateOrderStatus.do";
			var a = "ids=" + f + "&orderStatus=" + c+ "&judge=" + judge;
			$.ajax({
				type : "post",
				url : b,
				data : a,
				success : function(g) {
					if (g == "success") {
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
		} else {
		}
	} else {
		alert("请先选择要修改的店铺");
	}
}

function del(c) {
	if (confirm("确定要删除吗?")) {
		var b = "deleteStore.do";
		var a = "id=" + c;
		$.ajax({
			type : "post",
			url : b,
			data : a,
			success : function(d) {
				if (d == "success") {
					alert("删除成功");
					window.location.href = window.location.href;
				} else {
					alert("删除失败");
					window.location.href = window.location.href;
				}
			},
			error : function() {
				alert("服务器忙");
			}
		});
	} else {
	}
};

function getId(storeId,rateId,statementPeriod){
	$("#storeId").val(storeId);
	$("#period").val(statementPeriod);
	 $("#rate").val(rateId);
	document.getElementById('feilvset').style.display='block';
	document.getElementById('fade').style.display='block';
}

function setRate(){
	var url = "/managermall/systemmall/store/setStoreRate.do";
	var rateId = $("#rate").val();
	var period = $("#period").val();
	$.ajax({
		type : "post",
		url : url,
		data : {'storeId':$("#storeId").val(),'rateId':rateId,'period':period},
		success : function(result) {
			document.getElementById('feilvset').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success"){
				window.location.reload();
				alert("设置费率成功");
			}else{
				alert("设置费率失败");
			}
			return true;
		},
		error : function() {
			alert("服务器忙");
			return false;
		}
	});
	return true;
}