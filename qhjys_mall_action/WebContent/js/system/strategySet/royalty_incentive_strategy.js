function clickDelete(id){
	$("#fcr_id").val(id);
	document.getElementById('jlcl-sc01').style.display='block';
	document.getElementById('fade').style.display='block';
}

function deleteFqCommissionRole(){
	var id = $("#fcr_id").val();
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/deleteFqCommissionRole.do",
		data : {'id':id},
		success : function(result) {
			document.getElementById('jlcl-sc01').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success") {
				window.location.reload();
				alert("删除成功");
			}else if(result=="cannot") {
				document.getElementById('jlcl-sc02').style.display='block';
				document.getElementById('fade').style.display='block';
			}else {
				alert("删除失败");
			}
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function detail(id){
	$("#fcr_id").val(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/finance/fqCommissionRoleDetail.do",
		data : {'id':id},
		success : function(fqCommissionRole) {
			$("#roleName1").val(fqCommissionRole.roleName);
			$("#teamProportion1").val(fqCommissionRole.teamProportion);
			$("#clerkProportion1").val(fqCommissionRole.clerkProportion);
	    	document.getElementById('jlcl-xg').style.display='block';
			document.getElementById('fade').style.display='block';
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function update(){
	var roleName = $("#roleName1").val();
	var teamProportion = $("#teamProportion1").val();
	var clerkProportion = $("#clerkProportion1").val();
	if(roleName == "" || teamProportion == "" || clerkProportion == "") {
		alert("请输入完整信息");
	}else {
		$.ajax({
			type : "post",
			url : "/managermall/systemmall/finance/updateFqCommissionRole.do",
			data : {'id':$("#fcr_id").val(),'teamProportion':teamProportion,'clerkProportion':clerkProportion},
			success : function(result) {
				document.getElementById('jlcl-xg').style.display='none';
				document.getElementById('fade').style.display='none';
				if(result=="success") {
					window.location.reload();
					alert("修改成功");
				}else {
					alert("修改失败");
				}
			},
			error : function() {
				alert("信息错误，请重新输入");
			}
		});
	}
}

function add(){
	var roleName = $("#roleName").val();
	var teamProportion = $("#teamProportion").val();
	var clerkProportion = $("#clerkProportion").val();
	if(roleName == "" || teamProportion == "" || clerkProportion == "") {
		alert("请输入完整信息");
	}else if(!/^[\u4e00-\u9fa5a-zA-Z0-9]{1,14}$/.test(roleName)) {
		alert('超过字符限制');
	}else {
		$.ajax({
			type : "post",
			url : "/managermall/systemmall/finance/addFqCommissionRole.do",
			data : {'roleName':roleName,'teamProportion':teamProportion,'clerkProportion':clerkProportion},
			success : function(result) {
				document.getElementById('jlcl-xz').style.display='none';
				document.getElementById('fade').style.display='none';
				if(result=="success") {
					window.location.reload();
					alert("新增成功");
				}else {
					alert("新增失败");
				}
			},
			error : function() {
				alert("信息错误，请重新输入");
			}
		});
	}
}

function Excel(){
	var b=$(".submit9").serialize();
	var a="/managermall/systemmall/finance/excelFqCommissionRole.do?"+b;
	window.open(a);
}