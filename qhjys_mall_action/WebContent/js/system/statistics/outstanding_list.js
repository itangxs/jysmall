$(function(){
	$.ajax({
		async : false,
		type : "POST",
		url : "/managermall/systemmall/qdmanage/queryFqBranch.do",
		success : function(a) {
			$("#branchId").combobox({
				data : a.fqBranchList,
				valueField : "id",
				textField : "branchName",
				editable : false,
				onChange : function(b) {
					var c = b;
					$.ajax({
						async : false,
						type : "POST",
						data : {
							branchId : c
						},
						url : "/managermall/systemmall/qdmanage/queryFqTeam.do",
						success : function(d) {
							$("#teamId").combobox({
								data : d.fqTeamList,
								valueField : "id",
								textField : "principal",
								editable : false,
								onChange : function(b) {
									var c = b;
									$.ajax({
										async : false,
										type : "POST",
										data : {
											teamId : c
										},
										url : "/managermall/systemmall/qdmanage/queryFqClerk.do",
										success : function(d) {
											$("#clerkId").combobox({
												data : d.fqClerkList,
												valueField : "id",
												textField : "clerk",
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
	$("#branchId").combobox("setValue", $("#branchId1").val());
	$("#teamId").combobox("setValue", $("#teamId1").val());
	$("#clerkId").combobox("setValue", $("#clerkId1").val());
})

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
	var a = "/managermall/systemmall/statistics/outstandingListExcel.do?" + b;
	window.open(a)
};

