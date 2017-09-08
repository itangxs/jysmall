$(function() {
	$.ajax({
		async : false,
		type : "POST",
		url : "/managermall/systemmall/qdmanage/queryFqBranch.do",
		success : function(a) {
			$("#branchId2,#branchId1").combobox({
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
							$("#teamId2,#teamId1").combobox({
								data : d.fqTeamList,
								valueField : "id",
								textField : "principal",
								editable : false
							});
						}
					});
				}
			});
		}
	});
	$("#branchId").combobox("setValue", $("#branchId").val());
	$("#teamId").combobox("setValue", $("#teamId").val());
});

function clickDelete(id){
	$("#ywy_id").css("display","none");
	$("#ywy_id").html(id);
	document.getElementById('qud-sc01').style.display='block';
	document.getElementById('fade').style.display='block';
}

function deleteFqClerk(){
	var id = $("#ywy_id").html();
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/qdmanage/deleteFqClerk.do",
		data : {'id':id},
		success : function(result) {
			document.getElementById('qud-sc01').style.display='none';
			document.getElementById('fade').style.display='none';
			if(result=="success") {
				window.location.reload();
				alert("删除成功");
			}else if(result=="cannot") {
				document.getElementById('qud-sc02').style.display='block';
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
	$("#ywy_id").html(id);
	$.ajax({
		type : "post",
		url : "/managermall/systemmall/qdmanage/fqClerkDetail.do",
		data : {'id':id},
		success : function(fqClerk) {
	    	$("#clerk1").val(fqClerk.clerkName);
	    	$("#phoneNum1").val(fqClerk.phoneNum);
	    	$("#branchId1").combobox("setValue",fqClerk.branchId);
	    	$("#branchId1").combobox("setText",fqClerk.branchName);
	    	$("#teamId1").combobox("setValue",fqClerk.teamId);
	    	$("#teamId1").combobox("setText",fqClerk.teamName);
	    	if(fqClerk.workDate != null) {
	    		var date = new Date(fqClerk.workDate);
		    	$("#workDate1").val(date.getFullYear().toString()+"-"+(date.getMonth()+1)+"-"+date.getDate());
	    	}
	    	$("#commission1").val(fqClerk.commission);
	    	document.getElementById('qud-xg').style.display='block';
	    	document.getElementById('fade').style.display='block';
		},
		error : function() {
			alert("服务器忙");
		}
	});
}

function update(){
	var clerk = $("#clerk1").val();
	var phoneNum = $("#phoneNum1").val();
	var branchId = $("#branchId1").combobox("getValue");
	var branchName = $("#branchId1").combobox("getText");
	var teamId = $("#teamId1").combobox("getValue");
	var teamName = $("#teamId1").combobox("getText");
	var workDate = $("#workDate1").val();
	var commission = $("#commission1").val();
	if(phoneNum == "" || branchId == "" || teamId == ""){
		alert("请输入完整信息");
	}else{
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if(!reg.test(phoneNum)) {
			alert("手机号码必须是十一位数字");
		}else {
			$.ajax({
				type : "post",
				url : "/managermall/systemmall/qdmanage/updateFqClerk.do",
				data : {'id':$("#ywy_id").html(),'clerk':clerk,'branchId':branchId,'branchName':branchName,'teamName':teamName,'teamId':teamId,'phoneNum':phoneNum,'workDate':workDate,'commission':commission},
				success : function(result) {
					document.getElementById('qud-xg').style.display='none';
					document.getElementById('fade').style.display='none';
					if(result=="success") {
						window.location.reload();
						alert("修改成功");
					}else {
						alert("修改失败");
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		}
	}
}

function add(){
	var clerk = $("#clerk2").val();
	var teamId = $("#teamId2").combobox("getValue");
	var teamName = $("#teamId2").combobox("getText");
	var phoneNum = $("#phoneNum2").val();
	var branchId = $("#branchId2").combobox("getValue");
	var branchName = $("#branchId2").combobox("getText");
	var workDate = $("#workDate2").datebox('getValue');
	var commission = $("#commission2").val();
	if(clerk == "" || phoneNum == "" || branchId == "" || workDate == "" || teamId == "" || commission == ""){
		alert("请输入完整信息");
	}else {
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if(!reg.test(phoneNum)) {
			alert("手机号码必须是十一位数字");
		}else if(stripscript(clerk)) {
			alert('请勿输入特殊字符或者空格'); 
		}else if(clerk.length > 10) {
			alert('姓名超过输入框限制字符');
		}else {
			$.ajax({
				type : "post",
				url : "/managermall/systemmall/qdmanage/addFqClerk.do",
				data : {'clerk':clerk,'branchId':branchId,'branchName':branchName,'teamName':teamName,'teamId':teamId,'phoneNum':phoneNum,'workDate':workDate,'commission':commission},
				success : function(result) {
					document.getElementById('qud-xz').style.display='none';
					document.getElementById('fade').style.display='none';
					if(result=="success") {
						window.location.reload();
						alert("新增成功");
					}else {
						alert("新增失败");
					}
				},
				error : function() {
					alert("服务器忙");
				}
			});
		}
	}
}

function Excel(){
	var b=$(".submit9").serialize();
	var a="/managermall/systemmall/qdmanage/excelFqClerk.do?"+b;
	window.open(a);
}

function stripscript(obj) { 
	var specialKey = "[`~!#$^&*=|{}':;',\\[\\].<>/?~！#￥……&*|{}【】‘；：”“'。，、？]‘'";
	for (var i=0;i<obj.length;i++) {
		var flg = specialKey.indexOf(obj[i]) >= 0; 
		if(flg) {
			return true;
		}
	}
	var block = /\s/g.test(obj);
	return block;
} 

function keyword() { 
	var clerkId = $('#cId').val();
	if (clerkId != "") {
		if (!/[0-9]{1,}/.test(clerkId)) {
			alert('业务员ID输入类型错误');
			return false;
		}
	}
	return true;
} 